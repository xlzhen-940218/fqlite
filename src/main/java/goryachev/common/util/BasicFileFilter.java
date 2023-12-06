package goryachev.common.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.scene.control.ButtonBar;
import org.antlr.v4.runtime.tree.xpath.XPath;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter.class */
public abstract class BasicFileFilter implements FileFilter {
    public static final FileFilter ACCEPT = new FileFilter() { // from class: goryachev.common.util.BasicFileFilter.1
        @Override // java.io.FileFilter
        public boolean accept(File f) {
            return true;
        }
    };
    public static final FileFilter DENY = new FileFilter() { // from class: goryachev.common.util.BasicFileFilter.2
        @Override // java.io.FileFilter
        public boolean accept(File f) {
            return false;
        }
    };

    public static BasicFileFilter parse(final String spec) {
        if (spec.contains(XPath.WILDCARD) || spec.contains("?")) {
            return parseWildCard(spec);
        }
        return new BasicFileFilter() { // from class: goryachev.common.util.BasicFileFilter.3
            @Override // java.io.FileFilter
            public boolean accept(File f) {
                if (f != null) {
                    return spec.equalsIgnoreCase(f.getName());
                }
                return false;
            }
        };
    }

    public static BasicFileFilter parseWildCard(String spec) {
        boolean z;
        ArrayList<SegmentMatcher> a = new ArrayList<>();
        StringTokenizer tok = new StringTokenizer(spec.toLowerCase(), "*?", true);
        boolean wild = false;
        boolean star = false;
        while (tok.hasMoreTokens()) {
            String s = tok.nextToken();
            if (XPath.WILDCARD.equals(s)) {
                star = true;
            } else if ("?".equals(s)) {
                a.add(new AnyCharMatcher());
                wild = false;
            } else {
                if (star) {
                    a.add(new WildcardMatcher(s));
                    z = true;
                } else {
                    a.add(new RegularMatcher(s));
                    z = false;
                }
                wild = z;
                star = false;
            }
        }
        if (star) {
            a.add(new WildcardMatcher(ButtonBar.BUTTON_ORDER_NONE));
        } else if (!wild) {
            a.add(new EndMatcher());
        }
        return new WildCardFileFilter((SegmentMatcher[]) a.toArray(new SegmentMatcher[a.size()]));
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$WildCardFileFilter.class */
    public static class WildCardFileFilter extends BasicFileFilter {
        private SegmentMatcher[] segments;

        public WildCardFileFilter(SegmentMatcher[] segments) {
            this.segments = segments;
        }

        @Override // java.io.FileFilter
        public boolean accept(File f) {
            SegmentMatcher[] segmentMatcherArr;
            String name = f.getName().toLowerCase();
            int ix = 0;
            for (SegmentMatcher s : this.segments) {
                ix = s.match(name, ix);
                if (ix < 0) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            SegmentMatcher[] segmentMatcherArr;
            StringBuilder sb = new StringBuilder();
            for (SegmentMatcher s : this.segments) {
                s.dump(sb);
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$SegmentMatcher.class */
    public static abstract class SegmentMatcher {
        public abstract int match(String str, int i);

        public abstract void dump(StringBuilder sb);

        protected SegmentMatcher() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$RegularMatcher.class */
    public static class RegularMatcher extends SegmentMatcher {
        String pattern;

        public RegularMatcher(String pattern) {
            this.pattern = pattern;
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public int match(String text, int start) {
            if (text.startsWith(this.pattern, start)) {
                return start + this.pattern.length();
            }
            return -1;
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public void dump(StringBuilder sb) {
            sb.append("<M:").append(this.pattern).append(">");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$WildcardMatcher.class */
    public static class WildcardMatcher extends SegmentMatcher {
        String pattern;

        public WildcardMatcher(String pattern) {
            this.pattern = pattern;
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public int match(String text, int start) {
            int ix = text.indexOf(this.pattern, start);
            if (ix < 0) {
                return -1;
            }
            return ix + this.pattern.length();
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public void dump(StringBuilder sb) {
            sb.append("<W:").append(this.pattern).append(">");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$AnyCharMatcher.class */
    public static class AnyCharMatcher extends SegmentMatcher {
        protected AnyCharMatcher() {
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public int match(String text, int start) {
            int ix = start + 1;
            if (ix <= text.length()) {
                return ix;
            }
            return -1;
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public void dump(StringBuilder sb) {
            sb.append("<A>");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BasicFileFilter$EndMatcher.class */
    public static class EndMatcher extends SegmentMatcher {
        protected EndMatcher() {
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public int match(String text, int start) {
            if (start == text.length()) {
                return start;
            }
            return -1;
        }

        @Override // goryachev.common.util.BasicFileFilter.SegmentMatcher
        public void dump(StringBuilder sb) {
            sb.append("<E>");
        }
    }
}
