package goryachev.fx.icon;

import goryachev.fx.FxPath;
import goryachev.fx.IconBase;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/icon/FindIcon.class */
public class FindIcon extends IconBase {
    public FindIcon(double size) {
        super(size);
        double r = 0.3d * size;
        double w = 0.075d * size;
        double gap = 0.12d * size;
        double handle = 0.15d * size;
        Circle c = new Circle(0.0d, 0.0d, r);
        c.setFill(null);
        c.setStrokeWidth(w);
        c.setStroke(Color.BLACK);
        c.setFill(null);
        FxPath p = new FxPath();
        p.setStrokeLineCap(StrokeLineCap.SQUARE);
        p.setStroke(Color.BLACK);
        p.setStrokeWidth(w);
        p.moveto(r, 0.0d);
        p.lineto(r + gap, 0.0d);
        FxPath h = new FxPath();
        h.setStrokeLineCap(StrokeLineCap.ROUND);
        h.setStroke(Color.BLACK);
        h.setStrokeWidth(w * 2.0d);
        h.moveto(r + gap, 0.0d);
        h.lineto(r + gap + handle, 0.0d);
        Group g = new Group(c, p, h);
        g.setRotate(135.0d);
        g.setTranslateX(size * 0.3d);
        g.setTranslateY(size * 0.54d);
        add(g);
    }
}
