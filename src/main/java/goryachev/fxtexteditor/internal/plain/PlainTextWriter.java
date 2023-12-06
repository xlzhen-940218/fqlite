package goryachev.fxtexteditor.internal.plain;

import goryachev.common.util.CKit;
import goryachev.fxtexteditor.ITextSource;
import java.io.StringWriter;
import java.io.Writer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/plain/PlainTextWriter.class */
public class PlainTextWriter {
    private final ITextSource src;
    private final Writer wr;

    public PlainTextWriter(ITextSource src, Writer wr) {
        this.src = src;
        this.wr = wr;
    }

    public static String writeString(ITextSource src) throws Exception {
        StringWriter out = new StringWriter();
        PlainTextWriter wr = new PlainTextWriter(src, out);
        wr.write();
        return out.toString();
    }

    public void write() throws Exception {
        boolean nl2 = false;
        while (true) {
            String t = this.src.nextPlainTextLine();
            if (t != null) {
                CKit.checkCancelled();
                if (nl2) {
                    this.wr.write(10);
                } else {
                    nl2 = true;
                }
                int start = this.src.getStart();
                int len = this.src.getEnd() - start;
                this.wr.write(t, start, len);
            } else {
                this.wr.flush();
                return;
            }
        }
    }
}
