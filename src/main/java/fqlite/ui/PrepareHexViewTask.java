package fqlite.ui;

import fqlite.base.Job;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;

/* compiled from: PrepareHexViewStart.java */
/* loaded from: fqlite_next.jar:fqlite/ui/PrepareHexViewTask.class */
class PrepareHexViewTask {
    Job job;
    TreeItem<NodeObject> dbNode;

    public PrepareHexViewTask(Job job, TreeItem<NodeObject> dbNode) {
        this.job = job;
        this.dbNode = dbNode;
    }

    public void start() {
        Task<Integer> task = new Task<Integer>() { // from class: fqlite.ui.PrepareHexViewTask.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javafx.concurrent.Task
            public Integer call() throws Exception {
                System.out.println(" hex Dialog geladen ");
                return null;
            }

            @Override // javafx.concurrent.Task
            public void succeeded() {
                super.succeeded();
                System.out.println("Done HexViewImport!");
            }

            @Override // javafx.concurrent.Task
            public void cancelled() {
                super.cancelled();
                updateMessage("Creation of HexView Cancelled!");
            }

            @Override // javafx.concurrent.Task
            public void failed() {
                super.failed();
                updateMessage("Creation of HexView Failed!");
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        System.out.println("Thread started");
    }
}
