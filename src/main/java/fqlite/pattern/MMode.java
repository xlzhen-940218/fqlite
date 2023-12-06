package fqlite.pattern;

/* loaded from: fqlite_next.jar:fqlite/pattern/MMode.class */
public enum MMode {
    NORMAL,
    NOHEADER,
    NO1stCOL;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static MMode[] valuesCustom() {
        MMode[] valuesCustom = values();
        int length = valuesCustom.length;
        MMode[] mModeArr = new MMode[length];
        System.arraycopy(valuesCustom, 0, mModeArr, 0, length);
        return mModeArr;
    }
}
