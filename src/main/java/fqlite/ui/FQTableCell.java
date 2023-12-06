package fqlite.ui;

import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

/* loaded from: fqlite_next.jar:fqlite/ui/FQTableCell.class */
public class FQTableCell<T> extends TableCell<T, Boolean> {
    private final ImageView imageView = new ImageView();

    public FQTableCell() {
        setGraphic(this.imageView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // javafx.scene.control.Cell
    public void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            this.imageView.setImage(null);
        }
    }
}
