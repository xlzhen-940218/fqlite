package goryachev.fx;

import goryachev.common.util.CList;
import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/FxFileChooser.class */
public class FxFileChooser {
    private final Window parent;
    private final FileChooser fc = new FileChooser();

    public FxFileChooser(Object nodeOrWindow, Object lastDirProperty) {
        this.parent = FX.getParentWindow(nodeOrWindow);
    }

    public void setTitle(String title) {
        this.fc.setTitle(title);
    }

    public void addExtensionFilter(String desc, String... extensions) {
        this.fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(desc, CList.of( extensions)));
    }

    public void addExtensionFilter(FileChooser.ExtensionFilter f) {
        this.fc.getExtensionFilters().add(f);
    }

    public void setInitialFileName(String name) {
        this.fc.setInitialFileName(name);
    }

    public void setInitialDirectory(File dir) {
        this.fc.setInitialDirectory(dir);
    }

    public File showSaveDialog() {
        return this.fc.showSaveDialog(this.parent);
    }

    public File showOpenDialog() {
        return this.fc.showOpenDialog(this.parent);
    }

    public List<File> showOpenMultipleDialog() {
        return this.fc.showOpenMultipleDialog(this.parent);
    }

    public FileChooser.ExtensionFilter getSelectedExtensionFilter() {
        return this.fc.getSelectedExtensionFilter();
    }

    public String getSelectedExtension() {
        List<String> ss;
        FileChooser.ExtensionFilter f = getSelectedExtensionFilter();
        if (f != null && (ss = f.getExtensions()) != null && ss.size() > 0) {
            return ss.get(0);
        }
        return null;
    }
}
