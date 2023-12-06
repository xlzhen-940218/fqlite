package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/SmartCopyRTF.class */
public class SmartCopyRTF extends EditorAction {
    public SmartCopyRTF(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        editor().smartCopyRTF();
    }
}
