package goryachev.common.util;

import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/Mailbox.class */
public class Mailbox<T> {
    private final ArrayBlockingQueue<T> queue = new ArrayBlockingQueue<>(1);

    public void put(T item) {
        this.queue.offer(item);
    }

    public T get() {
        try {
            return this.queue.take();
        } catch (Exception e) {
            return null;
        }
    }
}
