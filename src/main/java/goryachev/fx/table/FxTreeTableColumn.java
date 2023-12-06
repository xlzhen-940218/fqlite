package goryachev.fx.table;

import goryachev.common.util.CKit;
import goryachev.fx.FxObject;
import java.util.function.Function;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTreeTableColumn.class */
public class FxTreeTableColumn<ITEM, CELL> extends TreeTableColumn<ITEM, CELL> {
    protected Function<CELL, String> formatter;
    protected Function<CELL, Node> renderer;
    protected OverrunStyle overrunStyle;
    protected Pos alignment;

    public FxTreeTableColumn(String name) {
        super(name);
        this.overrunStyle = OverrunStyle.ELLIPSIS;
        this.alignment = Pos.CENTER_LEFT;
        init();
    }

    public FxTreeTableColumn() {
        this.overrunStyle = OverrunStyle.ELLIPSIS;
        this.alignment = Pos.CENTER_LEFT;
        init();
    }

    private void init() {
        setCellFactory(cellFactory());
        setCellValueFactory(cellDataFeatures -> {
            return new FxObject(CKit.toStringOrNull(cellDataFeatures.getValue()));
        });
    }

    public FxTreeTableColumn<ITEM, CELL> setAlignment(Pos a) {
        this.alignment = a;
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setFormatter(Function<CELL, String> formatter) {
        this.formatter = formatter;
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setTextOverrun(OverrunStyle x) {
        this.overrunStyle = x;
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setRenderer(Function<CELL, Node> r) {
        this.renderer = r;
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setAccessor(Function<ITEM, ObservableValue<CELL>> func) {
        setCellValueFactory(cellDataFeatures -> {
            return (ObservableValue) func.apply(cellDataFeatures.getValue().getValue());
        });
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setSimpleAccessor(Function<ITEM, CELL> func) {
        setCellValueFactory(cellDataFeatures -> {
            return new FxObject(func.apply(cellDataFeatures.getValue().getValue()));
        });
        return this;
    }

    public FxTreeTableColumn<ITEM, CELL> setRealPrefWidth(double width) {
        setMaxWidth(width * 100.0d);
        return this;
    }

    public void setCheckboxCell() {
        setCellFactory(treeTableColumn -> {
            return new CheckBoxTreeTableCell();
        });
    }

    private Callback cellFactory() {
        return new Callback<TreeTableColumn<?, ?>, TreeTableCell<?, ?>>() { // from class: goryachev.fx.table.FxTreeTableColumn.1
            @Override // javafx.util.Callback
            public TreeTableCell<?, ?> call(TreeTableColumn<?, ?> param) {
                return new TreeTableCell<Object, Object>() { // from class: goryachev.fx.table.FxTreeTableColumn.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // javafx.scene.control.Cell
                    public void updateItem(Object item, boolean empty) {
                        if (item != getItem()) {
                            super.updateItem(item, empty);
                            if (item == null) {
                                super.setText(null);
                                super.setGraphic(null);
                            } else if (!(item instanceof Node)) {
                                if (FxTreeTableColumn.this.renderer == null) {
                                    String text = FxTreeTableColumn.this.formatter == null ? item.toString() : FxTreeTableColumn.this.formatter.apply((CELL) item);
                                    super.setText(text);
                                    super.setGraphic(null);
                                    super.setAlignment(FxTreeTableColumn.this.alignment);
                                    super.setTextOverrun(FxTreeTableColumn.this.overrunStyle);
                                    return;
                                }
                                Node n = FxTreeTableColumn.this.renderer.apply((CELL) item);
                                super.setText(null);
                                super.setGraphic(n);
                            } else {
                                super.setText(null);
                                super.setGraphic((Node) item);
                            }
                        }
                    }
                };
            }
        };
    }
}
