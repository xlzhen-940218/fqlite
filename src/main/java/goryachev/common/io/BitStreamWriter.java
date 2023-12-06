package goryachev.common.io;

import goryachev.common.util.CKit;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitStreamWriter.class */
public class BitStreamWriter extends BitStreamCommon implements Closeable, Flushable {
    protected OutputStream out;
    private int bits;
    private int count = 8;

    public BitStreamWriter(OutputStream out) {
        this.out = out;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BitStreamWriter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOutputStream(OutputStream out) {
        this.out = out;
    }

    public void write(int bitCount, int value) throws Exception {
        if (bitCount <= 0 || bitCount > 31) {
            throw new IllegalArgumentException("invalid bitCount: " + bitCount);
        }
        int value2 = value & MASK[bitCount];
        while (bitCount >= this.count) {
            this.bits = (this.bits << this.count) | (value2 >> (bitCount - this.count));
            this.out.write(this.bits);
            value2 &= MASK[bitCount - this.count];
            bitCount -= this.count;
            this.count = 8;
            this.bits = 0;
        }
        if (bitCount > 0) {
            this.bits = (this.bits << bitCount) | value2;
            this.count -= bitCount;
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        if (this.count != 8) {
            this.out.write(this.bits << this.count);
            this.bits = 0;
            this.count = 8;
        }
        this.out.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
        } finally {
            CKit.close(this.out);
        }
    }
}
