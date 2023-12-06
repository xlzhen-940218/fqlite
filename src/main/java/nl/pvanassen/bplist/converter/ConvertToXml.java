package nl.pvanassen.bplist.converter;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import nl.pvanassen.bplist.ext.base64.Base64;
import nl.pvanassen.bplist.ext.nanoxml.XMLElement;
import nl.pvanassen.bplist.parser.BPListElement;
import nl.pvanassen.bplist.parser.BPListType;
import nl.pvanassen.bplist.parser.ElementParser;


/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/converter/ConvertToXml.class */
public class ConvertToXml {
    private static final DatatypeFactory DATATYPE_FACTORY;
    private final ElementParser parser = new ElementParser();

    static {
        try {
            DATATYPE_FACTORY = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            throw new RuntimeException("Can't create XML datatype factory.", ex);
        }
    }

    public XMLElement convertToXml(File file) throws IOException {
        XMLElement root = new XMLElement(new HashMap(), false, false);
        root.setName("plist");
        root.setAttribute("version", "1.0");
        convertObjectTableToXML(root, this.parser.parseObjectTable(file).get(0));
        return root;
    }

    public XMLElement convertToXml(List<BPListElement<?>> list) throws IOException {
        XMLElement root = new XMLElement(new HashMap(), false, false);
        root.setName("plist");
        root.setAttribute("version", "1.0");
        convertObjectTableToXML(root, list.get(0));
        return root;
    }

    private void convertObjectTableToXML(XMLElement parent, BPListElement<?> object) {
        XMLElement elem = parent.createAnotherElement();
        if (object.getType() == BPListType.SHORT_DICT || object.getType() == BPListType.BYTE_DICT) {
            Map<String, BPListElement<?>> dictionary = (Map) object.getValue();
            elem.setName("dict");
            for (Map.Entry<String, BPListElement<?>> entry : dictionary.entrySet()) {
                XMLElement key = parent.createAnotherElement();
                key.setName("key");
                key.setContent(entry.getKey());
                elem.addChild(key);
                convertObjectTableToXML(elem, entry.getValue());
            }
        } else if (object.getType() == BPListType.SHORT_ARRAY || object.getType() == BPListType.BYTE_ARRAY) {
            List<BPListElement<?>> elements = (List) object.getValue();
            elem.setName("array");
            for (BPListElement<?> element : elements) {
                convertObjectTableToXML(elem, element);
            }
        } else if (object.getType() == BPListType.ASCII_STRING || object.getType() == BPListType.UNICODE_STRING) {
            elem.setName("string");
            elem.setContent(object.getValue().toString());
        } else if (object.getType() == BPListType.LONG) {
            elem.setName("integer");
            elem.setContent(object.getValue().toString());
        } else if (object.getType() == BPListType.FLOAT) {
            elem.setName("real");
            elem.setContent(object.getValue().toString());
        } else if (object.getType() == BPListType.DOUBLE) {
            elem.setName("real");
            elem.setContent(object.getValue().toString());
        } else if (object.getType() == BPListType.BOOLEAN) {
            elem.setName("boolean");
            elem.setContent(object.getValue().toString());
        } else if (object.getType() == BPListType.DATA) {
            elem.setName("data");
            elem.setContent(Base64.encodeBytes((byte[]) object.getValue(), 8));
        } else if (object.getType() == BPListType.DATE) {
            elem.setName("date");
            elem.setContent(fromDate((Date) object.getValue()).toXMLFormat() + "Z");
        } else if (object.getType() == BPListType.UID) {
            elem.setName("UID");
            elem.setContent(object.getValue().toString());
        } else {
            elem.setName("unsupported");
            elem.setContent(object.toString());
        }
        parent.addChild(elem);
    }

    private static XMLGregorianCalendar fromDate(Date date) {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gc.setTime(date);
        XMLGregorianCalendar xmlgc = DATATYPE_FACTORY.newXMLGregorianCalendar(gc);
        xmlgc.setFractionalSecond(null);
        xmlgc.setTimezone(Integer.MIN_VALUE);
        return xmlgc;
    }
}
