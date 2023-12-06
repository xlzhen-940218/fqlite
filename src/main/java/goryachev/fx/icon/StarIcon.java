package goryachev.fx.icon;

import goryachev.common.util.CKit;
import goryachev.fx.FxPath;
import goryachev.fx.IconBase;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/icon/StarIcon.class */
public class StarIcon extends IconBase {
    public StarIcon(double size, Color fill) {
        super(size);
        double rm = size * 0.4d;
        double r0 = size * 0.15d;
        double w = size * 0.0325d;
        FxPath p = new FxPath();
        p.setStrokeLineCap(StrokeLineCap.ROUND);
        p.setStrokeLineJoin(StrokeLineJoin.ROUND);
        p.setStroke(Color.BLACK);
        p.setStrokeWidth(w);
        p.setFill(fill);
        for (int i = 0; i < 11; i++) {
            double a = (3.141592653589793d * i) / 5.0d;
            double r = CKit.isEven(i) ? rm : r0;
            double x = r * Math.cos(a);
            double y = r * Math.sin(a);
            switch (i) {
                case 0:
                    p.moveto(x, y);
                    break;
                case 10:
                    p.close();
                    break;
                default:
                    p.lineto(x, y);
                    break;
            }
        }
        Group g = new Group(p);
        g.setRotate(-90.0d);
        g.setTranslateX(size * 0.5d);
        g.setTranslateY(size * 0.5d);
        add(g);
    }
}
