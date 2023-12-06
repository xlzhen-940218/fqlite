package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.GlyphType;
import goryachev.fxtexteditor.ITabPolicy;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/SimpleWrapInfo.class */
public class SimpleWrapInfo extends WrapInfo {
    private final int width;
    private final int length;

    public SimpleWrapInfo(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowCount() {
        if (this.width == 0) {
            return this.length;
        }
        return 1 + ((this.length - 1) / this.width);
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphIndexForRow(int row) {
        return row * this.width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public boolean isCompatible(ITabPolicy tabPolicy, int width, boolean wrapLines) {
        return wrapLines && this.width == width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForCharIndex(int charIndex) {
        if (charIndex < 0 || this.width == 0) {
            return 0;
        }
        if (charIndex > this.length) {
            charIndex = this.length;
        }
        return charIndex / this.width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForGlyphIndex(int glyphIndex) {
        return getWrapRowForCharIndex(glyphIndex);
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getColumnForCharIndex(int charIndex) {
        if (charIndex < 0) {
            return 0;
        }
        if (charIndex > this.length) {
            charIndex = this.length;
        }
        return charIndex % this.width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getCharIndexForColumn(int wrapRow, int column) {
        int ix = (wrapRow * this.width) + column;
        if (ix < this.length) {
            return ix;
        }
        return this.length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphCountAtRow(int wrapRow) {
        int rows = getWrapRowCount();
        if (wrapRow < rows - 1) {
            return this.width;
        }
        return this.length % this.width;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public TextCell getCell(TextCell cell, int wrapRow, int column) {
        GlyphType type;
        int ix = (wrapRow * this.width) + column;
        if (ix < this.length) {
            type = GlyphType.REG;
        } else if (ix == this.length) {
            type = GlyphType.EOL;
        } else {
            type = GlyphType.EOL;
            ix = -1;
        }
        cell.set(type, ix, ix, ix, ix);
        return cell;
    }
}
