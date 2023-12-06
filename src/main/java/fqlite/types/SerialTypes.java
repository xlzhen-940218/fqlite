package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/SerialTypes.class */
public enum SerialTypes {
    PRIMARY_KEY,
    INT8,
    INT16,
    INT24,
    INT32,
    INT48,
    INT64,
    FLOAT64,
    INT0,
    INT1,
    NOTUSED1,
    NOTUSED2,
    BLOB,
    STRING;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static SerialTypes[] valuesCustom() {
        SerialTypes[] valuesCustom = values();
        int length = valuesCustom.length;
        SerialTypes[] serialTypesArr = new SerialTypes[length];
        System.arraycopy(valuesCustom, 0, serialTypesArr, 0, length);
        return serialTypesArr;
    }
}
