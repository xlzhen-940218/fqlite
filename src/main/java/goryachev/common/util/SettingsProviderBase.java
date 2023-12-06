package goryachev.common.util;

import goryachev.common.io.CReader;
import goryachev.common.log.Log;
import goryachev.common.util.GlobalSettings;
import java.util.List;
import javafx.fxml.FXMLLoader;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SettingsProviderBase.class */
public abstract class SettingsProviderBase implements GlobalSettings.Provider {
    protected CMap<String, Object> data = new CMap<>();
    protected static final Log log = Log.get("SettingsProviderBase");

    @Override // goryachev.common.util.GlobalSettings.Provider
    public abstract void save();

    @Override // goryachev.common.util.GlobalSettings.Provider
    public synchronized List<String> getKeys() {
        return this.data.keys();
    }

    public synchronized boolean containsKey(String k) {
        return this.data.containsKey(k);
    }

    public synchronized int size() {
        return this.data.size();
    }

    protected synchronized Object getValue(String key) {
        return this.data.get(key);
    }

    protected String getStringPrivate(String key) {
        Object x = getValue(key);
        if (x instanceof String) {
            return (String) x;
        }
        return null;
    }

    protected String[] getArrayPrivate(String key) {
        Object x = getValue(key);
        if (x instanceof String[]) {
            return (String[]) x;
        }
        if (x instanceof String) {
            return new String[]{(String) x};
        }
        return null;
    }

    @Override // goryachev.common.util.GlobalSettings.Provider
    public String getString(String key) {
        String v = getStringPrivate(key);
        return v;
    }

    @Override // goryachev.common.util.GlobalSettings.Provider
    public synchronized void setString(String key, String val) {
        if (val == null) {
            this.data.remove(key);
        } else {
            this.data.put(key, val);
        }
    }

    @Override // goryachev.common.util.GlobalSettings.Provider
    public SStream getStream(String key) {
        String[] ss = getArrayPrivate(key);
        return new SStream(ss);
    }

    @Override // goryachev.common.util.GlobalSettings.Provider
    public synchronized void setStream(String key, SStream s) {
        this.data.put(key, s.toArray());
    }

    public String asString() {
        return asString(true);
    }

    public synchronized String asString(boolean sort) {
        String[] strArr;
        List<String> keys = getKeys();
        if (sort) {
            CSorter.sort(keys);
        }
        SB sb = new SB(keys.size() * 128);
        for (String k : keys) {
            Object x = this.data.get(k);
            sb.a(encode(k));
            sb.a('=');
            if (x == null) {
                sb.a(FXMLLoader.ESCAPE_PREFIX);
            } else if (x instanceof String) {
                sb.a(encode((String) x));
            } else if (x instanceof String[]) {
                boolean comma = false;
                for (String s : (String[]) x) {
                    if (comma) {
                        sb.a(',');
                    } else {
                        comma = true;
                    }
                    sb.a(encode(s));
                }
            } else {
                throw new Error("?" + x);
            }
            sb.nl();
        }
        return sb.toString();
    }

    public synchronized void loadFromString(String s) throws Exception {
        CReader rd = new CReader(s);
        while (true) {
            try {
                String line = rd.readLine();
                if (line != null) {
                    int ix = line.indexOf(61);
                    if (ix >= 0) {
                        String k = decode(line.substring(0, ix));
                        String v = line.substring(ix + 1);
                        if (v.contains(",")) {
                            String[] ss = CKit.split((CharSequence) v, ',');
                            for (int i = 0; i < ss.length; i++) {
                                ss[i] = decode(ss[i]);
                            }
                            this.data.put(k, ss);
                        } else {
                            this.data.put(k, decode(v));
                        }
                    }
                } else {
                    return;
                }
            } finally {
                CKit.close(rd);
            }
        }
    }

    protected static SStream parseStream(String text) {
        SStream rv = new SStream();
        if (text != null) {
            String[] ss = CKit.split(text, ",");
            for (String s : ss) {
                rv.add(decode(s));
            }
        }
        return rv;
    }

    protected static String decode(String s) {
        if (FXMLLoader.ESCAPE_PREFIX.equals(s)) {
            return null;
        }
        try {
            SB sb = new SB();
            int sz = s.length();
            int i = 0;
            while (i < sz) {
                char c = s.charAt(i);
                if (c == '\\') {
                    int i2 = i + 1;
                    String sub = s.substring(i2, i2 + 2);
                    sb.append((char) Hex.parseByte(sub));
                    i = i2 + 1;
                } else {
                    sb.append(c);
                }
                i++;
            }
            return sb.toString();
        } catch (Exception e) {
            log.error((Throwable) e);
            return null;
        }
    }

    protected static String encode(String s) {
        if (s == null) {
            return FXMLLoader.ESCAPE_PREFIX;
        }
        int sz = s.length();
        SB sb = new SB(sz * 3);
        for (int i = 0; i < sz; i++) {
            char c = s.charAt(i);
            if (c < ' ') {
                encode(c, sb);
            } else {
                switch (c) {
                    case ',':
                    case '=':
                    case '\\':
                        encode(c, sb);
                        continue;
                    default:
                        sb.append(c);
                        continue;
                }
            }
        }
        return sb.toString();
    }

    protected static void encode(char c, SB sb) {
        sb.a('\\');
        sb.a(Hex.toHexByte(c));
    }
}
