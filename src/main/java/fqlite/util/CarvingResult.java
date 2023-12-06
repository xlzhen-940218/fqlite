//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.util.LinkedList;

public class CarvingResult {
    public StringBuffer bf;
    public int rcursor;
    public int offset;
    public LinkedList<String> record;

    public CarvingResult(int rcursor, int offset, StringBuffer result, LinkedList<String> record) {
        this.bf = result;
        this.rcursor = rcursor;
        this.offset = offset;
        this.record = record;
    }
}
