package goryachev.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CMethod.class */
public class CMethod<T> {
    private final Method method;

    public CMethod(Method m) {
        this.method = m;
    }

    public T invoke(Object obj, Object... args) {
        try {
            return (T) this.method.invoke(obj, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public T invokeStatic(Object... args) {
        try {
            return (T) this.method.invoke(null, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
