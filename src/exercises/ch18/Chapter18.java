package exercises.ch18;

import exercises.ch18.ex19.Exercise18_19;
import exercises.ch18.ex20.Exercise18_20;
import exercises.ch18.ex26.Exercise18_26;
import exercises.ch18.ex27.Exercise18_27;
import exercises.ch18.ex32.Exercise18_32;
import exercises.ch18.ex33.Exercise18_33;
import exercises.ch18.ex34.Exercise18_34;
import exercises.ch18.ex35.Exercise18_35;
import exercises.ch18.ex36.Exercise18_36;
import exercises.ch18.ex37.Exercise18_37;
import exercises.ch18.ex38.Exercise18_38;
import exercises.ch18.ex39.Exercise18_39;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

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
        (Find the number of uppercase letters in an array) Write a recursive method
        to return the number of uppercase letters in an array of characters. You need to
        define the following two methods. The second one is a recursive helper method.
        public static int count(char[] chars)
        public static int count(char[] chars, int high)
        Write a test program that prompts the user to enter a list of characters in one line
        and displays the number of uppercase letters in the list.
     */
    public static void ch18_16() {
        System.out.print("Enter a list of characters: ");
        char[] chars = scanner.nextLine().toCharArray();
        System.out.printf("Number of uppercase letters in list %s is %d\n", Arrays.toString(chars), count(chars));
    }

    public static int count(char[] chars) {
        return count(chars, chars.length - 1);
    }

    public static int count(char[] chars, int high) {
        if (high == -1) {
            return 0;
        }
        char c = chars[high];
        int curr = Character.isLetter(c) && Character.isUpperCase(c) ? 1 : 0;
        return curr + count(chars, high - 1);
    }

    /*
        (Occurrences of a specified character in an array) Write a recursive method that
        finds the number of occurrences of a specified character in an array. You need to
        define the following two methods. The second one is a recursive helper method.
        public static int count(char[] chars, char ch)
        public static int count(char[] chars, char ch, int high)
        Write a test program that prompts the user to enter a list of characters in one line,
        and a character, and displays the number of occurrences of the character in the list.
     */
    public static void ch18_17() {
        System.out.print("Enter a list of characters: ");
        char[] chars = scanner.nextLine().toCharArray();
        System.out.print("Enter a character: ");
        char ch = scanner.next().charAt(0);
        System.out.printf("Number of occurrences of %c in list %s is %d\n", ch, Arrays.toString(chars), count(chars, ch));
    }

    public static int count(char[] chars, char ch) {
        return count(chars, ch, chars.length - 1);
    }

    public static int count(char[] chars, char ch, int high) {
        if (high == -1) {
            return 0;
        }
        return (chars[high] == ch ? 1 : 0) + count(chars, ch, high - 1);
    }

    /*
        (Tower of Hanoi) Modify Listing 18.8, TowerOfHanoi.java, so the program finds
        the number of moves needed to move n disks from tower A to tower B. (Hint:
        Use a static variable and increment it every time the method is called.)
     */
    public static void ch18_18() {
        System.out.print("Enter number of disks: ");
        int n = scanner.nextInt();
        System.out.println("The moves are:");
        moveDisks(n, 'A', 'B', 'C');
        System.out.printf("Total moves: %d\n", diskMoves);
    }

    private static int diskMoves = 0;

    public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        diskMoves++;
        if (n == 1) {
            System.out.printf("Move disk %d from %c to %c\n", n, fromTower, toTower);
        } else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            System.out.printf("Move disk %d from %c to %c\n", n, fromTower, toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }

    /*
        (Sierpinski triangle) Revise Listing 18.9 to develop a program that lets the
        user use the +/- buttons, primary/secondary mouse buttons, and UP/
        DOWN arrow keys to increase or decrease the current order by 1, as shown
        in Figure 18.12a. The initial order is 0. If the current order is 0, the Decrease
        button is ignored.
     */
    public static void ch18_19() {
        Exercise18_19.run();
    }

    /*
        (Display circles) Write a Java program that displays ovals, as shown in
        Figure 18.12b. The circles are centered in the pane. The gap between two adja-
        cent circles is 10 pixels, and the gap between the border of the pane and the
        largest circle is also 10.
     */
    public static void ch18_20() {
        Exercise18_20.run();
    }

    /*
        (Decimal to binary) Write a recursive method that converts a decimal number
        into a binary number as a string. The method header is
        public static String dec2Bin(int value)
        Write a test program that prompts the user to enter a decimal number and displays
        its binary equivalent.
     */
    public static void ch18_21() {
        System.out.print("Enter a decimal number: ");
        int value = scanner.nextInt();
        System.out.printf("Binary value of %d is %s\n", value, dec2Bin(value));
    }

    public static String dec2Bin(int value) {
        if (value == 0) {
            return "0";
        }
        return dec2Bin(value / 2) + value % 2;
    }

    /*
        (Decimal to hex) Write a recursive method that converts a decimal number into
        a hex number as a string. The method header is
        public static String dec2Hex(int value)
        Write a test program that prompts the user to enter a decimal number and displays
        its hex equivalent.
     */
    public static void ch18_22() {
        System.out.print("Enter a decimal number: ");
        int value = scanner.nextInt();
        System.out.printf("Hex value of %d is %s\n", value, dec2Hex(value));
    }

    public static String dec2Hex(int value) {
        if (value == 0) {
            return "";
        }
        int remainder = value % 16;
        char c = (char) (remainder > 9 ? 'A' + remainder - 10 : '0' + remainder);
        return dec2Hex(value / 16) + c;
    }

    /*
        (Binary to decimal) Write a recursive method that parses a binary number as a
        string into a decimal integer. The method header is
        public static int bin2Dec(String binaryString)
        Write a test program that prompts the user to enter a binary string and displays
        its decimal equivalent.
     */
    public static void ch18_23() {
        System.out.print("Enter a binary number: ");
        String value = scanner.next();
        System.out.printf("Decimal value of %s is %d\n", value, bin2Dec(value));
    }

    public static int bin2Dec(String binaryString) {
        return bin2DecHelper(binaryString, binaryString.length() - 1, 1);
    }

    public static int bin2DecHelper(String binaryString, int index, int order) {
        if (index < 0) {
            return 0;
        }
        int value = Character.getNumericValue(binaryString.charAt(index));
        return value * order + bin2DecHelper(binaryString, index - 1, order * 2);
    }


    /*
        (Hex to decimal) Write a recursive method that parses a hex number as a string
        into a decimal integer. The method header is
        public static int hex2Dec(String hexString)
        Write a test program that prompts the user to enter a hex string and displays its
        decimal equivalent.
     */
    public static void ch18_24() {
        System.out.print("Enter a hex number: ");
        String value = scanner.next();
        System.out.printf("Decimal value of %s is %d\n", value, hex2Dec(value));
    }

    public static int hex2Dec(String hexString) {
        return hex2DecHelper(hexString, hexString.length() - 1, 1);
    }

    public static int hex2DecHelper(String hexString, int index, int order) {
        if (index < 0) {
            return 0;
        }
        char c = hexString.charAt(index);
        int value = 0;
        if (c >= '0' && c <= '9') {
            value = c - '0';
        } else if (c >= 'A' && c <= 'F') {
            value = c - 'A' + 10;
        } else {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        return hex2DecHelper(hexString, index - 1, order * 16) + value * order;
    }


    /*
        (String permutation) Write a recursive method to print all the permutations of a
        string. For example, for the string abc, the permutation is
        abc
        acb
        bac
        bca
        cab
        cba
        (Hint: Define the following two methods. The second is a helper method.)
        public static void displayPermutation(String s)
        public static void displayPermutation(String s1, String s2)
        The first method simply invokes displayPermutation(" ", s). The second
        method uses a loop to move a character from s2 to s1 and recursively invokes it
        with new s1 and s2. The base case is that s2 is empty and prints s1 to the console.
        Write a test program that prompts the user to enter a string and displays all its
        permutations.
     */
    public static void ch18_25() {
        System.out.print("Enter a string: ");
        String input = scanner.next();
        displayPermutation(input);
    }

    public static void displayPermutation(String s) {
        displayPermutation("", s);
    }

    public static void displayPermutation(String s1, String s2) {
        if (s2.isEmpty()) {
            System.out.println(s1);
        }
        for (int i = 0; i < s2.length(); i++) {
            displayPermutation(s1 + s2.charAt(i), s2.substring(0, i) + s2.substring(i + 1));
        }
    }

    /*
        (Create a maze) Write a program that will find a path in a maze, as shown in
        Figure 18.13a. The maze is represented by a 8 * 8 board. The path must meet
        the following conditions:
        ■ The path is between the upper-left corner cell and the lower-right corner cell
        in the maze.
        ■ The program enables the user to place or remove a mark on a cell. A path
        consists of adjacent unmarked cells. Two cells are said to be adjacent if they
        are horizontal or vertical neighbors.
        ■ The path does not contain cells that form a square. The path in Figure 18.13b,
        for example, does not meet this condition. (The condition makes a path easy
        to identify on the board.)
     */
    public static void ch18_26() {
        Exercise18_26.run();
    }

    /*

     */
    public static void ch18_27() {
        Exercise18_27.run();
    }

    /*
        (Nonrecursive directory size) Rewrite Listing 18.7, DirectorySize.java, without
        using recursion.
     */
    public static void ch18_28() {
        ArrayList<File> files = new ArrayList<>();
        System.out.print("Enter a directory or a file: ");
        files.add(new File(scanner.nextLine()));
        long size = 0;

        while (!files.isEmpty()) {
            ArrayList<File> next = new ArrayList<>();
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file);
                    size += file.length();
                }
                if (file.isDirectory()) {
                    next.addAll(List.of(file.listFiles()));
                }
            }
            files = next;
        }
        System.out.printf("%d bytes\n", size);
    }


    /*
        (Number of files in a directory) Write a program that prompts the user to enter a
        directory and displays the number of the files in the directory.
     */
    public static void ch18_29() {
        System.out.print("Enter a directory: ");
        File dir = new File(scanner.nextLine());
        if (!dir.isDirectory()) {
            System.out.println("Invalid input!");
            return;
        }

        System.out.printf("Number of files in %s is %d\n", dir.getName(), countFiles(dir));
    }

    public static int countFiles(File dir) {
        int count = 0;
        File[] listFiles = dir.listFiles();
        if (listFiles == null) {
            return count;
        }
        for (File file : listFiles) {
            if (file.isFile()) {
                count++;
            } else if (file.isDirectory()) {
                count += countFiles(file);
            }
        }
        return count;
    }

    /*
        (Find words) Write a program that finds all occurrences of a word in all the files
        under a directory, recursively. Pass the parameters from the command line as
        follows:
        java Exercise18_30 dirName word
     */
    public static void ch18_30(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid input!");
        }
        String word = args[1];
        File dir = new File(args[0]);
        System.out.printf("The word '%s' occurs %d times\n", word, countWords(dir, word));
    }

    public static int countWords(File file, String word) {
        int count = 0;
        if (file.isFile()) {
            try (Scanner input = new Scanner(file)) {
                while (input.hasNext()) {
                    String nextWord = input.next().toLowerCase();
                    if (nextWord.equals(word.toLowerCase())) {
                        count++;
                    }
                }
            } catch (FileNotFoundException ex) {
                return 0;
            }
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return 0;
            }
            for (File f : listFiles) {
                count += countWords(f, word);
            }
        }
        return count;
    }

    /*
        (Replace words) Write a program that replaces all occurrences of a word with a
        new word in all the files under a directory, recursively. Pass the parameters from
        the command line as follows:
        java Exercise18_31 dirName oldWord newWord
     */
    public static void ch18_31(String[] args) {
        if (args.length != 3) {
            System.out.println("Invalid input!");
        }
        File file = new File(args[0]);
        String oldWord = args[1];
        String newWord = args[2];
        replaceWords(file, oldWord, newWord);
    }

    public static void replaceWords(File file, String oldWord, String newWord) {
        if (file.isFile()) {
            ArrayList<String> fileContent = new ArrayList<>();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    fileContent.add(scanner.nextLine().replace(oldWord, newWord));
                }
            } catch (FileNotFoundException ex) {
                return;
            }
            try (PrintWriter writer = new PrintWriter(file)) {
                fileContent.forEach(writer::println);
            } catch (FileNotFoundException ex) {
            }
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File f : listFiles) {
                replaceWords(f, oldWord, newWord);
            }
        }
    }

    /*
        (Game: Knight’s Tour) The Knight’s Tour is an ancient puzzle. The objective is
        to move a knight, starting from any square on a chessboard, to every other square
        once, as shown in Figure 18.15a. Note the knight makes only L-shaped moves
        (two spaces in one direction and one space in a perpendicular direction). As
        shown in Figure 18.15b, the knight can move to eight squares. Write a program
        that displays the moves for the knight, as shown in Figure 18.15c. When you
        click a cell, the knight is placed at the cell. This cell will be the starting point for
        the knight. Click the Solve button to display the path for a solution.
        (Hint: A brute-force approach for this problem is to move the knight from one
        square to another available square arbitrarily. Using such an approach, your
        program will take a long time to finish. A better approach is to employ some
        heuristics. A knight has two, three, four, six, or eight possible moves, depending
        on its location. Intuitively, you should attempt to move the knight to the least
        accessible squares first and leave those more accessible squares open, so there
        will be a better chance of success at the end of the search.)
     */
    public static void ch18_32() {
        Exercise18_32.run();
    }

    /*
        (Game: Knight’s Tour animation) Write a program for the Knight’s Tour problem.
        Your program should let the user move a knight to any starting square and click the
        Solve button to animate a knight moving along the path, as shown in Figure 18.16
     */
    public static void ch18_33() {
        Exercise18_33.run();
    }

    /*
        (Game: Eight Queens) The Eight Queens problem is to find a solution to place
        a queen in each row on a chessboard such that no two queens can attack each
        other. Write a program to solve the Eight Queens problem using recursion and
        display the result as shown in Figure 18.17.
     */
    public static void ch18_34() {
        Exercise18_34.run();
    }

    /*
        (H-tree fractal) An H-tree (introduced at the beginning of this chapter in Fig ure
        18.1) is a fractal defined as follows:
        1. Begin with a letter H. The three lines of H are of the same length, as shown
        in Figure 18.1a.
        2. The letter H (in its sans-serif form, H) has four endpoints. Draw an H centered
        at each of the four endpoints to an H-tree of order 1, as shown in Figure 18.1b.
        These Hs are half the size of the H that contains the four endpoints.
        3. Repeat Step 2 to create an H-tree of order 2, 3, . . . , and so on, as shown in
        Figures 18.1c and d.
        Write a program that draws an H-tree, as shown in Figure 18.1.
     */
    public static void ch18_35() {
        Exercise18_35.run();
    }

    /*
        (Sierpinski triangle) Write a program that lets the user to enter the order and
        display the filled Sierpinski triangles as shown in Figure 18.18.
     */
    public static void ch18_36() {
        Exercise18_36.run();
    }

    /*
        (Hilbert curve) The Hilbert curve, first described by German mathematician
        David Hilbert in 1891, is a space-filling curve that visits every point in a square
        grid with a size of 2 * 2, 4 * 4, 8 * 8, 16 * 16, or any other power of 2.
        Write a program that displays a Hilbert curve for the specified order, as shown
        in Figure 18.19.
     */
    public static void ch18_37() {
        Exercise18_37.run();
    }

    /*
        (Recursive tree) Write a program to display a recursive tree as shown in
        Figure 18.20.
     */
    public static void ch18_38() {
        Exercise18_38.run();
    }

    /*
        (Drag the tree) Revise Programming Exercise 18.38 to move the tree to where
        the mouse is dragged
     */
    public static void ch18_39() {
        Exercise18_39.run();
    }

}
