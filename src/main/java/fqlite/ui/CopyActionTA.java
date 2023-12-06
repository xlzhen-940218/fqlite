package fqlite.ui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;

/* loaded from: fqlite_next.jar:fqlite/ui/CopyActionTA.class */
public class CopyActionTA extends AbstractAction {
    private static final long serialVersionUID = -5863604468463177615L;
    private JTextArea ta;

    public CopyActionTA(JTextArea ta) {
        this.ta = ta;
        putValue("Name", "Copy");
    }

    public void actionPerformed(ActionEvent e) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringBuffer selection = new StringBuffer();
        selection.append(this.ta.getSelectedText());
        cb.setContents(new StringSelection(selection.toString()), (ClipboardOwner) null);
    }
}
