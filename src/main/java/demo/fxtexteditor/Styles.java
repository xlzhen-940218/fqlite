package demo.fxtexteditor;

import goryachev.fx.CommonStyles;
import goryachev.fx.FxStyleSheet;
import goryachev.fx.Theme;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/Styles.class */
public class Styles extends FxStyleSheet {
    public Styles() {
        Theme.current();
        add(new CommonStyles(), selector(StatusBar.LABEL).defines(padding(1, 1, 1, 5)));
    }
}
