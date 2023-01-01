package exercises.ch6;

import exercises.utils.Calendar;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        (Financial application: compute the future investment value) Write a method that
        computes future investment value at a given interest rate for a specified number
        of years. The future investment is determined using the formula in Programming
        Exercise 2.21.
        Use the following method header:
        public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate,int years)
        For example, futureInvestmentValue(10000, 0.05/12, 5) returns
        12833.59.
        Write a test program that prompts the user to enter the investment amount (e.g.,
        1,000) and the interest rate (e.g., 9%) and prints a table that displays future value
        for the years from 1 to 30, as shown below:
            The amount invested: 1000
            Annual interest rate: 9
            Years   Future Value
            1            1093.80
            2            1196.41
            ...
            29          13467.25
            30          14730.57
     */
    public static void ch6_7() {
        final int YEARS = 30;
        System.out.print("The amount invested: ");
        double amount = scanner.nextDouble();
        System.out.print("Annual interest rate: ");
        int rate = scanner.nextInt();
        double monthlyInterestRate = rate / 100.0 / 12;
        System.out.printf("%-8s %12s\n", "Years", "Future Value");
        for (int i = 1; i <= YEARS; i++) {
            System.out.printf("%-8d %12.2f\n", i, futureInvestmentValue(amount, monthlyInterestRate, i));
        }
    }

    public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years) {
        return investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12);
    }

    /*
        (Conversions between Celsius and Fahrenheit) Write a class that contains the
        following two methods:
        Convert from Celsius to Fahrenheit:
        public static double celsiusToFahrenheit(double celsius)
        Convert from Fahrenheit to Celsius:
        public static double fahrenheitToCelsius(double fahrenheit)
        The formula for the conversion is as follows:
        fahrenheit = (9.0 / 5) * celsius + 32
        celsius = (5.0 / 9) * (fahrenheit – 32)
        Write a test program that invokes these methods to display the following table:
        Celsius     Fahrenheit   |   Fahrenheit      Celsius
        40.0        104.0        |   120.0           48.89
        39.0        102.2        |   110.0           43.33
        38.0        100.4        |   100.0           37.78
        37.0        98.6         |   90.0            32.22
        36.0        96.8         |   80.0            26.67
        35.0        95.0         |   70.0            21.11
        34.0        93.2         |   60.0            21.11
        33.0        91.4         |   50.0            10.00
        32.0        89.6         |   40.0            4.44
        31.0        87.8         |   30.0            –1.11
     */
    public static void ch6_8() {
        double fahrenheit = 120;
        System.out.printf("%-10s\t%-10s\t|\t%-10s\t%-10s\n", "Celsius", "Fahrenheit", "Fahrenheit", "Celsius");
        for (double celsius = 40; celsius >= 31; celsius--) {
            System.out.printf("%-10.1f\t%-10.1f\t|\t%-10.1f\t%-10.2f\n",
                    celsius,
                    celsiusToFahrenheit(celsius),
                    fahrenheit,
                    fahrenheitToCelsius(fahrenheit)
            );
            fahrenheit -= 10;
        }
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (9.0 / 5) * celsius + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (5.0 / 9) * (fahrenheit - 32);
    }

    /*
        (Conversions between feet and meters) Write a class that contains the following
        two methods:
        Convert from feet to meters:
        public static double footToMeter(double foot)
        Convert from meters to feet:
        public static double meterToFoot(double meter)
        The formula for the conversion is:
        meter = 0.305 * foot
        foot = 3.279 * meter
        Feet      	Meters    	|	Meters    	Feet
        1,0       	0,305     	|	20,0      	65,580
        2,0       	0,610     	|	25,0      	81,975
        3,0       	0,915     	|	30,0      	98,370
        4,0       	1,220     	|	35,0      	114,765
        5,0       	1,525     	|	40,0      	131,160
        6,0       	1,830     	|	45,0      	147,555
        7,0       	2,135     	|	50,0      	163,950
        8,0       	2,440     	|	55,0      	180,345
        9,0       	2,745     	|	60,0      	196,740
        10,0      	3,050     	|	65,0      	213,135
     */
    public static void ch6_9() {
        double meters = 20;
        System.out.printf("%-10s\t%-10s\t|\t%-10s\t%-10s\n", "Feet", "Meters", "Meters", "Feet");
        for (double feet = 1; feet <= 10; feet++) {
            System.out.printf("%-10.1f\t%-10.3f\t|\t%-10.1f\t%-10.3f\n",
                    feet,
                    footToMeter(feet),
                    meters,
                    meterToFoot(meters)
            );
            meters += 5;
        }
    }

    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    /*
        (Use the isPrime Method) Listing 6.7, PrimeNumberMethod.java, provides the
        isPrime(int number) method for testing whether a number is prime. Use
        this method to find the number of prime numbers less than 10000.
     */
    public static void ch6_10() {
        final int MAX = 10000;
        long primes = IntStream.rangeClosed(1, MAX).filter(Chapter6::isPrime).count();
        System.out.printf("Number of prime numbers less than %d is %d\n", MAX, primes);
    }

    public static boolean isPrime(int number) {
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) { // If true, number is not prime
                return false; // Number is not a prime
            }
        }
        return true; // Number is prime
    }

    /*
        (Financial application: compute commissions) Write a method that computes the
        commission, using the scheme in Programming Exercise 5.39. The header of the
        method is as follows:
        public static double computeCommission(double salesAmount)
        Write a test program that displays the following table:
            Sales Amount      Commission
            10000             900.0
            15000             1500.0
            20000             2100.0
            25000             2700.0
            30000             3300.0
            ...
            90000             10500.0
            95000             11100.0
            100000            11700.0
     */
    public static void ch6_11() {
        final int START = 10000;
        final int STOP = 100000;
        final int STEP = 5000;
        System.out.println("Sales Amount\tCommission");
        for (int sales = START; sales <= STOP; sales += STEP) {
            System.out.printf("%-12d\t%-10.1f\n", sales, getCommission(sales));
        }
    }

    public static double getCommission(double sales) {
        return (sales < 5000 ? sales : 5000) * 0.08 +
                (sales < 10000 ? sales - 5000 : 5000) * 0.1 +
                (sales - 10000) * 0.12;
    }

    /*
        (Display characters) Write a method that prints characters using the following
        header:
        public static void printChars(char ch1, char ch2, int numberPerLine)
        This method prints the characters between ch1 and ch2 with the specified num-
        bers per line. Write a test program that prints 10 characters per line from 1 to Z.
        Characters are separated by exactly one space
     */
    public static void ch6_12() {
        printChars('1', 'Z', 10);
    }

    public static void printChars(char ch1, char ch2, int numberPerLine) {
        int i = 1;
        for (char c = ch1; c <= ch2; c++) {
            System.out.print(c);
            System.out.print(i % numberPerLine == 0 ? "\n" : " ");
            i++;
        }
    }

    /*
        (Sum series) Write a method to compute the following summation:
        m(i) = 1/2 + 2/3 + ... +i/(i + 1)
        Write a test program that displays the following table:
            i           m(i)
            1           0.5000
            2           1.1667
            3           1.9167
            4           2.7167
            5           3.5500
            6           4.4071
            7           5.2821
            ...
            16          13.5604
            17          14.5049
            18          15.4523
            19          16.4023
            20          17.3546
     */
    public static void ch6_13() {
        System.out.println("i\t\t\tm(i)");
        for (int i = 1; i <= 20; i++) {
            System.out.printf("%-2d\t\t\t%-6.4f\n", i, m(i));
        }
    }

    public static double m(int i) {
        double result = 0;
        for (int j = 1; j <= i; j++) {
            result += j / (j + 1.0);
        }
        return result;
    }

    /*
        (Estimate p) p can be computed using the following summation:
        m(i) = 4(1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 + ... + (-1)^(i+1)/(2i - 1)
        Write a method that returns m(i) for a given i and write a test program that
        displays the following table:
                i                 m(i)
                1                 4.0000
                101               3.1515
                201               3.1466
                301               3.1449
                401               3.1441
                501               3.1436
                601               3.1433
                701               3.1430
                801               3.1428
                901               3.1427
     */
    public static void ch6_14() {
        System.out.println("i\t\t\tm(i)");
        for (int i = 1; i <= 1000; i += 100) {
            System.out.printf("%d\t\t\t%-6.4f\n", i, computePi(i));
        }
    }

    public static double computePi(int n) {
        double pi = 0;
        for (int i = 1; i <= n; i++) {
            pi += Math.pow(-1, i + 1) / (2 * i - 1);
        }
        return pi * 4;
    }

    /*
        (Financial application: print a tax table) Listing 3.5 gives a program to compute
        tax. Write a method for computing tax using the following header:
        public static double computeTax(int status, double taxableIncome)
        Use this method to write a program that prints a tax table for taxable income
        from $50,000 to $60,000 with intervals of $50 for all the following statuses:
        Taxable    Single    Married Joint    Married     Head of
        Income               or Qualifying    Separate    House hold
                             Widow(er)
        50000      8688      6665             8688        7353
        50050      8700      6673             8700        7365
        50100      8712      6680             8712        7378
        50150      8725      6688             8725        7390
        . . .
        59850      11150     8142             11150       9815
        59900      11162     8150             11162       9828
        59950      11175     8158             11175       9840
        60000      11188     8165             11188       9853
        Hint: round the tax into integers using Math.round (i.e., Math .round(com-
        puteTax(status, taxableIncome))).
     */
    public static void ch6_15() {
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Taxable", "Single", "Married Joint", "Married", "Head of");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Income", "", "or Qualifying", "Separate", "House hold");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "", "", "Widow(er)", "", "");
        for (double income = 50_000; income <= 60_000; income += 50) {
            System.out.printf("%-14d%-14d%-18d%-14d%-14d\n",
                    Math.round(income),
                    Math.round(computeTax(0, income)),
                    Math.round(computeTax(1, income)),
                    Math.round(computeTax(2, income)),
                    Math.round(computeTax(3, income))
            );
        }
    }

    public static double computeTax(int status, double taxableIncome) {
        double tax = -1;
        if (status == 0) { // Compute tax for single filers
            tax = computeTax(taxableIncome, 8350, 33950, 82250, 171550, 372950);
        } else if (status == 1) { // Compute tax for married file jointly or qualifying widow(er)
            tax = computeTax(taxableIncome, 16700, 67900, 137050, 208850, 372950);
        } else if (status == 2) { // Compute tax for married separately
            tax = computeTax(taxableIncome, 8350, 33950, 68525, 104425, 186475);
        } else if (status == 3) { // Compute tax for head of household
            tax = computeTax(taxableIncome, 11950, 45500, 117450, 190200, 372950);
        }
        return tax;
    }

    public static double computeTax(double income, double m1, double m2, double m3, double m4, double m5) {
        if (income <= m1) return income * 0.10;
        else if (income <= m2) return m1 * 0.10 + (income - m1) * 0.15;
        else if (income <= m3) return m1 * 0.10 + (m2 - m1) * 0.15 + (income - m2) * 0.25;
        else if (income <= m4) return m1 * 0.10 + (m2 - m1) * 0.15 + (m3 - m2) * 0.25 + (income - m3) * 0.28;
        else if (income <= m5)
            return m1 * 0.10 + (m2 - m1) * 0.15 + (m3 - m2) * 0.25 + (m4 - m3) * 0.28 + (income - m4) * 0.33;
        else
            return m1 * 0.10 + (m2 - m1) * 0.15 + (m3 - m2) * 0.25 + (m4 - m3) * 0.28 + (m5 - m4) * 0.33 + (income - m5) * 0.35;
    }

    /*
        (Number of days in a year) Write a method that returns the number of days in a
        year using the following header:
        public static int numberOfDaysInAYear(int year)
        Write a test program that displays the number of days in year from 2000 to 2020.
     */
    public static void ch6_16() {
        System.out.printf("%10s%14s\n", "Year", "Number of days");
        for (int year = 2000; year <= 2020; year++) {
            System.out.printf("%10d%14d\n", year, numberOfDaysInAYear(year));
        }
    }

    public static int numberOfDaysInAYear(int year) {
        return Calendar.isLeapYear(year) ? 366 : 365;
    }

    /*
        (Display matrix of 0s and 1s) Write a method that displays an n-by-n matrix
        using the following header:
        public static void printMatrix(int n)
        Each element is 0 or 1, which is generated randomly. Write a test program that
        prompts the user to enter n and displays an n-by-n matrix.
        Here is a sample run:
            Enter n: 3
            0 1 0
            0 0 0
            1 1 1
     */
    public static void ch6_17() {
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        printMatrix(n);
    }

    public static void printMatrix(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", Math.round(Math.random()));
            }
            System.out.println();
        }
    }

    /*
        (Check password) Some Websites impose certain rules for passwords. Write a
        method that checks whether a string is a valid password. Suppose the password
        rules are as follows:
        ■ A password must have at least eight characters.
        ■ A password must contain only letters and digits.
        ■ A password must contain at least two digits.
        Write a program that prompts the user to enter a password and displays Valid
        Password if the rules are followed, or Invalid Password otherwise.
     */
    public static void ch6_18() {
        System.out.print("Enter a password: ");
        String password = scanner.nextLine().trim();
        System.out.printf("%s password\n", isValid(password) ? "Valid" : "Invalid");
    }

    public static boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean onlyAlphanumeric = password.chars()
                .filter(x -> !Character.isAlphabetic(x) && !Character.isDigit(x))
                .count() == 0;
        if (!onlyAlphanumeric) {
            return false;
        }

        long numberOfDigits = password.chars().filter(Character::isDigit).count();
        if (numberOfDigits < 2) {
            return false;
        }

        return true;
    }

    /*
        (Triangles) Implement the following two methods:
        Return true if the sum of every two sides is greater than the third side:
        public static boolean isValid(double side1, double side2, double side3)
        Return the area of the triangle:
        public static double area(double side1, double side2, double side3)
        Write a test program that reads three sides for a triangle and uses the isValid
        method to test if the input is valid and uses the area method to obtain the area.
        The program displays the area if the input is valid. Otherwise, it displays that
        the input is invalid. The formula for computing the area of a triangle is given in
        Programming Exercise 2.19.
     */
    public static void ch6_19() {
        System.out.print("Enter three sides of a triangle: ");
        double side1 = scanner.nextDouble();
        double side2 = scanner.nextDouble();
        double side3 = scanner.nextDouble();
        if (!isValid(side1, side2, side3)) {
            System.out.println("Invalid input");
        } else {
            System.out.printf("The area of the triangle is %.1f", area(side1, side2, side3));
        }
    }

    public static boolean isValid(double side1, double side2, double side3) {
        return side1 < side2 + side3 && side2 < side1 + side3 && side3 < side1 + side2;
    }

    public static double area(double side1, double side2, double side3) {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /*
        (Count the letters in a string) Write a method that counts the number of letters in
        a string using the following header:
        public static int countLetters(String s)
        Write a test program that prompts the user to enter a string and displays the num-
        ber of letters in the string.
     */
    public static void ch6_20() {
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        System.out.printf("This string has %d letters\n", countLetters(input));
    }

    public static int countLetters(String s) {
        return (int) s.chars().filter(Character::isLetter).count();
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
