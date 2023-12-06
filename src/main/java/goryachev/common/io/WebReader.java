package goryachev.common.io;

import demo.fxtexteditor.Conf;
import goryachev.common.util.CKit;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/io/WebReader.class */
public class WebReader {
    private int connectTimeout;
    private int readTimeout;
    private int maxContentLength;

    public WebReader() {
        this(Conf.LINE_COUNT, 5000, 1000000);
    }

    public WebReader(int connectTimeout, int readTimeout, int maxContentLength) {
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.maxContentLength = maxContentLength;
    }

    public void setMaxContentLength(int max) {
        this.maxContentLength = max;
    }

    public void setConnectTimeout(int ms) {
        this.connectTimeout = ms;
    }

    public void setReadTimeout(int ms) {
        this.readTimeout = ms;
    }

    public String readString(String url) throws Exception {
        return readString(new URL(url));
    }

    public String readString(URL url) throws Exception {
        byte[] b = readBytes(url);
        return new String(b, CKit.CHARSET_UTF8);
    }

    public byte[] readBytes(URL url) throws Exception {
        URLConnection c = url.openConnection();
        c.setConnectTimeout(this.connectTimeout);
        c.setReadTimeout(this.readTimeout);
        c.setDoOutput(false);
        c.setAllowUserInteraction(false);
        c.setUseCaches(false);
        c.connect();
        InputStream in = c.getInputStream();
        try {
            return CKit.readBytes(in, this.maxContentLength);
        } finally {
            CKit.close(in);
        }
    }
}
