package goryachev.fxtexteditor;

import goryachev.common.util.text.IBreakIterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/InMemoryPlainTextEditorModel.class */
public class InMemoryPlainTextEditorModel extends FxTextEditorModel {
    protected final String[] lines;

    public InMemoryPlainTextEditorModel(String[] lines) {
        this.lines = lines;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public IBreakIterator getBreakIterator() {
        return null;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public Edit edit(Edit ed) throws Exception {
        throw new Exception("not supported");
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public int getLineCount() {
        return this.lines.length;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(int line) {
        if (line < getLineCount()) {
            String text = this.lines[line];
            if (text != null) {
                if (text.endsWith("\r")) {
                    text = text.substring(0, text.length() - 1);
                }
                return new PlainTextLine(line, text);
            }
            return null;
        }
        return null;
    }
}
