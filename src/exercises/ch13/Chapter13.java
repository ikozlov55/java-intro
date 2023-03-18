package exercises.ch13;

import exercises.ch13.ex13.Course;
import exercises.ch13.ex15.Rational;
import exercises.ch13.ex17.Complex;
import exercises.ch13.ex8.MyStack;
import exercises.ch13.geometric.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Chapter13 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Triangle class) Design a new Triangle class that extends the abstract
        GeometricObject class. Draw the UML diagram for the classes Triangle and
        GeometricObject then implement the Triangle class. Write a test program
        that prompts the user to enter three sides of the triangle, a color, and a Boolean
        value to indicate whether the triangle is filled. The program should create a Tri-
        angle object with these sides, and set the color and filled properties using the
        input. The program should display the area, perimeter, color, and true or false to
        indicate whether it is filled or not.
     */
    public static void ch13_1() {
        System.out.print("Enter three sides of a triangle: ");
        double side1 = scanner.nextDouble();
        double side2 = scanner.nextDouble();
        double side3 = scanner.nextDouble();
        Triangle triangle = new Triangle(side1, side2, side3);
        System.out.print("Enter a color of the triangle: ");
        String color = scanner.next();
        System.out.print("Enter if the triangle is filled: ");
        boolean filled = scanner.nextBoolean();
        triangle.setColor(color);
        triangle.setFilled(filled);
        System.out.println(triangle);
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
    }

    /*
        (Shuffle ArrayList) Write the following method that shuffles an ArrayList of
        numbers:
        public static void shuffle(ArrayList<Number> list)
     */
    public static void ch13_2() {
        ArrayList<Number> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4.3);
        list.add(5.3);
        list.add(7.0);
        list.add(new BigInteger("1000"));
        list.add(new BigInteger("2000"));
        list.add(new BigInteger("5000"));
        list.add(new BigDecimal("99000"));
        shuffle(list);
        System.out.println(list);
    }

    public static void shuffle(ArrayList<Number> list) {
        for (int i = 0; i < list.size(); i++) {
            int newIndex = (int) (Math.random() * list.size());
            Number temp = list.get(newIndex);
            list.set(newIndex, list.get(i));
            list.set(i, temp);
        }
    }

    /*
        (Sort ArrayList) Write the following method that sorts an ArrayList of numbers:
        public static void sort(ArrayList<Number> list)
     */
    public static void ch13_3() {
        ArrayList<Number> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4.3);
        list.add(5.3);
        list.add(7.0);
        list.add(new BigInteger("1000"));
        list.add(new BigInteger("2000"));
        list.add(new BigInteger("5000"));
        list.add(new BigDecimal("99000"));
        shuffle(list);
        System.out.println(list);
        sort(list);
        System.out.println(list);
    }

    public static void sort(ArrayList<Number> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            Number min = list.get(i);
            int minIndex = i;
            for (int j = i; j < list.size(); j++) {
                BigDecimal current = new BigDecimal(list.get(j).toString());
                if (current.compareTo(new BigDecimal(min.toString())) < 0) {
                    min = list.get(j);
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                list.set(minIndex, list.get(i));
                list.set(i, min);
            }
        }
    }

    /*
        (Display calendars) Rewrite the PrintCalendar class in Listing 6.12 to display
        a calendar for a specified month using the Calendar and GregorianCalendar
        classes. Your program receives the month and year from the command line. For
        example:
        java Exercise13_04 5 2016
     */
    public static void ch13_4(String[] args) {
        Calendar calendar = new GregorianCalendar();
        if (args.length >= 1) {
            int month = Integer.parseInt(args[0]) - 1;
            calendar.set(Calendar.MONTH, month);
        }
        if (args.length == 2) {
            int year = Integer.parseInt(args[1]);
            calendar.set(Calendar.YEAR, year);
        }
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int numberOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        System.out.printf("%12s %d\n", monthName, calendar.get(Calendar.YEAR));
        System.out.println("−−−−−−−−−−−−−−−−−−−−−−−−−−−−−");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
        System.out.print("    ".repeat(startDay));
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            System.out.printf("%4d", i);
            if ((i + startDay) % 7 == 0) System.out.println();
        }
        System.out.println();
    }

    /*
        (Enable GeometricObject comparable) Modify the GeometricObject class to
        implement the Comparable interface and define a static max method in the Geo-
        metricObject class for finding the larger of two GeometricObject objects.
        Draw the UML diagram and implement the new GeometricObject class. Write
        a test program that uses the max method to find the larger of two circles, the larger
        of two rectangles.
     */
    public static void ch13_5() {
        Triangle t1 = new Triangle(1, 1, 2);
        Triangle t2 = new Triangle(2, 2, 2);
        Rectangle r1 = new Rectangle(2, 4);
        Rectangle r2 = new Rectangle(4, 2);
        System.out.println(GeometricObject.max(r1, r2));
        System.out.println(GeometricObject.max(t1, t2));
    }

    /*
        (The ComparableCircle class) Define a class named ComparableCircle
        that extends Circle and implements Comparable. Draw the UML diagram
        and implement the compareTo method to compare the circles on the basis of
        area. Write a test class to find the larger of two instances of ComparableCircle
        objects, and the larger between a circle and a rectangle.
     */
    public static void ch13_6() {
        ComparableCircle c1 = new ComparableCircle(5.4);
        ComparableCircle c2 = new ComparableCircle(10);
        ComparableCircle c3 = new ComparableCircle(5.4);
        Rectangle r1 = new Rectangle(12, 10);

        System.out.println(ComparableCircle.max(c1, c2));
        System.out.println(ComparableCircle.max(c1, c3));
        System.out.println(ComparableCircle.max(c1, r1));
    }

    /*
        (The Colorable interface) Design an interface named Colorable with a void
        method named howToColor(). Every class of a colorable object must imple-
        ment the Colorable interface. Design a class named Square that extends
        GeometricObject and implements Colorable. Implement howToColor to
        display the message Color all four sides. The Square class contains a
        data field side with getter and setter methods, and a constructor for constructing
        a Square with a specified side. The Square class has a private double data field
        named side with its getter and setter methods. It has a no-arg constructor to
        create a Square with side 0, and another constructor that creates a Square with
        the specified side.
        Draw a UML diagram that involves Colorable, Square, and GeometricObject.
        Write a test program that creates an array of five GeometricObjects. For each object
        in the array, display its area and invoke its howToColor method if it is colorable
     */
    public static void ch13_7() {
        GeometricObject[] array = {
                new Square(5),
                new Circle(3),
                new Circle(5.5),
                new Rectangle(2, 4),
                new Square(3.4),
        };
        for (GeometricObject object : array) {
            System.out.println(object.getClass());
            System.out.println(object.getArea());
            if (object instanceof Colorable) {
                ((Colorable) object).howToColor();
            }
            System.out.println();
        }
    }

    /*
        (Revise the MyStack class) Rewrite the MyStack class in Listing 11.10 to perform
        a deep copy of the list field
     */
    public static void ch13_8() {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2.5);
        stack.push(true);
        stack.push(new Date());

        MyStack copy = stack.clone();
        copy.push(33);
        copy.push(44);

        System.out.println(stack);
        System.out.println(copy);
    }

    /*
        (Enable Circle comparable) Rewrite the Circle class in Listing 13.2 to extend
        GeometricObject and implement the Comparable interface. Override the
        equals method in the Object class. Two Circle objects are equal if their radii
        are the same. Draw the UML diagram that involves Circle, GeometricObject,
        and Comparable.
     */
    public static void ch13_9() {
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(33);
        Circle c3 = new Circle(5);

        System.out.println(c1.compareTo(c2));
        System.out.println(c1.equals(c3));
    }

    /*
        (Enable Rectangle comparable) Rewrite the Rectangle class in Listing 13.3 to
        extend GeometricObject and implement the Comparable interface. Override
        the equals method in the Object class. Two Rectangle objects are equal if
        their areas are the same. Draw the UML diagram that involves Rectangle, Geo-
        metricObject, and Comparable.
     */
    public static void ch13_10() {
        Rectangle r1 = new Rectangle(2, 2);
        Rectangle r2 = new Rectangle(5, 5);
        Rectangle r3 = new Rectangle(2, 2);

        System.out.println(r1.compareTo(r2));
        System.out.println(r1.equals(r3));
    }

    /*
        (The Octagon class) Write a class named Octagon that extends
        GeometricObject and implements the Comparable and Cloneable inter-
        faces. Assume all eight sides of the octagon are of equal length. The area can be
        computed using the following formula:
        area = (2 + 4/sqrt(2)) * side * side
        The Octagon class has a private double data field named side with its getter and
        setter methods. The class has a no-arg constructor that creates an Octagon with
        side 0, and a constructor to create an Octagon with a specified side.
        Draw the UML diagram that involves Octagon, GeometricObject, Comparable,
        and Cloneable. Write a test program that creates an Octagon object with side
        value 5 and displays its area and perimeter. Create a new object using the clone
        method, and compare the two objects using the compareTo method.
     */
    public static void ch13_11() {
        Octagon octagon = new Octagon(5);
        System.out.println(octagon.getClass());
        System.out.println("Perimeter: " + octagon.getPerimeter());
        System.out.println("Area: " + octagon.getArea());
        Octagon clone = octagon.clone();
        System.out.println("octagon.compareTo(clone) == " + octagon.compareTo(clone));
    }

    /*
        (Sum the areas of geometric objects) Write a method that sums the areas of all the
        geometric objects in an array. The method signature is
        public static double sumArea(GeometricObject[] a)
        Write a test program that creates an array of four objects (two circles and two
        rectangles) and computes their total area using the sumArea method.
     */
    public static void ch13_12() {
        GeometricObject[] array = {
                new Circle(5),
                new Circle(3),
                new Rectangle(2, 3),
                new Rectangle(2, 2),
        };
        System.out.println(sumArea(array));
    }

    public static double sumArea(GeometricObject[] a) {
        return Arrays.stream(a)
                .map(GeometricObject::getArea)
                .reduce(0.0, Double::sum);
    }

    /*
        (Enable the Course class cloneable) Rewrite the Course class in Listing 10.6
        to add a clone method to perform a deep copy on the students field.
     */
    public static void ch13_13() {
        Course course1 = new Course("Data Structures");
        Course course2 = course1.clone();
        course1.addStudent("Peter Jones");
        course1.addStudent("Brian Smith");
        course1.addStudent("Anne Kennedy");
        course1.addStudent("Susan Kennedy");
        course1.addStudent("John Kennedy");
        course1.addStudent("Kim Johnson");

        System.out.println(course1.getNumberOfStudents());
        System.out.println(course2.getNumberOfStudents());
    }

    /*
        (Demonstrate the benefits of encapsulation) Rewrite the Rational class in List-
        ing 13.13 using a new internal representation for the numerator and denominator.
        Create an array of two integers as follows:
        private long[] r = new long[2];
        Use r[0] to represent the numerator and r[1] to represent the denominator. The
        signatures of the methods in the Rational class are not changed, so a client appli-
        cation that uses the previous Rational class can continue to use this new Ratio-
        nal class without being recompiled.
     */
    public static void ch13_14() {
        exercises.ch13.ex14.Rational r1 = new exercises.ch13.ex14.Rational(1, 123456789);
        exercises.ch13.ex14.Rational r2 = new exercises.ch13.ex14.Rational(1, 123456789);
        exercises.ch13.ex14.Rational r3 = new exercises.ch13.ex14.Rational(1, 123456789);
        System.out.println("r1 * r2 * r3 is " + r1.multiply(r2.multiply(r3)));
    }

    /*
        (Use BigInteger for the Rational class) Redesign and implement the Ratio-
        nal class in Listing 13.13 using BigInteger for the numerator and denomina-
        tor. Write a test program that prompts the user to enter two rational numbers and
        display the results as shown in the following sample run:
        Enter the first rational number: 3 454
        Enter the second rational number: 7 2389
        3/454 + 7/2389 = 10345/1084606
        3/454 – 7/2389 = 3989/1084606
        3/454 * 7/2389 = 21/1084606
        3/454 / 7/2389 = 7167/3178
        7/2389 is 0.0029300962745918793
     */
    public static void ch13_15() {
        System.out.print("Enter the first rational number: ");
        BigInteger n1 = new BigInteger(scanner.next());
        BigInteger d1 = new BigInteger(scanner.next());
        Rational r1 = new Rational(n1, d1);

        System.out.print("Enter the second rational number: ");
        BigInteger n2 = new BigInteger(scanner.next());
        BigInteger d2 = new BigInteger(scanner.next());
        Rational r2 = new Rational(n2, d2);

        System.out.printf("%s + %s = %s\n", r1, r2, r1.add(r2));
        System.out.printf("%s - %s = %s\n", r1, r2, r1.subtract(r2));
        System.out.printf("%s * %s = %s\n", r1, r2, r1.multiply(r2));
        System.out.printf("%s / %s = %s\n", r1, r2, r1.divide(r2));
        System.out.printf("%s is %f\n", r2, r2.doubleValue());
    }

    /*
        (Create a rational-number calculator) Write a program similar to Listing 7.9,
        Calculator.java. Instead of using integers, use rationals, as shown in Figure 13.10.
        You will need to use the split method in the String class, introduced in
        Section 10.10.3, Replacing and Splitting Strings, to retrieve the numerator string
        and denominator string, and convert strings into integers using the Integer
        . parseInt method
     */
    public static void ch13_16(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator operand1 operator operand2");
            System.exit(1);
        }
        String operator = args[1];
        Rational r1 = new Rational(args[0]);
        Rational r2 = new Rational(args[2]);
        Rational result = switch (operator) {
            case "+" -> r1.add(r2);
            case "-" -> r1.subtract(r2);
            case "*" -> r1.multiply(r2);
            case "/" -> r1.divide(r2);
            default -> null;
        };
        if (result == null) {
            System.out.println("Invalid operator: " + operator);
        }
        System.out.printf("%s %s %s = %s\n", r1, operator, r2, result);
    }

    /*
        (Math: The Complex class) A complex number is a number in the form a + bi,
        where a and b are real numbers and i is 2-1. The numbers a and b are known
        as the real part and imaginary part of the complex number, respectively. You can
        perform addition, subtraction, multiplication, and division for complex numbers
        using the following formulas:
        a + bi + c + di = (a + c) + (b + d)i
        a + bi - (c + di) = (a - c) + (b - d)i
        (a + bi) * (c + di) = (ac - bd) + (bc + ad)i
        (a + bi)/(c + di) = (ac + bd)/(c^2 + d^2) + (bc - ad)i/(c^2 + d^2)
        You can also obtain the absolute value for a complex number using the following
        formula:
        |a + bi| = sqrt(a^2 + b^2)
        (A complex number can be interpreted as a point on a plane by identifying the
        (a,b) values as the coordinates of the point. The absolute value of the com-
        plex number corresponds to the distance of the point to the origin, as shown
        in Figure 13.10.)
        Design a class named Complex for representing complex numbers and the
        methods add, subtract, multiply, divide, and abs for performing complex-
        number operations, and override toString method for returning a string repre-
        sentation for a complex number. The toString method returns (a + bi) as a
        string. If b is 0, it simply returns a. Your Complex class should also implement
        Cloneable and Comparable. Compare two complex numbers using their abso-
        lute values.
        Provide three constructors Complex(a, b), Complex(a), and Complex().
        Complex() creates a Complex object for number 0, and Complex(a) cre-
        ates a Complex object with 0 for b. Also provide the getRealPart() and
        getImaginaryPart() methods for returning the real part and the imaginary part
        of the complex number, respectively.
        Draw the UML class diagram and implement the class. Use the code at https://
        liveexample.pearsoncmg.com/test/Exercise13_17.txt to test your implementa-
        tion. Here is a sample run:

        Enter the first complex number: 3.5 5.5
        Enter the second complex number: -3.5 1
        (3.5 + 5.5i) + (–3.5 + 1.0i) = 0.0 + 6.5i
        (3.5 + 5.5i) – (–3.5 + 1.0i) = 7.0 + 4.5i
        (3.5 + 5.5i) * (–3.5 + 1.0i) = –17.75 + –15.75i
        (3.5 + 5.5i) / (–3.5 + 1.0i) = –0.5094 + –1.7i
        |(3.5 + 5.5i)| = 6.519202405202649
        false
        3.5
        5.5
        [–3.5 + 1.0i, 4.0 + –0.5i, 3.5 + 5.5i, 3.5 + 5.5i]
     */
    public static void ch13_17() {
        System.out.print("Enter the first complex number: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        Complex c1 = new Complex(a, b);

        System.out.print("Enter the second complex number: ");
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        Complex c2 = new Complex(c, d);

        System.out.println("(" + c1 + ")" + " + " + "(" + c2 + ")" + " = " + c1.add(c2));
        System.out.println("(" + c1 + ")" + " - " + "(" + c2 + ")" + " = " + c1.subtract(c2));
        System.out.println("(" + c1 + ")" + " * " + "(" + c2 + ")" + " = " + c1.multiply(c2));
        System.out.println("(" + c1 + ")" + " / " + "(" + c2 + ")" + " = " + c1.divide(c2));
        System.out.println("|" + c1 + "| = " + c1.abs());

        Complex c3 = c1.clone();
        System.out.println(c1 == c3);
        System.out.println(c3.getRealPart());
        System.out.println(c3.getImaginaryPart());
        Complex c4 = new Complex(4, -0.5);
        Complex[] list = {c1, c2, c3, c4};
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));
    }

    /*
        (Use the Rational class) Write a program that computes the following summa-
        tion series using the Rational class:
        1/2 + 2/3 + 3/4 + ... + 98/99 + 99/100
        You will discover that the output is incorrect because of integer overflow (too
        large). To fix this problem, see Programming Exercise 13.15.
     */
    public static void ch13_18() {
        Rational result = new Rational();
        for (int i = 1; i <= 100; i++) {
            Rational r = new Rational(BigInteger.valueOf(i), BigInteger.valueOf(i + 1));
            result = result.add(r);
        }
        System.out.println(result.doubleValue());
    }

    /*
        (Convert decimals to fractions) Write a program that prompts the user to enter a
        decimal number and displays the number in a fraction. (Hint: read the decimal
        number as a string, extract the integer part and fractional part from the string, and
        use the Rational class in Listing 13.13 to obtain a rational number for the deci-
        mal number.) Here are some sample runs:
            Enter a decimal number: 3.25
            The fraction number is 13/4

            Enter a decimal number: -0.45452
            The fraction number is –11363/25000
     */
    public static void ch13_19() {
        System.out.print("Enter a decimal number: ");
        String[] parts = scanner.next().split("\\.");

        int sign = parts[0].startsWith("-") ? -1 : 1;
        long integerPart = Long.parseLong(parts[0]);
        long fractionalPart = Long.parseLong(parts[1]);
        long base = (long) Math.pow(10, parts[1].length());
        long nominator = sign * (integerPart * base + fractionalPart);

        exercises.ch13.ex14.Rational r = new exercises.ch13.ex14.Rational(nominator, base);
        System.out.println("The fraction number is " + r);
    }

    /*
        (Algebra: solve quadratic equations) Rewrite Programming Exercise 3.1 to obtain
        imaginary roots if the determinant is less than 0 using the Complex class in
        Programming Exercise 13.17. Here are some sample runs:
            Enter a, b, c: 1 3 1
            The roots are –0.381966 and –2.61803

            Enter a, b, c: 1 2 1
            The root is –1

            Enter a, b, c: 1 2 3
            The roots are –1.0 + 1.4142i and –1.0 + –1.4142i
     */
    public static void ch13_20() {
        System.out.print("Enter a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = Math.pow(b, 2) - 4 * a * c;
        if (d >= 0) {
            double r1 = (-b + Math.sqrt(d)) / 2 * a;
            if (d == 0) {
                System.out.printf("The root is %.5f\n", r1);
                return;
            }
            double r2 = (-b - Math.sqrt(d)) / 2 * a;
            System.out.printf("The roots are %.5f and %.5f\n", r1, r2);
        } else {
            Complex r1 = new Complex(-b / 2 * a, Math.sqrt(Math.abs(d)) / 2 * a);
            Complex r2 = new Complex(-b / 2 * a, -Math.sqrt(Math.abs(d)) / 2 * a);
            System.out.printf("The roots are %s and %s\n", r1, r2);
        }


    }

    /*
        (Algebra: vertex form equations) The equation of a parabola can be expressed in
        either standard form (y = a*x^2 + b*x + c) or vertex form (y = a*(x - h)^2 + k).
        Write a program that prompts the user to enter a, b, and c as integers in standard
        form and displays h (=-b/(2*a)) and k (= (4*a*c - b^2)/(4*a)) in the vertex form.
        Display h and k as rational numbers. Here are some sample runs:
            Enter a, b, c: 1 3 1
            h is –3/2 k is –5/4

            Enter a, b, c: 2 3 4
            h is –3/4 k is 23/8
     */
    public static void ch13_21() {
        System.out.print("Enter a, b, c: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        exercises.ch13.ex14.Rational h = new exercises.ch13.ex14.Rational(-b, 2L * a);
        exercises.ch13.ex14.Rational k = new exercises.ch13.ex14.Rational(
                4L * a * c - (long) b * b,
                4L * a
        );
        System.out.printf("h is %s k is %s\n", h, k);
    }
}
