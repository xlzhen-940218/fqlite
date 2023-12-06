package goryachev.fx;

import javafx.beans.property.StringPropertyBase;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalStringProperty.class */
public class GlobalStringProperty extends StringPropertyBase implements GlobalProperty<String> {
    private final String key;

    public GlobalStringProperty(String key, String defaultValue) {
        super(defaultValue);
        this.key = key;
        GlobalProperties.add(this);
    }

    public GlobalStringProperty(String key) {
        this(key, null);
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
    public StringConverter<String> getConverter() {
        return Converters.STRING();
    }
}
