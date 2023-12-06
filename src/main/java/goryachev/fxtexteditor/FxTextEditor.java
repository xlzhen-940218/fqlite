package goryachev.fxtexteditor;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.D;
import goryachev.fx.CPane;
import goryachev.fx.FX;
import goryachev.fx.FxBoolean;
import goryachev.fx.FxDouble;
import goryachev.fx.FxObject;
import goryachev.fx.TextCellMetrics;
import goryachev.fx.XScrollBar;
import goryachev.fxtexteditor.internal.InputHandler;
import goryachev.fxtexteditor.internal.Markers;
import goryachev.fxtexteditor.internal.TabPolicy;
import java.io.StringWriter;
import java.io.Writer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Dimension2D;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.DataFormat;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/FxTextEditor.class */
public class FxTextEditor extends CPane {
    protected static final Log log = Log.get("FxTextEditor");
    public final Actions actions;
    protected final VFlow vflow;
    protected final ScrollBar hscroll;
    private InputHandler inputHandler;
    private boolean caretAtEofPriorToLastUpdate;
    protected final FxObject<Color> backgroundColor = new FxObject<>(Color.WHITE);
    protected final FxObject<Color> loadingIndicatorColor = new FxObject<>(FX.gray(32));
    protected final FxObject<Color> caretLineColor = new FxObject<>(FX.rgb(255, 200, 255));
    protected final FxObject<Color> selectionBackgroundColor = new FxObject<>(FX.rgb(255, 255, 128));
    protected final FxObject<Color> lineNumberColor = new FxObject<>(Color.GRAY);
    protected final FxObject<Font> fontProperty = new FxObject<>(Font.font("Monospace", 12.0d));
    protected final FxBoolean editableProperty = new FxBoolean(false);
    protected final FxObject<FxTextEditorModel> modelProperty = new FxObject<>();
    protected final FxBoolean wrapLinesProperty = new FxBoolean(true);
    protected final FxBoolean displayCaretProperty = new FxBoolean(true);
    protected final FxBoolean showLineNumbersProperty = new FxBoolean(true);
    protected final FxBoolean highlightCaretLineProperty = new FxBoolean(true);
    protected final FxDouble scrollWheelStepSize = new FxDouble(-0.25d);
    protected final FxObject<Duration> caretBlinkRateProperty = new FxObject<>(Duration.millis(500.0d));
    protected final FxObject<ALineNumberFormatter> lineNumberFormatterProperty = new FxObject<>(ALineNumberFormatter.getDefault());
    protected final FxObject<ITabPolicy> tabPolicy = new FxObject<>();
    protected final Markers markers = new Markers(32);
    protected final FxTextEditorModelListener modelListener = new FxTextEditorModelListener() { // from class: goryachev.fxtexteditor.FxTextEditor.1
        @Override // goryachev.fxtexteditor.FxTextEditorModelListener
        public void eventAllLinesChanged() {
            FxTextEditor.this.handleAllLinesChanged();
        }

        @Override // goryachev.fxtexteditor.FxTextEditorModelListener
        public void eventTextAltered(int line1, int charIndex1, int endLine, int endPos, int charsAdded1, int linesAdded, int charsAdded2) {
            FxTextEditor.this.handleTextAltered(line1, charIndex1, endLine, endPos, charsAdded1, linesAdded, charsAdded2);
        }
    };
    protected final ChangeListener<LoadStatus> loadStatusListener = new ChangeListener<LoadStatus>() { // from class: goryachev.fxtexteditor.FxTextEditor.2
        @Override // javafx.beans.value.ChangeListener
        public void changed(ObservableValue<? extends LoadStatus> observable, LoadStatus prev, LoadStatus cur) {
            FxTextEditor.this.updateLoadStatus(cur);
        }
    };
    protected final SelectionController selector = createSelectionController();
    protected final ScrollBar vscroll = createVScrollBar();

