package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.ITextSource;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/SelectedTextSource.class */
public class SelectedTextSource implements ITextSource {
    private final FxTextEditorModel model;
    private final int startLine;
    private final int startPos;
    private final int endLine;
    private final int endPos;
    private int current;
    private int start;
    private int end;

    public SelectedTextSource(FxTextEditorModel m, int startLine, int startPos, int endLine, int endPos) {
        this.model = m;
        this.startLine = startLine;
        this.startPos = startPos;
        this.endLine = endLine;
        this.endPos = endPos;
        this.current = startLine;
    }

    @Override // goryachev.fxtexteditor.ITextSource
    public String nextPlainTextLine() {
        if (this.current > this.endLine) {
            this.start = -1;
            this.end = -1;
            return null;
        }
        String t = this.model.getPlainText(this.current);
        if (t == null) {
            t = ButtonBar.BUTTON_ORDER_NONE;
        }
        if (this.current == this.startLine) {
            if (this.startLine == this.endLine) {
                this.start = this.startPos;
                this.end = this.endPos;
            } else {
                this.start = this.startPos;
                this.end = t.length();
            }
        } else if (this.current < this.endLine) {
            this.start = 0;
            this.end = t.length();
        } else {
            this.start = 0;
            this.end = this.endPos;
        }
        this.current++;
        return t;
    }

    @Override // goryachev.fxtexteditor.ITextSource
    public ITextLine nextLine() {
        if (this.current > this.endLine) {
            this.start = -1;
            this.end = -1;
            return null;
        }
        ITextLine t = this.model.getTextLine(this.current);
        if (this.current == this.startLine) {
            if (this.startLine == this.endLine) {
                this.start = this.startPos;
                this.end = this.endPos;
            } else {
                this.start = this.startPos;
                this.end = t.getTextLength();
            }
        } else if (this.current < this.endLine) {
            this.start = 0;
            this.end = t.getTextLength();
        } else {
            this.start = 0;
            this.end = this.endPos;
        }
        this.current++;
        return t;
    }

    @Override // goryachev.fxtexteditor.ITextSource
    public int getStart() {
        return this.start;
    }

    @Override // goryachev.fxtexteditor.ITextSource
    public int getEnd() {
        return this.end;
    }
}
