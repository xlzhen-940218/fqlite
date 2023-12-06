package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ElasticIntArray.class */
public class ElasticIntArray {
    protected int size;
    protected int[] array;

    public ElasticIntArray() {
        this(128);
    }

    public ElasticIntArray(int capacity) {
        this.size = 0;
        this.array = new int[capacity];
    }

    public int size() {
        return this.size;
    }

    public int get(int index) {
        try {
            return this.array[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public void add(int value) {
        prepareFor(this.size);
        int[] iArr = this.array;
        int i = this.size;
        this.size = i + 1;
        iArr[i] = value;
    }

    public void set(int index, int value) {
        prepareFor(index);
        this.array[index] = value;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    public int indexOf(int value) {
        return indexOf(value, 0);
    }

    public int indexOf(int value, int fromIndex) {
        for (int i = fromIndex; i < this.size; i++) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int value) {
        return lastIndexOf(value, this.size - 1);
    }

    public int lastIndexOf(int value, int fromIndex) {
        for (int i = Math.min(this.size, fromIndex); i >= 0; i--) {
            if (this.array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public void set(int[] d) {
        this.array = (int[]) d.clone();
        this.size = d.length;
    }

    public void clear() {
        this.size = 0;
    }

    protected void prepareFor(int sz) {
        if (sz >= this.size) {
            if (sz >= this.array.length) {
                int[] a = new int[sz + Math.max(16, sz / 2)];
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

    public void insert(int index, int value) {
        prepareFor(this.size);
        if (this.size > index) {
            System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        } else {
            this.size = index + 1;
        }
        this.array[index] = value;
    }

    public int[] toArray() {
        int[] rv = new int[this.size];
        System.arraycopy(this.array, 0, rv, 0, this.size);
        return rv;
    }
}
