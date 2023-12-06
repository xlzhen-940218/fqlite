package goryachev.fx.util;

import goryachev.common.util.CList;
import java.util.List;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/util/FxPathBuilder.class */
public class FxPathBuilder {
    private final CList<PathElement> path = new CList<>();

    public void moveto(double x, double y) {
        add(new MoveTo(x, y));
    }

    public void lineto(double x, double y) {
        add(new LineTo(x, y));
    }

    public List<PathElement> getPath() {
        return this.path;
    }

    public void add(PathElement em) {
        this.path.add(em);
    }

    public void addAll(PathElement[] elements) {
        this.path.addAll(elements);
    }
}
