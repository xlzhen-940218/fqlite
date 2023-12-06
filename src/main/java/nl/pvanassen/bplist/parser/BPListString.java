package nl.pvanassen.bplist.parser;

import java.io.UnsupportedEncodingException;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListString.class */
class BPListString implements BPListElement<String> {
    private final String value;
    private final BPListType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListString(char[] buf) {
        this.value = new String(buf);
        this.type = BPListType.UNICODE_STRING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPListString(byte[] buf) throws UnsupportedEncodingException {
        this.value = new String(buf, "ASCII");
        this.type = BPListType.ASCII_STRING;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return this.type;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public String getValue() {
        return this.value;
    }
}
