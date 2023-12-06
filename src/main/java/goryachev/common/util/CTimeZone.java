package goryachev.common.util;

import java.util.Locale;
import java.util.TimeZone;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CTimeZone.class */
public class CTimeZone implements HasProperty, HasDisplayName {
    public final TimeZone tz;

    public CTimeZone(TimeZone tz) {
        this.tz = tz;
    }

    public TimeZone getTimeZone() {
        return this.tz;
    }

    @Override // goryachev.common.util.HasDisplayName
    public String getDisplayName() {
        return this.tz.getDisplayName(Locale.getDefault());
    }

    @Override // goryachev.common.util.HasProperty
    public String getProperty() {
        return this.tz.getID();
    }

    public String toString() {
        return String.valueOf(getDisplayName()) + " - " + getID();
    }

    public String getID() {
        return this.tz.getID();
    }

    public static String getID(CTimeZone tz) {
        if (tz == null) {
            return null;
        }
        return tz.getID();
    }

    public int getOffset(long t) {
        return this.tz.getOffset(t);
    }

    public static String toStringCode(CTimeZone z) {
        if (z != null) {
            return z.getID();
        }
        return null;
    }

    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (x instanceof CTimeZone) {
            return this.tz.equals(((CTimeZone) x).tz);
        }
        return false;
    }

    public int hashCode() {
        return CTimeZone.class.hashCode() ^ this.tz.hashCode();
    }

    public static CTimeZone getDefault() {
        return new CTimeZone(TimeZone.getDefault());
    }

    public static CTimeZone getTimeZone(String s) {
        if (s != null) {
            return new CTimeZone(TimeZone.getTimeZone(s));
        }
        return null;
    }

    public static CTimeZone[] getAll() {
        String[] ids = TimeZone.getAvailableIDs();
        int sz = ids.length;
        CTimeZone[] a = new CTimeZone[sz];
        for (int i = 0; i < sz; i++) {
            String id = ids[i];
            a[i] = new CTimeZone(TimeZone.getTimeZone(id));
        }
        return a;
    }

    public static CTimeZone[] getAllSorted() {
        CTimeZone[] tz = getAll();
        CSorter.sort(tz);
        return tz;
    }

    public static CTimeZone parse(Object x) {
        if (x != null) {
            if (x instanceof CTimeZone) {
                return (CTimeZone) x;
            }
            String s = Parsers.parseString(x);
            if (CKit.isNotBlank(s)) {
                TimeZone tz = TimeZone.getTimeZone(s);
                return new CTimeZone(tz);
            }
            return null;
        }
        return null;
    }

    public String getCityName() {
        String s = getID().replace('_', ' ');
        int ix = s.indexOf(47);
        if (ix < 0) {
            return s;
        }
        return s.substring(ix + 1);
    }
}
