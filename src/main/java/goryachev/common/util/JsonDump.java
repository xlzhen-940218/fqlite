package goryachev.common.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import javafx.fxml.FXMLLoader;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/JsonDump.class */
public class JsonDump {
    private static final String CIRCULAR_REFERENCE = "↺";
    private final CSet<Object> all = new CSet<>();
    private final SB sb;
    private final String indent;
    private final boolean prettyPrint;
    private final Object value;
    private static final Comparator<Item> COMPARATOR = createComparator();

    public JsonDump(SB sb, String indent, boolean prettyPrint, Object v) {
        this.sb = sb;
        this.indent = indent;
        this.prettyPrint = prettyPrint;
        this.value = v;
    }

    public static String print(Object x) {
        SB sb = new SB();
        new JsonDump(sb, null, false, x).print();
        return sb.toString();
    }

    public static String prettyPrint(Object x) {
        SB sb = new SB();
        new JsonDump(sb, "  ", true, x).print();
        return sb.toString();
    }

    private static Comparator<Item> createComparator() {
        return new CComparator<Item>() { // from class: goryachev.common.util.JsonDump.1
            @Override // goryachev.common.util.CComparator, java.util.Comparator
            public int compare(Item a, Item b) {
                return collate(a.getName(), b.getName());
            }
        };
    }

    public void print() {
        print(0, this.value);
    }

    protected boolean checkDup(Object x) {
        if (this.all.contains(x)) {
            this.sb.append(CIRCULAR_REFERENCE);
            return true;
        }
        this.all.add(x);
        return false;
    }

    protected void print(int level, Object x) {
        CKit.checkCancelled();
        if (x == null) {
            this.sb.append(FXMLLoader.NULL_KEYWORD);
            return;
        }
        Class c = x.getClass();
        if (c == String.class) {
            String s = toJsonString(x);
            this.sb.append(s);
        } else if (c == byte[].class) {
            printByteArray((byte[]) x);
        } else if (isPrimitive(c)) {
            this.sb.append(x.toString());
        } else if (c.isArray()) {
            if (checkDup(x)) {
                return;
            }
            printArray(level, x);
        } else if (x instanceof Collection) {
            if (checkDup(x)) {
                return;
            }
            printCollection(level, (Collection) x);
        } else if (x instanceof Map) {
            if (checkDup(x)) {
                return;
            }
            printMap(level, (Map) x);
        } else if (isForbidden(c)) {
            String s2 = c.getName();
            this.sb.append(s2);
        } else if (checkDup(x)) {
        } else {
            printObject(level, x);
        }
    }

