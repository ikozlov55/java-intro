package exercises.ch23.sorting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {
    public static int[] run(Integer[] input) {
        return run(Arrays.stream(input).mapToInt(x -> x).toArray());
    }

    public static int[] run(int[] input) {
        int[] list = Arrays.copyOf(input, input.length);
        if (list.length == 0) return list;
        List<ArrayDeque<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayDeque<>());
        }
        int base = 1;
        boolean sorted = false;
        while (!sorted) {
            for (int j : list) {
                int radix = (j / base) % 10;
                buckets.get(radix).add(j);
            }
            if (buckets.get(0).size() == list.length) {
                sorted = true;
            }
            int i = 0;
            for (ArrayDeque<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    list[i++] = bucket.remove();
                }
            }
            base *= 10;
        }

        return list;
    }
}
