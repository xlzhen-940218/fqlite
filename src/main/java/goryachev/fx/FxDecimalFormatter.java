package goryachev.fx;

import java.text.DecimalFormat;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxDecimalFormatter.class */
public class FxDecimalFormatter extends FxFormatter {
    private final DecimalFormat format;

    public FxDecimalFormatter(String pattern) {
        this.format = new DecimalFormat(pattern);
    }

    @Override // goryachev.fx.FxFormatter, javafx.util.StringConverter
    public String toString(Object x) {
        if (x != null && (x instanceof Number)) {
            return this.format.format(x);
        }
        return null;
    }
}
