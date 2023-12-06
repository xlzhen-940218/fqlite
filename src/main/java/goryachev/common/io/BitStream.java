package goryachev.common.io;

import java.io.EOFException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitStream.class */
public class BitStream implements IBitStream {
    private byte[] bytes;
    private int index;
    private static final int[] MASK = {128, 64, 32, 16, 8, 4, 2, 1};

    public BitStream(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override // goryachev.common.io.IBitStream
    public int getIndex() {
        return this.index;
    }

    @Override // goryachev.common.io.IBitStream
    public boolean hasMoreBits() {
        return this.index < this.bytes.length * 8;
    }

    @Override // goryachev.common.io.IBitStream
    public int nextBits(int count) throws Exception {
        int d = 0;
        while (count > 0) {
            d = (d << 1) | nextBit();
            count--;
        }
        return d;
    }

    @Override // goryachev.common.io.IBitStream
    public int nextBit() throws Exception {
        int byteIndex = this.index / 8;
        if (byteIndex >= this.bytes.length) {
            throw new EOFException();
        }
        int offset = this.index - (byteIndex * 8);
        this.index++;
        if ((MASK[offset] & this.bytes[byteIndex]) == 0) {
            return 0;
        }
        return 1;
    }

    @Override // goryachev.common.io.IBitStream
    public void skip(int bits) {
        if (bits < 0) {
            throw new IllegalArgumentException();
        }
        this.index += bits;
    }
}
