package goryachev.common.util;


import fqlite.base.Global;
import java.lang.reflect.Array;
import java.util.Collection;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/TextTools.class */
public class TextTools {
    public static final SeparatorFunction NOT_LETTER_OR_DIGIT = new SeparatorFunction() { // from class: goryachev.common.util.TextTools.1
        @Override // goryachev.common.util.TextTools.SeparatorFunction
        public boolean isSeparator(char c) {
            return !Character.isLetterOrDigit(c);
        }
    };
    public static final SeparatorFunction ANY_BLANK = new SeparatorFunction() { // from class: goryachev.common.util.TextTools.2
        @Override // goryachev.common.util.TextTools.SeparatorFunction
        public boolean isSeparator(char c) {
            return CKit.isBlank(c);
        }
    };
    public static final SeparatorFunction BLANK_OR_PUNCT = new SeparatorFunction() { // from class: goryachev.common.util.TextTools.3
        @Override // goryachev.common.util.TextTools.SeparatorFunction
        public boolean isSeparator(char c) {
            return TextTools.isBlankOrPunctuation(c);
        }
    };

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/TextTools$SeparatorFunction.class */
    public interface SeparatorFunction {
        boolean isSeparator(char c);
    }

    public static String trimNicely(String s, int max) {
        try {
            return trimNicely_FIX(s, max);
        } catch (Exception e) {
            return s;
        }
    }

    private static String trimNicely_FIX(String s, int max) {
        if (s == null) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        String s2 = s.trim();
        if (s2.length() < max) {
            return s2;
        }
        int ix = s2.lastIndexOf(32, max);
        if (ix < 0) {
            if (max < 32) {
                return s2;
            }
            ix = max;
        } else if (ix > max / 2) {
            ix = max;
        }
        return String.valueOf(s2.substring(0, ix - 3)) + "...";
    }

