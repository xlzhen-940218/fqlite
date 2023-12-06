package goryachev.common.util.text;

import goryachev.common.util.SB;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/QuerySegment.class */
public abstract class QuerySegment {
    public abstract boolean isMatch(String str);

    public abstract int indexOf(String str, int i);

    public abstract int length();

    protected static String normalize(String s) {
        SB sb = new SB(s);
        AccentedCharacters.removeAccents(sb);
        sb.toLowerCase();
        return sb.toString();
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/QuerySegment$Include.class */
    public static class Include extends QuerySegment {
        private String pattern;

        public Include(String pattern) {
            this.pattern = normalize(pattern);
        }

        @Override // goryachev.common.util.text.QuerySegment
        public boolean isMatch(String s) {
            return normalize(s).contains(this.pattern);
        }

        public String toString() {
            return "I." + this.pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int indexOf(String s, int fromIndex) {
            return normalize(s).indexOf(this.pattern, fromIndex);
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int length() {
            return this.pattern.length();
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/QuerySegment$Exact.class */
    public static class Exact extends QuerySegment {
        private String pattern;

        public Exact(String pattern) {
            this.pattern = pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public boolean isMatch(String s) {
            return s.contains(this.pattern);
        }

        public String toString() {
            return "E." + this.pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int indexOf(String s, int fromIndex) {
            return s.indexOf(this.pattern, fromIndex);
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int length() {
            return this.pattern.length();
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/QuerySegment$Exclude.class */
    public static class Exclude extends QuerySegment {
        private String pattern;

        public Exclude(String pattern) {
            this.pattern = pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public boolean isMatch(String s) {
            return normalize(s).contains(this.pattern);
        }

        public String toString() {
            return "X." + this.pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int indexOf(String s, int fromIndex) {
            return normalize(s).indexOf(this.pattern, fromIndex);
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int length() {
            return this.pattern.length();
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/QuerySegment$ExcludeExact.class */
    public static class ExcludeExact extends QuerySegment {
        private String pattern;

        public ExcludeExact(String pattern) {
            this.pattern = pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public boolean isMatch(String s) {
            return s.contains(this.pattern);
        }

        public String toString() {
            return "XE." + this.pattern;
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int indexOf(String s, int fromIndex) {
            return s.indexOf(this.pattern, fromIndex);
        }

        @Override // goryachev.common.util.text.QuerySegment
        public int length() {
            return this.pattern.length();
        }
    }
}
