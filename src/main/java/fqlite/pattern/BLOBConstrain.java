package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/BLOBConstrain.class */
public class BLOBConstrain implements Constraint {
    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value % 2 == 0 && value > 12) {
            return true;
        }
        return false;
    }

    public String toString() {
        return ">=12";
    }
}
