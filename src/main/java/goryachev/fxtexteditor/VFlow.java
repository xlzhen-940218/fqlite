package goryachev.fxtexteditor;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fx.CPane;
import goryachev.fx.FX;
import goryachev.fx.FxBoolean;
import goryachev.fx.FxBooleanBinding;
import goryachev.fx.TextCellMetrics;
import goryachev.fx.TextCellStyle;
import goryachev.fxtexteditor.internal.FlowLine;
import goryachev.fxtexteditor.internal.FlowLineCache;
import goryachev.fxtexteditor.internal.ScreenBuffer;
import goryachev.fxtexteditor.internal.ScreenRow;
import goryachev.fxtexteditor.internal.ScrollAssist;
import goryachev.fxtexteditor.internal.SelectionHelper;
import goryachev.fxtexteditor.internal.TextCell;
import goryachev.fxtexteditor.internal.VerticalScrollHelper;
import goryachev.fxtexteditor.internal.WrapInfo;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/VFlow.class */
public class VFlow extends CPane {
    protected static final Log log = Log.get("VFlow");
    protected static final int LINE_CACHE_SIZE = 1024;
    protected static final double LINE_NUMBERS_BG_OPACITY = 0.1d;
    protected static final double CARET_LINE_OPACITY = 0.3d;
    protected static final double LINE_COLOR_OPACITY = 0.9d;
    protected static final double SELECTION_BACKGROUND_OPACITY = 0.9d;
    protected static final double CELL_BACKGROUND_OPACITY = 0.8d;
    protected static final int HORIZONTAL_SAFETY = 8;
    protected static final int VERTICAL_SAFETY = 1;
    protected final FxTextEditor editor;
    protected final BooleanExpression paintCaret;
    private Timeline cursorAnimation;
    private Font font;
    private Font boldFont;
    private Font boldItalicFont;
    private Font italicFont;
    private TextCellMetrics metrics;
    private Canvas canvas;
    private GraphicsContext gx;
    private int screenColumnCount;
    private int screenRowCount;
    private int lineNumbersCellCount;
    private int lineNumbersBarWidth;
    private int[] lineNumbersColumnWidths;
    private int topLine;
    private int topGlyphIndex;
    private int topColumn;
    private boolean screenBufferValid;
    private boolean repaintRequested;
    protected final FlowLineCache cache;
    private static volatile /* synthetic */ int[] $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType;
    protected final FxBoolean caretEnabledProperty = new FxBoolean(true);
    protected final FxBoolean suppressBlink = new FxBoolean(false);
    protected final ScreenBuffer screenBuffer = new ScreenBuffer(this);
    private boolean cursorEnabled = true;
    private boolean cursorOn = true;
    private int minLineNumberCellCount = 3;
    private int lineNumbersGap = 5;
    private Color textColor = Color.BLACK;
    private Color caretColor = Color.BLACK;
    private int phantomColumn = -1;
    protected boolean handleScrollEvents = true;
    protected final Text proto = new Text();

