package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListDouble.class */
class BPListDouble implements BPListElement<Double> {
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListDouble(double value) {
        this.value = value;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return BPListType.FLOAT;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Double getValue() {
        return Double.valueOf(this.value);
    }
}
