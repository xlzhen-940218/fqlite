package goryachev.fx;

import goryachev.common.util.CSorter;
import java.util.List;
import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxMenuBar.class */
public class FxMenuBar extends MenuBar {
    public void add(FxMenu m) {
        getMenus().add(m);
    }

    public FxMenu menu(String text) {
        FxMenu m = new FxMenu(text);
        getMenus().add(m);
        return m;
    }

    public FxMenu menu(String text, FxAction a) {
        FxMenu m = new FxMenu(text, a);
        getMenus().add(m);
        return m;
    }

    public FxCheckMenuItem checkItem(String text, FxAction a) {
        FxCheckMenuItem m = new FxCheckMenuItem(text, a);
        add(m);
        return m;
    }

    public FxCheckMenuItem checkItem(String text, GlobalBooleanProperty p) {
        FxCheckMenuItem m = new FxCheckMenuItem(text, p);
        add(m);
        return m;
    }

    public FxCheckMenuItem checkItem(String text, Property<Boolean> p) {
        FxCheckMenuItem m = new FxCheckMenuItem(text, p);
        add(m);
        return m;
    }

    public void addFill() {
    }

    public void add(Node n) {
        Menu m = new Menu();
        m.setGraphic(n);
        getMenus().add(m);
    }

    public void separator() {
        lastMenu().separator();
    }

    public FxMenu lastMenu() {
        List<Menu> ms = getMenus();
        return (FxMenu) ms.get(ms.size() - 1);
    }

    public FxMenuItem item(String name) {
        FxMenuItem m = new FxMenuItem(name);
        m.setDisable(true);
        add(m);
        return m;
    }

    public FxMenuItem item(String name, FxAction a) {
        FxMenuItem m = new FxMenuItem(name, a);
        add(m);
        return m;
    }

    public FxMenuItem item(String name, HotKey k, FxAction a) {
        FxMenuItem m = new FxMenuItem(name, a);
        k.attach(m);
        add(m);
        return m;
    }

    public FxMenuItem item(String name, Runnable r) {
        FxMenuItem m = new FxMenuItem(name, r);
        add(m);
        return m;
    }

    public FxMenuItem item(String name, HotKey k, Runnable r) {
        FxMenuItem m = new FxMenuItem(name, r);
        k.attach(m);
        add(m);
        return m;
    }

    public FxMenu item(FxMenu m) {
        lastMenu().add(m);
        return m;
    }

    public FxCheckMenuItem item(String text, Property<Boolean> prop) {
        return lastMenu().item(text, prop);
    }

    public void add(MenuItem m) {
        lastMenu().add(m);
    }

    public void collate() {
        CSorter.collate(lastMenu().getItems(), m -> {
            return m.getText();
        });
    }
}
