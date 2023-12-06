package goryachev.fx.internal;

import fqlite.base.Global;
import goryachev.fx.CssPseudo;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.FontSmoothingType;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/StandardFxProperties.class */
public class StandardFxProperties {
    public static final String BOLD = "bold";
    public static final String TRANSPARENT = "transparent";
    public static final String TABLE = ".table";
    public static final String CENTER_LEFT = "CENTER_LEFT";
    public static final CssPseudo ARMED = new CssPseudo(":armed");
    public static final CssPseudo DISABLED = new CssPseudo(":disabled");
    public static final CssPseudo EDITABLE = new CssPseudo(":editable");
    public static final CssPseudo FOCUSED = new CssPseudo(":focused");
    public static final CssPseudo HOVER = new CssPseudo(":hover");
    public static final CssPseudo PRESSED = new CssPseudo(":pressed");
    public static final CssPseudo SELECTED = new CssPseudo(":selected");
    protected static final Color R = Color.RED;
    protected static final Color G = Color.GREEN;
    protected static final Color B = Color.BLUE;
    protected static final Color M = Color.MAGENTA;

    public static FxCssProp alignment(Object x) {
        return new FxCssProp("-fx-alignment", x);
    }

    public static FxCssProp backgroundColor(Object x) {
        return new FxCssProp("-fx-background-color", CssTools.toColor(x));
    }

    public static FxCssProp backgroundImage(Object x) {
        return new FxCssProp("-fx-background-image", CssTools.toValue(x));
    }

    public static FxCssProp backgroundInsets(Object x) {
        return new FxCssProp("-fx-background-insets", CssTools.toValue(x));
    }

    public static FxCssProp backgroundRadius(Object x) {
        return new FxCssProp("-fx-background-radius", CssTools.toValue(x));
    }

    public static FxCssProp borderColor(Object x) {
        return new FxCssProp("-fx-border-color", CssTools.toColor(x));
    }

    public static FxCssProp borderColor(Object... xs) {
        return new FxCssProp("-fx-border-color", CssTools.toColors(xs));
    }

    public static FxCssProp borderRadius(Object x) {
        return new FxCssProp("-fx-border-radius", CssTools.toValue(x));
    }

    public static FxCssProp borderWidth(Object x) {
        return new FxCssProp("-fx-border-width", CssTools.toValue(x));
    }

    public static FxCssProp borderWidth(Object... xs) {
        return new FxCssProp("-fx-border-width", CssTools.toValues(xs));
    }

    public static FxCssProp cellSize(Object x) {
        return new FxCssProp("-fx-cell-size", x);
    }

    public static FxCssProp color(Object x) {
        return new FxCssProp("-fx-color", CssTools.toColor(x));
    }

    public static FxCssProp effect(Object x) {
        return new FxCssProp("-fx-effect", x);
    }

    public static FxCssProp fill(Object x) {
        return new FxCssProp("-fx-fill", x);
    }

    public static FxCssProp fitToHeight(boolean x) {
        return new FxCssProp("-fx-fit-to-height", Boolean.valueOf(x));
    }

    public static FxCssProp fitToWidth(boolean x) {
        return new FxCssProp("-fx-fit-to-width", Boolean.valueOf(x));
    }

    public static FxCssProp fixedCellSize(Object x) {
        return new FxCssProp("-fx-fixed-cell-size", x);
    }

    public static FxCssProp fontFamily(Object x) {
        return new FxCssProp("-fx-font-family", x);
    }

    public static FxCssProp fontSize(Object x) {
        return new FxCssProp("-fx-font-size", x);
    }

    public static FxCssProp fontSmoothingType(FontSmoothingType x) {
        return new FxCssProp("-fx-font-smoothing-type", CssTools.toValue(x));
    }

    public static FxCssProp fontStyle(Object x) {
        return new FxCssProp("-fx-font-style", x);
    }

    public static FxCssProp fontWeight(Object x) {
        return new FxCssProp("-fx-font-weight", x);
    }

    public static FxCssProp hBarPolicy(ScrollPane.ScrollBarPolicy x) {
        return new FxCssProp("-fx-hbar-policy", CssTools.toValue(x));
    }

    public static FxCssProp labelPadding(Object x) {
        return new FxCssProp("-fx-label-padding", x);
    }

    public static FxCssProp labelPadding(double top, double right, double bottom, double left) {
        return new FxCssProp("-fx-label-padding", spaces(Double.valueOf(top), Double.valueOf(right), Double.valueOf(bottom), Double.valueOf(left)));
    }

