package goryachev.common.log;

import java.util.List;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/log/ILogConfig.class */
public interface ILogConfig {
    boolean isVerbose();

    LogLevel getLogLevel(String str);

    LogLevel getDefaultLogLevel();

    List<IAppender> getAppenders() throws Exception;
}
