package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane.class */
public class CPane extends Pane {
    public static final double FILL = -1.0d;
    public static final double PREF = -2.0d;
    protected CList<Entry> entries = new CList<>();
    protected CList<LC> cols = new CList<>();
    protected CList<LC> rows = new CList<>();
    private final StyleableProperty<Number> hgap = SPF.createStyleableNumberProperty(this, "hgap", "-ag-hgap", s -> {
        return s.hgap;
    });
    private final StyleableProperty<Number> vgap = SPF.createStyleableNumberProperty(this, "vgap", "-ag-vgap", s -> {
        return s.vgap;
    });
    private static final Log log = Log.get("CPane");
    public static final CssStyle STYLE = new CssStyle("CPane_PANE");
    public static final CC TOP = new CC(true);
    public static final CC BOTTOM = new CC(true);
    public static final CC LEFT = new CC(true);
    public static final CC RIGHT = new CC(true);
    public static final CC CENTER = new CC(true);
    private static final StyleablePropertyFactory<CPane> SPF = new StyleablePropertyFactory<>(Pane.getClassCssMetaData());

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$AL.class */
    public enum AL {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        LEADING,
        TRAILING,
        FULL;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static AL[] valuesCustom() {
            AL[] valuesCustom = values();
            int length = valuesCustom.length;
            AL[] alArr = new AL[length];
            System.arraycopy(valuesCustom, 0, alArr, 0, length);
            return alArr;
        }
    }

    public CPane() {
    }

    public CPane(Node n) {
        setCenter(n);
    }

    public void setDefaultStyle() {
        FX.style(this, STYLE);
    }

    public void setHGap(int gap) {
        this.hgap.setValue(Integer.valueOf(gap));
    }

    public int getHGap() {
        return this.hgap.getValue().intValue();
    }

    public ObservableValue<Number> hgapProperty() {
        return (ObservableValue) this.hgap;
    }

    public void setVGap(int gap) {
        this.vgap.setValue(Integer.valueOf(gap));
    }

    public int getVGap() {
        return this.vgap.getValue().intValue();
    }

    public ObservableValue<Number> vgapProperty() {
        return (ObservableValue) this.vgap;
    }

