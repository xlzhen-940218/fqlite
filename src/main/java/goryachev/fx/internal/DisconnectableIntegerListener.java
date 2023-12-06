package goryachev.fx.internal;

import goryachev.common.util.Disconnectable;
import java.util.function.IntConsumer;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/DisconnectableIntegerListener.class */
public class DisconnectableIntegerListener implements ChangeListener<Number>, Disconnectable {
    private final ReadOnlyIntegerProperty prop;
    private final IntConsumer onChange;

    public DisconnectableIntegerListener(ReadOnlyIntegerProperty prop, IntConsumer onChange) {
        this.prop = prop;
        this.onChange = onChange;
        prop.addListener(this);
    }

    @Override // goryachev.common.util.Disconnectable
    public void disconnect() {
        this.prop.removeListener(this);
    }

    @Override // javafx.beans.value.ChangeListener
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        int v = newValue.intValue();
        this.onChange.accept(v);
    }
}
