package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CompoundKey.class */
public class CompoundKey {
    private final Object[] keys;
    private int hash;

    public CompoundKey(Object a, Object b) {
        this(new Object[]{a, b});
    }

    public CompoundKey(Object a, Object b, Object c) {
        this(new Object[]{a, b, c});
    }

    public CompoundKey(Object a, Object b, Object c, Object d) {
        this(new Object[]{a, b, c, d});
    }

    public CompoundKey(Object a, Object b, Object c, Object d, Object e) {
        this(new Object[]{a, b, c, d, e});
    }

    public CompoundKey(Object[] keys) {
        this.keys = keys;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof CompoundKey) {
            return CKit.equals(this.keys, ((CompoundKey) x).keys);
        }
        return false;
    }

    public int hashCode() {
        if (this.hash == 0) {
            this.hash = FH.hash(this.keys);
        }
        return this.hash;
    }
}
