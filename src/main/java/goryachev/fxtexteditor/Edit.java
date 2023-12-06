package goryachev.fxtexteditor;

import goryachev.common.util.CKit;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/Edit.class */
public class Edit {
    private final int minLine;
    private final int minPos;
    private final int maxLine;
    private final int maxPos;
    private final boolean caretAtMin;
    private final Object text;

    public Edit(int minLine, int minPos, int maxLine, int maxPos, boolean caretAtMin, Object text) {
        this.minLine = minLine;
        this.minPos = minPos;
        this.maxLine = maxLine;
        this.maxPos = maxPos;
        this.caretAtMin = caretAtMin;
        this.text = text;
    }

    public static Edit create(SelectionSegment seg, Object text) {
        if (!(text instanceof String) && !(text instanceof String[])) {
            throw new Error("?" + CKit.className(text));
        }
        int minLine = seg.getMinLine();
        int minPos = seg.getMinCharIndex();
        int maxLine = seg.getMaxLine();
        int maxPos = seg.getMaxCharIndex();
        boolean caretAtMin = seg.isCaretAtMin();
        return new Edit(minLine, minPos, maxLine, maxPos, caretAtMin, text);
    }

    public boolean isText() {
        return this.text instanceof String;
    }

    public boolean isTextLines() {
        return this.text instanceof String[];
    }

    public String getText() {
        return (String) this.text;
    }

    public String[] getTextLines() {
        return (String[]) this.text;
    }

    public int getMinLine() {
        return this.minLine;
    }

    public int getMaxLine() {
        return this.maxLine;
    }

    public int getMinCharIndex() {
        return this.minPos;
    }

    public int getMaxCharIndex() {
        return this.maxPos;
    }

    public boolean isOnSameLine() {
        return this.minLine == this.maxLine;
    }
}
