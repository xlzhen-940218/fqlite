package demo.fxtexteditor;

import goryachev.common.util.FileSettingsProvider;
import goryachev.common.util.GlobalSettings;
import goryachev.fx.CssLoader;
import goryachev.log.config.JsonLogConfig;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/FxTextEditorDemoApp.class */
public class FxTextEditorDemoApp extends Application {
    public static void main(String[] args) {
        JsonLogConfig.configure(new File("log-conf.json"), 1000L);
        launch(args);
    }

    @Override // javafx.application.Application
    public void init() throws Exception {
        File baseDir = new File(System.getProperty("user.home"), ".fqlite/HexViewFX");
        new File(baseDir, "logs");
        File settingsFile = new File(baseDir, "settings.conf");
        FileSettingsProvider p = new FileSettingsProvider(settingsFile);
        GlobalSettings.setProvider(p);
        p.loadQuiet();
    }

    @Override // javafx.application.Application
    public void start(Stage stage) throws Exception {
        new MainWindow().open();
        CssLoader.setStyles(() -> {
            return new Styles();
        });
    }
}
