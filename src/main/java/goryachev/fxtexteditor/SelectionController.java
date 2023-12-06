package goryachev.fxtexteditor;

import goryachev.fx.FxObject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SelectionController.class */
public class SelectionController {
    private final FxObject<SelectionSegment> segment = new FxObject<>(null);
    private final FxObject<EditorSelection> selectionProperty = new FxObject<>(EditorSelection.EMPTY);
    private Marker anchor;
    private SelectionSegment originalSelection;

    public ReadOnlyObjectProperty<EditorSelection> selectionProperty() {
        return this.selectionProperty.getReadOnlyProperty();
    }

    public EditorSelection getSelection() {
        return this.selectionProperty.get();
    }

    public void clear() {
        this.segment.set(null);
        this.originalSelection = null;
    }

    public boolean isSelected(Marker pos) {
        SelectionSegment s = this.segment.get();
        if (s != null && s.contains(pos)) {
            return true;
        }
        return false;
    }

    public boolean isSelected(int line, int pos) {
        SelectionSegment s = this.segment.get();
        if (s != null && s.contains(line, pos)) {
            return true;
        }
        return false;
    }

    public boolean isCaretLine(int line) {
        SelectionSegment s;
        if (line >= 0 && (s = this.segment.get()) != null && s.isCaretLine(line)) {
            return true;
        }
        return false;
    }

    public void setSelection(Marker anchor, Marker caret) {
        clear();
        addSelectionSegment(anchor, caret);
    }

    public void addSelectionSegment(Marker anchor, Marker caret) {
        addSelectionSegment(new SelectionSegment(anchor, caret));
    }

    public void addSelectionSegment(SelectionSegment seg) {
        mergeSegments(seg);
        this.originalSelection = null;
    }

    public void setSelection(Marker m) {
        setSelection(m, m);
    }

    public void clearAndExtendLastSegment(Marker pos) {
        if (this.anchor == null) {
            this.anchor = pos;
        }
        setSelection(this.anchor, pos);
    }

    public void setAnchor(Marker p) {
        this.anchor = p;
        this.originalSelection = this.segment.get();
    }

    public void extendLastSegment(Marker pos) {
        if (this.anchor == null) {
            this.anchor = pos;
        }
        SelectionSegment seg = new SelectionSegment(this.anchor, pos);
        mergeSegments(seg);
    }

    public ReadOnlyProperty<SelectionSegment> selectionSegmentProperty() {
        return this.segment.getReadOnlyProperty();
    }

    public SelectionSegment getSelectedSegment() {
        return this.segment.get();
    }

    protected void mergeSegments(SelectionSegment seg) {
        SelectionSegment rv;
        if (seg == null) {
            return;
        }
        if (this.originalSelection == null) {
            this.originalSelection = this.segment.get();
        }
        if (this.originalSelection == null) {
            rv = seg;
        } else if (seg.overlaps(this.originalSelection)) {
            rv = seg.merge(this.originalSelection);
        } else {
            rv = seg;
        }
        this.segment.set(rv);
    }

    public void commitSelection() {
        this.originalSelection = null;
        EditorSelection es = new EditorSelection(this.segment.get());
        this.selectionProperty.set(es);
    }

    public void refresh() {
        SelectionSegment seg = getSelectedSegment();
        if (seg != null) {
            SelectionSegment seg2 = seg.copy();
            this.segment.set(seg2);
            this.selectionProperty.set(new EditorSelection(seg2));
        }
    }
}
