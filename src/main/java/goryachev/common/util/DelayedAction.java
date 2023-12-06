package goryachev.common.util;

import goryachev.common.log.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/DelayedAction.class */
public class DelayedAction {
    private static final int WARN_THRESHOLD = 500;
    private final String name;
    private final Runnable action;
    private TimerTask task;
    private Consumer<Throwable> errorHandler;
    protected static final Log log = Log.get("DelayedAction");
    private static final Timer timer = new Timer("DelayedAction.timer", true);

    public DelayedAction(String name, Runnable action) {
        if (action == null) {
            throw new NullPointerException("action");
        }
        this.name = name;
        this.action = action;
    }

    public void setOnError(Consumer<Throwable> handler) {
        this.errorHandler = handler;
    }

    public void schedule(long delay) {
        timer.schedule(task(), delay);
    }

    public void schedule(long delay, long period) {
        timer.schedule(task(), delay, period);
    }

    protected synchronized TimerTask task() {
        if (this.task != null) {
            this.task.cancel();
        }
        this.task = new TimerTask() { // from class: goryachev.common.util.DelayedAction.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                DelayedAction.this.processTask();
            }

            public String toString() {
                return DelayedAction.this.getName();
            }
        };
        return this.task;
    }

    public String toString() {
        return getName();
    }

    public String getName() {
        return this.name;
    }

    protected final void processTask() {
        long start = System.currentTimeMillis();
        try {
            this.action.run();
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > 500) {
                log.error("taking too long to run: " + this.name);
            }
        }
    }
}