    public static FxCssProp maxHeight(double x) {
        return new FxCssProp("-fx-max-height", Double.valueOf(x));
    }

    public static FxCssProp maxHeight(Object x) {
        return new FxCssProp("-fx-max-height", x);
    }

    public static FxCssProp maxWidth(double x) {
        return new FxCssProp("-fx-max-width", Double.valueOf(x));
    }

    public static FxCssProp maxWidth(Object x) {
        return new FxCssProp("-fx-max-width", x);
    }

    public static FxCssProp minHeight(double x) {
        return new FxCssProp("-fx-min-height", Double.valueOf(x));
    }

    public static FxCssProp minHeight(Object x) {
        return new FxCssProp("-fx-min-height", x);
    }

    public static FxCssProp minWidth(double x) {
        return new FxCssProp("-fx-min-width", Double.valueOf(x));
    }

    public static FxCssProp minWidth(Object x) {
        return new FxCssProp("-fx-min-width", x);
    }

    public static FxCssProp opacity(double x) {
        return new FxCssProp("-fx-opacity", Double.valueOf(x));
    }

    public static FxCssProp overrunStyle(OverrunStyle x) {
        return new FxCssProp("-fx-text-overrun", CssTools.toValue(x));
    }

    public static FxCssProp padding(Object x) {
        return new FxCssProp("-fx-padding", CssTools.toValue(x));
    }

    public static FxCssProp padding(int top, int right, int bottom, int left) {
        return new FxCssProp("-fx-padding", spaces(Integer.valueOf(top), Integer.valueOf(right), Integer.valueOf(bottom), Integer.valueOf(left)));
    }

    public static FxCssProp padding(double top, double right, double bottom, double left) {
        return new FxCssProp("-fx-padding", spaces(Double.valueOf(top), Double.valueOf(right), Double.valueOf(bottom), Double.valueOf(left)));
    }

    public static FxCssProp prefHeight(double x) {
        return new FxCssProp("-fx-pref-height", Double.valueOf(x));
    }

    public static FxCssProp prefWidth(double x) {
        return new FxCssProp("-fx-pref-width", Double.valueOf(x));
    }

    public static FxCssProp prefWidth(String x) {
        return new FxCssProp("-fx-pref-width", x);
    }

    public static FxCssProp regionBackground(Object x) {
        return new FxCssProp("-fx-region-background", CssTools.toValue(x));
    }

    public static FxCssProp scaleShape(boolean x) {
        return new FxCssProp("-fx-shape", CssTools.toValue(x));
    }

    public static FxCssProp shape(Object x) {
        return new FxCssProp("-fx-shape", CssTools.toQuotedString(x));
    }

    public static FxCssProp smooth(boolean x) {
        return new FxCssProp("-fx-smooth", CssTools.toValue(x));
    }

    public static FxCssProp stroke(Object x) {
        return new FxCssProp("-fx-stroke", CssTools.toColor(x));
    }

    public static FxCssProp strokeLineCap(StrokeLineCap x) {
        return new FxCssProp("-fx-stroke-width", CssTools.toValue(x));
    }

    public static FxCssProp strokeWidth(double x) {
        return new FxCssProp("-fx-stroke-width", Double.valueOf(x));
    }

    public static FxCssProp textAlignment(Object x) {
        return new FxCssProp("-fx-text-alignment", x);
    }

    public static FxCssProp textFill(Object x) {
        return new FxCssProp("-fx-text-fill", CssTools.toColor(x));
    }

    public static FxCssProp textOverrun(OverrunStyle x) {
        return new FxCssProp("-fx-text-overrun", CssTools.toColor(x));
    }

    public static FxCssProp translateX(double x) {
        return new FxCssProp("-fx-translate-x", Double.valueOf(x));
    }

    public static FxCssProp translateY(double x) {
        return new FxCssProp("-fx-translate-y", Double.valueOf(x));
    }

    public static FxCssProp vBarPolicy(ScrollPane.ScrollBarPolicy x) {
        return new FxCssProp("-fx-vbar-policy", CssTools.toValue(x));
    }

    public static FxCssProp prop(String name, Object val) {
        return new FxCssProp(name, val);
    }

    public static String commas(Object... xs) {
        return CssTools.list(",", xs);
    }

    public static String spaces(Object... xs) {
        return CssTools.list(Global.REGULAR_RECORD, xs);
    }

    public static String px(int px) {
        return String.valueOf(px) + "px";
    }

    public static Object shadow() {
        return effect("dropshadow(two-pass-box, rgba(0, 0, 0, 0.4), 12, 0, 2, 2)");
    }
}
