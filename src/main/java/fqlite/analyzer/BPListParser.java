package fqlite.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import nl.pvanassen.bplist.converter.ConvertToXml;
import nl.pvanassen.bplist.ext.nanoxml.XMLElement;
import nl.pvanassen.bplist.parser.BPListElement;
import nl.pvanassen.bplist.parser.ElementParser;

/* loaded from: fqlite_next.jar:fqlite/analyzer/BPListParser.class */
public class BPListParser {
    private static final ConvertToXml convetToXml = new ConvertToXml();
    private static final ElementParser elementParser = new ElementParser();

    public static String parse(String path) {
        try {
            List<BPListElement<?>> elements = elementParser.parseObjectTable(new File(path));
            XMLElement xmlElement = convetToXml.convertToXml(elements);
            return xmlElement.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "<no valid bplist>";
        }
    }
}
