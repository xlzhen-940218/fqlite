package nl.pvanassen.bplist.ext.nanoxml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/ext/nanoxml/XMLElement.class */
public class XMLElement {
    static final long serialVersionUID = 6685035139346394777L;
    public static final int NANOXML_MAJOR_VERSION = 2;
    public static final int NANOXML_MINOR_VERSION = 2;
    private Map<String, String> attributes;
    private final List<XMLElement> children;
    private String name;
    private String contents;
    private Map<String, char[]> entities;
    private int lineNr;
    private boolean ignoreCase;
    private boolean ignoreWhitespace;
    private char charReadTooMuch;
    private Reader reader;
    private int parserLineNr;

    public XMLElement() {
        this(new HashMap(), false, true, true);
    }

    public XMLElement(Map<String, char[]> entities) {
        this(entities, false, true, true);
    }

    public XMLElement(boolean skipLeadingWhitespace) {
        this(new HashMap(), skipLeadingWhitespace, true, true);
    }

    public XMLElement(Map<String, char[]> entities, boolean skipLeadingWhitespace) {
        this(entities, skipLeadingWhitespace, true, true);
    }

    public XMLElement(Map<String, char[]> entities, boolean skipLeadingWhitespace, boolean ignoreCase) {
        this(entities, skipLeadingWhitespace, true, ignoreCase);
    }

    protected XMLElement(Map<String, char[]> entities, boolean skipLeadingWhitespace, boolean fillBasicConversionTable, boolean ignoreCase) {
        this.ignoreWhitespace = skipLeadingWhitespace;
        this.ignoreCase = ignoreCase;
        this.name = null;
        this.contents = ButtonBar.BUTTON_ORDER_NONE;
        this.attributes = new HashMap();
        this.children = new LinkedList();
        this.entities = entities;
        this.lineNr = 0;
        if (fillBasicConversionTable) {
            this.entities.put("amp", new char[]{'&'});
            this.entities.put("quot", new char[]{'\"'});
            this.entities.put("apos", new char[]{'\''});
            this.entities.put("lt", new char[]{'<'});
            this.entities.put("gt", new char[]{'>'});
        }
    }

    public void addChild(XMLElement child) {
        this.children.add(child);
    }

