package goryachev.common.util;

import java.lang.reflect.Field;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CField.class */
public final class CField<T> {
    private final Field field;

    public CField(Field f) {
        this.field = f;
    }

    public T get(Object obj) {
        if (this.field != null) {
            try {
                return (T) this.field.get(obj);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void set(Object obj, T value) {
        if (this.field != null) {
            try {
                this.field.set(obj, value);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
