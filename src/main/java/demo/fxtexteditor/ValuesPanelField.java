package demo.fxtexteditor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ValuePanel.java */
/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/ValuesPanelField.class */
public enum ValuesPanelField {
    BINARY0,
    BINARY1,
    BINARY2,
    BINARY3,
    BINARY4,
    BINARY5,
    BINARY6,
    BINARY7,
    BYTE,
    WORD,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    CHARACTER,
    STRING;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static ValuesPanelField[] valuesCustom() {
        ValuesPanelField[] valuesCustom = values();
        int length = valuesCustom.length;
        ValuesPanelField[] valuesPanelFieldArr = new ValuesPanelField[length];
        System.arraycopy(valuesCustom, 0, valuesPanelFieldArr, 0, length);
        return valuesPanelFieldArr;
    }
}
