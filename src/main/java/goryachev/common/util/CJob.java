package goryachev.common.util;

import goryachev.common.log.Log;
import goryachev.common.util.platform.ApplicationSupport;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CJob.class */
public abstract class CJob implements Runnable {
    private String name;
    private volatile Object result;
    private CList<CJob> children;
    private volatile boolean cancelled;
    protected static final Log log = Log.get("CJob");
    private static final Object NULL = new Object();
    private static final ParallelExecutor exec = createExecutor();
    private static final ThreadLocal<CJob> currentJob = new ThreadLocal<>();

    protected abstract void process() throws Exception;

    protected void onThisJobCompleted() {
    }

    protected void handleJobError(Throwable e) {
        log.error(e);
    }

    public CJob(String name) {
        this.name = name;
    }

    public CJob() {
        this("cjob." + CKit.id());
    }

    public CJob(CJob parent, String name) {
        this(name);
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public CJob(String name, boolean childOfCurrent) {
        this(childOfCurrent ? getJob() : null, name);
    }

    private static ParallelExecutor createExecutor() {
        return new ParallelExecutor("cjob", 10);
    }

    public static void setKeepAliveTime(long timeSeconds) {
        exec.setKeepAliveTime(timeSeconds, TimeUnit.SECONDS);
    }

    public static void shutdown() {
        exec.shutdown();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    protected synchronized void addChild(CJob ch) {
        if (this.children == null) {
            this.children = new CList<>();
        }
        this.children.add(ch);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (isCancelled()) {
            return;
        }
        Thread t = Thread.currentThread();
        String oldName = t.getName();
        t.setName(getName());
        currentJob.set(this);
        try {
            process();
            setResult(NULL);
        } catch (Throwable e) {
            setResult(e);
            handleJobError(e);
        }
        try {
            onThisJobCompleted();
        } catch (Throwable e2) {
            log.error(e2);
        }
        currentJob.set(null);
        t.setName(oldName);
    }

    public static CJob getJob() {
        return currentJob.get();
    }

    protected synchronized void setResult(Object x) {
        this.result = x;
        notifyAll();
    }

    protected boolean hasResult() {
        return this.result != null;
    }

    protected synchronized Object getResult() {
        if (this.result == NULL) {
            return null;
        }
        return this.result;
    }

    public synchronized Throwable getJobError() {
        if (this.result instanceof Throwable) {
            return (Throwable) this.result;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.Object] */
    public void waitForCompletion() throws Exception {
        while (!hasResult() && !isCancelled()) {
            Object r0 = this;
            synchronized (r0) {
                try {
                    r0 = this;
                    r0.wait(100L);
                } catch (Exception e) {
                    log.error((Throwable) e);
                }
            }
        }
        Object rv = getResult();
        if (rv instanceof Exception) {
            throw ((Exception) rv);
        }
        if (rv instanceof Throwable) {
            throw new Exception((Throwable) rv);
        }
        waitForChildren();
    }

    public void waitForChildren() {
        CList<CJob> cs = getChildrenPrivate();
        waitForAll(cs);
    }

    public CList<CJob> getChildren() {
        CList<CJob> cs = getChildrenPrivate();
        return cs == null ? new CList<>() : cs;
    }

    protected synchronized CList<CJob> getChildrenPrivate() {
        if (this.children == null) {
            return null;
        }
        return new CList<>(this.children);
    }

    public void submit() {
        submit(this);
    }

    public static void submit(Runnable r) {
        ApplicationSupport.shutdownCJobExecutor = true;
        exec.submit(r);
    }

    public static void waitForCompletionQuiet(CJob j) {
        try {
            j.waitForCompletion();
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    public static void waitForAll(Collection<CJob> jobs) {
        if (jobs != null) {
            for (CJob ch : jobs) {
                waitForCompletionQuiet(ch);
            }
        }
    }

    public static void waitForAll(CJob... jobs) {
        for (CJob ch : jobs) {
            if (ch != null) {
                waitForCompletionQuiet(ch);
            }
        }
    }

    public static void waitForAll(CJob j1, CJob j2) {
        waitForCompletionQuiet(j1);
        waitForCompletionQuiet(j2);
    }

    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
