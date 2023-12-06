package goryachev.common.log;

import goryachev.common.log.internal.FormatField;
import goryachev.common.util.CKit;
import goryachev.common.util.CMap;
import goryachev.common.util.CSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/LogUtil.class */
public class LogUtil {
    private static Pattern LEVELS = Pattern.compile("(OFF)|(FATAL)|(ERROR)|(WARN)|(INFO)|(DEBUG)|(TRACE)|(ALL)", 2);

    public static ILogConfig createDisabledLogConfig() {
        return new ILogConfig() { // from class: goryachev.common.log.LogUtil.1
            @Override // goryachev.common.log.ILogConfig
            public boolean isVerbose() {
                return true;
            }

            @Override // goryachev.common.log.ILogConfig
            public LogLevel getLogLevel(String name) {
                return LogLevel.OFF;
            }

            @Override // goryachev.common.log.ILogConfig
            public LogLevel getDefaultLogLevel() {
                return LogLevel.OFF;
            }

            @Override // goryachev.common.log.ILogConfig
            public List<IAppender> getAppenders() {
                return null;
            }
        };
    }

    public static LogLevel parseLevel(String text) {
        if (CKit.isNotBlank(text)) {
            Matcher m = LEVELS.matcher(text.trim());
            if (m.matches()) {
                if (m.group(1) != null) {
                    return LogLevel.OFF;
                }
                if (m.group(2) != null) {
                    return LogLevel.FATAL;
                }
                if (m.group(3) != null) {
                    return LogLevel.ERROR;
                }
                if (m.group(4) != null) {
                    return LogLevel.WARN;
                }
                if (m.group(5) != null) {
                    return LogLevel.INFO;
                }
                if (m.group(6) != null) {
                    return LogLevel.DEBUG;
                }
                if (m.group(7) != null) {
                    return LogLevel.TRACE;
                }
                if (m.group(8) != null) {
                    return LogLevel.ALL;
                }
            }
        }
        return LogLevel.OFF;
    }

    public static void process(CMap<String, LogLevel> m, String[] names, LogLevel lv) {
        if (names != null) {
            for (String name : names) {
                m.put(name, lv);
            }
        }
    }

    public static void process(CMap<String, LogLevel> m, CMap<String, String> channels) {
        if (channels != null) {
            for (String name : channels.keySet()) {
                String v = channels.get(name);
                LogLevel lv = parseLevel(v);
                m.put(name, lv);
            }
        }
    }

    public static boolean checkNeedsCaller(Iterable<AppenderBase> appenders) {
        for (AppenderBase a : appenders) {
            if (a.needsCaller()) {
                return true;
            }
        }
        return false;
    }

    public static boolean needsCaller(FormatField[] fields) {
        for (FormatField f : fields) {
            if (f.needsCaller()) {
                return true;
            }
        }
        return false;
    }

    public static CSet<String> initIgnoreClassNames() {
        CSet<String> s = new CSet<>();
        s.add(Log.class.getName());
        return s;
    }

    public static void internalError(Throwable e) {
        if (Log.showInternalErrors) {
            e.printStackTrace();
        }
    }
}
