package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/UserException.class */
public class UserException extends RuntimeException {
    private String title;

    public UserException(String title, String message, Throwable cause) {
        super(message, cause);
        this.title = title;
    }

    public UserException(String title, String message) {
        super(message);
        this.title = title;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(String message) {
        super(message);
    }

    public String getTitle() {
        return this.title;
    }
}
