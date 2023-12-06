package fqlite.pattern;

import java.util.ArrayList;
import java.util.List;

/* loaded from: fqlite_next.jar:fqlite/pattern/HeaderPattern.class */
public class HeaderPattern {
    List<Constraint> pattern = new ArrayList();

    public Constraint get(int idx) {
        return this.pattern.get(idx);
    }

    public void change2RowID(int idx) {
        this.pattern.set(idx, new ZeroConstrain());
    }

    public void add(Constraint c) {
        this.pattern.add(c);
    }

    public int size() {
        return this.pattern.size();
    }

    public void addZeroConstraint() {
        this.pattern.add(new ZeroConstrain());
    }

    public void addHeaderConstraint(int min, int max) {
        this.pattern.add(new HeaderConstrain(min, max));
    }

    public void addIntegerConstraint() {
        this.pattern.add(new IntegerConstraint(false));
    }

    public void addStringConstraint() {
        this.pattern.add(new StringConstrain());
    }

    public void addStringConstraint(int maxlength) {
        this.pattern.add(new StringConstrain(maxlength));
    }

    public void addNumericConstraint() {
        this.pattern.add(new NumericConstrain());
    }

    public void addBLOBConstraint() {
        this.pattern.add(new BLOBConstrain());
    }

    public void addFloatingConstraint() {
        this.pattern.add(new FloatingConstrain());
    }

    public String toString() {
        String result = "[";
        for (Constraint c : this.pattern) {
            result = result + c.toString() + "|";
        }
        return result + "]";
    }
}
