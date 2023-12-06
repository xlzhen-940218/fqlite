package goryachev.fxtexteditor.op;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/SelectAll.class */
public class SelectAll extends EditorAction {
    public SelectAll(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.EditorAction
    protected void action() {
        int ix = editor().getLineCount();
        if (ix > 0) {
            int ix2 = ix - 1;
            String s = model().getPlainText(ix2);
            Marker beg = markers().newMarker(0, 0);
            Marker end = markers().newMarker(ix2, Math.max(0, s == null ? 0 : s.length()));
            selector().setSelection(beg, end);
            selector().commitSelection();
            vflow().scrollCaretToView();
        }
    }
}
