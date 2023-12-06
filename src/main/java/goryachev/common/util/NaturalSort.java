package goryachev.common.util;

import java.math.BigInteger;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/NaturalSort.class */
public class NaturalSort {
    public static int compare(String a, String b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else if (b == null) {
            return 1;
        } else {
            int rv = compareTextAndNumbers(a, b);
            if (rv == 0) {
                rv = comparePunctuation(a, b);
                if (rv == 0) {
                    rv = a.compareTo(b);
                }
            }
            return rv;
        }
    }

    private static int charAt(String s, int ix) {
        if (ix < s.length()) {
            int c = s.codePointAt(ix);
            return Character.toLowerCase(c);
        }
        return -1;
    }

    private static boolean isNotTextOrNumber(int c) {
        if (c < 0) {
            return false;
        }
        if (!CKit.isBlank(c) && Character.isLetterOrDigit(c)) {
            return false;
        }
        return true;
    }

    private static boolean isNotPunctuation(int c) {
        if (c < 0) {
            return false;
        }
        if (CKit.isBlank(c) || Character.isLetterOrDigit(c)) {
            return true;
        }
        return false;
    }

    private static String number(String s, int ix) {
        int c;
        do {
            ix++;
            c = charAt(s, ix);
            if (c < 0) {
                break;
            }
        } while (Character.isDigit(c));
        return s.substring(ix, ix);
    }

    private static int compareNumbers(String a, String b) {
        if (a.length() < 18 && b.length() < 18) {
            try {
                long na = Long.parseLong(a);
                long nb = Long.parseLong(b);
                if (na < nb) {
                    return -1;
                }
                if (na > nb) {
                    return 1;
                }
                return 0;
            } catch (Exception e) {
            }
        }
        BigInteger ai = new BigInteger(a);
        BigInteger bi = new BigInteger(b);
        return ai.compareTo(bi);
    }

    private static int charCount(int c) {
        if (c < 0) {
            return 0;
        }
        return Character.charCount(c);
    }

    private static int skipWhiteSpace(String s, int ix) {
        while (true) {
            int c = charAt(s, ix);
            if (c < 0) {
                return ix;
            }
            if (CKit.isBlank(c)) {
                ix += Character.charCount(c);
            } else {
                return ix;
            }
        }
    }

    private static int skipPunctuation(String s, int ix) {
        while (true) {
            int c = charAt(s, ix);
            if (c < 0) {
                return ix;
            }
            if (CKit.isBlank(c)) {
                return ix;
            }
            if (Character.isLetterOrDigit(c)) {
                return ix;
            }
            ix += Character.charCount(c);
        }
    }

    private static int compareTextAndNumbers(String a, String b) {
        int ca;
        int cb;
        int ixa = 0;
        int ixb = 0;
        while (true) {
            int starta = ixa;
            int startb = ixb;
            while (true) {
                ca = charAt(a, ixa);
                ixa += charCount(ca);
                if (ca >= 0) {
                    if (CKit.isBlank(ca)) {
                        ca = 32;
                        ixa = skipWhiteSpace(a, ixa);
                        break;
                    } else if (Character.isLetterOrDigit(ca)) {
                        break;
                    } else {
                        ixa = skipPunctuation(a, ixa);
                    }
                } else {
                    break;
                }
            }
            while (true) {
                cb = charAt(b, ixb);
                ixb += charCount(cb);
                if (cb >= 0) {
                    if (CKit.isBlank(cb)) {
                        cb = 32;
                        ixb = skipWhiteSpace(b, ixb);
                        break;
                    } else if (Character.isLetterOrDigit(cb)) {
                        break;
                    } else {
                        ixb = skipPunctuation(b, ixb);
                    }
                } else {
                    break;
                }
            }
            if (Character.isDigit(ca) && Character.isDigit(cb)) {
                String na = number(a, starta);
                String nb = number(b, startb);
                int d = compareNumbers(na, nb);
                if (d == 0) {
                    ixa = starta + na.length();
                    ixb = startb + nb.length();
                } else {
                    return d;
                }
            } else if (ca == cb) {
                if (ca < 0) {
                    return 0;
                }
            } else if (ca < 0) {
                return -1;
            } else {
                if (cb < 0) {
                    return 1;
                }
                return ca - cb;
            }
        }
    }

    private static int comparePunctuation(String a, String b) {
        int cb;
        int ixa = 0;
        int ixb = 0;
        while (true) {
            int ca = charAt(a, ixa);
            if (isNotPunctuation(ca)) {
                ixa++;
            } else {
                while (true) {
                    cb = charAt(b, ixb);
                    if (!isNotPunctuation(cb)) {
                        break;
                    }
                    ixb++;
                }
                if (ca == cb) {
                    if (ca < 0) {
                        return 0;
                    }
                    ixa++;
                    ixb++;
                } else if (ca < 0) {
                    return -1;
                } else {
                    if (cb < 0) {
                        return 1;
                    }
                    return ca - cb;
                }
            }
        }
    }
}
