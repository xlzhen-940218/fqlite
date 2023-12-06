package goryachev.common.util.platform;

import goryachev.common.util.CPlatform;
import java.io.File;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/platform/CPlatformUnix.class */
public class CPlatformUnix extends CPlatform {
    @Override // goryachev.common.util.CPlatform
    protected File getSettingsFolderPrivate() {
        return new File(getUserHome(), ".goryachev.com");
    }
}
