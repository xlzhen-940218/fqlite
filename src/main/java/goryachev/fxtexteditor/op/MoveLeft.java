package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.FlowLine;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveLeft.class */
public class MoveLeft extends NavigationAction {
    public MoveLeft(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int pos = m.getCharIndex();
        int line = m.getLine();
        FlowLine fline = vflow().getTextLine(line);
        int gix = fline.getGlyphIndex(pos);
        if (gix > 0) {
            gix--;
        } else if (line > 0) {
            line--;
            fline = vflow().getTextLine(line);
            gix = fline.getGlyphCount();
        }
        int pos2 = fline.getCharIndex(gix);
        setPhantomColumn(line, pos2);
        return editor().newMarker(line, pos2);
    }
}
