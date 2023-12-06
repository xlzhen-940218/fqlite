package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/HeaderConstrain.class */
public class HeaderConstrain implements Constraint {
    int min;
    int max;

    public HeaderConstrain(int length) {
        this.min = 1;
        this.max = Integer.MAX_VALUE;
        this.min = length;
        this.max = length;
    }

    public HeaderConstrain(int min, int max) {
        this.min = 1;
        this.max = Integer.MAX_VALUE;
        this.min = min;
        this.max = max;
    }

    @Override // fqlite.pattern.Constraint
    public boolean match(int value) {
        if (value >= this.min && value <= this.max) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        if (this.min < 10) {
            s.append("0");
        }
        s.append(this.min + "..");
        if (this.max < 10) {
            s.append("0");
        }
        s.append(this.max);
        return s.toString();
    }
}
