package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveDocumentStart.class */
public class MoveDocumentStart extends NavigationAction {
    public MoveDocumentStart(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        editor().setOrigin(0);
        setPhantomColumn(0);
        return editor().newMarker(0, 0);
    }
}
