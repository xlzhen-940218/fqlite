package goryachev.fxtexteditor;

import goryachev.common.util.Assert;
import goryachev.common.util.FH;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SelectionSegment.class */
public class SelectionSegment {
    protected final Marker min;
    protected final Marker max;
    protected final boolean caretAtMin;

    public SelectionSegment(Marker min, Marker max, boolean caretAtMin) {
        Assert.notNull(min, "min");
        Assert.notNull(max, "max");
        Assert.isLessThanOrEqual(min, max, "min", "max");
        this.min = min;
        this.max = max;
        this.caretAtMin = caretAtMin;
    }

    public SelectionSegment(Marker anchor, Marker caret) {
        Assert.notNull(anchor, "anchor");
        Assert.notNull(caret, "caret");
        if (anchor.compareTo(caret) <= 0) {
            this.min = anchor;
            this.max = caret;
            this.caretAtMin = false;
            return;
        }
        this.min = caret;
        this.max = anchor;
        this.caretAtMin = true;
    }

    public SelectionSegment copy() {
        return new SelectionSegment(this.min, this.max, this.caretAtMin);
    }

    public int hashCode() {
        int h = FH.hash(SelectionSegment.class);
        return FH.hash(FH.hash(FH.hash(h, this.min), this.max), this.caretAtMin);
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof SelectionSegment) {
            SelectionSegment s = (SelectionSegment) x;
            return this.caretAtMin == s.caretAtMin && this.min.equals(s.min) && this.max.equals(s.max);
        }
        return false;
    }

    public String toString() {
        if (this.min.equals(this.max)) {
            return "[" + this.min + "]";
        }
        if (this.caretAtMin) {
            return "[" + this.min + "^-" + this.max + "]";
        }
        return "[" + this.min + "-" + this.max + "^)";
    }

    public Marker getAnchor() {
        return this.caretAtMin ? this.max : this.min;
    }

    public Marker getCaret() {
        return this.caretAtMin ? this.min : this.max;
    }

    public boolean isCaretAtMin() {
        return this.caretAtMin;
    }

    public Marker getMin() {
        return this.min;
    }

    public Marker getMax() {
        return this.max;
    }

    public int getMinLine() {
        return this.min.getLine();
    }

    public int getMaxLine() {
        return this.max.getLine();
    }

    public int getMinCharIndex() {
        return this.min.getCharIndex();
    }

    public int getMaxCharIndex() {
        return this.max.getCharIndex();
    }

    public int getCaretLine() {
        return getCaret().getLine();
    }

    public int getCaretCharIndex() {
        return getCaret().getCharIndex();
    }

    public boolean contains(Marker p) {
        if (p != null) {
            if (isEmpty()) {
                return p.compareTo(this.min) == 0;
            }
            int st = p.compareTo(this.min);
            if (st >= 0) {
                int en = p.compareTo(this.max);
                if (en <= 0) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean contains(int line, int pos) {
        if (this.min.isAfter(line, pos) || this.max.isAtOrBefore(line, pos)) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return this.min.equals(this.max);
    }

    public boolean isSameLine() {
        return this.min.getLine() == this.max.getLine();
    }

    public SelectionSegment merge(SelectionSegment s) {
        Marker m0 = s.getMin();
        Marker m1 = s.getMax();
        if (contains(m0)) {
            if (contains(m1)) {
                return this;
            }
            return new SelectionSegment(this.min, m1, this.caretAtMin);
        } else if (contains(m1)) {
            return new SelectionSegment(m0, this.max, this.caretAtMin);
        } else {
            return null;
        }
    }

    public boolean isBefore(SelectionSegment s) {
        return getMax().compareTo(s.getMin()) < 0;
    }

    public boolean overlaps(SelectionSegment s) {
        return contains(s.getMin()) || contains(s.getMax());
    }

    public boolean isCaretLine(int line) {
        Marker m = getCaret();
        return m.getLine() == line;
    }

    public boolean isCaret(int line, int pos) {
        Marker m = getCaret();
        if (m.getLine() == line && m.getCharIndex() == pos) {
            return true;
        }
        return false;
    }
}
