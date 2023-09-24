package exercises.ch19;

import exercises.ch19.ex1.GenericStack;
import exercises.ch19.ex11.Complex;
import exercises.ch19.ex11.ComplexMatrix;
import exercises.ch19.ex2.InheritanceGenericStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Chapter19 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Revising Listing19.1) Revise the GenericStack class in Listing 19.1 to imple-
        ment it using an array rather than an ArrayList. You should check the array size
        before adding a new element to the stack. If the array is full, create a new array that
        doubles the current array size and copy the elements from the current array to the
        new array.
     */
    public static void ch19_1() {
        GenericStack<Integer> stack = new GenericStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        for (int i = 0; i < 10; i++) {
            stack.pop();
            System.out.println(stack);
        }
    }

    /*
        (Implement GenericStack using inheritance) In Listing 19.1, GenericStack is
        implemented using composition. Define a new stack class that extends ArrayList.
        Draw the UML diagram for the classes then implement GenericStack. Write a test
        program that prompts the user to enter five strings and displays them in reverse order.
     */
    public static void ch19_2() {
        System.out.println("Enter five strings: ");
        InheritanceGenericStack<String> stack = new InheritanceGenericStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(scanner.nextLine());
        }
        System.out.println(stack);
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }

    /*
        (Distinct elements in ArrayList) Write the following method that returns a new
        ArrayList. The new list contains the nonduplicate elements from the original list.
        public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
     */
    public static void ch19_3() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4, 5, 1, 5));
        System.out.println(removeDuplicates(list));
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();
        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /*
        (Generic linear search) Implement the following generic method for linear search:
        public static <E extends Comparable<E>>
        int linearSearch(E[] list, E key)
     */
    public static void ch19_4() {
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        String[] arr2 = new String[]{"zzz", "aaa", "bbb"};
        System.out.println(linearSearch(arr1, 5));
        System.out.println(linearSearch(arr2, "zzz"));
        System.out.println(linearSearch(arr2, "xxx"));
    }

    public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /*
        (Maximum element in an array) Implement the following method that returns the
        maximum element in an array:
        public static <E extends Comparable<E>> E max(E[] list)
        Write a test program that prompts the user to enter 10 integers, invokes this method
        to find the max, and displays the maximum number.
     */
    public static void ch19_5() {
        int count = 10;
        System.out.printf("Enter %d integers: ", count);
        Integer[] input = new Integer[count];
        for (int i = 0; i < count; i++) {
            input[i] = scanner.nextInt();
        }
        System.out.println(max(input));
    }

    public static <E extends Comparable<E>> E max(E[] list) {
        E result = list[0];
        for (E item : list) {
            if (item.compareTo(result) > 0) {
                result = item;
            }
        }
        return result;
    }

    /*
        (Maximum element in a two-dimensional array) Write a generic method that returns
        the maximum element in a two-dimensional array.
        public static <E extends Comparable<E>> E max(E[][] list)
     */
    public static void ch19_6() {
        Double[][] arr = new Double[][]{
                {1.0, 2.0, 3.0},
                {8.0, 9.0, 0.0},
                {2.1, 9.2, 2.0}
        };
        System.out.println(max(arr));
    }

    public static <E extends Comparable<E>> E max(E[][] list) {
        E result = list[0][0];
        for (E[] row : list) {
            for (E item : row) {
                if (item.compareTo(result) > 0) {
                    result = item;
                }
            }
        }
        return result;
    }

    /*
        (Generic binary search) Implement the following method using binary search:
        public static <E extends Comparable<E>>
        int binarySearch(E[] list, E key)
     */
    public static void ch19_7() {
        Double[] arr = new Double[]{0.0, 0.6, 1.2, 2.0, 2.0, 2.2, 3.99, 4.3, 9.1};
        System.out.println(binarySearch(arr, 9.1));
    }

    public static <E extends Comparable<E>> int binarySearch(E[] list, E key) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (key.compareTo(list[mid]) < 0) {
                high = mid - 1;
            } else if (key.compareTo(list[mid]) > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*
        (Shuffle ArrayList) Write the following method that shuffles an ArrayList:
        public static <E> void shuffle(ArrayList<E> list)
     */
    public static void ch19_8() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        shuffle(list);
        System.out.println(list);
    }

    public static <E> void shuffle(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            E item = list.get(i);
            int index = (int) (Math.random() * list.size());
            list.set(i, list.get(index));
            list.set(index, item);
        }
    }

    /*
        (Sort ArrayList) Write the following method that sorts an ArrayList:
        public static <E extends Comparable<E>> void sort(ArrayList<E> list)
        Write a test program that prompts the user to enter 10 integers, invokes this method
        to sort the numbers, and displays the numbers in increasing order.

        4 1 8 4 0 6 3 8 9 1
     */
    public static void ch19_9() {
        ArrayList<Integer> input = new ArrayList<>();
        int count = 10;
        System.out.printf("Enter %d integers: ", count);
        for (int i = 0; i < count; i++) {
            input.add(scanner.nextInt());
        }
        System.out.println(input);
        sort(input);
        System.out.println(input);
    }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            E min = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(min) < 0) {
                    minIndex = j;
                    min = list.get(j);
                }
            }
            if (minIndex != i) {
                list.set(minIndex, list.get(i));
                list.set(i, min);
            }
        }
    }

    /*
        (Largest element in an ArrayList) Write the following method that returns the
        largest element in an ArrayList:
        public static <E extends Comparable<E>> E max(ArrayList<E> list)
     */
    public static void ch19_10() {
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(1.0, 3.0, 2.3, 0.0, -4.0, 8.98, 2.3, 7.1));
        System.out.println(max(list));
    }

    public static <E extends Comparable<E>> E max(ArrayList<E> list) {
        E result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(result) > 0) {
                result = list.get(i);
            }
        }
        return result;
    }

    /*
        (ComplexMatrix) Use the Complex class introduced in Programming Exercise
        13.17 to develop the ComplexMatrix class for performing matrix operations
        involving complex numbers. The ComplexMatrix class should extend the
        GenericMatrix class and implement the add, multiple, and zero methods.
        You need to modify GenericMatrix and replace every occurrence of Number
        by Object because Complex is not a subtype of Number. Write a test program
        that creates two matrices and displays the result of addition and multiplication
        of the matrices by invoking the printResult method.
     */
    public static void ch19_11() {
        Complex[][] m1 = new Complex[][]{
                {new Complex(), new Complex(1), new Complex(2)},
                {new Complex(3), new Complex(4), new Complex(5)},
                {new Complex(6), new Complex(7), new Complex(8)},
        };
        Complex[][] m2 = new Complex[][]{
                {new Complex(8,1), new Complex(7, 2), new Complex(6, 3)},
                {new Complex(5), new Complex(4), new Complex(3)},
                {new Complex(2), new Complex(1), new Complex()},
        };
        ComplexMatrix matrix = new ComplexMatrix();
        ComplexMatrix.printResult(m1, m2, matrix.addMatrix(m1, m2), '+');
        System.out.println();
        ComplexMatrix.printResult(m1, m2, matrix.multiplyMatrix(m1, m2), '*');
    }
}
