package goryachev.fx.internal;

import javafx.stage.Stage;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/FxWindowBoundsMonitor.class */
public class FxWindowBoundsMonitor {
    private final Stage win;
    private Duplet x = new Duplet();
    private Duplet y = new Duplet();
    private Duplet w = new Duplet();
    private Duplet h = new Duplet();

    public FxWindowBoundsMonitor(Stage w) {
        this.win = w;
        w.xProperty().addListener(p -> {
            updateX();
        });
        w.yProperty().addListener(p2 -> {
            updateY();
        });
        w.widthProperty().addListener(p3 -> {
            updateWidth();
        });
        w.heightProperty().addListener(p4 -> {
            updateHeight();
        });
        w.maximizedProperty().addListener(x -> {
            updateMaximized();
        });
        w.iconifiedProperty().addListener(x2 -> {
            updateMinimized();
        });
        w.fullScreenProperty().addListener(x3 -> {
            updateFullScreen();
        });
    }

    public double getX() {
        return this.x.get();
    }

    public double getY() {
        return this.y.get();
    }

    public double getWidth() {
        return this.w.get();
    }

    public double getHeight() {
        return this.h.get();
    }

    protected void updateMaximized() {
        if (this.win.maximizedProperty().get()) {
            this.x.discard();
            this.y.discard();
        }
    }

    protected void updateMinimized() {
        if (this.win.iconifiedProperty().get()) {
            this.x.discard();
            this.y.discard();
        }
    }

    protected void updateFullScreen() {
        if (this.win.fullScreenProperty().get()) {
            this.x.discard();
            this.y.discard();
            this.w.discard();
            this.h.discard();
        }
    }

    protected void updateX() {
        double v = this.win.xProperty().get();
        this.x.set(v);
    }

    protected void updateY() {
        double v = this.win.yProperty().get();
        this.y.set(v);
    }

    protected void updateWidth() {
        boolean use = !this.win.isMaximized();
        if (use) {
            double v = this.win.widthProperty().get();
            this.w.set(v);
        }
    }

    protected void updateHeight() {
        boolean use = !this.win.isMaximized();
        if (use) {
            double v = this.win.heightProperty().get();
            this.h.set(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/FxWindowBoundsMonitor$Duplet.class */
    public class Duplet {
        private double value;
        private double prev;

        Duplet() {
        }

        public void set(double v) {
            this.prev = this.value;
            this.value = v;
        }

        public double get() {
            return this.value;
        }

        public void discard() {
            this.value = this.prev;
        }
    }
}
