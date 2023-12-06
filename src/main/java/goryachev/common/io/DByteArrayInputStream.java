package goryachev.common.io;

import java.io.ByteArrayInputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DByteArrayInputStream.class */
public class DByteArrayInputStream extends ByteArrayInputStream {
    public DByteArrayInputStream(byte[] b) {
        super(b);
    }

    public int getPosition() {
        return this.pos;
    }
}
