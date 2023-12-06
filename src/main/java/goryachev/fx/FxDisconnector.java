package goryachev.fx;

import goryachev.common.util.CList;
import goryachev.common.util.Disconnectable;
import java.lang.ref.WeakReference;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDisconnector.class */
public class FxDisconnector implements Disconnectable {
    private final CList<Disconnectable> items = new CList<>();
    private static final Object KEY = new Object();

    public static FxDisconnector get(Node n) {
        Object x = n.getProperties().get(KEY);
        if (x instanceof FxDisconnector) {
            return (FxDisconnector) x;
        }
        FxDisconnector d = new FxDisconnector();
        n.getProperties().put(KEY, d);
        return d;
    }

    public static void disconnect(Node n) {
        Object x = n.getProperties().get(KEY);
        if (x instanceof FxDisconnector) {
            ((FxDisconnector) x).disconnect();
        }
    }

    public void addDisconnectable(Disconnectable d) {
        this.items.add(d);
    }

    @Override // goryachev.common.util.Disconnectable
    public void disconnect() {
        for (int i = this.items.size() - 1; i >= 0; i--) {
            Disconnectable d = this.items.remove(i);
            d.disconnect();
        }
    }

    public Disconnectable addChangeListener(Runnable callback, ObservableValue<?>... observableValueArr) {
        return addChangeListener(callback, false, observableValueArr);
    }

