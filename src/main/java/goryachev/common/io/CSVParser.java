package goryachev.common.io;

import goryachev.common.util.CList;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/CSVParser.class */
public class CSVParser {
    private boolean strictQuotes;
    private boolean inCell;
    private String savedLine;
    private char separator = ',';
    private char quoteChar = '\"';
    private char escapeChar = '\\';
    private boolean ignoreLeadingWhiteSpace = true;

    public void setSeparatorChar(char c) {
        this.separator = c;
    }

    public void setQuoteChar(char c) {
        this.quoteChar = c;
    }

    public void setEscapeChar(char c) {
        this.escapeChar = c;
    }

    public void setStrictQuotes(boolean on) {
        this.strictQuotes = on;
    }

    public void setIgnoreLeadingWhitespace(boolean on) {
        this.ignoreLeadingWhiteSpace = on;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isPending() {
        return this.savedLine != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] parseLineMulti(String nextLine) throws Exception {
        return parseLine(nextLine, true);
    }

    public String[] parseLine(String nextLine) throws Exception {
        return parseLine(nextLine, false);
    }

    protected String[] parseLine(String nextLine, boolean multi) throws Exception {
        if (!multi && this.savedLine != null) {
            this.savedLine = null;
        }
        if (nextLine == null) {
            if (this.savedLine != null) {
                String s = this.savedLine;
                this.savedLine = null;
                return new String[]{s};
            }
            return null;
        }
        CList<String> tokensOnThisLine = new CList<>();
        StringBuilder sb = new StringBuilder(256);
        boolean inQuotes = false;
        if (this.savedLine != null) {
            sb.append(this.savedLine);
            this.savedLine = null;
            inQuotes = true;
        }
        int i = 0;
        while (i < nextLine.length()) {
            char c = nextLine.charAt(i);
            if (c == this.escapeChar) {
                if (isNextCharacterEscapable(nextLine, inQuotes || this.inCell, i)) {
                    sb.append(nextLine.charAt(i + 1));
                    i++;
                }
            } else if (c == this.quoteChar) {
                if (isNextCharacterEscapedQuote(nextLine, inQuotes || this.inCell, i)) {
                    sb.append(nextLine.charAt(i + 1));
                    i++;
                } else {
                    if (!this.strictQuotes && i > 2 && nextLine.charAt(i - 1) != this.separator && nextLine.length() > i + 1 && nextLine.charAt(i + 1) != this.separator) {
                        if (this.ignoreLeadingWhiteSpace && sb.length() > 0 && isWhiteSpace(sb)) {
                            sb.setLength(0);
                        } else {
                            sb.append(c);
                        }
                    }
                    inQuotes = !inQuotes;
                }
                this.inCell = !this.inCell;
            } else if (c == this.separator && !inQuotes) {
                tokensOnThisLine.add(sb.toString());
                sb.setLength(0);
                this.inCell = false;
            } else if (!this.strictQuotes || inQuotes) {
                sb.append(c);
                this.inCell = true;
            }
            i++;
        }
        if (inQuotes) {
            if (multi) {
                sb.append("\n");
                this.savedLine = sb.toString();
                sb = null;
            } else {
                throw new Exception("quoted value must be terminated");
            }
        }
        if (sb != null) {
            tokensOnThisLine.add(sb.toString());
        }
        return (String[]) tokensOnThisLine.toArray(new String[tokensOnThisLine.size()]);
    }

    protected boolean isNextCharacterEscapable(String nextLine, boolean inQuotes, int i) {
        if (!inQuotes || nextLine.length() <= i + 1) {
            return false;
        }
        return nextLine.charAt(i + 1) == this.quoteChar || nextLine.charAt(i + 1) == this.escapeChar;
    }

    protected boolean isNextCharacterEscapedQuote(String nextLine, boolean inQuotes, int i) {
        return inQuotes && nextLine.length() > i + 1 && nextLine.charAt(i + 1) == this.quoteChar;
    }

    protected boolean isWhiteSpace(CharSequence sb) {
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }
}
