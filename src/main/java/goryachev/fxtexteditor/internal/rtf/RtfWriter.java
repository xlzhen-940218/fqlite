package goryachev.fxtexteditor.internal.rtf;

import fqlite.base.Global;
import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.fx.FX;
import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.ITextLine;
import goryachev.fxtexteditor.ITextSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.function.Supplier;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/rtf/RtfWriter.class */
public class RtfWriter {
    protected static final Log log = Log.get("RtfWriter");
    private final Supplier<ITextSource> source;
    private final OutputStream out;
    private String fontName = "Courier New";
    private String fontSize = "18";
    private ColorTable colorTable;

    public RtfWriter(Supplier<ITextSource> source, OutputStream out) {
        this.source = source;
        this.out = out;
    }

    public static String writeString(Supplier<ITextSource> src) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        RtfWriter wr = new RtfWriter(src, out);
        wr.write();
        byte[] b = out.toByteArray();
        return new String(b, CKit.CHARSET_ASCII);
    }

    public void setFont(String fontName, int fontSize) {
        this.fontName = fontName;
        this.fontSize = String.valueOf(2 * fontSize);
    }

    public void write() throws Exception {
        ITextSource src = this.source.get();
        this.colorTable = new ColorTable();
        while (true) {
            ITextLine t = src.nextLine();
            if (t == null) {
                break;
            }
            CKit.checkCancelled();
            int start = src.getStart();
            int end = src.getEnd();
            collectColors(t, start, end);
        }
        ITextSource src2 = this.source.get();
        writeBeginning();
        boolean nl2 = false;
        while (true) {
            ITextLine t2 = src2.nextLine();
            if (t2 != null) {
                CKit.checkCancelled();
                if (nl2) {
                    writeNL();
                } else {
                    nl2 = true;
                }
                int start2 = src2.getStart();
                int end2 = src2.getEnd();
                writeLine(t2, start2, end2);
            } else {
                writeEnd();
                this.out.flush();
                return;
            }
        }
    }

    protected void collectColors(ITextLine t, int start, int end) {
        CKit.checkCancelled();
        TextCellStyle prevStyle = null;
        for (int i = start; i < end; i++) {
            TextCellStyle st = t.getCellStyle(i);
            if (prevStyle != st) {
                if (st != null) {
                    Color c = st.getTextColor();
                    if (c != null) {
                        this.colorTable.add(c);
                    }
                    Color c2 = mixBackground(st.getBackgroundColor());
                    if (c2 != null) {
                        this.colorTable.add(c2);
                    }
                }
                prevStyle = st;
            }
        }
    }

    protected Color mixBackground(Color c) {
        return FX.mix(c, Color.WHITE, 0.85d);
    }

    protected void writeBeginning() throws Exception {
        write("{\\rtf1\\ansi\\ansicpg1252\\uc1\\sl0\\sb0\\sa0\\deff0{\\fonttbl{\\f0\\fnil ");
        write(this.fontName);
        write(";}}\r\n");
        write("{\\colortbl ;");
        for (Color c : this.colorTable.getColors()) {
            write("\\red");
            write(toInt255(c.getRed()));
            write("\\green");
            write(toInt255(c.getGreen()));
            write("\\blue");
            write(toInt255(c.getBlue()));
            write(";");
        }
        write("}\r\n");
        write("{\\f0\\fs");
        write(this.fontSize);
        write(" \\fi0\\ql ");
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
        write("\\fi0\\ql ");
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
                    bg = mixBackground(st.getBackgroundColor());
                    bld = st.isBold();
                    ita = st.isItalic();
                    und = st.isUnderscore();
                    str = st.isStrikeThrough();
                }
                prevStyle = st;
                if (CKit.notEquals(col, color)) {
                    if (col == null) {
                        write("\\cf0 ");
                    } else {
                        String s = this.colorTable.getIndexFor(col);
                        if (s == null) {
                            s = "0";
                            log.warn("no entry for " + col);
                        }
                        write("\\cf");
                        write(s);
                        write(Global.REGULAR_RECORD);
                    }
                    color = col;
                }
                if (CKit.notEquals(bg, background)) {
                    if (bg == null) {
                        write("\\highlight0 ");
                    } else {
                        String s2 = this.colorTable.getIndexFor(bg);
                        write("\\highlight");
                        write(s2);
                        write(Global.REGULAR_RECORD);
                    }
                    background = bg;
                }
                if (bld != bold) {
                    write(bld ? "\\b " : "\\b0 ");
                    bold = bld;
                }
                if (ita != italic) {
                    write(ita ? "\\i " : "\\i0 ");
                    italic = ita;
                }
                if (und != under) {
                    write(und ? "\\ul " : "\\ul0 ");
                    under = und;
                }
                if (str != strike) {
                    write(str ? "\\strike " : "\\strike0 ");
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
            } else if (ch < 128) {
                switch (ch) {
                    case '\\':
                        write("\\\\");
                        continue;
                    case '{':
                        write("\\{");
                        continue;
                    case '}':
                        write("\\}");
                        continue;
                    default:
                        this.out.write(ch);
                        continue;
                }
            } else {
                write("\\u");
                write(String.valueOf((int) ((short) ch)));
                write("?");
            }
        }
        if (color != null) {
            write("\\cf0 ");
        }
        if (background != null) {
            write("\\highlight0 ");
        }
        if (bold) {
            write("\\b0 ");
        }
        if (italic) {
            write("\\i0 ");
        }
        if (under) {
            write("\\ul0 ");
        }
        if (strike) {
            write("\\strike0 ");
        }
    }

    protected void writeNL() throws Exception {
        write("\\par\r\n");
    }

    protected void writeEnd() throws Exception {
        write("\r\n}}\r\n");
    }

    protected void write(String rtf) throws Exception {
        byte[] b = rtf.getBytes(CKit.CHARSET_ASCII);
        this.out.write(b);
    }

    protected static String toInt255(double x) {
        int v = CKit.round(255.0d * x);
        if (v < 0) {
            v = 0;
        } else if (v > 255) {
            v = 255;
        }
        return String.valueOf(v);
    }
}
