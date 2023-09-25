package exercises.ch20;

import exercises.ch20.ex2.Exercise20_02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Chapter20 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Display words in ascending alphabetical order) Write a program that reads
        words from a text file and displays all the words (duplicates allowed) in ascend-
        ing alphabetical order. The words must start with a letter. The text file is passed
        as a command-line argument.
     */
    public static void ch20_1(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        File file = new File(args[0]);
        ArrayList<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (!Character.isLetter(next.charAt(0))) continue;
                words.add(next);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        words.sort(String::compareToIgnoreCase);
        words.forEach(System.out::println);
    }

    /*
        (Store numbers in a linked list) Write a program that lets the user enter numbers
        from a graphical user interface and displays them in a text area, as shown in Figure
        20.17a. Use a linked list to store the numbers. Do not store duplicate numbers. Add
        the buttons Sort, Shuffle, and Reverse to sort, shuffle, and reverse the list.
     */
    public static void ch20_2() {
        Exercise20_02.run();
    }

    /*

     */
    public static void ch20_3() {

    }

    /*

     */
    public static void ch20_4() {

    }

    /*

     */
    public static void ch20_5() {

    }

    /*

     */
    public static void ch20_6() {

    }

    /*

     */
    public static void ch20_7() {

    }

    /*

     */
    public static void ch20_8() {

    }

    /*

     */
    public static void ch20_9() {

    }

    /*

     */
    public static void ch20_10() {

    }

    /*

     */
    public static void ch20_11() {

    }

    /*

     */
    public static void ch20_12() {

    }

    /*

     */
    public static void ch20_13() {

    }

    /*

     */
    public static void ch20_14() {

    }

    /*

     */
    public static void ch20_15() {

    }

    /*

     */
    public static void ch20_16() {

    }

    /*

     */
    public static void ch20_17() {

    }

    /*

     */
    public static void ch20_18() {

    }

    /*

     */
    public static void ch20_19() {

    }

    /*

     */
    public static void ch20_20() {

    }

    /*

     */
    public static void ch20_21() {

    }

    /*

     */
    public static void ch20_22() {

    }

    /*

     */
    public static void ch20_23() {

    }

}
