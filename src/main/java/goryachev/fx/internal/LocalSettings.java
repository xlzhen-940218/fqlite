package goryachev.fx.internal;

import goryachev.common.util.CMap;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.SStream;
import goryachev.fx.Converters;
import goryachev.fx.FxAction;
import goryachev.fx.FxDouble;
import goryachev.fx.FxInt;
import goryachev.fx.HasSettings;
import goryachev.fx.SSConverter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import javafx.stage.Window;
import javafx.util.StringConverter;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/LocalSettings.class */
public class LocalSettings {
    private final CMap<String, Entry> entries = new CMap<>();
    private static final Object PROP_BINDINGS = new Object();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/internal/LocalSettings$Entry.class */
    public abstract class Entry {
        public abstract void saveValue(String str);

        public abstract void loadValue(String str);

        protected Entry() {
        }
    }

    public static LocalSettings get(Node n) {
        LocalSettings s = find(n);
        if (s == null) {
            s = new LocalSettings();
            n.getProperties().put(PROP_BINDINGS, s);
        }
        return s;
    }

    public static LocalSettings get(Window w) {
        LocalSettings s = find(w);
        if (s == null) {
            s = new LocalSettings();
            w.getProperties().put(PROP_BINDINGS, s);
        }
        return s;
    }

    public static LocalSettings find(Node n) {
        return (LocalSettings) n.getProperties().get(PROP_BINDINGS);
    }

    public static LocalSettings find(Window w) {
        return (LocalSettings) w.getProperties().get(PROP_BINDINGS);
    }

    public <T> LocalSettings add(final String subKey, final Property<T> p, StringConverter<T> c) {
        final StringConverter<T> conv = c == null ? Converters.get(p) : c;
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {

            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                T v = p.getValue();
                String s = v == null ? null : conv.toString(v);
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, s);
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String s = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                if (s != null) {
                    p.setValue(conv.fromString(s));
                }
            }
        });
        return this;
    }

    public <T> LocalSettings add(final String subKey, final SSConverter<T> c, final Property<T> p) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                T v = p.getValue();
                SStream s = v == null ? null : c.toStream(v);
                GlobalSettings.setStream(String.valueOf(prefix) + "." + subKey, s);
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                SStream s = GlobalSettings.getStream(String.valueOf(prefix) + "." + subKey);
                if (s != null) {
                    p.setValue(c.fromStream(s));
                }
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final Property<String> p) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                String v = (String) p.getValue();
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, v);
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String v = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                p.setValue(v);
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final FxDouble p) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                double v = p.getValue().doubleValue();
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, String.valueOf(v));
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String s = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                if (s != null) {
                    try {
                        double v = Double.parseDouble(s);
                        p.setValue((Number) Double.valueOf(v));
                    } catch (Exception e) {
                    }
                }
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final FxInt p) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.5


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                int v = p.getValue().intValue();
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, String.valueOf(v));
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String s = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                if (s != null) {
                    try {
                        int v = Integer.parseInt(s);
                        p.setValue((Number) Integer.valueOf(v));
                    } catch (Exception e) {
                    }
                }
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final BooleanProperty p) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                boolean v = p.getValue().booleanValue();
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, v ? "true" : "false");
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String v = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                boolean on = Boolean.parseBoolean(v);
                p.setValue(Boolean.valueOf(on));
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final ComboBox cb) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                Object v = cb.getValue();
                String s = LocalSettings.encode(v);
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, s);
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String v = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                if (v != null) {
                    ObservableList<?> items = cb.getItems();
                    if (items != null) {
                        for (Object x : items) {
                            String s = LocalSettings.encode(x);
                            if (v.equals(s)) {
                                cb.setValue(x);
                                return;
                            }
                        }
                    }
                    if (cb.isEditable()) {
                        cb.setValue(v);
                    }
                }
            }
        });
        return this;
    }

    public LocalSettings add(final String subKey, final CheckBox cb) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                boolean v = cb.isSelected();
                GlobalSettings.setString(String.valueOf(prefix) + "." + subKey, v ? "true" : "false");
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                String s = GlobalSettings.getString(String.valueOf(prefix) + "." + subKey);
                if (s != null) {
                    cb.setSelected(Boolean.parseBoolean(s));
                }
            }
        });
        return this;
    }

    public LocalSettings add(String subKey, TextInputControl t) {
        return add(subKey, t.textProperty());
    }

    public LocalSettings add(String subKey, FxAction a) {
        return add(subKey, a.selectedProperty());
    }

    public <T> LocalSettings add(final String subKey, final HasSettings x) {
        this.entries.put(subKey, new Entry() { // from class: goryachev.fx.internal.LocalSettings.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */


            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void saveValue(String prefix) {
                x.storeSettings(String.valueOf(prefix) + "." + subKey);
            }

            @Override // goryachev.fx.internal.LocalSettings.Entry
            public void loadValue(String prefix) {
                x.restoreSettings(String.valueOf(prefix) + "." + subKey);
            }
        });
        return this;
    }

    protected static String encode(Object x) {
        if (x == null) {
            return null;
        }
        return x.toString();
    }

    public void loadValues(String prefix) {
        for (String k : this.entries.keySet()) {
            Entry en = this.entries.get(k);
            en.loadValue(prefix);
        }
    }

    public void saveValues(String prefix) {
        for (String k : this.entries.keySet()) {
            Entry en = this.entries.get(k);
            en.saveValue(prefix);
        }
    }
}
