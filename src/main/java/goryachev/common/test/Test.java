package goryachev.common.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/Test.class */
public @interface Test {
    Class<? extends Throwable> expected() default NoThrowable.class;

    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/test/Test$NoThrowable.class */
    public static class NoThrowable extends Throwable {
        private NoThrowable() {
        }
    }
}
