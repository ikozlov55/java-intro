package exercises.ch4;

import java.util.Locale;
import java.util.Scanner;

public class Chapter4 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Geometry: area of a pentagon) Write a program that prompts the user to enter
        the length from the center of a pentagon to a vertex and computes the area of the
        pentagon, as shown in the following figure.
        The formula for computing the area of a pentagon is Area =
        5 * s^2/4 * tan(pi/5)
        , wheres is the length of a side. The side can be computed using the formula
        s = 2*r*sin(pi/5),
        where r is the length from the center of a pentagon to a vertex. Round up two digits
        after the decimal point. Here is a sample run:
            Enter the length from the center to a vertex: 5.5
            The area of the pentagon is 71.92
     */
    public static void ch4_1() {
        System.out.print("Enter the length from the center to a vertex: ");
        double r = scanner.nextDouble();
        double s = 2 * r * Math.sin(Math.PI / 5);
        double a = (5 * s * s) / (4 * Math.tan(Math.PI / 5));
        System.out.printf("The area of the pentagon is %.2f", a);
    }

    /*
        (Geometry: great circle distance) The great circle distance is the distance be-
        tween two points on the surface of a sphere. Let (x1, y1) and (x2, y2) be the
        geographical latitude and longitude of two points. The great circle distance be-
        tween the two points can be computed using the following formula:
        d = radius * arccos(sin (x1) * sin(x2) + cos(x1) * cos(x2) * cos(y1 - y2))
        Write a program that prompts the user to enter the latitude and longitude of two
        points on the earth in degrees and displays its great circle distance. The average
        radius of the earth is 6,371.01 km. Note you need to convert the degrees into
        radi ans using the Math.toRadians method since the Java trigonometric meth-
        ods use radians. The latitude and longitude degrees in the formula are for north
        and west. Use negative to indicate south and east degrees. Here is a sample run:
            Enter point 1 (latitude and longitude) in degrees: 39.55 −116.25
            Enter point 2 (latitude and longitude) in degrees: 41.5 87.37
            The distance between the two points is 10691.79183231593 km
     */
    public static void ch4_2() {
        System.out.print("Enter point 1 (latitude and longitude) in degrees: ");
        double x1 = Math.toRadians(scanner.nextDouble());
        double y1 = Math.toRadians(scanner.nextDouble());
        System.out.print("Enter point 2 (latitude and longitude) in degrees: ");
        double x2 = Math.toRadians(scanner.nextDouble());
        double y2 = Math.toRadians(scanner.nextDouble());
        double d = geoDistance(x1, y1, x2, y2);

        System.out.printf("The distance between the two points is %f km\n", d);
    }

    private static double geoDistance(double x1, double y1, double x2, double y2) {
        final double EARTH_RADIUS = 6371.01;
        return EARTH_RADIUS * Math.acos(
                Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2)
        );
    }

    /*
        (Geography: estimate areas) Use the GPS locations for Atlanta, Georgia;
        Orlando, Florida; Savannah, Georgia; and Charlotte, North Carolina in the fig-
        ure in Section 4.1 to compute the estimated area enclosed by these four cities.
        (Hint: Use the formula in Programming Exercise 4.2 to compute the distance
        between two cities. Divide the polygon into two triangles and use the formula in
        Programming Exercise 2.19 to compute the area of a triangle.)
        Atlanta(33.7489954, 284.3879824)
        Charlotte (35.2270869, 280.8431267)
        Savannah(32.0835407, 281.0998342)
        Orlando (28.5383355, 281.3792365)
     */
    public static void ch4_3() {
        double atlantaToCharlotte = geoDistance(33.7489954, 284.3879824, 35.2270869, 280.8431267);
        double atlantaToOrlando = geoDistance(33.7489954, 284.3879824, 28.5383355, 281.3792365);
        double charlotteToOrlando = geoDistance(35.2270869, 280.8431267, 28.5383355, 281.3792365);
        double savannahToCharlotte = geoDistance(32.0835407, 281.0998342, 35.2270869, 280.8431267);
        double savannahToOrlando = geoDistance(32.0835407, 281.0998342, 28.5383355, 281.3792365);
        double area = triangleArea(atlantaToCharlotte, atlantaToOrlando, charlotteToOrlando)
                + triangleArea(savannahToCharlotte, savannahToOrlando, charlotteToOrlando);
        System.out.println(area);
    }

    private static double triangleArea(double side1, double side2, double side3) {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /*
        (Geometry: area of a hexagon) The area of a hexagon can be computed using the
        following formula (s is the length of a side):
        Area = (6 * s^2)/(4 * tan(pi/6))
        Write a program that prompts the user to enter the side of a hexagon and displays
        its area. Here is a sample run:
            Enter the side: 5.5
            The area of the hexagon is 78.59
     */
    public static void ch4_4() {
        System.out.print("Enter the side: ");
        double s = scanner.nextDouble();
        double area = (6 * s * s) / (4 * Math.tan(Math.PI / 6));
        System.out.printf("The area of the hexagon is %.2f\n", area);
    }


    /*
        (Geometry: area of a regular polygon) A regular polygon is an n-sided polygon
        in which all sides are of the same length and all angles have the same degree (i.e.,
        the polygon is both equilateral and equiangular). The formula for computing the
        area of a regular polygon is
        Area = (n * s^2)/(4 * tan(pi/n))
        Here, s is the length of a side. Write a program that prompts the user to enter the
        number of sides and their length of a regular polygon and displays its area. Here
        is a sample run:
            Enter the number of sides: 5
            Enter the side: 6.5
            The area of the polygon is 72.69017017488385
     */
    public static void ch4_5() {
        System.out.print("Enter the number of sides: ");
        double n = scanner.nextDouble();
        System.out.print("Enter the side: ");
        double s = scanner.nextDouble();
        double area = (n * s * s) / (4 * Math.tan(Math.PI / n));
        System.out.printf("The area of the polygon is %.2f\n", area);
    }

    /*
        (Random points on a circle) Write a program that generates three random points
        on a circle centered at (0, 0) with radius 40 and displays three angles in a triangle
        formed by these three points, as shown in Figure 4.4a. Display the angles in
        degrees. (Hint: Generate a random angle a in radians between 0 and 2p, as shown
        in Figure 4.4b and the point determined by this angle is rxcos (a), rxsin (a).)
     */
    public static void ch4_6() {
        final double RADIUS = 40;
        double angle = Math.random() * (2 * Math.PI);
        double x1 = RADIUS * Math.cos(angle);
        double y1 = RADIUS * Math.sin(angle);
        angle = Math.random() * (2 * Math.PI);
        double x2 = RADIUS * Math.cos(angle);
        double y2 = RADIUS * Math.sin(angle);
        angle = Math.random() * (2 * Math.PI);
        double x3 = RADIUS * Math.cos(angle);
        double y3 = RADIUS * Math.sin(angle);
        double a = distance(x1, y1, x2, y2);
        double b = distance(x2, y2, x3, y3);
        double c = distance(x3, y3, x1, y1);
        double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        double C = Math.toDegrees(Math.acos((c * c - a * a - b * b) / (-2 * a * b)));

        System.out.printf("The three angles are %f %f %f\n", A, B, C);
        System.out.println(A + B + C);
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
        (Corner point coordinates) Suppose a pentagon is centered at (0, 0) with one point
        at the 0 o’clock position, as shown in Figure 4.4c. Write a program that prompts
        the user to enter the radius of the bounding circle of a pentagon and displays the
        coordinates of the five corner points on the pentagon from p1 to p5 in this order. Use
        console format to display two digits after the decimal point. Here is a sample run:
            Enter the radius of the bounding circle: 100.52
            The coordinates of five points on the pentagon are
            (95.60, 31.06)
            (0.00, 100.52)
            (−95.60, 31.06)
            (−58.08, −81.32)
            (59.08, −81.32
     */
    public static void ch4_7() {
        System.out.print("Enter the radius of the bounding circle: ");
        double radius = scanner.nextDouble();
        System.out.println("The coordinates of five points on the pentagon are");
        double side = 2 * radius * Math.sin(Math.PI / 5);
        double angle = Math.acos((side * side - 2 * radius * radius) / (-2 * radius * radius));
        double x1 = radius * Math.cos(Math.PI / 2 - angle);
        double y1 = radius * Math.sin(Math.PI / 2 - angle);
        double x3 = radius * Math.cos(Math.PI / 2 + angle);
        double y3 = radius * Math.sin(Math.PI / 2 + angle);
        double x4 = radius * Math.cos(Math.PI / 2 + 2 * angle);
        double y4 = radius * Math.sin(Math.PI / 2 + 2 * angle);
        double x5 = radius * Math.cos(Math.PI / 2 + 3 * angle);
        double y5 = radius * Math.sin(Math.PI / 2 + 3 * angle);
        System.out.printf("(%.2f, %.2f)\n", x1, y1);
        System.out.printf("(%.2f, %.2f)\n", 0.0, radius);
        System.out.printf("(%.2f, %.2f)\n", x3, y3);
        System.out.printf("(%.2f, %.2f)\n", x4, y4);
        System.out.printf("(%.2f, %.2f)\n", x5, y5);
    }

    /*
        (Find the character of an ASCII code) Write a program that receives an ASCII code
        (an integer between 0 and 127) and displays its character. Here is a sample run:
            Enter an ASCII code: 69
            The character for ASCII code 69 is E
     */
    public static void ch4_8() {
        System.out.print("Enter an ASCII code: ");
        int c = scanner.nextInt();
        if (c < 0 || c > 127) {
            System.out.println("Wrong input");
            return;
        }
        System.out.printf("The character for ASCII code %d is %c\n", c, (char) c);
    }

    /*
        (Find the Unicode of a character) Write a program that receives a character and
        displays its Unicode. Here is a sample run:
            Enter a character: E
            The Unicode for the character E is 69
     */
    public static void ch4_9() {
        System.out.print("Enter a character: ");
        char c = scanner.nextLine().charAt(0);
        System.out.printf("The Unicode for the character %c is %d\n", c, (int) c);
    }

    /*
        (Guess birthday) Rewrite Listing 4.3, GuessBirthday.java, to prompt the user to
        enter the character Y for Yes and N for No, rather than entering 1 for Yes and 0 for
        No.
     */
    public static void ch4_10() {
        String set1 = " 1 3 5 7\n" +
                " 9 11 13 15\n" +
                "17 19 21 23\n" +
                "25 27 29 31";

        String set2 = " 2 3 6 7\n" +
                "10 11 14 15\n" +
                "18 19 22 23\n" +
                "26 27 30 31";

        String set3 = " 4 5 6 7\n" +
                "12 13 14 15\n" +
                "20 21 22 23\n" +
                "28 29 30 31";

        String set4 = " 8 9 10 11\n" +
                "12 13 14 15\n" +
                "24 25 26 27\n" +
                "28 29 30 31";

        String set5 = "16 17 18 19\n" +
                "20 21 22 23\n" +
                "24 25 26 27\n" +
                "28 29 30 31";

        int day = 0;

        // Prompt the user to answer questions
        System.out.print("Is your birthday in Set1?\n");
        System.out.print(set1);
        System.out.print("\nEnter N for No and Y for Yes: ");
        char answer = scanner.nextLine().charAt(0);

        if (answer == 'Y')
            day += 1;

        // Prompt the user to answer questions
        System.out.print("\nIs your birthday in Set2?\n");
        System.out.print(set2);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = scanner.nextLine().charAt(0);

        if (answer == 'Y') day += 2;

        // Prompt the user to answer questions
        System.out.print("\nIs your birthday in Set3?\n");
        System.out.print(set3);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = scanner.nextLine().charAt(0);

        if (answer == 'Y') day += 4;

        // Prompt the user to answer questions
        System.out.print("\nIs your birthday in Set4?\n");
        System.out.print(set4);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = scanner.nextLine().charAt(0);

        if (answer == 'Y') day += 8;

        // Prompt the user to answer questions
        System.out.print("\nIs your birthday in Set5?\n");
        System.out.print(set5);
        System.out.print("\nEnter 0 for No and 1 for Yes: ");
        answer = scanner.nextLine().charAt(0);

        if (answer == 'Y') day += 16;

        System.out.println("\nYour birthday is " + day + "!");
    }


    /*

     */
    public static void ch4_11() {

    }

    /*

     */
    public static void ch4_12() {

    }

    /*

     */
    public static void ch4_13() {

    }

    /*

     */
    public static void ch4_14() {

    }

    /*

     */
    public static void ch4_15() {

    }

    /*

     */
    public static void ch4_16() {

    }

    /*

     */
    public static void ch4_17() {

    }

    /*

     */
    public static void ch4_18() {

    }

    /*

     */
    public static void ch4_19() {

    }

    /*

     */
    public static void ch4_20() {

    }

    /*

     */
    public static void ch4_21() {

    }

    /*

     */
    public static void ch4_22() {

    }

    /*

     */
    public static void ch4_23() {

    }

    /*

     */
    public static void ch4_24() {

    }

    /*

     */
    public static void ch4_25() {

    }

    /*

     */
    public static void ch4_26() {

    }
}
