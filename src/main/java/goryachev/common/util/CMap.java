package goryachev.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CMap.class */
public class CMap<K, V> extends HashMap<K, V> {
    public CMap() {
    }

    public CMap(int capacity) {
        super(capacity);
    }

    public CMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public CList<K> keys() {
        return new CList<>(keySet());
    }

    public void removeAll(Collection<K> keys) {
        if (keys != null) {
            for (K k : keys) {
                remove(k);
            }
        }
    }

    public V set(K k, V v) {
        if (v == null) {
            return remove(k);
        }
        return put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap
    public Object clone() {
        return copyCMap();
    }

    public CMap<K, V> copyCMap() {
        return new CMap<>(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void loadArray(Object... xx) {
        if (xx != null) {
            int sz = xx.length;
            if (sz < 0 || CKit.isOdd(sz)) {
                throw new IllegalArgumentException("array must contain even number of elements");
            }
            int i = 0;
            while (i < sz) {
                int i2 = i;
                int i3 = i + 1;
                i = i3 + 1;
                put((K) xx[i2], (V) xx[i3]);
            }
        }
    }
}
