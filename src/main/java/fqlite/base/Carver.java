package fqlite.base;

import fqlite.descriptor.TableDescriptor;
import fqlite.pattern.MMode;
import fqlite.pattern.SerialTypeMatcher;
import fqlite.util.Auxiliary;
import fqlite.util.CarvingResult;
import fqlite.util.Match;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/* loaded from: fqlite_next.jar:fqlite/base/Carver.class */
public class Carver extends Base {
    ByteBuffer block;
    BitSet bs;
    String content;
    Job job;
    int pagenumber;

    public Carver(Job job, ByteBuffer bl, String content, BitSet bs, int pagenumber) {
        this.job = job;
        this.block = bl;
        this.bs = bs;
        this.content = content;
        this.pagenumber = pagenumber;
    }

    public int carve(int fromidx, int toidx, SerialTypeMatcher mat, int headertype, TableDescriptor tbd) {
        LinkedList<String> record;
        String m;
        Auxiliary c = new Auxiliary(this.job);
        switch (headertype) {
            case 0:
                mat.setMatchingMode(MMode.NORMAL);
                break;
            case 1:
                mat.setMatchingMode(MMode.NOHEADER);
                break;
            case 2:
                mat.setMatchingMode(MMode.NO1stCOL);
                break;
        }
        if (toidx - fromidx <= 5) {
            return -1;
        }
        mat.region(fromidx, toidx);
        mat.setPattern(tbd.getHpattern());
        LinkedList<Match> matches = new LinkedList<>();
        while (mat.find()) {
            String m2 = mat.group2Hex();
            if (m2.length() >= 2 && !m2.startsWith("00000000") && !m2.startsWith("030000")) {
                int from = mat.start();
                if (!this.bs.get(from)) {
                    if ((m2.length() / 2) + Auxiliary.getPayloadLength(m2) > toidx - fromidx) {
                        if (m2.startsWith("8")) {
                            m2 = m2.substring(2);
                            mat.start++;
                            from++;
                        }
                        if (headertype != 0) {
                        }
                    } else {
                        int abstand = from - 4;
                        if (abstand > 0) {
                            this.bs.get(from - 4);
                        }
                    }
                    int end = mat.end();
                    if (!Match.onlyZeros(m2)) {
                        debug("Match (0..NORMAL, 1..NOLENGTH, 2..FIRSTCOLMISSING) : " + headertype);
                        debug("Match: " + m2 + " on pos:" + (((this.pagenumber - 1) * this.job.ps) + from));
                        if (headertype == 0) {
                            if (m2.length() >= 4) {
                                mat.fallbackFor1stColumn = m2.substring(2, 4);
                            }
                            m2 = "RI" + m2;
                        } else if (headertype == 1) {
                            if (m2.length() >= 2) {
                                mat.fallbackFor1stColumn = m2.substring(0, 2);
                            }
                            m2 = addHeaderByte(m2);
                        } else if (headertype == 2) {
                            if (tbd.rowid_col == 0) {
                                m = "00" + m2;
                            } else if (tbd.serialtypes.get(0).equals("INT")) {
                                m = "XX" + m2;
                                System.out.println("XX - column on first place");
                            } else if (tbd.serialtypes.get(0).equals("REAL")) {
                                m = "07" + m2;
                            } else if (tbd.serialtypes.get(0).equals("TEXT")) {
                                m = "21" + m2;
                            } else if (tbd.serialtypes.get(0).equals("BLOB")) {
                                m = "20" + m2;
                            } else {
                                m = "02" + m2;
                            }
                            m2 = addHeaderByte(m);
                        }
                        if (!Match.onlyZeros(m2)) {
                            matches.add(new Match(m2, from, end));
                        }
                    }
                }
            }
        }
        Iterator<Match> it = matches.iterator();
        while (it.hasNext()) {
            Match e = it.next();
            this.bs.set(e.begin, e.end);
        }
        Match[] mm = (Match[]) matches.toArray(new Match[0]);
        for (int i = 0; i < mm.length; i++) {
            Match e2 = mm[i];
            Match next = null;
            if (i + 1 < mm.length) {
                next = mm[i + 1];
            }
            if (tbd.rowid_col >= 0) {
                e2.rowidcolum = tbd.rowid_col;
            }
            try {
                CarvingResult res = c.readDeletedRecordNew(this.job, this.block, this.bs, e2, next, this.pagenumber, mat.fallbackFor1stColumn);
                if (res != null && (record = res.record) != null) {
                    record.add(2, "D");
                    record.addFirst(tbd.tblname);
                    updateResultSet(record);
                }
            } catch (Exception err) {
                warning("Could not read record" + err.toString());
            }
        }
        return 0;
    }

    private void updateResultSet(LinkedList<String> line) {
        if (this.job.resultlist.containsKey(line.getFirst())) {
            ObservableList<LinkedList<String>> tablelist = this.job.resultlist.get(line.getFirst());
            if (!tablelist.contains(line)) {
                tablelist.add(line);
                return;
            }
            return;
        }
        ObservableList<LinkedList<String>> tablelist2 = FXCollections.observableArrayList();
        tablelist2.add(line);
        this.job.resultlist.put(line.getFirst(), tablelist2);
    }

    private String addHeaderByte(String s) {
        int hl = (s.length() / 2) + 1;
        String hls = Integer.toHexString(hl);
        if (hls.length() == 1) {
            hls = "0" + hls;
        }
        return hls + s;
    }
}
