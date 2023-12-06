package nl.pvanassen.bplist.ext.base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/ext/base64/Base64.class */
public class Base64 {
    public static final int NO_OPTIONS = 0;
    public static final int ENCODE = 1;
    public static final int DECODE = 0;
    public static final int GZIP = 2;
    public static final int DONT_BREAK_LINES = 8;

    private Base64() {
    }

    public static String encodeBytes(byte[] source, int options) {
        return encodeBytes(source, 0, source.length, options);
    }

    private static String encodeBytes(byte[] source, int off, int len, int options) {
        int dontBreakLines = options & 8;
        int gzip = options & 2;
        if (gzip == 2) {
            ByteArrayOutputStream baos = null;
            GZIPOutputStream gzos = null;
            Base64OutputStream b64os = null;
            try {
                try {
                    baos = new ByteArrayOutputStream();
                    b64os = new Base64OutputStream(baos, 1 | dontBreakLines);
                    gzos = new GZIPOutputStream(b64os);
                    gzos.write(source, off, len);
                    gzos.close();
                    IOUtils.closeQuietly((OutputStream) gzos);
                    IOUtils.closeQuietly((OutputStream) b64os);
                    IOUtils.closeQuietly((OutputStream) baos);
                    try {
                        return new String(baos.toByteArray(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return new String(baos.toByteArray());
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    IOUtils.closeQuietly((OutputStream) gzos);
                    IOUtils.closeQuietly((OutputStream) b64os);
                    IOUtils.closeQuietly((OutputStream) baos);
                    return null;
                }
            } catch (Throwable th) {
                IOUtils.closeQuietly((OutputStream) gzos);
                IOUtils.closeQuietly((OutputStream) b64os);
                IOUtils.closeQuietly((OutputStream) baos);
                throw th;
            }
        }
        boolean breakLines = dontBreakLines == 0;
        int len43 = (len * 4) / 3;
        byte[] outBuff = new byte[len43 + (len % 3 > 0 ? 4 : 0) + (breakLines ? len43 / 76 : 0)];
        int d = 0;
        int e3 = 0;
        int len2 = len - 2;
        int lineLength = 0;
        while (d < len2) {
            Encode3to4.encode3to4(source, d + off, 3, outBuff, e3);
            lineLength += 4;
            if (breakLines && lineLength == 76) {
                outBuff[e3 + 4] = 10;
                e3++;
                lineLength = 0;
            }
            d += 3;
            e3 += 4;
        }
        if (d < len) {
            Encode3to4.encode3to4(source, d + off, len - d, outBuff, e3);
            e3 += 4;
        }
        try {
            return new String(outBuff, 0, e3, "UTF-8");
        } catch (UnsupportedEncodingException e4) {
            return new String(outBuff, 0, e3);
        }
    }
}
