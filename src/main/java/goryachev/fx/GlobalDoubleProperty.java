package goryachev.fx;

import javafx.beans.property.DoublePropertyBase;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalDoubleProperty.class */
public class GlobalDoubleProperty extends DoublePropertyBase implements GlobalProperty<Number> {
    private final String key;

    public GlobalDoubleProperty(String key, double defaultValue) {
        super(defaultValue);
        this.key = key;
        GlobalProperties.add(this);
    }

    public GlobalDoubleProperty(String key) {
        this(key, 0.0d);
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
        return Converters.NUMBER_DOUBLE();
    }
}
