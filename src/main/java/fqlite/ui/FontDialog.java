package fqlite.ui;

import fqlite.base.GUI;
import fqlite.base.Global;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

/* loaded from: fqlite_next.jar:fqlite/ui/FontDialog.class */
public class FontDialog extends Dialog<Font> {
    private FontPanel fontPanel = new FontPanel();
    private Font defaultFont;
    static Node root;

    public FontDialog(Font defaultFont, Node rootelement) {
        root = rootelement;
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return this.fontPanel.getFont();
            }
            return null;
        });
        DialogPane dialogPane = getDialogPane();
        setTitle("Select font");
        dialogPane.setHeaderText("Select font");
        String s = GUI.class.getResource("/icon_checked.png").toExternalForm();
        dialogPane.setGraphic(new ImageView(new Image(s)));
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialogPane.setContent(this.fontPanel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: fqlite_next.jar:fqlite/ui/FontDialog$FontStyle.class */
    public static class FontStyle implements Comparable<FontStyle> {
        private FontPosture posture;
        private FontWeight weight;

        public FontStyle(FontWeight weight, FontPosture posture) {
            this.posture = posture == null ? FontPosture.REGULAR : posture;
            this.weight = weight;
        }

        public FontStyle() {
            this(null, null);
        }

        public FontStyle(String styles) {
            this();
            String[] fontStyles = (styles == null ? ButtonBar.BUTTON_ORDER_NONE : styles.trim().toUpperCase()).split(Global.REGULAR_RECORD);
            for (String style : fontStyles) {
                FontWeight w = FontWeight.findByName(style);
                if (w != null) {
                    this.weight = w;
                } else {
                    FontPosture p = FontPosture.findByName(style);
                    if (p != null) {
                        this.posture = p;
                    }
                }
            }
        }

        public FontStyle(Font font) {
            this(font.getStyle());
        }

        public FontPosture getPosture() {
            return this.posture;
        }

        public FontWeight getWeight() {
            return this.weight;
        }

        public int hashCode() {
            int result = (31 * 1) + (this.posture == null ? 0 : this.posture.hashCode());
            return (31 * result) + (this.weight == null ? 0 : this.weight.hashCode());
        }

        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (that == null || getClass() != that.getClass()) {
                return false;
            }
            FontStyle other = (FontStyle) that;
            if (this.posture != other.posture || this.weight != other.weight) {
                return false;
            }
            return true;
        }

        private static String makePretty(Object o) {
            String s = o == null ? ButtonBar.BUTTON_ORDER_NONE : o.toString();
            if (!s.isEmpty()) {
                String s2 = s.replace("_", Global.REGULAR_RECORD);
                s = s2.substring(0, 1).toUpperCase() + s2.substring(1).toLowerCase();
            }
            return s;
        }

        public String toString() {
            return String.format("%s %s", makePretty(this.weight), makePretty(this.posture)).trim();
        }

        private <T extends Enum<T>> int compareEnums(T e1, T e2) {
            if (e1 == e2) {
                return 0;
            }
            if (e1 == null) {
                return -1;
            }
            if (e2 == null) {
                return 1;
            }
            return e1.compareTo(e2);
        }

        @Override // java.lang.Comparable
        public int compareTo(FontStyle fs) {
            int result = compareEnums(this.weight, fs.weight);
            return result != 0 ? result : compareEnums(this.posture, fs.posture);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: fqlite_next.jar:fqlite/ui/FontDialog$FontPanel.class */
    public static class FontPanel extends GridPane {
        private static final double HGAP = 10.0d;
        private static final double VGAP = 5.0d;
        private static final Predicate<Object> MATCH_ALL = new Predicate<Object>() { // from class: fqlite.ui.FontDialog.FontPanel.1
            @Override // java.util.function.Predicate
            public boolean test(Object t) {
                return true;
            }
        };
        private static final Double[] fontSizes = {Double.valueOf(8.0d), Double.valueOf(9.0d), Double.valueOf(11.0d), Double.valueOf(12.0d), Double.valueOf(14.0d), Double.valueOf(16.0d), Double.valueOf(18.0d), Double.valueOf(20.0d), Double.valueOf(22.0d), Double.valueOf(24.0d), Double.valueOf(26.0d), Double.valueOf(28.0d), Double.valueOf(36.0d), Double.valueOf(48.0d), Double.valueOf(72.0d)};
        private final FilteredList<String> filteredFontList = new FilteredList<>(FXCollections.observableArrayList(Font.getFamilies()), MATCH_ALL);
        private final FilteredList<FontStyle> filteredStyleList = new FilteredList<>(FXCollections.observableArrayList(), MATCH_ALL);
        private final FilteredList<Double> filteredSizeList = new FilteredList<>(FXCollections.observableArrayList(fontSizes), MATCH_ALL);
        private final ListView<String> fontListView = new ListView<>(this.filteredFontList);
        private final ListView<FontStyle> styleListView = new ListView<>(this.filteredStyleList);
        private final ListView<Double> sizeListView = new ListView<>(this.filteredSizeList);
        private final Text sample = new Text("��\n What is bear + 1 ? ��");

        private static List<FontStyle> getFontStyles(String fontFamily) {
            Set<FontStyle> set = new HashSet<>();
            for (String f : Font.getFontNames(fontFamily)) {
                set.add(new FontStyle(f.replace(fontFamily, ButtonBar.BUTTON_ORDER_NONE)));
            }
            List<FontStyle> result = new ArrayList<>(set);
            Collections.sort(result);
            return result;
        }

        public FontPanel() {
            setHgap(HGAP);
            setVgap(VGAP);
            setPrefSize(500.0d, 300.0d);
            setMinSize(500.0d, 300.0d);
            ColumnConstraints c0 = new ColumnConstraints();
            c0.setPercentWidth(60.0d);
            ColumnConstraints c1 = new ColumnConstraints();
            c1.setPercentWidth(25.0d);
            ColumnConstraints c2 = new ColumnConstraints();
            c2.setPercentWidth(15.0d);
            getColumnConstraints().addAll(c0, c1, c2);
            RowConstraints r0 = new RowConstraints();
            r0.setVgrow(Priority.NEVER);
            RowConstraints r1 = new RowConstraints();
            r1.setVgrow(Priority.NEVER);
            RowConstraints r2 = new RowConstraints();
            r2.setFillHeight(true);
            r2.setVgrow(Priority.NEVER);
            RowConstraints r3 = new RowConstraints();
            r3.setPrefHeight(250.0d);
            r3.setVgrow(Priority.NEVER);
            getRowConstraints().addAll(r0, r1, r2, r3);
            add(new Label("Font"), 0, 0);
            add(this.fontListView, 0, 1);
            this.fontListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() { // from class: fqlite.ui.FontDialog.FontPanel.2
                @Override // javafx.util.Callback
                public ListCell<String> call(ListView<String> listview) {
                    return new ListCell<String>() { // from class: fqlite.ui.FontDialog.FontPanel.2.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // javafx.scene.control.Cell
                        public void updateItem(String family, boolean empty) {
                            super.updateItem(family, empty);
                            if (!empty) {
                                setFont(Font.font(family));
                                setText(family);
                                return;
                            }
                            setText(null);
                        }
                    };
                }
            });
            ChangeListener<Object> sampleRefreshListener = new ChangeListener<Object>() { // from class: fqlite.ui.FontDialog.FontPanel.3
                @Override // javafx.beans.value.ChangeListener
                public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
                    FontPanel.this.refreshSample();
                }
            };
            this.fontListView.selectionModelProperty().get().selectedItemProperty().addListener(new ChangeListener<String>() { // from class: fqlite.ui.FontDialog.FontPanel.4
                @Override // javafx.beans.value.ChangeListener
                public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                    String fontFamily = (String) FontPanel.this.listSelection(FontPanel.this.fontListView);
                    FontPanel.this.styleListView.setItems(FXCollections.observableArrayList(FontPanel.getFontStyles(fontFamily)));
                    FontPanel.this.refreshSample();
                }
            });
            add(new Label("Style"), 1, 0);
            add(this.styleListView, 1, 1);
            this.styleListView.selectionModelProperty().get().selectedItemProperty().addListener(sampleRefreshListener);
            add(new Label("Size"), 2, 0);
            add(this.sizeListView, 2, 1);
            this.sizeListView.selectionModelProperty().get().selectedItemProperty().addListener(sampleRefreshListener);
            DoubleBinding sampleWidth = new DoubleBinding() { // from class: fqlite.ui.FontDialog.FontPanel.5
                {
                    bind(FontPanel.this.fontListView.widthProperty(), FontPanel.this.styleListView.widthProperty(), FontPanel.this.sizeListView.widthProperty());
                }

                @Override // javafx.beans.binding.DoubleBinding
                protected double computeValue() {
                    return FontPanel.this.fontListView.getWidth() + FontPanel.this.styleListView.getWidth() + FontPanel.this.sizeListView.getWidth() + 30.0d;
                }
            };
            StackPane sampleStack = new StackPane(this.sample);
            sampleStack.setAlignment(Pos.CENTER_LEFT);
            sampleStack.setMinHeight(45.0d);
            sampleStack.setPrefHeight(45.0d);
            sampleStack.setMaxHeight(45.0d);
            sampleStack.prefWidthProperty().bind(sampleWidth);
            Rectangle clip = new Rectangle(0.0d, 45.0d);
            clip.widthProperty().bind(sampleWidth);
            sampleStack.setClip(clip);
            add(sampleStack, 0, 3, 1, 3);
        }

        public void setFont(Font font) {
            Font _font = font == null ? Font.getDefault() : font;
            if (_font != null) {
                selectInList(this.fontListView, _font.getFamily());
                selectInList(this.styleListView, new FontStyle(_font));
                selectInList(this.sizeListView, Double.valueOf(_font.getSize()));
            }
        }

        public Font getFont() {
            try {
                FontStyle style = (FontStyle) listSelection(this.styleListView);
                if (style == null) {
                    Font f = Font.font((String) listSelection(this.fontListView), ((Double) listSelection(this.sizeListView)).doubleValue());
                    System.out.println("Style 1" + f.getStyle());
                    FontDialog.root.setStyle("-fx-font: 13 \"" + f.getName() + "\"; ");
                    return f;
                }
                Font f2 = Font.font((String) listSelection(this.fontListView), style.getWeight(), style.getPosture(), ((Double) listSelection(this.sizeListView)).doubleValue());
                System.out.println("Style 2" + f2.getStyle() + " " + String.valueOf(style.getWeight()) + " " + String.valueOf(style.getPosture()) + " " + String.valueOf(listSelection(this.sizeListView)));
                FontDialog.root.setStyle("-fx-font: 13 \"" + f2.getName() + "\"; ");
                return f2;
            } catch (Throwable th) {
                return null;
            }
        }

        private void refreshSample() {
            System.out.println(getFont());
            this.sample.setFont(getFont());
            String os = System.getProperty("os.name", "generic").toLowerCase(Locale.US);
            if (os.indexOf("mac") > 0) {
                FontDialog.root.setStyle("-fx-font-size: 14pt");
            }
        }

        private <T> void selectInList(final ListView<T> listView, final T selection) {
            Platform.runLater(new Runnable() { // from class: fqlite.ui.FontDialog.FontPanel.6
                @Override // java.lang.Runnable
                public void run() {
                    listView.scrollTo(selection);
                    listView.getSelectionModel().select(selection);
                }
            });
        }

        private <T> T listSelection(ListView<T> listView) {
            return listView.selectionModelProperty().get().getSelectedItem();
        }
    }
}
