package goryachev.fxtexteditor;

import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SimpleTextLine.class */
public class SimpleTextLine implements ITextLine {
    private final int lineNumber;
    private final TextCellStyle style;
    private final String text;

    public SimpleTextLine(int lineNumber, TextCellStyle style, String text) {
        this.lineNumber = lineNumber;
        this.style = style;
        this.text = text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getModelIndex() {
        return this.lineNumber;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public String getPlainText() {
        return this.text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getTextLength() {
        return this.text.length();
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public TextCellStyle getCellStyle(int charOffset) {
        return this.style;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return null;
    }
}
