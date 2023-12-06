package goryachev.common.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BooleanArray.class */
public class BooleanArray implements Externalizable {
    private int size;
    private boolean[] array;
    private static final long serialVersionUID = 1;

    public BooleanArray() {
        this(32);
    }

    public BooleanArray(int capacity) {
        this.size = 0;
        this.array = new boolean[capacity];
    }

    public int size() {
        return this.size;
    }

    public boolean get(int n) {
        try {
            return this.array[n];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void add(boolean d) {
        prepareFor(this.size);
        boolean[] zArr = this.array;
        int i = this.size;
        this.size = i + 1;
        zArr[i] = d;
    }

    public void set(int n, boolean d) {
        prepareFor(n);
        this.array[n] = d;
        if (n >= this.size) {
            this.size = n + 1;
        }
    }

    public void set(boolean[] d) {
        this.array = d;
        this.size = d.length;
    }

    public void clear() {
        this.size = 0;
    }

    public void removeAll() {
        this.size = 0;
    }

    protected void prepareFor(int n) {
        if (n >= this.size) {
            if (n >= this.array.length) {
                boolean[] a = new boolean[n + Math.max(16, n / 2)];
                System.arraycopy(this.array, 0, a, 0, this.size);
                this.array = a;
                return;
            }
            for (int i = this.size; i < n; i++) {
                this.array[i] = false;
            }
        }
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput in) throws ClassNotFoundException, IOException {
        this.size = in.readInt();
        this.array = (boolean[]) in.readObject();
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

    public void insert(int n, boolean d) {
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
        boolean[] zArr = this.array;
        int i = this.size - 1;
        this.size = i;
        zArr[i] = false;
    }

    public boolean[] toArray() {
        boolean[] rv = new boolean[this.size];
        System.arraycopy(this.array, 0, rv, 0, this.size);
        return rv;
    }

    public void removeRange(int start, int end) {
        if (start < end) {
            System.arraycopy(this.array, end, this.array, start, this.size - end);
            this.size -= end - start;
        }
    }
}
