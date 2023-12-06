package goryachev.common.util;

import goryachev.common.io.CommaDelimitedParser;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CompoundFileFilter.class */
public class CompoundFileFilter implements FileFilter {
    private FileFilter includeFolderFilter;
    private FileFilter includeFileFilter;
    private FileFilter excludeFolderFilter;
    private FileFilter excludeFileFilter;
    private boolean skipSystem;

    public CompoundFileFilter(String includeFolders, String includeFiles, String excludeFolders, String excludeFiles, boolean skipSystem) {
        this.includeFolderFilter = parseCommaDelimited(includeFolders, true);
        this.includeFileFilter = parseCommaDelimited(includeFiles, true);
        this.excludeFolderFilter = parseCommaDelimited(excludeFolders, false);
        this.excludeFileFilter = parseCommaDelimited(excludeFiles, false);
        this.skipSystem = skipSystem;
    }

    @Override // java.io.FileFilter
    public boolean accept(File f) {
        if (f != null) {
            if (this.skipSystem && FileTools.isHiddenOrSystem(f)) {
                return false;
            }
            return f.isDirectory() ? this.includeFolderFilter.accept(f) && !this.excludeFolderFilter.accept(f) : this.includeFileFilter.accept(f) && !this.excludeFileFilter.accept(f);
        }
        return false;
    }

    public static FileFilter parseCommaDelimited(String s, boolean acceptIfBlank) {
        if (!CKit.isBlank(s)) {
            CommaDelimitedParser p = new CommaDelimitedParser(s);
            String[] ss = p.parse();
            if (ss != null) {
                final ArrayList<FileFilter> filters = new ArrayList<>(ss.length);
                for (String spec : ss) {
                    FileFilter f = BasicFileFilter.parse(spec);
                    if (f != null) {
                        filters.add(f);
                    }
                }
                if (filters.size() == 1) {
                    return filters.get(0);
                }
                return new FileFilter() { // from class: goryachev.common.util.CompoundFileFilter.1
                    @Override // java.io.FileFilter
                    public boolean accept(File f2) {
                        for (int i = 0; i < filters.size(); i++) {
                            if (((FileFilter) filters.get(i)).accept(f2)) {
                                return true;
                            }
                        }
                        return false;
                    }
                };
            }
        }
        return acceptIfBlank ? BasicFileFilter.ACCEPT : BasicFileFilter.DENY;
    }
}
