package goryachev.common.io;

import goryachev.common.util.CList;
import java.util.Collection;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DContainer.class */
public class DContainer {
    private CList<Object> data;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DContainer$ObjectMarker.class */
    public static class ObjectMarker {
        public final String type;

        public ObjectMarker(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public String toString() {
            return "Object:" + getType();
        }
    }

    public DContainer(DContainer x) {
        this.data = new CList<>((Collection<? extends Object>) x.data);
    }

    public DContainer() {
        this.data = new CList<>();
    }

    public Object clone() {
        return copyObjectList();
    }

    public DContainer copyObjectList() {
        return new DContainer(this);
    }

    public Object[] toArray() {
        return this.data.toArray();
    }

    public int size() {
        return this.data.size();
    }

    public Object get(int ix) {
        return this.data.get(ix);
    }

    public void add(Object x) {
        this.data.add(x);
    }
}
