package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/CopyPlainText.class */
public class CopyPlainText extends EditorAction {
    public CopyPlainText(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        editor().copyPlainText();
    }
}
