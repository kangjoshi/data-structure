package symbolTable;

import org.junit.Test;
import symbolTable.SequentialSymbolTable;

import static org.junit.Assert.*;

public class SequentialSymbolTableTest{

    /*
     * TDD 시나리오
     *   1. Key(String) & Value(String)를 받는 SymbolTable 클래스 생성
     *   2. 값을 입력하는 put 메서드 생성 (저장 되었으면 true 반환)
     *   3. 값을 반환하는 get 메서드 생성 (없으면 null 반환)
     *   4. 길이를 반환하는 size메서드 생성 (없으면 0 반환)
     *   5. 모든 key를 반환하는 keys (없으면 null 반환)
     *   6. 삭제 (있으면 true, 없으면 false 리턴)
     * */

    @Test
    public void whenCreateSymbolTableThenCreateSymbolTable(){
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        assertNotNull(symbolTable);
    }

    @Test
    public void whenPutKeyAndValueThenReturnTrue() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        boolean result = symbolTable.put("key", "value");

        assertTrue(result);
    }

    @Test
    public void whenGetValueFromEmptyTableThenReturnNull() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        String result = symbolTable.get("invalid");

        assertNull(result);
    }

    @Test
    public void whenGetValueByWrongKeyThenReturnNull() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();
        symbolTable.put("key", "value");

        String result = symbolTable.get("invalid");

        assertNull(result);
    }

    @Test
    public void whenGetValueByKeyThenReturnValue() {
        String value = "value";

        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();
        symbolTable.put("key", value);
        String result = symbolTable.get("key");

        assertEquals(value, result);
        assertEquals(symbolTable.size(), 1);
    }

    @Test
    public void whenGetSizeFromEmptyTableThenReturnZero() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        int size = symbolTable.size();

        assertEquals(size, 0);
    }

    @Test
    public void whenGetSizeThenReturnSize() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();
        symbolTable.put("key1", "value");
        symbolTable.put("key2", "value");

        int size = symbolTable.size();

        SequentialSymbolTable<String, String> duplicateSymbolTable = new SequentialSymbolTable<>();
        duplicateSymbolTable.put("sameKey", "value");
        duplicateSymbolTable.put("sameKey", "value");

        int duplicateSize = duplicateSymbolTable.size();

        assertEquals(size, 2);
        assertEquals(duplicateSize, 1);
    }

    @Test
    public void whenGetAllKeysFromEmptyTableThenReturnNull() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        String[] keys = symbolTable.keys();

        assertNull(keys);
    }

    @Test
    public void whenGetAllKeysThenReturnAllKeys() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();
        symbolTable.put("key1", "value");
        symbolTable.put("key2", "value");

        String[] keys = symbolTable.keys();

        assertEquals(keys.length, 2);
    }

    @Test
    public void whenDeleteByWrongKeyThenReturnFalse() {
        SequentialSymbolTable<String, String> symbolTable = new SequentialSymbolTable<>();

        String key = "invalid";
        boolean result = symbolTable.delete(key);

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