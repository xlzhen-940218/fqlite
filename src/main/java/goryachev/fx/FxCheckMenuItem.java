package goryachev.fx;

import javafx.beans.property.Property;
import javafx.scene.control.CheckMenuItem;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxCheckMenuItem.class */
public class FxCheckMenuItem extends CheckMenuItem {
    public FxCheckMenuItem(String text) {
        super(text);
    }

    public FxCheckMenuItem(String text, FxAction a) {
        super(text);
        a.attach(this);
    }

    public FxCheckMenuItem(String text, Property<Boolean> p) {
        super(text);
        selectedProperty().bindBidirectional(p);
    }

    public FxCheckMenuItem(String text, GlobalBooleanProperty op) {
        super(text);
        selectedProperty().bindBidirectional(op);
    }
}
