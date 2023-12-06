package goryachev.common.util;

import goryachev.common.log.Log;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CProperty.class */
public class CProperty<T> {
    protected static final Log log = Log.get("CProperty");
    private T value;
    private CList<Object> listeners;

    @FunctionalInterface
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CProperty$Listener.class */
    public interface Listener<T> {
        void onCPropertyChange(T t, T t2);
    }

    public CProperty(T value) {
        set(value);
    }

    public CProperty() {
    }

    public void set(T v) {
        if (CKit.notEquals(this.value, v)) {
            T old = this.value;
            this.value = v;
            fireEvent(old, v);
        }
    }

    public T get() {
        return this.value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v25 */
    protected void fireEvent(T old, T cur) {
        CList<Object> ls = getListeners();
        if (ls != null) {
            Iterator<Object> it = ls.iterator();
            while (it.hasNext()) {
                Object x = it.next();
                if (x instanceof WeakReference) {
                    Object v = ((WeakReference) x).get();
                    if (v == null) {
                        Object r0 = this;
                        synchronized (r0) {
                            this.listeners.remove(x);
                            r0 = r0;
                        }
                    } else {
                        x = v;
                    }
                }
                if (x instanceof Listener) {
                    try {
                        ((Listener) x).onCPropertyChange(old, cur);
                    } catch (Throwable e) {
                        log.error(e);
                    }
                }
            }
        }
    }

    protected synchronized CList<Object> getListeners() {
        if (this.listeners != null) {
            return new CList<>((Collection<? extends Object>) this.listeners);
        }
        return null;
    }

    protected CList<Object> listeners() {
        if (this.listeners == null) {
            this.listeners = new CList<>();
        }
        return this.listeners;
    }

    public synchronized void addListener(Listener<T> li) {
        listeners().add(li);
    }

    public void addListener(boolean fireImmediately, Listener<T> li) {
        addListener(li);
        if (fireImmediately) {
            fireEvent(null, get());
        }
    }

    public synchronized void addWeakListener(Listener<T> li) {
        listeners().add(new WeakReference(li));
    }

    public void addWeakListener(boolean fireImmediately, Listener<T> li) {
        addWeakListener(li);
        if (fireImmediately) {
            fireEvent(null, get());
        }
    }

    public synchronized void removeListener(Listener<T> li) {
        if (this.listeners != null) {
            for (int i = this.listeners.size() - 1; i >= 0; i--) {
                Object x = this.listeners.get(i);
                if (x == li) {
                    this.listeners.remove(i);
                    return;
                }
                if (x instanceof WeakReference) {
                    Object w = ((WeakReference) x).get();
                    if (w == null) {
                        this.listeners.remove(i);
                    } else if (w == li) {
                        this.listeners.remove(i);
                        return;
                    }
                }
            }
        }
    }
}
