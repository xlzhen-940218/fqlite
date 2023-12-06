package goryachev.common.log;

import fqlite.base.Global;
import goryachev.common.log.internal.ConsoleAppender;
import goryachev.common.util.CKit;
import goryachev.common.util.CMap;
import goryachev.common.util.CSet;
import goryachev.common.util.SB;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/Log.class */
public class Log {
    private static boolean needCaller;
    protected static boolean showInternalErrors;
    private final String fullName;
    private final String name;
    private Log parent;
    private LogLevel level;
    private int effectiveLevel;
    private final CMap<String, Log> children = new CMap<>(0);
    private static final int ALL = LogLevel.ALL.ordinal();
    private static final int TRACE = LogLevel.TRACE.ordinal();
    private static final int DEBUG = LogLevel.DEBUG.ordinal();
    private static final int INFO = LogLevel.INFO.ordinal();
    private static final int WARN = LogLevel.WARN.ordinal();
    private static final int ERROR = LogLevel.ERROR.ordinal();
    private static final int FATAL = LogLevel.FATAL.ordinal();
    private static final int OFF = LogLevel.OFF.ordinal();
    private static final CopyOnWriteArrayList<IAppender> appenders = new CopyOnWriteArrayList<>();
    private static int appendersThreshold = OFF;
    protected static final CSet<String> ignore = LogUtil.initIgnoreClassNames();
    protected static final Log root = new Log(null, null, null);

