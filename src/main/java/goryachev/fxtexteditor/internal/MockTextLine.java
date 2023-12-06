package goryachev.fxtexteditor.internal;

import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.ITextLine;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/MockTextLine.class */
public class MockTextLine implements ITextLine {
    private final String text;

    public MockTextLine(String text) {
        this.text = text;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getLineNumber() {
        return 0;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getModelIndex() {
        return 0;
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
        return null;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return null;
    }
}
