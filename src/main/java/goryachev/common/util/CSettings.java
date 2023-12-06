package goryachev.common.util;

import java.io.File;
import java.util.Hashtable;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSettings.class */
public class CSettings {
    public static final Provider NONE = new Provider() { // from class: goryachev.common.util.CSettings.1
        @Override // goryachev.common.util.CSettings.Provider
        public String getProperty(String key) {
            return null;
        }

        @Override // goryachev.common.util.CSettings.Provider
        public void setProperty(String key, String value) {
        }

        @Override // goryachev.common.util.CSettings.Provider
        public CList<String> getPropertyNames() {
            return new CList<>(0);
        }

        @Override // goryachev.common.util.CSettings.Provider
        public void save() throws Exception {
        }
    };
    private Provider provider = NONE;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSettings$Provider.class */
    public interface Provider {
        String getProperty(String str);

        void setProperty(String str, String str2);

        CList<String> getPropertyNames();

        void save() throws Exception;
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSettings$CMAP.class */
    public static class CMAP extends CSettings {
        public CMAP() {
            super(new Provider() { // from class: goryachev.common.util.CSettings.CMAP.1
                protected final Hashtable<String, Object> settings = new Hashtable<>();

                @Override // goryachev.common.util.CSettings.Provider
                public String getProperty(String key) {
                    return Parsers.parseString(this.settings.get(key));
                }

                @Override // goryachev.common.util.CSettings.Provider
                public void setProperty(String key, String value) {
                    this.settings.put(key, value);
                }

                @Override // goryachev.common.util.CSettings.Provider
                public CList<String> getPropertyNames() {
                    return new CList<>(this.settings.keySet());
                }

                @Override // goryachev.common.util.CSettings.Provider
                public void save() throws Exception {
                }
            });
        }
    }

    public CSettings() {
    }

    public CSettings(Provider p) {
        setProvider(p);
    }

    public void setProvider(Provider p) {
        this.provider = p;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public String getProperty(String key) {
        return this.provider.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.provider.setProperty(key, value);
    }

    public void save() throws Exception {
        this.provider.save();
    }

    public void set(String key, Object value) {
        setProperty(key, value == null ? null : value.toString());
    }

    public void setBoolean(String key, boolean val) {
        set(key, Boolean.valueOf(val));
    }

    public void setBool(String key, boolean val) {
        set(key, Boolean.valueOf(val));
    }

    public void setInt(String key, int val) {
        set(key, Integer.valueOf(val));
    }

    public void setLong(String key, long val) {
        set(key, Long.valueOf(val));
    }

    public Boolean getBoolean(String key) {
        String s = getProperty(key);
        if (s == null) {
            return null;
        }
        return Parsers.parseBoolean(s);
    }

    public boolean getBool(String key, boolean defaultValue) {
        Boolean b = getBoolean(key);
        if (b == null) {
            return defaultValue;
        }
        return b.booleanValue();
    }

    public boolean getBool(String key) {
        return Boolean.TRUE.equals(getBoolean(key));
    }

    public int getInt(String key, int defaultValue) {
        String s = getProperty(key);
        if (s != null) {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public long getLong(String key, long defaultValue) {
        String s = getProperty(key);
        if (s != null) {
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public String getProperty(String key, String defaultValue) {
        String s = getProperty(key);
        if (s == null) {
            return defaultValue;
        }
        return s;
    }

    public String[] getList(String key) {
        String s = getProperty(key);
        if (s == null) {
            return CKit.emptyStringArray;
        }
        return CKit.split(s, ",");
    }

    public static CSettings loadFromFile(String filename) {
        return loadFromFile(new File(filename));
    }

    public static CSettings loadFromFile(File f) {
        return new CSettings(CFileSettings.loadQuiet(f));
    }

    public File getFile(String key) {
        return Parsers.parseFile(getProperty(key));
    }

    public File getFile(String key, File defaultValue) {
        String s = getProperty(key);
        if (s == null) {
            return defaultValue;
        }
        return Parsers.parseFile(s);
    }

    public void setFile(String key, File f) {
        setProperty(key, f == null ? null : f.getAbsolutePath());
    }

    public <T extends Enum> T getEnum(String key, Class<T> type) {
        return (T) getEnum(key, type, null);
    }

    public <T extends Enum> T getEnum(String key, Class<T> type, T defaultValue) {
        String s = getProperty(key);
        if (s != null) {
            try {
                return (T) Enum.valueOf(type, s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public <T extends Enum> void setEnum(String key, T value) {
        setProperty(key, value == null ? null : value.toString());
    }
}
