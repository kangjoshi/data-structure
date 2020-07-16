package hashtable;

import hashtable.SeparateChainingHashtable;
import org.junit.Test;
import symbolTable.SequentialSymbolTable;

import static org.junit.Assert.*;

public class SeparateChainingHashtableTest {

    /*
     * TDD 시나리오
     *   1. Key(String) & Value(String)를 받는 Hashtable 클래스 생성
     *   2. 버킷의 인덱스를 계산 하는 해시 함수 계산
     *   2. 값을 입력하는 put 메서드 생성 (저장 되었으면 true 반환)
     *   3. 값을 반환하는 get 메서드 생성 (없으면 null 반환)
     *   4. 길이를 반환하는 size메서드 생성 (없으면 0 반환)
     *   5. 모든 key를 반환하는 keys (없으면 null 반환)
     *   6. 삭제 (있으면 true, 없으면 false 리턴)
     * */

    @Test
    public void whenCreateHashtableThenCreateHashtable(){
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();

        assertNotNull(hashtable);
    }

    @Test
    public void whenCallHashWithKeyThenReturnHashIndex() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        String key = "thisiskey";

        int index = hashtable.hash(key);

        System.out.println(index);
        assertNotNull(index);
    }

    @Test
    public void whenPutKeyAndValueThenReturnTrue() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        String key = "key";
        String value = "value";

        boolean result = hashtable.put(key, value);

        assertTrue(result);
    }

    @Test
    public void whenGetValueFromEmptyTableThenReturnNull() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        String key = "invalid";

        String value = hashtable.get(key);

        assertNull(value);
    }

    @Test
    public void whenGetValueByWrongKeyThenReturnNull() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        hashtable.put("key", "value");

        String result = hashtable.get("invalid");

        assertNull(result);
    }

    @Test
    public void whenGetValueByKeyThenReturnValue() {
        String value = "value";

        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        hashtable.put("key", value);
        String result = hashtable.get("key");

        assertNotNull(result);
        assertEquals(value, result);
    }

    @Test
    public void whenGetSizeFromEmptyTableThenReturnZero() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();

        int size = hashtable.size();

        assertEquals(size, 0);
    }

    @Test
    public void whenGetSizeThenReturnSize() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        hashtable.put("key1", "value");
        hashtable.put("key2", "value");

        int size = hashtable.size();

        SeparateChainingHashtable<String, String> duplicateHashtable = new SeparateChainingHashtable<>();
        duplicateHashtable.put("sameKey", "value");
        duplicateHashtable.put("sameKey", "value");

        int duplicateSize = duplicateHashtable.size();

        assertEquals(size, 2);
        assertEquals(duplicateSize, 1);
    }

    @Test
    public void whenGetAllKeysFromEmptyTableThenReturnNull() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();

        String[] keys = hashtable.keys();

        assertNull(keys);
    }

    @Test
    public void whenGetAllKeysThenReturnAllKeys() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();
        hashtable.put("key1", "value");
        hashtable.put("key2", "value");
        hashtable.put("key3", "value");
        hashtable.put("key4", "value");
        hashtable.put("key5", "value");

        String[] keys = hashtable.keys();

        assertEquals(keys.length, 5);
    }

    @Test
    public void whenDeleteByWrongKeyThenReturnFalse() {
        SeparateChainingHashtable<String, String> hashtable = new SeparateChainingHashtable<>();

        String key = "invalid";
        boolean result = hashtable.delete(key);

        assertFalse(result);
    }

    @Test
    public void whenDeleteByKeyThenReturnTrueAndDecreaseSize() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();
        symbolTable.put("key1", "value");
        symbolTable.put("key2", "value");

        int beforeSize = symbolTable.size();

        boolean result = symbolTable.delete("key1");

        String value = symbolTable.get("key1");

        assertTrue(result);
        assertEquals(symbolTable.size(), beforeSize - 1);
        assertNull(value);
    }

}