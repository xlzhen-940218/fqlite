package goryachev.fx.internal;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/TextStyleFlags.class */
public class TextStyleFlags {
    private static final int BOLD = 1;
    private static final int ITALIC = 4;
    private static final int UNDERSCORE = 8;
    private static final int STRIKETHROUGH = 16;

    public static boolean isBold(int x) {
        return (x & 1) != 0;
    }

    public static void setBold(byte[] flags, int ix, boolean on) {
        if (on) {
            flags[ix] = (byte) (flags[ix] | 1);
        } else {
            flags[ix] = (byte) (flags[ix] & (-2));
        }
    }

    public static boolean isItalic(int x) {
        return (x & 4) != 0;
    }

    public static void setItalic(byte[] flags, int ix, boolean on) {
        if (on) {
            flags[ix] = (byte) (flags[ix] | 4);
        } else {
            flags[ix] = (byte) (flags[ix] & (-5));
        }
    }

    public static boolean isStrikeThrough(int x) {
        return (x & 16) != 0;
    }

    public static void setStrikeThrough(byte[] flags, int ix, boolean on) {
        if (on) {
            flags[ix] = (byte) (flags[ix] | 16);
        } else {
            flags[ix] = (byte) (flags[ix] & (-17));
        }
    }

    public static boolean isUnderscore(int x) {
        return (x & 8) != 0;
    }

    public static void setUnderscore(byte[] flags, int ix, boolean on) {
        if (on) {
            flags[ix] = (byte) (flags[ix] | 8);
        } else {
            flags[ix] = (byte) (flags[ix] & (-9));
        }
    }
}