    protected Log(Log parent, String name, String fullName) {
        this.parent = parent;
        this.fullName = fullName;
        this.name = name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Class<goryachev.common.log.Log>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static Log get(String fullName) {
        String[] ss = CKit.split((CharSequence) fullName, '.');
        Log log = root;
        Object r0 = Log.class;
        synchronized (r0) {
            for (String s : ss) {
                Log ch = log.children.get(s);
                if (ch == null) {
                    ch = new Log(log, s, fullName);
                    log.children.put(s, ch);
                    ch.updateEffectiveLevelsRecursively(null);
                }
                log = ch;
            }
            r0 = r0;
            return log;
        }
    }

    public static void reset() {
        setConfig(LogUtil.createDisabledLogConfig());
    }

    public static void initConsoleForDebug() {
        initConsole(LogLevel.DEBUG);
    }

    public static void initConsole(LogLevel level) {
        SimpleLogConfig c = new SimpleLogConfig();
        c.setDefaultLogLevel(level);
        c.addAppender(new ConsoleAppender(level, System.out));
        setConfig(c);
    }

    public static void setShowInternalErrors(boolean on) {
        showInternalErrors = on;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Class<goryachev.common.log.Log>] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static void setConfig(ILogConfig cf) {
        if (cf == null) {
            cf = LogUtil.createDisabledLogConfig();
        }
        showInternalErrors = cf.isVerbose();
        try {
            cf.getAppenders();
            Object r0 = Log.class;
            synchronized (r0) {
                updateAll(cf);
                r0 = r0;
            }
        } catch (Throwable e) {
            LogUtil.internalError(e);
        }
    }

    protected static void updateAll(ILogConfig cf) {
        if (cf != null) {
            appenders.clear();
            try {
                List<IAppender> as = cf.getAppenders();
                if (as != null) {
                    for (IAppender a : as) {
                        appenders.add(a);
                    }
                }
            } catch (Exception e) {
                LogUtil.internalError(e);
            }
        }
        int th = OFF;
        boolean caller = false;
        Iterator<IAppender> it = appenders.iterator();
        while (it.hasNext()) {
            IAppender a2 = it.next();
            if (a2.getThreshold() < th) {
                th = a2.getThreshold();
            }
            caller |= a2.needsCaller();
        }
        appendersThreshold = th;
        needCaller = caller;
        root.updateEffectiveLevelsRecursively(cf);
    }

    public static synchronized void addAppender(IAppender a) {
        appenders.add(a);
        updateAll(null);
    }

    public static synchronized void removeAppender(IAppender a) {
        if (appenders.remove(a)) {
            updateAll(null);
        }
    }

    public static void addSecret(String secret) {
    }

    public static void addSecrets(Iterable<String> secrets) {
    }

    public static synchronized void setLevel(String channel, LogLevel level) {
        Log log = get(channel);
        log.setLevel(level);
    }

    public static Log getRoot() {
        return root;
    }

    public String getName() {
        return this.name;
    }

    protected void updateEffectiveLevelsRecursively(ILogConfig cf) {
        if (cf == null) {
            if (this.level == null) {
                if (this.parent == null) {
                    this.effectiveLevel = OFF;
                } else {
                    this.effectiveLevel = this.parent.effectiveLevel;
                }
            } else {
                int eff = this.level.ordinal();
                if (eff == this.effectiveLevel) {
                    return;
                }
                this.effectiveLevel = eff;
            }
        } else {
            this.level = cf.getLogLevel(this.fullName);
            if (this.level == null) {
                if (this.parent == null) {
                    this.effectiveLevel = cf.getDefaultLogLevel().ordinal();
                } else {
                    this.effectiveLevel = this.parent.effectiveLevel;
                }
            } else {
                this.effectiveLevel = this.level.ordinal();
            }
        }
        for (Log ch : this.children.values()) {
            ch.updateEffectiveLevelsRecursively(cf);
        }
    }

    protected Log getParent() {
        return this.parent;
    }

    protected void setLevel(LogLevel level) {
        this.level = level;
        updateEffectiveLevelsRecursively(null);
    }

    protected StackTraceElement extractCaller(Throwable err) {
        StackTraceElement[] stack = err.getStackTrace();
        for (StackTraceElement em : stack) {
            String name = em.getClassName();
            if (!ignore.contains(name)) {
                return em;
            }
        }
        throw new Error();
    }

    protected void logEvent(LogLevel level, Throwable err, Object message) {
        StackTraceElement caller;
        long time = System.currentTimeMillis();
        if (needCaller) {
            caller = extractCaller(err == null ? new Throwable() : err);
        } else {
            caller = null;
        }
        processEvent(level, time, caller, err, message);
    }

    protected void processEvent(LogLevel lv, long time, StackTraceElement caller, Throwable err, Object message) {
        String msg = message == null ? ButtonBar.BUTTON_ORDER_NONE : message.toString();
        processEvent(lv, time, caller, err, msg);
    }

    protected void processEvent(LogLevel lv, long time, StackTraceElement caller, Throwable err, String msg) {
        Iterator<IAppender> it = appenders.iterator();
        while (it.hasNext()) {
            IAppender a = it.next();
            a.append(lv, time, caller, err, msg);
        }
    }

    public static void p(Object... items) {
        SB sb = new SB();
        StackTraceElement t = new Throwable().getStackTrace()[1];
        String s = t.getClassName();
        int ix = s.lastIndexOf(46);
        if (ix >= 0) {
            sb.append(s.substring(ix + 1, s.length()));
        } else {
            sb.append(s);
        }
        sb.append('.');
        sb.append(t.getMethodName());
        sb.sp();
        for (Object x : items) {
            if (sb.length() > 0) {
                sb.append(Global.REGULAR_RECORD);
            }
            sb.append(x);
        }
        System.err.println(sb);
    }

    protected static String format(String fmt, Object[] args) {
        try {
            return String.format(fmt, args);
        } catch (Throwable e) {
            return "Error in logging statement [" + fmt + "]: " + CKit.stackTrace(e) + "]";
        }
    }

    public void error(Throwable err) {
        if (ERROR >= this.effectiveLevel) {
            logEvent(LogLevel.ERROR, err, null);
        }
    }

    public void error(Object message, Throwable err) {
        if (ERROR >= this.effectiveLevel) {
            logEvent(LogLevel.ERROR, err, message);
        }
    }

    public void error() {
        if (ERROR >= this.effectiveLevel) {
            logEvent(LogLevel.ERROR, null, ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    public void error(Object message) {
        if (ERROR >= this.effectiveLevel) {
            logEvent(LogLevel.ERROR, null, message);
        }
    }

    public void error(String format, Object... args) {
        if (ERROR >= this.effectiveLevel) {
            String msg = format(format, args);
            logEvent(LogLevel.ERROR, null, msg);
        }
    }

    public void error(Supplier<Object> lambda) {
        if (ERROR >= this.effectiveLevel) {
            Object msg = lambda.get();
            logEvent(LogLevel.ERROR, null, msg);
        }
    }

    public void warn(Throwable err) {
        if (WARN >= this.effectiveLevel) {
            logEvent(LogLevel.WARN, err, null);
        }
    }

    public void warn(Object message, Throwable err) {
        if (WARN >= this.effectiveLevel) {
            logEvent(LogLevel.WARN, err, message);
        }
    }

    public void warn() {
        if (WARN >= this.effectiveLevel) {
            logEvent(LogLevel.WARN, null, ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    public void warn(Object message) {
        if (WARN >= this.effectiveLevel) {
            logEvent(LogLevel.WARN, null, message);
        }
    }

    public void warn(String format, Object... args) {
        if (WARN >= this.effectiveLevel) {
            String msg = format(format, args);
            logEvent(LogLevel.WARN, null, msg);
        }
    }

    public void warn(Supplier<Object> lambda) {
        if (WARN >= this.effectiveLevel) {
            Object msg = lambda.get();
            logEvent(LogLevel.WARN, null, msg);
        }
    }

    public void info(Throwable err) {
        if (INFO >= this.effectiveLevel) {
            logEvent(LogLevel.INFO, err, null);
        }
    }

    public void info(Object message, Throwable err) {
        if (INFO >= this.effectiveLevel) {
            logEvent(LogLevel.INFO, err, message);
        }
    }

    public void info() {
        if (INFO >= this.effectiveLevel) {
            logEvent(LogLevel.INFO, null, ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    public void info(Object message) {
        if (INFO >= this.effectiveLevel) {
            logEvent(LogLevel.INFO, null, message);
        }
    }

    public void info(String format, Object... args) {
        if (INFO >= this.effectiveLevel) {
            String msg = format(format, args);
            logEvent(LogLevel.INFO, null, msg);
        }
    }

    public void info(Supplier<Object> lambda) {
        if (INFO >= this.effectiveLevel) {
            Object msg = lambda.get();
            logEvent(LogLevel.INFO, null, msg);
        }
    }

    public void debug(Throwable err) {
        if (DEBUG >= this.effectiveLevel) {
            logEvent(LogLevel.DEBUG, err, null);
        }
    }

    public void debug(Object message, Throwable err) {
        if (DEBUG >= this.effectiveLevel) {
            logEvent(LogLevel.DEBUG, err, message);
        }
    }

    public void debug() {
        if (DEBUG >= this.effectiveLevel) {
            logEvent(LogLevel.DEBUG, null, ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    public void debug(Object message) {
        if (DEBUG >= this.effectiveLevel) {
            logEvent(LogLevel.DEBUG, null, message);
        }
    }

    public void debug(String format, Object... args) {
        if (DEBUG >= this.effectiveLevel) {
            String msg = format(format, args);
            logEvent(LogLevel.DEBUG, null, msg);
        }
    }

    public void debug(Supplier<Object> lambda) {
        if (DEBUG >= this.effectiveLevel) {
            Object msg = lambda.get();
            logEvent(LogLevel.DEBUG, null, msg);
        }
    }

    public void trace() {
        if (TRACE >= this.effectiveLevel) {
            logEvent(LogLevel.TRACE, null, ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    public void trace(Throwable err) {
        if (TRACE >= this.effectiveLevel) {
            logEvent(LogLevel.TRACE, err, null);
        }
    }

    public void trace(Object message, Throwable err) {
        if (TRACE >= this.effectiveLevel) {
            logEvent(LogLevel.TRACE, err, message);
        }
    }

    public void trace(Object message) {
        if (TRACE >= this.effectiveLevel) {
            logEvent(LogLevel.TRACE, null, message);
        }
    }

    public void trace(String format, Object... args) {
        if (TRACE >= this.effectiveLevel) {
            String msg = format(format, args);
            logEvent(LogLevel.TRACE, null, msg);
        }
    }

    public void trace(Supplier<Object> lambda) {
        if (TRACE >= this.effectiveLevel) {
            Object msg = lambda.get();
            logEvent(LogLevel.TRACE, null, msg);
        }
    }
}
