package goryachev.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Stack;
import javafx.fxml.FXMLLoader;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.apache.commons.codec.CharEncoding;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/FileTools.class */
public class FileTools {
    public static final String COPYRIGHT = "Copyright © Andy Goryachev <andy@goryachev.com>.  All Rights Reserved.";
    public static final String[] IMAGE = {".png", ".jpg", ".jpeg", ".gif"};
    public static final String[] JAVA = {".java"};
    public static final String[] HTML = {".html", ".htm"};
    public static final String[] XML = {".xml"};
    public static final String[] PROPERTIES = {".properties"};
    public static final String[] SYSTEM_FOLDERS = {"cvs", ".svn", "_svn", ".git", ".TemporaryItems"};
    public static final String[] SYSTEM_FILES = {"thumbs.db", ".DS_Store"};

    public static boolean isImage(File f) {
        return endsWith(f, IMAGE);
    }

    public static boolean isImage(String filename) {
        return endsWith(filename, IMAGE);
    }

    public static boolean isHtml(File f) {
        return endsWith(f, HTML);
    }

    public static boolean isHtml(String filename) {
        return endsWith(filename, HTML);
    }

    public static boolean isJava(File f) {
        return endsWith(f, JAVA);
    }

    public static boolean isJava(String filename) {
        return endsWith(filename, JAVA);
    }

    public static boolean isXml(File f) {
        return endsWith(f, XML);
    }

    public static boolean isXml(String filename) {
        return endsWith(filename, XML);
    }

    public static boolean isProperties(File f) {
        return endsWith(f, PROPERTIES);
    }

