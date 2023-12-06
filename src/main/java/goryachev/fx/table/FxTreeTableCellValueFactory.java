package goryachev.fx.table;

import goryachev.fx.FxObject;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTreeTableCellValueFactory.class */
public abstract class FxTreeTableCellValueFactory<T> implements Callback<TreeTableColumn.CellDataFeatures<T, T>, ObservableValue<T>> {
    public abstract T value(TreeItem<T> treeItem, TreeTableColumn<T, T> treeTableColumn, TreeTableView<T> treeTableView);

    @Override
    public ObservableValue<T> call(TreeTableColumn.CellDataFeatures<T, T> f) {
        T v = value(f.getValue(), f.getTreeTableColumn(), f.getTreeTableView());
        return new FxObject(v);
    }
}
