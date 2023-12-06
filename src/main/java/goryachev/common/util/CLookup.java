package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CLookup.class */
public class CLookup {
    private CMap<Object, Object> values;

    public CLookup() {
        this.values = new CMap<>();
    }

    public CLookup(Object... pairs) {
        int sz = pairs.length;
        int cap = sz * 2;
        this.values = new CMap<>(cap);
        if (cap / 2 != sz) {
            throw new RuntimeException("number of objects must be even: " + sz);
        }
        int i = 0;
        while (i < sz) {
            int i2 = i;
            int i3 = i + 1;
            Object k = pairs[i2];
            i = i3 + 1;
            Object v = pairs[i3];
            add(k, v);
        }
    }

    public void add(Object k, Object v) {
        Object old = this.values.put(k, v);
        if (old != null) {
            throw new RuntimeException("duplicate: " + old + " and " + v);
        }
        Object old2 = this.values.put(v, k);
        if (old2 != null) {
            throw new RuntimeException("duplicate: " + old2 + " and " + k);
        }
    }

    public Object lookup(Object x) {
        return this.values.get(x);
    }

    public Object lookup(Object x, Object defaultValue) {
        Object rv = this.values.get(x);
        if (rv != null) {
            return rv;
        }
        return defaultValue;
    }

    public Object[] toArray() {
        return this.values.keySet().toArray();
    }
}
