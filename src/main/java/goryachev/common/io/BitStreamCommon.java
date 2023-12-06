package goryachev.common.io;



import demo.fxtexteditor.ValuePanel;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitStreamCommon.class */
public class BitStreamCommon {
    protected static final int BITS_PER_BYTE = 8;
    protected static final int[] MASK = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511
            , 1023, (1 << 11) - 1, 4095, 8191, 16383, ValuePanel.SWORD_MAX_VALUE
            , 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607
            ,0x00ffffff, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};

    public static int getMask(int bits) {
        return MASK[bits];
    }
}
