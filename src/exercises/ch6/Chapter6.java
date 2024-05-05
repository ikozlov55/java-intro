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
            System.out.printf("%-10.1f\t%-10.1f\t|\t%-10.1f\t%-10.2f\n", celsius, celsiusToFahrenheit(celsius), fahrenheit, fahrenheitToCelsius(fahrenheit));
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
            System.out.printf("%-10.1f\t%-10.3f\t|\t%-10.1f\t%-10.3f\n", feet, footToMeter(feet), meters, meterToFoot(meters));
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
        if (number <= 1) return false;
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
        return (sales < 5000 ? sales : 5000) * 0.08 + (sales < 10000 ? sales - 5000 : 5000) * 0.1 + (sales - 10000) * 0.12;
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
            System.out.printf("%-14d%-14d%-18d%-14d%-14d\n", Math.round(income), Math.round(computeTax(0, income)), Math.round(computeTax(1, income)), Math.round(computeTax(2, income)), Math.round(computeTax(3, income)));
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

        boolean onlyAlphanumeric = password.chars().filter(x -> !Character.isAlphabetic(x) && !Character.isDigit(x)).count() == 0;
        if (!onlyAlphanumeric) {
            return false;
        }

        long numberOfDigits = password.chars().filter(Character::isDigit).count();
        return numberOfDigits >= 2;
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
        (Phone keypads) The international standard letter/number mapping for tele-
        phones is given in Programming Exercise 4.15. Write a method that returns a
        number, given an uppercase letter, as follows:
        public static int getNumber(char uppercaseLetter)
        Write a test program that prompts the user to enter a phone number as a string.
        The input number may contain letters. The program translates a letter (uppercase
        or lowercase) to a digit and leaves all other characters intact. Here are sample
        runs of the program:
            Enter a string: 1-800-Flowers
            1-800-3569377
            Enter a string: 1800flowers
            18003569377
     */
    public static void ch6_21() {
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        String phoneNumber = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                phoneNumber += getNumber(Character.toUpperCase(c));
            } else {
                phoneNumber += c;
            }
        }
        System.out.println(phoneNumber);
    }

    public static int getNumber(char uppercaseLetter) {
        return switch (uppercaseLetter) {
            case 'A', 'B', 'C' -> 2;
            case 'D', 'E', 'F' -> 3;
            case 'G', 'H', 'I' -> 4;
            case 'J', 'K', 'L' -> 5;
            case 'M', 'N', 'O' -> 6;
            case 'P', 'Q', 'R', 'S' -> 7;
            case 'T', 'U', 'V' -> 8;
            case 'W', 'X', 'Y', 'Z' -> 9;
            default -> -1;
        };
    }

    /*
        (Math: approximate the square root) There are several techniques for imple-
        menting the sqrt method in the Math class. One such technique is known as the
        Babylonian method. It approximates the square root of a number, n, by repeat-
        edly performing the calculation using the following formula:
        nextGuess = (lastGuess + n / lastGuess) / 2
        When nextGuess and lastGuess are almost identical, nextGuess is the
        approximated square root. The initial guess can be any positive value (e.g., 1).
        This value will be the starting value for lastGuess. If the difference between
        nextGuess and lastGuess is less than a very small number, such as 0.0001,
        you can claim that nextGuess is the approximated square root of n. If not,
        nextGuess becomes lastGuess and the approximation process continues.
        Implement the following method that returns the square root of n:
        public static double sqrt(long n)
        Write a test program that prompts the user to enter a positive double value and
        displays its square root.
     */
    public static void ch6_22() {
        System.out.print("Enter a positive double value: ");
        double input = scanner.nextDouble();
        System.out.printf("It's square root is %.2f", sqrt((long) input));
    }

    public static double sqrt(long n) {
        double lastGuess = 0;
        double nextGuess = 1;
        while (Math.abs(lastGuess - nextGuess) > 0.0001) {
            lastGuess = nextGuess;
            nextGuess = (lastGuess + n / lastGuess) / 2;
            System.out.println(nextGuess);
        }
        return nextGuess;
    }

    /*
        (Occurrences of a specified character) Write a method that finds the number of
        occurrences of a specified character in a string using the following header:
        public static int count(String str, char a)
        For example, count("Welcome", 'e') returns 2. Write a test program that
        prompts the user to enter a string followed by a character then displays the
        number of occurrences of the character in the string.
     */
    public static void ch6_23() {
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();
        System.out.print("Enter a character: ");
        char ch = scanner.nextLine().strip().charAt(0);
        System.out.printf("There is %d occurrences of '%c' in string %s\n", count(str, ch), ch, str);

    }

    public static int count(String str, char a) {
        return (int) str.chars().filter(x -> (char) x == a).count();
    }

    /*
        (Display current date and time) Listing 2.7, ShowCurrentTime.java, displays the
        current time. Revise this example to display the current date and time. The calen-
        dar example in Listing 6.12, PrintCalendar.java, should give you some ideas on
        how to find the year, month, and day.
     */
    public static void ch6_24() {
        // Obtain the total milliseconds since midnight, Jan 1, 1970
        long totalMilliseconds = System.currentTimeMillis();
        long totalSeconds = totalMilliseconds / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        long totalDays = totalHours / 24;
        int currentYear = 1970;
        int currentMonth = 1;
        int currentDay = 0;
        while (totalDays > 0) {
            int daysInCurrentMonth = Calendar.getNumberOfDays(currentMonth, currentYear);
            if (totalDays > daysInCurrentMonth) {
                totalDays -= daysInCurrentMonth;
                currentMonth++;
            } else {
                currentDay = (int) totalDays + 1;
                totalDays = 0;
            }

            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }

        }
        long currentSecond = totalSeconds % 60;
        long currentMinute = totalMinutes % 60;
        long currentHour = totalHours % 24;

        System.out.printf("%d %s %d %02d:%02d:%02d\n", currentDay, Calendar.getMonthName(currentMonth), currentYear, currentHour, currentMinute, currentSecond);
    }

    /*
        (Convert milliseconds to hours, minutes, and seconds) Write a method that con-
        verts milliseconds to hours, minutes, and seconds using the following header:
        public static String convertMillis(long millis)
        The method returns a string as hours:minutes:seconds. For example, con-
        vertMillis(5500) returns a string 0:0:5, convertMillis(100000)
        returns a string 0:1:40, and convertMillis(555550000) returns a string
        154:19:10. Write a test program that prompts the user to enter a long integer
        for milliseconds and displays a string in the format of hours:minutes:seconds.
     */
    public static void ch6_25() {
        System.out.print("Enter a number of milliseconds: ");
        long millis = scanner.nextInt();
        System.out.println(convertMillis(millis));
    }

    public static String convertMillis(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        return String.format("%d:%d:%d", hours, minutes % 60, seconds % 60);
    }

    /*
        (Palindromic prime) A palindromic prime is a prime number and also palin-
        dromic. For example, 131 is a prime and also a palindromic prime, as are 313
        and 757. Write a program that displays the first 100 palindromic prime numbers.
        Display 10 numbers per line, separated by exactly one space, as follows:
        2 3 5 7 11 101 131 151 181 191
        313 353 373 383 727 757 787 797 919 929
        ..
     */
    public static void ch6_26() {
        final int TOTAL_NUMBERS = 100;
        final int NUMBERS_PER_LINE = 10;
        int numbers = 0;
        for (int i = 2; numbers <= TOTAL_NUMBERS; i++) {
            if (!isPrime(i) || !isPalindrome(i)) continue;
            numbers++;
            System.out.printf("%5d", i);
            System.out.print(numbers % NUMBERS_PER_LINE == 0 ? "\n" : " ");
        }
    }

    /*
        (Emirp) An emirp (prime spelled backward) is a nonpalindromic prime number
        whose reversal is also a prime. For example, 17 is a prime and 71 is a prime, so
        17 and 71 are emirps. Write a program that displays the first 100 emirps. Display
        10 numbers per line, separated by exactly one space, as follows:
        13 17 31 37 71 73 79 97 107 113
        149 157 167 179 199 311 337 347 359 389
        ...
     */
    public static void ch6_27() {
        final int TOTAL_NUMBERS = 100;
        final int NUMBERS_PER_LINE = 10;
        int numbers = 0;
        for (int i = 2; numbers <= TOTAL_NUMBERS; i++) {
            if (!isEmirp(i)) continue;
            numbers++;
            System.out.printf("%5d", i);
            System.out.print(numbers % NUMBERS_PER_LINE == 0 ? "\n" : " ");
        }
    }

    public static boolean isEmirp(int number) {
        return !isPalindrome(number) && isPrime(number) && isPrime(reverse(number));
    }

    /*
        (Mersenne prime) A prime number is called a Mersenne prime if it can be written
        in the form 2^p - 1 for some positive integer p. Write a program that finds all
        Mersenne primes with p … 31 and displays the output as follows:
        p         2^p – 1
        ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
        2         3
        3         7
        5         31
        ...
     */
    public static void ch6_28() {
        final int MAX_P = 31;
        System.out.println("p\t\t2^p – 1");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        for (int p = 0; p <= MAX_P; p++) {
            if (!isMersennePrime(p)) continue;
            System.out.printf("%d\t\t%d\n", p, (int) Math.pow(2, p) - 1);
        }
    }

    public static boolean isMersennePrime(int number) {
        return isPrime(number) && isPrime((int) Math.pow(2, number) - 1);
    }

    /*
        (Twin primes) Twin primes are a pair of prime numbers that differ by 2. For
        example, 3 and 5 are twin primes, 5 and 7 are twin primes, and 11 and 13 are
        twin primes. Write a program to find all twin primes less than 1,000. Display the
        output as follows:
        (3, 5)
        (5, 7)
        ...
     */
    public static void ch6_29() {
        final int MAX = 1000;
        for (int i = 0; i < MAX; i++) {
            if (!isPrime(i) || !isPrime(i + 2)) continue;
            System.out.printf("(%d, %d)\n", i, i + 2);
        }
    }

    /*
        (Game: craps) Craps is a popular dice game played in casinos. Write a program
        to play a variation of the game, as follows:
        Roll two dice. Each die has six faces representing values 1, 2, . . ., and 6, respec-
        tively. Check the sum of the two dice. If the sum is 2, 3, or 12 (called craps), you
        lose; if the sum is 7 or 11 (called natural), you win; if the sum is another value
        (i.e., 4, 5, 6, 8, 9, or 10), a point is established. Continue to roll the dice until either
        a 7 or the same point value is rolled. If 7 is rolled, you lose. Otherwise, you win.
        Your program acts as a single player. Here are some sample runs.
            You rolled 5 + 6 = 11
            You win
            You rolled 1 + 2 = 3
            You lose
            You rolled 4 + 4 = 8
            point is 8
            You rolled 6 + 2 = 8
            You win
            You rolled 3 + 2 = 5
            point is 5
            You rolled 2 + 5 = 7
            You lose
     */
    public static void ch6_30() {
        int dice1 = rollADice();
        int dice2 = rollADice();
        int sum = dice1 + dice2;
        int point;

        System.out.printf("You rolled %d + %d = %d\n", dice1, dice2, sum);
        if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println("You lose");
        } else if (sum == 7 || sum == 11) {
            System.out.println("You win");
        } else {
            point = sum;
            System.out.printf("point is %d\n", sum);
            while (true) {
                dice1 = rollADice();
                dice2 = rollADice();
                sum = dice1 + dice2;
                System.out.printf("You rolled %d + %d = %d\n", dice1, dice2, sum);
                if (sum == 7) {
                    System.out.println("You lose");
                    return;
                } else if (sum == point) {
                    System.out.println("You win");
                    return;
                }
            }
        }
    }

    public static int rollADice() {
        return (int) (Math.random() * 6 + 1);
    }

    /*
        (Financial: credit card number validation) Credit card numbers follow certain
        patterns. A credit card number must have between 13 and 16 digits. It must start
        with
        ■ 4 for Visa cards
        ■ 5 for Master cards
        ■ 37 for American Express cards
        ■ 6 for Discover cards
        In 1954, Hans Luhn of IBM proposed an algorithm for validating credit card
        numbers. The algorithm is useful to determine whether a card number is entered
        correctly, or whether a credit card is scanned correctly by a scanner. Credit card
        numbers are generated following this validity check, commonly known as the
        Luhn check or the Mod 10 check, which can be described as follows (for illustra-
        tion, consider the card number 4388576018402626):
        1. Double every second digit from right to left. If doubling of a digit results in a
        two-digit number, add up the two digits to get a single-digit number.
        2. Now add all single-digit numbers from Step 1.
        4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
        3. Add all digits in the odd places from right to left in the card number.
        6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
        4. Sum the results from Step 2 and Step 3.
        37 + 38 = 75
        5. If the result from Step 4 is divisible by 10, the card number is valid; other-
        wise, it is invalid. For example, the number 4388576018402626 is invalid,
        but the number 4388576018410707 is valid.
        Write a program that prompts the user to enter a credit card number as a long
        integer. Display whether the number is valid or invalid. Design your program to
        use the following methods:
        Return true if the card number is valid:
        public static boolean isValid(long number)
        Get the result from Step 2:
        public static int sumOfDoubleEvenPlace(long number)
        Return this number if it is a single digit, otherwise,
        return the sum of the two digits:
        public static int getDigit(int number)
        Return sum of odd-place digits in number:
        public static int sumOfOddPlace(long number)
        Return true if the number d is a prefix for number:
        public static boolean prefixMatched(long number, int d)
        Return the number of digits in d
        public static int getSize(long d)
        Return the first k number of digits from number. If the
        number of digits in number is less than k, return number.:
        public static long getPrefix(long number, int k)
        (You may also implement this program by reading the input as a string and pro-
        cessing the string to validate the credit card.)
            Enter a credit card number as a long integer:
            4388576018410707
            4388576018410707 is valid
            Enter a credit card number as a long integer:
            4388576018402626
            4388576018402626 is invalid
     */
    public static void ch6_31() {
        System.out.print("Enter a credit card number as a long integer: ");
        long cardNumber = scanner.nextLong();
        System.out.printf("%d is %s\n", cardNumber, LuhnAlgorithm.isValid(cardNumber) ? "valid" : "invalid");
    }

    static class LuhnAlgorithm {
        public static boolean isValid(long number) {
            int size = getSize(number);
            if (size < 13 || size > 16) return false;
            if (!prefixMatched(number, 4) && !prefixMatched(number, 5) && !prefixMatched(number, 37) && !prefixMatched(number, 6)) {
                return false;
            }
            int checksum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
            return checksum % 10 == 0;
        }

        public static int sumOfDoubleEvenPlace(long number) {
            int result = 0;
            String cardNumber = Long.toString(number);
            for (int i = 0; i < getSize(number); i += 2) {
                int value = Character.getNumericValue(cardNumber.charAt(i));
                value *= 2;
                result += getDigit(value);
            }
            return result;
        }

        public static int getDigit(int number) {
            return number <= 9 ? number : number / 10 + number % 10;
        }

        public static int sumOfOddPlace(long number) {
            int result = 0;
            String cardNumber = Long.toString(number);
            for (int i = getSize(number) - 1; i > 0; i -= 2) {
                result += Character.getNumericValue(cardNumber.charAt(i));
            }
            return result;
        }

        public static boolean prefixMatched(long number, int d) {
            return getPrefix(number, getSize(d)) == d;
        }

        public static int getSize(long d) {
            if (d == 0) return 1;
            int result = 0;
            while (d > 0) {
                d /= 10;
                result++;
            }
            return result;
        }

        public static long getPrefix(long number, int k) {
            int size = getSize(number);
            if (size < k) return number;
            while (size > k) {
                number /= 10;
                size--;
            }
            return number;
        }
    }

    /*
        (Game: chance of winning at craps) Revise Programming Exercise 6.30 to run it
        10,000 times and display the number of winning games.
     */
    public static void ch6_32() {
        final int NUMBER_OF_GAMES = 10_000;
        int games = 1;
        int wins = 0;

        while (games <= NUMBER_OF_GAMES) {
            int dice1 = rollADice();
            int dice2 = rollADice();
            int sum = dice1 + dice2;
            int point;
            if (sum == 7 || sum == 11) {
                wins++;
            } else if (sum != 2 && sum != 3 && sum != 12) {
                point = sum;
                while (true) {
                    dice1 = rollADice();
                    dice2 = rollADice();
                    sum = dice1 + dice2;
                    if (sum == 7) {
                        break;
                    } else if (sum == point) {
                        wins++;
                        break;
                    }
                }
            }
            games++;
        }
        System.out.printf("You've won %d out of %d games", wins, NUMBER_OF_GAMES);
    }

    /*
        (Current date and time) Invoking System.currentTimeMillis() returns the
        elapsed time in milliseconds since midnight of January 1, 1970. Write a program
        that displays the date and time. Here is a sample run:
        Current date and time is May 16, 2012 10:34:23
     */
    public static void ch6_33() {
        long totalMilliseconds = System.currentTimeMillis();
        long totalSeconds = totalMilliseconds / 1000;
        long totalMinutes = totalSeconds / 60;
        long totalHours = totalMinutes / 60;
        long totalDays = totalHours / 24;
        int currentYear = 1970;
        int currentMonth = 1;
        int currentDay = 0;
        while (totalDays > 0) {
            int daysInCurrentMonth = Calendar.getNumberOfDays(currentMonth, currentYear);
            if (totalDays > daysInCurrentMonth) {
                totalDays -= daysInCurrentMonth;
                currentMonth++;
            } else {
                currentDay = (int) totalDays + 1;
                totalDays = 0;
            }

            if (currentMonth > 12) {
                currentMonth = 1;
                currentYear++;
            }

        }
        long currentSecond = totalSeconds % 60;
        long currentMinute = totalMinutes % 60;
        long currentHour = totalHours % 24;

        System.out.printf("Current date and time is %s %d, %d %02d:%02d:%02d\n", Calendar.getMonthName(currentMonth), currentDay, currentYear, currentHour, currentMinute, currentSecond);
    }

    /*
        (Print calendar) Programming Exercise 3.21 uses Zeller’s congruence to calcu-
        late the day of the week. Simplify Listing 6.12, PrintCalendar.java, using Zeller’s
        algorithm to get the start day of the month
     */
    public static void ch6_34() {
        // Prompt the user to enter year
        System.out.print("Enter full year (e.g., 2012): ");
        int year = scanner.nextInt();

        // Prompt the user to enter month
        System.out.print("Enter month as a number between 1 and 12: ");
        int month = scanner.nextInt();

        // Print calendar for the month of the year
        printMonth(year, month);
    }

    /**
     * Print the calendar for a month in a year
     */
    public static void printMonth(int year, int month) {
        // Print the headings of the calendar
        printMonthTitle(year, month);

        // Print the body of the calendar
        printMonthBody(year, month);
    }

    /**
     * Print the month title, e.g., March 2012
     */
    public static void printMonthTitle(int year, int month) {
        System.out.println(" " + Calendar.getMonthName(month) + " " + year);
        System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−−");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
    }


    public static void printMonthBody(int year, int month) {
        // Get start day of the week for the first date in the month
        int startDay = getStartDay(year, month);

        // Get number of days in the month
        int numberOfDaysInMonth = Calendar.getNumberOfDays(month, year);

        // Pad space before the first day of the month
        int i = 0;
        for (i = 0; i < startDay; i++)
            System.out.print("    ");

        for (i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);

            if ((i + startDay) % 7 == 0) System.out.println();
        }

        System.out.println();
    }

    /**
     * Get the start day of month/1/year
     */
    public static int getStartDay(int year, int month) {
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int k = year % 100;
        int j = year / 100;
        int h = (1 + 26 * (month + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
        return switch (h) {
            case 0 -> 6;
            case 1 -> 0;
            case 2, 3, 4, 5, 6 -> h - 1;
            default -> -1;
        };
    }


    /*
        (Geometry: area of a pentagon) The area of a pentagon can be computed using
        the following formula:
        Area = (5 * s^2)/(4 * tan(pi/5))
        Write a method that returns the area of a pentagon using the following header:
        public static double area(double side)
        Write a main method that prompts the user to enter the side of a pentagon and
        displays its area. Here is a sample run:
            Enter the side: 5.5
            The area of the pentagon is 52.04444136781625
     */
    public static void ch6_35() {
        System.out.print("Enter the side: ");
        double side = scanner.nextDouble();
        System.out.println("The area of the pentagon is " + area(side));
    }

    public static double area(double side) {
        return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
    }

    /*
        (Geometry: area of a regular polygon) A regular polygon is an n-sided polygon
        in which all sides are of the same length and all angles have the same degree (i.e.,
        the polygon is both equilateral and equiangular). The formula for computing the
        area of a regular polygon is
        Area = (n * s^2)/(4 * tan(pi/n)
        Write a method that returns the area of a regular polygon using the following
        header:
        public static double area(int n, double side)
        Write a main method that prompts the user to enter the number of sides and the
        side of a regular polygon and displays its area. Here is a sample run:
            Enter the number of sides: 5
            Enter the side: 6.5
            The area of the polygon is 72.69017017488385
     */
    public static void ch6_36() {
        System.out.print("Enter the number of sides: ");
        int n = scanner.nextInt();
        System.out.print("Enter the side: ");
        double side = scanner.nextDouble();
        System.out.println("The area of the polygon is " + area(n, side));
    }

    public static double area(int n, double side) {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }

    /*
        (Format an integer) Write a method with the following header to format the inte-
        ger with the specified width.
        public static String format(int number, int width)
        The method returns a string for the number with one or more prefix 0s. The size
        of the string is the width. For example, format(34, 4) returns 0034 and for-
        mat(34, 5) returns 00034. If the number is longer than the width, the method
        returns the string representation for the number. For example, format(34, 1)
        returns 34.
        Write a test program that prompts the user to enter a number and its width, and
        displays a string returned by invoking format(number, width).
     */
    public static void ch6_37() {
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.print("Enter width: ");
        int width = scanner.nextInt();
        System.out.println(format(number, width));
    }

    public static String format(int number, int width) {
        int size = getSize(number);
        if (size > width) return Integer.toString(number);
        String result = "";
        int zeros = width - size;
        while (zeros > 0) {
            result += "0";
            zeros--;
        }
        result += number;
        return result;
    }

    public static int getSize(int d) {
        if (d == 0) return 1;
        int result = 0;
        while (d > 0) {
            d /= 10;
            result++;
        }
        return result;
    }

    /*
        (Generate random characters) Use the methods in RandomCharacter in Listing
        6.10 to print 100 uppercase letters then 100 single digits, printing 50 per line.
     */
    public static void ch6_38() {
        final int NUMBER_OF_CHARS = 100;
        final int CHARS_PER_LINE = 50;

        for (int i = 1; i <= NUMBER_OF_CHARS; i++) {
            System.out.print(getRandomUpperCaseLetter());
            System.out.print(i % CHARS_PER_LINE == 0 ? "\n" : " ");
        }
        for (int i = 1; i <= NUMBER_OF_CHARS; i++) {
            System.out.print(getRandomDigitCharacter());
            System.out.print(i % CHARS_PER_LINE == 0 ? "\n" : " ");
        }
    }

    public static char getRandomCharacter(char ch1, char ch2) {
        return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
    }


    /**
     * Generate a random uppercase letter
     */
    public static char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    /**
     * Generate a random digit character
     */
    public static char getRandomDigitCharacter() {
        return getRandomCharacter('0', '9');
    }


    /*
        (Geometry: point position) Programming Exercise 3.32 shows how to test
        whether a point is on the left side of a directed line, on the right, or on the same
        line. Write the methods with the following headers:
        Return true if point (x2, y2) is on the left side of the
        directed line from (x0, y0) to (x1, y1) :
        public static boolean leftOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2)
        Return true if point (x2, y2) is on the same
        line from (x0, y0) to (x1, y1):
        public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2)
        Return true if point (x2, y2) is on the
        line segment from (x0, y0) to (x1, y1):
        public static boolean onTheLineSegment(double x0, double y0, double x1, double y1, double x2, double y2)
        Write a program that prompts the user to enter the three points for p0, p1, and p2
        and displays whether p2 is on the left side of the line from p0 to p1, right side,
        the same line, or on the line segment. Here are some sample runs:
            Enter three points for p0, p1, and p2: 1 1 2 2 1.5 1.5
            (1.5, 1.5) is on the line segment from (1.0, 1.0) to (2.0, 2.0)

            Enter three points for p0, p1, and p2: 1 1 2 2 3 3
            (3.0, 3.0) is on the same line from (1.0, 1.0) to (2.0, 2.0)

            Enter three points for p0, p1, and p2: 1 1 2 2 1 1.5
            (1.0, 1.5) is on the left side of the line from (1.0, 1.0) to (2.0, 2.0)

            Enter three points for p0, p1, and p2: 1 1 2 2 1 –1
            (1.0, −1.0)from (1.0, 1.0) to (2.0, 2.0)
     */
    public static void ch6_39() {
        System.out.print("Enter three points for p0, p1, and p2: ");
        double x0 = scanner.nextDouble();
        double y0 = scanner.nextDouble();
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        System.out.printf("(%.1f, %.1f)", x2, y2);

        if (onTheLineSegment(x0, y0, x1, y1, x2, y2)) {
            System.out.print(" is on the line segment ");
        } else if (onTheSameLine(x0, y0, x1, y1, x2, y2)) {
            System.out.print(" is on the same line ");
        } else if (leftOfTheLine(x0, y0, x1, y1, x2, y2)) {
            System.out.print(" is on the left side of the line ");
        } else {
            System.out.print(" is on the right side of the line ");
        }
        System.out.printf("from (%.1f, %.1f) to (%.1f, %.1f)", x0, y0, x1, y1);
    }

    public static boolean leftOfTheLine(double x0, double y0, double x1, double y1, double x2, double y2) {
        return d(x0, y0, x1, y1, x2, y2) > 0;
    }

    public static boolean onTheSameLine(double x0, double y0, double x1, double y1, double x2, double y2) {
        return d(x0, y0, x1, y1, x2, y2) == 0;
    }

    public static boolean onTheLineSegment(double x0, double y0, double x1, double y1, double x2, double y2) {
        double d = d(x0, y0, x1, y1, x2, y2);
        double lineDistance = distance(x0, y0, x1, y1);
        return d == 0 && distance(x0, y0, x2, y2) < lineDistance && distance(x1, y1, x2, y2) < lineDistance;
    }

    private static double d(double x0, double y0, double x1, double y1, double x2, double y2) {
        return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
