package exercises.ch5;

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

     */
    public static void ch5_7() {
    }

    /*

     */
    public static void ch5_8() {
    }

    /*

     */
    public static void ch5_9() {
    }

    /*

     */
    public static void ch5_10() {
    }

    /*

     */
    public static void ch5_11() {
    }

    /*

     */
    public static void ch5_12() {
    }

    /*

     */
    public static void ch5_13() {
    }

    /*

     */
    public static void ch5_14() {
    }

    /*

     */
    public static void ch5_15() {
    }

    /*

     */
    public static void ch5_16() {
    }

    /*

     */
    public static void ch5_17() {
    }

    /*

     */
    public static void ch5_18() {
    }

    /*

     */
    public static void ch5_19() {
    }

    /*

     */
    public static void ch5_20() {
    }

    /*

     */
    public static void ch5_21() {
    }

    /*

     */
    public static void ch5_22() {
    }

    /*

     */
    public static void ch5_23() {
    }

    /*

     */
    public static void ch5_24() {
    }

    /*

     */
    public static void ch5_25() {
    }

    /*

     */
    public static void ch5_26() {
    }

    /*

     */
    public static void ch5_27() {
    }

    /*

     */
    public static void ch5_28() {
    }

    /*

     */
    public static void ch5_29() {
    }

    /*

     */
    public static void ch5_30() {
    }

    /*

     */
    public static void ch5_31() {
    }

    /*

     */
    public static void ch5_32() {
    }

    /*

     */
    public static void ch5_33() {
    }

    /*

     */
    public static void ch5_34() {
    }

    /*

     */
    public static void ch5_35() {
    }

    /*

     */
    public static void ch5_36() {
    }

    /*

     */
    public static void ch5_37() {
    }

    /*

     */
    public static void ch5_38() {
    }

    /*

     */
    public static void ch5_39() {
    }

    /*

     */
    public static void ch5_40() {
    }

    /*

     */
    public static void ch5_41() {
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
