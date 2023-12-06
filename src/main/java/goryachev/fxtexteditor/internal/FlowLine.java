package goryachev.fxtexteditor.internal;

import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.GlyphType;
import goryachev.fxtexteditor.ITabPolicy;
import goryachev.fxtexteditor.ITextLine;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/FlowLine.class */
public class FlowLine {
    public static final FlowLine BLANK = new FlowLine(null, AGlyphInfo.BLANK);
    private final ITextLine tline;
    private final AGlyphInfo info;
    private WrapInfo wrap;

    public FlowLine(ITextLine tline, AGlyphInfo info) {
        this.tline = tline;
        this.info = info;
    }

    public ITextLine getTextLine() {
        return this.tline;
    }

    public String getText() {
        if (this.tline == null) {
            return null;
        }
        return this.tline.getPlainText();
    }

    public AGlyphInfo glyphInfo() {
        return this.info;
    }

    public WrapInfo getWrapInfo(ITabPolicy tabPolicy, int width, boolean wrapLines) {
        if (this.wrap != null && this.wrap.isCompatible(tabPolicy, width, wrapLines)) {
            return this.wrap;
        }
        this.wrap = WrapInfo.create(this, tabPolicy, width, wrapLines);
        return this.wrap;
    }

    public boolean hasComplexGlyphs() {
        return this.info.hasComplexGlyphs();
    }

    public boolean hasTabs() {
        return this.info.hasTabs();
    }

    public int getGlyphCount() {
        return this.info.getGlyphCount();
    }

    public int getCharIndex(int glyphIndex) {
        return this.info.getCharIndex(glyphIndex);
    }

    public int getGlyphIndex(int charIndex) {
        return this.info.getGlyphIndex(charIndex);
    }

    public int getTextLength() {
        if (this.tline == null) {
            return 0;
        }
        return this.tline.getTextLength();
    }

    public int getModelIndex() {
        if (this.tline == null) {
            return -1;
        }
        return this.tline.getModelIndex();
    }

    public TextCellStyle getCellStyle(int charOffset) {
        if (this.tline != null) {
            return this.tline.getCellStyle(charOffset);
        }
        return null;
    }

    public GlyphType getGlyphType(int glyphIndex) {
        return this.info.getGlyphType(glyphIndex);
    }
}
