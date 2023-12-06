package goryachev.common.util;

import java.util.Iterator;
import java.util.Set;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CMultiMap.class */
public class CMultiMap<K, V> {
    private final CMap<K, CList<V>> map;

    public CMultiMap() {
        this(32);
    }

    public CMultiMap(int size) {
        this.map = new CMap<>(size);
    }

    public int size() {
        return this.map.size();
    }

    public int getTotalItemCount() {
        int count = 0;
        for (K k : this.map.keySet()) {
            CList<V> list = this.map.get(k);
            count += list.size();
        }
        return count;
    }

    public int getItemCount(K key) {
        CList<V> list = this.map.get(key);
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public boolean put(K key, V val) {
        CList<V> c = this.map.get(key);
        if (c == null) {
            c = new CList<>();
            this.map.put(key, c);
        }
        return c.add(val);
    }

    public CList<V> get(K key) {
        return this.map.get(key);
    }

    public boolean containsKey(K k) {
        return this.map.containsKey(k);
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public CList<K> keys() {
        return new CList<>(keySet());
    }

    public CList<V> collectValues() {
        CList<V> list = new CList<>(size() * 2);
        for (K k : this.map.keySet()) {
            CList<V> c = this.map.get(k);
            list.addAll(c);
        }
        return list;
    }

    public CList<V> remove(K key) {
        return this.map.remove(key);
    }

    public void remove(K key, V val) {
        CList<V> list = this.map.get(key);
        if (list != null) {
            list.remove(val);
            if (list.size() == 0) {
                this.map.remove(key);
            }
        }
    }

    public void putAll(CMultiMap<K, V> m) {
        for (K k : m.keySet()) {
            Iterator<V> it = m.get(k).iterator();
            while (it.hasNext()) {
                V v = it.next();
                put(k, v);
            }
        }
    }
}
