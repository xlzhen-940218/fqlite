//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.parser;

import fqlite.base.Job;
import fqlite.descriptor.IndexDescriptor;
import fqlite.descriptor.TableDescriptor;
import fqlite.util.Logger;
import java.util.ArrayList;

public class SQLiteSchemaParser {
    public static ArrayList<Integer> roots = new ArrayList();

    public SQLiteSchemaParser() {
    }

    public static void parse(Job job, String tablename, int root, String sql) {
        boolean rowid = true;
        int indexrowid = sql.indexOf("WITHOUT ROWID");
        if (indexrowid != -1) {
            Logger.out.debug(" attention: component " + tablename + " is defined as WITHOUT ROWID");
            rowid = false;
        }

        roots.add(root);
        TableParser p = new TableParser();
        TableDescriptor tds;
        if (sql.contains("VIRTUAL TABLE")) {
            tds = p.parseCREATETABLEStatement(sql);
            if (tds.isVirtual()) {
                job.virtualTables.put(tds.tblname, tds);
            }

            if (!job.headers.contains(tds)) {
                job.headers.add(tds);
            }
        }

        if (sql.contains("CREATE TABLE")) {
            tds = p.parseCREATETABLEStatement(sql);
            if (tds.isVirtual()) {
                job.virtualTables.put(tds.tblname, tds);
            }

            if (tds != null) {
                Logger.out.debug(tds.getStandardPattern().toString());
                tds.tblname = tablename;
                tds.ROWID = rowid;
                if (!job.headers.contains(tds)) {
                    job.headers.add(tds);
                    tds.root = root;
                } else {
                    System.out.println("Aussortiert ???" + tablename + " tblname " + tds.tblname);
                    System.out.println(job.headers);
                }
            }
        } else {
            IndexDescriptor ids;
            if (sql.contains("CREATE INDEX")) {
                ids = p.parseCREATEIndexStatement(sql);
                if (ids.idxname == null) {
                    return;
                }

                if (!job.indices.contains(ids)) {
                    job.indices.add(ids);
                    ids.root = root;
                }
            } else if (sql.contains("CREATE UNIQUE INDEX")) {
                sql = sql.replace("CREATE UNIQUE INDEX", "CREATE INDEX");
                ids = p.parseCREATEIndexStatement(sql);
                if (ids.idxname == null) {
                    return;
                }

                if (!job.indices.contains(ids)) {
                    job.indices.add(ids);
                    ids.root = root;
                }
            }
        }

    }
}
