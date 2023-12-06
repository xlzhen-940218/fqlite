package goryachev.fxtexteditor;

import goryachev.fx.FX;
import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/CellStyleArray.class */
public class CellStyleArray {
    private final TextCellStyle[] cells;
    private Color lineColor;

    public CellStyleArray(int size) {
        this.cells = new TextCellStyle[size];
    }

    public TextCellStyle get(int ix) {
        return this.cells[ix];
    }

    public void setStyle(TextCellStyle style, int start, int end) {
        for (int i = start; i < end; i++) {
            this.cells[i] = style;
        }
    }

    public void setLineColor(Color c) {
        this.lineColor = c;
    }

    public Color getLineColor() {
        return this.lineColor;
    }

    public void addHighlight(Color color, int start, int end) {
        TextCellStyle style = null;
        for (int i = start; i < end; i++) {
            TextCellStyle old = this.cells[i];
            if (old == null) {
                if (style == null) {
                    style = new TextCellStyle(null, color, false, false, false, false);
                }
                this.cells[i] = style;
            } else {
                this.cells[i] = new TextCellStyle(old.getTextColor(), FX.mix(old.getBackgroundColor(), color, 0.85d), old.isBold(), old.isItalic(), old.isStrikeThrough(), old.isUnderscore());
            }
        }
    }
}
