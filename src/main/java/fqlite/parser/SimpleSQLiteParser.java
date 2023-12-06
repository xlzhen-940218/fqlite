//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.parser;

import fqlite.descriptor.IndexDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.pattern.HeaderPattern;
import fqlite.pattern.IntegerConstraint;
import fqlite.util.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SimpleSQLiteParser {
    static String[] inttypes = new String[]{"INT", "INTEGER", "INTUNSIGNED", "INTSIGNED", "LONG", "TINYINT", "SMALLINT", "MEDIUMINT", "BIGINT", "UNSIGNEDBIGINT", "INT2", "INT8"};
    static String[] texttypes = new String[]{"TEXT", "CHARACTER", "CLOB", "VARCHAR", "VARYINGCHARACTER", "NCHAR", "NATIVE CHARACTER", "NVARCHAR"};
    static String[] blobtype = new String[]{"BLOB"};
    static String[] realtype = new String[]{"REAL", "DOUBLE", "DOUBLEPRECISION", "FLOAT"};
    static String[] numerictype = new String[]{"NUMERIC", "DECIMAL", "BOOLEAN", "DATE", "DATETIME"};
    SQLiteLexer lexer;
    SQLiteLexer parser;
    String tablename = null;
    String modulname = null;
    List<String> coltypes = new ArrayList();
    List<String> sqltypes = new ArrayList();
    List<String> colconstraints = new ArrayList();
    ArrayList<String> colnames = new ArrayList();
    List<String> tableconstraint = new ArrayList();
    Map<Integer, String> constraints = new HashMap();
    HeaderPattern pattern = new HeaderPattern();
    TableDescriptor tds = null;
    IndexDescriptor ids = null;
    int column;
    String idxname;

    public SimpleSQLiteParser() {
    }

    public TableDescriptor parseTable(String stmt) {
        if (stmt.indexOf("CREATE TABLE") >= 0) {
            return this.parseCreateTable(stmt);
        } else {
            if (stmt.indexOf("CREATE TEMP TABLE") >= 0) {
                Logger.out.debug("Found CREATE TEMP TABLE statement");
            } else if (stmt.indexOf("CREATE VIRTUAL TABLE") >= 0) {
                return this.parseCreateVirtualTable(stmt);
            }

            return null;
        }
    }

    public IndexDescriptor parseIndex(String stmt) {
        return stmt.indexOf("CREATE INDEX ") >= 0 ? this.parseCreateIndex(stmt) : null;
    }

    public String trim(String value) {
        if (value.startsWith("'") || value.startsWith("\"")) {
            value = value.substring(1);
            if (value.endsWith("'") || value.endsWith("\"")) {
                value = value.substring(0, value.length() - 1);
            }
        }

        return value;
    }

    private TableDescriptor parseCreateVirtualTable(String stmt) {
        this.column = 0;
        this.modulname = null;
        SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(stmt));
        SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.create_virtual_table_stmt();
        ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {
            public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
                SimpleSQLiteParser.this.tablename = ctx.getText();
                SimpleSQLiteParser.this.tablename = SimpleSQLiteParser.this.trim(SimpleSQLiteParser.this.tablename);
                if (SimpleSQLiteParser.this.tablename.length() == 0) {
                    SimpleSQLiteParser.this.tablename = "<no name>";
                }

                System.out.println("Tablename " + SimpleSQLiteParser.this.tablename);
            }

            public void enterModule_name(SQLiteParser.Module_nameContext ctx) {
                SimpleSQLiteParser.this.modulname = ctx.getText();
                System.out.println(" name " + SimpleSQLiteParser.this.modulname);
                if (!SimpleSQLiteParser.this.modulname.equals("fts4") && !SimpleSQLiteParser.this.modulname.equals("fts3")) {
                    if (SimpleSQLiteParser.this.modulname.equals("rtree")) {
                        System.out.println("Found rtree Module");
                    }
                } else {
                    System.out.println("Found FreeTextSearch Module (fts3/4)");
                }

            }

            public void enterModule_argument(SQLiteParser.Module_argumentContext ctx) {
                String modulargument = ctx.getText();
                System.out.println(" arg " + modulargument);
                if (SimpleSQLiteParser.this.modulname.equals("rtree")) {
                    SimpleSQLiteParser.this.colnames.add(modulargument);
                    if (SimpleSQLiteParser.this.column == 0) {
                        SimpleSQLiteParser.this.coltypes.add("INT");
                    } else {
                        SimpleSQLiteParser.this.coltypes.add("NUMERIC");
                    }

                    ++SimpleSQLiteParser.this.column;
                }

            }
        }, tree);
        this.tds = new TableDescriptor(this.tablename, stmt, this.sqltypes, this.coltypes, this.colnames, this.colconstraints, this.tableconstraint, (HeaderPattern)null, stmt.contains("WITHOUT ROWID"));
        this.tds.setVirtual(true);
        if (this.modulname != null) {
            this.tds.setModulname(this.modulname);
        }

        return this.tds;
    }

    private IndexDescriptor parseCreateIndex(String stmt) {
        this.column = 0;
        SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(stmt));
        SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.create_index_stmt();
        ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {
            public void enterIndex_name(SQLiteParser.Index_nameContext ctx) {
                SimpleSQLiteParser.this.idxname = ctx.getText();
                System.out.println("INDEX " + SimpleSQLiteParser.this.idxname);
            }

            public void enterIndexed_column(SQLiteParser.Indexed_columnContext ctx) {
                String colname = ctx.getText();
                colname = SimpleSQLiteParser.this.trim(colname);
                SimpleSQLiteParser.this.colnames.add(colname);
                System.out.println("Columnname " + colname);
            }

            public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
                SimpleSQLiteParser.this.tablename = ctx.getText();
                SimpleSQLiteParser.this.tablename = SimpleSQLiteParser.this.trim(SimpleSQLiteParser.this.tablename);
                if (SimpleSQLiteParser.this.tablename.length() == 0) {
                    SimpleSQLiteParser.this.tablename = "<no name>";
                }

                System.out.println("Tablename " + SimpleSQLiteParser.this.tablename);
            }
        }, tree);
        return new IndexDescriptor(this.idxname, this.tablename, stmt, this.colnames);
    }

    private TableDescriptor parseCreateTable(final String stmt) {
        this.column = 0;
        SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(stmt));
        SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.create_table_stmt();
        ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {
            boolean tblconstraint = false;
            boolean sqltypes_defined = false;
            boolean inForeignTable = false;
            String cons = "";
            boolean isTableConstraint = false;

            public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
                this.isTableConstraint = false;
                SimpleSQLiteParser.this.tablename = ctx.getText();
                SimpleSQLiteParser.this.tablename = SimpleSQLiteParser.this.trim(SimpleSQLiteParser.this.tablename);
                if (SimpleSQLiteParser.this.tablename.length() == 0) {
                    SimpleSQLiteParser.this.tablename = "<no name>";
                }

            }

            public void enterColumn_name(SQLiteParser.Column_nameContext ctx) {
                this.sqltypes_defined = false;
                if (this.inForeignTable) {
                    this.inForeignTable = false;
                    this.sqltypes_defined = true;
                } else if (!this.isTableConstraint) {
                    this.cons = "";
                    String colname = ctx.getText();
                    if (!colname.equals("CONSTRAINT")) {
                        colname = SimpleSQLiteParser.this.trim(colname);
                        SimpleSQLiteParser.this.colnames.add(colname);
                    } else {
                        this.tblconstraint = true;
                    }

                }
            }

            public void enterForeign_table(SQLiteParser.Foreign_tableContext ctx) {
                System.out.println("Enter foreign Table!!!");
                this.inForeignTable = true;
            }

            public void exitForeign_table(SQLiteParser.Foreign_tableContext ctx) {
                System.out.println("Exit foreign Table!!!");
            }

            public void enterType_name(SQLiteParser.Type_nameContext ctx) {
                String value = ctx.getText();
                value = value.trim();
                if (value.length() == 0) {
                    value = "BLOB";
                }

                if (this.tblconstraint) {
                    SimpleSQLiteParser.this.tableconstraint.add(value);
                    this.tblconstraint = false;
                } else {
                    SimpleSQLiteParser.this.sqltypes.add(value);
                    if (value.equalsIgnoreCase("PRIMARYKEY")) {
                        value = "BLOB";
                    }

                    String type = SimpleSQLiteParser.this.getType(value);
                    if (type.length() > 0) {
                        SimpleSQLiteParser.this.coltypes.add(type);
                    }
                }

                this.sqltypes_defined = true;
            }

            public void enterKeyword(SQLiteParser.KeywordContext ctx) {
                System.out.println("Enter keyword ");
            }

            public void exitKeyword(SQLiteParser.KeywordContext ctx) {
                System.out.println("Exit keyword ");
            }

            public void enterColumn_constraint(SQLiteParser.Column_constraintContext ctx) {
                String constraint = ctx.getText().toUpperCase();
                if (constraint.contains("NOTNULL")) {
                    SimpleSQLiteParser.this.constraints.put(SimpleSQLiteParser.this.column, constraint);
                }

            }

            public void exitColumn_constraint(SQLiteParser.Column_constraintContext ctx) {
                String constraint = ctx.getText();
                System.out.println("Columnconstraint " + constraint);
                String var10001 = String.valueOf(this.cons);
                this.cons = var10001 + constraint.toUpperCase() + " ";
            }

            public void exitColumn_def(SQLiteParser.Column_defContext ctx) {
                if (!this.sqltypes_defined) {
                    SimpleSQLiteParser.this.sqltypes.add("BLOB");
                    SimpleSQLiteParser.this.coltypes.add("BLOB");
                }

                SimpleSQLiteParser.this.colconstraints.add(this.cons);
                ++SimpleSQLiteParser.this.column;
            }

            public void enterTable_constraint(SQLiteParser.Table_constraintContext ctx) {
                this.isTableConstraint = true;
                SimpleSQLiteParser.this.tableconstraint.add(ctx.getText());
            }

            public void exitCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
                HeaderPattern pattern = new HeaderPattern();
                pattern.addHeaderConstraint(SimpleSQLiteParser.this.colnames.size() + 1, SimpleSQLiteParser.this.colnames.size() * 2);
                int cc;
                if (SimpleSQLiteParser.this.coltypes.size() == 0) {
                    for(cc = 0; cc < SimpleSQLiteParser.this.colnames.size(); ++cc) {
                        SimpleSQLiteParser.this.coltypes.add("BLOB");
                        SimpleSQLiteParser.this.sqltypes.add("BLOB");
                    }
                }

                if (SimpleSQLiteParser.this.coltypes.size() > SimpleSQLiteParser.this.sqltypes.size()) {
                    for(cc = 0; cc < SimpleSQLiteParser.this.colnames.size() - SimpleSQLiteParser.this.sqltypes.size(); ++cc) {
                        SimpleSQLiteParser.this.coltypes.add("BLOB");
                        SimpleSQLiteParser.this.sqltypes.add("BLOB");
                    }
                }

                cc = 0;

                for(ListIterator<String> list = SimpleSQLiteParser.this.coltypes.listIterator(); list.hasNext(); ++cc) {
                    switch ((String)list.next()) {
                        case "NUMERIC":
                            pattern.addNumericConstraint();
                            break;
                        case "INT":
                            if (SimpleSQLiteParser.this.constraints.containsKey(cc)) {
                                pattern.add(new IntegerConstraint(true));
                            } else {
                                pattern.add(new IntegerConstraint(false));
                            }
                            break;
                        case "BLOB":
                            pattern.addBLOBConstraint();
                            break;
                        case "REAL":
                            pattern.addFloatingConstraint();
                            break;
                        case "TEXT":
                            pattern.addStringConstraint();
                    }
                }

                SimpleSQLiteParser.this.tds = new TableDescriptor(SimpleSQLiteParser.this.tablename, stmt, SimpleSQLiteParser.this.sqltypes, SimpleSQLiteParser.this.coltypes, SimpleSQLiteParser.this.colnames, SimpleSQLiteParser.this.colconstraints, SimpleSQLiteParser.this.tableconstraint, pattern, stmt.contains("WITHOUT ROWID"));
                System.out.println("PATTTERN: " + String.valueOf(pattern));
            }
        }, tree);
        return this.tds;
    }

    private String getType(String s) {
        String type = "";
        s = s.toUpperCase();
        if (s.startsWith("TIMESTAMP")) {
            type = "NUMERIC";
        } else if (stringContainsItemFromList(s, texttypes)) {
            type = "TEXT";
        } else if (stringContainsItemFromList(s, inttypes)) {
            type = "INT";
        } else if (stringContainsItemFromList(s, blobtype)) {
            type = "BLOB";
        } else if (stringContainsItemFromList(s, realtype)) {
            type = "REAL";
        } else if (stringContainsItemFromList(s, numerictype)) {
            type = "NUMERIC";
        }

        return type;
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {

        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }

}
