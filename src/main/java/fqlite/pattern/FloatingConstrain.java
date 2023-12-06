package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/FloatingConstrain.class */
public class FloatingConstrain implements Constraint {
    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value == 7) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "07";
    }
}
