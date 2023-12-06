package goryachev.fx;

import goryachev.common.util.CList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxIconBuilder.class */
public class FxIconBuilder {
    private final double width;
    private final double height;
    private final CList<Node> elements;
    private double xorigin;
    private double yorigin;
    private double scale;
    private double rotate;
    private double opacity;
    private double xtranslate;
    private double ytranslate;
    private double strokeWidth;
    private Paint fill;
    private Paint strokeColor;
    private StrokeType strokeType;
    private StrokeLineCap lineCap;
    private StrokeLineJoin lineJoin;
    private double miterLimit;
    private double dashOffset;
    private FillRule fillRule;
    private Effect effect;
    private Path path;

    public FxIconBuilder(double width, double height, double xcenter, double ycenter) {
        this(width, height);
        setOrigin(xcenter, ycenter);
    }

    public FxIconBuilder(double size, double xcenter, double ycenter) {
        this(size);
        setOrigin(xcenter, ycenter);
    }

    public FxIconBuilder(double width, double height) {
        this.scale = 1.0d;
        this.opacity = 1.0d;
        this.strokeWidth = 1.0d;
        this.fill = Color.BLACK;
        this.strokeColor = Color.BLACK;
        this.strokeType = StrokeType.CENTERED;
        this.lineCap = StrokeLineCap.ROUND;
        this.lineJoin = StrokeLineJoin.ROUND;
        this.width = width;
        this.height = height;
        this.elements = new CList<>();
    }

    public FxIconBuilder(double size) {
        this(size, size);
    }

    public void setOrigin(double xcenter, double ycenter) {
        this.xorigin = xcenter;
        this.yorigin = ycenter;
    }

    public void setFill(Paint c) {
        this.fill = c;
    }

    public void fill() {
        fill(-this.xorigin, -this.yorigin, this.width, this.height);
    }

    public void fill(double x, double y, double w, double h) {
        Region r = new Region();
        r.setManaged(false);
        r.resizeRelocate(x + this.xorigin, y + this.yorigin, w, h);
        r.setBackground(FX.background(this.fill));
        this.elements.add(r);
    }

    public void circle(double x, double y, double radius) {
        Circle c = new Circle(x, y, radius);
        applyShapeProperties(c);
        this.elements.add(c);
    }

    protected Path createPath() {
        Path p = new Path();
        applyNodeProperties(p);
        applyShapeProperties(p);
        p.setFillRule(this.fillRule);
        return p;
    }

    protected void applyNodeProperties(Node n) {
        n.setOpacity(this.opacity);
        n.setScaleX(this.scale);
        n.setScaleY(this.scale);
        n.setTranslateX(this.xtranslate);
        n.setTranslateY(this.ytranslate);
        n.setEffect(this.effect);
        n.setRotate(this.rotate);
    }

    protected void applyShapeProperties(Shape p) {
        p.setFill(this.fill);
        p.setStroke(this.strokeColor);
        p.setStrokeDashOffset(this.dashOffset);
        p.setStrokeLineCap(this.lineCap);
        p.setStrokeLineJoin(this.lineJoin);
        p.setStrokeMiterLimit(this.miterLimit);
        p.setStrokeType(this.strokeType);
        p.setStrokeWidth(this.strokeWidth);
    }

    protected SVGPath createSVGPath() {
        SVGPath p = new SVGPath();
        applyNodeProperties(p);
        applyShapeProperties(p);
        p.setFillRule(this.fillRule);
        return p;
    }

    public Path newPath() {
        this.path = createPath();
        this.elements.add(this.path);
        return this.path;
    }

    public SVGPath svgPath(String svg) {
        return svgPath(svg, true);
    }

    public SVGPath svgPath(String svg, boolean autoScale) {
        SVGPath p = createSVGPath();
        p.setContent(svg);
        this.elements.add(p);
        if (autoScale) {
            autoFitLastElement();
        }
        return p;
    }

    public void image(byte[] bytes) {
        ImageView v = new ImageView();
        v.setImage(new Image((InputStream) new ByteArrayInputStream(bytes)));
        applyNodeProperties(v);
        v.setTranslateX(this.xtranslate + this.xorigin);
        v.setTranslateY(this.ytranslate + this.yorigin);
        this.elements.add(v);
    }

    public void setScale(double x) {
        this.scale = x;
    }

    public void setOpacity(double x) {
        this.opacity = x;
    }

    public void setTranslate(double dx, double dy) {
        this.xtranslate = dx;
        this.ytranslate = dy;
    }

    public void setRotate(double angleInRadians) {
        this.rotate = FX.toDegrees(angleInRadians);
    }

    public void setRotateDegrees(double angleInDegrees) {
        this.rotate = angleInDegrees;
    }

