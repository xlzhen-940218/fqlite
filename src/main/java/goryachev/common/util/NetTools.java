package goryachev.common.util;

import goryachev.common.log.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/NetTools.class */
public class NetTools {
    protected static final Log log = Log.get("NetTools");

    public static String readString(String url) throws Exception {
        HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
        c.setRequestProperty("Connection", "close");
        c.setInstanceFollowRedirects(true);
        int code = c.getResponseCode();
        if (code != 200) {
            throw new Exception("HTTP " + code + " reading " + url);
        }
        InputStream in = getInputStream(c);
        try {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            CKit.copy(in, ba);
            byte[] content = ba.toByteArray();
            CKit.close(in);
            String s = new String(content, CKit.CHARSET_UTF8);
            Charset cs = extractCharset(s);
            if (!CKit.CHARSET_UTF8.equals(cs)) {
                s = new String(content, cs);
            }
            return s;
        } catch (Throwable th) {
            CKit.close(in);
            throw th;
        }
    }

    private static InputStream getInputStream(HttpURLConnection c) throws Exception {
        InputStream in = c.getInputStream();
        String s = c.getRequestProperty("Content-Encoding");
        if (s != null && s.contains("zip")) {
            return new GZIPInputStream(in);
        }
        return in;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0072 A[Catch: Exception -> 0x00be, TryCatch #0 {Exception -> 0x00be, blocks: (B:2:0x0000, B:4:0x000b, B:6:0x001d, B:15:0x0065, B:7:0x0028, B:14:0x0062, B:11:0x0039, B:22:0x00aa, B:18:0x0072, B:19:0x0079, B:21:0x00a7, B:24:0x00b2), top: B:30:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Charset extractCharset(String s) {
        String s2;
        int ix;
        int end = 0;
        try {
            int ix2 = s.indexOf(62);
            if (ix2 > 0 && (ix = TextTools.indexOfIgnoreCase((s2 = s.substring(0, ix2)), "encoding")) >= 0) {
                int ix3;
                for (ix3 = ix + "encoding".length(); ix3 < s2.length(); ix3++) {
                    char c = s2.charAt(ix3);
                    if (!CKit.isBlank(c)) {
                        switch (c) {
                            case '\"':
                            case '\'':
                            case '=':
                                break;
                            default:
                                for (end = ix3; end < s2.length(); end++) {
                                    switch (s2.charAt(end)) {
                                        case '\"':
                                        case '\'':
                                        case '>':
                                        case '?':
                                            String enc = s2.substring(ix3, end);
                                            return Charset.forName(enc);
                                        default:
                                    }
                                }
                                String enc2 = s2.substring(ix3, end);
                                return Charset.forName(enc2);
                        }
                    }
                }
                while (end < s2.length()) {
                }
                String enc22 = s2.substring(ix3, end);
                return Charset.forName(enc22);
            }
        } catch (Exception e) {
            log.error((Throwable) e);
        }
        return CKit.CHARSET_UTF8;
    }

    public static URI parseURI(String uri) throws Exception {
        return new URI(parseUrlString(uri));
    }

    public static URL parseURL(String url) throws Exception {
        return new URL(parseUrlString(url));
    }

    public static String parseUrlString(String url) {
        byte[] bytes = url.getBytes(CKit.CHARSET_UTF8);
        SB sb = new SB(bytes.length * 2);
        for (byte b : bytes) {
            if (b < 0) {
                sb.a('%');
                sb.a(Hex.toHexByte(b & 255));
            } else if (b == 32) {
                sb.a("%20");
            } else {
                sb.a((char) b);
            }
        }
        return sb.toString();
    }
}
