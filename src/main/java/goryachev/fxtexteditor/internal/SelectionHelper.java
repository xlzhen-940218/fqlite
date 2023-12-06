package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.SelectionSegment;
import goryachev.fxtexteditor.VFlow;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/SelectionHelper.class */
public class SelectionHelper {
    private static final int CARET = 1;
    private static final int CARET_LINE = 2;
    private static final int SELECTED = 4;

    public static int getFlags(VFlow vflow, SelectionSegment seg, int line, TextCell cell, int x) {
        if (seg == null || line < 0) {
            return 0;
        }
        int off = cell.getCaretCharIndex();
        int selOff = cell.getLeadingEdgeCharIndex();
        int flags = 0;
        if (seg.isCaretLine(line)) {
            if (vflow.getEditor().isHighlightCaretLine()) {
                flags = 0 | 2;
            }
            if (off >= 0 && seg.isCaret(line, off) && !vflow.isWrapColumn(x)) {
                flags |= 1;
            }
        }
        int ix = selOff >= 0 ? selOff : off;
        if (ix < 0) {
            ix = vflow.getTopCellIndex();
        }
        if (seg.contains(line, ix)) {
            flags |= 4;
        }
        return flags;
    }

    public static boolean isCaretLine(SelectionSegment seg, ScreenRow row) {
        if (row != null && seg != null) {
            int line = row.getLineNumber();
            if (seg.isCaretLine(line)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isCaret(int flags) {
        return (flags & 1) != 0;
    }

    public static boolean isCaretLine(int flags) {
        return (flags & 2) != 0;
    }

    public static boolean isSelected(int flags) {
        return (flags & 4) != 0;
    }
}
