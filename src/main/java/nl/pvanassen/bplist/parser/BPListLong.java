package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListLong.class */
class BPListLong implements BPListElement<Long> {
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListLong(long value) {
        this.value = value;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.LONG;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Long getValue() {
        return Long.valueOf(this.value);
    }
}
