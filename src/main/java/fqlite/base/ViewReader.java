//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ViewReader {
    private Connection conn;

    public ViewReader() {
    }

    public void connect() throws ClassNotFoundException {
        try {
            String url = "jdbc:sqlite:/Users/pawlaszc/test.db";
            this.conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            this.createStatement();
        } catch (SQLException var10) {
            System.out.println(var10.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    this.conn.close();
                }
            } catch (SQLException var9) {
                System.out.println(var9.getMessage());
            }

        }

    }

    public void createStatement() {
        try {
            Statement statement = this.conn.createStatement();
            statement.setQueryTimeout(30);
            String sql = " SELECT name FROM sqlite_master WHERE type ='view' AND name NOT LIKE 'sqlite_%'";
            ResultSet rs = statement.executeQuery(sql);
            List<String> names = new LinkedList();

            while(rs.next()) {
                String vn = rs.getString(1);
                names.add(vn);
            }

            rs.close();
            Iterator<String> view_names = names.iterator();

            while(view_names.hasNext()) {
                String name = (String)view_names.next();
                System.out.println(name);
                String sql2 = "SELECT * FROM " + name + ";";
                ResultSet rs2 = statement.executeQuery(sql2);
                ResultSetMetaData rsMetaData = rs2.getMetaData();
                System.out.println("List of column names in the current table: ");
                int count = rsMetaData.getColumnCount();
                List<String> coltypes = new LinkedList();
                List<String> colnames = new LinkedList();

                int i;
                for(i = 1; i <= count; ++i) {
                    PrintStream var10000 = System.out;
                    String var10001 = rsMetaData.getColumnName(i);
                    var10000.println("column " + var10001 + " type " + rsMetaData.getColumnTypeName(i));
                    colnames.add(name);
                    String ct = rsMetaData.getColumnTypeName(i);
                    coltypes.add(ct);
                }

                while(rs2.next()) {
                    for(i = 1; i <= count; ++i) {
                        System.out.println(" " + rs2.getRow());
                        System.out.print(rs2.getString(i));
                    }
                }

                rs2.close();
            }
        } catch (SQLException var15) {
            var15.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            ViewReader v = new ViewReader();
            v.connect();
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        }

    }
}
