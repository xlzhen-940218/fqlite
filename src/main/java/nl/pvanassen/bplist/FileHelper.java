package nl.pvanassen.bplist;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import org.apache.commons.io.IOUtils;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/FileHelper.class */
public class FileHelper {
    private FileHelper() {
    }

    public static File getFile(String resource) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        try {
            if (url.getProtocol().equals("file")) {
                return new File(url.toURI());
            }
        } catch (URISyntaxException e) {
        }
        try {
            File file = File.createTempFile("raf", "tmp");
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            ReadableByteChannel src = Channels.newChannel(inputStream);
            WritableByteChannel dest = raf.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(16384);
            while (src.read(buffer) != -1) {
                buffer.flip();
                dest.write(buffer);
                buffer.compact();
            }
            buffer.flip();
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            raf.close();
            return file;
        } catch (IOException e2) {
            throw new RuntimeException("Error getting RAF", e2);
        }
    }

    public static String getContent(String resource) throws IOException {
        Throwable th = null;
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try {
                IOUtils.copy(input, output);
                String str = new String(output.toByteArray());
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
                return str;
            } catch (Throwable th2) {
                if (output != null) {
                    output.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            if (0 == 0) {
                th = th3;
            } else if (null != th3) {
                th.addSuppressed(th3);
            }

        }
        return resource;
    }
}
