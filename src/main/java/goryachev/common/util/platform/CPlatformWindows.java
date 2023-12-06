package goryachev.common.util.platform;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.CPlatform;
import java.io.File;
import java.nio.charset.Charset;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/platform/CPlatformWindows.class */
public class CPlatformWindows extends CPlatform {
    private static final String REGQUERY_UTIL = "reg query ";
    private static final String REGSTR_TOKEN = "REG_SZ";
    private static final String DESKTOP_FOLDER_CMD = "reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v DESKTOP";
    protected static final Log log = Log.get("CPlatformWindows");

    public static File getCurrentUserDesktop() {
        try {
            Process process = Runtime.getRuntime().exec(DESKTOP_FOLDER_CMD);
            String s = CKit.readString(process.getInputStream(), Charset.defaultCharset());
            int ix = s.indexOf(REGSTR_TOKEN);
            if (ix >= 0) {
                return new File(s.substring(ix + REGSTR_TOKEN.length()).trim());
            }
            return null;
        } catch (Exception e) {
            log.error((Throwable) e);
            return null;
        }
    }

    @Override // goryachev.common.util.CPlatform
    @Deprecated
    public File getDefaultSettingsFolder() {
        return new File(getUserHome(), "goryachev.com");
    }

    @Override // goryachev.common.util.CPlatform
    protected File getSettingsFolderPrivate() {
        return new File(getUserHome(), "goryachev.com");
    }
}
