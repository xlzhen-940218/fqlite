package goryachev.common.io;

import java.io.DataOutput;
import java.io.IOException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/RandomAccessByteBuffer.class */
public class RandomAccessByteBuffer {
    byte[] buffer;
    int size;
    int position;

    public RandomAccessByteBuffer(int capacity) {
        this.buffer = new byte[capacity];
        this.size = 0;
    }

    public RandomAccessByteBuffer() {
        this(1024);
    }

    public byte readByte() throws IOException {
        checkAvailable(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i];
    }

    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }
        prepareFor(len);
        System.arraycopy(b, off, this.buffer, this.position, len);
        this.position += len;
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void writeBoolean(boolean d) throws IOException {
        writeByte(d ? 1 : 0);
    }

    public void writeByte(int d) throws IOException {
        prepareFor(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) d;
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void writeInt(int d) throws IOException {
        prepareFor(4);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (d >>> 24);
        byte[] bArr2 = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr2[i2] = (byte) (d >>> 16);
        byte[] bArr3 = this.buffer;
        int i3 = this.position;
        this.position = i3 + 1;
        bArr3[i3] = (byte) (d >>> 8);
        byte[] bArr4 = this.buffer;
        int i4 = this.position;
        this.position = i4 + 1;
        bArr4[i4] = (byte) d;
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void writeLong(long d) throws IOException {
        prepareFor(8);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (d >>> 56);
        byte[] bArr2 = this.buffer;
        int i2 = this.position;
        this.position = i2 + 1;
        bArr2[i2] = (byte) (d >>> 48);
        byte[] bArr3 = this.buffer;
        int i3 = this.position;
        this.position = i3 + 1;
        bArr3[i3] = (byte) (d >>> 40);
        byte[] bArr4 = this.buffer;
        int i4 = this.position;
        this.position = i4 + 1;
        bArr4[i4] = (byte) (d >>> 32);
        byte[] bArr5 = this.buffer;
        int i5 = this.position;
        this.position = i5 + 1;
        bArr5[i5] = (byte) (d >>> 24);
        byte[] bArr6 = this.buffer;
        int i6 = this.position;
        this.position = i6 + 1;
        bArr6[i6] = (byte) (d >>> 16);
        byte[] bArr7 = this.buffer;
        int i7 = this.position;
        this.position = i7 + 1;
        bArr7[i7] = (byte) (d >>> 8);
        byte[] bArr8 = this.buffer;
        int i8 = this.position;
        this.position = i8 + 1;
        bArr8[i8] = (byte) d;
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void writeASCIIString(String s) throws IOException {
        if (s == null) {
            writeByte(-1);
            return;
        }
        int n = s.length();
        if (n > 127) {
            throw new IOException("string too long");
        }
        writeByte(n);
        prepareFor(n);
        for (int i = 0; i < n; i++) {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) s.charAt(i);
        }
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void writeUtfString(String s) throws IOException {
        if (s == null) {
            writeInt(-1);
            return;
        }
        int n = s.length();
        writeInt(n);
        prepareFor(n + n);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i);
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (c >>> 8);
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) c;
        }
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public void seek(int pos) throws IOException {
        if (pos < 0 || pos > this.size) {
            throw new IOException("index out of bounds");
        }
        this.position = pos;
    }

    public int getPointer() {
        return this.position;
    }

    public void writeTo(DataOutput out) throws IOException {
        out.write(this.buffer, 0, this.size);
    }

    private void checkAvailable(int n) throws IOException {
        if (this.position + n > this.size) {
            throw new IOException("not enough bytes in the buffer");
        }
    }

    private void prepareFor(int n) {
        int n2 = n + this.position;
        if (n2 > this.buffer.length) {
            byte[] buf = new byte[Math.max(this.buffer.length << 1, n2 + 256)];
            System.arraycopy(this.buffer, 0, buf, 0, this.size);
            this.buffer = buf;
        }
    }

    public int size() {
        return this.size;
    }

    public byte[] toArray() {
        byte[] a = new byte[this.size];
        System.arraycopy(this.buffer, 0, a, 0, this.size);
        return a;
    }
}
