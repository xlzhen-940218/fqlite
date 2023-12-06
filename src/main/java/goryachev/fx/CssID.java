package goryachev.fx;

import java.util.concurrent.atomic.AtomicLong;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CssID.class */
public class CssID {
    private final String id;
    private static final AtomicLong seq = new AtomicLong();

    public CssID(String id) {
        this.id = String.valueOf(id) + "_" + seq.incrementAndGet();
    }

    public CssID() {
        this(ButtonBar.BUTTON_ORDER_NONE);
    }

    public String getID() {
        return this.id;
    }

    public String toString() {
        return getID();
    }
}
