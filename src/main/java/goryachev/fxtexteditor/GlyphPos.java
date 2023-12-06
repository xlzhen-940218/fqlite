package goryachev.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/GlyphPos.class */
public class GlyphPos {
    public final int line;
    public final int glyphIndex;

    public GlyphPos(int line, int glyphIndex) {
        this.line = line;
        this.glyphIndex = glyphIndex;
    }

    public int getLine() {
        return this.line;
    }

    public int getGlyphIndex() {
        return this.glyphIndex;
    }
}
