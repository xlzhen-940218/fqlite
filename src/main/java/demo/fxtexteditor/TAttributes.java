package demo.fxtexteditor;

import goryachev.common.util.CList;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/TAttributes.class */
public class TAttributes {
    private CList<TSegment> segments;

    public int size() {
        return this.segments.size();
    }

    public void addSegment(TSegment seg) {
        if (this.segments == null) {
            this.segments = new CList<>();
        }
        this.segments.add(seg);
    }

    public TSegment getSegmentAt(int pos) {
        if (this.segments != null) {
            int low = 0;
            int high = this.segments.size() - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                TSegment seg = this.segments.get(mid);
                if (seg.end <= pos) {
                    low = mid + 1;
                } else if (seg.start > pos) {
                    high = mid - 1;
                } else {
                    return seg;
                }
            }
            return null;
        }
        return null;
    }
}
