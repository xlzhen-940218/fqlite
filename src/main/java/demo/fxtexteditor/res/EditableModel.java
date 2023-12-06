package demo.fxtexteditor.res;

import goryachev.common.util.CList;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fxtexteditor.Edit;
import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.PlainTextLine;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/res/EditableModel.class */
public class EditableModel extends FxTextEditorModel {
    protected final CList<String> lines = new CList<>();

    public EditableModel() {
        setEditable(true);
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public int getLineCount() {
        return this.lines.size();
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(int line) {
        String text = this.lines.get(line);
        return new PlainTextLine(line, text);
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public IBreakIterator getBreakIterator() {
        return null;
    }

    protected void setText(int ix, String text) {
        if (ix < this.lines.size()) {
            this.lines.set(ix, text);
        } else if (ix == this.lines.size()) {
            this.lines.add(text);
        } else {
            throw new Error("line=" + ix + " lineCount=" + getLineCount());
        }
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public Edit edit(Edit edit) throws Exception {
        String tail;
        String text;
        int line0 = edit.getMinLine();
        int pos0 = edit.getMinCharIndex();
        String text0 = getPlainText(line0);
        if (text0 == null) {
            text0 = ButtonBar.BUTTON_ORDER_NONE;
        }
        String head = text0.substring(0, pos0);
        int line2 = edit.getMaxLine();
        int pos2 = edit.getMaxCharIndex();
        if (line0 == line2) {
            tail = text0.substring(pos2);
        } else {
            String text2 = getPlainText(line2);
            if (text2 == null) {
                text2 = ButtonBar.BUTTON_ORDER_NONE;
            }
            tail = text2.substring(pos2);
        }
        if (edit.isText()) {
            String added = edit.getText();
            String s = String.valueOf(head) + added + tail;
            setText(line0, s);
            int mx = Math.min(line2, getLineCount() - 1);
            for (int i = mx; i > line0; i--) {
                this.lines.remove(i);
            }
            fireTextAltered(line0, pos0, line2, pos2, added.length(), line0 - line2, 0);
            return null;
        }
        String[] added2 = edit.getTextLines();
        int last = added2.length - 1;
        int ix = line0;
        for (int i2 = 0; i2 <= last; i2++) {
            if (i2 == 0) {
                text = String.valueOf(head) + added2[i2];
            } else if (i2 == last) {
                text = String.valueOf(added2[i2]) + tail;
            } else {
                text = added2[i2];
            }
            if (ix <= line2) {
                setText(ix, text);
            } else {
                this.lines.add(ix, text);
            }
            ix++;
        }
        int mx2 = ix;
        for (int i3 = line2; i3 > mx2; i3--) {
            this.lines.remove(i3);
        }
        fireTextAltered(line0, pos0, line2, pos2, added2[0].length(), ((line0 - line2) + added2.length) - 1, added2[last].length());
        return null;
    }

    @Deprecated
    public Edit edit_old(Edit edit) throws Exception {
        String text;
        int line0 = edit.getMinLine();
        if (edit.isOnSameLine()) {
            if (edit.isText()) {
                String add = edit.getText();
                int p0 = edit.getMinCharIndex();
                int p1 = edit.getMaxCharIndex();
                String old = getPlainText(line0);
                if (old == null) {
                    old = ButtonBar.BUTTON_ORDER_NONE;
                }
                if (p0 == p1) {
                    if (p0 < old.length()) {
                        text = String.valueOf(old.substring(0, p0)) + add + old.substring(p0);
                    } else {
                        text = String.valueOf(old) + add;
                    }
                } else {
                    if (p1 < old.length()) {
                        text = String.valueOf(old.substring(0, p0)) + add + old.substring(p1);
                    } else {
                        text = String.valueOf(old.substring(0, p0)) + add;
                    }
                    old.substring(p0, p1);
                }
                if (line0 < getLineCount()) {
                    this.lines.set(line0, text);
                } else if (line0 == getLineCount()) {
                    this.lines.add(text);
                } else {
                    throw new Error("line=" + line0 + " lineCount=" + getLineCount());
                }
                fireTextAltered(line0, p0, p1, add.length());
                return null;
            }
            edit.getTextLines();
            throw new Error("todo");
        }
        throw new Error("todo");
    }
}
