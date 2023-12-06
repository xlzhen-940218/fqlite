package goryachev.fx;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxSplitPane.class */
public class FxSplitPane extends SplitPane {
    public FxSplitPane(Orientation ori, Node... items) {
        super(items);
        setOrientation(ori);
    }

    public FxSplitPane(Node... items) {
        super(items);
    }

    public FxSplitPane() {
    }

    public void setNoResize(Node n) {
        FX.preventSplitPaneResizing(n);
    }
}
