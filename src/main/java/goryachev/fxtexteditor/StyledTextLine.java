package goryachev.fxtexteditor;

import goryachev.common.util.CList;
import goryachev.common.util.SB;
import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/StyledTextLine.class */
public class StyledTextLine implements ITextLine {
    private final int lineNumber;
    private Color lineColor;
    private final SB sb = new SB();
    private final CList<TextCellStyle> styles = new CList<>();

    public StyledTextLine(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public int getTextLength() {
        return this.sb.length();
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public String getPlainText() {
        return this.sb.toString();
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
        return this.styles.get(charOffset);
    }

    @Override // goryachev.fxtexteditor.ITextLine
    public Color getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(Color c) {
        this.lineColor = c;
    }

    public void append(TextCellStyle st, String text) {
        for (int i = 0; i < text.length(); i++) {
            this.styles.add(st);
        }
        this.sb.append(text);
    }

    public void append(String text) {
        for (int i = 0; i < text.length(); i++) {
            this.styles.add(null);
        }
        this.sb.append(text);
    }

    public void append(TextCellStyle st, char ch) {
        this.styles.add(st);
        this.sb.append(ch);
    }

    public void append(char ch) {
        this.styles.add(null);
        this.sb.append(ch);
    }
}
