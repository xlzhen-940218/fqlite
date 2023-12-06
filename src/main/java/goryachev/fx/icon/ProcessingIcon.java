package goryachev.fx.icon;

import goryachev.fx.FxIconBuilder;
import goryachev.fx.IconBase;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/icon/ProcessingIcon.class */
public class ProcessingIcon {
    private ProcessingIcon() {
    }

    public static IconBase create(double size) {
        double sz2 = size / 2.0d;
        FxIconBuilder b = new FxIconBuilder(size, sz2, sz2);
        double r = 0.8d * sz2;
        double w = 0.15d * sz2;
        b.setFill(null);
        b.setStrokeWidth(w);
        b.setStrokeLineCap(StrokeLineCap.ROUND);
        b.setStrokeColor(Color.BLACK);
        b.newPath();
        b.moveTo(r * Math.cos(0.7853981633974483d), (-r) * Math.sin(0.7853981633974483d));
        b.arcRel(0.0d, 0.0d, r, -((3.141592653589793d - 0.7853981633974483d) - 0.7853981633974483d));
        b.moveTo(r * Math.cos(0.7853981633974483d), r * Math.sin(0.7853981633974483d));
        b.arcRel(0.0d, 0.0d, r, (3.141592653589793d - 0.7853981633974483d) - 0.7853981633974483d);
        IconBase ic = b.getIcon();
        RotateTransition t = new RotateTransition(Duration.millis(750.0d), ic);
        t.setByAngle(360.0d);
        t.setCycleCount(-1);
        t.setInterpolator(Interpolator.LINEAR);
        t.play();
        return ic;
    }
}
