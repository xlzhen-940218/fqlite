//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.descriptor;

import fqlite.pattern.HeaderPattern;
import fqlite.util.Auxiliary;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class IndexDescriptor extends AbstractDescriptor implements Comparable {
    public List<String> columntypes;
    public ArrayList<String> columnnames;
    public List<String> boolcolumns;
    int size = 0;
    public String idxname = "";
    public String tablename = "";
    public int root = -1;
    public HeaderPattern hpattern = null;
    private String sql = "";
    public Hashtable<String, String> tooltiptypes = new Hashtable();

    public boolean checkMatch(String match) {
        try {
            byte[] bcol = Auxiliary.decode(match);
            int[] values = Auxiliary.readVarInt(bcol);
            int headerlength = values[0];
            System.out.println(headerlength);
            boolean valid = true;

            for(int i = 1; i < values.length; ++i) {
                String type = this.columntypes.get(i - 1);
                switch (type) {
                    case "NUMERIC":
                        valid = values[i] >= 0 && values[i] <= 9;
                        break;
                    case "INT":
                        valid = values[i] >= 0 && values[i] <= 6;
                        break;
                    case "BLOB":
                        if (values[i] == 0) {
                            valid = true;
                        } else if (values[i] % 2 == 0) {
                            valid = values[i] > 12;
                        } else {
                            valid = false;
                        }
                        break;
                    case "REAL":
                        valid = values[i] == 7;
                        break;
                    case "TEXT":
                        if (values[i] == 0) {
                            valid = true;
                        } else if (values[i] % 2 != 0) {
                            valid = values[i] > 13;
                        } else {
                            valid = false;
                        }
                }

                if (!valid) {
                    return false;
                }
            }

            return true;
        } catch (Exception var9) {
            return false;
        }
    }

    public IndexDescriptor(String idxname, String tablename, String stmt, ArrayList<String> names) {
        super.ROWID = false;
        this.columnnames = names;
        this.idxname = idxname;
        this.tablename = tablename;
        this.columntypes = new LinkedList();
        this.setSql(stmt);
        this.boolcolumns = new LinkedList();

        for(int i = 0; i < names.size(); ++i) {
            if (this.columntypes.size() > i && ((String)this.columntypes.get(i)).startsWith("BOOL")) {
                this.boolcolumns.add((String)names.get(i));
            }

            if (this.columntypes.size() > i) {
                this.tooltiptypes.put((String)names.get(i), (String)this.columntypes.get(i));
            }
        }

    }

    public String getPattern(int startcolumn, int endcolumn, boolean multicol) {
        String pat = "";

        for(int i = startcolumn; i < endcolumn; ++i) {
            pat = pat + this.getColumn((String)this.columntypes.get(i), multicol);
        }

        return pat;
    }

    public String getName() {
        return this.idxname;
    }

    public int getRootOffset() {
        return this.root;
    }

    public int getLength() {
        return 1 + this.size;
    }

    public int numberofColumns() {
        return this.columntypes.size();
    }

    public Pattern getPatternMultiCol() {
        return Pattern.compile(this.getPattern(0, this.size, true));
    }

    private String getColumn(String serialtype, boolean multicol) {
        switch (serialtype) {
            case "INT":
                return "0[0-6]";
            case "BLOB", "TEXT":
                if (multicol) {
                    return "[0-9a-f][0-9a-f]{0,4}";
                }

                return "[0-9a-f][0-9a-f]";

            case "REAL":
                return "07";

        }

        return "[0-9a-f][0-9a-f]";
    }

    public void printIndexDefinition() {
        System.out.println("Index" + this.idxname);
        System.out.println("COLUMNS: " + String.valueOf(this.columnnames));
    }

    public String toString() {
        String output = "";
        return output;
    }

    public String getSql() {
        return this.sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean equals(Object o) {
        if (o instanceof IndexDescriptor c) {
            return this.idxname.equals(c.idxname);
        } else {
            return false;
        }
    }

    public String getToolTypeForColumn(String column) {
        return (String)this.tooltiptypes.get(column);
    }

    public int compareTo(IndexDescriptor o) {
        return this.tablename.compareTo(o.tablename);
    }

    public int compareTo(Object o) {
        return this.compareTo((IndexDescriptor)o);
    }
}
