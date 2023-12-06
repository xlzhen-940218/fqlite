package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.CPlatform;
import goryachev.common.util.Disconnectable;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.SystemTask;
import goryachev.fx.internal.CssTools;
import goryachev.fx.internal.DisconnectableIntegerListener;
import goryachev.fx.internal.FxSchema;
import goryachev.fx.internal.FxStyleHandler;
import goryachev.fx.internal.ParentWindow;
import goryachev.fx.internal.WindowsFx;
import goryachev.fx.table.FxTable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.WeakListener;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.TransformationList;
import javafx.css.Styleable;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FX.class */
public final class FX {
    public static final double TWO_PI = 6.283185307179586d;
    public static final double PI_2 = 1.5707963267948966d;
    public static final double DEGREES_PER_RADIAN = 57.29577951308232d;
    public static final double GAMMA = 2.2d;
    public static final double ONE_OVER_GAMMA = 0.45454545454545453d;
    private static Text helper;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$fx$FxCtl;
    protected static final Log log = Log.get("FX");
    private static WindowsFx windowsFx = new WindowsFx();
    private static final Object PROP_TOOLTIP = new Object();

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$fx$FxCtl() {
        int[] iArr = $SWITCH_TABLE$goryachev$fx$FxCtl;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[FxCtl.valuesCustom().length];
        try {
            iArr2[FxCtl.BOLD.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[FxCtl.EDITABLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[FxCtl.FOCUSABLE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[FxCtl.FORCE_MAX_WIDTH.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[FxCtl.FORCE_MIN_HEIGHT.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[FxCtl.FORCE_MIN_WIDTH.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[FxCtl.NON_EDITABLE.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[FxCtl.NON_FOCUSABLE.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr2[FxCtl.WRAP_TEXT.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        $SWITCH_TABLE$goryachev$fx$FxCtl = iArr2;
        return iArr2;
    }

    public static FxWindow getWindow(Node n) {
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

    public static void storeSettings(Node n) {
        if (n != null) {
            windowsFx.storeNode(n);
            GlobalSettings.save();
        }
    }

    public static void restoreSettings(Node n) {
        if (n != null) {
            windowsFx.restoreNode(n);
        }
    }

    public static void storeSettings(FxWindow w) {
        windowsFx.storeWindow(w);
        GlobalSettings.save();
    }

    public static void restoreSettings(FxWindow w) {
        windowsFx.restoreWindow(w);
        GlobalSettings.save();
    }

    public static void setSkipSettings(Node n) {
        if (n != null) {
            FxSchema.setSkipSettings(n);
        }
    }

    public static void openWindows(Function<String, FxWindow> generator, Class<? extends FxWindow> defaultWindowType) {
        windowsFx.openWindows(generator, defaultWindowType);
    }

    public static void open(FxWindow w) {
        windowsFx.open(w);
    }

    public static void close(FxWindow w) {
        windowsFx.close(w);
    }

    public static void exit() {
        windowsFx.exit();
    }

    public static FxAction exitAction() {
        return windowsFx.exitAction();
    }

    public static Label label(Object... attrs) {
        Label n = new Label();
        for (Object a : attrs) {
            if (a != null) {
                if (a instanceof CssStyle) {
                    n.getStyleClass().add(((CssStyle) a).getName());
                } else if (a instanceof CssID) {
                    n.setId(((CssID) a).getID());
                } else if (a instanceof FxCtl) {
                    switch ($SWITCH_TABLE$goryachev$fx$FxCtl()[((FxCtl) a).ordinal()]) {
                        case 1:
                            n.getStyleClass().add(CssTools.BOLD.getName());
                            continue;
                        case 2:
                        case 7:
                        default:
                            throw new Error("?" + a);
                        case 3:
                            n.setFocusTraversable(true);
                            continue;
                        case 4:
                            n.setMaxWidth(Double.MAX_VALUE);
                            continue;
                        case 5:
                            n.setMinHeight(Double.NEGATIVE_INFINITY);
                            continue;
                        case 6:
                            n.setMinWidth(Double.NEGATIVE_INFINITY);
                            continue;
                        case 8:
                            n.setFocusTraversable(false);
                            continue;
                        case 9:
                            n.setWrapText(true);
                            continue;
                    }
                } else if (a instanceof Insets) {
                    n.setPadding((Insets) a);
                } else if (a instanceof OverrunStyle) {
                    n.setTextOverrun((OverrunStyle) a);
                } else if (a instanceof Pos) {
                    n.setAlignment((Pos) a);
                } else if (a instanceof String) {
                    n.setText((String) a);
                } else if (a instanceof TextAlignment) {
                    n.setTextAlignment((TextAlignment) a);
                } else if (a instanceof Color) {
                    n.setTextFill((Color) a);
                } else if (a instanceof StringProperty) {
                    n.textProperty().bind((StringProperty) a);
                } else if (a instanceof Node) {
                    n.setGraphic((Node) a);
                } else if (a instanceof Background) {
                    n.setBackground((Background) a);
                } else {
                    throw new Error("?" + a);
                }
            }
        }
        return n;
    }

    public static Text text(Object... attrs) {
        Text n = new Text();
        for (Object a : attrs) {
            if (a != null) {
                if (a instanceof CssStyle) {
                    n.getStyleClass().add(((CssStyle) a).getName());
                } else if (a instanceof CssID) {
                    n.setId(((CssID) a).getID());
                } else if (a instanceof FxCtl) {
                    switch ($SWITCH_TABLE$goryachev$fx$FxCtl()[((FxCtl) a).ordinal()]) {
                        case 1:
                            n.getStyleClass().add(CssTools.BOLD.getName());
                            continue;
                        case 3:
                            n.setFocusTraversable(true);
                            continue;
                        case 8:
                            n.setFocusTraversable(false);
                            continue;
                        default:
                            throw new Error("?" + a);
                    }
                } else if (a instanceof String) {
                    n.setText((String) a);
                } else if (a instanceof TextAlignment) {
                    n.setTextAlignment((TextAlignment) a);
                } else {
                    throw new Error("?" + a);
                }
            }
        }
        return n;
    }

    public static void style(Styleable n, CssStyle style) {
        n.getStyleClass().add(style.getName());
    }

    public static void style(Styleable n, boolean condition, CssStyle st) {
        if (n == null || st == null) {
            return;
        }
        String name = st.getName();
        ObservableList<String> ss = n.getStyleClass();
        if (condition) {
            if (!ss.contains(name)) {
                ss.add(st.getName());
                return;
            }
            return;
        }
        ss.remove(name);
    }

    public static void removeStyles(Styleable n, CssStyle... styles) {
        for (CssStyle st : styles) {
            n.getStyleClass().remove(st.getName());
        }
    }

    public static void style(Node n, Object... attrs) {
        if (n != null) {
            for (Object a : attrs) {
                if (a != null) {
                    if (a instanceof CssStyle) {
                        n.getStyleClass().add(((CssStyle) a).getName());
                    } else if (a instanceof CssID) {
                        n.setId(((CssID) a).getID());
                    } else if (a instanceof FxCtl) {
                        switch ($SWITCH_TABLE$goryachev$fx$FxCtl()[((FxCtl) a).ordinal()]) {
                            case 1:
                                n.getStyleClass().add(CssTools.BOLD.getName());
                                continue;
                            case 2:
                                ((TextInputControl) n).setEditable(true);
                                continue;
                            case 3:
                                n.setFocusTraversable(true);
                                continue;
                            case 4:
                            default:
                                throw new Error("?" + a);
                            case 5:
                                ((Region) n).setMinHeight(Double.NEGATIVE_INFINITY);
                                continue;
                            case 6:
                                ((Region) n).setMinWidth(Double.NEGATIVE_INFINITY);
                                continue;
                            case 7:
                                ((TextInputControl) n).setEditable(false);
                                continue;
                            case 8:
                                n.setFocusTraversable(false);
                                continue;
                            case 9:
                                if (n instanceof Labeled) {
                                    ((Labeled) n).setWrapText(true);
                                    continue;
                                } else if (n instanceof TextArea) {
                                    ((TextArea) n).setWrapText(true);
                                    break;
                                } else {
                                    throw new Error("?wrap for " + n);
                                }
                        }
                    } else if (a instanceof Insets) {
                        ((Region) n).setPadding((Insets) a);
                    } else if (a instanceof OverrunStyle) {
                        ((Labeled) n).setTextOverrun((OverrunStyle) a);
                    } else if (a instanceof Pos) {
                        if (n instanceof Labeled) {
                            ((Labeled) n).setAlignment((Pos) a);
                        } else if (n instanceof TextField) {
                            ((TextField) n).setAlignment((Pos) a);
                        } else {
                            throw new Error("?" + n);
                        }
                    } else if (a instanceof String) {
                        if (n instanceof Labeled) {
                            ((Labeled) n).setText((String) a);
                        } else if (n instanceof TextInputControl) {
                            ((TextInputControl) n).setText((String) a);
                        } else {
                            throw new Error("?" + n);
                        }
                    } else if (a instanceof TextAlignment) {
                        ((Labeled) n).setTextAlignment((TextAlignment) a);
                    } else if (a instanceof Background) {
                        ((Region) n).setBackground((Background) a);
                    } else {
                        throw new Error("?" + a);
                    }
                }
            }
        }
    }

    public static Background background(Paint c) {
        if (c == null) {
            return null;
        }
        return new Background(new BackgroundFill(c, null, null));
    }

    public static Color gray(int col) {
        return Color.rgb(col, col, col);
    }

    public static Color gray(int col, double alpha) {
        return Color.rgb(col, col, col, alpha);
    }

    public static Color rgb(int rgb) {
        int r = (rgb >> 16) & 255;
        int g = (rgb >> 8) & 255;
        int b = rgb & 255;
        return Color.rgb(r, g, b);
    }

    public static Color rgb(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }

    public static Color rgb(int rgb, double alpha) {
        int r = (rgb >> 16) & 255;
        int g = (rgb >> 8) & 255;
        int b = rgb & 255;
        return Color.rgb(r, g, b, alpha);
    }

    public static Color rgb(int red, int green, int blue, double alpha) {
        return Color.rgb(red, green, blue, alpha);
    }

    public static boolean contains(Node n, double screenx, double screeny) {
        Point2D p;
        if (n != null && (p = n.screenToLocal(screenx, screeny)) != null) {
            return n.contains(p);
        }
        return false;
    }

    public static boolean contains(Node eventSource, Node eventTarget, double x, double y) {
        Point2D p;
        Point2D p2 = eventSource.localToScreen(x, y);
        if (p2 != null && (p = eventTarget.screenToLocal(p2)) != null) {
            return eventTarget.contains(p);
        }
        return false;
    }

    public static boolean isParent(Node parent, Node child) {
        while (child != null) {
            if (child == parent) {
                return true;
            }
            child = child.getParent();
        }
        return false;
    }

    public static void setProperty(Node n, Object k, Object v) {
        if (v == null) {
            n.getProperties().remove(k);
        } else {
            n.getProperties().put(k, v);
        }
    }

    public static Object getProperty(Node n, Object k) {
        return n.getProperties().get(k);
    }

    public static Window getParentWindow(Object nodeOrWindow) {
        if (nodeOrWindow == null) {
            return null;
        }
        if (nodeOrWindow instanceof Window) {
            return (Window) nodeOrWindow;
        }
        if (nodeOrWindow instanceof Node) {
            Scene s = ((Node) nodeOrWindow).getScene();
            if (s != null) {
                return s.getWindow();
            }
            return null;
        }
        throw new Error("node or window");
    }

    public static void later(Runnable r) {
        Platform.runLater(r);
    }

    public static void later(int delay, Runnable r) {
        SystemTask.schedule(delay, () -> {
            Platform.runLater(r);
        });
    }

    public static void inFX(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            later(r);
        }
    }

    public static boolean isFX() {
        return Platform.isFxApplicationThread();
    }

    public static <T> T invokeAndWait(Callable<T> producer) throws Exception {
        if (Platform.isFxApplicationThread()) {
            return producer.call();
        }
        FutureTask<T> t = new FutureTask<>(producer);
        later(t);
        return t.get();
    }

    public static void invokeAndWait(Runnable action) throws Exception {
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }
        FutureTask<Boolean> t = new FutureTask<>(() -> {
            action.run();
            return Boolean.TRUE;
        });
        later(t);
        t.get();
    }

    public static Insets getDecorationInsets(Window w) {
        Scene s = w.getScene();
        double left = s.getX();
        double top = s.getY();
        double right = (w.getWidth() - s.getWidth()) - left;
        double bottom = (w.getHeight() - s.getHeight()) - top;
        return new Insets(top, right, bottom, left);
    }

    public static Insets getInsetsInWindow(Window w, Node n) {
        Bounds b = n.localToScreen(n.getBoundsInLocal());
        double left = b.getMinX() - w.getX();
        double top = b.getMinY() - w.getY();
        double right = (w.getX() + w.getWidth()) - b.getMaxX();
        double bottom = (w.getY() + w.getHeight()) - b.getMaxY();
        return new Insets(top, right, bottom, left);
    }

    public static void setName(Node n, String name) {
        FxSchema.setName(n, name);
    }

    public static String getName(Node n) {
        return FxSchema.getName(n);
    }

    public static void setOnSettingsLoaded(Node n, Runnable r) {
        FxSchema.setOnSettingsLoaded(n, r);
    }

    public static boolean isValidCoordinates(double x, double y) {
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D r = screen.getVisualBounds();
            if (r.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public static double toRadians(double degrees) {
        return degrees / 57.29577951308232d;
    }

    public static double toDegrees(double radians) {
        return radians * 57.29577951308232d;
    }

    public static Color alpha(Color c, double opacity) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), opacity);
    }

    public static Color mix(Color base, Color over, double fraction) {
        if (fraction <= 0.0d) {
            return base;
        }
        if (base == null) {
            if (over == null) {
                return null;
            }
            return new Color(over.getRed(), over.getGreen(), over.getBlue(), over.getOpacity() * fraction);
        } else if (over == null) {
            return base;
        } else {
            if (base.isOpaque() && over.isOpaque()) {
                double r = mix(base.getRed(), over.getRed(), fraction);
                double g = mix(base.getGreen(), over.getGreen(), fraction);
                double b = mix(base.getBlue(), over.getBlue(), fraction);
                return new Color(r, g, b, 1.0d);
            }
            double opacityBase = base.getOpacity();
            double opacityOver = clip(over.getOpacity() * fraction);
            double alpha = opacityOver + (opacityBase * (1.0d - opacityOver));
            if (alpha < 1.0E-5d) {
                return new Color(0.0d, 0.0d, 0.0d, 0.0d);
            }
            double r2 = mix(base.getRed(), opacityBase, over.getRed(), opacityOver, alpha);
            double g2 = mix(base.getGreen(), opacityBase, over.getGreen(), opacityOver, alpha);
            double b2 = mix(base.getBlue(), opacityBase, over.getBlue(), opacityOver, alpha);
            return new Color(r2, g2, b2, alpha);
        }
    }

    private static double mix(double base, double over, double fraction) {
        double v = (Math.pow(over, 2.2d) * fraction) + (Math.pow(base, 2.2d) * (1.0d - fraction));
        return clip(Math.pow(v, 0.45454545454545453d));
    }

    private static double mix(double base, double opacityBase, double over, double opacityOver, double alpha) {
        double v = (Math.pow(over, 2.2d) * opacityOver) + (Math.pow(base, 2.2d) * (1.0d - opacityOver));
        return clip(Math.pow(v / alpha, 0.45454545454545453d));
    }

    public static Color mix(Color[] colors, double gamma) {
        int sz = colors.length;
        double red = 0.0d;
        double green = 0.0d;
        double blue = 0.0d;
        for (Color c : colors) {
            double op = c.getOpacity();
            double r = c.getRed();
            red += Math.pow(r, gamma) * op;
            double g = c.getGreen();
            green += Math.pow(g, gamma) * op;
            double b = c.getBlue();
            blue += Math.pow(b, gamma) * op;
        }
        double oneOverGamma = 1.0d / gamma;
        return Color.color(clip(Math.pow(red / sz, oneOverGamma)), clip(Math.pow(green / sz, oneOverGamma)), clip(Math.pow(blue / sz, oneOverGamma)));
    }

    public static Color mix(Color[] colors) {
        return mix(colors, 2.2d);
    }

    private static double clip(double c) {
        if (c < 0.0d) {
            return 0.0d;
        }
        if (c >= 1.0d) {
            return 1.0d;
        }
        return c;
    }

    public static void toFront(Stage w) {
        if (w.isIconified()) {
            w.setIconified(false);
        }
        w.toFront();
    }

    public static Image loadImage(Class<?> c, String resource) {
        return new Image(c.getResourceAsStream(resource));
    }

    public static void setTooltip(Node n, String text) {
        if (n != null) {
            if (text == null) {
                Tooltip.uninstall(n, getTooltip(n));
                n.getProperties().remove(PROP_TOOLTIP);
                return;
            }
            Tooltip t = new Tooltip(text);
            Tooltip.install(n, t);
            n.getProperties().put(PROP_TOOLTIP, t);
        }
    }

    private static Tooltip getTooltip(Node n) {
        if (n != null) {
            return (Tooltip) n.getProperties().get(PROP_TOOLTIP);
        }
        return null;
    }

    public static void storeSettings() {
        windowsFx.storeSettings();
    }

    public static ObservableValue toObservableValue(Object x) {
        if (x == null) {
            return null;
        }
        if (x instanceof ObservableValue) {
            return (ObservableValue) x;
        }
        return new SimpleObjectProperty(x);
    }

    public static double clip(double val, double min, double max) {
        if (val < min) {
            return min;
        }
        if (val > max) {
            return max;
        }
        return val;
    }

    public static void setDisable(boolean on, Object... nodes) {
        for (Object x : nodes) {
            if (x instanceof Node) {
                ((Node) x).setDisable(on);
            } else if (x instanceof FxAction) {
                ((FxAction) x).setDisabled(on);
            }
        }
    }

    public static void addWindowMonitor(Consumer<FxWindow> monitor) {
        windowsFx.addWindowMonitor(monitor);
    }

    public static void removeWindowMonitor(Consumer<FxWindow> monitor) {
        windowsFx.removeWindowMonitor(monitor);
    }

    public static <T> ObservableList<T> observableArrayList() {
        return FXCollections.observableArrayList();
    }

    public static <K, V> ObservableMap<K, V> observableHashMap() {
        return FXCollections.observableHashMap();
    }

    public static Region spacer(double size) {
        Region r = new Region();
        r.setMinSize(size, size);
        r.setMaxSize(size, size);
        r.setPrefSize(size, size);
        return r;
    }

    public static FxSize getTextBounds(TextArea textArea, double targetWidth) {
        String text = textArea.getText();
        Font f = textArea.getFont();
        Bounds r = computeTextBounds(text, f, targetWidth);
        Insets m = textArea.getInsets();
        textArea.getPadding();
        double w = Math.ceil(r.getWidth() + m.getLeft() + m.getRight());
        double h = Math.ceil(r.getHeight() + m.getTop() + m.getBottom());
        return new FxSize(w, h);
    }

    public static Bounds computeTextBounds(String text, Font f) {
        return computeTextBounds(text, f, -1.0d);
    }

    public static Bounds computeTextBounds(String text, Font f, double targetWidth) {
        if (helper == null) {
            helper = new Text();
        }
        if (targetWidth < 0.0d) {
            helper.setWrappingWidth(0.0d);
        } else {
            helper.setWrappingWidth(targetWidth);
        }
        helper.setText(text);
        helper.setFont(f);
        return helper.getLayoutBounds();
    }

    public static void focusLater(Node n) {
        later(() -> {
            n.requestFocus();
        });
    }

    public static <T> T getAncestorOfClass(Class<T> c, Node node) {
        if (Window.class.isAssignableFrom(c)) {
            Scene sc = node.getScene();
            if (sc != null) {
                Window w = sc.getWindow();
                while (w != null) {
                    if (w.getClass().isAssignableFrom(c)) {
                        return (T) w;
                    }
                    if (w instanceof Stage) {
                        Stage stage = (Stage) w;
                        w = stage.getOwner();
                    }
                }
                return null;
            }
            return null;
        }
        while (node != null) {
            if (c.isInstance(node)) {
                return (T) node;
            }
            node = node.getParent();
        }
        return null;
    }

    public static void onDoubleClick(Node owner, Runnable handler) {
        if (owner == null) {
            throw new NullPointerException("cannot attach a double click handler to null");
        }
        owner.addEventHandler(MouseEvent.MOUSE_CLICKED, ev -> {
            if (ev.getClickCount() == 2 && ev.getButton() == MouseButton.PRIMARY) {
                handler.run();
            }
        });
    }

    public static void setPopupMenu(Node owner, Supplier<ContextMenu> generator) {
        if (owner == null) {
            throw new NullPointerException("cannot attach popup menu to null");
        }
        owner.setOnContextMenuRequested(ev -> {
            ContextMenu m;
            if (generator != null && (m = (ContextMenu) generator.get()) != null && m.getItems().size() > 0) {
                later(() -> {
                    EventHandler<MouseEvent> li = new EventHandler<MouseEvent>() { // from class: goryachev.fx.FX.1
                        @Override // javafx.event.EventHandler
                        public void handle(MouseEvent event) {
                            m.hide();
                            owner.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                            event.consume();
                        }
                    };
                    owner.addEventFilter(MouseEvent.MOUSE_PRESSED, li);
                    m.show(owner, ev.getScreenX(), ev.getScreenY());
                });
                ev.consume();
            }
            ev.consume();
        });
    }

    public static void checkThread() {
        if (!Platform.isFxApplicationThread()) {
            throw new Error("must be called from an FX application thread");
        }
    }

    public static void onKey(Node node, KeyCode code, FxAction a) {
        node.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() != code || ev.isAltDown() || ev.isControlDown() || ev.isMetaDown() || ev.isShiftDown() || ev.isShortcutDown()) {
                return;
            }
            a.invokeAction();
            ev.consume();
        });
    }

    public static <T> void addOneShotListener(final Property<T> p, final Consumer<T> c) {
        p.addListener(new ChangeListener<T>() { // from class: goryachev.fx.FX.2
            @Override // javafx.beans.value.ChangeListener
            public void changed(ObservableValue<? extends T> observable, T old, T cur) {
                c.accept(cur);
                p.removeListener(this);
            }
        });
    }

    public static void preventSplitPaneResizing(Node nd) {
        SplitPane.setResizableWithParent(nd, Boolean.FALSE);
    }

    public static boolean isLeftButton(MouseEvent ev) {
        return ev.getButton() == MouseButton.PRIMARY;
    }

    public static boolean isPopupTrigger(MouseEvent ev) {
        if (ev.getButton() == MouseButton.SECONDARY) {
            if (CPlatform.isMac()) {
                if (!ev.isAltDown() && !ev.isMetaDown() && !ev.isShiftDown()) {
                    return true;
                }
                return false;
            } else if (!ev.isAltDown() && !ev.isControlDown() && !ev.isMetaDown() && !ev.isShiftDown()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public static void disableAlternativeRowColor(FxTable<?> table) {
        style(table.table, CommonStyles.DISABLE_ALTERNATIVE_ROW_COLOR);
    }

    public static void disableAlternativeRowColor(TableView<?> table) {
        style(table, CommonStyles.DISABLE_ALTERNATIVE_ROW_COLOR);
    }

    public static void disableAlternativeRowColor(ListView<?> v) {
        style(v, CommonStyles.DISABLE_ALTERNATIVE_ROW_COLOR);
    }

    public static KeyCode getShortcutKeyCode() {
        KeyEvent ev = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, ButtonBar.BUTTON_ORDER_NONE, ButtonBar.BUTTON_ORDER_NONE, KeyCode.CONTROL, false, true, false, false);
        if (ev.isShortcutDown()) {
            return KeyCode.CONTROL;
        }
        KeyEvent ev2 = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, ButtonBar.BUTTON_ORDER_NONE, ButtonBar.BUTTON_ORDER_NONE, KeyCode.META, false, false, false, true);
        if (ev2.isShortcutDown()) {
            return KeyCode.META;
        }
        KeyEvent ev3 = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, ButtonBar.BUTTON_ORDER_NONE, ButtonBar.BUTTON_ORDER_NONE, KeyCode.ALT, false, false, true, false);
        if (ev3.isShortcutDown()) {
            return KeyCode.ALT;
        }
        KeyEvent ev4 = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, ButtonBar.BUTTON_ORDER_NONE, ButtonBar.BUTTON_ORDER_NONE, KeyCode.SHIFT, true, false, false, false);
        if (ev4.isShortcutDown()) {
            return KeyCode.SHIFT;
        }
        return null;
    }

    public static Disconnectable onChange(Runnable callback, ObservableValue<?>... observableValueArr) {
        return onChange(callback, false, observableValueArr);
    }

    public static Disconnectable onChange(Runnable callback, boolean fireImmediately, ObservableValue<?>... observableValueArr) {
        FxChangeListener li = new FxChangeListener(callback);
        li.listen(observableValueArr);
        if (fireImmediately) {
            li.fire();
        }
        return li;
    }

    public static Disconnectable onChangeLater(Runnable callback, ObservableValue<?>... observableValueArr) {
        FxChangeListener li = new FxChangeListener(callback) { // from class: goryachev.fx.FX.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // goryachev.fx.FxChangeListener
            public void invokeCallback() {
                FX.later(() -> {
                    super.invokeCallback();
                });
            }
        };
        li.listen(observableValueArr);
        return li;
    }

    public static void onChange(Runnable handler, ObservableList<?> list) {
        onChange(handler, false, (ObservableList) list);
    }

    public static void onChange(Runnable handler, boolean fireImmediately, ObservableList list) {
        list.addListener((ListChangeListener) ch -> {
            handler.run();
        });
        if (fireImmediately) {
            handler.run();
        }
    }

    public static void onInvalidation(Runnable handler, Observable prop) {
        prop.addListener(src -> {
            handler.run();
        });
    }

    public static void onInvalidation(Runnable handler, boolean fireImmediately, Observable prop) {
        prop.addListener(src -> {
            handler.run();
        });
        if (fireImmediately) {
            handler.run();
        }
    }

    public static void onInvalidation(Runnable handler, Observable... props) {
        for (Observable prop : props) {
            prop.addListener(src -> {
                handler.run();
            });
        }
    }

    public static void onInvalidation(Runnable handler, boolean fireImmediately, Observable... props) {
        for (Observable prop : props) {
            prop.addListener(src -> {
                handler.run();
            });
        }
        if (fireImmediately) {
            handler.run();
        }
    }

    public static String toFormattedColor(Color c) {
        int r = CKit.round(c.getRed() * 255.0d);
        int g = CKit.round(c.getGreen() * 255.0d);
        int b = CKit.round(c.getBlue() * 255.0d);
        int a = CKit.round(c.getOpacity() * 255.0d);
        return String.format("#%02X%02X%02X%02X", Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b), Integer.valueOf(a));
    }

    public static String toFormattedColorRGB(Color c) {
        int r = CKit.round(c.getRed() * 255.0d);
        int g = CKit.round(c.getGreen() * 255.0d);
        int b = CKit.round(c.getBlue() * 255.0d);
        return String.format("#%02X%02X%02X", Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b));
    }

    public static boolean isParentWindowVisible(Node n) {
        Scene s;
        Window w;
        if (n == null || (s = n.getScene()) == null || (w = s.windowProperty().get()) == null) {
            return false;
        }
        return w.isShowing();
    }

    public static ReadOnlyObjectProperty<Window> parentWindowProperty(Node n) {
        return new ParentWindow(n).windowProperty();
    }

    public static <T> void addChangeListener(ObservableList<T> list, ListChangeListener<? super T> li) {
        list.addListener(li);
    }

    public static <T> void addChangeListener(ObservableList<T> list, Runnable callback) {
        list.addListener((ListChangeListener<? super T>) change -> {
            callback.run();
        });
    }

    public static <T> void addChangeListener(ObservableList<T> list, boolean fireImmediately, Runnable callback) {
        list.addListener((ListChangeListener<? super T>) change -> {
            callback.run();
        });
        if (fireImmediately) {
            callback.run();
        }
    }

    public static <T> void addChangeListener(ObservableValue<T> prop, ChangeListener<? super T> li) {
        prop.addListener(li);
    }

    public static <T> void addChangeListener(ObservableValue<T> prop, Consumer<? super T> li) {
        prop.addListener((observableValue, obj, obj2) -> {
            li.accept(obj2);
        });
    }

    public static <T> void addChangeListener(ObservableValue<T> prop, Runnable callback) {
        prop.addListener((observableValue, obj, obj2) -> {
            callback.run();
        });
    }

    public static <T> void addChangeListener(ObservableValue<T> prop, boolean fireImmediately, Runnable callback) {
        prop.addListener((observableValue, obj, obj2) -> {
            callback.run();
        });
        if (fireImmediately) {
            callback.run();
        }
    }

    public static <T> void addChangeListener(ObservableValue<T> prop, boolean fireImmediately, Consumer<? super T> li) {
        prop.addListener((observableValue, obj, obj2) -> {
            li.accept(obj2);
        });
        if (fireImmediately) {
            li.accept(prop.getValue());
        }
    }

    public static Integer toRGBA(Color c) {
        int r = (int) Math.round(c.getRed() * 255.0d);
        int g = (int) Math.round(c.getGreen() * 255.0d);
        int b = (int) Math.round(c.getBlue() * 255.0d);
        int a = (int) Math.round(c.getOpacity() * 255.0d);
        return Integer.valueOf(r | (g << 8) | (b << 16) | (a << 24));
    }

    public static void copy(String text) {
        if (text != null) {
            ClipboardContent cc = new ClipboardContent();
            cc.putString(text);
            Clipboard.getSystemClipboard().setContent(cc);
        }
    }

    public static Insets insets(double top, double right, double bottom, double left) {
        return new Insets(top, right, bottom, left);
    }

    public static Insets insets(double vert, double hor) {
        return new Insets(vert, hor, vert, hor);
    }

    public static Insets insets(double gap) {
        return new Insets(gap);
    }

    public static <S, T> void bindContentWithTransform(ObservableList<? extends S> source, ObservableList<T> target, Function<S, T> converter) {
        ListContentBinding.bind(source, target, converter);
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FX$ListContentBinding.class */
    private static class ListContentBinding<S, T> implements ListChangeListener<S>, WeakListener {
        private final Function<S, T> converter;
        private final WeakReference<List<T>> ref;

        public ListContentBinding(List<T> target, Function<S, T> converter) {
            this.ref = new WeakReference<>(target);
            this.converter = converter;
        }

        public static <S, T> void bind(ObservableList<? extends S> source, ObservableList<T> target, Function<S, T> converter) {
            ListContentBinding<S, T> li = new ListContentBinding<>(target, converter);
            target.setAll(transform(source, converter));
            source.removeListener(li);
            source.addListener(li);
        }

        protected T transform(S item) {
            return this.converter.apply(item);
        }

        protected static <S, T> List<T> transform(List<? extends S> items, Function<S, T> converter) {
            int sz = items.size();
            CList<T> rv = new CList<>(sz);
            for (int i = 0; i < sz; i++) {
                S item = items.get(i);
                T val = converter.apply(item);
                rv.add(val);
            }
            return rv;
        }

        @Override // javafx.collections.ListChangeListener
        public void onChanged(Change<? extends S> ch) {
            List<T> target = this.ref.get();
            if (target == null) {
                ch.getList().removeListener(this);
                return;
            }
            while (ch.next()) {
                if (ch.wasPermutated()) {
                    target.subList(ch.getFrom(), ch.getTo()).clear();
                    target.addAll(ch.getFrom(), transform(ch.getList().subList(ch.getFrom(), ch.getTo()), this.converter));
                } else {
                    if (ch.wasRemoved()) {
                        target.subList(ch.getFrom(), ch.getFrom() + ch.getRemovedSize()).clear();
                    }
                    if (ch.wasAdded()) {
                        target.addAll(ch.getFrom(), transform(ch.getAddedSubList(), this.converter));
                    }
                }
            }
        }

        @Override // javafx.beans.WeakListener
        public boolean wasGarbageCollected() {
            return this.ref.get() == null;
        }

        public int hashCode() {
            Object me = this.ref.get();
            if (me == null) {
                return 0;
            }
            return me.hashCode();
        }

        public boolean equals(Object x) {
            if (this == x) {
                return true;
            }
            Object me = this.ref.get();
            return me != null && (x instanceof ListContentBinding) && me == ((ListContentBinding) x).ref.get();
        }
    }

    public static <S, T> ObservableList<T> transform(ObservableList<S> source, final Function<S, T> converter) {
        return new TransformationList<T, S>(source) { // from class: goryachev.fx.FX.4
            @Override // javafx.collections.transformation.TransformationList
            public int getSourceIndex(int index) {
                return index;
            }

            @Override // javafx.collections.transformation.TransformationList
            public int getViewIndex(int index) {
                return index;
            }

            @Override // java.util.AbstractList, java.util.List
            public T get(int index) {
                S src = getSource().get(index);
                return (T) converter.apply(src);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return getSource().size();
            }

            @Override // javafx.collections.transformation.TransformationList
            protected void sourceChanged(final ListChangeListener.Change<? extends S> c) {
                final Function function = converter;
                fireChange((ListChangeListener.Change<T>) new ListChangeListener.Change<T>(this) { // from class: goryachev.fx.FX.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // javafx.collections.ListChangeListener.Change
                    public List<T> getRemoved() {
                        ArrayList arrayList = new ArrayList(c.getRemovedSize());
                        for (Object obj : c.getRemoved()) {
                            arrayList.add(function.apply(obj));
                        }
                        return arrayList;
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean wasAdded() {
                        return c.wasAdded();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean wasRemoved() {
                        return c.wasRemoved();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean wasReplaced() {
                        return c.wasReplaced();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean wasUpdated() {
                        return c.wasUpdated();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean wasPermutated() {
                        return c.wasPermutated();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public int getPermutation(int ix) {
                        return c.getPermutation(ix);
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    protected int[] getPermutation() {
                        return new int[0];
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public int getFrom() {
                        return c.getFrom();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public int getTo() {
                        return c.getTo();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public boolean next() {
                        return c.next();
                    }

                    @Override // javafx.collections.ListChangeListener.Change
                    public void reset() {
                        c.reset();
                    }
                });
            }
        };
    }

    public static <T extends FxWindow> T findFirstWindowOfType(Class<T> type, boolean exact) {
        for (Window w : Window.getWindows()) {
            if (exact) {
                if (w.getClass() == type) {
                    return (T) w;
                }
            } else if (type.isAssignableFrom(w.getClass())) {
                return (T) w;
            }
        }
        return null;
    }

    public static <T extends FxWindow> T openSingleWindow(Class<T> type, Supplier<T> gen) {
        checkThread();
        T w = findFirstWindowOfType(type, true);
        if (w == null) {
            w = gen.get();
            w.open();
        } else if (w.isIconified()) {
            w.setIconified(false);
        }
        w.requestFocus();
        return (T) w;
    }

  /*  public static <T extends FxWindow> T openSingleWindow(Class<T> type) {
        return (T) openSingleWindow(type, () -> {
            try {
                return (FxWindow) type.newInstance();
            } catch (Throwable e) {
                log.error(e);
                throw new Error(type + " must declare a no-arg constructor", e);
            }
        });
    }*/

    public static ReadOnlyBooleanProperty getNodeVisibleInSceneProperty(Node node) {
        ReadOnlyBooleanWrapper showing = new ReadOnlyBooleanWrapper();
        ChangeListener<Window> windowChangeListener = (observableValue, p, win) -> {
            showing.unbind();
            if (win != null) {
                showing.bind(win.showingProperty());
            } else {
                showing.set(false);
            }
        };
        ChangeListener<Scene> sceneChangeListener = (observableValue2, prevScene, currScene) -> {
            showing.unbind();
            if (prevScene != null) {
                prevScene.windowProperty().removeListener(windowChangeListener);
            }
            if (currScene == null) {
                showing.set(false);
                return;
            }
            currScene.windowProperty().addListener(windowChangeListener);
            if (currScene.getWindow() == null) {
                showing.set(false);
            } else {
                showing.bind(currScene.getWindow().showingProperty());
            }
        };
        node.sceneProperty().addListener(sceneChangeListener);
        Scene scene = node.getScene();
        if (scene == null) {
            showing.set(false);
        } else {
            scene.windowProperty().addListener(windowChangeListener);
            Window w = scene.getWindow();
            if (w == null) {
                showing.set(false);
            } else {
                showing.bind(w.showingProperty());
            }
        }
        return showing.getReadOnlyProperty();
    }

    public static void onMousePressed(Node n, Runnable action) {
        n.addEventHandler(MouseEvent.MOUSE_PRESSED, ev -> {
            action.run();
        });
    }

    public static void openFile(File file) {
        String uri = file.toURI().toString();
        FxApplication.getInstance().getHostServices().showDocument(uri);
    }

    public static Disconnectable onChange(ReadOnlyIntegerProperty prop, IntConsumer onChange) {
        return new DisconnectableIntegerListener(prop, onChange);
    }

    public static void setStyle(Node n, String property, Object value) {
        if (n != null) {
            String s = n.getStyle();
            FxStyleHandler m = new FxStyleHandler(s);
            m.put(property, value);
            String s2 = m.toStyleString();
            n.setStyle(s2);
        }
    }

    public static void removeStyle(Node n, String property) {
        if (n != null) {
            String s = n.getStyle();
            FxStyleHandler m = new FxStyleHandler(s);
            m.remove(property);
            String s2 = m.toStyleString();
            n.setStyle(s2);
        }
    }

    public static void applyStyleSheet(String old, String cur) {
        for (Window w : Window.getWindows()) {
            applyStyleSheet(w, old, cur);
        }
    }

    public static void applyStyleSheet(Window w, String old, String cur) {
        Scene scene;
        if (cur != null && (scene = w.getScene()) != null) {
            if (old != null) {
                scene.getStylesheets().remove(old);
            }
            scene.getStylesheets().add(cur);
        }
    }
}
