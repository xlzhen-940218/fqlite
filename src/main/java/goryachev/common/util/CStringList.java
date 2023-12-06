package goryachev.common.util;

import java.util.Arrays;
import java.util.Collection;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CStringList.class */
public class CStringList extends CList<String> {
    public CStringList(int initialCapacity) {
        super(initialCapacity);
    }

    public CStringList() {
    }

    public CStringList(Collection<String> c) {
        super((Collection) c);
    }

    public CStringList(String[] a) {
        super((Collection) Arrays.asList(a));
    }

    public String[] toStringArray() {
        return (String[]) toArray(new String[size()]);
    }
}
