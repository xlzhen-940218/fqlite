package goryachev.common.io;

import fqlite.base.Global;
import goryachev.common.util.CKit;
import goryachev.common.util.SB;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/CSVWriter.class */
public class CSVWriter implements Closeable {
    private Writer wr;
    private char delimiter = ',';
    private Character quoteChar = '\"';
    private Character escapeChar = '\\';
    private String lineEnd = "\r\n";
    private boolean forceQuote;
    private int column;

    public CSVWriter(Writer wr) {
        this.wr = CKit.toBufferedWriter(wr);
    }

    public void setDelimiterChar(Character c) {
        this.delimiter = c.charValue();
    }

    public void setQuoteChar(Character c) {
        this.quoteChar = c;
    }

    public void setEscapeChar(Character c) {
        this.escapeChar = c;
    }

    public void setLineEnd(String s) {
        this.lineEnd = s;
    }

    public void setForceQuote(boolean on) {
        this.forceQuote = on;
    }

    public void write(String s) throws Exception {
        this.wr.write(s);
    }

    public void write(char c) throws Exception {
        this.wr.write(c);
    }

    public void writeLines(List<String[]> cells) throws Exception {
        for (String[] line : cells) {
            writeLine(line);
        }
    }

    public void writeLine(String[] cells) throws Exception {
        if (cells != null) {
            for (String s : cells) {
                writeCell(s);
            }
        }
        writeNewLine();
    }

    public void writeCell(Object x) throws Exception {
        if (this.column > 0) {
            this.wr.write(this.delimiter);
        }
        if (x != null) {
            String cell = x.toString();
            if (containsSpecialCharacters(cell)) {
                if (this.quoteChar != null) {
                    this.wr.write(this.quoteChar.charValue());
                }
                this.wr.write(convertCell(cell));
                if (this.quoteChar != null) {
                    this.wr.write(this.quoteChar.charValue());
                }
            } else {
                if (this.forceQuote && this.quoteChar != null) {
                    this.wr.write(this.quoteChar.charValue());
                }
                this.wr.write(cell);
                if (this.forceQuote && this.quoteChar != null) {
                    this.wr.write(this.quoteChar.charValue());
                }
            }
        }
        this.column++;
    }

    public void writeNewLine() throws Exception {
        this.wr.write(this.lineEnd);
        this.column = 0;
    }

    protected boolean containsSpecialCharacters(String s) {
        if (s.startsWith(Global.REGULAR_RECORD) || s.endsWith(Global.REGULAR_RECORD)) {
            return true;
        }
        if ((this.quoteChar != null && s.indexOf(this.quoteChar.charValue()) >= 0) || s.indexOf(this.delimiter) >= 0 || s.indexOf(10) >= 0) {
            return true;
        }
        return false;
    }

    protected String convertCell(String s) {
        SB sb = new SB(s.length() + 32);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (this.quoteChar != null && c == this.quoteChar.charValue()) {
                sb.append(this.quoteChar).append(this.quoteChar);
            } else if (this.escapeChar != null && c == this.escapeChar.charValue()) {
                sb.append(this.escapeChar).append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void flush() throws IOException {
        this.wr.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.wr.close();
    }
}
