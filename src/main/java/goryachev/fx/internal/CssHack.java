package goryachev.fx.internal;

import goryachev.common.util.SB;
import goryachev.fx.CssLoader;
import javafx.scene.Node;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/CssHack.class */
public class CssHack<T> {
    private final String name;
    private final String css;
    private final T value;
    private final double doubleValue;

    public CssHack(String name, String css, T value, double doubleValue) {
        this.name = name;
        this.css = css;
        this.value = value;
        this.doubleValue = doubleValue;
    }

    public String getName() {
        return this.name;
    }

    public String getCSS() {
        return this.css;
    }

    public T getValue() {
        return this.value;
    }

    public double doubleValue() {
        return this.doubleValue;
    }

    public static String generateName(String prefix, String suffix) {
        SB sb = new SB();
        sb.a(prefix);
        sb.a(suffix);
        sb.replace(' ', (char) 8728);
        sb.replace('-', (char) 8722);
        sb.replace('.', (char) 183);
        return sb.toString();
    }

    public static <X> CssHack<X> get(Node owner, Object key) {
        Object x = owner.getProperties().get(key);
        if (x instanceof CssHack) {
            return (CssHack) x;
        }
        return null;
    }

    public static void remove(Node owner, Object key) {
        CssHack h = get(owner, key);
        if (h != null) {
            String name = h.getName();
            owner.getStyleClass().remove(name);
        }
    }

    public void attachTo(Node owner, Object key) {
        owner.getStyleClass().add(this.name);
        owner.getProperties().put(key, this);
        CssLoader.addGlobalStyle(this.css);
    }
}
