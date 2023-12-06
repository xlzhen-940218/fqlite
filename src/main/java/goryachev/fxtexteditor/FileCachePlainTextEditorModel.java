package goryachev.fxtexteditor;

import goryachev.common.util.text.IBreakIterator;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;


/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/FileCachePlainTextEditorModel.class */
public class FileCachePlainTextEditorModel extends FxTextEditorModel {
    protected String[] lines;
    private int lineCount;
    private RandomAccessFile raf;
    private ReentrantLock lock = new ReentrantLock();
    private long length;
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public FileCachePlainTextEditorModel(File f) {
        this.length = 0L;
        this.lineCount = (((int) f.length()) / 16) + 1;
        this.lines = new String[this.lineCount];
        this.length = f.length();
        try {
            this.raf = new RandomAccessFile(f, "r");
            loadLines(0, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public IBreakIterator getBreakIterator() {
        return null;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public Edit edit(Edit ed) throws Exception {
        throw new Exception("not supported");
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public int getLineCount() {
        return this.lineCount;
    }

    public long length() {
        return this.length;
    }

    @Override // goryachev.fxtexteditor.FxTextEditorModel
    public ITextLine getTextLine(int line) {
        if (line >= this.lineCount) {
            return null;
        }
        this.lock.lock();
        try {
            if (this.lines[line] == null) {
                loadLines(line, 10);
            }
            String text = this.lines[line];
            if (text != null) {
                return new PlainTextLine(line, text);
            }
            this.lock.unlock();
            return null;
        } finally {
            this.lock.unlock();
        }
    }

    public synchronized void loadLines(int start, int numberoflines) {
        try {
            readFromFile(start, numberoflines);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public synchronized void readFromFile(int linetostart, int numberoflines) throws Exception {
        int total = numberoflines * 16;
        char[] tarray = new char[16];
        int linenumber = linetostart;
        StringBuilder sb = new StringBuilder(50);
        this.raf.seek(linetostart * 16);
        this.lock.lock();
        int inhexline = 0;
        int k = 0;
        for (int i = 0; i < total; i++) {
            try {
                int nextbyte = this.raf.read();
                if (nextbyte == -1) {
                    return;
                }
                sb.append(HEX[(240 & nextbyte) >>> 4]);
                sb.append(HEX[15 & nextbyte]);
                if (nextbyte < 32 || nextbyte >= 127) {
                    tarray[k] = '.';
                } else {
                    tarray[k] = (char) nextbyte;
                }
                k++;
                inhexline += 2;
                if (inhexline == 32) {
                    inhexline = 0;
                    sb.append("\t");
                    sb.append(tarray);
                    sb.append("\n");
                    this.lines[linenumber] = new String(sb.toString());
                    sb = new StringBuilder(50);
                    linenumber++;
                    k = 0;
                    if (linenumber >= this.lineCount || this.lines[linenumber] != null) {
                        return;
                    }
                }
            } finally {
                this.lock.unlock();
            }
        }
    }
}
