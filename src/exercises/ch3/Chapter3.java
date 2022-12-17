package exercises.ch3;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Chapter3 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Algebra: solve quadratic equations) The two roots of a quadratic equation
        ax2 + bx + c = 0 can be obtained using the following formula:
        r1 = (-b + sqrt(b^2 - 4ac))/2a
        r2 = (-b - sqrt(b^2 - 4ac))/2a

        b^2 - 4ac is called the discriminant of the quadratic equation. If it is positive, the
        equation has two real roots. If it is zero, the equation has one root. If it is nega-
        tive, the equation has no real roots.
        Write a program that prompts the user to enter values for a, b, and c and displays
        the result based on the discriminant. If the discriminant is positive, display two
        roots. If the discriminant is 0, display one root. Otherwise, display “The equation
        has no real roots.”
        Note you can use Math.pow(x, 0.5) to compute 2x. Here are some sample
        runs:
            Enter a, b, c: 1.0 3 1
            The equation has two roots −0.381966 and −2.61803
            Enter a, b, c: 1 2.0 1
            The equation has one root −1.0
            Enter a, b, c: 1 2 3
            The equation has no real roots
     */
    public static void ch3_1() {
        System.out.print("Enter a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = Math.pow(b, 2) - 4 * a * c;
        double r1 = (-b + Math.sqrt(d)) / 2 * a;
        if (d > 0) {
            double r2 = (-b - Math.sqrt(d)) / 2 * a;
            System.out.printf("The equation has two roots %.5f and %.5f\n", r1, r2);
        } else if (d == 0) {
            System.out.printf("The equation has one root %.5f\n", r1);
        } else {
            System.out.println("The equation has no real roots");
        }
    }

    /*
        (Game: add three numbers) The program in Listing 3.1, AdditionQuiz.java, gen-
        erates two integers and prompts the user to enter the sum of these two integers.
        Revise the program to generate three single-digit integers and prompt the user to
        enter the sum of these three integers.
     */
    public static void ch3_2() {
        int n1 = (int) (System.currentTimeMillis() % 10);
        int n2 = (int) (System.currentTimeMillis() / 10 % 10);
        int n3 = (int) (System.currentTimeMillis() / 100 % 10);
        System.out.printf("What is %d + %d + %d? ", n1, n2, n3);
        int answer = scanner.nextInt();
        int sum = n1 + n2 + n3;
        System.out.printf("%d + %d + %d = %d is %b\n", n1, n2, n3, answer, answer == sum);
    }

    /*
        (Algebra: solve 2 * 2 linear equations) A linear equation can be solved using
        Cramer’s rule given in Programming Exercise 1.13. Write a program that prompts
        the user to enter a, b, c, d, e, and f and displays the result. If ad - bc is 0, report
        that “The equation has no solution.”
            Enter a, b, c, d, e, f: 9.0 4.0 3.0 −5.0 −6.0 −21.0
            x is −2.0 and y is 3.0
            Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0
            The equation has no solution
     */
    public static void ch3_3() {
        System.out.print("Enter a, b, c, d, e, f: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        double e = scanner.nextDouble();
        double f = scanner.nextDouble();
        if (a * d - b * c == 0) {
            System.out.println("The equation has no solution.");
        } else {
            double x = (e * d - b * f) / (a * d - b * c);
            double y = (a * f - e * c) / (a * d - b * c);
            System.out.printf("x is %.1f and y is %.1f\n", x, y);
        }
    }

    /*
        (Random month) Write a program that randomly generates an integer between 1
        and 12 and displays the English month names January, February, . . . , December
        for the numbers 1, 2, . . . , 12, accordingly.
     */
    public static void ch3_4() {
        int month = (int) (Math.random() * 12 + 1);
        String monthName = getMonthName(month);
        System.out.println(monthName);
    }

    private static String getMonthName(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Unknown";
        };
    }

    /*
        (Find future dates) Write a program that prompts the user to enter an integer for
        today’s day of the week (Sunday is 0, Monday is 1, . . . , and Saturday is 6). Also
        prompt the user to enter the number of days after today for a future day and dis-
        play the future day of the week. Here is a sample run:
            Enter today’s day: 1
            Enter the number of days elapsed since today: 3
            Today is Monday and the future day is Thursday
            Enter today’s day: 0
            Enter the number of days elapsed since today: 31
            Today is Sunday and the future day is Wednesday
     */
    public static void ch3_5() {
        System.out.print("Enter today’s day: ");
        int todayInt = scanner.nextInt();
        System.out.print("Enter the number of days elapsed since today: ");
        int daysElapsed = scanner.nextInt();
        String today = getWeekdayOf(todayInt);
        String futureDay = getWeekdayOf((todayInt + daysElapsed) % 7);
        System.out.printf("Today is %s and the future day is %s\n", today, futureDay);
    }

    private static String getWeekdayOf(int n) {
        return switch (n) {
            case 0 -> "Sunday";
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Unknown";
        };
    }

    /*
        (Health application: BMI) Revise Listing 3.4, ComputeAndInterpretBMI.java, to
        let the user enter weight, feet, and inches. For example, if a person is 5 feet and
        10 inches, you will enter 5 for feet and 10 for inches. Here is a sample run:
            Enter weight in pounds: 140
            Enter feet: 5
            Enter inches: 10
            BMI is 20.087702275404553
            Normal
     */
    public static void ch3_6() {
        final double KILOGRAMS_PER_POUND = 0.45359237;
        final double METERS_PER_INCH = 0.0254;
        final double INCHES_IN_FOOT = 12;

        System.out.print("Enter weight in pounds: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter feet: ");
        double heightFeet = scanner.nextDouble();
        System.out.print("Enter inches: ");
        double heightInches = scanner.nextDouble();
        double height = heightFeet * INCHES_IN_FOOT + heightInches;
        double weightInKilograms = weight * KILOGRAMS_PER_POUND;
        double heightInMeters = height * METERS_PER_INCH;
        double bmi = weightInKilograms / (heightInMeters * heightInMeters);

        System.out.println("BMI is " + bmi);
        if (bmi < 18.5) System.out.println("Underweight");
        else if (bmi < 25) System.out.println("Normal");
        else if (bmi < 30) System.out.println("Overweight");
        else System.out.println("Obese");
    }

    /*
        (Financial application: monetary units) Modify Listing 2.10, ComputeChange.
        java, to display the nonzero denominations only, using singular words for single
        units such as 1 dollar and 1 penny, and plural words for more than one unit such
        as 2 dollars and 3 pennies.
     */
    public static void ch3_7() {
        System.out.print("Enter an amount in double, for example 11.56: ");
        double amount = scanner.nextDouble();

        int remainingAmount = (int) (amount * 100);

        // Find the number of one dollars
        int numberOfOneDollars = remainingAmount / 100;
        remainingAmount = remainingAmount % 100;

        // Find the number of quarters in the remaining amount
        int numberOfQuarters = remainingAmount / 25;
        remainingAmount = remainingAmount % 25;

        // Find the number of dimes in the remaining amount
        int numberOfDimes = remainingAmount / 10;
        remainingAmount = remainingAmount % 10;

        // Find the number of nickels in the remaining amount
        int numberOfNickels = remainingAmount / 5;
        remainingAmount = remainingAmount % 5;

        // Find the number of pennies in the remaining amount
        int numberOfPennies = remainingAmount;

        // Display results
        System.out.println("Your amount " + amount + " consists of");
        if (numberOfOneDollars > 0) {
            System.out.printf(" %d %s\n", numberOfOneDollars, numberOfOneDollars == 1 ? "dollar" : "dollars");
        }
        if (numberOfQuarters > 0) {
            System.out.printf(" %d %s\n", numberOfQuarters, numberOfQuarters == 1 ? "quarter" : "quarters");
        }
        if (numberOfDimes > 0) {
            System.out.printf(" %d %s\n", numberOfDimes, numberOfDimes == 1 ? "dime" : "dimes");
        }
        if (numberOfNickels > 0) {
            System.out.printf(" %d %s\n", numberOfNickels, numberOfNickels == 1 ? "nickel" : "nickels");
        }
        if (numberOfPennies > 0) {
            System.out.printf(" %d %s\n", numberOfPennies, numberOfPennies == 1 ? "penny" : "pennies");
        }
    }

    /*
        (Sort three integers) Write a program that prompts the user to enter three integers
        and display the integers in non-decreasing order.
     */
    public static void ch3_8() {
        System.out.print("Enter three integers: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a <= b && a <= c) {
            System.out.println(a);
            System.out.println(Math.min(b, c));
            System.out.println(Math.max(b, c));
        } else if (b <= a && b <= c) {
            System.out.println(b);
            System.out.println(Math.min(a, c));
            System.out.println(Math.max(a, c));
        } else {
            System.out.println(c);
            System.out.println(Math.min(a, b));
            System.out.println(Math.max(a, b));
        }
    }

    /*
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
    public static void ch3_9() {
        System.out.print("Enter the first 9 digits of an ISBN as integer: ");
        int digits = scanner.nextInt();
        List<Integer> digitsList = Arrays.stream(String.format("%09d", digits).split("")).map(Integer::parseInt).toList();
        int checksum = IntStream.range(0, digitsList.size()).reduce(0, (acc, next) -> acc + digitsList.get(next) * (next + 1)) % 11;
        System.out.printf("The ISBN-10 number is %09d%s", digits, checksum == 10 ? "X" : checksum);
    }


    /*
        (Game: addition quiz) Listing 3.3, SubtractionQuiz.java, randomly generates a
        subtraction question. Revise the program to randomly generate an addition ques-
        tion with two integers less than 100.
     */
    public static void ch3_10() {
        int number1 = (int) (Math.random() * 100);
        int number2 = (int) (Math.random() * 100);

        // 2. If number1 < number2, swap number1 with number2
        if (number1 < number2) {
            int temp = number1;
            number1 = number2;
            number2 = temp;
        }

        // 3. Prompt the student to answer "What is number1 – number2?"
        System.out.print("What is " + number1 + " + " + number2 + "? ");
        int answer = scanner.nextInt();

        // 4. Grade the answer and display the result
        if (number1 + number2 == answer) System.out.println("You are correct!");
        else {
            System.out.println("Your answer is wrong.");
            System.out.println(number1 + " + " + number2 + " should be " + (number1 + number2));
        }
    }

    /*
        (Find the number of days in a month) Write a program that prompts the user
        to enter the month and year and displays the number of days in the month. For
        example, if the user entered month 2 and year 2012, the program should display
        that February 2012 has 29 days. If the user entered month 3 and year 2015, the
        program should display that March 2015 has 31 days.
     */
    public static void ch3_11() {
        System.out.print("Enter month and year: ");
        int month = scanner.nextInt();
        int year = scanner.nextInt();
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 0;
        };
        System.out.printf("%s %d has %d days\n", getMonthName(month), year, days);
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /*
        (Palindrome integer) Write a program that prompts the user to enter a three-digit
        integer and determines whether it is a palindrome integer. An integer is palindrome
        if it reads the same from right to left and from left to right. A negative integer is
        treated the same as a positive integer. Here are sample runs of this program:
            Enter a three-digit integer: 121
            121 is a palindrome
            Enter a three-digit integer: 123
            123 is not a palindrome
     */
    public static void ch3_12() {
        System.out.print("Enter a three-digit integer: ");
        int number = scanner.nextInt();
        if (number / 100 == number % 10) {
            System.out.println(number + " is a palindrome");
        } else {
            System.out.println(number + " is not a palindrome");
        }
    }

    /*
        (Financial application: compute taxes) Listing 3.5, ComputeTax.java, gives the
        source code to compute taxes for single filers. Complete this program to compute
        taxes for all filing statuses.
     */
    public static void ch3_13() {
        // Prompt the user to enter filing status
        System.out.print("(0-single filer, 1-married jointly or " + "qualifying widow(er), 2-married separately, 3-head of " + "household) Enter the filing status: ");

        int status = scanner.nextInt();

        // Prompt the user to enter taxable income
        System.out.print("Enter the taxable income: ");
        double income = scanner.nextDouble();

        // Compute tax
        double tax = 0;
        if (status == 0) { // Compute tax for single filers
            tax = calculateTax(income, 8350, 33950, 82250, 171550, 372950);
        } else if (status == 1) { // Left as an exercise
            tax = calculateTax(income, 16700, 67900, 137050, 208850, 372950);
        } else if (status == 2) { // Compute tax for married separately
            tax = calculateTax(income, 8350, 33950, 68525, 104425, 186475);
        } else if (status == 3) { // Compute tax for head of household
            tax = calculateTax(income, 11950, 45500, 117450, 190200, 372950);
        } else {
            System.out.println("Error: invalid status");
            System.exit(1);
        }

        // Display the result
        System.out.println("Tax is " + (int) (tax * 100) / 100.0);
    }

    public static double calculateTax(double income, double m1, double m2, double m3, double m4, double m5) {
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
        (Game: heads or tails) Write a program that lets the user guess whether the flip
        of a coin results in heads or tails. The program randomly generates an integer
        0 or 1, which represents head or tail. The program prompts the user to enter a
        guess, and reports whether the guess is correct or incorrect.
     */
    public static void ch3_14() {
        System.out.println("Enter heads - 0 or tails - 1: ");
        int guess = scanner.nextInt();
        int answer = (int) (Math.random() * 2);
        if (guess == answer) {
            System.out.println("That's correct!");
        } else {
            System.out.println("That's incorrect!");
        }
    }

    /*
        (Game: lottery) Revise Listing 3.8, Lottery.java, to generate a lottery of a three-
        digit integer. The program prompts the user to enter a three-digit integer and
        determines whether the user wins according to the following rules:
        1. If the user input matches the lottery number in the exact order, the award is
        $10,000.
        2. If all digits in the user input match all digits in the lottery number, the award
        is $3,000.
        3. If one digit in the user input matches a digit in the lottery number, the award
        is $1,000.
     */
    public static void ch3_15() {
        // Generate a lottery number
        int lottery = (int) (Math.random() * 1000);
        System.out.println(lottery);
        // Prompt the user to enter a guess
        System.out.print("Enter your lottery pick (three digits): ");
        int guess = scanner.nextInt();

        // Get digits from lottery
        int lotteryDigit1 = lottery / 100;
        int lotteryDigit2 = lottery / 10 % 10;
        int lotteryDigit3 = lottery % 10;

        // Get digits from guess
        int guessDigit1 = guess / 100;
        int guessDigit2 = guess / 10 % 10;
        int guessDigit3 = guess % 10;
        System.out.println("The lottery number is " + lottery);
        System.out.printf("Guess digits: %d %d %d\n", guessDigit1, guessDigit2, guessDigit3);
        System.out.printf("Lottery digits: %d %d %d\n", lotteryDigit1, lotteryDigit2, lotteryDigit3);

        // Check the guess
        if (guess == lottery) System.out.println("Exact match: you win $10,000");
        else if ((guessDigit1 == lotteryDigit1 && guessDigit2 == lotteryDigit3 && guessDigit3 == lotteryDigit2) || (guessDigit1 == lotteryDigit2 && guessDigit2 == lotteryDigit1 && guessDigit3 == lotteryDigit3) || (guessDigit1 == lotteryDigit2 && guessDigit2 == lotteryDigit3 && guessDigit3 == lotteryDigit1) || (guessDigit1 == lotteryDigit3 && guessDigit2 == lotteryDigit1 && guessDigit3 == lotteryDigit2) || (guessDigit1 == lotteryDigit3 && guessDigit2 == lotteryDigit2 && guessDigit3 == lotteryDigit1))
            System.out.println("Match all digits: you win $3,000");
        else if (guessDigit1 == lotteryDigit1 || guessDigit1 == lotteryDigit2 || guessDigit1 == lotteryDigit3 || guessDigit2 == lotteryDigit1 || guessDigit2 == lotteryDigit2 || guessDigit2 == lotteryDigit3 || guessDigit3 == lotteryDigit1 || guessDigit3 == lotteryDigit2 || guessDigit3 == lotteryDigit3)
            System.out.println("Match one digit: you win $1,000");
        else System.out.println("Sorry, no match");
    }

    /*
        (Random point) Write a program that displays a random coordinate in a rectan-
        gle. The rectangle is centered at (0, 0) with width 100 and height 200.
     */
    public static void ch3_16() {
        int x = (int) (Math.random() * 51);
        if (Math.random() > 0.5) x *= -1;
        int y = (int) (Math.random() * 101);
        if (Math.random() > 0.5) y *= -1;
        System.out.printf("(%d, %d)\n", x, y);
    }

    /*
        (Game: scissor, rock, paper) Write a program that plays the popular scissor–
        rock–paper game. (A scissor can cut a paper, a rock can knock a scissor, and
        a paper can wrap a rock.) The program randomly generates a number 0, 1, or
        2 representing scissor, rock, and paper. The program prompts the user to enter
        a number 0, 1, or 2 and displays a message indicating whether the user or the
        computer wins, loses, or draws. Here are sample runs:
            scissor (0), rock (1), paper (2): 1
            The computer is scissor. You are rock. You won
            scissor (0), rock (1), paper (2): 2
            The computer is paper. You are paper too. It is a draw
     */
    public static void ch3_17() {
        int computer = (int) (Math.random() * 3);
        System.out.print("scissor (0), rock (1), paper (2): ");
        int player = scanner.nextInt();

        System.out.printf("The computer is %s. You are %s. ", srpGameResolve(computer), srpGameResolve(player));
        if (computer == player) {
            System.out.print("It is a draw");
        } else if (player == 0 && computer == 2 || player == 1 && computer == 0 || player == 2 && computer == 1) {
            System.out.print("You won");
        } else {
            System.out.print("Computer won");
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
        (Cost of shipping) A shipping company uses the following function to calculate
        the cost (in dollars) of shipping based on the weight of the package (in pounds).
            c(w) = d
            3.5, if 0 < w <= 1
            5.5, if 1 < w <= 3
            8.5, if 3 < w <= 10
            10.5, if 10 < w <= 20
        Write a program that prompts the user to enter the weight of the package and
        displays the shipping cost. If the weight is negative or zero, display a message
        “Invalid input.” If the weight is greater than 20, display a message “The package
        cannot be shipped.”
     */
    public static void ch3_18() {
        System.out.print("Enter the weight of the package in pounds: ");
        double weight = scanner.nextDouble();
        if (weight <= 0) System.out.println("Invalid input.");
        else if (weight > 20) {
            System.out.println("The package cannot be shipped.");
        } else {
            double shippingCost = 0;
            if (weight <= 1) shippingCost = 3.5;
            else if (weight > 1 && weight <= 3) shippingCost = 5.5;
            else if (weight > 3 && weight <= 10) shippingCost = 8.5;
            else if (weight > 10) shippingCost = 10.5;

            System.out.printf("Shipping cost is $%.2f.", shippingCost);
        }
    }

    /*
        (Compute the perimeter of a triangle) Write a program that reads three edges for
        a triangle and computes the perimeter if the input is valid. Otherwise, display
        that the input is invalid. The input is valid if the sum of every pair of two edges is
        greater than the remaining edge.
     */
    public static void ch3_19() {
        System.out.println("Enter three edges for a triangle: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        if (a + b > c && a + c > b && b + c > a) {
            System.out.printf("P: %.2f\n", a + b + c);
        } else {
            System.out.println("Invalid input");
        }
    }

    /*
        (Science: wind-chill temperature) Programming Exercise 2.17 gives a formula to
        compute the wind-chill temperature. The formula is valid for temperatures in the
        range between -58°F and 41°F and wind speed greater than or equal to 2. Write
        a program that prompts the user to enter a temperature and a wind speed. The
        program displays the wind-chill temperature if the input is valid; otherwise, it dis-
        plays a message indicating whether the temperature and/or wind speed is invalid.
     */
    public static void ch3_20() {
        System.out.print("Enter the temperature in Fahrenheit between -58°F and 41°F: ");
        double ta = scanner.nextDouble();
        System.out.print("Enter the wind speed (>= 2) in miles per hour: ");
        double v = scanner.nextDouble();
        boolean isCorrectInput = true;
        if (ta < -58 || ta > 41) {
            System.out.println("Invalid temperature!");
            isCorrectInput = false;
        }
        if (v < 2) {
            System.out.println("Invalid wind speed!");
            isCorrectInput = false;
        }
        if (isCorrectInput) {
            double twc = 35.74 + 0.6215 * ta - 35.75 * Math.pow(v, 0.16) + 0.4275 * ta * Math.pow(v, 0.16);
            System.out.printf("The wind chill index is %.5f\n", twc);
        }
    }

    /*
        (Science: day of the week) Zeller’s congruence is an algorithm developed by
        Christian Zeller to calculate the day of the week. The formula is
        h = (q + 26*(m + 1)/10 + k + k/4 + j/4 + 5*j) % 7
        where
        ■ h is the day of the week (0: Saturday, 1: Sunday, 2: Monday, 3: Tuesday, 4:
        Wednesday, 5: Thursday, and 6: Friday).
        ■ q is the day of the month.
        ■ m is the month (3: March, 4: April, ..., 12: December). January and February
        are counted as months 13 and 14 of the previous year.
        ■ j is year/100 .
        ■ k is the year of the century (i.e., year % 100).
        Note all divisions in this exercise perform an integer division. Write a program
        that prompts the user to enter a year, month, and day of the month, and displays
        the name of the day of the week. Here are some sample runs:
            Enter year: (e.g., 2012): 2015
            Enter month: 1−12: 1
            Enter the day of the month: 1−31: 25
            Day of the week is Sunday
            Enter year: (e.g., 2012): 2012
            Enter month: 1−12: 5
            Enter the day of the month: 1−31: 12
            Day of the week is Saturday
        (Hint: January and February are counted as 13 and 14 in the formula, so you need
        to convert the user input 1 to 13 and 2 to 14 for the month and change the year to
        the previous year. For example, if the user enters 1 for m and 2015 for year, m will
        be 13 and year will be 2014 used in the formula.)
     */
    public static void ch3_21() {
        System.out.print("Enter year: (e.g., 2012): ");
        int year = scanner.nextInt();
        System.out.print("Enter month: 1−12: ");
        int m = scanner.nextInt();
        System.out.print("Enter the day of the month: 1−31: ");
        int q = scanner.nextInt();

        if (m == 1 || m == 2) {
            m += 12;
            year--;
        }
        int k = year % 100;
        int j = year / 100;
        int h = (q + 26 * (m + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
        String weekday = switch (h) {
            case 0 -> "Saturday";
            case 1 -> "Sunday";
            case 2 -> "Monday";
            case 3 -> "Tuesday";
            case 4 -> "Wednesday";
            case 5 -> "Thursday";
            case 6 -> "Friday";
            default -> "Unknown";
        };
        System.out.println("Day of the week is " + weekday);
    }

    /*
        (Geometry: point in a circle?) Write a program that prompts the user to enter a
        point (x, y) and checks whether the point is within the circle centered at (0, 0)
        with radius 10. For example, (4, 5) is inside the circle and (9, 9) is outside the
        circle, as shown in Figure 3.7a.
        (Hint: A point is in the circle if its distance to (0, 0) is less than or equal to 10.
        The formula for computing the distance is sqrt((x2 - x1)^2 + (y2 - y1)^2). Test your
        program to cover all cases.) Two sample runs are shown below:
                Enter a point with two coordinates: 4 5
                Point (4.0, 5.0) is in the circle
                Enter a point with two coordinates: 9 9
                Point (9.0, 9.0) is not in the circle
     */
    public static void ch3_22() {
        final double radius = 10;
        System.out.print("Enter a point with two coordinates: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        double distanceToZero = Math.sqrt(x * x + y * y);
        if (distanceToZero <= radius)
            System.out.printf("Point (%.1f, %.1f) is in the circle\n", x, y);
        else
            System.out.printf("Point (%.1f, %.1f) is not the circle\n", x, y);
    }

    /*
        (Geometry: point in a rectangle?) Write a program that prompts the user to enter
        a point (x, y) and checks whether the point is within the rectangle centered at
        (0, 0) with width 10 and height 5. For example, (2, 2) is inside the rectangle and
        (6, 4) is outside the rectangle, as shown in Figure 3.7b. (Hint: A point is in the
        rectangle if its horizontal distance to (0, 0) is less than or equal to 10 / 2 and its
        vertical distance to (0, 0) is less than or equal to 5.0 / 2. Test your program to
        cover all cases.) Here are two sample runs:
            Enter a point with two coordinates: −4.9 2.49
            Point (−4.9, 2.49) is in the rectangle
            Enter a point with two coordinates: −5.1 −2.4
            Point (−5.1, −2.4) is not in the rectangle
     */
    public static void ch3_23() {
        final double width = 10;
        final double height = 5;
        System.out.print("Enter a point with two coordinates: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        if (Math.abs(x) <= width / 2 && Math.abs(y) <= height / 2)
            System.out.printf("Point (%.1f, %.1f) is in the rectangle\n", x, y);
        else
            System.out.printf("Point (%.1f, %.1f) is not the rectangle\n", x, y);
    }

    /*
        Game: pick a card) Write a program that simulates picking a card from a deck
        of 52 cards. Your program should display the rank (Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        Jack, Queen, King) and suit (Clubs, Diamonds, Hearts, Spades) of the card.
        Here is a sample run of the program:
        The card you picked is Jack of Hearts
     */
    public static void ch3_24() {
        int rank = (int) (Math.random() * 13);
        int suit = (int) (Math.random() * 4);

        String rankString = switch (rank) {
            case 0 -> "Ace";
            case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> Integer.toString(rank + 1);
            case 10 -> "Jack";
            case 11 -> "Queen";
            case 12 -> "King";
            default -> "Unknown";
        };

        String suitString = switch (suit) {
            case 0 -> "Clubs";
            case 1 -> "Diamonds";
            case 2 -> "Hearts";
            case 3 -> "Spades";
            default -> "Unknown";
        };

        System.out.printf("The card you picked is %s of %s\n", rankString, suitString);
    }

    /*
        (Geometry: intersecting point) Two points on line 1 are given as (x1, y1) and
        (x2, y2) and on line 2 as (x3, y3) and (x4, y4), as shown in Figure 3.8a and b.
        The intersecting point of the two lines can be found by solving the following
        linear equations:
        (y1 - y2)*x - (x1 - x2)*y = (y1 - y2)*x1 - (x1 - x2)*y1
        (y3 - y4)*x - (x3 - x4)*y = (y3 - y4)*x3 - (x3 - x4)*y3
        This linear equation can be solved using Cramer’s rule (see Programming
        Exercise 3.3). If the equation has no solutions, the two lines are parallel (see
        Figure 3.8c). Write a program that prompts the user to enter four points and dis-
        plays the intersecting point. Here are sample runs:
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 −1.0 4.0 2.0 −1.0 −2.0
            The intersecting point is at (2.88889, 1.1111)
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 −1.0 −2.0
            The two lines are parallel
     */
    public static void ch3_25() {
        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double x4 = scanner.nextDouble();
        double y4 = scanner.nextDouble();
        double a = y1 - y2;
        double b = x1 - x2;
        double c = y3 - y4;
        double d = x3 - x4;
        double e = (y1 - y2) * x1 - (x1 - x2) * y1;
        double f = (y3 - y4) * x3 - (x3 - x4) * y3;

        if (a * d - b * c == 0) {
            System.out.println("The two lines are parallel");
        } else {
            double x = (e * d - b * f) / (a * d - b * c);
            double y = (a * f - e * c) / (a * d - b * c);
            System.out.printf("The intersecting point is at (%.4f, %.4f)\n", x, y);
        }
    }

    /*
        (Use the &&, ||, and ^ operators) Write a program that prompts the user to
        enter an integer and determines whether it is divisible by 5 and 6, whether it is
        divisible by 5 or 6, and whether it is divisible by 5 or 6, but not both. Here is a
        sample run of this program:
            Enter an integer: 10
            Is 10 divisible by 5 and 6? false
            Is 10 divisible by 5 or 6? true
            Is 10 divisible by 5 or 6, but not both? true
     */
    public static void ch3_26() {
        System.out.print("Enter an integer: ");
        int x = scanner.nextInt();
        System.out.printf("Is %d divisible by 5 and 6? %b\n", x, x % 5 == 0 && x % 6 == 0);
        System.out.printf("Is %d divisible by 5 or 6? %b \n", x, x % 5 == 0 || x % 6 == 0);
        System.out.printf("Is %d divisible by 5 or 6, but not both? %b\n", x, x % 5 == 0 ^ x % 6 == 0);
    }

    /*
        (Geometry: points in triangle?) Suppose a right triangle is placed in a plane as
        shown below. The right-angle point is placed at (0, 0), and the other two points
        are placed at (200, 0) and (0, 100). Write a program that prompts the user to enter
        a point with x- and y-coordinates and determines whether the point is inside the
        triangle. Here are the sample runs:
            Enter a point’s x- and y-coordinates: 100.5 25.5
            The point is in the triangle
            Enter a point’s x- and y-coordinates: 100.5 50.5
            The point is not in the triangle
     */
    public static void ch3_27() {
        System.out.print("Enter a point’s x- and y-coordinates: ");
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        double A = area(0, 0, 0, 100, 200, 0);
        double A1 = area(0, 0, 0, 100, x, y);
        double A2 = area(0, 100, 200, 0, x, y);
        double A3 = area(200, 0, 0, 0, x, y);
        if (A1 + A2 + A3 == A) {
            System.out.println("The point is in the triangle");
        } else {
            System.out.println("The point is not in the triangle");
        }
    }

    private static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2;
    }

    /*
        (Geometry: two rectangles) Write a program that prompts the user to enter the
        center x-, y-coordinates, width, and height of two rectangles and determines
        whether the second rectangle is inside the first or overlaps with the first, as
        shown in Figure 3.9. Test your program to cover all cases.
            Enter r1’s center x-, y-coordinates, width, and height: 2.5 4 2.5 43
            Enter r2’s center x-, y-coordinates, width, and height: 1.5 5 0.5 3
            r2 is inside r1
            Enter r1’s center x-, y-coordinates, width, and height: 1 2 3 5.5
            Enter r2’s center x-, y-coordinates, width, and height: 3 4 4.5 5
            r2 overlaps r1
            Enter r1’s center x-, y-coordinates, width, and height: 1 2 3 3
            Enter r2’s center x-, y-coordinates, width, and height: 40 45 3 2
            r2 does not overlap r1
     */
    public static void ch3_28() {
        System.out.print("Enter r1’s center x-, y-coordinates, width, and height: ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double w1 = scanner.nextDouble();
        double h1 = scanner.nextDouble();
        System.out.print("Enter r2’s center x-, y-coordinates, width, and height: ");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double w2 = scanner.nextDouble();
        double h2 = scanner.nextDouble();

        double x1max = x1 + w1 / 2;
        double x1min = x1 - w1 / 2;
        double y1max = y1 + h1 / 2;
        double y1min = y1 - h1 / 2;
        double x2max = x2 + w2 / 2;
        double x2min = x2 - w2 / 2;
        double y2max = y2 + h2 / 2;
        double y2min = y2 - h2 / 2;

        if (x1max >= x2max && x1min <= x2min && y1max >= y2max && y1min <= y2min) {
            System.out.println("r2 is inside r1");
        } else if (x2min > x1max || x2max < x1min || y2min > y1max || y2max < y1min) {
            System.out.println("r2 does not overlap r1");
        } else {
            System.out.println("r2 overlaps r1");
        }
    }

    /*

     */
    public static void ch3_29() {
    }

    /*

     */
    public static void ch3_30() {
    }

    /*

     */
    public static void ch3_31() {
    }

    /*

     */
    public static void ch3_32() {
    }

    /*

     */
    public static void ch3_33() {
    }

    /*

     */
    public static void ch3_34() {
    }
}
