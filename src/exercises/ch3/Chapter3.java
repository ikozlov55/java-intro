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
        String monthName = switch (month) {
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
        System.out.println(monthName);
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
        if (bmi < 18.5)
            System.out.println("Underweight");
        else if (bmi < 25)
            System.out.println("Normal");
        else if (bmi < 30)
            System.out.println("Overweight");
        else
            System.out.println("Obese");
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
        List<Integer> digitsList = Arrays.stream(String.format("%09d", digits).split(""))
                .map(Integer::parseInt)
                .toList();
        int checksum = IntStream.range(0, digitsList.size())
                .reduce(0, (acc, next) -> acc + digitsList.get(next) * (next + 1)) % 11;
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
        if (number1 + number2 == answer)
            System.out.println("You are correct!");
        else {
            System.out.println("Your answer is wrong.");
            System.out.println(number1 + " + " + number2 + " should be " + (number1 + number2));
        }
    }

    /*

     */
    public static void ch3_11() {
    }

    /*

     */
    public static void ch3_12() {
    }

    /*

     */
    public static void ch3_13() {
    }

    /*

     */
    public static void ch3_14() {
    }

    /*

     */
    public static void ch3_15() {
    }

    /*

     */
    public static void ch3_16() {
    }

    /*

     */
    public static void ch3_17() {
    }

    /*

     */
    public static void ch3_18() {
    }

    /*

     */
    public static void ch3_19() {
    }

    /*

     */
    public static void ch3_20() {
    }

    /*

     */
    public static void ch3_21() {
    }

    /*

     */
    public static void ch3_22() {
    }

    /*

     */
    public static void ch3_23() {
    }

    /*

     */
    public static void ch3_24() {
    }

    /*

     */
    public static void ch3_25() {
    }

    /*

     */
    public static void ch3_26() {
    }

    /*

     */
    public static void ch3_27() {
    }

    /*

     */
    public static void ch3_28() {
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
