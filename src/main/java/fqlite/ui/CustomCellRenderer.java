package fqlite.ui;

import fqlite.base.GUI;
import fqlite.base.Global;
import fqlite.util.BLOBCarver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/* loaded from: fqlite_next.jar:fqlite/ui/CustomCellRenderer.class */
public class CustomCellRenderer extends DefaultTableCellRenderer {
    private static final long serialVersionUID = 1;
    private static final Font offsetFont = new Font("Courier New", 1, 13);
    private static final Color rowcol = new Color(246, 246, 246);
    private static final Font defaultFont = new Font("OpenSansEmoji", 0, 13);
    private static ImageIcon icon_deleted = new ImageIcon(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icon_deleted.png")));
    private static ImageIcon icon_trash = new ImageIcon(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/icon_trash.png")));
    public boolean walnode = false;
    public GUI gui = null;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column < 3) {
            rendererComp.setFont(offsetFont);
        } else if (GUI.ttfFont != null) {
            rendererComp.setFont(GUI.ttfFont);
        } else {
            rendererComp.setFont(defaultFont);
        }
        if (column > 2 && (value instanceof String)) {
            String v = (String) value;
            if (BLOBCarver.isGraphic(v)) {
                try {
                    if (BLOBCarver.isJPEG(v)) {
                        setValue("<JPEG>");
                    } else if (BLOBCarver.isICO(v)) {
                        setValue("<ICO>");
                    } else if (BLOBCarver.isGIF(v)) {
                        setValue("<GIF>");
                    } else if (BLOBCarver.isPNG(v)) {
                        setValue("<PNG>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (BLOBCarver.isSVG(v)) {
                setValue("<SVG>");
            } else if (BLOBCarver.isHEIC(v)) {
                setValue("<HEIC>");
            } else if (BLOBCarver.isPDF(v)) {
                setValue("<PDF>");
            } else if (BLOBCarver.isTIFF(v)) {
                setValue("<TIFF>");
            } else if (BLOBCarver.isBMP(v)) {
                setValue("<BMP>");
            } else if (BLOBCarver.isPLIST(v)) {
                setValue("<PLIST>");
            }
        }
        if (column < 2) {
            setHorizontalAlignment(2);
        }
        if (column == 1) {
            String v2 = (String) value;
            if (v2.startsWith("F")) {
                setIcon(icon_trash);
            } else if (v2.startsWith("D")) {
                setIcon(icon_deleted);
            } else if (v2.startsWith(Global.UNALLOCATED_SPACE)) {
                setIcon(icon_deleted);
            }
        } else {
            setIcon(null);
        }
        if (column == 0) {
            setText(Integer.toString(row));
            rendererComp.setBackground(Color.LIGHT_GRAY);
            setHorizontalAlignment(4);
        }
        if (this.walnode) {
            Object salt = table.getValueAt(row, 6);
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (this.gui != null) {
                this.gui.getRowcolors().containsKey(salt);
            }
        } else if (!isSelected && column > 0) {
            if (row % 2 == 0) {
                rendererComp.setBackground(rowcol);
            } else {
                rendererComp.setBackground(Color.WHITE);
            }
            if (column > 2) {
                rendererComp.setForeground(Color.BLUE);
            } else {
                rendererComp.setForeground(Color.BLACK);
            }
        }
        return rendererComp;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
