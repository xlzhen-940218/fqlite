package goryachev.common.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DReader.class */
public class DReader extends InputStream implements Closeable {
    protected InputStream in;

    public DReader(InputStream in) {
        this.in = in;
    }

    public DReader(byte[] b) {
        this(new ByteArrayInputStream(b));
    }

    public DReader(File f) throws Exception {
        this(new BufferedInputStream(new FileInputStream(f)));
    }

    public void readFully(byte[] b) throws IOException {
        CIOTools.readFully(this.in, b);
    }

    public byte[] readFully(int byteCount) throws IOException {
        byte[] b = new byte[byteCount];
        CIOTools.readFully(this.in, b);
        return b;
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        CIOTools.readFully(this.in, b, off, len);
    }

    public byte[] readByteArray(int max) throws IOException {
        return CIOTools.readByteArray(this.in, max);
    }

    public boolean readBoolean() throws IOException {
        int ch = this.in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return ch != 0;
    }

    public byte readByte() throws IOException {
        int ch = this.in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return (byte) ch;
    }

    public int readUInt8() throws IOException {
        int ch = this.in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return ch;
    }

    public int readInt8() throws IOException {
        int ch = this.in.read();
        if (ch < 0) {
            throw new EOFException();
        }
        return (byte) ch;
    }

    public int readByteRaw() throws IOException {
        return this.in.read();
    }

    public char readChar() throws IOException {
        int ch1 = this.in.read();
        int ch2 = this.in.read();
        if (ch2 < 0) {
            throw new EOFException();
        }
        return (char) ((ch1 << 8) + ch2);
    }

    public short readShort() throws IOException {
        int ch1 = this.in.read();
        int ch2 = this.in.read();
        if (ch2 < 0) {
            throw new EOFException();
        }
        return (short) ((ch1 << 8) + ch2);
    }

    public int readInt24() throws IOException {
        int ch1 = this.in.read();
        int ch2 = this.in.read();
        int ch3 = this.in.read();
        if (ch3 < 0) {
            throw new EOFException();
        }
        return (ch1 << 16) + (ch2 << 8) + ch3;
    }

    public int readInt() throws IOException {
        return readInt(this.in);
    }

    public static int readInt(InputStream in) throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        int ch3 = in.read();
        int ch4 = in.read();
        if (ch4 < 0) {
            throw new EOFException();
        }
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4;
    }

    public int[] readIntArray(int max) throws IOException {
        int count = readInt();
        if (count < 0) {
            if (count == -1) {
                return null;
            }
            throw new IOException("unexpected int array size " + count);
        } else if (count >= max) {
            throw new IOException("expecting no more than " + max + " bytes, received " + count);
        } else {
            int[] b = new int[count];
            for (int i = 0; i < count; i++) {
                b[i] = readInt();
            }
            return b;
        }
    }

    public long readLong() throws IOException {
        long d = this.in.read() << 56;
        long d2 = d | (this.in.read() << 48) | (this.in.read() << 40) | (this.in.read() << 32) | (this.in.read() << 24) | (this.in.read() << 16) | (this.in.read() << 8);
        int c = this.in.read();
        if (c < 0) {
            throw new EOFException();
        }
        return d2 | c;
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    public String readString() throws IOException {
        return CIOTools.readString(this.in);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public long skip(long nbytes) throws IOException {
        return this.in.skip(nbytes);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] buf) throws IOException {
        return this.in.read(buf);
    }

    @Override // java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        return this.in.read(buf, off, len);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readlimit) {
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        throw new IOException("reset not supported");
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }
}
