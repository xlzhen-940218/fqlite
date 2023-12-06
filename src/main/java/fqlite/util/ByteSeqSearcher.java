//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class ByteSeqSearcher {
    private byte[] pattern_;
    private int[] borders_;
    public static final int MAX_PATTERN_LENGTH = 2048;

    public ByteSeqSearcher(byte[] pattern) {
        this.setPattern(pattern);
    }

    public void setPattern(byte[] pattern) {
        this.pattern_ = Arrays.copyOf(pattern, pattern.length);
        this.borders_ = new int[this.pattern_.length + 1];
        this.preProcess();
    }

    public int indexOf(ByteBuffer buffer, int start) {
        buffer.position(start);
        return this.indexOf(buffer);
    }

    public int indexOf(ByteBuffer buffer) {
        int j = 0;

        do {
            if (buffer.position() >= buffer.limit()) {
                return -1;
            }

            for(int b = buffer.get(); j >= 0 && (byte)b != this.pattern_[j]; j = this.borders_[j]) {
            }

            ++j;
        } while(j != this.pattern_.length);

        return buffer.position();
    }

    private void preProcess() {
        int i = 0;
        int j = -1;

        for(this.borders_[i] = j; i < this.pattern_.length; this.borders_[i] = j) {
            while(j >= 0 && this.pattern_[i] != this.pattern_[j]) {
                j = this.borders_[j];
            }

            ++i;
            ++j;
        }

    }
}
