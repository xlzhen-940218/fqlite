package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/MurmurHash3.class */
public class MurmurHash3 {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int hash(byte[] data, int offset, int len, int seed) {
        int h1 = seed;
        int roundedEnd = offset + (len & (-4));
        for (int i = offset; i < roundedEnd; i += 4) {
            int k1 = ((data[i] & 255) | ((data[i + 1] & 255) << 8) | ((data[i + 2] & 255) << 16) | (data[i + 3] << 24)) * (-862048943);
            int h12 = h1 ^ (((k1 << 15) | (k1 >>> 17)) * 461845907);
            h1 = (((h12 << 13) | (h12 >>> 19)) * 5) - 430675100;
        }
        int k12 = 0;
        switch (len & 3) {
            case 1:
                int k13 = (k12 | (data[roundedEnd] & 255)) * (-862048943);
                h1 ^= ((k13 << 15) | (k13 >>> 17)) * 461845907;
                break;
            case 2:
                k12 |= (data[roundedEnd + 1] & 255) << 8;
                int k132 = (k12 | (data[roundedEnd] & 255)) * (-862048943);
                h1 ^= ((k132 << 15) | (k132 >>> 17)) * 461845907;
                break;
            case 3:
                k12 = (data[roundedEnd + 2] & 255) << 16;
                k12 |= (data[roundedEnd + 1] & 255) << 8;
                int k1322 = (k12 | (data[roundedEnd] & 255)) * (-862048943);
                h1 ^= ((k1322 << 15) | (k1322 >>> 17)) * 461845907;
                break;
        }
        int h13 = h1 ^ len;
        int h14 = (h13 ^ (h13 >>> 16)) * (-2048144789);
        int h15 = (h14 ^ (h14 >>> 13)) * (-1028477387);
        return h15 ^ (h15 >>> 16);
    }

    public static int hash(CharSequence data, int offset, int len, int seed) {
        int k2;
        int bits;
        int i;
        int h1 = seed;
        int pos = offset;
        int end = offset + len;
        int k1 = 0;
        int shift = 0;
        int nBytes = 0;
        while (pos < end) {
            int i2 = pos;
            pos++;
            int code = data.charAt(i2);
            if (code < 128) {
                k2 = code;
                bits = 8;
            } else if (code < 2048) {
                k2 = 192 | (code >> 6) | ((128 | (code & 63)) << 8);
                bits = 16;
            } else if (code < 55296 || code > 57343 || pos >= end) {
                k2 = 224 | (code >> 12) | ((128 | ((code >> 6) & 63)) << 8) | ((128 | (code & 63)) << 16);
                bits = 24;
            } else {
                pos++;
                int utf32 = ((code - 55232) << 10) + (data.charAt(pos) & 1023);
                k2 = (255 & (240 | (utf32 >> 18))) | ((128 | ((utf32 >> 12) & 63)) << 8) | ((128 | ((utf32 >> 6) & 63)) << 16) | ((128 | (utf32 & 63)) << 24);
                bits = 32;
            }
            k1 |= k2 << shift;
            shift += bits;
            if (shift >= 32) {
                int k12 = k1 * (-862048943);
                int h12 = h1 ^ (((k12 << 15) | (k12 >>> 17)) * 461845907);
                h1 = (((h12 << 13) | (h12 >>> 19)) * 5) - 430675100;
                shift -= 32;
                if (shift != 0) {
                    i = k2 >>> (bits - shift);
                } else {
                    i = 0;
                }
                k1 = i;
                nBytes += 4;
            }
        }
        if (shift > 0) {
            nBytes += shift >> 3;
            int k13 = k1 * (-862048943);
            h1 ^= ((k13 << 15) | (k13 >>> 17)) * 461845907;
        }
        int h13 = h1 ^ nBytes;
        int h14 = (h13 ^ (h13 >>> 16)) * (-2048144789);
        int h15 = (h14 ^ (h14 >>> 13)) * (-1028477387);
        return h15 ^ (h15 >>> 16);
    }

    public static int hash(CharSequence data, int seed) {
        return hash(data, 0, data.length(), seed);
    }
}
