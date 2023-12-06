package goryachev.fxtexteditor;

import goryachev.common.util.FH;
import goryachev.common.util.SB;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/TextPos.class */
public class TextPos implements Comparable<TextPos> {
    private final int line;
    private final int charIndex;

    public TextPos(int line, int charIndex) {
        this.line = line;
        this.charIndex = charIndex;
    }

    public int getLine() {
        return this.line;
    }

    public int getCharIndex() {
        return this.charIndex;
    }

    public int hashCode() {
        int h = FH.hash(TextPos.class);
        return FH.hash(FH.hash(h, this.line), this.charIndex);
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof TextPos) {
            TextPos p = (TextPos) x;
            return this.line == p.line && this.charIndex == p.charIndex;
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(TextPos p) {
        int d = this.line - p.line;
        if (d == 0) {
            return getCharIndex() - p.getCharIndex();
        }
        return d;
    }

    public boolean isBefore(TextPos p) {
        if (this.line < p.line) {
            return true;
        }
        if (this.line == p.line && this.charIndex < p.charIndex) {
            return true;
        }
        return false;
    }

    public boolean isBefore(int line, int pos) {
        if (this.line < line) {
            return true;
        }
        if (this.line == line && getCharIndex() < pos) {
            return true;
        }
        return false;
    }

    public boolean isAtOrBefore(int line, int pos) {
        if (this.line < line) {
            return true;
        }
        if (this.line == line && getCharIndex() <= pos) {
            return true;
        }
        return false;
    }

    public boolean isAfter(int line, int pos) {
        if (this.line > line) {
            return true;
        }
        if (this.line == line && getCharIndex() > pos) {
            return true;
        }
        return false;
    }

    public boolean isAtOrAfter(int line, int pos) {
        if (this.line > line) {
            return true;
        }
        if (this.line == line && getCharIndex() >= pos) {
            return true;
        }
        return false;
    }

    public String toString() {
        SB sb = new SB(32);
        sb.a(Integer.valueOf(this.line));
        sb.a(':');
        sb.a(Integer.valueOf(getCharIndex()));
        return sb.toString();
    }
}
