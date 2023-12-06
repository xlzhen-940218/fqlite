package nl.pvanassen.bplist.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPLDict.class */
class BPLDict implements BPListElement<Map<String, BPListElement<?>>> {
    private final List<BPListElement<?>> objectTable;
    private final int[] keyref;
    private final int[] objref;
    private final BPListType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPLDict(List<BPListElement<?>> objectTable, int[] keyref, int[] objref, BPListType type) {
        this.objectTable = objectTable;
        this.keyref = keyref;
        this.objref = objref;
        this.type = type;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return this.type;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public Map<String, BPListElement<?>> getValue() {
        Map<String, BPListElement<?>> dict = new HashMap<>();
        for (int idx = 0; idx != this.keyref.length; idx++) {
            int key = this.keyref[idx];
            int obj = this.objref[idx];
            dict.put(this.objectTable.get(key).getValue().toString(), this.objectTable.get(obj));
        }
        return dict;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("BPLDict{");
        for (int i = 0; i < this.keyref.length; i++) {
            if (i > 0) {
                buf.append(',');
            }
            if (this.keyref[i] < 0 || this.keyref[i] >= this.objectTable.size()) {
                buf.append("#" + this.keyref[i]);
            } else if (this.objectTable.get(this.keyref[i]) == this) {
                buf.append("*" + this.keyref[i]);
            } else {
                buf.append(this.objectTable.get(this.keyref[i]));
            }
            buf.append(":");
            if (this.objref[i] < 0 || this.objref[i] >= this.objectTable.size()) {
                buf.append("#" + this.objref[i]);
            } else if (this.objectTable.get(this.objref[i]) == this) {
                buf.append("*" + this.objref[i]);
            } else {
                buf.append(this.objectTable.get(this.objref[i]));
            }
        }
        buf.append('}');
        return buf.toString();
    }
}
