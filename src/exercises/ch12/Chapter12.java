package exercises.ch12;

import exercises.ch12.ex4.Loan;
import exercises.ch12.ex5.IllegalTriangleException;
import exercises.ch12.ex5.Triangle;
import exercises.ch12.ex8.HexFormatException;
import exercises.ch12.ex9.BinaryFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Chapter12 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (NumberFormatException) Listing 7.9, Calculator.java, is a simple
        command- line calculator. Note the program terminates if any operand is
        nonnumeric. Write a program with an exception handler that deals with non-
        numeric operands; then write another program without using an exception
        handler to achieve the same objective. Your program should display a mes-
        sage that informs the user of the wrong operand type before exiting (see
        Figure 12.12).
     */
    public static void ch12_1(String[] args) {
        exceptionCalculator(args);
        nonExceptionCalculator(args);
    }

    public static void exceptionCalculator(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator operand1 operator operand2");
            System.exit(1);
        }
        char operator = args[1].charAt(0);
        int operand1;
        int operand2;
        try {
            try {
                operand1 = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Wrong input: " + args[0]);
            }
            try {
                operand2 = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Wrong input: " + args[2]);
            }
            int result = switch (operator) {
                case '+' -> operand1 + operand2;
                case '-' -> operand1 - operand2;
                case '*' -> operand1 * operand2;
                case '/' -> operand1 / operand2;
                default -> throw new IllegalStateException("Wrong input: " + operator);
            };
            System.out.printf("%s %s %s = %d\n", args[0], operator, args[2], result);
        } catch (NumberFormatException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void nonExceptionCalculator(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator operand1 operator operand2");
            System.exit(1);
        }
        char operator = args[1].charAt(0);
        if (!isDigitString(args[0])) {
            System.out.println("Wrong input: " + args[0]);
            return;
        }
        if (!isDigitString(args[2])) {
            System.out.println("Wrong input: " + args[2]);
            return;
        }
        int operand1 = Integer.parseInt(args[0]);
        int operand2 = Integer.parseInt(args[2]);
        int result;
        switch (operator) {
            case '+' -> result = operand1 + operand2;
            case '-' -> result = operand1 - operand2;
            case '*' -> result = operand1 * operand2;
            case '/' -> result = operand1 / operand2;
            default -> {
                System.out.println("Wrong input: " + operator);
                return;
            }
        }
        ;
        System.out.printf("%s %s %s = %d\n", args[0], operator, args[2], result);
    }

    public static boolean isDigitString(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /*
        (InputMismatchException) Write a program that prompts the user to read
        two integers and displays their sum. Your program should prompt the user to
        read the number again if the input is incorrect.
     */
    public static void ch12_2() {
        while (true) {
            System.out.print("Enter two integers: ");
            try {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                System.out.println(a + b);
                break;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }

    }

    /*
        (ArrayIndexOutOfBoundsException) Write a program that meets the
        following requirements:
        ■ Creates an array with 100 randomly chosen integers.
        ■ Prompts the user to enter the index of the array, then displays the corre-
        sponding element value. If the specified index is out of bounds, display the
        message "Out of Bounds".
     */
    public static void ch12_3() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100 + 1);
        }
        System.out.print("Enter array index: ");
        int index = scanner.nextInt();
        try {
            System.out.println(array[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        }
    }

    /*
        (IllegalArgumentException) Modify the Loan class in Listing 10.2 to
        throw IllegalArgumentException if the loan amount, interest rate, or
        number of years is less than or equal to zero.
     */
    public static void ch12_4() {
        try {
            Loan loan = new Loan();
            loan.setAnnualInterestRate(5);
            loan.setNumberOfYears(3);
            loan.setLoanAmount(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /*
        (IllegalTriangleException) Programming Exercise 11.1 defined the
        Triangle class with three sides. In a triangle, the sum of any two sides is
        greater than the other side. The Triangle class must adhere to this rule.
        Create the IllegalTriangleException class, and modify the constructor
        of the Triangle class to throw an IllegalTriangleException object if a
        triangle is created with sides that violate the rule, as follows:
        /** Construct a triangle with the specified sides /
            public Triangle(double side1, double side2, double side3)
                    throws IllegalTriangleException {
        // Implement it
    }
     */
    public static void ch12_5() {
        try {
            Triangle t1 = new Triangle(3, 3, 5);
            Triangle t2 = new Triangle(1, 3, 5);
        } catch (IllegalTriangleException e) {
            System.out.println(e.getMessage());
        }

    }

    /*
        (NumberFormatException) Listing 6.8 implements the hex2Dec(String
        hexString) method, which converts a hex string into a decimal number.
        Implement the hex2Dec method to throw a NumberFormatException if the
        string is not a hex string. Write a test program that prompts the user to enter
        a hex number as a string and displays its decimal equivalent. If the method
        throws an exception, display “Not a hex number”.
     */
    public static void ch12_6() {
        System.out.print("Enter a hex number: ");
        String hex = scanner.next();
        try {
            System.out.println(hexToDecimal(hex));
        } catch (NumberFormatException e) {
            System.out.println("Not a hex number");
        }
    }

    public static int hexToDecimal(String hex) {
        int result = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            result *= 16;
            if (c >= 'A' && c <= 'F') {
                result += 10 + c - 'A';
            } else if (c >= '0' && c <= '9') {
                result += c - '0';
            } else {
                throw new NumberFormatException();
            }
        }
        return result;
    }


    /*
        (NumberFormatException) Write the bin2Dec(String binaryString)
        method to convert a binary string into a decimal number. Implement the bin-
        2Dec method to throw a NumberFormatException if the string is not a
        binary string. Write a test program that prompts the user to enter a binary
        number as a string and displays its decimal equivalent. If the method throws
        an exception, display “Not a binary number”.
     */
    public static void ch12_7() {
        System.out.print("Enter a binary number: ");
        String bin = scanner.next();
        try {
            System.out.println(bin2Dec(bin));
        } catch (NumberFormatException e) {
            System.out.println("Not a binary number");
        }
    }

    public static int bin2Dec(String binaryString) {
        int result = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if (c != '1' && c != '0') {
                throw new NumberFormatException();
            }
            result *= 2;
            result += c == '1' ? 1 : 0;
        }
        return result;
    }

    /*
        (HexFormatException) Programming Exercise 12.6 implements the
        hex2Dec method to throw a NumberFormatException if the string is
        not a hex string. Define a custom exception called HexFormatException.
        Implement the hex2Dec method to throw a HexFormatException if the
        string is not a hex string.
     */
    public static void ch12_8() {
        System.out.print("Enter a hex number: ");
        String hex = scanner.next();
        try {
            System.out.println(hexToDecimalEx(hex));
        } catch (HexFormatException e) {
            System.out.println("Not a hex number");
        }
    }

    public static int hexToDecimalEx(String hex) throws HexFormatException {
        int result = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            result *= 16;
            if (c >= 'A' && c <= 'F') {
                result += 10 + c - 'A';
            } else if (c >= '0' && c <= '9') {
                result += c - '0';
            } else {
                throw new HexFormatException();
            }
        }
        return result;
    }

    /*
        (BinaryFormatException) Exercise 12.7 implements the bin2Dec method
        to throw a BinaryFormatException if the string is not a binary string. Define
        a custom exception called BinaryFormatException. Implement the bin2Dec
        method to throw a BinaryFormatException if the string is not a binary string.
     */
    public static void ch12_9() {
        System.out.print("Enter a binary number: ");
        String bin = scanner.next();
        try {
            System.out.println(bin2DecEx(bin));
        } catch (BinaryFormatException e) {
            System.out.println("Not a binary number");
        }
    }

    public static int bin2DecEx(String binaryString) {
        int result = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char c = binaryString.charAt(i);
            if (c != '1' && c != '0') {
                throw new BinaryFormatException();
            }
            result *= 2;
            result += c == '1' ? 1 : 0;
        }
        return result;
    }

    /*
        (OutOfMemoryError) Write a program that causes the JVM to throw an
        OutOfMemoryError and catches and handles this error.
     */
    public static void ch12_10() {
        try {
            ArrayList<Loan> list = new ArrayList<>();
            while (true) {
                list.add(new Loan());
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory!");
        }
    }

    /*
        (Remove text) Write a program that removes all the occurrences of a specified
        string from a text file. For example, invoking
        java Exercise12_11 John filename
        removes the string John from the specified file. Your program should get the
        arguments from the command line.

        OutOfMemoryError ./src/exercises/ch12/ex11/test.txt
     */
    public static void ch12_11(String[] args) {
        String s = args[0];
        String path = args[1];
        File file = new File(path);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().replace(s, "");
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Reformat Java source code) Write a program that converts the Java source
        code from the next-line brace style to the end-of-line brace style. For example,
        the following Java source in (a) uses the next-line brace style. Your program
        converts it to the end-of-line brace style in (b).

        public class Test
        {
            public static void main(String[] args)
            {
                // Some statements
            }
        }
        (a) Next-line brace style

        public class Test {
            public static void main(String[] args) {
                // Some statements
            }
        }
        (b) End-of-line brace style

        Your program can be invoked from the command line with the Java source-code
        file as the argument. It converts the Java source code to a new format. For exam-
        ple, the following command converts the Java source-code file Test.java to
        the end-of-line brace style.
        ./src/exercises/ch12/ex12/Test.java
     */
    public static void ch12_12(String[] args) {
        String path = args[0];
        File file = new File(path);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.strip().equals("{")) {
                    var lastIndex = lines.size() - 1;
                    lines.set(lastIndex, lines.get(lastIndex) + " {");
                } else {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Count characters, words, and lines in a file) Write a program that will count
        the number of characters, words, and lines in a file. Words are separated by
        whitespace characters. The file name should be passed as a command-line
        argument, as shown in Figure 12.13.

        ./src/exercises/ch12/ex13/test.txt
     */
    public static void ch12_13(String[] args) {
        if (args.length != 1) {
            System.out.println("Pass file name as a command-line argument ");
            return;
        }
        File file = new File(args[0]);
        int characters = 0;
        int words = 0;
        int lines = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine().strip();
                lines++;
                words += line.split(" ").length;
                for (char c : line.toCharArray()) {
                    if (!Character.isWhitespace(c)) {
                        characters++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("File %s has\n", file.getName());
        System.out.printf("%d characters\n", characters);
        System.out.printf("%d words\n", words);
        System.out.printf("%d lines\n", lines);
    }

    /*
        (Process scores in a text file) Suppose a text file contains an unspecified
        number of scores separated by spaces. Write a program that prompts the user to
        enter the file, reads the scores from the file, and displays their total and average.

        ./src/exercises/ch12/ex14/scores.txt
     */
    public static void ch12_14() {
        System.out.print("Enter a path to scores.txt file: ");
        String path = scanner.next();
        File file = new File(path);

        try (Scanner scanner = new Scanner(file).useLocale(Locale.US)) {
            ArrayList<Double> scores = new ArrayList<>();
            while (scanner.hasNext()) {
                scores.add(scanner.nextDouble());
            }
            double total = scores.stream().reduce(0.0, Double::sum);
            double average = total / scores.size();
            System.out.printf("Total score: %.2f\n", total);
            System.out.printf("Average score: %.2f\n", average);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
        (Write/read data) Write a program to create a file named Exercise12_15.txt if
        it does not exist. Write 100 integers created randomly into the file using text
        I/O. Integers are separated by spaces in the file. Read the data back from the
        file and display the data in increasing order.
     */
    public static void ch12_15() {
        final int N = 100;
        File file = new File("./src/exercises/ch12/ex15/integers.txt");
        try {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (int i = 0; i < N; i++) {
                    int number = (int) (Math.random() * 100 + 1);
                    writer.print(number + " ");
                }
            }
            try (Scanner scanner = new Scanner(file)) {
                ArrayList<Integer> numbers = new ArrayList<>();
                while (scanner.hasNext()) {
                    numbers.add(scanner.nextInt());
                }
                Collections.sort(numbers);
                numbers.forEach(x -> System.out.print(x + " "));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /*

     */
    public static void ch12_16() {

    }

    /*

     */
    public static void ch12_17() {

    }

    /*

     */
    public static void ch12_18() {

    }

    /*

     */
    public static void ch12_19() {

    }

    /*

     */
    public static void ch12_20() {

    }

    /*

     */
    public static void ch12_21() {

    }

    /*

     */
    public static void ch12_22() {

    }

    /*

     */
    public static void ch12_23() {

    }

    /*

     */
    public static void ch12_24() {

    }

    /*

     */
    public static void ch12_25() {

    }

    /*

     */
    public static void ch12_26() {

    }

    /*

     */
    public static void ch12_27() {

    }

    /*

     */
    public static void ch12_28() {

    }

    /*

     */
    public static void ch12_29() {

    }

    /*

     */
    public static void ch12_30() {

    }

    /*

     */
    public static void ch12_31() {

    }

    /*

     */
    public static void ch12_32() {

    }

    /*

     */
    public static void ch12_33() {

    }
}
