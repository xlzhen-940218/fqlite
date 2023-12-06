package nl.pvanassen.bplist.parser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/ElementParser.class */
public class ElementParser {
    public List<BPListElement<?>> parseObjectTable(File file) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            List<BPListElement<?>> parseObjectTable = parseObjectTable(raf);
            IOUtils.closeQuietly(raf);
            return parseObjectTable;
        } catch (Throwable th) {
            IOUtils.closeQuietly(raf);
            throw th;
        }
    }

    private List<BPListElement<?>> parseObjectTable(RandomAccessFile raf) throws IOException {
        int bpli = raf.readInt();
        int st00 = raf.readInt();
        if (bpli != 1651534953 || st00 != 1936994352) {
            throw new IOException("parseHeader: File does not start with 'bplist00' magic.");
        }
        raf.seek(raf.length() - 32);
        raf.readLong();
        int refCount = (int) raf.readLong();
        raf.readLong();
        int topLevelOffset = (int) raf.readLong();
        raf.seek(8L);
        byte[] buf = new byte[topLevelOffset - 8];
        raf.readFully(buf);
        ByteArrayInputStream stream = new ByteArrayInputStream(buf);
        return parseObjectTable(new DataInputStream(stream), refCount);
    }

    private List<BPListElement<?>> parseObjectTable(DataInputStream in, int refCount) throws IOException {
        List<BPListElement<?>> objectTable = new LinkedList<>();
        while (true) {
            int marker = in.read();
            if (marker != -1) {
                switch ((marker & 240) >> 4) {
                    case 0:
                        parseBoolean(marker & 15, objectTable);
                        break;
                    case 1:
                        parseInt(in, 1 << (marker & 15), objectTable);
                        break;
                    case 2:
                        parseReal(in, 1 << (marker & 15), objectTable);
                        break;
                    case 3:
                        switch (marker & 15) {
                            case 3:
                                parseDate(in, objectTable);
                                break;
                            default:
                                throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                        }
                    case 4:
                        int count = marker & 15;
                        if (count == 15) {
                            count = readCount(in);
                        }
                        parseData(in, count, objectTable);
                        break;
                    case 5:
                        int count2 = marker & 15;
                        if (count2 == 15) {
                            count2 = readCount(in);
                        }
                        parseAsciiString(in, count2, objectTable);
                        break;
                    case 6:
                        int count3 = marker & 15;
                        if (count3 == 15) {
                            count3 = readCount(in);
                        }
                        parseUnicodeString(in, count3, objectTable);
                        break;
                    case 7:
                        return objectTable;
                    case 8:
                        parseUID(in, (marker & 15) + 1, objectTable);
                        break;
                    case 9:
                        throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                    case 10:
                        int count4 = marker & 15;
                        if (count4 == 15) {
                            count4 = readCount(in);
                        }
                        if (refCount > 255) {
                            parseShortArray(in, count4, objectTable);
                            break;
                        } else {
                            parseByteArray(in, count4, objectTable);
                            break;
                        }
                    case 11:
                        throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                    case 12:
                        throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                    case 13:
                        int count5 = marker & 15;
                        if (count5 == 15) {
                            count5 = readCount(in);
                        }
                        if (refCount > 256) {
                            parseShortDict(in, count5, objectTable);
                            break;
                        } else {
                            parseByteDict(in, count5, objectTable);
                            break;
                        }
                    case 14:
                        throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                    case 15:
                        throw new IOException("parseObjectTable: illegal marker " + Integer.toBinaryString(marker));
                }
            } else {
                return objectTable;
            }
        }
    }

    private int readCount(DataInputStream in) throws IOException {
        int marker = in.read();
        if (marker == -1) {
            throw new IOException("variableLengthInt: Illegal EOF in marker");
        }
        if (((marker & 240) >> 4) != 1) {
            throw new IOException("variableLengthInt: Illegal marker " + Integer.toBinaryString(marker));
        }
        int count = 1 << (marker & 15);
        int value = 0;
        for (int i = 0; i < count; i++) {
            int b = in.read();
            if (b == -1) {
                throw new IOException("variableLengthInt: Illegal EOF in value");
            }
            value = (value << 8) | b;
        }
        return value;
    }

    private void parseBoolean(int primitive, List<BPListElement<?>> objectTable) throws IOException {
        switch (primitive) {
            case 0:
                objectTable.add(null);
                return;
            case 8:
                objectTable.add(BPListBoolean.FALSE);
                return;
            case 9:
                objectTable.add(BPListBoolean.TRUE);
                return;
            case 15:
                return;
            default:
                throw new IOException("parsePrimitive: illegal primitive " + Integer.toBinaryString(primitive));
        }
    }

    private void parseByteArray(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        int[] objref = new int[count];
        for (int i = 0; i < count; i++) {
            objref[i] = in.readByte() & 255;
            if (objref[i] == -1) {
                throw new IOException("parseByteArray: illegal EOF in objref*");
            }
        }
        objectTable.add(new BPLArray(objectTable, objref, BPListType.BYTE_ARRAY));
    }

    private void parseShortArray(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        int[] objref = new int[count];
        for (int i = 0; i < count; i++) {
            objref[i] = in.readShort() & 65535;
            if (objref[i] == -1) {
                throw new IOException("parseShortArray: illegal EOF in objref*");
            }
        }
        objectTable.add(new BPLArray(objectTable, objref, BPListType.SHORT_ARRAY));
    }

    private void parseData(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        byte[] data = new byte[count];
        in.readFully(data);
        objectTable.add(new BPListData(data));
    }

    private void parseByteDict(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        int[] keyref = new int[count];
        int[] objref = new int[count];
        for (int i = 0; i < count; i++) {
            keyref[i] = in.readByte() & 255;
        }
        for (int i2 = 0; i2 < count; i2++) {
            objref[i2] = in.readByte() & 255;
        }
        objectTable.add(new BPLDict(objectTable, keyref, objref, BPListType.BYTE_DICT));
    }

    private void parseShortDict(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        int[] keyref = new int[count];
        int[] objref = new int[count];
        for (int i = 0; i < count; i++) {
            keyref[i] = in.readShort() & 65535;
        }
        for (int i2 = 0; i2 < count; i2++) {
            objref[i2] = in.readShort() & 65535;
        }
        objectTable.add(new BPLDict(objectTable, keyref, objref, BPListType.SHORT_DICT));
    }

    private void parseAsciiString(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        byte[] buf = new byte[count];
        in.readFully(buf);
        objectTable.add(new BPListString(buf));
    }

    private void parseUID(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        if (count > 4) {
            throw new IOException("parseUID: unsupported byte count: " + count);
        }
        byte[] uid = new byte[count];
        in.readFully(uid);
        objectTable.add(new BPLUid(new BigInteger(uid).intValue()));
    }

    private void parseInt(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        if (count > 8) {
            throw new IOException("parseInt: unsupported byte count: " + count);
        }
        long value = 0;
        for (int i = 0; i < count; i++) {
            int b = in.read();
            if (b == -1) {
                throw new IOException("parseInt: Illegal EOF in value");
            }
            value = (value << 8) | b;
        }
        objectTable.add(new BPListLong(value));
    }

    private void parseReal(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        switch (count) {
            case 4:
                objectTable.add(new BPListFloat(in.readFloat()));
                return;
            case 5:
            case 6:
            case 7:
            default:
                throw new IOException("parseReal: unsupported byte count:" + count);
            case 8:
                objectTable.add(new BPListDouble(in.readDouble()));
                return;
        }
    }

    private void parseDate(DataInputStream in, List<BPListElement<?>> objectTable) throws IOException {
        objectTable.add(new BPListDate(in.readDouble()));
    }

    private void parseUnicodeString(DataInputStream in, int count, List<BPListElement<?>> objectTable) throws IOException {
        char[] buf = new char[count];
        for (int i = 0; i < count; i++) {
            buf[i] = in.readChar();
        }
        objectTable.add(new BPListString(buf));
    }
}
