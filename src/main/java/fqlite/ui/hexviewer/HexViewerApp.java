package fqlite.ui.hexviewer;

import demo.fxtexteditor.MainWindow;
import demo.fxtexteditor.Styles;
import fqlite.base.GUI;
import goryachev.common.util.FileSettingsProvider;
import goryachev.common.util.GlobalSettings;
import goryachev.fx.CssLoader;
import goryachev.log.config.JsonLogConfig;
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/* loaded from: fqlite_next.jar:fqlite/ui/hexviewer/HexViewerApp.class */
public class HexViewerApp extends Application {
    static MainWindow mw;

    public static void main(String[] args) {
        JsonLogConfig.configure(new File("log-conf.json"), 1000L);
        launch(args);
    }

    @Override // javafx.application.Application
    public void init() throws Exception {
        new File(GUI.baseDir, "logs");
        File settingsFile = new File(GUI.baseDir, "settings.conf");
        System.out.println("settingsFile " + settingsFile.getAbsolutePath());
        FileSettingsProvider p = new FileSettingsProvider(settingsFile);
        GlobalSettings.setProvider(p);
        p.loadQuiet();
    }

    public void goTo(int offset) {
        mw.goTo(offset);
    }

    public void switchModel(String path) {
        mw.loadNewHexFile(path);
    }

    public void loadNewHexFile(String path) {
        mw.loadNewHexFile(path);
    }

    public void clearAll() {
        mw.clearAll();
    }

    public static void setVisible() {
        mw.setIconified(false);
        mw.toFront();
    }

    @Override // javafx.application.Application
    public void start(Stage stage) throws Exception {
        init();
        mw = new MainWindow();
        try {
            mw.open();
            Platform.setImplicitExit(false);
            mw.setOnCloseRequest(e -> {
                e.consume();
                mw.setIconified(true);
            });
            mw.setIconified(true);
        } catch (Exception err) {
            err.printStackTrace();
        }
        CssLoader.setStyles(() -> {
            return new Styles();
        });
    }
}
