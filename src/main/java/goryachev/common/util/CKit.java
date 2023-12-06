package goryachev.common.util;

import goryachev.common.io.CWriter;
import goryachev.common.log.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import org.apache.commons.codec.CharEncoding;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CKit.class */
public final class CKit {
    public static final String COPYRIGHT = "Copyright © 1996-2023 Andy Goryachev <andy@goryachev.com>  All Rights Reserved.";
    public static final char APPLE = 8984;
    public static final char BOM = 65279;
    private static Boolean eclipseDetected;
    private static final double LOW_MEMORY_CHECK_THRESHOLD = 0.9d;
    private static final double LOW_MEMORY_FAIL_AFTER_GC_THRESHOLD = 0.87d;
    private static final long MS_IN_A_SECOND = 1000;
    private static final long MS_IN_A_MINUTE = 60000;
    private static final long MS_IN_AN_HOUR = 3600000;
    private static final long MS_IN_A_DAY = 86400000;
    private static final double NANOSECONDS_IN_A_SECOND = 1.0E9d;
    protected static final Log log = Log.get("CKit");
    public static final String[] emptyStringArray = new String[0];
    public static final Charset CHARSET_8859_1 = Charset.forName("8859_1");
    public static final Charset CHARSET_ASCII = Charset.forName(CharEncoding.US_ASCII);
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static AtomicInteger id = new AtomicInteger();
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void close(Closeable x) {
        if (x != null) {
            try {
                x.close();
            } catch (Throwable th) {
            }
        }
    }

    public static void close(Socket x) {
        if (x != null) {
            try {
                x.close();
            } catch (Throwable th) {
            }
        }
    }

