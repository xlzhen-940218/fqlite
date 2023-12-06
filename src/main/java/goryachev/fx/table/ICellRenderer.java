package goryachev.fx.table;

import javafx.scene.control.TableCell;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/ICellRenderer.class */
public interface ICellRenderer<CELL> {
    Object renderCell(TableCell tableCell, CELL cell);
}
