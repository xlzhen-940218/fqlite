package goryachev.common.log;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/IAppender.class */
public interface IAppender {
    void append(LogLevel logLevel, long j, StackTraceElement stackTraceElement, Throwable th, String str);

    int getThreshold();

    boolean needsCaller();
}
