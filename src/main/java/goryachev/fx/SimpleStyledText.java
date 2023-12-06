package goryachev.fx;

import javafx.scene.control.ButtonBar;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/SimpleStyledText.class */
public class SimpleStyledText implements IStyledText {
    private final String text;
    private final TextCellStyle style;

    public SimpleStyledText(String text, TextCellStyle style) {
        this.text = text;
        this.style = style;
    }

    public static SimpleStyledText of(String text, Color textColor) {
        if (text == null) {
            return null;
        }
        TextCellStyle s = new TextCellStyle(textColor);
        return new SimpleStyledText(text, s);
    }

    public static SimpleStyledText of(String text, Color textColor, final Color lineColor) {
        if (text == null) {
            text = ButtonBar.BUTTON_ORDER_NONE;
        }
        TextCellStyle s = new TextCellStyle(textColor);
        return new SimpleStyledText(text, s) { // from class: goryachev.fx.SimpleStyledText.1
            @Override // goryachev.fx.SimpleStyledText, goryachev.fx.IStyledText
            public Color getLineColor() {
                return lineColor;
            }
        };
    }

    @Override // goryachev.fx.IStyledText
    public String getPlainText() {
        return this.text;
    }

    @Override // goryachev.fx.IStyledText
    public int getTextLength() {
        return this.text.length();
    }

    @Override // goryachev.fx.IStyledText
    public TextCellStyle getCellStyle(int charOffset) {
        return this.style;
    }

    @Override // goryachev.fx.IStyledText
    public Color getLineColor() {
        return null;
    }

    @Override // goryachev.fx.IStyledText
    public char charAt(int charOffset) {
        return this.text.charAt(charOffset);
    }
}
