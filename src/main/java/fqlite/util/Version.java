//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.util.LinkedList;

public class Version {
    int hash;
    LinkedList<String> record;

    Version(int hash, LinkedList<String> record) {
        this.hash = hash;
        this.record = record;
    }

    Version(LinkedList<String> record) {
        this.hash = Auxiliary.computeHash(record);
        this.record = record;
    }
}
