package fqlite.ui;

import fqlite.base.Job;
import fqlite.types.FileTypes;
import java.io.File;
import javafx.scene.layout.VBox;

/* loaded from: fqlite_next.jar:fqlite/ui/NodeObject.class */
public class NodeObject {
    public boolean hasData;
    public String name;
    public File filename;
    public Job job;
    int numberOfColumns;
    public FileTypes type;
    public VBox tablePane;
    public int tabletype;
    public boolean isRoot;

    public NodeObject(String name, boolean isRoot) {
        this.hasData = false;
        this.isRoot = false;
        this.name = name;
        this.isRoot = isRoot;
    }

    public NodeObject(String name, VBox tablePane, int numberOfColumns, FileTypes type, int tabletype) {
        this.hasData = false;
        this.isRoot = false;
        this.name = name;
        this.numberOfColumns = numberOfColumns;
        this.type = type;
        this.tablePane = tablePane;
        this.tabletype = tabletype;
    }

    public String toString() {
        return this.name;
    }
}
