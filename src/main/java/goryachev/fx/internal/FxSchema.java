package goryachev.fx.internal;

import goryachev.common.util.GlobalSettings;
import goryachev.common.util.SB;
import goryachev.common.util.SStream;
import goryachev.fx.FX;
import goryachev.fx.FxDialog;
import goryachev.fx.FxWindow;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/FxSchema.class */
public class FxSchema {
    public static final String FX_PREFIX = "FX.";
    public static final String WINDOWS = "FX..WINDOWS";
    public static final String SFX_COLUMNS = ".COLS";
    public static final String SFX_DIVIDERS = ".DIVS";
    public static final String SFX_SELECTION = ".SEL";
    public static final String SFX_SETTINGS = ".SETTINGS";
    public static final String SORT_ASCENDING = "A";
    public static final String SORT_DESCENDING = "D";
    public static final String SORT_NONE = "N";
    public static final String WINDOW_FULLSCREEN = "F";
    public static final String WINDOW_MAXIMIZED = "X";
    public static final String WINDOW_ICONIFIED = "I";
    public static final String WINDOW_NORMAL = "N";
    private static final Object PROP_LOAD_HANDLER = new Object();
    private static final Object PROP_NAME = new Object();
    private static final Object PROP_SKIP_SETTINGS = new Object();

    public static void storeWindow(String prefix, FxWindow win) {
        double x = win.getNormalX();
        double y = win.getNormalY();
        double w = win.getNormalWidth();
        double h = win.getNormalHeight();
        SStream s = new SStream();
        s.add(x);
        s.add(y);
        s.add(w);
        s.add(h);
        if (win.isFullScreen()) {
            s.add("F");
        } else if (win.isMaximized()) {
            s.add(WINDOW_MAXIMIZED);
        } else if (win.isIconified()) {
            s.add(WINDOW_ICONIFIED);
        } else {
            s.add("N");
        }
        GlobalSettings.setStream(FX_PREFIX + prefix, s);
    }

    public static void restoreWindow(String prefix, FxWindow win) {
        FxDialog d;
        Window parent;
        try {
            String id = FX_PREFIX + prefix;
            SStream s = GlobalSettings.getStream(id);
            double x = s.nextDouble(-1.0d);
            double y = s.nextDouble(-1.0d);
            double w = s.nextDouble(-1.0d);
            double h = s.nextDouble(-1.0d);
            String state = s.nextString("N");
            if (w > 0.0d && h > 0.0d) {
                if (FX.isValidCoordinates(x, y) && !(win instanceof FxDialog)) {
                    win.setX(x);
                    win.setY(y);
                }
                if (win.isResizable()) {
                    win.setWidth(w);
                    win.setHeight(h);
                } else {
                    w = win.getWidth();
                    h = win.getHeight();
                }
                switch (state.hashCode()) {
                    case 70:
                        if (!state.equals("F")) {
                            break;
                        } else {
                            win.setFullScreen(true);
                            break;
                        }
                    case 88:
                        if (!state.equals(WINDOW_MAXIMIZED)) {
                            break;
                        } else {
                            win.setMaximized(true);
                            break;
                        }
                }
                if ((win instanceof FxDialog) && (parent = (d = (FxDialog) win).getOwner()) != null) {
                    double cx = parent.getX() + (parent.getWidth() / 2.0d);
                    double cy = parent.getY() + (parent.getHeight() / 2.0d);
                    d.setX(cx - (w / 2.0d));
                    d.setY(cy - (h / 2.0d));
                }
            }
        } catch (Exception e) {
        }
    }

    private static void storeLocalSettings(String prefix, LocalSettings s) {
        String k = String.valueOf(prefix) + SFX_SETTINGS;
        s.saveValues(k);
    }

    private static void restoreLocalSettings(String prefix, LocalSettings s) {
        String k = String.valueOf(prefix) + SFX_SETTINGS;
        s.loadValues(k);
    }

    private static void storeSplitPane(String prefix, SplitPane sp) {
        SStream s = new SStream();
        s.add(sp.getDividers().size());
        s.addAll(sp.getDividerPositions());
        String k = String.valueOf(prefix) + SFX_DIVIDERS;
        GlobalSettings.setStream(k, s);
    }

    private static void restoreSplitPane(String prefix, SplitPane sp) {
        String k = String.valueOf(prefix) + SFX_DIVIDERS;
        SStream s = GlobalSettings.getStream(k);
        FX.later(() -> {
            int ct = s.nextInt();
            if (sp.getDividers().size() == ct) {
                for (int i = 0; i < ct; i++) {
                    double div = s.nextDouble();
                    sp.setDividerPosition(i, div);
                }
            }
        });
    }

    private static void storeTableView(String prefix, TableView t) {
        int sortOrder;
        ObservableList<TableColumn<?, ?>> cs = t.getColumns();
        int sz = cs.size();
        ObservableList<TableColumn<?, ?>> sorted = t.getSortOrder();
        SStream s = new SStream();
        s.add(sz);
        for (int i = 0; i < sz; i++) {
            TableColumn<?, ?> c = cs.get(i);
            int sortOrder2 = sorted.indexOf(c);
            if (sortOrder2 < 0) {
                sortOrder = 0;
            } else {
                sortOrder = sortOrder2 + 1;
                if (c.getSortType() == TableColumn.SortType.DESCENDING) {
                    sortOrder = -sortOrder;
                }
            }
            s.add(c.getId());
            s.add(c.getWidth());
            s.add(sortOrder);
        }
        GlobalSettings.setStream(String.valueOf(prefix) + SFX_COLUMNS, s);
        int ix = t.getSelectionModel().getSelectedIndex();
        GlobalSettings.setInt(String.valueOf(prefix) + SFX_SELECTION, ix);
    }

