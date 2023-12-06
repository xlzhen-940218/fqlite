package goryachev.common.util;

import goryachev.common.log.Log;
import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/RFileFilter.class */
public class RFileFilter {
    public static final String HIDDEN = "Hidden Files";
    public static final String SYSTEM = "System Files";
    public static final String TOKEN_EXCLUDE = "- ";
    public static final String TOKEN_INCLUDE = "+ ";
    public static final String TOKEN_IGNORE_HIDDEN_FILES = "-hidden";
    public static final String TOKEN_IGNORE_SYSTEM_FILES = "-system";
    private boolean ignoreHidden;
    private boolean ignoreSystem;
    private CList<RFilterPattern> excludePatterns;
    private CList<RFilterPattern> includePatterns;
    private volatile Object triggeredRule;
    protected static final Log log = Log.get("RFileFilter");
    private static CList<RFilterPattern> systemPatterns = createSystemPatterns();

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof RFileFilter) {
            RFileFilter o = (RFileFilter) x;
            return this.ignoreHidden == o.ignoreHidden && this.ignoreSystem == o.ignoreSystem && CKit.equals(this.excludePatterns, o.excludePatterns) && CKit.equals(this.includePatterns, o.includePatterns);
        }
        return false;
    }

    public int hashCode() {
        int h = FH.hash(RFileFilter.class);
        return FH.hash(FH.hash(FH.hash(FH.hash(h, this.excludePatterns), this.includePatterns), this.ignoreHidden), this.ignoreSystem);
    }

    public Object getTriggeredRule() {
        return this.triggeredRule;
    }

    public static CList<RFilterPattern> createSystemPatterns() {
        String[] ss = {"Thumbs.db", ".DS_Store", ".Spotlight-V100/", ".TemporaryItems/", "*~", "~*", "#*#", ".#*", "%*%", ".bzr/", ".bzrignore", "CVS/", ".cvsignore", ".git/", ".gitattributes", ".gitignore", ".gitmodules", ".hg/", ".hgignore", ".hgsub", ".hgsubstate", ".hgtags", "SCCS/", ".svn/", "_svn/", "vssver.scc"};
        CList<RFilterPattern> ps = new CList<>(ss.length);
        for (String s : ss) {
            try {
                ps.add(RFilterPattern.parse(s));
            } catch (Exception e) {
                log.error((Throwable) e);
            }
        }
        return ps;
    }

    public static RFileFilter parse(String spec) throws Exception {
        if (CKit.isNotBlank(spec)) {
            Object[] ss = CKit.split(spec, "\n");
            return parse(ss);
        }
        return new RFileFilter();
    }

    public static RFileFilter parse(Object[] ss) throws Exception {
        RFileFilter f = new RFileFilter();
        for (Object x : ss) {
            String s = x.toString().trim();
            if (CKit.isNotBlank(s)) {
                if (s.startsWith(TOKEN_IGNORE_HIDDEN_FILES)) {
                    f.setIgnoreHiddenFiles(true);
                } else if (s.startsWith(TOKEN_IGNORE_SYSTEM_FILES)) {
                    f.setIgnoreSystemFiles(true);
                } else if (s.startsWith(TOKEN_EXCLUDE)) {
                    RFilterPattern p = RFilterPattern.parse(s.substring(TOKEN_EXCLUDE.length()));
                    if (p != null) {
                        if (f.excludePatterns == null) {
                            f.excludePatterns = new CList<>();
                        }
                        f.excludePatterns.add(p);
                    }
                } else if (s.startsWith(TOKEN_INCLUDE)) {
                    RFilterPattern p2 = RFilterPattern.parse(s.substring(TOKEN_INCLUDE.length()));
                    if (p2 != null) {
                        if (f.includePatterns == null) {
                            f.includePatterns = new CList<>();
                        }
                        f.includePatterns.add(p2);
                    }
                } else {
                    throw new UserException("invalid filter spec: " + s);
                }
            }
        }
        return f;
    }

    public static RFileFilter parseQuiet(String spec) {
        try {
            if (CKit.isNotBlank(spec)) {
                return parse(spec);
            }
        } catch (Exception e) {
            log.error((Throwable) e);
        }
        RFileFilter f = new RFileFilter();
        f.setIgnoreHiddenFiles(true);
        f.setIgnoreSystemFiles(true);
        return f;
    }

    public String getSpec() {
        SB sb = new SB();
        if (this.ignoreHidden) {
            sb.a(TOKEN_IGNORE_HIDDEN_FILES);
            sb.nl();
        }
        if (this.ignoreSystem) {
            sb.a(TOKEN_IGNORE_SYSTEM_FILES);
            sb.nl();
        }
        if (this.excludePatterns != null) {
            toSpec(sb, TOKEN_EXCLUDE, this.excludePatterns);
        }
        if (this.includePatterns != null) {
            toSpec(sb, TOKEN_INCLUDE, this.includePatterns);
        }
        return sb.toString();
    }

    public void setExcludeSpec(String s) throws Exception {
        this.excludePatterns = parsePatterns(s);
    }

    public String getExcludeSpec() {
        return toSpec(null, null, this.excludePatterns);
    }

    public void setIncludeSpec(String s) throws Exception {
        this.includePatterns = parsePatterns(s);
    }

    public String getIncludeSpec() {
        return toSpec(null, null, this.includePatterns);
    }

    protected String toSpec(SB sb, String prefix, CList<RFilterPattern> patterns) {
        if (patterns != null) {
            Iterator<RFilterPattern> it = patterns.iterator();
            while (it.hasNext()) {
                RFilterPattern p = it.next();
                if (sb == null) {
                    sb = new SB();
                }
                if (prefix != null) {
                    sb.a(prefix);
                }
                sb.a(p.getSpec());
                sb.nl();
            }
        }
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    protected CList<RFilterPattern> parsePatterns(String spec) throws Exception {
        CList<RFilterPattern> list = new CList<>();
        String[] ss = CKit.split(spec, "\n");
        for (String s : ss) {
            RFilterPattern p = RFilterPattern.parse(s);
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    public void setIgnoreHiddenFiles(boolean on) {
        this.ignoreHidden = on;
    }

    public boolean isIgnoreHiddenFiles() {
        return this.ignoreHidden;
    }

    public void setIgnoreSystemFiles(boolean on) {
        this.ignoreSystem = on;
    }

    public boolean isIgnoreSystemFiles() {
        return this.ignoreSystem;
    }

    protected boolean isMatchFound(CList<RFilterPattern> patterns, String pathToRoot, String filename, boolean dir) {
        if (patterns != null) {
            Iterator<RFilterPattern> it = patterns.iterator();
            while (it.hasNext()) {
                RFilterPattern p = it.next();
                if (p.isMatch(pathToRoot, filename, dir)) {
                    this.triggeredRule = p;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    protected boolean isExcluded(String pathToRoot, String filename, boolean dir) {
        return isMatchFound(this.excludePatterns, pathToRoot, filename, dir);
    }

    protected boolean isIncluded(String pathToRoot, String filename, boolean dir) {
        return isMatchFound(this.includePatterns, pathToRoot, filename, dir);
    }

    public boolean accept(String pathToRoot, String filename, boolean dir, boolean hidden) {
        this.triggeredRule = null;
        if (isIncluded(pathToRoot, filename, dir)) {
            return true;
        }
        if (this.ignoreHidden && hidden) {
            this.triggeredRule = HIDDEN;
            return false;
        }
        if (this.ignoreSystem) {
            Iterator<RFilterPattern> it = systemPatterns.iterator();
            while (it.hasNext()) {
                RFilterPattern p = it.next();
                if (p.isMatch(pathToRoot, filename, dir)) {
                    this.triggeredRule = SYSTEM;
                    return false;
                }
            }
        }
        if (isExcluded(pathToRoot, filename, dir)) {
            return false;
        }
        this.triggeredRule = null;
        return true;
    }

    public FileFilter getFilter(final File root) {
        return new FileFilter() { // from class: goryachev.common.util.RFileFilter.1
            @Override // java.io.FileFilter
            public boolean accept(File f) {
                String pathToRoot = CKit.pathToRoot(root, f);
                String name = f.getName();
                boolean dir = f.isDirectory();
                boolean hidden = f.isHidden();
                return RFileFilter.this.accept(pathToRoot, name, dir, hidden);
            }
        };
    }
}
