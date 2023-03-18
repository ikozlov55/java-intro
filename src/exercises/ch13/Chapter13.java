package exercises.ch13;

import exercises.ch13.ex13.Course;
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

     */
    public static void ch13_14() {

    }

    /*

     */
    public static void ch13_15() {

    }

    /*

     */
    public static void ch13_16() {

    }

    /*

     */
    public static void ch13_17() {

    }

    /*

     */
    public static void ch13_18() {

    }

    /*

     */
    public static void ch13_19() {

    }

    /*

     */
    public static void ch13_20() {

    }

    /*

     */
    public static void ch13_21() {

    }
}
