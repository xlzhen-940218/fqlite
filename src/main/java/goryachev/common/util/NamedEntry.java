package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/NamedEntry.class */
public class NamedEntry implements HasName, HasStringValue {
    private String name;
    private String value;

    public NamedEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override // goryachev.common.util.HasName
    public String getName() {
        return this.name;
    }

    @Override // goryachev.common.util.HasStringValue
    public String getStringValue() {
        return this.value;
    }
}
