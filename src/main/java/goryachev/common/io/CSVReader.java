package goryachev.common.io;

import goryachev.common.util.CList;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/CSVReader.class */
public class CSVReader implements Closeable {
    public final CSVParser parser;
    private BufferedReader rd;
    private boolean hasNext;
    private int skipLineCount;
    private boolean linesSkipped;

    public CSVReader(Reader rd) {
        this.hasNext = true;
        this.rd = new BufferedReader(rd);
        this.parser = new CSVParser();
    }

    public CSVReader(String text) {
        this(new StringReader(text));
    }

    public void setSeparatorChar(char c) {
        this.parser.setSeparatorChar(c);
    }

    public void setQuoteChar(char c) {
        this.parser.setQuoteChar(c);
    }

    public void setEscapeChar(char c) {
        this.parser.setEscapeChar(c);
    }

    public void setStrictQuotes(boolean on) {
        this.parser.setStrictQuotes(on);
    }

    public void setIgnoreLeadingWhitespace(boolean on) {
        this.parser.setIgnoreLeadingWhitespace(on);
    }

    public void setSkipLineCount(int n) {
        this.skipLineCount = n;
    }

    public CList<String[]> read() throws Exception {
        CList<String[]> cells = new CList<>();
        while (this.hasNext) {
            String[] nextLineAsTokens = readNext();
            if (nextLineAsTokens != null) {
                cells.add(nextLineAsTokens);
            }
        }
        return cells;
    }

    public String[] readNext() throws Exception {
        String[] result = null;
        do {
            String nextLine = getNextLine();
            if (!this.hasNext) {
                return result;
            }
            String[] r = this.parser.parseLineMulti(nextLine);
            if (r.length > 0) {
                if (result == null) {
                    result = r;
                } else {
                    String[] t = new String[result.length + r.length];
                    System.arraycopy(result, 0, t, 0, result.length);
                    System.arraycopy(r, 0, t, result.length, r.length);
                    result = t;
                }
            }
        } while (this.parser.isPending());
        return result;
    }

    protected String getNextLine() throws Exception {
        if (!this.linesSkipped) {
            for (int i = 0; i < this.skipLineCount; i++) {
                this.rd.readLine();
            }
            this.linesSkipped = true;
        }
        String nextLine = this.rd.readLine();
        if (nextLine == null) {
            this.hasNext = false;
        }
        if (this.hasNext) {
            return nextLine;
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.rd.close();
    }
}
