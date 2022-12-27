package exercises.ch5;

import exercises.utils.Calendar;

import java.util.Locale;
import java.util.Scanner;

public class Chapter5 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Count positive and negative numbers and compute the average of numbers)
        Write a program that reads an unspecified number of integers, determines how
        many positive and negative values have been read, and computes the total and av-
        erage of the input values (not counting zeros). Your program ends with the input
        0. Display the average as a floating-point number. Here are sample runs:
            Enter an integer, the input ends if it is 0: 1 2 –1 3 0
            The number of positives is 3
            The number of negatives is 1
            The total is 5.0
            The average is 1.25
            Enter an integer, the input ends if it is 0: 0
            No numbers are entered except 0
     */
    public static void ch5_1() {
        int numberOfPositives = 0;
        int numberOfNegatives = 0;
        double total = 0;

        System.out.print("Enter an integer, the input ends if it is 0: ");
        int number = scanner.nextInt();
        while (number != 0) {
            if (number > 0) {
                numberOfPositives++;
            } else {
                numberOfNegatives++;
            }
            total += number;
            number = scanner.nextInt();
        }
        if (total == 0) {
            System.out.println("No numbers are entered except 0");
            return;
        }
        System.out.printf("The number of positives is %d\n", numberOfPositives);
        System.out.printf("The number of negatives is %d\n", numberOfNegatives);
        System.out.printf("The total is %.2f\n", total);
        System.out.printf("The average is %.2f\n", total / (numberOfPositives + numberOfNegatives));
    }

    /*
        (Repeat additions) Listing 5.4, SubtractionQuizLoop.java, generates five
        random subtraction questions. Revise the program to generate 10 random addi-
        tion questions for two integers between 1 and 15. Display the correct count and
        test time.
     */
    public static void ch5_2() {
        final int NUMBER_OF_QUESTIONS = 10;
        int correctCount = 0;
        int count = 0;
        long startTime = System.currentTimeMillis();
        String output = "";

        while (count < NUMBER_OF_QUESTIONS) {
            int number1 = (int) (Math.random() * 15 + 1);
            int number2 = (int) (Math.random() * 15 + 1);

            System.out.printf("What is %d + %d? ", number1, number2);
            int answer = scanner.nextInt();

            if (number1 + number2 == answer) {
                System.out.println("You are correct!");
                correctCount++;
            } else {
                System.out.printf("Your answer is wrong.\n%d + %d should be %d\n", number1, number2, number1 + number2);
            }

            count++;
            output += String.format("%d+%d=%d %s\n", number1, number2, answer,
                    (number1 + number2 == answer) ? " correct" : " wrong");
        }

        long endTime = System.currentTimeMillis();
        long testTime = endTime - startTime;
        System.out.printf("\nCorrect count is %d\nTest time is %d seconds\n\n%s", correctCount, testTime / 1000, output);
    }


    /*
        (Conversion from kilograms to pounds) Write a program that displays the follow-
        ing table (note 1 kilogram is 2.2 pounds):
        Kilograms   Pounds
        1           2.2
        3           6.6
        ...
        197         433.4
        199         437.8
     */
    public static void ch5_3() {
        final double POUNDS_PER_KG = 2.2;
        System.out.println("Kilograms\tPounds");
        int i = 1;
        while (i < 200) {
            System.out.printf("%-9d\t%-6.1f\n", i, i * POUNDS_PER_KG);
            i += 2;
        }
    }

    /*
        (Conversion from miles to kilometers) Write a program that displays the follow-
        ing table (note 1 mile is 1.609 kilometers):
        Miles    Kilometers
        1        1.609
        2        3.218
        . . .
        9        14.481
        10       16.090
     */
    public static void ch5_4() {
        final double MILES_PER_KM = 1.609;
        System.out.println("Miles\tKilometers");
        int i = 1;
        while (i <= 10) {
            System.out.printf("%-5d\t%-10.3f\n", i, i * MILES_PER_KM);
            i++;
        }
    }

    /*
        (Conversion from kilograms to pounds and pounds to kilograms) Write a pro-
        gram that displays the following two tables side by side:
        Kilograms   Pounds  |   Pounds  Kilograms
        1           2.2     |   20     9.09
        3           6.6     |   25     11.36
        . . .
        197         433.4   |   510    231.82
        199         437.8   |   515    234.09
     */
    public static void ch5_5() {
        final double POUNDS_PER_KG = 2.2;
        System.out.println("Kilograms\tPounds\t|\tPounds\tKilograms");
        int i = 1;
        int j = 20;
        while (i < 200) {
            System.out.printf("%-9d\t%-6.1f\t|\t%-6d\t%-9.2f\n", i, i * POUNDS_PER_KG, j, j / POUNDS_PER_KG);
            i += 2;
            j += 5;
        }
    }

    /*
        (Conversion from miles to kilometers) Write a program that displays the follow-
        ing two tables side by side:
        Miles   Kilometers  |   Kilometers  Miles
        1       1.609       |   20          12.430
        2       3.218       |   25          15.538
        . . .
        9       14.481      |   60          37.290
        10      16.090      |   65          40.398
     */
    public static void ch5_6() {
        final double MILES_PER_KM = 1.609;
        System.out.println("Miles\tKilometers\t|\tKilometers\tMiles");
        int i = 1;
        int j = 20;
        while (i <= 10) {
            System.out.printf("%-5d\t%-10.3f\t|\t%-9d\t%-8.3f\n", i, i * MILES_PER_KM, j, j / MILES_PER_KM);
            i++;
            j += 5;
        }
    }

    /*
        (Financial application: compute future tuition) Suppose the tuition for a univer-
        sity is $10,000 this year and increases 5% every year. In one year, the tuition will
        be $10,500. Write a program that displays the tuition in 10 years, and the total
        cost of four years’ worth of tuition starting after the tenth year.
     */
    public static void ch5_7() {
        final int INCREASE_RATE_PERCENT = 5;
        double tuition = 10_000;
        for (int i = 1; i <= 10; i++) {
            tuition = tuition + tuition * (INCREASE_RATE_PERCENT / 100.0);
        }
        System.out.printf("Tuition cost in ten years will be: $%.2f\n", tuition);
        double total = 0;
        for (int i = 1; i <= 4; i++) {
            tuition = tuition + tuition * (INCREASE_RATE_PERCENT / 100.0);
            total += tuition;
        }
        System.out.printf("Total cost of four years after the tenth year: $%.2f", total);
    }

    /*
        (Find the highest score) Write a program that prompts the user to enter the num-
        ber of students and each student’s name and score, and finally displays the name
        of the student with the highest score. Use the next() method in the Scanner
        class to read a name, rather than using the nextLine() method. Assume that the
        number of students is at least 1
     */
    public static void ch5_8() {
        System.out.print("Enter the number of students: ");
        int students = scanner.nextInt();
        String bestStudent = "";
        int bestScore = -1;
        for (int i = 0; i < students; i++) {
            System.out.print("Enter student’s name and score: ");
            String name = scanner.next();
            int score = scanner.nextInt();
            if (score > bestScore) {
                bestScore = score;
                bestStudent = name;
            }
        }
        System.out.printf("Student with the highest score is %s\n", bestStudent);
    }

    /*
        (Find the two highest scores) Write a program that prompts the user to enter the
        number of students and each student’s name and score, and finally displays the
        student with the highest score and the student with the second-highest score. Use
        the next() method in the Scanner class to read a name rather than using the
        nextLine() method. Assume that the number of students is at least 2.
     */
    public static void ch5_9() {
        System.out.print("Enter the number of students: ");
        int students = scanner.nextInt();
        String bestStudent = "";
        String secondBestStudent = "";
        int bestScore = -1;
        int secondBestScore = -1;
        for (int i = 0; i < students; i++) {
            System.out.print("Enter student’s name and score: ");
            String name = scanner.next();
            int score = scanner.nextInt();
            if (score > bestScore) {
                bestScore = score;
                bestStudent = name;
            } else if (score > secondBestScore) {
                secondBestScore = score;
                secondBestStudent = name;
            }
        }
        System.out.printf("Student with the highest score is %s\n", bestStudent);
        System.out.printf("Student with the second best score is %s\n", secondBestStudent);
    }

    /*
        (Find numbers divisible by 5 and 6) Write a program that displays all the num-
        bers from 100 to 1,000 (10 per line) that are divisible by 5 and 6. Numbers are
        separated by exactly one space.
     */
    public static void ch5_10() {
        final int START = 100;
        final int STOP = 1000;
        final int NUMBERS_PER_LINE = 10;
        int numbersOnTheLine = 0;
        for (int i = START; i <= STOP; i++) {
            if (i % 30 != 0) continue;
            System.out.printf("%d ", i);
            numbersOnTheLine++;
            if (numbersOnTheLine == NUMBERS_PER_LINE) {
                numbersOnTheLine = 0;
                System.out.println();
            }
        }
    }

    /*
        (Find numbers divisible by 5 or 6, but not both) Write a program that displays
        all the numbers from 100 to 200 (10 per line) that are divisible by 5 or 6, but not
        both. Numbers are separated by exactly one space.
     */
    public static void ch5_11() {
        final int START = 100;
        final int STOP = 200;
        final int NUMBERS_PER_LINE = 10;
        int numbersOnTheLine = 0;
        for (int i = START; i <= STOP; i++) {
            if ((i % 5 == 0 || i % 6 == 0) && i % 30 != 0) {
                System.out.printf("%d ", i);
                numbersOnTheLine++;
                if (numbersOnTheLine == NUMBERS_PER_LINE) {
                    numbersOnTheLine = 0;
                    System.out.println();
                }
            }
        }
    }

    /*
        (Find the smallest n such that n2 > 12,000) Use a while loop to find the small-
        est integer n such that n2 is greater than 12,000.
     */
    public static void ch5_12() {
        final int MAX = 12_000;
        int n = 0;
        while (Math.pow(n, 2) < MAX) {
            n++;
        }
        System.out.printf("%d^2 = %.0f", n, Math.pow(n, 2));
    }

    /*
        (Find the largest n such that n3 < 12,000) Use a while loop to find the largest
        integer n such that n3 is less than 12,000.
     */
    public static void ch5_13() {
        final int MAX = 12_000;
        int n = 0;
        while (Math.pow(n, 3) < MAX) {
            n++;
        }
        n--;
        System.out.printf("%d^3 = %.0f", n, Math.pow(n, 3));
    }

    /*
        (Compute the greatest common divisor) Another solution for Listing 5.9 to find
        the greatest common divisor of two integers n1 and n2 is as follows: First find d
        to be the minimum of n1 and n2, then check whether d, d–1, d–2, …, 2, or 1 is
        a divisor for both n1 and n2 in this order. The first such common divisor is the
        greatest common divisor for n1 and n2. Write a program that prompts the user to
        enter two positive integers and displays the gcd.
     */
    public static void ch5_14() {
        System.out.print("Enter two positive integers: ");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int d = Math.min(n1, n2);

        while (d > 0) {
            if (n1 % d == 0 && n2 % d == 0) break;
            d--;
        }
        System.out.printf("Greatest common divisor of %d and %d is %d\n", n1, n2, d);
    }

    /*
        (Display the ASCII character table) Write a program that prints the characters in
        the ASCII character table from ! to ~. Display 10 characters per line. The ASCII
        table is given in Appendix B. Characters are separated by exactly one space.
     */
    public static void ch5_15() {
        final char START = '!';
        final char STOP = '~';
        final int NUMBERS_PER_LINE = 10;
        int counter = 0;
        for (char i = START; i <= STOP; i++) {
            System.out.printf("%c ", i);
            counter++;
            if (counter == NUMBERS_PER_LINE) {
                counter = 0;
                System.out.println();
            }
        }
    }

    /*
        (Find the factors of an integer) Write a program that reads an integer and dis-
        plays all its smallest factors in an increasing order. For example, if the input
        integer is 120, the output should be as follows: 2, 2, 2, 3, 5.
     */
    public static void ch5_16() {
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    System.out.printf("%d ", i);
                    n /= i;
                    break;
                }
            }
        }
    }

    /*
        (Display pyramid) Write a program that prompts the user to enter an integer from
        1 to 15 and displays a pyramid, as presented in the following sample run:
        Enter the number of lines: 7
                    1
                  2 1 2
                3 2 1 2 3
              4 3 2 1 2 3 4
            5 4 3 2 1 2 3 4 5
          6 5 4 3 2 1 2 3 4 5 6
        7 6 5 4 3 2 1 2 3 4 5 6 7
     */
    public static void ch5_17() {
        System.out.print("Enter the number of lines: ");
        int n = scanner.nextInt();
        if (n < 1 || n > 15) {
            System.out.println("Invalid input: enter an integer from 1 to 15");
            return;
        }
        for (int i = 1; i <= n; i++) {
            int j = n;
            while (j > 0) {
                System.out.print(j > i ? "    " : String.format("%4d", j));
                j--;
            }
            j += 2;
            while (j <= n) {
                System.out.print(j > i ? "    " : String.format("%4d", j));
                j++;
            }
            System.out.println();
        }
    }

    /*
        (Display four patterns using loops) Use nested loops that display the following
        patterns in four separate programs:
        Pattern A       Pattern B       Pattern C       Pattern D
        1               1 2 3 4 5 6             1       1 2 3 4 5 6
        1 2             1 2 3 4 5             2 1         1 2 3 4 5
        1 2 3           1 2 3 4             3 2 1           1 2 3 4
        1 2 3 4         1 2 3             4 3 2 1             1 2 3
        1 2 3 4 5       1 2             5 4 3 2 1               1 2
        1 2 3 4 5 6     1             6 5 4 3 2 1                 1
     */
    public static void ch5_18() {
        final int MIN = 1;
        final int MAX = 6;

        System.out.println("Pattern A");
        for (int i = MIN; i <= MAX; i++) {
            for (int j = MIN; j <= MAX; j++) {
                System.out.print(j > i ? "  " : j + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Pattern B");
        for (int i = MAX; i >= MIN; i--) {
            for (int j = MIN; j <= MAX; j++) {
                System.out.print(j > i ? "  " : j + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Pattern C");
        for (int i = MIN; i <= MAX; i++) {
            for (int j = MAX; j >= MIN; j--) {
                System.out.print(j > i ? "  " : j + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Pattern B");
        for (int i = MAX; i >= MIN; i--) {
            for (int k = i; k < MAX; k++) {
                System.out.print("  ");
            }
            for (int j = MIN; j <= MAX; j++) {
                if (j <= i) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /*
        (Display numbers in a pyramid pattern) Write a nested for loop that prints the
        following output:
                                    1
                                1   2   1
                            1   2   4   2   1
                        1   2   4   8   4   2   1
                    1   2   4   8  16   8   2   1
                1   2   4   8  16  32  16   8   4   2   1
            1   2   4   8  16  32  64  32  16   8   4   2   1
        1   2   4   8  16  32  64 128  64  32  16   8   4   2   1
     */
    public static void ch5_19() {
        final int LINES = 8;
        for (int i = 0; i < LINES; i++) {
            int current = 1;
            for (int j = 1; j < LINES * 2; j++) {
                if (j < LINES - i || j > LINES + i) {
                    System.out.print("\t");
                } else {
                    System.out.printf("%4d", current);
                    current = j < LINES ? current * 2 : current / 2;
                }
            }
            System.out.println();
        }
    }

    /*
        (Display prime numbers between 2 and 1,000) Modify the program given in Listing
        5.15 to display all the prime numbers between 2 and 1,000, inclusive. Display eight
        prime numbers per line. Numbers are separated by exactly one space.
     */
    public static void ch5_20() {
        final int START = 2;
        final int END = 1000;
        final int NUMBER_OF_PRIMES_PER_LINE = 8;
        int number = START;
        int count = 0;
        System.out.printf("Prime numbers between %d and %d\n\n", START, END);

        while (number <= END) {
            boolean isPrime = true;
            for (int divisor = 2; divisor <= number / 2; divisor++) {
                if (number % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                System.out.printf("%-4d", number);
                System.out.print(count % NUMBER_OF_PRIMES_PER_LINE == 0 ? "\n" : " ");
            }
            number++;
        }
    }

    /*
        (Financial application: compare loans with various interest rates) Write a pro-
        gram that lets the user enter the loan amount and loan period in number of years,
        and displays the monthly and total payments for each interest rate starting from
        5% to 8%, with an increment of 1/8. Here is a sample run:
            Loan Amount: 10000
            Number of Years: 5
            Interest Rate   Monthly Payment     Total Payment
            5.000%          188.71              11322.74
            5.125%          189.29              11357.13
            5.250%          189.86              11391.59
            ...
            7.875%          202.17              12129.97
            8.000%          202.76              12165.84
     */
    public static void ch5_21() {
        final double INCREMENT = 0.125;
        final double INTEREST_MIN = 5;
        final double INTEREST_MAX = 8;
        System.out.print("Loan Amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Number of Years: ");
        int years = scanner.nextInt();

        System.out.println("Interest Rate    Monthly Payment    Total Payment");
        for (double interest = INTEREST_MIN; interest <= INTEREST_MAX; interest += INCREMENT) {
            double months = years * 12;
            double r = interest / 100 / 12;
            double n = years * 12;
            double monthlyPayment = amount * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            double totalPayment = monthlyPayment * months;

            System.out.printf("%.3f%%    %13.2f    %17.2f\n", interest, monthlyPayment, totalPayment);
        }
    }


    /*
        (Financial application: loan amortization schedule) The monthly payment for a given
        loan pays the principal and the interest. The monthly interest is computed by multiply-
        ing the monthly interest rate and the balance (the remaining principal). The principal
        paid for the month is therefore the monthly payment minus the monthly interest. Write
        a program that lets the user enter the loan amount, number of years, and interest rate
        then displays the amortization schedule for the loan. Here is a sample run:
            Loan Amount: 10000
            Number of Years: 1
            Annual Interest Rate: 7

            Monthly Payment: 865.26
            Total Payment: 10383.21

            Payment#       Interest      Principal     Balance
            1                 58.33         806.93      9193.07
            2                 53.62         811.64      8381.43
            ...
            11                10.00         855.26       860.27
            12                 5.01         860.25         0.01
     */
    public static void ch5_22() {
        System.out.print("Loan Amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Number of Years: ");
        int years = scanner.nextInt();
        System.out.print("Annual Interest Rate: ");
        double annualInterest = scanner.nextDouble();
        System.out.println();

        double months = years * 12;
        double r = annualInterest / 100 / 12;
        double n = years * 12;
        double monthlyPayment = amount * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
        double totalPayment = monthlyPayment * months;

        System.out.printf("Monthly Payment: %.2f\n", monthlyPayment);
        System.out.printf("Total Payment: %.2f\n\n", totalPayment);

        System.out.printf("%-15s%8s%15s%15s\n", "Payment#", "Interest", "Principal", "Balance");
        double balance = amount;
        for (int i = 1; i <= years * 12; i++) {
            double interest = r * balance;
            double principal = monthlyPayment - interest;
            balance -= principal;
            System.out.printf("%-15d%8.2f%15.2f%15.2f\n", i, interest, principal, balance);
        }
    }

    /*
        (Demonstrate cancellation errors) A cancellation error occurs when you are
        manipulating a very large number with a very small number. The large number
        may cancel out the smaller number. For example, the result of 100000000.0
        + 0.000000001 is equal to 100000000.0. To avoid cancellation errors and
        obtain more accurate results, carefully select the order of computation. For ex-
        ample, in computing the following summation, you will obtain more accurate
        results by computing from right to left rather than from left to right:
        1 + 1/2 + 1/3 + ... + 1/n
        Write a program that compares the results of the summation of the preceding
        series, computing from left to right and from right to left with n = 50000.
     */
    public static void ch5_23() {
        final int N = 50000;
        double rightToLeft = 0;
        double leftToRight = 0;
        for (int i = 1; i <= N; i++) {
            rightToLeft += 1.0 / i;
        }
        for (int i = N; i > 0; i--) {
            leftToRight += 1.0 / i;
        }
        System.out.printf("Right to left: %.20f\n", rightToLeft);
        System.out.printf("Left to right: %.20f\n", leftToRight);
    }

    /*
        (Sum a series) Write a program to compute the following summation:
        1/3 + 3/5 + 5/7 + 7/9 + 9/11 + 11/13 + ... + 95/97 + 97/99
     */
    public static void ch5_24() {
        double sum = 0;
        for (int i = 1, j = 3; i <= 97; i += 2, j += 2) {
            sum += (double) i / j;
        }
        System.out.println(sum);
    }

    /*
        (Compute p) You can approximate p by using the following summation:
        p = 4*(1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 + ... + (-1)^i/(2*i - 1)
        Write a program that displays the p value for i = 10000, 20000, …, and 100000.
     */
    public static void ch5_25() {
        System.out.printf("computePi(%d) = %.10f\n", 10000, computePi(10000));
        System.out.printf("computePi(%d) = %.10f\n", 20000, computePi(20000));
        System.out.printf("computePi(%d) = %.10f\n", 100000, computePi(100000));
    }

    private static double computePi(int n) {
        double pi = 0;
        for (int i = 1; i <= n; i++) {
            pi += Math.pow(-1, i) / (2 * i - 1);
        }
        return Math.abs(pi * 4);
    }

    /*
        (Compute e) You can approximate e using the following summation:
        e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ... + 1/i!
        Write a program that displays the e value for i = 1, 2, …, and 20. Format
        the number to display 16 digits after the decimal point. (Hint: Because
        i! = i * (i - 1) * ... * 2 * 1, then
        1/i! is 1/i(i - 1)!
        Initialize e and item to be 1, and keep adding a new item to e. The new item is
        the previous item divided by i, for i >= 2.)
     */
    public static void ch5_26() {
        for (int i = 1; i <= 20; i++) {
            System.out.printf("computeEulers(%d) = %.20f\n", i, computeEulers(i));
        }
    }

    private static double computeEulers(int n) {
        double e = 1;
        double item = 1;
        for (int i = 2; i <= n; i++) {
            e += item;
            item /= i;
        }
        return e;
    }

    /*
        (Display leap years) Write a program that displays all the leap years, 10 per line,
        from 101 to 2100, separated by exactly one space. Also display the number of
        leap years in this period.
     */
    public static void ch5_27() {
        final int START = 101;
        final int END = 2100;
        final int NUMBERS_PER_LINE = 10;

        int perLineCounter = 0;
        int totalCounter = 0;
        for (int i = START; i <= END; i++) {
            if (!Calendar.isLeapYear(i)) continue;
            perLineCounter++;
            totalCounter++;
            System.out.printf(perLineCounter % NUMBERS_PER_LINE == 0 ? "%d\n" : "%d ", i);
        }
        System.out.printf("\n\nTotal number of leap years between %d and %d is %d\n", START, END, totalCounter);
    }

    /*
        (Display the first days of each month) Write a program that prompts the user to
        enter the year and first day of the year, then displays the first day of each month
        in the year. For example, if the user entered the year 2013, and 2 for Tuesday,
        January 1, 2013, your program should display the following output:
            January 1, 2013 is Tuesday
            ...
            December 1, 2013 is Sunday
     */
    public static void ch5_28() {
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        System.out.print("Enter a first day of this year: ");
        int firstDay = scanner.nextInt();
        for (int i = 1; i <= 12; i++) {
            int day = firstDay;
            for (int j = 1; j < i; j++) {
                day += Calendar.getNumberOfDays(j, year);
            }
            System.out.printf("%s 1, %d is %s\n", Calendar.getMonthName(i), year, Calendar.getWeekdayOf(day % 7));
        }
    }


    /*
        (Display calendars) Write a program that prompts the user to enter the year and
        first day of the year and displays the calendar table for the year on the console. For
        example, if the user entered the year 2013, and 2 for Tuesday, January 1, 2013,
        your program should display the calendar for each month in the year, as follows:
                   January 2013
            Sun Mon Tue Wed Thu Fri Sat
                      1   2   3   4   5
              6   7   8   9  10  11  12
             13  14  15  16  17  18  19
             20  21  22  23  24  25  26
             27  28  29  30  31
            . . .
                  December 2013
            Sun Mon Tue Wed Thu Fri Sat
              1   2   3   4   5   6   7
              8   9  10  11  12  13  14
             15  16  17  18  19  20  21
             22  23  24  25  26  27  28
             29  30  31
     */
    public static void ch5_29() {
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        System.out.print("Enter a first day of this year: ");
        int weekDay = scanner.nextInt();
        System.out.println();
        for (int month = 1; month <= 12; month++) {
            System.out.printf("%14s %d\n", Calendar.getMonthName(month), year);
            System.out.println("─".repeat(28));
            System.out.printf("%4s%4s%4s%4s%4s%4s%4s\n", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
            int day = 1;
            int lastDay = Calendar.getNumberOfDays(month, year);
            for (int i = 0; i < 42; i++) {
                int weekdaySpace = i % 7;
                if (weekdaySpace < weekDay || day > lastDay) {
                    System.out.print(weekdaySpace == 6 ? "\t\n" : "\t");
                    continue;
                }
                System.out.printf(weekDay == 6 ? "%3d\n" : "%3d ", day);
                day++;
                weekDay = (weekDay + 1) % 7;
            }
        }
    }

    /*
        (Financial application: compound value) Suppose you save $100 each month
        into a savings account with the annual interest rate 5%. Thus, the monthly in-
        terest rate is 0.05 / 12 = 0.00417. After the first month, the value in the
        account becomes
        100 * (1 + 0.00417) = 100.417
        After the second month, the value in the account becomes
        (100 + 100.417) * (1 + 0.00417) = 201.252
        After the third month, the value in the account becomes
        (100 + 201.252) * (1 + 0.00417) = 302.507
        and so on.
        Write a program that prompts the user to enter an amount (e.g., 100), the an-
        nual interest rate (e.g., 5), and the number of months (e.g., 6) then displays the
        amount in the savings account after the given month
     */
    public static void ch5_30() {
        System.out.print("Enter deposit amount: ");
        double monthlyDeposit = scanner.nextDouble();
        System.out.print("Annual interest rate: ");
        double annualInterest = scanner.nextDouble();
        System.out.print("Number of months: ");
        int months = scanner.nextInt();
        System.out.println();

        double monthlyInterestRate = annualInterest / 100 / 12;
        double deposit = 0;
        for (int i = 0; i < months; i++) {
            deposit = (monthlyDeposit + deposit) * (1 + monthlyInterestRate);
        }
        System.out.printf("Deposit after %d months will be: $%.2f\n", months, deposit);
    }

    /*
        (Financial application: compute CD value) Suppose you put $10,000 into a CD
        with an annual percentage yield of 5.75%. After one month, the CD is worth
        10000 + 10000 * 5.75 / 1200 = 10047.92
        After two months, the CD is worth
        10047.91 + 10047.91 * 5.75 / 1200 = 10096.06
        After three months, the CD is worth
        10096.06 + 10096.06 * 5.75 / 1200 = 10144.44
        and so on.
        Write a program that prompts the user to enter an amount (e.g., 10000), the
        annual percentage yield (e.g., 5.75), and the number of months (e.g., 18) and
        displays a table as presented in the sample run.
            Enter the initial deposit amount: 10000
            Enter annual percentage yield: 5.75
            Enter maturity period (number of months): 18
            Month     CD Value
            1         10047.92
            2         10096.06
            ...
            17        10846.57
            18        10898.54
     */
    public static void ch5_31() {
        System.out.print("Enter the initial deposit amount: ");
        double deposit = scanner.nextDouble();
        System.out.print("Enter annual percentage yield: ");
        double annualInterest = scanner.nextDouble();
        System.out.print("Enter maturity period (number of months): ");
        int months = scanner.nextInt();
        double monthlyInterestRate = annualInterest / 100 / 12;
        System.out.printf("%-10s %10s\n", "Month", "CD Value");
        for (int month = 1; month <= months; month++) {
            deposit = deposit + deposit * monthlyInterestRate;
            System.out.printf("%-10d %10.2f\n", month, deposit);
        }
    }

    /*
        (Game: lottery) Revise Listing 3.8, Lottery.java, to generate a lottery of a two-
        digit number. The two digits in the number are distinct. (Hint: Generate the first
        digit. Use a loop to continuously generate the second digit until it is different
        from the first digit.)
     */
    public static void ch5_32() {
        // Generate a lottery number
        int lotteryDigit1 = (int) (Math.random() * 10);
        int lotteryDigit2 = lotteryDigit1;
        while (lotteryDigit2 == lotteryDigit1) {
            lotteryDigit2 = (int) (Math.random() * 10);
        }
        int lottery = lotteryDigit1 * 10 + lotteryDigit2;

        // Prompt the user to enter a guess
        System.out.print("Enter your lottery pick (two digits): ");
        int guess = scanner.nextInt();

        // Get digits from guess
        int guessDigit1 = guess / 10;
        int guessDigit2 = guess % 10;

        System.out.println("The lottery number is " + lottery);

        // Check the guess
        if (guess == lottery)
            System.out.println("Exact match: you win $10,000");
        else if (guessDigit2 == lotteryDigit1
                && guessDigit1 == lotteryDigit2)
            System.out.println("Match all digits: you win $3,000");
        else if (guessDigit1 == lotteryDigit1
                || guessDigit1 == lotteryDigit2
                || guessDigit2 == lotteryDigit1
                || guessDigit2 == lotteryDigit2)

            System.out.println("Match one digit: you win $1,000");
        else
            System.out.println("Sorry, no match");
    }

    /*
        (Perfect number) A positive integer is called a perfect number if it is equal to the sum
        of all of its positive divisors, excluding itself. For example, 6 is the first perfect num-
        ber because 6 = 3 + 2 + 1. The next is 28 = 14 + 7 + 4 + 2 + 1. There are
        four perfect numbers < 10,000. Write a program to find all these four numbers.
     */
    public static void ch5_33() {
        final int MAX = 10_000;
        System.out.printf("Perfect numbers < %d are: \n", MAX);
        for (int i = 1; i <= MAX; i++) {
            if (isPerfect(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isPerfect(int n) {
        int divisorsSum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) divisorsSum += i;
        }
        return divisorsSum == n;
    }

    /*
        (Game: scissor, rock, paper) Programming Exercise 3.17 gives a program that
        plays the scissor–rock–paper game. Revise the program to let the user continu-
        ously play until either the user or the computer wins more than two times than its
        opponent.
     */
    public static void ch5_34() {
        final int WIN_COUNT = 2;
        int computerWins = 0;
        int playerWins = 0;
        int gamesCount = 1;
        while (Math.abs(playerWins - computerWins) <= WIN_COUNT) {
            System.out.printf("Game %d Player: %d Computer: %d\n", gamesCount, playerWins, computerWins);
            int computer = (int) (Math.random() * 3);
            System.out.print("scissor (0), rock (1), paper (2): ");
            int player = scanner.nextInt();

            System.out.printf("The computer is %s. You are %s. ", srpGameResolve(computer), srpGameResolve(player));
            if (computer == player) {
                System.out.print("It is a draw");
            } else if (player == 0 && computer == 2 || player == 1 && computer == 0 || player == 2 && computer == 1) {
                System.out.print("You won");
                playerWins++;
            } else {
                System.out.print("Computer won");
                computerWins++;
            }
            gamesCount++;
            System.out.print("\n\n");
        }
    }

    private static String srpGameResolve(int choice) {
        return switch (choice) {
            case 0 -> "scissor";
            case 1 -> "rock";
            case 2 -> "paper";
            default -> "unknown";
        };
    }

    /*
        (Summation) Write a program to compute the following summation:
        1/(1 + sqrt(2)) + 1/(sqrt(2) + sqrt(3)) + 1/(sqrt(3) + sqrt(4)) + ... +  + 1/(sqrt(624) + sqrt(625))
     */
    public static void ch5_35() {
        final int START = 2;
        final int END = 625;
        double sum = 0;
        for (int i = START; i <= END; i++) {
            sum += 1 / (Math.sqrt(i - 1) + Math.sqrt(i));
        }
        System.out.println(sum);
    }

    /*
        (Business application: checking ISBN) Use loops to simplify Programming
        Exercise 3.9.

        (Business: check ISBN-10) An ISBN-10 (International Standard Book Number)
        consists of 10 digits: d1 d2 d3 d4 d5 d6 d7 d8 d9 d10. The last digit, d10, is a checksum,
        which is calculated from the other 9 digits using the following formula:
        (d1 * 1 + d2 * 2 + d3 * 3 + d4 * 4 + d5 * 5 +
        d6 * 6 + d7 * 7 + d8 * 8 + d9 * 9)%11
        If the checksum is 10, the last digit is denoted as X according to the ISBN-10
        convention. Write a program that prompts the user to enter the first 9 digits and
        displays the 10-digit ISBN (including leading zeros). Your program should read
        the input as an integer. Here are sample runs:
            Enter the first 9 digits of an ISBN as integer: 013601267
            The ISBN-10 number is 0136012671
            Enter the first 9 digits of an ISBN as integer: 013031997
            The ISBN-10 number is 013031997X
     */
    public static void ch5_36() {
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        String digits = scanner.nextLine().strip();
        int checksum = 0;
        for (int i = 0; i < digits.length(); i++) {
            checksum += Integer.parseInt(digits.substring(i, i + 1)) * (i + 1);
        }
        checksum %= 11;
        System.out.printf("The ISBN-10 number is %s%s", digits, checksum == 10 ? "X" : checksum);
    }

    /*
        (Decimal to binary) Write a program that prompts the user to enter a decimal
        integer then displays its corresponding binary value. Don’t use Java’s Integer.
        toBinaryString(int) in this program.
     */
    public static void ch5_37() {
        System.out.print("Enter an integer value: ");
        int number = scanner.nextInt();
        String binary = number == 0 ? "0" : "";
        while (number > 0) {
            binary = number % 2 + binary;
            number /= 2;
        }
        System.out.println(binary);
    }

    /*
        (Decimal to octal) Write a program that prompts the user to enter a decimal
        integer and displays its corresponding octal value. Don’t use Java’s Integer.
        toOctalString(int) in this program.
     */
    public static void ch5_38() {
        System.out.print("Enter an integer value: ");
        int number = scanner.nextInt();
        String octal = number == 0 ? "0" : "";
        while (number > 0) {
            octal = number % 8 + octal;
            number /= 8;
        }
        System.out.println(octal);
    }

    /*
        (Financial application: find the sales amount) You have just started a sales job
        in a department store. Your pay consists of a base salary and a commission. The
        base salary is $5,000. The scheme shown below is used to determine the commis-
        sion rate.
        Sales Amount            Commission Rate
        $0.01–$5,000                    8%
        $5,000.01–$10,000               10%
        $10,000.01 and above            12%
        Note this is a graduated rate. The rate for the first $5,000 is at 8%, the next
        $5,000 is at 10%, and the rest is at 12%. If the sales amount is 25,000, the com-
        mission is 5,000 * 8 + 5,000 * 10 + 15,000 * 12 = 2,700
        Your goal is to earn $30,000 a year. Write a program that finds out the mini-
        mum number of sales you have to generate in order to make $30,000
     */
    public static void ch5_39() {
        final double GOAL = 30_000;
        double sales = 0;
        double step = 1;
        double income = 0;
        while (income < GOAL) {
            income = (sales < 5000 ? sales : 5000) * 0.08 +
                    (sales < 10000 ? sales - 5000 : 5000) * 0.1 +
                    (sales - 10000) * 0.12;
            sales += step;
        }
        System.out.printf("Minimal amount of sales to make $%.2f is $%.2f\n", income, sales);
    }

    /*
        (Simulation: heads or tails) Write a program that simulates flipping a coin one
        million times and displays the number of heads and tails.
     */
    public static void ch5_40() {
        final int FLIPS = 1_000_000;
        int heads = 0;
        int tails = 0;
        for (int i = 1; i <= FLIPS; i++) {
            if (Math.random() > 0.5) {
                heads++;
            } else {
                tails++;
            }
        }
        System.out.printf("%d flips was made\nHeads: %d\nTails: %d\n", heads + tails, heads, tails);
    }

    /*
        (Occurrence of max numbers) Write a program that reads integers, finds the larg-
        est of them, and counts its occurrences. Assume the input ends with number 0.
        Suppose you entered 3 5 2 5 5 5 0; the program finds that the largest is 5 and
        the occurrence count for 5 is 4. If no input is entered, display "No numbers are
        entered except 0".
        (Hint: Maintain two variables, max and count. max stores the current max
        number and count stores its occurrences. Initially, assign the first number to
        max and 1 to count. Compare each subsequent number with max. If the num-
        ber is greater than max, assign it to max and reset count to 1. If the number is
        equal to max, increment count by 1.)
            Enter numbers: 3 5 2 5 5 5 0
            The largest number is 5
            The occurrence count of the largest number is 4
     */
    public static void ch5_41() {
        System.out.print("Enter numbers: ");
        int input;
        int max = 0;
        int count = 0;
        do {
            input = scanner.nextInt();
            if (input > max) {
                max = input;
                count = 1;
            } else if (input == max) {
                count++;
            }
        } while (input != 0);
        if (max == 0) {
            System.out.println("No numbers are entered except 0");
        } else {
            System.out.println("The largest number is " + max);
            System.out.println("The occurrence count of the largest number is " + count);
        }
    }

    /*

     */
    public static void ch5_42() {
    }

    /*

     */
    public static void ch5_43() {
    }

    /*

     */
    public static void ch5_44() {
    }

    /*

     */
    public static void ch5_45() {
    }

    /*

     */
    public static void ch5_46() {
    }

    /*

     */
    public static void ch5_47() {
    }

    /*

     */
    public static void ch5_48() {
    }

    /*

     */
    public static void ch5_49() {
    }

    /*

     */
    public static void ch5_50() {
    }

    /*

     */
    public static void ch5_51() {
    }

}
