package fqlite.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/* loaded from: fqlite_next.jar:fqlite/ui/Browser.class */
public class Browser extends Region {
    private HBox toolBar;
    private static String[] imageFiles = {"product.png", "blog.png", "documentation.png", "partners.png"};
    private static String[] captions = {"UserGuide", "Download", "Github"};
    private static String[] urls = {"https://www.staff.hs-mittweida.de/~pawlaszc/fqlite/userguide/", "https://www.staff.hs-mittweida.de/~pawlaszc/fqlite/", "https://github.com/pawlaszczyk/fqlite"};
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];
    final WebView browser = new WebView();
    final WebEngine webEngine = this.browser.getEngine();

    public Browser() {
        getStyleClass().add("browser");
        for (int i = 0; i < captions.length; i++) {
            Hyperlink hpl = new Hyperlink(captions[i]);
            this.hpls[i] = hpl;
            final String url = urls[i];
            hpl.setOnAction(new EventHandler<ActionEvent>() { // from class: fqlite.ui.Browser.1
                @Override // javafx.event.EventHandler
                public void handle(ActionEvent e) {
                    Browser.this.webEngine.load(url);
                }
            });
        }
        this.webEngine.load("https://www.staff.hs-mittweida.de/~pawlaszc/fqlite/userguide/");
        this.toolBar = new HBox();
        this.toolBar.getStyleClass().add("browser-toolbar");
        this.toolBar.getChildren().addAll(this.hpls);
        getChildren().add(this.toolBar);
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
