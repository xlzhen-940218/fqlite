package goryachev.fxtexteditor.internal.html;

import goryachev.common.util.CKit;
import goryachev.fx.FX;
import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.ITextSource;
import java.io.StringWriter;
import java.io.Writer;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/html/HtmlWriter.class */
public class HtmlWriter {
    private final ITextSource src;
    private final Writer out;
    private String fontName = "Courier New";

    public HtmlWriter(ITextSource src, Writer out) {
        this.src = src;
        this.out = out;
    }

    public static String writeString(ITextSource src) throws Exception {
        StringWriter out = new StringWriter();
        HtmlWriter wr = new HtmlWriter(src, out);
        wr.write();
        return out.toString();
    }

    public void setFont(String fontName) {
        this.fontName = fontName;
    }

    public void write() throws Exception {
        writeBeginning();
        boolean nl2 = false;
        while (true) {
            ITextLine t = this.src.nextLine();
            if (t != null) {
                CKit.checkCancelled();
                if (nl2) {
                    writeNL();
                } else {
                    nl2 = true;
                }
                int start = this.src.getStart();
                int end = this.src.getEnd();
                writeLine(t, start, end);
            } else {
                writeEnd();
                this.out.flush();
                return;
            }
        }
    }

    protected void writeBeginning() throws Exception {
        this.out.write("<pre><font face='");
        this.out.write(this.fontName);
        this.out.write("'>");
    }

    protected void writeLine(ITextLine t, int startPos, int endPos) throws Exception {
        Color col;
        Color bg;
        boolean bld;
        boolean ita;
        boolean und;
        boolean str;
        CKit.checkCancelled();
        if (t == null) {
            return;
        }
        TextCellStyle prevStyle = null;
        Color color = null;
        Color background = null;
        boolean bold = false;
        boolean italic = false;
        boolean under = false;
        boolean strike = false;
        String text = t.getPlainText();
        for (int i = startPos; i < endPos; i++) {
            TextCellStyle st = t.getCellStyle(i);
            if (prevStyle != st) {
                if (st == null) {
                    col = null;
                    bg = null;
                    bld = false;
                    ita = false;
                    und = false;
                    str = false;
                } else {
                    col = st.getTextColor();
                    bg = st.getBackgroundColor();
                    bld = st.isBold();
                    ita = st.isItalic();
                    und = st.isUnderscore();
                    str = st.isStrikeThrough();
                }
                prevStyle = st;
                if (CKit.notEquals(col, color)) {
                    if (col == null) {
                        this.out.write("</font>");
                    } else {
                        this.out.write("<font color='");
                        this.out.write(FX.toFormattedColorRGB(col));
                        this.out.write("'>");
                    }
                    color = col;
                }
                if (CKit.notEquals(bg, background)) {
                    if (bg == null) {
                        this.out.write("</span>");
                    } else {
                        this.out.write("</span><span style='background-color:");
                        this.out.write(FX.toFormattedColorRGB(bg));
                        this.out.write(";'>");
                    }
                    background = bg;
                }
                if (bld != bold) {
                    this.out.write(bld ? "<b>" : "</b>");
                    bold = bld;
                }
                if (ita != italic) {
                    this.out.write(ita ? "<i>" : "</i>");
                    italic = ita;
                }
                if (und != under) {
                    this.out.write(und ? "<u>" : "</u>");
                    under = und;
                }
                if (str != strike) {
                    this.out.write(str ? "<strike>" : "</strike>");
                    strike = str;
                }
            }
            char ch = text.charAt(i);
            if (ch < ' ') {
                switch (ch) {
                    case '\t':
                        this.out.write(ch);
                        continue;
                }
            } else {
                switch (ch) {
                    case '&':
                        this.out.write("&amp;");
                        continue;
                    case '<':
                        this.out.write("&lt;");
                        continue;
                    default:
                        this.out.write(ch);
                        continue;
                }
            }
        }
        if (strike) {
            this.out.write("</strike>");
        }
        if (under) {
            this.out.write("</u>");
        }
        if (italic) {
            this.out.write("</i>");
        }
        if (bold) {
            this.out.write("</b>");
        }
        if (background != null) {
            this.out.write("</span>");
        }
        if (color != null) {
            this.out.write("</font>");
        }
    }

    protected void writeNL() throws Exception {
        this.out.write("\n");
    }

    protected void writeEnd() throws Exception {
        this.out.write("</font></pre>\n");
    }
}
