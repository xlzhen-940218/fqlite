package nl.pvanassen.bplist.ext.base64;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/ext/base64/Base64OutputStream.class */
class Base64OutputStream extends FilterOutputStream {
    private boolean encode;
    private int position;
    private byte[] buffer;
    private int bufferLength;
    private int lineLength;
    private boolean breakLines;
    private byte[] b4;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Base64OutputStream(OutputStream out, int options) {
        super(out);
        this.breakLines = (options & 8) != 8;
        this.encode = (options & 1) == 1;
        this.bufferLength = this.encode ? 3 : 4;
        this.buffer = new byte[this.bufferLength];
        this.position = 0;
        this.lineLength = 0;
        this.b4 = new byte[4];
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int theByte) throws IOException {
        if (this.encode) {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) theByte;
            if (this.position >= this.bufferLength) {
                this.out.write(Encode3to4.encode3to4(this.b4, this.buffer, this.bufferLength));
                this.lineLength += 4;
                if (this.breakLines && this.lineLength >= 76) {
                    this.out.write(10);
                    this.lineLength = 0;
                }
                this.position = 0;
            }
        } else if (Constants.DECODABET[theByte & 127] > -5) {
            byte[] bArr2 = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr2[i2] = (byte) theByte;
            if (this.position >= this.bufferLength) {
                int len = Decode4to3.decode4to3(this.buffer, 0, this.b4, 0);
                this.out.write(this.b4, 0, len);
                this.position = 0;
            }
        } else if (Constants.DECODABET[theByte & 127] != -5) {
            throw new IOException("Invalid character in Base64 data.");
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] theBytes, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(theBytes[off + i]);
        }
    }

    private void flushBase64() throws IOException {
        if (this.position > 0) {
            if (this.encode) {
                this.out.write(Encode3to4.encode3to4(this.b4, this.buffer, this.position));
                this.position = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flushBase64();
        super.close();
        this.buffer = null;
        this.out = null;
    }
}
