package hashtable;

import org.junit.Test;
import symbolTable.SequentialSymbolTable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LinearProbingHashtableTest {

    /*
     * TDD 시나리오
     *   1. Key(String) & Value(String)를 받는 Hashtable 클래스 생성
     *   2. 버킷의 인덱스를 계산 하는 해시 함수 계산
     *   2. 값을 입력하는 put 메서드 생성 (저장 되었으면 true 반환)
     *   3. 값을 반환하는 get 메서드 생성 (없으면 null 반환)
     *   4. 길이를 반환하는 size메서드 생성 (없으면 0 반환)
     * */

    @Test
    public void whenCreateHashtableThenCreateHashtable(){
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();

        assertNotNull(hashtable);
    }

    @Test
    public void whenGetCapacityThenReturnCapacity() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();

        int expectCapacity = 17;

        assertEquals(hashtable.capacity(), expectCapacity);
    }

    @Test
    public void whenCallHashWithKeyThenReturnHashIndex() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        String key = "thisiskey";

        int index = hashtable.hash(key);

        System.out.println(index);
        assertNotNull(index);
    }

    @Test
    public void whenPutKeyAndValueThenReturnTrue() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        String key = "key";
        String value = "value";

        boolean result = hashtable.put(key, value);

        assertTrue(result);
    }

    @Test
    public void whenPutManyKeysThenCheckResizeTableCapacity() {
        int firstCapacity = 5;

        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>(firstCapacity);

        hashtable.put("key1", "value");
        hashtable.put("key2", "value");
        hashtable.put("key3", "value");
        hashtable.put("key4", "value");

        assertNotEquals(firstCapacity, hashtable.capacity());
        assertTrue(firstCapacity < hashtable.capacity());
        assertEquals(hashtable.size(), 4);
    }

    @Test
    public void whenGetValueFromEmptyTableThenReturnNull() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        String key = "invalid";

        String value = hashtable.get(key);

        assertNull(value);
    }

    @Test
    public void whenGetValueByWrongKeyThenReturnNull() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        hashtable.put("key", "value");

        String result = hashtable.get("invalid");

        assertNull(result);
    }

    @Test
    public void whenGetValueByKeyThenReturnValue() {
        String value = "value";

        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        hashtable.put("key", value);
        String result = hashtable.get("key");

        assertNotNull(result);
        assertEquals(value, result);
    }

    @Test
    public void whenGetSizeFromEmptyTableThenReturnZero() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();

        int size = hashtable.size();

        assertEquals(size, 0);
    }

    @Test
    public void whenGetSizeThenReturnSize() {
        LinearProbingHashtable<String, String> hashtable = new LinearProbingHashtable<>();
        hashtable.put("key1", "value");
        hashtable.put("key2", "value");

        int size = hashtable.size();

        LinearProbingHashtable<String, String> duplicateHashtable = new LinearProbingHashtable<>();
        duplicateHashtable.put("sameKey", "value");
        duplicateHashtable.put("sameKey", "value");

        int duplicateSize = duplicateHashtable.size();

        assertEquals(size, 2);
        assertEquals(duplicateSize, 1);
    }

}