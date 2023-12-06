package goryachev.fx.internal;

import goryachev.common.log.Log;
import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.SStream;
import goryachev.common.util.WeakList;
import goryachev.fx.CssLoader;
import goryachev.fx.FX;
import goryachev.fx.FxAction;
import goryachev.fx.FxDialog;
import goryachev.fx.FxWindow;
import goryachev.fx.OnWindowClosing;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.stage.Modality;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/WindowsFx.class */
public class WindowsFx {
    protected static final Log log = Log.get("WindowsFx");
    protected final WeakList<FxWindow> windowStack = new WeakList<>();
    protected final CMap<Object, Object> windows = new CMap<>();
    protected final CList<Consumer<FxWindow>> monitors = new CList<>();
    private static boolean exiting;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$stage$Modality;

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$stage$Modality() {
        int[] iArr = $SWITCH_TABLE$javafx$stage$Modality;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Modality.values().length];
        try {
            iArr2[Modality.APPLICATION_MODAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Modality.NONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Modality.WINDOW_MODAL.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$javafx$stage$Modality = iArr2;
        return iArr2;
    }

    public List<FxWindow> getWindows() {
        int sz = this.windowStack.size();
        CList<FxWindow> rv = new CList<>(sz);
        for (int i = 0; i < sz; i++) {
            FxWindow w = this.windowStack.get(i);
            if (w != null && w.isShowing()) {
                rv.add(w);
            }
        }
        return rv;
    }

    public int getEssentialWindowCount() {
        int ct = 0;
        for (int i = this.windowStack.size() - 1; i >= 0; i--) {
            FxWindow w = this.windowStack.get(i);
            if (w == null) {
                this.windowStack.remove(i);
            } else if (w.isShowing() && w.isEssentialWindow()) {
                ct++;
            }
        }
        return ct;
    }

    protected int getFxWindowCount() {
        int ct = 0;
        for (Window w : Window.getWindows()) {
            if ((w instanceof FxWindow) && w.isShowing()) {
                ct++;
            }
        }
        return ct;
    }

    protected boolean confirmExit() {
        OnWindowClosing choice = new OnWindowClosing(true);
        for (FxWindow w : getWindows()) {
            w.confirmClosing(choice);
            if (choice.isCancelled()) {
                return false;
            }
        }
        return true;
    }

    public void exit() {
        storeWindows();
        storeSettings();
        if (confirmExit()) {
            exitPrivate();
        }
    }

    protected void storeWindows() {
        SStream ss = new SStream();
        for (FxWindow w : getWindows()) {
            String id = w.getName();
            ss.add(id);
        }
        GlobalSettings.setStream(FxSchema.WINDOWS, ss);
    }

    public void storeSettings() {
        for (FxWindow w : getWindows()) {
            storeWindow(w);
        }
        GlobalSettings.save();
    }

    public FxAction exitAction() {
        return new FxAction(this::exit);
    }

    protected void exitPrivate() {
        exiting = true;
        Platform.exit();
    }

    public void storeWindow(FxWindow w) {
        String windowPrefix = lookupWindowPrefix(w);
        LocalSettings settings = LocalSettings.find(w);
        if (settings != null) {
            String k = String.valueOf(windowPrefix) + FxSchema.SFX_SETTINGS;
            settings.saveValues(k);
        }
        FxSchema.storeWindow(windowPrefix, w);
        Parent p = w.getScene().getRoot();
        FxSchema.storeNode(windowPrefix, p, p);
    }

    public void restoreWindow(FxWindow w) {
        String windowPrefix = lookupWindowPrefix(w);
        LocalSettings settings = LocalSettings.find(w);
        if (settings != null) {
            String k = String.valueOf(windowPrefix) + FxSchema.SFX_SETTINGS;
            settings.loadValues(k);
        }
        FxSchema.restoreWindow(windowPrefix, w);
        Parent p = w.getScene().getRoot();
        FxSchema.restoreNode(windowPrefix, p, p);
    }

    public void storeNode(Node n) {
        FxWindow w = getFxWindow(n);
        if (w != null) {
            String windowPrefix = lookupWindowPrefix(w);
            Node root = w.getScene().getRoot();
            FxSchema.storeNode(windowPrefix, root, n);
        }
    }

    public void restoreNode(final Node n) {
        FxWindow w = getFxWindow(n);
        if (w == null) {
            n.boundsInParentProperty().addListener(new ChangeListener<Bounds>() { // from class: goryachev.fx.internal.WindowsFx.1
                @Override // javafx.beans.value.ChangeListener
                public void changed(ObservableValue<? extends Bounds> src, Bounds prev, Bounds cur) {
                    src.removeListener(this);
                    WindowsFx.this.restoreNode(n);
                }
            });
            return;
        }
        String windowPrefix = lookupWindowPrefix(w);
        Node root = w.getScene().getRoot();
        FxSchema.restoreNode(windowPrefix, root, n);
    }

    protected FxWindow getFxWindow(Node n) {
        Scene sc = n.getScene();
        if (sc != null) {
            Window w = sc.getWindow();
            if (w instanceof FxWindow) {
                return (FxWindow) w;
            }
            return null;
        }
        return null;
    }

    public int openWindows(Function<String, FxWindow> generator, Class<? extends FxWindow> defaultWindowType) {
        SStream st = GlobalSettings.getStream(FxSchema.WINDOWS);
        boolean createDefault = true;
        for (int i = st.size() - 1; i >= 0; i--) {
            String id = st.getValue(i);
            FxWindow w = generator.apply(id);
            if (w != null) {
                if (!w.isShowing()) {
                    w.open();
                }
                if (defaultWindowType != null && w.getClass() == defaultWindowType) {
                    createDefault = false;
                }
            }
        }
        if (createDefault) {
            generator.apply(null).open();
        }
        return st.size();
    }

    public void open(FxWindow w) {
        if (w.isShowing()) {
            log.warn("use open() instead of show(): " + w.getClass());
        }
        w.setOnCloseRequest(ev -> {
            handleClose(w, ev);
        });
        w.showingProperty().addListener((observableValue, old, cur) -> {
            if (!cur.booleanValue()) {
                unlinkWindow(w);
            }
        });
        addWindow(w);
        restoreWindow(w);
        applyStyleSheet(w);
        try {
            if (this.monitors.size() > 0) {
                Iterator<Consumer<FxWindow>> it = this.monitors.iterator();
                while (it.hasNext()) {
                    Consumer<FxWindow> m = it.next();
                    m.accept(w);
                }
            }
        } catch (Exception e) {
            log.error((Throwable) e);
        }
        switch ($SWITCH_TABLE$javafx$stage$Modality()[w.getModality().ordinal()]) {
            case 2:
            case 3:
                w.showAndWait();
                return;
            default:
                w.show();
                return;
        }
    }

    protected void unlinkWindow(FxWindow w) {
        if (!exiting && !(w instanceof FxDialog) && getFxWindowCount() == 1) {
            storeWindows();
        }
        storeWindow(w);
        GlobalSettings.save();
        Object id = this.windows.remove(w);
        if (id instanceof String) {
            this.windows.remove(id);
        }
        if (getEssentialWindowCount() == 0) {
            exitPrivate();
        }
    }

    public void close(FxWindow w) {
        w.hide();
    }

    protected void handleClose(FxWindow w, WindowEvent ev) {
        OnWindowClosing ch = new OnWindowClosing(false);
        w.confirmClosing(ch);
        if (ch.isCancelled()) {
            ev.consume();
        }
    }

    public void addFocusListener(FxWindow w) {
        w.focusedProperty().addListener((observableValue, old, v) -> {
            if (v.booleanValue()) {
                onWindowFocused(w);
            }
        });
    }

    protected void onWindowFocused(FxWindow win) {
        int ix = 0;
        while (ix < this.windowStack.size()) {
            FxWindow w = this.windowStack.get(ix);
            if (w == null || w == win) {
                this.windowStack.remove(ix);
            } else {
                ix++;
            }
        }
        this.windowStack.add(win);
    }

    public FxWindow findTopWindow(List<FxWindow> ws) {
        int sz = ws.size();
        for (int i = this.windowStack.size() - 1; i >= 0; i--) {
            FxWindow w = this.windowStack.get(i);
            if (w == null) {
                this.windowStack.remove(i);
            } else {
                for (int j = 0; j < sz; j++) {
                    if (w == ws.get(j)) {
                        return w;
                    }
                }
                continue;
            }
        }
        return null;
    }

    protected String addWindow(FxWindow w) {
        String name = w.getName();
        String prefix = ButtonBar.BUTTON_ORDER_NONE;
        for (int i = 0; i < 10000; i++) {
            prefix = String.valueOf(name) + "." + i;
            if (!this.windows.containsKey(prefix)) {
                break;
            }
        }
        this.windows.put(w, prefix);
        this.windows.put(prefix, w);
        onWindowFocused(w);
        return prefix;
    }

    protected String lookupWindowPrefix(FxWindow w) {
        Object x = this.windows.get(w);
        if (x instanceof String) {
            return (String) x;
        }
        return addWindow(w);
    }

    public void addWindowMonitor(Consumer<FxWindow> m) {
        this.monitors.add(m);
    }

    public void removeWindowMonitor(Consumer<FxWindow> m) {
        this.monitors.remove(m);
    }

    public static void applyStyleSheet(Window w) {
        try {
            String style = CssLoader.getCurrentStyleSheet();
            FX.applyStyleSheet(w, null, style);
        } catch (Throwable e) {
            log.error(e);
        }
    }
}
