package goryachev.common.util.html;

import fqlite.base.Global;
import goryachev.common.log.Log;
import goryachev.common.util.Base64;
import goryachev.common.util.CComparator;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.CSet;
import goryachev.common.util.Hex;
import goryachev.common.util.SB;
import java.awt.Color;
import java.net.URI;
import javafx.fxml.FXMLLoader;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/html/HtmlTools.class */
public class HtmlTools {
    protected static final Log log = Log.get("HtmlTools");
    private static Html4SymbolEntities html4SymbolEntities;
    private static CSet<String> htmlTags;

    public static String safe(String s) {
        if (s != null) {
            if (s.indexOf(38) >= 0) {
                s = s.replace("&", "&#38;");
            }
            if (s.indexOf(60) >= 0) {
                s = s.replace("<", "&#60;");
            }
        }
        return s;
    }

    public static Character characterEntity(String token) {
        if (token.startsWith("#x")) {
            try {
                int unicode = Integer.parseInt(token.substring(2, token.length()), 16);
                return Character.valueOf((char) unicode);
            } catch (Exception e) {
                return null;
            }
        } else if (token.startsWith(FXMLLoader.CONTROLLER_METHOD_PREFIX)) {
            try {
                int unicode2 = Integer.parseInt(token.substring(1, token.length()));
                return Character.valueOf((char) unicode2);
            } catch (Exception e2) {
                return null;
            }
        } else {
            return html4SymbolEntities().lookupChar(token);
        }
    }

    private static synchronized Html4SymbolEntities html4SymbolEntities() {
        if (html4SymbolEntities == null) {
            html4SymbolEntities = new Html4SymbolEntities();
        }
        return html4SymbolEntities;
    }

    public static String decodeHtmlCharacterEntities(String text) {
        if (text == null) {
            return null;
        }
        SB sb = new SB(text);
        return decodeHtmlCharacterEntities(sb);
    }

    public static String decodeHtmlCharacterEntities(SB sb) {
        int xe;
        int ix = 0;
        while (true) {
            int ix2 = sb.indexOf("&", ix);
            if (ix2 >= 0 && (xe = sb.indexOf(";", ix2 + 1)) >= 0) {
                String entity = sb.substring(ix2 + 1, xe);
                Character c = characterEntity(entity);
                if (c == null) {
                    ix = xe + 1;
                } else {
                    sb.replace(ix2, xe + 1, String.valueOf(c));
                    ix = ix2 + 1;
                }
                break;
            }
        }
        return sb.toString();
    }

    public static String toHtml(String s) {
        return safe(s).replace(" ", "&nbsp;");
    }

    public static String formatPercent(int x) {
        return String.valueOf(x) + FXMLLoader.RESOURCE_KEY_PREFIX;
    }

    public static String formatPixels(int x) {
        return String.valueOf(x) + "px";
    }

    public static String toColorString(Color c) {
        return FXMLLoader.CONTROLLER_METHOD_PREFIX + Hex.toHexString(c.getRGB(), 6);
    }

    public static String safe(Object x) {
        if (x == null) {
            return null;
        }
        String text = x.toString();
        if (text.contains("&")) {
            text = text.replace("&", "&amp;");
        }
        if (text.contains("<")) {
            text = text.replace("<", "&lt;");
        }
        if (text.contains("\n")) {
            text = text.replace("\n", "<br>");
        }
        return text;
    }

    public static String removeQuotes(String s) {
        int last;
        if (s != null && (last = s.length() - 1) >= 0) {
            char c = s.charAt(0);
            switch (c) {
                case '\"':
                case '\'':
                    char ce = s.charAt(last);
                    if (c == ce) {
                        return s.substring(1, last);
                    }
                    break;
            }
        }
        return s;
    }

    public static String quoteConditionally(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s;
        }
        if (s.startsWith("'") && s.endsWith("'")) {
            return s;
        }
        boolean needToQuote = s.contains(Global.REGULAR_RECORD);
        boolean needToQuote2 = needToQuote | s.contains("+");
        if (s.contains("\"")) {
            needToQuote2 = true;
            s = s.replace("\"", "&#34;");
        }
        if (s.contains("'")) {
            s = s.replace("'", "&#39;");
        }
        if (needToQuote2) {
            return "\"" + s + "\"";
        }
        return s;
    }

    public static void sort(CList<String> ss) {
        new CComparator<String>() { // from class: goryachev.common.util.html.HtmlTools.1
            @Override // goryachev.common.util.CComparator, java.util.Comparator
            public int compare(String a, String b) {
                return compareAsStrings(a, b);
            }
        }.sort(ss);
    }

    public static String generate(Object... ss) {
        SB sb = new SB();
        for (Object x : ss) {
            sb.a(x);
        }
        return sb.toString();
    }

    public static String color(Color c) {
        SB sb = new SB(7);
        color(sb, c);
        return sb.toString();
    }

    public static void color(SB sb, Color c) {
        sb.a('#');
        sb.a(Hex.toHexByte(c.getRed()));
        sb.a(Hex.toHexByte(c.getGreen()));
        sb.a(Hex.toHexByte(c.getBlue()));
    }

    public static boolean isTag(String s) {
        if (s == null) {
            return false;
        }
        return htmlTags().contains(CKit.toLowerCase(s));
    }

    private static synchronized CSet<String> htmlTags() {
        if (htmlTags == null) {
            htmlTags = new CSet<>(CKit.collectPublicStaticFields(HTML4.class, String.class));
        }
        return htmlTags;
    }

    public static String toUrlString(String u) {
        String host;
        String path;
        String query;
        if (u != null) {
            try {
                u = u.trim();
                int ix = u.indexOf("://");
                String protocol = u.substring(0, ix);
                int start = ix + "://".length();
                int ix2 = u.indexOf("/", start);
                if (ix2 < 0) {
                    int ix3 = u.indexOf(63, start);
                    if (ix3 < 0) {
                        host = u.substring(start);
                        path = null;
                        query = null;
                    } else {
                        host = u.substring(start, ix3);
                        path = null;
                        query = u.substring(ix3 + 1);
                    }
                } else {
                    host = u.substring(start, ix2);
                    int ix4 = u.indexOf(63);
                    if (ix4 < 0) {
                        path = u.substring(ix2);
                        query = null;
                    } else {
                        path = u.substring(ix2, ix4);
                        query = u.substring(ix4 + 1);
                    }
                }
                URI uri = new URI(protocol, host, path, query, null);
                return uri.toASCIIString();
            } catch (Exception e) {
                log.error((Throwable) e);
            }
        }
        return u;
    }

    public static byte[] parseBase64Data(String s) throws Exception {
        int ix = s.indexOf(44);
        if (ix >= 0) {
            return Base64.decode(s.substring(ix + 1));
        }
        return null;
    }
}
