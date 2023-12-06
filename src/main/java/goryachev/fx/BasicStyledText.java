package goryachev.fx;

import goryachev.fx.internal.ColorMixer;
import goryachev.fx.internal.TextStyleFlags;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/BasicStyledText.class */
public class BasicStyledText implements IStyledText {
    private final String text;
    private final byte[] styles;
    private Color[] textColor;
    private ColorMixer[] background;

    public BasicStyledText(String text) {
        this.text = text;
        this.styles = new byte[text.length()];
    }

    @Override // goryachev.fx.IStyledText
    public String getPlainText() {
        return this.text;
    }

    public String toString() {
        return getPlainText();
    }

    @Override // goryachev.fx.IStyledText
    public int getTextLength() {
        return this.text.length();
    }

    @Override // goryachev.fx.IStyledText
    public TextCellStyle getCellStyle(int ix) {
        Color fg = getForeground(ix);
        Color bg = getBackground(ix);
        byte st = this.styles[ix];
        boolean bold = TextStyleFlags.isBold(st);
        boolean italic = TextStyleFlags.isItalic(st);
        boolean strikeThrough = TextStyleFlags.isStrikeThrough(st);
        boolean underscore = TextStyleFlags.isUnderscore(st);
        return new TextCellStyle(fg, bg, bold, italic, strikeThrough, underscore);
    }

    public Color getBackground(int ix) {
        ColorMixer cs;
        if (this.background != null && (cs = this.background[ix]) != null) {
            return cs.getColor();
        }
        return null;
    }

    public Color getForeground(int ix) {
        if (this.textColor != null) {
            return this.textColor[ix];
        }
        return null;
    }

    @Override // goryachev.fx.IStyledText
    public Color getLineColor() {
        return null;
    }

    @Override // goryachev.fx.IStyledText
    public char charAt(int ix) {
        return this.text.charAt(ix);
    }

    public void setBold(int start, int end, boolean on) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        for (int i = start2; i < end2; i++) {
            TextStyleFlags.setBold(this.styles, i, on);
        }
    }

    public void setItalic(int start, int end, boolean on) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        for (int i = start2; i < end2; i++) {
            TextStyleFlags.setItalic(this.styles, i, on);
        }
    }

    public void setStrikeThrough(int start, int end, boolean on) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        for (int i = start2; i < end2; i++) {
            TextStyleFlags.setStrikeThrough(this.styles, i, on);
        }
    }

    public void setUnderscore(int start, int end, boolean on) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        for (int i = start2; i < end2; i++) {
            TextStyleFlags.setUnderscore(this.styles, i, on);
        }
    }

    public void highlight(int start, int end, Color c) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        if (this.background == null) {
            this.background = new ColorMixer[getTextLength()];
        }
        for (int i = start2; i < end2; i++) {
            ColorMixer cs = this.background[i];
            if (cs == null) {
                cs = new ColorMixer(c);
            } else {
                cs.add(c);
            }
            this.background[i] = cs;
        }
    }

    public void textColor(int start, int end, Color c) {
        int start2 = Math.max(0, start);
        int end2 = Math.min(this.text.length(), end);
        if (this.textColor == null) {
            this.textColor = new Color[getTextLength()];
        }
        for (int i = start2; i < end2; i++) {
            this.textColor[i] = c;
        }
    }
}
