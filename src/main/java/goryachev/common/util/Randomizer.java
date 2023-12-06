package goryachev.common.util;

import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Randomizer.class */
public class Randomizer {
    private static final Random random = new Random();

    protected static synchronized int nextInt(int n) {
        return random.nextInt(n);
    }

    public static int getInt(int center, int variance) {
        return (center - variance) + nextInt(variance + variance);
    }

    public static int getInt(int center) {
        return getInt(center, center / 2);
    }
}
