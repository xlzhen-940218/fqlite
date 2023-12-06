package fqlite.ui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/* loaded from: fqlite_next.jar:fqlite/ui/CopyAction.class */
public class CopyAction extends AbstractAction {
    private static final long serialVersionUID = -5863604468463177615L;
    private JTable table;

    public CopyAction(JTable table) {
        this.table = table;
        putValue("Name", "Copy");
    }

    public void actionPerformed(ActionEvent e) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        int[] rows = this.table.getSelectedRows();
        int[] columns = this.table.getSelectedColumns();
        StringBuffer selection = new StringBuffer();
        for (int r : rows) {
            for (int c : columns) {
                selection.append((String) this.table.getValueAt(r, c));
                selection.append(";");
            }
            selection.append("\n");
        }
        cb.setContents(new StringSelection(selection.toString()), (ClipboardOwner) null);
    }
}
