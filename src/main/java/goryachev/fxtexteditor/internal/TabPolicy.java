package goryachev.fxtexteditor.internal;

import goryachev.fxtexteditor.ITabPolicy;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/internal/TabPolicy.class */
public class TabPolicy implements ITabPolicy {
    private final int tabWidth;

    public TabPolicy(int tabWidth) {
        if (tabWidth <= 0) {
            throw new IllegalArgumentException("tabWidth must be >0: " + tabWidth);
        }
        this.tabWidth = tabWidth;
    }

    public static TabPolicy create(int tabWidth) {
        return new TabPolicy(tabWidth);
    }

    public int distanceToNextTabStop(int position) {
        int rv = this.tabWidth - (position % this.tabWidth);
        return rv == 0 ? this.tabWidth : rv;
    }

    @Override // goryachev.fxtexteditor.ITabPolicy
    public int nextTabStop(int position) {
        int d = distanceToNextTabStop(position);
        return position + d;
    }

    @Override // goryachev.fxtexteditor.ITabPolicy
    public boolean isSimple() {
        return this.tabWidth == 1;
    }
}
