package goryachev.common.log;

import goryachev.common.log.internal.ConsoleAppender;
import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/SimpleLogConfig.class */
public class SimpleLogConfig implements ILogConfig {
    private boolean verbose;
    private LogLevel defaultLogLevel = LogLevel.OFF;
    private final CMap<String, LogLevel> channels = new CMap<>();
    private final CList<IAppender> appenders = new CList<>();

    public void verbose() {
        this.verbose = true;
    }

    @Override // goryachev.common.log.ILogConfig
    public boolean isVerbose() {
        return this.verbose;
    }

    public void all(String name) {
        setLevel(name, LogLevel.ALL);
    }

    public void trace(String name) {
        setLevel(name, LogLevel.TRACE);
    }

    public void debug(String name) {
        setLevel(name, LogLevel.DEBUG);
    }

    public void info(String name) {
        setLevel(name, LogLevel.INFO);
    }

    public void warn(String name) {
        setLevel(name, LogLevel.WARN);
    }

    public void error(String name) {
        setLevel(name, LogLevel.ERROR);
    }

    public void fatal(String name) {
        setLevel(name, LogLevel.FATAL);
    }

    public void off(String name) {
        setLevel(name, LogLevel.OFF);
    }

    public void setLevel(String name, LogLevel lv) {
        this.channels.put(name, lv);
    }

    @Override // goryachev.common.log.ILogConfig
    public LogLevel getLogLevel(String name) {
        LogLevel lv = this.channels.get(name);
        if (lv == null) {
            return this.defaultLogLevel;
        }
        return lv;
    }

    public void setDefaultLogLevel(LogLevel lv) {
        this.defaultLogLevel = lv;
    }

    @Override // goryachev.common.log.ILogConfig
    public LogLevel getDefaultLogLevel() {
        return this.defaultLogLevel;
    }

    public void addConsole() {
        ConsoleAppender a = new ConsoleAppender(System.out);
        addAppender(a);
    }

    public void addAppender(AppenderBase a) {
        this.appenders.add(a);
    }

    @Override // goryachev.common.log.ILogConfig
    public List<IAppender> getAppenders() throws Exception {
        return this.appenders;
    }
}
