package exercises.ch19;

import exercises.ch19.ex1.GenericStack;
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

     */
    public static void ch19_4() {

    }

    /*

     */
    public static void ch19_5() {

    }

    /*

     */
    public static void ch19_6() {

    }

    /*

     */
    public static void ch19_7() {

    }

    /*

     */
    public static void ch19_8() {

    }

    /*

     */
    public static void ch19_9() {

    }

    /*

     */
    public static void ch19_10() {

    }

    /*

     */
    public static void ch19_11() {

    }
}
