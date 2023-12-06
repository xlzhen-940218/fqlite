package fqlite.ui;


import fqlite.base.GUI;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.JLabel;

/* loaded from: fqlite_next.jar:fqlite/ui/WALPropertyPanel.class */
public class WALPropertyPanel extends StackPane {
    public JLabel ldbpath;
    public JLabel lpagesize;
    public JLabel lencoding;
    public JLabel ltotalsize;
    public JLabel lpagesizeout;
    public JLabel lencodingout;
    public JLabel ltotalsizeout;
    private FileInfo info;
    GUI gui;
    static int cl = 0;
    TabPane tabpane = new TabPane();
    VBox container = new VBox();
    String[] bgcolors = {"-fx-background-color: orange;", "-fx-background-color: yellow;", "-fx-background-color: lightblue;", "-fx-background-color: green;"};

    public WALPropertyPanel(FileInfo info, GUI gui) {
        this.info = info;
        this.gui = gui;
        this.container.setPrefHeight(4000.0d);
        this.container.getChildren().add(this.tabpane);
        this.container.getChildren().add(new Label("WAL archive"));
        VBox.setVgrow(this.tabpane, Priority.ALWAYS);
        getChildren().add(this.container);
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
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() { // from class: fqlite.ui.WALPropertyPanel.1
                @Override // javafx.util.Callback
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            if (i == 1) {
                col.prefWidthProperty().bind(table.widthProperty().multiply(0.3d));
            }
            if (i == 2) {
                col.prefWidthProperty().bind(table.widthProperty().multiply(0.5d));
            }
            table.getColumns().add(col);
        }
        fillTable(table, data);
        StackPane fields = new StackPane();
        fields.getChildren().add(table);
        Tab headerfieldstab = new Tab("Write Ahead Log Header", fields);
        this.tabpane.getTabs().add(headerfieldstab);
    }

    private void fillTable(TableView table, String[][] data) {
        ObservableList<ObservableList> obdata = FXCollections.observableArrayList();
        for (int i = 1; i < data.length; i++) {
            String[] s = data[i];
            ObservableList<String> row = FXCollections.observableArrayList();
            row.addAll(s);
            obdata.add(row);
        }
        Platform.runLater(() -> {
            table.setItems(obdata);
        });
    }

    public void initCheckpointTable(String[][] data) {
        String[] column = {"salt1", "salt2", "framenumber", "pagenumber", "commit"};
        TableView table = new TableView();
        for (int i = 0; i < column.length; i++) {
            String colname = column[i];
            final int j = i;
            TableColumn col = new TableColumn(colname);
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() { // from class: fqlite.ui.WALPropertyPanel.2
                @Override // javafx.util.Callback
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            table.setRowFactory(tv -> {
                return new TableRow<ObservableList<String>>() { // from class: fqlite.ui.WALPropertyPanel.3
                    @Override // javafx.scene.control.Cell
                    public void updateItem(ObservableList<String> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setStyle(ButtonBar.BUTTON_ORDER_NONE);
                            return;
                        }
                        String salt1 = item.get(0);
                        if (!WALPropertyPanel.this.gui.getRowcolors().containsKey(salt1)) {
                            WALPropertyPanel.this.gui.getRowcolors().put(salt1, WALPropertyPanel.this.bgcolors[WALPropertyPanel.cl % WALPropertyPanel.this.bgcolors.length]);
                            WALPropertyPanel.cl++;
                        }
                        setStyle(WALPropertyPanel.this.gui.getRowcolors().get(salt1));
                    }
                };
            });
            table.getColumns().add(col);
        }
        fillTable(table, data);
        StackPane fields = new StackPane();
        fields.getChildren().add(table);
        Tab checkpointtab = new Tab("Checkpoints", fields);
        this.tabpane.getTabs().add(checkpointtab);
    }
}
