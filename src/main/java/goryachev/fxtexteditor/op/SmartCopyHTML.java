package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/SmartCopyHTML.class */
public class SmartCopyHTML extends EditorAction {
    public SmartCopyHTML(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        editor().smartCopyHTML();
    }
}
