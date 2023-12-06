package goryachev.fx;

import java.util.Collection;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxPath.class */
public class FxPath extends Path {
    public FxPath() {
    }

    public FxPath(Collection<? extends PathElement> elements) {
        super(elements);
    }

    public FxPath(PathElement... elements) {
        super(elements);
    }

    public void moveto(double x, double y) {
        add(new MoveTo(x, y));
    }

    public void lineto(double x, double y) {
        add(new LineTo(x, y));
    }

    public void close() {
        add(new ClosePath());
    }

    public void add(PathElement em) {
        getElements().add(em);
    }

    public void addAll(PathElement... elements) {
        getElements().addAll(elements);
    }

    public void addAll(Collection<? extends PathElement> elements) {
        getElements().addAll(elements);
    }
}
