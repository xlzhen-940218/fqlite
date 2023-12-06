//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import fqlite.base.Base;
import fqlite.base.Job;
import fqlite.base.SqliteElement;
import fqlite.descriptor.AbstractDescriptor;
import fqlite.descriptor.IndexDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.parser.SQLiteSchemaParser;
import fqlite.pattern.HeaderPattern;
import fqlite.pattern.IntegerConstraint;
import fqlite.types.BLOBElement;
import fqlite.types.BLOBTYPE;
import fqlite.types.SerialTypes;
import fqlite.types.StorageClass;
import fqlite.types.TimeStamp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

public class Auxiliary extends Base {
    public AtomicInteger found = new AtomicInteger();
    public AtomicInteger inrecover = new AtomicInteger();
    public static final String TABLELEAFPAGE = "0d";
    public static final String TABLEINTERIORPAGE = "05";
    public static final String INDEXLEAFPAGE = "0a";
    public static final String INDEXINTERIORPAGE = "02";
    public static final String OVERFLOWPAGE = "00";
    protected static final char[] hexArray = "0123456789abcdef".toCharArray();
    public Job job;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
    static final long UNIX_MIN_SECONDS = 1262304000L;
    static final long UNIX_MAX_SECONDS = 2524608000L;
    static final long UNIX_MIN_DATE = 1262304000000L;
    static final long UNIX_MAX_DATE = 2524608000000L;
    static final long UNIX_MIN_DATE_NANO = 1262304000000000L;
    static final long UNIX_MAX_DATE_NANO = 2524608000000000L;
    static final long UNIX_MIN_DATE_PICO = 1262304000000000000L;
    static final long UNIX_MAX_DATE_PICO = 2524608000000000000L;
    static final double MAC_MIN_DATE = 3.0E8;
    static final double MAC_MAX_DATE = 8.0E8;
    static final long MAC_NANO_MIN_DATE = 300000000000000000L;
    static final long MAC_NANO_MAX_DATE = 800000000000000000L;
    static final long CHROME_MIN_DATE = 1L;

    public static int getPageType(String content) {
        boolean skip = false;
        switch (content.substring(0,2)) {
            case "00":
                return 0;
            case "02":
                skip = true;
                return 2;
            case "05":
                return 5;
            case "0a":
                skip = true;
                return 10;
            case "0d":
                return 8;
        }

        skip = true;
        return skip ? -1 : -99;
    }

    public Auxiliary(Job job) {
        this.job = job;
    }

