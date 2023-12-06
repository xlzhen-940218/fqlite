package goryachev.common.util;

import java.io.EOFException;
import java.io.InputStream;
import java.util.Arrays;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ByteDataBuffer.class */
public class ByteDataBuffer {
    private byte[] buffer;
    private int position;
    private int size;

    public ByteDataBuffer(int initialCapacity) {
        this.buffer = new byte[initialCapacity];
    }

    public ByteDataBuffer() {
        this(256);
    }

    protected ByteDataBuffer(byte[] reuse) {
        this.buffer = reuse;
        this.size = reuse.length;
    }

    public void clear() {
        this.position = 0;
        this.size = 0;
    }

    protected int read() throws Exception {
        if (this.position >= this.size) {
            throw new EOFException();
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    public boolean readBoolean() throws Exception {
        return read() != 0;
    }

    public byte readByte() throws Exception {
        return (byte) read();
    }

    public int readUnsignedByte() throws Exception {
        return read();
    }

    public short readShort() throws Exception {
        int b1 = read();
        int b2 = read();
        return (short) ((b1 << 8) + b2);
    }

    public int readUnsignedShort() throws Exception {
        int b1 = read();
        int b2 = read();
        return (b1 << 8) + b2;
    }

    public char readChar() throws Exception {
        int b1 = read();
        int b2 = read();
        return (char) ((b1 << 8) + b2);
    }

    public int readInt() throws Exception {
        int d = read() << 24;
        return d | (read() << 16) | (read() << 8) | read();
    }

    public long readLong() throws Exception {
        long d = read() << 56;
        return d | (read() << 48) | (read() << 40) | (read() << 32) | (read() << 24) | (read() << 16) | (read() << 8) | read();
    }

    public float readFloat() throws Exception {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws Exception {
        return Double.longBitsToDouble(readLong());
    }

    public void read(byte[] b) throws Exception {
        read(b, 0, b.length);
    }

    public void read(byte[] buf, int off, int len) throws Exception {
        if (this.position + len <= this.size) {
            System.arraycopy(this.buffer, this.position, buf, off, len);
            this.position += len;
            updateSize();
            return;
        }
        throw new EOFException();
    }

    public void skip(int n) throws Exception {
        prepareFor(n);
        this.position += n;
        updateSize();
    }

    public void write(byte[] b) throws Exception {
        write(b, 0, b.length);
    }

    public void write(byte[] b, int off, int len) throws Exception {
        prepareFor(len);
        System.arraycopy(b, off, this.buffer, this.position, len);
        this.position += len;
        updateSize();
    }

    public void write(int b) throws Exception {
        prepareFor(1);
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) b;
        updateSize();
    }

    protected void writeUnsigned(int b) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) (b & 255);
    }

    public void writeBoolean(boolean d) throws Exception {
        write(d ? 1 : 0);
    }

    public void writeByte(int d) throws Exception {
        write(d);
    }

    public void writeChar(int d) throws Exception {
        prepareFor(2);
        writeUnsigned(d >> 8);
        writeUnsigned(d);
        updateSize();
    }

    public void writeShort(int d) throws Exception {
        prepareFor(2);
        writeUnsigned(d >> 8);
        writeUnsigned(d);
        updateSize();
    }

    public void writeInt(int d) throws Exception {
        prepareFor(4);
        writeUnsigned(d >> 24);
        writeUnsigned(d >> 16);
        writeUnsigned(d >> 8);
        writeUnsigned(d);
        updateSize();
    }

    public void writeLong(long d) throws Exception {
        prepareFor(8);
        writeUnsigned((int) (d >> 56));
        writeUnsigned((int) (d >> 48));
        writeUnsigned((int) (d >> 40));
        writeUnsigned((int) (d >> 32));
        writeUnsigned((int) (d >> 24));
        writeUnsigned((int) (d >> 16));
        writeUnsigned((int) (d >> 8));
        writeUnsigned((int) d);
        updateSize();
    }

    public void writeFloat(float v) throws Exception {
        writeInt(Float.floatToIntBits(v));
    }

    public void writeDouble(double v) throws Exception {
        writeLong(Double.doubleToLongBits(v));
    }

    public void writeFrom(InputStream in, int len) throws Exception {
        prepareFor(len);
        int left = len;
        while (left > 0) {
            int read = in.read(this.buffer, this.position, left);
            if (read < 0) {
                throw new EOFException();
            }
            left -= read;
            this.position += read;
        }
    }

    public void writeFrom(byte[] buf) {
        writeFrom(buf, 0, buf.length);
    }

    public void writeFrom(byte[] buf, int off, int len) {
        prepareFor(len);
        System.arraycopy(buf, off, this.buffer, this.position, len);
        this.position += len;
        updateSize();
    }

    protected void updateSize() {
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    protected void prepareFor(int n) {
        int n2 = n + this.position;
        if (n2 >= this.size) {
            if (n2 >= this.buffer.length) {
                byte[] b = new byte[n2 + (n2 / 2)];
                System.arraycopy(this.buffer, 0, b, 0, this.size);
                this.buffer = b;
                return;
            }
            Arrays.fill(this.buffer, this.size, n2, (byte) 0);
        }
    }

    public int size() {
        return this.size;
    }

    public byte[] toArray() {
        byte[] buf = new byte[this.size];
        System.arraycopy(this.buffer, 0, buf, 0, this.size);
        return buf;
    }

    public byte[] getInnerArray() {
        return this.buffer;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException("Negative offset " + offset);
        }
        if (this.position > this.size) {
            throw new IllegalArgumentException("position outside of the data array " + this.position + " size=" + this.size);
        }
        this.position = offset;
        if (this.size < this.position) {
            this.size = this.position;
        }
    }

    public boolean equals(int bufferOffset, byte[] b) {
        if (b.length > size() - bufferOffset) {
            return false;
        }
        int ix = bufferOffset;
        for (byte b2 : b) {
            int i = ix;
            ix++;
            if (this.buffer[i] != b2) {
                return false;
            }
        }
        return true;
    }

    public static ByteDataBuffer reuseSuppliedArray(byte[] b) {
        return new ByteDataBuffer(b);
    }
}
