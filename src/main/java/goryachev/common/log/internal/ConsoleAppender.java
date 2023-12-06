package goryachev.common.log.internal;

import goryachev.common.log.AppenderBase;
import goryachev.common.log.LogLevel;
import java.io.PrintStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/internal/ConsoleAppender.class */
public class ConsoleAppender extends AppenderBase {
    private final PrintStream out;

    public ConsoleAppender(LogLevel threshold, PrintStream out) {
        super(threshold);
        this.out = out;
    }

    public ConsoleAppender(PrintStream out) {
        this(LogLevel.ALL, out);
    }

    @Override // goryachev.common.log.AppenderBase
    public void emit(String s) {
        this.out.println(s);
    }
}
