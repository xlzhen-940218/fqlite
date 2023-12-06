//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.util.ArrayDeque;

public class WorkerQueue<T> {
    private ArrayDeque<T> dequeStore;

    public WorkerQueue(int initialCapacity) {
        this.dequeStore = new ArrayDeque(initialCapacity);
    }

    public WorkerQueue() {
        this.dequeStore = new ArrayDeque();
    }

    public synchronized void addFirst(T element) {
        this.dequeStore.addFirst(element);
    }

    public synchronized void addLast(T element) {
        this.dequeStore.addLast(element);
    }

    public synchronized T pop() {
        return this.dequeStore.pop();
    }

    public synchronized void push(T element) {
        this.dequeStore.push(element);
    }

    public synchronized T peek() {
        return this.dequeStore.peek();
    }

    public synchronized int size() {
        return this.dequeStore.size();
    }
}
