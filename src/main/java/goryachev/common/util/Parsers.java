package goryachev.common.util;

import goryachev.common.log.Log;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Parsers.class */
public class Parsers {
    protected static final Log log = Log.get("Parsers");

    public static Double parseDouble(Object x) {
        if (x instanceof Number) {
            return Double.valueOf(((Number) x).doubleValue());
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Double.valueOf(Double.parseDouble(s.trim()));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static double parseDouble(Object x, double defaultValue) {
        Double d = parseDouble(x);
        if (d == null) {
            return defaultValue;
        }
        return d.doubleValue();
    }

    public static Integer parseInteger(Object x) {
        if (x instanceof Number) {
            return Integer.valueOf(((Number) x).intValue());
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Integer.valueOf(Integer.parseInt(s.trim()));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Integer parseIntegerHex(Object x) {
        if (x instanceof Number) {
            return Integer.valueOf(((Number) x).intValue());
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Integer.valueOf(Integer.parseInt(s.trim(), 16));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static Integer[] parseIntegerArray(Object x) {
        if (x instanceof Integer[]) {
            return (Integer[]) x;
        }
        return null;
    }

    public static int parseInt(Object x, int defaultValue) {
        if (x instanceof Number) {
            return ((Number) x).intValue();
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static Float parseFloat(Object x) {
        if (x instanceof Number) {
            return Float.valueOf(((Number) x).floatValue());
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Float.valueOf(Float.parseFloat(s.trim()));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static float parseFloat(Object x, float defaultValue) {
        Float f = parseFloat(x);
        if (f == null) {
            return defaultValue;
        }
        return f.floatValue();
    }

    public static Long parseLong(Object x) {
        if (x instanceof Number) {
            return Long.valueOf(((Number) x).longValue());
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Long.valueOf(Long.parseLong(s.trim()));
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static long parseLong(Object x, long defaultValue) {
        if (x instanceof Number) {
            return ((Number) x).longValue();
        }
        if (x != null) {
            try {
                String s = x.toString();
                return Long.parseLong(s.trim());
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static String parseString(Object x, String defaultValue) {
        if (x == null) {
            return defaultValue;
        }
        return parseString(x);
    }

    public static String parseString(Object x) {
        if (x == null) {
            return null;
        }
        if (x instanceof String) {
            return (String) x;
        }
        if (x instanceof char[]) {
            return new String((char[]) x);
        }
        return x.toString();
    }

    public static String parseStringNotNull(Object x) {
        if (x != null) {
            return x.toString();
        }
        return ButtonBar.BUTTON_ORDER_NONE;
    }

    public static String parseStringOnly(Object x) {
        if (x instanceof String) {
            return (String) x;
        }
        return null;
    }

    public static char[] parseCharArray(Object x) {
        if (x instanceof char[]) {
            return (char[]) x;
        }
        if (x instanceof String) {
            return ((String) x).toCharArray();
        }
        return null;
    }

    public static Boolean parseBoolean(Object x) {
        if (x instanceof Boolean) {
            return (Boolean) x;
        }
        if (x != null) {
            String s = x.toString();
            return "true".equalsIgnoreCase(s) || "y".equalsIgnoreCase(s) || FXMLLoader.FX_NAMESPACE_VERSION.equals(s);
        }
        return null;
    }

    public static boolean parseBool(Object x, boolean defaultValue) {
        Boolean v = parseBoolean(x);
        if (v == null) {
            return defaultValue;
        }
        return v.booleanValue();
    }

    public static boolean parseBool(Object x) {
        return parseBool(x, false);
    }

    public static boolean parseBooleanStrict(Object x) {
        return Boolean.TRUE.equals(x);
    }

    public static byte[] parseByteArray(Object x) {
        try {
            if (x instanceof byte[]) {
                return (byte[]) x;
            }
            if (x instanceof String) {
                return Hex.parseByteArray((String) x);
            }
            return null;
        } catch (Exception e) {
            log.error((Throwable) e);
            return null;
        }
    }

    public static byte[] parseByteArrayQuiet(Object x) {
        try {
            return parseByteArray(x);
        } catch (Exception e) {
            return null;
        }
    }

    public static File parseFile(Object x) {
        if (x != null) {
            try {
                if (x instanceof File) {
                    return (File) x;
                }
                if (x instanceof String) {
                    return new File((String) x);
                }
                return null;
            } catch (Exception e) {
                log.error((Throwable) e);
                return null;
            }
        }
        return null;
    }

    public static File parseCanonicalFile(Object x) {
        if (x != null) {
            try {
                File f = null;
                if (x instanceof File) {
                    f = (File) x;
                } else if (x instanceof String) {
                    f = new File((String) x);
                }
                if (f != null) {
                    try {
                        return f.getCanonicalFile();
                    } catch (Exception e) {
                        log.error((Throwable) e);
                        return null;
                    }
                }
                return null;
            } catch (Exception e2) {
                log.error((Throwable) e2);
                return null;
            }
        }
        return null;
    }

    public static String[] parseStringArray(Object x) {
        if (x instanceof String[]) {
            return (String[]) x;
        }
        return null;
    }

    public static String[] parseCommaSeparatedStringArray(String s) {
        if (s != null) {
            return CKit.split((CharSequence) s, ',');
        }
        return null;
    }

    public static Object[] parseObjectArray(Object x) {
        if (x instanceof Object[]) {
            return (Object[]) x;
        }
        return null;
    }

    public static BigInteger parseBigInteger(Object x) {
        if (x instanceof BigInteger) {
            return (BigInteger) x;
        }
        if (x instanceof Number) {
            try {
                return BigInteger.valueOf(((Number) x).longValue());
            } catch (Exception e) {
                return null;
            }
        } else if (x instanceof String) {
            try {
                return new BigInteger((String) x);
            } catch (Exception e2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static BigInteger parseBigIntegerNotNull(Object x) {
        BigInteger v = parseBigInteger(x);
        if (v == null) {
            throw new IllegalArgumentException("not a BigInteger: " + x);
        }
        return v;
    }

    public static BigDecimal parseBigDecimal(Object x) {
        if (x instanceof BigDecimal) {
            return (BigDecimal) x;
        }
        if (x instanceof Number) {
            try {
                return new BigDecimal(((Number) x).doubleValue());
            } catch (Exception e) {
                return null;
            }
        } else if (x instanceof String) {
            try {
                return new BigDecimal((String) x);
            } catch (Exception e2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Exception parseException(Object x) {
        if (x instanceof Exception) {
            return (Exception) x;
        }
        if (x instanceof Throwable) {
            return new Exception((Throwable) x);
        }
        return null;
    }

    public static ByteBuffer parseByteBuffer(Object x) {
        if (x != null && (x instanceof byte[])) {
            return ByteBuffer.wrap((byte[]) x);
        }
        return null;
    }

    public static <T> HashSet<T> parseHashSet(Object x) {
        if (x != null && (x instanceof Collection)) {
            return new HashSet<>((Collection) x);
        }
        return new HashSet<>();
    }

    public static <T extends Enum> T parseEnum(Object val, Class<T> type, T defaultValue) {
        T[] enumConstants;
        if (val != null) {
            for (T v : type.getEnumConstants()) {
                if (v == val) {
                    return v;
                }
                String s = val.toString();
                if (v.toString().equals(s)) {
                    return v;
                }
            }
        }
        return defaultValue;
    }
}
