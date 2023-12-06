package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/CopyRTF.class */
public class CopyRTF extends EditorAction {
    public CopyRTF(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        editor().copyRTF();
    }
}
