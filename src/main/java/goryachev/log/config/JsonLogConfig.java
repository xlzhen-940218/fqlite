package goryachev.log.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import goryachev.common.log.ILogConfig;
import goryachev.common.log.Log;
import goryachev.common.log.LogUtil;
import goryachev.common.util.CKit;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/log/config/JsonLogConfig.class */
public class JsonLogConfig {
    private static Gson gson;
    private static FileMonitor monitor;

    public static void configure(String spec) {
        try {
            ILogConfig cf = parseLogConfig(spec);
            Log.setConfig(cf);
        } catch (Throwable e) {
            LogUtil.internalError(e);
        }
    }

    public static synchronized void configure(File file, long pollingPeriod) {
        if (monitor != null) {
            monitor.cancel();
            monitor = null;
        }
        configure(file, false);
        if (pollingPeriod > 0) {
            monitor = new FileMonitor(file, pollingPeriod, f -> {
                configure(f, true);
            });
            monitor.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void configure(File file, boolean stderr) {
        try {
            String spec = CKit.readString(file);
            configure(spec);
            if (stderr) {
                System.err.println("Log config reloaded: " + file);
            }
        } catch (FileNotFoundException e) {
            if (stderr) {
                System.err.println("Log config not found: " + file);
            }
        } catch (Throwable e2) {
            if (stderr) {
                System.err.print("Failed to reload log config: " + file);
                e2.printStackTrace();
                return;
            }
            LogUtil.internalError(e2);
        }
    }

    private static LogConfig parseLogConfig(String spec) throws Exception {
        return  gson().fromJson(spec, LogConfig.class);
    }

    private static Gson gson() {
        if (gson == null) {
            gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
        }
        return gson;
    }
}
