package demo.fxtexteditor;

import goryachev.fx.TextCellStyle;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/TSegment.class */
public class TSegment {
    public final String text;
    public final int start;
    public final int end;
    public final TextCellStyle style;

    public TSegment(String text, int start, int end, TextCellStyle style) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.style = style;
    }

    public int length() {
        return this.end - this.start;
    }

    public String getText() {
        return this.text.substring(this.start, this.end);
    }

    public boolean contains(int off) {
        return off >= this.start && off < this.end;
    }
}
