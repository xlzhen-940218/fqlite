package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/StorageClass.class */
public enum StorageClass {
    INT,
    FLOAT,
    BLOB,
    TEXT;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static StorageClass[] valuesCustom() {
        StorageClass[] valuesCustom = values();
        int length = valuesCustom.length;
        StorageClass[] storageClassArr = new StorageClass[length];
        System.arraycopy(valuesCustom, 0, storageClassArr, 0, length);
        return storageClassArr;
    }
}
