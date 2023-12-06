package demo.fxtexteditor;

import goryachev.fx.CPane;
import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import goryachev.fx.FxMenu;
import goryachev.fx.FxPopupMenu;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.internal.TabPolicy;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/MainPane.class */
public class MainPane extends CPane {
    public static final CssStyle PANE = new CssStyle("MainPane_PANE");
    public final FxTextEditor editor;

    public MainPane() {
        FX.style(this, PANE);
        this.editor = new FxTextEditor();
        this.editor.setContentPadding(FX.insets(2.0d, 4.0d));
        this.editor.setBlinkRate(Duration.millis(600.0d));
        this.editor.setWrapLines(false);
        this.editor.setTabPolicy(TabPolicy.create(4));
        this.editor.setLineNumberFormatter(new OffsetFormatter());
        setCenter(this.editor);
        showFindPane();
        FX.setPopupMenu(this.editor, this::createPopupMenu);
    }

    protected FxPopupMenu createPopupMenu() {
        FxPopupMenu p = new FxPopupMenu();
        FxMenu m = p.menu("Copy", this.editor.actions.copy());
        m.item("Copy Plain Text", this.editor.actions.copyPlainText());
        p.separator();
        p.item("Select All", this.editor.actions.selectAll());
        return p;
    }

    public void setModel(FxTextEditorModel m) {
        this.editor.setModel(m);
    }

    public FxTextEditorModel getModel() {
        return this.editor.getModel();
    }

    public void showFindPane() {
    }
}
