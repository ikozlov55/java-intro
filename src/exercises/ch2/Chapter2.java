package exercises.ch2;

import java.util.Scanner;

public class Chapter2 {

    /*
        (Convert Celsius to Fahrenheit) Write a program that reads a Celsius degree in a
        double value from the console, then converts it to Fahrenheit, and displays the
        result. The formula for the conversion is as follows:
            fahrenheit = (9 / 5) * celsius + 32
    */
    public static void e2_1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a degree in Celsius: ");
        double celsius = scanner.nextDouble();
        double fahrenheit = (9.0 / 5) * celsius + 32;
        System.out.printf("%.1f Celsius is %.1f Fahrenheit", celsius, fahrenheit);
    }
}
