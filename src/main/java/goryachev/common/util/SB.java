package goryachev.common.util;

import fqlite.base.Global;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SB.class */
public class SB implements Appendable, CharSequence {
    protected StringBuilder sb;

    public SB(int capacity) {
        this.sb = new StringBuilder(capacity);
    }

    public SB() {
        this(32);
    }

    public SB(String s) {
        this.sb = new StringBuilder(s);
    }

    public SB(CharSequence cs) {
        this.sb = new StringBuilder(cs);
    }

    public SB nl() {
        this.sb.append("\n");
        return this;
    }

    public SB nl(int count) {
        for (int i = 0; i < count; i++) {
            this.sb.append("\n");
        }
        return this;
    }

    public SB comma() {
        this.sb.append(',');
        return this;
    }

    public SB tab() {
        this.sb.append("\t");
        return this;
    }

    public SB tab(int count) {
        for (int i = 0; i < count; i++) {
            this.sb.append("\t");
        }
        return this;
    }

    public SB a(char delimiter, Object x) {
        if (isNotEmpty()) {
            this.sb.append(delimiter);
        }
        return a(x);
    }

    public SB a(Object x) {
        if (x != null) {
            this.sb.append(x);
        }
        return this;
    }

    public SB a(char c) {
        this.sb.append(c);
        return this;
    }

    public SB line(Object x) {
        a(x);
        nl();
        return this;
    }

    public SB sp() {
        this.sb.append(Global.REGULAR_RECORD);
        return this;
    }

    public SB sp(int count) {
        for (int i = 0; i < count; i++) {
            this.sb.append(Global.REGULAR_RECORD);
        }
        return this;
    }

    public SB append(char c, int count) {
        for (int i = 0; i < count; i++) {
            this.sb.append(c);
        }
        return this;
    }

    public void safeHtml(Object x) {
        if (x != null) {
            String s = x.toString();
            this.sb.append(s.replace("<", "&lt;").replace("&", "&amp;"));
        }
    }

    public SB safeJson(Object x) {
        if (x == null) {
            this.sb.append(FXMLLoader.NULL_KEYWORD);
        } else {
            String s = JsonDump.toJsonString(x);
            this.sb.append(s);
        }
        return this;
    }

    public SB append(Object x) {
        this.sb.append(x);
        return this;
    }

    public SB append(String s) {
        this.sb.append(s);
        return this;
    }

    @Override // java.lang.Appendable
    public Appendable append(CharSequence cs) {
        this.sb.append(cs);
        return this;
    }

    @Override // java.lang.Appendable
    public SB append(CharSequence cs, int start, int end) {
        this.sb.append(cs, start, end);
        return this;
    }

    public SB append(char[] str) {
        this.sb.append(str);
        return this;
    }

    public SB append(char[] str, int offset, int len) {
        this.sb.append(str, offset, len);
        return this;
    }

    public SB append(boolean x) {
        this.sb.append(x);
        return this;
    }

    @Override // java.lang.Appendable
    public SB append(char x) {
        this.sb.append(x);
        return this;
    }

    public SB append(int x) {
        this.sb.append(x);
        return this;
    }

    public SB append(long x) {
        this.sb.append(x);
        return this;
    }

    public SB append(float x) {
        this.sb.append(x);
        return this;
    }

    public SB append(double d) {
        this.sb.append(d);
        return this;
    }

    public SB appendCodePoint(int codePoint) {
        this.sb.appendCodePoint(codePoint);
        return this;
    }

    public SB delete(int start, int end) {
        this.sb.delete(start, end);
        return this;
    }

    public SB deleteCharAt(int index) {
        this.sb.deleteCharAt(index);
        return this;
    }

    public SB replace(int start, int end, String str) {
        this.sb.replace(start, end, str);
        return this;
    }

    public SB insert(int index, char[] str, int offset, int len) {
        this.sb.insert(index, str, offset, len);
        return this;
    }

    public SB insert(int offset, Object x) {
        this.sb.insert(offset, x);
        return this;
    }

    public SB insert(int offset, String str) {
        this.sb.insert(offset, str);
        return this;
    }

    public SB insert(int offset, char[] str) {
        this.sb.insert(offset, str);
        return this;
    }

