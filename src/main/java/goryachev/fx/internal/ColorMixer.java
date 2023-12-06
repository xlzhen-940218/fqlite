package goryachev.fx.internal;

import goryachev.fx.FX;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/ColorMixer.class */
public class ColorMixer {
    private Object value;

    public ColorMixer() {
    }

    public ColorMixer(Color base) {
        this.value = base;
    }

    public ColorMixer(ColorMixer x) {
        if (x != null) {
            this.value = x.value;
        }
    }

    public void add(Color c) {
        if (this.value instanceof Color) {
            this.value = new Color[]{(Color) this.value, c};
        } else if (this.value instanceof Color[]) {
            Color[] old = (Color[]) this.value;
            Color[] cs = new Color[old.length + 1];
            System.arraycopy(old, 0, cs, 0, old.length);
            cs[old.length] = c;
            this.value = cs;
        } else {
            this.value = c;
        }
    }

    public Color getColor() {
        if (this.value instanceof Color) {
            return (Color) this.value;
        }
        if (this.value instanceof Color[]) {
            Color c = mix((Color[]) this.value);
            this.value = c;
            return c;
        }
        return null;
    }

    public static Color mix(Color[] colors) {
        return FX.mix(colors);
    }
}
