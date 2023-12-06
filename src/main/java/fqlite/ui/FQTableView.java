package fqlite.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/* loaded from: fqlite_next.jar:fqlite/ui/FQTableView.class */
public class FQTableView<T> extends TableView<T> {
    public String dbname;

    public FQTableView(String dbname) {
        this.dbname = dbname;
    }

    public FQTableView(ObservableList<T> items, String dbname) {
        super(items);
        this.dbname = dbname;
    }
}
