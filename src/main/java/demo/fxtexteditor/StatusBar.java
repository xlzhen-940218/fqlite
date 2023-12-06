package demo.fxtexteditor;

import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import goryachev.fx.Formatters;
import goryachev.fx.FxFormatter;
import goryachev.fx.HPane;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.SelectionSegment;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/StatusBar.class */
public class StatusBar extends HPane {
    public static final CssStyle PANE = new CssStyle("StatusBar_PANE");
    public static final CssStyle LABEL = new CssStyle("StatusBar_LABEL");
    public final Label caret;
    private int totalbytes = 0;

    public StatusBar() {
        FX.style(this, PANE);
        this.caret = FX.label(LABEL);
        add(this.caret);
        fill();
    }

    public void setTotal(int total) {
        this.totalbytes = total;
    }

    public void attach(FxTextEditor ed) {
        this.caret.textProperty().bind(Bindings.createStringBinding(() -> {
            int posinline;
            SelectionSegment seg = ed.getSelectedSegment();
            if (seg == null) {
                return null;
            }
            FxFormatter fmt = Formatters.integerFormatter();
            Marker m = seg.getCaret();
            int position = m.getCharIndex();
            if (position < 33) {
                posinline = position / 2;
            } else if (position == 33) {
                posinline = 16;
            } else {
                posinline = position - 33;
            }
            return "  Offset " + ((m.getLine() * 16) + posinline) + " out of " + this.totalbytes + " bytes  | line:" + fmt.format(Integer.valueOf(m.getLine() + 1)) + "  column:" + fmt.format(Integer.valueOf(ed.getColumnAt(m) + 1)) + "  char:" + fmt.format(Integer.valueOf(m.getCharIndex()));
        }, ed.selectionSegmentProperty()));
    }
}
