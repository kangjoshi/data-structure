package symbolTable;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 비순차 연결 리스트 심볼 테이블
 * */
public class SequentialSymbolTable<K, V> {

    private int size;
    private Node first;

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialSymbolTable() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public K[] keys() {
        if (size == 0) return null;

        @SuppressWarnings("unchecked")
        K[] keys = (K[]) Array.newInstance(first.key.getClass(), size);

        int index = 0;
        for(Node n=first; n!=null; n=n.next) {
            keys[index++] = n.key;
        }

        return keys;
    }

    public boolean put(K key, V value) {
        for (Node n=first; n!=null; n=n.next) {
            if (key.equals(n.key)) {
                n.value = value;
                return true;
            }
        }
        first = new Node(key, value, first);
        size++;
        return true;
    }

    public V get(K key) {
        if (size == 0) return null;

        V result = null;
        for (Node n=first; n!=null; n=n.next) {
            if (key.equals(n.key)) {
                result = n.value;
                break;
            }
        }

        return result;
    }

    public boolean delete(K key) {
        if (size == 0) return false;

        Node last = null;
        for (Node n=first; n!=null; n=n.next) {
            if (key.equals(n.key)) {
                n = null;
                if (last != null)
                    last.next = null;
                size--;
                return true;
            }
            last = n;
        }

        return false;
    }
}
