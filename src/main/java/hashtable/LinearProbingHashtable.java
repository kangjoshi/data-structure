package hashtable;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 선형-탐지를 이용한 해시테이블
 */
public class LinearProbingHashtable<K, V> {

    private int capacity;
    private int size;
    private K keySample;
    private K[] keys;
    private V[] values;

    public LinearProbingHashtable() {
        this(17);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashtable(int capacity) {
        this.capacity = capacity;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % this.capacity;
    }

    private void resize(int cap) {
        LinearProbingHashtable<K, V> t;
        t = new LinearProbingHashtable<>(cap);

        for (int i=0; i<this.capacity; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }

        this.keys = t.keys;
        this.values = t.values;
        this.capacity = t.capacity;
    }

    public boolean put(K key, V value) {
        if (size == 0) keySample = key;

        if (size >= capacity/2) resize(2*capacity);

        int i;
        for (i=hash(key); keys[i]!=null; i=(i+1)%this.capacity) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return true;
            }
        }

        keys[i] = key;
        values[i] = value;
        this.size++;
        return true;
    }

    public V get(K key) {
        if (size == 0) return null;

        for (int i=hash(key); keys[i]!=null; i=(i+1)%this.capacity) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

}
