package goryachev.fx.util;

import goryachev.common.util.CKit;
import goryachev.fx.IStyledText;
import goryachev.fx.TextCellMetrics;
import goryachev.fx.TextCellStyle;
import goryachev.fx.internal.GlyphCache;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/util/TextPainter.class */
public class TextPainter {
    protected static final Text proto = new Text();
    private TextCellMetrics metrics;
    private Font font;
    private Font boldFont;
    private Font boldItalicFont;
    private Font italicFont;
    private Canvas canvas;
    private GraphicsContext gx;
    private Color textColor = Color.BLACK;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$geometry$HPos;

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$geometry$HPos() {
        int[] iArr = $SWITCH_TABLE$javafx$geometry$HPos;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[HPos.values().length];
        try {
            iArr2[HPos.CENTER.ordinal()] = 2;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[HPos.LEFT.ordinal()] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[HPos.RIGHT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$javafx$geometry$HPos = iArr2;
        return iArr2;
    }

    public TextPainter() {
        setFont(null);
    }

    public void setFont(Font f) {
        if (f == null) {
            f = Font.font("Monospace", 12.0d);
        }
        this.font = f;
        this.metrics = null;
        this.boldFont = null;
        this.boldItalicFont = null;
        this.italicFont = null;
    }

    public Canvas createCanvas(Region r) {
        Insets m = r.getInsets();
        double w = (r.getWidth() - m.getLeft()) - m.getRight();
        double h = (r.getHeight() - m.getTop()) - m.getBottom();
        this.canvas = new Canvas(w, h);
        this.gx = this.canvas.getGraphicsContext2D();
        this.gx.setFontSmoothingType(FontSmoothingType.GRAY);
        return this.canvas;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public TextCellMetrics textMetrics() {
        if (this.metrics == null) {
            proto.setText("8");
            proto.setFont(this.font);
            Bounds b = proto.getBoundsInLocal();
            int w = CKit.round(b.getWidth());
            int h = CKit.round(b.getHeight());
            this.metrics = new TextCellMetrics(this.font, b.getMinY(), w, h);
        }
        return this.metrics;
    }

    protected Font getFont(TextCellStyle st) {
        if (st.isBold()) {
            if (st.isItalic()) {
                if (this.boldItalicFont == null) {
                    this.boldItalicFont = Font.font(this.font.getFamily(), FontWeight.BOLD, FontPosture.ITALIC, this.font.getSize());
                }
                return this.boldItalicFont;
            }
            if (this.boldFont == null) {
                this.boldFont = Font.font(this.font.getFamily(), FontWeight.BOLD, FontPosture.REGULAR, this.font.getSize());
            }
            return this.boldFont;
        } else if (st.isItalic()) {
            if (this.italicFont == null) {
                this.italicFont = Font.font(this.font.getFamily(), FontWeight.NORMAL, FontPosture.ITALIC, this.font.getSize());
            }
            return this.italicFont;
        } else {
            return this.font;
        }
    }

    public void clear() {
        this.gx.clearRect(0.0d, 0.0d, this.canvas.getWidth(), this.canvas.getHeight());
    }

    public void fill(Color c) {
        this.gx.setFill(c);
        this.gx.fillRect(0.0d, 0.0d, this.canvas.getWidth(), this.canvas.getHeight());
    }

    protected double computeStartX(int cellWidth, int textLength, HPos alignment) {
        switch ($SWITCH_TABLE$javafx$geometry$HPos()[alignment.ordinal()]) {
            case 1:
            default:
                return 0.0d;
            case 2:
                double x = (this.canvas.getWidth() - (textLength * cellWidth)) / 2.0d;
                if (x > 0.0d) {
                    return x;
                }
                return 0.0d;
            case 3:
                double x2 = this.canvas.getWidth() - (textLength * cellWidth);
                if (x2 > 0.0d) {
                    return x2;
                }
                return 0.0d;
        }
    }

    public void paint(String text, HPos alignment) {
        int i;
        TextCellMetrics tm = textMetrics();
        int len = text.length();
        double xoffset = computeStartX(tm.cellWidth, len, alignment);
        this.gx.setFont(this.font);
        this.gx.setFill(this.textColor);
        for (i = 0; i < len; i = i + 1) {
            double x = xoffset + (i * tm.cellWidth);
            if (x < 0.0d) {
                i = x + tm.cellWidth < 0.0d ? i + 1 : 0;
            } else if (x > this.canvas.getWidth()) {
                return;
            }
            char c = text.charAt(i);
            String s = GlyphCache.get(c);
            if (s != null) {
                this.gx.fillText(s, x, -tm.baseline, tm.cellWidth);
            }
        }
    }

    public void paint(IStyledText styledText, HPos alignment) {
        int i;
        TextCellMetrics tm = textMetrics();
        int len = styledText.getTextLength();
        double xoffset = computeStartX(tm.cellWidth, len, alignment);
        for (i = 0; i < len; i = i + 1) {
            double x = xoffset + (i * tm.cellWidth);
            if (x < 0.0d) {
                i = x + tm.cellWidth < 0.0d ? i + 1 : 0;
            } else if (x > this.canvas.getWidth()) {
                return;
            }
            TextCellStyle style = styledText.getCellStyle(i);
            if (style == null) {
                style = TextCellStyle.NONE;
            }
            Color bg = style.getBackgroundColor();
            if (bg != null) {
                this.gx.setFill(bg);
                this.gx.fillRect(x, 0.0d, tm.cellWidth, tm.cellHeight);
            }
            if (style.isUnderscore()) {
                this.gx.setFill(this.textColor);
                this.gx.fillRect(x, tm.cellHeight - 1, tm.cellWidth, 1.0d);
            }
            char c = styledText.charAt(i);
            String s = GlyphCache.get(c);
            if (s != null) {
                Color fg = style.getTextColor();
                if (fg == null) {
                    fg = this.textColor;
                }
                Font f = getFont(style);
                this.gx.setFont(f);
                this.gx.setFill(fg);
                this.gx.fillText(s, x, -tm.baseline, tm.cellWidth);
                if (style.isStrikeThrough()) {
                    this.gx.setFill(this.textColor);
                    this.gx.fillRect(x, tm.cellHeight / 2, tm.cellWidth, 1.0d);
                }
            }
        }
    }
}
