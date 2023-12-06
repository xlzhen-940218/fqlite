package goryachev.common.io;

import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.SB;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BufferedFile.class */
public class BufferedFile extends RandomAccessFile {
    private Buffer[] buffers;
    private int blockSize;
    private CList<Buffer> free;
    private long marker;
    private Random random;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$io$BufferedFile$Mode;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BufferedFile$Mode.class */
    public enum Mode {
        READ,
        READ_WRITE,
        READ_WRITE_CONTENT_METADATA,
        READ_WRITE_CONTENT;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Mode[] valuesCustom() {
            Mode[] valuesCustom = values();
            int length = valuesCustom.length;
            Mode[] modeArr = new Mode[length];
            System.arraycopy(valuesCustom, 0, modeArr, 0, length);
            return modeArr;
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$io$BufferedFile$Mode() {
        int[] iArr = $SWITCH_TABLE$goryachev$common$io$BufferedFile$Mode;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Mode.valuesCustom().length];
        try {
            iArr2[Mode.READ.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Mode.READ_WRITE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Mode.READ_WRITE_CONTENT.ordinal()] = 4;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[Mode.READ_WRITE_CONTENT_METADATA.ordinal()] = 3;
        } catch (NoSuchFieldError unused4) {
        }
        $SWITCH_TABLE$goryachev$common$io$BufferedFile$Mode = iArr2;
        return iArr2;
    }

    public BufferedFile(File f, Mode mode, int bufferSize, int bufferCount) throws Exception {
        super(f, modeToString(mode));
        this.random = new Random();
        this.blockSize = bufferSize;
        this.buffers = new Buffer[bufferCount];
        this.free = new CList<>(bufferCount);
        for (int i = 0; i < bufferCount; i++) {
            Buffer b = new Buffer(bufferSize);
            this.buffers[i] = b;
            this.free.add(b);
        }
    }

    public BufferedFile(File f, Mode mode) throws Exception {
        this(f, mode, 4096, 4);
    }

    protected static String modeToString(Mode m) {
        switch ($SWITCH_TABLE$goryachev$common$io$BufferedFile$Mode()[m.ordinal()]) {
            case 1:
                return "r";
            case 2:
                return "rw";
            case 3:
                return "rws";
            case 4:
                return "rwd";
            default:
                throw new Error("?" + m);
        }
    }

    protected void discardCache(long start, int len) {
        Buffer[] bufferArr;
        for (Buffer b : this.buffers) {
            if (b.contains(start, len)) {
                b.discard();
            }
        }
    }

    protected void syncMarker() throws IOException {
        if (this.marker != getFilePointer()) {
            super.seek(this.marker);
        }
    }

    public long getCurrentPosition() {
        return this.marker;
    }

    @Override // java.io.RandomAccessFile
    public long getFilePointer() throws IOException {
        return super.getFilePointer();
    }

    protected Buffer getBufferAtMarker() throws IOException {
        Buffer[] bufferArr;
        Buffer b;
        int pos = (int) (this.marker % this.blockSize);
        long start = this.marker - pos;
        for (Buffer b2 : this.buffers) {
            if (b2.getStartOffset() == start) {
                if (((int) (this.marker - start)) + 1 < b2.getAvailable()) {
                    return b2;
                } else {
                    if (this.marker >= length()) {
                        return null;
                    }
                    b2.read(this, start, Math.min(this.blockSize, (int) (length() - start)));
                    return b2;
                }
            }
        }
        if (this.free.size() > 0) {
            b = this.free.remove(this.free.size() - 1);
        } else {
            b = this.buffers[this.random.nextInt(this.buffers.length)];
        }
        int sz = Math.min(this.blockSize, (int) (length() - start));
        if (sz < 0) {
            return null;
        }
        b.read(this, start, sz);
        return b;
    }

    protected int lowLevelRead(byte[] buffer, long offset, int len) throws IOException {
        super.seek(offset);
        return super.read(buffer, 0, len);
    }

    protected synchronized int readBytes(byte[] buf, int off, int len) throws IOException {
        Buffer b = getBufferAtMarker();
        if (b == null) {
            return -1;
        }
        int rv = b.get(this.marker, buf, off, len);
        this.marker += rv;
        return rv;
    }

    @Override // java.io.RandomAccessFile
    public synchronized int read() throws IOException {
        return readPrivate(true);
    }

    public synchronized int peek() throws IOException {
        return readPrivate(false);
    }

