package goryachev.fx;

import goryachev.common.util.Disconnectable;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxChangeListener.class */
public class FxChangeListener implements ChangeListener, Disconnectable {
    private final Runnable callback;
    private final CopyOnWriteArrayList<ObservableValue> properties = new CopyOnWriteArrayList<>();
    private boolean enabled = true;

    public FxChangeListener(Runnable callback) {
        this.callback = callback;
    }

    public void listen(ObservableValue<?> p) {
        if (p != null) {
            this.properties.add(p);
            p.addListener(this);
        }
    }

    public void listen(ObservableValue<?>... observableValueArr) {
        for (ObservableValue<?> p : observableValueArr) {
            listen(p);
        }
    }

    @Override // goryachev.common.util.Disconnectable
    public void disconnect() {
        Iterator<ObservableValue> it = this.properties.iterator();
        while (it.hasNext()) {
            ObservableValue p = it.next();
            p.removeListener(this);
        }
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(true);
    }

    public void setEnabled(boolean on) {
        this.enabled = on;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override // javafx.beans.value.ChangeListener
    public void changed(ObservableValue src, Object prev, Object curr) {
        fire();
    }

    public void fire() {
        if (this.enabled) {
            invokeCallback();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void invokeCallback() {
        this.callback.run();
    }
}
