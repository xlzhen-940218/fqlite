package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPLUid.class */
class BPLUid implements BPListElement<Integer> {
    private final int number;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPLUid(int number) {
        this.number = number;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.UID;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Integer getValue() {
        return Integer.valueOf(this.number);
    }
}
