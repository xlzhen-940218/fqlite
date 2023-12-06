package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ElasticArray.class */
public class ElasticArray<T> {
    private CList<T> list = new CList<>();
    private int size;

    public void add(T item) {
        add(this.size, item);
    }

    public void add(int index, T item) {
        while (this.list.size() <= index) {
            this.list.add(null);
        }
        this.list.add(index, item);
        this.size++;
    }

    public void set(int index, T item) {
        while (this.list.size() <= index) {
            this.list.add(null);
        }
        this.list.set(index, item);
        if (this.size <= index) {
            this.size = index + 1;
        }
    }

    public T get(int index) {
        if (index >= 0 && index < this.list.size()) {
            return this.list.get(index);
        }
        return null;
    }

    public int size() {
        return this.size;
    }
}
