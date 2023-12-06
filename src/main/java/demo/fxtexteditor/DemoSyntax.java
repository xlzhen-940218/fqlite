package demo.fxtexteditor;

import goryachev.common.util.CList;
import goryachev.fx.TextCellStyle;
import java.util.List;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/DemoSyntax.class */
public class DemoSyntax {
    private static final TextCellStyle STYLE_TEXT = new TextCellStyle();
    private static final TextCellStyle STYLE_ENCLOSED = new TextCellStyle(null, Color.YELLOW, false, false, false, false);
    private static final TextCellStyle STYLE_NUMBER = new TextCellStyle(Color.RED, null, true, false, false, false);
    private static final TextCellStyle STYLE_RTF = new TextCellStyle(Color.OLIVEDRAB, null, true, false, false, false);
    private final String text;
    private int start;
    private final CList<TSegment> segments = new CList<>();
    private TextCellStyle style = STYLE_TEXT;

    public DemoSyntax(String text) {
        this.text = text;
    }

    public List<TSegment> generateSegments() {
        int ix;
        int i = 0;
        while (i < this.text.length()) {
            char c = this.text.charAt(i);
            int close = getClosingChar(c);
            if (close >= 0 && (ix = this.text.indexOf(close, i)) >= 0) {
                addSegment(i);
                this.style = STYLE_ENCLOSED;
                i = ix;
                addSegment(i);
            } else {
                TextCellStyle st = getCellStyle(c);
                if (!st.equals(this.style)) {
                    addSegment(i);
                    this.style = st;
                }
            }
            i++;
        }
        addSegment(this.text.length());
        return this.segments;
    }

    protected int getClosingChar(char c) {
        switch (c) {
            case '(':
                return 41;
            case '[':
                return 93;
            case '{':
                return 125;
            default:
                return -1;
        }
    }

    protected void addSegment(int end) {
        if (end > this.start) {
            this.segments.add(new TSegment(this.text, this.start, end, this.style));
            this.start = end;
        }
    }

    protected TextCellStyle getCellStyle(char c) {
        if (Character.isDigit(c)) {
            return STYLE_NUMBER;
        }
        byte dir = Character.getDirectionality(c);
        switch (dir) {
            case 1:
            case 2:
            case 6:
            case 16:
            case 17:
                return STYLE_RTF;
            default:
                return STYLE_TEXT;
        }
    }
}