    static /* synthetic */ int[] $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType() {
        int[] iArr = $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[GlyphType.valuesCustom().length];
        try {
            iArr2[GlyphType.EOF.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[GlyphType.EOL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[GlyphType.REG.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[GlyphType.TAB.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $SWITCH_TABLE$goryachev$fxtexteditor$GlyphType = iArr2;
        return iArr2;
    }

    public VFlow(FxTextEditor ed) {
        this.editor = ed;
        this.proto.setManaged(false);
        backgroundProperty().bind(Bindings.createObjectBinding(() -> {
            Color c = ed.getBackgroundColor();
            return new Background(new BackgroundFill(c, null, null));
        }, ed.backgroundColorProperty()));
        this.cache = new FlowLineCache(ed, 1024);
        setMinWidth(0.0d);
        setMinHeight(0.0d);
        setFocusTraversable(false);
        FX.onChange(this::repaint, ed.backgroundColorProperty());
        FX.onChange(this::handleSizeChange, widthProperty(), heightProperty());
        FX.onChange(this::updateModel, ed.modelProperty());
        FX.onChange(this::updateLineNumbers, ed.showLineNumbersProperty, ed.lineNumberFormatterProperty, ed.modelProperty);
        FX.onChange(this::updateFont, true, (ObservableValue<?>[]) new ObservableValue[]{ed.fontProperty});
        FX.onChange(this::handleWrapChange, ed.wrapLinesProperty);
        ed.getVerticalScrollBar().valueProperty().addListener((observableValue, p, c) -> {
            handleVerticalScroll(c.doubleValue());
        });
        ed.getHorizontalScrollBar().valueProperty().addListener((observableValue2, p2, c2 )-> {
            handleHorizontalScroll(c2.doubleValue());
        });
        ed.selector.selectionSegmentProperty().addListener((observableValue3, p3, c3) -> {
            handleSelectionSegmentUpdate(p3, c3);
        });
        this.paintCaret = new FxBooleanBinding(this.caretEnabledProperty, this.editor.displayCaretProperty, this.editor.focusedProperty(), this.editor.disabledProperty(), this.suppressBlink) { // from class: goryachev.fxtexteditor.VFlow.1
            @Override // goryachev.fx.FxBooleanBinding, javafx.beans.binding.BooleanBinding
            protected boolean computeValue() {
                return (VFlow.this.caretEnabledProperty.get() || VFlow.this.suppressBlink.get()) && VFlow.this.editor.isDisplayCaret() && VFlow.this.editor.isFocused() && !VFlow.this.editor.isDisabled();
            }
        };
        this.paintCaret.addListener((observableValue4, p4, c4) -> {
            refreshCursor();
        });
        FX.parentWindowProperty(this).addListener((observableValue5, p5, c5) -> {
            updateCursorAnimation(c5);
        });
    }

    public FxTextEditor getEditor() {
        return this.editor;
    }

    public SelectionSegment getSelectionSegment() {
        return this.editor.getSelection().getSegment();
    }

    public int getTopLine() {
        return this.topLine;
    }

    public int getTopGlyphIndex() {
        return this.topGlyphIndex;
    }

    public int getTopCellIndex() {
        return this.topColumn;
    }

    public void shiftViewPort(int delta) {
        int line = getTopLine();
        int gix = getTopGlyphIndex();
        WrapInfo wr = getWrapInfo(line);
        int wrapRow = wr.getWrapRowForGlyphIndex(gix);
        WrapPos wp = ensureLastPageFullView(advance(line, wrapRow, delta));
        int newLine = wp.getLine();
        int newGlyphIndex = wp.getStartGlyphIndex();
        setOrigin(newLine, newGlyphIndex);
    }

    protected WrapPos ensureLastPageFullView(WrapPos wp) {
        int newLine = wp.getLine();
        int newGlyphIndex = wp.getStartGlyphIndex();
        int lineCount = getModelLineCount();
        if (newLine > lineCount - this.screenRowCount) {
            WrapPos wp2 = advance(lineCount, 0, -this.screenRowCount);
            if (newLine > wp2.getLine()) {
                return wp2;
            }
            if (newLine == wp2.getLine() && newGlyphIndex > wp2.getStartGlyphIndex()) {
                return wp2;
            }
        }
        return wp;
    }

    public void setOrigin(int topLine, int glyphIndex) {
        if (topLine == this.topLine && glyphIndex == this.topGlyphIndex) {
            return;
        }
        log.debug("%d %s", Integer.valueOf(topLine), Integer.valueOf(glyphIndex));
        this.topLine = topLine;
        this.topGlyphIndex = glyphIndex;
        updateLineNumbers();
        invalidate();
        if (!isWrapLines()) {
            updateHorizontalScrollBarPosition();
        }
        updateVerticalScrollBarPosition();
    }

    public void setTopCellIndex(int ix) {
        if (this.topColumn != ix) {
            log.debug("%d", Integer.valueOf(ix));
            this.topColumn = ix;
            invalidate();
            if (!isWrapLines()) {
                updateHorizontalScrollBarPosition();
            }
        }
    }

    public int getScreenColumnCount() {
        return this.screenColumnCount;
    }

    public int getScreenRowCount() {
        return this.screenRowCount;
    }

    public int getModelLineCount() {
        return this.editor.getLineCount();
    }

    public int getMaxColumnCount() {
        return buffer().getWidth();
    }

    public boolean isWrapColumn(int x) {
        if (isWrapLines() && x == this.screenColumnCount) {
            return true;
        }
        return false;
    }

    public boolean isWrapLines() {
        return this.editor.isWrapLines();
    }

    public int getMaxCellCount() {
        if (isWrapLines()) {
            throw new Error();
        }
        ITabPolicy p = this.editor.getTabPolicy();
        return buffer().getMaxCellCount(p);
    }

    public void setSuppressBlink(boolean on) {
        this.suppressBlink.set(on);
        if (!on && this.cursorAnimation != null) {
            updateBlinkRate();
        }
    }

    public void updateBlinkRate() {
        Duration d = this.editor.getBlinkRate();
        Duration period = d.multiply(2.0d);
        this.cursorAnimation.stop();
        this.cursorAnimation.getKeyFrames().setAll(new KeyFrame(Duration.ZERO, ev -> {
            this.caretEnabledProperty.set(true);
        }, new KeyValue[0]), new KeyFrame(d, ev2 -> {
            this.caretEnabledProperty.set(false);
        }, new KeyValue[0]), new KeyFrame(period, new KeyValue[0]));
        this.cursorAnimation.play();
    }

    protected void updateFont() {
        Font f = this.editor.getFont();
        if (f == null) {
            f = Font.font("Monospace", 12.0d);
        }
        this.font = f;
        this.boldFont = Font.font(this.font.getFamily(), FontWeight.BOLD, FontPosture.REGULAR, this.font.getSize());
        this.boldItalicFont = Font.font(this.font.getFamily(), FontWeight.BOLD, FontPosture.ITALIC, this.font.getSize());
        this.italicFont = Font.font(this.font.getFamily(), FontWeight.NORMAL, FontPosture.ITALIC, this.font.getSize());
        this.metrics = null;
        this.lineNumbersCellCount = -1;
        updateLineNumbers();
        invalidate();
    }

    protected void setHandleScrollEvents(boolean on) {
        this.handleScrollEvents = on;
    }

    protected boolean isHandleScrollEvents() {
        return this.handleScrollEvents;
    }

    protected Color mixColor(Color base, Color added, double fraction) {
        if (base == null) {
            return added;
        }
        if (added == null) {
            return base;
        }
        return FX.mix(base, added, fraction);
    }

    public void setTextColor(Color c) {
        this.textColor = c;
        repaint();
    }

    public Color getTextColor() {
        return this.textColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TextCellMetrics textMetrics() {
        if (this.metrics == null) {
            getChildren().add(this.proto);
            this.proto.setText("8");
            this.proto.setFont(this.font);
            Bounds b = this.proto.getBoundsInLocal();
            int w = CKit.round(b.getWidth());
            int h = CKit.round(b.getHeight());
            getChildren().remove(this.proto);
            this.metrics = new TextCellMetrics(this.font, b.getMinY(), w, h);
        }
        return this.metrics;
    }

    protected Timeline createCursorAnimation() {
        Timeline t = new Timeline(new KeyFrame(Duration.millis(500.0d), ev -> {
            blinkCursor();
        }, new KeyValue[0]));
        t.setCycleCount(-1);
        t.play();
        return t;
    }

    protected void updateCursorAnimation(Window w) {
        if (w == null) {
            if (this.cursorAnimation != null) {
                log.trace("stopping cursor animation");
                this.cursorAnimation.stop();
                this.cursorAnimation = null;
            }
        } else if (this.cursorAnimation == null) {
            log.trace("starting cursor animation");
            this.cursorAnimation = createCursorAnimation();
        }
    }

    protected void blinkCursor() {
        this.cursorOn = !this.cursorOn;
        refreshCursor();
    }

    protected void refreshCursor() {
        EditorSelection sel = this.editor.getSelection();
        Marker caret = sel.getCaret();
        if (isVisible(caret)) {
            repaint();
        }
    }

    protected void updateModel() {
        log.trace();
        invalidate();
    }

    protected void handleSizeChange() {
        log.trace(() -> {
            return String.format("width=%.1f, height=%.1f", Double.valueOf(getWidth()), Double.valueOf(getHeight()));
        });
        invalidate();
        this.canvas = createCanvas();
        setCenter(this.canvas);
        this.gx = this.canvas.getGraphicsContext2D();
        this.gx.setFontSmoothingType(FontSmoothingType.GRAY);
        updateDimensions();
        paintAll();
    }

    protected Canvas createCanvas() {
        Insets m = getInsets();
        double w = ((getWidth() - m.getLeft()) - m.getRight()) + 1.0d;
        double h = ((getHeight() - m.getTop()) - m.getBottom()) + 1.0d;
        log.trace("w=%.1f, h=%.1f", Double.valueOf(w), Double.valueOf(h));
        return new Canvas(w, h);
    }

    public void invalidate() {
        this.screenBufferValid = false;
        repaint();
    }

    protected void updateLineNumbers() {
        int count;
        int len;
        this.lineNumbersColumnWidths = null;
        FxTextEditorModel m = this.editor.getModel();
        if (m == null) {
            return;
        }
        if (this.editor.isShowLineNumbers()) {
            ALineNumberFormatter fmt = this.editor.getLineNumberFormatter();
            if (fmt == null) {
                count = 0;
            } else {
                count = this.minLineNumberCellCount;
                int columns = fmt.getColumnCount();
                if (columns == 1) {
                    for (int i = 0; i < this.screenRowCount; i++) {
                        int ix = getTopLine() + i;
                        String s = fmt.formatLineNumber(ix);
                        if (s != null) {
                            count = Math.max(count, s.length());
                        }
                    }
                } else {
                    int[] widths = new int[columns];
                    for (int i2 = 0; i2 < this.screenRowCount; i2++) {
                        int ix2 = getTopLine() + i2;
                        String[] ss = fmt.formatMultiColumn(ix2);
                        for (int j = 0; j < columns; j++) {
                            String s2 = ss[j];
                            if (s2 != null && (len = s2.length()) > widths[j]) {
                                widths[j] = len;
                            }
                        }
                        int ct = 0;
                        for (int j2 = 0; j2 < columns; j2++) {
                            ct += widths[j2];
                        }
                        count = Math.max(count, ct);
                    }
                    this.lineNumbersColumnWidths = widths;
                }
            }
        } else {
            count = 0;
        }
        if (count != this.lineNumbersCellCount) {
            this.lineNumbersCellCount = count;
            if (count == 0) {
                this.lineNumbersBarWidth = 0;
            } else {
                TextCellMetrics tm = textMetrics();
                this.lineNumbersBarWidth = (count * tm.cellWidth) + this.lineNumbersGap + this.lineNumbersGap;
            }
            invalidate();
        }
        updateDimensions();
    }

    protected void updateDimensions() {
        log.trace();
        if (getWidth() == 0.0d || getHeight() == 0.0d) {
            return;
        }
        Insets m = getInsets();
        double w = (getWidth() - m.getLeft()) - m.getRight();
        double h = (getHeight() - m.getTop()) - m.getBottom();
        TextCellMetrics tm = textMetrics();
        if (this.lineNumbersCellCount > 0) {
            w -= ((this.lineNumbersCellCount * tm.cellWidth) + this.lineNumbersGap) + this.lineNumbersGap;
        }
        if (w < 0.0d) {
            w = 0.0d;
        }
        if (h < 0.0d) {
            h = 0.0d;
        }
        this.screenColumnCount = CKit.floor(w / tm.cellWidth);
        this.screenRowCount = CKit.floor(h / tm.cellHeight);
    }

    protected void updateHorizontalScrollBarSize() {
        if (!isWrapLines()) {
            int max = getMaxCellCount() + 1;
            double vis = getMaxColumnCount();
            double thumbSize = vis / max;
            this.editor.getHorizontalScrollBar().setVisibleAmount(thumbSize);
        }
    }

    protected void updateHorizontalScrollBarPosition() {
        double v;
        if (!isWrapLines()) {
            int max = getMaxCellCount();
            if (max <= this.screenColumnCount) {
                v = 0.0d;
            } else {
                v = this.topColumn / (max - this.screenColumnCount);
            }
            setHandleScrollEvents(false);
            try {
                this.editor.getHorizontalScrollBar().setValue(v);
            } finally {
                setHandleScrollEvents(true);
            }
        }
    }

    protected void updateVerticalScrollBarSize() {
        double v;
        int lineCount = getModelLineCount();
        if (isWrapLines()) {
            if (lineCount == 0) {
                v = 1.0d;
            } else {
                ScrollAssist a = ScrollAssist.create(this, this.topLine, getTopWrapRow());
                double total = lineCount + a.getAdditionalRows();
                if (total < this.screenRowCount) {
                    v = 1.0d;
                } else {
                    v = this.screenRowCount / total;
                }
            }
        } else if (lineCount < this.screenRowCount) {
            v = 1.0d;
        } else {
            v = this.screenRowCount / lineCount;
        }
        setHandleScrollEvents(false);
        try {
            this.editor.getVerticalScrollBar().setVisibleAmount(v);
        } finally {
            setHandleScrollEvents(true);
        }
    }

    protected void updateVerticalScrollBarPosition() {
        double v;
        int lineCount = getModelLineCount();
        if (lineCount == 0) {
            v = 0.0d;
        } else {
            if (isWrapLines()) {
                ScrollAssist a = ScrollAssist.create(this, this.topLine, getTopWrapRow());
                double max = getModelLineCount() + a.getAdditionalRows();
                v = (this.topLine + a.getAdditionalTopRows()) / max;
            } else if (lineCount < this.screenRowCount) {
                v = 0.0d;
            } else {
                v = this.topLine / (lineCount - this.screenRowCount);
            }
            double loaded = getLoadedRatio();
            if (loaded < 1.0d) {
                v *= loaded;
            }
        }
        setHandleScrollEvents(false);
        try {
            this.editor.getVerticalScrollBar().setValue(v);
        } finally {
            setHandleScrollEvents(true);
        }
    }

    protected double getLoadedRatio() {
        FxTextEditorModel m = this.editor.getModel();
        if (m == null) {
            return 1.0d;
        }
        double v = m.getLoadStatus().getProgress();
        if (v < 0.01d) {
            return 0.01d;
        }
        return v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void repaint() {
        if (!this.repaintRequested) {
            this.repaintRequested = true;
            FX.later(() -> {
                long start = System.nanoTime();
                try {
                    paintAll();
                    long elapsed = (System.nanoTime() - start) / 1000000;
                    if (elapsed > 100) {
                        log.warn("paintAll: %d", Long.valueOf(elapsed));
                    }
                    this.repaintRequested = false;
                } catch (Throwable th) {
                    long elapsed2 = (System.nanoTime() - start) / 1000000;
                    if (elapsed2 > 100) {
                        log.warn("paintAll: %d", Long.valueOf(elapsed2));
                    }
                    this.repaintRequested = false;
                    throw th;
                }
            });
        }
    }

    protected void handleWrapChange() {
        if (this.editor.isWrapLines()) {
            this.topColumn = 0;
        } else {
            this.editor.getHorizontalScrollBar().setValue(0.0d);
        }
        requestLayout();
        invalidate();
    }

    public void handleSelectionSegmentUpdate(SelectionSegment prev, SelectionSegment sel) {
        repaint();
    }

    protected void handleHorizontalScroll(double val) {
        if (this.handleScrollEvents && !isWrapLines()) {
            int max = getMaxCellCount() + 1;
            int vis = getMaxColumnCount();
            int fr = Math.max(0, max - vis);
            int off = CKit.round(fr * val);
            setTopCellIndex(off);
        }
    }

    protected void handleVerticalScroll(double val) {
        if (this.handleScrollEvents) {
            log.debug("val=%f", Double.valueOf(val));
            double loaded = getLoadedRatio();
            if (loaded < 1.0d) {
                if (val > loaded) {
                    FX.later(() -> {
                        this.editor.getVerticalScrollBar().setValue(loaded);
                    });
                }
                val /= loaded;
            }
            verticalScroll(val);
        }
    }

    protected Font getFont(TextCellStyle st) {
        if (st.isBold()) {
            if (st.isItalic()) {
                return this.boldItalicFont;
            }
            return this.boldFont;
        } else if (st.isItalic()) {
            return this.italicFont;
        } else {
            return this.font;
        }
    }

    public TextPos getInsertPosition(double screenx, double screeny) {
        int charIndex;
        Point2D p = this.canvas.screenToLocal(screenx, screeny);
        TextCellMetrics tm = textMetrics();
        double sx = p.getX() - this.lineNumbersBarWidth;
        if (sx < 0.0d) {
            sx = 0.0d;
        }
        double sy = p.getY();
        int x = CKit.round(sx / tm.cellWidth);
        if (isWrapLines() && x >= this.screenColumnCount) {
            x = this.screenColumnCount;
        }
        int y = CKit.floor(sy / tm.cellHeight);
        int topWrapRow = getTopWrapRow();
        WrapPos wp = advance(this.topLine, topWrapRow, y);
        int line = wp.getLine();
        if (isBeyondEOF(wp, y)) {
            charIndex = -1;
        } else {
            TextCell cell = wp.getWrapInfo().getCell(TextCell.globalInstance(), wp.getRow(), x + this.topColumn);
            charIndex = cell.getInsertCharIndex();
        }
        TextPos pos = new TextPos(line, charIndex);
        log.debug("screenx=%f, screeny=%f, pos=%s", Double.valueOf(screenx), Double.valueOf(screeny), pos);
        return pos;
    }

    protected boolean isBeyondEOF(WrapPos wp, int y) {
        int max = getModelLineCount();
        if (wp.getLine() >= max - 1) {
            try {
                ScreenRow r = this.screenBuffer.getRow(y);
                if (r.getLineNumber() < 0) {
                    return true;
                }
                if (r.getLineNumber() >= max) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    protected int getTopWrapRow() {
        return buffer().getRow(0).getWrapRow();
    }

    protected Color backgroundColor(boolean caretLine, boolean selected, Color lineColor, Color cellBG) {
        Color c = this.editor.getBackgroundColor();
        if (lineColor != null) {
            c = mixColor(c, lineColor, 0.9d);
        }
        if (caretLine) {
            c = mixColor(c, this.editor.getCaretLineColor(), CARET_LINE_OPACITY);
        }
        if (selected) {
            c = mixColor(c, this.editor.getSelectionBackgroundColor(), 0.9d);
        }
        if (cellBG != null) {
            c = mixColor(c, cellBG, CELL_BACKGROUND_OPACITY);
        }
        return c;
    }

    protected Color lineNumberBackgroundColor(boolean caretLine) {
        Color c = this.editor.getBackgroundColor();
        if (caretLine) {
            return c;
        }
        return mixColor(c, Color.GRAY, 0.1d);
    }

    protected ScreenBuffer buffer() {
        if (!this.screenBufferValid) {
            reflow();
            this.screenBufferValid = true;
            updateHorizontalScrollBarSize();
            updateVerticalScrollBarSize();
        }
        return this.screenBuffer;
    }

    public FlowLine getTextLine(int lineIndex) {
        if (lineIndex < 0) {
            throw new Error("lineIndex=" + lineIndex);
        }
        FxTextEditorModel m = this.editor.getModel();
        if (m != null && lineIndex < m.getLineCount()) {
            FlowLine f = this.cache.get(lineIndex);
            if (f == null) {
                ITextLine t = m.getTextLine(lineIndex);
                f = this.cache.insert(lineIndex, t);
            }
            return f;
        }
        return FlowLine.BLANK;
    }

    public void reset() {
        this.screenBuffer.reset();
        clearFlowLineCache();
        invalidate();
        if (this.screenColumnCount == 0 || this.screenRowCount == 0) {
            return;
        }
        updateVerticalScrollBarPosition();
        updateVerticalScrollBarSize();
        updateHorizontalScrollBarPosition();
        updateHorizontalScrollBarSize();
    }

    public void clearFlowLineCache() {
        this.cache.clear();
    }

    public void setBreakIterator(IBreakIterator b) {
        this.cache.setBreakIterator(b);
    }

    protected void reflow() {
        int startGlyphIndex;
        log.trace();
        this.repaintRequested = false;
        int bufferWidth = this.screenColumnCount + 1;
        int bufferHeight = this.screenRowCount + 1;
        this.screenBuffer.setSize(bufferWidth, bufferHeight);
        this.editor.getTabPolicy();
        int lineCount = getModelLineCount();
        if (isWrapLines()) {
            FlowLine fline = null;
            WrapInfo wr = null;
            int line = this.topLine;
            int rowCount = -1;
            int row = -1;
            for (int y = 0; y < bufferHeight; y++) {
                if (fline == null) {
                    fline = getTextLine(line);
                }
                if (wr == null) {
                    wr = getWrapInfo(fline);
                }
                if (rowCount < 0) {
                    rowCount = wr.getWrapRowCount();
                }
                if (y == 0) {
                    startGlyphIndex = this.topGlyphIndex;
                    row = wr.getWrapRowForGlyphIndex(startGlyphIndex);
                } else {
                    startGlyphIndex = wr.getGlyphIndexForRow(row);
                }
                int lineNumber = line <= lineCount ? line : -1;
                ScreenRow r = this.screenBuffer.getRow(y);
                r.init(fline, wr, lineNumber, row, startGlyphIndex);
                row++;
                if (row >= rowCount) {
                    line++;
                    fline = null;
                    wr = null;
                    row = 0;
                    rowCount = -1;
                }
            }
            return;
        }
        int line2 = this.topLine;
        int startGlyphIndex2 = this.topGlyphIndex;
        for (int y2 = 0; y2 < bufferHeight; y2++) {
            FlowLine fline2 = getTextLine(line2);
            WrapInfo wr2 = getWrapInfo(fline2);
            int lineNumber2 = line2 <= lineCount ? line2 : -1;
            ScreenRow r2 = this.screenBuffer.getRow(y2);
            r2.init(fline2, wr2, lineNumber2, 0, startGlyphIndex2);
            line2++;
        }
    }

    public void update(int startLine, int linesAdded, int endLine) {
        log.debug("start=%d end=%d inserted=%d", Integer.valueOf(startLine), Integer.valueOf(endLine), Integer.valueOf(linesAdded));
        this.cache.invalidate(startLine, endLine, linesAdded);
        int max = Math.max(endLine, startLine + linesAdded);
        if (max >= this.topLine && startLine <= this.topLine + this.screenRowCount + 1) {
            invalidate();
            requestLayout();
            updateLineNumbers();
        } else if (linesAdded != 0) {
            updateVerticalScrollBarPosition();
            updateVerticalScrollBarSize();
        }
    }

    protected void paintAll() {
        if (this.screenColumnCount == 0 || this.screenRowCount == 0) {
            log.trace("screenColumnCount=%d, screenRowCount=%d", Integer.valueOf(this.screenColumnCount), Integer.valueOf(this.screenRowCount));
            return;
        }
        boolean wrap = isWrapLines();
        boolean showLineNumbers = this.editor.isShowLineNumbers();
        ScreenBuffer buffer = buffer();
        int xmax = this.screenColumnCount;
        if (!wrap) {
            xmax++;
        }
        TextCellMetrics tm = textMetrics();
        TextCell cell = null;
        int ymax = this.screenRowCount + 1;
        for (int y = 0; y < ymax; y++) {
            this.gx.clearRect(0.0d, (y * tm.cellHeight) + 0.5d, getWidth(), tm.cellHeight);
            ScreenRow row = buffer.getScreenRow(y);
            if (showLineNumbers) {
                paintLineNumber(tm, row, y);
            }
            int x = 0;
            while (x < xmax) {
                cell = row.getCell(x + this.topColumn);
                GlyphType t = cell.getGlyphType();
                switch ($SWITCH_TABLE$goryachev$fxtexteditor$GlyphType()[t.ordinal()]) {
                    case 1:
                        paintBlank(tm, row, cell, x, y, xmax - x);
                        break;
                    case 2:
                        paintBlank(tm, row, cell, x, y, xmax - x);
                        x = xmax;
                        break;
                    case 3:
                        paintCell(tm, row, cell, x, y);
                        break;
                    case 4:
                        int w = cell.getTabSpan();
                        paintBlank(tm, row, cell, x, y, w);
                        x += w - 1;
                        break;
                    default:
                        throw new Error("?" + t);
                }
                x++;
            }
            if (wrap) {
                paintBlank(tm, row, cell, xmax, y, 1);
            }
        }
    }

    protected String charAt(String text, int pos, int width) {
        int ix;
        if (text != null && (ix = (pos - this.lineNumbersCellCount) + text.length()) >= 0 && ix < text.length() - 0) {
            return text.substring(ix, ix + 1);
        }
        return null;
    }

    protected void paintLineNumber(TextCellMetrics tm, ScreenRow row, int y) {
        int ix;
        double cy = y * tm.cellHeight;
        boolean caretLine = SelectionHelper.isCaretLine(this.editor.selector.getSelectedSegment(), row);
        Color bg = lineNumberBackgroundColor(caretLine);
        this.gx.setFill(bg);
        this.gx.fillRect(0.0d, cy, (tm.cellWidth * this.lineNumbersCellCount) + this.lineNumbersGap + this.lineNumbersGap, tm.cellHeight);
        Color fg = this.editor.getLineNumberColor();
        if ((y == 0 || row.isBOL()) && (ix = row.getLineNumber()) >= 0 && ix < this.editor.getLineCount()) {
            ALineNumberFormatter fmt = this.editor.getLineNumberFormatter();
            int columns = fmt.getColumnCount();
            if (columns == 1) {
                String txt = fmt.formatLineNumber(ix + 1);
                for (int i = 0; i < this.lineNumbersCellCount; i++) {
                    String ch = charAt(txt, i, this.lineNumbersCellCount);
                    if (ch != null) {
                        double cx = (i * tm.cellWidth) + this.lineNumbersGap;
                        this.gx.setFont(this.font);
                        this.gx.setFill(fg);
                        this.gx.fillText(ch, cx, cy - tm.baseline, tm.cellWidth);
                    }
                }
                return;
            }
            String[] values = fmt.formatMultiColumn(ix + 1);
            this.gx.setFont(this.font);
            this.gx.setFill(fg);
            int px = 0;
            for (int i2 = 0; i2 < columns; i2++) {
                int width = this.lineNumbersColumnWidths[i2];
                boolean right = fmt.isRightAlignmentForColumn(i2);
                String txt2 = values[i2];
                if (right) {
                    px += width - txt2.length();
                }
                for (int j = 0; j < txt2.length(); j++) {
                    char c = txt2.charAt(j);
                    String ch2 = String.valueOf(c);
                    if (ch2 != null) {
                        double cx2 = (px * tm.cellWidth) + this.lineNumbersGap;
                        this.gx.fillText(ch2, cx2, cy - tm.baseline, tm.cellWidth);
                    }
                    px++;
                }
            }
        }
    }

    protected void paintBlank(TextCellMetrics tm, ScreenRow row, TextCell cell, int x, int y, int count) {
        double cx = (x * tm.cellWidth) + this.lineNumbersBarWidth;
        double cy = y * tm.cellHeight;
        int line = row.getLineNumber();
        int flags = SelectionHelper.getFlags(this, this.editor.selector.getSelectedSegment(), line, cell, x);
        boolean caretLine = SelectionHelper.isCaretLine(flags);
        boolean caret = this.paintCaret.get() ? SelectionHelper.isCaret(flags) : false;
        boolean selected = SelectionHelper.isSelected(flags);
        Color bg = backgroundColor(caretLine, selected, row.getLineColor(), null);
        this.gx.setFill(bg);
        this.gx.fillRect(cx, cy, tm.cellWidth * count, tm.cellHeight);
        if (caret) {
            this.gx.setFill(this.caretColor);
            this.gx.fillRect(cx, cy, 2.0d, tm.cellHeight);
        }
    }

    protected void paintCell(TextCellMetrics tm, ScreenRow row, TextCell cell, int x, int y) {
        double cx = (x * tm.cellWidth) + this.lineNumbersBarWidth;
        double cy = y * tm.cellHeight;
        int line = row.getLineNumber();
        int flags = SelectionHelper.getFlags(this, this.editor.selector.getSelectedSegment(), line, cell, x);
        boolean caretLine = SelectionHelper.isCaretLine(flags);
        boolean caret = SelectionHelper.isCaret(flags);
        boolean selected = SelectionHelper.isSelected(flags);
        TextCellStyle style = row.getCellStyles(cell);
        if (style == null) {
            style = TextCellStyle.NONE;
        }
        Color bg = backgroundColor(caretLine, selected, row.getLineColor(), style.getBackgroundColor());
        this.gx.setFill(bg);
        this.gx.fillRect(cx, cy, tm.cellWidth, tm.cellHeight);
        if (this.paintCaret.get() && caret) {
            this.gx.setFill(this.caretColor);
            this.gx.fillRect(cx, cy, 2.0d, tm.cellHeight);
        }
        if (style.isUnderscore()) {
            this.gx.setFill(this.textColor);
            this.gx.fillRect(cx, (cy + tm.cellHeight) - 1.0d, tm.cellWidth, 1.0d);
        }
        String text = row.getCellText(cell);
        if (text != null) {
            Color fg = style.getTextColor();
            if (fg == null) {
                fg = this.textColor;
            }
            Font f = getFont(style);
            this.gx.setFont(f);
            this.gx.setFill(fg);
            this.gx.fillText(text, cx, cy - tm.baseline, tm.cellWidth);
            if (style.isStrikeThrough()) {
                this.gx.setFill(this.textColor);
                this.gx.fillRect(cx, cy + (tm.cellHeight / 2), tm.cellWidth, 1.0d);
            }
        }
    }

    public void scroll(int scrollSizeInLines, boolean up) {
        log.trace("scroll=%d %s", Integer.valueOf(scrollSizeInLines), Boolean.valueOf(up));
        if (scrollSizeInLines < 1) {
            scrollSizeInLines = 1;
        } else if (scrollSizeInLines > getScreenRowCount()) {
            scrollSizeInLines = getScreenRowCount();
        }
        shiftViewPort(up ? -scrollSizeInLines : scrollSizeInLines);
    }

    public void blockScroll(double deltaInPixels) {
        int delta;
        log.debug("blockScroll=%f", Double.valueOf(deltaInPixels));
        TextCellMetrics tm = textMetrics();
        double ch = tm.cellHeight;
        if (deltaInPixels < 0.0d) {
            delta = (int) Math.floor(deltaInPixels / ch);
        } else {
            delta = (int) Math.ceil(deltaInPixels / ch);
        }
        shiftViewPort(delta);
    }

    public void verticalScroll(double fraction) {
        int gix;
        int lineCount = getModelLineCount();
        int vis = this.screenRowCount;
        int max = Math.max(0, (lineCount + 1) - vis);
        int top = CKit.round(max * fraction);
        if (isWrapLines()) {
            VerticalScrollHelper h = new VerticalScrollHelper(this, lineCount, top, fraction);
            GlyphPos p = h.process();
            top = p.getLine();
            gix = p.getGlyphIndex();
        } else {
            gix = 0;
        }
        setOrigin(top, gix);
    }

    public WrapInfo getWrapInfo(int line) {
        FlowLine fline = getTextLine(line);
        return getWrapInfo(fline);
    }

    public WrapInfo getWrapInfo(FlowLine fline) {
        return fline.getWrapInfo(this.editor.getTabPolicy(), this.screenColumnCount, isWrapLines());
    }

    public void scrollCaretToView() {
        int delta;
        EditorSelection sel = this.editor.getSelection();
        Marker caret = sel.getCaret();
        if (caret == null || isVisible(caret)) {
            return;
        }
        int caretLine = caret.getLine();
        if (isWrapLines()) {
            if (caretLine <= this.topLine) {
                delta = 0;
            } else {
                delta = 1 - this.screenRowCount;
            }
            WrapInfo wr = getWrapInfo(caretLine);
            int caretWrapRow = wr.getWrapRowForCharIndex(caret.getCharIndex());
            WrapPos wp = ensureLastPageFullView(advance(caretLine, caretWrapRow, delta));
            int line = wp.getLine();
            int gix = wp.getStartGlyphIndex();
            setOrigin(line, gix);
            return;
        }
        int topCell = this.topColumn;
        getTextLine(caretLine);
        int x = getColumnAt(caretLine, caret.getCharIndex());
        if (x < this.topColumn) {
            int x2 = x - 8;
            if (x2 < 8) {
                x2 = 0;
            }
            topCell = x2;
        } else if (x >= this.topColumn + this.screenColumnCount) {
            int x3 = (x + 1) - this.screenColumnCount;
            if (x3 < 0) {
                x3 = 0;
            }
            topCell = x3;
        }
        int top = this.topLine;
        if (caretLine < this.topLine) {
            top = caretLine;
        } else if (caretLine >= this.topLine + this.screenRowCount) {
            int y = (caretLine + 1) - this.screenRowCount;
            if (y < 0) {
                y = 0;
            }
            top = y;
        }
        int i = this.topColumn;
        int i2 = this.topLine;
        setTopCellIndex(topCell);
        setOrigin(top, 0);
    }

    protected boolean isVisible(Marker m) {
        int line;
        if (m == null || (line = m.getLine()) < this.topLine || line >= this.topLine + this.screenRowCount) {
            return false;
        }
        if (isWrapLines()) {
            FlowLine fline = getTextLine(line);
            int gix = fline.getGlyphIndex(m.getCharIndex());
            ScreenRow r = buffer().getScreenRow(0);
            int ln = r.getLineNumber();
            if (ln < 0 || compare(line, gix, ln, r.getStartGlyphIndex()) < 0) {
                return false;
            }
            ScreenRow r2 = buffer().getScreenRow(this.screenRowCount - 1);
            if (r2.getLineNumber() >= 0 && compare(line, gix, r2.getLineNumber(), r2.getStartGlyphIndex() + r2.getGlyphCount()) > 0) {
                return false;
            }
            return true;
        }
        WrapInfo wr = getWrapInfo(m.getLine());
        int col = wr.getColumnForCharIndex(m.getCharIndex());
        if (col < this.topColumn || col >= this.topColumn + this.screenColumnCount) {
            return false;
        }
        return true;
    }

    protected static int compare(int lineA, int glyphIndexA, int lineB, int glyphIndexB) {
        if (lineA < 0) {
            throw new Error();
        }
        if (lineB < 0) {
            throw new Error();
        }
        int d = lineA - lineB;
        if (d == 0) {
            if (glyphIndexA < 0) {
                throw new Error();
            }
            if (glyphIndexB < 0) {
                throw new Error();
            }
            d = glyphIndexA - glyphIndexB;
        }
        return d;
    }

    public int getPhantomColumn() {
        return this.phantomColumn;
    }

    public int updatePhantomColumn(int line, int charIndex) {
        int col = getPhantomColumn();
        if (col < 0) {
            col = getColumnAt(line, charIndex);
            setPhantomColumn(col);
        }
        return col;
    }

    public int getColumnAt(int line, int charIndex) {
        WrapInfo wr = getWrapInfo(line);
        return wr.getColumnForCharIndex(charIndex);
    }

    public void setPhantomColumn(int x) {
        log.debug(Integer.valueOf(x));
        this.phantomColumn = x;
    }

    public void setPhantomColumn(int line, int charIndex) {
        int col = getColumnAt(line, charIndex);
        setPhantomColumn(col);
    }

    public void setPhantomColumnFromCursor() {
        Marker m = this.editor.getSelection().getCaret();
        if (m == null) {
            return;
        }
        int line = m.getLine();
        int charIndex = m.getCharIndex();
        int col = getColumnAt(line, charIndex);
        setPhantomColumn(col);
    }

    public WrapPos advance(int startLine, int startWrapRow, int delta) {
        log.trace("line=%d row=%d delta=%d", Integer.valueOf(startLine), Integer.valueOf(startWrapRow), Integer.valueOf(delta));
        WrapInfo wr = getWrapInfo(startLine);
        int line = startLine;
        int row = startWrapRow;
        int steps = Math.abs(delta);
        if (delta < 0) {
            while (true) {
                if (steps <= 0) {
                    break;
                } else if (steps <= row) {
                    row -= steps;
                    break;
                } else if (line == 0) {
                    break;
                } else {
                    steps -= row + 1;
                    line--;
                    wr = getWrapInfo(line);
                    row = wr.getWrapRowCount() - 1;
                }
            }
        } else if (delta > 0) {
            int size = wr.getWrapRowCount() - row;
            int last = getModelLineCount() - 1;
            while (true) {
                if (steps <= 0) {
                    break;
                } else if (steps < size) {
                    row += steps;
                    break;
                } else {
                    steps -= size;
                    if (line >= last) {
                        break;
                    }
                    line++;
                    row = 0;
                    wr = getWrapInfo(line);
                    size = wr.getWrapRowCount();
                }
            }
        }
        WrapPos p = new WrapPos(line, row, wr);
        log.trace(p);
        return p;
    }
}
