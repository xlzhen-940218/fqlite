package goryachev.fx;

import goryachev.common.util.CPlatform;
import goryachev.common.util.html.HTML4;
import goryachev.fx.FxStyleSheet;
import goryachev.fx.internal.CssTools;
import goryachev.fx.internal.FxCssProp;
import goryachev.fx.internal.StandardFxProperties;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CommonStyles.class */
public class CommonStyles extends FxStyleSheet {
    public static final CssStyle BOLD = new CssStyle("CommonStyles_BOLD");
    public static final CssStyle DISABLE_ALTERNATIVE_ROW_COLOR = new CssStyle("CommonStyles_DISABLE_ALTERNATIVE_ROW_COLOR");

    public CommonStyles() {
        Theme theme = Theme.current();
        add(selector(".root").defines(prop("-fx-font-smoothing-type", "gray"), prop("-fx-accent", FX.alpha(theme.selectedTextBG, 0.7d)), prop("-fx-base", theme.base), prop("-fx-highlight-text-fill", theme.selectedTextFG), prop("-fx-focus-color", theme.focus), prop("-fx-faint-focus-color", StandardFxProperties.TRANSPARENT), selector(".text-input").defines(textFill(theme.textFG)), selector(".text-input", FOCUSED).defines(textFill(theme.textFG))));
        if (CPlatform.isMac()) {
            add(selector(".root").defines(prop("-fx-font-size", "9pt"), prop("-fx-font-family", "Dialog")));
        }
        add(button(theme), comboBox(theme), menuBar(theme), popupMenu(theme), scrollBar(theme), scrollPane(theme), table(theme), text(theme), treeTable(theme), toolbar(theme), buttonPane(theme), cpane(), selector(DISABLE_ALTERNATIVE_ROW_COLOR).defines(new FxCssProp("-fx-control-inner-background-alt", "-fx-control-inner-background")), selector(BOLD).defines(fontWeight(StandardFxProperties.BOLD)));
    }

