package goryachev.common.util;

import java.util.BitSet;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BloomFilterMurmur3.class */
public class BloomFilterMurmur3 {
    private static final double LN2 = Math.log(2.0d);
    private final int hashFunctionCount;
    private final int bitSetSize;
    private final BitSet bits;

    public BloomFilterMurmur3(double falsePositiveProbability, int capacity) {
        this((int) Math.ceil(-(Math.log(falsePositiveProbability) / LN2)), (int) Math.ceil(capacity * (Math.ceil(-(Math.log(falsePositiveProbability) / LN2)) / LN2)));
    }

    public BloomFilterMurmur3(int hashFunctionCount, int bitSetSize) {
        this.hashFunctionCount = hashFunctionCount;
        this.bitSetSize = bitSetSize;
        this.bits = new BitSet(bitSetSize);
    }

    public BloomFilterMurmur3(int hashFunctionCount, byte[] b) {
        this.hashFunctionCount = hashFunctionCount;
        this.bitSetSize = b.length * 8;
        this.bits = BitSet.valueOf(b);
    }

    public int getHashFunctionCount() {
        return this.hashFunctionCount;
    }

    public int getBitSetSize() {
        return this.bitSetSize;
    }

    public void add(byte[] data) {
        for (int i = 0; i < this.hashFunctionCount; i++) {
            int hash = MurmurHash3.hash(data, 0, data.length, i);
            int ix = Math.abs(hash % this.bitSetSize);
            this.bits.set(ix, true);
        }
    }

    public void add(CharSequence data) {
        for (int i = 0; i < this.hashFunctionCount; i++) {
            int hash = MurmurHash3.hash(data, 0, data.length(), i);
            int ix = Math.abs(hash % this.bitSetSize);
            this.bits.set(ix, true);
        }
    }

    public boolean contains(byte[] data) {
        for (int i = 0; i < this.hashFunctionCount; i++) {
            int hash = MurmurHash3.hash(data, 0, data.length, i);
            int ix = Math.abs(hash % this.bitSetSize);
            if (!this.bits.get(ix)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(CharSequence data) {
        for (int i = 0; i < this.hashFunctionCount; i++) {
            int hash = MurmurHash3.hash(data, 0, data.length(), i);
            int ix = Math.abs(hash % this.bitSetSize);
            if (!this.bits.get(ix)) {
                return false;
            }
        }
        return true;
    }

    public byte[] toByteArray() {
        int sz = this.bitSetSize / 8;
        byte[] b = this.bits.toByteArray();
        if (b.length == sz) {
            return b;
        }
        byte[] rv = new byte[sz];
        System.arraycopy(b, 0, rv, 0, b.length);
        return rv;
    }
}
