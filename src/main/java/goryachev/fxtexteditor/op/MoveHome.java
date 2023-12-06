package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveHome.class */
public class MoveHome extends NavigationAction {
    public MoveHome(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int line = m.getLine();
        setPhantomColumn(line, 0);
        return editor().newMarker(line, 0);
    }
}
