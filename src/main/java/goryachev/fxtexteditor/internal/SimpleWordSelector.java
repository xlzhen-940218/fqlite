package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.Marker;
import java.util.function.BiConsumer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/SimpleWordSelector.class */
public class SimpleWordSelector implements BiConsumer<FxTextEditor, Marker> {
    protected boolean isWordChar(int c) {
        return Character.isLetterOrDigit(c);
    }

    protected int skipWordCharsForward(String text, int start) {
        int len = text.length();
        for (int i = start; i < len; i++) {
            char c = text.charAt(i);
            if (!isWordChar(c)) {
                return i;
            }
        }
        return len;
    }

    protected int skipNonWordCharsForward(String text, int start) {
        int len = text.length();
        for (int i = start; i < len; i++) {
            char c = text.charAt(i);
            if (isWordChar(c)) {
                return i;
            }
        }
        return len;
    }

    protected int skipWordCharsBackward(String text, int start) {
        for (int i = start; i >= 0; i--) {
            char c = text.charAt(i);
            if (!isWordChar(c)) {
                return i;
            }
        }
        return -1;
    }

    protected int skipNonWordCharsBackward(String text, int start) {
        if (start < text.length()) {
            for (int i = start; i >= 0; i--) {
                char c = text.charAt(i);
                if (isWordChar(c)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // java.util.function.BiConsumer
    public void accept(FxTextEditor ed, Marker m) {
        int pos;
        int line = m.getLine();
        String text = ed.getPlainText(line);
        if (text == null) {
            return;
        }
        int len = ed.getTextLength(line);
        if (len != 0 && (pos = m.getCharIndex()) < text.length() && isWordChar(text.charAt(pos))) {
            int start = skipWordCharsBackward(text, pos) + 1;
            int end = skipWordCharsForward(text, pos);
            ed.select(line, start, line, end);
        }
    }
}
