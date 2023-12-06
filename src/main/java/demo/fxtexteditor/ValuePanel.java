package demo.fxtexteditor;

import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.FxTextEditorModel;
import goryachev.fxtexteditor.Marker;
import goryachev.fxtexteditor.SelectionSegment;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/ValuePanel.class */
public class ValuePanel extends FlowPane {
    public static final int UBYTE_MAX_VALUE = 255;
    public static final int SWORD_MIN_VALUE = -32768;
    public static final int SWORD_MAX_VALUE = 32767;
    public static final int UWORD_MAX_VALUE = 65535;
    public static final long UINT_MAX_VALUE = 4294967295L;
    public static final String VALUE_OUT_OF_RANGE = "Value is out of range";
    private int dataPosition;
    private byte[] valuesCache = new byte[CACHE_SIZE];
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(this.valuesCache);
    private final ValuesUpdater valuesUpdater = new ValuesUpdater();
    private RadioButton bigEndianRadioButton;
    private CheckBox binaryCheckBox0;
    private CheckBox binaryCheckBox1;
    private CheckBox binaryCheckBox2;
    private CheckBox binaryCheckBox3;
    private CheckBox binaryCheckBox4;
    private CheckBox binaryCheckBox5;
    private CheckBox binaryCheckBox6;
    private CheckBox binaryCheckBox7;
    private Label binaryLabel;
    private Label byteLabel;
    private TextField byteTextField;
    private Label characterLabel;
    private TextField characterTextField;
    private Label doubleLabel;
    private TextField doubleTextField;
    private ToggleGroup endianButtonGroup;
    private Label floatLabel;
    private TextField floatTextField;
    private Label intLabel;
    private TextField intTextField;
    private ToggleGroup integerSignButtonGroup;
    private Separator jSeparator1;
    private RadioButton littleEndianRadioButton;
    private Label longLabel;
    private TextField longTextField;
    private RadioButton signedRadioButton;
    private Label stringLabel;
    private TextField stringTextField;
    private RadioButton unsignedRadioButton;
    private Label wordLabel;
    private TextField wordTextField;
    private FxTextEditor ed;
    public static final BigInteger ULONG_MAX_VALUE = new BigInteger("4294967295");
    public static final BigInteger BIG_INTEGER_BYTE_MASK = BigInteger.valueOf(255);
    public static int CACHE_SIZE = 16;

    public ValuePanel(FxTextEditor ed) {
        initComponents();
        this.ed = ed;
        ed.selectionProperty().addListener(e -> {
            updateValues();
        });
    }

