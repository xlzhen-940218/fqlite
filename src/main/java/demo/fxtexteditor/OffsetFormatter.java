package demo.fxtexteditor;

import goryachev.fxtexteditor.ALineNumberFormatter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/OffsetFormatter.class */
public class OffsetFormatter extends ALineNumberFormatter {
    @Override // goryachev.fxtexteditor.ALineNumberFormatter
    public String formatLineNumber(int lineNumber) {
        int offset = (lineNumber - 1) * 16;
        return Integer.toHexString(offset);
    }
}
