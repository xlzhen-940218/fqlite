package goryachev.fx;

import goryachev.common.util.CComparator;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.D;
import goryachev.common.util.SB;
import goryachev.common.util.TextTools;
import java.util.Iterator;
import javafx.css.CssMetaData;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDump.class */
public class FxDump {
    private final KeyCode trigger;
    private boolean showNulls;
    private double x;
    private double y;
    private PickResult r;
    private CComparator<CssMetaData<? extends Styleable, ?>> sorter;

    public FxDump(KeyCode trigger) {
        this.trigger = trigger;
    }

    public FxDump() {
        this(KeyCode.BACK_QUOTE);
    }

    public static void attach(Node n) {
        new FxDump().attachNode(n);
    }

    public static void attach(Window n) {
        new FxDump().attachWindow(n);
    }

    public void setShowNulls(boolean on) {
        this.showNulls = on;
    }

    public void attachWindow(Window s) {
        attachNode(s.getScene().getRoot());
    }

    public void attachNode(Node n) {
        n.getScene().addEventFilter(KeyEvent.KEY_PRESSED, ev -> {
            handleKeyPress(ev);
        });
        n.getScene().addEventFilter(MouseEvent.ANY, ev2 -> {
            handleMouseEvent(ev2);
        });
    }

    protected void handleMouseEvent(MouseEvent ev) {
        this.x = ev.getScreenX();
        this.y = ev.getScreenY();
        this.r = ev.getPickResult();
    }

    protected void handleKeyPress(KeyEvent ev) {
        if (ev.getCode() == this.trigger) {
            dump();
        }
    }

    protected void dump() {
        Node n;
        if (this.r != null && (n = this.r.getIntersectedNode()) != null) {
            dump(n);
        }
    }

    protected void sort(CList<CssMetaData<? extends Styleable, ?>> list) {
        if (this.sorter == null) {
            this.sorter = new CComparator<CssMetaData<? extends Styleable, ?>>() { // from class: goryachev.fx.FxDump.1
                @Override // goryachev.common.util.CComparator, java.util.Comparator
                public int compare(CssMetaData<? extends Styleable, ?> a, CssMetaData<? extends Styleable, ?> b) {
                    return compareAsStrings(a.getProperty(), b.getProperty());
                }
            };
        }
        this.sorter.sort(list);
    }

    protected boolean shouldShow(Object x) {
        if (x == null) {
            return this.showNulls;
        }
        return true;
    }

    protected String describeBackground(Background b) {
        SB sb = new SB();
        sb.a("Background<");
        boolean sep = false;
        for (BackgroundFill f : b.getFills()) {
            if (sep) {
                sb.a(",");
            } else {
                sep = true;
            }
            sb.a(describe(f));
        }
        sb.a(">");
        return sb.toString();
    }

    protected String describeBackgroundFill(BackgroundFill f) {
        SB sb = new SB();
        sb.a("Fill<").a(describe(f.getFill())).a(",");
        sb.a(describe(f.getInsets())).a(", ");
        sb.a(describe(f.getRadii())).a(">");
        return sb.toString();
    }

    protected String describeDouble(Double x) {
        if (x == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        double v = x.doubleValue();
        if (v == Double.MAX_VALUE || v == Double.MIN_VALUE) {
            return "MAX_VALUE";
        }
        if (Double.isNaN(v)) {
            return "NaN";
        }
        long n = x.longValue();
        if (n == v) {
            return String.valueOf(n);
        }
        return String.valueOf(v);
    }

    protected String describeFont(Font f) {
        SB sb = new SB();
        sb.a(f.getName()).sp().a(f.getStyle()).sp().a(Double.valueOf(f.getSize()));
        return sb.toString();
    }

    protected Object describe(Object x) {
        if (x instanceof Double) {
            return describeDouble((Double) x);
        }
        if (x instanceof Double[]) {
            Double[] v = (Double[]) x;
            SB sb = new SB();
            sb.a("<");
            boolean sep = false;
            for (Double d : v) {
                if (sep) {
                    sb.a(", ");
                } else {
                    sep = true;
                }
                sb.a(describeDouble(d));
            }
            sb.a(">");
            return sb.toString();
        } else if (x instanceof Background) {
            return describeBackground((Background) x);
        } else {
            if (x instanceof BackgroundFill) {
                return describeBackgroundFill((BackgroundFill) x);
            }
            if (x instanceof Font) {
                return describeFont((Font) x);
            }
            return x;
        }
    }

    protected void dump(Node n) {
        SB sb = new SB(4096);
        sb.nl();
        while (n != null) {
            sb.a(CKit.getSimpleName(n));
            String id = n.getId();
            if (CKit.isNotBlank(id)) {
                sb.a(" #");
                sb.a(id);
            }
            for (String s : n.getStyleClass()) {
                sb.a(" .").a(s);
            }
            for (PseudoClass c : n.getPseudoClassStates()) {
                sb.a(" :").a(c);
            }
            sb.nl();
            if (n instanceof Text) {
                sb.sp(4);
                sb.a("text: ");
                sb.a(TextTools.escapeControlsForPrintout(((Text) n).getText()));
                sb.nl();
            }
            CList<CssMetaData<? extends Styleable, ?>> md = new CList<>(n.getCssMetaData());
            sort(md);
            Iterator<CssMetaData<? extends Styleable, ?>> it = md.iterator();
            while (it.hasNext()) {
                CssMetaData d = it.next();
                String k = d.getProperty();
                Object v = d.getStyleableProperty(n).getValue();
                if (shouldShow(v)) {
                    Object val = describe(v);
                    sb.sp(4).a(k);
                    sb.sp().a(val);
                    if (d.isInherits()) {
                        sb.a(" *");
                    }
                    sb.nl();
                }
            }
            n = n.getParent();
        }
        D.print(sb);
    }
}
