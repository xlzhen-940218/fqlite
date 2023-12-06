package nl.pvanassen.bplist.parser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/parser/BPListElement.class */
public interface BPListElement<T> {
    BPListType getType();

    T getValue();
}
