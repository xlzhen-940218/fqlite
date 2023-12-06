//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.ui;

import fqlite.base.GUI;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DBPropertyPanel extends StackPane {
    public Label ldbpath;
    public Label lpagesize;
    public Label lencoding;
    public Label ltotalsize;
    public Label lpagesizeout;
    public Label lencodingout;
    public Label ltotalsizeout;
    private FileInfo info;
    public Button columnBtn;
    public String columnStr = "";
    TabPane tabpane = new TabPane();

    public DBPropertyPanel(FileInfo info, String fname) {
        this.info = info;
        VBox base = new VBox();
        String s = GUI.class.getResource("/find.png").toExternalForm();
        Button btnSchema = new Button("Show Schema Info");
        ImageView iv = new ImageView(s);
        btnSchema.setGraphic(iv);
        btnSchema.setOnAction((e) -> {
            this.showColumnInfo();
        });
        StackPane head = new StackPane();
        head.getChildren().add(btnSchema);
        base.getChildren().addAll(new Node[]{head, this.tabpane, new Label(fname)});
        this.tabpane.setPrefHeight(4000.0);
        VBox.setVgrow(this.tabpane, Priority.ALWAYS);
        this.getChildren().add(base);
    }

    public void initHeaderTable(String[][] data) {
        TextArea headerinfo = new TextArea();
        headerinfo.setEditable(false);
        headerinfo.setStyle("-fx-font-alignment: center");
        headerinfo.setText(this.info.toString());
        StackPane sp = new StackPane();
        sp.getChildren().add(headerinfo);
        Tab headerinfotab = new Tab("File Info", sp);
        this.tabpane.getTabs().add(headerinfotab);
        String[] column = new String[]{"Offset", "Property", "Value"};
        TableView table = new TableView();

        for(int i = 0; i < column.length; ++i) {
            String colname = column[i];
            TableColumn col = new TableColumn(colname);
            int finalI = i;
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(finalI).toString());
                }
            });
            switch (i) {
                case 1:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
                    break;
                case 2:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
            }

            table.getColumns().add(col);
        }

        this.fillTable(table, data, false);
        StackPane fields = new StackPane();
        fields.getChildren().add(table);
        Tab headerfieldstab = new Tab("Header Fields", fields);
        this.tabpane.getTabs().add(headerfieldstab);
    }

    public void initColumnTypesTable(LinkedHashMap<String, String[][]> ht) {
        String str = "<!DOCTYPE html><html> <head> <title>" + this.info.filename + " - Schema Information</title> <style type=\"text/css\">\n  table, td, tr { border:1px solid black;}\n </style> </head> <body><h1>Schema Information for database: " + this.info.filename + "</h1>";
        str = str + "<a id=\"top\"></a>";
        str = str + "<br />";
        str = str + "<hr>";
        str = str + "<h2> TABLES </h2>";
        str = str + "<hr>";
        boolean firstindex = true;
        Iterator var5 = ht.entrySet().iterator();

        Map.Entry entry;
        String tname;
        while(var5.hasNext()) {
            entry = (Map.Entry)var5.next();
            tname = (String)entry.getKey();
            if (!tname.startsWith("__")) {
                if (firstindex && tname.startsWith("idx:")) {
                    str = str + "<br />";
                    str = str + "<hr>";
                    str = str + "<h2> INDICES </h2>";
                    str = str + "<hr>";
                    firstindex = false;
                }

                str = str + "<a href=\"#" + tname + "\">" + tname + "</a><br />";
            }
        }

        str = str + "<br />";
        str = str + "<hr>";
        str = str + "<br />";
        var5 = ht.entrySet().iterator();

        while(true) {
            do {
                if (!var5.hasNext()) {
                    str = str + "</body></html>";
                    this.columnStr = str;
                    return;
                }

                entry = (Map.Entry)var5.next();
                tname = (String)entry.getKey();
            } while(tname.startsWith("__"));

            str = str + "<a id=\"" + tname + "\"></a>";
            str = str + "<p>";
            String bgcolor = "#00008b";
            if (tname.startsWith("idx:")) {
                bgcolor = "#DF0101";
            }

            String[][] tab = (String[][])entry.getValue();
            int cols = tab[0].length;
            str = str + "<table rules=groups>";
            str = str + "<thead bgcolor=" + bgcolor + " style=\"color:white;white-space:nowrap;width:100%;\" bordercolor=#000099>";
            str = str + "<tr>";
            if (tname.startsWith("idx:")) {
                str = str + "<th>INDEX </th><th>\"" + tname + "\"</th>";
            } else {
                str = str + "<th>TABLE\t </th><th>\"" + tname + "\"</th>";
            }

            int i;
            for(i = 0; i < cols - 1; ++i) {
                str = str + "<th></th>";
            }

            str = str + "</thead>";
            str = str + "</tr>";
            str = str + "<tbody>";

            for(i = 0; i < tab.length; ++i) {
                str = str + "<tr>";
                switch (i) {
                    case 0:
                        str = str + "<td> <b> column name </b> </td>";
                        break;
                    case 1:
                        str = str + "<td> <b> serialtype </b> </td>";
                        break;
                    case 2:
                        str = str + "<td> <b> sqltype </b> </td>";
                        break;
                    case 3:
                        str = str + "<td> <b> column constraints </b> </td>";
                        break;
                    case 4:
                        str = str + "<td> <b> table constraint </b> </td>";
                        break;
                    default:
                        str = str + "<td>  </td>";
                }

                for(int j = 0; j < tab[i].length; ++j) {
                    str = str + "<td>" + tab[i][j] + "</td>";
                }

                str = str + "</tr>";
            }

            str = str + "</tbody>";
            str = str + "</table>";
            str = str + "<a href=\"#top\">[TOP]</a><br/>";
            str = str + "</p>";
        }
    }

    public void showColumnInfo() {
        Stage secondStage = new Stage();
        Scene scene = new Scene(new SchemaBrowser(this.columnStr), 750.0, 500.0, Color.web("#666970"));
        secondStage.setTitle("Schema Info");
        secondStage.setScene(scene);
        secondStage.show();
    }

    public void initSchemaTable(String[][] data) {
        String[] column = new String[]{"No.", "Type", "Tablename", "Root", "SQL-Statement", "Virtual", "ROWID"};
        TableView table = new TableView();

        for(int i = 0; i < column.length; ++i) {
            String colname = column[i];
            TableColumn col = new TableColumn(colname);
            int finalI = i;
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(finalI) != null ? ((ObservableList)param.getValue()).get(finalI).toString() : "");
                }
            });
            switch (i) {
                case 0:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
                    break;
                case 1:
                case 3:
                default:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
                    break;
                case 2:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
                    break;
                case 4:
                    col.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
            }

            table.getColumns().add(col);
        }

        if (data != null) {
            this.fillTable(table, data, true);
        }

        Tab schematab = new Tab("SQL-Schema", table);
        this.tabpane.getTabs().add(schematab);
    }

    private void fillTable(TableView table, String[][] data, boolean rowno) {
        ObservableList<ObservableList> obdata = FXCollections.observableArrayList();
        int rownumber = 1;

        for(int i = 0; i < data.length; ++i) {
            String[] s = data[i];
            ObservableList<String> row = FXCollections.observableArrayList();
            if (rowno) {
                row.add("" + rownumber);
            }

            row.addAll(s);
            obdata.add(row);
            ++rownumber;
        }

        Platform.runLater(() -> {
            table.setItems(obdata);
        });
    }
}
