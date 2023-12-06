package goryachev.common.util.text;

import goryachev.common.util.TextTools;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/SimpleWordCounter.class */
public class SimpleWordCounter {
    private final String text;
    private Type type;
    private int count;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/SimpleWordCounter$Type.class */
    public enum Type {
        CJK,
        Space,
        Word;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static Type[] valuesCustom() {
            Type[] valuesCustom = values();
            int length = valuesCustom.length;
            Type[] typeArr = new Type[length];
            System.arraycopy(valuesCustom, 0, typeArr, 0, length);
            return typeArr;
        }
    }

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type() {
        int[] iArr = $SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[Type.valuesCustom().length];
        try {
            iArr2[Type.CJK.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[Type.Space.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[Type.Word.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type = iArr2;
        return iArr2;
    }

    public SimpleWordCounter(String text) {
        this.text = text;
    }

    public int countWords() {
        this.count = 0;
        int len = this.text.length();
        this.type = Type.Space;
        for (int i = 0; i < len; i++) {
            char c = this.text.charAt(i);
            Type t = getType(c);
            switch ($SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type()[t.ordinal()]) {
                case 1:
                    this.count++;
                    break;
                case 3:
                    if (this.type != t) {
                        this.count++;
                        break;
                    } else {
                        break;
                    }
            }
            this.type = t;
        }
        return this.count;
    }

    protected void cnt() {
        switch ($SWITCH_TABLE$goryachev$common$util$text$SimpleWordCounter$Type()[this.type.ordinal()]) {
            case 1:
            case 3:
                this.count++;
                return;
            case 2:
            default:
                return;
        }
    }

    protected Type getType(char c) {
        if (TextTools.isWhitespace(c)) {
            return Type.Space;
        }
        if (TextTools.isWordDelimiter(c)) {
            return Type.Space;
        }
        if (TextTools.isCJK(c)) {
            return Type.CJK;
        }
        return Type.Word;
    }

    public static int count(String s) {
        if (s == null) {
            return 0;
        }
        return new SimpleWordCounter(s).countWords();
    }
}
