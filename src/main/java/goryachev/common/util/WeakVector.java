package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/WeakVector.class */
public class WeakVector<T> extends WeakList<T> {
    public WeakVector() {
    }

    public WeakVector(int size) {
        super(size);
    }

    @Override // goryachev.common.util.WeakList
    public synchronized CList<T> asList() {
        return super.asList();
    }

    @Override // goryachev.common.util.WeakList
    public synchronized T get(int ix) {
        return (T) super.get(ix);
    }

    @Override // goryachev.common.util.WeakList
    public synchronized void add(T item) {
        super.add(item);
    }

    @Override // goryachev.common.util.WeakList
    public synchronized void add(int index, T item) {
        super.add(index, item);
    }

    @Override // goryachev.common.util.WeakList
    public synchronized int size() {
        return super.size();
    }

    @Override // goryachev.common.util.WeakList
    public synchronized void remove(T item) {
        super.remove( item);
    }

    @Override // goryachev.common.util.WeakList
    public synchronized void remove(int ix) {
        super.remove(ix);
    }
}
