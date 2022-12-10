package exercises.ch2;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Chapter2 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Convert Celsius to Fahrenheit) Write a program that reads a Celsius degree in a
        double value from the console, then converts it to Fahrenheit, and displays the
        result. The formula for the conversion is as follows:
            fahrenheit = (9 / 5) * celsius + 32
    */
    public static void e2_1() {
        System.out.print("Enter a degree in Celsius: ");
        double celsius = scanner.nextDouble();
        double fahrenheit = (9.0 / 5) * celsius + 32;
        System.out.printf("%.1f Celsius is %.1f Fahrenheit", celsius, fahrenheit);
    }

    /*
        (Compute the volume of a cylinder) Write a program that reads in the radius
        and length of a cylinder and computes the area and volume using the following
        formulas:
            area = radius * radius * π
            volume = area * length
        Here is a sample run:
            Enter the radius and length of a cylinder: 5.5 12
            The area is 95.0331
            The volume is 1140.4
    */
    public static void e2_2() {
        System.out.print("Enter the radius and length of a cylinder: ");
        double radius = scanner.nextDouble();
        double length = scanner.nextDouble();
        double area = radius * radius * Math.PI;
        double volume = area * length;
        System.out.printf("The area is %.4f\n", area);
        System.out.printf("The volume is %.1f\n", volume);
    }

    /*
        (Convert feet into meters) Write a program that reads a number in feet, converts it
        to meters, and displays the result. One foot is 0.305 meter. Here is a sample run:
            Enter a value for feet: 16.5
            16.5 feet is 5.0325 meters
    */
    public static void e2_3() {
        final double metersInFoot = 0.305;
        System.out.print("Enter a value for feet: ");
        double feet = scanner.nextDouble();
        double meters = feet * metersInFoot;
        System.out.printf("%.1f feet is %.4f meters\n", feet, meters);
    }

    /*
        (Convert pounds into kilograms) Write a program that converts pounds into ki-
        lograms. The program prompts the user to enter a number in pounds, converts it
        to kilograms, and displays the result. One pound is 0.454 kilogram. Here is a
        sample run:
            Enter a number in pounds: 55.5
            55.5 pounds is 25.197 kilograms
    */
    public static void e2_4() {
        System.out.print("Enter a number in pounds: ");
        final double kilogramsInPound = 0.454;
        double pounds = scanner.nextDouble();
        double kilograms = pounds * kilogramsInPound;
        System.out.printf("%.1f pounds is %.4f kilograms\n", pounds, kilograms);
    }

    /*
        (Financial application: calculate tips) Write a program that reads the subtotal
        and the gratuity rate, then computes the gratuity and total. For example, if the
        user enters 10 for subtotal and 15% for gratuity rate, the program displays $1.5
        as gratuity and $11.5 as total. Here is a sample run:
            Enter the subtotal and a gratuity rate: 10 15
            The gratuity is $1.5 and total is $11.5
    */
    public static void e2_5() {
        System.out.print("Enter the subtotal and a gratuity rate: ");
        double subtotal = scanner.nextDouble();
        double gratuityRate = scanner.nextDouble();
        double gratuity = (subtotal / 100) * gratuityRate;
        double total = subtotal + gratuity;
        System.out.printf("The gratuity is $%.1f and total is $%.1f\n", gratuity, total);
    }

    /*
        (Sum the digits in an integer) Write a program that reads an integer between 0
        and 1000 and adds all the digits in the integer. For example, if an integer is 932,
        the sum of all its digits is 14.
        Hint: Use the % operator to extract digits, and use the / operator to remove the
        extracted digit. For instance, 932 % 10 = 2 and 932 / 10 = 93.
        Here is a sample run:
            Enter a number between 0 and 1000: 999
            The sum of the digits is 27
    */
    public static void e2_6() {
        System.out.print("Enter a number between 0 and 1000: ");
        int number = scanner.nextInt();
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        System.out.println("The sum of the digits is " + sum);
    }

    /*
        (Find the number of years) Write a program that prompts the user to enter the
        minutes (e.g., 1 billion), and displays the maximum number of years and remain-
        ing days for the minutes. For simplicity, assume that a year has 365 days. Here is
        a sample run:
            Enter the number of minutes: 1000000000
            1000000000 minutes is approximately 1902 years and 214 days
    */
    public static void e2_7() {
        final int daysInYear = 365;
        System.out.print("Enter the number of minutes: ");
        int minutes = scanner.nextInt();
        int days = minutes / 60 / 24;
        int years = days / daysInYear;
        int daysRemaining = days % daysInYear;
        System.out.printf("%d minutes is approximately %d years", minutes, years);
        if (daysRemaining > 0) System.out.printf(" and %d days", daysRemaining);
        System.out.println();
    }

    /*
        (Current time) Listing 2.7, ShowCurrentTime.java, gives a program that displays
        the current time in GMT. Revise the program so it prompts the user to enter the
        time zone offset to GMT and displays the time in the specified time zone. Here is
        a sample run:
            Enter the time zone offset to GMT: -5
            The current time is 4:50:34
    */
    public static void e2_8() {
        System.out.print("Enter the time zone offset to GMT: ");
        int offset = scanner.nextInt();

        // Obtain the total milliseconds since midnight, Jan 1, 1970
        long totalMilliseconds = System.currentTimeMillis();

        // Obtain the total seconds since midnight, Jan 1, 1970
        long totalSeconds = totalMilliseconds / 1000;

        // Compute the current second in the minute in the hour
        long currentSecond = totalSeconds % 60;

        // Obtain the total minutes
        long totalMinutes = totalSeconds / 60;

        // Compute the current minute in the hour
        long currentMinute = totalMinutes % 60;

        // Obtain the total hours
        long totalHours = totalMinutes / 60 + offset;

        // Compute the current hour
        long currentHour = totalHours % 24;

        // Display results
        System.out.printf("Current time is %02d:%02d:%02d\n", currentHour, currentMinute, currentSecond);
    }

    /*
        (Physics: acceleration) Average acceleration is defined as the change of velocity
        divided by the time taken to make the change, as given by the following formula:
            a = v1 - v0
                    t
        Write a program that prompts the user to enter the starting velocity v0 in meters/
        second, the ending velocity v1 in meters/second, and the time span t in seconds,
        then displays the average acceleration. Here is a sample run:
            Enter v0, v1, and t: 5.5 50.9 4.5
            The average acceleration is 10.0889
    */
    public static void e2_9() {
        System.out.print("Enter v0, v1, and t: ");
        double v0 = scanner.nextDouble();
        double v1 = scanner.nextDouble();
        double t = scanner.nextDouble();
        double a = (v1 - v0) / t;
        System.out.printf("The average acceleration is %.4f\n", a);
    }

    /*
        (Science: calculating energy) Write a program that calculates the energy needed
        to heat water from an initial temperature to a final temperature. Your program
        should prompt the user to enter the amount of water in kilograms and the initial
        and final temperatures of the water. The formula to compute the energy is
            Q = M * (finalTemperature – initialTemperature) * 4184
        where M is the weight of water in kilograms, initial and final temperatures are in
        degrees Celsius, and energy Q is measured in joules. Here is a sample run:
            Enter the amount of water in kilograms: 55.5
            Enter the initial temperature: 3.5
            Enter the final temperature: 10.5
            The energy needed is 1625484.0
    */
    public static void e2_10() {
        System.out.print("Enter the amount of water in kilograms: ");
        double m = scanner.nextDouble();
        System.out.print("Enter the initial temperature: ");
        double initialTemperature = scanner.nextDouble();
        System.out.print("Enter the final temperature: ");
        double finalTemperature = scanner.nextDouble();
        double q = m * (finalTemperature - initialTemperature) * 4184;
        System.out.printf("The energy needed is %.1f\n", q);
    }

    /*
        (Population projection) Rewrite Programming Exercise 1.11 to prompt the user
        to enter the number of years and display the population after the number of years.
        Use the hint in Programming Exercise 1.11 for this program. Here is a sample
        run of the program:
            Enter the number of years: 5
            The population in 5 years is 325932969
    */
    public static void e2_11() {
        System.out.print("Enter the number of years: ");
        int years = scanner.nextInt();
        double population = 312_032_486;
        int secondsInYear = 365 * 24 * 60 * 60;
        for (int i : IntStream.range(0, years).toArray()) {
            population += secondsInYear / 7.0;
            population -= secondsInYear / 13.0;
            population += secondsInYear / 45.0;
        }
        System.out.printf("The population in %d years is %d\n", years, (int) population);
    }

    /*
        (Physics: finding runway length) Given an airplane’s acceleration a and take-off
        speed v, you can compute the minimum runway length needed for an airplane to
        take off using the following formula:
            length = v^2 / 2a
        Write a program that prompts the user to enter v in meters/second (m/s) and
        the acceleration a in meters/second squared (m/s2), then, displays the minimum
        runway length.
            Enter speed and acceleration: 60 3.5
            The minimum runway length for this airplane is 514.286
    */
    public static void e2_12() {
        System.out.print("Enter speed and acceleration: ");
        double v = scanner.nextDouble();
        double a = scanner.nextDouble();
        double length = Math.pow(v, 2) / (2 * a);
        System.out.printf("The minimum runway length for this airplane is %.3f\n", length);
    }

    /*
        (Financial application: compound value) Suppose you save $100 each month into
        a savings account with an annual interest rate of 5%. Thus, the monthly interest
        rate is 0.05/12 = 0.00417. After the first month, the value in the account becomes
            100 * (1 + 0.00417) = 100.417
        After the second month, the value in the account becomes
            (100 + 100.417) * (1 + 0.00417) = 201.252
        After the third month, the value in the account becomes
            (100 + 201.252) * (1 + 0.00417) = 302.507
        and so on.
        Write a program that prompts the user to enter a monthly saving amount and dis-
        plays the account value after the sixth month. (In Programming Exercise 5.30, you
        will use a loop to simplify the code and display the account value for any month.)
            Enter the monthly saving amount: 100
            After the sixth month, the account value is $608.81
     */
    public static void e2_13() {
        final int interestPercent = 5;
        final int months = 6;
        double account = 0;
        System.out.print("Enter the monthly saving amount: ");
        double monthlySaving = scanner.nextDouble();
        for (int i : IntStream.range(0, months).toArray()) {
            account = (account + monthlySaving) * (1 + interestPercent / 100.0 / 12);
        }
        System.out.printf("After the sixth month, the account value is $%.2f\n", account);
    }

    /*
        (Health application: computing BMI) Body Mass Index (BMI) is a measure of
        health on weight. It can be calculated by taking your weight in kilograms and divid-
        ing, by the square of your height in meters. Write a program that prompts the user to
        enter a weight in pounds and height in inches and displays the BMI. Note one pound
        is 0.45359237 kilograms and one inch is 0.0254 meters. Here is a sample run:
            Enter weight in pounds: 95.5
            Enter height in inches: 50
            BMI is 26.8573
     */
    public static void e2_14() {
        final double kilogramsInPound = 0.45359237;
        final double metersInInch = 0.0254;
        System.out.print("Enter weight in pounds: ");
        double weight = scanner.nextDouble() * kilogramsInPound;
        System.out.print("Enter height in inches:  ");
        double height = scanner.nextDouble() * metersInInch;
        double BMI = weight / Math.pow(height, 2);
        System.out.printf("BMI is %.4f\n", BMI);
    }

    /*
        (Geometry: distance of two points) Write a program that prompts the user to
        enter two points (x1, y1) and (x2, y2) and displays their distance. The for-
        mula for computing the distance is sqrt (x2 - x1)^2 + (y2 - y1)^2. Note you can use
        Math.pow(a, 0.5) to compute 2a. Here is a sample run:
            Enter x1 and y1: 1.5 -3.4
            Enter x2 and y2: 4 5
            The distance between the two points is 8.764131445842194
     */
    public static void e2_15() {
        System.out.print("Enter x1 and y1: ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        System.out.print("Enter x2 and y2: ");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double distance = Math.pow(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2), 0.5);
        System.out.println("The distance between the two points is " + distance);
    }

    /*
        (Geometry: area of a hexagon) Write a program that prompts the user to enter the
        side of a hexagon and displays its area. The formula for computing the area of a
        hexagon is
        Area = (3*sqrt3)/2 * s^2
        where s is the length of a side. Here is a sample run:
            Enter the length of the side: 5.5
            The area of the hexagon is 78.5918
     */
    public static void e2_16() {
        System.out.print("Enter the length of the side: ");
        double s = scanner.nextDouble();
        double area = (3 * Math.sqrt(3) / 2) * Math.pow(s, 2);
        System.out.printf("The area of the hexagon is %.4f\n", area);
    }

    /*
        (Science: wind-chill temperature) How cold is it outside? The temperature alone is
        not enough to provide the answer. Other factors including wind speed, relative hu-
        midity, and sunshine play important roles in determining coldness outside. In 2001,
        the National Weather Service (NWS) implemented the new wind-chill temperature
        to measure the coldness using temperature and wind speed. The formula is
            twc = 35.74 + 0.6215ta - 35.75v^0.16 + 0.4275tav^0.16
        where ta is the outside temperature measured in degrees Fahrenheit, v is the speed
        measured in miles per hour, and twc is the wind-chill temperature. The formula cannot
        be used for wind speeds below 2 mph or temperatures below -58°F or above 41°F.
        Write a program that prompts the user to enter a temperature between -58°F
        and 41°F and a wind speed greater than or equal to 2 then displays the wind-chill
        temperature. Use Math.pow(a, b) to compute v0.16. Here is a sample run:
            Enter the temperature in Fahrenheit between -58°F and 41°F:
            5.3
            Enter the wind speed (7 = 2) in miles per hour: 6
            The wind chill index is -5.56707
     */
    public static void e2_17() {
        System.out.print("Enter the temperature in Fahrenheit between -58°F and 41°F: ");
        double ta = scanner.nextDouble();
        System.out.print("Enter the wind speed (7 = 2) in miles per hour: ");
        double v = scanner.nextDouble();
        double twc = 35.74 + 0.6215 * ta - 35.75 * Math.pow(v, 0.16) + 0.4275 * ta * Math.pow(v, 0.16);
        System.out.printf("The wind chill index is %.5f\n", twc);
    }

    /*
        (Print a table) Write a program that displays the following table. Cast
        floating-point numbers into integers.
            a b pow(a, b)
            1 2 1
            2 3 8
            3 4 81
            4 5 1024
            5 6 15625
     */
    public static void e2_18() {
        final int steps = 5;
        System.out.println("a  b  pow(a, b)");
        for (int a : IntStream.rangeClosed(1, steps).toArray()) {
            int b = a + 1;
            System.out.printf("%-2d %-2d %-9d\n", a, b, (int) Math.pow(a, b));
        }
    }

    /*
        (Geometry: area of a triangle) Write a program that prompts the user to enter
        three points, (x1, y1), (x2, y2), and (x3, y3), of a triangle then displays
        its area. The formula for computing the area of a triangle is
            s = (side1 + side2 + side3)/2;
            area = sqrt(s(s - side1)(s - side2)(s - side3))
        Here is a sample run:
            Enter the coordinates of three points separated by spaces
            like x1 y1 x2 y2 x3 y3: 1.5 -3.4 4.6 5 9.5 -3.4
            The area of the triangle is 33.6
     */
    public static void e2_19() {
        System.out.print("Enter the coordinates of three points separated by spaces like x1 y1 x2 y2 x3 y3: ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double side1 = calcDistance(x1, y1, x2, y2);
        double side2 = calcDistance(x2, y2, x3, y3);
        double side3 = calcDistance(x3, y3, x1, y1);
        double s = (side1 + side2 + side3) / 2;
        double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        System.out.printf("The area of the triangle is %.1f", area);
    }

    private static double calcDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
        (Financial application: calculate interest) If you know the balance and the an-
        nual percentage interest rate, you can compute the interest on the next monthly
        payment using the following formula:
            interest = balance * (annualInterestRate/1200)
        Write a program that reads the balance and the annual percentage interest rate
        and displays the interest for the next month. Here is a sample run:
            Enter balance and interest rate (e.g., 3 for 3%): 1000 3.5
            The interest is 2.91667
     */
    public static void e2_20() {
        System.out.print("Enter balance and interest rate (e.g., 3 for 3%): ");
        double balance = scanner.nextDouble();
        double annualInterestRate = scanner.nextDouble();
        double interest = balance * (annualInterestRate / 1200);
        System.out.printf("The interest is %.5f\n", interest);
    }

    /*
        (Financial application: calculate future investment value) Write a program that
        reads in investment amount, annual interest rate, and number of years and dis-
        plays the future investment value using the following formula:
            futureInvestmentValue =
            investmentAmount * (1 + monthlyInterestRate)^numberOfYears*12
        For example, if you enter amount 1000, annual interest rate 3.25%, and number
        of years 1, the future investment value is 1032.98.
        Here is a sample run:
            Enter investment amount: 1000.56
            Enter annual interest rate in percentage: 4.25
            Enter number of years: 1
            Future value is $1043.92
     */
    public static void e2_21() {
        System.out.print("Enter investment amount: ");
        double investmentAmount = scanner.nextDouble();
        System.out.print("Enter annual interest rate in percentage: ");
        double annualInterestRate = scanner.nextDouble();
        double monthlyInterestRate = annualInterestRate / 1200;
        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();
        double futureInvestmentValue = investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12);
        System.out.printf("Future value is $%.2f\n", futureInvestmentValue);
    }

    /*
        (Financial application: monetary units)
        RewriteListing 2.10, ComputeChange.java, to fix the possible loss of accuracy when converting
        a double value to an int value. Enter the input as an integer whose last
        two digits represent the cents. For example, the input 1156 represents 11
        dollars and 56 cents
     */
    public static void e2_22() {
        // Receive the amount
        System.out.print("Enter an amount in int, for example 1156: ");
        int amount = scanner.nextInt();

        // Find the number of one dollars
        int numberOfOneDollars = amount / 100;
        amount = amount % 100;

        // Find the number of quarters in the remaining amount
        int numberOfQuarters = amount / 25;
        amount = amount % 25;

        // Find the number of dimes in the remaining amount
        int numberOfDimes = amount / 10;
        amount = amount % 10;

        // Find the number of nickels in the remaining amount
        int numberOfNickels = amount / 5;
        amount = amount % 5;

        // Find the number of pennies in the remaining amount
        int numberOfPennies = amount;

        // Display results
        System.out.println("Your amount " + amount + " consists of");
        System.out.println(" " + numberOfOneDollars + " dollars");
        System.out.println(" " + numberOfQuarters + " quarters ");
        System.out.println(" " + numberOfDimes + " dimes");
        System.out.println(" " + numberOfNickels + " nickels");
        System.out.println(" " + numberOfPennies + " pennies");
    }

    /*
        (Cost of driving) Write a program that prompts the user to enter the distance to
        drive, the fuel efficiency of the car in miles per gallon, and the price per gallon
        then displays the cost of the trip. Here is a sample run:
            Enter the driving distance: 900.5
            Enter miles per gallon: 25.5
            Enter price per gallon: 3.55
            The cost of driving is $125.36
     */
    public static void e2_23() {
        System.out.print("Enter the driving distance: ");
        double distance = scanner.nextDouble();
        System.out.print("Enter miles per gallon: ");
        double milesPerGallon = scanner.nextDouble();
        System.out.print("Enter price per gallon: ");
        double pricePerGallon = scanner.nextDouble();
        double cost = (distance / milesPerGallon) * pricePerGallon;
        System.out.printf("The cost of driving is $%.2f\n", cost);

    }
}
