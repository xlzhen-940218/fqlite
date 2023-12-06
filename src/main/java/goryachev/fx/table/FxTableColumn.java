package goryachev.fx.table;

import goryachev.common.util.CKit;
import goryachev.fx.FxObject;
import java.util.function.Function;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTableColumn.class */
public class FxTableColumn<ITEM, CELL> extends TableColumn<ITEM, CELL> {
    protected Function<CELL, String> formatter;
    protected ICellRenderer<CELL> renderer;
    protected OverrunStyle overrunStyle;
    protected Pos alignment;

    public FxTableColumn(String name) {
        super(name);
        this.overrunStyle = OverrunStyle.ELLIPSIS;
        this.alignment = Pos.CENTER_LEFT;
        init();
    }

    public FxTableColumn() {
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

    public FxTableColumn<ITEM, CELL> setAlignment(Pos a) {
        this.alignment = a == null ? Pos.CENTER_LEFT : a;
        return this;
    }

    public FxTableColumn<ITEM, CELL> setRightAlignment() {
        setAlignment(Pos.CENTER_RIGHT);
        return this;
    }

    @Deprecated
    public void setToolTip() {
        String text = getText();
        setToolTip(text);
    }

    @Deprecated
    public void setToolTip(String text) {
    }

    public Pos getAlignment() {
        return this.alignment;
    }

    public FxTableColumn<ITEM, CELL> setFormatter(Function<CELL, String> formatter) {
        this.formatter = formatter;
        return this;
    }

    public FxTableColumn<ITEM, CELL> setTextOverrun(OverrunStyle x) {
        this.overrunStyle = x;
        return this;
    }

    public FxTableColumn<ITEM, CELL> setRenderer(ICellRenderer<CELL> r) {
        this.renderer = r;
        return this;
    }

    public ICellRenderer<CELL> getRenderer() {
        return this.renderer;
    }

    public FxTableColumn<ITEM, CELL> setAccessor(Function<ITEM, ObservableValue<CELL>> func) {
        setCellValueFactory(cellDataFeatures -> {
            return (ObservableValue) func.apply(cellDataFeatures.getValue());
        });
        return this;
    }

    public FxTableColumn<ITEM, CELL> setSimpleAccessor(Function<ITEM, CELL> func) {
        setCellValueFactory(cellDataFeatures -> {
            return new FxObject(func.apply(cellDataFeatures.getValue()));
        });
        return this;
    }

    public FxTableColumn<ITEM, CELL> setRealPrefWidth(double width) {
        setMaxWidth(width * 100.0d);
        setPrefWidth(width);
        return this;
    }

    public void setCheckboxCell() {
        setCellFactory(tableColumn -> {
            return new CheckBoxTableCell();
        });
    }

    protected Callback cellFactory() {
        return new Callback<TableColumn<?, ?>, TableCell<?, ?>>() { // from class: goryachev.fx.table.FxTableColumn.1
            @Override // javafx.util.Callback
            public TableCell<?, ?> call(TableColumn<?, ?> param) {
                return new TableCell<Object, Object>() { // from class: goryachev.fx.table.FxTableColumn.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // javafx.scene.control.Cell
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else if (!(item instanceof Node)) {
                            if (FxTableColumn.this.renderer != null) {
                                Object rendered = FxTableColumn.this.renderer.renderCell((TableCell) this, (CELL) item);
                                if (rendered != null) {
                                    if (rendered instanceof Node) {
                                        Node n = (Node) rendered;
                                        super.setText(null);
                                        super.setGraphic(n);
                                        return;
                                    }
                                    String text = rendered.toString();
                                    super.setText(text);
                                    super.setGraphic(null);
                                    return;
                                }
                                return;
                            }
                            String text2 = FxTableColumn.this.formatter == null ? item.toString() : FxTableColumn.this.formatter.apply((CELL) item);
                            super.setText(text2);
                            super.setGraphic(null);
                            super.setAlignment(FxTableColumn.this.alignment);
                            super.setTextOverrun(FxTableColumn.this.overrunStyle);
                        } else {
                            super.setText(null);
                            super.setGraphic((Node) item);
                        }
                    }
                };
            }
        };
    }
}