    public boolean readMasterTableRecord(Job job, int start, ByteBuffer buffer, String header) throws IOException {
        SqliteElement[] columns = MasterRecordToColumns(header);
        if (columns == null) {
            return false;
        } else {
            String cl = header.substring(2);
            int pll = this.computePayloadLength(cl.substring(0, 12));
            buffer.position(start + 8);
            int so = this.computePayload(pll);
            //int overflow = true;
            int con;
            if (so < pll) {
                con = header.length() / 2;
                int last = buffer.position();
                int overflow = buffer.getInt(job.ps - 4);
                if (overflow > job.numberofpages) {
                    return false;
                }

                byte[] extended = readOverflow(job, overflow - 1);
                byte[] originalbuffer = new byte[job.ps];

                for(int bb = 0; bb < job.ps; ++bb) {
                    originalbuffer[bb] = buffer.get(bb);
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(originalbuffer);
                outputStream.write(extended);
                byte[] c = outputStream.toByteArray();
                ByteBuffer bf = ByteBuffer.wrap(c);
                buffer = bf;
                bf.position(last);
            }

            con = 0;
            String tablename = null;
            int rootpage = -1;
            String statement = null;
            SqliteElement[] var17 = columns;
            int var28 = columns.length;

            for(int var27 = 0; var27 < var28; ++var27) {
                SqliteElement en = var17[var27];
                if (en != null) {
                    //byte[] value = null;
                    byte[] value = new byte[en.length];

                    try {
                        buffer.get(value);
                    } catch (BufferUnderflowException var20) {
                        return false;
                    }

                    if (con == 2) {
                        tablename = en.toString(value, true);
                    }

                    if (con == 3) {
                        if (value.length == 0) {
                            this.debug("Seems to be a virtual component -> no root page ;)");
                        } else if (en.type == SerialTypes.INT8) {
                            rootpage = SqliteElement.decodeInt8(value[0]);
                        } else if (en.type == SerialTypes.INT16) {
                            rootpage = SqliteElement.decodeInt16(new byte[]{value[0], value[1]});
                        } else if (en.type == SerialTypes.INT24) {
                            rootpage = SqliteElement.decodeInt24(new byte[]{value[0], value[1], value[2]});
                        } else if (en.type == SerialTypes.INT32) {
                            rootpage = SqliteElement.decodeInt32(new byte[]{value[0], value[1], value[2], value[3]});
                        }
                    }

                    if (con == 4) {
                        statement = en.toString(value, true);
                        break;
                    }

                    ++con;
                }
            }

            SQLiteSchemaParser.parse(job, tablename, rootpage, statement);
            return true;
        }
    }

    public CarvingResult readDeletedRecordNew(Job job, ByteBuffer buffer, BitSet bs, Match m, Match next, int pagenumber, String fallback) throws IOException {
        LinkedList<String> record = new LinkedList();
        int rowid = -1;
        buffer.position(m.end);
        String header;
        String match;
        int so;
        byte[] freeblockinfo;
        //boolean overflow;
        int overflow;
        if (m.match.startsWith("RI")) {
            header = m.match.substring(2);
            match = header.substring(0, 2);
            so = varintHexString2Integer(match);
            if (so != header.length() / 2) {
                return null;
            }

            buffer.position(m.end - so - 4);
            freeblockinfo = new byte[4];
            buffer.get(freeblockinfo);
            int[] values = readVarInt(freeblockinfo);
            //overflow = true;
            if (values != null && values.length > 0) {
                overflow = values[values.length - 1];
                rowid = overflow;
            }

            m.match = m.match.replace("RI", "");
            buffer.position(m.end);
        }

        header = m.match.substring(2);
        if (header.startsWith("XX")) {
            match = header.substring(2);
            so = match.length() / 2;
            buffer.position(m.end - so - 2);
            freeblockinfo = new byte[2];
            buffer.get(freeblockinfo);
            buffer.position(buffer.position() - 2);
            short lg = buffer.getShort();
            if (next != null) {
                overflow = next.begin;
                if (overflow < m.begin + lg) {
                    lg -= (short)(m.begin + lg - next.begin);
                }
            }

            overflow = lg - 4 - so - getPayloadLength(match);
            if (overflow < 0) {
                if (header.startsWith("XX")) {
                    header = "02" + header.substring(4);
                }
            } else if (overflow >= 0 && overflow <= 6) {
                String repl = Integer.toHexString(overflow);
                if (repl.length() % 2 != 0) {
                    repl = "0" + repl;
                }

                header = header.replace("XX", repl);
            } else {
                header = header.replace("XX", "02");
                System.out.println("fallback strategy" + header);
            }

            buffer.position(m.end);
        }

        SqliteElement[] columns = toColumns(header);
        if (columns == null) {
            this.debug(" no valid header-string: " + header);
            return null;
        } else {
            int pll = this.computePayloadLength(header);
            so = this.computePayload(pll);
            int co = 0;
            boolean error = false;
            record.add("" + ((pagenumber - 1) * job.ps + m.begin));
            //overflow = true;
            int number;
            byte[] value;
            int nextrecord;
            int blobcolidx;
            SqliteElement en;
            if (so < pll) {
                nextrecord = header.length() / 2;
                number = buffer.position();
                this.debug(" deleted spilled payload ::" + so);
                this.debug(" deleted pll payload ::" + pll);
                buffer.position(buffer.position() + so - nextrecord - 1);
                overflow = buffer.getInt();
                this.debug(" deleted overflow::::::::: " + overflow + " " + Integer.toHexString(overflow));
                buffer.position(number);
                ByteBuffer bf;
                int bb;
                if (overflow > 0 && overflow < job.numberofpages) {
                    byte[] extended = this.readOverflowIterativ(overflow - 1, false);
                    byte[] c = new byte[pll + job.ps];
                    buffer.position(0);
                    value = new byte[job.ps];

                    for(bb = 0; bb < job.ps; ++bb) {
                        value[bb] = buffer.get(bb);
                    }

                    buffer.position(number);
                    bf = null;
                    System.arraycopy(value, buffer.position(), c, 0, so + 7);

                    try {
                        if (extended != null) {
                            System.arraycopy(extended, 0, c, so - nextrecord - 1, pll - so);
                        }

                        bf = ByteBuffer.wrap(c);
                    } catch (ArrayIndexOutOfBoundsException var27) {
                        System.out.println("Error IndexOutOfBounds");
                    } catch (NullPointerException var28) {
                        System.out.println("Error NullPointer in ");
                    }
                } else {
                    pll = so;
                    bf = buffer;
                }

                bf.position(0);
                blobcolidx = 0;
                SqliteElement[] var24 = columns;
                bb = columns.length;

                for(int var41 = 0; var41 < bb; ++var41) {
                    en = var24[var41];
                    if (en != null) {
                        byte[] t_value = new byte[en.length];
                        bf.get(t_value);
                        if (en.serial == StorageClass.BLOB) {
                            String tablecelltext = en.getBLOB(t_value);
                            if (tablecelltext.length() > 0) {
                                record.add("[BLOB-" + blobcolidx + "] " + tablecelltext + "..");
                                this.storeBLOB(record, blobcolidx, tablecelltext, t_value, 2);
                                ++blobcolidx;
                            } else {
                                record.add("");
                            }
                        } else {
                            record.add(en.toString(t_value, false));
                        }

                        ++co;
                    }
                }

                buffer.position(number + so - nextrecord - 1);
            } else {
                if (pll < 42) {
                    nextrecord = 0;
                    number = -1;
                    SqliteElement[] var42 = columns;
                    int var40 = columns.length;

                    for(blobcolidx = 0; blobcolidx < var40; ++blobcolidx) {
                        SqliteElement en_t = var42[blobcolidx];
                        ++number;
                        if (en_t != null) {
                            byte[] value_t = new byte[en_t.length];
                            if (buffer.position() + en_t.length > buffer.limit()) {
                                error = true;
                                return null;
                            }

                            if (rowid >= 0 && en_t.length == 0 && m.rowidcolum >= 0 && m.rowidcolum == number) {
                                record.add("" + rowid);
                                ++co;
                            } else {
                                buffer.get(value_t);
                                if (en_t.serial == StorageClass.BLOB) {
                                    String tablecelltext = en_t.getBLOB(value_t);
                                    if (tablecelltext.length() > 0) {
                                        String vv = "[BLOB-" + nextrecord + "] " + tablecelltext;
                                        if (tablecelltext.length() > 32) {
                                            vv = vv + "..";
                                        }

                                        record.add(vv);
                                        this.storeBLOB(record, nextrecord, tablecelltext, value_t, 0);
                                        ++nextrecord;
                                    } else {
                                        record.add("");
                                    }
                                } else {
                                    record.add(en_t.toString(value_t, false));
                                }

                                ++co;
                            }
                        }
                    }
                } else {
                    nextrecord = bs.nextSetBit(buffer.position());
                    boolean partial = false;
                    //int blobcolidx = 0;

                    for(blobcolidx = 0; blobcolidx < columns.length; ++blobcolidx) {
                        if (partial) {
                            record.add("");
                        } else {
                            en = columns[blobcolidx];
                            if (en != null) {
                                if (rowid >= 0 && en.length == 0 && m.rowidcolum >= 0 && m.rowidcolum == blobcolidx) {
                                    record.add("" + rowid);
                                    ++co;
                                } else {
                                    value = new byte[en.length];
                                    if (buffer.position() + en.length > buffer.limit()) {
                                        error = true;
                                        return null;
                                    }

                                    if (nextrecord == -1) {
                                        nextrecord = job.ps;
                                    }

                                    String tablecelltext;
                                    if (buffer.position() + en.length <= nextrecord) {
                                        buffer.get(value);
                                        if (en.serial == StorageClass.BLOB) {
                                            tablecelltext = en.getBLOB(value);
                                            if (tablecelltext.length() > 0) {
                                                record.add("[BLOB-" + blobcolidx + "] " + tablecelltext + "..");
                                                this.storeBLOB(record, blobcolidx, tablecelltext, value, 0);
                                                ++blobcolidx;
                                            } else {
                                                record.add("");
                                            }
                                        } else {
                                            record.add(en.toString(value, false));
                                        }

                                        ++co;
                                    } else {
                                        en.length -= buffer.position() + en.length - nextrecord;
                                        if (en.length > 0 && (en.type == SerialTypes.BLOB || en.type == SerialTypes.STRING)) {
                                            value = new byte[en.length];
                                            buffer.get(value);
                                            if (en.serial == StorageClass.BLOB) {
                                                tablecelltext = en.getBLOB(value);
                                                if (tablecelltext.length() > 0) {
                                                    record.add("[BLOB-" + blobcolidx + "] " + tablecelltext + "..");
                                                    this.storeBLOB(record, blobcolidx, tablecelltext, value, 0);
                                                    ++blobcolidx;
                                                } else {
                                                    record.add("");
                                                }
                                            } else {
                                                record.add(en.toString(value, false));
                                            }
                                        }

                                        partial = true;
                                    }
                                }
                            }
                        }
                    }
                }

                if (error) {
                    return null;
                }
            }

            bs.set(m.end, buffer.position(), true);
            int var10001 = m.end;
            this.debug("visited :: " + var10001 + " bis " + buffer.position());
            nextrecord = (pagenumber - 1) * job.ps + buffer.position();
            var10001 = (pagenumber - 1) * job.ps + m.end;
            this.debug("visited :: " + var10001 + " bis " + nextrecord);
            record.add(0, "" + pll);
            int var10002 = header.length();
            record.add(1, "" + var10002 / 2);
            return new CarvingResult(buffer.position(), nextrecord, new StringBuffer(), record);
        }
    }

    public static String convertToUTF8(String s) {
        String out = null;

        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
            return out;
        } catch (UnsupportedEncodingException var3) {
            return null;
        }
    }

