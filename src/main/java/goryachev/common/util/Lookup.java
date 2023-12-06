package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Lookup.class */
public class Lookup {
    private static final CMap<Class, Provider> providers = new CMap<>();

    @FunctionalInterface
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Lookup$Provider.class */
    public interface Provider<T> {
        T createService();
    }

    public static <T> T get(Class<T> type) {
        Provider<T> p = getProvider(type);
        if (p == null) {
            throw new Error("Must register a provider before first use: Lookup.register(" + type + ", provider);");
        }
        return p.createService();
    }

    private static synchronized <T> Provider<T> getProvider(Class<T> type) {
        return providers.get(type);
    }

    public static synchronized <T> void register(Class<T> type, Provider<T> provider) {
        if (providers.containsKey(type)) {
            throw new Error("Provider already registered: " + type);
        }
        providers.put(type, provider);
    }

    public static synchronized <T> void registerIfEmpty(Class<T> type, Provider<T> provider) {
        if (!providers.containsKey(type)) {
            providers.put(type, provider);
        }
    }
}
