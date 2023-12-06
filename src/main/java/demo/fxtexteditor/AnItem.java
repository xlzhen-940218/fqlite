package demo.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/AnItem.class */
public class AnItem {
    private final String code;
    private final String displayText;

    public AnItem(String code, String displayText) {
        this.code = code;
        this.displayText = displayText;
    }

    public String toString() {
        return getDisplayText();
    }

    public String getCode() {
        return this.code;
    }

    public String getDisplayText() {
        return this.displayText;
    }
}
