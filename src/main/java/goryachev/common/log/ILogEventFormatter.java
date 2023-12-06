package goryachev.common.log;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/ILogEventFormatter.class */
public interface ILogEventFormatter {
    String format(LogLevel logLevel, long j, StackTraceElement stackTraceElement, Throwable th, String str);

    boolean needsCaller();
}
