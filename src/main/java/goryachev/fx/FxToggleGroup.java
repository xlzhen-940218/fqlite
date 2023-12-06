package goryachev.fx;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxToggleGroup.class */
public class FxToggleGroup extends ToggleGroup {
    public FxToggleGroup(ToggleButton... buttons) {
        for (ToggleButton b : buttons) {
            b.setToggleGroup(this);
        }
        selectedToggleProperty().addListener((observableValue, p, c )-> {
            if (c == null) {
                p.setSelected(true);
            }
        });
        ensureSelected(buttons);
    }

    protected static void ensureSelected(ToggleButton[] buttons) {
        if (buttons.length > 0) {
            for (ToggleButton b : buttons) {
                if (b.isSelected()) {
                    return;
                }
            }
            buttons[0].setSelected(true);
        }
    }

    public FxToggleGroup() {
    }

    public void add(ToggleButton b) {
        b.setToggleGroup(this);
    }
}
