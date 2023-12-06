package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListBoolean.class */
class BPListBoolean implements BPListElement<Boolean> {
    private final Boolean value;
    static BPListBoolean TRUE = new BPListBoolean(Boolean.TRUE);
    static BPListBoolean FALSE = new BPListBoolean(Boolean.FALSE);

    BPListBoolean(Boolean value) {
        this.value = value;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.BOOLEAN;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Boolean getValue() {
        return this.value;
    }
}
