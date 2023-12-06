package goryachev.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Dump.class */
public class Dump {
    private static final String HEX = "0123456789ABCDEF";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMdd-HH:mm:ss.SSS");

    public static String list(Object x) {
        if (x == null) {
            return "<null>";
        }
        if (x instanceof int[]) {
            return listIntegerArray((int[]) x);
        }
        if (x instanceof float[]) {
            return listFloatArray((float[]) x);
        }
        if (x instanceof byte[]) {
            return hex((byte[]) x, 0L);
        }
        if (x.getClass().isArray()) {
            return listObjectArray((Object[]) x);
        }
        if (x instanceof Collection) {
            return listCollection((Collection) x);
        }
        if (x instanceof Enumeration) {
            return listEnumeration((Enumeration) x);
        }
        if (x instanceof Iterable) {
            return listIterable((Iterable) x);
        }
        if (x instanceof Iterator) {
            return listIterator((Iterator) x);
        }
        if (x instanceof Map) {
            return listMap((Map) x);
        }
        return "Can't list " + CKit.getSimpleName(x);
    }

    private static String listIntegerArray(int[] a) {
        if (a == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        SB sb = new SB();
        for (int i = 0; i < a.length; i++) {
            sb.append('\n');
            sb.append(format(i));
            sb.append(" [").append(format(a[i])).append(']');
        }
        return sb.toString();
    }

    private static String listFloatArray(float[] ar) {
        SB sb = new SB();
        for (int i = 0; i < ar.length; i++) {
            sb.append('\n');
            sb.append(format(i));
            sb.append(" [").append(ar[i]).append(']');
        }
        return sb.toString();
    }

    private static String listObjectArray(Object[] ar) {
        SB sb = new SB();
        for (int i = 0; i < ar.length; i++) {
            sb.append('\n');
            sb.append(format(i));
            sb.append(" [").append(ar[i]).append(']');
        }
        return sb.toString();
    }

    private static String listEnumeration(Enumeration<?> en) {
        CList<Object> a = new CList<>();
        while (en.hasMoreElements()) {
            a.add(en.nextElement());
        }
        return list(a.toArray());
    }

    private static String listCollection(Collection<?> c) {
        return list(c.toArray());
    }

    private static String listIterable(Iterable<?> c) {
        return list(c.iterator());
    }

    private static String listIterator(Iterator<?> c) {
        CList<Object> a = new CList<>();
        while (c.hasNext()) {
            a.add(c.next());
        }
        return list(a.toArray());
    }

    private static String listMap(Map<?, ?> map) {
        CList<String> a = new CList<>();
        for (Map.Entry<?, ?> en : map.entrySet()) {
            a.add(en.getKey() + "=" + en.getValue());
        }
        return list(a.toArray());
    }

    public static String hex(byte[] bytes, long startAddress) {
        if (bytes == null) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        SB sb = new SB((((bytes.length / 16) + 1) * 77) + 1);
        hex(sb, bytes, startAddress, 0);
        return sb.toString();
    }

    private static void hex(SB sb, byte[] bytes, long startAddress, int indent) {
        boolean bigfile = startAddress + ((long) bytes.length) > 65535;
        int col = 0;
        long addr = startAddress;
        int lineStart = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (col == 0) {
                for (int j = 0; j < indent; j++) {
                    sb.a(' ');
                }
                if (col == 0) {
                    lineStart = i;
                    if (bigfile) {
                        hex(sb, (int) (addr >> 24));
                        hex(sb, (int) (addr >> 16));
                    }
                    hex(sb, (int) (addr >> 8));
                    hex(sb, (int) addr);
                    sb.append("  ");
                }
            }
            hex(sb, bytes[i]);
            sb.append(' ');
            if (col >= 15) {
                dumpASCII(sb, bytes, lineStart);
                col = 0;
            } else {
                col++;
            }
            addr++;
        }
        if (col == 0) {
            return;
        }
        while (true) {
            int i2 = col;
            col++;
            if (i2 < 16) {
                sb.append("   ");
            } else {
                dumpASCII(sb, bytes, lineStart);
                return;
            }
        }
    }

    public static String hex(byte[] bytes) {
        return hex(bytes, 0L);
    }

