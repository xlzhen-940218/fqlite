package goryachev.fx;

import javafx.scene.Node;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FlatButton.class */
public class FlatButton extends FxButton {
    public static final CssStyle STYLE = new CssStyle("FlatButton_STYLE");

    public FlatButton(String text, FxAction a) {
        super(text, a);
        FX.style(this, STYLE);
    }

    public FlatButton(String text, Runnable action) {
        super(text, action);
        FX.style(this, STYLE);
    }

    public FlatButton(String text) {
        super(text);
        FX.style(this, STYLE);
    }

    public FlatButton(Node icon) {
        super(icon);
        FX.style(this, STYLE);
    }

    public FlatButton(Node icon, FxAction a) {
        super(icon, a);
        FX.style(this, STYLE);
    }

    public FlatButton(Node icon, Runnable action) {
        super(icon, action);
        FX.style(this, STYLE);
    }
}
