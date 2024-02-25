package exercises.ch23.sorting;

import java.util.Arrays;

public class BubbleSort {
    public static <E extends Comparable<E>> E[] run(E[] input) {
        E[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        for (int i = 1; i < list.length; i++) {
            for (int j = 0; j < list.length - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
        return list;
    }
}
