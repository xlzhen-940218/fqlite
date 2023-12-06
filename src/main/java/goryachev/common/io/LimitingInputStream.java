package goryachev.common.io;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/LimitingInputStream.class */
public class LimitingInputStream extends InputStream {
    private final InputStream stream;
    private final long length;
    private long read;

    public LimitingInputStream(InputStream stream, long length) {
        this.stream = stream;
        this.length = length;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.read < this.length) {
            int c = this.stream.read();
            this.read++;
            return c;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int rv;
        long toread = this.length - this.read;
        if (toread >= len) {
            rv = this.stream.read(b, off, len);
            if (rv >= 0) {
                this.read += rv;
            }
        } else if (toread > 0) {
            rv = this.stream.read(b, off, (int) toread);
            if (rv >= 0) {
                this.read += rv;
            }
        } else {
            rv = -1;
        }
        return rv;
    }
}
