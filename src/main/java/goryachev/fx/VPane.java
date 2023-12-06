package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.Parsers;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/VPane.class */
public class VPane extends Pane {
    public static final double FILL = -1.0d;
    public static final double PREF = -2.0d;
    protected int gap;
    protected static final Log log = Log.get("VPane");
    protected static final Object KEY_CONSTRAINT = new Object();

    public VPane(int hgap) {
        this.gap = hgap;
    }

    public VPane() {
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public void space(int height) {
        Pane r = new Pane();
        r.setPrefHeight(height);
        add(r);
    }

    public void space() {
        space(10);
    }

    public void add(Node n) {
        massage(n);
        getChildren().add(n);
    }

    public void add(int ix, Node n) {
        massage(n);
        getChildren().add(ix, n);
    }

    public void add(Node n, double constraint) {
        massage(n);
        getChildren().add(n);
        FX.setProperty(n, KEY_CONSTRAINT, Double.valueOf(constraint));
    }

    public void fill() {
        Region n = new Region();
        massage(n);
        getChildren().add(n);
        FX.setProperty(n, KEY_CONSTRAINT, Double.valueOf(-1.0d));
    }

    public void fill(Node n) {
        massage(n);
        getChildren().add(n);
        FX.setProperty(n, KEY_CONSTRAINT, Double.valueOf(-1.0d));
    }

    public void fill(int ix, Node n) {
        massage(n);
        getChildren().add(ix, n);
        FX.setProperty(n, KEY_CONSTRAINT, Double.valueOf(-1.0d));
    }

    protected void massage(Node n) {
        if (n instanceof Region) {
            Region r = (Region) n;
            r.setMaxHeight(Double.MAX_VALUE);
            r.setMaxWidth(Double.MAX_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefWidth(double height) {
        return h().computeWidth(height, true);
    }

    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    protected double computeMinWidth(double height) {
        return h().computeWidth(height, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefHeight(double width) {
        return h().computeSizes(true);
    }

    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    protected double computeMinHeight(double width) {
        return h().computeSizes(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.Parent
    public void layoutChildren() {
        try {
            h().layout();
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    protected Helper h() {
        return new Helper(getManagedChildren(), getInsets());
    }

    protected void setBounds(Node nd, double left, double top, double width, double height) {
        layoutInArea(nd, left, top, width, height, 0.0d, HPos.CENTER, VPos.CENTER);
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

    public void remove(Node n) {
        getChildren().remove(n);
    }

    public void clear() {
        getChildren().clear();
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/VPane$Helper.class */
    public class Helper {
        public final List<Node> nodes;
        public final int sz;
        public final int gaps;
        public int top;
        public int bottom;
        public int left;
        public int right;
        public int[] size;
        public int[] pos;

        public Helper(List<Node> nodes, Insets m) {
            this.nodes = nodes;
            this.sz = nodes.size();
            this.top = CKit.round(m.getTop());
            this.bottom = CKit.round(m.getBottom());
            this.left = CKit.round(m.getLeft());
            this.right = CKit.round(m.getRight());
            this.gaps = this.sz < 2 ? 0 : VPane.this.gap * (this.sz - 1);
        }

        protected double getConstraint(Node n) {
            Object x = FX.getProperty(n, VPane.KEY_CONSTRAINT);
            return Parsers.parseDouble(x, -2.0d);
        }

        protected boolean isFixed(double x) {
            return x > 1.0d;
        }

        protected boolean isPercent(double x) {
            return x < 1.0d && x >= 0.0d;
        }

        protected boolean isFill(double x) {
            return x == -1.0d;
        }

        protected int computeSizes(boolean preferred) {
            int d;
            int total = 0;
            for (int i = 0; i < this.sz; i++) {
                Node n = this.nodes.get(i);
                double cc = getConstraint(n);
                if (isFixed(cc)) {
                    d = CKit.ceil(cc);
                } else if (preferred) {
                    d = CKit.ceil(Math.max(n.prefHeight(-1.0d), n.minHeight(-1.0d)));
                } else {
                    d = CKit.ceil(n.minHeight(-1.0d));
                }
                if (this.size != null) {
                    this.size[i] = d;
                }
                total += d;
            }
            return total + this.top + this.bottom + this.gaps;
        }

        protected double computeWidth(double height, boolean preferred) {
            int d;
            int max = 0;
            for (int i = 0; i < this.sz; i++) {
                Node n = this.nodes.get(i);
                if (preferred) {
                    d = CKit.ceil(n.prefWidth(height));
                } else {
                    d = CKit.ceil(n.minWidth(height));
                }
                if (d > max) {
                    max = d;
                }
            }
            return max + this.left + this.right;
        }

        protected void computePositions() {
            int start = this.left;
            this.pos = new int[this.sz + 1];
            this.pos[0] = start;
            for (int i = 0; i < this.sz; i++) {
                start += this.size[i] + VPane.this.gap;
                this.pos[i + 1] = start;
            }
        }

        protected void adjust(int delta) {
            double w;
            double w2;
            int available = delta;
            double percent = 0.0d;
            int fillsCount = 0;
            for (int i = 0; i < this.sz; i++) {
                Node n = this.nodes.get(i);
                double cc = getConstraint(n);
                if (isPercent(cc)) {
                    percent += cc;
                    available += this.size[i];
                } else if (isFill(cc)) {
                    fillsCount++;
                    available += this.size[i];
                }
            }
            if (available < 0) {
                available = 0;
            }
            double percentFactor = percent > 1.0d ? 1.0d / percent : percent;
            int remaining = available;
            for (int i2 = 0; i2 < this.sz; i2++) {
                Node n2 = this.nodes.get(i2);
                double cc2 = getConstraint(n2);
                if (isPercent(cc2)) {
                    if (remaining > 0) {
                        w2 = cc2 * available * percentFactor;
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
                for (int i3 = 0; i3 < this.sz; i3++) {
                    Node n3 = this.nodes.get(i3);
                    if (isFill(getConstraint(n3))) {
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

        public void applySizes() {
            computePositions();
            int w = CKit.floor((VPane.this.getWidth() - this.left) - this.right);
            for (int i = 0; i < this.sz; i++) {
                Node n = this.nodes.get(i);
                int y = this.pos[i];
                int h = this.size[i];
                VPane.this.setBounds(n, this.left, y, w, h);
            }
        }

        public void layout() {
            this.size = new int[this.sz];
            int ph = computeSizes(true);
            int dh = CKit.floor(VPane.this.getHeight()) - ph;
            if (dh != 0) {
                adjust(dh);
            }
            applySizes();
        }
    }
}
