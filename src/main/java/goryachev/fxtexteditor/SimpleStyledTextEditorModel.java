package goryachev.fxtexteditor;

import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import goryachev.common.util.ElasticIntArray;
import goryachev.common.util.SB;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fx.TextCellStyle;
import java.text.BreakIterator;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/SimpleStyledTextEditorModel.class */
public class SimpleStyledTextEditorModel extends FxTextEditorModel {
    private final ElasticIntArray lines;
    private final CList<Object> data;
    private final CMap<TextCellStyle, TextCellStyle> styles;
    private final TextCellStyle style;
    private final IBreakIterator breakIterator;
    private TextCellStyle prevStyle;

    public SimpleStyledTextEditorModel(IBreakIterator bi) {
        this.lines = new ElasticIntArray();
        this.data = new CList<>();
        this.styles = new CMap<>();
        this.style = new TextCellStyle();
        this.breakIterator = bi;
        setDefaultRtfCopyHandler();
        setDefaultHtmlCopyHandler();
    }

    public SimpleStyledTextEditorModel() {
        this(IBreakIterator.wrap(BreakIterator.getCharacterInstance()));
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public IBreakIterator getBreakIterator() {
        return this.breakIterator;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public int getLineCount() {
        return this.lines.size();
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(int line) {
        if (line < 0 || line >= getLineCount()) {
            return null;
        }
        int start = line == 0 ? 0 : this.lines.get(line - 1);
        int end = line < getLineCount() ? this.lines.get(line) : this.data.size();
        SB sb = new SB(128);
        CList<TextCellStyle> ss = new CList<>();
        TextCellStyle st = new TextCellStyle();
        for (int i = start; i < end; i++) {
            Object x = this.data.get(i);
            if (x instanceof String) {
                String s = x.toString();
                sb.append(s);
                for (int j = 0; j < s.length(); j++) {
                    ss.add(st);
                }
            } else if (x instanceof TextCellStyle) {
                st = (TextCellStyle) x;
            }
        }
        String text = sb.toString();
        TextCellStyle[] cs = (TextCellStyle[]) ss.toArray(new TextCellStyle[ss.size()]);
        return new SimpleStyledTextLine(line, text, null, cs);
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public Edit edit(Edit ed) throws Exception {
        throw new Error("this model is read only");
    }

    protected TextCellStyle style() {
        TextCellStyle s = this.styles.get(this.style);
        if (s == null) {
            s = this.style.copy();
            this.styles.put(s, s);
        }
        return s;
    }

    public SimpleStyledTextEditorModel setStyle(TextCellStyle st) {
        this.style.init(st);
        return this;
    }

    public SimpleStyledTextEditorModel append(String text) {
        TextCellStyle s = style();
        if (!CKit.equals(this.prevStyle, s)) {
            this.data.add(s);
            this.prevStyle = s;
        }
        this.data.add(text);
        return this;
    }

    public SimpleStyledTextEditorModel append(TextCellStyle st, String text) {
        setStyle(st);
        return append(text);
    }

    public SimpleStyledTextEditorModel nl() {
        this.lines.add(this.data.size());
        return this;
    }

    public SimpleStyledTextEditorModel setBold(boolean on) {
        this.style.setBold(on);
        return this;
    }

    public SimpleStyledTextEditorModel setItalic(boolean on) {
        this.style.setItalic(on);
        return this;
    }

    public SimpleStyledTextEditorModel setStrikeThrough(boolean on) {
        this.style.setStrikeThrough(on);
        return this;
    }

    public SimpleStyledTextEditorModel setUnderscore(boolean on) {
        this.style.setUnderscore(on);
        return this;
    }

    public SimpleStyledTextEditorModel setBackgroundColor(Color c) {
        this.style.setBackgroundColor(c);
        return this;
    }

    public SimpleStyledTextEditorModel setTextColor(Color c) {
        this.style.setTextColor(c);
        return this;
    }
}
