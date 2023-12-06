package goryachev.fx;

import javafx.application.Application;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxApplication.class */
public abstract class FxApplication extends Application {
    private static FxApplication instance;

    public FxApplication() {
        if (instance != null) {
            throw new Error("there could be only one FxApplication");
        }
        instance = this;
    }

    public static FxApplication getInstance() {
        if (instance == null) {
            throw new Error("your application must extend FxApplication");
        }
        return instance;
    }
}
