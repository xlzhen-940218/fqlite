package goryachev.fx;

import java.util.function.Consumer;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/XScrollBar.class */
public class XScrollBar extends ScrollBar {
    private Canvas canvas;
    private Consumer<Canvas> painter;

    public XScrollBar() {
        setStyle("-fx-background-color:transparent; -fx-background-insets:0px;");
    }

    public void setPainter(Consumer<Canvas> p) {
        if (this.canvas != null) {
            getChildren().remove(this.canvas);
            this.canvas = null;
        }
        this.painter = p;
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.control.Control, javafx.scene.Parent
    public void layoutChildren() {
        super.layoutChildren();
        if (this.painter == null) {
            return;
        }
        double w = getWidth();
        double h = getHeight();
        if (this.canvas == null || this.canvas.getWidth() != w || this.canvas.getHeight() != h) {
            if (this.canvas != null) {
                getChildren().remove(this.canvas);
            }
            this.canvas = new Canvas(w, h);
            this.canvas.setManaged(false);
            getChildren().add(0, this.canvas);
            layoutInArea(this.canvas, 0.0d, 0.0d, w, h, 0.0d, null, true, true, HPos.CENTER, VPos.CENTER);
            this.painter.accept(this.canvas);
        }
    }
}
