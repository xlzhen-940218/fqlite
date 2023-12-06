package goryachev.fx;

import javafx.beans.property.SimpleIntegerProperty;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxInt.class */
public class FxInt extends SimpleIntegerProperty {
    public FxInt(int initialValue) {
        super(initialValue);
    }

    public FxInt() {
    }

    public void set(Number n) {
        if (n != null) {
            set(n.intValue());
        }
    }
}
