package goryachev.common.util.text;

import goryachev.common.util.CKit;
import goryachev.common.util.ElasticIntArray;
import java.util.Locale;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/FindOperation.class */
public class FindOperation {
    private final Locale locale;
    private final String pattern;
    private final boolean caseSensitive;
    private final boolean ignoreAccents;
    private final boolean wholeWords;

    public FindOperation(Locale locale, String pattern, boolean caseSensitive, boolean ignoreAccents, boolean wholeWords) {
        this.locale = locale;
        this.caseSensitive = caseSensitive;
        this.ignoreAccents = ignoreAccents;
        this.wholeWords = wholeWords;
        this.pattern = normalize(pattern);
    }

    protected String normalize(String s) {
        if (s == null) {
            return null;
        }
        if (!this.caseSensitive) {
            s = s.toLowerCase(this.locale);
        }
        if (this.ignoreAccents) {
            s = AccentedCharacters.removeAccents(s);
        }
        return s;
    }

    protected static boolean isWholeWord(String a, int ix, int len) {
        if (ix > 0 && !isWordSpace(a.charAt(ix - 1))) {
            return false;
        }
        int ix2 = ix + len;
        if (ix2 < a.length() && !isWordSpace(a.charAt(ix2))) {
            return false;
        }
        return true;
    }

    public static boolean isWordSpace(char c) {
        return !Character.isLetterOrDigit(c);
    }

    public FindOperationResult find(String text) {
        if (CKit.isBlank(text)) {
            return null;
        }
        String text2 = normalize(text);
        int ix = text2.indexOf(this.pattern);
        if (ix < 0) {
            return null;
        }
        ElasticIntArray ixs = null;
        while (ix >= 0) {
            if (this.wholeWords && !isWholeWord(text2, ix, this.pattern.length())) {
                ix = text2.indexOf(this.pattern, ix + 1);
            } else {
                if (ixs == null) {
                    ixs = new ElasticIntArray(16);
                }
                ixs.add(ix);
                ix = text2.indexOf(this.pattern, ix + this.pattern.length());
            }
        }
        if (ixs == null) {
            return null;
        }
        return new FindOperationResult(text2, this.pattern, ixs.toArray());
    }
}
