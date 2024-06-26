package exercises.ch1;


import java.util.stream.IntStream;

public class Chapter1 {
    /*
        (Display three messages) Write a program that displays Welcome to Java,
        Welcome to Computer Science, and Programming is fun
    */
    public static void e1_1() {
        System.out.println("Welcome to Java");
        System.out.println("Welcome to Computer Science");
        System.out.println("Programming is fun");
    }

    /*
        (Display five messages) Write a program that displays Welcome to Java five
        times
    */
    public static void e1_2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Welcome to Java");
        }

    }

    /*
        (Display a pattern) Write a program that displays the following pattern:
    */
    public static void e1_3() {
        System.out.println("   J    A    V     V    A    ");
        System.out.println("   J   A A    V   V    A A   ");
        System.out.println("J  J  AAAAA    V V    AAAAA  ");
        System.out.println(" JJ  A     A    V    A     A ");
    }

    /*
        (Print a table) Write a program that displays the following table:
    */
    public static void e1_4() {
        System.out.printf("%-6s %-8s %-8s\n", "a", "a^2", "a^3");
        for (int i = 1; i <= 4; i++) {
            System.out.printf("%-6d %-8d %-8d\n", i, (int) Math.pow(i, 2), (int) Math.pow(i, 3));
        }
    }

    /*
        (Compute expressions) Write a program that displays the result of
            9.5 * 4.5 - 2.5 * 3
            45.5 - 3.5
    */
    public static void e1_5() {
        double x = (9.5 * 4.5 - 2.5 * 3) / (45.5 - 3.5);
        System.out.println(x);
    }

    /*
        (Summation of a series) Write a program that displays the result of
            1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9.
     */
    public static void e1_6() {
        int x = 9;
        int sum = IntStream.rangeClosed(1, 9).reduce(Integer::sum).orElse(0);
        System.out.println(sum);
    }

    /*
        (Approximate p) p can be computed using the following formula:
    */
    public static void e1_7() {
        System.out.println(calcPi(5));
        System.out.println(calcPi(6));
        System.out.println(calcPi(10));
        System.out.println(calcPi(100));
        System.out.println(calcPi(100000));
        System.out.println(calcPi(10000000));
    }

    private static double calcPi(int steps) {
        boolean minusSign = true;
        int x = 1;
        double sum = 0;
        for (int i = 0; i < steps; i++) {
            x += 2;
            sum += minusSign ? -1.0 / x : 1.0 / x;
            minusSign = !minusSign;
        }
        return 4 * (1 + sum);
    }

    /*
        (Area and perimeter of a circle) Write a program that displays the area and
        perimeter of a circle that has a radius of 5.5 using the following formulas:
                perimeter = 2 * radius * p
                area = radius * radius * p
    */
    public static void e1_8() {
        double x = 5.5;
        System.out.println(calcCirclePerimeter(x));
        System.out.println(calcCircleArea(x));
    }

    private static double calcCirclePerimeter(double radius) {
        return 2 * radius * Math.PI;
    }

    private static double calcCircleArea(double radius) {
        return Math.pow(radius, 2) * Math.PI;
    }

    /*
        (Area and perimeter of a rectangle) Write a program that displays the area and
        perimeter of a rectangle with a width of 4.5 and a height of 7.9 using the fol-
        lowing formula:
            area = width * height
    */
    public static void e1_9() {
        System.out.println(calcRectanglePerimeter(4.5, 7.9));
    }

    private static double calcRectanglePerimeter(double width, double height) {
        return width * height;
    }

    /*
        (Average speed in miles) Assume that a runner runs 14 kilometers in 45 minutes
        and 30 seconds. Write a program that displays the average speed in miles per
        hour. (Note 1 mile is equal to 1.6 kilometers.)
    */
    public static void e1_10() {
        double kilometers = 14;
        double minutes = 45.5;
        double miles = kilometers / 1.6;
        double hours = minutes / 60;

        System.out.println(miles / hours);
    }

    /*
        (Population projection) The U.S. Census Bureau projects population based on
        the following assumptions:
            ■ One birth every 7 seconds
            ■ One death every 13 seconds
            ■ One new immigrant every 45 seconds
        Write a program to display the population for each of the next five years. Assume
        that the current population is 312,032,486, and one year has 365 days. Hint: In Java,
        if two integers perform division, the result is an integer. The fractional part is trun-
        cated. For example, 5 / 4 is 1 (not 1.25) and 10 / 4 is 2 (not 2.5). To get an accu-
        rate result with the fractional part, one of the values involved in the division must be
        a number with a decimal point. For example, 5.0 / 4 is 1.25 and 10 / 4.0 is 2.5
    */
    public static void e1_11() {
        int population = 312_032_486;
        int secondsInYear = 365 * 24 * 60 * 60;
        for (int i : IntStream.rangeClosed(1, 5).toArray()) {
            population += secondsInYear / 7.0;
            population -= secondsInYear / 13.0;
            population += secondsInYear / 45.0;
            System.out.println(population);
        }
    }

    /*
        (Average speed in kilometers) Assume that a runner runs 24 miles in 1 hour, 40
        minutes, and 35 seconds. Write a program that displays the average speed in
        kilometers per hour. (Note 1 mile is equal to 1.6 kilometers.)
    */
    public static void e1_12() {
        double miles = 24;
        double hours = (100 * 60 + 35) / 3600.0;
        double kilometers = miles * 1.6;

        System.out.println(kilometers / hours);
    }

    /*
        (Algebra: solve 2 * 2 linear equations) You can use Cramer’s rule to solve the
        following 2 * 2 system of linear equation provided that ad – bc is not 0:
            ax + by = e
            cx + dy = f
            x = ed - bf
                ad - bc
            y = af - ec
                ad - bc
        Write a program that solves the following equation and displays the value for x and
        y: (Hint: replace the symbols in the formula with numbers to compute x and y. This
        exercise can be done in Chapter 1 without using materials in later chapters.)
            3.4x + 50.2y = 44.5
            2.1x + .55y = 5.9
    */
    public static void e1_13() {
        double a = 3.4;
        double b = 50.3;
        double c = 2.1;
        double d = 0.55;
        double e = 44.5;
        double f = 5.9;
        double x = (e * d - b * f) / (a * d - b * c);
        double y = (a * f - e * c) / (a * d - b * c);
        System.out.println(x);
        System.out.println(y);
    }
}
