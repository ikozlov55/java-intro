package exercises.ch24.ex15;

import exercises.ch24.ex08.MyArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyArrayListTests {

    @Test
    void createEmpty() {
        MyArrayList<Integer> list = new MyArrayList<>();

        assertEquals(0, list.size());
        assertEquals(4, list.getCapacity());
        assertEquals("[x][x][x][x]", list.toString());
    }

    @Test
    void createWithArray() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});

        assertEquals(3, list.size());
        assertEquals(4, list.getCapacity());
        assertEquals("[1][2][3][x]", list.toString());
    }

    @Test
    void addition() {
        MyArrayList<Integer> list = new MyArrayList<>();
        String expected = "[x][x][x][x]";

        for (int i = 1; i < 5; i++) {
            expected = expected.replaceFirst("x", Integer.toString(i));
            list.add(i);

            assertEquals(i, list.size());
            assertEquals(4, list.getCapacity());
            assertEquals(expected, list.toString());
        }
    }

    @Test
    void capacityDoublesOnAddition() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 4});
        list.add(5);

        assertEquals(8, list.getCapacity());
        assertEquals("[1][2][3][4][5][x][x][x]", list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, [1][2][3][x]",
            "0, [99][1][2][3]",
            "1, [1][99][2][3]",
            "3, [1][2][3][99]",
            "4, [1][2][3][x]"
    })
    void additionByIndex(int index, String expectedResult) {
        int value = 99;
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});
        list.add(index, value);

        assertEquals(expectedResult, list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "2, 3"
    })
    void getByIndex(int index, int value) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});

        assertEquals(value, list.get(index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void getByIndexNegative(int index) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});

        assertNull(list.get(index));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -1",
            "1, 0",
            "3, 2",
    })
    void getIndexOf(int value, int expectedIndex) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 1, 3});

        assertEquals(expectedIndex, list.indexOf(value));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -1",
            "1, 3",
            "3, 4",
    })
    void getLastIndexOf(int value, int expectedIndex) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 1, 3});

        assertEquals(expectedIndex, list.lastIndexOf(value));
    }

    @ParameterizedTest
    @CsvSource({
            "0, [2][3][x][x]",
            "2, [1][2][x][x]",
    })
    void removalByIndex(int index, String expectedResult) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});
        list.remove(index);

        assertEquals(2, list.size());
        assertEquals(expectedResult, list.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void removalByIndexNegative(int index) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});
        list.remove(index);

        assertEquals("[1][2][3][x]", list.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 0, [1][2][3][x]",
            "0, 11, [11][2][3][x]",
            "2, 33, [1][2][33][x]",
            "3, 44, [1][2][3][x]"
    })
    void set(int index, int value, String expected) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});
        Integer setResult = list.set(index, value);
        Integer expectedResult = index < 0 || index >= list.size() ? null : value;

        assertEquals(expected, list.toString());
        assertEquals(expectedResult, setResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, true",
            "3, true",
            "4, false",
    })
    void contains(int value, boolean result) {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});

        assertEquals(result, list.contains(value));
    }

    @Test
    void clear() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        list.clear();

        assertEquals(0, list.size());
        assertEquals(4, list.getCapacity());
        assertEquals("[x][x][x][x]", list.toString());
    }

    @Test
    void trim() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        list.trim();

        assertEquals("[1][2][3][4][5]", list.toString());
    }

    @Test
    void iterator() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[]{1, 2, 3});
        int i = 1;
        for (int value : list) {
            assertEquals(i, value);
            i++;
        }
    }
}
