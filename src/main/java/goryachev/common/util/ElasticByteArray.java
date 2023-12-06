package goryachev.common.util;

import java.nio.charset.Charset;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ElasticByteArray.class */
public class ElasticByteArray {
    protected int size;
    protected byte[] array;

    public ElasticByteArray() {
        this(128);
    }

    public ElasticByteArray(int capacity) {
        this.size = 0;
        this.array = new byte[capacity];
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
        byte[] bArr = this.array;
        int i = this.size;
        this.size = i + 1;
        bArr[i] = (byte) value;
    }

    public void set(int index, int value) {
        prepareFor(index);
        this.array[index] = (byte) value;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    public int indexOf(int value) {
        return indexOf(value, 0);
    }

    public int indexOf(int value, int fromIndex) {
        byte v = (byte) value;
        for (int i = fromIndex; i < this.size; i++) {
            if (this.array[i] == v) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(int value) {
        return lastIndexOf(value, this.size - 1);
    }

    public int lastIndexOf(int value, int fromIndex) {
        byte v = (byte) value;
        for (int i = Math.min(this.size, fromIndex); i >= 0; i--) {
            if (this.array[i] == v) {
                return i;
            }
        }
        return -1;
    }

    public void set(byte[] d) {
        this.array = (byte[]) d.clone();
        this.size = d.length;
    }

    public void clear() {
        this.size = 0;
    }

    protected void prepareFor(int sz) {
        if (sz >= this.size) {
            if (sz >= this.array.length) {
                byte[] a = new byte[sz + Math.max(16, sz / 2)];
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
        this.array[index] = (byte) value;
    }

    public byte[] toArray() {
        byte[] rv = new byte[this.size];
        System.arraycopy(this.array, 0, rv, 0, this.size);
        return rv;
    }

    public String toString() {
        return super.toString();
    }

    public String toString(Charset cs) {
        return new String(this.array, 0, this.size, cs);
    }

    public String toString(int start, int len, Charset cs) {
        return new String(this.array, start, len, cs);
    }
}
