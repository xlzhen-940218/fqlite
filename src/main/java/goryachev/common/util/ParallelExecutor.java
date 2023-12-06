package goryachev.common.util;

import goryachev.common.log.Log;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/ParallelExecutor.class */
public class ParallelExecutor implements ThreadFactory {
    protected static final Log log = Log.get("ParallelExecutor");
    private String name;
    private AtomicInteger number;
    private ThreadPoolExecutor exec;
    private boolean closed;

    public ParallelExecutor(String name) {
        this(name, 60);
    }

    public ParallelExecutor(String name, int keepAliveTimeSeconds) {
        this.number = new AtomicInteger();
        this.name = name;
        this.exec = new ThreadPoolExecutor(0, Integer.MAX_VALUE, keepAliveTimeSeconds, TimeUnit.SECONDS, new SynchronousQueue(), this);
        this.exec.allowCoreThreadTimeOut(true);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, String.valueOf(this.name) + "." + this.number.getAndIncrement());
        t.setDaemon(true);
        return t;
    }

    public void setKeepAliveTime(long time, TimeUnit unit) {
        this.exec.setKeepAliveTime(time, unit);
    }

    public synchronized void shutdown() {
        if (!this.closed) {
            this.exec.shutdown();
            try {
                this.exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error((Throwable) e);
            }
            this.closed = true;
        }
    }

    protected synchronized boolean isClosed() {
        return this.closed;
    }

    public void submit(Runnable r) {
        this.exec.execute(r);
    }
}
