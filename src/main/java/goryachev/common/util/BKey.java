package goryachev.common.util;

import java.util.Arrays;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/BKey.class */
public class BKey {
    private byte[] key;
    private int hash;

    public BKey(byte[] k) {
        this.key = (byte[]) k.clone();
    }

    public BKey(BKey other) {
        this.key = other.key;
        this.hash = other.hash;
    }

    public String toString() {
        return toHexString();
    }

    public String toHexString() {
        return Hex.toHexString(this.key);
    }

    public byte[] toByteArray() {
        return (byte[]) this.key.clone();
    }

    public int sizeInBytes() {
        return this.key.length;
    }

    public static BKey parse(Object x) throws Exception {
        if (x instanceof BKey) {
            return (BKey) x;
        }
        if (x instanceof String) {
            String s = (String) x;
            byte[] b = Parsers.parseByteArray(s);
            return new BKey(b);
        } else if (x instanceof byte[]) {
            return new BKey((byte[]) x);
        } else {
            throw new Exception();
        }
    }

    public static BKey parseQuiet(Object x) {
        try {
            return parse(x);
        } catch (Exception e) {
            return null;
        }
    }

    public String getShortString() {
        SB sb = new SB();
        Hex.appendHexString(sb, this.key, 0, Math.min(5, sizeInBytes()));
        return sb.toString();
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof BKey) {
            BKey other = (BKey) x;
            if (hashCode() == other.hashCode()) {
                return Arrays.equals(this.key, other.key);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        if (this.hash == 0) {
            int h = FH.hash(BKey.class);
            this.hash = FH.hash(h, this.key);
        }
        return this.hash;
    }
}
