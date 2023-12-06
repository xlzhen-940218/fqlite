package goryachev.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/GlyphType.class */
public enum GlyphType {
    EOF,
    EOL,
    REG,
    TAB;

    /* renamed from: values  reason: to resolve conflict with enum method */
    public static GlyphType[] valuesCustom() {
        GlyphType[] valuesCustom = values();
        int length = valuesCustom.length;
        GlyphType[] glyphTypeArr = new GlyphType[length];
        System.arraycopy(valuesCustom, 0, glyphTypeArr, 0, length);
        return glyphTypeArr;
    }
}
