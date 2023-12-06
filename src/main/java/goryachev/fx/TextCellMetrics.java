package goryachev.fx;

import javafx.scene.text.Font;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/TextCellMetrics.class */
public class TextCellMetrics {
    public final Font font;
    public final double baseline;
    public final int cellWidth;
    public final int cellHeight;

    public TextCellMetrics(Font f, double baseline, int cellWidth, int cellHeight) {
        this.font = f;
        this.cellHeight = cellHeight;
        this.baseline = baseline;
        this.cellWidth = cellWidth;
    }
}
