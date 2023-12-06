package goryachev.fxtexteditor;

import goryachev.fxtexteditor.internal.WrapInfo;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/WrapPos.class */
public class WrapPos implements Comparable<WrapPos> {
    private final int line;
    private final int row;
    private final WrapInfo wrap;

    public WrapPos(int line, int row, WrapInfo wrap) {
        this.line = line;
        this.row = row;
        this.wrap = wrap;
    }

    public int getLine() {
        return this.line;
    }

    public int getRow() {
        return this.row;
    }

    public WrapInfo getWrapInfo() {
        return this.wrap;
    }

    public boolean isAfter(WrapPos p) {
        return compareTo(p) > 0;
    }

    @Override // java.lang.Comparable
    public int compareTo(WrapPos p) {
        int d = this.line - p.line;
        if (d == 0) {
            d = this.row - p.row;
        }
        return d;
    }

    public int getStartGlyphIndex() {
        return this.wrap.getGlyphIndexForRow(this.row);
    }

    public String toString() {
        return "WrapPos[" + this.line + ":" + this.row + "]";
    }
}
