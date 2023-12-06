package goryachev.common.util;

import goryachev.common.log.Log;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/JavaVersion.class */
public class JavaVersion implements Comparable<JavaVersion> {
    protected static final Log log = Log.get("JavaVersion");
    private final String version;
    private final int[] ver;

    protected JavaVersion(String version, int[] ver) {
        this.version = version;
        this.ver = ver;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof JavaVersion) {
            JavaVersion v = (JavaVersion) x;
            return this.version.equals(v.version);
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(JavaVersion.class);
        return FH.hash(h, this.version);
    }

    @Override // java.lang.Comparable
    public int compareTo(JavaVersion v) {
        int n = Math.min(this.ver.length, v.ver.length);
        for (int i = 0; i < n; i++) {
            int a = this.ver[i];
            int b = v.ver[i];
            int d = a - b;
            if (d != 0) {
                return d;
            }
        }
        return 0;
    }

    public String toString() {
        return this.version;
    }

    public int[] toIntArray() {
        return (int[]) this.ver.clone();
    }

    public boolean isSameOrLaterThan(JavaVersion x) {
        return compareTo(x) >= 0;
    }

    public boolean isLaterThan(JavaVersion x) {
        return compareTo(x) > 0;
    }

    public static JavaVersion getJavaVersion() {
        return parseSystemProperty("java.version");
    }

    public static JavaVersion getJavaRuntimeVersion() {
        return parseSystemProperty("java.runtime.version");
    }

    public static JavaVersion getJavaSpecificationVersion() {
        return parseSystemProperty("java.specification.version");
    }

    protected static int parseNumber(String s) throws Exception {
        int end = s.length();
        int i = 0;
        while (true) {
            if (i >= end) {
                break;
            }
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                i++;
            } else {
                end = i;
                break;
            }
        }
        if (end < s.length()) {
            s = s.substring(0, end);
        }
        return Integer.parseInt(s);
    }

    protected static JavaVersion parseSystemProperty(String propertyKey) {
        String s = System.getProperty(propertyKey);
        return parse(s);
    }

    public static JavaVersion parse(String s) {
        int[] ver;
        try {
            String[] ss = CKit.split((CharSequence) s, '.');
            ver = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                ver[i] = parseNumber(ss[i]);
            }
        } catch (Exception e) {
            log.error((Throwable) e);
            ver = new int[0];
        }
        return new JavaVersion(s, ver);
    }
}
