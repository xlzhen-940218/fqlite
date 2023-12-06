package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListFloat.class */
class BPListFloat implements BPListElement<Float> {
    private final float value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListFloat(float value) {
        this.value = value;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.FLOAT;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Float getValue() {
        return Float.valueOf(this.value);
    }
}
