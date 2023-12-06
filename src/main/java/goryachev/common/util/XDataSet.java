package goryachev.common.util;

import java.util.Arrays;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/XDataSet.class */
public class XDataSet {
    protected CList<Object[]> data = new CList<>();
    private int width;

    public XDataSet() {
    }

    public XDataSet(XDataSet x) {
        this.width = x.width;
        int sz = x.getRowCount();
        for (int i = 0; i < sz; i++) {
            Object[] rs = x.data.get(i);
            this.data.add(rs == null ? null : Arrays.copyOf(rs, this.width));
        }
    }

    public int getColumnCount() {
        return this.width;
    }

    public int getRowCount() {
        return this.data.size();
    }

    public void addRow(Object... xs) {
        if (this.width < xs.length) {
            this.width = xs.length;
        }
        this.data.add(Arrays.copyOf(xs, xs.length));
    }

    public Object get(int row, int col) {
        Object[] r;
        if (row < this.data.size() && (r = this.data.get(row)) != null && col < r.length) {
            return r[col];
        }
        return null;
    }

    public void set(int row, int col, Object value) {
        while (row >= this.data.size()) {
            this.data.add(null);
        }
        Object[] r = this.data.get(row);
        if (r == null) {
            r = new Object[col + 8];
            this.data.set(row, r);
        } else if (col >= r.length) {
            r = Arrays.copyOf(r, col + 8);
            this.data.set(row, r);
        }
        r[col] = value;
        if (col >= this.width) {
            this.width = col + 1;
        }
    }

    public String dump() {
        SB sb = new SB();
        for (int r = 0; r < getRowCount(); r++) {
            for (int c = 0; c < getColumnCount(); c++) {
                if (c > 0) {
                    sb.a(',');
                }
                sb.a(get(r, c));
            }
            sb.a('\n');
        }
        return sb.toString();
    }
}
