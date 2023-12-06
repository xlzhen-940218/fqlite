package goryachev.common.util;

import goryachev.common.log.Log;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.SortedMap;
import org.apache.commons.codec.CharEncoding;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CEncoding.class */
public class CEncoding implements HasDisplayName, HasProperty {
    protected static final Log log = Log.get("CEncoding");
    public static final CEncoding UTF8 = new CEncoding(Charset.forName("UTF-8"));
    public static final CEncoding UTF16 = new CEncoding(Charset.forName(CharEncoding.UTF_16));
    private Charset charset;
    private static CEncoding[] encodings;

    public CEncoding(Charset cs) {
        this.charset = cs;
    }

    public Charset getCharset() {
        return this.charset;
    }

    @Override // goryachev.common.util.HasDisplayName
    public String getDisplayName() {
        return this.charset.displayName(Locale.getDefault());
    }

    public String toString() {
        return getDisplayName();
    }

    public String getID() {
        return this.charset.name();
    }

    @Override // goryachev.common.util.HasProperty
    public String getProperty() {
        return getID();
    }

    public static CEncoding[] list() {
        if (encodings == null) {
            SortedMap<String, Charset> m = Charset.availableCharsets();
            CEncoding[] a = new CEncoding[m.size()];
            int ix = 0;
            for (Charset cs : m.values()) {
                int i = ix;
                ix++;
                a[i] = new CEncoding(cs);
            }
            encodings = a;
        }
        return encodings;
    }

    public static CEncoding parse(Object x) {
        if (x instanceof CEncoding) {
            return (CEncoding) x;
        }
        if (x instanceof String) {
            try {
                Charset cs = Charset.forName((String) x);
                return new CEncoding(cs);
            } catch (Exception e) {
                log.error((Throwable) e);
                return null;
            }
        }
        return null;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof CEncoding) {
            return this.charset.equals(((CEncoding) x).charset);
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.charset.hashCode();
    }
}
