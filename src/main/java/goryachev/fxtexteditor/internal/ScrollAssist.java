package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.VFlow;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/ScrollAssist.class */
public class ScrollAssist {
    public static final int FRAME_SIZE = 64;
    private final long additionalTopRows;
    private final long additionalRows;

    protected ScrollAssist(long additionalTopRows, long additionalRows) {
        this.additionalTopRows = additionalTopRows;
        this.additionalRows = additionalRows;
    }

    public long getAdditionalTopRows() {
        return this.additionalTopRows;
    }

    public long getAdditionalRows() {
        return this.additionalRows;
    }

    public static ScrollAssist create(VFlow vflow, int startLine, int startWrapRow) {
        int lineCount = vflow.getModelLineCount();
        int screenRowCount = vflow.getScreenRowCount();
        int frameSize = Math.max(64, screenRowCount);
        int start = startLine - frameSize;
        if (start < 0) {
            start = 0;
        }
        int end = start + frameSize + frameSize + screenRowCount;
        if (end >= lineCount) {
            int d = end - lineCount;
            end = lineCount - 1;
            start -= d;
            if (start < 0) {
                start = 0;
            }
        }
        long additionalRows = 0;
        long additionalTopRows = 0;
        for (int line = start; line <= end; line++) {
            WrapInfo wr = vflow.getWrapInfo(line);
            int ct = wr.getWrapRowCount();
            if (ct > 1) {
                int ct2 = ct - 1;
                if (line < startLine) {
                    additionalRows += ct2;
                    additionalTopRows += ct2;
                } else if (line == startLine) {
                    additionalRows += ct2;
                    additionalTopRows += Math.max(0, startWrapRow - 1);
                } else {
                    additionalRows += ct2;
                }
            }
        }
        return new ScrollAssist(additionalTopRows, additionalRows);
    }
}
