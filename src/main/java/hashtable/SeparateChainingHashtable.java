package hashtable;

import symbolTable.SequentialSymbolTable;

import java.lang.reflect.Array;

/**
 * 개별-체이닝을 이용한 해시테이블
 */
public class SeparateChainingHashtable<K, V> {

    private final int capacity;
    private int size;
    private K keySample;
    private SequentialSymbolTable<K, V>[] symbolTables;

    public SeparateChainingHashtable() {
        this(17);
    }

    public SeparateChainingHashtable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.symbolTables = (SequentialSymbolTable<K, V>[]) new SequentialSymbolTable[capacity];

        for (int i=0; i<capacity; i++) {
            symbolTables[i] = new SequentialSymbolTable();
        }

    }

    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % this.capacity;
    }


    public boolean put(K key, V value) {
        if (size == 0) keySample = key;

        SequentialSymbolTable<K, V> st = symbolTables[hash(key)];
        this.size -= st.size();
        boolean result = st.put(key, value);
        this.size += st.size();

        return result;
    }

    public V get(K key) {
        if (size == 0) return null;
        return symbolTables[hash(key)].get(key);
    }

    public int size() {
        return this.size;
    }

    public K[] keys() {
        if (size == 0) return null;

        K[] keys = (K[]) Array.newInstance(keySample.getClass(), size);
        int index=0;
        for (SequentialSymbolTable<K, V> st : symbolTables) {
            if (st.keys() == null) continue;

            for (K k : st.keys()) {
                keys[index++] = k;
            }
        }
        return keys;
    }

    public boolean delete(K key) {
        if (size == 0) return false;

        SequentialSymbolTable<K, V> st = symbolTables[hash(key)];

        this.size -= st.size();
        boolean result = st.delete(key);
        this.size += st.size();

        return result;
    }
}
