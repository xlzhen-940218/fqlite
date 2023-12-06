package nl.pvanassen.bplist.ext.nanoxml;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/ext/nanoxml/XMLParseException.class */
public class XMLParseException extends RuntimeException {
    private static final long serialVersionUID = 6392534332039988250L;
    public static final int NO_LINE = -1;
    private int lineNr;

    public XMLParseException(String name, String message) {
        super("XML Parse Exception during parsing of " + (name == null ? "the XML definition" : "a " + name + " element") + ": " + message);
        this.lineNr = -1;
    }

    public XMLParseException(String name, int lineNr, String message) {
        super("XML Parse Exception during parsing of " + (name == null ? "the XML definition" : "a " + name + " element") + " at line " + lineNr + ": " + message);
        this.lineNr = lineNr;
    }

    public int getLineNr() {
        return this.lineNr;
    }
}
