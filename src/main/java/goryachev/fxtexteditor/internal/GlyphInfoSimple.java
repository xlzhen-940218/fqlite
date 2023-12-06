package goryachev.fxtexteditor.internal;

import goryachev.fx.internal.GlyphCache;
import goryachev.fxtexteditor.GlyphType;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/GlyphInfoSimple.class */
public class GlyphInfoSimple extends AGlyphInfo {
    public GlyphInfoSimple(String text, boolean hasTabs) {
        super(text, hasTabs);
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public String getGlyphText(int ix) {
        if (ix >= 0 && ix < this.text.length()) {
            char c = this.text.charAt(ix);
            return GlyphCache.get(c);
        }
        return null;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public GlyphType getGlyphType(int ix) {
        if (ix < 0) {
            return GlyphType.EOL;
        }
        if (ix >= this.text.length()) {
            return GlyphType.EOL;
        }
        char c = this.text.charAt(ix);
        if (c == '\t') {
            return GlyphType.TAB;
        }
        return GlyphType.REG;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getCharIndex(int glyphIndex) {
        if (glyphIndex < 0) {
            return 0;
        }
        if (glyphIndex > this.text.length()) {
            return this.text.length();
        }
        return glyphIndex;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getGlyphIndex(int charIndex) {
        return charIndex;
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public int getGlyphCount() {
        return this.text.length();
    }

    @Override // goryachev.fxtexteditor.internal.AGlyphInfo
    public boolean hasComplexGlyphs() {
        return false;
    }
}
