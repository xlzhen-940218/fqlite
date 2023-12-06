package goryachev.common.io;

import goryachev.common.util.CKit;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/CIOTools.class */
public class CIOTools {
    private CIOTools() {
    }

    public static void writeByte(OutputStream out, int d) throws IOException {
        out.write(d);
    }

    public static int readByte(InputStream in) throws IOException {
        int c = in.read();
        if (c < 0) {
            throw new EOFException();
        }
        return c & 255;
    }

    public static void writeByteArray(OutputStream out, byte[] b) throws IOException {
        if (b == null) {
            writeInt(out, -1);
            return;
        }
        writeInt(out, b.length);
        out.write(b);
    }

    public static byte[] readByteArray(InputStream in) throws IOException {
        return readByteArray(in, Integer.MAX_VALUE);
    }

    public static byte[] readByteArray(InputStream in, int max) throws IOException {
        int count = readInt(in);
        if (count < 0) {
            if (count == -1) {
                return null;
            }
            throw new IOException("unexpected byte array size " + count);
        } else if (count > max) {
            throw new IOException("expecting no more than " + max + " bytes, received " + count);
        } else {
            byte[] b = new byte[count];
            readFully(in, b);
            return b;
        }
    }

    public static void writeInt(OutputStream out, int d) throws IOException {
        writeByte(out, d >>> 24);
        writeByte(out, d >>> 16);
        writeByte(out, d >>> 8);
        writeByte(out, d);
    }

    public static int readInt(InputStream in) throws IOException {
        int d = readByte(in) << 24;
        return d | (readByte(in) << 16) | (readByte(in) << 8) | readByte(in);
    }

    public static void writeLong(OutputStream out, long d) throws IOException {
        writeByte(out, (int) (d >>> 56));
        writeByte(out, (int) (d >>> 48));
        writeByte(out, (int) (d >>> 40));
        writeByte(out, (int) (d >>> 32));
        writeByte(out, (int) (d >>> 24));
        writeByte(out, (int) (d >>> 16));
        writeByte(out, (int) (d >>> 8));
        writeByte(out, (int) d);
    }

    public static long readLong(InputStream in) throws IOException {
        long d = readByte(in) << 56;
        return d | (readByte(in) << 48) | (readByte(in) << 40) | (readByte(in) << 32) | (readByte(in) << 24) | (readByte(in) << 16) | (readByte(in) << 8) | readByte(in);
    }

    public static void readChars(InputStream in, char[] cs) throws IOException {
        int sz = cs.length;
        for (int i = 0; i < sz; i++) {
            int c = readByte(in) << 8;
            cs[i] = (char) (c | readByte(in));
        }
    }

    public static void writeString(OutputStream out, String s) throws IOException {
        if (s == null) {
            writeInt(out, -1);
            return;
        }
        byte[] b = s.getBytes(CKit.CHARSET_UTF8);
        int len = b.length;
        writeInt(out, len);
        out.write(b);
    }

    public static String readString(InputStream in) throws IOException {
        int len = readInt(in);
        if (len < 0) {
            return null;
        }
        byte[] b = new byte[len];
        readFully(in, b);
        return new String(b, CKit.CHARSET_UTF8);
    }

    public static void readFully(InputStream in, byte[] b) throws IOException {
        readFully(in, b, 0, b.length);
    }

    public static void readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        if (len < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        while (true) {
            int n = i;
            if (n < len) {
                int count = in.read(b, off + n, len - n);
                if (count < 0) {
                    throw new EOFException();
                }
                i = n + count;
            } else {
                return;
            }
        }
    }

    public static long bytesToLong(byte[] buf) {
        return ((buf[0] & 255) << 56) | ((buf[1] & 255) << 48) | ((buf[2] & 255) << 40) | ((buf[3] & 255) << 32) | ((buf[4] & 255) << 24) | ((buf[5] & 255) << 16) | ((buf[6] & 255) << 8) | (buf[7] & 255);
    }

    public static void longToBytes(byte[] buf, long val) {
        buf[0] = (byte) (val >>> 56);
        buf[1] = (byte) (val >>> 48);
        buf[2] = (byte) (val >>> 40);
        buf[3] = (byte) (val >>> 32);
        buf[4] = (byte) (val >>> 24);
        buf[5] = (byte) (val >>> 16);
        buf[6] = (byte) (val >>> 8);
        buf[7] = (byte) val;
    }

    public static int bytesToInt(byte[] buf) {
        return ((buf[0] & 255) << 24) | ((buf[1] & 255) << 16) | ((buf[2] & 255) << 8) | (buf[3] & 255);
    }

    public static void intToBytes(byte[] buf, int val) {
        buf[0] = (byte) (val >>> 24);
        buf[1] = (byte) (val >>> 16);
        buf[2] = (byte) (val >>> 8);
        buf[3] = (byte) val;
    }
}
