package exercises.ch23.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static <E extends Comparable<E>> E[] run(E[] input) {
        E[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;
            E min = list[minIndex];
            for (int j = i + 1; j < list.length; j++) {
                if (list[j].compareTo(min) < 0) {
                    minIndex = j;
                    min = list[j];
                }
            }
            if (minIndex != i) {
                list[minIndex] = list[i];
                list[i] = min;
            }
        }
        return list;
    }

}
