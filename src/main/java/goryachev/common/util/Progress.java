package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Progress.class */
public class Progress {
    public static final Progress UNKNOWN = new Progress(0.0d);
    public static final Progress DONE = new Progress(1.0d);
    private double progress;

    public Progress(double progress) {
        this.progress = progress;
    }

    public Progress(int count, int total) {
        this.progress = total == 0 ? 0.0d : count / total;
    }

    public Progress(long count, long total) {
        this.progress = total == 0 ? 0.0d : count / total;
    }

    public double getProgress() {
        return this.progress;
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        return (x instanceof Progress) && this.progress == ((Progress) x).progress;
    }

    public int hashCode() {
        int h = FH.hash(0, Progress.class);
        return FH.hash(h, this.progress);
    }

    public String getPercentString() {
        return getPercentString(2);
    }

    public String getPercentString(int significantDigits) {
        return CKit.getPercentString(this.progress, significantDigits);
    }

    public String toString() {
        return "Progress:" + this.progress;
    }
}
