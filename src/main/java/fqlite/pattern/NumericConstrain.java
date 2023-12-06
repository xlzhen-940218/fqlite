package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/NumericConstrain.class */
public class NumericConstrain implements Constraint {
    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        return true;
    }

    public String toString() {
        return "00..255";
    }
}
