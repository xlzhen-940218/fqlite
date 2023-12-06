package goryachev.common.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/RandomAccessFileOutputStreamWrapper.class */
public class RandomAccessFileOutputStreamWrapper extends OutputStream {
    private final RandomAccessFile f;

    public RandomAccessFileOutputStreamWrapper(RandomAccessFile f) {
        this.f = f;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.f.write(b);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        this.f.write(b, 0, b.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.f.write(b, off, len);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f.close();
    }
}
