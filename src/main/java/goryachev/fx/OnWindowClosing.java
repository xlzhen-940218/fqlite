package goryachev.fx;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/OnWindowClosing.class */
public class OnWindowClosing {
    private final boolean multiple;
    private Object choice;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/OnWindowClosing$Choice.class */
    public enum Choice {
        CANCELLED,
        DISCARD_ALL,
        SAVE_ALL;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Choice[] valuesCustom() {
            Choice[] valuesCustom = values();
            int length = valuesCustom.length;
            Choice[] choiceArr = new Choice[length];
            System.arraycopy(valuesCustom, 0, choiceArr, 0, length);
            return choiceArr;
        }
    }

    public OnWindowClosing(boolean closingMultipleWindows) {
        this.multiple = closingMultipleWindows;
    }

    public boolean isClosingMultipleWindows() {
        return this.multiple;
    }

    public void setChoice(Object choice) {
        this.choice = choice;
    }

    public Object getChoice() {
        return this.choice;
    }

    public void setSaveAll() {
        setChoice(Choice.SAVE_ALL);
    }

    public void setDiscardAll() {
        setChoice(Choice.DISCARD_ALL);
    }

    public void setCancelled() {
        setChoice(Choice.CANCELLED);
    }

    public boolean isSaveAll() {
        return this.choice == Choice.SAVE_ALL;
    }

    public boolean isDiscardAll() {
        return this.choice == Choice.DISCARD_ALL;
    }

    public boolean isCancelled() {
        return this.choice == Choice.CANCELLED;
    }
}
