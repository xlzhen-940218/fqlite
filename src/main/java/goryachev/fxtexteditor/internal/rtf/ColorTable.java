package goryachev.fxtexteditor.internal.rtf;

import goryachev.common.util.CList;
import goryachev.common.util.CMap;
import java.util.List;
import javafx.scene.paint.Color;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/rtf/ColorTable.class */
public class ColorTable {
    private final CList<Color> colors = new CList<>();
    private final CMap<Color, String> indexes = new CMap<>();

    public void add(Color c) {
        if (!this.indexes.containsKey(c)) {
            this.colors.add(c);
            this.indexes.put(c, String.valueOf(this.colors.size()));
        }
    }

    public String getIndexFor(Color c) {
        return this.indexes.get(c);
    }

    public List<Color> getColors() {
        return this.colors;
    }
}
