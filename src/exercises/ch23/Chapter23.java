package exercises.ch23;

import exercises.ch23.ex07.MinHeap;
import exercises.ch23.ex10.Exercise23_10;
import exercises.ch23.ex11.CloneableHeap;
import exercises.ch23.ex15.Exercise23_15;
import exercises.ch23.ex16.Exercise23_16;
import exercises.ch23.ex17.Exercise23_17;
import exercises.ch23.ex18.Exercise23_18;
import exercises.ch23.ex19.Exercise23_19;
import exercises.ch23.sorting.*;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static exercises.utils.Utils.testTime;

public class Chapter23 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Generic bubble sort) Write the following two generic methods using bubble
        sort. The first method sorts the elements using the Comparable interface, and
        the second uses the Comparator interface.
     */
    public static void ch23_1() {
        Integer[] intList = new Integer[]{3, 4, 6, 1, 7, 8, 2, 9, 5};
        Character[] charList = new Character[]{'d', 'b', 'e', 'a', 'c', 'g', 'b', 'f', 'h'};
        bubbleSort(intList);
        bubbleSort(charList);
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
        bubbleSort(intList, Comparator.reverseOrder());
        bubbleSort(charList, Comparator.reverseOrder());
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        bubbleSort(list, Comparator.naturalOrder());
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        if (list.length < 2) return;
        for (int k = 1; k < list.length; k++) {
            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    Object temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = (E) temp;
                }
            }
        }
    }

    /*
        (Generic merge sort) Write the following two generic methods using merge sort.
        The first method sorts the elements using the Comparable interface and the
        second uses the Comparator interface.
     */
    public static void ch23_2() {
        Integer[] intList = new Integer[]{3, 4, 6, 1, 7, 8, 2, 9, 5};
        Character[] charList = new Character[]{'d', 'b', 'e', 'a', 'c', 'g', 'b', 'f', 'h'};
        mergeSort(intList);
        mergeSort(charList);
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
        mergeSort(intList, Comparator.reverseOrder());
        mergeSort(charList, Comparator.reverseOrder());
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
    }

    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        mergeSort(list, Comparator.naturalOrder());
    }

    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {
        mergeSort(Arrays.asList(list), comparator);
    }

    public static <E> void mergeSort(List<E> list, Comparator<? super E> comparator) {
        if (list.size() < 2) return;
        List<E> firstHalf = new ArrayList<>(list.subList(0, list.size() / 2));
        List<E> secondHalf = new ArrayList<>(list.subList(list.size() / 2, list.size()));
        mergeSort(firstHalf, comparator);
        mergeSort(secondHalf, comparator);
        merge(firstHalf, secondHalf, list, comparator);
    }

    public static <E> void merge(List<E> list1, List<E> list2, List<E> temp, Comparator<? super E> comparator) {
        int i1 = 0;
        int i2 = 0;
        int it = 0;
        while (i1 < list1.size() && i2 < list2.size()) {
            if (comparator.compare(list1.get(i1), list2.get(i2)) < 0) {
                temp.set(it++, list1.get(i1++));
            } else {
                temp.set(it++, list2.get(i2++));
            }
        }
        while (i1 < list1.size()) {
            temp.set(it++, list1.get(i1++));
        }
        while (i2 < list2.size()) {
            temp.set(it++, list2.get(i2++));
        }
    }

    /*
        (Generic quick sort) Write the following two generic methods using quick sort.
        The first method sorts the elements using the Comparable interface, and the
        second uses the Comparator interface.
     */
    public static void ch23_3() {
        Integer[] intList = new Integer[]{3, 4, 6, 1, 7, 8, 2, 9, 5};
        Character[] charList = new Character[]{'d', 'b', 'e', 'a', 'c', 'g', 'b', 'f', 'h'};
        quickSort(intList);
        quickSort(charList);
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
        quickSort(intList, Comparator.reverseOrder());
        quickSort(charList, Comparator.reverseOrder());
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
    }

    public static <E extends Comparable<E>> void quickSort(E[] list) {
        quickSort(list, 0, list.length - 1, Comparator.naturalOrder());
    }

    public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
        quickSort(list, 0, list.length - 1, comparator);
    }

    private static <E> void quickSort(E[] list, int first, int last, Comparator<? super E> comparator) {
        if (first >= last) return;
        int pivotIndex = partition(list, first, last, comparator);
        quickSort(list, first, pivotIndex - 1, comparator);
        quickSort(list, pivotIndex + 1, last, comparator);
    }

    private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
        E pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        while (high > low) {
            // Search forward from left
            while (low <= high && comparator.compare(list[low], pivot) <= 0) {
                low++;
            }
            // Search backward from right
            while (low <= high && comparator.compare(list[high], pivot) > 0) {
                high--;
            }
            // Swap two elements in the list
            if (high > low) {
                E temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && comparator.compare(list[high], pivot) >= 0) {
            high--;
        }
        // Swap pivot with list[high]
        if (comparator.compare(pivot, list[high]) > 0) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }

    }

    /*
        (Improve quick sort) The quick-sort algorithm presented in the book selects the
        first element in the list as the pivot. Revise it by selecting the median among the
        first, middle, and the last elements in the list.
     */
    public static void ch23_4() {
        int[] list = new int[]{7, 4, 6, 1, 3, 8, 2, 9, 5, 0, 2};
        quickSort(list);
        System.out.println(Arrays.toString(list));
    }

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] list, int first, int last) {
        int pivotIndex = medianIndex(list, first, last);
        int pivot = list[pivotIndex];
        list[pivotIndex] = list[first];
        list[first] = pivot;
        int low = first + 1;
        int high = last;

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public static int medianIndex(int[] list, int first, int last) {
        int length = last - first + 1;
        if (length < 3) {
            return first;
        }
        int mid = length / 2;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(list[first], first);
        map.put(list[mid], mid);
        map.put(list[last], last);
        int median = Stream.of(list[first], list[mid], list[last]).sorted().toList().get(1);
        return map.get(median);
    }

    /*
        (Modify merge sort) Rewrite the mergeSort method to recursively sort the first
        half of the array and the second half of the array without creating new temporary
        arrays, then merge the two into a temporary array and copy its contents to the
        original array, as shown in Figure 23.6b.
     */
    public static void ch23_5() {
        int[] list = new int[]{7, 4, 6, 1, 3, 8, 2, 9, 5, 0};
        mergeSort(list);
        System.out.println(Arrays.toString(list));
    }

    public static void mergeSort(int[] list) {
        mergeSort(list, 0, list.length);
    }

    public static void mergeSort(int[] list, int first, int last) {
        int length = last - first;
        int mid = first + length / 2;
        if (length <= 1) return;
        mergeSort(list, first, mid);
        mergeSort(list, mid, last);
        int[] mergedList = merge(list, first, last);
        System.arraycopy(mergedList, 0, list, first, length);
    }

    public static int[] merge(int[] list, int first, int last) {
        int length = last - first;
        int mid = first + length / 2;
        int[] mergedList = new int[length];
        int current1 = first; // Current index in first Half
        int current2 = mid; // Current index in second Half
        int current3 = 0; // Current index in merged List

        while (current1 < mid && current2 < last) {
            if (list[current1] < list[current2])
                mergedList[current3++] = list[current1++];
            else
                mergedList[current3++] = list[current2++];
        }

        while (current1 < mid)
            mergedList[current3++] = list[current1++];

        while (current2 < last)
            mergedList[current3++] = list[current2++];

        return mergedList;
    }

    /*
        (Check order) Write the following overloaded methods that check whether an
        array is ordered in ascending order or descending order. By default, the method
        checks ascending order. To check descending order, pass false to the ascend-
        ing argument in the method.
     */
    public static void ch23_6() {
        int[] list1 = new int[]{7, 4, 6, 1, 3};
        int[] list2 = new int[]{1, 2, 3, 4, 5};
        int[] list3 = new int[]{5, 4, 3, 2};
        System.out.printf("%s is ordered: %b\n", Arrays.toString(list1), ordered(list1));
        System.out.printf("%s is ordered: %b\n", Arrays.toString(list2), ordered(list2));
        System.out.printf("%s is ordered descending: %b\n", Arrays.toString(list3), ordered(list3, false));
    }

    public static boolean ordered(int[] list) {
        return ordered(list, true);
    }

    public static boolean ordered(int[] list, boolean ascending) {
        Integer[] boxedList = Arrays.stream(list)
                .boxed()
                .toArray(Integer[]::new);
        return ordered(boxedList, ascending);
    }

    public static boolean ordered(double[] list) {
        return ordered(list, true);
    }

    public static boolean ordered(double[] list, boolean ascending) {
        Double[] boxedList = Arrays.stream(list)
                .boxed()
                .toArray(Double[]::new);
        return ordered(boxedList, ascending);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list) {
        return ordered(list, true);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list, boolean ascending) {
        return ordered(list, Comparator.naturalOrder(), ascending);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator) {
        return ordered(list, comparator, true);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator, boolean ascending) {
        if (list.length == 0) return true;

        for (int i = 1; i < list.length; i++) {
            E previous = list[i - 1];
            E current = list[i];
            int diff = comparator.compare(current, previous);
            if (ascending && diff < 0 || !ascending && diff > 0) {
                return false;
            }
        }
        return true;
    }

    /*
        (Min-heap) The heap presented in the text is also known as a max-heap, in which
        each node is greater than or equal to any of its children. A min-heap is a heap in
        which each node is less than or equal to any of its children. Min-heaps are often
        used to implement priority queues. Revise the Heap class in Listing 23.9 to
        implement a min-heap.
     */
    public static void ch23_7() {
        Integer[] list = new Integer[]{7, 4, 6, 1, 3, 2, 5, 9, 8, 0, 1, 3};
        MinHeap<Integer> heap = new MinHeap<>(list);
        int[] sortedList = new int[list.length];
        for (int i = 0; i < sortedList.length; i++) {
            sortedList[i] = heap.remove();
        }
        System.out.println(Arrays.toString(sortedList));
    }

    /*
        (Generic insertion sort) Write the following two generic methods using insertion
        sort. The first method sorts the elements using the Comparable interface, and
        the second uses the Comparator interface.
     */
    public static void ch23_8() {
        Integer[] intList = new Integer[]{3, 4, 6, 1, 7, 8, 2, 9, 5};
        Character[] charList = new Character[]{'d', 'b', 'e', 'a', 'c', 'g', 'b', 'f', 'h'};

        insertionSort(intList);
        insertionSort(charList);
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));

        insertionSort(intList, Comparator.reverseOrder());
        insertionSort(charList, Comparator.reverseOrder());
        System.out.println(Arrays.toString(intList));
        System.out.println(Arrays.toString(charList));
    }

    public static <E extends Comparable<E>> void insertionSort(E[] list) {
        insertionSort(list, Comparator.naturalOrder());
    }

    public static <E> void insertionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 1; i < list.length; i++) {
            E currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && comparator.compare(list[k], currentElement) > 0; k--) {
                list[k + 1] = list[k];
            }
            list[k + 1] = currentElement;
        }
    }

    /*
        (Generic heap sort) Write the following two generic methods using heap sort.
        The first method sorts the elements using the Comparable interface, and the sec-
        ond uses the Comparator interface. (Hint: Use the Heap class in Programming
        Exercise 23.5.)
     */
    public static void ch23_9() {
        Integer[] list = new Integer[]{3, 4, 6, 1, 7, 8, 2, 9, 5, 0};
        heapSort(list);
        System.out.println(Arrays.toString(list));
        heapSort(list, Comparator.reverseOrder());
        System.out.println(Arrays.toString(list));
    }

    public static <E extends Comparable<E>> void heapSort(E[] list) {
        heapSort(list, Comparator.naturalOrder());
    }

    public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
        MinHeap<E> heap = new MinHeap<>(comparator);
        for (E element : list) {
            heap.add(element);
        }
        for (int i = 0; i < list.length; i++) {
            list[i] = heap.remove();
        }
    }

    /*
        (Heap visualization) Write a program that displays a heap graphically, as shown
        in Figure 23.10. The program lets you insert and delete an element from the heap.
     */
    public static void ch23_10() {
        Exercise23_10.run();
    }

    /*
        (Heap clone and equals) Implement the clone and equals method in the
        Heap class.
     */
    public static void ch23_11() {
        String[] strings = {"red", "green", "purple", "orange", "yellow", "cyan"};
        CloneableHeap<String> heap1 = new CloneableHeap<>(strings);
        CloneableHeap<String> heap2 = (CloneableHeap<String>) (heap1.clone());

        System.out.println("heap1: " + heap1.getSize());
        System.out.println("heap2: " + heap2.getSize());
        System.out.println("equals? " + heap1.equals(heap2));

        heap1.remove();

        System.out.println("heap1: " + heap1.getSize());
        System.out.println("heap2: " + heap2.getSize());
        System.out.println("equals? " + heap1.equals(heap2));
    }

    /*
        (Radix sort) Write a program that randomly generates 1,000,000 integers and
        sorts them using radix sort.
     */
    public static void ch23_12() {
        int n = 1_000_000;
        int bound = 100000;
        int[] list = new int[n];
        Random random = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextInt(0, bound);
        }
        List<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        int base = 1;
        while (base <= bound) {
            for (int x : list) {
                int radix = (x / base) % 10;
                buckets.get(radix).add(x);
            }
            int i = 0;
            while (i < n) {
                for (List<Integer> bucket : buckets) {
                    for (int integer : bucket) {
                        list[i] = integer;
                        i++;
                    }
                    bucket.clear();
                }
            }
            base *= 10;
        }
    }

    /*
        (Execution time for sorting) Write a program that obtains the execution time of
        selection sort, bubble sort, merge sort, quick sort, heap sort, and radix sort for
        input size 50,000, 100,000, 150,000, 200,000, 250,000, and 300,000. Your pro-
        gram should create data randomly and print a table like this:
     */
    public static void ch23_13() {
        Random random = new Random();
        System.out.printf("%-15s| %-15s%-15s%-15s%-15s%-15s%-15s\n",
                "Array", "Selection", "Bubble", "Merge", "Quick", "Heap", "Radix");
        System.out.printf("%-15s| %-15s%-15s%-15s%-15s%-15s%-15s\n",
                "size", "Sort", "Sort", "Sort", "Sort", "Sort", "Sort");
        System.out.printf("%s┼%s\n", "─".repeat(15), "─".repeat(15 * 6));
        for (int n = 50_000; n <= 300_000; n += 50_000) {
            Integer[] list = new Integer[n];
            for (int i = 0; i < list.length; i++) {
                list[i] = random.nextInt(0, 1000);
            }
            System.out.printf("%,-15d| %-15f%-15f%-15f%-15f%-15f%-15f\n",
                    n,
                    testTime(() -> SelectionSort.run(list)),
                    testTime(() -> BubbleSort.run(list)),
                    testTime(() -> MergeSort.run(list)),
                    testTime(() -> QuickSort.run(list)),
                    testTime(() -> HeapSort.run(list)),
                    testTime(() -> RadixSort.run(list))
            );
        }
    }

    /*
        (Execution time for external sorting) Write a program that obtains the execution
        time of external sorts for integers of size 5,000,000, 10,000,000, 15,000,000,
        20,000,000, 25,000,000, and 30,000,000.
     */
    public static void ch23_14() {
        final String inputFile = "src/exercises/ch23/data.dat";
        final String targetFile = "src/exercises/ch23/sortedData.dat";
        int[] sizes = new int[]{5_000_000, 10_000_000, 15_000_000, 20_000_000, 25_000_000, 30_000_000};
        System.out.printf("%-15s| %,-15d%,-15d%,-15d%,-15d%,-15d%,-15d\n",
                "File size", sizes[0], sizes[1], sizes[2], sizes[3], sizes[4], sizes[5]);
        System.out.printf("%s┼%s\n", "─".repeat(15), "─".repeat(15 * 6));
        System.out.printf("%-15s| ", "Time");
        for (int n : sizes) {
            try (DataOutputStream output = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(inputFile, false)))
            ) {
                for (int i = 0; i < n; i++) {
                    int x = (int) (Math.random() * 1_000_000);
                    output.writeInt(x);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            double time = testTime(() -> ExternalSort.run(inputFile, targetFile));
            System.out.printf("%-15.2f", time);
        }
        System.out.println();
    }

    /*
        (Selection-sort animation) Write a program that animates the selection-sort algo-
        rithm. Create an array that consists of 20 distinct numbers from 1 to 20 in a random
        order. The array elements are displayed in a histogram, as shown in Figure 23.20a.
        Clicking the Step button causes the program to perform an iteration of the outer
        loop in the algorithm and repaints the histogram for the new array. Color the last
        bar in the sorted subarray. When the algorithm is finished, display a message to
        inform the user. Clicking the Reset button creates a new random array for a new
        start. (You can easily modify the program to animate the insertion algorithm.)
     */
    public static void ch23_15() {
        Exercise23_15.run();
    }

    /*
        (Bubble-sort animation) Write a program that animates the bubble-sort algo-
        rithm. Create an array that consists of 20 distinct numbers from 1 to 20 in a
        random order. The array elements are displayed in a histogram, as shown in
        Figure 23.20b. Clicking the Step button causes the program to perform one com-
        parison in the algorithm and repaints the histogram for the new array. Color the
        bar that represents the number being considered in the swap. When the algo-
        rithm is finished, display a message to inform the user. Clicking the Reset button
        creates a new random array for a new start.
     */
    public static void ch23_16() {
        Exercise23_16.run();
    }

    /*
        (Radix-sort animation) Write a program that animates the radix-sort algorithm. Cre-
        ate an array that consists of 20 random numbers from 0 to 1,000. The array elements
        are displayed, as shown in Figure 23.21. Clicking the Step button causes the program
        to place a number in a bucket. The number that has just been placed is displayed in
        red. Once all the numbers are placed in the buckets, clicking the Step button col-
        lects all the numbers from the buckets and moves them back to the array. When the
        algorithm is finished, clicking the Step button displays a message to inform the user.
        Clicking the Reset button creates a new random array for a new start.
     */
    public static void ch23_17() {
        Exercise23_17.run();
    }

    /*
        (Merge animation) Write a program that animates the merge of two sorted lists.
        Create two arrays, list1 and list2, each of which consists of 8 random num-
        bers from 1 to 999. The array elements are displayed, as shown in Figure 23.22a.
        Clicking the Step button causes the program to move an element from list1 or
        list2 to temp. Clicking the Reset button creates two new random arrays for
        a new start. When the algorithm is finished, clicking the Step button displays a
        message to inform the user.
     */
    public static void ch23_18() {
        Exercise23_18.run();
    }

    /*
        (Quick-sort partition animation) Write a program that animates the partition for
        a quick sort. The program creates a list that consists of 20 random numbers from
        1 to 999. The list is displayed, as shown in Figure 23.22b. Clicking the Step but-
        ton causes the program to move low to the right or high to the left, or swap the
        elements at low and high. Clicking the Reset button creates a new list of random
        numbers for a new start. When the algorithm is finished, clicking the Step button
        displays a message to inform the user.
     */
    public static void ch23_19() {
        Exercise23_19.run();
    }
}
