package goryachev.fx;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxCtl.class */
public enum FxCtl {
    BOLD,
    EDITABLE,
    FOCUSABLE,
    FORCE_MAX_WIDTH,
    FORCE_MIN_HEIGHT,
    FORCE_MIN_WIDTH,
    NON_EDITABLE,
    NON_FOCUSABLE,
    WRAP_TEXT;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static FxCtl[] valuesCustom() {
        FxCtl[] valuesCustom = values();
        int length = valuesCustom.length;
        FxCtl[] fxCtlArr = new FxCtl[length];
        System.arraycopy(valuesCustom, 0, fxCtlArr, 0, length);
        return fxCtlArr;
    }
}
