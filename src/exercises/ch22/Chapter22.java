package exercises.ch22;

import exercises.ch22.ex07.ClosestPair;
import exercises.ch22.ex07.Pair;
import exercises.ch22.ex08.PrimeNumbersWriter;
import javafx.geometry.Point2D;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        (Revise Boyer-Moore algorithm) Revise the implementation for the
        Boyer-Moore algorithm in Listing 22.15 StringMatchBoyerMoore.java
        to test where a mismatch character is in the pattern in O(1) time using a
        set that consists of all the characters in the pattern. If the test is false, the algo-
        rithm can shift the pattern past the mismatched character.
            Enter a string s1: Mississippi
            Enter a string s2: sip
            matched at index 6
     */
    public static void ch22_4() {
        System.out.print("Enter a string s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter a string s2: ");
        String s2 = scanner.nextLine();
        int index = match(s1, s2);
        if (index >= 0) {
            System.out.printf("matched at index %d\n", index);
        } else {
            System.out.println("no match");
        }
    }

    // Return the index of the first match. –1 otherwise.
    public static int match(String text, String pattern) {
        Set<Character> patternChars = new HashSet<>();
        pattern.chars().forEach(x -> patternChars.add((char) x));
        int i = pattern.length() - 1;
        while (i < text.length()) {
            int k = i;
            int j = pattern.length() - 1;
            while (j >= 0) {
                if (text.charAt(k) == pattern.charAt(j)) {
                    k--;
                    j--;
                } else {
                    break;
                }
            }
            if (j < 0) {
                return i - pattern.length() + 1; // A match found
            }
            if (!patternChars.contains(text.charAt(k))) {
                i = k + pattern.length();
            } else {
                i = k + pattern.length() - 1 - findLastIndex(text.charAt(k), j - 1, pattern);
            }
        }

        return -1;
    }

    // Return the index of the last element in pattern[0 .. j]
    // that matches ch. –1 otherwise.
    private static int findLastIndex(char ch, int j, String pattern) {
        for (int k = j; k >= 0; k--) {
            if (ch == pattern.charAt(k)) {
                return k;
            }
        }
        return -1;
    }

    /*
        (Same-number subsequence) Write an O(n) time program that prompts the user
        to enter a sequence of integers ending with 0 and finds the longest subsequence
        with the same number. Here is a sample run of the program:
            Enter a series of numbers ending with 0:
            2 4 4 8 8 8 8 2 4 4 0
            The longest same number sequence starts at index 3 with 4 values of 8
     */
    public static void ch22_5() {
        System.out.println("Enter a series of numbers ending with 0:");
        ArrayList<Integer> numbers = new ArrayList<>();
        while (true) {
            int n = scanner.nextInt();
            if (n == 0) {
                break;
            } else {
                numbers.add(n);
            }
        }
        int index = -1;
        int number = 0;
        int sequenceLength = 0;
        int maxNumber = 0;
        int maxSequenceLength = 0;
        int maxIndex = -1;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == number) {
                sequenceLength++;
            } else {
                if (sequenceLength > maxSequenceLength) {
                    maxSequenceLength = sequenceLength;
                    maxNumber = number;
                    maxIndex = index;
                }
                number = numbers.get(i);
                sequenceLength = 1;
                index = i;
            }
        }
        System.out.printf("The longest same number sequence starts at index %d with %d values of %d\n",
                maxIndex, maxSequenceLength, maxNumber);
    }

    /*
        (Execution time for GCD) Write a program that obtains the execution time for
        finding the GCD of every two consecutive Fibonacci numbers from the index
        40 to index 45 using the algorithms in Listing 22.3 and Listing 22.4. Your pro-
        gram should print a table like this:
     */
    public static void ch22_6() {
        List<Integer> fibIndexes = IntStream.rangeClosed(40, 45).boxed().toList();
        Map<Integer, Long> fibValues = new HashMap<>();
        for (int n : fibIndexes) {
            fibValues.put(n, fib(n));
        }
        List<Double> gcdResults = new ArrayList<>();
        List<Double> gcdEuclidResults = new ArrayList<>();
        for (int i = 0; i < fibIndexes.size() - 1; i++) {
            long m = fibValues.get(fibIndexes.get(i + 1));
            long n = fibValues.get(fibIndexes.get(i));
            gcdResults.add(executionTime(() -> gcd(m, n)));
            gcdEuclidResults.add(executionTime(() -> gcdEuclid(m, n)));
        }

        String fibsHeader = String.join("", fibIndexes.stream().map(x -> String.format("%8d", x)).toList());
        System.out.printf("%s│%s\n", " ".repeat(25), fibsHeader);
        System.out.printf("%s┼%s\n", "─".repeat(25), "─".repeat(fibIndexes.size() * 10));
        String gcdRow = String.join("", gcdResults.stream().map(x -> String.format("%8.2f", x)).toList());
        System.out.printf("%-25s│%s\n", "Listing 22.3 GCD", gcdRow);
        String gcdEuclidRow = String.join("", gcdEuclidResults.stream().map(x -> String.format("%8.2f", x)).toList());
        System.out.printf("%-25s│%s\n", "Listing 22.3 GCDEuclid", gcdEuclidRow);
    }

    private static double executionTime(Runnable action) {
        long startTime = System.currentTimeMillis();
        action.run();
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }

    public static long gcd(long m, long n) {
        long gcd = 1;
        if (m % n == 0) return n;
        for (long k = n / 2; k >= 1; k--) {
            if (m % k == 0 && n % k == 0) {
                gcd = k;
                break;
            }
        }

        return gcd;
    }

    public static long gcdEuclid(long m, long n) {
        return m % n == 0 ? n : gcdEuclid(n, m % n);
    }

    public static long fib(long n) {
        long f0 = 0; // For fib(0)
        long f1 = 1; // For fib(1)
        long f2 = 1; // For fib(2)

        if (n == 0)
            return f0;
        else if (n == 1)
            return f1;
        else if (n == 2)
            return f2;

        for (int i = 3; i <= n; i++) {
            f0 = f1;
            f1 = f2;
            f2 = f0 + f1;
        }

        return f2;
    }

    /*
        (Closest pair of points) Section 22.8 introduced an algorithm for finding the
        closest pair of points using a divide-and-conquer approach. Implement the
        algorithm to meet the following requirements:
        ■ Define a class named Pair with the data fields p1 and p2 to represent
        two points and a method named getDistance() that returns the distance
        between the two points.
        ■ Implement the following methods:
        // Return the distance of the closest pair of points
        public static Pair getClosestPair(double[][] points)
        // Return the distance of the closest pair of points
        public static Pair getClosestPair(Point2D[] points)
        // Return the distance of the closest pair of points
        // in pointsOrderedOnX[low..high]. This is a recursive
        // method. pointsOrderedOnX and pointsOrderedOnY are
        // not changed in the subsequent recursive calls.
        public static Pair distance(Point2D[] pointsOrderedOnX,int low, int high, Point2D[] pointsOrderedOnY)
        // Compute the distance between two points p1 and p2
        public static double distance(Point2D p1, Point2D p2)
        // Compute the distance between points (x1, y1) and (x2, y2)
        public static double distance(double x1, double y1, double x2, double y2)
    */
    public static void ch22_7() {
        Point2D[] points = new Point2D[]{
                new Point2D(0, 0),
                new Point2D(2, 7),
                new Point2D(3, 2),
                new Point2D(2, 5),
                new Point2D(2, 0),
                new Point2D(5, 5),
                new Point2D(3, 8),
        };
        Pair closestPair = ClosestPair.getClosestPair(points);
        System.out.printf("Closest pair found using Divide-and-Conquer algorithm is: %s\n", closestPair);
        Pair closestPairBruteForce = ClosestPair.getClosestPairBruteForce(points);
        System.out.printf("Closest pair found using brute force algorithm is: %s\n", closestPairBruteForce);
    }

    /*
        (All prime numbers up to 10,000,000,000) Write a program that finds
        all prime numbers up to 10,000,000,000. There are approximately
        455,052,511 such prime numbers. Your program should meet the following
        requirements:
        ■ Your program should store the prime numbers in a binary data file, named
        PrimeNumbers.dat. When a new prime number is found, the number is
        appended to the file.
        ■ To find whether a new number is prime, your program should load the
        prime numbers from the file to an array of the long type of size 10000.
        If no number in the array is a divisor for the new number, continue to read
        the next 10000 prime numbers from the data file, until a divisor is found
        or all numbers in the file are read. If no divisor is found, the new number
        is prime.
        ■ Since this program takes a long time to finish, you should run it as a batch
        job from a UNIX machine. If the machine is shut down and rebooted, your
        program should resume by using the prime numbers stored in the binary data
        file rather than start over from scratch.
     */
    public static void ch22_8() {
        File dataFile = new File("src/exercises/ch22/PrimeNumbers.dat");
        PrimeNumbersWriter primeNumbersWriter = new PrimeNumbersWriter(dataFile);
        primeNumbersWriter.start();
    }


    /*
        (Geometry: gift-wrapping algorithm for finding a convex hull) Section 22.10.1
        introduced the gift-wrapping algorithm for finding a convex hull for a set of
        points. Implement the algorithm using the following method:
        public static ArrayList<Point2D> getConvexHull(double[][] s)
        Point2D was introduced in Section 9.6.3.
        Write a test program that prompts the user to enter the set size and the points, and
        displays the points that form a convex hull. Note that when you debug the code,
        you will discover that the algorithm overlooked two cases (1) when t1 = t0
        and (2) when there is a point that is on the same line from t0 to t1. When either
        case happens, replace t1 by point p if the distance from t0 to p is greater than
        the distance from t0 to t1. Here is a sample run:
            How many points are in the set? 6
            Enter 6 points: 1 2.4 2.5 2 1.5 34.5 5.5 6 6 2.4 5.5 9
            The convex hull is
            (2.5, 2.0) (6.0, 2.4) (5.5, 9.0) (1.5, 34.5) (1.0, 2.4)
     */
    public static void ch22_9() {
        System.out.print("How many points are in the set? ");
        int n = scanner.nextInt();
        double[][] s = new double[n][2];
        System.out.printf("Enter %d points: ", n);
        for (int i = 0; i < n; i++) {
            s[i] = new double[]{scanner.nextDouble(), scanner.nextDouble()};
        }

        ArrayList<Point2D> convexHull = getConvexHull(s);
        System.out.println("The convex hull is");
        for (Point2D p : convexHull) {
            System.out.printf(Locale.US, "(%.1f, %.1f) ", p.getX(), p.getY());
        }
    }

    public static ArrayList<Point2D> getConvexHull(double[][] s) {
        ArrayList<Point2D> H = new ArrayList<>();
        ArrayList<Point2D> S = Arrays.stream(s)
                .map(p -> new Point2D(p[0], p[1]))
                .collect(Collectors.toCollection(ArrayList::new));
        Point2D h0 = S.stream().max(Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX)).get();
        H.add(h0);
        Point2D t0 = h0;
        while (true) {
            Point2D t1 = S.get(0);
            for (Point2D p : S) {
                double d = d(t0, t1, p);
                if (d < 0) {
                    t1 = p;
                } else if ((d == 0 || t1.equals(t0)) && t0.distance(p) > t0.distance(t1)) {
                    t1 = p;
                }
            }
            if (t1.equals(h0)) {
                return H;
            } else {
                H.add(t1);
                t0 = t1;
            }
        }
    }

    private static double d(Point2D p0, Point2D p1, Point2D p2) {
        return (p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) - (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
    }

    /*
        (Number of prime numbers) Programming Exercise 22.8 stores the prime numbers
        in a file named PrimeNumbers.dat. Write a program that finds the num-
        ber of prime numbers that are less than or equal to 10, 100, 1,000, 10,000,
        100,000, 1,000,000, 10,000,000, 100,000,000, 1,000,000,000, and
        10,000,000,000. Your program should read the data from PrimeNumbers.dat.
     */
    public static void ch22_10() {
        File dataFile = new File("src/exercises/ch22/PrimeNumbers.dat");
        PrimeNumbersWriter primeNumbersWriter = new PrimeNumbersWriter(dataFile);
        for (long n = 10; n <= 10_000; n *= 10) {
            long primes = primeNumbersWriter.getNumberOfPrimes(n);
            System.out.printf("Number of primes less than or equal to %d is %d\n", n, primes);
        }

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
