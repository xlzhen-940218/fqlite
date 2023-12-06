package goryachev.common.io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/PositionTrackingInputStream.class */
public class PositionTrackingInputStream extends InputStream {
    private final InputStream in;
    private long pos;

    public PositionTrackingInputStream(InputStream in) {
        this.in = in;
    }

    public long getPosition() {
        return this.pos;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int r = this.in.read();
        if (r >= 0) {
            this.pos++;
        }
        return r;
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        int r = this.in.read(b);
        if (r > 0) {
            this.pos += r;
        }
        return r;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int r = this.in.read(b, off, len);
        if (r > 0) {
            this.pos += r;
        }
        return r;
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        long r = this.in.skip(n);
        this.pos += r;
        return r;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int readlimit) {
        this.in.mark(readlimit);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.in.reset();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
