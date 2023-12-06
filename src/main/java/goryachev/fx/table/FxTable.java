package goryachev.fx.table;

import goryachev.common.util.CList;
import goryachev.fx.CommonStyles;
import goryachev.fx.FX;
import goryachev.fx.FxBoolean;
import goryachev.fx.util.FxTools;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTable.class */
public class FxTable<T> extends BorderPane {
    public final TableView<T> table;
    public final FxBoolean autoResizeMode;
    private BooleanBinding singleSelectionProperty;
    private BooleanBinding nonEmptySelectionProperty;

    public FxTable() {
        this.autoResizeMode = new FxBoolean();
        this.table = new TableView<>();
        setCenter(this.table);
        init();
    }

    public FxTable(ObservableList<T> items) {
        this.autoResizeMode = new FxBoolean();
        this.table = new TableView<>(items);
        setCenter(this.table);
        init();
    }

    private void init() {
        this.table.skinProperty().addListener((observableValue, skin, skin2) -> {
            fixHorizontalScrollbar();
        });
    }

    public void wrapSortedList(ObservableList<T> src) {
        SortedList<T> s = new SortedList<>(src);
        s.comparatorProperty().bind(this.table.comparatorProperty());
        setItems((ObservableList) s);
    }

    public void wrapSortedList(ObservableList<T> src, Comparator<T> comparator) {
        SortedList<T> s = new SortedList<>(src, comparator);
        s.comparatorProperty().bind(this.table.comparatorProperty());
        setItems((ObservableList) s);
    }

    public boolean isAutoResizeMode() {
        return this.autoResizeMode.get();
    }

    public void setAutoResizeMode(boolean on) {
        this.autoResizeMode.set(on);
        if (on) {
            this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } else {
            this.table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        }
        fixHorizontalScrollbar();
    }

