package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SW.class */
public class SW {
    private long start;

    public SW() {
        reset();
    }

    public void reset() {
        this.start = System.nanoTime();
    }

    public long getElapsedTimeNano() {
        return System.nanoTime() - this.start;
    }

    public long getElapsedTimeMilli() {
        return getElapsedTimeNano() / 1000000;
    }

    public String toString() {
        long elapsed = getElapsedTimeNano();
        return CKit.formatTimePeriod(elapsed / 1000000);
    }
}