    protected int readPrivate(boolean advance) throws IOException {
        Buffer b = getBufferAtMarker();
        if (b == null) {
            return -1;
        }
        int c = b.get(this.marker);
        if (advance && c >= 0) {
            this.marker++;
        }
        return c;
    }

    @Deprecated
    public synchronized String readText() throws Exception {
        SB sb = new SB(128);
        while (true) {
            int c = readPrivate(true);
            if (c < 0) {
                if (sb.length() == 0) {
                    return null;
                }
                return sb.toString();
            } else if (c != 13) {
                if (c == 10) {
                    return sb.toString();
                }
                sb.append((char) c);
            }
        }
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] b, int off, int len) throws IOException {
        return readBytes(b, off, len);
    }

    @Override // java.io.RandomAccessFile
    public int read(byte[] b) throws IOException {
        return readBytes(b, 0, b.length);
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public synchronized void write(int b) throws IOException {
        syncMarker();
        super.write(b);
        discardCache(this.marker, 1);
        this.marker++;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public synchronized void write(byte[] b) throws IOException {
        syncMarker();
        super.write(b, 0, b.length);
        discardCache(this.marker, b.length);
        this.marker += b.length;
    }

    @Override // java.io.RandomAccessFile, java.io.DataOutput
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        syncMarker();
        super.write(b, off, len);
        discardCache(this.marker, len);
        this.marker += len;
    }

    @Override // java.io.RandomAccessFile
    public synchronized void seek(long pos) throws IOException {
        this.marker = pos;
    }

    public synchronized void writeString(String s) throws IOException {
        if (s == null) {
            writeInt(-1);
            return;
        }
        int sz = s.length();
        byte[] b = new byte[4 + sz + sz];
        int ix = 0 + 1;
        b[0] = (byte) (sz >> 24);
        int ix2 = ix + 1;
        b[ix] = (byte) (sz >> 16);
        int ix3 = ix2 + 1;
        b[ix2] = (byte) (sz >> 8);
        int ix4 = ix3 + 1;
        b[ix3] = (byte) sz;
        for (int i = 0; i < sz; i++) {
            int c = s.charAt(i);
            int i2 = ix4;
            int ix5 = ix4 + 1;
            b[i2] = (byte) (c >> 8);
            ix4 = ix5 + 1;
            b[ix5] = (byte) c;
        }
        write(b);
    }

    public void writeText(String s) throws IOException {
        byte[] b = s.getBytes(CKit.CHARSET_UTF8);
        write(b);
    }

    public synchronized String readString() throws IOException {
        int sz = readInt();
        if (sz < -1) {
            throw new IOException("not a string");
        }
        if (sz == -1) {
            return null;
        }
        byte[] b = new byte[sz + sz];
        readFully(b);
        SB sb = new SB(sz);
        int ix = 0;
        for (int i = 0; i < sz; i++) {
            int i2 = ix;
            int ix2 = ix + 1;
            int c = (b[i2] & 255) << 8;
            ix = ix2 + 1;
            sb.append((char) (c | (b[ix2] & 255)));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BufferedFile$Buffer.class */
    public static class Buffer {
        private byte[] buffer;
        private long offset;
        private int available;

        public Buffer(int size) {
            this.buffer = new byte[size];
        }

        public void read(BufferedFile f, long start, int len) throws IOException {
            int read = 0;
            do {
                int count = f.lowLevelRead(this.buffer, start + read, len - read);
                if (count < 0) {
                    throw new IOException();
                }
                read += count;
            } while (read < len);
            this.offset = start;
            this.available = len;
        }

        public long getStartOffset() {
            return this.offset;
        }

        public int getAvailable() {
            return this.available;
        }

        public void discard() {
            this.offset = -1L;
            this.available = 0;
        }

        public boolean contains(long start, int len) {
            if (start + len <= this.offset || start >= this.offset + this.available) {
                return false;
            }
            return true;
        }

        public int get(long marker) {
            int pos = (int) (marker - this.offset);
            return this.buffer[pos] & 255;
        }

        public int get(long marker, byte[] dest, int off, int len) {
            int start = (int) (marker - this.offset);
            int sz = Math.min(this.available - start, len);
            System.arraycopy(this.buffer, start, dest, off, sz);
            return sz;
        }

        public String toString() {
            return "Buffer[" + this.offset + ":" + this.available + "]";
        }
    }
}
