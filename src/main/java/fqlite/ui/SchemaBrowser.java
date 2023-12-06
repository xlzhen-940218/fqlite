package fqlite.ui;

import java.io.File;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/* loaded from: fqlite_next.jar:fqlite/ui/SchemaBrowser.class */
public class SchemaBrowser extends Region {
    private HBox toolBar = new HBox();
    final ImageView selectedImage = new ImageView();
    final WebView browser = new WebView();
    final WebEngine webEngine = this.browser.getEngine();

    public SchemaBrowser(String schema) {
        getStyleClass().add("browser");
        new File("temp.html");
        this.webEngine.loadContent(schema);
        getChildren().add(this.browser);
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.Parent
    public void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        double tbHeight = this.toolBar.prefHeight(w);
        layoutInArea(this.browser, 0.0d, 0.0d, w, h - tbHeight, 0.0d, HPos.CENTER, VPos.CENTER);
        layoutInArea(this.toolBar, 0.0d, h - tbHeight, w, tbHeight, 0.0d, HPos.CENTER, VPos.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefWidth(double height) {
        return 750.0d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javafx.scene.layout.Region, javafx.scene.Parent
    public double computePrefHeight(double width) {
        return 500.0d;
    }
}
