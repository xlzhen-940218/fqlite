package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/PTable.class */
public class PTable {
    private CList<Object> columns = new CList<>();
    private CList<Object[]> rows = new CList<>();

    public PTable() {
    }

    public PTable(Object[] columns) {
        for (Object c : columns) {
            addColumn(c);
        }
    }

    public int getColumnCount() {
        return this.columns.size();
    }

    public void addColumn(Object name) {
        this.columns.add(name);
    }

    public void addColumn() {
        addColumn(null);
    }

    public String getColumnName(int ix) {
        return Parsers.parseStringNotNull(this.columns.get(ix));
    }

    public Object getColumn(int ix) {
        if (ix >= 0 && ix < this.columns.size()) {
            return this.columns.get(ix);
        }
        return null;
    }

    public void setColumnName(int ix, String name) {
        ensureColumn(ix);
        this.columns.set(ix, name);
    }

    protected void ensureColumn(int col) {
        while (getColumnCount() <= col) {
            addColumn();
        }
    }

    public Object[] getColumns() {
        return this.columns.toArray();
    }

    public int indexOfColumn(Object x) {
        return this.columns.indexOf(x);
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public void addCells(Object... cells) {
        int r = getRowCount();
        for (int c = 0; c < cells.length; c++) {
            Object v = cells[c];
            setValueAt(r, c, v);
        }
    }

    public void addRow(Object[] cells) {
        int r = getRowCount();
        for (int c = 0; c < cells.length; c++) {
            Object v = cells[c];
            setValueAt(r, c, v);
        }
    }

    public Object setValueAt(int row, int col, Object value) {
        ensureColumn(col);
        Object[] r = ensureRow(row);
        Object old = r[col];
        r[col] = value;
        return old;
    }

    public Object getValueAt(int row, int col) {
        Object[] r;
        if (row < getRowCount() && (r = this.rows.get(row)) != null && col < r.length) {
            return r[col];
        }
        return null;
    }

    protected Object[] ensureRow(int row) {
        while (getRowCount() <= row) {
            this.rows.add(null);
        }
        Object[] r = this.rows.get(row);
        if (r == null) {
            r = new Object[getColumnCount()];
            this.rows.set(row, r);
        } else if (r.length < getColumnCount()) {
            r = new Object[getColumnCount()];
            System.arraycopy(r, 0, r, 0, r.length);
            this.rows.set(row, r);
        }
        return r;
    }

    public void insertRow(int row) {
        if (getRowCount() <= row) {
            ensureRow(row);
        } else {
            this.rows.add(row, null);
        }
    }

    public void removeRow(int row) {
        this.rows.remove(row);
    }

    public void removeRows(int startInclusive, int endExclusive) {
        this.rows.removeRange(startInclusive, endExclusive);
    }

    public void addAll(Object[][] vs) {
        if (vs != null) {
            int start = getRowCount();
            for (int r = 0; r < vs.length; r++) {
                Object[] vss = vs[r];
                int ix = r + start;
                if (vss != null) {
                    for (int c = 0; c < vss.length; c++) {
                        setValueAt(ix, c, vss[c]);
                    }
                }
            }
        }
    }

    public boolean equals(Object x) {
        if (x == this) {
            return true;
        }
        if (x instanceof PTable) {
            PTable p = (PTable) x;
            if (getRowCount() == p.getRowCount() && getColumnCount() == p.getColumnCount()) {
                for (int c = getColumnCount() - 1; c >= 0; c--) {
                    Object a = getColumn(c);
                    Object b = p.getColumn(c);
                    if (CKit.notEquals(a, b)) {
                        return false;
                    }
                }
                for (int r = getRowCount() - 1; r >= 0; r--) {
                    for (int c2 = getColumnCount() - 1; c2 >= 0; c2--) {
                        Object a2 = getValueAt(r, c2);
                        Object b2 = p.getValueAt(r, c2);
                        if (CKit.notEquals(a2, b2)) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int h = PTable.class.hashCode();
        for (int c = getColumnCount() - 1; c >= 0; c--) {
            Object a = getColumn(c);
            h = FH.hash(h, a);
        }
        for (int r = getRowCount() - 1; r >= 0; r--) {
            for (int c2 = getColumnCount() - 1; c2 >= 0; c2--) {
                Object a2 = getValueAt(r, c2);
                h = FH.hash(h, a2);
            }
        }
        return h;
    }
}
