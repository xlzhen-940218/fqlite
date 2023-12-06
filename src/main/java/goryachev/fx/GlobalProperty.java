package goryachev.fx;

import javafx.beans.property.Property;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalProperty.class */
public interface GlobalProperty<T> extends Property<T> {
    @Override // javafx.beans.property.ReadOnlyProperty
    String getName();

    StringConverter<T> getConverter();
}
