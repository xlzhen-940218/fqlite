package goryachev.fxtexteditor.internal;

import goryachev.common.util.CList;
import goryachev.common.util.ElasticIntArray;
import goryachev.fxtexteditor.GlyphType;
import goryachev.fxtexteditor.ITabPolicy;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/ComplexWrapInfo.class */
public class ComplexWrapInfo extends WrapInfo {
    protected final FlowLine fline;
    protected final ITabPolicy tabPolicy;
    protected final int width;
    protected final boolean wrapLines;
    protected final int[][] cells;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType;

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType() {
        int[] iArr = $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[GlyphType.valuesCustom().length];
        try {
            iArr2[GlyphType.EOF.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[GlyphType.EOL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[GlyphType.REG.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[GlyphType.TAB.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType = iArr2;
        return iArr2;
    }

    public ComplexWrapInfo(FlowLine fline, ITabPolicy tabPolicy, int width, boolean wrapLines, int[][] cells) {
        this.fline = fline;
        this.tabPolicy = tabPolicy;
        this.width = width;
        this.wrapLines = wrapLines;
        this.cells = cells;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowCount() {
        return this.cells.length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphIndexForRow(int row) {
        int[] cs = this.cells[row];
        return GlyphIndex.fixGlypIndex(cs[0]);
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public boolean isCompatible(ITabPolicy tabPolicy, int width, boolean wrapLines) {
        return this.wrapLines == wrapLines && this.width == width && this.tabPolicy == tabPolicy;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForCharIndex(int charIndex) {
        int gix = this.fline.getGlyphIndex(charIndex);
        return getWrapRowForGlyphIndex(gix);
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getWrapRowForGlyphIndex(int gix) {
        int row = 0;
        while (row < this.cells.length) {
            int start = this.cells[row][0];
            if (gix >= start) {
                row++;
            } else {
                return row - 1;
            }
        }
        return row - 1;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getGlyphCountAtRow(int wrapRow) {
        return this.cells[wrapRow].length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getColumnForCharIndex(int charIndex) {
        int gix = this.fline.getGlyphIndex(charIndex);
        int row = getWrapRowForGlyphIndex(gix);
        int[] cs = this.cells[row];
        for (int i = 0; i < cs.length; i++) {
            int ix = cs[i];
            if (ix < 0) {
                ix = (-ix) - 1;
            }
            if (gix <= ix) {
                return i;
            }
        }
        return cs.length;
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public int getCharIndexForColumn(int wrapRow, int column) {
        if (wrapRow < 0 || wrapRow >= this.cells.length) {
            throw new Error("wrapRow=" + wrapRow);
        }
        int[] cs = this.cells[wrapRow];
        if (column < 0) {
            column = 0;
        }
        int gix = findNearestInsertPoint(cs, column);
        return this.fline.getCharIndex(gix);
    }

    @Override // goryachev.fxtexteditor.internal.WrapInfo
    public TextCell getCell(TextCell cell, int wrapRow, final int column) {
        GlyphType type;
        int caretIndex;
        int leadingCharIndex;
        int insertCharIndex;
        int glyphIndex;
        if (wrapRow >= this.cells.length) {
            throw new Error("wrapRow=" + wrapRow);
        }
        final int[] cs = this.cells[wrapRow];
        if (column < cs.length) {
            int gix = cs[column];
            if (gix < 0) {
                GlyphType type2 = GlyphType.TAB;
                int gx = GlyphIndex.fixGlypIndex(gix);
                int ix = this.fline.getCharIndex(gx);
                int gi = backtrackToLeadingTabEdge(cs, column, gx);
                final int leadIndex = this.fline.getCharIndex(gi);
                boolean leading = isLeading(cs, column, gix);
                int caret = leading ? ix : -1;
                return new TextCell(type2, caret, ix, ix, gx) { // from class: goryachev.fxtexteditor.internal.ComplexWrapInfo.1
                    @Override // goryachev.fxtexteditor.internal.TextCell
                    public int getLeadingEdgeCharIndex() {
                        return leadIndex;
                    }

                    @Override // goryachev.fxtexteditor.internal.TextCell
                    public int getInsertCharIndex() {
                        int gi2 = ComplexWrapInfo.findNearestInsertPoint(cs, column);
                        return ComplexWrapInfo.this.fline.getCharIndex(gi2);
                    }

                    @Override // goryachev.fxtexteditor.internal.TextCell
                    public int getTabSpan() {
                        return ComplexWrapInfo.computeTabSpan(cs, column);
                    }
                };
            }
            type = GlyphType.REG;
            int ix2 = this.fline.getCharIndex(gix);
            caretIndex = ix2;
            leadingCharIndex = ix2;
            insertCharIndex = ix2;
            glyphIndex = ix2;
        } else if (column == cs.length) {
            type = GlyphType.EOL;
            int ix3 = this.fline.getTextLength();
            caretIndex = ix3;
            leadingCharIndex = ix3;
            insertCharIndex = ix3;
            glyphIndex = ix3;
        } else {
            type = GlyphType.EOL;
            caretIndex = -1;
            leadingCharIndex = -1;
            insertCharIndex = this.fline.getTextLength();
            glyphIndex = -1;
        }
        cell.set(type, caretIndex, leadingCharIndex, insertCharIndex, glyphIndex);
        return cell;
    }

    protected static int computeTabSpan(int[] cs, int ix) {
        int span = 1;
        int val = cs[ix];
        for (int i = ix + 1; i < cs.length; i++) {
            if (cs[i] != val) {
                return span;
            }
            span++;
        }
        return span;
    }

    protected static boolean isLeading(int[] cs, int ix, int gix) {
        return ix == 0 || cs[ix - 1] != gix;
    }

    protected static int backtrackToLeadingTabEdge(int[] cs, int ix, int gix) {
        for (int i = ix; i >= 0; i--) {
            int j = i - 1;
            if (j < 0) {
                int v = cs[0];
                return GlyphIndex.fixGlypIndex(v);
            }
            int gv = cs[j];
            if (gv != gix) {
                int v2 = cs[i];
                return GlyphIndex.fixGlypIndex(v2);
            }
        }
        throw new Error();
    }

    protected static int findNearestInsertPoint(int[] cs, int column) {
        if (column >= cs.length) {
            return cs[cs.length - 1] + 1;
        }
        int gix = cs[column];
        if (gix >= 0) {
            return gix;
        }
        int i = 1;
        while (true) {
            if (i >= cs.length) {
                break;
            }
            int ix = column + i;
            if (ix >= cs.length) {
                gix = cs[cs.length - 1];
                break;
            } else if (cs[ix] != gix) {
                gix = cs[ix];
                break;
            } else {
                int ix2 = column - i;
                if (ix2 <= 0) {
                    gix = cs[0];
                    break;
                } else if (cs[ix2] == gix) {
                    i++;
                } else {
                    gix = cs[ix2 + 1];
                    break;
                }
            }
        }
        return GlyphIndex.fixGlypIndex(gix);
    }

    /* JADX WARN: Type inference failed for: r0v32, types: [int[], int[][], java.lang.Object[]] */
    public static ComplexWrapInfo createComplexWrapInfo(FlowLine fline, ITabPolicy tabPolicy, int width, boolean wrapLines) {
        CList cList = new CList(4);
        ElasticIntArray cells = null;
        int glyphIndex = 0;
        int x = 0;
        int tabSpan = -1;
        while (true) {
            if (cells == null) {
                cells = new ElasticIntArray();
            }
            if (tabSpan > 0) {
                if (wrapLines && x >= width) {
                    tabSpan = -1;
                    cList.add(cells.toArray());
                    cells = null;
                    x = 0;
                    glyphIndex++;
                } else {
                    cells.add((-glyphIndex) - 1);
                    tabSpan--;
                    if (tabSpan == 0) {
                        glyphIndex++;
                    }
                    x++;
                }
            } else if (wrapLines && x >= width) {
                if (tabSpan == 0) {
                    glyphIndex++;
                }
                tabSpan = -1;
                x = 0;
                cList.add(cells.toArray());
                cells = null;
            } else {
                tabSpan = -1;
                GlyphType gt = fline.getGlyphType(glyphIndex);
                switch ($SWITCH_TABLE$goryachev$fxtexteditor$GlyphType()[gt.ordinal()]) {
                    case 2:
                        if (cells.size() > 0) {
                            cList.add(cells.toArray());
                        }
                        Object r0 = new int[cList.size()];
                        cList.toArray((Object[]) r0);
                        return new ComplexWrapInfo(fline, tabPolicy, width, wrapLines, (int[][]) r0);
                    case 3:
                        cells.add(glyphIndex);
                        glyphIndex++;
                        x++;
                        continue;
                    case 4:
                        int tabSpan2 = tabPolicy.nextTabStop(x) - x;
                        cells.add((-glyphIndex) - 1);
                        tabSpan = tabSpan2 - 1;
                        x++;
                        continue;
                    default:
                        throw new Error("?" + gt);
                }
            }
        }
    }
}