    public FxTextEditor() {
        this.vscroll.setOrientation(Orientation.VERTICAL);
        this.vscroll.setManaged(true);
        this.vscroll.setMin(0.0d);
        this.vscroll.setMax(1.0d);
        this.vscroll.addEventFilter(ScrollEvent.ANY, ev -> {
            ev.consume();
        });
        this.hscroll = createHScrollBar();
        this.hscroll.setOrientation(Orientation.HORIZONTAL);
        this.hscroll.setManaged(true);
        this.hscroll.setMin(0.0d);
        this.hscroll.setMax(1.0d);
        this.hscroll.addEventFilter(ScrollEvent.ANY, ev2 -> {
            ev2.consume();
        });
        this.hscroll.visibleProperty().bind(this.wrapLinesProperty.not());
        this.vflow = new VFlow(this);
        getChildren().addAll(this.vflow, this.vscroll, this.hscroll);
        this.actions = new Actions(this);
        this.inputHandler = createInputHandler();
        setFocusTraversable(true);
        setTabPolicy(TabPolicy.create(4));
        this.vflow.layoutXProperty().addListener((observableValue, p, c) -> {
            D.print("vflow", c);
        });
        layoutXProperty().addListener((observableValue2, p2, c2) -> {
            D.print("editor", c2);
        });
    }

    protected ScrollBar createVScrollBar() {
        return new XScrollBar();
    }

    protected ScrollBar createHScrollBar() {
        return new XScrollBar();
    }

    protected InputHandler createInputHandler() {
        return new InputHandler(this, this.vflow, this.selector);
    }

    public FxObject<Font> fontProperty() {
        return this.fontProperty;
    }

    public Font getFont() {
        return this.fontProperty.get();
    }

    public void setFont(Font f) {
        this.fontProperty.set(f);
    }

    public void setFontSize(double size) {
        Font f = getFont();
        setFont(Font.font(f.getFamily(), size));
    }

    public ScrollBar getVerticalScrollBar() {
        return this.vscroll;
    }

    public ScrollBar getHorizontalScrollBar() {
        return this.hscroll;
    }

    public void setContentPadding(Insets m) {
        this.vflow.setPadding(m);
    }

    public Insets getContentPadding() {
        return this.vflow.getPadding();
    }

    public FxObject<ALineNumberFormatter> lineNumberFormatterProperty() {
        return this.lineNumberFormatterProperty;
    }

    public ALineNumberFormatter getLineNumberFormatter() {
        return this.lineNumberFormatterProperty.get();
    }

    public void setLineNumberFormatter(ALineNumberFormatter f) {
        if (f == null) {
            f = ALineNumberFormatter.getDefault();
        }
        this.lineNumberFormatterProperty.set(f);
    }

    protected SelectionController createSelectionController() {
        return new SelectionController();
    }

    public ReadOnlyProperty<SelectionSegment> selectionSegmentProperty() {
        return this.selector.selectionSegmentProperty();
    }

    public SelectionSegment getSelectedSegment() {
        return this.selector.getSelectedSegment();
    }

    public ReadOnlyObjectProperty<EditorSelection> selectionProperty() {
        return this.selector.selectionProperty();
    }

    public EditorSelection getSelection() {
        return this.selector.getSelection();
    }

    public void clearSelection() {
        this.selector.clear();
    }

    public void setModel(FxTextEditorModel m) {
        this.markers.clear();
        clearSelection();
        FxTextEditorModel old = getModel();
        if (old != null) {
            old.removeListener(this.modelListener);
            old.loadStatus().removeListener(this.loadStatusListener);
        }
        this.modelProperty.set(m);
        this.caretAtEofPriorToLastUpdate = false;
        if (m != null) {
            this.vflow.setBreakIterator(m.getBreakIterator());
            m.addListener(this.modelListener);
            m.loadStatus().addListener(this.loadStatusListener);
            updateLoadStatus(m.getLoadStatus());
        }
        handleAllLinesChanged();
    }

    public FxTextEditorModel getModel() {
        return this.modelProperty.get();
    }

    public int getLineCount() {
        FxTextEditorModel m = getModel();
        if (m == null) {
            return 0;
        }
        return m.getLineCount();
    }

    protected void updateLoadStatus(LoadStatus s) {
        if (this.vscroll instanceof XScrollBar) {
            Color c = getLoadingIndicatorColor();
            XScrollBar vs = (XScrollBar) this.vscroll;
            if (s.isValid() && c != null) {
                vs.setPainter(canvas -> {
                    double w = canvas.getWidth();
                    double h = canvas.getHeight();
                    double y = s.getProgress() * h;
                    GraphicsContext g = canvas.getGraphicsContext2D();
                    g.setFill(c);
                    g.fillRect(0.0d, y, w, h - y);
                });
            } else {
                vs.setPainter(null);
            }
        }
    }

    public void setLoadingIndicatorColor(Color c) {
        this.loadingIndicatorColor.set(c);
    }

