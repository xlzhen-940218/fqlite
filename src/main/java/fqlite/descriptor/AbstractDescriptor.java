package fqlite.descriptor;

/* loaded from: fqlite_next.jar:fqlite/descriptor/AbstractDescriptor.class */
public abstract class AbstractDescriptor {
    public boolean ROWID = true;
    public int rowid_col = -1;

    public abstract String getName();
}
