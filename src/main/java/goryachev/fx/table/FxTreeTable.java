package goryachev.fx.table;

import goryachev.fx.CPane;
import goryachev.fx.FxBoolean;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTreeTable.class */
public class FxTreeTable<T> extends CPane {
    public final FxBoolean autoResizeMode = new FxBoolean();
    public final TreeTableView<T> tree = new TreeTableView<>();

    public FxTreeTable() {
        this.tree.skinProperty().addListener((observableValue, skin, skin2) -> {
            fixHorizontalScrollbar();
        });
        setCenter(this.tree);
    }

    public void setEditable(boolean on) {
        this.tree.setEditable(on);
    }

    public void selectFirst() {
        this.tree.getSelectionModel().selectFirst();
    }

    public TreeTableView.TreeTableViewSelectionModel<T> getSelectionModel() {
        return this.tree.getSelectionModel();
    }

    private Callback createCellFactory() {
        return new Callback<TreeTableColumn<T, ?>, TreeTableCell<T, ?>>() { // from class: goryachev.fx.table.FxTreeTable.1
            /*@Override // javafx.util.Callback
            public *//* bridge *//* *//* synthetic *//* Object call(Object obj) {
                return call((TreeTableColumn) ((TreeTableColumn) obj));
            }*/

            public TreeTableCell<T, ?> call(TreeTableColumn<T, ?> column) {
                return new TreeTableCell() { // from class: goryachev.fx.table.FxTreeTable.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // javafx.scene.control.Cell
                    public void updateItem(Object item, boolean empty) {
                        if (item == getItem()) {
                            return;
                        }
                        super.updateItem(item, empty);
                        if (item == null) {
                            super.setText(null);
                            super.setGraphic(null);
                        } else if (item instanceof Node) {
                            super.setText(null);
                            super.setGraphic((Node) item);
                        } else {
                            super.setText(item.toString());
                            super.setGraphic(null);
                        }
                    }
                };
            }
        };
    }

    public void setRoot(TreeItem<T> root) {
        this.tree.setRoot(root);
    }

    public TreeItem<T> getRoot() {
        return this.tree.getRoot();
    }

    public void setShowRoot(boolean on) {
        this.tree.setShowRoot(on);
    }

    public ObservableList<TreeTableColumn<T, ?>> getColumns() {
        return this.tree.getColumns();
    }

    public <C> FxTreeTableColumn<T, C> addColumn(FxTreeTableColumn<T, C> c) {
        this.tree.getColumns().add(c);
        return c;
    }

    public <C> FxTreeTableColumn<T, C> addColumn() {
        FxTreeTableColumn<T, C> c = new FxTreeTableColumn<>();
        this.tree.getColumns().add(c);
        return c;
    }

    public <C> FxTreeTableColumn<T, C> addColumn(String name) {
        FxTreeTableColumn<T, C> c = new FxTreeTableColumn<>(name);
        this.tree.getColumns().add(c);
        return c;
    }

    private void init() {
        this.tree.skinProperty().addListener((observableValue, skin, skin2) -> {
            fixHorizontalScrollbar();
        });
    }

    public boolean isAutoResizeMode() {
        return this.autoResizeMode.get();
    }

    public void setAutoResizeMode(boolean on) {
        this.autoResizeMode.set(on);
        if (on) {
            this.tree.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        } else {
            this.tree.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
        }
        fixHorizontalScrollbar();
    }

    protected void fixHorizontalScrollbar() {
        Skin skin = this.tree.getSkin();
        if (skin == null) {
            return;
        }
        for (Node n : skin.getNode().lookupAll(".scroll-bar")) {
            if (n instanceof ScrollBar) {
                ScrollBar b = (ScrollBar) n;
                if (b.getOrientation() == Orientation.HORIZONTAL) {
                    if (isAutoResizeMode()) {
                        b.setManaged(false);
                        b.setPrefHeight(0.0d);
                        b.setPrefWidth(0.0d);
                    } else {
                        b.setManaged(true);
                        b.setPrefHeight(-1.0d);
                        b.setPrefWidth(-1.0d);
                    }
                }
            }
        }
    }

    public void hideHeader() {
        this.tree.skinProperty().addListener((observableValue, skin, skin2 )-> {
            Pane header = (Pane) this.tree.lookup("TableHeaderRow");
            if (header.isVisible()) {
                header.setMaxHeight(0.0d);
                header.setMinHeight(0.0d);
                header.setPrefHeight(0.0d);
                header.setVisible(false);
            }
        });
    }

    public int getExpandedItemCount() {
        return this.tree.getExpandedItemCount();
    }

    public TreeItem<T> getTreeItem(int row) {
        return this.tree.getTreeItem(row);
    }

    public void edit(int row, TreeTableColumn<T, ?> c) {
        this.tree.edit(row, c);
    }
}
