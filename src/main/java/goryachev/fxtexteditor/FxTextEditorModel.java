package goryachev.fxtexteditor;

import goryachev.common.log.Log;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fx.FxBoolean;
import goryachev.fx.FxObject;
import goryachev.fxtexteditor.internal.SelectedTextSource;
import goryachev.fxtexteditor.internal.html.HtmlWriter;
import goryachev.fxtexteditor.internal.plain.PlainTextWriter;
import goryachev.fxtexteditor.internal.rtf.RtfWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/FxTextEditorModel.class */
public abstract class FxTextEditorModel {
    protected static final Log log = Log.get("FxTextEditorModel");
    protected final FxBoolean editableProperty = new FxBoolean(false);
    protected final FxObject<LoadStatus> loadStatus = new FxObject<>(LoadStatus.UNKNOWN);
    protected final CList<FxTextEditorModelListener> listeners = new CList<>();
    protected final CMap<DataFormat, IClipboardCopyHandler> copyHandlers = new CMap<>(1);
    protected final CMap<DataFormat, IClipboardPasteHandler> pasteHandlers = new CMap<>(0);

    public abstract int getLineCount();

    public abstract ITextLine getTextLine(int i);

    public abstract Edit edit(Edit edit) throws Exception;

    public abstract IBreakIterator getBreakIterator();

    public FxTextEditorModel() {
        setCopyHandler(DataFormat.PLAIN_TEXT, (m, sL, sC, eL, eC )-> {
            return copyPlainText(sL, sC, eL, eC);
        });
    }

    public FxObject<LoadStatus> loadStatus() {
        return this.loadStatus;
    }

    public void addListener(FxTextEditorModelListener li) {
        this.listeners.add(li);
    }

    public void removeListener(FxTextEditorModelListener li) {
        this.listeners.remove(li);
    }

    public boolean isEditable() {
        return this.editableProperty.get();
    }

    public void setEditable(boolean on) {
        this.editableProperty.set(on);
    }

    public FxBoolean editableProperty() {
        return this.editableProperty;
    }

    public void fireAllChanged() {
        fireEvent(li -> {
            li.eventAllLinesChanged();
        });
    }

    public void fireTextAltered(int line1, int charIndex1, int line2, int charIndex2, int charsInserted1, int linesInserted, int charsInserted2) {
        fireEvent(li -> {
            li.eventTextAltered(line1, charIndex1, line2, charIndex2, charsInserted1, linesInserted, charsInserted2);
        });
    }

    public void fireTextAltered(int line, int charIndex1, int charIndex2, int charsInserted) {
        fireTextAltered(line, charIndex1, line, charIndex2, charsInserted, 0, 0);
    }

    protected void fireEvent(Consumer<FxTextEditorModelListener> f) {
        Iterator<FxTextEditorModelListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            FxTextEditorModelListener li = it.next();
            f.accept(li);
        }
    }

    public void setLoadStatus(LoadStatus s) {
        if (s == null) {
            throw new NullPointerException("load status");
        }
        this.loadStatus.set(s);
    }

    public void setLoadComplete() {
        setLoadStatus(LoadStatus.COMPLETE);
    }

    public LoadStatus getLoadStatus() {
        return this.loadStatus.get();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DataFormat[] getSupportedFormats(boolean forCopy) {
        Set<DataFormat> keySet;
        if (forCopy) {
            keySet = this.copyHandlers.keySet();
        } else {
            keySet = this.pasteHandlers.keySet();
        }
        Set<DataFormat> fs = keySet;
        return (DataFormat[]) fs.toArray(new DataFormat[fs.size()]);
    }

    protected void setCopyHandler(DataFormat f, IClipboardCopyHandler h) {
        this.copyHandlers.put(f, h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDefaultRtfCopyHandler() {
        setCopyHandler(DataFormat.RTF, (m, sL, sC, eL, eC )-> {
            return copyRTF(sL, sC, eL, eC);
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDefaultHtmlCopyHandler() {
        setCopyHandler(DataFormat.HTML,( m, sL, sC, eL, eC) -> {
            return copyHTML(sL, sC, eL, eC);
        });
    }

    public final String getPlainText(int line) {
        ITextLine t;
        if (line < 0) {
            throw new Error("line=" + line);
        }
        if (line >= getLineCount() || (t = getTextLine(line)) == null) {
            return null;
        }
        return t.getPlainText();
    }

    public void copyToClipboard(int startLine, int startPos, int endLine, int endPos, Consumer<Throwable> errorHandler, DataFormat[] formats) {
        Object v;
        try {
            CMap<DataFormat, Object> m = null;
            for (DataFormat f : formats) {
                CKit.checkCancelled();
                IClipboardCopyHandler h = this.copyHandlers.get(f);
                if (h != null && (v = h.copy(this, startLine, startPos, endLine, endPos)) != null) {
                    if (m == null) {
                        m = new CMap<>();
                    }
                    m.put(f, v);
                }
            }
            if (m != null) {
                Clipboard c = Clipboard.getSystemClipboard();
                c.setContent(m);
            }
        } catch (Throwable e) {
            if (errorHandler == null) {
                log.error(FXMLLoader.COPY_TAG, e);
            } else {
                errorHandler.accept(e);
            }
        }
    }

    public String copyPlainText(int startLine, int startPos, int endLine, int endPos) throws Exception {
        StringWriter wr = new StringWriter();
        writePlainText(startLine, startPos, endLine, endPos, wr);
        return wr.toString();
    }

    public String copyRTF(int startLine, int startPos, int endLine, int endPos) throws Exception {
        return RtfWriter.writeString(() -> {
            return new SelectedTextSource(this, startLine, startPos, endLine, endPos);
        });
    }

    public String copyHTML(int startLine, int startPos, int endLine, int endPos) throws Exception {
        SelectedTextSource src = new SelectedTextSource(this, startLine, startPos, endLine, endPos);
        return HtmlWriter.writeString(src);
    }

    public void writePlainText(int startLine, int startPos, int endLine, int endPos, Writer wr) throws Exception {
        SelectedTextSource src = new SelectedTextSource(this, startLine, startPos, endLine, endPos);
        new PlainTextWriter(src, wr).write();
    }
}
