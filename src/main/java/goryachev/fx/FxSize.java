package goryachev.fx;

import goryachev.common.util.FH;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxSize.class */
public class FxSize {
    private double width;
    private double height;

    public FxSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public FxSize() {
    }

    public void setWidth(double w) {
        this.width = w;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof FxSize) {
            FxSize s = (FxSize) x;
            return this.width == s.width && this.height == s.height;
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(FxSize.class.hashCode(), this.width);
        return FH.hash(h, this.height);
    }
}
