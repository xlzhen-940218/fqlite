package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/FileTypes.class */
public enum FileTypes {
    SQLiteDB,
    WriteAheadLog,
    RollbackJournalLog;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static FileTypes[] valuesCustom() {
        FileTypes[] valuesCustom = values();
        int length = valuesCustom.length;
        FileTypes[] fileTypesArr = new FileTypes[length];
        System.arraycopy(valuesCustom, 0, fileTypesArr, 0, length);
        return fileTypesArr;
    }
}
