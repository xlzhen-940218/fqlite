//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

public class Match {
    public String match;
    public int begin;
    public int end;
    public int rowidcolum = -1;

    public Match(String match, int begin, int end) {
        this.match = match;
        this.begin = begin;
        this.end = end;
    }

    public static boolean onlyZeros(String s) {
        for(int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }
}
