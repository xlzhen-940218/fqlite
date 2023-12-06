package goryachev.common.util.text;

import fqlite.base.Global;
import goryachev.common.util.SB;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/FindOperationResult.class */
public class FindOperationResult {
    private final String original;
    private final String pattern;
    private final int[] indexes;

    public FindOperationResult(String original, String pattern, int[] indexes) {
        this.original = original;
        this.pattern = pattern;
        this.indexes = indexes;
    }

    public String replace(String replace) {
        int[] iArr;
        SB sb = new SB(2 * this.original.length());
        int start = 0;
        for (int ix : this.indexes) {
            if (start < ix) {
                sb.append(this.original.substring(start, ix));
                start = ix;
            }
            sb.append(replace);
            start += this.pattern.length();
        }
        if (start != this.original.length()) {
            sb.append(this.original.substring(start, this.original.length()));
        }
        return sb.toString();
    }

    protected void safeAppend(SB sb, String s) {
        int start = sb.length();
        sb.append(s);
        for (int i = sb.length() - 1; i >= start; i--) {
            switch (sb.charAt(i)) {
                case '\t':
                case '\n':
                case '\r':
                    sb.replace(i, i + 1, Global.REGULAR_RECORD);
                    break;
                case ' ':
                    sb.replace(i, i + 1, "&nbsp;");
                    break;
                case '&':
                    sb.replace(i, i + 1, "&#38;");
                    break;
                case '<':
                    sb.replace(i, i + 1, "&#60;");
                    break;
            }
        }
    }

    public String highlight(String replace) {
        return highlight(replace, false);
    }

    public String highlight(String replace, boolean bold) {
        int[] iArr;
        SB sb = new SB(3 * this.original.length());
        sb.append("<html>");
        int start = 0;
        for (int ix : this.indexes) {
            if (start < ix) {
                safeAppend(sb, this.original.substring(start, ix));
                start = ix;
            }
            sb.append("<span style='background-color:#ffff00'>");
            if (bold) {
                sb.a("<b>");
            }
            if (replace == null) {
                safeAppend(sb, this.original.substring(ix, ix + this.pattern.length()));
            } else {
                safeAppend(sb, replace);
            }
            if (bold) {
                sb.a("</b>");
            }
            sb.append("</span>");
            start += this.pattern.length();
        }
        if (start != this.original.length()) {
            safeAppend(sb, this.original.substring(start, this.original.length()));
        }
        return sb.toString();
    }
}
