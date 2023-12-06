package goryachev.common.util;

import goryachev.common.log.Log;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SystemTask.class */
public abstract class SystemTask extends TimerTask {
    protected static final Log log = Log.get("SystemTask");
    private static Timer timer;

    protected abstract void systemTaskBody() throws Exception;

    public static SystemTask create(final Runnable r) {
        return new SystemTask() { // from class: goryachev.common.util.SystemTask.1
            @Override // goryachev.common.util.SystemTask
            protected void systemTaskBody() throws Exception {
                r.run();
            }
        };
    }

    public static void schedule(long delay, long period, final Runnable r) {
        new SystemTask() { // from class: goryachev.common.util.SystemTask.2
            @Override // goryachev.common.util.SystemTask
            protected void systemTaskBody() throws Exception {
                r.run();
            }
        }.schedule(delay, period);
    }

    public static void schedule(long delay, final Runnable r) {
        new SystemTask() { // from class: goryachev.common.util.SystemTask.3
            @Override // goryachev.common.util.SystemTask
            protected void systemTaskBody() throws Exception {
                r.run();
            }
        }.schedule(delay);
    }

    public void schedule(long delay, long period) {
        timer().schedule(this, delay, period);
    }

    public void schedule(long delay) {
        timer().schedule(this, delay);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<goryachev.common.util.SystemTask>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    protected Timer timer() {
        if (timer == null) {
            Object r0 = SystemTask.class;
            synchronized (r0) {
                if (timer == null) {
                    timer = new Timer("SystemTask", true);
                }
                r0 = r0;
            }
        }
        return timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        try {
            systemTaskBody();
        } catch (Throwable e) {
            log.error(e);
        }
    }
}
