package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/StringConstrain.class */
public class StringConstrain implements Constraint {
    int max;

    public StringConstrain() {
        this.max = Integer.MAX_VALUE;
    }

    public StringConstrain(int max) {
        this.max = Integer.MAX_VALUE;
        this.max = (max * 2) + 13;
    }

    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value == 0) {
            return true;
        }
        if (value > 13 && value % 2 != 0 && value <= this.max) {
            return true;
        }
        return false;
    }

    public String toString() {
        return ">=13";
    }
}
