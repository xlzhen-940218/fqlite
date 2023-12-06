package goryachev.fx;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDateFormatter.class */
public class FxDateFormatter extends FxFormatter {
    private final SimpleDateFormat format;

    public FxDateFormatter(String pattern) {
        this.format = new SimpleDateFormat(pattern);
    }

    public String format(long t) {
        return this.format.format(Long.valueOf(t));
    }

    @Override // goryachev.fx.FxFormatter, javafx.util.StringConverter
    public String toString(Object x) {
        if (x == null) {
            return null;
        }
        if (x instanceof Date) {
            return this.format.format(x);
        }
        if (x instanceof Long) {
            Long v = (Long) x;
            if (v.longValue() <= 0) {
                return null;
            }
            return this.format.format(x);
        }
        return null;
    }
}
