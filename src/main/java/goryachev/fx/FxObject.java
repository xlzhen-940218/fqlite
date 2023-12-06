package goryachev.fx;

import javafx.beans.property.ReadOnlyObjectWrapper;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxObject.class */
public class FxObject<T> extends ReadOnlyObjectWrapper<T> {
    public FxObject(T initialValue) {
        super(initialValue);
    }

    public FxObject() {
    }
}
