package fqlite.base;

import javafx.scene.control.ButtonBar;

/* compiled from: Job.java */
/* loaded from: fqlite_next.jar:fqlite/base/Signatures.class */
class Signatures {
    Signatures() {
    }

    static String getTable(String signature) {
        return signature.trim().replaceAll("[0-9]", ButtonBar.BUTTON_ORDER_NONE).replaceAll("PRIMARY_KEY", ButtonBar.BUTTON_ORDER_NONE);
    }
}
