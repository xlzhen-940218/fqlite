package goryachev.log.config;

import goryachev.common.util.CTask;
import goryachev.common.util.SystemTask;
import java.io.File;
import java.util.function.Consumer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/log/config/FileMonitor.class */
public class FileMonitor {
    protected final File file;
    protected final long period;
    protected final Consumer<File> reloader;
    private long last;
    private boolean disabled;

    public FileMonitor(File f, long period, Consumer<File> reloader) {
        this.file = f;
        this.period = period;
        this.reloader = reloader;
    }

    public void cancel() {
        this.disabled = true;
    }

    public void start() {
        this.last = this.file.lastModified();
        SystemTask.schedule(this.period, this.period, this::check);
    }

    protected void check() {
        if (this.disabled) {
            return;
        }
        long t = this.file.lastModified();
        if (t != this.last) {
            this.last = t;
            triggerUpdate();
        }
    }

    protected void triggerUpdate() {
        CTask.submit(() -> {
            this.reloader.accept(this.file);
            SystemTask.schedule(this.period, this::check);
        });
    }
}
