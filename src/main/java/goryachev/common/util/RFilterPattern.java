package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/RFilterPattern.class */
public class RFilterPattern {
    private final String spec;
    private final boolean wildcard;
    private final boolean relative;
    private final boolean directory;

    protected RFilterPattern(String spec, boolean wildcard, boolean relative, boolean directory) {
        this.spec = spec;
        this.wildcard = wildcard;
        this.relative = relative;
        this.directory = directory;
    }

    public static RFilterPattern parse(String spec) throws Exception {
        if (CKit.isBlank(spec)) {
            return null;
        }
        String s = spec;
        boolean relative = false;
        boolean dir = false;
        if (s.startsWith("/")) {
            relative = true;
            s = s.substring(1);
        }
        if (s.endsWith("/")) {
            dir = true;
        }
        boolean wildcard = CKit.containsAny(s, "?*");
        return new RFilterPattern(s, wildcard, relative, dir);
    }

    public String getSpec() {
        return this.spec;
    }

    public String toString() {
        return getSpec();
    }

    protected boolean matchName(String filename) {
        if (this.wildcard) {
            return FileTools.wildcardMatch(filename, this.spec, false);
        }
        return filename.equalsIgnoreCase(this.spec);
    }

    protected boolean matchPath(String pathToRoot, String filename) {
        String fn = String.valueOf(pathToRoot) + "/" + filename;
        if (this.wildcard) {
            return FileTools.wildcardMatch(fn, this.spec, false);
        }
        if (TextTools.startsWithIgnoreCase(fn, this.spec)) {
            if (this.spec.endsWith("/") || this.spec.endsWith("/" + filename)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean isMatch(String pathToRoot, String filename, boolean dir) {
        if (this.directory != dir) {
            return false;
        }
        if (dir) {
            filename = String.valueOf(filename) + "/";
        }
        if (this.relative) {
            return matchPath(pathToRoot, filename);
        }
        return matchName(filename);
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof RFilterPattern) {
            return this.spec.equals(((RFilterPattern) x).spec);
        }
        return false;
    }

    public int hashCode() {
        return RFilterPattern.class.hashCode() ^ this.spec.hashCode();
    }
}
