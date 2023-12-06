package goryachev.common.test;

import goryachev.common.test.Test;
import goryachev.common.util.CJob;
import goryachev.common.util.CKit;
import goryachev.common.util.CList;
import goryachev.common.util.CSorter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/TestRunner.class */
public class TestRunner {
    private int failed;
    protected long started;
    protected long ended;
    private final CList<Class> classes = new CList<>();
    protected final Vector<TestCase> cases = new Vector<>();

    public static void run(List<Class> tests) {
        initLog();
        TestRunner r = new TestRunner();
        r.print("Testing started " + new Date() + ".\n");
        for (Class c : tests) {
            r.add(c);
        }
        r.executeTests();
        r.printResults();
        System.out.flush();
        System.err.flush();
        CKit.sleep(10L);
        System.exit(-r.failed);
    }

    public static void initLog() {
    }

    public void add(Class c) {
        checkConstructor(c);
        this.classes.add(c);
    }

    protected void checkConstructor(Class c) {
        try {
            if (c.getConstructor(new Class[0]) != null) {
                if (c.getConstructors().length == 1) {
                }
            }
        } catch (Exception e) {
            throw new TestException("Test class must define a single no-arg constructor: " + CKit.getSimpleName(c), e);
        }
    }

    protected void extract(Class c, CList<RunEntry> list, Class<? extends Annotation> type, boolean needsStatic) {
        Method[] declaredMethods;
        Annotation a;
        for (Method m : c.getDeclaredMethods()) {
            int mods = m.getModifiers();
            if (Modifier.isPublic(mods) && (a = m.getAnnotation(type)) != null) {
                if (Modifier.isStatic(mods) != needsStatic) {
                    if (needsStatic) {
                        throw new TestException("method " + CKit.getSimpleName(c) + "." + m.getName() + "() must be static");
                    }
                    throw new TestException("method " + CKit.getSimpleName(c) + "." + m.getName() + "() must not be static");
                }
                list.add(new RunEntry(m, a));
            }
        }
        CSorter.sort(list);
    }

    protected void print(String s) {
        System.out.print(s);
    }

    protected void executeTests() {
        CList<CJob> jobs = new CList<>();
        this.started = System.currentTimeMillis();
        Iterator<Class> it = this.classes.iterator();
        while (it.hasNext()) {
            final Class c = it.next();
            CJob job = new CJob("test " + CKit.getSimpleName(c)) { // from class: goryachev.common.test.TestRunner.1
                @Override // goryachev.common.util.CJob
                protected void process() throws Exception {
                    try {
                        TestRunner.this.executeTestClass(this, c);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            jobs.add(job);
            job.submit();
        }
        CJob.waitForAll(jobs);
        this.ended = System.currentTimeMillis();
    }

    protected void executeTestClass(CJob parent, final Class c) throws Exception {
        CList<RunEntry> beforeAll = new CList<>();
        final CList<RunEntry> before = new CList<>();
        CList<RunEntry> tests = new CList<>();
        final CList<RunEntry> after = new CList<>();
        CList<RunEntry> afterAll = new CList<>();
        extract(c, beforeAll, BeforeClass.class, true);
        extract(c, before, Before.class, false);
        extract(c, tests, Test.class, false);
        extract(c, after, After.class, false);
        extract(c, afterAll, AfterClass.class, true);
        if (tests.size() == 0) {
            System.out.println("No tests in " + CKit.getSimpleName(c));
            return;
        }
        Iterator<RunEntry> it = beforeAll.iterator();
        while (it.hasNext()) {
            it.next().invoke(null);
        }
        CList<CJob> jobs = new CList<>();
        Iterator<RunEntry> it2 = tests.iterator();
        while (it2.hasNext()) {
            final RunEntry m = it2.next();
            CJob job = new CJob(parent, "test " + CKit.getSimpleName(c) + "." + m) { // from class: goryachev.common.test.TestRunner.2
                @Override // goryachev.common.util.CJob
                protected void process() throws Exception {
                    TestRunner.this.executeInstance(c, m, before, after);
                }
            };
            jobs.add(job);
            job.submit();
        }
        CJob.waitForAll(jobs);
        Iterator<RunEntry> it3 = afterAll.iterator();
        while (it3.hasNext()) {
            it3.next().invoke(null);
        }
    }

    protected void executeInstance(Class c, RunEntry testMethod, CList<RunEntry> before, CList<RunEntry> after) {
        String name = String.valueOf(CKit.getSimpleName(c)) + "." + testMethod;
        TestCase tc = new TestCase(name);
        this.cases.add(tc);
        tc.started();
        try {
            Object instance = c.newInstance();
            tc.setTestInstance(instance);
            Iterator<RunEntry> it = before.iterator();
            while (it.hasNext()) {
                RunEntry m = it.next();
                m.invoke(instance);
            }
            try {
                testMethod.invoke(instance);
                testMethod.checkNoException();
            } catch (InvocationTargetException e) {
                Throwable err = e.getTargetException();
                if (testMethod.isUnexpected(err)) {
                    if (err instanceof Exception) {
                        throw ((Exception) err);
                    }
                    throw new Exception(err);
                }
            } catch (Exception e2) {
                throw e2;
            }
            Iterator<RunEntry> it2 = after.iterator();
            while (it2.hasNext()) {
                RunEntry m2 = it2.next();
                m2.invoke(instance);
            }
            tc.stopped();
            testSuccess(tc);
        } catch (Throwable e3) {
            tc.stopped();
            tc.setFailed(e3);
            testFailed(tc);
        }
    }

    protected void testSuccess(TestCase tc) {
        print(".");
    }

    protected void testFailed(TestCase tc) {
        print("E");
    }

    public void printResults() {
        int count = this.cases.size();
        Iterator<TestCase> it = this.cases.iterator();
        while (it.hasNext()) {
            if (it.next().isFailed()) {
                this.failed++;
            }
        }
        print("\n\n");
        String elapsed = CKit.formatTimePeriod(this.ended - this.started);
        if (this.failed == 0) {
            print("OK");
        } else {
            print("FAILED " + this.failed);
        }
        print(" (" + count + " tests) " + elapsed + "\n");
        if (this.failed > 0) {
            Iterator<TestCase> it2 = this.cases.iterator();
            while (it2.hasNext()) {
                TestCase c = it2.next();
                if (c.isFailed()) {
                    print("\n");
                    print(String.valueOf(c.getName()) + "\n");
                    print(c.getText());
                    print("\n");
                    print(CKit.stackTrace(c.getFailure()));
                    print("\n");
                }
            }
        }
    }

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/TestRunner$RunEntry.class */
    public static class RunEntry implements Comparable<RunEntry> {
        private final Method method;
        private final Annotation annotation;

        public RunEntry(Method method, Annotation annotation) {
            this.method = method;
            this.annotation = annotation;
        }

        public String toString() {
            return getName();
        }

        public String getName() {
            return this.method.getName();
        }

        public boolean isUnexpected(Throwable e) {
            Class<? extends Throwable> expected = ((Test) this.annotation).expected();
            return expected == null || !expected.isAssignableFrom(e.getClass());
        }

        public void checkNoException() throws Exception {
            Class<? extends Throwable> expected = ((Test) this.annotation).expected();
            if (expected != Test.NoThrowable.class && expected != null) {
                throw new Exception("This test case is expected to throw an " + CKit.getSimpleName(expected));
            }
        }

        public void invoke(Object x) throws Exception {
            this.method.invoke(x, new Object[0]);
        }

        @Override // java.lang.Comparable
        public int compareTo(RunEntry x) {
            return getName().compareTo(x.getName());
        }
    }
}