    protected void fixHorizontalScrollbar() {
        for (Node n : lookupAll(".scroll-bar")) {
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

    public ObservableList<TableColumn<T, ?>> getColumns() {
        return this.table.getColumns();
    }

    public <C> FxTableColumn<T, C> addColumn(FxTableColumn<T, C> c) {
        this.table.getColumns().add(c);
        return c;
    }

    public <C> FxTableColumn<T, C> addColumn() {
        FxTableColumn<T, C> c = new FxTableColumn<>();
        this.table.getColumns().add(c);
        return c;
    }

    public <C> FxTableColumn<T, C> addColumn(String name) {
        FxTableColumn<T, C> c = new FxTableColumn<>(name);
        this.table.getColumns().add(c);
        return c;
    }

    public void setColumns(Collection<FxTableColumn<T, ?>> cs) {
        this.table.getColumns().setAll(cs);
    }

    public void setColumns(FxTableColumn<T, ?>... fxTableColumnArr) {
        this.table.getColumns().setAll(fxTableColumnArr);
    }

    public int getColumnCount() {
        return this.table.getColumns().size();
    }

    public FxTableColumn<T, ?> lastColumn() {
        ObservableList<TableColumn<T, ?>> cs = this.table.getColumns();
        return (FxTableColumn) cs.get(cs.size() - 1);
    }

    public int getRowCount() {
        return this.table.getItems().size();
    }

    public ObservableList<T> getItems() {
        return this.table.getItems();
    }

    public T getItem(int row) {
        if (row < 0) {
            return null;
        }
        ObservableList<T> items = this.table.getItems();
        if (row >= items.size()) {
            return null;
        }
        return items.get(row);
    }

    public void setItems(Collection<T> items) {
        clearSelection();
        if (items == null) {
            this.table.getItems().clear();
        } else {
            this.table.getItems().setAll((Collection<? extends T>) items);
        }
        this.table.sort();
    }

    public void bindItems(ObservableList<T> items) {
        if (items == null) {
            this.table.getItems().clear();
        } else {
            Bindings.bindContent(this.table.getItems(), items);
        }
    }

    public void setItems(T... tArr) {
        clearSelection();
        if (tArr == null) {
            this.table.getItems().clear();
        } else {
            this.table.getItems().setAll(tArr);
        }
        this.table.sort();
    }

    public void setItems(ObservableList<T> source) {
        this.table.setItems(source);
    }

    public void addItem(T item) {
        this.table.getItems().add(item);
    }

    public void addItem(int ix, T item) {
        this.table.getItems().add(ix, item);
    }

    public void addItems(T... tArr) {
        this.table.getItems().addAll(tArr);
    }

    public void clearItems() {
        clearSelection();
        this.table.getItems().clear();
    }

    public T getSelectedItem() {
        return this.table.getSelectionModel().getSelectedItem();
    }

    public ObservableList<T> getSelectedItems() {
        return this.table.getSelectionModel().getSelectedItems();
    }

    public List<T> getSelectedItemsCopy() {
        return new CList((Collection) this.table.getSelectionModel().getSelectedItems());
    }

    public void setPlaceholder(String s) {
        this.table.setPlaceholder(new Label(s));
    }

    public final StringProperty placeholderLabelTextProperty() {
        Node n = this.table.getPlaceholder();
        if (n instanceof Label) {
            return ((Label) n).textProperty();
        }
        Label t = new Label();
        this.table.setPlaceholder(t);
        return t.textProperty();
    }

    public void setPlaceholder(Node n) {
        this.table.setPlaceholder(n);
    }

    public void selectAll() {
        this.table.getSelectionModel().selectAll();
    }

    public void selectFirst() {
        this.table.getSelectionModel().clearSelection();
        this.table.getSelectionModel().selectFirst();
        this.table.scrollTo(0);
    }

    public void select(T item) {
        this.table.getSelectionModel().clearSelection();
        this.table.getSelectionModel().select(item);
    }

    public void select(Collection<T> items) {
        this.table.getSelectionModel().clearSelection();
        if (items != null) {
            for (T item : items) {
                this.table.getSelectionModel().select(item);
            }
        }
    }

    public int getSelectedItemCount() {
        return this.table.getSelectionModel().getSelectedItems().size();
    }

    public void scrollTo(int row) {
        this.table.scrollTo(row);
    }

    public void selectRow(int ix) {
        this.table.getSelectionModel().select(ix);
    }

    public void clearSelection() {
        this.table.getSelectionModel().clearSelection();
    }

    public TableView.TableViewSelectionModel<T> getSelectionModel() {
        return this.table.getSelectionModel();
    }

    public ReadOnlyObjectProperty<T> selectedItemProperty() {
        return getSelectionModel().selectedItemProperty();
    }

    public ObservableList<T> selectedItemsProperty() {
        return getSelectionModel().getSelectedItems();
    }

    public ReadOnlyIntegerProperty selectedIndexProperty() {
        return getSelectionModel().selectedIndexProperty();
    }

    public void setMultipleSelection(boolean on) {
        this.table.getSelectionModel().setSelectionMode(on ? SelectionMode.MULTIPLE : SelectionMode.SINGLE);
    }

    public void setCellSelectionEnabled(boolean on) {
        this.table.getSelectionModel().setCellSelectionEnabled(on);
    }

    public void setAlternateRowsColoring(boolean on) {
        FX.style(this.table, !on, CommonStyles.DISABLE_ALTERNATIVE_ROW_COLOR);
    }

    public void setPopupMenu(Supplier<ContextMenu> generator) {
        FX.setPopupMenu(this, generator);
    }

    public void hideHeader() {
        this.table.skinProperty().addListener((observableValue, skin, skin2) -> {
            Pane h = (Pane) this.table.lookup("TableHeaderRow");
            if (h != null && h.isVisible()) {
                h.setMaxHeight(0.0d);
                h.setMinHeight(0.0d);
                h.setPrefHeight(0.0d);
                h.setVisible(false);
            }
        });
    }

    public Pane getHeader() {
        return (Pane) this.table.lookup("TableHeaderRow");
    }

    public void setHeaderPopupMenu(Supplier<ContextMenu> generator) {
        Pane h = getHeader();
        if (h != null) {
            FX.setPopupMenu(h, generator);
        } else {
            this.table.skinProperty().addListener((observableValue, skin, skin2 )-> {
                Pane hd = getHeader();
                if (hd != null) {
                    FX.setPopupMenu(hd, generator);
                }
            });
        }
    }

    public BooleanBinding singleSelectionProperty() {
        if (this.singleSelectionProperty == null) {
            this.singleSelectionProperty = Bindings.createBooleanBinding(() -> {
                return Boolean.valueOf(this.table.getSelectionModel().getSelectedIndices().size() == 1);
            }, this.table.getSelectionModel().getSelectedIndices());
        }
        return this.singleSelectionProperty;
    }

    public BooleanBinding nonEmptySelectionProperty() {
        if (this.nonEmptySelectionProperty == null) {
            this.nonEmptySelectionProperty = Bindings.createBooleanBinding(() -> {
                return Boolean.valueOf(this.table.getSelectionModel().getSelectedIndices().size() > 0);
            }, this.table.getSelectionModel().getSelectedIndices());
        }
        return this.nonEmptySelectionProperty;
    }

    public void setEditable(boolean on) {
        this.table.setEditable(on);
    }

    public boolean isEditable() {
        return this.table.isEditable();
    }

    public ObservableList<TablePosition> getSelectedCells() {
        return getSelectionModel().getSelectedCells();
    }

    public void setSortable(boolean on) {
        if (on) {
            this.table.setSortPolicy(null);
        } else {
            this.table.setSortPolicy(tableView -> {
                return false;
            });
        }
    }

    public void setSortPolicy(Callback<TableView<T>, Boolean> policy) {
        this.table.setSortPolicy(policy);
    }

    public void removeSelectedItems() {
        TableView.TableViewSelectionModel<T> m = this.table.getSelectionModel();
        List<Integer> indexes = m.getSelectedIndices();
        if (indexes.size() >= 0) {
            int ix = FxTools.getMaximumValue(indexes) + 1;
            List<T> sel = m.getSelectedItems();
            int ix2 = ix - sel.size();
            this.table.getItems().removeAll(sel);
            if (ix2 >= this.table.getItems().size()) {
                ix2 = this.table.getItems().size() - 1;
            }
            if (ix2 >= 0) {
                m.clearAndSelect(ix2);
            }
        }
    }
}
