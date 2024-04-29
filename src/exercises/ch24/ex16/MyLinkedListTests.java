package exercises.ch24.ex16;

import exercises.ch24.ex07.MyLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyLinkedListTests {

    @Test
    void createEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        assertEquals(0, list.size());
        assertEquals("", list.toString());
    }

    @Test
    void createWithArray() {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});

        assertEquals(3, list.size());
        assertEquals("[1]->[2]->[3]", list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, true",
            "3, true",
            "4, false",
    })
    void contains(int value, boolean result) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});

        assertEquals(result, list.contains(value));
    }

    @Test
    void iterator() {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});
        int i = 1;
        for (int value : list) {
            assertEquals(i, value);
            i++;
        }
    }

    @Test
    void clear() {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        list.clear();

        assertEquals(0, list.size());
        assertEquals("", list.toString());
    }

    @Test
    void addition() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        StringBuilder expected = new StringBuilder();
        for (int i = 1; i < 5; i++) {
            list.add(i);

            expected.append(i == 1 ? String.format("[%d]", i) : String.format("->[%d]", i));
            assertEquals(i, list.size());
            assertEquals(expected.toString(), list.toString());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "-1, [1]->[2]->[3]",
            "0, [99]->[1]->[2]->[3]",
            "1, [1]->[99]->[2]->[3]",
            "3, [1]->[2]->[3]->[99]",
    })
    void additionByIndex(int index, String expected) {
        int value = 99;
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});
        list.add(index, value);

        assertEquals(expected, list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "2, 3"
    })
    void getByIndex(int index, int value) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});

        assertEquals(value, list.get(index));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -1",
            "1, 0",
            "3, 2",
    })
    void getIndexOf(int value, int expectedIndex) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3, 1, 3});

        assertEquals(expectedIndex, list.indexOf(value));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -1",
            "1, 3",
            "3, 4",
    })
    void getLastIndexOf(int value, int expectedIndex) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3, 1, 3});

        assertEquals(expectedIndex, list.lastIndexOf(value));
    }

    @ParameterizedTest
    @CsvSource({
            "0, [2]->[3]",
            "2, [1]->[2]",
    })
    void removalByIndex(int index, String expected) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});
        int initialSize = list.size();
        int value = list.get(index);
        int result = list.remove(index);

        assertEquals(initialSize - 1, list.size());
        assertEquals(value, result);
        assertEquals(expected, list.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void removalByIndexNegative(int index) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});
        int initialSize = list.size();
        Integer result = list.remove(index);

        assertEquals(initialSize, list.size());
        assertNull(result);
        assertEquals("[1]->[2]->[3]", list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0, [1]->[2]->[3]",
            "0, 11, [11]->[2]->[3]",
            "2, 33, [1]->[2]->[33]",
            "3, 44, [1]->[2]->[3]"
    })
    void set(int index, int value, String expected) {
        MyLinkedList<Integer> list = new MyLinkedList<>(new Integer[]{1, 2, 3});
        Integer setResult = list.set(index, value);
        Integer expectedResult = index < 0 || index >= list.size() ? null : value;

        assertEquals(expected, list.toString());
        assertEquals(expectedResult, setResult);
    }
}
