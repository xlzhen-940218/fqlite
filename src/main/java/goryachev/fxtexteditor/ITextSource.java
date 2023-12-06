package goryachev.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/ITextSource.class */
public interface ITextSource {
    ITextLine nextLine();

    String nextPlainTextLine();

    int getStart();

    int getEnd();
}
