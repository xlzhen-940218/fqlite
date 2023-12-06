package goryachev.common.util.platform;

import goryachev.common.util.CList;
import goryachev.common.util.CSorter;
import goryachev.common.util.Hex;
import goryachev.common.util.SB;
import java.security.Security;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import javafx.scene.control.ButtonBar;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/platform/SysInfo.class */
public class SysInfo {
    protected DecimalFormat numberFormat = new DecimalFormat("#,##0.##");
    protected final Out out;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/platform/SysInfo$Out.class */
    public static abstract class Out {
        public abstract void header(String str);

        public abstract void nl();

        public abstract void print(int i, String str, String str2);
    }

    public SysInfo(Out out) {
        this.out = out;
    }

    public static String getSystemInfo() {
        StringOut out = new StringOut();
        getSystemInfo(out);
        return out.getReport();
    }

    public static void getSystemInfo(Out out) {
        SysInfo s = new SysInfo(out);
        s.extractApp();
        s.extractSystemProperties();
        s.extractEnvironment();
    }

    protected void header(String title) {
        this.out.header(title);
    }

    protected void nl() {
        this.out.nl();
    }

    protected void print(String name, String value) {
        print(1, name, value);
    }

    protected void print(int indents, String name, String value) {
        this.out.print(indents, name, value);
    }

    protected String number(Object x) {
        return this.numberFormat.format(x);
    }

    protected String safe(String s) {
        if (s != null) {
            boolean notSafe = false;
            int sz = s.length();
            int i = 0;
            while (true) {
                if (i < sz) {
                    if (s.charAt(i) >= ' ') {
                        i++;
                    } else {
                        notSafe = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (notSafe) {
                SB sb = new SB(sz);
                for (int i2 = 0; i2 < sz; i2++) {
                    char c = s.charAt(i2);
                    if (c < ' ') {
                        sb.a(unicode(c));
                    } else {
                        sb.a(c);
                    }
                }
                s = sb.toString();
            }
        }
        return s;
    }

    protected static String unicode(char c) {
        return "\\u" + Hex.toHexString((int) c, 4);
    }

    public void extractApp() {
        long max = Runtime.getRuntime().maxMemory();
        long free = (max - Runtime.getRuntime().totalMemory()) + Runtime.getRuntime().freeMemory();
        header("Application");
        print("Time/Date", new SimpleDateFormat("yyyy-MMdd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis())));
        print("Available Memory", number(Long.valueOf(max)));
        print("Free Memory:", number(Long.valueOf(free)));
        nl();
    }

    public void extractEnvironment() {
        header("Environment");
        Map<String, String> env = System.getenv();
        CList<String> keys = new CList<>(env.keySet());
        CSorter.sort(keys);
        Iterator<?> it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            print(key, safe(env.get(key)));
        }
        nl();
    }

    public void extractSystemProperties() {
        header("System Properties");
        Properties p = System.getProperties();
        CList<String> keys = new CList<>(p.stringPropertyNames());
        CSorter.sort(keys);
        Iterator<?> it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            print(key, safe(p.getProperty(key)));
        }
        nl();
    }

    public void extractSecurity() {
        header("Security");
        listSecurityAlgorithms("Cipher");
        listSecurityAlgorithms("KeyStore");
        listSecurityAlgorithms("Mac");
        listSecurityAlgorithms("MessageDigest");
        listSecurityAlgorithms("Signature");
        nl();
    }

    protected void listSecurityAlgorithms(String name) {
        print(name, ButtonBar.BUTTON_ORDER_NONE);
        try {
            CList<String> names = new CList<>(Security.getAlgorithms(name));
            CSorter.sort(names);
            Iterator<?> it = names.iterator();
            while (it.hasNext()) {
                String s = (String) it.next();
                print(2, s, ButtonBar.BUTTON_ORDER_NONE);
            }
        } catch (Exception e) {
            print(e.getMessage(), ButtonBar.BUTTON_ORDER_NONE);
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/platform/SysInfo$StringOut.class */
    public static class StringOut extends Out {
        private String indent = "\t";
        private final SB sb = new SB();

        @Override // goryachev.common.util.platform.SysInfo.Out
        public void header(String title) {
            this.sb.a(title).nl();
        }

        @Override // goryachev.common.util.platform.SysInfo.Out
        public void nl() {
            this.sb.nl();
        }

        @Override // goryachev.common.util.platform.SysInfo.Out
        public void print(int count, String name, String value) {
            for (int i = 0; i < count; i++) {
                this.sb.a(this.indent);
            }
            this.sb.append(name);
            this.sb.append("=");
            this.sb.append(value);
            this.sb.nl();
        }

        public String getReport() {
            return this.sb.getAndClear();
        }
    }
}
