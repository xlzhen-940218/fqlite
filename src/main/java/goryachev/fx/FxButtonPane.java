package goryachev.fx;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxButtonPane.class */
public class FxButtonPane extends HPane {
    public static final CssStyle PANE = new CssStyle("FxButtonPane_PANE");
    private static final int MIN_WIDTH = 70;

    public FxButtonPane() {
        super(5);
        FX.style(this, PANE);
    }

    public FxButton addButton(String text, FxAction a, CssStyle style) {
        FxButton b = new FxButton(text, a, style);
        return addButton(b);
    }

    public FxButton addButton(String text, FxAction a) {
        FxButton b = new FxButton(text, a);
        return addButton(b);
    }

    public FxButton addButton(String text, Runnable r, CssStyle style) {
        FxButton b = new FxButton(text, new FxAction(r), style);
        return addButton(b);
    }

    public FxButton addButton(String text, CssStyle style) {
        FxButton b = new FxButton(text, FxAction.DISABLED, style);
        return addButton(b);
    }

    public FxButton addButton(String text, Runnable r) {
        FxButton b = new FxButton(text, new FxAction(r));
        return addButton(b);
    }

    public FxButton addButton(String text) {
        FxButton b = new FxButton(text);
        b.setDisable(true);
        return addButton(b);
    }

    public FxButton addButton(FxButton b) {
        b.setMinWidth(70.0d);
        add(b);
        return b;
    }
}
