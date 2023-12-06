package goryachev.common.test;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/TestException.class */
public class TestException extends RuntimeException {
    public TestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestException(Throwable cause) {
        super(cause);
    }

    public TestException(String message) {
        super(message);
    }

    public TestException() {
    }

    @Override // java.lang.Throwable
    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] ss = super.getStackTrace();
        String testPrefix = TestException.class.getPackage().getName();
        for (int i = 0; i < ss.length; i++) {
            StackTraceElement em = ss[i];
            String name = em.getClassName();
            if (!name.startsWith(testPrefix)) {
                int ct = ss.length - i;
                StackTraceElement[] rv = new StackTraceElement[ct];
                System.arraycopy(ss, i, rv, 0, ct);
                return rv;
            }
        }
        return ss;
    }
}
