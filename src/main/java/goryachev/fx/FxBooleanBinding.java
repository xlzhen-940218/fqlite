package goryachev.fx;

import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxBooleanBinding.class */
public abstract class FxBooleanBinding extends BooleanBinding {
    @Override // javafx.beans.binding.BooleanBinding
    protected abstract boolean computeValue();

    public FxBooleanBinding(Observable... dependencies) {
        bind(dependencies);
    }
}