    protected static boolean isForbidden(Class c) {
        String name = c.getName();
        if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("java.awt.") || name.startsWith("javax.swing") || name.startsWith("javafx.")) {
            return true;
        }
        return false;
    }

    protected static boolean isPrimitive(Class c) {
        return c.isPrimitive() || c == Boolean.TYPE || c == Boolean.class || c == Byte.TYPE || c == Byte.class || c == Character.TYPE || c == Character.class || c == Short.TYPE || c == Short.class || c == Integer.TYPE || c == Integer.class || c == Long.TYPE || c == Long.class || c == Float.TYPE || c == Float.class || c == Double.TYPE || c == Double.class;
    }

    public static String toJsonString(Object x) {
        String text = x.toString();
        int len = text.length();
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            switch (c) {
                case ' ':
                case '\"':
                case '&':
                case '\'':
                case ',':
                case ':':
                case '<':
                case '=':
                case '>':
                case '\\':
                    return escapeString(text, i);
                default:
                    if (c < ' ') {
                        return escapeString(text, i);
                    }
            }
        }
        return text;
    }

    protected static String escapeString(String text, int start) {
        int len = text.length();
        SB sb = new SB(len + len);
        sb.append('\"');
        sb.append((CharSequence) text, 0, start);
        for (int i = start; i < len; i++) {
            char c = text.charAt(i);
            switch (c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                case '&':
                    sb.append("\\u0026");
                    break;
                case '\'':
                    sb.append("\\u0027");
                    break;
                case '<':
                    sb.append("\\u003c");
                    break;
                case '=':
                    sb.append("\\u003d");
                    break;
                case '>':
                    sb.append("\\u003e");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    if (c < ' ') {
                        sb.append(String.format("\\u%04x", Integer.valueOf(c)));
                        break;
                    } else {
                        sb.append(c);
                        break;
                    }
            }
        }
        sb.append('\"');
        return sb.toString();
    }

    protected void printArray(int level, Object x) {
        this.sb.a("[");
        int len = Array.getLength(x);
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                this.sb.append(",");
            }
            Object v = Array.get(x, i);
            print(level + 1, v);
        }
        this.sb.a("]");
    }

    protected void printCollection(int level, Collection coll) {
        this.sb.a("[");
        boolean sep = false;
        for (Object v : coll) {
            if (sep) {
                this.sb.append(",");
            } else {
                sep = true;
            }
            print(level + 1, v);
        }
        this.sb.a("]");
    }

    protected void printMap(int level, final Map x) {
        CList<Item> items = new CList<>();
        Iterator it = x.keySet().iterator();
        while (it.hasNext()) {
            final Object k = it.next();
            items.add(new Item(k, k == null ? null : k.toString()) { // from class: goryachev.common.util.JsonDump.2
                @Override // goryachev.common.util.JsonDump.Item
                public String getFullyQualifiedName() {
                    if (k == null) {
                        return FXMLLoader.NULL_KEYWORD;
                    }
                    return k + "(" + k.getClass() + ")";
                }

                @Override // goryachev.common.util.JsonDump.Item
                public Object getValue() {
                    return x.get(k);
                }
            });
        }
        printFields(level, items);
    }

    protected void printObject(int level, final Object x) {
        CList<Field> fields = new CList<>();
        Class cls = x.getClass();
        while (true) {
            Class c = cls;
            if (c == Object.class) {
                break;
            }
            Field[] fs = c.getDeclaredFields();
            for (Field f : fs) {
                int m = f.getModifiers();
                if (!Modifier.isStatic(m)) {
                    f.setAccessible(true);
                    fields.add(f);
                }
            }
            cls = c.getSuperclass();
        }
        CList<Item> items = new CList<>(fields.size());
        Iterator<?> it = fields.iterator();
        while (it.hasNext()) {
            final Field f2 = (Field) it.next();
            items.add(new Item(f2, f2.getName()) { // from class: goryachev.common.util.JsonDump.3
                @Override // goryachev.common.util.JsonDump.Item
                public String getFullyQualifiedName() {
                    return f2.toString();
                }

                @Override // goryachev.common.util.JsonDump.Item
                public Object getValue() {
                    try {
                        return f2.get(x);
                    } catch (Throwable e) {
                        return e.getClass().getSimpleName();
                    }
                }
            });
        }
        printFields(level, items);
    }

    protected void printFields(int level, CList<Item> items) {
        CList<Item> duplicates = null;
        CMap<String, Item> names = new CMap<>(items.size());
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            String name = item.getName();
            Item old = names.get(name);
            if (old != null) {
                if (duplicates == null) {
                    duplicates = new CList<>();
                }
                duplicates.add(old);
                duplicates.add(item);
            } else {
                names.put(name, item);
            }
        }
        if (duplicates != null) {
            Iterator<?> it2 = duplicates.iterator();
            while (it2.hasNext()) {
                Item item2 = (Item) it2.next();
                String s = item2.getFullyQualifiedName();
                item2.setName(s);
            }
        }
        Collections.sort(items, COMPARATOR);
        this.sb.a("{");
        boolean sep = false;
        Iterator<Item> it3 = items.iterator();
        while (it3.hasNext()) {
            Item k = it3.next();
            if (sep) {
                this.sb.append(",");
            } else {
                sep = true;
            }
            String s2 = toJsonString(k.getName());
            this.sb.append(s2).append(":");
            Object v = k.getValue();
            print(level + 1, v);
        }
        this.sb.a("}");
    }

    protected void printByteArray(byte[] bytes) {
        if (bytes.length < 80) {
            for (byte b : bytes) {
                this.sb.append(Hex.toHexChar(b >> 4));
                this.sb.append(Hex.toHexChar(b));
            }
            return;
        }
        for (int i = 0; i < 40; i++) {
            byte b2 = bytes[i];
            this.sb.append(Hex.toHexChar(b2 >> 4));
            this.sb.append(Hex.toHexChar(b2));
        }
        this.sb.append("…");
        for (int i2 = 0; i2 < 40; i2++) {
            byte b3 = bytes[(bytes.length - 40) + i2];
            this.sb.append(Hex.toHexChar(b3 >> 4));
            this.sb.append(Hex.toHexChar(b3));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/JsonDump$Item.class */
    public static abstract class Item {
        public final Object key;
        private String name;

        public abstract String getFullyQualifiedName();

        public abstract Object getValue();

        public Item(Object key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getName() {
            return this.name == null ? this.key.toString() : this.name;
        }

        public void setName(String s) {
            this.name = s;
        }
    }
}
