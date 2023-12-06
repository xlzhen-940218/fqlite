package goryachev.fx;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxMenuItem.class */
public class FxMenuItem extends MenuItem {
    public FxMenuItem(String text, Node icon, FxAction a) {
        super(text);
        setGraphic(icon);
        if (a == null) {
            setDisable(true);
        } else {
            a.attach(this);
        }
    }

    public FxMenuItem(Node icon, FxAction a) {
        setGraphic(icon);
        if (a == null) {
            setDisable(true);
        } else {
            a.attach(this);
        }
    }

    public FxMenuItem(String text, FxAction a) {
        super(text);
        if (a == null) {
            setDisable(true);
        } else {
            a.attach(this);
        }
    }

    public FxMenuItem(String text, Runnable r) {
        super(text);
        new FxAction(r).attach(this);
    }

    public FxMenuItem(String text) {
        super(text);
        setDisable(true);
    }
}
