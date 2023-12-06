package fqlite.ui;

import fqlite.base.Global;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import javafx.scene.control.ButtonBar;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: fqlite_next.jar:fqlite/ui/FileInfo.class */
public class FileInfo {
    String filename;
    String sha256hash = ButtonBar.BUTTON_ORDER_NONE;
    String md5hash = ButtonBar.BUTTON_ORDER_NONE;
    String sha1 = ButtonBar.BUTTON_ORDER_NONE;
    StringBuilder sb = new StringBuilder();

    public FileInfo(String path) {
        this.filename = ButtonBar.BUTTON_ORDER_NONE;
        Path p = Paths.get(path, new String[0]);
        try {
            computeHashes(path);
            Map<String, Object> attributes = Files.readAttributes(p, XPath.WILDCARD, LinkOption.NOFOLLOW_LINKS);
            this.filename = p.getFileName().toString();
            this.sb.append("--------------------------------------------------------------------------------\n");
            this.sb.append(String.format("%80s%n", "FQlite Forensic Report"));
            this.sb.append(String.format("%80s%n", "created with version 2.2"));
            this.sb.append(String.format("%80s%n", "created by [" + System.getProperty("user.name") + "]"));
            this.sb.append("--------------------------------------------------------------------------------\n");
            this.sb.append("\n");
            this.sb.append(" File: " + String.valueOf(p.getFileName()));
            this.sb.append("\n");
            this.sb.append(" Path: " + path);
            this.sb.append("\n");
            this.sb.append(" Size: " + String.valueOf(attributes.get("size")) + " Bytes");
            this.sb.append("\n");
            this.sb.append("\n\n");
            this.sb.append("  Key                         Value                                         Remarks\n");
            printRow(Global.REGULAR_RECORD, "--------------", " ----------------------", "-----------------");
            int i = 1 + 1;
            printRow(String.valueOf(1), "creationTime    ", attributes.get("creationTime"), " of file on disk ");
            int i2 = i + 1;
            printRow(String.valueOf(i), "lastAccess        ", attributes.get("lastAccessTime"), " of file on disk ");
            int i3 = i2 + 1;
            printRow(String.valueOf(i2), "lastModified     ", attributes.get("lastModifiedTime"), " of file on disk ");
            this.sb.append("\n");
            try {
                this.sb.append("Hashes \n");
                String hexMD5 = this.md5hash;
                this.sb.append(" md5      " + hexMD5 + "\n");
                this.sb.append(" sha1     " + this.sha1 + "\n");
                String hex = this.sha256hash;
                this.sb.append(" sha256 " + hex + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void computeHashes(String path) {
        try {
            this.sha256hash = new DigestUtils(MessageDigestAlgorithms.SHA_256).digestAsHex(new File(path));
            this.md5hash = new DigestUtils(MessageDigestAlgorithms.MD5).digestAsHex(new File(path));
            this.sha1 = new DigestUtils(MessageDigestAlgorithms.SHA_1).digestAsHex(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printRowShort(String c0, String c1, Object c2) {
        this.sb.append(String.format("%s %-10s %s%n", c0, c1, String.valueOf(c2)));
    }

    private void printRow(String c0, String c1, Object c2, Object c3) {
        StringBuilder sb = this.sb;
        Object[] objArr = new Object[4];
        objArr[0] = c0;
        objArr[1] = c1;
        objArr[2] = String.valueOf(c2);
        objArr[3] = c3 instanceof Integer ? "$" + String.valueOf(c3) : c3;
        sb.append(String.format("%s %s %-30s %-15s%n", objArr));
    }

    public StringBuilder getReport() {
        return this.sb;
    }

    public static String center(String text, int len) {
        if (len <= text.length()) {
            return text.substring(0, len);
        }
        int before = (len - text.length()) / 2;
        if (before == 0) {
            return String.format("%-" + len + "s", text);
        }
        int rest = len - before;
        return String.format("%" + before + "s%-" + rest + "s", ButtonBar.BUTTON_ORDER_NONE, text);
    }

    public void print() {
        System.out.println(this.sb);
    }

    public String toString() {
        return this.sb.toString();
    }
}
