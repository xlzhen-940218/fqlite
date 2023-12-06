package goryachev.common.util.text;

import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.text.QuerySegment;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/ZQueryParser.class */
public class ZQueryParser {
    private final String expression;
    private CList<QuerySegment> includes = new CList<>();
    private CList<QuerySegment> excludes = new CList<>();
    private boolean quote;
    private boolean white;
    private boolean plusMinus;
    private boolean done;

    public ZQueryParser(String expression) {
        this.expression = expression;
    }

    public CList<QuerySegment> getIncludes() {
        if (this.includes.size() == 0) {
            return null;
        }
        return this.includes;
    }

    public CList<QuerySegment> getExcludes() {
        if (this.excludes.size() == 0) {
            return null;
        }
        return this.excludes;
    }

    protected void add(String s) {
        if (s.length() > 0) {
            char c = s.charAt(0);
            switch (c) {
                case '\"':
                    if (s.endsWith("\"")) {
                        if (s.length() > 2) {
                            this.includes.add(new QuerySegment.Exact(s.substring(1, s.length() - 1)));
                            return;
                        } else {
                            this.includes.add(new QuerySegment.Include("\""));
                            return;
                        }
                    }
                    this.includes.add(new QuerySegment.Include(s));
                    return;
                case '+':
                    if (s.startsWith("+\"")) {
                        if (s.length() > 3) {
                            this.includes.add(new QuerySegment.Exact(s.substring(2, s.length() - 1)));
                            return;
                        } else {
                            this.includes.add(new QuerySegment.Exact(s.substring(1, s.length() - 1)));
                            return;
                        }
                    } else if (s.length() > 1) {
                        this.includes.add(new QuerySegment.Exact(s.substring(1, s.length())));
                        return;
                    } else {
                        this.includes.add(new QuerySegment.Include("+"));
                        return;
                    }
                case '-':
                    if (s.startsWith("-\"")) {
                        if (s.length() > 3) {
                            this.excludes.add(new QuerySegment.ExcludeExact(s.substring(2, s.length() - 1)));
                            return;
                        } else {
                            this.excludes.add(new QuerySegment.Exclude(s.substring(1, s.length() - 1)));
                            return;
                        }
                    } else if (s.length() > 1) {
                        this.excludes.add(new QuerySegment.Exclude(s.substring(1, s.length())));
                        return;
                    } else {
                        this.includes.add(new QuerySegment.Include("-"));
                        return;
                    }
                default:
                    this.includes.add(new QuerySegment.Include(s));
                    return;
            }
        }
    }

    protected int getChar(int ix) {
        if (ix >= 0 && ix < this.expression.length()) {
            return this.expression.charAt(ix);
        }
        return -1;
    }

    protected boolean isStartQuote(int ix) {
        if (this.quote) {
            return false;
        }
        if (this.plusMinus) {
            return true;
        }
        return this.white;
    }

    public void parse() {
        if (this.done) {
            return;
        }
        if (this.expression != null) {
            this.white = true;
            int start = 0;
            int sz = this.expression.length();
            for (int i = 0; i < sz; i++) {
                char c = this.expression.charAt(i);
                if (c == '\"') {
                    if (isStartQuote(i)) {
                        if (this.white) {
                            start = i;
                        }
                        this.white = false;
                        this.quote = true;
                    } else {
                        this.quote = false;
                    }
                } else if (CKit.isBlank(c)) {
                    if (!this.quote && !this.white) {
                        String sub = this.expression.substring(start, i);
                        add(sub);
                        this.white = true;
                    }
                    this.plusMinus = false;
                } else if (this.white) {
                    start = i;
                    this.white = false;
                    switch (c) {
                        case '+':
                        case '-':
                            this.plusMinus = true;
                            continue;
                    }
                } else {
                    this.plusMinus = false;
                }
            }
            if (!this.white) {
                String sub2 = this.expression.substring(start, sz);
                add(sub2);
            }
        }
        this.done = true;
    }
}
