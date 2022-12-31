package exercises.ch6;

import java.util.Locale;
import java.util.Scanner;

public class Chapter6 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Math: pentagonal numbers) A pentagonal number is defined as n(3n-1)/2 for
        n = 1, 2, . . ., and so on. Therefore, the first few numbers are 1, 5, 12, 22, ... .
        Write a method with the following header that returns a pentagonal number:
        public static int getPentagonalNumber(int n)
        For example, getPentagonalNumber(1) returns 1 and getPentagonalNum-
        ber(2) returns 5. Write a test program that uses this method to display the first
        100 pentagonal numbers with 10 numbers on each line. Use the %7d format to
        display each number.
     */
    public static void ch6_1() {
        final int TOTAL_NUMBERS = 100;
        final int NUMBERS_PER_LINE = 10;
        for (int i = 1; i <= TOTAL_NUMBERS; i++) {
            System.out.printf("%7d", getPentagonalNumber(i));
            if (i % NUMBERS_PER_LINE == 0) {
                System.out.println();
            }
        }
    }

    public static int getPentagonalNumber(int n) {
        return n * (3 * n - 1) / 2;
    }

    /*
        (Sum the digits in an integer) Write a method that computes the sum of the digits
        in an integer. Use the following method header:
        public static int sumDigits(long n)
        For example, sumDigits(234) returns 9 (= 2 + 3 + 4). (Hint: Use the % op-
        erator to extract digits and the / operator to remove the extracted digit. For in-
        stance, to extract 4 from 234, use 234 % 10 (= 4). To remove 4 from 234, use
        234 / 10 (= 23). Use a loop to repeatedly extract and remove the digit until
        all the digits are extracted. Write a test program that prompts the user to enter an
        integer then displays the sum of all its digits.
     */
    public static void ch6_2() {
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();
        System.out.println("The sum of the digits is " + sumDigits(number));
    }

    public static int sumDigits(long n) {
        int result = 0;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }

    /*
        (Palindrome integer) Write the methods with the following headers:
        // Return the reversal of an integer, e.g., reverse(456) returns 654
        public static int reverse(int number)
        // Return true if number is a palindrome
        public static boolean isPalindrome(int number)
        Use the reverse method to implement isPalindrome. A number is a palin-
        drome if its reversal is the same as itself. Write a test program that prompts the
        user to enter an integer and reports whether the integer is a palindrome.
     */
    public static void ch6_3() {
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();
        System.out.printf("%d %s a palindrome\n", number, isPalindrome(number) ? "is" : "is not");
    }

    public static int reverse(int number) {
        int result = 0;
        while (number > 0) {
            result *= 10;
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public static boolean isPalindrome(int number) {
        return number == reverse(number);
    }

    /*
        (Display an integer reversed) Write a method with the following header to dis-
        play an integer in reverse order:
        public static void reverse(int number)
        For example, reverse(3456) displays 6543. Write a test program that prompts
        the user to enter an integer then displays its reversal.
     */
    public static void ch6_4() {
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();
        displayReverse(number);
    }

    public static void displayReverse(int number) {
        while (number > 0) {
            System.out.print(number % 10);
            number /= 10;
        }
    }

    /*
        (Sort three numbers) Write a method with the following header to display three
        numbers in increasing order:
        public static void displaySortedNumbers(double num1, double num2, double num3)
        Write a test program that prompts the user to enter three numbers and invokes the
        method to display them in increasing order.
     */
    public static void ch6_5() {
        System.out.print("Enter three numbers: ");
        double num1 = scanner.nextDouble();
        double num2 = scanner.nextDouble();
        double num3 = scanner.nextDouble();
        displaySortedNumbers(num1, num2, num3);
    }

    public static void displaySortedNumbers(double num1, double num2, double num3) {
        if (num1 <= num2 && num1 <= num3) {
            System.out.println(num1);
            System.out.println(Math.min(num2, num3));
            System.out.println(Math.max(num2, num3));
        } else if (num2 <= num1 && num2 <= num3) {
            System.out.println(num2);
            System.out.println(Math.min(num1, num3));
            System.out.println(Math.max(num1, num3));
        } else {
            System.out.println(num3);
            System.out.println(Math.min(num1, num2));
            System.out.println(Math.max(num1, num2));
        }
    }

    /*
        (Display patterns) Write a method to display a pattern as follows:
                      1
                    2 1
                  3 2 1
               ...
        n n–1 ... 3 2 1
        The method header is
        public static void displayPattern(int n)
        Write a test program that prompts the user to enter a number n and invokes dis-
        playPattern(n) to display the pattern.
     */
    public static void ch6_6() {
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();
        displayPattern(n);
    }

    public static void displayPattern(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = n; j > 0; j--) {
                if (j <= i) {
                    System.out.printf("%3d", j);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

    /*

     */
    public static void ch6_7() {

    }

    /*

     */
    public static void ch6_8() {

    }

    /*

     */
    public static void ch6_9() {

    }

    /*

     */
    public static void ch6_10() {

    }

    /*

     */
    public static void ch6_11() {

    }

    /*

     */
    public static void ch6_12() {

    }

    /*

     */
    public static void ch6_13() {

    }

    /*

     */
    public static void ch6_14() {

    }

    /*

     */
    public static void ch6_15() {

    }

    /*

     */
    public static void ch6_16() {

    }

    /*

     */
    public static void ch6_17() {

    }

    /*

     */
    public static void ch6_18() {

    }

    /*

     */
    public static void ch6_19() {

    }

    /*

     */
    public static void ch6_20() {

    }

    /*

     */
    public static void ch6_21() {

    }

    /*

     */
    public static void ch6_22() {

    }

    /*

     */
    public static void ch6_23() {

    }

    /*

     */
    public static void ch6_24() {

    }

    /*

     */
    public static void ch6_25() {

    }

    /*

     */
    public static void ch6_26() {

    }

    /*

     */
    public static void ch6_27() {

    }

    /*

     */
    public static void ch6_28() {

    }

    /*

     */
    public static void ch6_29() {

    }

    /*

     */
    public static void ch6_30() {

    }

    /*

     */
    public static void ch6_31() {

    }

    /*

     */
    public static void ch6_32() {

    }

    /*

     */
    public static void ch6_33() {

    }

    /*

     */
    public static void ch6_34() {

    }

    /*

     */
    public static void ch6_35() {

    }

    /*

     */
    public static void ch6_36() {

    }

    /*

     */
    public static void ch6_37() {

    }

    /*

     */
    public static void ch6_38() {

    }

    /*

     */
    public static void ch6_39() {

    }
}
