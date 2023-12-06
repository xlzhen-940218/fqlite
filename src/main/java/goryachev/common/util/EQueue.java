package goryachev.common.util;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/EQueue.class */
public class EQueue<T> {
    protected CList<T> queue;

    public EQueue(int size) {
        this.queue = new CList<>(size);
    }

    public EQueue() {
        this(16);
    }

    public synchronized void put(T d) {
        this.queue.add(d);
        notifyAll();
    }

    public synchronized void putToFront(T d) {
        this.queue.add(0, d);
        notifyAll();
    }

    public synchronized T get() {
        while (this.queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return this.queue.remove(0);
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public synchronized void delete(T d) {
        this.queue.remove(d);
    }

    public synchronized void clear() {
        this.queue.clear();
        notify();
    }
}
