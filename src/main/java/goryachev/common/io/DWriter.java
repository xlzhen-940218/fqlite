package goryachev.common.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DWriter.class */
public class DWriter extends OutputStream {
    protected OutputStream out;

    public DWriter(OutputStream out) {
        this.out = out;
    }

    public DWriter(File f) throws Exception {
        this(new BufferedOutputStream(new FileOutputStream(f)));
    }

    public void writeByteArray(byte[] b) throws IOException {
        CIOTools.writeByteArray(this.out, b);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    public void writeBoolean(boolean x) throws IOException {
        this.out.write(x ? 1 : 0);
    }

    public void writeByte(int x) throws IOException {
        this.out.write(x);
    }

    public void writeChar(int x) throws IOException {
        this.out.write((x >>> 8) & 255);
        this.out.write(x & 255);
    }

    public void writeShort(int x) throws IOException {
        this.out.write(x >>> 8);
        this.out.write(x);
    }

    public void writeInt(int x) throws IOException {
        this.out.write(x >>> 24);
        this.out.write(x >>> 16);
        this.out.write(x >>> 8);
        this.out.write(x);
    }

    public void writeInt8(int x) throws IOException {
        this.out.write(x);
    }

    public void writeUInt8(int x) throws IOException {
        this.out.write(x);
    }

    public void writeIntArray(int[] b) throws IOException {
        if (b == null) {
            writeInt(-1);
            return;
        }
        writeInt(b.length);
        for (int i : b) {
            writeInt(i);
        }
    }

    public void writeLong(long x) throws IOException {
        this.out.write(((int) (x >>> 56)) & 255);
        this.out.write(((int) (x >>> 48)) & 255);
        this.out.write(((int) (x >>> 40)) & 255);
        this.out.write(((int) (x >>> 32)) & 255);
        this.out.write(((int) (x >>> 24)) & 255);
        this.out.write(((int) (x >>> 16)) & 255);
        this.out.write(((int) (x >>> 8)) & 255);
        this.out.write(((int) x) & 255);
    }

    public void writeFloat(float x) throws IOException {
        writeInt(Float.floatToIntBits(x));
    }

    public void writeDouble(double x) throws IOException {
        writeLong(Double.doubleToLongBits(x));
    }

    public void writeString(String s) throws IOException {
        CIOTools.writeString(this.out, s);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
    }
}
