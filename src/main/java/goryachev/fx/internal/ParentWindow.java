package goryachev.fx.internal;

import goryachev.fx.FxObject;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/ParentWindow.class */
public class ParentWindow {
    private final FxObject<Window> windowProperty = new FxObject<>();
    private final ChangeListener<Scene> sceneListener = new ChangeListener<Scene>() { // from class: goryachev.fx.internal.ParentWindow.1
        @Override // javafx.beans.value.ChangeListener
        public void changed(ObservableValue<? extends Scene> observable, Scene prev, Scene cur) {
            ParentWindow.this.handleSceneChange(prev, cur);
        }
    };

    public ParentWindow(Node n) {
        n.sceneProperty().addListener(this.sceneListener);
        Scene sc = n.getScene();
        update(sc);
    }

    protected void update(Scene sc) {
        if (sc == null) {
            this.windowProperty.set(null);
            return;
        }
        Window w = sc.getWindow();
        if (w == null) {
            this.windowProperty.set(null);
            return;
        }
        w.showingProperty().addListener(createShowingListener(w));
        if (w.isShowing()) {
            this.windowProperty.set(w);
        } else {
            this.windowProperty.set(null);
        }
    }

    public ReadOnlyObjectProperty<Window> windowProperty() {
        return this.windowProperty.getReadOnlyProperty();
    }

    protected ChangeListener<Boolean> createShowingListener(final Window w) {
        return new ChangeListener<Boolean>() { // from class: goryachev.fx.internal.ParentWindow.2
            @Override // javafx.beans.value.ChangeListener
            public void changed(ObservableValue<? extends Boolean> observable, Boolean prev, Boolean cur) {
                ParentWindow.this.handleShowingChange(w, cur.booleanValue());
            }
        };
    }

    protected void handleSceneChange(Scene old, Scene cur) {
        update(cur);
    }

    protected void handleShowingChange(Window w, boolean showing) {
        if (showing) {
            this.windowProperty.set(w);
        } else {
            this.windowProperty.set(null);
        }
    }
}
