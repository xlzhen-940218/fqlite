package fqlite.analyzer;

import fqlite.util.Auxiliary;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:fqlite/analyzer/BinaryLoader.class */
public class BinaryLoader {
    public static String parse2(String path) {
        String result = ButtonBar.BUTTON_ORDER_NONE;
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
            ByteBuffer bf = ByteBuffer.wrap(buffer.readAllBytes());
            bf.position(0);
            result = Auxiliary.bytesToHex(bf);
            buffer.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return result;
    }

    public static String parse(String path) {
        String result = ButtonBar.BUTTON_ORDER_NONE;
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(path));
            ByteBuffer bf = ByteBuffer.wrap(buffer.readAllBytes());
            bf.position(0);
            result = Auxiliary.hex2ASCII_v2(Auxiliary.bytesToHex(bf));
            buffer.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return result;
    }
}
