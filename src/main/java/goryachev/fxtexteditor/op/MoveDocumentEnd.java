package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.internal.FlowLine;
import goryachev.fxtexteditor.internal.NavigationAction;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveDocumentEnd.class */
public class MoveDocumentEnd extends NavigationAction {
    public MoveDocumentEnd(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int line = vflow().getModelLineCount() - 1;
        if (line < 0) {
            line = 0;
        }
        FlowLine fline = vflow().getTextLine(line);
        int pos = fline.getTextLength();
        setPhantomColumn(line, pos);
        return editor().newMarker(line, pos);
    }
}
