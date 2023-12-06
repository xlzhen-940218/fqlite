package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/BLOBTYPE.class */
public enum BLOBTYPE {
    UNKOWN,
    PNG,
    JPG,
    GIF,
    TIFF,
    BMP,
    PDF,
    HEIC,
    PLIST,
    GZIP;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static BLOBTYPE[] valuesCustom() {
        BLOBTYPE[] valuesCustom = values();
        int length = valuesCustom.length;
        BLOBTYPE[] blobtypeArr = new BLOBTYPE[length];
        System.arraycopy(valuesCustom, 0, blobtypeArr, 0, length);
        return blobtypeArr;
    }
}
