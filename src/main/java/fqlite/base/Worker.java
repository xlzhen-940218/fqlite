package fqlite.base;

import fqlite.util.Auxiliary;
import fqlite.util.WorkerQueue;

/* loaded from: fqlite_next.jar:fqlite/base/Worker.class */
public class Worker implements Runnable {
    WorkerQueue<RecoveryTask> toDo = new WorkerQueue<>();
    Auxiliary util;

    public Worker(Job rt) {
        this.util = new Auxiliary(rt);
    }

    public void addTask(RecoveryTask t) {
        this.toDo.addLast(t);
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.toDo.size() > 0) {
            RecoveryTask next = this.toDo.pop();
            next.run();
        }
    }
}
