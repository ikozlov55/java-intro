package exercises.ch18;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Chapter18 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Factorial) Using the BigInteger class introduced in Section 10.9, you can
        find the factorial for a large number (e.g., 100!). Implement the factorial
        method using recursion. Write a program that prompts the user to enter an inte-
        ger and displays its factorial.
     */
    public static void ch18_1() {
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        BigInteger f = factorial(n, BigInteger.ONE);
        System.out.printf("Factorial of %d is %s\n", n, f);
    }

    private static BigInteger factorial(int n, BigInteger result) {
        if (n == 0) {
            return result;
        } else {
            return factorial(n - 1, result.multiply(BigInteger.valueOf(n)));
        }
    }

    /*
        (Fibonacci numbers) Rewrite the fib method in Listing 18.2 using iterations.
        Hint: To compute fib(n) without recursion, you need to obtain fib(n − 2)
        and fib(n − 1) first. Let f0 and f1 denote the two previous Fibonacci num-
        bers. The current Fibonacci number would then be f0 + f1. The algorithm can
        be described as follows:
        f0 = 0; // For fib(0)
        f1 = 1; // For fib(1)
        for (int i = 1; i <= n; i++) {
            currentFib = f0 + f1;
            f0 = f1;
            f1 = currentFib;
        }
        // After the loop, currentFib is fib(n)
        Write a test program that prompts the user to enter an index and displays its
        Fibonacci number.
     */
    public static void ch18_2() {
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = scanner.nextInt();
        System.out.printf("The Fibonacci number at index %d is %d\n", index, fib(index));
    }

    public static long fib(long index) {
        if (index == 1) return 1;
        long result = 0;
        long f0 = 0;
        long f1 = 1;
        for (int i = 2; i <= index; i++) {
            result = f0 + f1;
            f0 = f1;
            f1 = result;
        }
        return result;
    }

    /*
        (Compute greatest common divisor using recursion) The gcd(m, n) can also
        be defined recursively as follows:
        ■ If m % n is 0, gcd(m, n) is n.
        ■ Otherwise, gcd(m, n) is gcd(n, m % n).
        Write a recursive method to find the GCD. Write a test program that prompts the
        user to enter two integers and displays their GCD.
     */
    public static void ch18_3() {
        System.out.print("Enter two integers: ");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.printf("The greatest common divisor of %d and %d is %d\n", m, n, gcd(m, n));
    }

    private static int gcd(int m, int n) {
        if (m % n == 0) {
            return n;
        } else {
            return gcd(n, m % n);
        }
    }

    /*
        (Sum series) Write a recursive method to compute the following series:
        m(i) = 1 + 1/2 + 1/3 + ... + 1/i
        Write a test program that displays m(i) for i = 1, 2, . . . , 10
     */
    public static void ch18_4() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("m(%d) = %.2f\n", i, m(i));
        }
    }

    private static double m(int i) {
        if (i == 1) {
            return 1;
        } else {
            return 1.0 / i + m(i - 1);
        }
    }

    /*
        (Sum series) Write a recursive method to compute the following series:
        m(i) = 1/3 + 2/5 + 3/7 + 4/9 + 5/11 + 6/13 + ... + i/(2*i + 1)
        Write a test program that displays m(i) for i = 1, 2, . . . , 10.
     */
    public static void ch18_5() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("m2(%d) = %.2f\n", i, m2(i));
        }
    }

    private static double m2(int i) {
        if (i == 0) {
            return 0;
        } else {
            return i / (2 * i + 1.0) + m2(i - 1);
        }
    }

    /*
        (Sum series) Write a recursive method to compute the following series:
        m(i) = 1/2 + 2/3 + ... + i/(i + 1)
        Write a test program that displays m(i) for i = 1, 2, . . . , 10.
     */
    public static void ch18_6() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("m3(%d) = %.2f\n", i, m3(i));
        }
    }

    private static double m3(int i) {
        if (i == 0) {
            return 0;
        } else {
            return i / (i + 1.0) + m3(i - 1);
        }
    }

    /*
        (Fibonacci series) Modify Listing 18.2, ComputeFibonacci.java, so that the pro-
        gram finds the number of times the fib method is called. (Hint: Use a static
        variable and increment it every time the method is called.)
     */
    private static int fibCounter = 0;

    public static void ch18_7() {
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = scanner.nextInt();
        System.out.printf("The Fibonacci number at index %d is %d\n", index, fib2(index));
        System.out.printf("fib2 function was called %d times\n", fibCounter);
    }

    private static long fib2(long index) {
        fibCounter++;
        if (index == 0) // Base case
            return 0;
        else if (index == 1) // Base case
            return 1;
        else // Reduction and recursive calls
            return fib2(index - 1) + fib2(index - 2);
    }

    /*
        (Print the digits in an integer reversely) Write a recursive method that displays
        an int value reversely on the console using the following header:
        public static void reverseDisplay(int value)
        For example, reverseDisplay(12345) displays 54321. Write a test program
        that prompts the user to enter an integer and displays its reversal.
     */
    public static void ch18_8() {
        System.out.print("Enter an integer: ");
        int value = scanner.nextInt();
        reverseDisplay(value);
    }

    private static void reverseDisplay(int value) {
        if (value == 0) {
            System.out.println();
            return;
        }
        System.out.print(value % 10);
        reverseDisplay(value / 10);
    }

    /*
        (Print the characters in a string reversely) Write a recursive method that dis-
        plays a string reversely on the console using the following header:
        public static void reverseDisplay(String value)
        For example, reverseDisplay("abcd") displays dcba. Write a test program
        that prompts the user to enter a string and displays its reversal
     */
    public static void ch18_9() {
        System.out.print("Enter a string: ");
        String value = scanner.nextLine();
        reverseDisplay(value);
    }

    public static void reverseDisplay(String value) {
        if (value.isEmpty()) {
            System.out.println();
            return;
        }
        System.out.print(value.charAt(value.length() - 1));
        reverseDisplay(value.substring(0, value.length() - 1));
    }

    /*
        (Occurrences of a specified character in a string) Write a recursive method that
        finds the number of occurrences of a specified letter in a string using the follow-
        ing method header:
        public static int count(String str, char a)
        For example, count("Welcome", 'e') returns 2. Write a test program that
        prompts the user to enter a string and a character, and displays the number of
        occurrences for the character in the string.
     */
    public static void ch18_10() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        System.out.print("Enter a character: ");
        char c = scanner.next().charAt(0);
        System.out.printf("Number of occurrences for '%s' in '%s' is %d", c, str, count(str, c));
    }

    public static int count(String str, char a) {
        if (str.isEmpty()) {
            return 0;
        } else {
            return (str.charAt(0) == a ? 1 : 0) + count(str.substring(1), a);
        }
    }

    /*
        (Sum the digits in an integer using recursion) Write a recursive method that
        computes the sum of the digits in an integer. Use the following method header:
        public static int sumDigits(long n)
        For example, sumDigits(234) returns 2 + 3 + 4 = 9. Write a test program
        that prompts the user to enter an integer and displays its sum.
     */
    public static void ch18_11() {
        System.out.print("Enter an integer: ");
        int value = scanner.nextInt();
        System.out.printf("Sum of digits of value %d is %d\n", value, sumDigits(value));
    }

    public static int sumDigits(long n) {
        if (n == 0) {
            return 0;
        } else {
            return (int) n % 10 + sumDigits(n / 10);
        }
    }

    /*
        (Print the characters in a string reversely) Rewrite Programming Exercise 18.9
        using a helper method to pass the substring high index to the method. The
        helper method header is
        public static void reverseDisplay(String value, int high)
     */
    public static void ch18_12() {
        System.out.print("Enter a string: ");
        String value = scanner.nextLine();
        reverseDisplay2(value);
    }

    public static void reverseDisplay2(String value) {
        reverseDisplay2Helper(value, value.length() - 1);
    }

    public static void reverseDisplay2Helper(String value, int high) {
        System.out.print(value.charAt(high));
        if (high == 0) {
            System.out.println();
        } else {
            reverseDisplay2Helper(value, high - 1);
        }
    }

    /*
        (Find the largest number in an array) Write a recursive method that returns the
        largest integer in an array. Write a test program that prompts the user to enter a
        list of eight integers and displays the largest element.
     */
    public static void ch18_13() {
        int size = 8;
        int[] list = new int[8];
        System.out.printf("Enter a list of %d integers: ", size);
        for (int i = 0; i < list.length; i++) {
            list[i] = scanner.nextInt();
        }
        System.out.printf("Largest element of %s is %d\n", Arrays.toString(list), findLargest(list));
    }

    private static int findLargest(int[] list) {
        return findLargestHelper(list, 1, list[0]);
    }

    private static int findLargestHelper(int[] list, int currentIndex, int currentMax) {
        if (list[currentIndex] > currentMax) {
            currentMax = list[currentIndex];
        }
        if (currentIndex == list.length - 1) {
            return currentMax;
        } else {
            return findLargestHelper(list, currentIndex + 1, currentMax);
        }
    }


    /*
        (Find the number of uppercase letters in a string) Write a recursive method
        to return the number of uppercase letters in a string. Write a test program that
        prompts the user to enter a string and displays the number of uppercase letters in
        the string.
     */
    public static void ch18_14() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        System.out.printf("Number of uppercase letters in this string is: %d\n", numberOfUppercaseLetters(str));
    }

    private static int numberOfUppercaseLetters(String str) {
        return numberOfUppercaseLettersHelper(str, 0, 0);
    }

    private static int numberOfUppercaseLettersHelper(String str, int index, int num) {
        char c = str.charAt(index);
        if (Character.isLetter(c) && Character.isUpperCase(c)) {
            num++;
        }
        if (index == str.length() - 1) {
            return num;
        } else {
            return numberOfUppercaseLettersHelper(str, index + 1, num);
        }
    }

    /*
        (Occurrences of a specified character in a string) Rewrite Programming Exer-
        cise 18.10 using a helper method to pass the substring high index to the method.
        The helper method header is
        public static int count(String str, char a, int high)
     */
    public static void ch18_15() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        System.out.print("Enter a character: ");
        char c = scanner.next().charAt(0);
        System.out.printf("Number of occurrences for '%s' in '%s' is %d", c, str, count2(str, c));
    }

    public static int count2(String str, char a) {
        return count2Helper(str, a, str.length());
    }

    public static int count2Helper(String str, char a, int high) {
        if (str.isEmpty()) {
            return 0;
        } else {
            high--;
            return (str.charAt(high) == a ? 1 : 0) + count2Helper(str.substring(0, high), a, high);
        }
    }

    /*

     */
    public static void ch18_16() {
    }

    /*

     */
    public static void ch18_17() {
    }

    /*

     */
    public static void ch18_18() {
    }

    /*

     */
    public static void ch18_19() {
    }

    /*

     */
    public static void ch18_20() {
    }

    /*

     */
    public static void ch18_21() {
    }

    /*

     */
    public static void ch18_22() {
    }

    /*

     */
    public static void ch18_23() {
    }

    /*

     */
    public static void ch18_24() {
    }

    /*

     */
    public static void ch18_25() {
    }

    /*

     */
    public static void ch18_26() {
    }

    /*

     */
    public static void ch18_27() {
    }

    /*

     */
    public static void ch18_28() {
    }

    /*

     */
    public static void ch18_29() {
    }

    /*

     */
    public static void ch18_30() {
    }

    /*

     */
    public static void ch18_31() {
    }

    /*

     */
    public static void ch18_32() {
    }

    /*

     */
    public static void ch18_33() {
    }

    /*

     */
    public static void ch18_34() {
    }

    /*

     */
    public static void ch18_35() {
    }

    /*

     */
    public static void ch18_36() {
    }

    /*

     */
    public static void ch18_37() {
    }

    /*

     */
    public static void ch18_38() {
    }

    /*

     */
    public static void ch18_39() {
    }


}
