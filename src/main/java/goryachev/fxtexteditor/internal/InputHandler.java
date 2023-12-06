package goryachev.fxtexteditor.internal;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.fx.FX;
import goryachev.fx.KeyMap;
import goryachev.fxtexteditor.Edit;
import goryachev.fxtexteditor.EditorSelection;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.SelectionController;
import goryachev.fxtexteditor.VFlow;
import java.util.function.BiConsumer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/InputHandler.class */
public class InputHandler {
    protected static final Log log = Log.get("InputHandler");
    protected final FxTextEditor editor;
    protected final VFlow vflow;
    protected final SelectionController selector;
    private boolean fastAutoScroll;
    private boolean autoScrollUp;
    private boolean inTrackpadScroll;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$input$KeyCode;
    private Duration autoScrollPeriod = Duration.millis(100.0d);
    private double fastScrollThreshold = 100.0d;
    private double autoScrollStepFast = 200.0d;
    private double autoScrollStepSlow = 20.0d;
    private double scrollWheelStepSize = 0.1d;
    private int lastx = -1;
    private int lasty = -1;
    protected BiConsumer<FxTextEditor, Marker> doubleClickHandler = new SimpleWordSelector();
    protected BiConsumer<FxTextEditor, Marker> tripleClickHandler = new LineSelector();
    protected final Timeline autoScrollTimer = new Timeline(new KeyFrame(this.autoScrollPeriod, ev -> {
        autoScroll();
    }, new KeyValue[0]));

