package fqlite.pattern;

import fqlite.base.Global;
import fqlite.util.Auxiliary;
import java.nio.ByteBuffer;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:fqlite/pattern/SerialTypeMatcher.class */
public class SerialTypeMatcher {
    ByteBuffer buffer;
    int endRegion;
    public int start;
    int end;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$fqlite$pattern$MMode;
    HeaderPattern pattern = null;
    int pos = 0;
    MMode mode = MMode.NORMAL;
    public String fallbackFor1stColumn = "02";
    int startRegion = 0;

    static /* synthetic */ int[] $SWITCH_TABLE$fqlite$pattern$MMode() {
        int[] iArr = $SWITCH_TABLE$fqlite$pattern$MMode;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[MMode.valuesCustom().length];
        try {
            iArr2[MMode.NO1stCOL.ordinal()] = 3;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[MMode.NOHEADER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[MMode.NORMAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$fqlite$pattern$MMode = iArr2;
        return iArr2;
    }

    public SerialTypeMatcher(ByteBuffer buffer) {
        this.buffer = null;
        this.buffer = buffer;
        this.endRegion = buffer.capacity();
        buffer.position(0);
    }

    public void setMatchingMode(MMode newMode) {
        this.mode = newMode;
    }

    public MMode getMachtingMode() {
        return this.mode;
    }

    public void setPattern(HeaderPattern pattern) {
        this.pattern = pattern;
    }

    public void region(int from, int to) {
        this.startRegion = from;
        this.endRegion = to;
        this.buffer.position(from);
    }

    public int end() {
        return this.end;
    }

    public int start() {
        return this.start;
    }

    public boolean find() {
        int idx = 0;
        switch ($SWITCH_TABLE$fqlite$pattern$MMode()[this.mode.ordinal()]) {
            case 1:
                idx = 0;
                break;
            case 2:
                idx = 1;
                break;
            case 3:
                idx = 2;
                break;
        }
        int i = idx;
        while (i < this.pattern.size()) {
            if (this.buffer.position() > this.endRegion - 4) {
                return false;
            }
            int current = this.buffer.position();
            if (i == idx) {
                this.pos = current;
            }
            int value = readUnsignedVarInt();
            if (value == -1 || !this.pattern.get(i).match(value)) {
                this.buffer.position(this.pos + 1);
                i = idx;
            } else {
                i++;
            }
        }
        this.start = this.pos;
        this.end = this.buffer.position();
        if (this.end <= this.start) {
            return false;
        }
        return true;
    }

    public ByteBuffer group() {
        byte[] match = new byte[this.end - this.start];
        this.buffer.position(this.start);
        this.buffer.get(match, 0, this.end - this.start);
        return ByteBuffer.wrap(match);
    }

    public String substring(int start, int end) {
        if (start > end) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        byte[] match = new byte[end - start];
        this.buffer.position(start);
        this.buffer.get(match, 0, end - start);
        return Auxiliary.bytesToHex(match);
    }

    public String group2Hex() {
        return substring(this.start, this.end);
    }

    public int readUnsignedVarInt() {
        int b;
        int value = 0;
        int counter = 0;
        int shift = 0;
        while (true) {
            b = this.buffer.get();
            if ((b & 128) == 0 || counter >= 3) {
                break;
            }
            counter++;
            shift += 7;
            value |= (b & 127) << shift;
        }
        if ((b & 128) != 0) {
            return -1;
        }
        return value | b;
    }

    public static void main(String[] args) {
        String hexstring = "00000000 210A0603 151D0407 00C35A4C 75697348 6572726D 616E6E47 A0081441 EA353168 BAA2DB22 09".replaceAll(Global.REGULAR_RECORD, ButtonBar.BUTTON_ORDER_NONE);
        byte[] barray = Auxiliary.hexStringToByteArray(hexstring);
        ByteBuffer buffer = ByteBuffer.wrap(barray);
        SerialTypeMatcher stm = new SerialTypeMatcher(buffer);
        HeaderPattern pattern = new HeaderPattern();
        pattern.addHeaderConstraint(6, 10);
        pattern.addIntegerConstraint();
        pattern.addStringConstraint();
        pattern.addStringConstraint();
        pattern.addIntegerConstraint();
        pattern.addFloatingConstraint();
        stm.setPattern(pattern);
        while (stm.find()) {
            String m = stm.group2Hex();
            System.out.println("Got it! :: " + m);
        }
    }
}
