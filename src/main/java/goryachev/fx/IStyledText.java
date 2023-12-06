package goryachev.fx;

import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/IStyledText.class */
public interface IStyledText {
    String getPlainText();

    int getTextLength();

    TextCellStyle getCellStyle(int i);

    Color getLineColor();

    char charAt(int i);
}
