package exercises.ch4;

import java.util.*;
import java.util.stream.IntStream;

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
        (Decimal to hex) Write a program that prompts the user to enter an integer be-
        tween 0 and 15 and displays its corresponding hex number. For an incorrect
        input number, display invalid input. Here are some sample runs:
            Enter a decimal value (0 to 15): 11
            The hex value is B
     */
    public static void ch4_11() {
        System.out.print("Enter a decimal value (0 to 15): ");
        int input = scanner.nextInt();
        if (input < 0 || input > 15) {
            System.out.println("Invalid input");
            return;
        }
        String hex = input < 10 ? Integer.toString(input) : Character.toString((char) ('A' + input - 10));
        System.out.printf("The hex value is %s", hex);
    }

    /*
        (Hex to binary) Write a program that prompts the user to enter a hex digit and
        displays its corresponding binary number in four digits. For example, hex digit 7
        is 0111 in binary. Hex digits can be entered either in uppercase or lowercase. For
        an incorrect input, display invalid input. Here is a sample run:
            Enter a hex digit: B
            The binary value is 1011
            Enter a hex digit: G
            G is an invalid input
     */
    public static void ch4_12() {
        System.out.print("Enter a hex digit: ");
        char c = scanner.nextLine().toUpperCase().charAt(0);
        if (c > 'F') {
            System.out.printf("%c is invalid input\n", c);
            return;
        }
        int n = 'A' <= c ? c - 'A' + 10 : c - '0';

        System.out.print("The binary value is ");
        System.out.print(n / 8);
        if (n >= 8) n -= 8;
        System.out.print(n / 4);
        if (n >= 4) n -= 4;
        System.out.print(n / 2);
        System.out.print(n % 2);
    }

    /*
        (Vowel or consonant?) Write a program that prompts the user to enter a letter and
        check whether the letter is a vowel or consonant. For a nonletter input, display
        invalid input. Here is a sample run:
            Enter a letter: B
            B is a consonant
            Enter a letter: a
            a is a vowel
            Enter a letter: #
            # is an invalid input
     */
    public static void ch4_13() {
        final Set<Character> vowels = Set.of('A', 'E', 'I', 'O', 'U', 'Y');
        System.out.print("Enter a letter: ");
        char c = scanner.nextLine().charAt(0);
        if (!Character.isAlphabetic(c)) {
            System.out.printf("%c is an invalid input\n", c);
            return;
        }
        if (vowels.contains(Character.toUpperCase(c))) {
            System.out.printf("%c is a vowel\n", c);
        } else {
            System.out.printf("%c is a consonant\n", c);
        }
    }

    /*
        (Convert letter grade to number) Write a program that prompts the user to enter
        a letter grade A, B, C, D, or F and displays its corresponding numeric value 4, 3,
        2, 1, or 0. For other input, display invalid grade. Here is a sample run:
        Enter a letter grade: B
        The numeric value for grade B is 3
        Enter a letter grade: T
        T is an invalid grade
     */
    public static void ch4_14() {
        System.out.print("Enter a letter grade: ");
        char c = scanner.nextLine().charAt(0);
        int grade = switch (c) {
            case 'A' -> 4;
            case 'B' -> 3;
            case 'C' -> 2;
            case 'D' -> 1;
            case 'F' -> 0;
            default -> -1;
        };
        if (grade < 0) {
            System.out.printf("%c is an invalid grade\n", c);
            return;
        }
        System.out.printf("The numeric value for grade %c is %d\n", c, grade);
    }

    /*
        (Phone key pads) The international standard letter/number mapping found on the
        telephone is shown below:
        Write a program that prompts the user to enter a lowercase or uppercase let-
        ter and displays its corresponding number. For a nonletter input, display invalid
        input.
            Enter a letter: A
            The corresponding number is 2
            Enter a letter: a
            The corresponding number is 2
            Enter a letter: +
            + is an invalid input
     */
    public static void ch4_15() {
        System.out.print("Enter a letter: ");
        char c = scanner.nextLine().charAt(0);
        if (!Character.isAlphabetic(c)) {
            System.out.printf("%c is an invalid input\n", c);
            return;
        }
        int number = switch (Character.toLowerCase(c)) {
            case 'a', 'b', 'c' -> 2;
            case 'd', 'e', 'f' -> 3;
            case 'g', 'h', 'i' -> 4;
            case 'j', 'k', 'l' -> 5;
            case 'm', 'n', 'o' -> 6;
            case 'p', 'q', 'r', 's' -> 7;
            case 't', 'u', 'v' -> 8;
            case 'w', 'x', 'y', 'z' -> 9;
            default -> -1;
        };
        System.out.printf("The corresponding number is %d\n", number);
    }

    /*
        (Random character) Write a program that displays a random uppercase letter
        using the Math.random() method.
     */
    public static void ch4_16() {
        char letter = (char) (int) (Math.random() * ('Z' + 1 - 'A') + 'A');
        System.out.println(letter);
    }

    public static void ch4_16_test() {
        Set<Character> letters = new HashSet<>();
        for (int i : IntStream.range(0, 100).toArray()) {
            char letter = (char) (int) (Math.random() * ('Z' + 1 - 'A') + 'A');
            letters.add(letter);
        }
        System.out.println(Arrays.toString(letters.stream().sorted().toArray()));
    }

    /*
        (Days of a month) Write a program that prompts the user to enter the year and the
        first three letters of a month name (with the first letter in uppercase) and displays
        the number of days in the month. If the input for month is incorrect, display a
        message as presented in the following sample runs:
            Enter a year: 2001
            Enter a month: Jan
            Jan 2001 has 31 days

            Enter a year: 2016
            Enter a month: jan
            jan is not a correct month name
     */
    public static void ch4_17() {
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        System.out.print("Enter a month: ");
        String month = scanner.next();
        int days = switch (month) {
            case "Jan", "Mar", "May", "Jul", "Aug", "Oct", "Dec" -> 31;
            case "Apr", "Jun", "Sep", "Nov" -> 30;
            case "Feb" -> isLeapYear(year) ? 29 : 28;
            default -> -1;
        };
        if (days < 0) {
            System.out.printf("%s is not a correct month name\n", month);
        } else {
            System.out.printf("%s %d has %d days", month, year, days);
        }

    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /*
        (Student major and status) Write a program that prompts the user to enter two
        characters and displays the major and status represented in the characters. The
        first character indicates the major and the second is a number character 1, 2, 3, or
        4, which indicates whether a student is a freshman, sophomore, junior, or senior.
        Suppose that the following characters are used to denote the majors:
        M: Mathematics
        C: Computer Science
        I: Information Technology
        Here are sample runs:
            Enter two characters: M1
            Mathematics Freshman
            Enter two characters: C3
            Computer Science Junior
            Enter two characters: T3
            Invalid input
     */
    public static void ch4_18() {
        System.out.print("Enter two characters: ");
        String input = scanner.nextLine();
        if (input.length() != 2) {
            System.out.println("Invalid input");
            return;
        }
        String error = "error";
        String major = switch (input.charAt(0)) {
            case 'M' -> "Mathematics";
            case 'C' -> "Computer Science";
            case 'I' -> "Information Technology";
            default -> error;
        };
        String status = switch (input.charAt(1)) {
            case '1' -> "Freshman";
            case '2' -> "Sophomore";
            case '3' -> "Junior";
            case '4' -> "Senior";
            default -> error;
        };
        if (major.equals(error) || status.equals(error)) {
            System.out.println("Invalid input");
            return;
        }
        System.out.printf("%s %s\n", major, status);

    }

    /*
        (Business: check ISBN-10) Rewrite Programming Exercise 3.9 by entering the
        ISBN number as a string.

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
    public static void ch4_19() {
        System.out.print("Enter the first 9 digits of an ISBN: ");
        String input = scanner.nextLine();

        List<Integer> digitsList = Arrays.stream(input.split("")).map(Integer::parseInt).toList();
        int checksum = IntStream.range(0, digitsList.size()).reduce(0,
                (acc, next) -> acc + digitsList.get(next) * (next + 1)) % 11;
        System.out.printf("The ISBN-10 number is %s%s", input, checksum == 10 ? "X" : checksum);
    }

    /*
        (Process a string) Write a program that prompts the user to enter a string and
        displays its length and its first character.
     */
    public static void ch4_20() {
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        System.out.printf("String length is %d\n", input.length());
        System.out.printf("It's first character is %c\n", input.charAt(0));
    }

    /*
        (Check SSN) Write a program that prompts the user to enter a Social Security
        number in the format DDD-DD-DDDD, where D is a digit. Your program should
        check whether the input is valid. Here are sample runs:
            Enter a SSN: 232−23−5435
            232−23−5435 is a valid social security number
            Enter a SSN: 23−23−5435
            23−23−5435 is an invalid social security number
     */
    public static void ch4_21() {
        System.out.print("Enter a SSN: ");
        String input = scanner.nextLine();
        if (input.length() != 11 || !input.matches("\\d\\d\\d-\\d\\d-\\d\\d\\d\\d")) {
            System.out.printf("%s is an invalid social security number\n", input);
            return;
        }
        System.out.printf("%s is a valid social security number\n", input);
    }

    /*
        (Check substring) Write a program that prompts the user to enter two strings, and
        reports whether the second string is a substring of the first string.
            Enter string s1: ABCD
            Enter string s2: BC
            BC is a substring of ABCD
            Enter string s1: ABCD
            Enter string s2: BDC
            BDC is not a substring of ABCD
     */
    public static void ch4_22() {
        System.out.print("Enter string s1: ");
        String s1 = scanner.nextLine();
        System.out.print("Enter string s2: ");
        String s2 = scanner.nextLine();
        if (s1.contains(s2)) {
            System.out.printf("%s is a substring of %s\n", s1, s2);
        } else {
            System.out.printf("%s is not a substring of %s\n", s1, s2);
        }
    }

    /*
        (Financial application: payroll) Write a program that reads the following infor-
        mation and prints a payroll statement:
        Employee’s name (e.g., Smith)
        Number of hours worked in a week (e.g., 10)
        Hourly pay rate (e.g., 9.75)
        Federal tax withholding rate (e.g., 20%)
        State tax withholding rate (e.g., 9%)
        A sample run is as follows:
            Enter employee’s name: Smith
            Enter number of hours worked in a week: 10
            Enter hourly pay rate: 9.75
            Enter federal tax withholding rate: 0.20
            Enter state tax withholding rate: 0.09
            Employee Name: Smith
            Hours Worked: 10.0
            Pay Rate: $9.75
            Gross Pay: $97.50
            Deductions:
            Federal Withholding (20.0%): $19.50
            State Withholding (9.0%): $8.77
            Total Deduction: $28.27
            Net Pay: $69.22
     */
    public static void ch4_23() {
        System.out.print("Enter employee’s name: ");
        String name = scanner.nextLine();
        System.out.print("Enter number of hours worked in a week: ");
        double hours = scanner.nextDouble();
        System.out.print("Enter hourly pay rate: ");
        double hourlyPayRate = scanner.nextDouble();
        System.out.print("Enter federal tax withholding rate:  ");
        double federalTaxRate = scanner.nextDouble();
        System.out.print("Enter state tax withholding rate: ");
        double stateTaxRate = scanner.nextDouble();
        double grossPay = hours * hourlyPayRate;
        double federalWithholding = grossPay * federalTaxRate;
        double stateWithholding = grossPay * stateTaxRate;
        System.out.printf("Employee Name: %s\n", name);
        System.out.printf("Hours Worked: %.1f\n", hours);
        System.out.printf("Pay Rate: $%.2f\n", hourlyPayRate);
        System.out.printf("Gross Pay: $%.2f\n", grossPay);
        System.out.println("Deductions:");
        System.out.printf("Federal Withholding (%.1f%%): $%.2f\n", federalTaxRate * 100, federalWithholding);
        System.out.printf("State Withholding (%.1f%%): $%.2f\n", stateTaxRate * 100, stateWithholding);
        System.out.printf("Total Deduction: $%.2f\n", federalWithholding + stateWithholding);
        System.out.printf("Net Pay: $%.2f\n", grossPay - federalWithholding - stateWithholding);
    }

    /*
        (Order three cities) Write a program that prompts the user to enter three cities
        and displays them in ascending order. Here is a sample run:
            Enter the first city: Chicago
            Enter the second city: Los Angeles
            Enter the third city: Atlanta
            The three cities in alphabetical order are Atlanta Chicago
            Los Angeles
     */
    public static void ch4_24() {
        List<String> cities = new ArrayList<>();
        System.out.print("Enter the first city: ");
        cities.add(scanner.nextLine().strip());
        System.out.print("Enter the second city: ");
        cities.add(scanner.nextLine().strip());
        System.out.print("Enter the third city: ");
        cities.add(scanner.nextLine().strip());
        System.out.print("The three cities in alphabetical order are");
        cities.stream().sorted().forEach(x -> System.out.printf(" %s", x));
    }

    /*
        (Generate vehicle plate numbers) Assume that a vehicle plate number consists
        of three uppercase letters followed by four digits. Write a program to generate a
        plate number.
     */
    public static void ch4_25() {
        StringBuilder plateNumber = new StringBuilder();
        for (int i : IntStream.range(0, 3).toArray()) {
            plateNumber.append((char) (int) (Math.random() * ('Z' - 'A' + 1) + 'A'));
        }
        for (int i : IntStream.range(0, 4).toArray()) {
            plateNumber.append((int) (Math.random() * 10));
        }
        System.out.println(plateNumber);
    }

    /*
        (Financial application: monetary units) Rewrite Listing 2.10, ComputeChange.
        java, to fix the possible loss of accuracy when converting a float value to an int
        value. Read the input as a string such as "11.56". Your program should ex-
        tract the dollar amount before the decimal point, and the cents after the decimal
        amount using the indexOf and substring methods.
     */
    public static void ch4_26() {
        System.out.print("Enter an amount in double, for example 11.56: ");
        String input = scanner.nextLine();

        int dollars = Integer.parseInt(input.substring(0, input.indexOf('.')));
        int cents = Integer.parseInt(input.substring(input.indexOf('.') + 1));
        int amount = dollars * 100 + cents;
        int remainingAmount = amount;

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
        System.out.println(" " + numberOfOneDollars + " dollars");
        System.out.println(" " + numberOfQuarters + " quarters ");
        System.out.println(" " + numberOfDimes + " dimes");
        System.out.println(" " + numberOfNickels + " nickels");
        System.out.println(" " + numberOfPennies + " pennies");
    }
}

