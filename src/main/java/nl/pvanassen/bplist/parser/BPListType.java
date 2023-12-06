package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListType.class */
public enum BPListType {
    BOOLEAN,
    LONG,
    FLOAT,
    DOUBLE,
    DATE,
    DATA,
    ASCII_STRING,
    UNICODE_STRING,
    UID,
    SHORT_DICT,
    BYTE_DICT,
    SHORT_ARRAY,
    BYTE_ARRAY;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static BPListType[] valuesCustom() {
        BPListType[] valuesCustom = values();
        int length = valuesCustom.length;
        BPListType[] bPListTypeArr = new BPListType[length];
        System.arraycopy(valuesCustom, 0, bPListTypeArr, 0, length);
        return bPListTypeArr;
    }
}
