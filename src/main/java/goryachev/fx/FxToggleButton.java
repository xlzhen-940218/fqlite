package goryachev.fx;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxToggleButton.class */
public class FxToggleButton extends ToggleButton {
    public FxToggleButton(String text) {
        super(text);
    }

    public FxToggleButton(String text, Property<Boolean> prop) {
        super(text);
        selectedProperty().bindBidirectional(prop);
    }

    public FxToggleButton(String text, FxAction a) {
        super(text);
        a.attach(this);
    }

    public FxToggleButton(String text, String tooltip, Property<Boolean> prop) {
        super(text);
        selectedProperty().bindBidirectional(prop);
        FX.setTooltip(this, tooltip);
    }

    public FxToggleButton(String text, String tooltip) {
        super(text);
        FX.setTooltip(this, tooltip);
    }

    public FxToggleButton(String text, String tooltip, FxAction a) {
        super(text);
        a.attach(this);
        FX.setTooltip(this, tooltip);
    }

    public FxToggleButton(String text, Node graphic) {
        super(text, graphic);
    }
}