    private static void restoreTableView(String prefix, TableView t) {
        ObservableList<TableColumn<?, ?>> cs = t.getColumns();
        SStream s = GlobalSettings.getStream(String.valueOf(prefix) + SFX_COLUMNS);
        int sz = s.nextInt();
        if (sz == cs.size()) {
            for (int i = 0; i < sz; i++) {
                cs.get(i);
                s.nextString();
                s.nextDouble();
                s.nextInt();
            }
        }
        GlobalSettings.getInt(String.valueOf(prefix) + SFX_SELECTION, -1);
    }

    private static void storeTabPane(String prefix, TabPane p) {
        int ix = p.getSelectionModel().getSelectedIndex();
        GlobalSettings.setInt(String.valueOf(prefix) + SFX_SELECTION, ix);
    }

    private static void restoreTabPane(String prefix, TabPane p) {
        int ix = GlobalSettings.getInt(String.valueOf(prefix) + SFX_SELECTION, -1);
        if (ix >= 0) {
            FX.later(() -> {
                if (ix < p.getTabs().size()) {
                    p.getSelectionModel().select(ix);
                }
            });
        }
    }

    private static String getFullName(String windowPrefix, Node root, Node n) {
        SB sb;
        String name = getName(n);
        if (name == null || (sb = getFullNamePrivate(windowPrefix, null, root, n)) == null) {
            return null;
        }
        return sb.toString();
    }

    private static SB getFullNamePrivate(String windowPrefix, SB sb, Node root, Node n) {
        String name;
        SB sb2;
        if (n == null || (name = getName(n)) == null) {
            return null;
        }
        if (n == root) {
            sb2 = new SB();
            sb2.a(FX_PREFIX);
            sb2.a(windowPrefix);
        } else {
            Parent p = n.getParent();
            if (p == null) {
                return null;
            }
            sb2 = getFullNamePrivate(windowPrefix, sb, root, p);
            if (sb2 == null) {
                return null;
            }
            sb2.a('.');
            sb2.a(name);
        }
        return sb2;
    }

    public static void storeNode(String windowPrefix, Node root, Node n) {
        String name = getFullName(windowPrefix, root, n);
        if (name == null) {
            return;
        }
        LocalSettings s = LocalSettings.find(n);
        if (s != null) {
            storeLocalSettings(name, s);
        }
        if (!isSkipSettings(n)) {
            if (n instanceof SplitPane) {
                storeSplitPane(name, (SplitPane) n);
            } else if (n instanceof TableView) {
                storeTableView(name, (TableView) n);
            } else if (n instanceof TabPane) {
                storeTabPane(name, (TabPane) n);
            }
        }
        if (n instanceof Parent) {
            for (Node ch : ((Parent) n).getChildrenUnmodifiable()) {
                storeNode(windowPrefix, root, ch);
            }
        }
    }

    public static void restoreNode(String windowPrefix, Node root, Node n) {
        String name = getFullName(windowPrefix, root, n);
        if (name == null) {
            return;
        }
        if (!isSkipSettings(n)) {
            if (n instanceof SplitPane) {
                restoreSplitPane(name, (SplitPane) n);
            } else if (n instanceof TableView) {
                restoreTableView(name, (TableView) n);
            } else if (n instanceof TabPane) {
                restoreTabPane(name, (TabPane) n);
            }
        }
        if (n instanceof Parent) {
            for (Node ch : ((Parent) n).getChildrenUnmodifiable()) {
                restoreNode(windowPrefix, root, ch);
            }
        }
        LocalSettings s = LocalSettings.find(n);
        if (s != null) {
            restoreLocalSettings(name, s);
        }
        Runnable r = getOnSettingsLoaded(n);
        if (r != null) {
            r.run();
        }
    }

    public static void setName(Node n, String name) {
        n.getProperties().put(PROP_NAME, name);
    }

    public static String getName(Node n) {
        Object x = n.getProperties().get(PROP_NAME);
        if (x instanceof String) {
            return (String) x;
        }
        if ((n instanceof MenuBar) || (n instanceof Shape) || (n instanceof ImageView)) {
            return null;
        }
        String id = n.getId();
        if (id != null) {
            return id;
        }
        return n.getClass().getSimpleName();
    }

    public static void setOnSettingsLoaded(Node n, Runnable r) {
        n.getProperties().put(PROP_LOAD_HANDLER, r);
    }

    private static Runnable getOnSettingsLoaded(Node n) {
        Object x = n.getProperties().get(PROP_LOAD_HANDLER);
        if (x instanceof Runnable) {
            return (Runnable) x;
        }
        return null;
    }

    public static void setSkipSettings(Node n) {
        n.getProperties().put(PROP_SKIP_SETTINGS, Boolean.TRUE);
    }

    public static boolean isSkipSettings(Node n) {
        Object x = n.getProperties().get(PROP_SKIP_SETTINGS);
        return Boolean.TRUE.equals(x);
    }
}
