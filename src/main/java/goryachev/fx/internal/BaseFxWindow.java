package goryachev.fx.internal;

import goryachev.fx.FxObject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.Node;
import javafx.stage.Stage;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/BaseFxWindow.class */
public class BaseFxWindow extends Stage {
    private final FxWindowBoundsMonitor normalBoundsMonitor = new FxWindowBoundsMonitor(this);
    private static final FxObject<Node> lastFocusOwner = new FxObject<>();

    public BaseFxWindow() {
        sceneProperty().addListener((observableValue, prev, cur) -> {
            if (cur != null) {
                cur.focusOwnerProperty().addListener((observableValue1, p, val) -> {
                    updateFocusOwner(val);
                });
            }
        });
    }

    protected void updateFocusOwner(Node n) {
        if (n != null) {
            lastFocusOwner.set(n);
        }
    }

    public static Node getLastFocusOwner() {
        return lastFocusOwner.get();
    }

    public static ReadOnlyObjectProperty<Node> lastFocusOwnerProperty() {
        return lastFocusOwner.getReadOnlyProperty();
    }

    public final double getNormalX() {
        return this.normalBoundsMonitor.getX();
    }

    public final double getNormalY() {
        return this.normalBoundsMonitor.getY();
    }

    public final double getNormalWidth() {
        return this.normalBoundsMonitor.getWidth();
    }

    public final double getNormalHeight() {
        return this.normalBoundsMonitor.getHeight();
    }

    public void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public void setMinSize(double width, double height) {
        setMinWidth(width);
        setMinHeight(height);
        setSize(width, height);
    }

    public void setMaxSize(double width, double height) {
        setMaxWidth(width);
        setMaxHeight(height);
    }
}
