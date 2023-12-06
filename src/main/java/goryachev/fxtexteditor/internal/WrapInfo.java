package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.ITabPolicy;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/WrapInfo.class */
public abstract class WrapInfo {
    public static final WrapInfo EMPTY = new SingleRowWrapInfo(0);

    public abstract boolean isCompatible(ITabPolicy iTabPolicy, int i, boolean z);

    public abstract int getWrapRowCount();

    public abstract int getWrapRowForGlyphIndex(int i);

    public abstract int getWrapRowForCharIndex(int i);

    public abstract int getColumnForCharIndex(int i);

    public abstract int getGlyphCountAtRow(int i);

    public abstract int getGlyphIndexForRow(int i);

    public abstract int getCharIndexForColumn(int i, int i2);

    public abstract TextCell getCell(TextCell textCell, int i, int i2);

    public static WrapInfo create(FlowLine fline, ITabPolicy tabPolicy, int width, boolean wrapLines) {
        if (width == 0) {
            return EMPTY;
        }
        int lineIndex = fline.getModelIndex();
        if (lineIndex < 0) {
            return EMPTY;
        }
        boolean complex = fline.hasComplexGlyphs();
        if (!complex && !tabPolicy.isSimple()) {
            complex |= fline.hasTabs();
        }
        if (complex) {
            return ComplexWrapInfo.createComplexWrapInfo(fline, tabPolicy, width, wrapLines);
        }
        int len = fline.getGlyphCount();
        if (wrapLines) {
            return new SimpleWrapInfo(len, width);
        }
        return new SingleRowWrapInfo(len);
    }
}
