package goryachev.fxtexteditor;

import goryachev.fx.TextCellStyle;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/ITextLine.class */
public interface ITextLine {
    int getLineNumber();

    int getModelIndex();

    String getPlainText();

    int getTextLength();

    TextCellStyle getCellStyle(int i);

    Color getLineColor();
}
