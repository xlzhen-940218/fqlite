//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.parser.SQLiteSchemaParser;
import fqlite.types.SerialTypes;
import fqlite.types.StorageClass;
import fqlite.util.Auxiliary;
import fqlite.util.CarvingResult;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class PageReader extends Base {
    public AtomicInteger found = new AtomicInteger();
    public AtomicInteger inrecover = new AtomicInteger();
    public static final String TABLELEAFPAGE = "0d";
    public static final String TABLEINTERIORPAGE = "05";
    public static final String INDEXLEAFPAGE = "0a";
    public static final String INDEXINTERIORPAGE = "02";
    public static final String OVERFLOWPAGE = "00";
    public Job job;

    public static int getPageType(String content) {
        boolean skip = false;
        switch (content.substring(0,2)) {
            case "00":
                return 0;
            case "02":
                skip = true;
                return 2;
            case "05":
                return 12;
            case "0a":
                skip = true;
                return 10;
            case "0d":
                return 8;
        }

        skip = true;
        return skip ? -1 : -99;
    }

    public PageReader(Job job) {
        this.job = job;
    }

    public void readMasterTableRecord(Job job, int start, ByteBuffer buffer, String header) throws IOException {
        buffer.position(start);
        SqliteElement[] columns = this.toColumns(header);
        if (columns != null) {
            int pll = Auxiliary.computePayloadLengthS(header);
            int so = Auxiliary.computePayloadS(pll, job.ps);

            int con;
            int bb;
            if (so < pll) {
                con = header.length() / 2;
                int last = buffer.position();
                this.debug(" spilled payload ::" + so);
                this.debug(" pll payload ::" + pll);
                buffer.position(buffer.position() + so - con - 1);
                int overflow = buffer.getInt();
                this.debug(" overflow::::::::: " + overflow + " " + Integer.toHexString(overflow));
                buffer.position(last);
                byte[] extended = this.readOverflow(overflow - 1);
                byte[] c = new byte[pll + job.ps];
                buffer.position(0);
                byte[] originalbuffer = new byte[job.ps];

                for(bb = 0; bb < job.ps; ++bb) {
                    originalbuffer[bb] = buffer.get(bb);
                }

                buffer.position(last);
                System.arraycopy(originalbuffer, buffer.position(), c, 0, so - con);
                System.arraycopy(extended, 0, c, so - con - 1, extended.length);
                ByteBuffer bf = ByteBuffer.wrap(c);
                buffer = bf;
                bf.position(0);
            }

            con = 0;
            String tablename = null;
            int rootpage = -1;
            String statement = null;
            SqliteElement[] var16 = columns;
            int var15 = columns.length;

            for(bb = 0; bb < var15; ++bb) {
                SqliteElement en = var16[bb];
                if (en != null) {
                    byte[] value = null;
                    if (con == 5) {
                        value = new byte[en.length];
                    } else {
                        value = new byte[en.length];
                    }

                    buffer.get(value);
                    if (con == 3) {
                        tablename = en.toString(value, true);
                    }

                    if (con == 4) {
                        rootpage = SqliteElement.decodeInt8(value[0]);
                    }

                    if (con == 5) {
                        statement = en.toString(value, true);
                    }

                    ++con;
                }
            }

            SQLiteSchemaParser.parse(job, tablename, rootpage, statement);
        }
    }

    public CarvingResult readDeletedRecord(Job job, int start, ByteBuffer buffer, String header, BitSet bs, int pagenumber) throws IOException {
        LinkedList<String> record = new LinkedList();
        buffer.position(start);
        int recordstart = start - header.length() / 2 - 2;
        SqliteElement[] columns = this.toColumns(header);
        if (columns == null) {
            return null;
        } else {
            int co = 0;
            String fp = null;

            try {
                fp = Auxiliary.getTableFingerPrint(columns);
            } catch (NullPointerException var24) {
            }

            if (fp == null) {
                fp = "unkown";
            }

            boolean error = false;
            int var10001 = (pagenumber - 1) * job.ps;
            record.add("" + (var10001 + buffer.position()));
            int pll = Auxiliary.computePayloadLengthS(header);
            int so = Auxiliary.computePayloadS(pll, job.ps);

            int phl;
            int last;
            byte[] value;
            if (so < pll) {
                phl = header.length() / 2;
                last = buffer.position();
                this.debug(" deleted spilled payload ::" + so);
                this.debug(" deleted pll payload ::" + pll);
                buffer.position(buffer.position() + so - phl - 1);
                int overflow = buffer.getInt();
                this.debug(" deleted overflow::::::::: " + overflow + " " + Integer.toHexString(overflow));
                buffer.position(last);
                ByteBuffer bf;
                if (overflow >= job.numberofpages) {
                    bf = buffer;
                } else {
                    byte[] extended = this.readOverflow(overflow - 1);
                    value = new byte[pll + job.ps];
                    buffer.position(0);
                    byte[] originalbuffer = new byte[job.ps];

                    for(int bb = 0; bb < job.ps; ++bb) {
                        originalbuffer[bb] = buffer.get(bb);
                    }

                    buffer.position(last);
                    System.arraycopy(originalbuffer, buffer.position(), value, 0, so - phl);
                    System.arraycopy(extended, 0, value, so - phl, extended.length - so);
                    bf = ByteBuffer.wrap(value);
                }

                bf.position(0);
                SqliteElement[] var32 = columns;
                int var31 = columns.length;

                for(int var30 = 0; var30 < var31; ++var30) {
                    SqliteElement en = var32[var30];
                    if (en != null) {
                        value = new byte[en.length];
                        bf.get(value);
                        record.add(en.toString(value, false));
                        ++co;
                    }
                }

                buffer.position(last + so - phl - 1);
            } else {
                SqliteElement[] var29 = columns;
                int var27 = columns.length;

                for(last = 0; last < var27; ++last) {
                    SqliteElement en = var29[last];
                    if (en != null) {
                        value = new byte[en.length];
                        if (buffer.position() + en.length > buffer.limit()) {
                            error = true;
                            return null;
                        }

                        buffer.get(value);
                        record.add(en.toString(value, false));
                        ++co;
                    }
                }

                if (error) {
                    return null;
                }
            }

            bs.set(recordstart, buffer.position() - 1, true);
            this.debug("Besucht :: " + recordstart + " bis " + buffer.position());
            phl = (pagenumber - 1) * job.ps + buffer.position();
            var10001 = (pagenumber - 1) * job.ps + recordstart;
            this.debug("Besucht :: " + var10001 + " bis " + phl);
            return new CarvingResult(buffer.position(), phl, new StringBuffer(), record);
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

    public byte[] readOverflow(int pagenumber) {
        byte[] part = null;
        ByteBuffer overflowpage = this.job.readPageWithNumber(pagenumber, this.job.ps);
        overflowpage.position(0);
        int overflow = overflowpage.getInt();
        this.debug(" overflow:: " + overflow);
        if (overflow == 0) {
            this.debug("No further overflow pages");
        } else {
            part = this.readOverflow(overflow);
        }

        byte[] current = new byte[this.job.ps - 4];
        overflowpage.position(4);
        overflowpage.get(current, 0, this.job.ps - 4);
        if (part != null) {
            byte[] of = new byte[current.length + part.length];
            System.arraycopy(current, 0, of, 0, current.length);
            System.arraycopy(part, 0, of, current.length, part.length);
            return of;
        } else {
            return current;
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

    public SqliteElement[] toColumns(String header) {
        byte[] bcol = Auxiliary.decode(header);
        return this.get(bcol);
    }

    public SqliteElement[] getColumns(int headerlength, ByteBuffer buffer) throws IOException {
        byte[] header = new byte[headerlength];

        try {
            buffer.get(header);
        } catch (Exception var5) {
            System.out.println("ERROR " + var5.toString());
        }

        this.debug("Header: " + Auxiliary.bytesToHex(header));
        return this.get(header);
    }

    private SqliteElement[] get(byte[] header) {
        int[] columns = Auxiliary.readVarInt(header);
        if (columns == null) {
            return null;
        } else {
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
                    case 11:
                        columns[i] = 0;
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
    }

    public int readUnsignedVarInt(ByteBuffer buffer) throws IOException {
        return Auxiliary.readUnsignedVarInt(buffer);
    }

    public static int TwoByteBuffertoInt(ByteBuffer b) {
        short v = b.getShort();
        int iv = v >= 0 ? v : 65536 + v;
        return iv;
    }
}
