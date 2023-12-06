package goryachev.common.util;

import java.util.Collection;
import java.util.HashSet;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CSet.class */
public class CSet<T> extends HashSet<T> {
    private static final int DEFAULT_SIZE = 64;

    public CSet(int size) {
        super(size);
    }

    public CSet() {
        super(64);
    }

    public CSet(Collection<T> items) {
        super(items);
    }

    public CSet(T[] tArr) {
        addAll(tArr);
    }

    public void addAll(T[] tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                add(t);
            }
        }
    }

    public void removeAll(T[] tArr) {
        if (tArr != null) {
            for (T t : tArr) {
                remove(t);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends T> items) {
        if (items != null) {
            return super.addAll(items);
        }
        return false;
    }

    public CList<T> asList() {
        return new CList<>((Collection) this);
    }
}
