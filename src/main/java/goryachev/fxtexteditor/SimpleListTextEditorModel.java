package goryachev.fxtexteditor;

import goryachev.common.util.CList;
import goryachev.common.util.text.IBreakIterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SimpleListTextEditorModel.class */
public class SimpleListTextEditorModel extends FxTextEditorModel {
    private final CList<ITextLine> lines = new CList<>();

    public void add(ITextLine line) {
        this.lines.add(line);
    }

    public void add(String text) {
        int line = this.lines.size();
        this.lines.add(new PlainTextLine(line, text));
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public int getLineCount() {
        return this.lines.size();
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(int line) {
        return this.lines.get(line);
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public Edit edit(Edit ed) throws Exception {
        throw new Exception();
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public IBreakIterator getBreakIterator() {
        return null;
    }
}
