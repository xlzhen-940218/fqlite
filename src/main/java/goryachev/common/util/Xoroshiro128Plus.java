package goryachev.common.util;

import java.io.Serializable;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Xoroshiro128Plus.class */
public final class Xoroshiro128Plus implements Serializable {
    private static final long DOUBLE_MASK = 9007199254740991L;
    private static final double NORM_53 = 1.1102230246251565E-16d;
    private static final long FLOAT_MASK = 16777215;
    private static final double NORM_24 = 5.960464477539063E-8d;
    private static final long serialVersionUID = 1018744536171610262L;
    private long state0;
    private long state1;

    public Xoroshiro128Plus() {
        this(((long) ((Math.random() - 0.5d) * 4.503599627370496E15d)) ^ ((long) (((Math.random() - 0.5d) * 2.0d) * (-9.223372036854776E18d))), ((long) ((Math.random() - 0.5d) * 4.503599627370496E15d)) ^ ((long) (((Math.random() - 0.5d) * 2.0d) * (-9.223372036854776E18d))));
    }

    public Xoroshiro128Plus(long seed) {
        setSeed(seed);
    }

    public Xoroshiro128Plus(long stateA, long stateB) {
        setSeed(stateA, stateB);
    }

    public final int next(int bits) {
        long s0 = this.state0;
        long s1 = this.state1;
        int result = ((int) (s0 + s1)) >>> (32 - bits);
        long s12 = s1 ^ s0;
        this.state0 = (((s0 << 55) | (s0 >>> 9)) ^ s12) ^ (s12 << 14);
        this.state1 = (s12 << 36) | (s12 >>> 28);
        return result;
    }

    public final long nextLong() {
        long s0 = this.state0;
        long s1 = this.state1;
        long result = s0 + s1;
        long s12 = s1 ^ s0;
        this.state0 = (((s0 << 55) | (s0 >>> 9)) ^ s12) ^ (s12 << 14);
        this.state1 = (s12 << 36) | (s12 >>> 28);
        return result;
    }

    public Xoroshiro128Plus copy() {
        Xoroshiro128Plus next = new Xoroshiro128Plus(this.state0);
        next.state0 = this.state0;
        next.state1 = this.state1;
        return next;
    }

    public int nextInt() {
        return (int) nextLong();
    }

    public int nextInt(int bound) {
        int bits;
        if (bound <= 0) {
            return 0;
        }
        int threshold = ((Integer.MAX_VALUE - bound) + 1) % bound;
        do {
            bits = (int) (nextLong() & 2147483647L);
        } while (bits < threshold);
        return bits % bound;
    }

    public int nextInt(int lower, int upper) {
        if (upper - lower <= 0) {
            throw new IllegalArgumentException("Upper bound must be greater than lower bound");
        }
        return lower + nextInt(upper - lower);
    }

    public long nextLong(long bound) {
        long bits;
        if (bound <= 0) {
            return 0L;
        }
        long threshold = ((Long.MAX_VALUE - bound) + 1) % bound;
        do {
            bits = nextLong() & Long.MAX_VALUE;
        } while (bits < threshold);
        return bits % bound;
    }

    public double nextDouble() {
        return (nextLong() & DOUBLE_MASK) * NORM_53;
    }

    public float nextFloat() {
        return (float) ((nextLong() & FLOAT_MASK) * NORM_24);
    }

    public boolean nextBoolean() {
        return nextLong() < 0;
    }

    public void nextBytes(byte[] bytes) {
        int i = bytes.length;
        while (i != 0) {
            int n = Math.min(i, 8);
            long nextLong = nextLong();
            while (true) {
                long bits = nextLong;
                int i2 = n;
                n--;
                if (i2 == 0) {
                    break;
                }
                i--;
                bytes[i] = (byte) bits;
                nextLong = bits >>> 8;
            }
        }
    }

    public void setSeed(long seed) {
        long state = seed - 7046029254386353131L;
        long z = (state ^ (state >>> 30)) * (-4658895280553007687L);
        long z2 = (z ^ (z >>> 27)) * (-7723592293110705685L);
        this.state0 = z2 ^ (z2 >>> 31);
        long state2 = state - 7046029254386353131L;
        long z3 = (state2 ^ (state2 >>> 30)) * (-4658895280553007687L);
        long z4 = (z3 ^ (z3 >>> 27)) * (-7723592293110705685L);
        this.state1 = z4 ^ (z4 >>> 31);
    }

    public void setSeed(long stateA, long stateB) {
        this.state0 = stateA;
        this.state1 = stateB;
        if ((stateA | stateB) == 0) {
            this.state0 = 1L;
        }
    }

    public long getStateA() {
        return this.state0;
    }

    public long getStateB() {
        return this.state1;
    }

    public String toString() {
        return "XoRoRNG with stateA 0x" + Hex.toHexString(this.state0) + "L and stateB 0x" + Hex.toHexString(this.state1) + 'L';
    }

    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (x == null || getClass() != x.getClass()) {
            return false;
        }
        Xoroshiro128Plus xoRoRNG = (Xoroshiro128Plus) x;
        return this.state0 == xoRoRNG.state0 && this.state1 == xoRoRNG.state1;
    }

    public int hashCode() {
        return (int) ((31 * (this.state0 ^ (this.state0 >>> 32))) + (this.state1 ^ (this.state1 >>> 32)));
    }
}
