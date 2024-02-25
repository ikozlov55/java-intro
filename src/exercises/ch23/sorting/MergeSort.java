package exercises.ch23.sorting;

import java.util.Arrays;

public class MergeSort {
    public static <E extends Comparable<E>> E[] run(E[] input) {
        E[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        mergeSort(list);
        return list;
    }

    private static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list.length <= 1) return;
        E[] firstHalf = Arrays.copyOf(list, list.length / 2);
        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
        int secondHalfLength = list.length - list.length / 2;
        E[] secondHalf = Arrays.copyOf(list, secondHalfLength);
        System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
        mergeSort(firstHalf);
        mergeSort(secondHalf);
        merge(firstHalf, secondHalf, list);
    }

    private static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int i1 = 0;
        int i2 = 0;
        int it = 0;
        while (i1 < list1.length && i2 < list2.length) {
            if (list1[i1].compareTo(list2[i2]) < 0) {
                temp[it++] = list1[i1++];
            } else {
                temp[it++] = list2[i2++];
            }
        }
        while (i1 < list1.length) {
            temp[it++] = list1[i1++];
        }
        while (i2 < list2.length) {
            temp[it++] = list2[i2++];
        }
    }
}
