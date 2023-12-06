package goryachev.common.util;

import goryachev.common.io.DReader;
import goryachev.common.io.DWriterBytes;
import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/HiddenData.class */
public class HiddenData {
    public static byte[] encode(byte[] b) throws Exception {
        DReader in = new DReader(b);
        Random r = new Random();
        long seed = r.nextLong();
        r.setSeed(seed);
        DWriterBytes wr = new DWriterBytes();
        wr.writeLong(seed);
        while (true) {
            int c = in.readByteRaw();
            if (c >= 0) {
                wr.writeByte(c ^ r.nextInt());
            } else {
                return wr.toByteArray();
            }
        }
    }

    public static byte[] decode(byte[] b) throws Exception {
        DReader in = new DReader(b);
        long seed = in.readLong();
        Random r = new Random();
        r.setSeed(seed);
        DWriterBytes wr = new DWriterBytes();
        while (true) {
            int c = in.readByteRaw();
            if (c >= 0) {
                wr.writeByte(c ^ r.nextInt());
            } else {
                return wr.toByteArray();
            }
        }
    }
}
