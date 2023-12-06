package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import java.util.function.BiConsumer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/LineSelector.class */
public class LineSelector implements BiConsumer<FxTextEditor, Marker> {
    @Override // java.util.function.BiConsumer
    public void accept(FxTextEditor ed, Marker m) {
        selectLine(ed, m);
    }

    public void selectLine(FxTextEditor ed, Marker m) {
        int endPos;
        if (m != null) {
            int line = m.getLine();
            int endLine = line + 1;
            if (endLine >= ed.getLineCount()) {
                endPos = ed.getTextLength(line);
                endLine = line;
            } else {
                endPos = 0;
            }
            ed.select(line, 0, endLine, endPos);
        }
    }
}
