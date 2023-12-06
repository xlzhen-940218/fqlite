package goryachev.fx.icon;

import goryachev.fx.FxPath;
import goryachev.fx.IconBase;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/icon/HamburgerIcon.class */
public class HamburgerIcon extends IconBase {
    public HamburgerIcon(double sz) {
        super(sz);
        double gapx = sz * 0.2d;
        double gapy = sz * 0.25d;
        double th = sz * 0.075d;
        double xm = sz - gapx;
        double y1 = sz / 2.0d;
        double ym = sz - gapy;
        FxPath p = new FxPath();
        p.setStroke(Color.BLACK);
        p.setStrokeWidth(th);
        p.setStrokeLineCap(StrokeLineCap.ROUND);
        p.moveto(gapx, gapy);
        p.lineto(xm, gapy);
        p.moveto(gapx, y1);
        p.lineto(xm, y1);
        p.moveto(gapx, ym);
        p.lineto(xm, ym);
        add(p);
    }
}