    private void initComponents() {
        this.endianButtonGroup = new ToggleGroup();
        this.integerSignButtonGroup = new ToggleGroup();
        this.binaryLabel = new Label();
        this.binaryCheckBox0 = new CheckBox();
        this.binaryCheckBox1 = new CheckBox();
        this.binaryCheckBox2 = new CheckBox();
        this.binaryCheckBox3 = new CheckBox();
        this.binaryCheckBox4 = new CheckBox();
        this.binaryCheckBox5 = new CheckBox();
        this.binaryCheckBox6 = new CheckBox();
        this.binaryCheckBox7 = new CheckBox();
        this.byteLabel = new Label();
        this.byteTextField = new TextField();
        this.wordLabel = new Label();
        this.wordTextField = new TextField();
        this.intLabel = new Label();
        this.intTextField = new TextField();
        this.longLabel = new Label();
        this.longTextField = new TextField();
        this.floatLabel = new Label();
        this.floatTextField = new TextField();
        this.doubleLabel = new Label();
        this.doubleTextField = new TextField();
        this.characterLabel = new Label();
        this.characterTextField = new TextField();
        this.stringLabel = new Label();
        this.stringTextField = new TextField();
        this.jSeparator1 = new Separator();
        this.bigEndianRadioButton = new RadioButton();
        this.littleEndianRadioButton = new RadioButton();
        this.signedRadioButton = new RadioButton();
        this.unsignedRadioButton = new RadioButton();
        this.binaryLabel.setText("Binary ");
        this.binaryCheckBox0.setOnAction(e -> {
            binaryCheckBox0ActionPerformed();
        });
        this.binaryCheckBox1.setOnAction(e2 -> {
            binaryCheckBox1ActionPerformed();
        });
        this.binaryCheckBox2.setOnAction(e3 -> {
            binaryCheckBox2ActionPerformed();
        });
        this.binaryCheckBox3.setOnAction(e4 -> {
            binaryCheckBox3ActionPerformed();
        });
        this.binaryCheckBox4.setOnAction(e5 -> {
            binaryCheckBox4ActionPerformed();
        });
        this.binaryCheckBox5.setOnAction(e6 -> {
            binaryCheckBox5ActionPerformed();
        });
        this.binaryCheckBox6.setOnAction(e7 -> {
            binaryCheckBox6ActionPerformed();
        });
        this.binaryCheckBox7.setOnAction(e8 -> {
            binaryCheckBox7ActionPerformed();
        });
        this.byteLabel.setText("Byte ");
        this.byteTextField.setEditable(false);
        this.longTextField.setEditable(false);
        this.floatTextField.setEditable(false);
        this.doubleTextField.setEditable(false);
        this.characterTextField.setEditable(false);
        this.stringTextField.setEditable(false);
        this.wordLabel.setText("Word ");
        this.intLabel.setText("Integer ");
        this.longLabel.setText("Long ");
        this.longLabel.setContentDisplay(ContentDisplay.CENTER);
        this.floatLabel.setText("Float  ");
        this.doubleLabel.setText("Double ");
        this.characterLabel.setText("Char  ");
        this.stringLabel.setText("String ");
        this.jSeparator1.setOrientation(Orientation.VERTICAL);
        this.bigEndianRadioButton.setToggleGroup(this.endianButtonGroup);
        this.bigEndianRadioButton.setSelected(true);
        this.bigEndianRadioButton.setText("Big Endian");
        this.bigEndianRadioButton.setTooltip(new Tooltip("Big Endian"));
        this.littleEndianRadioButton.setToggleGroup(this.endianButtonGroup);
        this.littleEndianRadioButton.setText("Little Endian");
        this.littleEndianRadioButton.setTooltip(new Tooltip("Little Endian"));
        this.signedRadioButton.setToggleGroup(this.integerSignButtonGroup);
        this.signedRadioButton.setText("Signed");
        this.signedRadioButton.setTooltip(new Tooltip("Signed Integers"));
        this.unsignedRadioButton.setToggleGroup(this.integerSignButtonGroup);
        this.unsignedRadioButton.setText("Unsigned");
        this.unsignedRadioButton.setSelected(true);
        this.unsignedRadioButton.setTooltip(new Tooltip("Unsigned Integers"));
        setPadding(new Insets(10.0d, 10.0d, 10.0d, 10.0d));
        setVgap(10.0d);
        setHgap(10.0d);
        HBox firstrow = new HBox();
        firstrow.setSpacing(10.0d);
        firstrow.getChildren().addAll(this.bigEndianRadioButton, this.littleEndianRadioButton, this.signedRadioButton, this.unsignedRadioButton);
        HBox secondrow = new HBox();
        secondrow.setSpacing(10.0d);
        secondrow.getChildren().addAll(this.binaryLabel, this.binaryCheckBox0, this.binaryCheckBox1, this.binaryCheckBox2, this.binaryCheckBox3, this.binaryCheckBox4, this.binaryCheckBox5, this.binaryCheckBox6, this.binaryCheckBox7);
        HBox vierrow = new HBox();
        vierrow.setSpacing(10.0d);
        vierrow.getChildren().addAll(this.byteLabel, this.byteTextField);
        vierrow.getChildren().addAll(this.wordLabel, this.wordTextField);
        vierrow.getChildren().addAll(this.intLabel, this.intTextField);
        HBox thirdrow = new HBox();
        thirdrow.setSpacing(10.0d);
        thirdrow.getChildren().addAll(this.longLabel, this.longTextField);
        thirdrow.getChildren().addAll(this.floatLabel, this.floatTextField);
        thirdrow.getChildren().addAll(this.doubleLabel, this.doubleTextField);
        getChildren().addAll(firstrow, secondrow, vierrow, thirdrow);
    }

