package fqlite.base;

import java.nio.file.FileSystems;

/* loaded from: fqlite_next.jar:fqlite/base/Global.class */
public class Global {
    public static final String REGULAR_RECORD = " ";
    public static final String DELETED_RECORD_IN_PAGE = "D";
    public static final String FREELIST_ENTRY = "F";
    public static final String STATUS_CLOMUN = "S";
    public static final String UNALLOCATED_SPACE = "U";
    public static final String FQLITE_VERSION = "2.2";
    public static final String FQLITE_RELEASEDATE = "24/10/2023";
    public static final int CARVING_ERROR = -1;
    public static int LOGLEVEL = 4;
    public static int numberofThreads = 1;
    public static final String separator = FileSystems.getDefault().getSeparator();
    public static String WORKINGDIRECTORY;
    public static final int TTBLLEAFPAGE = 8;
    public static final int TTBLINTERIORPAGE = 12;
    public static final int TIDXLEAFPAGE = 10;
    public static final int TIDXINTERIORPAGE = 2;
    public static final int TOVERFLOWPAGE = 0;
    public static final String APPLICATION_NAME = "FQLite";
    public static final String APPLICATION_ICON = "/logo.png";
    public static final int REGULAR_DB_FILE = 0;
    public static final int ROLLBACK_JOURNAL_FILE = 1;
    public static final int WAL_ARCHIVE_FILE = 2;
}