    public Color getLoadingIndicatorColor() {
        return this.loadingIndicatorColor.get();
    }

    public boolean isWrapLines() {
        return this.wrapLinesProperty.get();
    }

    public void setWrapLines(boolean on) {
        this.wrapLinesProperty.set(on);
    }

    public BooleanProperty wrapLinesProperty() {
        return this.wrapLinesProperty;
    }

    public ReadOnlyObjectProperty<FxTextEditorModel> modelProperty() {
        return this.modelProperty.getReadOnlyProperty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // goryachev.fx.CPane, javafx.scene.Parent
    public void layoutChildren() {
        double x0 = snappedLeftInset();
        double y0 = snappedTopInset();
        double vscrollWidth = 0.0d;
        double hscrollHeight = 0.0d;
        if (this.vscroll.isVisible()) {
            vscrollWidth = this.vscroll.prefWidth(-1.0d);
        }
        if (this.hscroll.isVisible()) {
            hscrollHeight = this.hscroll.prefHeight(-1.0d);
        }
        double w = (snapSizeX((getWidth() - vscrollWidth) - 1.0d) - snappedLeftInset()) - snappedRightInset();
        double h = (((getHeight() - snappedTopInset()) - snappedBottomInset()) - hscrollHeight) - 1.0d;
        layoutInArea(this.vscroll, w, y0 + 1.0d, vscrollWidth, h, 0.0d, null, true, true, HPos.RIGHT, VPos.TOP);
        layoutInArea(this.hscroll, x0 + 1.0d, h, w, hscrollHeight, 0.0d, null, true, true, HPos.LEFT, VPos.BOTTOM);
        layoutInArea(this.vflow, x0, y0, w, h, 0.0d, null, true, true, HPos.LEFT, VPos.TOP);
    }

    public Marker getInsertPosition(double screenx, double screeny) {
        TextPos p = this.vflow.getInsertPosition(screenx, screeny);
        int line = p.getLine();
        int off = p.getCharIndex();
        if (line < 0) {
            line = getLineCount();
            off = 0;
        } else if (off < 0) {
            String s = getPlainText(line);
            if (s == null) {
                off = 0;
            } else {
                off = s.length();
            }
        }
        return this.markers.newMarker(line, off);
    }

    public Marker newMarker(int lineNumber, int position) {
        return this.markers.newMarker(lineNumber, position);
    }

    public ReadOnlyObjectProperty<Duration> blinkRateProperty() {
        return this.caretBlinkRateProperty.getReadOnlyProperty();
    }

    public Duration getBlinkRate() {
        return this.caretBlinkRateProperty.get();
    }

    public void setBlinkRate(Duration d) {
        this.caretBlinkRateProperty.set(d);
    }

    public boolean isEditable() {
        return this.editableProperty.get();
    }

    public void setEditable(boolean on) {
        this.editableProperty.set(on);
    }

    public void setScrollWheelStepSize(double val) {
        this.scrollWheelStepSize.set(val);
    }

    public double getScrollWheelStepSize() {
        return this.scrollWheelStepSize.get();
    }

    protected void handleAllLinesChanged() {
        this.vflow.reset();
    }

    public void setDisplayCaret(boolean on) {
        this.displayCaretProperty.set(on);
    }

    public boolean isDisplayCaret() {
        return this.displayCaretProperty.get();
    }

    public void setShowLineNumbers(boolean on) {
        this.showLineNumbersProperty.set(on);
    }

    public boolean isShowLineNumbers() {
        return true;
    }

    public BooleanProperty showLineNumbersProperty() {
        return this.showLineNumbersProperty;
    }

    public void setHighlightCaretLine(boolean on) {
        this.highlightCaretLineProperty.set(on);
    }

    public boolean isHighlightCaretLine() {
        return this.highlightCaretLineProperty.get();
    }

    public void setOrigin(int row) {
        if (row >= getLineCount()) {
            row = getLineCount() - 1;
        }
        if (row < 0) {
            row = 0;
        }
        this.vflow.setOrigin(row, 0);
    }

    public void setCaret(int row, int charIndex) {
        Marker m = newMarker(row, charIndex);
        select(m, m);
    }

    public void setDoubleClickHandler(BiConsumer<FxTextEditor, Marker> h) {
        this.inputHandler.setDoubleClickHandler(h);
    }

    public BiConsumer<FxTextEditor, Marker> getDoubleClickHandler() {
        return this.inputHandler.getDoubleClickHandler();
    }

    public void setTripleClickHandler(BiConsumer<FxTextEditor, Marker> h) {
        this.inputHandler.setTripleClickHandler(h);
    }

    public BiConsumer<FxTextEditor, Marker> getTripleClickHandler() {
        return this.inputHandler.getTripleClickHandler();
    }

    public int getTextLength(int line) {
        String s;
        FxTextEditorModel m = getModel();
        if (m == null || (s = m.getPlainText(line)) == null) {
            return 0;
        }
        return s.length();
    }

    public Color getCaretLineColor() {
        return this.caretLineColor.get();
    }

    public void setCaretLineColor(Color c) {
        this.caretLineColor.set(c);
    }

    public Color getSelectionBackgroundColor() {
        return this.selectionBackgroundColor.get();
    }

    public void setSelectionBackgroundColor(Color c) {
        this.selectionBackgroundColor.set(c);
    }

    public Color getLineNumberColor() {
        return this.lineNumberColor.get();
    }

    public void setLineNumberColor(Color c) {
        this.lineNumberColor.set(c);
    }

    public void reloadVisibleArea() {
        this.vflow.repaint();
    }

    public ITabPolicy getTabPolicy() {
        return this.tabPolicy.get();
    }

    public void setTabPolicy(ITabPolicy p) {
        if (p == null) {
            p = TabPolicy.create(1);
        }
        this.tabPolicy.set(p);
    }

    public void setTabSize(int size) {
        setTabPolicy(TabPolicy.create(size));
    }

    public boolean isCaretLine(int line) {
        return this.selector.isCaretLine(line);
    }

    public boolean isSelected(int line, int pos) {
        return this.selector.isSelected(line, pos);
    }

    public void setBackgroundColor(Color c) {
        this.backgroundColor.set(c);
    }

    public Color getBackgroundColor() {
        return this.backgroundColor.get();
    }

    public FxObject<Color> backgroundColorProperty() {
        return this.backgroundColor;
    }

    public int getColumnAt(Marker m) {
        int line = m.getLine();
        int pos = m.getCharIndex();
        return this.vflow.getColumnAt(line, pos);
    }

    public String getPlainText(int line) {
        FxTextEditorModel m = getModel();
        if (m == null) {
            return null;
        }
        return m.getPlainText(line);
    }

    public String getSelectedPlainText() throws Exception {
        StringWriter wr = new StringWriter();
        writeSelectedText(wr);
        return wr.toString();
    }

    public void writeSelectedText(Writer wr) throws Exception {
        SelectionSegment seg;
        EditorSelection sel = getSelection();
        if (sel == null || (seg = sel.getSegment()) == null) {
            return;
        }
        int startLine = seg.getMin().getLine();
        int startPos = seg.getMin().getCharIndex();
        int endLine = seg.getMax().getLine();
        int endPos = seg.getMax().getCharIndex();
        getModel().writePlainText(startLine, startPos, endLine, endPos, wr);
    }

    public void copy() {
        copy(null, false, getModel().getSupportedFormats(true));
    }

    public void smartCopy() {
        copy(null, true, getModel().getSupportedFormats(true));
    }

    public void copyPlainText() {
        copy(null, false, DataFormat.PLAIN_TEXT);
    }

    public void smartCopyPlainText() {
        copy(null, true, DataFormat.PLAIN_TEXT);
    }

    public void copyRTF() {
        DataFormat[] formats = getModel().getSupportedFormats(true);
        if (CKit.contains(formats, DataFormat.RTF)) {
            copy(null, false, DataFormat.RTF);
        }
    }

    public void smartCopyRTF() {
        DataFormat[] formats = getModel().getSupportedFormats(true);
        if (CKit.contains(formats, DataFormat.RTF)) {
            copy(null, true, DataFormat.RTF);
        }
    }

    public void copyHTML() {
        DataFormat[] formats = getModel().getSupportedFormats(true);
        if (CKit.contains(formats, DataFormat.HTML)) {
            copy(null, false, DataFormat.HTML);
        }
    }

    public void smartCopyHTML() {
        DataFormat[] formats = getModel().getSupportedFormats(true);
        if (CKit.contains(formats, DataFormat.HTML)) {
            copy(null, true, DataFormat.HTML);
        }
    }

    public void copy(Consumer<Throwable> errorHandler, boolean smart, DataFormat... formats) {
        int startLine;
        int startPos;
        int endLine;
        int endPos;
        SelectionSegment seg = getNonEmptySelection();
        if (seg == null) {
            if (smart) {
                startLine = 0;
                startPos = 0;
                endLine = getLineCount() - 1;
                endPos = getTextLength(endLine);
            } else {
                return;
            }
        } else {
            startLine = seg.getMin().getLine();
            startPos = seg.getMin().getCharIndex();
            endLine = seg.getMax().getLine();
            endPos = seg.getMax().getCharIndex();
        }
        if (startLine == endLine && startPos == endPos) {
            return;
        }
        copy(startLine, startPos, endLine, endPos, errorHandler, formats);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SelectionSegment getNonEmptySelection() {
        SelectionSegment seg;
        EditorSelection sel = getSelection();
        if (sel != null && (seg = sel.getSegment()) != null && !seg.isEmpty()) {
            return seg;
        }
        return null;
    }

    public void copy(int startLine, int startPos, int endLine, int endPos, Consumer<Throwable> errorHandler, DataFormat... formats) {
        getModel().copyToClipboard(startLine, startPos, endLine, endPos, errorHandler, formats);
    }

    public void copyAll() {
        FxTextEditorModel m = getModel();
        int endLine = m.getLineCount() - 1;
        int endPos = m.getTextLine(endLine).getTextLength();
        copy(0, 0, endLine, endPos, null, m.getSupportedFormats(true));
    }

    public void select(Marker start, Marker end) {
        this.selector.setSelection(start, end);
        this.selector.commitSelection();
        this.vflow.scrollCaretToView();
    }

    public void scrollCaretToView() {
        this.vflow.scrollCaretToView();
    }

    public void select(int startLine, int startPos, int endLine, int endPos) {
        Marker start = this.markers.newMarker(startLine, startPos);
        Marker end = this.markers.newMarker(endLine, endPos);
        select(start, end);
    }

    protected void handleTextAltered(int line1, int charIndex1, int line2, int charIndex2, int charsAdded1, int linesAdded, int charsAdded2) {
        log.debug("line1=%d charIndex1=%d line2=%d charIndex2=%d | charsAdded1=%d linesAdded=%d charsAdded2=%d", Integer.valueOf(line1), Integer.valueOf(charIndex1), Integer.valueOf(line2), Integer.valueOf(charIndex2), Integer.valueOf(charsAdded1), Integer.valueOf(linesAdded), Integer.valueOf(charsAdded2));
        this.caretAtEofPriorToLastUpdate = isCaretAtEOF();
        this.markers.update(line1, charIndex1, charsAdded1, linesAdded, line2, charIndex2, charsAdded2);
        this.selector.refresh();
        this.vflow.update(line1, linesAdded, line2);
    }

    public void goToLine(int row) {
        if (row >= 0 && row < getLineCount()) {
            setOrigin(Math.max(0, row - 3));
            setCaret(row, 0);
        }
    }

    public int getCaretLine() {
        SelectionSegment seg = this.selector.getSelectedSegment();
        if (seg == null) {
            return 0;
        }
        return seg.getCaretLine();
    }

    public boolean isCaretAtEOF() {
        SelectionSegment seg = getSelectedSegment();
        if (seg == null) {
            return true;
        }
        if (seg.isEmpty() && seg.getCaretLine() == getLineCount() - 1) {
            String txt = getPlainText(seg.getCaretLine());
            int end = txt == null ? 0 : txt.length();
            if (seg.getMaxCharIndex() == end) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean isCaretAtEofPriorToLastUpdate() {
        return this.caretAtEofPriorToLastUpdate;
    }

    public void scrollToEOF() {
        log.debug("scrollToEOF");
        this.actions.moveDocumentEnd().fire();
        scrollCaretToView();
    }

    public Dimension2D computePreferredContentSize() {
        FxTextEditorModel m = getModel();
        int w = 0;
        int max = Math.min(1000, m.getLineCount());
        for (int i = 0; i < max; i++) {
            ITextLine t = m.getTextLine(i);
            int len = t.getTextLength();
            if (len > w) {
                w = len;
            }
        }
        Insets p = getContentPadding();
        TextCellMetrics tm = this.vflow.textMetrics();
        double width = (w * tm.cellWidth) + p.getLeft() + p.getRight();
        double height = (max * tm.cellHeight) + p.getTop() + p.getBottom();
        return new Dimension2D(width, height);
    }
}
