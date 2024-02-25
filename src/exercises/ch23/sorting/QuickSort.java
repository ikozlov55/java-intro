package exercises.ch23.sorting;

import java.util.Arrays;

public class QuickSort {
    public static <E extends Comparable<E>> E[] run(E[] input) {
        E[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        quickSort(list, 0, list.length - 1);
        return list;
    }

    private static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
        if (first >= last) return;
        int pivotIndex = partition(list, first, last);
        quickSort(list, first, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, last);
    }

    private static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
        int pivotIndex = first;
        E pivot = list[pivotIndex];
        int lh = first + 1;
        int rh = last;
        while (lh < rh) {
            while (lh <= rh && list[lh].compareTo(pivot) <= 0) {
                lh++;
            }
            while (lh <= rh && list[rh].compareTo(pivot) > 0) {
                rh--;
            }
            if (lh < rh) {
                E temp = list[lh];
                list[lh] = list[rh];
                list[rh] = temp;
            }
        }
        while (rh > pivotIndex && list[rh].compareTo(pivot) > 0) {
            rh--;
        }
        if (rh > pivotIndex) {
            list[first] = list[rh];
            list[rh] = pivot;
            return rh;
        } else {
            return pivotIndex;
        }
    }
}
