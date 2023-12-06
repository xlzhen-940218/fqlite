package goryachev.common.test;

import goryachev.common.util.SB;
import javafx.scene.control.ButtonBar;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/TestCase.class */
public class TestCase {
    private final String name;
    private Object test;
    private Throwable failure;
    private SB text = new SB();
    private long startTime;
    private long stopTime;
    protected static final ThreadLocal<TestCase> currentTestCase = new ThreadLocal<>();

    public TestCase(String name) {
        this.name = name;
        currentTestCase.set(this);
    }

    public String getName() {
        return this.name;
    }

    public static String getNameCurrent() {
        TestCase tc = get();
        return tc == null ? ButtonBar.BUTTON_ORDER_NONE : tc.getName();
    }

    public void setTestInstance(Object test) {
        this.test = test;
    }

    public Object getTestInstance() {
        return this.test;
    }

    public void setFailed(Throwable e) {
        this.failure = e;
    }

    public boolean isFailed() {
        return this.failure != null;
    }

    public Throwable getFailure() {
        return this.failure;
    }

    public static TestCase get() {
        return currentTestCase.get();
    }

    public static void print(Object x) {
        TestCase tc = get();
        if (tc == null || TF.isForcePrint()) {
            System.out.println(x);
        } else {
            tc.appendText(String.valueOf(x));
        }
    }

    public synchronized void appendText(String s) {
        this.text.append(s);
        this.text.append("\n");
    }

    public synchronized String getText() {
        return this.text.toString();
    }

    public void started() {
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void stopped() {
        this.stopTime = System.currentTimeMillis();
    }

    public long getStopTime() {
        return this.stopTime;
    }
}
