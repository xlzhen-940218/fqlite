package goryachev.common.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CDateFormat.class */
public class CDateFormat implements IFormat {
    private final String pattern;
    private SimpleDateFormat simple;
    private DateTimeFormatter dt;

    public CDateFormat(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    protected SimpleDateFormat simple() {
        if (this.simple == null) {
            this.simple = new SimpleDateFormat(this.pattern);
        }
        return this.simple;
    }

    protected DateTimeFormatter dt() {
        if (this.dt == null) {
            this.dt = DateTimeFormatter.ofPattern(this.pattern);
        }
        return this.dt;
    }

    @Override // goryachev.common.util.IFormat
    public String format(Object x) {
        if (x != null) {
            if (x instanceof Long) {
                return simple().format(x);
            }
            if (x instanceof Date) {
                return simple().format(x);
            }
            if (x instanceof TemporalAccessor) {
                return dt().format((TemporalAccessor) x);
            }
            return null;
        }
        return null;
    }
}
