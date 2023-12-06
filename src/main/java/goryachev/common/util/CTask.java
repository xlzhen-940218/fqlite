package goryachev.common.util;

import goryachev.common.log.Log;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CTask.class */
public class CTask<T> implements Runnable {
    protected ValueGenerator<? extends T> generator;
    protected Consumer<T> onSuccess;
    protected Consumer<Throwable> onError;
    protected Runnable onFinish;
    protected static final Log log = Log.get("CTask");
    protected static ParallelExecutor exec = initExecutor();

    public CTask<T> producer(ValueGenerator<? extends T> generator) {
        this.generator = generator;
        return this;
    }

    public CTask<T> onError(Consumer<Throwable> onError) {
        this.onError = onError;
        return this;
    }

    public CTask<T> onSuccess(Consumer<T> onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    public CTask<T> onFinish(Runnable onFinish) {
        this.onFinish = onFinish;
        return this;
    }

    public void submit() {
        submit(this);
    }

    public void submit(ExecutorService ex) {
        ex.submit(this);
    }

    public static void submit(Runnable r) {
        exec.submit(r);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            T result = this.generator.generate();
            handleSuccess(result);
        } catch (Throwable e) {
            try {
                handleError(e);
            } catch (Throwable th) {
                log.error(e);
            }
        }
        try {
            handleFinish();
        } catch (Throwable e2) {
            log.error(e2);
        }
    }

    private static ParallelExecutor initExecutor() {
        ParallelExecutor ex = new ParallelExecutor("CTask.Executor", 5);
        return ex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleSuccess(T result) {
        Consumer<T> c = this.onSuccess;
        if (c != null) {
            c.accept(result);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleError(Throwable e) {
        Consumer<Throwable> c = this.onError;
        if (c != null) {
            c.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleFinish() {
        Runnable r = this.onFinish;
        if (r != null) {
            r.run();
        }
    }
}
