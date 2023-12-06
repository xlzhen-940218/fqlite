package goryachev.common.io;

import java.io.ByteArrayInputStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/BitReader.class */
public class BitReader extends BitStreamReader {
    public BitReader(byte[] bytes) {
        super(new ByteArrayInputStream(bytes));
    }
}