    @Override // javafx.scene.layout.Region, javafx.scene.Node, javafx.css.Styleable
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return SPF.getCssMetaData();
    }

    public void setGaps(int horizontal, int vertical) {
        setHGap(horizontal);
        setVGap(vertical);
    }

    public void setGaps(int gaps) {
        setHGap(gaps);
        setVGap(gaps);
    }

    public void setPadding(double gap) {
        setPadding(FX.insets(gap));
    }

    public void setPadding(double ver, double hor) {
        setPadding(FX.insets(ver, hor));
    }

    public void setPadding(double top, double right, double bottom, double left) {
        setPadding(FX.insets(top, right, bottom, left));
    }

    public static Label rlabel(String text) {
        return FX.label(text, Pos.BASELINE_RIGHT);
    }

    public int getCenterColumnCount() {
        return this.cols.size();
    }

    public int getCenterRowCount() {
        return this.rows.size();
    }

    public void addColumn(double spec) {
        this.cols.add(new LC(spec));
    }

    public void addColumns(double... specs) {
        for (double cs : specs) {
            addColumn(cs);
        }
    }

    public void insertColumn(int ix, double spec) {
        CKit.todo();
    }

    public void addRow(double spec) {
        this.rows.add(new LC(spec));
    }

    public void addRows(double... specs) {
        for (double rs : specs) {
            addRow(rs);
        }
    }

    public void insertRow(int ix, double spec) {
        if (ix < 0) {
            throw new IllegalArgumentException("negative row: " + ix);
        }
        if (ix >= getCenterRowCount()) {
            ix = getCenterRowCount();
        }
        this.rows.add(ix, new LC());
        Iterator<Entry> it = this.entries.iterator();
        while (it.hasNext()) {
            Entry en = it.next();
            if (en.cc.row >= ix) {
                en.cc.row++;
                en.cc.row2++;
            } else if (en.cc.row2 >= ix) {
                en.cc.row2++;
            }
        }
    }

    protected LC getColumnSpec(int col) {
        while (getCenterColumnCount() <= col) {
            addColumn(-2.0d);
        }
        return this.cols.get(col);
    }

    public void setColumnMinimumSize(int col, int size) {
        LC c = getColumnSpec(col);
        c.min = size;
    }

    public void setColumnSpec(int col, double spec) {
        LC c = getColumnSpec(col);
        c.width = spec;
    }

    protected LC getRowSpec(int row) {
        while (getCenterRowCount() <= row) {
            addRow(-2.0d);
        }
        return this.rows.get(row);
    }

    public void setRowMinimumSize(int row, int size) {
        LC c = getRowSpec(row);
        c.min = size;
    }

    public void setRow(int row, double spec) {
        LC c = getRowSpec(row);
        c.width = spec;
    }

    public Node add(Node c) {
        setCenter(c);
        return c;
    }

    public void addRow(int row, Node... ns) {
        for (int i = 0; i < ns.length; i++) {
            Node nd = ns[i];
            if (nd != null) {
                add(i, row, nd);
            }
        }
    }

    public void add(int col, int row, Node nd) {
        add(col, row, 1, 1, nd);
    }

    public void add(int col, int row, int colSpan, int rowSpan, Node nd) {
        addPrivate(nd, new CC(col, row, (col + colSpan) - 1, (row + rowSpan) - 1));
    }

    protected Entry getEntry(Node c) {
        for (int i = this.entries.size() - 1; i >= 0; i--) {
            Entry en = this.entries.get(i);
            if (en.node == c) {
                return en;
            }
        }
        return null;
    }

    protected Node getBorderComponent(CC cc) {
        int sz = this.entries.size();
        for (int i = 0; i < sz; i++) {
            Entry en = this.entries.get(i);
            if (en.cc.border && en.cc == cc) {
                return en.node;
            }
        }
        return null;
    }

    protected Node set(Node c, CC cc) {
        Node old = getBorderComponent(cc);
        if (old != c) {
            if (old != null) {
                removeLayoutComponent(old);
            }
            if (c != null) {
                addPrivate(c, cc);
            }
        }
        return old;
    }

    public Node setCenter(Node c) {
        return set(c, CENTER);
    }

    public Node getCenter() {
        return getBorderComponent(CENTER);
    }

    public Node setRight(Node c) {
        return set(c, RIGHT);
    }

    public Node getRight() {
        return getBorderComponent(RIGHT);
    }

    public Node setLeft(Node c) {
        return set(c, LEFT);
    }

    public Node getLeft() {
        return getBorderComponent(LEFT);
    }

    public Node setTop(Node c) {
        return set(c, TOP);
    }

    public Node getTop() {
        return getBorderComponent(TOP);
    }

    public Node setBottom(Node c) {
        return set(c, BOTTOM);
    }

    public Node getBottom() {
        return getBorderComponent(BOTTOM);
    }

    protected void addPrivate(Node nd, CC cc) {
        Entry en = getEntry(nd);
        if (en == null) {
            if (nd instanceof Region) {
                Region r = (Region) nd;
                r.setMaxWidth(Double.MAX_VALUE);
                r.setMaxHeight(Double.MAX_VALUE);
            }
            en = new Entry();
            en.node = nd;
            this.entries.add(en);
            if (!cc.border) {
                int mxc = cc.col2;
                while (this.cols.size() <= mxc) {
                    this.cols.add(new LC());
                }
                int mxr = cc.row2;
                while (this.rows.size() <= mxr) {
                    this.rows.add(new LC());
                }
            }
        }
        en.cc = cc;
        getChildren().add(nd);
    }

    public void clear() {
        getChildren().clear();
    }

    protected void removeLayoutComponent(Node nd) {
        for (int i = this.entries.size() - 1; i >= 0; i--) {
            Entry en = this.entries.get(i);
            if (en.node == nd) {
                this.entries.remove(i);
                getChildren().remove(nd);
                return;
            }
        }
    }

    public void remove(Node c) {
        removeLayoutComponent(c);
    }

    protected void setBounds(Node nd, int left, int top, int width, int height) {
        layoutInArea(nd, left, top, width, height, 0.0d, HPos.CENTER, VPos.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefWidth(double height) {
        return new Helper().computeWidth(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefHeight(double width) {
        return new Helper().computeHeight(true);
    }

    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    protected double computeMinWidth(double height) {
        return new Helper().computeWidth(false);
    }

    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    protected double computeMinHeight(double width) {
        return new Helper().computeHeight(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.Parent
    public void layoutChildren() {
        try {
            new Helper().layout();
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$LC.class */
    public static class LC {
        public double width;
        public int min;
        public int max;
        public int group;
        public AL align;

        public LC() {
            this.width = -2.0d;
            this.align = AL.FULL;
        }

        public LC(double width) {
            this.width = width;
            this.align = AL.FULL;
        }

        public boolean isPercent() {
            return this.width >= 0.0d && this.width < 1.0d;
        }

        public boolean isFill() {
            return this.width == -1.0d;
        }

        public boolean isScaled() {
            return isFill() || isPercent();
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$CC.class */
    public static class CC {
        public int col;
        public int row;
        public int col2;
        public int row2;
        public AL horAlign;
        public AL verAlign;
        public boolean border;

        public CC(int col, int row) {
            this(col, row, col, row);
        }

        public CC(int col, int row, int col2, int row2) {
            this(col, row, col2, row2, AL.FULL, AL.FULL);
        }

        public CC(boolean border) {
            this.border = border;
        }

        public CC(int col, int row, int col2, int row2, AL horAlign, AL verAlign) {
            this.col = col;
            this.row = row;
            this.col2 = col2;
            this.row2 = row2;
            this.horAlign = horAlign;
            this.verAlign = verAlign;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$Entry.class */
    public static class Entry {
        public Node node;
        public CC cc;

        protected Entry() {
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$Axis.class */
    public abstract class Axis {
        public final int gap;
        public final CList<LC> specs;
        public Entry left;
        public Entry center;
        public Entry right;
        public int[] size;
        public int[] pos;
        public Axis otherAxis;

        public abstract int start(CC cc);

        public abstract int end(CC cc);

        public abstract double sizingMethod(boolean z, Node node, double d);

        public abstract double otherDimension(Entry entry, boolean z);

        public Axis(CList<LC> specs, int gap) {
            this.specs = specs;
            this.gap = gap;
            this.size = new int[specs.size()];
        }

        protected void computePositions(int start, int gap) {
            int sz = this.size.length;
            this.pos = new int[sz + 1];
            this.pos[0] = start;
            for (int i = 0; i < sz; i++) {
                start += this.size[i] + gap;
                this.pos[i + 1] = start;
            }
        }

        protected int min(int ix) {
            return this.specs.get(ix).min;
        }

        protected int fixed(int ix) {
            double w = this.specs.get(ix).width;
            if (w >= 1.0d) {
                return (int) w;
            }
            return 0;
        }

        protected int max(int ix) {
            int max = this.specs.get(ix).max;
            if (max > 0) {
                return max;
            }
            return Integer.MAX_VALUE;
        }

        protected int aggregateSize(int start, int end, int gap) {
            int rv = 0;
            for (int i = start; i < end; i++) {
                rv += this.size[i];
            }
            int ngaps = end - start;
            if (ngaps > 0) {
                rv += ngaps * gap;
            }
            return rv;
        }

        protected boolean spansScaled(int start, int end) {
            for (int i = start; i <= end; i++) {
                if (this.specs.get(i).isScaled()) {
                    return true;
                }
            }
            return false;
        }

        protected int computeSizes(boolean pref, boolean doingLayout) {
            int total = 0;
            int sz = this.specs.size();
            for (int i = 0; i < sz; i++) {
                int w = fixed(i);
                if (w == 0) {
                    int n = CPane.this.entries.size();
                    int j = 0;
                    while (true) {
                        if (j >= n) {
                            break;
                        }
                        Entry en = CPane.this.entries.get(j);
                        CC cc = en.cc;
                        int end = end(cc);
                        if (!cc.border && end == i) {
                            int start = start(cc);
                            boolean skip = doingLayout && spansScaled(start, end);
                            if (!skip) {
                                double other = otherDimension(en, doingLayout);
                                int d = CKit.ceil(sizingMethod(pref, en.node, other));
                                int cw = d - aggregateSize(start, i, this.gap);
                                if (cw > w) {
                                    w = cw;
                                }
                                int mx = max(i);
                                if (w > mx) {
                                    w = mx;
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                        j++;
                    }
                    int min = min(i);
                    if (w < min) {
                        w = min;
                    }
                }
                this.size[i] = w;
                total += w;
            }
            if (sz > 1) {
                total += this.gap * (sz - 1);
            }
            return total;
        }

        protected void adjust(int delta) {
            double w;
            double w2;
            int available = delta;
            double percent = 0.0d;
            int fillsCount = 0;
            int sz = this.specs.size();
            for (int i = 0; i < sz; i++) {
                LC lc = this.specs.get(i);
                if (lc.isPercent()) {
                    percent += lc.width;
                    available += this.size[i];
                } else if (lc.isFill()) {
                    fillsCount++;
                    available += this.size[i];
                }
            }
            if (available < 0) {
                available = 0;
            }
            double percentFactor = percent > 1.0d ? 1.0d / percent : percent;
            int remaining = available;
            for (int i2 = 0; i2 < sz; i2++) {
                LC lc2 = this.specs.get(i2);
                if (lc2.isPercent()) {
                    if (remaining > 0) {
                        w2 = lc2.width * available * percentFactor;
                    } else {
                        w2 = 0.0d;
                    }
                    int d = CKit.round(w2);
                    this.size[i2] = d;
                    remaining -= d;
                }
            }
            if (fillsCount > 0) {
                double cw = remaining / fillsCount;
                for (int i3 = 0; i3 < sz; i3++) {
                    if (this.specs.get(i3).isFill()) {
                        if (remaining >= 0) {
                            w = Math.min(cw, remaining);
                        } else {
                            w = 0.0d;
                        }
                        int d2 = CKit.ceil(w);
                        this.size[i3] = d2;
                        remaining -= d2;
                    }
                }
            }
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CPane$Helper.class */
    public class Helper {
        private final boolean ltr = true;
        public int mtop;
        public int mbottom;
        public int mleft;
        public int mright;
        public Node centerComp;
        public Node topComp;
        public Node bottomComp;
        public Node leftComp;
        public Node rightComp;
        private int tableLeft;
        private int tableRight;
        private int tableTop;
        private int tableBottom;
        private int midHeight;

        public Helper() {
            Insets m = CPane.this.getInsets();
            this.mtop = CKit.round(m.getTop());
            this.mbottom = CKit.round(m.getBottom());
            this.mleft = CKit.round(m.getLeft());
            this.mright = CKit.round(m.getRight());
        }

        protected void scanBorderComponents() {
            for (int i = CPane.this.entries.size() - 1; i >= 0; i--) {
                Entry en = CPane.this.entries.get(i);
                if (en.cc.border) {
                    CC cc = en.cc;
                    if (cc == CPane.CENTER) {
                        this.centerComp = en.node;
                    } else if (cc == CPane.TOP) {
                        this.topComp = en.node;
                    } else if (cc == CPane.BOTTOM) {
                        this.bottomComp = en.node;
                    } else if (cc == CPane.LEFT) {
                        this.leftComp = en.node;
                    } else if (cc == CPane.RIGHT) {
                        this.rightComp = en.node;
                    }
                }
            }
        }

        protected double sizeHeight(boolean pref, Node n) {
            double d = n.minHeight(-1.0d);
            if (pref) {
                d = Math.max(d, n.prefHeight(-1.0d));
            }
            return d;
        }

        protected double sizeWidth(boolean pref, Node n) {
            double d = n.minWidth(-1.0d);
            if (pref) {
                d = Math.max(d, n.prefWidth(-1.0d));
            }
            return d;
        }

        protected int computeBorderHeight(boolean pref) {
            int h = 0;
            Node node = this.ltr ? this.leftComp : this.rightComp;
            Node c = node;
            if (node != null) {
                int d = CKit.ceil(sizeHeight(pref, c));
                h = Math.max(d, 0);
            }
            Node node2 = this.ltr ? this.rightComp : this.leftComp;
            Node c2 = node2;
            if (node2 != null) {
                int d2 = CKit.ceil(sizeHeight(pref, c2));
                h = Math.max(d2, h);
            }
            this.midHeight = h;
            if (this.centerComp != null) {
                int d3 = CKit.ceil(sizeHeight(pref, this.centerComp));
                h = Math.max(d3, h);
            }
            if (this.topComp != null) {
                int d4 = CKit.ceil(sizeHeight(pref, this.topComp));
                h += d4 + CPane.this.getVGap();
            }
            if (this.bottomComp != null) {
                int d5 = CKit.ceil(sizeHeight(pref, this.bottomComp));
                h += d5 + CPane.this.getVGap();
            }
            return h + this.mtop + this.mbottom;
        }

        protected int computeBorderWidth(boolean pref) {
            int w = 0;
            Node node = this.ltr ? this.leftComp : this.rightComp;
            Node c = node;
            if (node != null) {
                int d = CKit.ceil(sizeWidth(pref, c));
                w = 0 + d + CPane.this.getHGap();
            }
            Node node2 = this.ltr ? this.rightComp : this.leftComp;
            Node c2 = node2;
            if (node2 != null) {
                int d2 = CKit.ceil(sizeWidth(pref, c2));
                w += d2 + CPane.this.getHGap();
            }
            if (this.centerComp != null) {
                int d3 = CKit.ceil(sizeWidth(pref, this.centerComp));
                w += d3;
            }
            if (this.topComp != null) {
                int d4 = CKit.ceil(sizeWidth(pref, this.topComp));
                w = Math.max(d4, w);
            }
            if (this.bottomComp != null) {
                int d5 = CKit.ceil(sizeWidth(pref, this.bottomComp));
                w = Math.max(d5, w);
            }
            return w + this.mleft + this.mright;
        }

        protected Axis createHorAxis() {
            return new Axis(cols, CPane.this.getHGap()) { // from class: goryachev.fx.CPane.Helper.1
                @Override // goryachev.fx.CPane.Axis
                public int start(CC cc) {
                    return cc.col;
                }

                @Override // goryachev.fx.CPane.Axis
                public int end(CC cc) {
                    return cc.col2;
                }

                @Override // goryachev.fx.CPane.Axis
                public double sizingMethod(boolean pref, Node n, double other) {
                    double d = n.minWidth(other);
                    if (pref) {
                        d = Math.max(n.prefWidth(other), d);
                    }
                    return d;
                }

                @Override // goryachev.fx.CPane.Axis
                public double otherDimension(Entry en, boolean doingLayout) {
                    return -1.0d;
                }
            };
        }

        protected Axis createVerAxis() {
            return new Axis(CPane.this.rows, CPane.this.getVGap()) { // from class: goryachev.fx.CPane.Helper.2
                @Override // goryachev.fx.CPane.Axis
                public int start(CC cc) {
                    return cc.row;
                }

                @Override // goryachev.fx.CPane.Axis
                public int end(CC cc) {
                    return cc.row2;
                }

                @Override // goryachev.fx.CPane.Axis
                public double sizingMethod(boolean pref, Node n, double other) {
                    double d = n.minHeight(other);
                    if (pref) {
                        d = Math.max(n.prefHeight(other), d);
                    }
                    return d;
                }

                @Override // goryachev.fx.CPane.Axis
                public double otherDimension(Entry en, boolean doingLayout) {
                    if (doingLayout) {
                        int start = this.otherAxis.start(en.cc);
                        int end = this.otherAxis.end(en.cc);
                        double other = 0.0d;
                        for (int i = start; i <= end; i++) {
                            other += this.otherAxis.size[i];
                        }
                        return other + (this.gap * (end - start));
                    }
                    return -1.0d;
                }
            };
        }

        protected void layoutBorderComponents() {
            int top = this.mtop;
            int bottom = CKit.round(CPane.this.getHeight()) - this.mbottom;
            int left = this.mleft;
            int right = CKit.round(CPane.this.getWidth()) - this.mright;
            if (this.topComp != null) {
                Node c = this.topComp;
                int h = CKit.ceil(c.prefHeight(right - left));
                CPane.this.setBounds(c, left, top, right - left, h);
                top += h + CPane.this.getVGap();
            }
            if (this.bottomComp != null) {
                Node c2 = this.bottomComp;
                int h2 = CKit.ceil(c2.prefHeight(right - left));
                CPane.this.setBounds(c2, left, bottom - h2, right - left, h2);
                bottom -= h2 + CPane.this.getVGap();
            }
            Node node = this.ltr ? this.rightComp : this.leftComp;
            Node c3 = node;
            if (node != null) {
                int w = CKit.ceil(c3.prefWidth(bottom - top));
                CPane.this.setBounds(c3, right - w, top, w, bottom - top);
                right -= w + CPane.this.getHGap();
            }
            Node node2 = this.ltr ? this.leftComp : this.rightComp;
            Node c4 = node2;
            if (node2 != null) {
                int w2 = CKit.ceil(c4.prefWidth(bottom - top));
                CPane.this.setBounds(c4, left, top, w2, bottom - top);
                left += w2 + CPane.this.getHGap();
            }
            if (this.centerComp != null) {
                CPane.this.setBounds(this.centerComp, left, top, right - left, bottom - top);
                right = left;
                bottom = top;
            }
            this.tableLeft = left;
            this.tableRight = right;
            this.tableTop = top;
            this.tableBottom = bottom;
        }

        public double computeWidth(boolean pref) {
            scanBorderComponents();
            int d = computeBorderWidth(pref);
            if (this.centerComp != null) {
                return d;
            }
            Axis hor = createHorAxis();
            int w = hor.computeSizes(pref, false);
            return w + d;
        }

        public double computeHeight(boolean pref) {
            scanBorderComponents();
            double d = computeBorderHeight(pref);
            if (this.centerComp != null) {
                return d;
            }
            Axis ver = createVerAxis();
            double h = ver.computeSizes(pref, false);
            return Math.max(h, this.midHeight) + (d - this.midHeight);
        }

        public void sizeComponents(Axis hor, Axis ver) {
            hor.computePositions(this.tableLeft, CPane.this.getHGap());
            ver.computePositions(this.tableTop, CPane.this.getVGap());
            int xr = this.ltr ? 0 : this.tableRight + this.mright;
            int sz = CPane.this.entries.size();
            for (int i = 0; i < sz; i++) {
                Entry en = CPane.this.entries.get(i);
                CC cc = en.cc;
                if (!cc.border) {
                    int x = hor.pos[cc.col];
                    int w = (hor.pos[cc.col2 + 1] - x) - CPane.this.getHGap();
                    int y = ver.pos[cc.row];
                    int h = (ver.pos[cc.row2 + 1] - y) - CPane.this.getVGap();
                    if (this.ltr) {
                        CPane.this.setBounds(en.node, x, y, w, h);
                    } else {
                        CPane.this.setBounds(en.node, (xr - x) - w, y, w, h);
                    }
                }
            }
        }

        public void layout() {
            scanBorderComponents();
            layoutBorderComponents();
            Axis hor = createHorAxis();
            int w = hor.computeSizes(true, true);
            int dw = (this.tableRight - this.tableLeft) - w;
            if (dw != 0) {
                hor.adjust(dw);
            }
            Axis ver = createVerAxis();
            ver.otherAxis = hor;
            int h = ver.computeSizes(true, true);
            int dh = (this.tableBottom - this.tableTop) - h;
            if (dh != 0) {
                ver.adjust(dh);
            }
            sizeComponents(hor, ver);
        }
    }
}
