package fqlite.ui;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:fqlite/ui/RollbackPropertyPanel.class */
public class RollbackPropertyPanel extends StackPane {
    private FileInfo info;
    TabPane tabpane = new TabPane();

    public RollbackPropertyPanel(FileInfo info) {
        this.info = info;
        new StackPane();
        getChildren().add(this.tabpane);
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
        String[] column = {"Offset", "Property", "Value"};
        TableView table = new TableView();
        for (int i = 0; i < column.length; i++) {
            String colname = column[i];
            final int j = i;
            TableColumn col = new TableColumn(colname);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() { // from class: fqlite.ui.RollbackPropertyPanel.1
                @Override // javafx.util.Callback
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            table.getColumns().add(col);
        }
        fillTable(table, data);
        StackPane fields = new StackPane();
        fields.getChildren().add(table);
        Tab headerfieldstab = new Tab("Rollback Journal Header", fields);
        this.tabpane.getTabs().add(headerfieldstab);
    }

    private void fillTable(TableView table, String[][] data) {
        ObservableList<ObservableList> obdata = FXCollections.observableArrayList();
        for (int i = 1; i < data.length; i++) {
            String[] s = data[i];
            ObservableList<String> row = FXCollections.observableArrayList();
            if (i == 1 && s[2].equals("0")) {
                s[2] = " 0 -> zero padded header (transaction committed)";
            }
            row.addAll(s);
            obdata.add(row);
        }
        Platform.runLater(() -> {
            table.setItems(obdata);
        });
    }
}
