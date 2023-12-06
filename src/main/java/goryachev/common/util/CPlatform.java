package goryachev.common.util;

import goryachev.common.log.Log;
import goryachev.common.util.platform.CPlatformLinux;
import goryachev.common.util.platform.CPlatformMac;
import goryachev.common.util.platform.CPlatformUnix;
import goryachev.common.util.platform.CPlatformWindows;
import java.io.File;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CPlatform.class */
public abstract class CPlatform {
    protected static final Log log = Log.get("CPlatform");
    protected static final String SETTINGS_FOLDER = "goryachev.com";
    private static CPlatform instance;

    protected abstract File getSettingsFolderPrivate();

    public static CPlatform get() {
        if (instance == null) {
            instance = detect();
        }
        return instance;
    }

    public static boolean isWindows() {
        return get() instanceof CPlatformWindows;
    }

    public static boolean isMac() {
        return get() instanceof CPlatformMac;
    }

    public static boolean isLinux() {
        return get() instanceof CPlatformLinux;
    }

    public static boolean isUnix() {
        return get() instanceof CPlatformUnix;
    }

    public static String getName() {
        return System.getProperty("os.name");
    }

    public static String getVersion() {
        return System.getProperty("os.version");
    }

    public static File getUserHome() {
        return new File(System.getProperty("user.home"));
    }

    public static File getSettingsFolder() {
        return get().getSettingsFolderPrivate();
    }

    @Deprecated
    public File getDefaultSettingsFolder() {
        return new File(getUserHome(), ".goryachev.com");
    }

    private static CPlatform detect() {
        String os = null;
        try {
            os = System.getProperty("os.name");
        } catch (Exception e) {
            log.error((Throwable) e);
        }
        if (os.startsWith("Windows")) {
            return new CPlatformWindows();
        }
        if (os.startsWith("Mac OS")) {
            return new CPlatformMac();
        }
        if (os.startsWith("Linux")) {
            return new CPlatformLinux();
        }
        return new CPlatformUnix();
    }
}
