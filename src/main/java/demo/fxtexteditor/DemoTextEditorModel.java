package demo.fxtexteditor;

import goryachev.common.util.CKit;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.Edit;
import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.PlainTextLine;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/DemoTextEditorModel.class */
public class DemoTextEditorModel extends FxTextEditorModel {
    protected final String[] lines;
    protected final int lineCount;
    private static TAttributes NONE = new TAttributes();
    private static int cachedStylesLine = -1;
    private static TSegment cachedSegment;

    public DemoTextEditorModel(String text, int lineCount) {
        this.lines = CKit.split((CharSequence) text, '\n');
        this.lineCount = lineCount <= 0 ? this.lines.length : lineCount;
        setDefaultRtfCopyHandler();
        setDefaultHtmlCopyHandler();
    }

    public DemoTextEditorModel(String text) {
        this(text, -1);
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
        return this.lineCount;
    }

    protected String plainText(int line) {
        if (line < 0) {
            throw new IllegalArgumentException("line=" + line);
        }
        if (line < getLineCount()) {
            int ix = line % this.lines.length;
            String s = this.lines[ix];
            if (s.length() > 0) {
                switch (s.charAt(s.length() - 1)) {
                    case '\n':
                    case '\r':
                        return s.substring(0, s.length() - 1);
                }
            }
            return s;
        }
        return null;
    }

    protected TAttributes applySyntax(String text) {
        if (CKit.isBlank(text)) {
            return NONE;
        }
        TAttributes a = new TAttributes();
        for (TSegment seg : new DemoSyntax(text).generateSegments()) {
            a.addSegment(seg);
        }
        return a;
    }

    protected TSegment fastGetSegment(int line, int off, TAttributes attributes) {
        if (line == cachedStylesLine && cachedSegment != null && cachedSegment != null && cachedSegment.contains(off)) {
            return cachedSegment;
        }
        TSegment seg = attributes.getSegmentAt(off);
        cachedStylesLine = line;
        cachedSegment = seg;
        return seg;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(final int line) {
        String text = plainText(line);
        if (text != null) {
            return new PlainTextLine(line, text) { // from class: demo.fxtexteditor.DemoTextEditorModel.1
                private TAttributes attributes;

                @Override // goryachev.fxtexteditor.PlainTextLine, goryachev.fxtexteditor.ITextLine
                public TextCellStyle getCellStyle(int off) {
                    if (this.attributes == null) {
                        String text2 = getPlainText();
                        this.attributes = DemoTextEditorModel.this.applySyntax(text2);
                    }
                    TSegment seg = DemoTextEditorModel.this.fastGetSegment(line, off, this.attributes);
                    if (seg != null) {
                        return seg.style;
                    }
                    return null;
                }
            };
        }
        return null;
    }
}
