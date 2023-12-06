package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.WeakList;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/GlobalProperties.class */
public class GlobalProperties {
    protected static final Log log = Log.get("GlobalProperties");
    private static final WeakList<GlobalProperty<?>> properties = new WeakList<>();

    public static <T> void add(GlobalProperty<T> p) {
        properties.add(p);
        p.addListener((observableValue, obj, obj2) -> {
            store(p);
        });
        load(p);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T> void store(GlobalProperty<T> p) {
        String s;
        try {
            String k = p.getName();
            T v = p.getValue();
            if (v == null) {
                s = null;
            } else {
                StringConverter<T> c = p.getConverter();
                s = c.toString(v);
            }
            GlobalSettings.setString(k, s);
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }

    protected static <T> void load(GlobalProperty<T> p) {
        try {
            String k = p.getName();
            String s = GlobalSettings.getString(k);
            if (s != null) {
                T v = p.getConverter().fromString(s);
                p.setValue(v);
            }
        } catch (Exception e) {
            log.error((Throwable) e);
        }
    }
}
