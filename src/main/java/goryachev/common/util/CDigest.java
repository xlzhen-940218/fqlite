package goryachev.common.util;

import goryachev.common.util.api.IMessageDigest;
import goryachev.common.util.api.IMessageDigestBlake2b;
import java.io.InputStream;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CDigest.class */
public class CDigest {
    private final IMessageDigest md;

    protected CDigest(IMessageDigest md) {
        this.md = md;
    }

    public static CDigest sha256() {
        IMessageDigest md = IMessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
        return new CDigest(md);
    }

    public static CDigest sha512() {
        IMessageDigest md = IMessageDigest.getInstance(MessageDigestAlgorithms.SHA_512);
        return new CDigest(md);
    }

    public static CDigest blake2b(int bits) {
        try {
            IMessageDigestBlake2b md = (IMessageDigestBlake2b) Lookup.get(IMessageDigestBlake2b.class);
            return new CDigest(md);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void update(byte b) {
        this.md.update(b);
    }

    public void updateByte(int b) {
        this.md.update((byte) b);
    }

    public void update(byte[] b) {
        this.md.update(b, 0, b.length);
    }

    public void update(byte[] b, int offset, int len) {
        this.md.update(b, offset, len);
    }

    public void updateInt(int x) {
        this.md.update((byte) (x >> 24));
        this.md.update((byte) (x >> 16));
        this.md.update((byte) (x >> 8));
        this.md.update((byte) x);
    }

    public void updateLong(long x) {
        this.md.update((byte) (x >> 56));
        this.md.update((byte) (x >> 48));
        this.md.update((byte) (x >> 40));
        this.md.update((byte) (x >> 32));
        this.md.update((byte) (x >> 24));
        this.md.update((byte) (x >> 16));
        this.md.update((byte) (x >> 8));
        this.md.update((byte) x);
    }

    public void updateChar(char c) {
        this.md.update((byte) (c >> '\b'));
        this.md.update((byte) c);
    }

    public void updateCharArray(char[] cs) {
        if (cs == null) {
            updateInt(-1);
            return;
        }
        int sz = cs.length;
        updateInt(sz);
        for (char c : cs) {
            updateChar(c);
        }
    }

    public void updateString(String s) {
        if (s == null) {
            updateInt(-1);
            return;
        }
        updateInt(s.length());
        byte[] b = s.getBytes(CKit.CHARSET_UTF8);
        this.md.update(b, 0, b.length);
    }

    public byte[] digest() {
        return this.md.digest();
    }

    public void reset() {
        this.md.reset();
    }

    public byte[] compute(byte[] bytes) {
        update(bytes);
        return digest();
    }

    public byte[] compute(InputStream in) throws Exception {
        byte[] buf = new byte[4096];
        while (true) {
            CKit.checkCancelled();
            int rd = in.read(buf);
            if (rd < 0) {
                return digest();
            }
            if (rd > 0) {
                update(buf, 0, rd);
            }
        }
    }
}
