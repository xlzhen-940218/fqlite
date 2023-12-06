package goryachev.fx;

import javafx.beans.property.Property;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxMenu.class */
public class FxMenu extends Menu {
    public FxMenu(String text) {
        super(text);
    }

    public FxMenu(String text, FxAction a) {
        super(text);
        a.attach(this);
    }

    public FxMenu(String text, Runnable r) {
        super(text);
        new FxAction(r).attach(this);
    }

    public SeparatorMenuItem separator() {
        SeparatorMenuItem m = new SeparatorMenuItem();
        getItems().add(m);
        return m;
    }

    public FxMenuItem item(String text, FxAction a) {
        FxMenuItem m = new FxMenuItem(text, a);
        getItems().add(m);
        return m;
    }

    public FxMenuItem item(String text, Runnable r) {
        FxMenuItem m = new FxMenuItem(text, r);
        getItems().add(m);
        return m;
    }

    public FxCheckMenuItem item(String text, Property<Boolean> prop) {
        FxCheckMenuItem m = new FxCheckMenuItem(text, prop);
        getItems().add(m);
        return m;
    }

    public MenuItem add(MenuItem m) {
        getItems().add(m);
        return m;
    }

    public MenuItem item(String text) {
        FxMenuItem m = new FxMenuItem(text);
        m.setDisable(true);
        return add(m);
    }

    public void clear() {
        getItems().clear();
    }
}
