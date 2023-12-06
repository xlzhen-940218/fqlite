package goryachev.common.util;

import java.lang.ref.WeakReference;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/WeakList.class */
public class WeakList<T> {
    private final CList<WeakReference<T>> list;

    public WeakList() {
        this(8);
    }

    public WeakList(int size) {
        this.list = new CList<>(size);
    }

    public void gc() {
        int sz = this.list.size();
        for (int i = sz - 1; i >= 0; i--) {
            WeakReference<T> ref = this.list.get(i);
            T item = ref.get();
            if (item == null) {
                this.list.remove(i);
            }
        }
    }

    public CList<T> asList() {
        int sz = this.list.size();
        CList<T> rv = new CList<>(sz);
        for (int i = sz - 1; i >= 0; i--) {
            WeakReference<T> ref = this.list.get(i);
            T item = ref.get();
            if (item == null) {
                this.list.remove(i);
            } else {
                rv.add(item);
            }
        }
        return rv;
    }

    public T get(int ix) {
        return this.list.get(ix).get();
    }

    public void add(T item) {
        this.list.add(new WeakReference<>(item));
    }

    public void add(int index, T item) {
        this.list.add(index, new WeakReference<>(item));
    }

    public int size() {
        return this.list.size();
    }

    public void remove(T item) {
        int sz = this.list.size();
        for (int i = sz - 1; i >= 0; i--) {
            WeakReference<T> ref = this.list.get(i);
            T x = ref.get();
            if (x == null) {
                this.list.remove(i);
            } else if (item == x) {
                this.list.remove(i);
            }
        }
    }

    public void remove(int ix) {
        this.list.remove(ix);
    }

    public void clear() {
        this.list.clear();
    }

    public WeakReference<T> getRef(int ix) {
        return this.list.get(ix);
    }

    public String toString() {
        int sz = this.list.size();
        SB sb = new SB(sz * 8);
        sb.append("[");
        for (int i = 0; i < sz; i++) {
            if (i > 0) {
                sb.append(",");
            }
            WeakReference<T> ref = this.list.get(i);
            T item = ref.get();
            if (item == null) {
                sb.append("<null>");
            } else {
                sb.append(item);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
