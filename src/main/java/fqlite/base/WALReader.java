//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.descriptor.AbstractDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.pattern.SerialTypeMatcher;
import fqlite.util.Auxiliary;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WALReader extends Base {
    TreeMap<Long, LinkedList<WALFrame>> checkpoints = new TreeMap();
    public AsynchronousFileChannel file;
    ByteBuffer wal;
    long size;
    int ffversion;
    int ps;
    int csn;
    long hsalt1;
    long hsalt2;
    long hchecksum1;
    long hchecksum2;
    String path;
    BitSet visit = null;
    boolean withoutROWID = false;
    Job job;
    int pagenumber_maindb;
    int pagenumber_wal;
    int framestart = 0;
    public String headerstring = "";
    private Auxiliary ct;
    private StringBuffer firstcol = new StringBuffer();
    private static final String MAGIC_HEADER_STRING1 = "377f0682";
    private static final String MAGIC_HEADER_STRING2 = "377f0683";
    ByteBuffer buffer;
    public static List<TableDescriptor> tables = new LinkedList();
    ConcurrentLinkedQueue<LinkedList<String>> output = new ConcurrentLinkedQueue();

    public WALReader(String path, Job job) {
        this.path = path;
        this.job = job;
        this.ct = new Auxiliary(job);
    }

    public void parse() {
        int framenumber = 0;
        Path p = Paths.get(this.path);
        System.out.println("parse WAL-File");

        try {
            this.file = AsynchronousFileChannel.open(p, StandardOpenOption.READ);
        } catch (Exception var17) {
            this.err("Cannot open WAL-file" + String.valueOf(p.getFileName()));
            return;
        }

        try {
            this.readFileIntoBuffer();
        } catch (IOException var16) {
            var16.printStackTrace();
        }

        this.buffer = ByteBuffer.allocate(32);
        Future<Integer> result = this.file.read(this.buffer, 0L);

        while(!result.isDone()) {
        }

        this.buffer.flip();

        try {
            if (this.file.size() <= 32L) {
                this.info("WAL-File is empty. Skip analyzing.");
                return;
            }
        } catch (IOException var15) {
            var15.printStackTrace();
        }

        byte[] header = new byte[4];
        this.buffer.get(header);
        if (Auxiliary.bytesToHex(header).equals("377f0682")) {
            this.headerstring = "377f0682";
            this.info("header is okay. seems to be an write ahead log file.");
        } else if (Auxiliary.bytesToHex(header).equals("377f0683")) {
            this.headerstring = "377f0683";
            this.info("header is okay. seems to be an write ahead log file.");
        } else {
            this.info("sorry. doesn't seem to be an WAL file. Wrong header.");
            this.err("Doesn't seem to be an valid WAL file. Wrong header");
        }

        this.buffer.position(4);
        this.ffversion = this.buffer.getInt();
        this.info(" file format version " + this.ffversion);
        this.ps = this.buffer.getInt();
        if (this.ps == 0 || this.ps == 1) {
            this.ps = 65536;
        }

        this.info("page size " + this.ps + " Bytes ");
        this.csn = this.buffer.getInt();
        this.info(" checkpoint sequence number " + this.csn);
        this.hsalt1 = Integer.toUnsignedLong(this.buffer.getInt());
        this.info(" salt1 " + this.hsalt1);
        this.hsalt2 = Integer.toUnsignedLong(this.buffer.getInt());
        this.info(" salt2 " + this.hsalt2);
        this.hchecksum1 = Integer.toUnsignedLong(this.buffer.getInt());
        this.info(" checksum-1 of first frame header " + this.hchecksum1);
        this.hchecksum2 = Integer.toUnsignedLong(this.buffer.getInt());
        this.info(" checksum-2 second part ot the checksum on the first frame header " + this.hchecksum2);
        this.visit = new BitSet(this.ps);
        this.framestart = 32;
        boolean next = false;
        int numberofpages = 0;

        do {
            byte[] frameheader = new byte[24];
            this.wal.position(this.framestart);
            this.wal.get(frameheader);
            ByteBuffer fheader = ByteBuffer.wrap(frameheader);
            this.pagenumber_maindb = fheader.getInt();
            int commit = fheader.getInt();
            if (commit > 0) {
                this.info(" Information of the WAL-archive has been commited successful. ");
            } else {
                this.info(" No commit so far. this frame holds the latest! version of the page ");
            }

            long fsalt1 = Integer.toUnsignedLong(fheader.getInt());
            this.info("fsalt1 " + fsalt1);
            long fsalt2 = Integer.toUnsignedLong(fheader.getInt());
            this.info("fsalt2" + fsalt2);
            if (this.hsalt1 == fsalt1 && this.hsalt2 == fsalt2) {
                this.info("seems to be an valid frame. Condition 1 is true at least. ");
            }

            int var10001 = this.pagenumber_maindb;
            this.debug("pagenumber of frame in main db " + var10001);
            var10001 = this.pagenumber_maindb;
            System.out.println("pagenumber of frame in main db " + var10001);
            this.buffer = this.readPage();
            ++numberofpages;
            this.pagenumber_wal = numberofpages;
            WALFrame frame = this.updateCheckpoint(this.pagenumber_maindb, framenumber, fsalt1, fsalt2, commit != 0);
            this.analyzePage(frame);
            this.framestart += this.ps + 24;
            if ((long)(this.framestart + 24 + this.ps) < this.size) {
                next = true;
            } else {
                next = false;
            }

            ++framenumber;
        } while(next);

        this.info("Lines after WAL-file recovery: " + this.output.size());
        this.info("Number of pages in WAL-file" + numberofpages);
        this.info("Checkpoints " + this.checkpoints.toString());
    }

    private WALFrame updateCheckpoint(int pagenumber, int framenumber, long salt1, long salt2, boolean committed) {
        WALFrame f = new WALFrame(pagenumber, framenumber, salt1, salt2, committed);
        LinkedList trx;
        if (!this.checkpoints.containsKey(salt1)) {
            trx = new LinkedList();
            trx.add(f);
            this.checkpoints.put(salt1, trx);
        } else {
            trx = (LinkedList)this.checkpoints.get(salt1);
            trx.add(f);
        }

        return f;
    }

    public int analyzePage(WALFrame frame) {
        this.withoutROWID = false;
        String content = Auxiliary.bytesToHex(this.buffer);
        this.buffer.position(0);
        int type = Auxiliary.getPageType(content);
        this.visit.set(0, 2);
        if (type == 0) {
            this.buffer.position(0);
            Integer checksum = this.buffer.getInt();
            if (checksum == 0) {
                System.out.println(" DROPPED PAGE !!!");
                this.carve(content, (Carver)null);
            }

            return 0;
        } else if (type < 0) {
            this.info("No Data page. " + this.pagenumber_wal);
            return -1;
        } else if (type == 5) {
            this.info("Internal Table page " + this.pagenumber_wal);
            return -1;
        } else {
            if (type == 10) {
                this.info("Index leaf page " + this.pagenumber_wal);
                this.withoutROWID = true;
            } else {
                int var10001 = this.pagenumber_wal;
                this.info("Data page " + var10001 + " Offset: " + this.wal.position());
            }

            if (type == 8) {
                byte[] fboffset = new byte[2];
                this.buffer.position(1);
                this.buffer.get(fboffset);
            }

            int ccrstart = this.job.ps;
            byte[] cpn = new byte[2];
            this.buffer.position(3);
            this.buffer.get(cpn);
            byte[] ccr = new byte[2];
            this.buffer.position(5);
            ByteBuffer contentregionstart = ByteBuffer.wrap(ccr);
            ccrstart = Auxiliary.TwoByteBuffertoInt(contentregionstart);
            this.visit.set(2, 8);
            ByteBuffer size = ByteBuffer.wrap(cpn);
            int cp = Auxiliary.TwoByteBuffertoInt(size);
            this.debug(" number of cells: " + cp + " type of page " + type);
            this.job.numberofcells.addAndGet(cp);
            if (cp == 0) {
                this.debug(" Page seems to be dropped. No cell entries.");
            }

            int headerend = 8 + cp * 2;
            this.visit.set(0, headerend);
            int last = 0;

            ByteBuffer ignore;
            String secondcol;
            for(int i = 0; i < cp; ++i) {
                byte[] pointer = new byte[2];
                if (type == 5) {
                    this.buffer.position(12 + 2 * i);
                } else {
                    this.buffer.position(8 + 2 * i);
                }

                this.buffer.get(pointer);
                ignore = ByteBuffer.wrap(pointer);
                int celloff = Auxiliary.TwoByteBuffertoInt(ignore);
                if (last <= 0 || celloff != last) {
                    last = celloff;
                    secondcol = Auxiliary.Int2Hex(celloff);
                    secondcol.trim();
                    LinkedList<String> rc = null;

                    try {
                        rc = this.ct.readRecord(celloff, this.buffer, this.pagenumber_maindb, this.visit, type, Integer.MAX_VALUE, this.firstcol, this.withoutROWID, 2, (long)(this.framestart + 24 + celloff));
                        if (rc == null) {
                            continue;
                        }
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }

                    rc.add(5, "" + frame.salt2);
                    rc.add(5, "" + frame.salt1);
                    rc.add(5, "" + frame.framenumber);
                    rc.add(5, "" + this.pagenumber_maindb);
                    rc.add(5, "" + frame.committed);
                    if (rc != null && rc.size() > 0) {
                        int p1 = rc.indexOf("_node;");
                        this.output.add(rc);
                    }
                }
            }

            this.debug("finished STEP2 -> cellpoint array completed");

            try {
                this.buffer.position(headerend);
                byte[] garbage = new byte[2];


                int garbageoffset;
                do {
                    this.buffer.get(garbage);
                    ignore = ByteBuffer.wrap(garbage);
                    garbageoffset = Auxiliary.TwoByteBuffertoInt(ignore);
                } while(this.buffer.position() < this.ps && garbageoffset > 0);

                for(byte zerob = 0; this.buffer.position() < this.ps && zerob == 0; zerob = this.buffer.get()) {
                }

                this.visit.set(headerend, this.buffer.position());
                this.buffer.position(this.buffer.position() - 1);
                if (ccrstart - this.buffer.position() > 3) {
                    LinkedList<String> rc = this.ct.readRecord(this.buffer.position(), this.buffer, this.ps, this.visit, type, ccrstart - this.buffer.position(), this.firstcol, this.withoutROWID, 2, (long)(this.framestart + 24 + this.buffer.position()));
                    if (rc != null) {
                        secondcol = (String)rc.get(1);
                        secondcol = "D" + secondcol;
                        rc.set(1, secondcol);
                        this.updateResultSet(rc);
                    }
                }

                this.carve(content, (Carver)null);
                return 0;
            } catch (Exception var19) {
                var19.printStackTrace();
                return -1;
            }
        }
    }

    private void updateResultSet(LinkedList<String> line) {
        ObservableList tablelist;
        if (this.job.resultlist.containsKey(line.getFirst())) {
            tablelist = (ObservableList)this.job.resultlist.get(line.getFirst());
            tablelist.add(line);
        } else {
            tablelist = FXCollections.observableArrayList();
            tablelist.add(line);
            this.job.resultlist.put((String)line.getFirst(), tablelist);
        }

    }

    static boolean allCharactersZero(String s) {
        if (!s.startsWith("0000")) {
            return false;
        } else {
            int n = s.length();

            for(int i = 1; i < n; ++i) {
                if (s.charAt(i) != s.charAt(0)) {
                    return false;
                }
            }

            return true;
        }
    }

    private void readFileIntoBuffer() throws IOException {
        this.size = this.file.size();
        this.wal = ByteBuffer.allocateDirect((int)this.size);
        Future<Integer> result = this.file.read(this.wal, 0L);

        while(!result.isDone()) {
        }

        this.wal.position(0);
    }

    protected ByteBuffer readPage() {
        ByteBuffer page = this.wal.slice();
        page.limit(this.ps);
        return page;
    }

    public void carve(ByteBuffer buffer, String content, Carver crv) {
        Carver c = crv;
        if (crv == null) {
            c = new Carver(this.job, buffer, content, this.visit, this.ps);
        }

        TableDescriptor tdesc = null;
        if (this.job.pages.length > this.ps) {
            AbstractDescriptor ad = this.job.pages[this.ps];
            if (ad instanceof TableDescriptor) {
                tdesc = (TableDescriptor)ad;
            }
        }

        List<TableDescriptor> tab = tables;
        this.debug(" tables :: " + tables.size());

        if (tdesc != null) {
            tab = new LinkedList();
            ((List)tab).add(tdesc);
            this.debug(" added tdsec ");
        } else {
            this.warning(" No component description!" + content);
            tab = tables;
        }

        LinkedList<Gap> gaps = this.findGaps();
        if (gaps.size() == 0) {
            this.debug("no gaps anymore. Stopp search");
        } else {
            for(int n = 0; n < ((List)tab).size(); ++n) {
                tdesc = (TableDescriptor)((List)tab).get(n);
                int var10001 = this.pagenumber_maindb;
                this.debug("pagenumber :: " + var10001 + " component size :: " + ((List)tab).size());
                this.debug("n " + n);
                String tablename = ((TableDescriptor)((List)tab).get(n)).tblname;
                this.debug("WALReader Check component : " + tablename);
                if (!tablename.startsWith("__UNASSIGNED")) {
                    SerialTypeMatcher stm = new SerialTypeMatcher(buffer);
                    gaps = this.findGaps();

                    int a;
                    Gap next;
                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (next.to - next.from > 10 && c.carve(next.from + 4, next.to, stm, 0, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP NORMAL finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (c.carve(next.from + 4, next.to, stm, 1, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP COLUMNSONLY finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (c.carve(next.from + 4, next.to, stm, 2, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP FIRSTCOLUMNMISSING finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        c.carve(next.from + 4 + 1, next.to, stm, 2, (TableDescriptor)((List)tab).get(n));
                    }
                }
            }

            this.debug("End of WALReader:parse()");
        }
    }

    public void output() {
        String walpath;
        if (this.job.gui != null) {
            System.out.println("Number of WAL records recovered: " + this.output.size());
            Iterator<LinkedList<String>> lines = this.output.iterator();
            Hashtable<String, ObservableList<LinkedList<String>>> dataSets = new Hashtable();

            while(lines.hasNext()) {
                LinkedList<String> line = (LinkedList)lines.next();
                String[] rec = (String[])line.toArray(new String[0]);
                if (((String)line.getFirst()).trim().length() == 0) {
                    line.set(0, "__UNASSIGNED");
                    line.add(3, "D");
                }

                ObservableList tablelist;
                if (dataSets.containsKey(line.get(0))) {
                    tablelist = (ObservableList)dataSets.get(line.getFirst());
                    tablelist.add(line);
                } else {
                    tablelist = FXCollections.observableArrayList();
                    tablelist.add(line);
                    dataSets.put((String)line.getFirst(), tablelist);
                }
            }

            Enumeration<String> tables = dataSets.keys();

            while(tables.hasMoreElements()) {
                String tablename = (String)tables.nextElement();
                if (!tablename.startsWith("null#")) {
                    walpath = (String)this.job.guiwaltab.get(tablename);
                    if (walpath != null) {
                        System.out.println("WALReader:: called update_table for " + tablename + " path  :: " + walpath);
                        this.job.gui.update_table(walpath, (ObservableList)dataSets.get(tablename), true);
                    }
                }
            }
        } else {
            Path dbfilename = Paths.get(this.path);
            String name = dbfilename.getFileName().toString();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
            walpath = df.format(now);
            String filename = "results" + name + walpath + ".csv";
            String[] lines = (String[])this.output.toArray(new String[0]);
            this.job.writeResultsToFile(filename, lines);
        }

    }

    public LinkedList<Gap> findGaps() {
        LinkedList<Gap> gaps = new LinkedList();


        for(int i = 0; i < this.ps; ++i) {
            if (!this.visit.get(i)) {
                int from = i;
                int to = i;

                while(true) {
                    ++i;
                    if (this.visit.get(i) || i >= this.ps - 1) {
                        if (to - from > 10) {
                            boolean isNull = false;
                            if (this.buffer.get(from) == 0) {
                                isNull = true;

                                for(int index = from; index < to; ++index) {
                                    if (this.buffer.get(index) != 0) {
                                        isNull = false;
                                    }
                                }
                            }

                            if (isNull) {
                                this.visit.set(from, to);
                            } else {
                                gaps.add(new Gap(from, to));
                            }
                        }
                        break;
                    }

                    ++to;
                }
            }
        }

        return gaps;
    }

    public void carve(String content, Carver crv) {
        Carver c = crv;
        if (crv == null) {
            c = new Carver(this.job, this.buffer, content, this.visit, this.pagenumber_maindb);
        }

        TableDescriptor tdesc = null;
        if (this.job.pages.length > this.pagenumber_maindb) {
            AbstractDescriptor ad = this.job.pages[this.pagenumber_maindb];
            if (ad instanceof TableDescriptor) {
                tdesc = (TableDescriptor)ad;
            }
        }

        List<TableDescriptor> tab = tables;
        this.debug(" tables :: " + tables.size());

        if (tdesc != null) {
            tab = new LinkedList();
            ((List)tab).add(tdesc);
            this.debug(" added tdsec ");
        } else {
            this.warning(" No component description!");
            tab = tables;
        }

        LinkedList<Gap> gaps = this.findGaps();
        if (gaps.size() == 0) {
            this.debug("no gaps anymore. Stopp search");
        } else {
            for(int n = 0; n < ((List)tab).size(); ++n) {
                tdesc = (TableDescriptor)((List)tab).get(n);
                int var10001 = this.pagenumber_maindb;
                this.debug("pagenumber :: " + var10001 + " component size :: " + ((List)tab).size());
                this.debug("n " + n);
                String tablename = ((TableDescriptor)((List)tab).get(n)).tblname;
                this.debug("Check component : " + tablename);
                if (!tablename.startsWith("__UNASSIGNED")) {
                    SerialTypeMatcher stm = new SerialTypeMatcher(this.buffer);
                    gaps = this.findGaps();

                    int a;
                    Gap next;
                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (next.to - next.from > 10 && c.carve(next.from + 4, next.to, stm, 0, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP NORMAL finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (c.carve(next.from + 4, next.to, stm, 1, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP COLUMNSONLY finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(a = 0; a < gaps.size(); ++a) {
                        next = (Gap)gaps.get(a);
                        if (c.carve(next.from + 4, next.to, stm, 2, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP FIRSTCOLUMNMISSING finished with matches");
                        }
                    }
                }
            }

        }
    }

    class WALFrame {
        int pagenumber;
        int framenumber;
        long salt1;
        long salt2;
        boolean committed = false;

        public String toString() {
            return "{pagenumber=" + this.pagenumber + " framenumber=" + this.framenumber + " committed=" + this.committed + "}";
        }

        public WALFrame(int pagenumber, int framenumber, long salt1, long salt2, boolean committed) {
            this.salt1 = salt1;
            this.salt2 = salt2;
            this.pagenumber = pagenumber;
            this.framenumber = framenumber;
            this.committed = committed;
        }
    }
}
