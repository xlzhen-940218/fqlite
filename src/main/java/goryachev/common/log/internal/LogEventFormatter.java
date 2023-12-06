package goryachev.common.log.internal;

import fqlite.base.Global;
import goryachev.common.log.ILogEventFormatter;
import goryachev.common.log.LogLevel;
import goryachev.common.log.LogUtil;
import goryachev.common.util.SB;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/internal/LogEventFormatter.class */
public class LogEventFormatter implements ILogEventFormatter {
    private final FormatField[] fields;
    private final boolean needsCaller;

    public LogEventFormatter(FormatField[] fields) {
        this.fields = fields;
        this.needsCaller = LogUtil.needsCaller(fields);
    }

    @Override // goryachev.common.log.ILogEventFormatter
    public String format(LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
        SB sb = new SB(128);
        for (int i = 0; i < this.fields.length; i++) {
            FormatField ff = this.fields[i];
            ff.format(sb, level, time, caller, err, msg);
        }
        return sb.toString();
    }

    @Override // goryachev.common.log.ILogEventFormatter
    public boolean needsCaller() {
        return this.needsCaller;
    }

    public static LogEventFormatter simpleFormatter() {
        return new LogEventFormatter(new FormatField[]{FormatField.date("MM/dd HH:mm:ss.SSS"), FormatField.text(Global.REGULAR_RECORD), FormatField.level(), FormatField.text(Global.REGULAR_RECORD), FormatField.className(), FormatField.text("."), FormatField.method(), FormatField.text(":"), FormatField.line(), FormatField.text(Global.REGULAR_RECORD), FormatField.error(), FormatField.text(Global.REGULAR_RECORD), FormatField.message()});
    }
}
