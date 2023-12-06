package goryachev.common.util;

import java.io.File;
import java.util.Collection;
import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/GlobalSettings.class */
public class GlobalSettings {
    private static Provider provider;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/GlobalSettings$Provider.class */
    public interface Provider {
        String getString(String str);

        void setString(String str, String str2);

        SStream getStream(String str);

        void setStream(String str, SStream sStream);

        List<String> getKeys();

        void save();
    }

    public static void setProvider(Provider p) {
        provider = p;
    }

    public static void setFileProvider(File f) {
        FileSettingsProvider p = new FileSettingsProvider(f);
        p.loadQuiet();
        setProvider(p);
    }

    private static Provider provider() {
        if (provider == null) {
            throw new NullPointerException("GlobalSettings.setProvider()");
        }
        return provider;
    }

    public static void save() {
        provider().save();
    }

    public static List<String> getKeys() {
        return provider().getKeys();
    }

    public static String getString(String key) {
        return provider().getString(key);
    }

    public static void setString(String key, String val) {
        set(key, val);
    }

    private static void set(String key, Object val) {
        String s = val == null ? null : val.toString();
        provider().setString(key, s);
    }

    public static int getInt(String key, int defaultValue) {
        return Parsers.parseInt(getString(key), defaultValue);
    }

    public static void setInt(String key, int val) {
        set(key, Integer.valueOf(val));
    }

    public static SStream getStream(String key) {
        return provider().getStream(key);
    }

    public static void setStream(String key, SStream s) {
        provider().setStream(key, s);
    }

    public static File getFile(String key) {
        String s = getString(key);
        if (s != null) {
            return new File(s);
        }
        return null;
    }

    public static void setFile(String key, File f) {
        String s = f == null ? null : f.getAbsolutePath();
        setString(key, s);
    }

    public static List<File> getFiles(String key) {
        SStream ss = getStream(key);
        int sz = ss.size();
        CList<File> list = new CList<>(sz);
        for (int i = 0; i < sz; i++) {
            String s = ss.nextString();
            list.add(s == null ? null : new File(s));
        }
        return list;
    }

    public static void setFiles(String key, Collection<File> files) {
        SStream ss = new SStream();
        if (files != null) {
            for (File f : files) {
                ss.add(f);
            }
        }
        setStream(key, ss);
    }
}