    protected Object button(Theme theme) {
        String affirm = CssTools.toColor(theme.affirm);
        String destruct = CssTools.toColor(theme.destruct);
        return new Object[]{selector(FxButton.AFFIRM).defines(backgroundColor(String.format("-fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, linear-gradient(to bottom, ladder(%1$s, derive(%1$s,8%%) 75%%, derive(%1$s,10%%) 80%% ), derive(%1$s,-8%%))", affirm)), selector(FOCUSED).defines(backgroundColor("-fx-focus-color, -fx-inner-border, -fx-body-color, -fx-faint-focus-color, " + CssTools.toColor(affirm)))), selector(FxButton.DESTRUCT).defines(backgroundColor(String.format("-fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, linear-gradient(to bottom, ladder(%1$s, derive(%1$s,8%%) 75%%, derive(%1$s,10%%) 80%% ), derive(%1$s,-8%%))", destruct)), selector(FOCUSED).defines(backgroundColor("-fx-focus-color, -fx-inner-border, -fx-body-color, -fx-faint-focus-color, " + CssTools.toColor(destruct)))), selector(FlatButton.STYLE).defines(backgroundColor(StandardFxProperties.TRANSPARENT), backgroundInsets(0), backgroundRadius(0), padding(spaces("0.33333em 0.666667em 0.333333em 0.666667em")), textFill("-fx-text-base-color"), prop("-fx-alignment", HTML4.CENTER), prop("-fx-content-display", "left"), selector(HOVER).defines(backgroundColor(Color.LIGHTGRAY), backgroundInsets(0), backgroundRadius(px(4))), selector(FOCUSED).defines(backgroundColor(commas(theme.focus, theme.base)), backgroundInsets("0, 1"), backgroundRadius(px(4))), selector(FOCUSED, HOVER).defines(backgroundColor(commas(theme.focus, Color.LIGHTGRAY)), backgroundInsets("0 0 0 0, 1 1 1 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(DISABLED).defines(opacity(0.4d)), selector(ARMED).defines(textFill(Color.BLACK), backgroundColor(commas(theme.focus, Color.WHITE)), backgroundInsets("0, 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(ARMED, HOVER).defines(textFill(Color.BLACK), backgroundColor(commas(theme.focus, Color.WHITE)), backgroundInsets("0, 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(FlatToggleButton.STYLE).defines(backgroundColor(StandardFxProperties.TRANSPARENT), backgroundInsets(0), backgroundRadius(0), padding(spaces("0.33333em 0.666667em 0.333333em 0.666667em")), textFill("-fx-text-base-color"), prop("-fx-alignment", HTML4.CENTER), prop("-fx-content-display", "left"), selector(HOVER).defines(backgroundColor(Color.LIGHTGRAY), backgroundInsets(0), backgroundRadius(px(4))), selector(FOCUSED).defines(backgroundColor(commas(theme.focus, theme.base)), backgroundInsets("0, 1"), backgroundRadius(px(4))), selector(FOCUSED, HOVER).defines(backgroundColor(commas(theme.focus, Color.LIGHTGRAY)), backgroundInsets("0 0 0 0, 1 1 1 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(DISABLED).defines(opacity(0.4d)), selector(ARMED).defines(textFill(Color.BLACK), backgroundColor(commas(theme.focus, Color.WHITE)), backgroundInsets("0, 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(ARMED, HOVER).defines(textFill(Color.BLACK), backgroundColor(commas(theme.focus, Color.WHITE)), backgroundInsets("0, 1"), backgroundRadius(commas(px(4), px(4 - 1)))), selector(SELECTED).defines(textFill(Color.BLACK), backgroundColor(Color.WHITE), backgroundInsets("0, 1"), backgroundRadius(px(4)), selector(FOCUSED).defines(backgroundColor(commas(theme.focus, Color.WHITE)), backgroundInsets("0 0 0 0, 1 1 1 1"), backgroundRadius(commas(px(4), px(4 - 1))), selector(HOVER).defines(backgroundColor(commas(theme.focus, FX.gray(250))), backgroundInsets("0 0 0 0, 1 1 1 1"), backgroundRadius(commas(px(4), px(4 - 1))))), selector(HOVER).defines(backgroundColor(commas(theme.focus, FX.gray(250))), backgroundInsets("0 0 0 0, 1 1 1 1"), backgroundRadius(commas(px(4), px(4 - 1)))))))};
    }

    protected Object checkbox(Theme theme) {
        return selector(".check-box").defines(labelPadding("0.0em 0.0em 0.0em 0.416667em"), textFill("-fx-text-background-color"), selector(HOVER, "> .box").defines(color(theme.control)), selector(ARMED).defines(color(Color.GREEN)), selector(" > .box").defines(backgroundColor(theme.control), backgroundInsets(3), backgroundRadius(3), padding("0.5em"), selector("> .mark").defines(backgroundColor(null), padding("0.4em"), shape("M49,119 L64,106 L100,139 L176,66 L188,78 L100,166 z"))), selector(FOCUSED, "> .box").defines(backgroundColor(commas(theme.focus, StandardFxProperties.TRANSPARENT, theme.control)), backgroundInsets(commas(0, 1, 3)), backgroundRadius(commas(3, 2, 1)), padding("0.5em")), selector(SELECTED, "> .box > .mark").defines(backgroundColor(theme.outline), backgroundInsets(0)), selector(":indeterminate > .box").defines(padding(0), selector("> .mark").defines(shape("M0,0H10V2H0Z"), scaleShape(false), padding("0.5em"))));
    }

    protected Object comboBox(Theme theme) {
        return selector(".combo-box-base").defines(backgroundRadius(0), selector(EDITABLE).defines(selector("> .text-field").defines(backgroundColor(theme.textBG), backgroundInsets(1), backgroundRadius(0)), selector(FOCUSED).defines(backgroundColor(theme.focus), backgroundInsets(0), backgroundRadius(0), selector("> .text-field").defines(backgroundColor(theme.textBG), backgroundInsets(spaces(1, 0, 1, 1)), backgroundRadius(0), effect(null)))), selector(FOCUSED).defines(backgroundRadius(0), shadow()));
    }

    protected Object menuBar(Theme theme) {
        Color bg = FX.alpha(theme.focus, 0.8d);
        return new Object[]{selector(".menu-bar").defines(backgroundColor("-fx-background"), backgroundInsets(0), backgroundRadius(0), selector("> .container").defines(selector("> .menu-button:hover").defines(backgroundColor(bg)), selector("> .menu-button:focused").defines(backgroundColor(bg)), selector("> .menu-button:showing").defines(backgroundColor(bg)))), selector(".menu-item:focused").defines(backgroundColor(bg))};
    }

    protected Object popupMenu(Theme theme) {
        return new Object[]{selector(FxPopupMenu.MENU).defines(fontWeight("normal"))};
    }

    protected Object radioButton(Theme theme) {
        return new Object[]{selector(".radio-button").defines(selector(".text").defines(fill("-fx-text-base-color"))), selector(".radio-button>.radio, .radio-button>.radio.unfocused, .radio-button:disabled>.radio, .radio-button:selected>.radio").defines(borderRadius(100), borderColor("gray"), borderWidth((Object) 2), backgroundRadius(100), backgroundColor(StandardFxProperties.TRANSPARENT), padding(3))};
    }

    protected Object scrollBar(Theme theme) {
        Color fg = FX.alpha(theme.control, 0.5d);
        double w2 = 7.0d + 3.0d + 3.0d;
        return selector(".scroll-bar").defines(selector(":vertical").defines(maxWidth(w2), padding(0), selector(".thumb").defines(backgroundColor(fg), backgroundInsets(Double.valueOf(3.0d)), backgroundRadius(Double.valueOf(3.0d)), maxWidth(7.0d)), selector(".increment-button").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefWidth(w2), prefHeight(0.0d)), selector(".decrement-button").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefWidth(w2), prefHeight(0.0d)), selector(".increment-arrow").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefWidth(0.0d), prefHeight(0.0d)), selector(".decrement-arrow").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefWidth(0.0d), prefHeight(0.0d))), selector(":horizontal").defines(maxHeight(w2), padding(0), selector(".thumb").defines(backgroundColor(fg), backgroundInsets(Double.valueOf(3.0d)), backgroundRadius(Double.valueOf(3.0d)), maxHeight(7.0d)), selector(".increment-button").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefHeight(w2), prefWidth(0.0d)), selector(".decrement-button").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefHeight(w2), prefWidth(0.0d)), selector(".increment-arrow").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefHeight(0.0d), prefWidth(0.0d)), selector(".decrement-arrow").defines(minWidth(0.0d), maxWidth(0.0d), maxHeight(0.0d), prefHeight(0.0d), prefWidth(0.0d))));
    }

    protected Object scrollPane(Theme theme) {
        return selector(".scroll-pane").defines(new Selector(" > .viewport").defines(backgroundColor(theme.textBG)));
    }

    protected Object treeTable(Theme theme) {
        return new Object[]{selector(".tree-table-cell").defines(padding(0)), selector(".tree-table-row-cell").defines(padding(0))};
    }

    protected Object table(Theme theme) {
        Color c = FX.alpha(theme.selectedTextBG, 0.15d);
        FX.mix(theme.selectedTextBG, theme.textFG, 0.9d);
        return new Object[]{selector(".table-cell").defines(padding(0)), selector(".table-row-cell").defines(padding(0)), selector(".table-row-cell:filled:selected").defines(backgroundColor(c), backgroundInsets(spaces(0, 0, 1, 0))), selector(".table-row-cell:filled").defines(new Object[0]), selector(".table-row-cell:empty").defines(backgroundColor(StandardFxProperties.TRANSPARENT), borderWidth((Object) 0)), selector(".table-row-cell:empty:odd, .table-row-cell:empty:even, .indexed-cell:odd:empty, .indexed-cell:even:empty").defines(backgroundColor(StandardFxProperties.TRANSPARENT), borderWidth((Object) 0)), selector(".table-view > .virtual-flow > .clipped-container > .sheet > .table-row-cell .table-cell:selected").defines(backgroundColor(commas("-fx-table-cell-border-color", "-fx-background")), backgroundInsets(commas(0, spaces(0, 0, 1, 0))))};
    }

    protected Object text(Theme theme) {
        return new Object[]{selector(".text").defines(prop("-fx-font-smoothing-type", "gray")), selector(".text-area").defines(backgroundColor(theme.textBG), selector(".content").defines(backgroundColor(theme.textBG), backgroundRadius(0)), selector(FOCUSED, ".content").defines(backgroundColor(theme.textBG), backgroundRadius(0), backgroundInsets(0))), selector(".text-input").defines(backgroundInsets(commas(0, 1)), backgroundColor(commas(theme.outline, theme.textBG)), backgroundRadius(commas(0, 0)), new Selector(FOCUSED).defines(textFill(theme.textFG), prop("-fx-highlight-text-fill", theme.selectedTextFG), backgroundInsets(commas(0, 1)), backgroundColor(commas(theme.focus, theme.textBG)), backgroundRadius(commas(0, 0)), shadow()))};
    }

    protected Object toolbar(Theme theme) {
        return new Object[]{selector(FxToolBar.STYLE).defines(prop("-fx-spacing", 1), padding(2, 2, 2, 2))};
    }

    protected Object buttonPane(Theme theme) {
        return new Object[]{selector(FxButtonPane.PANE).defines(borderWidth((Object) 0), padding(10), backgroundColor(FX.alpha(Color.GRAY, 0.1d)))};
    }

    protected Object cpane() {
        return new Object[]{selector(CPane.STYLE).defines(padding(10), prop("-ag-hgap", 10), prop("-ag-vgap", 5))};
    }
}
