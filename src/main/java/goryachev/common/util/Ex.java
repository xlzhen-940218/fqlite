package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Ex.class */
public class Ex extends Exception {
    private final Object id;
    private Object parameter;

    public Ex(Object id) {
        this.id = id;
    }

    public Ex(Object id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public Ex(Object id, Object parameter) {
        this.id = id;
        this.parameter = parameter;
    }

    public Ex(Object id, Object value, Throwable cause) {
        super(cause);
        this.id = id;
        this.parameter = value;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        SB sb = new SB();
        sb.a(this.id);
        if (this.parameter != null) {
            sb.sp();
            sb.a(this.parameter);
        }
        if (getCause() != null) {
            sb.sp();
            sb.a(getCause());
        }
        return sb.toString();
    }

    public Object getID() {
        return this.id;
    }

    public static Object getID(Throwable e) {
        if (e instanceof Ex) {
            return ((Ex) e).getID();
        }
        return null;
    }

    public Object getParameter() {
        return this.parameter;
    }
}
