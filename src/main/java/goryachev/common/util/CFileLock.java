package goryachev.common.util;

import goryachev.common.log.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/CFileLock.class */
public class CFileLock {
    protected static final Log log = Log.get("CFileLock");
    private File file;
    private FileChannel channel;
    private FileLock lock;

    public CFileLock(File file) {
        this.file = file;
    }

    public boolean lock() {
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
            }
            this.channel = new RandomAccessFile(this.file, "rw").getChannel();
            this.lock = this.channel.tryLock();
            if (this.lock != null) {
                return true;
            }
        } catch (Exception e) {
            log.error((Throwable) e);
        }
        unlock();
        return false;
    }

    public void unlock() {
        if (this.lock != null) {
            try {
                this.lock.release();
            } catch (Exception e) {
                log.error((Throwable) e);
            }
            this.lock = null;
        }
        if (this.channel != null) {
            CKit.close(this.channel);
            this.channel = null;
            try {
                this.file.delete();
            } catch (Exception e2) {
                log.error((Throwable) e2);
            }
        }
    }

    public void checkLock() throws CFileLockedException {
        if (!lock()) {
            throw new CFileLockedException();
        }
    }
}
