package goryachev.fx.internal;

import fqlite.base.Global;
import goryachev.common.util.CKit;
import goryachev.common.util.Parsers;
import goryachev.common.util.SB;
import goryachev.fx.CssID;
import goryachev.fx.CssPseudo;
import goryachev.fx.CssStyle;
import java.text.DecimalFormat;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.FontSmoothingType;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/CssTools.class */
public class CssTools {
    public static final CssStyle BOLD = new CssStyle("CommonStyles_BOLD");
    private static DecimalFormat decimalFormat;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$text$FontSmoothingType;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$control$OverrunStyle;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$control$ScrollPane$ScrollBarPolicy;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$shape$StrokeLineCap;

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$text$FontSmoothingType() {
        int[] iArr = $SWITCH_TABLE$javafx$scene$text$FontSmoothingType;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[FontSmoothingType.values().length];
        try {
            iArr2[FontSmoothingType.GRAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[FontSmoothingType.LCD.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        $SWITCH_TABLE$javafx$scene$text$FontSmoothingType = iArr2;
        return iArr2;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$control$OverrunStyle() {
        int[] iArr = $SWITCH_TABLE$javafx$scene$control$OverrunStyle;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[OverrunStyle.values().length];
        try {
            iArr2[OverrunStyle.CENTER_ELLIPSIS.ordinal()] = 4;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[OverrunStyle.CENTER_WORD_ELLIPSIS.ordinal()] = 5;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[OverrunStyle.CLIP.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[OverrunStyle.ELLIPSIS.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[OverrunStyle.LEADING_ELLIPSIS.ordinal()] = 6;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[OverrunStyle.LEADING_WORD_ELLIPSIS.ordinal()] = 7;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[OverrunStyle.WORD_ELLIPSIS.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        $SWITCH_TABLE$javafx$scene$control$OverrunStyle = iArr2;
        return iArr2;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$control$ScrollPane$ScrollBarPolicy() {
        int[] iArr = $SWITCH_TABLE$javafx$scene$control$ScrollPane$ScrollBarPolicy;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[ScrollPane.ScrollBarPolicy.values().length];
        try {
            iArr2[ScrollPane.ScrollBarPolicy.ALWAYS.ordinal()] = 2;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[ScrollPane.ScrollBarPolicy.AS_NEEDED.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[ScrollPane.ScrollBarPolicy.NEVER.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$javafx$scene$control$ScrollPane$ScrollBarPolicy = iArr2;
        return iArr2;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$shape$StrokeLineCap() {
        int[] iArr = $SWITCH_TABLE$javafx$scene$shape$StrokeLineCap;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[StrokeLineCap.values().length];
        try {
            iArr2[StrokeLineCap.BUTT.ordinal()] = 2;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[StrokeLineCap.ROUND.ordinal()] = 3;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[StrokeLineCap.SQUARE.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        $SWITCH_TABLE$javafx$scene$shape$StrokeLineCap = iArr2;
        return iArr2;
    }

    public static String toColor(Object x) {
        if (x == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        if (x instanceof Integer) {
            int rgb = ((Integer) x).intValue();
            return String.format("#%02x%02x%02x", Integer.valueOf((rgb >> 16) & 255), Integer.valueOf((rgb >> 8) & 255), Integer.valueOf(rgb & 255));
        } else if (x instanceof Color) {
            Color c = (Color) x;
            double r = c.getRed();
            double g = c.getGreen();
            double b = c.getBlue();
            if (c.isOpaque()) {
                return String.format("#%02x%02x%02x", Integer.valueOf(to8bit(r)), Integer.valueOf(to8bit(g)), Integer.valueOf(to8bit(b)));
            }
            double a = c.getOpacity();
            return String.format("#%02x%02x%02x%02x", Integer.valueOf(to8bit(r)), Integer.valueOf(to8bit(g)), Integer.valueOf(to8bit(b)), Integer.valueOf(to8bit(a)));
        } else {
            return x.toString();
        }
    }

    public static String toColors(Object... xs) {
        int sz = xs.length;
        if (sz == 1) {
            return toColor(xs[0]);
        }
        if (sz % 4 != 0) {
            throw new Error("please specify colors in groups of four");
        }
        SB sb = new SB();
        for (int i = 0; i < sz; i++) {
            if (i != 0) {
                if (i % 4 == 0) {
                    sb.a(',');
                } else {
                    sb.sp();
                }
            }
            sb.a(toColor(xs[i]));
        }
        return sb.toString();
    }

    private static int to8bit(double x) {
        int v = (int) Math.round(255.0d * x);
        if (v < 0) {
            return 0;
        }
        if (v > 255) {
            return 255;
        }
        return v;
    }

    public static String toValue(Object x) {
        if (x == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        if (x instanceof Number) {
            Number n = (Number) x;
            int vi = n.intValue();
            double vd = n.doubleValue();
            if (vd == vi) {
                return String.valueOf(vi);
            }
            return String.valueOf(vd);
        } else if (x instanceof Color) {
            return toColor(x);
        } else {
            return x.toString();
        }
    }

    public static String toValue(FontSmoothingType t) {
        switch ($SWITCH_TABLE$javafx$scene$text$FontSmoothingType()[t.ordinal()]) {
            case 1:
            default:
                return "gray";
            case 2:
                return "lcd";
        }
    }

    public static String toValue(double x) {
        return String.valueOf(x);
    }

    public static String toValue(boolean x) {
        return x ? "true" : "false";
    }

    public static String toValue(OverrunStyle s) {
        switch ($SWITCH_TABLE$javafx$scene$control$OverrunStyle()[s.ordinal()]) {
            case 1:
                return "clip";
            case 2:
                return "ellipsis";
            case 3:
                return "word-ellipsis";
            case 4:
                return "center-ellipsis";
            case 5:
                return "center-word-ellipsis";
            case 6:
                return "leading-ellipsis";
            case 7:
                return "leading-word-ellipsis";
            default:
                throw new Error("?" + s);
        }
    }

    public static String toValue(ScrollPane.ScrollBarPolicy x) {
        switch ($SWITCH_TABLE$javafx$scene$control$ScrollPane$ScrollBarPolicy()[x.ordinal()]) {
            case 1:
                return "never";
            case 2:
                return "always";
            case 3:
                return "as-needed";
            default:
                throw new Error("?" + x);
        }
    }

    public static String toValue(StrokeLineCap x) {
        switch ($SWITCH_TABLE$javafx$scene$shape$StrokeLineCap()[x.ordinal()]) {
            case 1:
                return "square";
            case 2:
                return "butt";
            case 3:
                return "round";
            default:
                throw new Error("?" + x);
        }
    }

    public static String toValues(Object... xs) {
        int sz = xs.length;
        if (sz == 1) {
            return toValue(xs[0]);
        }
        if (sz % 4 != 0) {
            throw new Error("please specify values in groups of four");
        }
        SB sb = new SB();
        for (int i = 0; i < sz; i++) {
            if (i != 0) {
                if (i % 4 == 0) {
                    sb.a(',');
                } else {
                    sb.sp();
                }
            }
            sb.a(toValue(xs[i]));
        }
        return sb.toString();
    }

    public static String list(String separator, Object[] items) {
        SB sb = new SB();
        boolean sep = false;
        for (Object x : items) {
            if (sep) {
                sb.a(separator);
            } else {
                sep = true;
            }
            sb.a(toValue(x));
        }
        return sb.toString();
    }

    public static String selector(Object[] sel) {
        SB sb = new SB();
        for (Object x : sel) {
            addSelector(sb, x);
        }
        return sb.toString();
    }

    private static void addSelector(SB sb, Object x) {
        if (x instanceof CssStyle) {
            if (sb.isNotEmpty()) {
                sb.a(' ');
            }
            sb.a('.');
            sb.a(((CssStyle) x).getName());
        } else if (x instanceof CssID) {
            if (sb.isNotEmpty()) {
                sb.a(' ');
            }
            sb.a('#');
            sb.a(((CssID) x).getID());
        } else if (x instanceof String) {
            String s = (String) x;
            if (!s.startsWith(":") && sb.isNotEmpty()) {
                sb.a(' ');
            }
            sb.a(s);
        } else if (x instanceof CssPseudo) {
            sb.a(((CssPseudo) x).getName());
        } else {
            throw new Error("?" + x);
        }
    }

    public static String toQuotedString(Object x) {
        if (x == null) {
            return FXMLLoader.NULL_KEYWORD;
        }
        String s = x.toString();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s;
        }
        return "\"" + s + "\"";
    }

    public static String format(String format, Object... args) {
        int ix;
        int sz = format.length();
        SB sb = new SB(sz * 3);
        int i = 0;
        while (i < sz) {
            char c = format.charAt(i);
            switch (c) {
                case '%':
                    i++;
                    int ix2 = format.indexOf(115, i);
                    if (ix2 < 0) {
                        sb.append((CharSequence) format, i, sz);
                        i = sz;
                        break;
                    } else if (ix2 == 0) {
                        String a = formatArgument(args[0]);
                        sb.append(a);
                        break;
                    } else {
                        String n = format.substring(i, ix2);
                        i = ix2;
                        if (n.length() == 0) {
                            ix = 1;
                        } else {
                            ix = Parsers.parseInteger(n).intValue();
                        }
                        String a2 = formatArgument(args[ix - 1]);
                        sb.append(a2);
                        break;
                    }
                case '\\':
                    i++;
                    char c2 = format.charAt(i);
                    switch (c2) {
                        case 'n':
                            sb.append('\n');
                            continue;
                        case 'o':
                        case 'p':
                        case 'q':
                        case 's':
                        default:
                            sb.append(c2);
                            continue;
                        case 'r':
                            sb.append('\r');
                            continue;
                        case 't':
                            sb.append('\t');
                            continue;
                    }
                default:
                    sb.append(c);
                    break;
            }
            i++;
        }
        return sb.toString();
    }

    private static String formatArgument(Object x) {
        if (x == null) {
            return ButtonBar.BUTTON_ORDER_NONE;
        }
        if (x instanceof Number) {
            if (decimalFormat == null) {
                decimalFormat = new DecimalFormat("#0.##");
            }
            return decimalFormat.format(x);
        }
        String s = x.toString();
        if (CKit.containsAny(s, Global.REGULAR_RECORD)) {
            return String.valueOf('\"') + s + '\"';
        }
        return s;
    }
}
