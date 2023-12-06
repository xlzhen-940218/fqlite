package fqlite.types;

/* loaded from: fqlite_next.jar:fqlite/types/BLOBElement.class */
public class BLOBElement {
    public byte[] binary;
    public BLOBTYPE type;

    public BLOBElement(byte[] content, BLOBTYPE type) {
        this.binary = content;
        this.type = type;
    }
}
