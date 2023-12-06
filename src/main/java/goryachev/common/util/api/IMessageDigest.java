package goryachev.common.util.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/api/IMessageDigest.class */
public interface IMessageDigest {
    void update(byte b);

    void update(byte[] bArr, int i, int i2);

    void reset();

    byte[] digest();

    static IMessageDigest getInstance(String algorithm) {
        try {
            final MessageDigest md = MessageDigest.getInstance(algorithm);
            return new IMessageDigest() { // from class: goryachev.common.util.api.IMessageDigest.1
                @Override // goryachev.common.util.api.IMessageDigest
                public void update(byte[] buf, int offset, int length) {
                    md.update(buf, offset, length);
                }

                @Override // goryachev.common.util.api.IMessageDigest
                public void update(byte b) {
                    md.update(b);
                }

                @Override // goryachev.common.util.api.IMessageDigest
                public void reset() {
                    md.reset();
                }

                @Override // goryachev.common.util.api.IMessageDigest
                public byte[] digest() {
                    return md.digest();
                }
            };
        } catch (NoSuchAlgorithmException e) {
            throw new Error(e);
        }
    }
}
