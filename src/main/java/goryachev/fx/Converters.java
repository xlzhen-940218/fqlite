package goryachev.fx;

import goryachev.common.util.Parsers;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/Converters.class */
public class Converters {
    protected static StringConverter<Boolean> booleanConverter;
    protected static StringConverter<Integer> intConverter;
    protected static StringConverter<Number> doubleNumberConverter;
    protected static StringConverter<Number> intNumberConverter;
    protected static StringConverter<String> stringConverter;
    protected static StringConverter<Object> objectConverter;

    public static <T> StringConverter<T> get(Property<T> p) {
        if (p instanceof BooleanProperty) {
            return (StringConverter<T>) BOOLEAN();
        }
        if (p instanceof IntegerProperty) {
            return (StringConverter<T>) INT();
        }
        if (p instanceof DoubleProperty) {
            return (StringConverter<T>) NUMBER_DOUBLE();
        }
        if (p instanceof StringProperty) {
            return (StringConverter<T>) STRING();
        }
        throw new Error("?" + p);
    }

    public static StringConverter<Boolean> BOOLEAN() {
        if (booleanConverter == null) {
            booleanConverter = new StringConverter<Boolean>() { // from class: goryachev.fx.Converters.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javafx.util.StringConverter
                public Boolean fromString(String s) {
                    return Parsers.parseBoolean(s);
                }

                @Override // javafx.util.StringConverter
                public String toString(Boolean x) {
                    return Boolean.TRUE.equals(x) ? "true" : "false";
                }
            };
        }
        return booleanConverter;
    }

    public static StringConverter<Integer> INT() {
        if (intConverter == null) {
            intConverter = new StringConverter<Integer>() { // from class: goryachev.fx.Converters.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javafx.util.StringConverter
                public Integer fromString(String s) {
                    return Integer.valueOf(Parsers.parseInt(s, 0));
                }

                @Override // javafx.util.StringConverter
                public String toString(Integer x) {
                    return String.valueOf(x);
                }
            };
        }
        return intConverter;
    }

    public static StringConverter<Number> NUMBER_INT() {
        if (intNumberConverter == null) {
            intNumberConverter = new StringConverter<Number>() { // from class: goryachev.fx.Converters.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javafx.util.StringConverter
                public Number fromString(String s) {
                    return Integer.valueOf(Parsers.parseInt(s, 0));
                }

                @Override // javafx.util.StringConverter
                public String toString(Number x) {
                    return String.valueOf(x);
                }
            };
        }
        return intNumberConverter;
    }

    public static StringConverter<Number> NUMBER_DOUBLE() {
        if (doubleNumberConverter == null) {
            doubleNumberConverter = new StringConverter<Number>() { // from class: goryachev.fx.Converters.4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javafx.util.StringConverter
                public Number fromString(String s) {
                    return Double.valueOf(Parsers.parseDouble(s, 0.0d));
                }

                @Override // javafx.util.StringConverter
                public String toString(Number x) {
                    return String.valueOf(x);
                }
            };
        }
        return doubleNumberConverter;
    }

    public static StringConverter<String> STRING() {
        if (stringConverter == null) {
            stringConverter = new StringConverter<String>() { // from class: goryachev.fx.Converters.5
                @Override // javafx.util.StringConverter
                public String toString(String s) {
                    return s;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javafx.util.StringConverter
                public String fromString(String s) {
                    return s;
                }
            };
        }
        return stringConverter;
    }

    public static StringConverter<Object> OBJECT() {
        if (objectConverter == null) {
            objectConverter = new StringConverter<Object>() { // from class: goryachev.fx.Converters.6
                @Override // javafx.util.StringConverter
                public String toString(Object x) {
                    if (x == null) {
                        return null;
                    }
                    return x.toString();
                }

                @Override // javafx.util.StringConverter
                public Object fromString(String s) {
                    return s;
                }
            };
        }
        return objectConverter;
    }
}
