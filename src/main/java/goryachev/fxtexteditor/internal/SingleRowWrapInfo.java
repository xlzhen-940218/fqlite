package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.GlyphType;
import goryachev.fxtexteditor.ITabPolicy;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/SingleRowWrapInfo.class */
public class SingleRowWrapInfo extends WrapInfo {
    private final int length;

    public SingleRowWrapInfo(int length) {
        this.length = length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowCount() {
        return 1;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphIndexForRow(int row) {
        return 0;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public boolean isCompatible(ITabPolicy tabPolicy, int width, boolean wrapLines) {
        return !wrapLines;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForCharIndex(int charIndex) {
        return 0;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForGlyphIndex(int glyphIndex) {
        return 0;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getColumnForCharIndex(int charIndex) {
        if (charIndex > this.length) {
            return this.length;
        }
        return charIndex;
    }

    protected void checkRow(int wrapRow) {
        if (wrapRow != 0) {
            throw new Error("wrapRow=" + wrapRow);
        }
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getCharIndexForColumn(int wrapRow, int column) {
        checkRow(wrapRow);
        if (column > this.length) {
            return this.length;
        }
        return column;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphCountAtRow(int wrapRow) {
        checkRow(wrapRow);
        return this.length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public TextCell getCell(TextCell cell, int wrapRow, int column) {
        GlyphType type;
        int ix;
        checkRow(wrapRow);
        if (column < this.length) {
            type = GlyphType.REG;
            ix = column;
        } else if (column == this.length) {
            type = GlyphType.EOL;
            ix = column;
        } else {
            type = GlyphType.EOL;
            ix = -1;
        }
        cell.set(type, ix, ix, ix, ix);
        return cell;
    }
}
