package goryachev.fxtexteditor.internal;

import goryachev.common.util.SB;
import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.GlyphType;
import goryachev.fxtexteditor.ITextLine;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/ScreenRow.class */
public class ScreenRow {
    private FlowLine flowLine = FlowLine.BLANK;
    private WrapInfo wrap;
    private int lineNumber;
    private int wrapRow;
    private int startGlyphIndex;
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

    public FlowLine getFlowLine() {
        return this.flowLine;
    }

    public boolean isBOL() {
        return this.wrapRow == 0;
    }

    public ITextLine getTextLine() {
        return this.flowLine.getTextLine();
    }

    public Color getLineColor() {
        ITextLine t = getTextLine();
        if (t == null) {
            return null;
        }
        return t.getLineColor();
    }

    public String toString() {
        SB sb = new SB();
        sb.append("(");
        sb.append(this.lineNumber);
        sb.append(",");
        sb.append(this.wrapRow);
        sb.append(") ");
        return sb.toString();
    }

    public void init(FlowLine fline, WrapInfo wrap, int lineNumber, int wrapRow, int startGlyphIndex) {
        this.flowLine = fline;
        this.wrap = wrap;
        this.lineNumber = lineNumber;
        this.wrapRow = wrapRow;
        this.startGlyphIndex = startGlyphIndex;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getStartGlyphIndex() {
        return this.startGlyphIndex;
    }

    public TextCell getCell(int column) {
        return this.wrap.getCell(TextCell.globalInstance(), this.wrapRow, column);
    }

    public String getCellText(TextCell cell) {
        switch ($SWITCH_TABLE$goryachev$fxtexteditor$GlyphType()[cell.getGlyphType().ordinal()]) {
            case 1:
            case 2:
            case 4:
                return null;
            case 3:
            default:
                int gix = cell.getGlyphIndex();
                return this.flowLine.glyphInfo().getGlyphText(gix);
        }
    }

    public TextCellStyle getCellStyles(TextCell cell) {
        switch ($SWITCH_TABLE$goryachev$fxtexteditor$GlyphType()[cell.getGlyphType().ordinal()]) {
            case 1:
            case 2:
                return null;
            default:
                int gix = cell.getGlyphIndex();
                int charIndex = this.flowLine.glyphInfo().getCharIndex(gix);
                return this.flowLine.getCellStyle(charIndex);
        }
    }

    public int getGlyphCount() {
        return this.wrap.getGlyphCountAtRow(this.wrapRow);
    }

    public int getWrapRow() {
        return this.wrapRow;
    }
}