    public void setAttribute(String name, Object value) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        this.attributes.put(name, value.toString());
    }

    public void setIntAttribute(String name, int value) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        this.attributes.put(name, Integer.toString(value));
    }

    public void setDoubleAttribute(String name, double value) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        this.attributes.put(name, Double.toString(value));
    }

    public int countChildren() {
        return this.children.size();
    }

    public Iterator<String> enumerateAttributeNames() {
        return this.attributes.keySet().iterator();
    }

    public Iterator<XMLElement> iterateChildren() {
        return this.children.iterator();
    }

    public List<XMLElement> getChildren() {
        return new ArrayList(this.children);
    }

    public XMLElement getFirstChildWithName(String name) {
        for (XMLElement elem : this.children) {
            if (name.equals(elem.getName())) {
                return elem;
            }
        }
        return null;
    }

    public List<XMLElement> getChildrenWithName(String name) {
        List<XMLElement> elements = new LinkedList<>();
        for (XMLElement elem : this.children) {
            if (name.equals(elem.getName())) {
                elements.add(elem);
            }
        }
        return elements;
    }

    public String getContent() {
        return this.contents;
    }

    public int getLineNr() {
        return this.lineNr;
    }

    public Object getAttribute(String name) {
        return getAttribute(name, null);
    }

    public Object getAttribute(String name, Object defaultValue) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        Object value = this.attributes.get(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public Object getAttribute(String name, Map<String, Object> valueSet, String defaultKey, boolean allowLiterals) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        Object key = (String) this.attributes.get(name);
        if (key == null) {
            key = defaultKey;
        }
        Object result = valueSet.get(key);
        if (result == null) {
            if (allowLiterals) {
                result = key;
            } else {
                throw invalidValue(name, (String) key);
            }
        }
        return result;
    }

    public String getStringAttribute(String name) {
        return getStringAttribute(name, null);
    }

    public String getStringAttribute(String name, String defaultValue) {
        return (String) getAttribute(name, defaultValue);
    }

    public String getStringAttribute(String name, Map<String, Object> valueSet, String defaultKey, boolean allowLiterals) {
        return (String) getAttribute(name, valueSet, defaultKey, allowLiterals);
    }

    public int getIntAttribute(String name) {
        return getIntAttribute(name, 0);
    }

    public int getIntAttribute(String name, int defaultValue) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        String value = this.attributes.get(name);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw invalidValue(name, value);
        }
    }

    public int getIntAttribute(String name, Map<String, Object> valueSet, String defaultKey, boolean allowLiteralNumbers) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        Object key = this.attributes.get(name);
        if (key == null) {
            key = defaultKey;
        }
        try {
            Integer result = (Integer) valueSet.get(key);
            if (result == null) {
                if (!allowLiteralNumbers) {
                    throw invalidValue(name, (String) key);
                }
                try {
                    result = Integer.valueOf((String) key);
                } catch (NumberFormatException e) {
                    throw invalidValue(name, (String) key);
                }
            }
            return result.intValue();
        } catch (ClassCastException e2) {
            throw invalidValueSet(name);
        }
    }

    public double getDoubleAttribute(String name) {
        return getDoubleAttribute(name, 0.0d);
    }

    public double getDoubleAttribute(String name, double defaultValue) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        String value = this.attributes.get(name);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value).doubleValue();
        } catch (NumberFormatException e) {
            throw invalidValue(name, value);
        }
    }

    public double getDoubleAttribute(String name, Map<String, Object> valueSet, String defaultKey, boolean allowLiteralNumbers) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        Object key = this.attributes.get(name);
        if (key == null) {
            key = defaultKey;
        }
        try {
            Double result = (Double) valueSet.get(key);
            if (result == null) {
                if (!allowLiteralNumbers) {
                    throw invalidValue(name, (String) key);
                }
                try {
                    result = Double.valueOf((String) key);
                } catch (NumberFormatException e) {
                    throw invalidValue(name, (String) key);
                }
            }
            return result.doubleValue();
        } catch (ClassCastException e2) {
            throw invalidValueSet(name);
        }
    }

    public boolean getBooleanAttribute(String name, String trueValue, String falseValue, boolean defaultValue) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        Object value = this.attributes.get(name);
        if (value == null) {
            return defaultValue;
        }
        if (value.equals(trueValue)) {
            return true;
        }
        if (value.equals(falseValue)) {
            return false;
        }
        throw invalidValue(name, (String) value);
    }

    public String getName() {
        return this.name;
    }

    public void removeChild(XMLElement child) {
        this.children.remove(child);
    }

    public void removeAttribute(String name) {
        if (this.ignoreCase) {
            name = name.toUpperCase();
        }
        this.attributes.remove(name);
    }

    public XMLElement createAnotherElement() {
        return new XMLElement(this.entities, this.ignoreWhitespace, false, this.ignoreCase);
    }

    public void setContent(String content) {
        this.contents = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            write(writer);
            writer.flush();
            return new String(out.toByteArray());
        } catch (IOException e) {
            return super.toString();
        }
    }

    public void write(Writer writer) throws IOException {
        if (this.name == null) {
            writeEncoded(writer, this.contents);
            return;
        }
        writer.write(60);
        writer.write(this.name);
        if (!this.attributes.isEmpty()) {
            for (String key : this.attributes.keySet()) {
                writer.write(32);
                String value = this.attributes.get(key);
                writer.write(key);
                writer.write(61);
                writer.write(34);
                writeEncoded(writer, value);
                writer.write(34);
            }
        }
        if (this.contents != null && this.contents.length() > 0) {
            writer.write(62);
            writeEncoded(writer, this.contents);
            writer.write(60);
            writer.write(47);
            writer.write(this.name);
            writer.write(62);
        } else if (this.children.isEmpty()) {
            writer.write(47);
            writer.write(62);
        } else {
            writer.write(62);
            Iterator<XMLElement> iter = iterateChildren();
            while (iter.hasNext()) {
                XMLElement child = iter.next();
                child.write(writer);
            }
            writer.write(60);
            writer.write(47);
            writer.write(this.name);
            writer.write(62);
        }
    }

    private void writeEncoded(Writer writer, String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '\"':
                    writer.write(38);
                    writer.write(113);
                    writer.write(117);
                    writer.write(111);
                    writer.write(116);
                    writer.write(59);
                    break;
                case '&':
                    writer.write(38);
                    writer.write(97);
                    writer.write(109);
                    writer.write(112);
                    writer.write(59);
                    break;
                case '\'':
                    writer.write(38);
                    writer.write(97);
                    writer.write(112);
                    writer.write(111);
                    writer.write(115);
                    writer.write(59);
                    break;
                case '<':
                    writer.write(38);
                    writer.write(108);
                    writer.write(116);
                    writer.write(59);
                    break;
                case '>':
                    writer.write(38);
                    writer.write(103);
                    writer.write(116);
                    writer.write(59);
                    break;
                default:
                    if (ch < ' ' || ch > '~') {
                        writer.write(38);
                        writer.write(35);
                        writer.write(120);
                        writer.write(Integer.toString(ch, 16));
                        writer.write(59);
                        break;
                    } else {
                        writer.write(ch);
                        break;
                    }
            }
        }
    }

    private XMLParseException invalidValueSet(String name) {
        String msg = "Invalid value set (entity name = \"" + name + "\")";
        return new XMLParseException(getName(), this.parserLineNr, msg);
    }

    private XMLParseException invalidValue(String name, String value) {
        String msg = "Attribute \"" + name + "\" does not contain a valid value (\"" + value + "\")";
        return new XMLParseException(getName(), this.parserLineNr, msg);
    }
}
