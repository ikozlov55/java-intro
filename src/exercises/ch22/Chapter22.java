package exercises.ch22;

import exercises.ch22.ex07.ClosestPair;
import exercises.ch22.ex07.Pair;
import exercises.ch22.ex08.PrimeNumbersWriter;
import exercises.ch22.ex13.Exercise22_13;
import exercises.ch22.ex14.PrimeNumbersCalculator;
import exercises.ch22.ex15.Exercise22_15;
import exercises.ch22.ex16.Exercise22_16;
import exercises.ch22.ex17.Exercise22_17;
import exercises.ch22.ex18.Exercise22_18;
import exercises.ch22.ex19.Exercise22_19;
import exercises.ch22.ex20.Sudoku;
import exercises.ch22.ex21.Exercise22_21;
import exercises.ch22.ex22.SudokuRecursive;
import exercises.ch22.ex23.Exercise22_23;
import exercises.ch22.ex25.Exercise22_25;
import javafx.geometry.Point2D;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static exercises.utils.Utils.testTime;

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
            gcdResults.add(testTime(() -> gcd(m, n)));
            gcdEuclidResults.add(testTime(() -> gcdEuclid(m, n)));
        }

        String fibsHeader = String.join("", fibIndexes.stream().map(x -> String.format("%8d", x)).toList());
        System.out.printf("%s│%s\n", " ".repeat(25), fibsHeader);
        System.out.printf("%s┼%s\n", "─".repeat(25), "─".repeat(fibIndexes.size() * 10));
        String gcdRow = String.join("", gcdResults.stream().map(x -> String.format("%8.2f", x)).toList());
        System.out.printf("%-25s│%s\n", "Listing 22.3 GCD", gcdRow);
        String gcdEuclidRow = String.join("", gcdEuclidResults.stream().map(x -> String.format("%8.2f", x)).toList());
        System.out.printf("%-25s│%s\n", "Listing 22.3 GCDEuclid", gcdEuclidRow);
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

        ArrayList<Point2D> convexHull = getConvexHullGiftWrapping(s);
        System.out.println("The convex hull is");
        for (Point2D p : convexHull) {
            System.out.printf(Locale.US, "(%.1f, %.1f) ", p.getX(), p.getY());
        }
    }

    public static ArrayList<Point2D> getConvexHullGiftWrapping(double[][] s) {
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
        (Geometry: Graham’s algorithm for finding a convex hull) Section 22.10.2
        introduced Graham’s algorithm for finding a convex hull for a set of points.
        Assume Java’s coordinate system is used for the points. Implement the algo-
        rithm using the following method:
        public static ArrayList<MyPoint> getConvexHull(double[][] s)
        Write a test program that prompts the user to enter the set size and the points,
        and displays the points that form a convex hull. Here is a sample run:
        How many points are in the set? 6
        Enter six points: 1 2.4 2.5 2 1.5 34.5 5.5 6 6 2.4 5.5 9
        The convex hull is
        (2.5, 2.0) (6.0, 2.4) (5.5, 9.0) (1.5, 34.5) (1.0, 2.4)


     */
    public static void ch22_11() {
        System.out.print("How many points are in the set? ");
        int n = scanner.nextInt();
        double[][] s = new double[n][2];
        System.out.printf("Enter %d points: ", n);
        for (int i = 0; i < n; i++) {
            s[i] = new double[]{scanner.nextDouble(), scanner.nextDouble()};
        }

        ArrayList<MyPoint> convexHull = getConvexHullGrahams(s);
        System.out.println("The convex hull is");
        convexHull.forEach(p -> System.out.print(p + " "));
    }

    public static ArrayList<MyPoint> getConvexHullGrahams(double[][] s) {
        ArrayList<MyPoint> S = Arrays.stream(s)
                .map(p -> new MyPoint(p[0], p[1]))
                .collect(Collectors.toCollection(ArrayList::new));
        MyPoint p0 = S.stream().max(Comparator.comparing(MyPoint::getY).thenComparing(MyPoint::getX)).get();
        S.remove(p0);
        S.forEach(p -> p.setRightMostLowestPoint(p0));
        S.sort(Comparator.reverseOrder());
        S.add(0, p0);
        ArrayList<MyPoint> pointsToRemove = new ArrayList<>();
        for (int i = 1; i < S.size() - 1; i++) {
            MyPoint p1 = S.get(i);
            MyPoint p2 = S.get(i + 1);
            if (p1.compareTo(p2) == 0) {
                pointsToRemove.add(p0.distance(p1) < p0.distance(p2) ? p1 : p2);
            }
        }
        S.removeAll(pointsToRemove);
        System.out.println(S);

        Stack<MyPoint> H = new Stack<>();
        H.push(S.get(0));
        H.push(S.get(1));
        H.push(S.get(2));

        int i = 3;
        while (i < S.size()) {
            MyPoint t1 = H.pop();
            MyPoint t2 = H.peek();
            MyPoint p = S.get(i);
            t1.setRightMostLowestPoint(t2);
            if (t1.compareTo(p) > 0) {
                H.push(t1);
                H.push(p);
                i++;
            }
        }

        return new ArrayList<>(H);
    }

    private static class MyPoint implements Comparable<MyPoint> {
        double x, y;
        MyPoint rightMostLowestPoint;

        MyPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void setRightMostLowestPoint(MyPoint p) {
            rightMostLowestPoint = p;
        }


        @Override
        public int compareTo(MyPoint o) {
            // Implement it to compare this point with point o
            // angularly along the x-axis with rightMostLowestPoint
            // as the center, as shown in Figure 22.10b. By implementing
            // the Comparable interface, you can use the Array.sort
            // method to sort the points to simplify coding.
            MyPoint p0 = rightMostLowestPoint;
            MyPoint p1 = this;
            double d = (p1.getX() - p0.getX()) * (o.getY() - p0.getY()) - (o.getX() - p0.getX()) * (p1.getY() - p0.getY());
            if (d < 0) {
                return 1;
            } else if (d > 0) {
                return -1;
            } else {
                return 0;
            }
        }

        public double distance(MyPoint p) {
            return Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - x, 2));
        }


        @Override
        public String toString() {
            return String.format("(%.1f, %.1f)", x, y);
        }
    }

    /*
        (Last 100 prime numbers) Programming Exercise 22.8 stores the prime numbers
        in a file named PrimeNumbers.dat. Write an efficient program that reads the
        last 100 numbers in the file. (Hint: Don’t read all numbers from the file. Skip
        all numbers before the last 100 numbers in the file.)
     */
    public static void ch22_12() {
        File dataFile = new File("src/exercises/ch22/PrimeNumbers.dat");
        PrimeNumbersWriter primeNumbersWriter = new PrimeNumbersWriter(dataFile);
        long[] lastHundred = primeNumbersWriter.getLatestNumbers(100);
        System.out.println(Arrays.toString(lastHundred));
    }

    /*
        (Geometry: convex hull animation) Programming Exercise 22.11 finds a convex
        hull for a set of points entered from the console. Write a program that enables
        the user to add or remove points by clicking the left or right mouse button and
        displays a convex hull, as shown in Figure 22.8c
     */
    public static void ch22_13() {
        Exercise22_13.run();
    }

    /*
        (Execution time for prime numbers) Write a program that obtains the execution
        time for finding all the prime numbers less than 8,000,000, 10,000,000,
        12,000,000, 14,000,000, 16,000,000, and 18,000,000 using the algorithms in
        Listings 22.5–22.7.
     */
    public static void ch22_14() {
        ArrayList<Double> t1 = new ArrayList<>();
        ArrayList<Double> t2 = new ArrayList<>();
        ArrayList<Double> t3 = new ArrayList<>();
        StringBuilder header = new StringBuilder();
        for (int i = 8; i <= 18; i += 2) {
            int n = i * 1000000;
            header.append(String.format("%10d", n));
            t1.add(testTime(() -> PrimeNumbersCalculator.getPrimes(n)));
            t2.add(testTime(() -> PrimeNumbersCalculator.getPrimesEfficient(n)));
            t3.add(testTime(() -> PrimeNumbersCalculator.getPrimesSieveOfEratosthenes(n)));
        }

        System.out.printf("%s│%s\n", " ".repeat(15), header);
        System.out.printf("%s┼%s\n", "─".repeat(15), "─".repeat(header.length() * 10));
        String row1 = String.join("", t1.stream().map(x -> String.format("%10.2f", x)).toList());
        System.out.printf("%-15s│%s\n", "Listing 22.5", row1);
        String row2 = String.join("", t2.stream().map(x -> String.format("%10.2f", x)).toList());
        System.out.printf("%-15s│%s\n", "Listing 22.6", row2);
        String row3 = String.join("", t3.stream().map(x -> String.format("%10.2f", x)).toList());
        System.out.printf("%-15s│%s\n", "Listing 22.7", row3);
    }

    /*
        (Geometry: noncrossed polygon) Write a program that enables the user to add
        or remove points by clicking the left or right mouse button and displays a non-
        crossed polygon that links all the points, as shown in Figure 22.17a. A polygon
        is crossed if two or more sides intersect, as shown in Figure 22.17b. Use the
        following algorithm to construct a polygon from a set of points:
        Step 1: Given a set of points S, select the rightmost lowest
        point p0 in the set S.
        Step 2: Sort the points in S angularly along the x-axis with p0
        as the center. If there is a tie and two points have the same
        angle, the one that is closer to p0 is considered greater. The
        points in S are now sorted as p0, p1, p2, ..., pn−1.
        Step 3: The sorted points form a noncrossed polygon.
     */
    public static void ch22_15() {
        Exercise22_15.run();
    }

    /*
        (Linear search animation) Write a program that animates the linear search
        algorithm. Create an array that consists of 20 distinct numbers from 1 to 20
        in a random order. The array elements are displayed in a histogram, as shown
        in Figure 22.18. You need to enter a search key in the text field. Clicking the
        Step button causes the program to perform one comparison in the algorithm and
        repaints the histogram with a bar indicating the search position. This button
        also freezes the text field to prevent its value from being changed. When the
        algorithm is finished, display the status in the label at the top of the border pane
        to inform the user. Clicking the Reset button creates a new random array for a
        new start. This button also makes the text field editable.
     */
    public static void ch22_16() {
        Exercise22_16.run();
    }

    /*
        (Closest-pair animation) Write a program that enables the user to add/remove
        points by clicking the left/right mouse button and displays a line that connects
        the pair of nearest points, as shown in Figure 22.4.
     */
    public static void ch22_17() {
        Exercise22_17.run();
    }

    /*
        (Binary search animation) Write a program that animates the binary search algo-
        rithm. Create an array with numbers from 1 to 20 in this order. The array ele-
        ments are displayed in a histogram, as shown in Figure 22.19. You need to enter
        a search key in the text field. Clicking the Step button causes the program to
        perform one comparison in the algorithm. Use a light-gray color to paint the bars
        for the numbers in the current search range, and use a black color to paint the bar
        indicating the middle number in the search range. The Step button also freezes the
        text field to prevent its value from being changed. When the algorithm is finished,
        display the status in a label at the top of a border pane. Clicking the Reset button
        enables a new search to start. This button also makes the text field editable.
     */
    public static void ch22_18() {
        Exercise22_18.run();
    }

    /*
        (Largest block) The problem for finding a largest block is described in
        Programming Exercise 8.35. Design a dynamic programming algorithm for solv-
        ing this problem in O(n2) time. Write a test program that displays a 10- by-10
        square matrix, as shown in Figure 22.20a. Each element in the matrix is 0 or 1,
        randomly generated with a click of the Refresh button. Display each number cen-
        tered in a text field. Use a text field for each entry. Allow the user to change the
        entry value. Click the Find Largest Block button to find a largest square submatrix
        that consists of 1s. Highlight the numbers in the block, as shown in Figure 22.20b
     */
    public static void ch22_19() {
        Exercise22_19.run();
    }

    /*
        (Game: multiple Sudoku solutions) The complete solution for the Sudoku
        problem is given in Supplement VI.A. A Sudoku problem may have multiple
        solutions. Modify Sudoku.java in Supplement VI.A to display the total number
        of solutions. Display two solutions if multiple solutions exist.
     */
    public static void ch22_20() {
        Sudoku sudoku = new Sudoku();
        if (!sudoku.isValid()) {
            System.out.println("Invalid input");
            return;
        }
        int[][][] solutions = sudoku.search();
        if (solutions.length > 0) {
            System.out.printf("%s solutions found:\n", solutions.length);
            for (int[][] solution : solutions) {
                Sudoku.printGrid(solution);
            }
        } else {
            System.out.println("No solution");
        }
    }

    /*
        (Game: Sudoku) The complete solution for the Sudoku problem is given in
        Supplement VI.C. Write a program that lets the user enter the input from the
        text fields, as shown in Figure 22.21a. Clicking the Solve button displays the
        result, as shown in Figures 22.21b and c.
     */
    public static void ch22_21() {
        Exercise22_21.run();
    }

    /*
        (Game: recursive Sudoku) Write a recursive solution for the Sudoku problem

        5 3 4 6 7 8 9 1 2
        6 7 2 1 9 5 3 4 8
        1 9 8 3 4 2 5 6 7
        8 5 9 7 6 1 4 2 3
        4 2 6 8 5 3 7 9 1
        7 1 3 9 2 4 8 5 6
        9 6 1 5 3 7 2 8 4
        2 8 7 4 1 9 6 3 5
        3 4 5 2 8 6 1 7 9
     */
    public static void ch22_22() {
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        SudokuRecursive sudoku = new SudokuRecursive(grid);
        if (!sudoku.isValid()) {
            System.out.println("Invalid input");
            return;
        }
        if (sudoku.search()) {
            System.out.println("The solution is found:");
            System.out.println(sudoku);
        } else {
            System.out.println("No solution");
        }
    }

    /*
        (Game: multiple Eight Queens solution) Write a program to display all possible
        solutions for the Eight Queens puzzle in a scroll pane, as shown in Figure 22.22.
        For each solution, put a label to denote the solution number. (Hint: Place all
        solution panes into an HBox and place this one pane into a ScrollPane. If
        you run into a StackOverflowError, run the program using java –Xss200m
        Exercise22_23 from the command window.)
     */
    public static void ch22_23() {
        Exercise22_23.run();
    }

    /*
        (Find the smallest number) Write a method that uses the divide-and-conquer
        approach to find the smallest number in a list
     */
    public static void ch22_24() {
        List<Integer> list = List.of(5, 3, 7, 8, 2, 1, 9);
        System.out.printf("Smallest value in list %s is %d\n", list, findSmallest(list));
    }

    private static Integer findSmallest(List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0);
        } else {
            int mid = list.size() / 2;
            int a = findSmallest(list.subList(0, mid));
            int b = findSmallest(list.subList(mid, list.size()));
            return Math.min(a, b);
        }
    }

    /*
        (Game: Sudoku) Revise Programming Exercise 22.21 to display all solutions
        for the Sudoku game, as shown in Figure 22.23a. When you click the Solve
        button, the program stores all solutions in an ArrayList. Each element in the
        list is a two-dimensional 9-by-9 grid. If the program has multiple solutions, the
        Next button appears as shown in Figure 22.23b. You can click the Next button
        to display the next solution and also add a label to show the solution count.
        When the Clear button is clicked, the cells are cleared and the Next button is
        hidden as shown in Figure 22.23c.
     */
    public static void ch22_25() {
        Exercise22_25.run();
    }

    /*
        (Bin packing with smallest object first) The bin packing problem is to pack the
        objects of various weights into containers. Assume each container can hold a
        maximum of 10 pounds. The program uses an algorithm that places an object with
        the smallest weight into the first bin in which it would fit. Your program should
        prompt the user to enter the total number of objects and the weight of each object.
        The program displays the total number of containers needed to pack the objects,
        and the contents of each container. Here is a sample run of the program:
            Enter the number of objects: 6
            Enter the weights of the objects: 7 5 2 3 5 8
            Container 1 contains objects with weight 2 3 5
            Container 2 contains objects with weight 5
            Container 3 contains objects with weight 7
            Container 4 contains objects with weight 8
        Does this program produce an optimal solution, that is, finding the minimum
        number of containers to pack the objects?
     */
    public static void ch22_26() {
        System.out.print("Enter the number of objects: ");
        int n = scanner.nextInt();
        List<Integer> objects = new ArrayList<>();
        System.out.print("Enter the weights of the objects: ");
        for (int i = 0; i < n; i++) {
            objects.add(scanner.nextInt());
        }
        objects.sort(Comparator.naturalOrder());

        final int capacity = 10;
        int binNumber = 1;
        while (!objects.isEmpty()) {
            int currentCapacity = 0;
            StringBuilder binContents = new StringBuilder();
            while (!objects.isEmpty() && currentCapacity + objects.get(0) <= capacity) {
                int obj = objects.remove(0);
                currentCapacity += obj;
                binContents.append(obj).append(" ");
            }

            System.out.printf("Container %d contains objects with weight %s\n", binNumber, binContents);
            binNumber++;
        }
    }

    /*
        (Optimal bin packing) Rewrite the preceding program so that it finds an optimal
        solution that packs all objects using the smallest number of containers. Here is
        a sample run of the program:
            Enter the number of objects: 6
            Enter the weights of the objects: 7 5 2 3 5 8
            Container 1 contains objects with weight 7 3
            Container 2 contains objects with weight 5 5
            Container 3 contains objects with weight 2 8
            The optimal number of bins is 3
        What is the time complexity of your program?
     */
    public static void ch22_27() {
        System.out.print("Enter the number of objects: ");
        int n = scanner.nextInt();
        List<Integer> objects = new ArrayList<>();
        System.out.print("Enter the weights of the objects: ");
        for (int i = 0; i < n; i++) {
            objects.add(scanner.nextInt());
        }
        objects.sort(Comparator.reverseOrder());
        System.out.println(objects);

        final int maxCapacity = 10;
        List<List<Integer>> bins = new ArrayList<>();
        while (true) {
            int i = 0;
            while (i < bins.size() && !objects.isEmpty()) {
                int capacity = bins.get(i).stream().reduce(0, Integer::sum);
                if (capacity + objects.get(0) <= maxCapacity) {
                    bins.get(i).add(objects.remove(0));
                    i = 0;
                } else {
                    i++;
                }
            }
            if (objects.isEmpty()) break;
            List<Integer> bin = new ArrayList<>();
            bin.add(objects.remove(0));
            bins.add(bin);
        }

        for (int i = 0; i < bins.size(); i++) {
            String contents = bins.get(i)
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(" "));
            System.out.printf("Container %d contains objects with weight %s\n", i + 1, contents);
        }

        System.out.printf("The optimal number of bins is %d\n", bins.size());
    }
}
