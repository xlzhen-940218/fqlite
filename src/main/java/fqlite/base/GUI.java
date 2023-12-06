//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.base;

import fqlite.analyzer.BinaryLoader;
import fqlite.types.CtxTypes;
import fqlite.types.FileTypes;
import fqlite.ui.AboutDialog;
import fqlite.ui.Browser;
import fqlite.ui.DBPropertyPanel;
import fqlite.ui.FQTableView;
import fqlite.ui.FileInfo;
import fqlite.ui.FontDialog;
import fqlite.ui.Importer;
import fqlite.ui.NodeObject;
import fqlite.ui.RollbackPropertyPanel;
import fqlite.ui.TooltippedTableCell;
import fqlite.ui.WALPropertyPanel;
import fqlite.ui.hexviewer.HexViewerApp;
import fqlite.util.Auxiliary;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class GUI extends Application {
    public static File baseDir;
    public static int pos = 0;
    public static final CountDownLatch latch = new CountDownLatch(1);
    public static GUI mainwindow;
    public ConcurrentHashMap<String, Node> tables = new ConcurrentHashMap();
    ConcurrentHashMap<String, JTextPane> hexviews = new ConcurrentHashMap();
    private Hashtable<Object, String> rowcolors = new Hashtable();
    ContextMenu cm = null;
    TextArea logwindow;
    MenuBar menuBar;
    public static ScrollPane CellDetailsScrollPane;
    VBox table_panel_with_filter;
    SplitPane splitPane;
    final StackPane leftSide = new StackPane();
    final VBox rightSide = new VBox();
    static HexViewerApp HEXVIEWER_WINDOW = null;
    TextField currentFilter = null;
    HBox head;
    int datacounter = 0;
    static GUI app;
    static TreeView<NodeObject> tree;
    TreeItem<NodeObject> walNode;
    TreeItem<NodeObject> rjNode;
    public static Font ttfFont = null;
    TreeItem<NodeObject> root = new TreeItem(new NodeObject("data bases", true));
    ConcurrentHashMap<String, TreeItem<NodeObject>> treeitems = new ConcurrentHashMap();
    String lasthit = "";
    int lasthitrow = 0;
    int lasthitcol = 0;
    File lastDir = null;
    ImageIcon facewink;
    ImageIcon findIcon;
    ImageIcon errorIcon;
    ImageIcon infoIcon;
    ImageIcon questionIcon;
    ImageIcon warningIcon;
    StackPane rootPane = new StackPane();
    Stage stage;
    Scene scene;
    public static VBox topContainer;
    Font appFont = null;
    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

    public GUI() {
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            String option = args[0];
            Global.WORKINGDIRECTORY = option;
        }

        ImageIcon img = new ImageIcon(GUI.class.getResource("/logo.png"));
        String os = System.getProperty("os.name").toLowerCase();
        if ((os.contains("osx") || os.contains("os x")) && Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(img.getImage());
            taskbar.setIconBadge("FQLite");
        }

        Application.launch(args);
    }

    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();

        while(keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }

    }

    public void start(Stage stage) throws Exception {
        baseDir = new File(System.getProperty("user.home"), ".fqlite");
        Path pp = Path.of(baseDir.getAbsolutePath());
        System.out.println("FQlite home::" + baseDir.getAbsolutePath());
        if (!Files.exists(pp, new LinkOption[0])) {
            baseDir.mkdir();
        }

        this.clearCacheFromPreviousRun();
        this.stage = stage;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        boolean msfontinstalled = false;
        mainwindow = this;
        stage.setTitle("FQLite Carving Tool");
        this.rootPane.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)}));
        String s = GUI.class.getResource("/leaf.jpg").toExternalForm();
        ImageView iv = new ImageView(s);
        this.root.setGraphic(iv);
        this.root.setExpanded(true);
        URL url = GUI.class.getResource("/find.png");
        this.findIcon = new ImageIcon(url);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        MenuItem mntopen = new MenuItem("Open Database...");
        mntopen.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        mntopen.setOnAction((e) -> {
            this.open_db((File)null);
        });
        MenuItem mntmExport = new MenuItem("Export Database...");
        mntmExport.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        mntmExport.setOnAction((e) -> {
            this.doExport();
        });
        MenuItem mntclose = new MenuItem("Close All");
        mntclose.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        mntclose.setOnAction((e) -> {
            this.closeAll();
        });
        MenuItem mntmExit = new MenuItem("Exit");
        mntmExit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
        mntmExit.setOnAction((e) -> {
            System.exit(0);
        });
        MenuItem mntAbout = new MenuItem("About...");
        mntAbout.setOnAction((e) -> {
            new AboutDialog(topContainer);
        });
        MenuItem mntFont = new MenuItem("Fonts...");
        mntFont.setOnAction((e) -> {
            FontDialog fdia = new FontDialog(javafx.scene.text.Font.getDefault(), topContainer);
            fdia.show();
        });
        MenuItem mntmHelp = new MenuItem("Help");
        mntmHelp.setOnAction((e) -> {
            this.showHelp();
        });
        SeparatorMenuItem sep = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        Menu mnFiles = new Menu("File");
        Menu mnInfo = new Menu("Info");
        mnFiles.getItems().addAll(new MenuItem[]{mntopen, mntmExport, sep, mntclose, sep2, mntmExit});
        mnInfo.getItems().addAll(new MenuItem[]{mntmHelp, mntFont, mntAbout});
        this.menuBar = new MenuBar();
        this.menuBar.getMenus().addAll(new Menu[]{mnFiles, mnInfo});
        this.splitPane = new SplitPane();
        s = GUI.class.getResource("/root.png").toExternalForm();
        Button starthere = new Button();
        starthere.setMaxSize(200.0, 200.0);
        iv = new ImageView(s);
        starthere.setGraphic(iv);
        starthere.setOnAction((e) -> {
            this.open_db((File)null);
        });
        this.rootPane.setAlignment(Pos.CENTER);
        this.rootPane.getChildren().add(starthere);
        this.rootPane.setPrefHeight(4000.0);
        this.prepare_tree();
        this.leftSide.getChildren().add(tree);
        this.splitPane.getItems().add(this.leftSide);
        this.splitPane.getItems().add(this.rightSide);
        SplitPane.setResizableWithParent(this.leftSide, true);
        SplitPane.setResizableWithParent(this.rightSide, true);
        ToolBar toolBar = new ToolBar();
        s = GUI.class.getResource("/openDB_gray.png").toExternalForm();
        Button btnOeffne = new Button();
        iv = new ImageView(s);
        btnOeffne.setGraphic(iv);
        btnOeffne.setOnAction((e) -> {
            this.open_db((File)null);
        });
        btnOeffne.setTooltip(new Tooltip("open database file"));
        toolBar.getItems().add(btnOeffne);
        s = GUI.class.getResource("/export_gray.png").toExternalForm();
        Button btnExport = new Button();
        iv = new ImageView(s);
        btnExport.setGraphic(iv);
        btnExport.setOnAction((e) -> {
            this.doExport();
        });
        btnExport.setTooltip(new Tooltip("export data base to file"));
        toolBar.getItems().add(btnExport);
        s = GUI.class.getResource("/closeDB_gray.png").toExternalForm();
        Button btnClose = new Button();
        iv = new ImageView(s);
        btnClose.setGraphic(iv);
        btnClose.setTooltip(new Tooltip("close All"));
        toolBar.getItems().add(btnClose);
        btnClose.setOnAction((e) -> {
            this.closeAll();
        });
        s = GUI.class.getResource("/helpcontent_gray.png").toExternalForm();
        Button about = new Button();
        iv = new ImageView(s);
        about.setGraphic(iv);
        about.setOnAction((e) -> {
            this.showHelp();
        });
        about.setTooltip(new Tooltip("about"));
        toolBar.getItems().add(about);
        s = GUI.class.getResource("/exit3_gray.png").toExternalForm();
        Button btnexit = new Button();
        iv = new ImageView(s);
        btnexit.setGraphic(iv);
        btnexit.setTooltip(new Tooltip("exit"));
        btnexit.setOnAction((e) -> {
            System.exit(-1);
        });
        toolBar.getItems().add(btnexit);
        s = GUI.class.getResource("/hex-32.png").toExternalForm();
        Button hexViewBtn = new Button();
        iv = new ImageView(s);
        hexViewBtn.setGraphic(iv);
        hexViewBtn.setTooltip(new Tooltip("open database in HexView"));
        hexViewBtn.setOnAction((e) -> {
            TreeItem<NodeObject> node = (TreeItem)tree.getSelectionModel().getSelectedItem();
            if (node != null && node != this.root && node.getValue() != null) {
                NodeObject no = (NodeObject)node.getValue();
                if (no.type == FileTypes.SQLiteDB) {
                    if (no.job != null) {
                        HEXVIEWER_WINDOW.loadNewHexFile(no.job.path);
                    }
                } else if (no.type == FileTypes.WriteAheadLog) {
                    if (no.job.wal != null) {
                        HEXVIEWER_WINDOW.loadNewHexFile(no.job.wal.path);
                    }
                } else if (no.type == FileTypes.RollbackJournalLog && no.job.rol != null) {
                    HEXVIEWER_WINDOW.loadNewHexFile(no.job.rol.path);
                }
            }

            Platform.runLater(new Runnable() {
                public void run() {
                    try {
                        HexViewerApp.setVisible();
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }

                }
            });
        });
        toolBar.getItems().add(hexViewBtn);
        url = GUI.class.getResource("/facewink.png");
        this.facewink = new ImageIcon(url);
        url = GUI.class.getResource("/error-48.png");
        this.errorIcon = new ImageIcon(url);
        url = GUI.class.getResource("/information-48.png");
        this.infoIcon = new ImageIcon(url);
        url = GUI.class.getResource("/question-48.png");
        this.questionIcon = new ImageIcon(url);
        url = GUI.class.getResource("/warning-48.png");
        this.warningIcon = new ImageIcon(url);
        topContainer = new VBox();
        topContainer.getChildren().add(this.menuBar);
        topContainer.getChildren().add(toolBar);
        topContainer.getChildren().add(this.splitPane);
        this.scene = new Scene(topContainer, Screen.getPrimary().getVisualBounds().getWidth() * 0.9, Screen.getPrimary().getVisualBounds().getHeight() * 0.9);
        VBox.setVgrow(this.splitPane, Priority.ALWAYS);
        stage.showingProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    GUI.this.splitPane.setDividerPositions(new double[]{0.25});
                    observable.removeListener(this);
                }

            }
        });
        tree.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            public void handle(ContextMenuEvent event) {
                GUI.this.hideContextMenu();
                GUI.this.cm = GUI.this.createContextMenu(CtxTypes.TABLE);
                GUI.tree.setContextMenu(GUI.this.cm);
                GUI.this.cm.show(GUI.tree, event.getScreenX(), event.getScreenY());
                GUI.this.cm.show(GUI.tree.getScene().getWindow(), event.getScreenX(), event.getScreenY());
            }
        });
        this.scene.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(new TransferMode[]{TransferMode.COPY});
                } else {
                    event.consume();
                }

            }
        });
        this.scene.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    Iterator var6 = db.getFiles().iterator();

                    while(var6.hasNext()) {
                        File file = (File)var6.next();
                        filePath = file.getAbsolutePath();
                        System.out.println(filePath);
                        GUI.this.open_db(file);
                    }
                }

                event.setDropCompleted(success);
                event.consume();
            }
        });
        if (!msfontinstalled) {
            try {
                System.out.println(" Didn't find the Microsoft font. Use OpenSansEmoji instead.");
                InputStream is = this.getClass().getResourceAsStream("/OpenSansEmoji.ttf");
                this.appFont = Font.createFont(0, is);
                ge.registerFont(this.appFont);
                this.rootPane.setStyle("-fx-font: 13 \"" + this.appFont.getFontName() + "\"; ");
                topContainer.setStyle("-fx-font: 13 \"" + this.appFont.getFontName() + "\"; ");
                javafx.scene.text.Font f = javafx.scene.text.Font.font("Segoe UI Emoji", (FontWeight)null, FontPosture.REGULAR, 14.0);
                topContainer.setStyle("-fx-font: 13 \"" + f.getName() + "\"; ");
            } catch (IOException | FontFormatException var31) {
                var31.printStackTrace();
            }
        }

        tree.autosize();
        HEXVIEWER_WINDOW = new HexViewerApp();
        HEXVIEWER_WINDOW.start(new Stage());
        stage.setScene(this.scene);
        stage.centerOnScreen();
        stage.sizeToScene();
        stage.show();
    }

    private void showHelp() {
        Stage secondStage = new Stage();
        this.scene = new Scene(new Browser(), 750.0, 500.0, Color.web("#666970"));
        secondStage.setTitle("FQlite Help");
        secondStage.setScene(this.scene);
        secondStage.show();
    }

    private void hideContextMenu() {
        if (this.cm != null) {
            this.cm.hide();
        }

    }

    private ContextMenu createContextMenu(CtxTypes type) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem mntclosesingle = new MenuItem("Close Database");
        String s = GUI.class.getResource("/closeDB_gray.png").toExternalForm();
        ImageView iv = new ImageView(s);
        mntclosesingle.setGraphic(iv);
        mntclosesingle.setOnAction((e) -> {
            System.out.println("Must be implemented");
        });
        MenuItem mntopen = new MenuItem("Open Database...");
        s = GUI.class.getResource("/openDB_gray.png").toExternalForm();
        iv = new ImageView(s);
        mntopen.setGraphic(iv);
        mntopen.setOnAction((e) -> {
            this.open_db((File)null);
        });
        MenuItem mntmExport = new MenuItem("Export Node...");
        mntmExport.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        mntmExport.setOnAction((e) -> {
            this.doExport();
        });
        MenuItem mntclose = new MenuItem("Close All");
        mntclose.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));
        mntclose.setOnAction((e) -> {
            this.closeAll();
        });
        MenuItem mntmExit = new MenuItem("Exit");
        mntmExit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
        mntmExit.setOnAction((e) -> {
            System.exit(0);
        });
        SeparatorMenuItem sepA = new SeparatorMenuItem();
        SeparatorMenuItem sepB = new SeparatorMenuItem();
        contextMenu.getItems().addAll(new MenuItem[]{mntclosesingle, sepA, mntopen, mntmExport, mntclose, sepB, mntmExit});
        return contextMenu;
    }

    private void fillClipboard() {
        java.awt.datatransfer.Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringBuffer selection = new StringBuffer();
        Transferable transferable = new StringSelection(selection.toString());
        cb.setContents(transferable, (ClipboardOwner)null);
    }

    private void clearCacheFromPreviousRun() {
        if (baseDir != null) {
            try {
                File[] cache = baseDir.listFiles();
                if (cache == null) {
                    return;
                }

                File[] var5;
                int var4 = (var5 = baseDir.listFiles()).length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    File file = var5[var3];
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            } catch (Exception var6) {
            }
        }

    }

    public void closeAll() {
        TreeItem<NodeObject> root = tree.getRoot();
        Iterator<TreeItem<NodeObject>> it = root.getChildren().iterator();

        while(it.hasNext()) {
            TreeItem<NodeObject> node = (TreeItem)it.next();
            if (node.getValue() != null) {
                NodeObject no = (NodeObject)node.getValue();
                if (no.job != null) {
                    no.job = null;
                }
            }
        }

        if (root != null) {
            root.getChildren().clear();
        }

        this.tables.clear();
        this.hexviews.clear();
        this.treeitems.clear();
        HEXVIEWER_WINDOW.clearAll();
        System.gc();
    }

    public void doExport() {
        NodeObject no = null;
        TreeItem<NodeObject> node = (TreeItem)tree.getSelectionModel().getSelectedItem();
        if (node != null && !((NodeObject)node.getValue()).isRoot) {
            if (node.getValue() != null) {
                no = (NodeObject)node.getValue();
                Job var10000 = no.job;
            }

            this.export_table(no);
        }
    }

    String add_table(Job job, String tablename, List<String> columns, List<String> columntypes, List<String> PK, List<String> BoolColumns, boolean walnode, boolean rjnode, int db_object) {
        NodeObject o = null;
        Path p = Paths.get(job.path);
        final FQTableView<Object> table = new FQTableView(p.getFileName().toString());
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Image img = new Image(GUI.class.getResource("/icon_status.png").toExternalForm());
        ImageView view = new ImageView(img);
        TableColumn numbercolumn;
        TableColumn pllcolumn;
        TableColumn hlcolumn;
        Label statusLabel;
        TableColumn statuscolumn;
        TableColumn offsetcolumn;
        if (!walnode) {
            numbercolumn = new TableColumn("No.");
            numbercolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(0).toString());
                }
            });
            pllcolumn = new TableColumn("PLL");
            pllcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            pllcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(2).toString());
                }
            });
            hlcolumn = new TableColumn("HL");
            hlcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            hlcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(3).toString());
                }
            });
            statusLabel = new Label("S");
            statusLabel.setTooltip(new Tooltip("indicates if data record is deleted or not"));
            statuscolumn = new TableColumn();
            statuscolumn.setGraphic(statusLabel);
            statuscolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            statuscolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(4).toString());
                }
            });
            statuscolumn.setGraphic(view);
            offsetcolumn = new TableColumn("Offset");
            offsetcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            offsetcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(5).toString());
                }
            });
            table.getColumns().addAll(new TableColumn[]{numbercolumn, statuscolumn, offsetcolumn, pllcolumn, hlcolumn});
        } else {
            numbercolumn = new TableColumn("No.");
            numbercolumn.setComparator(new CustomComparator());
            numbercolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(0).toString());
                }
            });
            pllcolumn = new TableColumn("PLL");
            pllcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            pllcolumn.setComparator(new CustomComparator());
            pllcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(2).toString());
                }
            });
            hlcolumn = new TableColumn("HL");
            hlcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            hlcolumn.setComparator(new CustomComparator());
            hlcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(3).toString());
                }
            });
            statusLabel = new Label("S");
            statusLabel.setTooltip(new Tooltip("indicates if data record is deleted or not"));
            statuscolumn = new TableColumn();
            statuscolumn.setGraphic(statusLabel);
            statuscolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            statuscolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(4).toString());
                }
            });
            statuscolumn.setGraphic(view);
            offsetcolumn = new TableColumn("Offset");
            offsetcolumn.setComparator(new CustomComparator());
            offsetcolumn.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            offsetcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(5).toString());
                }
            });
            table.getColumns().addAll(new TableColumn[]{numbercolumn, statuscolumn, offsetcolumn, pllcolumn, hlcolumn});
        }

        this.datacounter = 0;

        for(int i = 0; i < columns.size(); ++i) {
            String colname = (String)columns.get(i);
            final int j = walnode ? i + 6 : i + 6;
            TableColumn col = new TableColumn(colname);
            col.setCellFactory(TooltippedTableCell.forTableColumn(tablename, job));
            col.setComparator(new CustomComparator());
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return ((ObservableList)param.getValue()).size() <= j ? new SimpleStringProperty("") : new SimpleStringProperty(((ObservableList)param.getValue()).get(j).toString());
                }
            });
            if (columntypes.size() > i && !((String)columntypes.get(i)).equals("BLOB") && !((String)columntypes.get(i)).equals("TEXT")) {
                col.setStyle("-fx-alignment: CENTER-RIGHT;");
            }

            if (PK != null && PK.contains(colname)) {
                img = new Image(GUI.class.getResource("/key-icon.png").toExternalForm());
                view = new ImageView(img);
                col.setGraphic(view);
            }

            table.getColumns().add(col);
        }

        VBox tablePane = new VBox();
        TextField filter = new TextField();
        tablePane.getChildren().add(filter);
        tablePane.getChildren().add(table);
        table.setPrefHeight(4000.0);
        VBox.setVgrow(table, Priority.ALWAYS);
        Label l = new Label("Table: " + tablename);
        tablePane.getChildren().add(l);
        if (walnode) {
            o = new NodeObject(tablename, tablePane, columns.size(), FileTypes.WriteAheadLog, db_object);
        }

        if (rjnode) {
            o = new NodeObject(tablename, tablePane, columns.size(), FileTypes.RollbackJournalLog, db_object);
        }

        if (!walnode && !rjnode) {
            o = new NodeObject(tablename, tablePane, columns.size(), FileTypes.SQLiteDB, db_object);
        }

        o.job = job;
        TreeItem<NodeObject> dmtn = new TreeItem(o);
        dmtn.setExpanded(true);
        String s = null;
        switch (o.tabletype) {
            case 0:
                s = GUI.class.getResource("/table_icon_empty.png").toExternalForm();
                break;
            case 1:
                s = GUI.class.getResource("/table-key-icon-reddot.png").toExternalForm();
                break;
            case 99:
                s = GUI.class.getResource("/database-small-icon.png").toExternalForm();
                break;
            case 100:
                s = GUI.class.getResource("/journal-icon.png").toExternalForm();
                break;
            case 101:
                s = GUI.class.getResource("/wal-icon.png").toExternalForm();
        }

        ImageView iv = new ImageView(s);
        dmtn.setGraphic(iv);
        if (walnode) {
            this.walNode.getChildren().add(dmtn);
        } else if (rjnode) {
            this.rjNode.getChildren().add(dmtn);
        } else {
            Platform.runLater(() -> {
                job.getTreeItem().getChildren().add(dmtn);
                FXCollections.sort(job.getTreeItem().getChildren(), new Comparator<TreeItem<NodeObject>>() {
                    public int compare(TreeItem<NodeObject> o1, TreeItem<NodeObject> o2) {
                        return ((NodeObject)o1.getValue()).name.compareTo(((NodeObject)o2.getValue()).name);
                    }
                });
            });
        }

        String tp = this.getPath(dmtn);
        this.treeitems.put(tp, dmtn);
        final ContextMenu tcm = this.createContextMenu(CtxTypes.TABLE, tablename, table, job);
        table.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (!table.getSelectionModel().isEmpty()) {
                    KeyCodeCombination copylineCombination = new KeyCodeCombination(KeyCode.L, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN});
                    KeyCodeCombination copycellCombination = new KeyCodeCombination(KeyCode.C, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN});
                    if (copylineCombination.match(event)) {
                        GUI.this.copyLineAction(table);
                        event.consume();
                    } else if (copycellCombination.match(event)) {
                        GUI.this.copyCellAction(table);
                        event.consume();
                    }
                }

            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!event.getTarget().toString().startsWith("TableColumnHeader")) {
                    //int rowx = true;
                    TablePosition pos = null;

                    int row;
                    try {
                        pos = (TablePosition)table.getSelectionModel().getSelectedCells().get(0);
                        row = pos.getRow();
                    } catch (Exception var10) {
                        return;
                    }

                    Object item = table.getItems().get(row);
                    TableColumn col = pos.getTableColumn();
                    if (col != null) {
                        Object data = col.getCellObservableValue(item).getValue();
                        if (col.getText().equals("Offset") && row >= 0) {
                            NodeObject no = GUI.this.getSelectedNode();
                            String model = null;
                            if (no.type == FileTypes.SQLiteDB) {
                                model = no.job.path;
                            } else if (no.type == FileTypes.WriteAheadLog) {
                                model = no.job.wal.path;
                            } else if (no.type == FileTypes.RollbackJournalLog) {
                                model = no.job.rol.path;
                            }

                            ObservableList var10000 = (ObservableList)table.getItems().get(row);
                            GUI.HEXVIEWER_WINDOW.switchModel(model);
                            GUI.HEXVIEWER_WINDOW.goTo(Integer.parseInt((String)data));
                            HexViewerApp.setVisible();
                        }

                        if (event.getButton() == MouseButton.SECONDARY) {
                            tcm.show(table.getScene().getWindow(), event.getScreenX(), event.getScreenY());
                        }

                    }
                }
            }
        });
        this.tables.put(tp, tablePane);
        return tp;
    }

    private ContextMenu createContextMenu(CtxTypes type, final String tablename, final FQTableView table, final Job job) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem mntcopyline = new MenuItem("Copy Line(s)");
        String s = GUI.class.getResource("/edit-copy.png").toExternalForm();
        ImageView iv = new ImageView(s);
        KeyCodeCombination copylineCombination = new KeyCodeCombination(KeyCode.L, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN});
        KeyCodeCombination copycellCombination = new KeyCodeCombination(KeyCode.C, new KeyCombination.Modifier[]{KeyCombination.SHORTCUT_DOWN});
        mntcopyline.setAccelerator(copylineCombination);
        mntcopyline.setGraphic(iv);
        mntcopyline.setOnAction((e) -> {
            this.copyLineAction(table);
            e.consume();
        });
        MenuItem mntcopycell = new MenuItem("Copy Cell");
        s = GUI.class.getResource("/edit-copy.png").toExternalForm();
        iv = new ImageView(s);
        mntcopycell.setGraphic(iv);
        mntcopycell.setAccelerator(copycellCombination);
        mntcopycell.setOnAction((e) -> {
            this.copyCellAction(table);
            e.consume();
        });
        SeparatorMenuItem sepA = new SeparatorMenuItem();
        s = GUI.class.getResource("/edit-find.png").toExternalForm();
        iv = new ImageView(s);
        Menu analyze = new Menu("Analyze...");
        analyze.setGraphic(iv);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (((CheckMenuItem)e.getSource()).isSelected()) {
                    System.out.println("Protobuffer support enabled." + tablename);
                    job.inspectProtoBuffer.add(tablename);
                    table.refresh();
                } else {
                    System.out.println("Off.");
                    job.inspectProtoBuffer.remove(tablename);
                    table.refresh();
                }

            }
        };
        CheckMenuItem protob = new CheckMenuItem("BLOB -> ProtoBuffer (experimental)");
        protob.setOnAction(event);
        analyze.getItems().add(protob);
        EventHandler<ActionEvent> eventBASE64 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (((CheckMenuItem)e.getSource()).isSelected()) {
                    System.out.println("BASE64 support enabled." + tablename);
                    job.inspectBASE64.add(tablename);
                    table.refresh();
                } else {
                    System.out.println("Off.");
                    job.inspectBASE64.remove(tablename);
                    table.refresh();
                }

            }
        };
        CheckMenuItem base64 = new CheckMenuItem("BASE64 for Textfields (experimental)");
        base64.setOnAction(eventBASE64);
        analyze.getItems().add(base64);
        contextMenu.getItems().addAll(new MenuItem[]{mntcopyline, mntcopycell, sepA, analyze});
        return contextMenu;
    }

    private void copyCellAction(FQTableView table) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        ObservableList<TablePosition> selection = table.getSelectionModel().getSelectedCells();
        if (selection.size() != 0) {
            TablePosition tp = (TablePosition)selection.get(0);
            int row = tp.getRow();
            int col = tp.getColumn();
            TableColumn tc = (TableColumn)table.getColumns().get(col);
            ObservableValue observableValue = tc.getCellObservableValue(row);
            String cellvalue = "";
            if (observableValue != null) {
                cellvalue = (String)observableValue.getValue();
                if (cellvalue.startsWith("[BLOB-")) {
                    int from = cellvalue.indexOf("BLOB-");
                    int to = cellvalue.indexOf("]");
                    String number = cellvalue.substring(from + 5, to);
                    int start = cellvalue.indexOf("<");
                    int end = cellvalue.indexOf(">");
                    String type;
                    if (end > 0) {
                        type = cellvalue.substring(start + 1, end);
                    } else {
                        type = "bin";
                    }

                    tc = (TableColumn)table.getColumns().get(2);
                    ObservableValue off = tc.getCellObservableValue(row);
                    String path = String.valueOf(baseDir) + Global.separator + table.dbname + "_" + String.valueOf(off.getValue()) + "-" + number + "." + type;
                    String data = BinaryLoader.parse2(path);
                    System.out.println(" Data" + data);
                    content.putString(data.toUpperCase());
                    clipboard.setContent(content);
                    return;
                }
            }

            content.putString(cellvalue);
            clipboard.setContent(content);
        }
    }

    private void cellvalue2clipboard(String cellvalue, FQTableView table, int row) {
        int from = cellvalue.indexOf("BLOB-");
        int to = cellvalue.indexOf("]");
        String number = cellvalue.substring(from + 5, to);
        int start = cellvalue.indexOf("<");
        int end = cellvalue.indexOf(">");
        String type;
        if (end > 0) {
            type = cellvalue.substring(start + 1, end);
        } else {
            type = "bin";
        }

        TableColumn tc = (TableColumn)table.getColumns().get(2);
        ObservableValue off = tc.getCellObservableValue(row);
        String path = String.valueOf(baseDir) + Global.separator + table.dbname + "_" + String.valueOf(off.getValue()) + "-" + number + "." + type;
        String data = BinaryLoader.parse2(path);
        System.out.println(" Data" + data);
        this.content.putString(data.toUpperCase());
        this.clipboard.setContent(this.content);
    }

    private void copyLineAction(TableView table) {
        StringBuffer sb = new StringBuffer();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        ObservableList<TablePosition> selection = table.getSelectionModel().getSelectedCells();
        Iterator<TablePosition> iter = selection.iterator();

        while(iter.hasNext()) {
            TablePosition pos = (TablePosition)iter.next();
            ObservableList<String> hl = (ObservableList)table.getItems().get(pos.getRow());
            sb.append(hl.toString());
        }

        System.out.println("Write value to clipboard " + sb.toString());
        content.putString(sb.toString());
        clipboard.setContent(content);
    }

    public static TreePath getPath(TreeNode treeNode) {
        List<Object> nodes = new ArrayList();
        if (treeNode != null) {
            nodes.add(treeNode);

            for(treeNode = treeNode.getParent(); treeNode != null; treeNode = treeNode.getParent()) {
                nodes.add(0, treeNode);
            }
        }

        return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
    }

    private String getPath(TreeItem<NodeObject> item) {
        StringBuilder pathBuilder;
        for(pathBuilder = new StringBuilder(); item != null; item = item.getParent()) {
            pathBuilder.insert(0, item.getValue());
            pathBuilder.insert(0, "/");
        }

        String path = pathBuilder.toString();
        return path;
    }

    private NodeObject getSelectedNode() {
        return (NodeObject)((TreeItem)tree.getSelectionModel().getSelectedItem()).getValue();
    }

    protected void prepare_tree() {
        if (tree == null) {
            tree = new TreeView(this.root);
            tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }

        this.rightSide.getChildren().add(this.rootPane);
        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {
            public void changed(ObservableValue observable, TreeItem oldValue, TreeItem newValue) {
                if (newValue == null) {
                    GUI.this.rightSide.getChildren().clear();
                    GUI.this.rightSide.getChildren().add(GUI.this.rootPane);
                } else {
                    final NodeObject node = (NodeObject)newValue.getValue();
                    if (node.tablePane != null) {
                        Platform.runLater(new Runnable() {
                            public void run() {
                                GUI.this.rightSide.getChildren().clear();
                                GUI.this.rightSide.getChildren().add(node.tablePane);
                                VBox.setVgrow(node.tablePane, Priority.ALWAYS);
                            }
                        });
                    } else if (node.isRoot) {
                        GUI.this.rightSide.getChildren().clear();
                        GUI.this.rightSide.getChildren().add(GUI.this.rootPane);
                    } else {
                        String tp = GUI.this.getPath(newValue);
                        StackPane dbpanel = (StackPane)GUI.this.tables.get(tp);
                        GUI.this.rightSide.getChildren().clear();
                        if (dbpanel != null) {
                            GUI.this.rightSide.getChildren().add(dbpanel);
                            dbpanel.setPrefHeight(4000.0);
                            VBox.setVgrow(dbpanel, Priority.ALWAYS);
                        }
                    }

                }
            }
        });
    }

    public synchronized void open_db(File f) {
        File file = f;
        if (f == null) {
            JFileChooser chooser = new JFileChooser();
            if (this.lastDir == null) {
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            } else {
                chooser.setCurrentDirectory(this.lastDir);
            }

            chooser.setDialogTitle("open database");
            chooser.setName("open database");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Sqlite & DB Files (*.sqlite,*.db)", new String[]{"sqlite", "db"});
            chooser.setFileFilter(filter);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open database File");
            file = fileChooser.showOpenDialog(this.stage);
        }

        if (file != null) {
            if (file.length() < 512L) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("File size is smaller than 512 bytes. Import stopped.");
                alert.showAndWait();
            } else {
                RandomAccessFile raf = null;
                boolean abort = false;

                try {
                    Alert alert;
                    try {
                        raf = new RandomAccessFile(file, "r");
                        byte[] h = new byte[16];
                        raf.read(h);
                        if (!Auxiliary.bytesToHex(h).equals("53514c69746520666f726d6174203300")) {
                            abort = true;
                            alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setContentText("Couldn't find a valid SQLite3 magic. Import stopped");
                            alert.showAndWait();
                        }
                    } catch (Exception var21) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("IO-Exception. Cloud not open file.");
                        alert.showAndWait();
                        abort = true;
                    }
                } finally {
                    try {
                        raf.close();
                    } catch (IOException var20) {
                    }

                }

                if (!abort) {
                    FileInfo info = new FileInfo(file.getAbsolutePath());
                    DBPropertyPanel panel = new DBPropertyPanel(info, file.getName());
                    panel.setPrefHeight(4000.0);
                    VBox.setVgrow(panel, Priority.ALWAYS);
                    NodeObject o = new NodeObject(file.getName(), (VBox)null, -1, FileTypes.SQLiteDB, 99);
                    TreeItem<NodeObject> dbNode = new TreeItem(o);
                    dbNode.setGraphic(this.createFadeTransition("loading..."));
                    this.root.getChildren().add(dbNode);
                    String tp = this.getPath(dbNode);
                    Job job = new Job();
                    this.tables.put(tp, panel);
                    NodeObject wo;
                    String tpw;
                    FileInfo winfo;
                    if (doesRollbackJournalExist(file.getAbsolutePath()) > 0L) {
                        wo = new NodeObject(file.getName() + "-journal", (VBox)null, -1, FileTypes.RollbackJournalLog, 100);
                        this.rjNode = new TreeItem(wo);
                        this.rjNode.setGraphic(this.createFadeTransition("loading..."));
                        this.root.getChildren().add(this.rjNode);
                        tpw = this.getPath(this.rjNode);
                        winfo = new FileInfo(file.getAbsolutePath() + "-journal");
                        RollbackPropertyPanel rpanel = new RollbackPropertyPanel(winfo);
                        this.tables.put(tpw, rpanel);
                        job.rjNode = this.rjNode;
                        job.setRollbackPropertyPanel(rpanel);
                        wo.job = job;
                        wo.job.readRollbackJournal = true;
                        wo.job.readWAL = false;
                    } else if (doesWALFileExist(file.getAbsolutePath()) > 0L) {
                        wo = new NodeObject(file.getName() + "-wal", (VBox)null, -1, FileTypes.WriteAheadLog, 101);
                        this.walNode = new TreeItem(wo);
                        this.walNode.setGraphic(this.createFadeTransition("loading..."));
                        this.root.getChildren().add(this.walNode);
                        tpw = this.getPath(this.walNode);
                        winfo = new FileInfo(file.getAbsolutePath() + "-wal");
                        WALPropertyPanel wpanel = new WALPropertyPanel(winfo, this);
                        this.tables.put(tpw, wpanel);
                        job.walNode = this.walNode;
                        job.setWALPropertyPanel(wpanel);
                        wo.job = job;
                        wo.job.readWAL = true;
                        wo.job.readRollbackJournal = false;
                    }

                    tree.refresh();
                    job.setPropertyPanel(panel);
                    job.setGUI(this);
                    job.setTreeItem(dbNode);
                    job.setPath(file.getAbsolutePath());
                    Importer.createAndShowGUI(this, file.getAbsolutePath(), job, dbNode);
                    o.job = job;
                    int idx = tree.getRow(dbNode);
                    tree.getSelectionModel().select(idx);
                    tree.scrollTo(idx);
                }
            }
        }
    }

    private Label createFadeTransition(String msg) {
        Label l = new Label(msg);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), l);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(-1);
        fadeTransition.play();
        return l;
    }

    public static long doesWALFileExist(String dbfile) {
        String walpath = dbfile + "-wal";
        Path path = Paths.get(walpath);
        if (Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            try {
                return Files.size(path);
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        return -1L;
    }

    public static long doesRollbackJournalExist(String dbfile) {
        String rolpath = dbfile + "-journal";
        Path path = Paths.get(rolpath);
        if (Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
            try {
                return Files.size(path);
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        return -1L;
    }

    protected void doLog(String message) {
    }

    private void export_table(NodeObject no) {
        if (no != null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export Database");
            fileChooser.setInitialFileName(this.prepareDefaultFileName(no.name));
            File f = fileChooser.showSaveDialog(this.stage);
            if (f != null) {
                String var10001;
                boolean success;
                Alert alert;
                switch (no.tabletype) {
                    case 0:
                    case 1:
                        if (f != null) {
                            success = no.job.exportResults2File(f, no.name);
                            if (success) {
                                alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Success Info");
                                var10001 = no.name;
                                alert.setContentText("Table " + var10001 + " exported successfully to \n" + f.getAbsolutePath());
                                alert.showAndWait();
                            }
                        }
                        break;
                    case 99:
                    case 100:
                    case 101:
                        success = no.job.exportResults2File(f, no.name);
                        if (success) {
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Success Info");
                            var10001 = no.name;
                            alert.setContentText("Data base " + var10001 + " exported successfully to \n" + f.getAbsolutePath());
                            alert.showAndWait();
                        }
                }

            }
        }
    }

    private String prepareDefaultFileName(String nameofnode) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        String date = df.format(now);
        date = date.replace(":", "_");
        return nameofnode + date + ".csv";
    }

    private String[] filterLines(Iterator<String> lines, String filterword, String headerline) {
        LinkedList<String> filtered = new LinkedList();

        while(lines.hasNext()) {
            String line = (String)lines.next();
            if (line.startsWith(filterword)) {
                filtered.add(line);
            }
        }

        filtered.addFirst(headerline);
        return (String[])filtered.toArray(new String[0]);
    }

    private ObservableList<String> prepareRow(int linenumber, LinkedList<String> row, boolean isWALTable) {
        ObservableList<String> list = FXCollections.observableArrayList(row);
        ++linenumber;
        list.add(0, String.valueOf(linenumber));
        return list;
    }

    protected void update_table(String treepath, ObservableList<LinkedList<String>> rows, boolean isWALTable) {
        ObservableList<ObservableList> obdata = FXCollections.observableArrayList();
        TableView tb = null;
        TextField filterField = null;

        try {
            VBox tablepanel = (VBox)this.tables.get(treepath);
            VBox.setVgrow(tablepanel, Priority.ALWAYS);
            filterField = (TextField)tablepanel.getChildren().get(0);
            tb = (TableView)tablepanel.getChildren().get(1);
            Label statusline = (Label)tablepanel.getChildren().get(2);
            String text = statusline.getText();
            statusline.setText(text + " | rows: " + rows.size());
        } catch (Exception var11) {
            System.err.println(var11);
            return;
        }

        if (tb == null) {
            this.doLog(">>>> Unkown tablename" + treepath);
        } else {
            final TreeItem<NodeObject> node = (TreeItem)this.treeitems.get(treepath);
            if (node != null && rows.size() > 0) {
                ((NodeObject)node.getValue()).hasData = true;
                Platform.runLater(new Runnable() {
                    public void run() {
                        String s = null;
                        if (((NodeObject)node.getValue()).tabletype == 0) {
                            s = GUI.class.getResource("/table-icon.png").toExternalForm();
                        }

                        if (((NodeObject)node.getValue()).tabletype == 1) {
                            s = GUI.class.getResource("/table-key-icon.png").toExternalForm();
                        }

                        if (s != null) {
                            ImageView iv = new ImageView(s);
                            node.setGraphic((Node)null);
                            node.setGraphic(iv);
                            TreeItem.graphicChangedEvent();
                            TreeItem.valueChangedEvent();
                            GUI.tree.refresh();
                        }
                    }
                });
            }

            for(int i = 0; i < rows.size(); ++i) {
                LinkedList<String> data = (LinkedList)rows.get(i);
                obdata.add(FXCollections.observableList(this.prepareRow(i, data, isWALTable)));
            }

            FilteredList<ObservableList> filteredData = new FilteredList(obdata, (p) -> {
                return true;
            });
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((r) -> {
                    if (newValue != null && !newValue.isEmpty()) {
                        String lowerCaseFilter = newValue.toLowerCase();

                        for(int i = 0; i < r.size(); ++i) {
                            String value = (String)r.get(i);
                            if (value.toLowerCase().contains(lowerCaseFilter)) {
                                return true;
                            }
                        }

                        return false;
                    } else {
                        return true;
                    }
                });
            });
            SortedList<ObservableList> sortedData = new SortedList(filteredData);
            sortedData.comparatorProperty().bind(tb.comparatorProperty());
            tb.setItems(sortedData);
            TableView finalTb = tb;
            tb.getSelectionModel().getSelectedItems().addListener((ListChangeListener) (c) -> {
                if (finalTb != null) {
                    int selecteditems = finalTb.getSelectionModel().getSelectedCells().size();
                    VBox tablepanel = (VBox)this.tables.get(treepath);
                    Label statusline = (Label)tablepanel.getChildren().get(2);
                    String text = statusline.getText();
                    int idx = text.indexOf(" | rows: ");
                    if (idx > 0) {
                        String var10001 = text.substring(0, idx);
                        statusline.setText(var10001 + " | rows: " + rows.size() + " | selected rows: " + selecteditems);
                    }
                }

            });
        }
    }

    public Hashtable<Object, String> getRowcolors() {
        return this.rowcolors;
    }

    public void setRowcolors(Hashtable<Object, String> rowcolors) {
        this.rowcolors = rowcolors;
    }

    private class CustomComparator implements Comparator<String> {
        private CustomComparator() {
        }

        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                return 0;
            } else if (o1 == null) {
                return -1;
            } else if (o2 == null) {
                return 1;
            } else if (o1.length() == 0) {
                return -1;
            } else {
                char ch = o1.charAt(0);
                if ((ch < '0' || ch > '9') && ch != '-' && ch != '+') {
                    return o1.compareTo(o2);
                } else {
                    Integer i1 = null;

                    try {
                        i1 = Integer.valueOf(o1);
                    } catch (NumberFormatException var8) {
                    }

                    Integer i2 = null;

                    try {
                        i2 = Integer.valueOf(o2);
                    } catch (NumberFormatException var7) {
                    }

                    if (i1 == null && i2 == null) {
                        return o1.compareTo(o2);
                    } else if (i1 == null) {
                        return -1;
                    } else {
                        return i2 == null ? 1 : i1 - i2;
                    }
                }
            }
        }
    }
}
