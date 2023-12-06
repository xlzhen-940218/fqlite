package goryachev.fxtexteditor.internal;

import goryachev.common.log.Log;
import goryachev.common.util.WeakList;
import goryachev.fxtexteditor.Marker;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/Markers.class */
public class Markers {
    protected static final Log log = Log.get("Markers");
    private final WeakList<Marker> markers = new WeakList<>();

    public Markers(int size) {
    }

    public Marker newMarker(int lineNumber, int charIndex) {
        Marker m = new Marker(this, lineNumber, charIndex);
        this.markers.add(m);
        if (this.markers.size() > 1000000) {
            this.markers.gc();
            if (this.markers.size() > 1000000) {
                throw new Error("too many markers");
            }
        }
        return m;
    }

    public void clear() {
        this.markers.clear();
    }

    public void update(int startLine, int startPos, int startCharsAdded, int linesAdded, int endLine, int endPos, int endCharsAdded) {
        log.debug("startLine=%d, startPos=%d, startCharsAdded=%d, linesAdded=%d, endLine=%d, endPos=%d, endCharsAdded=%d", Integer.valueOf(startLine), Integer.valueOf(startPos), Integer.valueOf(startCharsAdded), Integer.valueOf(linesAdded), Integer.valueOf(endLine), Integer.valueOf(endPos), Integer.valueOf(endCharsAdded));
        log.trace("before:%s", this.markers);
        for (int i = this.markers.size() - 1; i >= 0; i--) {
            Marker m = this.markers.get(i);
            if (m == null) {
                this.markers.remove(i);
            } else if (!m.isBefore(startLine, startPos)) {
                if (m.isAfter(endLine, endPos)) {
                    if (endLine == m.getLine()) {
                        int charDelta = endCharsAdded - (endPos - startPos);
                        m.movePosition(charDelta);
                    }
                    int lineDelta = linesAdded - (endLine - startLine);
                    m.moveLine(lineDelta);
                } else if (linesAdded == 0) {
                    m.reset(endLine + linesAdded, startPos + startCharsAdded + endCharsAdded);
                } else {
                    m.reset(endLine + linesAdded, endCharsAdded);
                }
            }
        }
        log.trace("after:%s", this.markers);
    }
}
