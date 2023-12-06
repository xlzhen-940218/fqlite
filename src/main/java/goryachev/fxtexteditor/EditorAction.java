package goryachev.fxtexteditor;

import goryachev.fx.FxAction;
import goryachev.fxtexteditor.internal.Markers;
import goryachev.fxtexteditor.internal.WrapInfo;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/EditorAction.class */
public abstract class EditorAction extends FxAction {
    protected final FxTextEditor editor;

    protected abstract void action();

    public EditorAction(FxTextEditor ed) {
        this.editor = ed;
        setOnAction(this::action);
    }

    public final FxTextEditor editor() {
        return this.editor;
    }

    public final VFlow vflow() {
        return this.editor.vflow;
    }

    public final SelectionController selector() {
        return this.editor.selector;
    }

    public final FxTextEditorModel model() {
        return this.editor.getModel();
    }

    public final boolean isWrapLines() {
        return vflow().isWrapLines();
    }

    public final EditorSelection selection() {
        return this.editor.selector.getSelection();
    }

    public final Markers markers() {
        return this.editor.markers;
    }

    public final WrapInfo wrapInfo(int line) {
        return vflow().getWrapInfo(line);
    }
}
