//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.types.SerialTypes;
import fqlite.types.StorageClass;
import fqlite.util.Auxiliary;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class SqliteElement {
    public SerialTypes type;
    public StorageClass serial;
    public int length;
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public SqliteElement(SerialTypes type, StorageClass serial, int length) {
        this.length = length;
        this.type = type;
        this.serial = serial;
    }

    public final String getBLOB(byte[] value) {
        String s = this.toString(value, false);
        String v = null;
        if (s.length() > 64) {
            v = s.substring(0, 64);
        } else {
            v = s;
        }

        String var10000 = parseBLOB(v);
        return var10000 + v;
    }

    public final String toString(byte[] value, boolean withoutQuotes) {
        if (this.type == SerialTypes.INT0) {
            return String.valueOf(0);
        } else if (this.type == SerialTypes.INT1) {
            return String.valueOf(1);
        } else if (this.type == SerialTypes.PRIMARY_KEY && value.length == 0) {
            return "";
        } else if (value.length == 0) {
            return "";
        } else if (this.type == SerialTypes.STRING) {
            String result = decodeString(value).toString();
            return withoutQuotes ? result : result;
        } else if (this.type == SerialTypes.INT8) {
            return String.valueOf(decodeInt8(value[0]));
        } else if (this.type == SerialTypes.INT16) {
            return String.valueOf(decodeInt16(value));
        } else if (this.type == SerialTypes.INT24) {
            return String.valueOf(decodeInt24(value));
        } else if (this.type == SerialTypes.INT32) {
            return String.valueOf(decodeInt32(value));
        } else if (this.type == SerialTypes.INT48) {
            return String.valueOf(decodeInt48(value));
        } else if (this.type == SerialTypes.INT64) {
            return String.valueOf(decodeInt64(value));
        } else if (this.type == SerialTypes.FLOAT64) {
            return String.valueOf(decodeFloat64(value));
        } else if (this.type == SerialTypes.BLOB) {
            return value.length <= 32 ? String.valueOf(Auxiliary.bytesToHex(Arrays.copyOfRange(value, 0, value.length))) : String.valueOf(Auxiliary.bytesToHex(Arrays.copyOfRange(value, 0, 32)));
        } else {
            return null;
        }
    }

    public static String parseBLOB(String blob) {
        if (blob.contains("ffd8")) {
            return "<jpg>";
        } else if (blob.startsWith("89504e470d0a1a")) {
            return "<png>";
        } else if (blob.startsWith("003b")) {
            return "<gif>";
        } else if (blob.startsWith("424d")) {
            return "<bmp>";
        } else if (blob.startsWith("25504446")) {
            return "<pdf>";
        } else if (blob.startsWith("62706c697374")) {
            return "<plist>";
        } else if (!blob.startsWith("49492a00") && !blob.startsWith("4D4D002A")) {
            if (!blob.startsWith("474946383761") && !blob.startsWith("474946383961")) {
                if (blob.startsWith("1f8b")) {
                    return "<gzip>";
                } else {
                    return !blob.contains("66747970686569") && !blob.contains("667479706d") ? "" : "<heic>";
                }
            } else {
                return "<gif>";
            }
        } else {
            return "<tiff>";
        }
    }

    public static final int decodeInt8(byte v) {
        return v;
    }

    public static final int decodeInt16(byte[] v) {
        ByteBuffer bf = ByteBuffer.wrap(v);
        return bf.getShort();
    }

    public static final int decodeInt24(byte[] v) {
        int result = int24bytesToUInt(v);
        return result;
    }

    private static int int24bytesToUInt(byte[] input) {
        return input.length < 3 ? (input[0] & 255) << 8 | (input[1] & 255) << 0 : (input[0] & 255) << 16 | (input[1] & 255) << 8 | (input[2] & 255) << 0;
    }

    public static final int decodeInt32(byte[] v) {
        ByteBuffer bf = ByteBuffer.wrap(v);
        return bf.getInt();
    }

    public static final long decodeInt48ToLong(byte[] v) {
        if (v.length < 6) {
            return 0L;
        } else {
            ByteBuffer bf = ByteBuffer.wrap(v);
            byte[] value = bf.array();
            byte[] converted = new byte[8];

            for(int i = 0; i < 6; ++i) {
                converted[i + 2] = value[i];
            }

            ByteBuffer result = ByteBuffer.wrap(converted);
            long z = result.getLong();
            return z;
        }
    }

    public static final String decodeInt48(byte[] v) {
        if (v.length < 6) {
            return "00";
        } else {
            ByteBuffer bf = ByteBuffer.wrap(v);
            byte[] value = bf.array();
            byte[] converted = new byte[8];

            for(int i = 0; i < 6; ++i) {
                converted[i + 2] = value[i];
            }

            ByteBuffer result = ByteBuffer.wrap(converted);
            long z = result.getLong();
            return Long.toString(z);
        }
    }

    static final String decodeInt64(byte[] v) {
        ByteBuffer bf = ByteBuffer.wrap(v);
        long z = bf.getLong();
        String int64 = Long.toString(z);
        return int64;
    }

    static final String decodeFloat64(byte[] v) {
        ByteBuffer bf = ByteBuffer.wrap(v);
        double d = bf.getDouble();
        return String.format("%.8f", d);
    }

    static final CharBuffer decodeString(byte[] v) {
        return Job.db_encoding.decode(ByteBuffer.wrap(v));
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for(int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 15];
        }

        return new String(hexChars);
    }

    public static boolean isStringContent(byte[] value) {
        float threshold = 0.8F;
        int printable = 0;
        byte[] var6 = value;
        int var5 = value.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            byte b = var6[var4];
            if (b >= 32 && b < 127) {
                ++printable;
            }
        }

        if ((float)(printable / value.length) > threshold) {
            return true;
        } else {
            return false;
        }
    }
}
