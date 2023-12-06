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
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecoveryTask extends Base implements Runnable {
    public int pagesize;
    public long offset;
    public ByteBuffer buffer;
    public BitSet visit;
    public static List<TableDescriptor> tables = new LinkedList();
    public int pagenumber;
    private Job job;
    private Auxiliary ct;
    private StringBuffer firstcol = new StringBuffer();
    private boolean freeList = false;

    public RecoveryTask(Auxiliary ct, Job job, long offset, int pagenumber, int pagesize, boolean freeList) {
        if (job.size < offset) {
            System.exit(-1);
        }

        this.job = job;
        this.pagesize = pagesize;
        this.offset = offset;
        this.pagenumber = pagenumber;
        this.ct = ct;
        this.freeList = freeList;
        this.visit = new BitSet(pagesize);
    }

    public void carveOnly() {
        this.debug("carveOnly()::" + this.offset);
        this.buffer = this.job.readPageWithOffset(this.offset, this.pagesize);
        String content = Auxiliary.bytesToHex(this.buffer);
        this.carve(content, (Carver)null);
    }

    public int recover() {
        boolean withoutROWID = false;
        int fbstart = -1;

        try {
            this.debug("Offset in recover()::" + this.offset);
            this.buffer = this.job.readPageWithOffset(this.offset, this.pagesize);
            if (this.buffer == null) {
                return -1;
            } else {
                String content = Auxiliary.bytesToHex(this.buffer);
                this.buffer.position(0);
                int type = Auxiliary.getPageType(content);
                this.visit.set(0, 2);
                if (type == 0) {
                    this.buffer.position(0);
                    Integer checksum = this.buffer.getInt();
                    if (checksum == 0) {
                        this.carve(content, (Carver)null);
                    }

                    return 0;
                } else if (type < 0) {
                    this.info("No Data page. " + this.pagenumber);
                    return -1;
                } else if (type == 5) {
                    this.info("Inner Table page (only references no data). Page:" + this.pagenumber);
                    return 0;
                } else if (type == 12) {
                    this.info("Internal Table page " + this.pagenumber);
                    return 0;
                } else {
                    if (type == 10) {
                        this.info("Index leaf page " + this.pagenumber);
                        withoutROWID = true;
                    } else {
                        this.info("Data page " + this.pagenumber + " Offset: " + this.offset);
                    }

                    if (type == 8) {
                        byte[] fboffset = new byte[2];
                        this.buffer.position(1);
                        this.buffer.get(fboffset);
                        ByteBuffer firstfb = ByteBuffer.wrap(fboffset);
                        fbstart = Auxiliary.TwoByteBuffertoInt(firstfb);
                    }

                    int ccrstart = this.job.ps;
                    byte[] cpn = new byte[2];
                    this.buffer.position(3);
                    this.buffer.get(cpn);
                    byte[] ccr = new byte[2];
                    this.buffer.position(5);
                    this.buffer.get(ccr);
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

                    int pageheaderend = 8 + cp * 2;
                    this.visit.set(0, pageheaderend);
                    String tbln;
                    if (fbstart > pageheaderend && fbstart < this.job.ps) {
                        boolean goon = false;

                        do {
                            this.buffer.position(fbstart);
                            byte[] dword = new byte[2];
                            this.buffer.get(dword);
                            ByteBuffer nextblock = ByteBuffer.wrap(dword);
                            int next = Auxiliary.TwoByteBuffertoInt(nextblock);
                            this.buffer.get(dword);
                            ByteBuffer blocklength = ByteBuffer.wrap(dword);
                            int length = Auxiliary.TwoByteBuffertoInt(blocklength);
                            byte[] header = new byte[6];
                            this.buffer.get(header);
                            tbln = Auxiliary.bytesToHex(header);
                            if (next > 0 && next < this.job.ps) {
                                fbstart = next;
                                goon = true;
                            } else {
                                goon = false;
                            }
                        } while(goon);
                    }

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
                            if (Logger.LOGLEVEL == 1) {
                                String hls = Auxiliary.Int2Hex(celloff);
                                Logger.out.debug(this.pagenumber + " -> " + celloff + " 0" + hls);
                            }

                            LinkedList<String> record = this.ct.readRecord(celloff, this.buffer, this.pagenumber, this.visit, type, Integer.MAX_VALUE, this.firstcol, withoutROWID, 0, -1L);
                            if (record != null && record.size() > 0) {
                                int p;
                                String secondcol;
                                if ((p = ((String)record.getFirst()).indexOf("_node")) > 0) {
                                    secondcol = (String)record.getFirst();
                                    tbln = secondcol.substring(0, p);
                                    if (this.job.virtualTables.containsKey(tbln)) {
                                        TableDescriptor tds = (TableDescriptor)this.job.virtualTables.get(tbln);
                                        String data = (String)record.get(6);
                                        byte[] binary = Auxiliary.decode(data);
                                        ByteBuffer bf = ByteBuffer.wrap(binary);
                                        bf.getShort();

                                        for(int entries = bf.getShort(); entries > 0; --entries) {
                                            LinkedList<String> rtreerecord = new LinkedList();
                                            rtreerecord.add("" + tbln);
                                            rtreerecord.add("");
                                            rtreerecord.add("");
                                            rtreerecord.add("");
                                            rtreerecord.add("");
                                            long primarykey = bf.getLong();
                                            rtreerecord.add(String.valueOf(primarykey));

                                            for(int number = tds.columnnames.size() - 1; number > 0; --number) {
                                                float rv = bf.getFloat();
                                                rtreerecord.add(String.valueOf(rv));
                                            }

                                            this.updateResultSet(rtreerecord);
                                        }
                                    }
                                }

                                if (this.freeList) {
                                    secondcol = (String)record.get(3);
                                    secondcol = "F" + secondcol;
                                    record.set(3, secondcol);
                                }

                                this.updateResultSet(record);
                            }
                        }
                    }

                    this.buffer.position(pageheaderend);
                    this.carve(content, (Carver)null);
                    return 0;
                }
            }
        } catch (Exception var31) {
            var31.printStackTrace();
            return -1;
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

    public LinkedList<Gap> findGaps() {
        LinkedList<Gap> gaps = new LinkedList();


        for(int i = 0; i < this.pagesize; ++i) {
            if (!this.visit.get(i)) {
                int from = i;
                int to = i;

                while(true) {
                    ++i;
                    if (this.visit.get(i) || i >= this.pagesize - 1) {
                        if (to - from >= 4) {
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
                                Gap g = new Gap(from, to);
                                if (!gaps.contains(g)) {
                                    int var10001 = this.job.ps * (this.pagenumber - 1) + from;
                                    this.debug("ohne match : " + var10001 + " - " + (this.job.ps * (this.pagenumber - 1) + to) + " Bytes");
                                }

                                gaps.add(g);
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
            c = new Carver(this.job, this.buffer, content, this.visit, this.pagenumber);
        }

        TableDescriptor tdesc = null;
        if (this.job.pages.length > this.pagenumber) {
            AbstractDescriptor ad = this.job.pages[this.pagenumber];
            if (ad instanceof TableDescriptor) {
                tdesc = (TableDescriptor)ad;
            }
        }

        List<TableDescriptor> tab = tables;
        this.debug(" tables :: " + tables.size());

        if (tdesc != null) {
            tab = new LinkedList();
            tab.add(tdesc);
            this.debug(" added tdsec ");
        } else {
            this.warning(" No component description!");
            tab = tables;
        }

        List<Gap> gaps = this.findGaps();
        if (gaps.size() == 0) {
            this.debug("no gaps anymore. Stopp search");
        } else {
            for(int n = 0; n < ((List)tab).size(); ++n) {
                tdesc = (TableDescriptor)((List)tab).get(n);
                int var10001 = this.pagenumber;
                this.debug("pagenumber :: " + var10001 + " component size :: " + ((List)tab).size());
                this.debug("n " + n);
                String tablename = ((TableDescriptor)((List)tab).get(n)).tblname;
                if (!tablename.startsWith("__UNASSIGNED")) {
                    SerialTypeMatcher stm = new SerialTypeMatcher(this.buffer);
                    gaps = this.findGaps();

                    int oldhash;
                    Gap next;
                    for(oldhash = 0; oldhash < gaps.size(); ++oldhash) {
                        next = (Gap)gaps.get(oldhash);
                        if (next.to - next.from > 5 && c.carve(next.from, next.to, stm, 0, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP NORMAL finished with matches");
                        }
                    }

                    gaps = this.findGaps();

                    for(oldhash = 0; oldhash < gaps.size(); ++oldhash) {
                        next = (Gap)gaps.get(oldhash);
                        if (c.carve(next.from, next.to, stm, 1, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("*****************************  STEP COLUMNSONLY finished with matches");
                        }
                    }

                    gaps = this.findGaps();
                    oldhash = gaps.hashCode();

                    int newhash;
                    for(newhash = 0; newhash < gaps.size(); ++newhash) {
                         next = gaps.get(newhash);
                        if (c.carve(next.from, next.to, stm, 2, (TableDescriptor)((List)tab).get(n)) != -1) {
                            this.debug("RecoveryTask *****************************  STEP FIRSTCOLUMNMISSING finished with matches");
                        }
                    }

                    gaps = this.findGaps();
                    newhash = gaps.hashCode();

                    for(int a = 0; a < gaps.size(); ++a) {
                         next = gaps.get(a);
                        if (oldhash != newhash) {
                            c.carve(next.from + 4, next.to, stm, 2, (TableDescriptor)((List)tab).get(n));
                        }
                    }
                }
            }

        }
    }

    public void run() {
        try {
            this.recover();
        } catch (Exception var5) {
            System.err.println(var5.toString());
        } finally {
            this.job.runningTasks.decrementAndGet();
        }

    }
}
