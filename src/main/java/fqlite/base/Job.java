//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.descriptor.AbstractDescriptor;
import fqlite.descriptor.IndexDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.pattern.HeaderPattern;
import fqlite.types.BLOBElement;
import fqlite.ui.DBPropertyPanel;
import fqlite.ui.NodeObject;
import fqlite.ui.RollbackPropertyPanel;
import fqlite.ui.WALPropertyPanel;
import fqlite.util.Auxiliary;
import fqlite.util.ByteSeqSearcher;
import fqlite.util.Version;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Job extends Base {
    public ByteBuffer db;
    public boolean readWAL = false;
    String walpath = null;
    public WALReader wal = null;
    Hashtable<String, String> guiwaltab = new Hashtable();
    public boolean readRollbackJournal = false;
    String rollbackjournalpath = null;
    public RollbackJournalReader rol = null;
    Hashtable<String, String> guiroltab = new Hashtable();
    static final String MAGIC_HEADER_STRING = "53514c69746520666f726d6174203300";
    static final String NO_AUTO_VACUUM = "00000000";
    static final String NO_MORE_ENTRIES = "00000000";
    static final String LEAF_PAGE = "0d";
    static final String INTERIOR_PAGE = "05";
    static final String ROW_ID = "00";
    public GUI gui = null;
    public TreeItem<NodeObject> dbNode = null;
    public TreeItem<NodeObject> walNode = null;
    public TreeItem<NodeObject> rjNode = null;
    public ConcurrentLinkedQueue<Integer> overflowpages = new ConcurrentLinkedQueue();
    Hashtable<String, String> guitab = new Hashtable();
    public HashMap<String, Number> timestamps = new HashMap();
    DBPropertyPanel panel = null;
    WALPropertyPanel walpanel = null;
    RollbackPropertyPanel rolpanel = null;
    long size = 0L;
    public String path;
    public String filename;
    public AsynchronousFileChannel file;
    public static Charset db_encoding;
    List<String> lines = new LinkedList();
    ConcurrentHashMap<String, ObservableList<LinkedList<String>>> resultlist = new ConcurrentHashMap();
    String headerstring;
    String PRAGMA_journal_mode = "OFF";
    byte ffwversion;
    byte ffrversion;
    byte reservedspace;
    byte maxpayloadfrac;
    byte minpayloadfrac;
    byte leafpayloadfrac;
    long filechangecounter;
    long inheaderdbsize;
    long sizeinpages;
    long schemacookie;
    long schemaformatnumber;
    long defaultpagecachesize;
    long userversion;
    long vacuummode;
    long versionvalidfornumber;
    long avacc;
    public Map<String, TableDescriptor> virtualTables = new HashMap();
    int scanned_entries = 0;
    Hashtable<String, String> tblSig;
    boolean is_default = false;
    public List<TableDescriptor> headers = new ArrayList();
    public List<IndexDescriptor> indices = new ArrayList();
    public AtomicInteger runningTasks = new AtomicInteger();
    int tablematch = 0;
    int indexmatch = 0;
    public Map<String, BLOBElement> BLOBs = new HashMap();
    public Map<String, Image> Thumbnails = new HashMap();
    public Map<String, String> FileCache = new HashMap();
    public Map<String, Integer> LineHashes = new HashMap();
    public Map<String, LinkedList<Version>> TimeLineHashes = new HashMap();
    public Set<String> inspectProtoBuffer = new HashSet();
    public Set<String> inspectBASE64 = new HashSet();
    AtomicInteger numberofcells = new AtomicInteger();
    Set<Integer> allreadyvisit;
    List<RecoveryTask> tasklist = new LinkedList();
    public AbstractDescriptor[] pages;
    int[] pagetype;
    public int ps = 0;
    public int numberofpages = 0;
    int fpnumber = 0;
    int fphead = 0;
    String sqliteversion = "";
    boolean autovacuum = false;
    long totalbytes = 0L;
    AtomicReferenceArray<Boolean> checked;
    public AtomicInteger hits = new AtomicInteger();
    boolean emptydb = false;

    static {
        db_encoding = StandardCharsets.UTF_8;
    }

    private void readFileIntoBuffer() throws IOException {
        this.size = this.file.size();
        this.db = ByteBuffer.allocateDirect((int) this.size);
        Future<Integer> result = this.file.read(this.db, 0L);

        while (!result.isDone()) {
        }

        this.db.position(0);
    }

    private ByteBuffer readWALIntoBuffer(String walpath) throws IOException {
        Path p = Paths.get(walpath);
        this.file = AsynchronousFileChannel.open(p, StandardOpenOption.READ);
        if (this.file == null) {
            return null;
        } else {
            this.size = this.file.size();
            ByteBuffer bb = ByteBuffer.allocateDirect((int) this.size);
            Future<Integer> result = this.file.read(bb, 0L);

            while (!result.isDone()) {
            }

            bb.position(0);
            return bb;
        }
    }

    public void setPropertyPanel(DBPropertyPanel p) {
        this.panel = p;
    }

    public void setWALPropertyPanel(WALPropertyPanel p) {
        this.walpanel = p;
    }

    public void setRollbackPropertyPanel(RollbackPropertyPanel p) {
        this.rolpanel = p;
    }

    public void setTreeItem(TreeItem<NodeObject> dbNode) {
        this.dbNode = dbNode;
    }

    public TreeItem<NodeObject> getTreeItem() {
        return this.dbNode;
    }

    public String[][] getHeaderProperties() {
        if (this.ffwversion == this.ffrversion) {
            switch (this.ffwversion) {
                case 0:
                    this.PRAGMA_journal_mode = "OFF";
                    break;
                case 1:
                    if (this.readRollbackJournal) {
                        this.PRAGMA_journal_mode = "Rollback Journal PERSIST";
                    } else {
                        this.PRAGMA_journal_mode = "Rollback Journal OFF (no file)";
                    }
                    break;
                case 2:
                    this.PRAGMA_journal_mode = "WAL";
                    break;
                default:
                    if (this.ffwversion <= 2 || this.ffrversion != 1 && this.ffrversion != 2) {
                        this.PRAGMA_journal_mode = "NO READ OR WRITE";
                    } else {
                        this.PRAGMA_journal_mode = "READ ONLY";
                    }
            }
        }

        String[][] var10000 = new String[][]{{"0", "The header string", this.headerstring}, {"16", "The database page size in bytes", String.valueOf(this.ps)}, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        String[] var10003 = new String[]{"18", "File format write version", null};
        String var10006 = String.valueOf(this.ffwversion);
        var10003[2] = var10006 + " Journal Mode ->" + this.PRAGMA_journal_mode;
        var10000[2] = var10003;
        var10000[3] = new String[]{"19", "File format read version", String.valueOf(this.ffrversion)};
        var10000[4] = new String[]{"20", "Unused reserved space at the end of each page ", String.valueOf(this.reservedspace)};
        var10000[5] = new String[]{"21", "Maximum embedded payload fraction. Must be 64.", String.valueOf(this.maxpayloadfrac)};
        var10000[6] = new String[]{"22", "Minimum embedded payload fraction. Must be 32.", String.valueOf(this.minpayloadfrac)};
        var10000[7] = new String[]{"23", "Leaf payload fraction. Must be 32.", String.valueOf(this.leafpayloadfrac)};
        var10000[8] = new String[]{"24", "File change counter.", String.valueOf(this.filechangecounter)};
        var10000[9] = new String[]{"28", "Size of the database file in pages. ", String.valueOf(this.sizeinpages)};
        var10000[10] = new String[]{"32", "Page number of the first freelist trunk page.", String.valueOf(this.fphead)};
        var10000[11] = new String[]{"36", "Total number of freelist pages.", String.valueOf(this.fpnumber)};
        var10000[12] = new String[]{"40", "The schema cookie.", String.valueOf(this.schemacookie)};
        var10000[13] = new String[]{"44", "The schema format number. Supported schema formats are 1, 2, 3, and 4.", String.valueOf(this.schemaformatnumber)};
        var10000[14] = new String[]{"48", "Default page cache size.", String.valueOf(this.defaultpagecachesize)};
        var10003 = new String[]{"52", "The page number of the largest root b-tree page when in auto-vacuum or incremental-vacuum modes, or zero otherwise.", null};
        var10006 = String.valueOf(this.avacc);
        var10003[2] = var10006 + " (" + (this.avacc > 0L) + ")";
        var10000[15] = var10003;
        var10000[16] = new String[]{"56", "The database text encoding.", String.valueOf(db_encoding.displayName())};
        var10000[17] = new String[]{"60", "The \"user version\"", String.valueOf(this.userversion)};
        var10003 = new String[]{"64", "True (non-zero) for incremental-vacuum mode. False (zero) otherwise.", null};
        var10006 = String.valueOf(this.vacuummode);
        var10003[2] = var10006 + " (" + (this.vacuummode > 0L) + ")";
        var10000[18] = var10003;
        var10000[19] = new String[]{"92", "The version-valid-for number.", String.valueOf(this.versionvalidfornumber)};
        var10000[20] = new String[]{"96", "SQLITE_VERSION_NUMBER", String.valueOf(this.sqliteversion)};
        String[][] prop = var10000;
        return prop;
    }

    public String[][] getWALHeaderProperties() {
        String[][] prop = new String[][]{{"0", "HeaderString", this.wal.headerstring}, {"4", "File format version", String.valueOf(this.wal.ffversion)}, {"8", "Database page size", String.valueOf(this.wal.ps)}, {"12", "Checkpoint sequence number", String.valueOf(this.wal.csn)}, {"16", "Salt-1", String.valueOf(this.wal.hsalt1)}, {"20", "Salt-2", String.valueOf(this.wal.hsalt2)}, {"24", "Checksum1", String.valueOf(this.wal.hchecksum1)}, {"28", "Checksum2", String.valueOf(this.wal.hchecksum2)}};
        return prop;
    }

    public String[][] getRollbackHeaderProperties() {
        String[][] prop = new String[][]{{"0", "HeaderString", "d9d505f920a163d7"}, {"8", "number of pages", String.valueOf(this.rol.pagecount)}, {"12", "nounce for checksum", String.valueOf(this.rol.nounce)}, {"16", "pages", String.valueOf(this.rol.pages)}, {"20", "sector size ", String.valueOf(this.rol.sectorsize)}, {"24", "journal page size", String.valueOf(this.rol.journalpagesize)}};
        return prop;
    }

    public String[][] getCheckpointProperties() {
        ArrayList<String[]> prop = new ArrayList();
        Set<Long> data = this.wal.checkpoints.descendingKeySet();
        Iterator<Long> it = data.iterator();

        while (it.hasNext()) {
            Long salt1 = (Long) it.next();
            LinkedList<WALReader.WALFrame> list = (LinkedList) this.wal.checkpoints.get(salt1);
            Iterator<WALReader.WALFrame> frames = list.iterator();

            while (frames.hasNext()) {
                WALReader.WALFrame current = (WALReader.WALFrame) frames.next();
                String[] line = new String[]{String.valueOf(current.salt1), String.valueOf(current.salt2), String.valueOf(current.framenumber), String.valueOf(current.pagenumber), String.valueOf(current.committed)};
                prop.add(line);
            }
        }

        String[][] result = new String[prop.size()][5];
        prop.toArray(result);
        return result;
    }

    public LinkedHashMap<String, String[][]> getTableColumnTypes() {
        Iterator<TableDescriptor> it1 = this.headers.iterator();

        LinkedHashMap ht;
        TableDescriptor td;
        String[] types;
        String[] sqltypes;
        String[] tableconstraints;
        String[][] row;
        for (ht = new LinkedHashMap(); it1.hasNext(); ht.put(td.tblname, row)) {
            td = (TableDescriptor) it1.next();
            String[] names = (String[]) td.columnnames.toArray(new String[0]);
            types = (String[]) td.serialtypes.toArray(new String[0]);
            sqltypes = (String[]) td.sqltypes.toArray(new String[0]);
            tableconstraints = null;
            String[] constraints = null;
            if (td.tableconstraints != null) {
                tableconstraints = (String[]) td.tableconstraints.toArray(new String[0]);
            }

            if (td.constraints != null) {
                constraints = (String[]) td.constraints.toArray(new String[0]);
            }

            row = null;
            if (tableconstraints != null && constraints != null) {
                row = new String[][]{names, types, sqltypes, constraints, tableconstraints};
            } else if (tableconstraints != null) {
                row = new String[][]{names, types, sqltypes, tableconstraints};
            } else if (constraints != null) {
                row = new String[][]{names, types, sqltypes, constraints};
            } else {
                row = new String[][]{names, types, sqltypes};
            }
        }

        Iterator<IndexDescriptor> it2 = this.indices.iterator();

        while (it2.hasNext()) {
            IndexDescriptor id = (IndexDescriptor) it2.next();
            types = (String[]) id.columnnames.toArray(new String[0]);
            sqltypes = (String[]) id.columntypes.toArray(new String[0]);
            tableconstraints = null;
            row = new String[][]{types, sqltypes};
            ht.put("idx:" + id.idxname, row);
        }

        return ht;
    }

    public String[][] getSchemaProperties() {
        String[][] prop = new String[this.headers.size() - 1 + this.indices.size()][6];
        int counter = 0;
        Iterator<TableDescriptor> it1 = this.headers.iterator();

        while (it1.hasNext()) {
            TableDescriptor td = (TableDescriptor) it1.next();
            if (!td.tblname.startsWith("__")) {
                prop[counter] = new String[]{"Table", td.tblname, String.valueOf(td.root), td.sql, String.valueOf(td.isVirtual()), String.valueOf(td.ROWID)};
                ++counter;
            }
        }

        for (Iterator<IndexDescriptor> it2 = this.indices.iterator(); it2.hasNext(); ++counter) {
            IndexDescriptor td = (IndexDescriptor) it2.next();

            try {
                prop[counter] = new String[]{"Index", td.idxname, String.valueOf(td.root), td.getSql(), "", ""};
            } catch (Exception var7) {
                System.out.println("Exception wurde abgefangen");
            }
        }

        return prop;
    }

    public void updateRollbackPanel() {
        this.rolpanel.initHeaderTable(this.getRollbackHeaderProperties());
    }

    public void updatePropertyPanel() {
        this.panel.initHeaderTable(this.getHeaderProperties());
        this.panel.initSchemaTable(this.getSchemaProperties());
        this.panel.initColumnTypesTable(this.getTableColumnTypes());
    }

    public void updateWALPanel() {
        this.walpanel.initHeaderTable(this.getWALHeaderProperties());
        this.walpanel.initCheckpointTable(this.getCheckpointProperties());
    }

    protected int processDB() throws InterruptedException, ExecutionException {
        this.allreadyvisit = ConcurrentHashMap.newKeySet();

        try {
            Path p = Paths.get(this.path);
            this.filename = p.getFileName().toString();
            this.path = p.toString();
            this.file = AsynchronousFileChannel.open(p, StandardOpenOption.READ);
            this.readFileIntoBuffer();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            Future<Integer> result = this.file.read(buffer, 0L);

            while (!result.isDone()) {
            }

            buffer.flip();
            byte[] header = new byte[16];
            buffer.get(header);
            this.headerstring = Auxiliary.bytesToHex(header);
            char[] charArray = new char[16];
            int cn = 0;
            byte[] pagesize = header;
            int codepage = header.length;

            for (int var8 = 0; var8 < codepage; ++var8) {
                byte b = pagesize[var8];
                charArray[cn] = (char) b;
                ++cn;
            }

            String txt = new String(charArray);
            this.headerstring = txt + " (0x" + this.headerstring + ")";
            if (!Auxiliary.bytesToHex(header).equals("53514c69746520666f726d6174203300")) {
                this.info("sorry. doesn't seem to be an sqlite file. Wrong header.");
                this.err("Doesn't seem to be an valid sqlite file. Wrong header");
                return -1;
            }

            this.info("header is okay. seems to be an sqlite database file.");
            buffer.position(18);
            this.ffwversion = buffer.get();
            this.info("File format write version. 1 for legacy; 2 for WAL. " + this.ffwversion);
            buffer.position(19);
            this.ffrversion = buffer.get();
            this.info("File format read version. 1 for legacy; 2 for WAL. " + this.ffrversion);
            buffer.position(20);
            this.reservedspace = buffer.get();
            this.info("Bytes of unused \"reserved\" space at the end of each page. Usually 0. " + this.reservedspace);
            this.maxpayloadfrac = buffer.get();
            this.info("Maximum embedded payload fraction. Must be 64." + this.maxpayloadfrac);
            this.minpayloadfrac = buffer.get();
            this.info("Minimum embedded payload fraction. Must be 32." + this.maxpayloadfrac);
            this.leafpayloadfrac = buffer.get();
            this.info("Leaf payload fraction. Must be 32.  " + this.leafpayloadfrac);
            buffer.position(16);
            this.inheaderdbsize = Integer.toUnsignedLong(buffer.getInt());
            if (this.inheaderdbsize == 1L) {
                this.inheaderdbsize = 65536L;
            }

            buffer.position(24);
            this.filechangecounter = Integer.toUnsignedLong(buffer.getInt());
            this.info("File change counter " + this.filechangecounter);
            buffer.position(28);
            this.sizeinpages = Integer.toUnsignedLong(buffer.getInt());
            this.info("Size of the database file in pages " + this.sizeinpages);
            buffer.position(40);
            this.schemacookie = Integer.toUnsignedLong(buffer.getInt());
            this.info("The schema cookie. (offset 40) " + this.schemacookie);
            this.schemaformatnumber = Integer.toUnsignedLong(buffer.getInt());
            this.info("The schema format number. (offset 44) " + this.schemaformatnumber);
            this.defaultpagecachesize = Integer.toUnsignedLong(buffer.getInt());
            this.info("Default page cache size. (offset 48) " + this.defaultpagecachesize);
            buffer.position(60);
            this.userversion = Integer.toUnsignedLong(buffer.getInt());
            this.info("User version (offset 60) " + this.userversion);
            this.vacuummode = Integer.toUnsignedLong(buffer.getInt());
            this.info("Incremential vacuum-mode (offset 64) " + this.vacuummode);
            buffer.position(92);
            this.versionvalidfornumber = Integer.toUnsignedLong(buffer.getInt());
            this.info("The version-valid-for number.  " + this.versionvalidfornumber);
            this.is_default = true;
            this.tblSig = new Hashtable();
            this.tblSig.put("", "default");
            this.info("found unkown sqlite-database.");
            buffer.position(52);
            this.avacc = Integer.toUnsignedLong(buffer.getInt());
            if (this.avacc == 0L) {
                this.info("Seems to be no AutoVacuum db. Nice :-).");
                this.autovacuum = false;
            } else {
                this.autovacuum = true;
            }

            byte[] encoding = new byte[4];
            buffer.position(56);
            buffer.get(encoding);
            codepage = ByteBuffer.wrap(encoding).getInt();
            switch (codepage) {
                case 0:
                case 1:
                    db_encoding = StandardCharsets.UTF_8;
                    this.info("Database encoding: UTF_8");
                    break;
                case 2:
                    db_encoding = StandardCharsets.UTF_16LE;
                    this.info("Database encoding: UTF_16LE");
                    break;
                case 3:
                    db_encoding = StandardCharsets.UTF_16BE;
                    this.info("Database encoding: UTF_16BE");
            }

            pagesize = new byte[2];
            buffer.position(16);
            buffer.get(pagesize);
            ByteBuffer psize = ByteBuffer.wrap(pagesize);
            this.ps = Auxiliary.TwoByteBuffertoInt(psize);
            if (this.ps == 0 || this.ps == 1) {
                this.ps = 65536;
            }

            this.info("page size " + this.ps + " Bytes ");
            this.totalbytes = this.file.size();
            this.numberofpages = (int) (this.totalbytes / (long) this.ps);
            this.info("Number of pages:" + this.numberofpages);
            ByteBuffer schema = ByteBuffer.allocate(this.ps);
            Future<Integer> rs = this.file.read(schema, 0L);

            while (!rs.isDone()) {
            }

            byte[] version = new byte[4];
            buffer.position(96);
            buffer.get(version);
            Integer v = ByteBuffer.wrap(version).getInt();
            this.sqliteversion = "" + String.valueOf(v);
            this.pages = new AbstractDescriptor[this.numberofpages + 1];
            this.pagetype = new int[this.numberofpages];
            this.checked = new AtomicReferenceArray(new Boolean[this.numberofpages]);
            byte[] tpattern = null;
            byte[] ipattern = null;
            int goback = 0;
            if (db_encoding == StandardCharsets.UTF_8) {
                tpattern = new byte[]{116, 97, 98, 108, 101};
                ipattern = new byte[]{105, 110, 100, 101, 120};
                goback = 11;
            } else if (db_encoding == StandardCharsets.UTF_16LE) {
                tpattern = new byte[]{116, 0, 97, 0, 98, 0, 108, 0, 101};
                ipattern = new byte[]{105, 0, 110, 0, 100, 0, 101, 0, 120};
                goback = 15;
            } else if (db_encoding == StandardCharsets.UTF_16BE) {
                tpattern = new byte[]{0, 116, 0, 97, 0, 98, 0, 108, 0, 101};
                ipattern = new byte[]{0, 105, 0, 110, 0, 100, 0, 101, 0, 120};
                goback = 16;
            }

            ByteSeqSearcher bsearch = new ByteSeqSearcher(tpattern);
            boolean again = false;
            int round = 0;
            ByteBuffer bb = this.db;

            String headerStr;
            label385:
            do {
                ++round;
                if (round == 2 && !this.readWAL && !this.readRollbackJournal) {
                    break;
                }

                if (round == 2 && this.readWAL) {
                    this.emptydb = true;
                    bb = this.readCheckpoint();
                }

                for (int index = bsearch.indexOf(bb, 0); index != -1; index = bsearch.indexOf(bb, index + 2)) {
                    byte[] mheader = new byte[40];
                    bb.position(index - goback);
                    bb.get(mheader);
                    headerStr = Auxiliary.bytesToHex(mheader);
                    if (!headerStr.startsWith("17") && !headerStr.startsWith("21")) {
                        if (headerStr.startsWith("0617")) {
                            System.out.println(" 0617 ");
                            ++this.tablematch;
                            this.readSchema(bb, index, headerStr);
                        }
                    } else {
                        System.out.println(" 17 ");
                        headerStr = "07" + headerStr;
                        ++this.tablematch;
                        this.readSchema(bb, index, headerStr);
                    }
                }

                ByteSeqSearcher bisearch = new ByteSeqSearcher(ipattern);
                int index2 = bisearch.indexOf(bb, 0);

                while (true) {
                    byte[] mheader;
                    while (true) {
                        if (index2 == -1) {
                            System.out.println("headers:::: " + this.headers.size());
                            if (this.headers.size() == 0 && this.readWAL) {
                                this.info("Could not find a schema definition inside the main db-file. Try to find something inside the WAL archive");
                                bb = this.readWALIntoBuffer(this.path + "-wal");
                                if (bb != null) {
                                    again = true;
                                }
                            }
                            continue label385;
                        }

                        mheader = new byte[40];
                        bb.position(index2 - goback);

                        try {
                            bb.get(mheader);
                            break;
                        } catch (Exception var57) {
                            index2 = -1;
                        }
                    }

                    headerStr = Auxiliary.bytesToHex(mheader);
                    System.out.println("INDEX - Search " + headerStr);
                    if (!headerStr.startsWith("17") && !headerStr.startsWith("21")) {
                        if (headerStr.startsWith("0617")) {
                            new Auxiliary(this);
                            ++this.indexmatch;
                            this.readSchema(bb, index2, headerStr);
                        }
                    } else {
                        headerStr = "07" + headerStr;
                        ++this.indexmatch;
                        this.readSchema(bb, index2, headerStr);
                    }

                    index2 = bisearch.indexOf(bb, index2 + 2);
                }
            } while (again && round < 2 && this.readWAL);

            Iterator<IndexDescriptor> itidx = this.indices.iterator();

            while (true) {
                int head;
                int start;
                Iterator it;
                while (itidx.hasNext()) {
                    IndexDescriptor id = (IndexDescriptor) itidx.next();
                    headerStr = id.tablename;
                    it = this.headers.iterator();

                    while (it.hasNext()) {
                        TableDescriptor desc = (TableDescriptor) it.next();
                        if (headerStr != null && headerStr.equals(desc.tblname)) {
                            TableDescriptor td = desc;
                            List<String> idname = id.columnnames;
                            List<String> tdnames = desc.columnnames;

                            for (head = 0; head < idname.size(); ++head) {
                                if (tdnames.contains(idname.get(head))) {
                                    try {
                                        start = tdnames.indexOf(idname.get(head));
                                        String line = td.getColumntypes().get(start);
                                        id.columntypes.add(line);
                                        System.out.println("ADDING IDX TYPE::: " + line + " FOR TABLE " + td.tblname + " INDEx " + id.idxname);
                                    } catch (Exception var56) {
                                        id.columntypes.add("");
                                    }
                                }
                            }

                            id.columnnames.add("rowid");
                            id.columntypes.add("INT");
                            Auxiliary.addHeadPattern2Idx(id);
                            this.exploreBTree(id.getRootOffset(), id, bb);
                            break;
                        }
                    }
                }

                Collections.sort(this.headers);
                Iterator<TableDescriptor> iter = this.headers.iterator();
                byte[] freepageno;
                int type;
                if (this.autovacuum) {
                    ByteBuffer pointermap = ByteBuffer.allocate(this.ps);
                    Future<Integer> operation = this.file.read(pointermap, (long) this.ps);

                    while (!operation.isDone()) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(1L);
                        } catch (InterruptedException var55) {
                            var55.printStackTrace();
                        }
                    }

                    pointermap.flip();
                    freepageno = new byte[1];

                    try {
                        pointermap.get(freepageno);
                        System.out.println("Pagetype of Pointer Map (should be 1): " + String.valueOf(freepageno));
                        pointermap.position(5);
                    } catch (Exception var54) {
                        System.out.println("Warning Error parsing pointer map");
                    }

                    type = pointermap.get();
                    switch (type) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                    }
                }

                HashSet<String> doubles = new HashSet();

                while (iter.hasNext()) {
                    TableDescriptor td = (TableDescriptor) iter.next();
                    if (td.tblname.length() > 2 && td.tblname.startsWith("\"")) {
                        td.tblname = td.tblname.substring(1, td.tblname.length() - 1);
                    }

                    if (doubles.add(td.tblname)) {
                        td.printTableDefinition();
                        int r = td.getRootOffset();
                        this.info(" root offset for component " + r);
                        String signature = td.getSignature();
                        this.info(" signature " + signature);
                        if (signature != null && signature.length() > 0) {
                            this.tblSig.put(td.getSignature(), td.tblname);
                        }

                        if (this.gui != null) {
                            Platform.runLater(() -> {
                                String path = this.gui.add_table(this, td.tblname, td.columnnames, td.getColumntypes(), td.primarykeycolumns, td.boolcolumns, false, false, 0);
                                this.guitab.put(td.tblname, path);
                                if (this.readWAL) {
                                    List<String> cnames = td.columnnames;
                                    cnames.add(0, "commit");
                                    cnames.add(1, "dbpage");
                                    cnames.add(2, "walframe");
                                    cnames.add(3, "salt1");
                                    cnames.add(4, "salt2");
                                    List<String> ctypes = td.serialtypes;

                                    for (int cc = 0; cc < 5; ++cc) {
                                    }

                                    String walpath = this.gui.add_table(this, td.tblname, cnames, ctypes, td.primarykeycolumns, td.boolcolumns, true, false, 0);
                                    this.guiwaltab.put(td.tblname, walpath);
                                    this.setWALPath(walpath.toString());
                                } else if (this.readRollbackJournal) {
                                    String rjpath = this.gui.add_table(this, td.tblname, td.columnnames, td.getColumntypes(), td.primarykeycolumns, td.boolcolumns, false, true, 0);
                                    this.guiroltab.put(td.tblname, rjpath);
                                    this.setRollbackJournalPath(rjpath.toString());
                                }

                            });
                        }

                        if (!td.isVirtual()) {
                            RecoveryTask.tables.add(td);
                            this.exploreBTree(r, td, bb);
                        }
                    }
                }

                Collections.sort(this.indices);
                it = this.indices.iterator();

                String path;
                while (it.hasNext()) {
                    IndexDescriptor id = (IndexDescriptor) it.next();
                    type = id.getRootOffset();
                    this.info(" root offset for index " + type);
                    if (this.gui != null) {
                        path = this.gui.add_table(this, id.idxname, id.columnnames, id.columntypes, id.boolcolumns, (List) null, false, false, 1);
                        System.out.println("id.idxname " + id.idxname);
                        this.guitab.put(id.idxname, path);
                        if (this.readWAL) {
                            List<String> cnames = id.columnnames;
                            cnames.add(0, "commit");
                            cnames.add(1, "dbpage");
                            cnames.add(2, "walframe");
                            cnames.add(3, "salt1");
                            cnames.add(4, "salt2");
                            List<String> ctypes = id.columntypes;
                            ctypes.add(0, "INT");
                            ctypes.add(1, "INT");
                            ctypes.add(2, "INT");
                            ctypes.add(3, "INT");
                            ctypes.add(4, "INT");
                            String walpath = this.gui.add_table(this, id.idxname, cnames, ctypes, id.boolcolumns, (List) null, true, false, 1);
                            this.guiwaltab.put(id.idxname, walpath);
                            this.setWALPath(walpath.toString());
                        } else if (this.readRollbackJournal) {
                            path = this.gui.add_table(this, id.idxname, id.columnnames, id.columntypes, id.boolcolumns, (List) null, false, true, 1);
                            this.guiroltab.put(id.idxname, path);
                            this.setRollbackJournalPath(path.toString());
                        }
                    }
                }

                if (this.gui != null) {
                    List<String> col = new ArrayList();
                    List<String> names = new ArrayList();

                    for (int i = 0; i < 20; ++i) {
                        col.add("TEXT");
                        names.add("col" + (i + 1));
                    }

                    TableDescriptor tdefault = new TableDescriptor("__UNASSIGNED", "", col, col, names, (List) null, (List) null, (HeaderPattern) null, false);
                    this.headers.add(0, tdefault);
                    if (this.gui != null) {
                        path = this.gui.add_table(this, tdefault.tblname, tdefault.columnnames, tdefault.getColumntypes(), tdefault.boolcolumns, (List) null, false, false, 0);
                        this.guitab.put(tdefault.tblname, path);
                        String rjpath;
                        if (this.readWAL) {
                            rjpath = this.gui.add_table(this, tdefault.tblname, tdefault.columnnames, tdefault.getColumntypes(), tdefault.boolcolumns, (List) null, true, false, 0);
                            this.guiwaltab.put(tdefault.tblname, rjpath);
                            this.setWALPath(rjpath.toString());
                        } else if (this.readRollbackJournal) {
                            rjpath = this.gui.add_table(this, tdefault.tblname, tdefault.columnnames, tdefault.getColumntypes(), tdefault.boolcolumns, (List) null, false, true, 0);
                            this.guiroltab.put(tdefault.tblname, rjpath);
                            this.setRollbackJournalPath(rjpath.toString());
                        }
                    }
                }

                if (this.emptydb) {
                    System.out.println("Omit analysis of the database since database file is empty ");
                    return 0;
                }

                freepageno = new byte[4];
                buffer.position(36);
                buffer.get(freepageno);
                this.info("Total number of free list (trunk) pages " + Auxiliary.bytesToHex(freepageno));
                ByteBuffer no = ByteBuffer.wrap(freepageno);
                this.fpnumber = no.getInt();
                System.out.println(" no " + this.fpnumber);
                byte[] freelistpage = new byte[4];
                buffer.position(32);
                buffer.get(freelistpage);
                this.info("FreeListPage starts at offset " + Auxiliary.bytesToHex(freelistpage));
                ByteBuffer freelistoffset = ByteBuffer.wrap(freelistpage);
                head = freelistoffset.getInt();
                this.info("head:: " + head);
                this.fphead = head;
                start = (head - 1) * this.ps;
                if (head == 0) {
                    this.info("INFO: Couldn't locate any free pages to recover. ");
                }

                if (head > 0) {
                    this.info("first:: " + start + " 0hx " + Integer.toHexString(start));
                    long startfp = System.currentTimeMillis();
                    System.out.println("Start free page recovery .....");
                    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Global.numberofThreads);
                    boolean morelistpages = false;
                    int freepagesum = 0;

                    while (true) {
                        ByteBuffer fplist = ByteBuffer.allocate(this.ps);
                        Future<Integer> operation = this.file.read(fplist, (long) start);

                        while (!operation.isDone()) {
                            try {
                                TimeUnit.MILLISECONDS.sleep(1L);
                            } catch (InterruptedException var53) {
                                var53.printStackTrace();
                            }
                        }

                        fplist.flip();
                        byte[] nextlistoffset = new byte[4];

                        try {
                            fplist.get(nextlistoffset);
                        } catch (Exception var52) {
                            System.out.println("Warning Error while parsing free list.");
                        }

                        int entries;
                        if (!Auxiliary.bytesToHex(nextlistoffset).equals("00000000")) {
                            ByteBuffer of = ByteBuffer.wrap(nextlistoffset);
                            entries = of.getInt();
                            start = (entries - 1) * this.ps;
                            if (!this.allreadyvisit.contains(entries)) {
                                this.allreadyvisit.add(entries);
                            } else {
                                this.info("Antiforensiscs found: cyclic freepage list entry");
                                morelistpages = true;
                            }
                        } else {
                            morelistpages = false;
                        }

                        byte[] numberOfEntries = new byte[4];

                        try {
                            fplist.get(numberOfEntries);
                        } catch (Exception var51) {
                        }


                        ByteBuffer e = ByteBuffer.wrap(numberOfEntries);
                        entries = e.getInt();
                        this.info(" Number of Entries in freepage list " + this.fpnumber);
                        this.runningTasks.set(0);

                        for (int zz = 1; zz <= entries; ++zz) {
                            byte[] next = new byte[4];
                            fplist.position(4 + 4 * zz);
                            fplist.get(next);
                            ByteBuffer bf = ByteBuffer.wrap(next);
                            int n = bf.getInt();
                            if (n != 0) {
                                int offset = (n - 1) * this.ps;
                                RecoveryTask task1 = new RecoveryTask(new Auxiliary(this), this, (long) offset, n, this.ps, true);
                                this.runningTasks.incrementAndGet();
                                this.tasklist.add(task1);
                                executor.execute(task1);
                            }
                        }

                        freepagesum += entries;
                        if (!morelistpages) {
                            executor.shutdown();
                            this.info("ImportDBTask total: " + this.runningTasks.intValue());

                            while (this.runningTasks.intValue() != 0) {
                                try {
                                    TimeUnit.MILLISECONDS.sleep(10L);
                                } catch (InterruptedException var50) {
                                    var50.printStackTrace();
                                }
                            }

                            this.info("Number of cells " + this.numberofcells.intValue());
                            this.info(" Finished. No further free pages. Scanned " + freepagesum);
                            long endfp = System.currentTimeMillis();
                            this.info("Duration of free page recovery in ms: " + (endfp - startfp));
                            break;
                        }
                    }
                }

                this.info("Lines after free page recovery: " + this.resultlist.size());
                this.scan(this.numberofpages, this.ps);
                if (this.gui != null) {
                    this.info("Number of tables recovered: " + this.resultlist.size());
                    System.out.println("Number of tables recovered: " + this.resultlist.size());
                    Enumeration<String> tables = this.resultlist.keys();

                    while (tables.hasMoreElements()) {
                        String tablename = (String) tables.nextElement();
                        path = (String) this.guitab.get(tablename);
                        System.out.println("update_table() in Job.java : " + path);
                        if (path != null) {
                            this.gui.update_table(path, (ObservableList) this.resultlist.get(tablename), false);
                        }
                    }
                } else {
                    String[] lines = null;
                    this.writeResultsToFile((String) null, lines);
                    if (this.readRollbackJournal) {
                        System.out.println(" RollbackJournal-File " + this.rollbackjournalpath);
                        this.rol = new RollbackJournalReader(this.rollbackjournalpath, this);
                        this.rol.ps = this.ps;
                        this.rol.parse();
                        this.rol.output();
                    } else if (this.readWAL) {
                        System.out.println(" WAL-File " + this.walpath);
                        WALReader wal = new WALReader(this.walpath, this);
                        wal.parse();
                        wal.output();
                    }

                    if (lines != null) {
                        return lines.toString().hashCode();
                    }
                }

                Platform.runLater(() -> {
                    this.db.clear();
                    this.lines.clear();
                    this.lines = null;

                    try {
                        this.file.close();
                    } catch (IOException var2) {
                        var2.printStackTrace();
                    }

                    this.tasklist.clear();
                    this.tasklist = null;
                    this.overflowpages.clear();
                    System.gc();
                    System.out.println("Garbage Collector started...");
                });
                break;
            }
        } catch (IOException var58) {
            this.info("Error: Could not open file.");
            System.exit(-1);
        }

        return 0;
    }

    private ByteBuffer readCheckpoint() {
        TreeMap<Integer, ByteBuffer> cptable = new TreeMap();
        ByteBuffer wal = null;
        ByteBuffer checkpoint = ByteBuffer.allocate(0);

        try {
            wal = this.readWALIntoBuffer(this.path + "-wal");
        } catch (IOException var12) {
            System.err.println("Could not access wal-archive");
            return null;
        }

        boolean next = false;

        int framestart = 32;

        do {
            byte[] frameheader = new byte[24];
            wal.position(framestart);
            wal.get(frameheader);
            ByteBuffer fheader = ByteBuffer.wrap(frameheader);
            int pagenumber_maindb = fheader.getInt();
            int commit = fheader.getInt();
            if (commit > 0) {
                this.info(" Information of the WAL-archive has been commited successful. ");
            } else {
                this.info(" No commit so far. this frame holds the latest! version of the page ");
            }

            ByteBuffer page = wal.slice();
            page.limit(this.ps);
            framestart += this.ps + 24;
            System.out.println("create Checkpoint() for page::" + pagenumber_maindb);
            cptable.put(pagenumber_maindb, page);
            if ((long) (framestart + 24 + this.ps) < this.size) {
                next = true;
            } else {
                next = false;
            }
        } while (next);

        Set<Integer> cppages = cptable.keySet();

        ByteBuffer pp;
        for (Iterator<Integer> it = cppages.iterator(); it.hasNext(); checkpoint = ByteBuffer.allocate(checkpoint.limit() + pp.limit()).put(checkpoint).put(pp).rewind()) {
            pp = (ByteBuffer) cptable.get(it.next());
        }

        this.pages = new AbstractDescriptor[cptable.size() + 1];
        return checkpoint;
    }

    public boolean exportResults2File(File f, String tablename) {
        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                label412:
                {
                    BufferedWriter writer = Files.newBufferedWriter(f.toPath(), Charset.forName("UTF-8"), StandardOpenOption.CREATE);

                    try {
                        System.out.println("tablename " + tablename);
                        int var14;
                        int from;
                        if (this.resultlist.get(tablename) == null) {
                            Enumeration<String> keyset = this.resultlist.keys();

                            while (true) {
                                if (!keyset.hasMoreElements()) {
                                    break label412;
                                }

                                ObservableList<LinkedList<String>> table = (ObservableList) this.resultlist.get(keyset.nextElement());

                                boolean var43;
                                for (Iterator<LinkedList<String>> iter = table.iterator(); iter.hasNext(); var43 = false) {
                                    LinkedList<String> list = (LinkedList) iter.next();
                                    String[] line = (String[]) list.toArray(new String[0]);
                                    String output = "";
                                    int counter = 0;
                                    String[] var46 = line;
                                    int var45 = line.length;

                                    for (var14 = 0; var14 < var45; ++var14) {
                                        String c = var46[var14];
                                        if (c.startsWith("[BLOB")) {
                                            Iterator<TableDescriptor> td = this.headers.iterator();

                                            while (td.hasNext()) {
                                                TableDescriptor tbl = (TableDescriptor) td.next();
                                                if (tbl.tblname.equals(line[0]) && counter >= 5) {
                                                    from = c.indexOf("BLOB-");
                                                    int to = c.indexOf("] ");
                                                    String number = c.substring(from + 5, to);
                                                    System.out.println("BLOB >>>>" + c);
                                                    System.out.println(Arrays.toString(line));
                                                    int id = Integer.parseInt(number);
                                                    Long hash = Long.parseLong(line[4]) + (long) id;
                                                    System.out.println("filecache hash" + String.valueOf(hash));
                                                    String path = (String) this.FileCache.get(hash);
                                                    System.out.println("pfad : " + path);
                                                    Path pp = Paths.get(path);
                                                    c = pp.getFileName().toString();
                                                    break;
                                                }
                                            }
                                        }

                                        ++counter;
                                        output = output + c + ",";
                                    }

                                    writer.write(output + "\n");
                                }
                            }
                        }

                        ObservableList<LinkedList<String>> table = (ObservableList) this.resultlist.get(tablename);
                        if (table != null) {
                            Iterator<LinkedList<String>> iter = table.iterator();

                            while (true) {
                                if (!iter.hasNext()) {
                                    break label412;
                                }

                                LinkedList<String> list = (LinkedList) iter.next();
                                String[] line = (String[]) list.toArray(new String[0]);
                                String output = "";
                                int counter = 0;
                                String[] var15 = line;
                                var14 = line.length;

                                for (int var13 = 0; var13 < var14; ++var13) {
                                    String c = var15[var13];
                                    if (c.startsWith("[BLOB-")) {
                                        Iterator<TableDescriptor> td = this.headers.iterator();

                                        while (td.hasNext()) {
                                            TableDescriptor tbl = (TableDescriptor) td.next();
                                            if (tbl.tblname.equals(line[0]) && counter >= 5) {
                                                 from = c.indexOf("BLOB-");
                                                from = c.indexOf("]");
                                                String number = c.substring(from + 5, from);
                                                System.out.println("BLOB >>>>" + c);
                                                System.out.println(Arrays.toString(line));
                                                int id = Integer.parseInt(number);
                                                Long hash = Long.parseLong(line[4]) + (long) id;
                                                System.out.println("HASH :: " + String.valueOf(hash));
                                                String path = (String) this.FileCache.get(hash);
                                                Path pp = Paths.get(path);
                                                c = pp.getFileName().toString();
                                                break;
                                            }
                                        }
                                    }

                                    ++counter;
                                    output = output + c + ",";
                                }


                                writer.write(output + "\n");
                            }
                        }
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }

                    }

                    return false;
                }
            } catch (Throwable var33) {
                if (var3 == null) {
                    var3 = var33;
                } else if (var3 != var33) {
                    var3.addSuppressed(var33);
                }

                throw var3;
            }
        } catch (Throwable var34) {
            var34.printStackTrace();
            return false;
        }

        if (this.FileCache != null && this.FileCache.size() > 0) {
            System.out.println("§§§§ " + f.getParentFile().getAbsolutePath());
            this.exportBLOB2Disk(f.getParentFile().getAbsolutePath());
        }

        return true;
    }

    private void exportBLOB2Disk(String target) {
        Set<String> files = this.FileCache.keySet();
        files.forEach((f) -> {
            try {
                String path = (String) this.FileCache.get(f);
                Path source = Paths.get(path);
                String fname = source.getFileName().toString();
                Path ftarget = Paths.get(target + File.separator + fname);
                Files.copy(source, ftarget, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        });
    }

    public void writeResultsToFile(String filename, String[] lines) {
        System.out.println("Write results to file...");
        System.out.println("Number of records recovered: " + this.resultlist.size());
        LocalDateTime now;
        String line;
        if (filename == null) {
            Path dbfilename = Paths.get(this.path);
            String name = dbfilename.getFileName().toString();
            now = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
            line = df.format(now);
            line = line.replace(":", "_");
            filename = "results" + name + line + ".csv";
        }

        try {
            File file = new File(filename);
            Throwable var21 = null;
            now = null;

            try {
                BufferedWriter writer = Files.newBufferedWriter(file.toPath(), Charset.forName("UTF-8"), StandardOpenOption.CREATE);

                try {
                    String[] var10 = lines;
                    int var9 = lines.length;

                    for (int var8 = 0; var8 < var9; ++var8) {
                        line = var10[var8];
                        writer.write(line + "\n");
                    }
                } finally {
                    if (writer != null) {
                        writer.close();
                    }

                }
            } catch (Throwable var18) {
                if (var21 == null) {
                    var21 = var18;
                } else if (var21 != var18) {
                    var21.addSuppressed(var18);
                }

                throw var21;
            }
        } catch (Throwable var19) {
            var19.printStackTrace();
        }

    }

    private void readSchema(ByteBuffer bb, int index, String headerStr) throws IOException {
        System.out.println("Entering readSchema()");
        Auxiliary c = new Auxiliary(this);
        if (db_encoding == StandardCharsets.UTF_8) {
            c.readMasterTableRecord(this, index - 13, bb, headerStr);
        } else if (db_encoding == StandardCharsets.UTF_16LE) {
            c.readMasterTableRecord(this, index - 17, bb, headerStr);
        } else if (db_encoding == StandardCharsets.UTF_16BE) {
            c.readMasterTableRecord(this, index - 18, bb, headerStr);
        }

        System.out.println("Leave readSchema()");
    }

    public static String decode(ByteBuffer tblb) {
        try {
            byte[] m = tblb.array();
            String val = new String(m, StandardCharsets.UTF_8);
            byte[] n = val.getBytes(StandardCharsets.UTF_8);
            byte[] cs = new byte[n.length];
            int xx = 0;
            byte[] var9 = n;
            int var8 = n.length;

            for (int var7 = 0; var7 < var8; ++var7) {
                byte e = var9[var7];
                if (e != 0) {
                    cs[xx] = e;
                    ++xx;
                }
            }

            String schemastr = new String(cs);
            return schemastr;
        } catch (Exception var10) {
            System.out.println(var10);
            return "";
        }
    }

    public void scan(int number, int ps) {
        this.info("Start with scan...");
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Global.numberofThreads);
        long begin = System.currentTimeMillis();
        Worker[] worker = new Worker[Global.numberofThreads];

        int cc;
        for (cc = 0; cc < Global.numberofThreads; ++cc) {
            worker[cc] = new Worker(this);
        }

        long ende;
        for (cc = 1; cc < this.pages.length; ++cc) {
            if (this.pages[cc] == null) {
                this.debug("page " + cc + " is no regular leaf page component. Maybe a indices or overflow or dropped component page.");
            } else {
                this.debug("page" + cc + " is a regular leaf page. ");
                ende = (long) ((cc - 1) * ps);
                RecoveryTask task = new RecoveryTask(worker[cc % Global.numberofThreads].util, this, ende, cc, ps, false);
                worker[cc % Global.numberofThreads].addTask(task);
                this.runningTasks.incrementAndGet();
            }
        }

        int var10001 = this.runningTasks.intValue();
        this.debug("ImportDBTask total: " + var10001 + " worker threads " + Global.numberofThreads);
        cc = 1;
        Worker[] var11 = worker;
        int var21 = worker.length;

        int i;
        for (i = 0; i < var21; ++i) {
            Worker w = var11[i];
            var10001 = cc++;
            System.out.println(" Start worker thread" + var10001);
            executor.execute(w);
        }

        try {
            executor.shutdown();
            i = 0;

            int remaining;
            do {
                remaining = this.runningTasks.intValue();
                ++i;
                Thread.currentThread();
                Thread.sleep(100L);
            } while (i < 50 && remaining > 0);

            executor.awaitTermination(2L, TimeUnit.SECONDS);
        } catch (InterruptedException var16) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }

            executor.shutdownNow();
            System.out.println("shutdown finished");
        }

        while (this.runningTasks.intValue() != 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000L);
                System.out.println("warte....");
            } catch (InterruptedException var15) {
                var15.printStackTrace();
            }
        }

        ende = System.currentTimeMillis();
        this.info("Duration of scanning all pages in ms : " + (ende - begin));
        this.info("End of Scan...");
        executor = null;
    }

    protected void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWALPath(String path) {
        this.walpath = path;
    }

    public void setRollbackJournalPath(String path) {
        this.rollbackjournalpath = path;
    }

    public void start() {
        if (this.path != null) {
            try {
                this.run(this.path);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    public void info(String message) {
        if (this.gui != null) {
            this.gui.doLog(message);
        } else {
            System.out.println(message);
        }

    }

    public void err(String message) {
        if (this.gui != null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText(message);
            alert.showAndWait();
        } else {
            System.err.println("ERROR: " + message);
        }

    }

    public Job() {
        Base.LOGLEVEL = Global.LOGLEVEL;
    }

    public int run(String p) {
        int hashcode = -1;
        this.path = p;
        long start = System.currentTimeMillis();

        try {
            hashcode = this.processDB();
        } catch (ExecutionException | InterruptedException var7) {
            var7.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Duration in ms: " + (end - start));
        return hashcode;
    }

    public ByteBuffer readPageWithOffset(long offset, int pagesize) {
        if (offset <= (long) this.db.limit() && offset >= 0L) {
            this.db.position((int) offset);
            ByteBuffer page = this.db.slice();
            if (page.capacity() == 0) {
                System.out.println(" Achtung!!!! leere Seite");
                return null;
            } else {
                page.limit(pagesize);
                return page;
            }
        } else {
            System.out.println(" offset greater than file size ?!" + offset + " > " + this.db.limit());
            return null;
        }
    }

    public ByteBuffer readPageWithNumber(int pagenumber, int pagesize) {
        return pagenumber < 0 ? null : this.readPageWithOffset((long) (pagenumber * pagesize), pagesize);
    }

    public ByteBuffer readPageWithNumberFromBuffer(int pagenumber, int pagesize, ByteBuffer bb) {
        if (pagenumber < 0) {
            return null;
        } else {
            int offset = pagenumber * pagesize;
            if (offset <= this.db.limit() && offset >= 0) {
                this.db.position(offset);
                ByteBuffer page = this.db.slice();
                page.limit(pagesize);
                return page;
            } else {
                System.out.println(" offset greater than file size ?!" + offset + " > " + this.db.limit());
                return null;
            }
        }
    }

    private void exploreBTree(int root, AbstractDescriptor td, ByteBuffer filebuffer) throws IOException {
        if (root < this.pages.length) {
            this.pages[root] = td;
            int offset = this.ps * (root - 1);
            if (offset > 0) {
                if (root >= 0) {
                    filebuffer.position(offset);
                    byte typ = filebuffer.get();
                    if (typ == 2) {
                        this.debug(" page number" + root + " is a  INDEXINTERIORPAGE.");
                    } else if (typ == 5) {
                        this.debug("page number " + root + " is a interior data page ");
                        int rightChildptr = filebuffer.getInt(offset + 8);
                        this.exploreBTree(rightChildptr, td, filebuffer);
                        ByteBuffer buffer = this.readPageWithNumber(root - 1, this.ps);
                        byte[] numberofcells = new byte[2];
                        buffer.position(3);
                        buffer.get(numberofcells);
                        ByteBuffer noc = ByteBuffer.wrap(numberofcells);
                        int e = Auxiliary.TwoByteBuffertoInt(noc);
                        byte[] cpn = new byte[2];
                        buffer.position(5);
                        buffer.get(cpn);
                        ByteBuffer size = ByteBuffer.wrap(cpn);
                        int cp = Auxiliary.TwoByteBuffertoInt(size);

                        for (int i = 0; i < e; ++i) {
                            byte[] pointer = new byte[2];
                            buffer.position(12 + 2 * i);
                            if (buffer.capacity() > buffer.position() + 2) {
                                buffer.get(pointer);
                                ByteBuffer celladdr = ByteBuffer.wrap(pointer);
                                int celloff = Auxiliary.TwoByteBuffertoInt(celladdr);
                                this.debug(" celloff " + celloff);
                                byte[] pnext = new byte[4];
                                if (celloff < buffer.capacity() && celloff >= 0 && celloff <= this.ps) {
                                    buffer.position(celloff);
                                    buffer.get(pnext);
                                    int p = ByteBuffer.wrap(pnext).getInt();
                                    this.debug(" child page " + p);
                                    this.exploreBTree(p, td, filebuffer);
                                }
                            }
                        }
                    } else if (typ != 8 && typ != 10) {
                        this.debug("Page" + root + " is neither a leaf page nor a internal page. Try to set component to " + td.getName());
                        if (root > this.numberofpages) {
                            return;
                        }

                        if (this.pages[root] == null) {
                            this.pages[root] = td;
                        }
                    } else {
                        this.debug("page number " + root + " is a leaf page  set component/index to " + td.getName());
                        if (root > this.numberofpages) {
                            return;
                        }

                        if (this.pages[root] == null) {
                            this.pages[root] = td;
                        } else {
                            this.debug("WARNING page is member in two B+Trees! Possible Antiforensics.");
                        }
                    }

                }
            }
        }
    }

    public String[] getHeaderString(String tablename) {
        Iterator<TableDescriptor> iter = this.headers.iterator();

        while (iter.hasNext()) {
            TableDescriptor td = (TableDescriptor) iter.next();
            if (td.tblname.equals(tablename)) {
                return (String[]) td.columnnames.toArray(new String[0]);
            }
        }

        Iterator<IndexDescriptor> itI = this.indices.iterator();

        while (itI.hasNext()) {
            IndexDescriptor id = (IndexDescriptor) itI.next();
            if (id.idxname.equals(tablename)) {
                return (String[]) id.columnnames.toArray(new String[0]);
            }
        }

        return null;
    }
}
