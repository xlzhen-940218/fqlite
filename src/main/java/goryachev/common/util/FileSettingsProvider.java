package goryachev.common.util;

import goryachev.common.log.Log;
import java.io.File;
import java.io.FileNotFoundException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/FileSettingsProvider.class */
public class FileSettingsProvider extends SettingsProviderBase {
    protected static final Log log = Log.get("FileSettingsProvider");
    private File file;

    public FileSettingsProvider(File f) {
        setFile(f);
    }

    public void setFile(File f) {
        this.file = f;
    }

    @Override // goryachev.common.util.SettingsProviderBase, goryachev.common.util.GlobalSettings.Provider
    public void save() {
        try {
            String s = asString();
            CKit.write(this.file, s);
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    public void load() throws Exception {
        try {
            System.out.println(">>>> load() " + this.file);
            String s = CKit.readString(this.file);
            loadFromString(s);
        } catch (FileNotFoundException e) {
        }
    }

    public void loadQuiet() {
        try {
            load();
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    public void load(File f) throws Exception {
        setFile(f);
        load();
    }

    public void loadQuiet(File f) {
        try {
            load(f);
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }
}
