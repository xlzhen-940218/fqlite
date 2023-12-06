package demo.fxtexteditor;

import goryachev.common.util.Parsers;
import goryachev.fx.CPane;
import goryachev.fx.FxComboBox;
import goryachev.fx.FxMenuBar;
import goryachev.fx.FxToolBar;
import goryachev.fx.FxWindow;
import goryachev.fx.internal.LocalSettings;
import goryachev.fxtexteditor.Actions;
import goryachev.fxtexteditor.FileCachePlainTextEditorModel;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.internal.Markers;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/MainWindow.class */
public class MainWindow extends FxWindow {
    public final MainPane mainPane;
    public final CPane content;
    public final StatusBar statusBar;
    protected final FxComboBox<Object> modelSelector;
    protected final FxComboBox<Object> fontSelector;
    protected Hashtable<Object, FileCachePlainTextEditorModel> files;
    Markers myselection;

    public void loadNewHexFile(String path) {
        Path pp = Paths.get(path, new String[0]);
        AnItem newhex = new AnItem(path, pp.getFileName().toString());
        if (this.files.containsKey(path)) {
            this.modelSelector.select(newhex);
            return;
        }
        FileCachePlainTextEditorModel m = new FileCachePlainTextEditorModel(new File(path));
        try {
            Thread.currentThread();
            Thread.sleep(50L);
        } catch (InterruptedException e) {
        }
        this.modelSelector.addItem(newhex);
        this.files.put(path, m);
        this.modelSelector.select(this.modelSelector.getItems().size() - 1);
    }

    public MainWindow() {
        super("HexViewer");
        this.modelSelector = new FxComboBox<>();
        this.fontSelector = new FxComboBox<>();
        this.files = new Hashtable<>();
        this.myselection = new Markers(100);
        AnItem EMPTY = new AnItem("EMTPY", "<EMPTY>");
        this.modelSelector.addItem(EMPTY);
        this.modelSelector.select(0);
        this.modelSelector.valueProperty().addListener((observableValue, p, c) -> {
            onModelSelectionChange(c);
        });
        this.fontSelector.setItems("9", "12", "18", "24");
        this.fontSelector.valueProperty().addListener((observableValue2, p2, c2) -> {
            onFontChange(c2);
        });
        this.mainPane = new MainPane();
        this.content = new CPane();
        this.content.setTop(createToolbar());
        this.content.setCenter(this.mainPane);
        this.statusBar = new StatusBar();
        setTitle("HexView");
        setTop(createMenu());
        setCenter(this.content);
        VBox vb = new VBox();
        vb.getChildren().addAll(new ValuePanel(editor()), this.statusBar);
        setBottom(vb);
        setSize(680.0d, 700.0d);
        this.fontSelector.setEditable(false);
        this.fontSelector.select( "18");
        LocalSettings.get(this).add("SHOW_LINE_NUMBERS", editor().showLineNumbersProperty()).add("MODEL", this.modelSelector).add("FONT_SIZE", this.fontSelector);
        this.statusBar.attach(editor());
        editor().setShowLineNumbers(true);
    }

    protected FxTextEditor editor() {
        return this.mainPane.editor;
    }

    protected Node createMenu() {
        FxMenuBar m = new FxMenuBar();
        Actions a = editor().actions;
        m.menu("Action");
        m.item("Copy", a.copy());
        return m;
    }

    protected Node createToolbar() {
        FxToolBar t = new FxToolBar();
        t.add(new Label("Font:"));
        t.add(this.fontSelector);
        t.space();
        t.add(new Label("Model:"));
        t.space(2);
        t.add(this.modelSelector);
        return t;
    }

    protected void preferences() {
    }

    protected void newWindow() {
        MainWindow w = new MainWindow();
        w.mainPane.setModel(this.mainPane.getModel());
        w.open();
    }

    protected void onModelSelectionChange(Object x) {
        if (x instanceof AnItem) {
            AnItem s = (AnItem) x;
            FileCachePlainTextEditorModel m = this.files.get(s.getCode());
            this.mainPane.setModel(m);
            if (m == null) {
                return;
            }
            this.statusBar.setTotal((int) m.length());
        }
    }

    protected void onFontChange(Object x) {
        int sz = Parsers.parseInt(x, 18);
        this.mainPane.editor.setFontSize(sz);
    }

    public void select() {
        editor().select(this.myselection.newMarker(2, 3), this.myselection.newMarker(3, 14));
        editor().select(this.myselection.newMarker(20, 5), this.myselection.newMarker(20, 15));
    }

    public void goTo(int offset) {
        editor().goToLine(offset / 16);
    }

    public void clearAll() {
        this.modelSelector.getSelectionModel().clearSelection();
        this.modelSelector.setValue(null);
        this.modelSelector.getItems().clear();
        this.files.clear();
        AnItem EMPTY = new AnItem("EMTPY", "<EMPTY>");
        this.modelSelector.addItem(EMPTY);
        this.modelSelector.select(0);
    }
}
