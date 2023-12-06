package fqlite.ui;

import fqlite.analyzer.BPListParser;
import fqlite.analyzer.BinaryLoader;
import fqlite.base.GUI;
import fqlite.base.Global;
import fqlite.base.Job;
import fqlite.base.Protoc;
import fqlite.descriptor.TableDescriptor;
import fqlite.util.Auxiliary;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Cell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import org.apache.commons.codec.binary.Base64;

/* loaded from: fqlite_next.jar:fqlite/ui/TooltippedTableCell.class */
public class TooltippedTableCell<S, T> extends TableCell<S, T> {
    private String tablename;
    private Job job;
    private ObjectProperty<StringConverter<T>> converter;

    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn(String tablename, Job job) {
        return forTableColumn(new DefaultStringConverter(), tablename, job);
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(StringConverter<T> converter, String tablename, Job job) {
        return tableColumn -> {
            return new TooltippedTableCell(converter, tablename, job);
        };
    }

    public static <S> Callback<TableColumn<S, String>, TableCell<S, String>> forTableColumn() {
        return forTableColumn(new DefaultStringConverter());
    }

    public static <S, T> Callback<TableColumn<S, T>, TableCell<S, T>> forTableColumn(StringConverter<T> converter) {
        return tableColumn -> {
            return new TooltippedTableCell(converter);
        };
    }

    private static <T> String getItemText(Cell<T> cell, StringConverter<T> converter) {
        return converter == null ? cell.getItem() == null ? ButtonBar.BUTTON_ORDER_NONE : cell.getItem().toString() : converter.toString(cell.getItem());
    }

    public void setTablename(String name) {
        this.tablename = name;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    private void updateItem(Cell<T> cell, StringConverter<T> converter) {
        String firstpart;
        Tooltip tooltip;
        Tooltip tooltip2;
        TableRow tr = null;
        if (cell.isEmpty()) {
            cell.setText(null);
            cell.setTooltip(null);
        } else if (getTableColumn().getText().equals(ButtonBar.BUTTON_ORDER_NONE)) {
            Tooltip tooltip3 = new Tooltip("state (deleted, updated ...) \n empty .. regular dataset");
            cell.setTooltip(tooltip3);
            cell.setText(getItemText(cell, converter));
        } else if (getTableColumn().getText().equals("Offset")) {
            Tooltip tooltip4 = new Tooltip("byte position");
            cell.setTooltip(tooltip4);
            cell.setText(getItemText(cell, converter));
        } else if (getTableColumn().getText().trim().equals("PLL")) {
            Tooltip tooltip5 = new Tooltip("payload length");
            cell.setTooltip(tooltip5);
            cell.setText(getItemText(cell, converter));
        } else if (getTableColumn().getText().trim().equals("HL")) {
            Tooltip tooltip6 = new Tooltip("header length");
            cell.setTooltip(tooltip6);
            cell.setText(getItemText(cell, converter));
        } else {
            String s = getItemText(cell, converter);
            cell.setText(s);
            String tttype = null;
            for (TableDescriptor td : this.job.headers) {
                if (td.tblname.equals(this.tablename)) {
                    tttype = td.getToolTypeForColumn(getTableColumn().getText());
                }
            }
            if (tttype != null) {
                tttype = tttype.toUpperCase();
            }
            if (tttype == null) {
                tttype = ButtonBar.BUTTON_ORDER_NONE;
            }
            if (tttype.equals("REAL") || tttype.equals("DOUBLE") || tttype.equals("FLOAT")) {
                String bb = (String) cell.getItem();
                int point = bb.indexOf(",");
                if (point > 0) {
                    firstpart = bb.substring(0, point);
                } else {
                    firstpart = bb;
                }
                String value = Auxiliary.int2Timestamp(firstpart);
                Tooltip tooltip7 = new Tooltip("[" + tttype + "] " + bb + "\n" + value);
                cell.setTooltip(tooltip7);
            } else if (tttype.equals("INTEGER") || tttype.equals("INT")) {
                String bb2 = (String) cell.getItem();
                String value2 = Auxiliary.int2Timestamp(bb2);
                Tooltip tooltip8 = new Tooltip("[" + tttype + "] " + bb2 + "\n" + value2);
                cell.setTooltip(tooltip8);
            } else if (s.contains("[BLOB")) {
                int row = -1;
                try {
                    tr = getTableRow();
                } catch (Exception e) {
                }
                if (tr == null) {
                    return;
                }
                row = tr.getIndex();
                ObservableList<String> hl = (ObservableList) getTableView().getItems().get(row);
                int from = s.indexOf("BLOB-");
                int to = s.indexOf("]");
                String number = s.substring(from + 5, to);
                Integer.parseInt(number);
                Long hash = Long.valueOf(Long.parseLong(hl.get(5)));
                String shash = String.valueOf(hash) + "-" + number;
                Image ii = this.job.Thumbnails.get(shash);
                if (ii != null) {
                    Tooltip tooltip9 = new Tooltip();
                    ImageView iv = new ImageView(ii);
                    tooltip9.setGraphic(iv);
                    cell.setTooltip(tooltip9);
                } else if (s.contains("plist")) {
                    long off = Long.parseLong(hl.get(5));
                    String valueOf = String.valueOf(GUI.baseDir);
                    String path = valueOf + Global.separator + this.job.filename + "_" + off + "-" + valueOf + ".plist";
                    String plist = BPListParser.parse(path);
                    if (plist.length() > 5000) {
                        plist = plist.substring(0, 5000);
                    }
                    Tooltip tooltip10 = new Tooltip(plist);
                    tooltip10.setWrapText(true);
                    tooltip10.prefWidthProperty().bind(cell.widthProperty());
                    cell.setTooltip(tooltip10);
                } else if (this.job.inspectProtoBuffer.contains(this.tablename)) {
                    long off2 = Long.parseLong(hl.get(5));
                    String valueOf2 = String.valueOf(GUI.baseDir);
                    String path2 = valueOf2 + Global.separator + this.job.filename + "_" + off2 + "-" + valueOf2 + ".bin";
                    String buffer = Protoc.decode(path2);
                    Tooltip tooltip11 = new Tooltip(buffer);
                    tooltip11.setWrapText(true);
                    tooltip11.prefWidthProperty().bind(cell.widthProperty());
                    cell.setTooltip(tooltip11);
                } else {
                    long off3 = Long.parseLong(hl.get(5));
                    String z = ".bin";
                    if (s.contains("<tiff>")) {
                        z = ".tiff";
                    } else if (s.contains("<pdf>")) {
                        z = ".pdf";
                    } else if (s.contains("<heic>")) {
                        z = ".heic";
                    } else if (s.contains("<gzip>")) {
                        z = ".gzip";
                    }
                    String valueOf3 = String.valueOf(GUI.baseDir);
                    String path3 = valueOf3 + Global.separator + this.job.filename + "_" + off3 + "-" + valueOf3 + number;
                    String text = BinaryLoader.parse(path3);
                    if (text.length() > 5000) {
                        text = text.substring(0, 5000);
                    }
                    Tooltip tooltip12 = new Tooltip(text);
                    tooltip12.setWrapText(true);
                    tooltip12.prefWidthProperty().bind(cell.widthProperty());
                    if (tttype != null) {
                        cell.setTooltip(tooltip12);
                    }
                    ImageView iv2 = new ImageView(GUI.class.getResource("/hex-32.png").toExternalForm());
                    tooltip12.setGraphic(iv2);
                }
            } else {
                String bb3 = (String) cell.getItem();
                if (this.job.timestamps.containsKey(bb3)) {
                    Object value3 = this.job.timestamps.get(bb3);
                    new Tooltip("[" + tttype + "] " + String.valueOf(value3));
                    return;
                }
                if (this.job.inspectBASE64.contains(this.tablename)) {
                    boolean isBase64 = Base64.isBase64(s);
                    if (isBase64 && s.length() > 2) {
                        try {
                            byte[] decodedBytes = java.util.Base64.getDecoder().decode(s);
                            String decodedString = new String(decodedBytes);
                            if (decodedString != null && decodedString.length() > 0) {
                                tooltip2 = new Tooltip(decodedString);
                            } else {
                                tooltip2 = new Tooltip("[" + tttype + "] " + s);
                            }
                            tooltip2.setWrapText(true);
                            tooltip2.prefWidthProperty().bind(cell.widthProperty());
                            cell.setTooltip(tooltip2);
                            return;
                        } catch (Exception e2) {
                        }
                    }
                }
                if (tttype == null || tttype == ButtonBar.BUTTON_ORDER_NONE) {
                    tooltip = new Tooltip(getItemText(cell, converter));
                } else {
                    tooltip = new Tooltip("[" + tttype + "] " + getItemText(cell, converter));
                }
                tooltip.setWrapText(true);
                tooltip.prefWidthProperty().bind(cell.widthProperty());
                cell.setTooltip(tooltip);
            }
        }
    }

    public TooltippedTableCell() {
        this(null);
    }

    public TooltippedTableCell(StringConverter<T> converter) {
        this.tablename = null;
        this.job = null;
        this.converter = new SimpleObjectProperty(this, "converter");
        getStyleClass().add("tooltipped-table-cell");
        setConverter(converter);
    }

    public TooltippedTableCell(StringConverter<T> converter, String tablename, Job job) {
        this.tablename = null;
        this.job = null;
        this.converter = new SimpleObjectProperty(this, "converter");
        getStyleClass().add("tooltipped-table-cell");
        setConverter(converter);
        setTablename(tablename);
        setJob(job);
    }

    public final ObjectProperty<StringConverter<T>> converterProperty() {
        return this.converter;
    }

    public final void setConverter(StringConverter<T> value) {
        converterProperty().set(value);
    }

    public final StringConverter<T> getConverter() {
        return converterProperty().get();
    }

    @Override // javafx.scene.control.Cell
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        updateItem(this, getConverter());
    }
}
