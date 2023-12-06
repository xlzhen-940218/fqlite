package goryachev.common.util;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SequenceNumbers.class */
public class SequenceNumbers {
    private static AtomicLong number = new AtomicLong();

    public static long get() {
        return number.getAndIncrement();
    }
}
