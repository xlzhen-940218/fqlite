package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.ITextLine;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/TLayout.class */
public class TLayout {
    private final int width;
    private final ITextLine[] lines;
    private final int[] offsets;
    private boolean[] selection;
    private boolean[] carets;

    public TLayout(int width, ITextLine[] lines, int[] offsets) {
        this.width = width;
        this.lines = lines;
        this.offsets = offsets;
    }
}
