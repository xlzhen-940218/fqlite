package goryachev.common.log.internal;

import goryachev.common.log.LogLevel;
import goryachev.common.util.CKit;
import goryachev.common.util.SB;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/internal/FormatField.class */
public abstract class FormatField {
    public abstract void format(SB sb, LogLevel logLevel, long j, StackTraceElement stackTraceElement, Throwable th, String str);

    public boolean needsCaller() {
        return false;
    }

    public static FormatField className() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.1
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                if (caller == null) {
                    sb.append("NO_CALLER_ERRROR");
                    return;
                }
                String name = caller.getClassName();
                int ix = name.lastIndexOf(46);
                if (ix >= 0) {
                    name = name.substring(ix + 1);
                }
                sb.append(name);
            }

            @Override // goryachev.common.log.internal.FormatField
            public boolean needsCaller() {
                return true;
            }
        };
    }

    public static FormatField date(String spec) {
        final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(spec);
        final ZoneId tz = ZoneId.systemDefault();
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.2
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                LocalDateTime t = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), tz);
                fmt.formatTo(t, sb);
            }
        };
    }

    public static FormatField error() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.3
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                if (err != null) {
                    sb.append(CKit.stackTrace(err));
                }
            }
        };
    }

    public static FormatField level() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.4
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                sb.append(level);
            }
        };
    }

    public static FormatField line() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.5
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                if (caller == null) {
                    sb.append("NO_CALLER_ERRROR");
                    return;
                }
                int line = caller.getLineNumber();
                sb.append(line);
            }

            @Override // goryachev.common.log.internal.FormatField
            public boolean needsCaller() {
                return true;
            }
        };
    }

    public static FormatField method() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.6
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                if (caller == null) {
                    sb.append("NO_CALLER_ERRROR");
                    return;
                }
                String method = caller.getMethodName();
                sb.append(method);
            }

            @Override // goryachev.common.log.internal.FormatField
            public boolean needsCaller() {
                return true;
            }
        };
    }

    public static FormatField message() {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.7
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                sb.append(msg);
            }
        };
    }

    public static FormatField text(final String text) {
        return new FormatField() { // from class: goryachev.common.log.internal.FormatField.8
            @Override // goryachev.common.log.internal.FormatField
            public void format(SB sb, LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
                sb.append(text);
            }
        };
    }
}
