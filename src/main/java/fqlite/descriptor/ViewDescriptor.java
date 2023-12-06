//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.descriptor;

import java.util.List;

public class ViewDescriptor extends AbstractDescriptor {
    public List<String> columntypes;
    public List<String> columnnames;
    public String viewname = "";

    public String getName() {
        return this.viewname;
    }

    public ViewDescriptor(String name, List<String> coltypes, List<String> names) {
        this.viewname = name;
        this.setColumntypes(coltypes);
        this.columnnames = names;
    }

    public int numberofColumns() {
        return this.getColumntypes().size();
    }

    public void printTableDefinition() {
        System.out.println("TABLE" + this.viewname);
        System.out.println("COLUMNS: " + String.valueOf(this.columnnames));
    }

    public List<String> getColumntypes() {
        return this.columntypes;
    }

    public void setColumntypes(List<String> columntypes) {
        this.columntypes = columntypes;
    }
}
