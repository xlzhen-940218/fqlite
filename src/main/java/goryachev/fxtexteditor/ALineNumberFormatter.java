package goryachev.fxtexteditor;

import goryachev.fx.Formatters;
import goryachev.fx.FxFormatter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/ALineNumberFormatter.class */
public abstract class ALineNumberFormatter {
    public abstract String formatLineNumber(int i);

    public int getColumnCount() {
        return 1;
    }

    public String[] formatMultiColumn(int lineNumber) {
        throw new Error();
    }

    public boolean isRightAlignmentForColumn(int column) {
        return true;
    }

    public static ALineNumberFormatter getDefault() {
        return new ALineNumberFormatter() { // from class: goryachev.fxtexteditor.ALineNumberFormatter.1
            FxFormatter f = Formatters.integerFormatter();

            @Override // goryachev.fxtexteditor.ALineNumberFormatter
            public String formatLineNumber(int lineNumber) {
                return this.f.format(Integer.valueOf(lineNumber));
            }
        };
    }
}
