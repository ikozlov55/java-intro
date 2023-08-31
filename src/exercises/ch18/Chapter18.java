package exercises.ch18;

import java.math.BigInteger;
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

     */
    public static void ch18_6() {
    }

    /*

     */
    public static void ch18_7() {
    }

    /*

     */
    public static void ch18_8() {
    }

    /*

     */
    public static void ch18_9() {
    }

    /*

     */
    public static void ch18_10() {
    }

    /*

     */
    public static void ch18_11() {
    }

    /*

     */
    public static void ch18_12() {
    }

    /*

     */
    public static void ch18_13() {
    }

    /*

     */
    public static void ch18_14() {
    }

    /*

     */
    public static void ch18_15() {
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
