package goryachev.common.util;

import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Locale;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/UrlStreamFactory.class */
public class UrlStreamFactory {
    private static CMap<String, URLStreamHandler> handlers;

    public static synchronized void registerHandler(String protocol, URLStreamHandler h) throws Exception {
        if (handlers == null) {
            handlers = new CMap<>();
            URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() { // from class: goryachev.common.util.UrlStreamFactory.1
                @Override // java.net.URLStreamHandlerFactory
                public URLStreamHandler createURLStreamHandler(String protocol2) {
                    URLStreamHandler h2 = UrlStreamFactory.getHandler(protocol2);
                    return h2;
                }
            });
        }
        handlers.put(protocol.toLowerCase(Locale.US), h);
    }

    protected static URLStreamHandler getHandler(String protocol) {
        return handlers.get(protocol.toLowerCase(Locale.US));
    }
}
