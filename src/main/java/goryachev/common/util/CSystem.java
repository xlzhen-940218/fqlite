package goryachev.common.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSystem.class */
public class CSystem {
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static String listEnvironment() {
        return listEnvironment(0);
    }

    public static String listEnvironment(int indent) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> env = System.getenv();
        String[] keys = (String[]) env.keySet().toArray(new String[env.size()]);
        Arrays.sort(keys);
        for (String key : keys) {
            for (int i = 0; i < indent; i++) {
                sb.append(' ');
            }
            sb.append(key).append('=').append(env.get(key)).append('\n');
        }
        return sb.toString();
    }

    public static String listSystemProperties() {
        return listSystemProperties(0);
    }

    public static String listSystemProperties(int indent) {
        StringBuilder sb = new StringBuilder();
        Properties p = System.getProperties();
        Set<String> pnames = p.stringPropertyNames();
        String[] keys = (String[]) pnames.toArray(new String[pnames.size()]);
        Arrays.sort(keys);
        for (String key : keys) {
            for (int i = 0; i < indent; i++) {
                sb.append(' ');
            }
            sb.append(key).append('=').append(p.getProperty(key)).append("\n");
        }
        return sb.toString();
    }

    public static String getPlatformEncoding() {
        return System.getProperty("file.encoding");
    }
}
