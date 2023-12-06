package goryachev.common.io;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/IBitStream.class */
public interface IBitStream {
    boolean hasMoreBits();

    int getIndex();

    int nextBits(int i) throws Exception;

    int nextBit() throws Exception;

    void skip(int i);
}
