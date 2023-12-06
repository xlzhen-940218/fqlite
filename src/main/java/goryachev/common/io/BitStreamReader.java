package goryachev.common.io;

import goryachev.common.util.CKit;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitStreamReader.class */
public class BitStreamReader extends BitStreamCommon implements Closeable {
    private InputStream inp;
    private int count;
    private int bits;

    public BitStreamReader(InputStream inp) {
        this.inp = inp;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        CKit.close(this.inp);
    }

    public int readBits(int bitCount) throws Exception {
        if (bitCount <= 0 || bitCount > 31) {
            throw new IllegalArgumentException("invalid bitCount: " + bitCount);
        }
        int rv = 0;
        if (this.inp == null) {
            return -1;
        }
        while (bitCount > this.count) {
            rv |= this.bits << (bitCount - this.count);
            bitCount -= this.count;
            int read = this.inp.read();
            this.bits = read;
            if (read == -1) {
                return -1;
            }
            this.count = 8;
        }
        if (bitCount > 0) {
            rv |= this.bits >> (this.count - bitCount);
            this.bits &= MASK[this.count - bitCount];
            this.count -= bitCount;
        }
        return rv;
    }
}
