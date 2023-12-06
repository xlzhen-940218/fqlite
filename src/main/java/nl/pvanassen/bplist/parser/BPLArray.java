package nl.pvanassen.bplist.parser;

import java.util.ArrayList;
import java.util.List;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPLArray.class */
class BPLArray implements BPListElement<List<BPListElement<?>>> {
    private final List<BPListElement<?>> objectTable;
    private final int[] objref;
    private final BPListType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BPLArray(List<BPListElement<?>> objectTable, int[] objref, BPListType type) {
        this.objectTable = objectTable;
        this.objref = objref;
        this.type = type;
    }

    @Override // nl.pvanassen.bplist.parser.BPListElement
    public BPListType getType() {
        return this.type;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // nl.pvanassen.bplist.parser.BPListElement
    public List<BPListElement<?>> getValue() {
        int[] iArr;
        List<BPListElement<?>> array = new ArrayList<>(this.objref.length);
        for (int objr : this.objref) {
            array.add(this.objectTable.get(objr));
        }
        return array;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("Array{");
        for (int i = 0; i < this.objref.length; i++) {
            if (i > 0) {
                buf.append(',');
            }
            if (this.objectTable.size() > this.objref[i] && this.objectTable.get(this.objref[i]) != this) {
                buf.append(this.objectTable.get(this.objref[i]));
            } else {
                buf.append("*" + this.objref[i]);
            }
        }
        buf.append('}');
        return buf.toString();
    }
}
