package goryachev.fxtexteditor.internal;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.Dump;
import goryachev.common.util.SB;
import goryachev.fxtexteditor.ITabPolicy;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.VFlow;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/ScreenBuffer.class */
public class ScreenBuffer {
    protected static final Log log = Log.get("ScreenBuffer");
    protected final VFlow vflow;
    private int height;
    private int width;
    private ScreenRow[] rows;

    public ScreenBuffer(VFlow vf) {
        this.vflow = vf;
    }

    public void setSize(int w, int h) {
        if (h > this.height) {
            this.rows = new ScreenRow[h];
            for (int i = 0; i < h; i++) {
                this.rows[i] = new ScreenRow();
            }
        }
        this.width = w;
        this.height = h;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public ScreenRow getRow(int ix) {
        return this.rows[ix];
    }

    public ScreenRow getScreenRow(int y) {
        if (y >= 0 && y < this.height) {
            return this.rows[y];
        }
        return null;
    }

    public int getMaxCellCount(ITabPolicy tabPolicy) {
        int w = 0;
        for (int i = 0; i < this.height; i++) {
            int len = getRow(i).getGlyphCount();
            if (len > w) {
                w = len;
            }
        }
        if (w > 0) {
            w++;
        }
        return w;
    }

    public String dump() {
        SB sb = new SB();
        for (int i = 0; i < getHeight(); i++) {
            ScreenRow r = this.rows[i];
            ITextLine tline = r.getTextLine();
            String text = tline == null ? ButtonBar.BUTTON_ORDER_NONE : Dump.toPrintable(CKit.trim(tline.getPlainText(), 80));
            sb.format("%02d %s %s\n", Integer.valueOf(i), r, text);
        }
        return sb.toString();
    }

    public void reset() {
        this.height = 0;
        this.width = 0;
    }
}