    static /* synthetic */ int[] $SWITCH_TABLE$javafx$scene$input$KeyCode() {
        int[] iArr = $SWITCH_TABLE$javafx$scene$input$KeyCode;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[KeyCode.values().length];
        try {
            iArr2[KeyCode.A.ordinal()] = 37;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[KeyCode.ACCEPT.ordinal()] = 159;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[KeyCode.ADD.ordinal()] = 77;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[KeyCode.AGAIN.ordinal()] = 181;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[KeyCode.ALL_CANDIDATES.ordinal()] = 169;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[KeyCode.ALPHANUMERIC.ordinal()] = 163;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[KeyCode.ALT.ordinal()] = 8;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[KeyCode.ALT_GRAPH.ordinal()] = 186;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr2[KeyCode.AMPERSAND.ordinal()] = 135;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr2[KeyCode.ASTERISK.ordinal()] = 136;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr2[KeyCode.AT.ordinal()] = 142;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr2[KeyCode.B.ordinal()] = 38;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr2[KeyCode.BACK_QUOTE.ordinal()] = 113;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            iArr2[KeyCode.BACK_SLASH.ordinal()] = 64;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            iArr2[KeyCode.BACK_SPACE.ordinal()] = 2;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            iArr2[KeyCode.BEGIN.ordinal()] = 187;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            iArr2[KeyCode.BRACELEFT.ordinal()] = 140;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            iArr2[KeyCode.BRACERIGHT.ordinal()] = 141;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            iArr2[KeyCode.C.ordinal()] = 39;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            iArr2[KeyCode.CANCEL.ordinal()] = 4;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            iArr2[KeyCode.CAPS.ordinal()] = 10;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            iArr2[KeyCode.CHANNEL_DOWN.ordinal()] = 219;
        } catch (NoSuchFieldError unused22) {
        }
        try {
            iArr2[KeyCode.CHANNEL_UP.ordinal()] = 218;
        } catch (NoSuchFieldError unused23) {
        }
        try {
            iArr2[KeyCode.CIRCUMFLEX.ordinal()] = 144;
        } catch (NoSuchFieldError unused24) {
        }
        try {
            iArr2[KeyCode.CLEAR.ordinal()] = 5;
        } catch (NoSuchFieldError unused25) {
        }
        try {
            iArr2[KeyCode.CLOSE_BRACKET.ordinal()] = 65;
        } catch (NoSuchFieldError unused26) {
        }
        try {
            iArr2[KeyCode.CODE_INPUT.ordinal()] = 171;
        } catch (NoSuchFieldError unused27) {
        }
        try {
            iArr2[KeyCode.COLON.ordinal()] = 143;
        } catch (NoSuchFieldError unused28) {
        }
        try {
            iArr2[KeyCode.COLORED_KEY_0.ordinal()] = 207;
        } catch (NoSuchFieldError unused29) {
        }
        try {
            iArr2[KeyCode.COLORED_KEY_1.ordinal()] = 208;
        } catch (NoSuchFieldError unused30) {
        }
        try {
            iArr2[KeyCode.COLORED_KEY_2.ordinal()] = 209;
        } catch (NoSuchFieldError unused31) {
        }
        try {
            iArr2[KeyCode.COLORED_KEY_3.ordinal()] = 210;
        } catch (NoSuchFieldError unused32) {
        }
        try {
            iArr2[KeyCode.COMMA.ordinal()] = 21;
        } catch (NoSuchFieldError unused33) {
        }
        try {
            iArr2[KeyCode.COMMAND.ordinal()] = 223;
        } catch (NoSuchFieldError unused34) {
        }
        try {
            iArr2[KeyCode.COMPOSE.ordinal()] = 185;
        } catch (NoSuchFieldError unused35) {
        }
        try {
            iArr2[KeyCode.CONTEXT_MENU.ordinal()] = 155;
        } catch (NoSuchFieldError unused36) {
        }
        try {
            iArr2[KeyCode.CONTROL.ordinal()] = 7;
        } catch (NoSuchFieldError unused37) {
        }
        try {
            iArr2[KeyCode.CONVERT.ordinal()] = 157;
        } catch (NoSuchFieldError unused38) {
        }
        try {
            iArr2[KeyCode.COPY.ordinal()] = 178;
        } catch (NoSuchFieldError unused39) {
        }
        try {
            iArr2[KeyCode.CUT.ordinal()] = 177;
        } catch (NoSuchFieldError unused40) {
        }
        try {
            iArr2[KeyCode.D.ordinal()] = 40;
        } catch (NoSuchFieldError unused41) {
        }
        try {
            iArr2[KeyCode.DEAD_ABOVEDOT.ordinal()] = 125;
        } catch (NoSuchFieldError unused42) {
        }
        try {
            iArr2[KeyCode.DEAD_ABOVERING.ordinal()] = 127;
        } catch (NoSuchFieldError unused43) {
        }
        try {
            iArr2[KeyCode.DEAD_ACUTE.ordinal()] = 120;
        } catch (NoSuchFieldError unused44) {
        }
        try {
            iArr2[KeyCode.DEAD_BREVE.ordinal()] = 124;
        } catch (NoSuchFieldError unused45) {
        }
        try {
            iArr2[KeyCode.DEAD_CARON.ordinal()] = 129;
        } catch (NoSuchFieldError unused46) {
        }
        try {
            iArr2[KeyCode.DEAD_CEDILLA.ordinal()] = 130;
        } catch (NoSuchFieldError unused47) {
        }
        try {
            iArr2[KeyCode.DEAD_CIRCUMFLEX.ordinal()] = 121;
        } catch (NoSuchFieldError unused48) {
        }
        try {
            iArr2[KeyCode.DEAD_DIAERESIS.ordinal()] = 126;
        } catch (NoSuchFieldError unused49) {
        }
        try {
            iArr2[KeyCode.DEAD_DOUBLEACUTE.ordinal()] = 128;
        } catch (NoSuchFieldError unused50) {
        }
        try {
            iArr2[KeyCode.DEAD_GRAVE.ordinal()] = 119;
        } catch (NoSuchFieldError unused51) {
        }
        try {
            iArr2[KeyCode.DEAD_IOTA.ordinal()] = 132;
        } catch (NoSuchFieldError unused52) {
        }
        try {
            iArr2[KeyCode.DEAD_MACRON.ordinal()] = 123;
        } catch (NoSuchFieldError unused53) {
        }
        try {
            iArr2[KeyCode.DEAD_OGONEK.ordinal()] = 131;
        } catch (NoSuchFieldError unused54) {
        }
        try {
            iArr2[KeyCode.DEAD_SEMIVOICED_SOUND.ordinal()] = 134;
        } catch (NoSuchFieldError unused55) {
        }
        try {
            iArr2[KeyCode.DEAD_TILDE.ordinal()] = 122;
        } catch (NoSuchFieldError unused56) {
        }
        try {
            iArr2[KeyCode.DEAD_VOICED_SOUND.ordinal()] = 133;
        } catch (NoSuchFieldError unused57) {
        }
        try {
            iArr2[KeyCode.DECIMAL.ordinal()] = 80;
        } catch (NoSuchFieldError unused58) {
        }
        try {
            iArr2[KeyCode.DELETE.ordinal()] = 82;
        } catch (NoSuchFieldError unused59) {
        }
        try {
            iArr2[KeyCode.DIGIT0.ordinal()] = 25;
        } catch (NoSuchFieldError unused60) {
        }
        try {
            iArr2[KeyCode.DIGIT1.ordinal()] = 26;
        } catch (NoSuchFieldError unused61) {
        }
        try {
            iArr2[KeyCode.DIGIT2.ordinal()] = 27;
        } catch (NoSuchFieldError unused62) {
        }
        try {
            iArr2[KeyCode.DIGIT3.ordinal()] = 28;
        } catch (NoSuchFieldError unused63) {
        }
        try {
            iArr2[KeyCode.DIGIT4.ordinal()] = 29;
        } catch (NoSuchFieldError unused64) {
        }
        try {
            iArr2[KeyCode.DIGIT5.ordinal()] = 30;
        } catch (NoSuchFieldError unused65) {
        }
        try {
            iArr2[KeyCode.DIGIT6.ordinal()] = 31;
        } catch (NoSuchFieldError unused66) {
        }
        try {
            iArr2[KeyCode.DIGIT7.ordinal()] = 32;
        } catch (NoSuchFieldError unused67) {
        }
        try {
            iArr2[KeyCode.DIGIT8.ordinal()] = 33;
        } catch (NoSuchFieldError unused68) {
        }
        try {
            iArr2[KeyCode.DIGIT9.ordinal()] = 34;
        } catch (NoSuchFieldError unused69) {
        }
        try {
            iArr2[KeyCode.DIVIDE.ordinal()] = 81;
        } catch (NoSuchFieldError unused70) {
        }
        try {
            iArr2[KeyCode.DOLLAR.ordinal()] = 145;
        } catch (NoSuchFieldError unused71) {
        }
        try {
            iArr2[KeyCode.DOWN.ordinal()] = 20;
        } catch (NoSuchFieldError unused72) {
        }
        try {
            iArr2[KeyCode.E.ordinal()] = 41;
        } catch (NoSuchFieldError unused73) {
        }
        try {
            iArr2[KeyCode.EJECT_TOGGLE.ordinal()] = 211;
        } catch (NoSuchFieldError unused74) {
        }
        try {
            iArr2[KeyCode.END.ordinal()] = 15;
        } catch (NoSuchFieldError unused75) {
        }
        try {
            iArr2[KeyCode.ENTER.ordinal()] = 1;
        } catch (NoSuchFieldError unused76) {
        }
        try {
            iArr2[KeyCode.EQUALS.ordinal()] = 36;
        } catch (NoSuchFieldError unused77) {
        }
        try {
            iArr2[KeyCode.ESCAPE.ordinal()] = 11;
        } catch (NoSuchFieldError unused78) {
        }
        try {
            iArr2[KeyCode.EURO_SIGN.ordinal()] = 146;
        } catch (NoSuchFieldError unused79) {
        }
        try {
            iArr2[KeyCode.EXCLAMATION_MARK.ordinal()] = 147;
        } catch (NoSuchFieldError unused80) {
        }
        try {
            iArr2[KeyCode.F.ordinal()] = 42;
        } catch (NoSuchFieldError unused81) {
        }
        try {
            iArr2[KeyCode.F1.ordinal()] = 85;
        } catch (NoSuchFieldError unused82) {
        }
        try {
            iArr2[KeyCode.F10.ordinal()] = 94;
        } catch (NoSuchFieldError unused83) {
        }
        try {
            iArr2[KeyCode.F11.ordinal()] = 95;
        } catch (NoSuchFieldError unused84) {
        }
        try {
            iArr2[KeyCode.F12.ordinal()] = 96;
        } catch (NoSuchFieldError unused85) {
        }
        try {
            iArr2[KeyCode.F13.ordinal()] = 97;
        } catch (NoSuchFieldError unused86) {
        }
        try {
            iArr2[KeyCode.F14.ordinal()] = 98;
        } catch (NoSuchFieldError unused87) {
        }
        try {
            iArr2[KeyCode.F15.ordinal()] = 99;
        } catch (NoSuchFieldError unused88) {
        }
        try {
            iArr2[KeyCode.F16.ordinal()] = 100;
        } catch (NoSuchFieldError unused89) {
        }
        try {
            iArr2[KeyCode.F17.ordinal()] = 101;
        } catch (NoSuchFieldError unused90) {
        }
        try {
            iArr2[KeyCode.F18.ordinal()] = 102;
        } catch (NoSuchFieldError unused91) {
        }
        try {
            iArr2[KeyCode.F19.ordinal()] = 103;
        } catch (NoSuchFieldError unused92) {
        }
        try {
            iArr2[KeyCode.F2.ordinal()] = 86;
        } catch (NoSuchFieldError unused93) {
        }
        try {
            iArr2[KeyCode.F20.ordinal()] = 104;
        } catch (NoSuchFieldError unused94) {
        }
        try {
            iArr2[KeyCode.F21.ordinal()] = 105;
        } catch (NoSuchFieldError unused95) {
        }
        try {
            iArr2[KeyCode.F22.ordinal()] = 106;
        } catch (NoSuchFieldError unused96) {
        }
        try {
            iArr2[KeyCode.F23.ordinal()] = 107;
        } catch (NoSuchFieldError unused97) {
        }
        try {
            iArr2[KeyCode.F24.ordinal()] = 108;
        } catch (NoSuchFieldError unused98) {
        }
        try {
            iArr2[KeyCode.F3.ordinal()] = 87;
        } catch (NoSuchFieldError unused99) {
        }
        try {
            iArr2[KeyCode.F4.ordinal()] = 88;
        } catch (NoSuchFieldError unused100) {
        }
        try {
            iArr2[KeyCode.F5.ordinal()] = 89;
        } catch (NoSuchFieldError unused101) {
        }
        try {
            iArr2[KeyCode.F6.ordinal()] = 90;
        } catch (NoSuchFieldError unused102) {
        }
        try {
            iArr2[KeyCode.F7.ordinal()] = 91;
        } catch (NoSuchFieldError unused103) {
        }
        try {
            iArr2[KeyCode.F8.ordinal()] = 92;
        } catch (NoSuchFieldError unused104) {
        }
        try {
            iArr2[KeyCode.F9.ordinal()] = 93;
        } catch (NoSuchFieldError unused105) {
        }
        try {
            iArr2[KeyCode.FAST_FWD.ordinal()] = 214;
        } catch (NoSuchFieldError unused106) {
        }
        try {
            iArr2[KeyCode.FINAL.ordinal()] = 156;
        } catch (NoSuchFieldError unused107) {
        }
        try {
            iArr2[KeyCode.FIND.ordinal()] = 182;
        } catch (NoSuchFieldError unused108) {
        }
        try {
            iArr2[KeyCode.FULL_WIDTH.ordinal()] = 166;
        } catch (NoSuchFieldError unused109) {
        }
        try {
            iArr2[KeyCode.G.ordinal()] = 43;
        } catch (NoSuchFieldError unused110) {
        }
        try {
            iArr2[KeyCode.GAME_A.ordinal()] = 199;
        } catch (NoSuchFieldError unused111) {
        }
        try {
            iArr2[KeyCode.GAME_B.ordinal()] = 200;
        } catch (NoSuchFieldError unused112) {
        }
        try {
            iArr2[KeyCode.GAME_C.ordinal()] = 201;
        } catch (NoSuchFieldError unused113) {
        }
        try {
            iArr2[KeyCode.GAME_D.ordinal()] = 202;
        } catch (NoSuchFieldError unused114) {
        }
        try {
            iArr2[KeyCode.GREATER.ordinal()] = 139;
        } catch (NoSuchFieldError unused115) {
        }
        try {
            iArr2[KeyCode.H.ordinal()] = 44;
        } catch (NoSuchFieldError unused116) {
        }
        try {
            iArr2[KeyCode.HALF_WIDTH.ordinal()] = 167;
        } catch (NoSuchFieldError unused117) {
        }
        try {
            iArr2[KeyCode.HELP.ordinal()] = 111;
        } catch (NoSuchFieldError unused118) {
        }
        try {
            iArr2[KeyCode.HIRAGANA.ordinal()] = 165;
        } catch (NoSuchFieldError unused119) {
        }
        try {
            iArr2[KeyCode.HOME.ordinal()] = 16;
        } catch (NoSuchFieldError unused120) {
        }
        try {
            iArr2[KeyCode.I.ordinal()] = 45;
        } catch (NoSuchFieldError unused121) {
        }
        try {
            iArr2[KeyCode.INFO.ordinal()] = 206;
        } catch (NoSuchFieldError unused122) {
        }
        try {
            iArr2[KeyCode.INPUT_METHOD_ON_OFF.ordinal()] = 176;
        } catch (NoSuchFieldError unused123) {
        }
        try {
            iArr2[KeyCode.INSERT.ordinal()] = 110;
        } catch (NoSuchFieldError unused124) {
        }
        try {
            iArr2[KeyCode.INVERTED_EXCLAMATION_MARK.ordinal()] = 148;
        } catch (NoSuchFieldError unused125) {
        }
        try {
            iArr2[KeyCode.J.ordinal()] = 46;
        } catch (NoSuchFieldError unused126) {
        }
        try {
            iArr2[KeyCode.JAPANESE_HIRAGANA.ordinal()] = 173;
        } catch (NoSuchFieldError unused127) {
        }
        try {
            iArr2[KeyCode.JAPANESE_KATAKANA.ordinal()] = 172;
        } catch (NoSuchFieldError unused128) {
        }
        try {
            iArr2[KeyCode.JAPANESE_ROMAN.ordinal()] = 174;
        } catch (NoSuchFieldError unused129) {
        }
        try {
            iArr2[KeyCode.K.ordinal()] = 47;
        } catch (NoSuchFieldError unused130) {
        }
        try {
            iArr2[KeyCode.KANA.ordinal()] = 161;
        } catch (NoSuchFieldError unused131) {
        }
        try {
            iArr2[KeyCode.KANA_LOCK.ordinal()] = 175;
        } catch (NoSuchFieldError unused132) {
        }
        try {
            iArr2[KeyCode.KANJI.ordinal()] = 162;
        } catch (NoSuchFieldError unused133) {
        }
        try {
            iArr2[KeyCode.KATAKANA.ordinal()] = 164;
        } catch (NoSuchFieldError unused134) {
        }
        try {
            iArr2[KeyCode.KP_DOWN.ordinal()] = 116;
        } catch (NoSuchFieldError unused135) {
        }
        try {
            iArr2[KeyCode.KP_LEFT.ordinal()] = 117;
        } catch (NoSuchFieldError unused136) {
        }
        try {
            iArr2[KeyCode.KP_RIGHT.ordinal()] = 118;
        } catch (NoSuchFieldError unused137) {
        }
        try {
            iArr2[KeyCode.KP_UP.ordinal()] = 115;
        } catch (NoSuchFieldError unused138) {
        }
        try {
            iArr2[KeyCode.L.ordinal()] = 48;
        } catch (NoSuchFieldError unused139) {
        }
        try {
            iArr2[KeyCode.LEFT.ordinal()] = 17;
        } catch (NoSuchFieldError unused140) {
        }
        try {
            iArr2[KeyCode.LEFT_PARENTHESIS.ordinal()] = 149;
        } catch (NoSuchFieldError unused141) {
        }
        try {
            iArr2[KeyCode.LESS.ordinal()] = 138;
        } catch (NoSuchFieldError unused142) {
        }
        try {
            iArr2[KeyCode.M.ordinal()] = 49;
        } catch (NoSuchFieldError unused143) {
        }
        try {
            iArr2[KeyCode.META.ordinal()] = 112;
        } catch (NoSuchFieldError unused144) {
        }
        try {
            iArr2[KeyCode.MINUS.ordinal()] = 22;
        } catch (NoSuchFieldError unused145) {
        }
        try {
            iArr2[KeyCode.MODECHANGE.ordinal()] = 160;
        } catch (NoSuchFieldError unused146) {
        }
        try {
            iArr2[KeyCode.MULTIPLY.ordinal()] = 76;
        } catch (NoSuchFieldError unused147) {
        }
        try {
            iArr2[KeyCode.MUTE.ordinal()] = 222;
        } catch (NoSuchFieldError unused148) {
        }
        try {
            iArr2[KeyCode.N.ordinal()] = 50;
        } catch (NoSuchFieldError unused149) {
        }
        try {
            iArr2[KeyCode.NONCONVERT.ordinal()] = 158;
        } catch (NoSuchFieldError unused150) {
        }
        try {
            iArr2[KeyCode.NUMBER_SIGN.ordinal()] = 150;
        } catch (NoSuchFieldError unused151) {
        }
        try {
            iArr2[KeyCode.NUMPAD0.ordinal()] = 66;
        } catch (NoSuchFieldError unused152) {
        }
        try {
            iArr2[KeyCode.NUMPAD1.ordinal()] = 67;
        } catch (NoSuchFieldError unused153) {
        }
        try {
            iArr2[KeyCode.NUMPAD2.ordinal()] = 68;
        } catch (NoSuchFieldError unused154) {
        }
        try {
            iArr2[KeyCode.NUMPAD3.ordinal()] = 69;
        } catch (NoSuchFieldError unused155) {
        }
        try {
            iArr2[KeyCode.NUMPAD4.ordinal()] = 70;
        } catch (NoSuchFieldError unused156) {
        }
        try {
            iArr2[KeyCode.NUMPAD5.ordinal()] = 71;
        } catch (NoSuchFieldError unused157) {
        }
        try {
            iArr2[KeyCode.NUMPAD6.ordinal()] = 72;
        } catch (NoSuchFieldError unused158) {
        }
        try {
            iArr2[KeyCode.NUMPAD7.ordinal()] = 73;
        } catch (NoSuchFieldError unused159) {
        }
        try {
            iArr2[KeyCode.NUMPAD8.ordinal()] = 74;
        } catch (NoSuchFieldError unused160) {
        }
        try {
            iArr2[KeyCode.NUMPAD9.ordinal()] = 75;
        } catch (NoSuchFieldError unused161) {
        }
        try {
            iArr2[KeyCode.NUM_LOCK.ordinal()] = 83;
        } catch (NoSuchFieldError unused162) {
        }
        try {
            iArr2[KeyCode.O.ordinal()] = 51;
        } catch (NoSuchFieldError unused163) {
        }
        try {
            iArr2[KeyCode.OPEN_BRACKET.ordinal()] = 63;
        } catch (NoSuchFieldError unused164) {
        }
        try {
            iArr2[KeyCode.P.ordinal()] = 52;
        } catch (NoSuchFieldError unused165) {
        }
        try {
            iArr2[KeyCode.PAGE_DOWN.ordinal()] = 14;
        } catch (NoSuchFieldError unused166) {
        }
        try {
            iArr2[KeyCode.PAGE_UP.ordinal()] = 13;
        } catch (NoSuchFieldError unused167) {
        }
        try {
            iArr2[KeyCode.PASTE.ordinal()] = 179;
        } catch (NoSuchFieldError unused168) {
        }
        try {
            iArr2[KeyCode.PAUSE.ordinal()] = 9;
        } catch (NoSuchFieldError unused169) {
        }
        try {
            iArr2[KeyCode.PERIOD.ordinal()] = 23;
        } catch (NoSuchFieldError unused170) {
        }
        try {
            iArr2[KeyCode.PLAY.ordinal()] = 212;
        } catch (NoSuchFieldError unused171) {
        }
        try {
            iArr2[KeyCode.PLUS.ordinal()] = 151;
        } catch (NoSuchFieldError unused172) {
        }
        try {
            iArr2[KeyCode.POUND.ordinal()] = 204;
        } catch (NoSuchFieldError unused173) {
        }
        try {
            iArr2[KeyCode.POWER.ordinal()] = 205;
        } catch (NoSuchFieldError unused174) {
        }
        try {
            iArr2[KeyCode.PREVIOUS_CANDIDATE.ordinal()] = 170;
        } catch (NoSuchFieldError unused175) {
        }
        try {
            iArr2[KeyCode.PRINTSCREEN.ordinal()] = 109;
        } catch (NoSuchFieldError unused176) {
        }
        try {
            iArr2[KeyCode.PROPS.ordinal()] = 183;
        } catch (NoSuchFieldError unused177) {
        }
        try {
            iArr2[KeyCode.Q.ordinal()] = 53;
        } catch (NoSuchFieldError unused178) {
        }
        try {
            iArr2[KeyCode.QUOTE.ordinal()] = 114;
        } catch (NoSuchFieldError unused179) {
        }
        try {
            iArr2[KeyCode.QUOTEDBL.ordinal()] = 137;
        } catch (NoSuchFieldError unused180) {
        }
        try {
            iArr2[KeyCode.R.ordinal()] = 54;
        } catch (NoSuchFieldError unused181) {
        }
        try {
            iArr2[KeyCode.RECORD.ordinal()] = 213;
        } catch (NoSuchFieldError unused182) {
        }
        try {
            iArr2[KeyCode.REWIND.ordinal()] = 215;
        } catch (NoSuchFieldError unused183) {
        }
        try {
            iArr2[KeyCode.RIGHT.ordinal()] = 19;
        } catch (NoSuchFieldError unused184) {
        }
        try {
            iArr2[KeyCode.RIGHT_PARENTHESIS.ordinal()] = 152;
        } catch (NoSuchFieldError unused185) {
        }
        try {
            iArr2[KeyCode.ROMAN_CHARACTERS.ordinal()] = 168;
        } catch (NoSuchFieldError unused186) {
        }
        try {
            iArr2[KeyCode.S.ordinal()] = 55;
        } catch (NoSuchFieldError unused187) {
        }
        try {
            iArr2[KeyCode.SCROLL_LOCK.ordinal()] = 84;
        } catch (NoSuchFieldError unused188) {
        }
        try {
            iArr2[KeyCode.SEMICOLON.ordinal()] = 35;
        } catch (NoSuchFieldError unused189) {
        }
        try {
            iArr2[KeyCode.SEPARATOR.ordinal()] = 78;
        } catch (NoSuchFieldError unused190) {
        }
        try {
            iArr2[KeyCode.SHIFT.ordinal()] = 6;
        } catch (NoSuchFieldError unused191) {
        }
        try {
            iArr2[KeyCode.SHORTCUT.ordinal()] = 224;
        } catch (NoSuchFieldError unused192) {
        }
        try {
            iArr2[KeyCode.SLASH.ordinal()] = 24;
        } catch (NoSuchFieldError unused193) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_0.ordinal()] = 189;
        } catch (NoSuchFieldError unused194) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_1.ordinal()] = 190;
        } catch (NoSuchFieldError unused195) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_2.ordinal()] = 191;
        } catch (NoSuchFieldError unused196) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_3.ordinal()] = 192;
        } catch (NoSuchFieldError unused197) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_4.ordinal()] = 193;
        } catch (NoSuchFieldError unused198) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_5.ordinal()] = 194;
        } catch (NoSuchFieldError unused199) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_6.ordinal()] = 195;
        } catch (NoSuchFieldError unused200) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_7.ordinal()] = 196;
        } catch (NoSuchFieldError unused201) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_8.ordinal()] = 197;
        } catch (NoSuchFieldError unused202) {
        }
        try {
            iArr2[KeyCode.SOFTKEY_9.ordinal()] = 198;
        } catch (NoSuchFieldError unused203) {
        }
        try {
            iArr2[KeyCode.SPACE.ordinal()] = 12;
        } catch (NoSuchFieldError unused204) {
        }
        try {
            iArr2[KeyCode.STAR.ordinal()] = 203;
        } catch (NoSuchFieldError unused205) {
        }
        try {
            iArr2[KeyCode.STOP.ordinal()] = 184;
        } catch (NoSuchFieldError unused206) {
        }
        try {
            iArr2[KeyCode.SUBTRACT.ordinal()] = 79;
        } catch (NoSuchFieldError unused207) {
        }
        try {
            iArr2[KeyCode.T.ordinal()] = 56;
        } catch (NoSuchFieldError unused208) {
        }
        try {
            iArr2[KeyCode.TAB.ordinal()] = 3;
        } catch (NoSuchFieldError unused209) {
        }
        try {
            iArr2[KeyCode.TRACK_NEXT.ordinal()] = 217;
        } catch (NoSuchFieldError unused210) {
        }
        try {
            iArr2[KeyCode.TRACK_PREV.ordinal()] = 216;
        } catch (NoSuchFieldError unused211) {
        }
        try {
            iArr2[KeyCode.U.ordinal()] = 57;
        } catch (NoSuchFieldError unused212) {
        }
        try {
            iArr2[KeyCode.UNDEFINED.ordinal()] = 188;
        } catch (NoSuchFieldError unused213) {
        }
        try {
            iArr2[KeyCode.UNDERSCORE.ordinal()] = 153;
        } catch (NoSuchFieldError unused214) {
        }
        try {
            iArr2[KeyCode.UNDO.ordinal()] = 180;
        } catch (NoSuchFieldError unused215) {
        }
        try {
            iArr2[KeyCode.UP.ordinal()] = 18;
        } catch (NoSuchFieldError unused216) {
        }
        try {
            iArr2[KeyCode.V.ordinal()] = 58;
        } catch (NoSuchFieldError unused217) {
        }
        try {
            iArr2[KeyCode.VOLUME_DOWN.ordinal()] = 221;
        } catch (NoSuchFieldError unused218) {
        }
        try {
            iArr2[KeyCode.VOLUME_UP.ordinal()] = 220;
        } catch (NoSuchFieldError unused219) {
        }
        try {
            iArr2[KeyCode.W.ordinal()] = 59;
        } catch (NoSuchFieldError unused220) {
        }
        try {
            iArr2[KeyCode.WINDOWS.ordinal()] = 154;
        } catch (NoSuchFieldError unused221) {
        }
        try {
            iArr2[KeyCode.X.ordinal()] = 60;
        } catch (NoSuchFieldError unused222) {
        }
        try {
            iArr2[KeyCode.Y.ordinal()] = 61;
        } catch (NoSuchFieldError unused223) {
        }
        try {
            iArr2[KeyCode.Z.ordinal()] = 62;
        } catch (NoSuchFieldError unused224) {
        }
        $SWITCH_TABLE$javafx$scene$input$KeyCode = iArr2;
        return iArr2;
    }

    public InputHandler(FxTextEditor ed, VFlow f, SelectionController sel) {
        this.editor = ed;
        this.vflow = f;
        this.selector = sel;
        this.autoScrollTimer.setCycleCount(-1);
        this.vflow.addEventFilter(MouseEvent.MOUSE_CLICKED, ev -> {
            handleMouseClicked(ev);
        });
        this.vflow.addEventFilter(MouseEvent.MOUSE_PRESSED, ev2 -> {
            handleMousePressed(ev2);
        });
        this.vflow.addEventFilter(MouseEvent.MOUSE_RELEASED, ev3 -> {
            handleMouseReleased(ev3);
        });
        this.vflow.addEventFilter(MouseEvent.MOUSE_DRAGGED, ev4 -> {
            handleMouseDragged(ev4);
        });
        this.vflow.addEventFilter(ScrollEvent.ANY, ev5 -> {
            handleScrollWheel(ev5);
        });
        KeyMap.onKeyPressed(ed, KeyCode.A, 16, ed.actions.selectAll());
        KeyMap.onKeyPressed(ed, KeyCode.C, 16, ed.actions.copy());
        KeyMap.onKeyPressed(ed, KeyCode.BACK_SPACE, ed.actions.backspace());
        KeyMap.onKeyPressed(ed, KeyCode.DELETE, ed.actions.delete());
        KeyMap.onKeyPressed(ed, KeyCode.DOWN, ed.actions.moveDown());
        KeyMap.onKeyPressed(ed, KeyCode.END, ed.actions.moveEnd());
        KeyMap.onKeyPressed(ed, KeyCode.END, 16, ed.actions.moveDocumentEnd());
        KeyMap.onKeyPressed(ed, KeyCode.HOME, ed.actions.moveHome());
        KeyMap.onKeyPressed(ed, KeyCode.HOME, 16, ed.actions.moveDocumentStart());
        KeyMap.onKeyPressed(ed, KeyCode.LEFT, ed.actions.moveLeft());
        KeyMap.onKeyPressed(ed, KeyCode.PAGE_DOWN, ed.actions.pageDown());
        KeyMap.onKeyPressed(ed, KeyCode.PAGE_UP, ed.actions.pageUp());
        KeyMap.onKeyPressed(ed, KeyCode.RIGHT, ed.actions.moveRight());
        KeyMap.onKeyPressed(ed, KeyCode.UP, ed.actions.moveUp());
        ed.addEventFilter(KeyEvent.KEY_PRESSED, ev6 -> {
            handleKeyPressed(ev6);
        });
        ed.addEventFilter(KeyEvent.KEY_RELEASED, ev7 -> {
            handleKeyReleased(ev7);
        });
        ed.addEventFilter(KeyEvent.KEY_TYPED, ev8 -> {
            handleKeyTyped(ev8);
        });
    }

    protected void handleScrollWheel(ScrollEvent ev) {
        int step;
        EventType<ScrollEvent> t = ev.getEventType();
        if (t == ScrollEvent.SCROLL_STARTED) {
            this.inTrackpadScroll = true;
        } else if (t == ScrollEvent.SCROLL_FINISHED) {
            this.inTrackpadScroll = false;
        } else {
            if (this.inTrackpadScroll) {
                step = 3;
            } else if (ev.isShortcutDown()) {
                step = Integer.MAX_VALUE;
            } else {
                step = getScrollStepSize();
            }
            boolean up = ev.getDeltaY() >= 0.0d;
            this.vflow.scroll(step, up);
        }
    }

    protected int getScrollStepSize() {
        int step;
        double v = this.editor.getScrollWheelStepSize();
        if (v < 0.0d) {
            step = CKit.round((-v) * this.vflow.getScreenRowCount());
        } else {
            step = (int) v;
        }
        if (step < 1) {
            return 1;
        }
        return step;
    }

    protected Marker getMarker(MouseEvent ev) {
        double x = ev.getScreenX();
        double y = ev.getScreenY();
        return this.editor.getInsertPosition(x, y);
    }

    public void handleMouseClicked(MouseEvent ev) {
        this.editor.requestFocus();
        if (ev.getButton() != MouseButton.PRIMARY) {
            return;
        }
        int clicks = ev.getClickCount();
        switch (clicks) {
            case 2:
                handleDoubleClick(ev);
                return;
            case 3:
                handleTripleClick(ev);
                ev.consume();
                return;
            default:
                return;
        }
    }

    public void handleDoubleClick(MouseEvent ev) {
        Marker m;
        if (this.doubleClickHandler != null && (m = getMarker(ev)) != null) {
            this.doubleClickHandler.accept(this.editor, m);
        }
    }

    public void handleTripleClick(MouseEvent ev) {
        Marker m;
        if (this.tripleClickHandler != null && (m = getMarker(ev)) != null) {
            this.tripleClickHandler.accept(this.editor, m);
        }
    }

    public void handleMousePressed(MouseEvent ev) {
        ev.consume();
        this.editor.requestFocus();
        Marker m = getMarker(ev);
        if (FX.isPopupTrigger(ev)) {
            if (!this.selector.isSelected(m)) {
                this.selector.setAnchor(m);
                this.selector.setSelection(m);
                return;
            }
            return;
        }
        this.vflow.setSuppressBlink(true);
        if (ev.isShiftDown()) {
            this.selector.clearAndExtendLastSegment(m);
        } else if (ev.isShortcutDown()) {
            this.selector.setAnchor(m);
            this.selector.setSelection(m);
        } else {
            this.editor.clearSelection();
            this.selector.addSelectionSegment(m, m);
            this.selector.setAnchor(m);
        }
    }

    public void handleMouseDragged(MouseEvent ev) {
        if (!FX.isLeftButton(ev)) {
            return;
        }
        double y = ev.getY();
        if (y < 0.0d) {
            autoScroll(y);
        } else if (y > this.vflow.getHeight()) {
            autoScroll(y - this.vflow.getHeight());
        } else {
            stopAutoScroll();
            Marker m = getMarker(ev);
            this.selector.extendLastSegment(m);
        }
    }

    public void handleMouseReleased(MouseEvent ev) {
        stopAutoScroll();
        this.selector.commitSelection();
        this.vflow.setSuppressBlink(false);
        this.vflow.scrollCaretToView();
        this.vflow.setPhantomColumnFromCursor();
        this.vflow.scrollCaretToView();
    }

    protected void autoScroll(double delta) {
        this.autoScrollUp = delta < 0.0d;
        this.fastAutoScroll = Math.abs(delta) > this.fastScrollThreshold;
        this.autoScrollTimer.play();
    }

    protected void stopAutoScroll() {
        this.autoScrollTimer.stop();
    }

    protected void autoScroll() {
        Point2D p;
        double delta = this.fastAutoScroll ? this.autoScrollStepFast : this.autoScrollStepSlow;
        if (this.autoScrollUp) {
            delta = -delta;
        }
        this.vflow.blockScroll(delta);
        if (this.autoScrollUp) {
            p = this.vflow.localToScreen(0.0d, 0.0d);
        } else {
            p = this.vflow.localToScreen(0.0d, this.vflow.getHeight());
        }
        Marker pos = this.editor.getInsertPosition(p.getX(), p.getY());
        this.selector.extendLastSegment(pos);
    }

    public void handleKeyPressed(KeyEvent ev) {
        if (ev.isConsumed()) {
            return;
        }
        switch ($SWITCH_TABLE$javafx$scene$input$KeyCode()[ev.getCode().ordinal()]) {
            case 1:
            case 3:
                handleKeyTyped(ev.getCode(), ev.isControlDown(), ev.isShiftDown(), ev.isShortcutDown());
                ev.consume();
                return;
            case 2:
            default:
                return;
        }
    }

    public void handleKeyReleased(KeyEvent ev) {
        if (ev.isConsumed()) {
        }
    }

    protected void handleKeyTyped(KeyCode code, boolean ctrl, boolean shift, boolean shortcut) {
        EditorSelection sel;
        Object typed;
        FxTextEditorModel m = this.editor.getModel();
        if (m.isEditable() && (sel = this.editor.getSelection()) != null) {
            this.vflow.setSuppressBlink(true);
            try {
                switch ($SWITCH_TABLE$javafx$scene$input$KeyCode()[code.ordinal()]) {
                    case 1:
                        typed = new String[]{ButtonBar.BUTTON_ORDER_NONE, ButtonBar.BUTTON_ORDER_NONE};
                        break;
                    case 2:
                    default:
                        throw new Error("?" + code);
                    case 3:
                        typed = "\n";
                        break;
                }
                try {
                    Edit ed = Edit.create(sel.getSegment(), typed);
                    m.edit(ed);
                    updateSelection(ed);
                } catch (Exception e) {
                    log.error((Throwable) e);
                }
            } finally {
                this.vflow.setSuppressBlink(false);
            }
        }
    }

    public void handleKeyTyped(KeyEvent ev) {
        FxTextEditorModel m;
        EditorSelection sel;
        if (!ev.isConsumed() && (m = this.editor.getModel()) != null && m.isEditable() && (sel = this.editor.getSelection()) != null) {
            this.vflow.setSuppressBlink(true);
            try {
                String ch = ev.getCharacter();
                if (isTypedCharacter(ch)) {
                    try {
                        Edit ed = Edit.create(sel.getSegment(), ch);
                        m.edit(ed);
                        updateSelection(ed);
                        ev.consume();
                    } catch (Exception e) {
                        log.error((Throwable) e);
                    }
                }
            } finally {
                this.vflow.setSuppressBlink(false);
            }
        }
    }

    protected void updateSelection(Edit ed) {
    }

    protected boolean isTypedCharacter(String ch) {
        if (KeyEvent.CHAR_UNDEFINED.equals(ch)) {
            return false;
        }
        int len = ch.length();
        switch (len) {
            case 0:
                return false;
            case 1:
                char c = ch.charAt(0);
                if (c < ' ') {
                    switch (c) {
                        case '\t':
                            return true;
                        default:
                            return false;
                    }
                }
                switch (c) {
                    case 127:
                        return false;
                    default:
                        return true;
                }
            default:
                return true;
        }
    }

    public void setDoubleClickHandler(BiConsumer<FxTextEditor, Marker> h) {
        this.doubleClickHandler = h;
    }

    public BiConsumer<FxTextEditor, Marker> getDoubleClickHandler() {
        return this.doubleClickHandler;
    }

    public void setTripleClickHandler(BiConsumer<FxTextEditor, Marker> h) {
        this.tripleClickHandler = h;
    }

    public BiConsumer<FxTextEditor, Marker> getTripleClickHandler() {
        return this.tripleClickHandler;
    }
}
