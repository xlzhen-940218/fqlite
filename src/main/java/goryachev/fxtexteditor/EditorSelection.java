package goryachev.fxtexteditor;

import goryachev.common.util.CKit;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/EditorSelection.class */
public class EditorSelection {
    public static final EditorSelection EMPTY = createEmpty();
    private final SelectionSegment segment;

    public EditorSelection(SelectionSegment segment) {
        this.segment = segment;
    }

    public String toString() {
        return CKit.toStringOrNull(this.segment);
    }

    private static EditorSelection createEmpty() {
        return new EditorSelection(new SelectionSegment(Marker.ZERO, Marker.ZERO, false));
    }

    public SelectionSegment getSegment() {
        return this.segment;
    }

    public boolean isEmpty() {
        return this.segment == null;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public EditorSelection getSelection() {
        return new EditorSelection(this.segment);
    }

    public Marker getCaret() {
        if (isEmpty()) {
            return null;
        }
        return this.segment.getCaret();
    }

    public Marker getAnchor() {
        if (isEmpty()) {
            return null;
        }
        return this.segment.getAnchor();
    }
}
