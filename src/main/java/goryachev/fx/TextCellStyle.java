package goryachev.fx;

import goryachev.common.util.CKit;
import goryachev.common.util.FH;
import goryachev.common.util.SB;
import goryachev.fx.internal.StandardFxProperties;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/TextCellStyle.class */
public class TextCellStyle implements Cloneable {
    public static final TextCellStyle NONE = new TextCellStyle();
    private Color textColor;
    private Color backgroundColor;
    private boolean bold;
    private boolean italic;
    private boolean strikeThrough;
    private boolean underscore;
    private String style;

    public TextCellStyle(Color fg, Color bg, boolean bold, boolean italic, boolean strikeThrough, boolean underscore) {
        this.textColor = fg;
        this.backgroundColor = bg;
        this.bold = bold;
        this.italic = italic;
        this.strikeThrough = strikeThrough;
        this.underscore = underscore;
    }

    public TextCellStyle(Color fg) {
        this.textColor = fg;
    }

    public TextCellStyle() {
    }

    public TextCellStyle copy() {
        return (TextCellStyle) clone();
    }

    public Object clone() {
        return new TextCellStyle(this.textColor, this.backgroundColor, this.bold, this.italic, this.strikeThrough, this.underscore);
    }

    public boolean isEmpty() {
        return (this.bold || this.italic || this.strikeThrough || this.underscore || this.backgroundColor != null || this.textColor != null) ? false : true;
    }

    public void init(TextCellStyle x) {
        this.backgroundColor = x.backgroundColor;
        this.textColor = x.textColor;
        this.bold = x.bold;
        this.italic = x.italic;
        this.strikeThrough = x.strikeThrough;
        this.underscore = x.underscore;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof TextCellStyle) {
            TextCellStyle c = (TextCellStyle) x;
            return this.bold == c.bold && this.italic == c.italic && this.strikeThrough == c.strikeThrough && this.underscore == c.underscore && CKit.equals(this.textColor, c.textColor) && CKit.equals(this.backgroundColor, c.backgroundColor);
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(TextCellStyle.class);
        return FH.hash(FH.hash(FH.hash(FH.hash(FH.hash(FH.hash(h, this.backgroundColor), this.textColor), this.bold), this.italic), this.strikeThrough), this.underscore);
    }

    public void clear() {
        this.backgroundColor = null;
        this.textColor = null;
        this.bold = false;
        this.italic = false;
        this.strikeThrough = false;
        this.underscore = false;
    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color c) {
        this.backgroundColor = c;
        this.style = null;
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public void setTextColor(Color c) {
        this.textColor = c;
        this.style = null;
    }

    public boolean isBold() {
        return this.bold;
    }

    public void setBold(boolean on) {
        this.bold = on;
        this.style = null;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public void setItalic(boolean on) {
        this.italic = on;
        this.style = null;
    }

    public boolean isStrikeThrough() {
        return this.strikeThrough;
    }

    public void setStrikeThrough(boolean on) {
        this.strikeThrough = on;
        this.style = null;
    }

    public boolean isUnderscore() {
        return this.underscore;
    }

    public void setUnderscore(boolean on) {
        this.underscore = on;
        this.style = null;
    }

    public String getStyle() {
        if (this.style == null) {
            SB sb = new SB(128);
            if (this.textColor != null) {
                StandardFxProperties.textFill(this.textColor).write(sb);
            }
            if (this.bold) {
                StandardFxProperties.fontWeight(StandardFxProperties.BOLD).write(sb);
            }
            if (this.italic) {
                StandardFxProperties.fontStyle("italic").write(sb);
            }
            this.style = sb.toString();
        }
        return this.style;
    }
}
