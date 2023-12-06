package goryachev.fx;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/Formatters.class */
public class Formatters {
    private static FxDecimalFormatter integerFormatter;

    public static FxDecimalFormatter integerFormatter() {
        if (integerFormatter == null) {
            integerFormatter = new FxDecimalFormatter("#,##0");
        }
        return integerFormatter;
    }
}
