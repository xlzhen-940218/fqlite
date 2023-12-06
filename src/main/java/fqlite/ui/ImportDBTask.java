package fqlite.ui;

import fqlite.base.GUI;
import fqlite.base.Job;
import fqlite.base.RollbackJournalReader;
import fqlite.base.WALReader;
import fqlite.util.BLOB2Disk;
import java.awt.Toolkit;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

/* loaded from: fqlite_next.jar:fqlite/ui/ImportDBTask.class */
public class ImportDBTask {
    Job job;
    TreeItem<NodeObject> dbNode;

    public ImportDBTask(Job job, TreeItem<NodeObject> dbNode) {
        this.job = job;
        this.dbNode = dbNode;
    }

    public void start() {
        Task<Integer> task = new Task<Integer>() { // from class: fqlite.ui.ImportDBTask.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // javafx.concurrent.Task
            public Integer call() throws Exception {
                ImportDBTask.this.job.start();
                return null;
            }

            @Override // javafx.concurrent.Task
            public void succeeded() {
                super.succeeded();
                updateMessage("DB Import Done!");
                System.out.println("DB Import Done !");
                String s = GUI.class.getResource("/database-small-icon.png").toExternalForm();
                ImageView iv = new ImageView(s);
                ImportDBTask.this.job.dbNode.setGraphic(iv);
                ImportDBTask.this.job.updatePropertyPanel();
                if (GUI.doesRollbackJournalExist(ImportDBTask.this.job.path) > 0) {
                    String rjpath = String.valueOf(ImportDBTask.this.job.path) + "-journal";
                    RollbackJournalReader rol = new RollbackJournalReader(rjpath, ImportDBTask.this.job);
                    rol.ps = ImportDBTask.this.job.ps;
                    rol.parse();
                    rol.output();
                    String s2 = GUI.class.getResource("/journal-icon.png").toExternalForm();
                    ImageView iv2 = new ImageView(s2);
                    if (ImportDBTask.this.job.rjNode != null) {
                        ImportDBTask.this.job.rjNode.setGraphic(iv2);
                    }
                    ImportDBTask.this.job.rol = rol;
                    ImportDBTask.this.job.updateRollbackPanel();
                }
                if (GUI.doesWALFileExist(ImportDBTask.this.job.path) > 0) {
                    String walpath = String.valueOf(ImportDBTask.this.job.path) + "-wal";
                    WALReader wal = new WALReader(walpath, ImportDBTask.this.job);
                    wal.parse();
                    wal.output();
                    String s3 = GUI.class.getResource("/wal-icon.png").toExternalForm();
                    ImageView iv3 = new ImageView(s3);
                    if (ImportDBTask.this.job.walNode != null) {
                        ImportDBTask.this.job.walNode.setGraphic(iv3);
                    }
                    ImportDBTask.this.job.wal = wal;
                    ImportDBTask.this.job.updateWALPanel();
                }
                Platform.runLater(new Runnable() { // from class: fqlite.ui.ImportDBTask.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        System.out.println("BLOBs grösse " + ImportDBTask.this.job.BLOBs.size());
                        if (ImportDBTask.this.job.BLOBs.size() > 0) {
                            BLOB2Disk out = new BLOB2Disk(ImportDBTask.this.job);
                            System.out.println("Ausgabe auf Dateisystem");
                            out.export();
                        }
                    }
                });
                Toolkit.getDefaultToolkit().beep();
            }

            @Override // javafx.concurrent.Task
            public void cancelled() {
                super.cancelled();
                updateMessage("Cancelled!");
            }

            @Override // javafx.concurrent.Task
            public void failed() {
                super.failed();
                updateMessage("Failed!");
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }
}
