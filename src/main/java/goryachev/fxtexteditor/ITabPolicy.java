package goryachev.fxtexteditor;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fxtexteditor/ITabPolicy.class */
public interface ITabPolicy {
    boolean isSimple();

    int nextTabStop(int i);
}
