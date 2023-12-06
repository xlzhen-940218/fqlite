package goryachev.common.util;

import goryachev.common.util.CSettings;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Properties;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CFileSettings.class */
public class CFileSettings implements CSettings.Provider {
    private File file;
    private Properties properties = new Properties();
    private boolean sorted;

    public CFileSettings(File f) {
        this.file = f;
    }

    public File getFile() {
        return this.file;
    }

    public void setSorted(boolean on) {
        this.sorted = on;
    }

    public boolean isSorted() {
        return this.sorted;
    }

    @Override // goryachev.common.util.CSettings.Provider
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    @Override // goryachev.common.util.CSettings.Provider
    public void setProperty(String key, String value) {
        if (value == null) {
            this.properties.remove(key);
        } else {
            this.properties.setProperty(key, value);
        }
    }

    @Override // goryachev.common.util.CSettings.Provider
    public CList<String> getPropertyNames() {
        return new CList<>(this.properties.stringPropertyNames());
    }

    public void load() throws Exception {
        load(getFile());
    }

    public void load(File f) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
        try {
            this.properties.load(in);
        } finally {
            CKit.close(in);
        }
    }

    @Override // goryachev.common.util.CSettings.Provider
    public void save() throws Exception {
        save(getFile());
    }

    public void save(File f) throws Exception {
        FileTools.ensureParentFolder(f);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "8859_1"));
        try {
            save(out);
        } finally {
            CKit.close(out);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Properties] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    protected void save(BufferedWriter wr) throws Exception {
        Object r0 = this.properties;
        synchronized (r0) {
            CList<String> keys = new CList<>(this.properties.stringPropertyNames());
            if (this.sorted) {
                CComparator.sortStrings(keys);
            }
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                String val = this.properties.getProperty(key);
                String key2 = saveConvert(key, true);
                String val2 = saveConvert(val, false);
                wr.write(key2);
                wr.write(61);
                wr.write(val2);
                wr.newLine();
            }
            r0 = r0;
            wr.flush();
        }
    }

    protected String saveConvert(String s, boolean escapeSpace) {
        int len = s.length();
        int sz = len + len;
        if (sz < 0) {
            sz = Integer.MAX_VALUE;
        }
        StringBuffer sb = new StringBuffer(sz);
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch > '=' && ch < 127) {
                if (ch == '\\') {
                    sb.append('\\');
                    sb.append('\\');
                } else {
                    sb.append(ch);
                }
            } else {
                switch (ch) {
                    case '\t':
                        sb.append('\\');
                        sb.append('t');
                        continue;
                    case '\n':
                        sb.append('\\');
                        sb.append('n');
                        continue;
                    case '\f':
                        sb.append('\\');
                        sb.append('f');
                        continue;
                    case '\r':
                        sb.append('\\');
                        sb.append('r');
                        continue;
                    case ' ':
                        if (i == 0 || escapeSpace) {
                            sb.append('\\');
                        }
                        sb.append(' ');
                        continue;
                    case '!':
                    case '#':
                    case ':':
                    case '=':
                        sb.append('\\');
                        sb.append(ch);
                        continue;
                    default:
                        if (ch < ' ' || ch > '~') {
                            sb.append('\\');
                            sb.append('u');
                            sb.append(Hex.toHexChar((ch >> '\f') & 15));
                            sb.append(Hex.toHexChar((ch >> '\b') & 15));
                            sb.append(Hex.toHexChar((ch >> 4) & 15));
                            sb.append(Hex.toHexChar(ch & 15));
                            break;
                        } else {
                            sb.append(ch);
                            continue;
                        }

                }
            }
        }
        return sb.toString();
    }

    public static CFileSettings loadQuiet(File f) {
        CFileSettings s = new CFileSettings(f);
        s.setSorted(true);
        try {
            s.load();
        } catch (Exception e) {
        }
        return s;
    }

    public void clear() {
        this.properties.clear();
    }
}
