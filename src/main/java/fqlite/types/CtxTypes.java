package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/CtxTypes.class */
public enum CtxTypes {
    ROOT,
    DATABASE,
    TABLE;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static CtxTypes[] valuesCustom() {
        CtxTypes[] valuesCustom = values();
        int length = valuesCustom.length;
        CtxTypes[] ctxTypesArr = new CtxTypes[length];
        System.arraycopy(valuesCustom, 0, ctxTypesArr, 0, length);
        return ctxTypesArr;
    }
}
