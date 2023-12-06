package goryachev.fx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDialog.class */
public class FxDialog extends FxWindow {
    public final FxAction closeDialogAction;
    public static final CssStyle PANE = new CssStyle("FxDialog_PANE");

    public FxDialog(Object owner, String name) {
        super(name);
        this.closeDialogAction = new FxAction(this::close);
        initModality(Modality.APPLICATION_MODAL);
        FX.style(this.pane, PANE);
        Window win = FX.getParentWindow(owner);
        initOwner(win);
        if (win != null) {
            win.getX();
            win.getY();
            win.getWidth();
            win.getHeight();
        }
    }

    public FxButtonPane buttonPane() {
        Node n = getBottom();
        if (n instanceof FxButtonPane) {
            return (FxButtonPane) n;
        }
        FxButtonPane p = new FxButtonPane();
        setBottom(p);
        return p;
    }

    @Override // goryachev.fx.FxWindow
    public void open() {
        double w = getWidth();
        double h = getHeight();
        if (isInvalid(w)) {
            setWidth(400.0d);
        }
        if (isInvalid(h)) {
            setHeight(300.0d);
        }
        super.open();
    }

    protected static boolean isInvalid(double x) {
        if (Double.isNaN(x) || x <= 1.0d) {
            return true;
        }
        return false;
    }

    public void closeOnEscape() {
        KeyMap.onKeyPressed(this.pane, KeyCode.ESCAPE, this.closeDialogAction);
    }
}
