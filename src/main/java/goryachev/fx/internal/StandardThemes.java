package goryachev.fx.internal;

import goryachev.fx.FX;
import goryachev.fx.Theme;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/StandardThemes.class */
public class StandardThemes {
    public static Theme createLightTheme() {
        Color base = FX.rgb(15527148);
        Theme t = new Theme();
        t.affirm = FX.mix(base, Color.LIGHTGREEN, 0.8d);
        t.base = base;
        t.control = FX.rgb(6710886);
        t.destruct = FX.mix(base, Color.MAGENTA, 0.7d);
        t.focus = FX.rgb(4775240);
        t.outline = FX.rgb(14540253);
        t.selectedTextBG = FX.rgb(255, 255, 148, 0.7d);
        t.selectedTextFG = Color.BLACK;
        t.textBG = Color.WHITE;
        t.textFG = Color.BLACK;
        return t;
    }
}
