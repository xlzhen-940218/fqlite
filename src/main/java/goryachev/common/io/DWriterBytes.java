package goryachev.common.io;

import java.io.ByteArrayOutputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/DWriterBytes.class */
public class DWriterBytes extends DWriter {
    public DWriterBytes() {
        super(new ByteArrayOutputStream());
    }

    public byte[] toByteArray() {
        return getByteArrayOutputStream().toByteArray();
    }

    private ByteArrayOutputStream getByteArrayOutputStream() {
        return (ByteArrayOutputStream) this.out;
    }
}
