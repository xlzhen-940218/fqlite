package goryachev.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxButton.class */
public class FxButton extends Button {
    public static final CssStyle AFFIRM = new CssStyle("FxButton_AFFIRM");
    public static final CssStyle DESTRUCT = new CssStyle("FxButton_DESTRUCT");

    public FxButton(String text, FxAction a, CssStyle style) {
        super(text);
        a.attach(this);
        FX.style(this, style);
    }

    public FxButton(String text, CssStyle style) {
        super(text);
        FX.style(this, style);
    }

    public FxButton(String text, Runnable handler, CssStyle style) {
        this(text, new FxAction(handler), style);
    }

    public FxButton(String text, FxAction a) {
        super(text);
        a.attach(this);
    }

    public FxButton(String text, Runnable action) {
        this(text, new FxAction(action));
    }

    public FxButton(Node icon, Runnable action) {
        this(icon, new FxAction(action));
    }

    public FxButton(String text) {
        super(text);
    }

    public FxButton(Node icon) {
        setGraphic(icon);
    }

    public FxButton(Node icon, FxAction a) {
        setGraphic(icon);
        a.attach(this);
    }
}