    private StringBuffer write(int col, SqliteElement en, byte[] value) {
        StringBuffer val = new StringBuffer();
        if (col > 0) {
            val.append(";");
        }

        val.append(en.toString(value, false));
        return val;
    }

    public LinkedList<String> readRecord(int cellstart, ByteBuffer buffer, int pagenumber_db, BitSet bs, int pagetype, int maxlength, StringBuffer firstcol, boolean withoutROWID, int filetype, long offset) throws IOException {
        LinkedList<String> record = new LinkedList();
        boolean unkown = false;
        int rowid_col = -1;
        buffer.position(0);
        TableDescriptor td = null;
        AbstractDescriptor ad = null;
        if (filetype != 1 && filetype != 2) {
            if (this.job.pages[pagenumber_db] != null) {
                ad = this.job.pages[pagenumber_db];
                if (ad instanceof TableDescriptor) {
                    td = (TableDescriptor)ad;
                }

                record.add(ad.getName());
                record.add(" ");
                record.add("" + ((pagenumber_db - 1) * this.job.ps + cellstart));
                rowid_col = this.job.pages[pagenumber_db].rowid_col;
            } else {
                unkown = true;
            }
        } else {
            if (pagenumber_db < this.job.pages.length && this.job.pages[pagenumber_db] != null) {
                ad = this.job.pages[pagenumber_db];
                if (ad instanceof TableDescriptor) {
                    td = (TableDescriptor)ad;
                }

                record.add(this.job.pages[pagenumber_db].getName());
            } else {
                record.add("D");
                record.add("__UNASSIGNED");
            }

            record.add(" ");
            if (offset > -1L) {
                record.add("" + offset);
            } else {
                record.add("" + cellstart);
            }

            if (pagenumber_db < this.job.pages.length && this.job.pages[pagenumber_db] != null) {
                rowid_col = this.job.pages[pagenumber_db].rowid_col;
            }
        }

        this.debug("cellstart for pll: " + ((pagenumber_db - 1) * this.job.ps + cellstart));

        try {
            buffer.position(cellstart);
        } catch (Exception var49) {
            System.err.println("ERROR: cellstart not in buffer" + cellstart);
            return null;
        }

        int pll = readUnsignedVarInt(buffer);
        this.debug("Length of payload int : " + pll + " as hex : " + Integer.toHexString(pll));
        if (pll < 4) {
            return null;
        } else {
            int rowid = 0;
            if (!withoutROWID) {
                if (unkown) {
                    rowid = readUnsignedVarInt(buffer);
                    this.debug("rowid: " + Integer.toHexString(rowid));
                } else if (pagenumber_db < this.job.pages.length && (this.job.pages[pagenumber_db] == null || this.job.pages[pagenumber_db].ROWID)) {
                    rowid = readUnsignedVarInt(buffer);
                    this.debug("rowid: " + Integer.toHexString(rowid));
                }
            }

            int phl = readUnsignedVarInt(buffer);
            if (phl == 0) {
                return null;
            } else {
                this.debug("Header Length int: " + phl + " as hex : " + Integer.toHexString(phl));
                --phl;
                maxlength -= phl;
                if (phl == 0) {
                    return null;
                } else {
                    int pp = buffer.position();
                    String hh = this.getHeaderString(phl, buffer);
                    buffer.position(pp);
                    int[] values = readVarInt(decode(hh));
                    SqliteElement[] columns = this.getColumns(phl, buffer, firstcol);
                    if (columns == null) {
                        this.debug(" No valid header. Skip recovery.");
                        return null;
                    } else {
                        this.debug("Number of columns: " + columns.length);
                        //int co = false;

                        try {
                            if (unkown) {
                                td = this.matchTable(columns);
                                if (td == null) {
                                    record.add("D");
                                    record.add("__UNASSIGNED");
                                } else {
                                    record.add(td.tblname);
                                    this.job.pages[pagenumber_db] = td;
                                    rowid_col = td.rowid_col;
                                }

                                record.add(" ");
                                record.add("" + ((pagenumber_db - 1) * this.job.ps + cellstart));
                            }
                        } catch (NullPointerException var48) {
                            System.err.println(var48);
                        }

                        boolean error = false;
                        int so = this.computePayload(pll);
                        //int overflow = true;
                        int blobcolidx;
                        int co;
                        SqliteElement en;
                        if (so < pll) {
                            blobcolidx = buffer.position();
                            this.debug("regular spilled payload ::" + so);
                            if (buffer.position() + so - phl - 1 > buffer.limit() - 4) {
                                return null;
                            }

                            int overflow;
                            try {
                                buffer.position(buffer.position() + so - phl - 1);
                                overflow = buffer.getInt();
                            } catch (Exception var47) {
                                return null;
                            }

                            if (overflow < 0) {
                                return null;
                            }

                            this.debug("regular overflow::::::::: " + overflow + " " + Integer.toHexString(overflow));
                            this.job.overflowpages.add(overflow);
                            buffer.position(blobcolidx);
                            byte[] extended = this.readOverflowIterativ(overflow - 1, false);
                            byte[] c = new byte[pll + this.job.ps];
                            buffer.position(0);
                            byte[] originalbuffer = new byte[this.job.ps];

                            for(int bb = 0; bb < this.job.ps; ++bb) {
                                originalbuffer[bb] = buffer.get(bb);
                            }

                            buffer.position(blobcolidx);
                            System.arraycopy(originalbuffer, buffer.position(), c, 0, so - phl);

                            try {
                                if (extended != null) {
                                    System.arraycopy(extended, 0, c, so - phl - 1, pll - so);
                                }
                            } catch (ArrayIndexOutOfBoundsException var45) {
                                System.out.println("Error IndexOutOfBounds");
                            } catch (NullPointerException var46) {
                                System.out.println("Error NullPointer in ");
                            }

                            ByteBuffer bf = ByteBuffer.wrap(c);
                            bf.position(0);
                            co = 0;
                             blobcolidx = 0;
                            SqliteElement[] var37 = columns;
                            int var36 = columns.length;

                            for(int var35 = 0; var35 < var36; ++var35) {
                                 en = var37[var35];
                                if (en == null) {
                                    record.add("NULL");
                                } else if (rowid_col == co && !withoutROWID) {
                                    record.add("" + rowid);
                                    ++co;
                                } else {
                                    byte[] value = new byte[en.length];
                                    if (bf.limit() - bf.position() < value.length) {
                                        PrintStream var10000 = System.out;
                                        int var10001 = bf.limit() - bf.position();
                                        var10000.println(" Bufferunderflow " + var10001 + " is lower than" + value.length);
                                    }

                                    try {
                                        bf.get(value);
                                    } catch (BufferUnderflowException var44) {
                                        System.out.println("readRecord():: buffer underflow ERROR " + String.valueOf(var44));
                                        return null;
                                    }

                                    String vv;
                                    if (en.serial == StorageClass.BLOB) {
                                        vv = en.getBLOB(value);
                                        if (vv.length() > 0) {
                                            record.add("[BLOB-" + blobcolidx + "] " + vv + "..");
                                            this.storeBLOB(record, blobcolidx, vv, value, 2);
                                            ++blobcolidx;
                                        } else {
                                            record.add("");
                                        }
                                    } else {
                                        vv = null;
                                        boolean istimestamp = false;
                                        if (td == null) {
                                            record.add(en.toString(value, false));
                                            continue;
                                        }

                                        if (co < td.serialtypes.size()) {
                                            String coltype = (String)td.sqltypes.get(co);
                                            if (coltype.equals("TIMESTAMP")) {
                                                TimeStamp ts = this.timestamp2String(en, value);
                                                if (ts != null) {
                                                    vv = ts.text;
                                                    istimestamp = true;
                                                    System.out.println("Update Timestamps :: " + vv + " ->  " + String.valueOf(this.found));
                                                    this.job.timestamps.put(vv, this.found);
                                                }
                                            }
                                        }

                                        if (!istimestamp) {
                                            if (en.type == SerialTypes.PRIMARY_KEY) {
                                                en.toString(value, false);
                                                vv = null;
                                            } else {
                                                vv = en.toString(value, false);
                                            }
                                        }

                                        if (vv != null) {
                                            record.add(vv);
                                        } else {
                                            record.add("null");
                                        }
                                    }

                                    ++co;
                                    if (maxlength <= 0) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            co = 0;
                            blobcolidx = 0;
                            SqliteElement[] var65 = columns;
                            int var58 = columns.length;

                            for(int var55 = 0; var55 < var58; ++var55) {
                                en = var65[var55];
                                if (en == null) {
                                    record.add("NULL");
                                } else if (rowid_col == co && !withoutROWID) {
                                    record.add("" + rowid);
                                    ++co;
                                } else {
                                    byte[] value = null;
                                    if (maxlength >= en.length) {
                                        value = new byte[en.length];
                                    } else if (maxlength > 0) {
                                        value = new byte[maxlength];
                                    }

                                    maxlength -= en.length;
                                    if (value == null) {
                                        break;
                                    }

                                    try {
                                        buffer.get(value);
                                    } catch (BufferUnderflowException var43) {
                                        System.out.println("readRecord():: buffer underflow ERROR " + String.valueOf(var43));
                                        return null;
                                    }

                                    String vv;
                                    if (en.serial == StorageClass.BLOB) {
                                        vv = en.getBLOB(value);
                                        if (vv.length() > 0) {
                                            record.add("[BLOB-" + blobcolidx + "] " + vv + "..");
                                            this.storeBLOB(record, blobcolidx, vv, value, 2);
                                            ++blobcolidx;
                                        } else {
                                            record.add("");
                                        }
                                    } else {
                                        vv = null;
                                        boolean istimestamp = false;
                                        if (td == null) {
                                            record.add(en.toString(value, false));
                                            continue;
                                        }

                                        if (co < td.serialtypes.size()) {
                                            String coltype = (String)td.sqltypes.get(co);
                                            if (coltype.equals("TIMESTAMP")) {
                                                TimeStamp ts = this.timestamp2String(en, value);
                                                if (ts != null) {
                                                    vv = ts.text;
                                                    istimestamp = true;
                                                }
                                            }
                                        }

                                        if (!istimestamp) {
                                            if (en.type == SerialTypes.PRIMARY_KEY) {
                                                en.toString(value, false);
                                                vv = null;
                                            } else {
                                                vv = en.toString(value, false);
                                            }
                                        }

                                        if (vv != null) {
                                            record.add(vv);
                                        } else {
                                            record.add("null");
                                        }
                                    }

                                    ++co;
                                    if (maxlength <= 0) {
                                        break;
                                    }
                                }
                            }
                        }

                        record.add(1, "" + pll);
                        int var10002 = hh.length();
                        record.add(2, "" + var10002 / 2);
                        if (so < pll) {
                            this.debug("visted " + cellstart + " bis " + (cellstart + so + 7));
                            bs.set(cellstart, cellstart + so + 4);
                        } else {
                            this.debug("visted " + cellstart + " bis " + buffer.position());
                            bs.set(cellstart, buffer.position());
                        }

                        if (error) {
                            this.err("spilles overflow page error ...");
                            return null;
                        } else {
                            String archivekey = this.getPrimaryKey(td, record);
                            Integer originalhash;
                            if (filetype == 1 && ad != null && !unkown && !withoutROWID) {
                                if (this.job.LineHashes.containsKey(archivekey)) {
                                     originalhash = (Integer)this.job.LineHashes.get(archivekey);
                                    originalhash = computeHash(record);
                                    if (!originalhash.equals(originalhash)) {
                                        record.set(3, "updated");
                                    }
                                } else {
                                    System.out.println("removed record at offset " + archivekey);
                                    record.set(3, "deleted");
                                }
                            } else if (filetype == 2 && !unkown && ad != null && !withoutROWID) {
                                LinkedList versions;
                                if (this.job.TimeLineHashes.containsKey(archivekey)) {
                                    versions = (LinkedList)this.job.TimeLineHashes.get(archivekey);
                                    originalhash = computeHash(record);
                                    Integer journalhash = ((Version)versions.getFirst()).hash;
                                    String status = (String)((Version)versions.getFirst()).record.get(3);
                                    String lastversion = status.substring(0, status.indexOf("."));
                                    int vers = versions.size();
                                    ++vers;
                                    Object[] var10003;
                                    if (!originalhash.equals(journalhash)) {
                                        var10003 = new Object[]{vers};
                                        record.set(3, String.format("%03d", var10003) + ". version update");
                                    } else {
                                        var10003 = new Object[]{vers};
                                        record.set(3, String.format("%03d", var10003) + ". version (no change)");
                                    }

                                    versions.addFirst(new Version(record));
                                } else {
                                    record.set(3, "001. version");
                                    versions = new LinkedList();
                                    versions.add(new Version(record));
                                    this.job.TimeLineHashes.put(archivekey, versions);
                                }
                            } else if (!unkown && ad != null && !withoutROWID && ad instanceof TableDescriptor) {
                                en = null;
                                td = (TableDescriptor)ad;
                                List<Integer> pk = td.primarykeycolumnnumbers;
                                String key;
                                if (pk != null && pk.size() > 0) {
                                    Iterator<Integer> pcol = pk.iterator();
                                    StringBuffer sb = new StringBuffer();

                                    while(pcol.hasNext()) {
                                        sb.append((String)record.get(5 + (Integer)pcol.next()));
                                    }

                                    key = sb.toString();
                                } else {
                                    String var75 = ad.getName();
                                    key = var75 + "_" + rowid;
                                }

                                if (filetype == 1) {
                                    this.job.LineHashes.put(ad.getName() + "_" + rowid, computeHash(record));
                                } else if (filetype == 2) {
                                    LinkedList<Version> lll = new LinkedList();
                                    lll.add(new Version(record));
                                    this.job.TimeLineHashes.put(key, lll);
                                }
                            }

                            return record;
                        }
                    }
                }
            }
        }
    }

    private void storeBLOB(LinkedList<String> record, int blobcolidx, String tablecelltext, byte[] value, int offsetidx) {
        Long hash;
        if (((String)record.get(offsetidx)).length() > 2) {
            hash = Long.parseLong((String)record.get(offsetidx));
        } else {
            hash = (long)blobcolidx;
        }

        String var10000 = String.valueOf(hash);
        String shash = var10000 + "-" + blobcolidx;
        if (tablecelltext.contains("jpg")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.JPG));
        } else if (tablecelltext.contains("png")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.PNG));
        } else if (tablecelltext.contains("gif")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.GIF));
        } else if (tablecelltext.contains("bmp")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.BMP));
        } else if (tablecelltext.contains("tiff")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.TIFF));
        } else if (tablecelltext.contains("heic")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.HEIC));
        } else if (tablecelltext.contains("pdf")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.PDF));
        } else if (tablecelltext.contains("plist")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.PLIST));
        } else if (tablecelltext.contains("gzip")) {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.GZIP));
        } else {
            this.job.BLOBs.put(shash, new BLOBElement(value, BLOBTYPE.UNKOWN));
        }

        if (tablecelltext.contains("jpg") || tablecelltext.contains("png") || tablecelltext.contains("gif") || tablecelltext.contains("bmp")) {
            Image img = scaledown(value);
            if (img != null) {
                this.job.Thumbnails.put(shash, img);
            }
        }

    }

    private String getPrimaryKey(TableDescriptor td, LinkedList<String> record) {
        String key = null;
        if (td == null) {
            return null;
        } else {
            List<Integer> pk = td.primarykeycolumnnumbers;
            if (pk != null && pk.size() > 0) {
                Iterator<Integer> pcol = pk.iterator();
                StringBuffer sb = new StringBuffer();

                while(pcol.hasNext()) {
                    sb.append((String)record.get(5 + (Integer)pcol.next()));
                }

                key = sb.toString();
            }

            return key;
        }
    }

    public static int computeHash(LinkedList<String> record) {
        LinkedList<String> ll = new LinkedList();

        for(int zz = 5; zz < record.size(); ++zz) {
            ll.add((String)record.get(zz));
        }

        return ll.hashCode();
    }

    public static String int2Timestamp(String time) {
        if (time != null && !time.equals("") && !time.equals("null")) {
            long l;
            try {
                l = Long.parseLong(time);
            } catch (Exception var8) {
                return "";
            }

            String timestamp = "";
            ZonedDateTime utc;
            if (l > 1262304000L && l < 2524608000L) {
                utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(l), ZoneOffset.UTC);
                timestamp = utc.format(Auxiliary.formatter);
                return timestamp;
            } else {

                DateTimeFormatter formatter;
                long ut;
                if ((double)l > 3.0E8 && (double)l < 8.0E8) {
                    ut = 978307200L + l;
                    utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(ut), ZoneOffset.UTC);
                    formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                    timestamp = utc.format(formatter);
                    return timestamp;
                } else if (l > 300000000000000000L && l < 800000000000000000L) {
                    l /= 1000000000L;
                    ut = 978307200L + l;
                    utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(ut), ZoneOffset.UTC);
                    formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                    timestamp = utc.format(formatter);
                    return timestamp;
                } else if (l > 1262304000000L && l < 2524608000000L) {
                    utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC);
                    timestamp = utc.format(Auxiliary.formatter);
                    return timestamp;
                } else if (l > 1262304000000000L && l < 2524608000000000L) {
                    l /= 1000L;
                    utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC);
                    timestamp = utc.format(Auxiliary.formatter);
                    return timestamp;
                } else if (l > 1262304000000000000L && l < 2524608000000000000L) {
                    l /= 1000000L;
                    utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC);
                    timestamp = utc.format(Auxiliary.formatter);
                    return timestamp;
                } else {
                    return timestamp;
                }
            }
        } else {
            return "";
        }
    }

    private TimeStamp timestamp2String(SqliteElement en, byte[] value) {
        long time;
        ZonedDateTime utc;
        DateTimeFormatter formatter;
        String s;
        if (en.type == SerialTypes.INT48) {
            long l = SqliteElement.decodeInt48ToLong(value) / 100L;
            time = 978307200L + l;
            utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC);
            formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
            s = utc.format(formatter);
            return new TimeStamp(s, l);
        } else {
            ByteBuffer bf;
            if (en.type == SerialTypes.INT64) {
                bf = ByteBuffer.wrap(value);
                long l = bf.getLong();
                 utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(l), ZoneOffset.UTC);
                 formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                 s = utc.format(formatter);
                return new TimeStamp(s, l);
            } else if (en.type == SerialTypes.FLOAT64) {
                bf = ByteBuffer.wrap(value);
                double l = bf.getDouble();
                 time = 978307200L + (long)l;
                 utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC);
                 formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                 s = utc.format(formatter);
                return new TimeStamp(s, l);
            } else if (en.type == SerialTypes.INT32) {
                bf = ByteBuffer.wrap(value);
                int l = bf.getInt();
                time = 978307200L + (long)l;
                utc = ZonedDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.UTC);
                formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                s = utc.format(formatter);
                return new TimeStamp(s, l);
            } else {
                return new TimeStamp("null", 0);
            }
        }
    }

    private TimeStamp Real2String(SqliteElement en, byte[] value) {
        if (value.length == 0) {
            return null;
        } else {
            ByteBuffer bf = ByteBuffer.wrap(value);
            System.out.println("value length " + value.length);
            double d = bf.getDouble();
            ZonedDateTime utc = null;
            System.out.print("CFMACTime " + d);
            if (d > 3.0E8 && d < 8.0E8) {
                long l = Double.valueOf(d).longValue();
                System.out.print("CFMACTime as long" + l);
                l += 978307200L;
                l *= 1000L;
                System.out.println(" summe " + l);
                utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC);
                System.out.println("UTC " + String.valueOf(utc));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
                String s = utc.format(formatter);
                return new TimeStamp(s, d);
            } else {
                return null;
            }
        }
    }

    private TimeStamp Integer2String(SqliteElement en, byte[] value) {
        long l = -1L;
        ByteBuffer bf;
        if (en.type == SerialTypes.INT32) {
            System.out.println("INT32 zeit " + String.valueOf(en.type));
            bf = ByteBuffer.wrap(value);
            l = getUnsignedInt(bf.getInt());
        }

        if (en.type == SerialTypes.INT48) {
            System.out.println("INT48 zeit " + String.valueOf(en.type));
            l = SqliteElement.decodeInt48ToLong(value);
        }

        if (en.type == SerialTypes.INT64) {
            System.out.println("INT64 zeit " + String.valueOf(en.type));
            bf = ByteBuffer.wrap(value);
            l = bf.getLong();
        }

        int utc_type = 0;
        if (l > 1262304000000L && l < 2524608000000L) {
            utc_type = 1;
        } else if (l > 1262304000000000L && l < 2524608000000000L) {
            utc_type = 2;
        }

        if (utc_type > 0) {
            ZonedDateTime utc = null;
            if (utc_type == 1) {
                utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneOffset.UTC);
            } else if (utc_type == 2) {
                utc = ZonedDateTime.ofInstant(Instant.ofEpochMilli(l / 1000L), ZoneOffset.UTC);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
            String s = utc.format(formatter);
            System.out.println(" Unix Epoch: " + l);
            System.out.println(" UTC Epoch: " + s);
            System.out.println("time: " + s);
            return new TimeStamp(s, l);
        } else {
            return new TimeStamp(String.valueOf(l), l);
        }
    }

    public static long getUnsignedInt(int x) {
        return (long)x & 4294967295L;
    }

    private TableDescriptor matchTable(SqliteElement[] header) {
        Iterator<TableDescriptor> itds = this.job.headers.iterator();

        TableDescriptor table;
        boolean eq;
        do {
            do {
                if (!itds.hasNext()) {
                    return null;
                }

                table = (TableDescriptor)itds.next();
            } while(table.getColumntypes().size() != header.length);

            int idx = 0;
            eq = true;
            SqliteElement[] var9 = header;
            int var8 = header.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                SqliteElement s = var9[var7];
                String type = (String)table.getColumntypes().get(idx);
                if (!s.serial.name().equals(type)) {
                    eq = false;
                    break;
                }

                ++idx;
            }
        } while(!eq);

        return table;
    }

    private static Image scaledown(byte[] bf) {
        InputStream is = new ByteArrayInputStream(bf);
        BufferedImage image = null;

        try {
            image = ImageIO.read(is);
            if (image == null) {
                return null;
            } else {
                java.awt.Image scaled = image.getScaledInstance(100, 100, 2);
                BufferedImage bimage = new BufferedImage(100, 100, image.getType());
                Graphics2D bGr = bimage.createGraphics();
                bGr.drawImage(scaled, 0, 0, (ImageObserver)null);
                bGr.dispose();
                Image ii = SwingFXUtils.toFXImage(bimage, (WritableImage)null);
                return ii;
            }
        } catch (IOException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    private byte[] readOverflowIterativ(int pagenumber, boolean fromWAL) {
        List<ByteBuffer> parts = new LinkedList();
        boolean more = true;
        ByteBuffer overflowpage = null;
        int next = pagenumber;

        while(more) {
            if (fromWAL) {
                System.out.println(" >>>> Read Overflow Iterativ from WAL Archive");
                more = false;
                break;
            }

            overflowpage = this.job.readPageWithNumber(next, this.job.ps);
            if (overflowpage == null) {
                more = false;
                break;
            }

            overflowpage.position(0);
            next = overflowpage.getInt() - 1;
            this.info(" next overflow:: " + next);
            byte[] current = new byte[this.job.ps - 4];
            overflowpage.position(4);
            overflowpage.get(current, 0, this.job.ps - 4);
            ByteBuffer part = ByteBuffer.wrap(current);
            parts.add(part);
            if (next < 0 || next > this.job.numberofpages) {
                this.debug("No further overflow pages");
                more = false;
            }
        }

        if (parts != null && parts.size() != 0) {
            if (parts.size() == 1) {
                return ((ByteBuffer)parts.get(0)).array();
            } else {
                ByteBuffer fullContent = ByteBuffer.allocate(parts.stream().mapToInt(Buffer::capacity).sum());
                parts.forEach(fullContent::put);
                fullContent.flip();
                return fullContent.array();
            }
        } else {
            return ByteBuffer.allocate(0).array();
        }
    }

    public static byte[] decode(String s) {
        int len = s.length();
        byte[] r = new byte[len / 2];

        for(int i = 0; i < r.length; ++i) {
            int digit1 = s.charAt(i * 2);
            int digit2 = s.charAt(i * 2 + 1);
            if (digit1 >= 48 && digit1 <= 57) {
                digit1 -= 48;
            } else if (digit1 >= 97 && digit1 <= 102) {
                digit1 -= 87;
            }

            if (digit2 >= 48 && digit2 <= 57) {
                digit2 -= 48;
            } else if (digit2 >= 97 && digit2 <= 102) {
                digit2 -= 87;
            }

            r[i] = (byte)((digit1 << 4) + digit2);
        }

        return r;
    }

    public int computePLL(SqliteElement[] columns, int numberofcolumns, int headerlength) {
        int pll = 0;
        pll += headerlength;
        SqliteElement[] var8 = columns;
        int var7 = columns.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            SqliteElement e = var8[var6];
            if (e.type != SerialTypes.BLOB && e.type != SerialTypes.STRING) {
                pll += e.length;
            } else {
                pll += e.length * 2;
            }
        }

        return pll;
    }

    public int computePayloadLength(String header) {
        byte[] bcol = decode(header);
        int[] columns = readVarInt(bcol);
        int pll = 0;
        pll += header.length() / 2 + 1;

        for(int i = 0; i < columns.length; ++i) {
            switch (columns[i]) {
                case 0:
                case 8:
                case 9:
                case 10:
                case 11:
                    break;
                case 1:
                    ++pll;
                    break;
                case 2:
                    pll += 2;
                    break;
                case 3:
                    pll += 3;
                    break;
                case 4:
                    pll += 4;
                    break;
                case 5:
                    pll += 6;
                    break;
                case 6:
                    pll += 8;
                    break;
                case 7:
                    pll += 8;
                    break;
                default:
                    if (columns[i] % 2 == 0) {
                        pll += (columns[i] - 12) / 2;
                    } else {
                        pll += (columns[i] - 13) / 2;
                    }
            }
        }

        return pll;
    }

    public static SqliteElement[] toColumns(String header) {
        byte[] bcol = decode(header);
        return get(bcol);
    }

    private static SqliteElement[] MasterRecordToColumns(String header) {
        if (header.startsWith("07") || header.startsWith("06")) {
            header = header.substring(2);
        }

        byte[] bcol = decode(header);
        return getMaster(bcol);
    }

    public static int getPayloadLength(String header) {
        int sum = 0;
        SqliteElement[] cols = toColumns(header);
        SqliteElement[] var6 = cols;
        int var5 = cols.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            SqliteElement e = var6[var4];
            if (e != null) {
                sum += e.length;
            }
        }

        return sum;
    }

    public String getHeaderString(int headerlength, ByteBuffer buffer) {
        byte[] header = new byte[headerlength];

        try {
            buffer.get(header);
        } catch (Exception var5) {
            System.out.println("ERROR " + var5.toString());
        }

        String sheader = bytesToHex(header);
        return sheader;
    }

    public SqliteElement[] getColumns(int headerlength, ByteBuffer buffer, StringBuffer firstcol) throws IOException {
        byte[] header = new byte[headerlength];

        try {
            buffer.get(header);
        } catch (Exception var6) {
            System.out.println("Auxiliary::ERROR " + var6.toString());
            return null;
        }

        String sheader = bytesToHex(header);
        if (sheader.length() > 1) {
            firstcol.insert(0, sheader.substring(0, 2));
        }

        return get(header);
    }

    private static SqliteElement[] getMaster(byte[] header) {
        int[] columns = readMasterHeaderVarInts(header);
        return columns == null ? null : getElements(columns);
    }

    private static SqliteElement[] get(byte[] header) {
        int[] columns = readVarInt(header);
        return columns == null ? null : getElements(columns);
    }

    private static SqliteElement[] getElements(int[] columns) {
        SqliteElement[] column = new SqliteElement[columns.length];

        for(int i = 0; i < columns.length; ++i) {
            switch (columns[i]) {
                case 0:
                    column[i] = new SqliteElement(SerialTypes.PRIMARY_KEY, StorageClass.INT, 0);
                    break;
                case 1:
                    column[i] = new SqliteElement(SerialTypes.INT8, StorageClass.INT, 1);
                    break;
                case 2:
                    column[i] = new SqliteElement(SerialTypes.INT16, StorageClass.INT, 2);
                    break;
                case 3:
                    column[i] = new SqliteElement(SerialTypes.INT24, StorageClass.INT, 3);
                    break;
                case 4:
                    column[i] = new SqliteElement(SerialTypes.INT32, StorageClass.INT, 4);
                    break;
                case 5:
                    column[i] = new SqliteElement(SerialTypes.INT48, StorageClass.INT, 6);
                    break;
                case 6:
                    column[i] = new SqliteElement(SerialTypes.INT64, StorageClass.INT, 8);
                    break;
                case 7:
                    column[i] = new SqliteElement(SerialTypes.FLOAT64, StorageClass.FLOAT, 8);
                    break;
                case 8:
                    column[i] = new SqliteElement(SerialTypes.INT0, StorageClass.INT, 0);
                    break;
                case 9:
                    column[i] = new SqliteElement(SerialTypes.INT1, StorageClass.INT, 0);
                    break;
                case 10:
                    columns[i] = 0;
                case 11:
                    break;
                default:
                    if (columns[i] % 2 == 0) {
                        column[i] = new SqliteElement(SerialTypes.BLOB, StorageClass.BLOB, (columns[i] - 12) / 2);
                    } else {
                        column[i] = new SqliteElement(SerialTypes.STRING, StorageClass.TEXT, (columns[i] - 13) / 2);
                    }
            }
        }

        return column;
    }

    private int computePayload(int p) {
        int u = this.job.ps;
        int x = u - 35;
        int m = (u - 12) * 32 / 255 - 23;
        int k = m + (p - m) % (u - 4);
        if (p <= x) {
            return p;
        } else if (p > x && k <= x) {
            return k;
        } else {
            return p > x && k > x ? m : p;
        }
    }

    public static int readUnsignedVarInt(ByteBuffer buffer) {
        byte b = buffer.get();

        int value;
        for(value = b & 127; (b & 128) != 0; value |= b & 127) {
            b = buffer.get();
            value <<= 7;
        }

        return value;
    }

    public static int TwoByteBuffertoInt(ByteBuffer b) {
        byte[] ret = new byte[]{0, 0, b.get(0), b.get(1)};
        return ByteBuffer.wrap(ret).getInt();
    }

    public static int findNthOccur(String str, char ch, int N) {
        int occur = 0;

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == ch) {
                ++occur;
            }

            if (occur == N) {
                return i;
            }
        }

        return -1;
    }

    public static void addHeadPattern2Idx(IndexDescriptor id) {
        List<String> colnames = id.columnnames;
        List<String> coltypes = id.columntypes;
        HeaderPattern pattern = new HeaderPattern();
        pattern.addHeaderConstraint(colnames.size() + 1, colnames.size() + 1);
        ListIterator<String> list = coltypes.listIterator();

        while(list.hasNext()) {
            switch ((String)list.next()) {
                case "NUMERIC":
                    pattern.addNumericConstraint();
                    break;
                case "INT":
                    pattern.add(new IntegerConstraint(false));
                    break;
                case "BLOB":
                    pattern.addBLOBConstraint();
                    break;
                case "REAL":
                    pattern.addFloatingConstraint();
                    break;
                case "TEXT":
                    pattern.addStringConstraint();
            }
        }

        id.hpattern = pattern;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var5 = bytes;
        int var4 = bytes.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte hashByte = var5[var3];
            int intVal = 255 & hashByte;
            if (intVal < 16) {
                sb.append('0');
            }

            sb.append(Integer.toHexString(intVal));
        }

        return sb.toString();
    }

    public static String byteToHex(byte b) {
        byte[] ch = new byte[]{b};
        return bytesToHex(ch);
    }

    public static String bytesToHex(ByteBuffer bb) {
        int limit = bb.limit();
        char[] hexChars = new char[limit * 2];
        bb.position(0);

        for(int counter = 0; bb.position() < limit; ++counter) {
            int v = bb.get() & 255;
            hexChars[counter * 2] = hexArray[v >>> 4];
            hexChars[counter * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String bytesToHex(byte[] bytes, int fromidx, int toidx) {
        char[] hexChars = new char[(toidx - fromidx + 2) * 2];

        for(int j = 0; j < toidx; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static int[] readVarInt(byte[] values) {
        ByteBuffer in = ByteBuffer.wrap(values);
        LinkedList<Integer> res = new LinkedList();

        int n;
        do {
            if (in.hasRemaining()) {
                byte b = in.get();

                for(n = b & 127; (b & 128) != 0 && in.hasRemaining(); n |= b & 127) {
                    b = in.get();
                    n <<= 7;
                }

                res.add(n);
            }
        } while(in.position() < in.limit());

        int[] result = new int[res.size()];
        n = 0;

        for(Iterator<Integer> it = res.iterator(); it.hasNext(); ++n) {
            result[n] = (Integer)it.next();
        }

        return result;
    }

    public static int[] readMasterHeaderVarInts(byte[] values) {
        return Arrays.copyOfRange(readVarInt(values), 0, 5);
    }

    /** @deprecated */
    public static int[] readVarIntOld(byte[] values) {

        int counter = 0;

        LinkedList res;
        for(res = new LinkedList(); counter < values.length; ++counter) {
            byte var10000 = values[counter];
            int value = 0;

            byte b;
            while(((b = values[counter]) & 128) != 0) {
                int shift = 7;
                value |= (b & 127) << shift;
                ++counter;
                if (counter >= values.length) {
                    return null;
                }
            }

            res.add(value | b);
        }

        int[] result = new int[res.size()];
        int n = 0;

        for(Iterator<Integer> it = res.iterator(); it.hasNext(); ++n) {
            result[n] = (Integer)it.next();
        }

        return result;
    }

    public static String getSerial(SqliteElement[] columns) {
        String serial = "";
        SqliteElement[] var5 = columns;
        int var4 = columns.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            SqliteElement e = var5[var3];
            serial = serial + String.valueOf(e.serial);
        }

        return serial;
    }

    public static String getTableFingerPrint(SqliteElement[] columns) {
        String fp = "";
        SqliteElement[] var5 = columns;
        int var4 = columns.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            SqliteElement e = var5[var3];
            fp = fp + String.valueOf(e.type);
        }

        return fp;
    }

    static boolean contains(ByteBuffer bb, String searchText) {
        String text = new String(bb.array());
        return text.indexOf(searchText) > -1;
    }

    public static void printStackTrace() {
        System.out.println("Printing stack trace:");
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        for(int i = 1; i < elements.length; ++i) {
            StackTraceElement s = elements[i];
            PrintStream var10000 = System.out;
            String var10001 = s.getClassName();
            var10000.println("\tat " + var10001 + "." + s.getMethodName() + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
        }

    }

    public static int computePayloadLengthS(String header) {
        byte[] bcol = decode(header);
        int[] columns = readVarInt(bcol);
        int pll = 0;
        pll += header.length() / 2 + 1;

        for(int i = 0; i < columns.length; ++i) {
            switch (columns[i]) {
                case 0:
                case 8:
                case 9:
                case 10:
                case 11:
                    break;
                case 1:
                    ++pll;
                    break;
                case 2:
                    pll += 2;
                    break;
                case 3:
                    pll += 3;
                    break;
                case 4:
                    pll += 4;
                    break;
                case 5:
                    pll += 6;
                    break;
                case 6:
                    pll += 8;
                    break;
                case 7:
                    pll += 8;
                    break;
                default:
                    if (columns[i] % 2 == 0) {
                        pll += (columns[i] - 12) / 2;
                    } else {
                        pll += (columns[i] - 13) / 2;
                    }
            }
        }

        return pll;
    }

    public static int computePayloadS(int p, int ps) {
        int x = ps - 35;
        int m = (ps - 12) * 32 / 255 - 23;
        int k = m + (p - m) % (ps - 4);
        if (p <= x) {
            return p;
        } else if (p > x && k <= x) {
            return k;
        } else {
            return p > x && k > x ? m : p;
        }
    }

    public static String Int2Hex(int i) {
        return bytesToHex(new byte[]{(byte)(i >>> 24), (byte)(i >>> 16), (byte)(i >>> 8), (byte)i});
    }

    public static int varintHexString2Integer(String s) {
        byte[] value = hexStringToByteArray(s);
        ByteBuffer bb = ByteBuffer.wrap(value);
        return readUnsignedVarInt(bb);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static long String2long(String s) {
        long longInput = -1L;
        int var5 = -1;
        int var6 = 0;
        int length;
        if ((length = s.length()) != 0 && !s.equals("-") && !s.equals("+")) {
            BigDecimal bigdecimal = null;
            BigInteger biginteger = null;
            boolean var9 = false;
            boolean ishex = s.startsWith("0x") || s.startsWith("Ox") || s.startsWith("ox");
            String var11 = "yzafpnµm kMGTPEZY";
            String var12 = "KMGTPE";
            s.replaceAll(" ", "");
            if (1 < s.length() && !ishex) {
                if (var9 = 'i' == s.charAt(s.length() - 1)) {
                    var5 = var12.indexOf(s.charAt(s.length() - 2));
                } else {
                    int var18 = s.endsWith("c") ? -2 : (s.endsWith("d") ? -1 : (s.endsWith("da") ? 1 : (s.endsWith("h") ? 2 : (s.endsWith("%") ? -2 : 0))));
                    int var19 = var11.indexOf(s.charAt(s.length() - 1));
                    var6 = var18 != 0 ? var18 : (-1 < var19 ? var19 * 3 - 24 : 0);
                }
            }

            if (!var9 || s.length() >= 3 && var5 >= 0) {
                if (ishex) {
                    if (s.length() < 3) {
                        return -1L;
                    }

                    try {
                        biginteger = new BigInteger(s.substring(2, length), 16);
                    } catch (Exception var14) {
                        return -1L;
                    }
                } else {
                    while(length > 0) {
                        try {
                            bigdecimal = new BigDecimal(s.substring(0, length));
                            break;
                        } catch (Exception var15) {
                            --length;
                        }
                    }

                    if (length == 0 || bigdecimal == null) {
                        return -1L;
                    }

                    bigdecimal = bigdecimal.scaleByPowerOfTen(var6).multiply(BigDecimal.valueOf(1L << 10 * (var5 + 1)));
                    biginteger = bigdecimal.toBigInteger();
                }

                long var14 = biginteger.longValue();
                if (biginteger.signum() < 0) {
                    longInput = -1L;
                } else if (BigInteger.valueOf(Long.MAX_VALUE).compareTo(biginteger) < 0) {
                    longInput = Long.MAX_VALUE;
                } else {
                    longInput = var14;
                }
            }
        }

        return longInput;
    }

    public static byte[] readOverflow(Job job, int pagenumber) {
        byte[] part = null;
        ByteBuffer overflowpage = job.readPageWithNumber(pagenumber, job.ps);
        overflowpage.position(0);
        int overflow = overflowpage.getInt();
        System.out.println(" overflow:: " + overflow);
        if (overflow == 0) {
            System.out.println("No further overflow pages");
        } else {
            part = readOverflow(job, overflow);
        }

        byte[] current = new byte[job.ps - 4];
        overflowpage.position(4);
        overflowpage.get(current, 0, job.ps - 4);
        if (part != null) {
            byte[] of = new byte[current.length + part.length];
            System.arraycopy(current, 0, of, 0, current.length);
            System.arraycopy(part, 0, of, current.length, part.length);
            return of;
        } else {
            return current;
        }
    }

    public static int computeWALPageForOffset(int offset, int ps) {
        int temp = offset - 32;
        int pagenumber = temp / (24 + ps);
        return pagenumber;
    }

    public static String hex2ASCII(String hex) {
        hex = hex.replace("..", "");
        int idx = hex.indexOf("] ");
        hex.substring(0, idx);
        String tail = hex.substring(idx + 2);
        idx = hex.indexOf(">");
        if (idx > 0) {
            hex.substring(0, idx);
            tail = hex.substring(idx + 1);
        }

        StringBuilder output = new StringBuilder();

        for(int i = 0; i < tail.length(); i += 2) {
            String str = tail.substring(i, i + 2);
            char next = (char)Integer.parseInt(str, 16);
            if (next > 31 && next < 127) {
                output.append(next);
            } else {
                output.append('.');
            }
        }

        return output.toString();
    }

    public static String hex2ASCII_v2(String hex) {
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            char next = (char)Integer.parseInt(str, 16);
            if (next > 31 && next < 127) {
                output.append(next);
            } else {
                output.append('.');
            }
        }

        return output.toString();
    }

    public static boolean isWindowsSystem() {
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);
        return os.contains("Windows");
    }

    public static boolean isMacOS() {
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);
        return os.contains("Mac");
    }
}
