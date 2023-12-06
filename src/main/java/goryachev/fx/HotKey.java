package goryachev.fx;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/HotKey.class */
public class HotKey {
    private final String id;
    private KeyCombination key;
    private Runnable action;

    public HotKey(String id, KeyCombination key) {
        this.id = id;
        this.key = key;
    }

    public void setKeyCombination(KeyCombination k) {
    }

    public boolean isSet() {
        return (this.key == null || this.action == null) ? false : true;
    }

    public void attach(Node n) {
        if (isSet()) {
            Mnemonic m = new Mnemonic(n, this.key);
            Scene sc = n.getScene();
            if (sc == null) {
                n.sceneProperty().addListener((observableValue, p, c) -> {
                    if (c != null) {
                        c.addMnemonic(m);
                        n.sceneProperty().removeListener((ChangeListener) this);
                    }
                });
            } else {
                sc.addMnemonic(m);
            }
        }
    }

    public void attach(Window w) {
        Scene sc;
        if (isSet() && (sc = w.getScene()) != null) {
            sc.getAccelerators().put(this.key, this.action);
        }
    }

    public void attach(MenuItem m) {
        if (this.key != null) {
            m.setAccelerator(this.key);
        }
    }
}
