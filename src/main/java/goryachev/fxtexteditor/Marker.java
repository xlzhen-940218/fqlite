package goryachev.fxtexteditor;

import goryachev.common.util.Assert;
import goryachev.common.util.FH;
import goryachev.common.util.SB;
import goryachev.fxtexteditor.internal.Markers;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/Marker.class */
public class Marker implements Comparable<Marker> {
    public static final Marker ZERO = new Marker();
    private int line;
    private int charIndex;

    public Marker(Markers owner, int line, int charIndex) {
        Assert.notNull(owner, "owner");
        this.line = line;
        this.charIndex = charIndex;
    }

    private Marker() {
        this.line = 0;
        this.charIndex = 0;
    }

    public int hashCode() {
        int h = FH.hash(Marker.class);
        return FH.hash(FH.hash(h, this.line), this.charIndex);
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof Marker) {
            Marker m = (Marker) x;
            return this.line == m.line && this.charIndex == m.charIndex;
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(Marker m) {
        int d = this.line - m.line;
        if (d == 0) {
            return getCharIndex() - m.getCharIndex();
        }
        return d;
    }

    public void reset(int line, int pos) {
        this.line = line;
        this.charIndex = pos;
    }

    public int getLine() {
        return this.line;
    }

    public int getCharIndex() {
        return this.charIndex;
    }

    public String toString() {
        SB sb = new SB(16);
        sb.a(Integer.valueOf(this.line));
        sb.a(':');
        sb.a(Integer.valueOf(getCharIndex()));
        return sb.toString();
    }

    public boolean isBefore(Marker m) {
        if (this.line < m.line) {
            return true;
        }
        if (this.line == m.line && this.charIndex < m.charIndex) {
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
        if (this.line == line && this.charIndex > pos) {
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

    public void moveLine(int delta) {
        this.line += delta;
    }

    public void movePosition(int delta) {
        this.charIndex += delta;
    }
}
