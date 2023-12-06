package goryachev.fx;

import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/IconBase.class */
public class IconBase extends Region {
    public IconBase(double size) {
        this(size, size);
    }

    public IconBase(double width, double height) {
        setMinSize(width, height);
        setPrefSize(width, height);
        setMaxSize(width, height);
    }

    public void add(Node n) {
        getChildren().add(n);
    }

    public void addAll(Node... ns) {
        getChildren().addAll(ns);
    }

    public void addAll(Collection<Node> ns) {
        getChildren().addAll(ns);
    }
}
