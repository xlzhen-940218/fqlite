package fqlite.base;

/* loaded from: fqlite_next.jar:fqlite/base/Base.class */
public abstract class Base {
    public static final int ALL = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    public static int LOGLEVEL = 0;

    public void debug(String message) {
        if (LOGLEVEL <= 1) {
            System.out.println("[DEBUG] " + message);
        }
    }

    public void info(String message) {
        if (LOGLEVEL <= 2) {
            System.out.println("[INFO] " + message);
        }
    }

    public void warning(String message) {
        if (LOGLEVEL <= 3) {
            System.out.println("[WARNING] " + message);
        }
    }

    public void err(String message) {
        System.err.println("ERROR: " + message);
    }
}
