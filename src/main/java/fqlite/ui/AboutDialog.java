package fqlite.ui;

import fqlite.base.GUI;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:fqlite/ui/AboutDialog.class */
public class AboutDialog extends Dialog<Object> {
    Node root;
    final DialogPane dialogPane = getDialogPane();

    public AboutDialog(Node rootelement) {
        this.root = rootelement;
        Image img = new Image(GUI.class.getResource("/fqlite_logo_small.png").toExternalForm());
        ImageView view = new ImageView(img);
        this.dialogPane.setGraphic(view);
        this.dialogPane.getButtonTypes().addAll(ButtonType.OK);
        createLayout();
        setTitle("About this Program");
        show();
    }

    private void createLayout() {
        this.dialogPane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5.0d), Insets.EMPTY)));
        setContentText("FQLite Retrieval Tool, Version 2.2\nAuthor: Dirk Pawlaszczyk \n\nMittweida University of Applied Sciences\nGermany\n© 2023");
        Label label = new Label("License Information");
        TextArea textArea = new TextArea("GNU GENERAL PUBLIC LICENSE \n Version 3, 29 June 2007 \n----------------------------------------------------- \nThis program is free software:  you  can  redistribute  it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(0.8d);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        getDialogPane().setExpandableContent(expContent);
        Window window = getDialogPane().getScene().getWindow();
        Stage stage = (Stage) window;
    }
}
