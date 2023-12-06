package goryachev.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CList.class */
public class CList<T> extends ArrayList<T> {
    public CList(int initialCapacity) {
        super(initialCapacity);
    }

    public CList() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CList(Collection<? extends T> c) {
        super(c == null ? 0 : c.size());
        if (c != null) {
            addAll(c);
        }
    }

    public CList(Iterator<? extends T> it) {
        if (it != null) {
            while (it.hasNext()) {
                add(it.next());
            }
        }
    }

    public CList(T[] tArr) {
        super(tArr == null ? 0 : tArr.length);
        if (tArr != null) {
            addAll(tArr);
        }
    }

    public CList(T item) {
        if (item != null) {
            add(item);
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    public void removeRange(int fromInclusive, int toExclusive) {
        super.removeRange(fromInclusive, toExclusive);
    }

    public void addNonNull(T t) {
        if (t != null) {
            add(t);
        }
    }

    @Override // java.util.ArrayList
    public Object clone() {
        return copyCList();
    }

    public CList<T> copyCList() {
        return new CList<>(this);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof CList) {
            CList o = (CList) x;
            if (size() == o.size()) {
                int sz = size();
                for (int i = 0; i < sz; i++) {
                    if (CKit.notEquals(get(i), o.get(i))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return CList.class.hashCode() ^ super.hashCode();
    }

    public void setAll(T[] tArr) {
        clear();
        if (tArr != null) {
            addAll(tArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAll(Collection<T> items) {
        clear();
        if (items != null) {
            addAll(items);
        }
    }

    public void addAll(T[] tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                add(t);
            }
        }
    }

    public void addAll(int startIndex, T[] tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                add(startIndex, t);
                startIndex++;
            }
        }
    }

    public void prune(int fromInclusive) {
        if (fromInclusive >= 0) {
            int ct = size() - fromInclusive;
            if (ct > 0) {
                removeRange(fromInclusive, size());
            }
        }
    }

    public T getLast() {
        if (size() > 0) {
            return get(size() - 1);
        }
        return null;
    }

    public T removeLast() {
        int ix = size() - 1;
        if (ix >= 0) {
            return remove(ix);
        }
        return null;
    }

    public static CList parse(Object x) {
        if (x instanceof CList) {
            return (CList) x;
        }
        return null;
    }

    public boolean isValidIndex(int ix) {
        return ix >= 0 && ix < size();
    }

    public void prepareFor(int ix) {
        int sz = ix + 1;
        while (size() < sz) {
            super.add(null);
        }
    }

    public void insert(int index, T item) {
        if (index < 0) {
            index = 0;
        }
        if (index >= size()) {
            add(item);
        } else {
            add(index, item);
        }
    }

    @SafeVarargs
    public static <V> CList<V> of(V... vArr) {
        return new CList<>(vArr);
    }

    public static <V> CList<V> copy(Collection<V> items) {
        if (items == null) {
            return null;
        }
        int sz = items.size();
        CList<V> rv = new CList<>(sz);
        rv.addAll(items);
        return rv;
    }
}
