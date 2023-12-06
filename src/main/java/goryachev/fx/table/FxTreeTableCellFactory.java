package goryachev.fx.table;

import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTreeTableCellFactory.class */
public abstract class FxTreeTableCellFactory<T> implements Callback<TreeTableColumn<T, T>, TreeTableCell<T, T>> {
    public abstract TreeTableCell<T, T> call(TreeTableColumn<T, T> treeTableColumn);

   /* @Override // javafx.util.Callback
    public *//* bridge *//* *//* synthetic *//* Object call(Object obj) {
        return call((TreeTableColumn) ((TreeTableColumn) obj));
    }*/


}
