//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.descriptor;

import fqlite.pattern.HeaderPattern;
import fqlite.util.Auxiliary;
import fqlite.util.Logger;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableDescriptor extends AbstractDescriptor implements Comparable<TableDescriptor> {
    String regex = "";
    String delregex = "";
    public List<String> serialtypes;
    public List<String> columnnames;
    public List<String> sqltypes;
    public List<String> constraints;
    public List<String> tableconstraints;
    public List<String> primarykeycolumns;
    public List<Integer> primarykeycolumnnumbers;
    public List<String> boolcolumns;
    public Hashtable<String, String> tooltiptypes = new Hashtable();
    int size = 0;
    int numberofmultibytecolumns = 0;
    String fingerprint = "";
    String signature = "";
    public String tblname = "";
    public int root = -1;
    public boolean ROWID = true;
    private HeaderPattern hpattern = null;
    public boolean virtual = false;
    public String modulname = null;
    public String sql = "";
    public String rowidcolumn = null;

    public boolean checkMatch(String match) {
        try {
            byte[] bcol = Auxiliary.decode(match);
            int[] values = Auxiliary.readVarInt(bcol);
            int headerlength = values[0];
            System.out.println(headerlength);
            boolean valid = true;

            for(int i = 1; i < values.length; ++i) {
                String type = getColumntypes().get(i - 1);
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

    public String getName() {
        return this.tblname;
    }

    public void setModulname(String name) {
        this.modulname = name;
    }

    public String getModulename() {
        return this.modulname;
    }

    public void signature(List<String> col) {
        this.signature = "";

        String var10001;
        for(Iterator<String> iter = this.getColumntypes().iterator(); iter.hasNext(); this.signature = var10001 + (String)iter.next()) {
            var10001 = String.valueOf(this.signature);
        }

    }

    public String getSignature() {
        return this.signature;
    }

    public TableDescriptor(String tblname, String stmt, List<String> sqltypes, List<String> col, List<String> names, List<String> constraints, List<String> tableconstraints, HeaderPattern pattern, boolean withoutROWID) {
        this.setColumntypes(col);
        this.signature(col);
        this.sqltypes = sqltypes;
        this.constraints = constraints;
        this.tableconstraints = tableconstraints;
        this.columnnames = names;
        this.ROWID = !withoutROWID;
        this.sql = stmt;
        this.tblname = tblname;
        this.primarykeycolumns = new LinkedList();
        this.primarykeycolumnnumbers = new LinkedList();
        this.boolcolumns = new LinkedList();

        int i;
        for(i = 0; i < names.size() && !tblname.equals("__UNASSIGNED") && constraints != null; ++i) {
            if (i < constraints.size() && ((String)constraints.get(i)).contains("PRIMARYKEY")) {
                this.primarykeycolumns.add((String)names.get(i));
            }

            if (sqltypes.size() > i && ((String)sqltypes.get(i)).contains("BOOL")) {
                System.out.println("Bool-Spalte gefunden");
                this.boolcolumns.add((String)names.get(i));
            }

            if (sqltypes.size() > i) {
                this.tooltiptypes.put((String)names.get(i), (String)sqltypes.get(i));
            }
        }

        if (tableconstraints != null) {
            label87:
            for(i = 0; i < tableconstraints.size(); ++i) {
                String constraint = (String)tableconstraints.get(i);
                Matcher m = Pattern.compile("PRIMARYKEY\\((.*?)\\)").matcher(constraint);

                while(true) {
                    while(true) {
                        if (!m.find()) {
                            continue label87;
                        }

                        String key = m.group(1);
                        System.out.println("Table Constraint Key " + key);
                        if (!key.contains(",")) {
                            this.primarykeycolumns.add(key);
                            int index = this.columnnames.indexOf(key);
                            if (index >= 0) {
                                this.primarykeycolumnnumbers.add(index);
                            }
                        } else {
                            String[] parts = key.split(",");
                            String[] var18 = parts;
                            int var17 = parts.length;

                            for(int var16 = 0; var16 < var17; ++var16) {
                                String c = var18[var16];
                                this.primarykeycolumns.add(c);
                                int index = this.columnnames.indexOf(c);
                                if (index >= 0) {
                                    this.primarykeycolumnnumbers.add(index);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (this.primarykeycolumns.size() == 1) {
            i = names.indexOf(this.primarykeycolumns.get(0));
            System.out.println("Primary key column :: " + i);
            this.primarykeycolumnnumbers.add(i);
            if (i >= 0 && ((String)sqltypes.get(i)).toUpperCase().equals("INTEGER") && !((String)constraints.get(i)).toUpperCase().contains("DESC")) {
                PrintStream var10000 = System.out;
                Object var10001 = names.get(i);
                var10000.println("Attention!!! integer primary key: " + (String)var10001);
                this.rowidcolumn = (String)names.get(i);
                pattern.change2RowID(i + 1);
                Logger.out.debug("set rowid column for table " + tblname + " to " + i);
                this.rowid_col = i;
            }
        }

        this.setHpattern(pattern);

        String var22;
        for(Iterator<String> iter = this.getColumntypes().iterator(); iter.hasNext(); this.fingerprint = var22 + this.fingerprint) {
            ++this.size;
            var22 = String.valueOf(this.regex);
            this.regex = var22 + this.getColumn((String)iter.next(), false);
            var22 = String.valueOf(this.fingerprint);
        }

    }

    public String getToolTypeForColumn(String column) {
        return (String)this.tooltiptypes.get(column);
    }

    public void setVirtual(boolean val) {
        this.virtual = val;
    }

    public boolean isVirtual() {
        return this.virtual;
    }

    public Pattern getStandardPattern() {
        String var10000 = Auxiliary.byteToHex((byte)(this.size + 1));
        return Pattern.compile((var10000 + this.regex).trim());
    }

    public Pattern getPatternWithoutHeaderLength() {
        return Pattern.compile(this.regex.trim());
    }

    public Pattern getPatternWithoutFirstCol() {
        return Pattern.compile(this.getPattern(1, this.size, false));
    }

    public String getPattern(int startcolumn, int endcolumn, boolean multicol) {
        String pat = "";

        for(int i = startcolumn; i < endcolumn; ++i) {
            pat = pat + this.getColumn((String)this.getColumntypes().get(i), multicol);
        }

        return pat;
    }

    public int getRootOffset() {
        return this.root;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public boolean matches(String signature) {
        return this.fingerprint.equals(signature);
    }

    public int getLength() {
        return 1 + this.size;
    }

    public int numberofColumns() {
        return this.getColumntypes().size();
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

    public Pattern[] getRegex() {
        Pattern[] headers = new Pattern[]{this.getStandardPattern(), this.getPatternWithoutHeaderLength(), this.getPatternMultiCol(), this.getPatternWithoutFirstCol()};
        return headers;
    }

    public void printTableDefinition() {
        System.out.println("TABLE" + this.tblname);
        System.out.println("COLUMNS: " + String.valueOf(this.columnnames));
    }

    public String toString() {
        Pattern[] elements = this.getRegex();
        String output = "";
        Pattern[] var6 = elements;
        int var5 = elements.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            Pattern s = var6[var4];
            output = output + String.valueOf(s) + "\n";
        }

        return output;
    }

    public List<String> getColumntypes() {
        return this.serialtypes;
    }

    public void setColumntypes(List<String> columntypes) {
        this.serialtypes = columntypes;
    }

    public List<String> getBoolColumns() {
        return this.boolcolumns;
    }

    public boolean isBoolColumn(String name) {
        return this.boolcolumns.contains(name);
    }

    public HeaderPattern getHpattern() {
        return this.hpattern;
    }

    public void setHpattern(HeaderPattern hpattern) {
        this.hpattern = hpattern;
    }

    public int compareTo(TableDescriptor o) {
        return this.tblname.compareTo(o.tblname);
    }

    public boolean equals(Object o) {
        return o instanceof TableDescriptor ? this.equals((TableDescriptor)o) : false;
    }

    public boolean equals(TableDescriptor o) {
        return this.tblname.equals(o.tblname);
    }
}
