package goryachev.fx;

import javafx.beans.property.BooleanPropertyBase;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalBooleanProperty.class */
public class GlobalBooleanProperty extends BooleanPropertyBase implements GlobalProperty<Boolean> {
    private final String key;

    public GlobalBooleanProperty(String key, boolean defaultValue) {
        super(defaultValue);
        this.key = key;
        GlobalProperties.add(this);
    }

    public GlobalBooleanProperty(String key) {
        this(key, false);
    }

    @Override // javafx.beans.property.ReadOnlyProperty
    public Object getBean() {
        return null;
    }

    @Override // javafx.beans.property.ReadOnlyProperty
    public String getName() {
        return this.key;
    }

    @Override // goryachev.fx.GlobalProperty
    public StringConverter<Boolean> getConverter() {
        return Converters.BOOLEAN();
    }
}
