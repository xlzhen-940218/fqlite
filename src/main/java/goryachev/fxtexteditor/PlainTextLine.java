package goryachev.fxtexteditor;

import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/PlainTextLine.class */
public class PlainTextLine implements ITextLine {
    private final int line;
    private final String text;

    public PlainTextLine(int line, String text) {
        this.line = line;
        this.text = text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getLineNumber() {
        return this.line;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getModelIndex() {
        return this.line;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public String getPlainText() {
        return this.text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getTextLength() {
        if (this.text == null) {
            return 0;
        }
        return this.text.length();
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public TextCellStyle getCellStyle(int offset) {
        return null;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return null;
    }
}
