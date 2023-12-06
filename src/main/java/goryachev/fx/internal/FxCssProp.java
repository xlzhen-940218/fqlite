package goryachev.fx.internal;

import goryachev.common.util.SB;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/FxCssProp.class */
public class FxCssProp {
    protected final String name;
    protected final Object value;

    public FxCssProp(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public void write(SB sb) {
        sb.a(this.name);
        sb.a(": ");
        sb.a(CssTools.toValue(this.value));
        sb.a("; ");
    }

    public String toString() {
        SB sb = new SB();
        write(sb);
        return sb.toString();
    }
}