    public void setStrokeWidth(double w) {
        this.strokeWidth = w;
    }

    public void setStrokeColor(Paint x) {
        this.strokeColor = x;
    }

    public void setStrokeLineCap(StrokeLineCap x) {
        this.lineCap = x;
    }

    public void setStrokeLineJoin(StrokeLineJoin x) {
        this.lineJoin = x;
    }

    public void setStrokeMiterLimit(double x) {
        this.miterLimit = x;
    }

    public void setEffect(Effect x) {
        this.effect = x;
    }

    public void addEffect(Effect x) {
        if (this.effect == null) {
            this.effect = x;
        } else {
            this.effect = setInputEffect(this.effect, x);
        }
    }

    protected Effect setInputEffect(Effect a, Effect b) {
        if (b instanceof GaussianBlur) {
            ((GaussianBlur) b).setInput(a);
        } else if (b instanceof ColorAdjust) {
            ((ColorAdjust) b).setInput(a);
        } else {
            throw new Error("todo: does " + b + " have setInput()?");
        }
        return b;
    }

    protected void add(PathElement em) {
        if (this.path == null) {
            this.path = newPath();
        }
        this.path.getElements().add(em);
    }

    protected Point2D currentPos() {
        if (this.path == null) {
            return new Point2D(this.xorigin, this.yorigin);
        }
        ObservableList<PathElement> es = this.path.getElements();
        int sz = es.size();
        if (sz == 0) {
            return new Point2D(this.xorigin, this.yorigin);
        }
        PathElement em = es.get(sz - 1);
        if (em instanceof LineTo) {
            LineTo p = (LineTo) em;
            return new Point2D(p.getX(), p.getY());
        } else if (em instanceof MoveTo) {
            MoveTo p2 = (MoveTo) em;
            return new Point2D(p2.getX(), p2.getY());
        } else if (em instanceof ArcTo) {
            ArcTo p3 = (ArcTo) em;
            return new Point2D(p3.getX(), p3.getY());
        } else if (em instanceof CubicCurveTo) {
            CubicCurveTo p4 = (CubicCurveTo) em;
            return new Point2D(p4.getX(), p4.getY());
        } else if (em instanceof QuadCurveTo) {
            QuadCurveTo p5 = (QuadCurveTo) em;
            return new Point2D(p5.getX(), p5.getY());
        } else {
            throw new Error("?" + em);
        }
    }

    public void moveTo(double x, double y) {
        add(new MoveTo(x + this.xorigin, y + this.yorigin));
    }

    public void moveRel(double dx, double dy) {
        Point2D p = currentPos();
        add(new MoveTo(dx + p.getX(), dy + p.getY()));
    }

    public void lineTo(double x, double y) {
        add(new LineTo(x + this.xorigin, y + this.yorigin));
    }

    public void lineRel(double dx, double dy) {
        Point2D p = currentPos();
        add(new LineTo(dx + p.getX(), dy + p.getY()));
    }

    public void arcRel(double xc, double yc, double radius, double angle) {
        if (angle >= 6.283185307179586d) {
            angle = 6.283185207179586d;
        } else if (angle <= -6.283185307179586d) {
            angle = -6.283185207179586d;
        }
        Point2D p = currentPos();
        double a = Math.atan2((yc + this.yorigin) - p.getY(), (p.getX() - xc) - this.xorigin);
        double b = a - angle;
        double xe = this.xorigin + xc + (radius * Math.cos(b));
        double ye = (this.yorigin - yc) - (radius * Math.sin(b));
        boolean large = angle >= 3.141592653589793d;
        boolean sweep = angle > 0.0d;
        add(new ArcTo(radius, radius, 0.0d, xe, ye, large, sweep));
    }

    public Node last() {
        return this.elements.get(this.elements.size() - 1);
    }

    public void autoFitLastElement() {
        Node n = last();
        double w = n.prefHeight(this.width);
        double h = n.prefWidth(this.height);
        double sx = this.width / w;
        double sy = this.height / h;
        double sc = Math.min(sx, sy);
        n.setScaleX(sc);
        n.setScaleY(sc);
        Bounds b = n.getBoundsInLocal();
        double dx = ((this.width / 2.0d) - b.getMinX()) - (b.getWidth() / 2.0d);
        double dy = ((this.height / 2.0d) - b.getMinY()) - (b.getHeight() / 2.0d);
        n.setTranslateX(dx);
        n.setTranslateY(dy);
    }

    public IconBase getIcon() {
        IconBase ic = new IconBase(this.width, this.height);
        ic.addAll(this.elements);
        return ic;
    }

    public Pane getIconBox() {
        HBox b = new HBox(getIcon());
        b.setAlignment(Pos.CENTER);
        return b;
    }
}
