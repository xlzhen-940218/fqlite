package goryachev.fx.table;

import goryachev.common.util.CKit;
import goryachev.fx.CssLoader;
import goryachev.fx.FX;
import goryachev.fx.FxObject;
import goryachev.fx.internal.CssHack;
import goryachev.fx.internal.CssTools;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/FxTextTable.class */
public class FxTextTable<T> extends FxTable<T> {
    protected final FxObject<Font> fontProperty;
    protected final FxObject<Insets> cellPaddingProperty;
    private static final Object TABLE_FONT_KEY = new Object();
    private static final Object TABLE_CELL_PADDING_KEY = new Object();
    private static final Object TABLE_ROW_HEIGHT_KEY = new Object();
    public static final double FUDGE = 2.0d;

    public FxTextTable() {
        this.fontProperty = new FxObject<>(Font.font("Monospace", 12.0d));
        this.cellPaddingProperty = new FxObject<>();
        init();
    }

    public FxTextTable(ObservableList<T> items) {
        super(items);
        this.fontProperty = new FxObject<>(Font.font("Monospace", 12.0d));
        this.cellPaddingProperty = new FxObject<>();
        init();
    }

    private void init() {
        FX.addChangeListener(this.fontProperty, this::updateFontHeightPadding);
        FX.addChangeListener(this.cellPaddingProperty, this::updateFontHeightPadding);
        FX.addChangeListener(getColumns(), this::updateColumnCellFactory);
    }

    protected void updateColumnCellFactory(ListChangeListener.Change ch) {
        while (ch.next()) {
            ch.getAddedSubList().forEach(c -> {
                if (c instanceof FxTableColumn) {
                    FxTableColumn tc = (FxTableColumn) c;
                    if (tc.getRenderer() == null) {
                        tc.setRenderer((tcell, item) -> {
                            HPos align = tc.getAlignment().getHpos();
                            return new CanvasTextTableCell(this, item, align);
                        });
                        return;
                    }
                    return;
                }
                throw new Error("not an FxTableColumn: " + c);
            });
        }
    }

    public FxObject<Font> fontProperty() {
        return this.fontProperty;
    }

    public Font getFont() {
        return this.fontProperty.get();
    }

    public void setFont(Font f) {
        this.fontProperty.set(f);
    }

    public void setCellPadding(Insets m) {
        cellPaddingProperty().set(m);
    }

    public Insets getCellPadding() {
        return this.cellPaddingProperty.get();
    }

    public FxObject<Insets> cellPaddingProperty() {
        return this.cellPaddingProperty;
    }

    protected void updateFontHeightPadding() {
        boolean updated = false;
        Font f = getFont();
        double targetHeight = f.getSize() + 2.0d;
        double currentHeight = currentRowHeight();
        if (targetHeight != currentHeight) {
            updateRowHeight(targetHeight);
            updated = true;
        }
        Font targetFont = getFont();
        Font currentFont = currentFont();
        if (CKit.notEquals(targetFont, currentFont)) {
            updateFont(targetFont);
            updated = true;
        }
        Insets targetPadding = getCellPadding();
        Insets currentPadding = currentCellPadding();
        if (CKit.notEquals(targetPadding, currentPadding)) {
            updateCellPadding(targetPadding);
            updated = true;
        }
        if (updated) {
            CssLoader.updateStyles();
        }
    }

    protected double currentRowHeight() {
        CssHack<Double> h = CssHack.get(this.table, TABLE_ROW_HEIGHT_KEY);
        if (h == null) {
            return 0.0d;
        }
        return h.doubleValue();
    }

    protected void updateRowHeight(double size) {
        CssHack.remove(this.table, TABLE_ROW_HEIGHT_KEY);
        if (size > 0.0d) {
            String val = CssTools.format("%spt", Double.valueOf(size));
            String name = CssHack.generateName("FxTableRowHeight", val);
            String css = String.format(".%s .table-row-cell { -fx-cell-size:%s; }", name, val);
            CssHack<Double> h = new CssHack<>(name, css, Double.valueOf(size), size);
            h.attachTo(this.table, TABLE_ROW_HEIGHT_KEY);
        }
    }

    protected Insets currentCellPadding() {
        CssHack<Insets> h = CssHack.get(this.table, TABLE_CELL_PADDING_KEY);
        if (h == null) {
            return null;
        }
        return h.getValue();
    }

    protected void updateCellPadding(Insets p) {
        CssHack.remove(this.table, TABLE_CELL_PADDING_KEY);
        if (p != null) {
            String val = CssTools.format("%1s %2s %3s %4s", Double.valueOf(p.getTop()), Double.valueOf(p.getRight()), Double.valueOf(p.getBottom()), Double.valueOf(p.getLeft()));
            String name = CssHack.generateName("FxTableCellPadding", val);
            String css = String.format(".%1$s .table-row-cell { -fx-padding:%2$s; }\n.%1$s .table-cell { -fx-padding:%2$s; }", name, val);
            CssHack<Insets> h = new CssHack<>(name, css, p, 0.0d);
            h.attachTo(this.table, TABLE_CELL_PADDING_KEY);
        }
    }

    protected Font currentFont() {
        CssHack<Font> h = CssHack.get(this.table, TABLE_FONT_KEY);
        if (h == null) {
            return null;
        }
        return h.getValue();
    }

    protected void updateFont(Font f) {
        CssHack.remove(this.table, TABLE_FONT_KEY);
        if (f != null) {
            String family = f.getFamily();
            String style = f.getStyle();
            double size = f.getSize();
            String nm = String.format("%s %s %.1f", family, style, Double.valueOf(size));
            String name = CssHack.generateName("FxTableFont", nm);
            String css = CssTools.format(".%1s .table-cell { -fx-font-family:%2s; -fx-font-style:%3s; -fx-font-size:%4spt; }", name, family, style, Double.valueOf(size));
            CssHack<Font> h = new CssHack<>(name, css, f, 0.0d);
            h.attachTo(this.table, TABLE_FONT_KEY);
        }
    }
}
