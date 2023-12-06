package goryachev.fxtexteditor.op;

import goryachev.common.log.Log;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.WrapPos;
import goryachev.fxtexteditor.internal.NavigationAction;
import goryachev.fxtexteditor.internal.WrapInfo;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/op/MoveUp.class */
public class MoveUp extends NavigationAction {
    protected static final Log log = Log.get("MoveUp");

    public MoveUp(FxTextEditor ed) {
        super(ed);
    }

    @Override // goryachev.fxtexteditor.internal.NavigationAction
    protected Marker move(Marker m) {
        int pos = m.getCharIndex();
        int line = m.getLine();
        int col = updatePhantomColumn(line, pos);
        WrapInfo wr = wrapInfo(line);
        int wrapRow = wr.getWrapRowForCharIndex(pos);
        WrapPos wp = vflow().advance(line, wrapRow, -1);
        int newLine = wp.getLine();
        int newWrapRow = wp.getRow();
        WrapInfo wr2 = wrapInfo(newLine);
        int newPos = wr2.getCharIndexForColumn(newWrapRow, col);
        log.debug("col=%d line=%d pos=%d", Integer.valueOf(col), Integer.valueOf(newLine), Integer.valueOf(newPos));
        return editor().newMarker(newLine, newPos);
    }
}
