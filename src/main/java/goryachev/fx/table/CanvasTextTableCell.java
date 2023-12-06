package goryachev.fx.table;

import goryachev.fx.CPane;
import goryachev.fx.IStyledText;
import goryachev.fx.util.TextPainter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/table/CanvasTextTableCell.class */
public class CanvasTextTableCell extends CPane {
    protected final TextPainter painter = new TextPainter();
    protected final Object value;
    protected final HPos alignment;
    private final ObjectBinding binding;

    public CanvasTextTableCell(FxTextTable table, Object value, HPos alignment) {
        this.value = value;
        this.alignment = alignment;
        this.binding = Bindings.createObjectBinding(() -> {
            Font f = table.getFont();
            return updateCanvas(f);
        }, table.fontProperty(), widthProperty(), heightProperty());
        this.binding.addListener((c, p, v) -> {
        });
        Font f = table.getFont();
        updateCanvas(f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.fx.CPane, javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefHeight(double width) {
        return 10.0d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.fx.CPane, javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefWidth(double height) {
        double w;
        Canvas c = this.painter.getCanvas();
        if (c == null) {
            w = 0.0d;
        } else {
            w = c.getWidth();
        }
        Insets m = getInsets();
        return m.getLeft() + w + m.getRight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.fx.CPane, javafx.scene.Parent
    public void layoutChildren() {
        super.layoutChildren();
    }

    protected Object updateCanvas(Font f) {
        Canvas canvas = this.painter.createCanvas(this);
        setCenter(canvas);
        this.painter.clear();
        this.painter.setFont(f);
        if (this.value instanceof IStyledText) {
            this.painter.paint((IStyledText) this.value, this.alignment);
        } else if (this.value != null) {
            String text = this.value.toString();
            this.painter.paint(text, this.alignment);
        }
        return canvas;
    }
}
