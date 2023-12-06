package goryachev.fx;

import javafx.scene.Node;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxRadioToggleButton.class */
public class FxRadioToggleButton extends FxToggleButton {
    public FxRadioToggleButton(String text, String tooltip) {
        super(text, tooltip);
    }

    public FxRadioToggleButton(String text, Node graphic) {
        super(text, graphic);
    }

    @Override // javafx.scene.control.ToggleButton, javafx.scene.control.ButtonBase
    public void fire() {
        if (getToggleGroup() == null || !isSelected()) {
            super.fire();
        }
    }
}
