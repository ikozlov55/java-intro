package exercises.ch22;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Chapter22 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Maximum consecutive increasingly ordered substring) Write a program that
        prompts the user to enter a string and displays the maximum consecutive
        increasingly ordered substring. Analyze the time complexity of your program.
        Here is a sample run:
            Enter a string: abcabcdgabxy
            Maximum consecutive substring is abcdg
            Enter a string: abcabcdgabmnsxy
            Maximum consecutive substring is abmnsxy
     */
    public static void ch22_1() {
        System.out.print("Enter a string: ");
        String s = scanner.nextLine();
        String result = s.substring(0, 1);
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) > s.charAt(i - 1)) {
                endIndex = i;
            }
            if (endIndex != i || i == s.length() - 1) {
                if (endIndex - startIndex + 1 > result.length()) {
                    result = s.substring(startIndex, endIndex + 1);
                }
                startIndex = i;
                endIndex = i;
            }
        }

        System.out.printf("Maximum consecutive substring is %s\n", result);
    }

    /*
        (Maximum increasingly ordered subsequence) Write a program that prompts
        the user to enter a string and displays the maximum increasingly ordered sub-
        sequence of characters. Analyze the time complexity of your program. Here is
        a sample run:
            Enter a string: Welcome
            Maximum increasingly ordered subsequence is Welo
     */
    public static void ch22_2() {
        System.out.print("Enter a string: ");
        char[] chars = scanner.nextLine().toCharArray();

        ArrayList<Character> maxSequence = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            ArrayList<Character> sequence = new ArrayList<>();
            sequence.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] > sequence.get(sequence.size() - 1)) {
                    sequence.add(chars[j]);
                }
            }
            if (sequence.size() > maxSequence.size()) {
                maxSequence.clear();
                maxSequence.addAll(sequence);
            }
        }
        System.out.printf("Maximum increasingly ordered subsequence is %s\n", maxSequence.
                stream().
                map(x -> Character.toString(x)).
                collect(Collectors.joining())
        );
    }

    /*
        (Pattern matching) Write an O(n) time program that prompts the user to enter
        two strings and tests whether the second string is a substring of the first string.
        Suppose the neighboring characters in the string are distinct. (Don’t use the
        indexOf method in the String class.) Here is a sample run of the program:
            Enter a string s1: Welcome to Java
            Enter a string s2: come
            matched at index 3
     */
    public static void ch22_3() {
        System.out.print("Enter a string s1: ");
        char[] s1 = scanner.nextLine().toCharArray();
        System.out.print("Enter a string s2: ");
        char[] s2 = scanner.nextLine().toCharArray();
        int index = -1;
        int substringIndex = 0;
        for (int i = 0; i < s1.length && substringIndex < s2.length; i++) {
            if (s1[i] == s2[substringIndex]) {
                if (index < 0) {
                    index = i;
                }
                substringIndex++;
            } else {
                index = -1;
                substringIndex = 0;
            }
        }

        if (index >= 0) {
            System.out.printf("matched at index %d\n", index);
        } else {
            System.out.println("no match");
        }

    }

    /*

     */
    public static void ch22_4() {

    }

    /*

     */
    public static void ch22_5() {

    }

    /*

     */
    public static void ch22_6() {

    }

    /*

     */
    public static void ch22_7() {

    }

    /*

     */
    public static void ch22_8() {

    }

    /*

     */
    public static void ch22_9() {

    }

    /*

     */
    public static void ch22_10() {

    }

    /*

     */
    public static void ch22_11() {

    }

    /*

     */
    public static void ch22_12() {

    }

    /*

     */
    public static void ch22_13() {

    }

    /*

     */
    public static void ch22_14() {

    }

    /*

     */
    public static void ch22_15() {

    }

    /*

     */
    public static void ch22_16() {

    }

    /*

     */
    public static void ch22_17() {

    }

    /*

     */
    public static void ch22_18() {

    }

    /*

     */
    public static void ch22_19() {

    }

    /*

     */
    public static void ch22_20() {

    }

    /*

     */
    public static void ch22_21() {

    }

    /*

     */
    public static void ch22_22() {

    }

    /*

     */
    public static void ch22_23() {

    }

    /*

     */
    public static void ch22_24() {

    }

    /*

     */
    public static void ch22_25() {

    }

    /*

     */
    public static void ch22_26() {

    }

    /*

     */
    public static void ch22_27() {

    }
}
