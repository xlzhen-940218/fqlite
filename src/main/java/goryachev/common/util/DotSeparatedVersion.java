package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/DotSeparatedVersion.class */
public class DotSeparatedVersion {
    public static int[] parse(String text) {
        String[] ss = CKit.split((CharSequence) text, '.');
        int sz = ss.length;
        int[] rv = new int[sz];
        for (int i = 0; i < sz; i++) {
            rv[i] = parseNumber(ss[i]);
        }
        return rv;
    }

    public static int compare(String a, String b) throws Exception {
        int[] ai = parse(a);
        int[] bi = parse(b);
        int sz = Math.min(ai.length, bi.length);
        if (sz > 0) {
            for (int i = 0; i < sz; i++) {
                int d = ai[i] - bi[i];
                if (d < 0) {
                    return -1;
                }
                if (d > 0) {
                    return 1;
                }
            }
            return ai.length - bi.length;
        }
        throw new Exception("unable to compare " + a + " and " + b);
    }

    private static int parseNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                s = s.substring(0, i);
            }
        }
        return Parsers.parseInt(s, 0);
    }
}
