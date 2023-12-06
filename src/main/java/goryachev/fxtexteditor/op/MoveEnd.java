package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveEnd.class */
public class MoveEnd extends NavigationAction {
    public MoveEnd(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int line = m.getLine();
        int pos = editor().getTextLength(line);
        setPhantomColumn(line, pos);
        return editor().newMarker(line, pos);
    }
}
