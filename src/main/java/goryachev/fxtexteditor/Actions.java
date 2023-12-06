package goryachev.fxtexteditor;

import goryachev.fx.FX;
import goryachev.fx.FxAction;
import goryachev.fxtexteditor.op.Backspace;
import goryachev.fxtexteditor.op.Copy;
import goryachev.fxtexteditor.op.CopyHTML;
import goryachev.fxtexteditor.op.CopyPlainText;
import goryachev.fxtexteditor.op.CopyRTF;
import goryachev.fxtexteditor.op.Delete;
import goryachev.fxtexteditor.op.MoveDocumentEnd;
import goryachev.fxtexteditor.op.MoveDocumentEndAtPos0;
import goryachev.fxtexteditor.op.MoveDocumentStart;
import goryachev.fxtexteditor.op.MoveDown;
import goryachev.fxtexteditor.op.MoveEnd;
import goryachev.fxtexteditor.op.MoveHome;
import goryachev.fxtexteditor.op.MoveLeft;
import goryachev.fxtexteditor.op.MoveRight;
import goryachev.fxtexteditor.op.MoveUp;
import goryachev.fxtexteditor.op.PageDown;
import goryachev.fxtexteditor.op.PageUp;
import goryachev.fxtexteditor.op.SelectAll;
import goryachev.fxtexteditor.op.SmartCopy;
import goryachev.fxtexteditor.op.SmartCopyHTML;
import goryachev.fxtexteditor.op.SmartCopyPlainText;
import goryachev.fxtexteditor.op.SmartCopyRTF;
import javafx.beans.value.ObservableValue;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/Actions.class */
public class Actions {
    private final FxAction backspace;
    private final FxAction copy;
    private FxAction copyHtml;
    private FxAction copyPlainText;
    private FxAction copyRtf;
    private final FxAction delete;
    private final FxAction moveDocumentEnd;
    private MoveDocumentEndAtPos0 moveDocumentEndAtPos0;
    private final FxAction moveDocumentStart;
    private final FxAction moveDown;
    private final FxAction moveEnd;
    private final FxAction moveHome;
    private final FxAction moveLeft;
    private final FxAction moveRight;
    private final FxAction moveUp;
    private final FxAction pageDown;
    private final FxAction pageUp;
    private final FxAction selectAll;
    private FxAction smartCopy;
    private FxAction smartCopyHtml;
    private FxAction smartCopyPlainText;
    private FxAction smartCopyRtf;
    private final FxTextEditor editor;

    public Actions(FxTextEditor ed) {
        this.editor = ed;
        this.backspace = new Backspace(ed);
        this.copy = new Copy(ed);
        this.delete = new Delete(ed);
        this.moveDocumentEnd = new MoveDocumentEnd(ed);
        this.moveDocumentStart = new MoveDocumentStart(ed);
        this.moveDown = new MoveDown(ed);
        this.moveEnd = new MoveEnd(ed);
        this.moveHome = new MoveHome(ed);
        this.moveLeft = new MoveLeft(ed);
        this.moveRight = new MoveRight(ed);
        this.moveUp = new MoveUp(ed);
        this.pageDown = new PageDown(ed);
        this.pageUp = new PageUp(ed);
        this.selectAll = new SelectAll(ed);
        FX.onChange(this::handleSelectionChange, true, (ObservableValue<?>[]) new ObservableValue[]{ed.selectionProperty()});
    }

    protected void handleSelectionChange() {
        boolean on = this.editor.getNonEmptySelection() != null;
        this.copy.setEnabled(on);
        if (this.copyHtml != null) {
            this.copyHtml.setEnabled(on);
        }
        if (this.copyPlainText != null) {
            this.copyPlainText.setEnabled(on);
        }
        if (this.copyRtf != null) {
            this.copyRtf.setEnabled(on);
        }
    }

    public FxAction backspace() {
        return this.backspace;
    }

    public FxAction copy() {
        return this.copy;
    }

    public FxAction smartCopy() {
        if (this.smartCopy == null) {
            this.smartCopy = new SmartCopy(this.editor);
        }
        return this.smartCopy;
    }

    public FxAction copyHtml() {
        if (this.copyHtml == null) {
            this.copyHtml = new CopyHTML(this.editor);
        }
        return this.copyHtml;
    }

    public FxAction smartCopyHtml() {
        if (this.smartCopyHtml == null) {
            this.smartCopyHtml = new SmartCopyHTML(this.editor);
        }
        return this.smartCopyHtml;
    }

    public FxAction copyPlainText() {
        if (this.copyPlainText == null) {
            this.copyPlainText = new CopyPlainText(this.editor);
        }
        return this.copyPlainText;
    }

    public FxAction smartCopyPlainText() {
        if (this.smartCopyPlainText == null) {
            this.smartCopyPlainText = new SmartCopyPlainText(this.editor);
        }
        return this.smartCopyPlainText;
    }

    public FxAction copyRtf() {
        if (this.copyRtf == null) {
            this.copyRtf = new CopyRTF(this.editor);
        }
        return this.copyRtf;
    }

    public FxAction smartCopyRtf() {
        if (this.smartCopyRtf == null) {
            this.smartCopyRtf = new SmartCopyRTF(this.editor);
        }
        return this.smartCopyRtf;
    }

    public FxAction delete() {
        return this.delete;
    }

    public FxAction moveDocumentEnd() {
        return this.moveDocumentEnd;
    }

    public MoveDocumentEndAtPos0 moveDocumentEndAtPos0() {
        if (this.moveDocumentEndAtPos0 == null) {
            this.moveDocumentEndAtPos0 = new MoveDocumentEndAtPos0(this.editor);
        }
        return this.moveDocumentEndAtPos0;
    }

    public FxAction moveDocumentStart() {
        return this.moveDocumentStart;
    }

    public FxAction moveDown() {
        return this.moveDown;
    }

    public FxAction moveEnd() {
        return this.moveEnd;
    }

    public FxAction moveHome() {
        return this.moveHome;
    }

    public FxAction moveLeft() {
        return this.moveLeft;
    }

    public FxAction moveRight() {
        return this.moveRight;
    }

    public FxAction moveUp() {
        return this.moveUp;
    }

    public FxAction pageDown() {
        return this.pageDown;
    }

    public FxAction pageUp() {
        return this.pageUp;
    }

    public FxAction selectAll() {
        return this.selectAll;
    }
}
