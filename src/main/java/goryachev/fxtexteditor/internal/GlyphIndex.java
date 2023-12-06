package goryachev.fxtexteditor.internal;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/GlyphIndex.class */
public class GlyphIndex {
    public static final int EOF = Integer.MIN_VALUE;
    public static final int EOL = -2147483647;

    public static boolean isTab(int ix) {
        if (ix < 0) {
            switch (ix) {
                case Integer.MIN_VALUE:
                case EOL /* -2147483647 */:
                    return false;
                default:
                    return true;
            }
        }
        return false;
    }

    public static boolean isEOF(int glyphIndex) {
        return glyphIndex == Integer.MIN_VALUE;
    }

    public static boolean isEOL(int glyphIndex) {
        return glyphIndex == -2147483647;
    }

    public static int fixGlypIndex(int gix) {
        switch (gix) {
            case Integer.MIN_VALUE:
            case EOL /* -2147483647 */:
                throw new Error("gix=" + gix);
            default:
                if (gix < 0) {
                    return (-gix) - 1;
                }
                return gix;
        }
    }
}
