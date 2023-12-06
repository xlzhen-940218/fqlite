package goryachev.common.io;

import goryachev.common.util.CList;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/CommaDelimitedParser.class */
public class CommaDelimitedParser {
    private String text;
    private boolean trim = true;
    private char quote = '\"';
    private char comma = ',';
    private int start = 0;
    private CList<String> list;

    public CommaDelimitedParser(String text) {
        this.text = text;
    }

    public char getQuoteChar() {
        return this.quote;
    }

    public void setQuoteChar(char c) {
        this.quote = c;
    }

    public char getCommaChar() {
        return this.comma;
    }

    public void setCommaChar(char c) {
        this.comma = c;
    }

    public boolean getTrim() {
        return this.trim;
    }

    public void setTrim(boolean on) {
        this.trim = on;
    }

    public String[] parse() {
        this.list = new CList<>();
        boolean inQuotes = false;
        int i = 0;
        while (i < this.text.length()) {
            char c = this.text.charAt(i);
            if (inQuotes) {
                if (c == this.quote) {
                    inQuotes = false;
                }
            } else if (c == this.comma) {
                add(i);
                this.start = i + 1;
            } else if (c == this.quote && afterComma(i)) {
                inQuotes = true;
            }
            i++;
        }
        add(i);
        int sz = this.list.size();
        if (sz == 0) {
            return null;
        }
        return (String[]) this.list.toArray(new String[sz]);
    }

    protected boolean afterComma(int end) {
        String s = this.text.substring(this.start, end);
        return s.trim().length() == 0;
    }

    protected void add(int end) {
        if (end > this.start) {
            String s = this.text.substring(this.start, end);
            this.start = end;
            if (this.trim) {
                s = s.trim();
                if (s.length() == 0) {
                    return;
                }
            }
            this.list.add(s);
        }
    }
}