    public static boolean isHidden(File f) {
        if (f.isHidden()) {
            if (CPlatform.isWindows() && f.getParentFile() == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isHiddenOrSystem(File f) {
        if (isHidden(f) || isSystem(f)) {
            return true;
        }
        return false;
    }

    public static boolean isSystem(File f) {
        if (f.getName().startsWith(".")) {
            return true;
        }
        if (f.isDirectory()) {
            if (matches(f, SYSTEM_FOLDERS)) {
                return true;
            }
            return false;
        } else if (matches(f, SYSTEM_FILES)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean endsWith(File f, String[] extensions) {
        if (f == null) {
            return false;
        }
        return endsWith(f.getName(), extensions);
    }

    private static boolean endsWith(String name, String[] extensions) {
        if (name == null) {
            return false;
        }
        String name2 = name.toLowerCase();
        for (String ext : extensions) {
            if (name2.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matches(File f, String[] extensions) {
        if (f == null) {
            return false;
        }
        return matches(f.getName(), extensions);
    }

    private static boolean matches(String name, String[] extensions) {
        if (name == null) {
            return false;
        }
        for (String ext : extensions) {
            if (ext.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static String loadText(File f, String encoding, int max) throws Exception {
        byte[] buf = CKit.readBytes(f, max);
        if (encoding != null) {
            try {
                return new String(buf, encoding);
            } catch (Exception e) {
            }
        }
        return new String(buf);
    }

    public static String getPathToRootWithName(File root, File file) {
        SB prefix = filePrefix(root, file);
        if (prefix == null) {
            return null;
        }
        return prefix.toString();
    }

    private static SB filePrefix(File root, File file) {
        SB prefix;
        if (root.equals(file)) {
            return new SB();
        }
        File parent = file.getParentFile();
        if (parent == null || (prefix = filePrefix(root, parent)) == null) {
            return null;
        }
        if (prefix.length() > 0) {
            prefix.append('/');
        }
        prefix.append(file.getName());
        return prefix;
    }

    public static String getPathToRoot(File root, File file) {
        String rootPath = root.getAbsolutePath();
        String path = file.getAbsolutePath().substring(rootPath.length());
        String path2 = path.substring(0, path.length() - file.getName().length());
        if (path2.endsWith("/") || path2.endsWith(FXMLLoader.ESCAPE_PREFIX)) {
            path2 = path2.substring(0, path2.length() - 1);
        }
        if (path2.startsWith("/") || path2.startsWith(FXMLLoader.ESCAPE_PREFIX)) {
            return path2.substring(1);
        }
        return path2;
    }

    public static String getPathToParent(File parent, File f) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            f = f.getParentFile();
            if (f != null && !parent.equals(f)) {
                if (sb.length() > 0) {
                    sb.insert(0, "/");
                }
                sb.insert(0, f.getName());
                break;
            }
        }
        return sb.toString();
    }

    public static int getIntFileLength(File f) {
        long len = f.length();
        if (len <= 2147483647L) {
            return (int) len;
        }
        throw new RuntimeException("file length exceeds 2^31: " + len);
    }

    public static byte[] readByteArray(File file) throws Exception {
        int len = getIntFileLength(file);
        byte[] array = new byte[len];
        FileInputStream in = new FileInputStream(file);
        try {
            in.read(array);
            return array;
        } finally {
            CKit.close(in);
        }
    }

    public static byte[] readByteArray(String name) throws Exception {
        File file = new File(name);
        FileInputStream in = new FileInputStream(file);
        try {
            int len = getIntFileLength(file);
            byte[] data = new byte[len];
            in.read(data);
            return data;
        } finally {
            CKit.close(in);
        }
    }

    public static void writeByteArray(File file, byte[] data) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        try {
            out.write(data);
        } finally {
            CKit.close(out);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static InputStream getInputStream(String name) {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemResourceAsStream(name);
        } catch (Exception e) {
        }
        if (is == null) {
            try {
                is = new FileInputStream(name);
            } catch (Exception e2) {
            }
        }
        return is;
    }

    public static String loadText(String name) {
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        try {
            in = getInputStream(name);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    break;
                }
                sb.append((char) c);
            }
            CKit.close(in);
        } catch (Exception e) {
            CKit.close(in);
        } catch (Throwable th) {
            CKit.close(in);
            throw th;
        }
        return sb.toString();
    }

    public static String loadUnicodeText(String name) {
        StringBuilder sb = new StringBuilder();
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(getInputStream(name), CharEncoding.UTF_16);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    break;
                }
                sb.append((char) c);
            }
            CKit.close(in);
        } catch (Exception e) {
            CKit.close(in);
        } catch (Throwable th) {
            CKit.close(in);
            throw th;
        }
        return sb.toString();
    }

    public static Object loadObject(String name) throws Exception {
        ObjectInputStream in = new ObjectInputStream(getInputStream(name));
        try {
            Object d = in.readObject();
            return d;
        } finally {
            CKit.close(in);
        }
    }

    public static String loadFile(String file) throws Exception {
        File f = new File(file);
        byte[] b = new byte[(int) f.length()];
        FileInputStream in = new FileInputStream(f);
        try {
            in.read(b);
            return new String(b);
        } finally {
            CKit.close(in);
        }
    }

    public static void load(Properties p, String filename) throws Exception {
        FileInputStream in = new FileInputStream(filename);
        try {
            p.load(in);
        } finally {
            CKit.close(in);
        }
    }

    public static boolean isFileExist(File f) {
        try {
            if (f.exists()) {
                if (f.isFile()) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFolderExist(File f) {
        try {
            if (f.exists()) {
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void createNewFile(File f) throws Exception {
        ensureParentFolder(f);
        f.createNewFile();
    }

    public static void copy(File src, File dst) throws Exception {
        if (src.getAbsoluteFile().equals(dst.getAbsoluteFile())) {
            throw new Exception("same file");
        }
        long t = src.lastModified();
        FileInputStream in = new FileInputStream(src);
        copy(in, dst);
        dst.setLastModified(t);
    }

    public static void copy(InputStream src, File dst) throws Exception {
        try {
            FileOutputStream out = new FileOutputStream(dst);
            CKit.copy(src, out);
            CKit.close(out);
        } finally {
            CKit.close(src);
        }
    }

    public static boolean deleteRecursively(File file) throws Exception {
        File[] fs;
        CKit.checkCancelled();
        boolean result = true;
        if (file.exists()) {
            if (file.isDirectory() && (fs = file.listFiles()) != null) {
                for (File f : fs) {
                    result &= deleteRecursively(f);
                }
            }
            result &= file.delete();
        }
        return result;
    }

    public static boolean wildcardMatch(String filename, String wildcard) {
        return wildcardMatch(filename, wildcard, true);
    }

    public static boolean wildcardMatch(String filename, String wildcard, boolean caseSensitive) {
        if (filename == null && wildcard == null) {
            return true;
        }
        if (filename == null || wildcard == null) {
            return false;
        }
        String[] wcs = splitOnTokens(wildcard);
        boolean anyChars = false;
        int textIdx = 0;
        int wcsIdx = 0;
        Stack<int[]> backtrack = new Stack<>();
        do {
            if (backtrack.size() > 0) {
                int[] array = backtrack.pop();
                wcsIdx = array[0];
                textIdx = array[1];
                anyChars = true;
            }
            while (wcsIdx < wcs.length) {
                if (wcs[wcsIdx].equals("?")) {
                    textIdx++;
                    if (textIdx > filename.length()) {
                        break;
                    }
                    anyChars = false;
                    wcsIdx++;
                } else {
                    if (wcs[wcsIdx].equals(XPath.WILDCARD)) {
                        anyChars = true;
                        if (wcsIdx == wcs.length - 1) {
                            textIdx = filename.length();
                        }
                    } else if (anyChars) {
                        textIdx = checkIndexOf(filename, textIdx, wcs[wcsIdx], caseSensitive);
                        if (textIdx == -1) {
                            break;
                        }
                        int repeat = checkIndexOf(filename, textIdx + 1, wcs[wcsIdx], caseSensitive);
                        if (repeat >= 0) {
                            backtrack.push(new int[]{wcsIdx, repeat});
                        }
                        textIdx += wcs[wcsIdx].length();
                        anyChars = false;
                    } else {
                        if (!checkRegionMatches(filename, textIdx, wcs[wcsIdx], caseSensitive)) {
                            break;
                        }
                        textIdx += wcs[wcsIdx].length();
                        anyChars = false;
                    }
                    wcsIdx++;
                }
            }
            if (wcsIdx == wcs.length && textIdx == filename.length()) {
                return true;
            }
        } while (backtrack.size() > 0);
        return false;
    }

    protected static String[] splitOnTokens(String text) {
        if (text.indexOf(63) == -1 && text.indexOf(42) == -1) {
            return new String[]{text};
        }
        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '?' || array[i] == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (array[i] == '?') {
                    list.add("?");
                } else if (list.isEmpty() || (i > 0 && !list.get(list.size() - 1).equals(XPath.WILDCARD))) {
                    list.add(XPath.WILDCARD);
                }
            } else {
                buffer.append(array[i]);
            }
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    protected static int checkIndexOf(String str, int strStartIndex, String search, boolean caseSensitive) {
        int endIndex = str.length() - search.length();
        if (endIndex >= strStartIndex) {
            for (int i = strStartIndex; i <= endIndex; i++) {
                if (checkRegionMatches(str, i, search, caseSensitive)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    protected static boolean checkRegionMatches(String str, int strStartIndex, String search, boolean caseSensitive) {
        return str.regionMatches(!caseSensitive, strStartIndex, search, 0, search.length());
    }

    public static void touch(File f) throws Exception {
        if (f.exists()) {
            f.setLastModified(System.currentTimeMillis());
        } else {
            CKit.write(new byte[0], f);
        }
    }

    public static void ensureParentFolder(File f) {
        File folder;
        if (f != null && (folder = f.getParentFile()) != null) {
            folder.mkdirs();
        }
    }

    public static boolean rename(File f, String newName) {
        File dst = new File(f.getParent(), newName);
        return f.renameTo(dst);
    }

    public static void createBackup(File f) {
        File backup = new File(String.valueOf(f.getAbsolutePath()) + ".backup");
        backup.delete();
        f.renameTo(backup);
    }

    public static boolean isParent(File parent, File file) {
        while (file != null) {
            if (file.equals(parent)) {
                return true;
            }
            file = file.getParentFile();
        }
        return false;
    }

    public static boolean isEmptyDirectory(File f) {
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            if (fs == null || fs.length == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static String getCanonicalPath(File f) {
        if (f == null) {
            return null;
        }
        try {
            return f.getCanonicalPath();
        } catch (Exception e) {
            return f.getAbsolutePath();
        }
    }

    public static File inSameDir(File file, String name) {
        File parent = file.getParentFile();
        return new File(parent, name);
    }

    public static String getExtension(String name) {
        int ix;
        if (name != null && (ix = name.lastIndexOf(46)) >= 0) {
            return name.substring(ix + 1);
        }
        return null;
    }

    public static String getBaseName(String name) {
        if (name == null) {
            return null;
        }
        int ix = name.lastIndexOf(46);
        if (ix >= 0) {
            return name.substring(0, ix);
        }
        return name;
    }
}
