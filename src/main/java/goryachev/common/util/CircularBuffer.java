package goryachev.common.util;

import java.util.Collection;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CircularBuffer.class */
public class CircularBuffer<T> {
    protected final Object[] items;
    private int size;
    private int first;

    public CircularBuffer(int capacity) {
        this.items = new Object[capacity];
    }

    protected int index(int ix) {
        return ix % this.items.length;
    }

    public int size() {
        return this.size;
    }

    public int getCapacity() {
        return this.items.length;
    }

    public void add(T item) {
        int ix = index(this.first + this.size);
        this.items[ix] = item;
        if (this.size < this.items.length) {
            this.size++;
        } else {
            this.first++;
        }
    }

    public void addAll(T... tArr) {
        for (T t : tArr) {
            add(t);
        }
    }

    public void addAll(Collection<T> adding) {
        for (T item : adding) {
            add(item);
        }
    }

    public void insertFirst(T item) {
        int capacity = getCapacity();
        if (this.size >= capacity) {
            throw new ArrayIndexOutOfBoundsException("buffer is full");
        }
        this.first = index((this.first + capacity) - 1);
        this.items[this.first] = item;
        this.size++;
    }

    public T get(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int ix = index(this.first + index);
        return (T) this.items[ix];
    }

    public T removeFirst() {
        if (this.size == 0) {
            throw new ArrayIndexOutOfBoundsException(0);
        }
        T item = (T) this.items[this.first];
        this.first = index(this.first + 1);
        this.size--;
        return item;
    }

    public T removeLast() {
        if (this.size == 0) {
            throw new ArrayIndexOutOfBoundsException(0);
        }
        int ix = index((this.first + this.size) - 1);
        T item = (T) this.items[ix];
        this.size--;
        return item;
    }
}
