package goryachev.fx;

import goryachev.fx.internal.BaseFxWindow;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxWindow.class */
public class FxWindow extends BaseFxWindow {
    private final String name;
    public final FxAction closeWindowAction = new FxAction(this::closeWithConfirmation);
    protected final BorderPane pane = new BorderPane();

    public void confirmClosing(OnWindowClosing choice) {
    }

    public boolean isEssentialWindow() {
        return true;
    }

    public FxWindow(String name) {
        this.name = name;
        Scene sc = new Scene(this.pane);
        setScene(sc);
    }

    public String getName() {
        return this.name;
    }

    public void open() {
        FX.open(this);
    }

    public void setTop(Node n) {
        this.pane.setTop(n);
    }

    public Node getTop() {
        return this.pane.getTop();
    }

    public void setBottom(Node n) {
        this.pane.setBottom(n);
    }

    public Node getBottom() {
        return this.pane.getBottom();
    }

    public void setLeft(Node n) {
        this.pane.setLeft(n);
    }

    public Node getLeft() {
        return this.pane.getLeft();
    }

    public void setRight(Node n) {
        this.pane.setRight(n);
    }

    public Node getRight() {
        return this.pane.getRight();
    }

    public void setCenter(Node n) {
        this.pane.setCenter(n);
    }

    public Node getCenter() {
        return this.pane.getCenter();
    }

    public Parent getContentPane() {
        return getScene().getRoot();
    }

    public void closeWithConfirmation() {
        OnWindowClosing ch = new OnWindowClosing(false);
        confirmClosing(ch);
        if (!ch.isCancelled()) {
            close();
        }
    }
}