    public SB insert(int dstOffset, CharSequence cs) {
        this.sb.insert(dstOffset, cs);
        return this;
    }

    public SB insert(int dstOffset, CharSequence cs, int start, int end) {
        this.sb.insert(dstOffset, cs, start, end);
        return this;
    }

    public SB insert(int offset, boolean x) {
        this.sb.insert(offset, x);
        return this;
    }

    public SB insert(int offset, char c) {
        this.sb.insert(offset, c);
        return this;
    }

    public SB insert(int offset, char c, int count) {
        if (count > 0) {
            char[] cs = new char[count];
            Arrays.fill(cs, c);
            this.sb.insert(offset, cs);
        }
        return this;
    }

    public SB insert(int offset, int x) {
        return insert(offset, String.valueOf(x));
    }

    public SB insert(int offset, long x) {
        return insert(offset, String.valueOf(x));
    }

    public SB insert(int offset, float f) {
        return insert(offset, String.valueOf(f));
    }

    public SB insert(int offset, double d) {
        return insert(offset, String.valueOf(d));
    }

    public int indexOf(String s) {
        return indexOf(s, 0);
    }

    public int indexOf(String s, int fromIndex) {
        return this.sb.indexOf(s, fromIndex);
    }

    public int indexOf(char c) {
        return indexOf(c, 0);
    }

