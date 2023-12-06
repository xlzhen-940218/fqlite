package goryachev.fx;

import goryachev.common.util.SStream;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/fx/SSConverter.class */
public interface SSConverter<T> {
    SStream toStream(T t);

    T fromStream(SStream sStream);
}
