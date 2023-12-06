package goryachev.common.util;

import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/FixedMemCache.class */
public class FixedMemCache<K, V> {
    private int maxItems;
    private CMap<K, V> cache = new CMap<>();
    private CList<K> keys = new CList<>();
    private Random random = new Random();

    public FixedMemCache(int maxItems) {
        this.maxItems = maxItems;
    }

    protected void removeRandomObject() {
        int sz = this.keys.size();
        if (sz > 0) {
            int ix = this.random.nextInt(sz);
            K key = this.keys.get(ix);
            this.keys.set(ix, this.keys.remove(sz - 1));
            this.cache.remove(key);
        }
    }

    public synchronized V get(K key) {
        return this.cache.get(key);
    }

    public synchronized void put(K key, V value) {
        while (this.keys.size() >= this.maxItems) {
            removeRandomObject();
        }
        this.cache.put(key, value);
        this.keys.add(key);
    }

    public synchronized void clear() {
        this.cache.clear();
        this.keys.clear();
    }
}
