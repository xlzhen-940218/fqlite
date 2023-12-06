package goryachev.fx;

import goryachev.common.log.Log;
import goryachev.common.util.Base64;
import goryachev.common.util.CKit;
import goryachev.common.util.CSet;
import goryachev.common.util.SB;
import goryachev.common.util.UrlStreamFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.function.Supplier;
import javafx.application.Platform;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/CssLoader.class */
public class CssLoader {
    protected static final Log log = Log.get("CssLoader");
    public static final String PREFIX = "javafxcss";
    private static String url;
    private static Supplier<FxStyleSheet> generator;
    private static CSet<String> styles;

    static {
        try {
            UrlStreamFactory.registerHandler(PREFIX, new URLStreamHandler() { // from class: goryachev.fx.CssLoader.1
                @Override // java.net.URLStreamHandler
                protected URLConnection openConnection(URL url2) throws IOException {
                    return new URLConnection(url2) { // from class: goryachev.fx.CssLoader.1.1
                        @Override // java.net.URLConnection
                        public void connect() throws IOException {
                        }

                        @Override // java.net.URLConnection
                        public InputStream getInputStream() throws IOException {
                            try {
                                byte[] b = CssLoader.decode(this.url.toString());
                                return new ByteArrayInputStream(b);
                            } catch (Throwable e) {
                                throw new IOException(e);
                            }
                        }
                    };
                }
            });
            if (FxConfig.cssRefreshEnabled()) {
                Thread t = new Thread("reloading css") { // from class: goryachev.fx.CssLoader.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        while (true) {
                            CKit.sleep(999L);
                            CssLoader.updateStyles();
                        }
                    }
                };
                t.setDaemon(true);
                t.start();
            }
        } catch (Throwable e) {
            log.error(e);
        }
    }

    public static synchronized void setStyles(Supplier<FxStyleSheet> g) {
        generator = g;
        updateStyles();
    }

    public static String getCurrentStyleSheet() {
        return url;
    }

    protected static String encode(String css) {
        return "javafxcss:" + Base64.encode(CKit.getBytes(css));
    }

    protected static byte[] decode(String css) throws Exception {
        return Base64.decode(CKit.getBytes(css.substring(PREFIX.length() + 1)));
    }

    public static synchronized void updateStyles() {
        try {
            if (generator == null) {
                return;
            }
            String css = generator.get().generateStyleSheet();
            if (styles != null) {
                SB sb = new SB(2048);
                sb.append(css);
                sb.nl();
                Iterator<String> it = styles.iterator();
                while (it.hasNext()) {
                    String v = it.next();
                    sb.append(v);
                    sb.nl();
                }
                css = sb.toString();
            }
            String encoded = encode(css);
            if (CKit.notEquals(encoded, url)) {
                log.trace(css);
                String old = url;
                url = encoded;
                if (Platform.isFxApplicationThread()) {
                    update(old, url);
                } else {
                    Platform.runLater(() -> {
                        update(old, url);
                    });
                }
                log.trace(css);
            }
        } catch (Error e) {
            log.error((Throwable) e);
            throw e;
        } catch (Throwable e2) {
            log.error(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void update(String old, String cur) {
        FX.applyStyleSheet(old, cur);
        if (old == null) {
            log.debug("css loaded");
        } else {
            log.debug("css reloaded");
        }
    }

    public static synchronized void addGlobalStyle(String style) {
        if (styles == null) {
            styles = new CSet<>();
        }
        styles.add(style);
        updateStyles();
    }
}
