package fqlite.base;

/* loaded from: fqlite_next.jar:fqlite/base/Gap.class */
public class Gap {
    int from;
    int to;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Gap(int fromIdx, int toIdx) {
        this.from = fromIdx;
        this.to = toIdx;
    }

    public boolean equals(Gap g) {
        return this.from == g.from && this.to == g.to;
    }
}
