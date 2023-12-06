package goryachev.fxtexteditor.internal;

import goryachev.common.log.Log;
import goryachev.common.util.CMap;
import goryachev.common.util.text.IBreakIterator;
import goryachev.fxtexteditor.FxTextEditor;
import goryachev.fxtexteditor.ITextLine;
import java.util.Iterator;
import java.util.Random;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/FlowLineCache.class */
public class FlowLineCache {
    protected static final Log log = Log.get("FlowLineCache");
    private final FxTextEditor editor;
    private final int capacity;
    private final CMap<Integer, FlowLine> cache;
    private final Random random = new Random();
    private IBreakIterator breakIterator;

    public FlowLineCache(FxTextEditor editor, int capacity) {
        if (capacity <= 8) {
            throw new Error("capacity too small: " + capacity);
        }
        this.editor = editor;
        this.capacity = capacity;
        this.cache = new CMap<>(capacity);
    }

    public void setBreakIterator(IBreakIterator b) {
        this.breakIterator = b;
    }

    public FlowLine get(int key) {
        return this.cache.get(Integer.valueOf(key));
    }

    protected void prune() {
        Iterator<FlowLine> it = this.cache.values().iterator();
        while (it.hasNext()) {
            it.next();
            boolean remove = this.random.nextBoolean();
            if (remove) {
                it.remove();
            }
        }
    }

    public FlowLine insert(int key, ITextLine t) {
        if (this.cache.size() >= this.capacity - 1) {
            prune();
        }
        FlowLine f = new FlowLine(t, createInfo(t));
        this.cache.put(Integer.valueOf(key), f);
        return f;
    }

    public void clear() {
        this.cache.clear();
    }

    protected AGlyphInfo createInfo(ITextLine t) {
        if (t == null) {
            return AGlyphInfo.BLANK;
        }
        String text = t.getPlainText();
        return AGlyphInfo.create(text, this.breakIterator);
    }

    public void invalidate(int startIndex, int endIndex, int linesInserted) {
        int end;
        log.debug("start=%d, end=%d, ins=%d", Integer.valueOf(startIndex), Integer.valueOf(endIndex), Integer.valueOf(linesInserted));
        if (endIndex - startIndex == linesInserted) {
            end = endIndex;
        } else {
            end = Integer.MAX_VALUE;
        }
        log.trace("end=%d", Integer.valueOf(end));
        Iterator<FlowLine> it = this.cache.values().iterator();
        while (it.hasNext()) {
            FlowLine fline = it.next();
            int line = fline.getModelIndex();
            if (line >= startIndex && line <= end) {
                it.remove();
                log.trace("removed: %d", Integer.valueOf(line));
            }
        }
    }
}
