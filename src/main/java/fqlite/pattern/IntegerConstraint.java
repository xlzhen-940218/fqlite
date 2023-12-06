package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/IntegerConstraint.class */
public class IntegerConstraint implements Constraint {
    int min;
    int max = 6;

    public IntegerConstraint(boolean notNull) {
        this.min = 0;
        if (notNull) {
            this.min = 1;
        }
    }

    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value <= this.max && value >= this.min) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "0" + this.min + "..06";
    }
}
