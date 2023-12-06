package goryachev.common.log;

import goryachev.common.log.internal.LogEventFormatter;
import goryachev.common.util.CList;
import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/AppenderBase.class */
public abstract class AppenderBase implements IAppender {
    private int threshold;
    private ILogEventFormatter formatter;
    private final CList<String> channels;

    public abstract void emit(String str);

    public AppenderBase(LogLevel threshold) {
        this.formatter = LogEventFormatter.simpleFormatter();
        this.channels = new CList<>();
        this.threshold = threshold.ordinal();
    }

    public AppenderBase() {
        this(LogLevel.ALL);
    }

    public void setFormatter(ILogEventFormatter f) {
        this.formatter = f;
    }

    @Override // goryachev.common.log.IAppender
    public int getThreshold() {
        return this.threshold;
    }

    @Override // goryachev.common.log.IAppender
    public boolean needsCaller() {
        return this.formatter.needsCaller();
    }

    public List<String> getChannels() {
        return this.channels;
    }

    @Override // goryachev.common.log.IAppender
    public void append(LogLevel level, long time, StackTraceElement caller, Throwable err, String msg) {
        if (level.ordinal() >= this.threshold) {
            String s = this.formatter.format(level, time, caller, err, msg);
            emit(s);
        }
    }
}
