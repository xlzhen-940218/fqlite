package goryachev.fx;

import javafx.scene.control.CheckBox;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxCheckBox.class */
public class FxCheckBox extends CheckBox {
    public FxCheckBox(String text, boolean selected) {
        super(text);
        setSelected(selected);
    }

    public FxCheckBox(boolean selected) {
        setSelected(selected);
    }

    public FxCheckBox(String text) {
        super(text);
    }

    public FxCheckBox() {
    }
}
