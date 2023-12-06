//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.descriptor.AbstractDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.pattern.SerialTypeMatcher;
import fqlite.util.Auxiliary;
import fqlite.util.Logger;
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
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RollbackJournalReader extends Base {
    public static final String MAGIC_HEADER_STRING = "d9d505f920a163d7";
    public AsynchronousFileChannel file;
    ByteBuffer rollbackjournal;
    long size;
    public int ps;
    String path;
    BitSet visit = null;
    Job job;
    int pagenumber_rol;
    int pagenumber_maindb;
    long pagecount;
    long nounce;
    long pages;
    long sectorsize;
    long journalpagesize;
    boolean withoutROWID = false;
    private Auxiliary ct;
    private StringBuffer firstcol = new StringBuffer();
    ByteBuffer buffer;
    public static List<TableDescriptor> tables = new LinkedList();
    ConcurrentLinkedQueue<LinkedList<String>> output = new ConcurrentLinkedQueue();
    int journalpointer = 0;

    public RollbackJournalReader(String path, Job job) {
        this.path = path;
        this.job = job;
        this.ct = new Auxiliary(job);
    }

    public void parse() {
        Path p = Paths.get(this.path);

        try {
            this.file = AsynchronousFileChannel.open(p, StandardOpenOption.READ);
        } catch (Exception var10) {
            this.err("Cannot open RollbackJournal-file" + String.valueOf(p.getFileName()));
            return;
        }

        try {
            this.readFileIntoBuffer();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        try {
            if (this.file.size() <= 512L) {
                System.out.println("RollbackJournal-File is empty. Skip analyzing.");
                return;
            }
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        ByteBuffer header = ByteBuffer.allocate(28);
        Future<Integer> result = this.file.read(header, 0L);

        while(!result.isDone()) {
        }

        header.flip();
        byte[] head = new byte[8];
        header.get(head);
        if (Auxiliary.bytesToHex(head).equals("d9d505f920a163d7")) {
            this.info("header is okay. seems to be an rollback journal file.");
        } else {
            this.info("This file does not contain a valid header.");
        }

        this.pagecount = Integer.toUnsignedLong(header.getInt());
        this.info(" pagecount " + this.pagecount);
        this.nounce = Integer.toUnsignedLong(header.getInt());
        this.info(" nounce " + this.nounce);
        this.pages = Integer.toUnsignedLong(header.getInt());
        this.info(" pages " + this.pages);
        this.sectorsize = Integer.toUnsignedLong(header.getInt());
        this.info(" sector size  " + this.sectorsize);
        this.journalpagesize = Integer.toUnsignedLong(header.getInt());
        this.info(" journal page size  " + this.journalpagesize);
        this.journalpointer = 512;
        this.visit = new BitSet(this.ps);
        boolean next = false;
        int numberofpages = 0;

        do {
            this.rollbackjournal.position(this.journalpointer);
            this.pagenumber_maindb = this.rollbackjournal.getInt();
            this.debug("pagenumber of journal-entry " + this.pagenumber_maindb);
            int pageoffset = this.rollbackjournal.position();
            this.buffer = this.readPage();
            ++numberofpages;
            this.pagenumber_rol = numberofpages;
            this.analyzePage(this.pagenumber_maindb, pageoffset);
            this.journalpointer += 4 + this.ps + 4;
            if ((long)(this.journalpointer + this.ps) <= this.size) {
                next = true;
            } else {
                next = false;
            }
        } while(next);

        this.info("Lines after RollbackJournal-file recovery: " + this.output.size());
        this.info("Number of pages in RollbackJournal-file" + numberofpages);
    }

    public int analyzePage(int originalpagenumber, int pageoffset) {
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
            this.info("No Data page. " + this.pagenumber_rol);
            return -1;
        } else if (type == 5) {
            this.info("Internal Table page " + this.pagenumber_rol);
            return -1;
        } else {
            if (type == 10) {
                this.info("Index leaf page " + this.pagenumber_rol);
                this.withoutROWID = true;
            } else {
                int var10001 = this.pagenumber_rol;
                this.info("Data page " + var10001 + " Offset: " + (this.rollbackjournal.position() - this.ps));
            }

            byte[] cpn;
            if (type == 8) {
                cpn = new byte[2];
                this.buffer.position(1);
                this.buffer.get(cpn);
            }

            cpn = new byte[2];
            this.buffer.position(3);
            this.buffer.get(cpn);
            byte[] ccr = new byte[2];
            this.buffer.position(5);
            this.buffer.get(ccr);
            ByteBuffer contentregionstart = ByteBuffer.wrap(ccr);
            Auxiliary.TwoByteBuffertoInt(contentregionstart);
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

            for(int i = 0; i < cp; ++i) {
                byte[] pointer = new byte[2];
                if (type == 5) {
                    this.buffer.position(12 + 2 * i);
                } else {
                    this.buffer.position(8 + 2 * i);
                }

                this.buffer.get(pointer);
                ByteBuffer celladdr = ByteBuffer.wrap(pointer);
                int celloff = Auxiliary.TwoByteBuffertoInt(celladdr);
                if (last <= 0 || celloff != last) {
                    last = celloff;
                    String hls = Auxiliary.Int2Hex(celloff);
                    Logger.out.debug(this.pagenumber_rol + " -> " + celloff + " 0" + hls);
                    hls.trim();
                    String rc = null;
                    LinkedList<String> record = null;

                    try {
                        record = this.ct.readRecord(celloff, this.buffer, this.pagenumber_maindb, this.visit, type, Integer.MAX_VALUE, this.firstcol, this.withoutROWID, 1, (long)(pageoffset + celloff));
                    } catch (IOException var20) {
                        var20.printStackTrace();
                    }

                    if (record != null && record.size() > 0) {
                        this.output.add(record);
                    }
                }
            }

            this.debug("finished STEP2 -> cellpoint array completed");
            return 0;
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
        this.rollbackjournal = ByteBuffer.allocateDirect((int)this.size);
        Future<Integer> result = this.file.read(this.rollbackjournal, 0L);

        while(!result.isDone()) {
        }

        this.rollbackjournal.position(0);
    }

    protected ByteBuffer readPage() {
        byte[] page = new byte[this.ps];
        this.rollbackjournal.get(page);
        ByteBuffer content = ByteBuffer.wrap(page);
        return content;
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

            this.debug("End of journal parse");
        }
    }

    public void output() {
        String rpath;
        if (this.job.gui != null) {
            this.info("Number of records recovered: " + this.output.size());
            Iterator<LinkedList<String>> lines = this.output.iterator();
            Hashtable<String, ObservableList<LinkedList<String>>> dataSets = new Hashtable();

            while(lines.hasNext()) {
                LinkedList<String> line = (LinkedList)lines.next();
                if (((String)line.getFirst()).trim().length() == 0) {
                    line.set(0, "__UNASSIGNED");
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
                rpath = (String)this.job.guiroltab.get(tablename);
                this.job.gui.update_table(rpath, (ObservableList)dataSets.get(tablename), false);
            }
        } else {
            Path dbfilename = Paths.get(this.path);
            String name = dbfilename.getFileName().toString();
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
            rpath = df.format(now);
            String filename = "results" + name + rpath + ".csv";
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
}
