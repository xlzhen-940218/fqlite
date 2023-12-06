package goryachev.fx;

import goryachev.common.util.FH;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CssStyle.class */
public class CssStyle {
    private final String name;

    public CssStyle(String name) {
        this.name = name;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof CssStyle) {
            CssStyle z = (CssStyle) x;
            return this.name.equals(z.name);
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(CssStyle.class);
        return FH.hash(h, this.name);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }
}
