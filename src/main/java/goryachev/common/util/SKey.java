package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SKey.class */
public class SKey implements Cloneable, Comparable<SKey> {
    private String key;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SKey$Getter.class */
    public interface Getter {
        SKey getSKey();
    }

    public SKey(String key) {
        this.key = key;
    }

    public static SKey format(String format, Object... args) {
        String s = String.format(format, args);
        return new SKey(s);
    }

    public String toString() {
        return this.key;
    }

    public static SKey parse(Object x) throws Exception {
        if (x instanceof SKey) {
            return (SKey) x;
        }
        if (x instanceof String) {
            return new SKey((String) x);
        }
        throw new Exception();
    }

    public static SKey parseQuiet(Object x) {
        try {
            return parse(x);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof SKey) {
            return this.key.equals(((SKey) x).key);
        }
        return false;
    }

    public int hashCode() {
        return FH.hash(SKey.class.hashCode(), this.key);
    }

    public boolean startsWith(String prefix) {
        return this.key.startsWith(prefix);
    }

    public boolean endsWith(String suffix) {
        return this.key.endsWith(suffix);
    }

    @Override // java.lang.Comparable
    public int compareTo(SKey x) {
        return this.key.compareTo(x.key);
    }
}
