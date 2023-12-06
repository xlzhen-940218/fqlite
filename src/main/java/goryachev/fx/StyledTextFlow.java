package goryachev.fx;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/StyledTextFlow.class */
public class StyledTextFlow extends TextFlow {
    public void append(CharSequence text) {
        Text t = new Text(text.toString());
        getChildren().add(t);
    }

    public void append(CssStyle style, CharSequence text) {
        Text t = new Text(text.toString());
        FX.style(t, style);
        getChildren().add(t);
    }
}
