package goryachev.common.util;

import goryachev.common.log.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Reflector.class */
public class Reflector {
    protected static final Log log = Log.get("Reflector");

    public static CMethod method(Class c, String name, Class<?>... clsArr) {
        try {
            Method m = c.getDeclaredMethod(name, clsArr);
            m.setAccessible(true);
            return new CMethod(m);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static CField field(Class c, String name) {
        try {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            return new CField(f);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static <T> T invoke(Class<T> returnType, String methodName, Object object, Object... args) {
        if (object == null) {
            return null;
        }
        try {
            Class c = object.getClass();
            int sz = args.length;
            Class[] argTypes = new Class[sz];
            for (int i = 0; i < sz; i++) {
                Object a = args[i];
                argTypes[i] = a == null ? null : a.getClass();
            }
            try {
                Method m = c.getDeclaredMethod(methodName, argTypes);
                T t = (T) m.invoke(object, args);
                if (t != null) {
                    if (returnType.isAssignableFrom(t.getClass())) {
                        return t;
                    }
                    return null;
                }
                return null;
            } catch (Exception e) {
                log.error((Throwable) e);
                return null;
            }
        } catch (Exception e2) {
            log.error((Throwable) e2);
            return null;
        }
    }
}
