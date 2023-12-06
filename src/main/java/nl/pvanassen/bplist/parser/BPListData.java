package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListData.class */
class BPListData implements BPListElement<byte[]> {
    private final byte[] value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListData(byte[] value) {
        this.value = value;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.DATA;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public byte[] getValue() {
        return this.value;
    }
}
