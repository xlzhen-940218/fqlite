package goryachev.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/LoadStatus.class */
public class LoadStatus {
    public static final LoadStatus UNKNOWN = new LoadStatus(1.0d, true, false);
    public static final LoadStatus COMPLETE = new LoadStatus(1.0d, false, true);
    private final double progress;
    private final boolean loading;
    private final boolean valid;

    public LoadStatus(double progress, boolean loading, boolean valid) {
        this.progress = progress;
        this.loading = loading;
        this.valid = valid;
    }

    public double getProgress() {
        return this.progress;
    }

    public boolean isLoading() {
        return this.loading;
    }

    public boolean isValid() {
        return this.valid;
    }
}
