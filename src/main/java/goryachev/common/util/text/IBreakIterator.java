package goryachev.common.util.text;

import java.text.BreakIterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/text/IBreakIterator.class */
public interface IBreakIterator {
    public static final int DONE = -1;

    void setText(String str);

    int first();

    int next();

    IBreakIterator copy();

    static IBreakIterator wrap(final BreakIterator br) {
        return new IBreakIterator() { // from class: goryachev.common.util.text.IBreakIterator.1
            @Override // goryachev.common.util.text.IBreakIterator
            public void setText(String text) {
                br.setText(text);
            }

            @Override // goryachev.common.util.text.IBreakIterator
            public int first() {
                return br.first();
            }

            @Override // goryachev.common.util.text.IBreakIterator
            public int next() {
                int rv = br.next();
                if (rv == -1) {
                    return -1;
                }
                return rv;
            }

            @Override // goryachev.common.util.text.IBreakIterator
            public IBreakIterator copy() {
                return (IBreakIterator) br.clone();
            }
        };
    }
}