    public int indexOf(char ch, int fromIndex) {
        int sz = this.sb.length();
        for (int i = fromIndex; i < sz; i++) {
            char c = this.sb.charAt(i);
            if (c == ch) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String s) {
        return this.sb.lastIndexOf(s);
    }

    public int lastIndexOf(String s, int fromIndex) {
        return this.sb.lastIndexOf(s, fromIndex);
    }

    public int lastIndexOf(char c) {
        return lastIndexOf(c, this.sb.length());
    }

    public int lastIndexOf(char ch, int fromIndex) {
        if (fromIndex < 0) {
            return -1;
        }
        int sz = this.sb.length();
        if (fromIndex > sz) {
            fromIndex = sz;
        }
        for (int i = fromIndex - 1; i >= 0; i--) {
            char c = this.sb.charAt(i);
            if (c == ch) {
                return i;
            }
        }
        return -1;
    }

    public SB reverse() {
        this.sb.reverse();
        return this;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.sb.toString();
    }

    @Override // java.lang.CharSequence
    public int length() {
        return getLength();
    }

    public int getLength() {
        return this.sb.length();
    }

    public void setLength(int length) {
        this.sb.setLength(length);
    }

    public void clear() {
        this.sb.setLength(0);
    }

    @Override // java.lang.CharSequence
    public char charAt(int ix) {
        return this.sb.charAt(ix);
    }

    public SB replace(String s) {
        this.sb.replace(0, this.sb.length(), s);
        return this;
    }

    public String substring(int start, int end) {
        return this.sb.substring(start, end);
    }

    public String substring(int start) {
        return this.sb.substring(start);
    }

    public boolean isEmpty() {
        return this.sb.length() == 0;
    }

    public boolean isNotEmpty() {
        return this.sb.length() > 0;
    }

    public boolean isBlank() {
        int len = this.sb.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(this.sb.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void setCharAt(int index, char c) {
        this.sb.replace(index, index + 1, String.valueOf(c));
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        this.sb.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public char[] getChars() {
        int sz = this.sb.length();
        char[] rv = new char[sz];
        this.sb.getChars(0, sz, rv, 0);
        return rv;
    }

    public char[] getCharsAndClear() {
        char[] rv = getChars();
        this.sb.setLength(0);
        return rv;
    }

    public String getAndClear() {
        String s = this.sb.toString();
        this.sb.setLength(0);
        return s;
    }

    public int indexOfIgnoreCase(String pattern, int fromIndex) {
        int len = this.sb.length();
        int plen = pattern.length();
        if (fromIndex >= len) {
            if (plen == 0) {
                return len;
            }
            return -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (plen == 0) {
            return fromIndex;
        }
        char c0 = pattern.charAt(0);
        int max = len - plen;
        int i = fromIndex;
        while (i <= max) {
            if (!TextTools.isSameIgnoreCase(this.sb.charAt(i), c0)) {
                do {
                    i++;
                    if (i > max) {
                        break;
                    }
                } while (!TextTools.isSameIgnoreCase(this.sb.charAt(i), c0));
            }
            if (i <= max) {
                int j = i + 1;
                int end = (j + plen) - 1;
                for (int k = 1; j < end && TextTools.isSameIgnoreCase(this.sb.charAt(j), pattern.charAt(k)); k++) {
                    j++;
                }
                if (j == end) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    public void replace(String old, String newText) {
        int i = 0;
        while (true) {
            int start = i;
            int ix = this.sb.indexOf(old, start);
            if (ix < 0) {
                return;
            }
            this.sb.replace(ix, ix + old.length(), newText);
            i = ix + newText.length();
        }
    }

    public void replace(char old, String newText) {
        int i = 0;
        while (true) {
            int start = i;
            int ix = indexOf(old, start);
            if (ix < 0) {
                return;
            }
            this.sb.replace(ix, ix + 1, newText);
            i = ix + newText.length();
        }
    }

    public void replace(char old, char newChar) {
        for (int i = this.sb.length() - 1; i >= 0; i--) {
            char c = this.sb.charAt(i);
            if (c == old) {
                this.sb.setCharAt(i, newChar);
            }
        }
    }

    public void toLowerCase() {
        int sz = this.sb.length();
        for (int i = 0; i < sz; i++) {
            char c = this.sb.charAt(i);
            char n = Character.toLowerCase(c);
            if (n != c) {
                this.sb.setCharAt(i, n);
            }
        }
    }

    public void toUpperCase() {
        int sz = this.sb.length();
        for (int i = 0; i < sz; i++) {
            char c = this.sb.charAt(i);
            char n = Character.toUpperCase(c);
            if (n != c) {
                this.sb.setCharAt(i, n);
            }
        }
    }

    public boolean conditionalNewline() {
        if (this.sb.length() > 0) {
            this.sb.append('\n');
            return true;
        }
        return false;
    }

    public void padLeading(char c, int max, Object v) {
        String s = v == null ? ButtonBar.BUTTON_ORDER_NONE : v.toString();
        for (int i = s.length(); i < max; i++) {
            this.sb.append(c);
        }
        this.sb.append(s);
    }

    public void padTrailing(char c, int max, Object v) {
        String s = v == null ? ButtonBar.BUTTON_ORDER_NONE : v.toString();
        this.sb.append(s);
        for (int i = s.length(); i < max; i++) {
            this.sb.append(c);
        }
    }

    public void addAll(Object[] ss, Object sep) {
        boolean first = true;
        for (Object s : ss) {
            if (first) {
                first = false;
            } else {
                a(sep);
            }
            a(s);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int start, int end) {
        return this.sb.subSequence(start, end);
    }

    public SB repeat(char c, int count) {
        for (int i = 0; i < count; i++) {
            this.sb.append(c);
        }
        return this;
    }

    public SB list(Collection<?> items, char delimiter) {
        if (items != null) {
            boolean sep = false;
            for (Object x : items) {
                if (sep) {
                    this.sb.append(delimiter);
                } else {
                    sep = true;
                }
                this.sb.append(x);
            }
        }
        return this;
    }

    public SB list(Object[] items, char delimiter) {
        if (items != null) {
            boolean sep = false;
            for (Object x : items) {
                if (sep) {
                    this.sb.append(delimiter);
                } else {
                    sep = true;
                }
                this.sb.append(x);
            }
        }
        return this;
    }

    public SB list(Map<?, ?> items, char delimiter) {
        if (items != null) {
            boolean sep = false;
            for (Object k : items.keySet()) {
                if (sep) {
                    this.sb.append(delimiter);
                } else {
                    sep = true;
                }
                Object v = items.get(k);
                this.sb.append(k);
                this.sb.append('=');
                this.sb.append(v);
            }
        }
        return this;
    }

    public SB format(String fmt, Object... args) {
        Formatter f = new Formatter(this.sb);
        try {
            f.format(fmt, args);
            return this;
        } finally {
            CKit.close(f);
        }
    }

    public byte[] getBytes(Charset cs) {
        return toString().getBytes(cs);
    }
}
