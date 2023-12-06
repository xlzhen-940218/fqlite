package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.Progress;
import javafx.application.Platform;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxThread.class */
public abstract class FxThread extends Thread {
    protected static final Log log = Log.get("FxThread");
    private long startTime;

    protected abstract void process() throws Throwable;

    protected abstract void processSuccess();

    protected void onProcessEnd() {
    }

    protected void processError(Throwable e) {
        log.error(e);
    }

    public Progress getProgress() {
        return null;
    }

    public FxThread(String name, int priority) {
        setName(name);
        setPriority(priority);
    }

    public FxThread(String name) {
        this(name, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Object r0 = this;
        try {
            synchronized (r0) {
                this.startTime = System.currentTimeMillis();
                r0 = r0;
                CKit.sleep(10L);
                process();
                Platform.runLater(() -> {
                    try {
                        onProcessEnd();
                    } catch (Throwable e) {
                        log.error(e);
                    }
                    processSuccess();
                });
            }
        } catch (Throwable err) {
            Platform.runLater(() -> {
                try {
                    onProcessEnd();
                } catch (Throwable e) {
                    log.error(e);
                }
                processError(err);
            });
        }
    }

    public synchronized long getStartTime() {
        return this.startTime;
    }

    public void comfortSleep(int minimumTimeMilliseconds) {
        CKit.comfortSleep(this.startTime, minimumTimeMilliseconds);
    }

    public void comfortSleep() {
        comfortSleep(400);
    }
}
