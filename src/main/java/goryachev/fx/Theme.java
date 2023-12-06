package goryachev.fx;

import goryachev.common.util.Keep;
import goryachev.common.util.SB;
import goryachev.fx.internal.StandardThemes;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import javafx.scene.paint.Color;

@Keep
/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/Theme.class */
public class Theme {
    public Color affirm;
    public Color base;
    public Color control;
    public Color destruct;
    public Color focus;
    public Color outline;
    public Color selectedTextBG;
    public Color selectedTextFG;
    public Color textBG;
    public Color textFG;
    private static Theme current;

    public static Theme current() {
        if (current == null) {
            Theme t = loadFromSettings();
            if (t == null) {
                t = StandardThemes.createLightTheme();
            }
            check(t);
            current = t;
        }
        return current;
    }

    private static Theme loadFromSettings() {
        return null;
    }

    private static void check(Theme t) {
        Object v;
        SB sb = null;
        Field[] fs = Theme.class.getDeclaredFields();
        for (Field f : fs) {
            int m = f.getModifiers();
            if (Modifier.isPublic(m) && !Modifier.isStatic(m)) {
                try {
                    v = f.get(t);
                } catch (Exception e) {
                    v = null;
                }
                if (v == null) {
                    if (sb == null) {
                        sb = new SB();
                        sb.append("Missing theme values: ");
                    } else {
                        sb.a(",");
                    }
                    sb.append(f.getName());
                }
            }
        }
        if (sb != null) {
            throw new Error(sb.toString());
        }
    }

    public Color gray(int gray) {
        if (isLight()) {
            return Color.rgb(gray, gray, gray);
        }
        return Color.rgb(255 - gray, 255 - gray, 255 - gray);
    }

    public boolean isLight() {
        return this.textBG.getBrightness() > 0.5d;
    }

    public boolean isDark() {
        return !isLight();
    }
}
