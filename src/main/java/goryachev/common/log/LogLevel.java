package goryachev.common.log;

import goryachev.common.util.Keep;

@Keep
/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/LogLevel.class */
public enum LogLevel {
    ALL,
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL,
    OFF;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static LogLevel[] valuesCustom() {
        LogLevel[] valuesCustom = values();
        int length = valuesCustom.length;
        LogLevel[] logLevelArr = new LogLevel[length];
        System.arraycopy(valuesCustom, 0, logLevelArr, 0, length);
        return logLevelArr;
    }
}
