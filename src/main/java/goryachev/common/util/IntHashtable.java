package goryachev.common.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/IntHashtable.class */
public class IntHashtable<T> implements Cloneable, Serializable {
    private Entry<Object>[] table;
    private int count;
    private int threshold;
    private float loadFactor;
    private static final int MASK = Integer.MAX_VALUE;
    private static final long serialVersionUID = 1;

    public IntHashtable(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0 || loadFactor <= 0.0d) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        this.table = new Entry[initialCapacity];
        this.threshold = (int) (initialCapacity * loadFactor);
    }

    public IntHashtable(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public IntHashtable() {
        this(101, 0.75f);
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public synchronized Enumeration keys() {
        return new Enumerator(this.table, true);
    }

    public synchronized Enumeration elements() {
        return new Enumerator(this.table, false);
    }

    public synchronized boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Entry<Object>[] tab = this.table;
        int i = tab.length;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                Entry<Object> entry = tab[i];
                while (true) {
                    Entry<Object> e = entry;
                    if (e == null) {
                        break;
                    } else if (!e.value.equals(value)) {
                        entry = e.next;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
    }

    public synchronized boolean containsKey(int key) {
        Entry<Object>[] tab = this.table;
        int index = (key & Integer.MAX_VALUE) % tab.length;
        Entry<Object> entry = tab[index];
        while (true) {
            Entry<Object> e = entry;
            if (e != null) {
                if (e.hash != key || e.key != key) {
                    entry = e.next;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public synchronized T get(int key) {
        Entry<T>[] tab = (Entry<T>[]) this.table;
        int index = (key & Integer.MAX_VALUE) % tab.length;
        Entry<T> entry = tab[index];
        while (true) {
            Entry<T> e = entry;
            if (e != null) {
                if (e.hash != key || e.key != key) {
                    entry = e.next;
                } else {
                    return e.value;
                }
            } else {
                return null;
            }
        }
    }

    protected void rehash() {
        int oldCapacity = this.table.length;
        Entry<Object>[] oldTable = this.table;
        int newCapacity = (oldCapacity * 2) + 1;
        Entry[] newTable = new Entry[newCapacity];
        this.threshold = (int) (newCapacity * this.loadFactor);
        this.table = newTable;
        int i = oldCapacity;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                Entry<Object> old = oldTable[i];
                while (old != null) {
                    Entry<Object> e = old;
                    old = old.next;
                    int index = (e.hash & Integer.MAX_VALUE) % newCapacity;
                    e.next = newTable[index];
                    newTable[index] = e;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Object put(int key, Object value) {
        Entry<Object>[] tab = this.table;
        int index = (key & Integer.MAX_VALUE) % tab.length;
        Entry<Object> entry = tab[index];
        while (true) {
            Entry<Object> e = entry;
            if (e != null) {
                if (e.hash != key || e.key != key) {
                    entry = e.next;
                } else {
                    Object old = e.value;
                    e.value = value;
                    return old;
                }
            } else if (this.count >= this.threshold) {
                rehash();
                return put(key, value);
            } else {
                Entry<Object> e2 = new Entry<>();
                e2.hash = key;
                e2.key = key;
                e2.value = value;
                e2.next = tab[index];
                tab[index] = e2;
                this.count++;
                return null;
            }
        }
    }

    public synchronized Object remove(int key) {
        Entry<Object>[] tab = this.table;
        int index = (key & Integer.MAX_VALUE) % tab.length;
        Entry<Object> prev = null;
        for (Entry<Object> e = tab[index]; e != null; e = e.next) {
            if (e.hash != key || e.key != key) {
                prev = e;
            } else {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                this.count--;
                return e.value;
            }
        }
        return null;
    }

    public synchronized void clear() {
        Entry[] tab = this.table;
        int index = tab.length;
        while (true) {
            index--;
            if (index >= 0) {
                tab[index] = null;
            } else {
                this.count = 0;
                return;
            }
        }
    }

    public synchronized Object clone() {
        try {
            IntHashtable t = (IntHashtable) super.clone();
            t.table = new Entry[this.table.length];
            int i = this.table.length;
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    t.table[i] = this.table[i] != null ? (Entry) this.table[i].clone() : null;
                } else {
                    return t;
                }
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public synchronized String toString() {
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Enumeration k = keys();
        Enumeration e = elements();
        buf.append("{");
        for (int i = 0; i <= max; i++) {
            String s1 = k.nextElement().toString();
            String s2 = e.nextElement().toString();
            buf.append(s1).append("=").append(s2);
            if (i < max) {
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/IntHashtable$Entry.class */
    public static class Entry<T> implements Serializable {
        int hash;
        int key;
        T value;
        Entry next;
        private static final long serialVersionUID = 1;

        protected Entry() {
        }

        protected Object clone() {
            Entry<T> entry = new Entry<>();
            entry.hash = this.hash;
            entry.key = this.key;
            entry.value = this.value;
            entry.next = this.next != null ? (Entry) this.next.clone() : null;
            return entry;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: fqlite_next.jar:FxTextEditor.jar:goryachev/common/util/IntHashtable$Enumerator.class */
    public static class Enumerator implements Enumeration {
        boolean keys;
        int index;
        Entry[] table;
        Entry entry;

        public Enumerator(Entry[] table, boolean keys) {
            this.table = table;
            this.keys = keys;
            this.index = table.length;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            Entry entry;
            if (this.entry != null) {
                return true;
            }
            do {
                int i = this.index;
                this.index = i - 1;
                if (i > 0) {
                    entry = this.table[this.index];
                    this.entry = entry;
                } else {
                    return false;
                }
            } while (entry == null);
            return true;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            Entry entry;
            if (this.entry == null) {
                do {
                    int i = this.index;
                    this.index = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    entry = this.table[this.index];
                    this.entry = entry;
                } while (entry == null);
            }
            if (this.entry != null) {
                Entry<Object> e = this.entry;
                this.entry = e.next;
                return this.keys ? Integer.valueOf(e.key) : e.value;
            }
            throw new NoSuchElementException("IntHashtableEnumerator");
        }
    }
}
