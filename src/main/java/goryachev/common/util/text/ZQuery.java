package goryachev.common.util.text;

import goryachev.common.util.CList;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/ZQuery.class */
public class ZQuery {
    private final String expression;
    private CList<QuerySegment> includes;
    private CList<QuerySegment> excludes;

    public ZQuery(String expression) {
        this.expression = expression;
        ZQueryParser p = new ZQueryParser(expression);
        p.parse();
        this.includes = p.getIncludes();
        this.excludes = p.getExcludes();
    }

    public int includedSegmentCount() {
        if (this.includes == null) {
            return 0;
        }
        return this.includes.size();
    }

    public QuerySegment getIncludeSegment(int ix) {
        return this.includes.get(ix);
    }

    public String getExpression() {
        return this.expression;
    }

    public String toString() {
        return getExpression();
    }

    public boolean isIncluded(String s) {
        if (this.includes == null) {
            return true;
        }
        if (s == null) {
            return false;
        }
        int sz = this.includes.size();
        if (sz == 0) {
            return true;
        }
        for (int i = 0; i < sz; i++) {
            QuerySegment seg = this.includes.get(i);
            if (!seg.isMatch(s)) {
                return false;
            }
        }
        return true;
    }

    public boolean isExcluded(String s) {
        if (this.excludes == null || s == null) {
            return false;
        }
        int sz = this.excludes.size();
        if (sz == 0) {
            return true;
        }
        for (int i = 0; i < sz; i++) {
            QuerySegment seg = this.excludes.get(i);
            if (seg.isMatch(s)) {
                return true;
            }
        }
        return false;
    }
}
