package goryachev.common.util.html;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/html/AbstractHtmlParser.class */
public abstract class AbstractHtmlParser {
    public final String text;
    private int line = 1;
    private int offset;
    private int startOffset;
    private boolean skipLF;
    private boolean inBreak;
    private boolean inComment;
    private boolean inTags;
    private boolean inScript;
    private boolean tagWillEnd;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$util$html$AbstractHtmlParser$HtmlSegment;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/html/AbstractHtmlParser$HtmlSegment.class */
    public enum HtmlSegment {
        Break,
        Comment,
        Script,
        Tag,
        Text;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static HtmlSegment[] valuesCustom() {
            HtmlSegment[] valuesCustom = values();
            int length = valuesCustom.length;
            HtmlSegment[] htmlSegmentArr = new HtmlSegment[length];
            System.arraycopy(valuesCustom, 0, htmlSegmentArr, 0, length);
            return htmlSegmentArr;
        }
    }

    public abstract void addBreak(int i, int i2, int i3, String str, String str2);

    public abstract void addComment(int i, int i2, int i3, String str, String str2);

    public abstract void addScript(int i, int i2, int i3, String str, String str2);

    public abstract void addTag(int i, int i2, int i3, String str, String str2);

    public abstract void addText(int i, int i2, int i3, String str, String str2);

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$util$html$AbstractHtmlParser$HtmlSegment() {
        int[] iArr = $SWITCH_TABLE$goryachev$common$util$html$AbstractHtmlParser$HtmlSegment;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[HtmlSegment.valuesCustom().length];
        try {
            iArr2[HtmlSegment.Break.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[HtmlSegment.Comment.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[HtmlSegment.Script.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[HtmlSegment.Tag.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[HtmlSegment.Text.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        $SWITCH_TABLE$goryachev$common$util$html$AbstractHtmlParser$HtmlSegment = iArr2;
        return iArr2;
    }

    public AbstractHtmlParser(String text) {
        this.text = text;
    }

    public int getLineCount() {
        return this.line;
    }

    public void parse() throws Exception {
        int length = this.text.length();
        this.offset = 0;
        while (this.offset < length) {
            boolean breakStarts = false;
            boolean breakEnds = false;
            boolean commentStarts = false;
            boolean commentEnds = false;
            boolean scriptStarts = false;
            boolean scriptEnds = false;
            boolean tagStarts = false;
            boolean tagEnds = this.tagWillEnd;
            if (tagEnds) {
                this.tagWillEnd = false;
                if (this.inComment) {
                    if (isCommentEndTag()) {
                        commentEnds = true;
                    }
                } else if (isScriptEndTag()) {
                    scriptEnds = true;
                }
            }
            char c = this.text.charAt(this.offset);
            switch (c) {
                case '\n':
                    if (this.skipLF) {
                        this.skipLF = false;
                        break;
                    } else {
                        this.line++;
                        breakStarts = true;
                        break;
                    }
                case '\r':
                    this.line++;
                    this.skipLF = true;
                    breakStarts = true;
                    break;
                case '<':
                    breakEnds = true;
                    if (!this.inComment || commentEnds) {
                        if (isCommentTag()) {
                            commentStarts = true;
                            break;
                        } else if (isScriptTag()) {
                            scriptStarts = true;
                            break;
                        } else if (scriptEnds || !this.inScript) {
                            tagStarts = true;
                            break;
                        }
                    }
                    break;
                case '>':
                    breakEnds = true;
                    this.tagWillEnd = true;
                    break;
                default:
                    breakEnds = true;
                    break;
            }
            boolean addChunk = (this.inBreak && breakEnds) || (!this.inBreak && breakStarts) || ((this.inComment && commentEnds) || ((!this.inComment && commentStarts) || ((this.inScript && scriptEnds) || ((!this.inScript && scriptStarts) || ((!this.inTags && tagStarts) || (this.inTags && tagEnds))))));
            if (addChunk) {
                addChunk();
            }
            if (breakStarts) {
                this.inBreak = true;
            } else if (breakEnds) {
                this.inBreak = false;
            }
            if (commentStarts) {
                this.inComment = true;
            } else if (commentEnds) {
                this.inComment = false;
            }
            if (scriptStarts) {
                this.inScript = true;
                this.inComment = false;
            } else if (scriptEnds) {
                this.inScript = false;
            }
            if (tagStarts) {
                this.inTags = true;
                this.inComment = false;
                this.inScript = false;
            } else if (tagEnds) {
                this.inTags = false;
            }
            this.offset++;
        }
        addChunk();
    }

    protected void addChunk() {
        if (this.inBreak) {
            addChunk(HtmlSegment.Break);
            this.inBreak = false;
        } else if (this.inComment) {
            addChunk(HtmlSegment.Comment);
        } else if (this.inScript) {
            addChunk(HtmlSegment.Script);
        } else if (this.inTags) {
            addChunk(HtmlSegment.Tag);
        } else {
            addChunk(HtmlSegment.Text);
        }
    }

    protected void addChunk(HtmlSegment type) {
        int endOffset = this.offset;
        if (endOffset > this.startOffset) {
            String original = this.text.substring(this.startOffset, endOffset);
            String unicode = HtmlTools.decodeHtmlCharacterEntities(original);
            switch ($SWITCH_TABLE$goryachev$common$util$html$AbstractHtmlParser$HtmlSegment()[type.ordinal()]) {
                case 1:
                    addBreak(this.startOffset, endOffset, this.line, original, unicode);
                    break;
                case 2:
                    addComment(this.startOffset, endOffset, this.line, original, unicode);
                    break;
                case 3:
                    addScript(this.startOffset, endOffset, this.line, original, unicode);
                    break;
                case 4:
                    addTag(this.startOffset, endOffset, this.line, original, unicode);
                    break;
                case 5:
                    addText(this.startOffset, endOffset, this.line, original, unicode);
                    break;
            }
            this.startOffset = endOffset;
        }
    }

    protected boolean isCommentTag() {
        try {
            return isStartOf("<!--");
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isScriptTag() {
        return isTagToRight("<script") || isTagToRight("<style");
    }

    protected boolean isTagToRight(String tag) {
        try {
            if (tag.equalsIgnoreCase(this.text.substring(this.offset, this.offset + tag.length()))) {
                char c = this.text.charAt(this.offset + tag.length());
                if (c != '>') {
                    return Character.isWhitespace(c);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isScriptEndTag() {
        return isEndOf("</script>") || isEndOf("</style>");
    }

    protected boolean isCommentEndTag() {
        return isEndOf("-->");
    }

    protected boolean isEndOf(String tag) {
        try {
            return tag.equals(this.text.substring(this.offset - tag.length(), this.offset));
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isStartOf(String tag) {
        try {
            return tag.equals(this.text.substring(this.offset, this.offset + tag.length()));
        } catch (Exception e) {
            return false;
        }
    }
}
