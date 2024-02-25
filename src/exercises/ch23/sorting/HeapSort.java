package exercises.ch23.sorting;

import exercises.ch23.ex07.MinHeap;

import java.util.Arrays;

public class HeapSort {

    public static <E extends Comparable<E>> E[] run(E[] input) {
        E[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        MinHeap<E> heap = new MinHeap<>(input);
        for (int i = 0; i < list.length; i++) {
            list[i] = heap.remove();
        }
        return list;
    }

}
