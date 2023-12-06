package goryachev.fx;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FlowBox.class */
public class FlowBox extends Pane {
    protected int vgap;
    protected int hgap;

    public void setVGap(int x) {
        this.vgap = x;
    }

    public void setHGap(int x) {
        this.hgap = x;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefHeight(double forWidth) {
        return new Helper().computePrefHeight(forWidth);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefWidth(double forHeight) {
        return -1.0d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.Parent
    public void layoutChildren() {
        new Helper().layoutChildren();
    }

    protected void layoutInArea(Node n, double x, double y, double w, double h) {
        layoutInArea(n, x, y, w, h, 0.0d, null, HPos.CENTER, VPos.CENTER);
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FlowBox$Helper.class */
    protected class Helper {
        protected final List<Node> children;
        protected final int sz;
        protected final double top;
        protected final double bottom;
        protected final double left;
        protected final double right;
        protected final double lineHeight;
        protected final double[] widths;

        public Helper() {
            Insets m = FlowBox.this.getInsets();
            this.top = m.getTop();
            this.bottom = m.getBottom();
            this.left = m.getLeft();
            this.right = m.getRight();
            double lh = 0.0d;
            this.children = FlowBox.this.getChildren();
            this.sz = this.children.size();
            this.widths = new double[this.sz];
            for (int i = 0; i < this.sz; i++) {
                Node ch = this.children.get(i);
                if (ch.isManaged()) {
                    double w = ch.prefWidth(-1.0d);
                    this.widths[i] = w;
                    double h = ch.prefHeight(-1.0d);
                    if (h > lh) {
                        lh = h;
                    }
                }
            }
            this.lineHeight = lh;
        }

        public double computePrefHeight(double forWidth) {
            if (forWidth < 0.0d) {
                return -1.0d;
            }
            double max = forWidth - this.right;
            double x = this.left;
            double y = this.top;
            boolean addRow = false;
            for (int i = 0; i < this.sz; i++) {
                Node ch = this.children.get(i);
                if (ch.isManaged()) {
                    addRow = true;
                    double w = this.widths[i];
                    if (x + w >= max) {
                        x = this.left;
                        y += FlowBox.this.vgap + this.lineHeight;
                    }
                    x += FlowBox.this.hgap + w;
                }
            }
            if (addRow) {
                y += this.lineHeight;
            }
            return y + this.bottom;
        }

        public void layoutChildren() {
            double x = this.left;
            double y = this.top;
            double max = FlowBox.this.getWidth() - this.right;
            for (int i = 0; i < this.sz; i++) {
                Node ch = this.children.get(i);
                if (ch.isManaged()) {
                    double w = this.widths[i];
                    if (x + w >= max) {
                        x = this.left;
                        y += FlowBox.this.vgap + this.lineHeight;
                    }
                    FlowBox.this.layoutInArea(ch, x, y, w, this.lineHeight);
                    x += FlowBox.this.hgap + w;
                }
            }
        }
    }
}
