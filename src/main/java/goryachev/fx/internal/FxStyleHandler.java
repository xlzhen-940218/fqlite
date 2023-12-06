package goryachev.fx.internal;

import goryachev.common.util.CMap;
import goryachev.common.util.SB;
import java.util.Map;
import java.util.StringTokenizer;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/FxStyleHandler.class */
public class FxStyleHandler {
    private final CMap<String, String> styles = new CMap<>();

    public FxStyleHandler(String style) {
        if (style != null) {
            StringTokenizer tok = new StringTokenizer(style, ";");
            while (tok.hasMoreElements()) {
                String s = tok.nextToken();
                int ix = s.indexOf(58);
                if (ix > 0) {
                    String prop = s.substring(0, ix);
                    this.styles.put(prop, s);
                }
            }
        }
    }

    public void put(String property, Object value) {
        String v = String.valueOf(property) + ":" + value;
        this.styles.put(property, v);
    }

    public void remove(String property) {
        this.styles.remove(property);
    }

    public String toStyleString() {
        SB sb = new SB(256);
        for (Map.Entry<String, String> en : this.styles.entrySet()) {
            sb.append(en.getValue()).append(';');
        }
        return sb.toString();
    }
}
