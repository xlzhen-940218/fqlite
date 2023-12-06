package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/ZeroConstrain.class */
public class ZeroConstrain implements Constraint {
    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "00";
    }
}
