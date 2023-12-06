package goryachev.common.util;

import java.util.Iterator;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/SStream.class */
public class SStream implements Iterable<String> {
    private final CList<String> list;
    private int pos;

    public SStream() {
        this.list = new CList<>();
    }

    public SStream(String[] ss) {
        this.list = new CList<>(ss);
    }

    public int size() {
        return this.list.size();
    }

    public String getValue(int ix) {
        return this.list.get(ix);
    }

    public void add(Object x) {
        this.list.add(x == null ? null : x.toString());
    }

    public void add(double x) {
        if (((long) x) == x) {
            this.list.add(Long.toString((long) x));
        } else {
            this.list.add(Double.toString(x));
        }
    }

    public void addAll(double[] xs) {
        for (double x : xs) {
            add(x);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return this.list.iterator();
    }

    public String nextString() {
        if (this.pos < this.list.size()) {
            CList<String> cList = this.list;
            int i = this.pos;
            this.pos = i + 1;
            return cList.get(i);
        }
        return null;
    }

    public String nextString(String defaultValue) {
        String s = nextString();
        if (s == null) {
            return defaultValue;
        }
        return s;
    }

    public double nextDouble(double defaultValue) {
        return Parsers.parseDouble(nextString(), defaultValue);
    }

    public double nextDouble() {
        return nextDouble(-1.0d);
    }

    public int nextInt(int defaultValue) {
        return Parsers.parseInt(nextString(), defaultValue);
    }

    public int nextInt() {
        return nextInt(-1);
    }

    public String[] toArray() {
        return (String[]) this.list.toArray(new String[this.list.size()]);
    }

    public String toString() {
        return this.list.toString();
    }
}
