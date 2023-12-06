package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.GlyphType;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/TextCell.class */
public class TextCell {
    private static final TextCell globalInstance = new TextCell();
    private GlyphType type;
    private int caretCharIndex;
    private int leadingCharIndex;
    private int insertCharIndex;
    private int glyphIndex;

    public TextCell(GlyphType type, int caretCharIndex, int leadingCharIndex, int insertCharIndex, int glyphIndex) {
        this.type = type;
        this.caretCharIndex = caretCharIndex;
        this.leadingCharIndex = leadingCharIndex;
        this.insertCharIndex = insertCharIndex;
        this.glyphIndex = glyphIndex;
    }

    public TextCell() {
    }

    public String toString() {
        return "TextCell[type=" + this.type + ", caret=" + this.caretCharIndex + ", leading=" + this.leadingCharIndex + ", insert=" + this.insertCharIndex + ", glyph=" + this.glyphIndex + "]";
    }

    public void set(GlyphType type, int caretCharIndex, int leadingCharIndex, int insertCharIndex, int glyphIndex) {
        this.type = type;
        this.caretCharIndex = caretCharIndex;
        this.leadingCharIndex = leadingCharIndex;
        this.insertCharIndex = insertCharIndex;
        this.glyphIndex = glyphIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void set(TextCell c) {
        this.type = c.type;
        this.caretCharIndex = c.caretCharIndex;
        this.leadingCharIndex = c.leadingCharIndex;
        this.insertCharIndex = c.insertCharIndex;
        this.glyphIndex = c.glyphIndex;
    }

    public static TextCell globalInstance() {
        return globalInstance;
    }

    public GlyphType getGlyphType() {
        return this.type;
    }

    public int getCaretCharIndex() {
        return this.caretCharIndex;
    }

    public int getLeadingEdgeCharIndex() {
        return this.leadingCharIndex;
    }

    public int getGlyphIndex() {
        return this.glyphIndex;
    }

    public int getInsertCharIndex() {
        return this.insertCharIndex;
    }

    public int getTabSpan() {
        throw new Error();
    }
}
