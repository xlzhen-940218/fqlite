package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveDocumentEndAtPos0.class */
public class MoveDocumentEndAtPos0 extends NavigationAction {
    public MoveDocumentEndAtPos0(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int line = vflow().getModelLineCount() - 1;
        if (line < 0) {
            line = 0;
        }
        setPhantomColumn(line, 0);
        return editor().newMarker(line, 0);
    }
}
