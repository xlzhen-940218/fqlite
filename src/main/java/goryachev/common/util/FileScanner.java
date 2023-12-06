package goryachev.common.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/FileScanner.class */
public abstract class FileScanner {
    private CList<ScanEntry> folders = new CList<>();
    private boolean sort;
    private RFileFilter defaultFilter;

    protected abstract void processFile(File file) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/FileScanner$ScanEntry.class */
    public static class ScanEntry {
        public File folder;
        public RFileFilter filter;

        protected ScanEntry() {
        }
    }

    public void setSortingEnabled(boolean on) {
        this.sort = on;
    }

    public void setFileFilter(RFileFilter f) {
        this.defaultFilter = f;
    }

    public void setFileFilterSpec(String spec) throws Exception {
        this.defaultFilter = RFileFilter.parse(spec);
    }

    public void addFolder(File folder) {
        addFolder(folder, null);
    }

    public void addFolder(File folder, RFileFilter filter) {
        ScanEntry en = new ScanEntry();
        en.folder = folder;
        en.filter = filter;
        this.folders.add(en);
    }

    public void addFolder(String filename) {
        File f = new File(filename);
        addFolder(f);
    }

    public void scan() throws Exception {
        Iterator<ScanEntry> it = this.folders.iterator();
        while (it.hasNext()) {
            ScanEntry en = it.next();
            RFileFilter f = en.filter == null ? this.defaultFilter : en.filter;
            if (f == null) {
                f = new RFileFilter();
            }
            FileFilter ff = f.getFilter(en.folder);
            scanFile(en.folder, ff);
        }
    }

    protected void scanFile(File file, FileFilter filter) throws Exception {
        CKit.checkCancelled();
        if (file.isFile()) {
            processFile(file);
        } else if (file.isDirectory()) {
            File[] fs = filter == null ? file.listFiles() : file.listFiles(filter);
            if (fs != null) {
                if (this.sort) {
                    new CComparator<File>() { // from class: goryachev.common.util.FileScanner.1
                        @Override // goryachev.common.util.CComparator, java.util.Comparator
                        public int compare(File a, File b) {
                            CKit.checkCancelled();
                            return compareText(a.getName(), b.getName());
                        }
                    }.sort(fs);
                }
                for (File f : fs) {
                    scanFile(f, filter);
                }
            }
        }
    }
}