    public static String hex(long x) {
        char[] cs = new char[16];
        int shift = 60;
        for (int i = 0; i < cs.length; i++) {
            cs[i] = "0123456789ABCDEF".charAt(((int) (x >>> shift)) & 15);
            shift -= 4;
        }
        return new String(cs);
    }

    public static String field(Class<?> cls, int n) {
        try {
            Field[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType() == Integer.TYPE && fields[i].getInt(null) == n) {
                    return fields[i].getName();
                }
            }
        } catch (Throwable th) {
        }
        return String.valueOf(n);
    }

    public static String field(Class<?> cls, short n) {
        try {
            Field[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType() == Short.TYPE && fields[i].getShort(null) == n) {
                    return fields[i].getName();
                }
            }
        } catch (Throwable th) {
        }
        return String.valueOf((int) n);
    }

    public static String field(Class<?> cls, byte n) {
        try {
            Field[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType() == Byte.TYPE && fields[i].getByte(null) == n) {
                    return fields[i].getName();
                }
            }
        } catch (Throwable th) {
        }
        return String.valueOf((int) n);
    }

    public static String field(Class<?> cls, String s) {
        try {
            Field[] fields = cls.getFields();
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getType() == String.class && fields[i].get(null).equals(s)) {
                    return fields[i].getName();
                }
            }
        } catch (Throwable th) {
        }
        return s;
    }

    private static String format(int n) {
        String s = "          " + n;
        return s.substring(s.length() - 10, s.length());
    }

    private static void dumpASCII(SB sb, byte[] bytes, int lineStart) {
        sb.append(' ');
        int max = Math.min(bytes.length, lineStart + 16);
        for (int i = lineStart; i < max; i++) {
            int d = bytes[i] & 255;
            if (d < 32 || d >= 127) {
                d = 46;
            }
            sb.append((char) d);
        }
        sb.append('\n');
    }

    public static void hex(SB sb, int c) {
        sb.append("0123456789ABCDEF".charAt((c >> 4) & 15));
        sb.append("0123456789ABCDEF".charAt(c & 15));
    }

    public static String hex1(int d) {
        return String.valueOf("0123456789ABCDEF".charAt(d & 15));
    }

    public static String hex2(int d) {
        char[] ch = {"0123456789ABCDEF".charAt((d >> 4) & 15), "0123456789ABCDEF".charAt(d & 15)};
        return new String(ch);
    }

    public static String toHexString(byte[] b) {
        if (b == null) {
            return "<null>";
        }
        int sz = b.length;
        SB sb = new SB(sz + sz);
        for (byte b2 : b) {
            sb.append(hex1(b2 >> 4));
            sb.append(hex1(b2));
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.text.SimpleDateFormat] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    public static String date(Date x) {
        Object r0 = dateFormat;
        synchronized (r0) {
            r0 = dateFormat.format(x);
        }
        return (String) r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.text.SimpleDateFormat] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    public static String date(long x) {
        Object r0 = dateFormat;
        synchronized (r0) {
            r0 = dateFormat.format(Long.valueOf(x));
        }
        return (String) r0;
    }

    public static String describe(Object x) {
        SB sb = new SB();
        describe(x, sb, 0);
        return sb.toString();
    }

    public static void describe(Object x, SB sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.a("  ");
        }
        if (x == null) {
            sb.a("<null>");
        } else if (x instanceof String) {
            sb.a("String=");
            toShortString((String) x, sb);
        } else if (x instanceof byte[]) {
            describeByteArray((byte[]) x, sb, indent + 1);
        } else if (x.getClass().isArray()) {
            describeArray(x, sb, indent + 1);
        } else if (x instanceof Collection) {
            describeCollection((Collection) x, sb, indent + 1);
        } else if (x instanceof Map) {
            describeMap((Map) x, sb, indent + 1);
        } else if (isPrimitive(x.getClass())) {
            sb.a(simpleName(x)).a("=").a(x);
        } else if (x instanceof Enum) {
            sb.a(x);
        } else {
            describeObject(x, sb, indent + 1);
        }
    }

    private static boolean isStatic(Field f) {
        return Modifier.isStatic(f.getModifiers());
    }

    private static void describeObject(Object x, SB sb, int indent) {
        Object v;
        if (indent > 2) {
            return;
        }
        Class c = x.getClass();
        sb.nl();
        sb.sp(indent);
        sb.append(c.getSimpleName());
        sb.nl();
        try {
            CMultiMap<String, Object> m = new CMultiMap<>();
            while (c != null) {
                Field[] fs = c.getDeclaredFields();
                for (Field f : fs) {
                    if (!isStatic(f)) {
                        try {
                            f.setAccessible(true);
                            v = f.get(x);
                        } catch (Exception e) {
                            v = "<ERR> unable to describe";
                        }
                        m.put(f.getName(), v);
                    }
                }
                c = c.getSuperclass();
                if (c == Object.class) {
                    c = null;
                }
            }
            CList<String> names = new CList<>(m.keySet());
            CSorter.sort(names);
            Iterator<?> it = names.iterator();
            while (it.hasNext()) {
                String fname = (String) it.next();
                sb.sp(indent);
                sb.sp();
                sb.append(fname);
                sb.append(": ");
                List<Object> vals = m.get(fname);
                if (vals.size() == 1) {
                    describe(vals.get(0), sb, indent + 1);
                } else {
                    for (Object v2 : vals) {
                        describe(v2, sb, indent + 1);
                        sb.nl();
                    }
                }
            }
            sb.nl();
        } catch (Exception e2) {
            sb.sp(indent);
            sb.sp();
            sb.append("<ERR> unable to describe");
        }
    }

    public static boolean isPrimitive(Class c) {
        return c.isPrimitive() || c == Boolean.class || c == Character.class || c == Byte.class || c == Short.class || c == Integer.class || c == Long.class || c == Float.class || c == Double.class || c == BigDecimal.class || c == BigInteger.class;
    }

    public static void toShortString(String s, SB sb) {
        toShortString(s, sb, 80);
    }

    public static void dumpString(String s, SB sb) {
        toShortString(s, sb, Integer.MAX_VALUE);
    }

    public static void toShortString(String s, SB sb, int max) {
        String s2 = s.replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t");
        sb.a('\"');
        int len = s2.length();
        if (len < max) {
            sb.a(s2);
        } else {
            sb.a(s2.substring(0, len - 1)).a("…");
        }
        sb.a('\"');
    }

    private static void describeMap(Map x, SB sb, int indent) {
        sb.a(CKit.getSimpleName(x)).a("(").a(Integer.valueOf(x.size())).a(")").nl();
        for (Object k : x.keySet()) {
            sb.sp(indent);
            sb.a(k).a(": ");
            Object v = x.get(k);
            describe(v, sb, indent + 1);
            sb.nl();
        }
    }

    private static void describeCollection(Collection x, SB sb, int indent) {
        Object[] array;
        sb.a(CKit.getSimpleName(x)).a("(").a(Integer.valueOf(x.size())).a(")").nl();
        for (Object item : x.toArray()) {
            describe(item, sb, indent + 1);
            sb.nl();
        }
    }

    private static void describeArray(Object x, SB sb, int indent) {
        int sz = Array.getLength(x);
        sb.a(CKit.getSimpleName(x.getClass().getComponentType())).a("[").a(Integer.valueOf(sz)).a("]\n");
        for (int i = 0; i < sz; i++) {
            describe(Array.get(x, i), sb, indent + 1);
            sb.nl();
        }
    }

    private static void describeByteArray(byte[] bytes, SB sb, int indent) {
        sb.a('\n');
        hex(sb, bytes, 0L, indent);
    }

    public static String className(Object x) {
        if (x == null) {
            return "<null>";
        }
        return x.getClass().getName();
    }

    public static String simpleName(Object x) {
        if (x == null) {
            return "<null>";
        }
        if (x instanceof Class) {
            return String.valueOf(((Class) x).getSimpleName()) + ".class";
        }
        Class c = x.getClass();
        return c.getSimpleName();
    }

    public static String shorter(Object x) {
        if (x == null) {
            return "<null>";
        }
        String s = x.toString();
        if (s.length() > 6) {
            return s.substring(0, 6);
        }
        return s;
    }

    public static String toPrintable(Object x) {
        if (x == null) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        String s = x.toString();
        int len = s.length();
        SB sb = new SB(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case 11:
                default:
                    if (c < ' ') {
                        sb.append("\\x");
                        sb.append(Hex.toHexByte(c));
                        break;
                    } else {
                        sb.append(c);
                        break;
                    }
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
            }
        }
        return sb.toString();
    }
}
