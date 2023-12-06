package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/SmartCopy.class */
public class SmartCopy extends EditorAction {
    public SmartCopy(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        editor().smartCopy();
    }
}
