package goryachev.common.util;

import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/RandomIndexGenerator.class */
public class RandomIndexGenerator {
    public final Random random = new Random();
    private Integer[] indexes;
    private int size;

    public RandomIndexGenerator(int max) {
        this.indexes = new Integer[max];
        this.size = max;
    }

    protected int get(int ix) {
        Integer r = this.indexes[ix];
        if (r == null) {
            return ix;
        }
        return r.intValue();
    }

    public int next() {
        if (this.size > 0) {
            int ix = this.random.nextInt(this.size);
            int r = get(ix);
            this.size--;
            this.indexes[ix] = Integer.valueOf(get(this.size));
            return r;
        }
        return -1;
    }
}
