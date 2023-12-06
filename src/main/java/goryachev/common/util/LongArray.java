package goryachev.common.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/LongArray.class */
public class LongArray implements Externalizable {
    private int size;
    private long[] array;
    private static final long serialVersionUID = 1;

    public LongArray() {
        this(128);
    }

    public LongArray(int capacity) {
        this.size = 0;
        this.array = new long[capacity];
    }

    public LongArray(long[] a) {
        this.size = a.length;
        this.array = a;
    }

    public int size() {
        return this.size;
    }

    public long get(int n) {
        if (n >= this.size) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return this.array[n];
    }

    public void add(long d) {
        prepareFor(this.size);
        long[] jArr = this.array;
        int i = this.size;
        this.size = i + 1;
        jArr[i] = d;
    }

    public void set(int n, long d) {
        prepareFor(n);
        this.array[n] = d;
        if (n >= this.size) {
            this.size = n + 1;
        }
    }

    public void clear() {
        this.size = 0;
    }

    protected void prepareFor(int n) {
        if (n >= this.size) {
            if (n >= this.array.length) {
                long[] a = new long[n + Math.max(16, n / 2)];
                System.arraycopy(this.array, 0, a, 0, this.size);
                this.array = a;
                return;
            }
            for (int i = this.size; i < n; i++) {
                this.array[i] = 0;
            }
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        this.size = in.readInt();
        this.array = (long[]) in.readObject();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.size);
        out.writeObject(this.array);
    }

    public void trim(int sz) {
        if (sz < this.size) {
            this.size = sz;
        }
    }

    public void insert(int n, long d) {
        prepareFor(this.size);
        if (this.size > n) {
            System.arraycopy(this.array, n, this.array, n + 1, this.size - n);
        } else {
            this.size = n + 1;
        }
        this.array[n] = d;
    }

    public void remove(int n) {
        int tomove = (this.size - n) - 1;
        if (tomove > 0) {
            System.arraycopy(this.array, n + 1, this.array, n, tomove);
        }
        long[] jArr = this.array;
        int i = this.size - 1;
        this.size = i;
        jArr[i] = 0;
    }

    public long[] toArray() {
        long[] rv = new long[this.size];
        System.arraycopy(this.array, 0, rv, 0, this.size);
        return rv;
    }

    public long[] getArray() {
        return this.array;
    }
}
