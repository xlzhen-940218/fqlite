package goryachev.fx.internal;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/GlyphCache.class */
public class GlyphCache {
    private static final int CAPACITY = 1280;
    private static final String[] cache = new String[CAPACITY];

    public static String get(char ch) {
        if (ch < CAPACITY) {
            String s = cache[ch];
            if (s == null) {
                s = String.valueOf(ch);
                cache[ch] = s;
            }
            return s;
        }
        return String.valueOf(ch);
    }
}
