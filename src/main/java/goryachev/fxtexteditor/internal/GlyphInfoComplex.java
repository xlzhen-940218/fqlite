package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.GlyphType;
import java.util.Arrays;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/GlyphInfoComplex.class */
public class GlyphInfoComplex extends AGlyphInfo {
    private final int[] charOffsets;

    public GlyphInfoComplex(String text, boolean hasTabs, int[] charOffsets) {
        super(text, hasTabs);
        this.charOffsets = charOffsets;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public boolean hasComplexGlyphs() {
        return true;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getCharIndex(int glyphIndex) {
        if (glyphIndex >= this.charOffsets.length) {
            return -1;
        }
        return this.charOffsets[glyphIndex];
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getGlyphIndex(int charIndex) {
        int ix = Arrays.binarySearch(this.charOffsets, charIndex);
        if (ix < 0) {
            ix = -ix;
        }
        return ix;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public String getGlyphText(int ix) {
        try {
            if (ix >= this.charOffsets.length) {
                return null;
            }
            int start = this.charOffsets[ix];
            int ix2 = ix + 1;
            if (ix2 == this.charOffsets.length) {
                return this.text.substring(start);
            }
            int end = this.charOffsets[ix2];
            return this.text.substring(start, end);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public GlyphType getGlyphType(int glyphIndex) {
        String s = getGlyphText(glyphIndex);
        if (s == null) {
            return GlyphType.EOL;
        }
        if (s.length() == 1 && "\t".equals(s)) {
            return GlyphType.TAB;
        }
        return GlyphType.REG;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getGlyphCount() {
        return this.charOffsets.length;
    }
}
