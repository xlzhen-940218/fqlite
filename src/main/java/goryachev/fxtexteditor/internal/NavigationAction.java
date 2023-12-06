package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.EditorAction;
import goryachev.fxtexteditor.EditorSelection;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.SelectionSegment;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/NavigationAction.class */
public abstract class NavigationAction extends EditorAction {
    protected abstract Marker move(Marker marker);

    public NavigationAction(FxTextEditor ed) {
        super(ed);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int updatePhantomColumn(int line, int charIndex) {
        return vflow().updatePhantomColumn(line, charIndex);
    }

    protected int getPhantomColumn() {
        return vflow().getPhantomColumn();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPhantomColumn(int x) {
        vflow().setPhantomColumn(x);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPhantomColumn(int line, int charIndex) {
        vflow().setPhantomColumn(line, charIndex);
    }

    protected int getTopCellIndex() {
        return vflow().getTopCellIndex();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.fxtexteditor.EditorAction
    public void action() {
        move();
    }

    protected void move() {
        vflow().setSuppressBlink(true);
        try {
            EditorSelection sel = selection();
            SelectionSegment seg = sel.getSegment();
            if (seg == null) {
                return;
            }
            Marker from = seg.getCaret();
            Marker to = move(from);
            if (to != null) {
                selector().clear();
                selector().addSelectionSegment(to, to);
                selector().commitSelection();
            }
        } finally {
            vflow().setSuppressBlink(false);
            vflow().scrollCaretToView();
        }
    }
}
