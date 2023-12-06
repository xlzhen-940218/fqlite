package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.FlowLine;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveRight.class */
public class MoveRight extends NavigationAction {
    public MoveRight(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int gix;
        int pos = m.getCharIndex();
        int line = m.getLine();
        FlowLine fline = vflow().getTextLine(line);
        int gix2 = fline.getGlyphIndex(pos);
        if (gix2 < fline.getGlyphCount()) {
            gix = gix2 + 1;
        } else if (line < vflow().getModelLineCount() - 1) {
            line++;
            gix = 0;
        } else {
            line = vflow().getModelLineCount() - 1;
            gix = fline.getGlyphCount();
        }
        int pos2 = fline.getCharIndex(gix);
        setPhantomColumn(line, pos2);
        return editor().newMarker(line, pos2);
    }
}
