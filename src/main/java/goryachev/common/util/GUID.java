package goryachev.common.util;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/GUID.class */
public class GUID {
    private static final int INITIAL_RANDOMNESS_BYTES = 32;
    private static final int SEP = 255;
    private static byte[] machineID;
    private static AtomicLong seq = new AtomicLong();

    public static byte[] generate() {
        CDigest d = CDigest.sha256();
        return generate(d);
    }

    public static byte[] generate(CDigest d) {
        d.update(machineID());
        d.updateByte(255);
        d.updateLong(System.currentTimeMillis());
        d.updateByte(255);
        d.updateLong(System.nanoTime());
        d.updateByte(255);
        d.updateLong(seq.incrementAndGet());
        return d.digest();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<goryachev.common.util.GUID>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    private static byte[] machineID() {
        if (machineID == null) {
            Object r0 = GUID.class;
            synchronized (r0) {
                if (machineID == null) {
                    byte[] b = new byte[32];
                    new SecureRandom().nextBytes(b);
                    CDigest d = CDigest.sha256();
                    d.update(b);
                    Runtime r = Runtime.getRuntime();
                    d.updateInt(r.availableProcessors());
                    d.updateByte(255);
                    d.updateLong(r.freeMemory());
                    d.updateByte(255);
                    d.updateInt(r.hashCode());
                    d.updateByte(255);
                    d.updateString(System.getProperty("java.runtime.version"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("java.class.path"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("os.arch"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("os.name"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("os.version"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.country"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.dir"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.home"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.language"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.name"));
                    d.updateByte(255);
                    d.updateString(System.getProperty("user.timezone"));
                    machineID = d.digest();
                }
                r0 = r0;
            }
        }
        return machineID;
    }
}
