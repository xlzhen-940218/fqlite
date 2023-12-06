package goryachev.fxtexteditor;

import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/AStyledCellTextLine.class */
public abstract class AStyledCellTextLine implements ITextLine {
    protected final int line;
    protected final String plainText;
    private CellStyleArray styles;

    protected abstract CellStyleArray createCellStyleArray(String str);

    public AStyledCellTextLine(int line, String text) {
        this.line = line;
        this.plainText = text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getTextLength() {
        return this.plainText.length();
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public String getPlainText() {
        return this.plainText;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getModelIndex() {
        return this.line;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getLineNumber() {
        return this.line;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public TextCellStyle getCellStyle(int charOffset) {
        return styles().get(charOffset);
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return styles().getLineColor();
    }

    protected CellStyleArray styles() {
        if (this.styles == null) {
            this.styles = createCellStyleArray(this.plainText);
        }
        return this.styles;
    }
}
