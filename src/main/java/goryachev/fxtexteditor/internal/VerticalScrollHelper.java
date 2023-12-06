package goryachev.fxtexteditor.internal;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.fxtexteditor.GlyphPos;
import goryachev.fxtexteditor.VFlow;

@Deprecated
/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/VerticalScrollHelper.class */
public class VerticalScrollHelper {
    protected static final Log log = Log.get("VerticalScrollHelper");
    private final VFlow vflow;
    private final int modelLineCount;
    private final int originalTarget;
    private final double fraction;

    public VerticalScrollHelper(VFlow vflow, int modelLineCount, int top, double fraction) {
        this.vflow = vflow;
        this.modelLineCount = modelLineCount;
        this.originalTarget = top;
        this.fraction = fraction;
    }

    public GlyphPos process() {
        int newLineNumber;
        int newGlyphIndex;
        int screenRows = this.vflow.getScreenRowCount();
        int frameSize = Math.max(64, screenRows);
        int start = this.originalTarget - frameSize;
        int shift = 0;
        if (start < 0) {
            shift = -start;
            start = 0;
        }
        int end = this.originalTarget + screenRows + frameSize + shift;
        int shift2 = 0;
        if (end > this.modelLineCount) {
            shift2 = end - this.modelLineCount;
            end = this.modelLineCount;
        }
        if (shift2 > 0) {
            start = Math.max(start - shift2, 0);
        }
        int additionalRows = 0;
        for (int ix = start; ix < end; ix++) {
            int add = this.vflow.getWrapInfo(ix).getWrapRowCount();
            if (add > 1) {
                additionalRows += add - 1;
            }
        }
        int rowsToSkip = CKit.round(((this.modelLineCount + additionalRows) - screenRows) * this.fraction) - start;
        if (rowsToSkip == 0) {
            newLineNumber = this.originalTarget;
            newGlyphIndex = 0;
        } else {
            this.vflow.getModelLineCount();
            int lineix = start;
            int gix = 0;
            while (true) {
                if (rowsToSkip <= 0) {
                    break;
                }
                FlowLine fline = this.vflow.getTextLine(lineix);
                WrapInfo wr = this.vflow.getWrapInfo(fline);
                int wrapRowCount = wr.getWrapRowCount();
                if (rowsToSkip >= wrapRowCount) {
                    rowsToSkip -= wrapRowCount;
                    lineix++;
                } else {
                    gix = wr.getGlyphIndexForRow(rowsToSkip);
                    break;
                }
            }
            newLineNumber = lineix;
            newGlyphIndex = gix;
            log.trace("ori=%d add=%d frac=%f start=%d skip=%d res=%d,%d", Integer.valueOf(this.originalTarget), Integer.valueOf(additionalRows), Double.valueOf(this.fraction), Integer.valueOf(start), Integer.valueOf(rowsToSkip), Integer.valueOf(newLineNumber), Integer.valueOf(gix));
        }
        return new GlyphPos(newLineNumber, newGlyphIndex);
    }
}
