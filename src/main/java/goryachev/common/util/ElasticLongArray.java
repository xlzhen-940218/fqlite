package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ElasticLongArray.class */
public class ElasticLongArray {
    protected int size;
    protected long[] array;

    public ElasticLongArray() {
        this(128);
    }

    public ElasticLongArray(int capacity) {
        this.size = 0;
        this.array = new long[capacity];
    }

    public int size() {
        return this.size;
    }

    public long get(int index) {
        return this.array[index];
    }

    public void add(long value) {
        prepareFor(this.size);
        long[] jArr = this.array;
        int i = this.size;
        this.size = i + 1;
        jArr[i] = value;
    }

    public void addAll(ElasticLongArray a) {
        int sz;
        if (a != null && (sz = a.size()) > 0) {
            prepareFor(this.size + sz);
            System.arraycopy(a.array, 0, this.array, this.size, sz);
            this.size += sz;
        }
    }

    public void set(int index, long value) {
        prepareFor(index);
        this.array[index] = value;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    public int indexOf(long value) {
        return indexOf(value, 0);
    }

    public int indexOf(long value, int fromIndex) {
        for (int i = fromIndex; i < this.size; i++) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(long value) {
        return lastIndexOf(value, this.size - 1);
    }

    public int lastIndexOf(long value, int fromIndex) {
        for (int i = Math.min(this.size, fromIndex); i >= 0; i--) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void set(long[] d) {
        this.array = (long[]) d.clone();
        this.size = d.length;
    }

    public void clear() {
        this.size = 0;
    }

    protected void prepareFor(int sz) {
        if (sz >= this.size) {
            if (sz >= this.array.length) {
                long[] a = new long[sz + Math.max(16, sz / 2)];
                System.arraycopy(this.array, 0, a, 0, this.size);
                this.array = a;
                return;
            }
            for (int i = this.size; i < sz; i++) {
                this.array[i] = 0;
            }
        }
    }

    public void trim(int sz) {
        if (sz < this.size) {
            this.size = sz;
        }
    }

    public void insert(int index, long value) {
        prepareFor(this.size);
        if (this.size > index) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        } else {
            this.size = index + 1;
        }
        this.array[index] = value;
    }

    public long[] toArray() {
        long[] rv = new long[this.size];
        System.arraycopy(this.array, 0, rv, 0, this.size);
        return rv;
    }
}
