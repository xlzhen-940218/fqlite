package goryachev.fxtexteditor;

import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SimpleStyledTextLine.class */
public class SimpleStyledTextLine implements ITextLine {
    private final int lineNumber;
    private final String text;
    private final Color lineColor;
    private final TextCellStyle[] styles;

    public SimpleStyledTextLine(int lineNumber, String text, Color lineColor, TextCellStyle[] styles) {
        this.lineNumber = lineNumber;
        this.text = text;
        this.lineColor = lineColor;
        this.styles = styles;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getTextLength() {
        return this.text.length();
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public String getPlainText() {
        return this.text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getModelIndex() {
        return this.lineNumber;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public TextCellStyle getCellStyle(int charOffset) {
        return this.styles[charOffset];
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return this.lineColor;
    }
}
