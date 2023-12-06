package goryachev.common.io;

import goryachev.common.util.CKit;
import java.io.ByteArrayOutputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitWriter.class */
public class BitWriter extends BitStreamWriter {
    private ByteArrayOutputStream stream;

    public BitWriter() {
        this(32);
    }

    public BitWriter(int sizeInBytes) {
        this.stream = new ByteArrayOutputStream(sizeInBytes);
        setOutputStream(this.stream);
    }

    public byte[] toByteArray() {
        CKit.close(this);
        return this.stream.toByteArray();
    }
}