    public static void close(ZipFile x) {
        if (x != null) {
            try {
                x.close();
            } catch (Throwable th) {
            }
        }
    }

    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null) {
            if (b == null) {
                return true;
            }
            return false;
        } else if (b == null) {
            return false;
        } else {
            Class ca = a.getClass();
            Class cb = b.getClass();
            if (ca.isArray() && cb.isArray()) {
                Class ta = ca.getComponentType();
                Class tb = cb.getComponentType();
                if (ta.isPrimitive() || tb.isPrimitive()) {
                    if (ta.equals(tb)) {
                        if (ta == Byte.TYPE) {
                            return Arrays.equals((byte[]) a, (byte[]) b);
                        }
                        if (ta == Boolean.TYPE) {
                            return Arrays.equals((boolean[]) a, (boolean[]) b);
                        }
                        if (ta == Character.TYPE) {
                            return Arrays.equals((char[]) a, (char[]) b);
                        }
                        if (ta == Short.TYPE) {
                            return Arrays.equals((short[]) a, (short[]) b);
                        }
                        if (ta == Short.TYPE) {
                            return Arrays.equals((short[]) a, (short[]) b);
                        }
                        if (ta == Integer.TYPE) {
                            return Arrays.equals((int[]) a, (int[]) b);
                        }
                        if (ta == Long.TYPE) {
                            return Arrays.equals((long[]) a, (long[]) b);
                        }
                        if (ta == Float.TYPE) {
                            return Arrays.equals((float[]) a, (float[]) b);
                        }
                        if (ta == Double.TYPE) {
                            return Arrays.equals((double[]) a, (double[]) b);
                        }
                        return false;
                    }
                    return false;
                }
                return Arrays.deepEquals((Object[]) a, (Object[]) b);
            }
            return a.equals(b);
        }
    }

    public static boolean notEquals(Object a, Object b) {
        return !equals(a, b);
    }

    public static boolean isBlank(int c) {
        if (Character.isWhitespace(c) || Character.isSpaceChar(c)) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(Object x) {
        if (x == null) {
            return true;
        }
        if (x instanceof char[]) {
            return ((char[]) x).length == 0;
        }
        String s = x.toString();
        int beg = 0;
        int end = s.length();
        while (beg < end && isBlank(s.charAt(beg))) {
            beg++;
        }
        while (beg < end && isBlank(s.charAt(end - 1))) {
            end--;
        }
        return beg == end;
    }

    public static boolean isNotBlank(Object x) {
        return !isBlank(x);
    }

    public static boolean isEmpty(Collection<?> x) {
        if (x != null && x.size() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Collection<?> x) {
        return !isEmpty(x);
    }

    public static void sleep(long ms) {
        if (ms > 0) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void comfortSleep(long start, long minDelay) {
        long t = (start + minDelay) - System.currentTimeMillis();
        sleep(t);
    }

    public static URL getPackageResource(Class<?> c, String resource) {
        String pkg = c.getPackage().getName().replace(".", "/");
        if (pkg.length() != 0) {
            resource = String.valueOf(pkg) + '/' + resource;
        }
        return c.getClassLoader().getResource(resource);
    }

    public static int indexOf(Collection<?> c, Object d) {
        if (c != null) {
            int ix = 0;
            for (Object item : c) {
                if (equals(item, d)) {
                    return ix;
                }
                ix++;
            }
            return -1;
        }
        return -1;
    }

    public static int indexOf(Object[] a, Object d) {
        if (a != null) {
            int ix = 0;
            for (Object item : a) {
                if (equals(item, d)) {
                    return ix;
                }
                ix++;
            }
            return -1;
        }
        return -1;
    }

    public static String msToString(long ms) {
        StringBuffer sb = new StringBuffer();
        long ms2 = ms / MS_IN_A_SECOND;
        long n = ms2 / 3600;
        if (n != 0) {
            sb.append(n);
            sb.append(":");
        }
        long ms3 = ms2 % 3600;
        sb.append(ms3 / 600);
        long ms4 = ms3 % 600;
        sb.append(ms4 / 60);
        sb.append(':');
        long ms5 = ms4 % 60;
        sb.append(ms5 / 10);
        sb.append(ms5 % 10);
        return sb.toString();
    }

    public static String readString(Class cs, String resource) throws Exception {
        return readString(cs.getResourceAsStream(resource));
    }

    public static String readString(InputStream is) throws Exception {
        Reader in = new InputStreamReader(is, CHARSET_UTF8);
        try {
            SB sb = new SB(16384);
            while (true) {
                int c = in.read();
                if (c != -1) {
                    if (sb.length() != 0 || c != 65279) {
                        sb.append((char) c);
                    }
                } else {
                    return sb.toString();
                }
            }
        } finally {
            close(in);
        }
    }

    public static String readStringQuiet(Class cs, String resource) {
        try {
            return readString(cs, resource);
        } catch (Exception e) {
            log.error((Throwable) e);
            return null;
        }
    }

    public static String readString(String resource) throws Exception {
        return readString(resource, CHARSET_UTF8);
    }

    public static String readString(String resource, Charset encoding) throws Exception {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
        try {
            return readString(in, encoding);
        } finally {
            close(in);
        }
    }

    public static String readString(File f, Charset cs) throws Exception {
        return readString(new FileInputStream(f), cs);
    }

    public static String readString(File f, int max, Charset cs) throws Exception {
        long length = f.length();
        return readString(new FileInputStream(f), (int) length, cs);
    }

    public static String readString(File f) throws Exception {
        return readString(f, CHARSET_UTF8);
    }

    public static String readStringQuiet(File f) {
        return readStringQuiet(f, Integer.MAX_VALUE);
    }

    public static String readStringQuiet(File f, int max) {
        try {
            return readString(f, max, CHARSET_UTF8);
        } catch (Exception e) {
            return null;
        }
    }

    public static String readString(InputStream is, String encoding) throws Exception {
        Reader in = new InputStreamReader(is, encoding);
        try {
            return readString(in);
        } finally {
            close(is);
        }
    }

    public static String readString(Reader in) throws Exception {
        return readString(in, Integer.MAX_VALUE);
    }

    public static String readString(InputStream is, Charset cs) throws Exception {
        return readString(is, Integer.MAX_VALUE, cs);
    }

    public static String readString(InputStream is, int max, Charset cs) throws Exception {
        if (!(is instanceof BufferedInputStream)) {
            is = new BufferedInputStream(is);
        }
        Reader in = new InputStreamReader(is, cs);
        try {
            String readString2Hex = readString2Hex(in, max);
            close(is);
            return readString2Hex;
        } catch (Throwable th) {
            close(is);
            throw th;
        }
    }

    public static String readString2Hex(Reader in, int max) throws Exception {
        try {
            return dump(in, max);
        } finally {
            close(in);
        }
    }

    public static String dump(Reader in, int max) throws Exception {
        char[] harray = new char[(2 * max) + (max / 16)];
        char[] tarray = new char[max + (max / 16)];
        int inhexline = 0;
        int intxtline = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < max) {
            byte nextbyte = (byte) in.read();
            if (nextbyte == -1) {
                return new String(harray);
            }
            harray[j] = HEX[(240 & nextbyte) >>> 4];
            int j2 = j + 1;
            harray[j2] = HEX[15 & nextbyte];
            inhexline += 2;
            intxtline++;
            if (inhexline == 32) {
                inhexline = 0;
                j2++;
                harray[j2] = '\n';
            }
            if (nextbyte >= 32 && nextbyte < Byte.MAX_VALUE) {
                tarray[k] = (char) nextbyte;
            } else {
                tarray[k] = '.';
            }
            if (intxtline % 16 == 0) {
                intxtline = 0;
                k++;
                tarray[k] = '\n';
            }
            i++;
            j = j2 + 1;
            k++;
        }
        return new String(harray);
    }

    public static String readString(Reader in, int max) throws Exception {
        try {
            boolean first = true;
            StringBuilder sb = new StringBuilder(16384);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    break;
                }
                if (first) {
                    first = false;
                    if (c == 65279) {
                    }
                }
                if (sb.length() >= max) {
                    break;
                }
                sb.append((char) c);
            }
            return sb.toString();
        } finally {
            close(in);
        }
    }

    public static String[] readLines(Class cs, String resource) throws Exception {
        String s = readString(cs, resource);
        return readLines(s);
    }

    public static String[] readLines(File f) throws Exception {
        String s = readString(f);
        return readLines(s);
    }

    private static String[] readLines(String text) throws Exception {
        BufferedReader rd = new BufferedReader(new StringReader(text));
        try {
            CList<String> lines = new CList<>();
            while (true) {
                String s = rd.readLine();
                if (s != null) {
                    lines.add(s);
                } else {
                    return toArray(lines);
                }
            }
        } finally {
            close(rd);
        }
    }

    public static int compare(String a, String b) {
        if (a == null) {
            if (b != null) {
                return -1;
            }
            return 0;
        } else if (b == null) {
            return 1;
        } else {
            return a.compareTo(b);
        }
    }

    public static int compare(long a, long b) {
        if (a < b) {
            return -1;
        }
        if (a == b) {
            return 0;
        }
        return 1;
    }

    public static int compareLastResort(Object a, Object b) {
        if (a == null) {
            if (b == null) {
                return 0;
            }
            return -1;
        } else if (b == null) {
            return 1;
        } else {
            Class ca = a.getClass();
            Class cb = b.getClass();
            if (ca == cb) {
                if (a instanceof Comparable) {
                    return ((Comparable) a).compareTo(b);
                }
                return a.toString().compareTo(b.toString());
            }
            return ca.getName().compareTo(cb.getName());
        }
    }

    public static int computeHashCode(Object... ss) {
        int hash = 0;
        for (Object s : ss) {
            if (s != null) {
                hash ^= s.hashCode();
            }
        }
        return hash;
    }

    public static String pathToRoot(File root, File file) {
        try {
            root = root.getCanonicalFile();
        } catch (Exception e) {
        }
        try {
            file = file.getCanonicalFile();
        } catch (Exception e2) {
        }
        SB sb = pathToRoot(null, root, file, 0);
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    protected static SB pathToRoot(SB sb, File root, File file, int level) {
        if (file == null) {
            return null;
        }
        if (root.equals(file)) {
            if (level == 0) {
                return null;
            }
            return new SB();
        }
        File p = file.getParentFile();
        SB sb2 = pathToRoot(sb, root, p, level + 1);
        if (sb2 == null) {
            return null;
        }
        if (level > 0) {
            sb2.append(file.getName());
            sb2.append("/");
        }
        return sb2;
    }

    public static void write(File f, String text) throws Exception {
        write(f, text, CHARSET_UTF8);
    }

    public static void write(File f, String text, Charset encoding) throws Exception {
        FileTools.ensureParentFolder(f);
        FileOutputStream out = new FileOutputStream(f);
        if (text != null) {
            try {
                out.write(text.getBytes(encoding));
            } finally {
                close(out);
            }
        }
    }

    public static void write(byte[] buffer, String filename) throws Exception {
        write(buffer, new File(filename));
    }

    public static void write(byte[] buffer, File f) throws Exception {
        FileTools.ensureParentFolder(f);
        FileOutputStream out = new FileOutputStream(f);
        try {
            out.write(buffer);
        } finally {
            close(out);
        }
    }

    public static byte[] readBytes(File f) throws Exception {
        return readBytes(f, Integer.MAX_VALUE);
    }

    public static byte[] readBytes(File f, int maxSize) throws Exception {
        int len = (int) Math.min(maxSize, f.length());
        byte[] buf = new byte[len];
        FileInputStream in = new FileInputStream(f);
        int read = 0;
        while (read < len) {
            try {
                int rv = in.read(buf, read, len - read);
                if (rv < 0) {
                    throw new IOException("eof");
                }
                read += rv;
            } finally {
                close(in);
            }
        }
        return buf;
    }

    public static int sign(long x) {
        if (x < 0) {
            return -1;
        }
        if (x > 0) {
            return 1;
        }
        return 0;
    }

    public static String[] split(String s) {
        CList<String> list = new CList<>();
        if (s != null) {
            int start = 0;
            int sz = s.length();
            boolean white = true;
            for (int i = 0; i < sz; i++) {
                char c = s.charAt(i);
                if (isBlank(c)) {
                    if (!white) {
                        String sub = s.substring(start, i);
                        list.add(sub);
                        white = true;
                    }
                } else if (white) {
                    start = i;
                    white = false;
                }
            }
            if (!white) {
                String sub2 = s.substring(start, sz);
                list.add(sub2);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String[] split(String s, String delim) {
        int start;
        CList<String> list = new CList<>();
        if (s != null) {
            int i = 0;
            while (true) {
                start = i;
                int ix = s.indexOf(delim, start);
                if (ix < 0) {
                    break;
                }
                list.add(s.substring(start, ix));
                i = ix + delim.length();
            }
            list.add(s.substring(start, s.length()));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String[] split(CharSequence s, char delim) {
        return split(s, delim, false);
    }

    public static String[] split(CharSequence s, char delim, boolean includeDelimiter) {
        int start;
        CList<String> a = new CList<>();
        if (s != null) {
            int i = 0;
            while (true) {
                start = i;
                int ix = indexOf(s, delim, start);
                if (ix < 0) {
                    break;
                }
                a.add(s.subSequence(start, ix).toString());
                if (includeDelimiter) {
                    a.add(s.subSequence(ix, ix + 1).toString());
                }
                i = ix + 1;
            }
            a.add(s.subSequence(start, s.length()).toString());
        }
        return (String[]) a.toArray(new String[a.size()]);
    }

    public static int indexOf(CharSequence s, char ch, int start) {
        int len = s.length();
        for (int i = start; i < len; i++) {
            char c = s.charAt(i);
            if (c == ch) {
                return i;
            }
        }
        return -1;
    }

    public static String[] splitAny(String s, String delimiters) {
        CList<String> list = new CList<>();
        int start = 0;
        boolean white = true;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (delimiters.indexOf(c) >= 0) {
                if (!white) {
                    list.add(s.substring(start, i));
                    white = true;
                }
            } else if (white) {
                start = i;
                white = false;
            }
        }
        if (!white) {
            list.add(s.substring(start));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String stackTrace(Throwable e) {
        if (e == null) {
            return null;
        }
        return stackTrace(e, 0);
    }

    public static String stackTrace(Throwable e, int level) {
        SB sb = new SB();
        printStackTrace(sb, e, level);
        return sb.toString();
    }

    private static void printStackTrace(SB sb, Throwable e, int level) {
        sb.a(e).nl();
        StackTraceElement[] trace = e.getStackTrace();
        for (int i = level; i < trace.length; i++) {
            StackTraceElement em = trace[i];
            sb.a("\tat ").a(em).nl();
        }
        Throwable cause = e.getCause();
        if (cause != null) {
            printEnclosedStackTrace(sb, cause, trace, "Caused by: ");
        }
    }

    private static void printEnclosedStackTrace(SB sb, Throwable e, StackTraceElement[] enclosingTrace, String caption) {
        StackTraceElement[] trace = e.getStackTrace();
        int m = trace.length - 1;
        for (int n = enclosingTrace.length - 1; m >= 0 && n >= 0 && trace[m].equals(enclosingTrace[n]); n--) {
            m--;
        }
        int framesInCommon = (trace.length - 1) - m;
        sb.a(caption).a(e).nl();
        for (int i = 0; i <= m; i++) {
            sb.a("\tat ").a(trace[i]).nl();
        }
        if (framesInCommon != 0) {
            sb.a("\t... ").a(Integer.valueOf(framesInCommon)).a(" more").nl();
        }
        Throwable ourCause = e.getCause();
        if (ourCause != null) {
            printEnclosedStackTrace(sb, ourCause, trace, "Caused by: ");
        }
    }

    public static String toString(byte[] b) {
        if (b == null) {
            return null;
        }
        return new String(b, CHARSET_UTF8);
    }

    public static String toStringOrNull(Object x) {
        if (x == null) {
            return null;
        }
        return x.toString();
    }

    public static <T> String toString(T[] tArr) {
        if (tArr == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        SB sb = new SB(512);
        boolean comma = false;
        sb.a("[");
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            T item = tArr[i];
            if (comma) {
                sb.a(", ");
            } else {
                comma = true;
            }
            sb.a(item == null ? FXMLLoader.NULL_KEYWORD : item.toString());
        }
        sb.a("]");
        return sb.toString();
    }

    public static void forceGC() {
        System.runFinalization();
        System.gc();
    }

    public static long availableMemory() {
        Runtime r = Runtime.getRuntime();
        return r.maxMemory() - (r.totalMemory() - r.freeMemory());
    }

    public static String stripBOM(String s) {
        if (s != null && s.startsWith("\ufeff")) {
            return s.substring(1);
        }
        return s;
    }

    public static String className(Object x) {
        if (x == null) {
            return "<null>";
        }
        return x.getClass().getName();
    }

    public static String toStringOrBlank(Object x) {
        return x == null ? ButtonBar.BUTTON_ORDER_NONE : x.toString();
    }

    public static String getExceptionMessage(Throwable e) {
        if (e == null) {
            return null;
        }
        String msg = e.getMessage();
        if (isBlank(msg)) {
            msg = e.getClass().getSimpleName();
        }
        return msg;
    }

    public static String getSimpleName(Object x) {
        return Dump.simpleName(x);
    }

    public static boolean startsWith(String a, String prefix) {
        if (a != null) {
            return a.startsWith(prefix);
        }
        return false;
    }

    public static void readFully(InputStream in, byte[] b) throws Exception {
        int i = 0;
        while (true) {
            int offset = i;
            if (offset < b.length) {
                int count = in.read(b, offset, b.length - offset);
                if (count < 0) {
                    throw new EOFException("read only " + offset + " bytes instead of " + b.length);
                }
                i = offset + count;
            } else {
                return;
            }
        }
    }

    public static String getExtension(String name) {
        int ix;
        if (name != null && (ix = name.lastIndexOf(46)) >= 0) {
            return name.substring(ix + 1).toLowerCase();
        }
        return ButtonBar.BUTTON_ORDER_NONE;
    }

    public static String getBaseName(String name) {
        int ix;
        if (name != null && (ix = name.lastIndexOf(46)) >= 0) {
            return name.substring(0, ix);
        }
        return ButtonBar.BUTTON_ORDER_NONE;
    }

    public static boolean sameClass(Object a, Object b) {
        return a == null ? b == null : b != null && a.getClass() == b.getClass();
    }

    public static boolean contains(Object[] array, Object x) {
        if (array != null && x != null) {
            for (Object a : array) {
                if (x.equals(a)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static long copy(InputStream in, OutputStream out) throws Exception {
        return copy(in, out, 65536);
    }

    public static long copy(InputStream in, OutputStream out, int bufferSize) throws Exception {
        if (bufferSize < 1) {
            throw new IllegalArgumentException("invalid bufferSize=" + bufferSize);
        }
        if (in == null) {
            return 0L;
        }
        byte[] buf = new byte[bufferSize];
        long count = 0;
        while (true) {
            checkCancelled();
            int rd = in.read(buf);
            if (rd < 0) {
                out.flush();
                return count;
            } else if (rd > 0) {
                out.write(buf, 0, rd);
                count += rd;
            }
        }
    }

    public static String compressString(String s) throws Exception {
        if (s == null) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        ByteArrayOutputStream ba = new ByteArrayOutputStream((s.length() * 2) + 20);
        DeflaterOutputStream out = new DeflaterOutputStream(ba);
        byte[] bytes = s.getBytes(CHARSET_UTF8);
        out.write(bytes);
        out.finish();
        out.flush();
        byte[] compressed = ba.toByteArray();
        return Hex.toHexString(compressed);
    }

    public static String decompressString(String s) throws Exception {
        if (s == null) {
            return null;
        }
        byte[] compressed = Hex.parseByteArray(s);
        InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(compressed));
        ByteArrayOutputStream out = new ByteArrayOutputStream(compressed.length * 2);
        copy(in, out);
        byte[] decompressed = out.toByteArray();
        return new String(decompressed, CHARSET_UTF8);
    }

    public static boolean isEven(int sz) {
        return (sz & 1) == 0;
    }

    public static boolean isOdd(int sz) {
        return !isEven(sz);
    }

    public static String beforeSpace(String s) {
        return TextTools.beforeSpace(s);
    }

    public static int indexOfWhitespace(String s) {
        return TextTools.indexOfWhitespace(s);
    }

    public static int indexOfWhitespace(String s, int start) {
        return TextTools.indexOfWhitespace(s, start);
    }

    public static boolean startsWithIgnoreCase(String s, String pattern) {
        return TextTools.startsWithIgnoreCase(s, pattern);
    }

    public static boolean endsWithIgnoreCase(String s, String suffix) {
        return TextTools.endsWithIgnoreCase(s, suffix);
    }

    public static void makeParentFile(File f) throws IOException {
        File p = f.getCanonicalFile().getParentFile();
        if (p != null) {
            p.mkdirs();
        }
    }

    public static File ensureExtension(File f, String ext) {
        String name = f.getName();
        if (name.contains(".")) {
            return f;
        }
        return new File(f.getParentFile(), String.valueOf(name) + ext);
    }

    public static String ensureExtension(String name, String ext) {
        if (name.contains(".")) {
            return name;
        }
        return String.valueOf(name) + ext;
    }

    public static int mod(int x, int max) {
        if (x < 0) {
            return (max + ((x + 1) % max)) - 1;
        }
        return x % max;
    }

    public static int id() {
        return id.getAndIncrement();
    }

    public static void todo() {
        throw new Error("(to be implemented)");
    }

    public static byte[] readBytes(Object parent, String name) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream(65536);
        Class c = parent instanceof Class ? (Class) parent : parent.getClass();
        InputStream in = c.getResourceAsStream(name);
        try {
            copy(in, out);
            close(in);
            close(out);
            return out.toByteArray();
        } catch (Throwable th) {
            close(in);
            close(out);
            throw th;
        }
    }

    public static byte[] readBytesQuiet(Object parent, String name) {
        try {
            return readBytes(parent, name);
        } catch (Exception e) {
            return null;
        }
    }

    public static void checkCancelled() throws CancelledException, LowMemoryException {
        if (Thread.interrupted()) {
            throw new CancelledException();
        }
        if (isLowMemory()) {
            throw new LowMemoryException();
        }
    }

    public static boolean isLowMemory() {
        return isLowMemory(LOW_MEMORY_CHECK_THRESHOLD, LOW_MEMORY_FAIL_AFTER_GC_THRESHOLD);
    }

    public static boolean isLowMemory(double triggerThreshold, double failThreshold) {
        Runtime r = Runtime.getRuntime();
        long total = r.totalMemory();
        long used = total - r.freeMemory();
        long max = r.maxMemory();
        if (used > ((long) (max * triggerThreshold))) {
            System.gc();
            System.runFinalization();
            long total2 = r.totalMemory();
            long used2 = total2 - r.freeMemory();
            if (used2 > ((long) (max * failThreshold))) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean containsAny(String text, String pattern) {
        if (text != null) {
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (text.indexOf(c) >= 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static byte[] readBytes(InputStream in) throws Exception {
        return readBytes(in, Integer.MAX_VALUE);
    }

    public static byte[] readBytes(InputStream in, int max) throws Exception {
        int rd;
        if (in == null) {
            return null;
        }
        int read = 0;
        byte[] buf = new byte[Math.min(max, 65536)];
        ByteArrayOutputStream ba = new ByteArrayOutputStream(65536);
        while (true) {
            if (read >= max || (rd = in.read(buf)) < 0) {
                break;
            } else if (rd > 0) {
                int allowed = max - read;
                if (allowed < rd) {
                    ba.write(buf, 0, allowed);
                    break;
                }
                ba.write(buf, 0, rd);
                read += rd;
            } else {
                sleep(10L);
            }
        }
        return ba.toByteArray();
    }

    public static long time() {
        return System.nanoTime() / 1000000;
    }

    public static String toLowerCase(Object x) {
        if (x == null) {
            return null;
        }
        return x.toString().toLowerCase(Locale.ENGLISH);
    }

    public static String toUpperCase(Object x) {
        if (x == null) {
            return null;
        }
        return x.toString().toUpperCase(Locale.ENGLISH);
    }

    public static BufferedInputStream toBufferedInputStream(InputStream in) {
        if (in instanceof BufferedInputStream) {
            return (BufferedInputStream) in;
        }
        if (in != null) {
            return new BufferedInputStream(in);
        }
        return null;
    }

    public static BufferedOutputStream toBufferedOutputStream(OutputStream out) {
        if (out instanceof BufferedOutputStream) {
            return (BufferedOutputStream) out;
        }
        return new BufferedOutputStream(out);
    }

    public static BufferedReader toBufferedReader(Reader rd) {
        if (rd instanceof BufferedReader) {
            return (BufferedReader) rd;
        }
        return new BufferedReader(rd);
    }

    public static BufferedWriter toBufferedWriter(Writer wr) {
        if (wr instanceof BufferedWriter) {
            return (BufferedWriter) wr;
        }
        return new BufferedWriter(wr);
    }

    public static String trimBOM(String s) {
        if (s != null && s.length() > 0 && s.charAt(0) == 65279) {
            return s.substring(1);
        }
        return s;
    }

    public static String getPercentString(double value, int significantDigits) {
        MathContext mc = new MathContext(significantDigits, RoundingMode.HALF_DOWN);
        BigDecimal d = new BigDecimal(100.0d * value, mc);
        return String.valueOf(d.toPlainString()) + FXMLLoader.RESOURCE_KEY_PREFIX;
    }

    public static void append(String filename, String s) throws Exception {
        append(new File(filename), s);
    }

    public static void append(File f, String s) throws Exception {
        FileTools.ensureParentFolder(f);
        CWriter wr = new CWriter(new FileOutputStream(f, true), CHARSET_UTF8);
        if (s != null) {
            try {
                wr.write(s);
            } finally {
                close(wr);
            }
        }
    }

    public static Object deepCopy(Object x) throws Exception {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(x);
            close(out);
            byte[] b = bout.toByteArray();
            ByteArrayInputStream bin = new ByteArrayInputStream(b);
            ObjectInputStream in = new ObjectInputStream(bin);
            Object readObject = in.readObject();
            close(in);
            return readObject;
        } catch (Exception e) {
            throw new Exception("failed to copy " + getSimpleName(x), e);
        }
    }

    public static String toURL(String s) {
        byte[] bytes = s.getBytes(CHARSET_UTF8);
        int sz = bytes.length;
        SB sb = new SB(sz * 3);
        for (byte b : bytes) {
            int c = b & 255;
            if (c <= 32) {
                sb.append('+');
            } else if (c > 127) {
                sb.append('%');
                Hex.hexByte(sb, c);
            } else {
                switch (c) {
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 47:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 91:
                    case 92:
                    case 93:
                    case 94:
                    case 96:
                    case 123:
                    case 124:
                    case 125:
                    case 126:
                        sb.append('%');
                        Hex.hexByte(sb, c);
                        continue;
                    default:
                        sb.append((char) c);
                        continue;
                }
            }
        }
        return sb.toString();
    }

    public static String formatTime24(int hour, int min) {
        SB sb = new SB(5);
        if (hour < 10) {
            sb.a('0');
        }
        sb.a(Integer.valueOf(hour));
        sb.a(':');
        if (min < 10) {
            sb.a('0');
        }
        sb.a(Integer.valueOf(min));
        return sb.toString();
    }

    public static String formatTwoDigits(int x) {
        if (x >= 0 && x < 10) {
            return "0" + x;
        }
        return String.valueOf(x);
    }

    public static String formatTimePeriod(long t) {
        boolean force = false;
        SB sb = new SB();
        int d = (int) (t / MS_IN_A_DAY);
        if (d != 0) {
            sb.append(d);
            sb.append(':');
            t %= MS_IN_A_DAY;
            force = true;
        }
        int h = (int) (t / MS_IN_AN_HOUR);
        if (force || h != 0) {
            append(sb, h, 2);
            sb.append(':');
            t %= MS_IN_AN_HOUR;
            force = true;
        }
        int m = (int) (t / MS_IN_A_MINUTE);
        if (force || m != 0) {
            append(sb, m, 2);
            sb.append(':');
            t %= MS_IN_A_MINUTE;
            force = true;
        }
        int s = (int) (t / MS_IN_A_SECOND);
        if (force) {
            append(sb, s, 2);
        } else {
            sb.append(s);
        }
        sb.append('.');
        int ms = (int) (t % MS_IN_A_SECOND);
        append(sb, ms, 3);
        return sb.toString();
    }

    private static void append(SB sb, int n, int precision) {
        String s = String.valueOf(n);
        int n2 = precision - s.length();
        if (n2 > 0) {
            sb.append("0000000000", 0, n2);
        }
        sb.append(s);
    }

    public static byte[] cat(byte[] a, byte[] b) {
        byte[] rv = new byte[a.length + b.length];
        System.arraycopy(a, 0, rv, 0, a.length);
        System.arraycopy(b, 0, rv, a.length, b.length);
        return rv;
    }

    public static Properties readProperties(String filename) {
        return readProperties(new File(filename));
    }

    public static Properties readProperties(File f) {
        Properties p = new Properties();
        try {
            FileInputStream in = new FileInputStream(f);
            p.load(in);
            close(in);
        } catch (Exception e) {
        }
        return p;
    }

    public static void writeProperties(Properties p, File f) throws Exception {
        if (p != null) {
            FileOutputStream out = new FileOutputStream(f);
            try {
                p.store(out, (String) null);
            } finally {
                close(out);
            }
        }
    }

    public static int binCount(int itemCount, int binSize) {
        if (itemCount == 0) {
            return 0;
        }
        if (binSize == 0) {
            return itemCount;
        }
        return 1 + ((itemCount - 1) / binSize);
    }

    public static byte[] concat(byte[] a, byte[] b) {
        byte[] r = new byte[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }

    public static byte[] getBytes(String s) {
        if (s == null) {
            return null;
        }
        return s.getBytes(CHARSET_UTF8);
    }

    public static int round(double x) {
        return (int) Math.round(x);
    }

    public static int ceil(double x) {
        return (int) Math.ceil(x);
    }

    public static int floor(double x) {
        return (int) Math.floor(x);
    }

    public static <T> CList<T> collectPublicStaticFields(Class<?> c, Class<T> type) {
        Field[] fields;
        CList<T> rv = new CList<>();
        for (Field f : c.getFields()) {
            int m = f.getModifiers();
            if (Modifier.isPublic(m) && Modifier.isStatic(m)) {
                try {
                    Object v = f.get(null);
                    if (v != null && type.isAssignableFrom(v.getClass())) {
                        rv.add((T) v);
                    }
                } catch (Exception e) {
                    log.error((Throwable) e);
                }
            }
        }
        return rv;
    }

    public static boolean isEclipse() {
        if (eclipseDetected == null) {
            eclipseDetected = Boolean.valueOf(new File(".project").exists() && new File(".classpath").exists());
        }
        return eclipseDetected.booleanValue();
    }

    public static <T> Collection<T> asList(T... tArr) {
        return new CList((Object[]) tArr);
    }

    public static String[] toArray(Collection<String> coll) {
        if (coll == null) {
            return null;
        }
        return (String[]) coll.toArray(new String[coll.size()]);
    }

    public static <T> T[] toArray(Class<T> type, Collection<T> coll) {
        if (coll == null) {
            return null;
        }
        int sz = coll.size();
        Object[] a = (Object[]) Array.newInstance((Class<?>) type, sz);
        return (T[]) coll.toArray(a);
    }

    public static String tabs(int count) {
        if (count <= 0) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        return new SB(count).tab(count).toString();
    }

    public static String spaces(int count) {
        if (count <= 0) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        return new SB(count).sp(count).toString();
    }

    public static <T> T[] addAndGrow(T[] tArr, T item) {
        int len = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, len + 1);
        tArr2[len] = item;
        return tArr2;
    }

    public static <T> T[] removeAndShrink(T[] tArr, T item) {
        int ix = indexOf(tArr, item);
        if (ix < 0) {
            return tArr;
        }
        int len = tArr.length;
        T[] tArr2 = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), len - 1));
        if (ix > 0) {
            System.arraycopy(tArr, 0, tArr2, 0, ix);
        }
        if (ix + 1 < len) {
            System.arraycopy(tArr, ix + 1, tArr2, ix, (len - ix) - 1);
        }
        return tArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> CMap<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... pairs) {
        int sz = pairs.length;
        CMap<K, V> m = (CMap<K, V>) new CMap(sz / 2);
        int i = 0;
        while (i < sz) {
            Object obj = pairs[i];
            if (!obj.getClass().isAssignableFrom(keyType)) {
                throw new Error("Expecting " + keyType + " at index " + i);
            }
            int i2 = i + 1;
            Object obj2 = pairs[i2];
            if (obj2 != null && !obj2.getClass().isAssignableFrom(valueType)) {
                throw new Error("Expecting " + valueType + " at index " + i2);
            }
            Object old = m.put((K) obj, (V) obj2);
            if (old != null) {
                throw new Error("Duplicate key " + obj + " at index " + (i2 - 1));
            }
            i = i2 + 1;
        }
        return m;
    }

    public static <T> CSet<T> toSet(Class<T> type, T... tArr) {
        int sz = tArr.length;
        CSet<T> rv = new CSet<>(sz);
        for (int i = 0; i < sz; i++) {
            T item = tArr[i];
            if (!item.getClass().isAssignableFrom(type)) {
                throw new Error("Expecting " + type + " at index " + i);
            }
            rv.add(item);
        }
        return rv;
    }

    public static String codePointToString(int cp) {
        char[] cs = Character.toChars(cp);
        return new String(cs);
    }

    public static boolean inRange(int value, int min, int max) {
        if (min > max) {
            throw new Error("min > max");
        }
        return value >= min && value <= max;
    }

    public static long kibi(int x) {
        return 1024 * x;
    }

    public static long mebi(int x) {
        return 1048576 * x;
    }

    public static long gibi(int x) {
        return 1073741824 * x;
    }

    public static long tebi(int x) {
        return 1099511627776L * x;
    }

    public static long milliseconds(int hours, int minutes, int seconds) {
        return (hours * MS_IN_AN_HOUR) + (minutes * MS_IN_A_MINUTE) + (seconds * MS_IN_A_SECOND);
    }

    public static long secondsToMilliseconds(int seconds) {
        return seconds * MS_IN_A_SECOND;
    }

    public static long minutesToMilliseconds(int minutes) {
        return minutes * MS_IN_A_MINUTE;
    }

    public static long hoursToMilliseconds(int hours) {
        return hours * MS_IN_AN_HOUR;
    }

    public static long daysToMilliseconds(int days) {
        return days * MS_IN_A_DAY;
    }

    public static byte[] copy(byte[] b) {
        if (b == null) {
            return null;
        }
        byte[] c = new byte[b.length];
        System.arraycopy(b, 0, c, 0, b.length);
        return c;
    }

    public static <S, T> List<T> transform(List<S> src, Function<S, T> converter) {
        return transform(src, null, converter);
    }

    public static <S, T> List<T> transform(List<S> src, List<T> target, Function<S, T> converter) {
        if (src == null) {
            return null;
        }
        int sz = src.size();
        if (target == null) {
            target = new CList(sz);
        }
        for (int i = 0; i < sz; i++) {
            S s = src.get(i);
            T t = converter.apply(s);
            target.add(t);
        }
        return target;
    }

    public static byte[] copyOf(byte[] b) {
        if (b == null) {
            return null;
        }
        return Arrays.copyOf(b, b.length);
    }

    public static <T> T[] add(T[] tArr, T item) {
        int len = tArr.length;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, len + 1);
        tArr2[len] = item;
        return tArr2;
    }

    public static <T> T[] remove(T[] tArr, T item) {
        int ix = indexOf(tArr, item);
        if (ix < 0) {
            return tArr;
        }
        int len = tArr.length - 1;
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, len);
        int len2 = len - ix;
        if (len2 > 0) {
            System.arraycopy(tArr, ix + 1, tArr2, ix, len2);
        }
        return tArr2;
    }

    public static int toNeatSize(int x) {
        int v = Integer.highestOneBit(x);
        if (x == v) {
            return v;
        }
        int v2 = v << 1;
        if (v2 < 0) {
            return x;
        }
        return v2;
    }

    public static <T> Iterator<T> iterator(final T[] tArr) {
        if (tArr == null) {
            return Collections.emptyIterator();
        }
        return new Iterator<T>() { // from class: goryachev.common.util.CKit.1
            private int ix;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.ix < tArr.length;
            }

            @Override // java.util.Iterator
            public T next() {
                Object[] objArr = tArr;
                int i = this.ix;
                this.ix = i + 1;
                return (T) objArr[i];
            }
        };
    }

    public static String trim(Object x, int maxLength) {
        if (x == null) {
            return null;
        }
        String s = x.toString();
        if (s.length() > maxLength) {
            return s.substring(0, maxLength);
        }
        return s;
    }

    public static <T> T[] shallowCopy(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) Arrays.copyOf(tArr, tArr.length);
    }

    public static double elapsedSeconds(long startNanoseconds) {
        long t = System.nanoTime() - startNanoseconds;
        return t / NANOSECONDS_IN_A_SECOND;
    }
}
