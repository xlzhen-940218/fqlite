package goryachev.fx.icon;

import goryachev.fx.FxPath;
import goryachev.fx.IconBase;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/icon/CloseIcon.class */
public class CloseIcon extends IconBase {
    public CloseIcon(double size) {
        super(size);
        double d = 0.4d * size;
        double w = 0.075d * size;
        double d2 = 0.3d * size;
        FxPath p = new FxPath();
        p.setStrokeLineCap(StrokeLineCap.ROUND);
        p.setStroke(Color.BLACK);
        p.setStrokeWidth(w);
        p.moveto(-d2, -d2);
        p.lineto(d2, d2);
        p.moveto(d2, -d2);
        p.lineto(-d2, d2);
        Group g = new Group(p);
        g.setTranslateX(size * 0.5d);
        g.setTranslateY(size * 0.5d);
        add(g);
    }
}
