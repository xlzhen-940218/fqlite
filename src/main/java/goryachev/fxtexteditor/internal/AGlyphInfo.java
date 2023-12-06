package goryachev.fxtexteditor.internal;

import goryachev.common.util.text.IBreakIterator;
import goryachev.fxtexteditor.GlyphType;
import java.util.Arrays;
import javafx.scene.control.ButtonBar;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/AGlyphInfo.class */
public abstract class AGlyphInfo {
    public static final AGlyphInfo BLANK = new GlyphInfoSimple(ButtonBar.BUTTON_ORDER_NONE, false);
    protected final String text;
    protected final boolean hasTabs;

    public abstract String getGlyphText(int i);

    public abstract GlyphType getGlyphType(int i);

    public abstract int getCharIndex(int i);

    public abstract int getGlyphIndex(int i);

    public abstract int getGlyphCount();

    public abstract boolean hasComplexGlyphs();

    /* JADX INFO: Access modifiers changed from: protected */
    public AGlyphInfo(String text, boolean hasTabs) {
        this.text = text;
        this.hasTabs = hasTabs;
    }

    public final boolean hasTabs() {
        return this.hasTabs;
    }

    public static AGlyphInfo create(String text, IBreakIterator breakIterator) {
        if (text == null) {
            return BLANK;
        }
        boolean hasTabs = false;
        if (breakIterator == null) {
            hasTabs = text.indexOf(9) >= 0;
        } else {
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c == '\t') {
                    hasTabs = true;
                } else if (isComplex(c)) {
                    return createComplex(text, hasTabs, breakIterator);
                }
            }
        }
        return new GlyphInfoSimple(text, hasTabs);
    }

    private static boolean isComplex(char c) {
        if (Character.isSurrogate(c)) {
            return true;
        }
        int t = Character.getType(c);
        switch (t) {
            case 0:
            case 4:
            case 6:
            case 8:
            case 13:
            case 14:
            case 16:
            case 19:
                return true;
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 15:
            case 17:
            case 18:
            default:
                return false;
        }
    }

    private static GlyphInfoComplex createComplex(String text, boolean hasTabs, IBreakIterator bi) {
        int len = text.length();
        int[] offsets = new int[len];
        int gi = 0;
        int off = 0;
        bi.setText(text);
        int start = bi.first();
        int next = bi.next();
        while (true) {
            int end = next;
            if (end == -1) {
                break;
            }
            String s = text.substring(start, end);
            if (!hasTabs && "\t".equals(s)) {
                hasTabs = true;
            }
            int i = gi;
            gi++;
            offsets[i] = off;
            off += s.length();
            start = end;
            next = bi.next();
        }
        if (gi != len) {
            offsets = Arrays.copyOf(offsets, gi);
        }
        return new GlyphInfoComplex(text, hasTabs, offsets);
    }
}