    public static boolean hasLetters(String s) {
        if (s != null) {
            int sz = s.length();
            for (int i = 0; i < sz; i++) {
                char c = s.charAt(i);
                if (Character.isLetter(c)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static String beforeSpace(String s) {
        int ix = indexOfWhitespace(s);
        if (ix < 0) {
            return s;
        }
        return s.substring(0, ix);
    }

    public static int indexOfWhitespace(String s) {
        return indexOfWhitespace(s, 0);
    }

    public static int indexOfWhitespace(String s, int start) {
        if (s != null) {
            int sz = s.length();
            for (int i = start; i < sz; i++) {
                if (CKit.isBlank(s.charAt(i))) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    public static int skipWhitespace(String text, int start) {
        if (text == null || start < 0) {
            return -1;
        }
        int sz = text.length();
        for (int i = start; i < sz; i++) {
            if (CKit.isNotBlank(Character.valueOf(text.charAt(i)))) {
                return i;
            }
        }
        return -1;
    }

    public static int skipNonWhitespace(String text) {
        return skipNonWhitespace(text, 0);
    }

    public static int skipNonWhitespace(String text, int start) {
        if (text == null || start < 0) {
            return -1;
        }
        int sz = text.length();
        for (int i = start; i < sz; i++) {
            if (CKit.isBlank(text.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public static boolean startsWithIgnoreCase(String s, String pattern) {
        int sz;
        if (s != null && s.length() >= (sz = pattern.length())) {
            for (int i = 0; i < sz; i++) {
                if (Character.toLowerCase(pattern.charAt(i)) != Character.toLowerCase(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean endsWithIgnoreCase(String s, String suffix) {
        int sz;
        if (s != null && s.length() > (sz = suffix.length())) {
            int ix = s.length() - sz;
            int i = 0;
            while (i < sz) {
                if (Character.toLowerCase(suffix.charAt(i)) == Character.toLowerCase(s.charAt(ix))) {
                    i++;
                    ix++;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String toDelimitedString(Collection<?> list) {
        return toDelimitedString(list, Global.REGULAR_RECORD);
    }

    public static String toDelimitedString(Collection<?> list, String delimiter) {
        if (list != null) {
            SB sb = new SB();
            for (Object x : list) {
                if (sb.length() > 0) {
                    sb.a(delimiter);
                }
                sb.a(x);
            }
            return sb.toString();
        }
        return null;
    }

    public static String trimTrailing(String text, String pattern) {
        if (text.endsWith(pattern)) {
            return text.substring(0, text.length() - pattern.length());
        }
        return text;
    }

    public static boolean isAllCaps(String s) {
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isUpperCase(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCamelCase(String s) {
        int upper = 0;
        boolean lower = false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                upper++;
                if (lower && upper >= 2) {
                    return true;
                }
            } else {
                lower = true;
            }
        }
        return false;
    }

    public static boolean containsNumbers(String s) {
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTrimmablePunctuation(int c) {
        switch (c) {
            case 33:
            case 34:
            case 39:
            case 40:
            case 41:
            case 42:
            case 44:
            case 45:
            case 46:
            case 47:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 91:
            case 92:
            case 93:
            case 0xa1:
            case 174:
            case 183:
            case 191:
            case 1472:
            case 1475:
            case 8222:
            case 8226:
            case 8229:
            case 8230:
            case 8231:
            case 8243:
            case 12289:
            case 12290:
            case 65281:
            case 65288:
            case 65289:
            case 65306:
            case 65311:
                return true;
            default:
                switch (Character.getType(c)) {
                    case 29:
                    case 30:
                        return true;
                    default:
                        return false;
                }
        }
    }

    public static String trimPunctuation(String s) {
        int end = s.length();
        boolean sub = false;
        int start = 0;
        while (start < end) {
            char c = s.charAt(start);
            if (!isTrimmablePunctuation(c)) {
                break;
            }
            sub = true;
            start++;
        }
        while (true) {
            end--;
            if (end <= start) {
                break;
            }
            char c2 = s.charAt(end);
            if (!isTrimmablePunctuation(c2)) {
                break;
            }
            sub = true;
        }
        int end2 = end + 1;
        if (sub) {
            return s.substring(start, end2);
        }
        return s;
    }

    public static boolean isTrimmableTrailingPunctuation(int c) {
        switch (c) {
            case 33:
            case 44:
            case 46:
            case 58:
            case 59:
            case 63:
            case 183:
            case 1472:
            case 1475:
            case 8229:
            case 8230:
            case 8231:
            case 12289:
            case 12290:
            case 65281:
            case 65306:
            case 65311:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSentenceEnd(int c) {
        switch (c) {
            case 33:
            case 46:
            case 63:
            case 183:
            case 8229:
            case 8230:
            case 12290:
            case 65281:
            case 65306:
            case 65311:
                return true;
            default:
                return false;
        }
    }

    public static String trimTrailingPunctuation(String s) {
        int end = s.length() - 1;
        boolean sub = false;
        while (end >= 0) {
            char c = s.charAt(end);
            if (!isTrimmableTrailingPunctuation(c)) {
                break;
            }
            sub = true;
            end--;
        }
        int end2 = end + 1;
        if (sub) {
            return s.substring(0, end2);
        }
        return s;
    }

    public static String trimTrailingPunctuation1(String s) {
        int pos = s.length() - 1;
        if (pos >= 0) {
            char c = s.charAt(pos);
            if (isTrimmableTrailingPunctuation(c)) {
                return s.substring(0, pos);
            }
        }
        return s;
    }

    private static boolean within(char c, int min, int max) {
        return c >= min && c <= max;
    }

    public static boolean isCJK(char c) {
        return within(c, 11904, 13311) || within(c, 19968, 40959) || within(c, 63744, 64255) || within(c, 65376, 65519);
    }

    public static boolean isNumber(char c) {
        return false;
    }

    public static boolean isWhitespace(char c) {
        if (Character.isWhitespace(c) || Character.isSpaceChar(c)) {
            return true;
        }
        return false;
    }

    public static boolean isWhitespaceOrWordSeparator(char c) {
        if (isWhitespace(c)) {
            return true;
        }
        switch (c) {
            case 8212:
            case 8213:
            case 12289:
            case 12316:
            case 65374:
                return true;
            default:
                return false;
        }
    }

    public static boolean isWordSeparator(char c) {
        switch (c) {
            case '\'':
            case ',':
            case '.':
                return false;
            case 8211:
            case 8212:
            case 8213:
                return true;
            default:
                return isTrimmablePunctuation(c);
        }
    }

    public static boolean isPartOfNumber(char c) {
        switch (c) {
            case '+':
            case ',':
            case '-':
            case '.':
                return true;
            default:
                return Character.isDigit(c);
        }
    }

    public static boolean containsLettersOrDigits(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDigits(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsLetters(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmail(String s) {
        boolean at = false;
        boolean dot = false;
        int sz = s.length();
        for (int i = 0; i < sz; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '.':
                    if (at) {
                        dot = true;
                        break;
                    } else {
                        break;
                    }
                case '@':
                    if (at) {
                        return false;
                    }
                    at = true;
                    break;
                case '_':
                    break;
                default:
                    if (Character.isLetterOrDigit(c)) {
                        break;
                    } else {
                        return false;
                    }
            }
        }
        return at && dot;
    }

    public static boolean isUrl(String s) {
        if (startsWithIgnoreCase(s, "http://") || startsWithIgnoreCase(s, "https://") || startsWithIgnoreCase(s, "ftp://") || startsWithIgnoreCase(s, "file://") || startsWithIgnoreCase(s, "mailto://")) {
            return true;
        }
        return false;
    }

    public static boolean isEmailOrUrl(String s) {
        if (isEmail(s) || isUrl(s)) {
            return true;
        }
        return false;
    }

    public static boolean isWordDelimiter(int c) {
        switch (c) {
            case 39:
            case 45:
            case 8211:
                return false;
            default:
                if (isTrimmablePunctuation(c) || isTrimmableTrailingPunctuation(c)) {
                    return true;
                }
                return false;
        }
    }

    public static int lastIndexOfSeparator(String s, int pos, SeparatorFunction f) {
        int len = s.length();
        if (pos < 0) {
            throw new IllegalArgumentException("pos<0");
        }
        if (pos >= len) {
            throw new IllegalArgumentException("pos>len");
        }
        for (int i = pos - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (f.isSeparator(c)) {
                return i + 1;
            }
        }
        return -1;
    }

    public static int lastIndexOfWhitespace(String s, int pos) {
        if (s != null) {
            int len = s.length();
            if (pos < 0) {
                throw new IllegalArgumentException("pos<0");
            }
            if (pos >= len) {
                pos = len;
            }
            for (int i = pos - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (CKit.isBlank(c)) {
                    return i + 1;
                }
            }
            return -1;
        }
        return -1;
    }

    public static int lastIndexOfWhitespace(String s) {
        return lastIndexOfWhitespace(s, Integer.MAX_VALUE);
    }

    public static int indexOfSeparator(String s, int pos, SeparatorFunction f) {
        int len = s.length();
        if (pos < 0) {
            throw new IllegalArgumentException("pos<0");
        }
        if (pos >= len) {
            throw new IllegalArgumentException("pos>len");
        }
        for (int i = pos; i < len; i++) {
            char c = s.charAt(i);
            if (f.isSeparator(c)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isBlankOrPunctuation(int c) {
        if (CKit.isBlank(c)) {
            return true;
        }
        return isWordDelimiter(c);
    }

    public static CList<String> splitWords(String text) {
        CList<String> list = new CList<>();
        if (text != null) {
            int start = 0;
            int len = text.length();
            boolean white = true;
            for (int i = 0; i < len; i++) {
                char c = text.charAt(i);
                if (isBlankOrPunctuation(c)) {
                    if (!white) {
                        if (i > start) {
                            add(list, text.substring(start, i));
                        }
                        white = true;
                    }
                } else if (isCJK(c)) {
                    if (white) {
                        white = false;
                    } else if (i > start) {
                        add(list, text.substring(start, i));
                    }
                    start = i;
                } else if (white) {
                    start = i;
                    white = false;
                }
            }
            if (!white && start < len) {
                add(list, text.substring(start, len));
            }
        }
        return list;
    }

    private static void add(CList<String> list, String s) {
        String s2 = trimPunctuation(s);
        if (s2.length() > 0) {
            list.add(s2);
        }
    }

    public static String replace(String text, String pattern, String newPattern) {
        if (text != null) {
            SB sb = new SB(text);
            sb.replace(pattern, newPattern);
            return sb.toString();
        }
        return null;
    }

    public static String replaceIgnoreCase(String text, String pattern, String newPattern) {
        SB sb = new SB(text);
        int i = 0;
        while (true) {
            int start = i;
            int ix = sb.indexOfIgnoreCase(pattern, start);
            if (ix < 0) {
                return sb.toString();
            }
            sb.replace(ix, ix + pattern.length(), newPattern);
            i = ix + newPattern.length();
        }
    }

    public static boolean containsIgnoreCase(String text, String pattern) {
        return indexOfIgnoreCase(text, pattern, 0) >= 0;
    }

    public static int indexOfIgnoreCase(String text, String pattern) {
        return indexOfIgnoreCase(text, pattern, 0);
    }

    public static boolean isSameIgnoreCase(char a, char b) {
        if (Character.toUpperCase(a) == Character.toUpperCase(b) || Character.toLowerCase(a) == Character.toLowerCase(b)) {
            return true;
        }
        return false;
    }

    public static int indexOfIgnoreCase(CharSequence text, String pattern, int fromIndex) {
        int textLen = text.length();
        int patternLen = pattern.length();
        if (fromIndex >= textLen) {
            if (patternLen == 0) {
                return textLen;
            }
            return -1;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (patternLen == 0) {
            return fromIndex;
        }
        char first = pattern.charAt(0);
        int max = textLen - patternLen;
        int i = fromIndex;
        while (i <= max) {
            if (!isSameIgnoreCase(text.charAt(i), first)) {
                do {
                    i++;
                    if (i > max) {
                        break;
                    }
                } while (!isSameIgnoreCase(text.charAt(i), first));
            }
            if (i <= max) {
                int j = i + 1;
                int end = (j + patternLen) - 1;
                for (int k = 1; j < end && isSameIgnoreCase(text.charAt(j), pattern.charAt(k)); k++) {
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

    public static boolean isNumber(String s) {
        if (CKit.isBlank(s)) {
            return false;
        }
        boolean number = false;
        boolean exp = false;
        int sign = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '+':
                case '-':
                    sign++;
                    break;
                case ',':
                case '.':
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    number = true;
                    break;
                case 'E':
                case 'F':
                case 'G':
                case 'e':
                case 'f':
                case 'g':
                    if (exp) {
                        return false;
                    }
                    exp = true;
                    break;
                default:
                    return false;
            }
        }
        if (sign <= 2 && number) {
            return true;
        }
        return false;
    }

    public static String trimOuterNonLetters(String s) {
        if (s == null) {
            return null;
        }
        int end = s.length();
        boolean sub = false;
        int start = 0;
        while (start < end) {
            char c = s.charAt(start);
            if (Character.isLetter(c)) {
                break;
            }
            sub = true;
            start++;
        }
        while (true) {
            end--;
            if (end <= start) {
                break;
            }
            char c2 = s.charAt(end);
            if (Character.isLetter(c2)) {
                break;
            }
            sub = true;
        }
        int end2 = end + 1;
        if (sub) {
            return s.substring(start, end2);
        }
        return s;
    }

    public static int countChar(String s, char c) {
        if (s != null) {
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    cnt++;
                }
            }
            return cnt;
        }
        return 0;
    }

    public static boolean contains(String text, char c) {
        return text != null && text.indexOf(c) >= 0;
    }

    public static boolean containsWhitespace(String text) {
        if (text != null) {
            int sz = text.length();
            for (int i = 0; i < sz; i++) {
                char c = text.charAt(i);
                if (CKit.isBlank(c)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static String printable(int c) {
        switch (c) {
            case 8:
                return "\\b";
            case 9:
                return "\\t";
            case 10:
                return "\\n";
            case 12:
                return "\\f";
            case 13:
                return "\\r";
            case 92:
                return "\\\\";
            default:
                return null;
        }
    }

    public static String escapeControlsForPrintout(String text) {
        if (text == null) {
            return null;
        }
        SB sb = new SB(text.length() + 64);
        text.codePoints().forEach(c -> {
            if (c < 32) {
                String s = printable(c);
                if (s == null) {
                    sb.append(Hex.toHexString((short) c));
                    return;
                } else {
                    sb.append(s);
                    return;
                }
            }
            sb.appendCodePoint(c);
        });
        return sb.toString();
    }

    public static String[] array(Object... items) {
        CList<String> rv = new CList<>(128);
        array(rv, items);
        return CKit.toArray(rv);
    }

    private static void array(CList<String> list, Object x) {
        CKit.checkCancelled();
        if (x == null) {
            return;
        }
        if (x.getClass().isArray()) {
            int sz = Array.getLength(x);
            for (int i = 0; i < sz; i++) {
                array(list, Array.get(x, i));
            }
        } else if (x instanceof Collection) {
            for (Object item : (Collection) x) {
                array(list, item);
            }
        } else {
            list.add(x.toString());
        }
    }

    public static int indexOf(CharSequence text, CharSequence pattern) {
        return indexOf(text, pattern, 0);
    }

    public static int indexOf(CharSequence text, CharSequence pattern, int start) {
        int len = pattern.length();
        if (start >= text.length()) {
            if (len == 0) {
                return text.length();
            }
            return -1;
        }
        if (start < 0) {
            start = 0;
        }
        if (len == 0) {
            return start;
        }
        int mx = text.length() - len;
        char ch0 = pattern.charAt(0);
        int i = start;
        while (i <= mx) {
            if (text.charAt(i) != ch0) {
                do {
                    i++;
                    if (i > mx) {
                        break;
                    }
                } while (text.charAt(i) != ch0);
            }
            if (i <= mx) {
                int j = i + 1;
                int end = (j + len) - 1;
                for (int k = 1; j < end && text.charAt(j) == pattern.charAt(k); k++) {
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

    public static boolean isWhiteSpaceOrCtrl(char c) {
        if (c <= ' ') {
            return true;
        }
        return isWhitespace(c);
    }

    public static String toSingleLine(String text) {
        if (text == null) {
            return null;
        }
        int len = text.length();
        SB sb = new SB(len);
        boolean white = true;
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (isWhiteSpaceOrCtrl(c)) {
                if (!white) {
                    white = true;
                }
            } else {
                if (white && sb.length() > 0) {
                    sb.append(' ');
                }
                white = false;
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
