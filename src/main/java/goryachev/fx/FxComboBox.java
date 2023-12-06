package goryachev.fx;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxComboBox.class */
public class FxComboBox<T> extends ComboBox<T> {
    public FxComboBox(ObservableList<T> items) {
        super(items);
    }

    public FxComboBox(T... tArr) {
        setItems(tArr);
    }

    public FxComboBox(Collection<T> items) {
        setItems(items);
    }

    public FxComboBox() {
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            getItems().clear();
        } else {
            getItems().setAll(tArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setItems(Collection<T> items) {
        if (items == null) {
            getItems().clear();
        } else {
            getItems().setAll((Collection<? extends T>) items);
        }
    }

    public void addItem(T item) {
        getItems().add(item);
    }

    public void selectFirst() {
        getSelectionModel().selectFirst();
    }

    public void select(int ix) {
        getSelectionModel().select(ix);
    }

    public void select(T item) {
        getSelectionModel().select( item);
    }

    public <V> void select(V key, Function<T, V> converter) {
        if (key == null) {
            return;
        }
        List<T> items = getItems();
        for (int i = items.size() - 1; i >= 0; i--) {
            T item = items.get(i);
            V k = converter.apply(item);
            if (key.equals(k)) {
                select(i);
                return;
            }
        }
    }

    public void selectOrFirst(T item) {
        int ix = indexOf(item);
        if (ix < 0) {
            selectFirst();
        } else {
            select(ix);
        }
    }

    public final ReadOnlyObjectProperty<T> selectedItemProperty() {
        return getSelectionModel().selectedItemProperty();
    }

    public T getSelectedItem() {
        return getSelectionModel().getSelectedItem();
    }

    public int getSelectedIndex() {
        return getSelectionModel().getSelectedIndex();
    }

    public int indexOf(T item) {
        return getItems().indexOf(item);
    }

    public String getSelectedItemAsString() {
        T x = getSelectedItem();
        if (x == null) {
            return null;
        }
        return x.toString();
    }
}
