package goryachev.common.util;

import java.util.Collection;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ObjectCounter.class */
public class ObjectCounter<T> {
    private CMap<T, Integer> counts;

    public ObjectCounter() {
        this(32);
    }

    public ObjectCounter(int size) {
        this.counts = new CMap<>(size);
    }

    protected ObjectCounter(CMap<T, Integer> m) {
        this.counts = new CMap<>(m);
    }

    public ObjectCounter copy() {
        return new ObjectCounter(this.counts);
    }

    public void add(T x) {
        Integer ct = this.counts.get(x);
        if (ct == null) {
            this.counts.put(x, 1);
            return;
        }
        int n = Integer.valueOf(ct.intValue()).intValue() + 1;
        if (n < Integer.MAX_VALUE) {
            this.counts.put(x, Integer.valueOf(n));
        }
    }

    public void remove(T x) {
        int n;
        Integer ct = this.counts.get(x);
        if (ct != null && (n = Integer.valueOf(ct.intValue()).intValue() - 1) >= 0) {
            this.counts.put(x, Integer.valueOf(n));
        }
    }

    public void addAll(T[] tArr) {
        if (tArr != null) {
            for (T x : tArr) {
                add(x);
            }
        }
    }

    public void addAll(Collection<T> xs) {
        if (xs != null) {
            for (T x : xs) {
                add(x);
            }
        }
    }

    public CList<T> getKeys() {
        return new CList<>((Collection) this.counts.keySet());
    }

    public int getCount(T x) {
        return Parsers.parseInt(this.counts.get(x), 0);
    }

    public int size() {
        return this.counts.size();
    }

    public int getTotalCount() {
        int ct = 0;
        for (Integer x : this.counts.values()) {
            ct += x.intValue();
        }
        return ct;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof ObjectCounter) {
            return this.counts.equals(((ObjectCounter) x).counts);
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(ObjectCounter.class);
        return FH.hash(h, this.counts);
    }

    public T getTop() {
        T top = null;
        int count = 0;
        for (T x : this.counts.keySet()) {
            int ct = this.counts.get(x).intValue();
            if (ct > count) {
                count = ct;
                top = x;
            }
        }
        return top;
    }
}
