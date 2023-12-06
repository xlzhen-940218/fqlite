package goryachev.fx;

import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxFormatter.class */
public abstract class FxFormatter extends StringConverter<Object> {
    @Override // javafx.util.StringConverter
    public abstract String toString(Object obj);

    @Override // javafx.util.StringConverter
    public Object fromString(String string) {
        throw new Error("FxFormatter: fromString not supported");
    }

    public String format(Object x) {
        return toString(x);
    }
}