    public Disconnectable addChangeListener(final Runnable onChange, boolean fireImmediately, final ObservableValue<?>... observableValueArr) {
        ChLi li = new ChLi() { // from class: goryachev.fx.FxDisconnector.1
            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                ObservableValue[] observableValueArr2;
                for (ObservableValue p : observableValueArr) {
                    p.removeListener(this);
                }
            }

            @Override // javafx.beans.value.ChangeListener
            public void changed(ObservableValue p, Object oldValue, Object newValue) {
                onChange.run();
            }
        };
        this.items.add(li);
        for (ObservableValue<?> observableValue : observableValueArr) {
            observableValue.addListener(li);
        }
        if (fireImmediately) {
            onChange.run();
        }
        return li;
    }

    public <T> Disconnectable addChangeListener(ObservableValue<T> prop, ChangeListener<T> li) {
        return addChangeListener((ObservableValue) prop, false, (ChangeListener) li);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Disconnectable addChangeListener(final ObservableValue<T> prop, boolean fireImmediately, final ChangeListener<T> li) {
        Disconnectable d = new Disconnectable() { // from class: goryachev.fx.FxDisconnector.2
            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                prop.removeListener(li);
            }
        };
        this.items.add(d);
        prop.addListener(li);
        if (fireImmediately) {
            T v = prop.getValue();
            li.changed(prop, null, v);
        }
        return d;
    }

    public Disconnectable addWeakChangeListener(Runnable onChange, ObservableValue<?>... observableValueArr) {
        return addWeakChangeListener(onChange, false, observableValueArr);
    }

    public Disconnectable addWeakChangeListener(Runnable onChange, boolean fireImmediately, ObservableValue<?>... observableValueArr) {
        ChLi li = new ChLi() { // from class: goryachev.fx.FxDisconnector.3
            WeakReference<Runnable> ref;
            private final /* synthetic */ ObservableValue[] val$props;

            {
                this.val$props = observableValueArr;
                this.ref = new WeakReference<>(onChange);
            }

            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                ObservableValue[] observableValueArr2;
                for (ObservableValue p : this.val$props) {
                    p.removeListener(this);
                }
            }

            @Override // javafx.beans.value.ChangeListener
            public void changed(ObservableValue p, Object oldValue, Object newValue) {
                Runnable r = this.ref.get();
                if (r == null) {
                    disconnect();
                } else {
                    r.run();
                }
            }
        };
        this.items.add(li);
        for (ObservableValue<?> observableValue : observableValueArr) {
            observableValue.addListener(li);
        }
        if (fireImmediately) {
            onChange.run();
        }
        return li;
    }

    public <T> Disconnectable addWeakChangeListener(ObservableValue<T> prop, ChangeListener<T> li) {
        return addChangeListener((ObservableValue) prop, false, (ChangeListener) li);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Disconnectable addWeakChangeListener(ObservableValue<T> prop, boolean fireImmediately, ChangeListener<T> listener) {
        ChLi<T> d = new ChLi<>() { // from class: goryachev.fx.FxDisconnector.4
            /*WeakReference<ChangeListener<T>> ref;
            private final *//* synthetic *//* ObservableValue val$prop;

            {
                this.val$prop = prop;
                this.ref = new WeakReference<>(listener);
            }*/

            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                prop.removeListener(this);
            }

            @Override // javafx.beans.value.ChangeListener
            public void changed(ObservableValue<? extends T> p, T oldValue, T newValue) {
                ChangeListener<T> li = listener;
                if (li == null) {
                    disconnect();
                } else {
                    li.changed(p, oldValue, newValue);
                }
            }
        };
        this.items.add(d);
        prop.addListener(d);
        if (fireImmediately) {
            T v = prop.getValue();
            listener.changed(prop, null, v);
        }
        return d;
    }

    public Disconnectable addInvalidationListener(Runnable callback, ObservableValue<?>... observableValueArr) {
        return addInvalidationListener(callback, false, observableValueArr);
    }

    public Disconnectable addInvalidationListener(final Runnable onChange, boolean fireImmediately, final ObservableValue<?>... observableValueArr) {
        InLi li = new InLi() { // from class: goryachev.fx.FxDisconnector.5
            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                ObservableValue[] observableValueArr2;
                for (ObservableValue p : observableValueArr) {
                    p.removeListener(this);
                }
            }

            @Override // javafx.beans.InvalidationListener
            public void invalidated(Observable p) {
                onChange.run();
            }
        };
        this.items.add(li);
        for (ObservableValue<?> observableValue : observableValueArr) {
            observableValue.addListener(li);
        }
        if (fireImmediately) {
            onChange.run();
        }
        return li;
    }

    public <T> Disconnectable addInvalidationListener(ObservableValue<T> prop, InvalidationListener li) {
        return addInvalidationListener((ObservableValue) prop, false, li);
    }

    public <T> Disconnectable addInvalidationListener(final ObservableValue<T> prop, boolean fireImmediately, final InvalidationListener li) {
        Disconnectable d = new Disconnectable() { // from class: goryachev.fx.FxDisconnector.6
            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                prop.removeListener(li);
            }
        };
        this.items.add(d);
        prop.addListener(li);
        if (fireImmediately) {
            li.invalidated(prop);
        }
        return d;
    }

    public Disconnectable addWeakInvalidationListener(Runnable onChange, ObservableValue<?>... observableValueArr) {
        return addWeakInvalidationListener(onChange, false, observableValueArr);
    }

    public Disconnectable addWeakInvalidationListener(Runnable onChange, boolean fireImmediately, ObservableValue<?>... observableValueArr) {
        InLi li = new InLi() { // from class: goryachev.fx.FxDisconnector.7
            WeakReference<Runnable> ref;
            private final /* synthetic */ ObservableValue[] val$props;

            {
                this.val$props = observableValueArr;
                this.ref = new WeakReference<>(onChange);
            }

            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                ObservableValue[] observableValueArr2;
                for (ObservableValue p : this.val$props) {
                    p.removeListener(this);
                }
            }

            @Override // javafx.beans.InvalidationListener
            public void invalidated(Observable p) {
                Runnable r = this.ref.get();
                if (r == null) {
                    disconnect();
                } else {
                    r.run();
                }
            }
        };
        this.items.add(li);
        for (ObservableValue<?> observableValue : observableValueArr) {
            observableValue.addListener(li);
        }
        if (fireImmediately) {
            onChange.run();
        }
        return li;
    }

    public Disconnectable addWeakInvalidationListener(ObservableValue<?> prop, InvalidationListener li) {
        return addWeakInvalidationListener(prop, false, li);
    }

    public Disconnectable addWeakInvalidationListener(ObservableValue<?> prop, boolean fireImmediately, InvalidationListener listener) {
        InLi d = new InLi() { // from class: goryachev.fx.FxDisconnector.8
            /*WeakReference<InvalidationListener> ref;
            private final *//* synthetic *//* ObservableValue val$prop;

            {
                this.val$prop = prop;
                this.ref = new WeakReference<>(listener);
            }*/

            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                prop.removeListener(this);
            }

            @Override // javafx.beans.InvalidationListener
            public void invalidated(Observable p) {
                InvalidationListener li = listener;
                if (li == null) {
                    disconnect();
                } else {
                    li.invalidated(p);
                }
            }
        };
        this.items.add(d);
        prop.addListener(d);
        if (fireImmediately) {
            listener.invalidated(prop);
        }
        return d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> Disconnectable addListChangeListener(final ObservableList<T> list, final ListChangeListener<T> listener) {
        Disconnectable d = new Disconnectable() { // from class: goryachev.fx.FxDisconnector.9
            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                list.removeListener(listener);
            }
        };
        this.items.add(d);
        list.addListener((ListChangeListener<? super T>) listener);
        return d;
    }

    public <T> Disconnectable addWeakListChangeListener(ObservableList<T> list, ListChangeListener<T> listener) {
        LiChLi<T> li = new LiChLi<T>() { // from class: goryachev.fx.FxDisconnector.10
            /*WeakReference<ListChangeListener<T>> ref;
            private final *//* synthetic *//* ObservableList val$list;

            {
                this.val$list = list;
                this.ref = new WeakReference<>(listener);
            }*/

            @Override // goryachev.common.util.Disconnectable
            public void disconnect() {
                list.removeListener(this);
            }

            @Override // javafx.collections.ListChangeListener
            public void onChanged(Change<? extends T> ch) {
                ListChangeListener<T> li2 = listener;
                if (li2 == null) {
                    disconnect();
                } else {
                    li2.onChanged(ch);
                }
            }
        };
        this.items.add(li);
        list.addListener(li);
        return li;
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDisconnector$ChLi.class */
    protected static abstract class ChLi<T> implements Disconnectable, ChangeListener<T> {
        protected ChLi() {
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDisconnector$InLi.class */
    protected static abstract class InLi implements Disconnectable, InvalidationListener {
        protected InLi() {
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDisconnector$LiChLi.class */
    protected static abstract class LiChLi<T> implements Disconnectable, ListChangeListener<T> {
        protected LiChLi() {
        }
    }
}
