package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ByteArrayClassLoader.class */
public class ByteArrayClassLoader extends ClassLoader {
    public Class<?> load(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
