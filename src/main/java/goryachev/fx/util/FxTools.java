package goryachev.fx.util;

import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/util/FxTools.class */
public class FxTools {
    public static int getMaximumValue(List<Integer> items) {
        int rv = Integer.MIN_VALUE;
        for (Integer num : items) {
            int v = num.intValue();
            if (rv < v) {
                rv = v;
            }
        }
        return rv;
    }
}
