package goryachev.log.config;

import goryachev.common.log.IAppender;
import goryachev.common.log.ILogConfig;
import goryachev.common.log.LogLevel;
import goryachev.common.log.LogUtil;
import goryachev.common.log.internal.ConsoleAppender;
import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import goryachev.common.util.Keep;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

@Keep
/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/log/config/LogConfig.class */
public class LogConfig implements ILogConfig {
    protected static final String STDOUT = "stdout";
    protected static final String STDERR = "stderr";
    public String defaultLevel;
    public boolean verbose;
    public String[] off;
    public String[] fatal;
    public String[] error;
    public String[] debug;
    public String[] info;
    public String[] trace;
    public String[] all;
    public CMap<String, String> channels;
    public CMap<String, Profile> profiles;
    public CList<AppenderInfo> appenders;
    private transient CMap<String, LogLevel> levels;

    @Keep
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/log/config/LogConfig$AppenderInfo.class */
    public static class AppenderInfo {
        public String type;
        public boolean disabled;
        public String pattern;
        public String[] channels;
    }

    @Keep
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/log/config/LogConfig$Profile.class */
    public static class Profile {
        public boolean enabled;
        public String[] off;
        public String[] fatal;
        public String[] error;
        public String[] debug;
        public String[] info;
        public String[] trace;
        public String[] all;
        public CMap<String, String> channels;
    }

    @Override // goryachev.common.log.ILogConfig
    public boolean isVerbose() {
        return this.verbose;
    }

    @Override // goryachev.common.log.ILogConfig
    public LogLevel getLogLevel(String name) {
        LogLevel lv;
        try {
            lv = this.levels.get(name);
        } catch (NullPointerException e) {
            this.levels = initLevels();
            lv = this.levels.get(name);
        }
        return lv;
    }

    @Override // goryachev.common.log.ILogConfig
    public LogLevel getDefaultLogLevel() {
        return LogUtil.parseLevel(this.defaultLevel);
    }

    protected CMap<String, LogLevel> initLevels() {
        CMap<String, LogLevel> m = new CMap<>();
        if (this.profiles != null) {
            for (Profile p : this.profiles.values()) {
                if (p.enabled) {
                    LogUtil.process(m, p.off, LogLevel.OFF);
                    LogUtil.process(m, p.fatal, LogLevel.FATAL);
                    LogUtil.process(m, p.error, LogLevel.ERROR);
                    LogUtil.process(m, p.debug, LogLevel.DEBUG);
                    LogUtil.process(m, p.info, LogLevel.INFO);
                    LogUtil.process(m, p.trace, LogLevel.TRACE);
                    LogUtil.process(m, p.all, LogLevel.ALL);
                    LogUtil.process(m, p.channels);
                }
            }
        }
        LogUtil.process(m, this.off, LogLevel.OFF);
        LogUtil.process(m, this.fatal, LogLevel.FATAL);
        LogUtil.process(m, this.error, LogLevel.ERROR);
        LogUtil.process(m, this.debug, LogLevel.DEBUG);
        LogUtil.process(m, this.info, LogLevel.INFO);
        LogUtil.process(m, this.trace, LogLevel.TRACE);
        LogUtil.process(m, this.all, LogLevel.ALL);
        LogUtil.process(m, this.channels);
        return m;
    }

    @Override // goryachev.common.log.ILogConfig
    public List<IAppender> getAppenders() throws Exception {
        CList<IAppender> rv = new CList<>();
        if (this.appenders != null) {
            Iterator<AppenderInfo> it = this.appenders.iterator();
            while (it.hasNext()) {
                AppenderInfo inf = it.next();
                IAppender a = createAppender(inf);
                rv.add(a);
            }
        }
        return rv;
    }

    protected static IAppender createAppender(AppenderInfo inf) throws Exception {
        if (inf.type == null) {
            throw new Exception("undefined appender type (null)");
        }
        String str = inf.type;
        switch (str.hashCode()) {
            case -892406686:
                if (str.equals(STDERR)) {
                    return createConsoleAppender(inf, System.err);
                }
                break;
            case -892396981:
                if (str.equals(STDOUT)) {
                    return createConsoleAppender(inf, System.out);
                }
                break;
        }
        throw new Exception("unknown appender type: " + inf.type);
    }

    protected static ConsoleAppender createConsoleAppender(AppenderInfo inf, PrintStream out) {
        ConsoleAppender a = new ConsoleAppender(out);
        return a;
    }
}
