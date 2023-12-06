package goryachev.fx;

import javafx.beans.property.IntegerPropertyBase;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalIntProperty.class */
public class GlobalIntProperty extends IntegerPropertyBase implements GlobalProperty<Number> {
    private final String key;

    public GlobalIntProperty(String key, int defaultValue) {
        super(defaultValue);
        this.key = key;
        GlobalProperties.add(this);
    }

    public GlobalIntProperty(String key) {
        this(key, 0);
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
    public StringConverter<Number> getConverter() {
        return Converters.NUMBER_INT();
    }
}
