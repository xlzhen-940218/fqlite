//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.parser;

import fqlite.descriptor.IndexDescriptor;
import fqlite.descriptor.TableDescriptor;

public class TableParser {
    public TableParser() {
    }

    public TableDescriptor parseCREATETABLEStatement(String stmt) {
        SimpleSQLiteParser parser = new SimpleSQLiteParser();
        TableDescriptor tds = parser.parseTable(stmt);
        return tds;
    }

    public IndexDescriptor parseCREATEIndexStatement(String stmt) {
        SimpleSQLiteParser parser = new SimpleSQLiteParser();
        IndexDescriptor idx = parser.parseIndex(stmt);
        return idx;
    }

    public static void main(String[] args) {
        String stmt = "CREATE TABLE 'users' (\n      'id' INTEGER,\n      'name' TEXT,\n      'surname' TEXT,\n      'zip' INTEGER,\n      CONSTRAINT constName PRIMARY KEY (id) UNIQUE(name));";
        TableParser p = new TableParser();
        p.parseCREATETABLEStatement(stmt);
    }
}