    public void updateValues() {
        String value;
        SelectionSegment seg = this.ed.getSelectedSegment();
        if (seg == null) {
            return;
        }
        Marker m = seg.getCaret();
        int posinline = m.getCharIndex();
        int line = seg.getCaretLine();
        FxTextEditorModel model = this.ed.getModel();
        if (model == null || (value = model.getPlainText(line)) == null) {
            return;
        }
        String value2 = value.substring(0, 32);
        if (posinline < 32) {
            String bf = value2.substring(posinline, posinline + (posinline < 24 ? 8 : 32 - posinline));
            int availableData = 32 - posinline;
            if (bf.length() % 2 == 1) {
                bf = bf.concat("0");
            }
            byte[] values = hexStringToByteArray(bf);
            int cc = 0;
            this.valuesCache = new byte[CACHE_SIZE];
            for (byte b : values) {
                this.valuesCache[cc] = b;
                cc++;
            }
            if (availableData < CACHE_SIZE) {
                Arrays.fill(this.valuesCache, availableData, CACHE_SIZE, (byte) 0);
            }
        }
        ByteBuffer.wrap(this.valuesCache);
        this.valuesUpdater.schedule();
    }

    private boolean isSigned() {
        return this.signedRadioButton.isSelected();
    }

    private void binaryCheckBox0ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 128) > 0) != this.binaryCheckBox0.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 128);
            modifyValues(1);
        }
    }

    private void binaryCheckBox1ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 64) > 0) != this.binaryCheckBox1.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 64);
            modifyValues(1);
        }
    }

    private void binaryCheckBox2ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 32) > 0) != this.binaryCheckBox2.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 32);
            modifyValues(1);
        }
    }

    private void binaryCheckBox3ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 16) > 0) != this.binaryCheckBox3.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 16);
            modifyValues(1);
        }
    }

    private void binaryCheckBox4ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 8) > 0) != this.binaryCheckBox4.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 8);
            modifyValues(1);
        }
    }

    private void binaryCheckBox5ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 4) > 0) != this.binaryCheckBox5.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 4);
            modifyValues(1);
        }
    }

    private void binaryCheckBox6ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 2) > 0) != this.binaryCheckBox6.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 2);
            modifyValues(1);
        }
    }

    private void binaryCheckBox7ActionPerformed() {
        if (this.valuesUpdater.isUpdateInProgress()) {
            return;
        }
        if (((this.valuesCache[0] & 1) > 0) != this.binaryCheckBox7.isSelected()) {
            this.valuesCache[0] = (byte) (this.valuesCache[0] ^ 1);
            modifyValues(1);
        }
    }

    private void modifyValues(int bytesCount) {
    }

    private ByteOrder getByteOrder() {
        return this.littleEndianRadioButton.isSelected() ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:demo/fxtexteditor/ValuePanel$ValuesUpdater.class */
    public class ValuesUpdater {
        private boolean updateInProgress = false;
        private boolean updateTerminated = false;
        private boolean scheduleUpdate = false;
        private boolean clearFields = true;
        boolean signed;
        ByteOrder byteOrder;
        byte[] values;
        private  volatile /* synthetic */ int[] $SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField;

        private ValuesUpdater() {
        }

         /* synthetic */ int[] $SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField() {
            int[] iArr = $SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField;
            if (iArr != null) {
                return iArr;
            }
            int[] iArr2 = new int[ValuesPanelField.valuesCustom().length];
            try {
                iArr2[ValuesPanelField.BINARY0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr2[ValuesPanelField.BINARY1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[ValuesPanelField.BINARY2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ValuesPanelField.BINARY3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ValuesPanelField.BINARY4.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ValuesPanelField.BINARY5.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[ValuesPanelField.BINARY6.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[ValuesPanelField.BINARY7.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ValuesPanelField.BYTE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[ValuesPanelField.CHARACTER.ordinal()] = 15;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[ValuesPanelField.DOUBLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[ValuesPanelField.FLOAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[ValuesPanelField.INTEGER.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[ValuesPanelField.LONG.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[ValuesPanelField.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[ValuesPanelField.WORD.ordinal()] = 10;
            } catch (NoSuchFieldError unused16) {
            }
            $SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField = iArr2;
            return iArr2;
        }

        public boolean isUpdateInProgress() {
            return this.updateInProgress;
        }

        private synchronized void schedule() {
            if (this.updateInProgress) {
                this.updateTerminated = true;
            }
            if (!this.scheduleUpdate) {
                this.scheduleUpdate = true;
                scheduleNextStep(ValuesPanelField.valuesCustom()[0]);
            }
        }

        private void scheduleNextStep(final ValuesPanelField valuesPanelField) {
            Platform.runLater(new Runnable() { // from class: demo.fxtexteditor.ValuePanel.ValuesUpdater.1
                @Override // java.lang.Runnable
                public void run() {
                    ValuesUpdater.this.updateValue(valuesPanelField);
                }
            });
        }

        private void updateValue(final ValuesPanelField valuesPanelField) {
            if (valuesPanelField.ordinal() == 0) {
                this.clearFields = ((long) ValuePanel.this.dataPosition) >= 16;
                this.byteOrder = ValuePanel.this.littleEndianRadioButton.isSelected() ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
                this.byteOrder = ValuePanel.this.getByteOrder();
                this.signed = ValuePanel.this.isSigned();
                this.values = ValuePanel.this.valuesCache;
                if (this.clearFields) {
                    this.values[0] = 0;
                }
                updateStarted();
            }
            if (this.updateTerminated) {
                stopUpdate();
                return;
            }
            if (this.clearFields) {
                clearField(valuesPanelField);
            } else {
                updateField(valuesPanelField);
            }
            final ValuesPanelField[] panelFields = ValuesPanelField.valuesCustom();
            ValuesPanelField lastValue = panelFields[panelFields.length - 1];
            if (valuesPanelField == lastValue) {
                stopUpdate();
            } else {
                Platform.runLater(new Runnable() { // from class: demo.fxtexteditor.ValuePanel.ValuesUpdater.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ValuesPanelField nextValue = panelFields[valuesPanelField.ordinal() + 1];
                        ValuesUpdater.this.updateValue(nextValue);
                    }
                });
            }
        }

        private synchronized void updateStarted() {
            this.updateInProgress = true;
            this.scheduleUpdate = false;
        }

        private synchronized void stopUpdate() {
            this.updateInProgress = false;
            this.updateTerminated = false;
        }

        private void updateField(ValuesPanelField valuesPanelField) {
            switch ($SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField()[valuesPanelField.ordinal()]) {
                case 1:
                    ValuePanel.this.binaryCheckBox0.setSelected((this.values[0] & 128) > 0);
                    return;
                case 2:
                    ValuePanel.this.binaryCheckBox1.setSelected((this.values[0] & 64) > 0);
                    return;
                case 3:
                    ValuePanel.this.binaryCheckBox2.setSelected((this.values[0] & 32) > 0);
                    return;
                case 4:
                    ValuePanel.this.binaryCheckBox3.setSelected((this.values[0] & 16) > 0);
                    return;
                case 5:
                    ValuePanel.this.binaryCheckBox4.setSelected((this.values[0] & 8) > 0);
                    return;
                case 6:
                    ValuePanel.this.binaryCheckBox5.setSelected((this.values[0] & 4) > 0);
                    return;
                case 7:
                    ValuePanel.this.binaryCheckBox6.setSelected((this.values[0] & 2) > 0);
                    return;
                case 8:
                    ValuePanel.this.binaryCheckBox7.setSelected((this.values[0] & 1) > 0);
                    return;
                case 9:
                    ValuePanel.this.byteTextField.setText(String.valueOf(this.signed ? this.values[0] : this.values[0] & 255));
                    return;
                case 10:
                    int wordValue = this.signed ? this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (this.values[0] & 255) | (this.values[1] << 8) : (this.values[1] & 255) | (this.values[0] << 8) : this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (this.values[0] & 255) | ((this.values[1] & 255) << 8) : (this.values[1] & 255) | ((this.values[0] & 255) << 8);
                    ValuePanel.this.wordTextField.setText(String.valueOf(wordValue));
                    return;
                case 11:
                    long intValue = this.signed ? this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (this.values[0] & 255) | ((this.values[1] & 255) << 8) | ((this.values[2] & 255) << 16) | (this.values[3] << 24) : (this.values[3] & 255) | ((this.values[2] & 255) << 8) | ((this.values[1] & 255) << 16) | (this.values[0] << 24) : this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (this.values[0] & 255) | ((this.values[1] & 255) << 8) | ((this.values[2] & 255) << 16) | ((this.values[3] & 255) << 24) : (this.values[3] & 255) | ((this.values[2] & 255) << 8) | ((this.values[1] & 255) << 16) | ((this.values[0] & 255) << 24);
                    ValuePanel.this.intTextField.setText(String.valueOf(intValue));
                    return;
                case 12:
                    if (this.signed) {
                        ValuePanel.this.byteBuffer.rewind();
                        if (ValuePanel.this.byteBuffer.order() != this.byteOrder) {
                            ValuePanel.this.byteBuffer.order(this.byteOrder);
                        }
                        ValuePanel.this.longTextField.setText(String.valueOf(ValuePanel.this.byteBuffer.getLong()));
                        return;
                    }
                    long longValue = this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (this.values[0] & 255) | ((this.values[1] & 255) << 8) | ((this.values[2] & 255) << 16) | ((this.values[3] & 255) << 24) | ((this.values[4] & 255) << 32) | ((this.values[5] & 255) << 40) | ((this.values[6] & 255) << 48) : (this.values[7] & 255) | ((this.values[6] & 255) << 8) | ((this.values[5] & 255) << 16) | ((this.values[4] & 255) << 24) | ((this.values[3] & 255) << 32) | ((this.values[2] & 255) << 40) | ((this.values[1] & 255) << 48);
                    BigInteger bigInt1 = BigInteger.valueOf(this.values[this.byteOrder == ByteOrder.LITTLE_ENDIAN ? (char) 7 : (char) 0] & 255);
                    BigInteger bigInt2 = bigInt1.shiftLeft(56);
                    BigInteger bigInt3 = bigInt2.add(BigInteger.valueOf(longValue));
                    ValuePanel.this.longTextField.setText(bigInt3.toString());
                    return;
                case 13:
                    ByteBuffer buffer = ByteBuffer.wrap(this.values).rewind();
                    if (buffer.order() != this.byteOrder) {
                        buffer.order(this.byteOrder);
                    }
                    ValuePanel.this.floatTextField.setText(String.valueOf(buffer.getFloat()));
                    return;
                case 14:
                    ValuePanel.this.byteBuffer.position(0);
                    ByteBuffer buffer2 = ByteBuffer.wrap(this.values).rewind();
                    if (buffer2.order() != this.byteOrder) {
                        buffer2.order(this.byteOrder);
                    }
                    ValuePanel.this.doubleTextField.setText(String.valueOf(buffer2.getDouble()));
                    return;
                case 15:
                    if (ButtonBar.BUTTON_ORDER_NONE.length() > 0) {
                        ValuePanel.this.characterTextField.setText(ButtonBar.BUTTON_ORDER_NONE.substring(0, 1));
                        return;
                    } else {
                        ValuePanel.this.characterTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                        return;
                    }
                default:
                    return;
            }
        }

        private void clearField(ValuesPanelField valuesPanelField) {
            switch ($SWITCH_TABLE$demo$fxtexteditor$ValuesPanelField()[valuesPanelField.ordinal()]) {
                case 1:
                    ValuePanel.this.binaryCheckBox0.setSelected(false);
                    return;
                case 2:
                    ValuePanel.this.binaryCheckBox1.setSelected(false);
                    return;
                case 3:
                    ValuePanel.this.binaryCheckBox2.setSelected(false);
                    return;
                case 4:
                    ValuePanel.this.binaryCheckBox3.setSelected(false);
                    return;
                case 5:
                    ValuePanel.this.binaryCheckBox4.setSelected(false);
                    return;
                case 6:
                    ValuePanel.this.binaryCheckBox5.setSelected(false);
                    return;
                case 7:
                    ValuePanel.this.binaryCheckBox6.setSelected(false);
                    return;
                case 8:
                    ValuePanel.this.binaryCheckBox7.setSelected(false);
                    return;
                case 9:
                    ValuePanel.this.byteTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 10:
                    ValuePanel.this.wordTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 11:
                    ValuePanel.this.intTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 12:
                    ValuePanel.this.longTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 13:
                    ValuePanel.this.floatTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 14:
                    ValuePanel.this.doubleTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 15:
                    ValuePanel.this.characterTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                case 16:
                    ValuePanel.this.stringTextField.setText(ButtonBar.BUTTON_ORDER_NONE);
                    return;
                default:
                    return;
            }
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
