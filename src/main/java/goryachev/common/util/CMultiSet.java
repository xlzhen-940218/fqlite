package goryachev.common.util;

import java.util.Set;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CMultiSet.class */
public class CMultiSet<K, V> {
    private final CMap<K, CSet<V>> map;

    public CMultiSet() {
        this(32);
    }

    public CMultiSet(int size) {
        this.map = new CMap<>(size);
    }

    public int size() {
        return this.map.size();
    }

    public int getTotalItemCount() {
        int count = 0;
        for (K k : this.map.keySet()) {
            CSet<V> list = this.map.get(k);
            count += list.size();
        }
        return count;
    }

    public int getItemCount(K key) {
        CSet<V> list = this.map.get(key);
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public boolean put(K key, V val) {
        CSet<V> c = this.map.get(key);
        if (c == null) {
            c = new CSet<>();
            this.map.put(key, c);
        }
        return c.add(val);
    }

    public CSet<V> get(K key) {
        return this.map.get(key);
    }

    public boolean containsKey(K k) {
        return this.map.containsKey(k);
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public CList<V> collectValues() {
        CList<V> list = new CList<>(size() * 2);
        for (K k : this.map.keySet()) {
            CSet<V> c = this.map.get(k);
            list.addAll(c);
        }
        return list;
    }

    public CSet<V> remove(K k) {
        return this.map.remove(k);
    }
}
