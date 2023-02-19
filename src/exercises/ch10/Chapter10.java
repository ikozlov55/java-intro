package exercises.ch10;

import exercises.ch10.ex1.Time;
import exercises.ch10.ex10.Queue;
import exercises.ch10.ex11.Circle2D;
import exercises.ch10.ex12.Triangle2D;
import exercises.ch10.ex13.MyRectangle2D;
import exercises.ch10.ex14.MyDate;
import exercises.ch10.ex2.BMI;
import exercises.ch10.ex3.MyInteger;
import exercises.ch10.ex4.MyPoint;
import exercises.ch10.ex5.StackOfIntegers;
import exercises.ch10.ex8.Tax;
import exercises.ch10.ex9.Course;
import exercises.ch9.ex7.Account;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Chapter10 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (The Time class) Design a class named Time. The class contains:
        ■ The data fields hour, minute, and second that represent a time.
        ■ A no-arg constructor that creates a Time object for the current time. (The
        values of the data fields will represent the current time.)
        ■ A constructor that constructs a Time object with a specified elapsed time
        since midnight, January 1, 1970, in milliseconds. (The values of the data
        fields will represent this time.)
        ■ A constructor that constructs a Time object with the specified hour, minute,
        and second.
        ■ Three getter methods for the data fields hour, minute, and second,
        respectively.
        ■ A method named setTime(long elapseTime) that sets a new time for the
        object using the elapsed time. For example, if the elapsed time is 555550000
        milliseconds, the hour is 10, the minute is 19, and the second is 10.
        Draw the UML diagram for the class then implement the class. Write a
        test program that creates three Time objects (using new Time(), new
        Time(555550000), and new Time(5, 23, 55)) and displays their hour,
        minute, and second in the format hour:minute:second.
        (Hint: The first two constructors will extract the hour, minute, and second
        from the elapsed time. For the no-arg constructor, the current time can be
        obtained using System.currentTimeMillis(), as shown in Listing 2.7,
        Show CurrentTime.java. Assume the time is in GMT.)

                    Time
        ----------------------------
        -hour: int
        -minute: int
        -second: int
        ----------------------------
        +Time()
        +Time(hour: int, minute: int, second: int)
        +Time(elapsedTime: long)
        +getHour(): int
        +getMinute(): int
        +getSecond(): int
        +setTime(elapseTime: long): void
        +toString(): String
        ----------------------------
     */
    public static void ch10_1() {
        Time time1 = new Time();
        Time time2 = new Time(555550000);
        Time time3 = new Time(5, 23, 55);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
    }

    /*
        (The BMI class) Add the following new constructor in the BMI class:
        /** Construct a BMI with the specified name, age, weight, feet, and inches
        public BMI(String name, int age, double weight, double feet, double inches)
     */
    public static void ch10_2() {
        BMI bmi1 = new BMI("Kim Yang", 18, 145, 5, 10);
        System.out.println(bmi1);
        BMI bmi2 = new BMI("Susan King", 20, 215, 5, 10);
        System.out.println(bmi2);
    }

    /*
        (The MyInteger class) Design a class named MyInteger. The class contains:
        ■ An int data field named value that stores the int value represented by
        this object.
        ■ A constructor that creates a MyInteger object for the specified int value.
        ■ A getter method that returns the int value.
        ■ The methods isEven(), isOdd(), and isPrime() that return true if the
        value in this object is even, odd, or prime, respectively.
        ■ The static methods isEven(int), isOdd(int), and isPrime(int) that
        return true if the specified value is even, odd, or prime, respectively.
        ■ The static methods isEven(MyInteger), isOdd(MyInteger), and
        isPrime(MyInteger) that return true if the specified value is even, odd,
        or prime, respectively.
        ■ The methods equals(int) and equals(MyInteger) that return true if
        the value in this object is equal to the specified value.
        ■ A static method parseInt(char[]) that converts an array of numeric
        characters to an int value.
        ■ A static method parseInt(String) that converts a string into an int value.
        Draw the UML diagram for the class then implement the class. Write a client
        program that tests all methods in the class.

                   MyInteger
        ----------------------------
        -value: int
        ----------------------------
        +MyInteger(value: int)
        +getValue(): int
        +isEven(): boolean
        +isOdd(): boolean
        +isPrime(): boolean
        +isEven(value: int): boolean <<static>>
        +isOdd(value: int): boolean <<static>>
        +isPrime(value: int): boolean <<static>>
        +isEven(value: MyInteger): boolean <<static>>
        +isOdd(value: MyInteger): boolean <<static>>
        +isPrime(value: MyInteger): boolean <<static>>
        +equals(other: int): boolean
        +equals(other: MyInteger): boolean
        +parseInt(chars: char[]): MyInteger <<static>>
        +parseInt(string: String): MyInteger <<static>>
        ----------------------------
     */
    public static void ch10_3() {
        MyInteger int1 = new MyInteger(7);
        System.out.printf("int1(%d) isEven: %b\n", int1.getValue(), int1.isEven());
        System.out.printf("int1(%d) isOdd: %b\n", int1.getValue(), int1.isOdd());
        System.out.printf("int1(%d) isPrime: %b\n", int1.getValue(), int1.isPrime());
        System.out.printf("MyInteger.isEven(%d) isEven: %b\n", 2, MyInteger.isEven(2));
        System.out.printf("MyInteger.isOdd(%d) : %b\n", 3, MyInteger.isOdd(3));
        System.out.printf("MyInteger.isPrime(%d) : %b\n", 8, MyInteger.isPrime(8));
        System.out.printf("MyInteger.isEven(MyInteger(%d)) isEven: %b\n", 2, MyInteger.isEven(new MyInteger(2)));
        System.out.printf("MyInteger.isOdd(MyInteger(%d)) : %b\n", 3, MyInteger.isOdd(new MyInteger(3)));
        System.out.printf("MyInteger.isPrime(MyInteger(%d)) : %b\n", 8, MyInteger.isPrime(new MyInteger(4)));
        System.out.printf("int1(%d) equals %d: %b\n", int1.getValue(), 7, int1.equals(7));
        System.out.printf("int1(%d) equals MyInteger(%d): %b\n", int1.getValue(), 7, int1.equals(new MyInteger(7)));
        MyInteger int2 = MyInteger.parseInt(new char[]{'1', '2', '3'});
        System.out.printf("MyInteger.parseInt(new char[]{'1', '2', '3'}) = %d\n", int2.getValue());
        MyInteger int3 = MyInteger.parseInt("33");
        System.out.printf("MyInteger.parseInt(\"33\") = %d\n", int3.getValue());
    }


    /*
        (The MyPoint class) Design a class named MyPoint to represent a point with
        x- and y-coordinates. The class contains:
        ■ The data fields x and y that represent the coordinates with getter methods.
        ■ A no-arg constructor that creates a point (0, 0).
        ■ A constructor that constructs a point with specified coordinates.
        ■ A method named distance that returns the distance from this point to a
        specified point of the MyPoint type.
        ■ A method named distance that returns the distance from this point to
        another point with specified x- and y-coordinates.
        ■ A static method named distance that returns the distance from two MyPoint
        objects.
        Draw the UML diagram for the class then implement the class. Write a test
        program that creates the two points (0, 0) and (10, 30.5) and displays the dis-
        tance between them.
                MyPoint
        ----------------------------
        -x: double
        -y: double
        ----------------------------
        +MyPoint()
        +MyPoint(x: double, y: double)
        +getX(): double
        +getY(): double
        +distance(other: MyPoint): double
        +distance(x: double, y: double): double
        +distance(first: MyPoint, second: MyPoint): double <<static>>
        ----------------------------
     */
    public static void ch10_4() {
        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint(10, 30.5);
        System.out.printf("Distance between %s and %s is %.2f", p1, p2, MyPoint.distance(p1, p2));
    }

    /*
        (Display the prime factors) Write a program that prompts the user to enter a
        positive integer and displays all its smallest factors in decreasing order. For
        example, if the integer is 120, the smallest factors are displayed as 5, 3, 2, 2,
        2. Use the StackOfIntegers class to store the factors (e.g., 2, 2, 2, 3, 5) and
        retrieve and display them in reverse order.
     */
    public static void ch10_5() {
        StackOfIntegers stack = new StackOfIntegers();
        System.out.print("Enter a positive integer: ");
        int n = scanner.nextInt();
        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    stack.push(i);
                    n /= i;
                    break;
                }
            }
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /*
        (Display the prime numbers) Write a program that displays all the prime num-
        bers less than 120 in decreasing order. Use the StackOfIntegers class to
        store the prime numbers (e.g., 2, 3, 5, . . . ) and retrieve and display them in
        reverse order.
     */
    public static void ch10_6() {
        final int N = 120;
        StackOfIntegers stack = new StackOfIntegers();
        IntStream.rangeClosed(1, N)
                .filter(MyInteger::isPrime)
                .forEach(stack::push);
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /*
        (Game: ATM machine) Use the Account class created in Programming Exer-
        cise 9.7 to simulate an ATM machine. Create 10 accounts in an array with id
        0, 1, . . . , 9, and an initial balance of $100. The system prompts the user to
        enter an id. If the id is entered incorrectly, ask the user to enter a correct id.
        Once an id is accepted, the main menu is displayed as shown in the sample
        run. You can enter choice 1 for viewing the current balance, 2 for withdraw-
        ing money, 3 for depositing money, and 4 for exiting the main menu. Once
        you exit, the system will prompt for an id again. Thus, once the system starts,
        it will not stop.

        Enter an id: 4

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 1
        The balance is 100.0

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 2
        Enter an amount to withdraw: 3

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 1
        The balance is 97.0

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 3
        Enter an amount to deposit: 10

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 1
        The balance is 107.0

        Main menu
        1: check balance
        2: withdraw
        3: deposit
        4: exit
        Enter a choice: 4
        Enter an id:
     */
    public static void ch10_7() {
        final int N = 10;
        Account[] accounts = IntStream.range(0, N)
                .mapToObj(x -> new Account(x, 100))
                .toArray(Account[]::new);
        while (true) {
            System.out.print("Enter an id: ");
            int id = scanner.nextInt();
            if (id < 0 || id > N) continue;
            mainMenu:
            while (true) {
                System.out.println("Main menu");
                System.out.println("1: check balance");
                System.out.println("2: withdraw");
                System.out.println("3: deposit");
                System.out.println("4: exit");
                System.out.print("Enter a choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.printf("The balance is %.1f\n", accounts[id].getBalance());
                    }
                    case 2 -> {
                        System.out.print("Enter an amount to withdraw: ");
                        double amount = scanner.nextDouble();
                        accounts[id].withdraw(amount);
                    }
                    case 3 -> {
                        System.out.print("Enter an amount to deposit: ");
                        double amount = scanner.nextDouble();
                        accounts[id].deposit(amount);
                    }
                    case 4 -> {
                        break mainMenu;
                    }
                }
                System.out.println();
            }
        }
    }

    /*
        (Financial: the Tax class) Programming Exercise 8.12 writes a program for
        computing taxes using arrays. Design a class named Tax to contain the follow-
        ing instance data fields:
        ■ int filingStatus: One of the four tax-filing statuses: 0—single filer,
        1—married filing jointly or qualifying widow(er), 2—married filing separately,
        and 3—head of household. Use the public static constants SINGLE_FILER
        (0), MARRIED_JOINTLY_OR_QUALIFYING_WIDOW(ER) (1), MARRIED_
        SEPARATELY (2), HEAD_OF_HOUSEHOLD (3) to represent the statuses.
        ■ int[][] brackets: Stores the tax brackets for each filing status.
        ■ double[] rates: Stores the tax rates for each bracket.
        ■ double taxableIncome: Stores the taxable income.
        Provide the getter and setter methods for each data field and the getTax()
        method that returns the tax. Also, provide a no-arg constructor and the construc-
        tor Tax(filingStatus, brackets, rates, taxableIncome).
        Draw the UML diagram for the class and then implement the class. Write a test
        program that uses the Tax class to print the 2001 and 2009 tax tables for taxable
        income from $50,000 to $60,000 with intervals of $1,000 for all four statuses.
        The tax rates for the year 2009 were given in Table 3.2. The tax rates for 2001
        are shown in Table 10.1.

                   Tax
        ----------------------------
        -filingStatus: int
        -brackets: int[][]
        -rates: double[]
        -taxableIncome: double
        +SINGLE_FILER: int <<static>>
        +MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_ER: int <<static>>
        +MARRIED_SEPARATELY: int <<static>>
        +HEAD_OF_HOUSEHOLD: int <<static>>
        ----------------------------
        +Tax()
        +Tax(filingStatus: int, brackets: int[][], rates: double[], taxableIncome: double)
        +getFilingStatus(filingStatus: int): int
        +setFilingStatus(): void
        +getBrackets(brackets: int[][]): int[][]
        +setBrackets(): void
        +getRates(): double[]
        +setRates(rates: double[]): void
        +getTaxableIncome(): double
        +setTaxableIncome(taxableIncome: double): void
        +getTax(): double
        ----------------------------
     */
    public static void ch10_8() {
        double incomeFrom = 50_000;
        double incomeTo = 60_000;
        double incomeInterval = 1_000;
        int[][] brackets2001 = new int[][]{
                {27050, 65550, 136750, 297350},
                {45200, 109350, 166500, 297350},
                {22600, 54625, 83250, 148675},
                {36350, 93650, 151650, 297350}
        };
        double[] rates2001 = {0.15, 0.275, 0.305, 0.355, 0.391};

        System.out.println("2009 U.S. Federal Personal Tax Rates");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Taxable", "Single", "Married Joint", "Married", "Head of");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Income", "", "or Qualifying", "Separate", "House hold");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "", "", "Widow(er)", "", "");
        for (double income = incomeFrom; income <= incomeTo; income += incomeInterval) {
            Tax calculator = new Tax();
            calculator.setTaxableIncome(income);
            long tax1 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_ER);
            long tax2 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.MARRIED_SEPARATELY);
            long tax3 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.HEAD_OF_HOUSEHOLD);
            long tax4 = Math.round(calculator.getTax());
            System.out.printf("%-14.0f%-14d%-18d%-14d%-14d\n", income, tax1, tax2, tax3, tax4);
        }
        System.out.println();
        System.out.println("2001 U.S. Federal Personal Tax Rates");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Taxable", "Single", "Married Joint", "Married", "Head of");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Income", "", "or Qualifying", "Separate", "House hold");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "", "", "Widow(er)", "", "");
        for (double income = incomeFrom; income <= incomeTo; income += incomeInterval) {
            Tax calculator = new Tax(Tax.SINGLE_FILER, brackets2001, rates2001, income);
            long tax1 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW_ER);
            long tax2 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.MARRIED_SEPARATELY);
            long tax3 = Math.round(calculator.getTax());
            calculator.setFilingStatus(Tax.HEAD_OF_HOUSEHOLD);
            long tax4 = Math.round(calculator.getTax());
            System.out.printf("%-14.0f%-14d%-18d%-14d%-14d\n", income, tax1, tax2, tax3, tax4);
        }
    }


    /*
        (The Course class) Revise the Course class as follows:
        ■ Revise the getStudents() method to return an array whose length is the
        same as the number of students in the course. (Hint: create a new array and
        copy students to it.)
        ■ The array size is fixed in Listing 10.6. Revise the addStudent method to automat-
        ically increase the array size if there is no room to add more students. This is done
        by creating a new larger array and copying the contents of the current array to it.
        ■ Implement the dropStudent method.
        ■ Add a new method named clear() that removes all students from the course.
        Test your program using https://liveexample.pearsoncmg.com/test/Exercise10_09.txt
     */
    public static void ch10_9() {
        Course course1 = new Course("Data Structures");
        Course course2 = new Course("Database Systems");

        course1.addStudent("Peter Jones");
        course1.addStudent("Brian Smith");
        course1.addStudent("Anne Kennedy");
        course1.addStudent("Susan Kennedy");
        course1.addStudent("John Kennedy");
        course1.addStudent("Kim Johnson");
        course1.addStudent("S1");
        course1.addStudent("S2");
        course1.addStudent("S3");
        course1.addStudent("S4");
        course1.addStudent("S5");
        course1.addStudent("S6");
        course1.addStudent("S7");

        course2.addStudent("Peter Jones");
        course2.addStudent("Steve Smith");

        System.out.println("Number of students in course1: " + course1.getNumberOfStudents());
        String[] students = course1.getStudents();
        for (int i = 0; i < students.length; i++) {
            System.out.print(students[i] + (i < course1.getNumberOfStudents() - 1 ? ", " : " "));
        }


        System.out.println();
        System.out.println("Number of students in course2: " + course2.getNumberOfStudents());

        course1.dropStudent("S1");
        System.out.println("Number of students in course1: " + course1.getNumberOfStudents());
        students = course1.getStudents();
        for (int i = 0; i < course1.getNumberOfStudents(); i++) {
            System.out.print(students[i] + (i < course1.getNumberOfStudents() - 1 ? ", " : " "));
        }

        course1.clear();
        System.out.println("\nNumber of students in course1: " + course1.getNumberOfStudents());
    }

    /*
        (The Queue class) Section 10.6 gives a class for Stack. Design a class named
        Queue for storing integers. Like a stack, a queue holds elements. In a stack, the
        elements are retrieved in a last-in first-out fashion. In a queue, the elements are
        retrieved in a first-in first-out fashion. The class contains:
        ■ An int[] data field named elements that stores the int values in the queue.
        ■ A data field named size that stores the number of elements in the queue.
        ■ A constructor that creates a Queue object with default capacity 8.
        ■ The method enqueue(int v) that adds v into the queue.
        ■ The method dequeue() that removes and returns the element from the queue.
        ■ The method empty() that returns true if the queue is empty.
        ■ The method getSize() that returns the size of the queue.
        Draw an UML diagram for the class. Implement the class with the initial array size
        set to 8. The array size will be doubled once the number of the elements exceeds
        the size. After an element is removed from the beginning of the array, you need
        to shift all elements in the array one position to the left. Write a test program that
        adds 20 numbers from 1 to 20 into the queue then removes these numbers and
        displays them.

                    Queue
        ----------------------------
        -data: int[]
        -size: int
        ----------------------------
        +Queue()
        +getSize(): int
        +enqueue(v: int): void
        +dequeue(): int
        +empty(): boolean
        ----------------------------
     */
    public static void ch10_10() {
        final int N = 20;
        Queue queue = new Queue();
        System.out.println(queue);
        for (int i = 1; i <= N; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        while (!queue.empty()) {
            queue.dequeue();
            System.out.println(queue);
        }
    }

    /*
        (Geometry: the Circle2D class) Define the Circle2D class that contains:
        ■ Two double data fields named x and y that specify the center of the circle
        with getter methods.
        ■ A data field radius with a getter method.
        ■ A no-arg constructor that creates a default circle with (0, 0) for (x, y) and 1
        for radius.
        ■ A constructor that creates a circle with the specified x, y, and radius.
        ■ A method getArea() that returns the area of the circle.
        ■ A method getPerimeter() that returns the perimeter of the circle.
        ■ A method contains(double x, double y) that returns true if the
        specified point (x, y) is inside this circle (see Figure 10.21a).
        ■ A method contains(Circle2D circle) that returns true if the specified
        circle is inside this circle (see Figure 10.21b).
        ■ A method overlaps(Circle2D circle) that returns true if the specified
        circle overlaps with this circle (see Figure 10.21c).
        Draw the UML diagram for the class then implement the class. Write a test
        program that creates a Circle2D object c1 (new Circle2D(2, 2, 5.5)),
        displays its area and perimeter, and displays the result of c1.contains(3,
        3), c1.contains(new Circle2D(4, 5, 10.5)), and c1.overlaps(new
        Circle2D(3, 5, 2.3)).

                  Circle2D
        ----------------------------
        -x: double
        -y: double
        -radius: double
        ----------------------------
        +Circle2D()
        +Circle2D(x: double, y: double, radius: double)
        +getRadius(): double
        +getArea(): double
        +getPerimeter(): double
        +contains(x: double, y: double): boolean
        +contains(circle: Circle2D): boolean
        +overlaps(circle: Circle2D): boolean
        ----------------------------
     */
    public static void ch10_11() {
        Circle2D c1 = new Circle2D(2, 2, 5.5);
        Circle2D c2 = new Circle2D(4, 5, 10.5);
        Circle2D c3 = new Circle2D(3, 5, 2.3);
        System.out.println(c1);
        System.out.println("c1.contains(3, 3) is " + c1.contains(3, 3));
        System.out.println("c1.contains(new Circle2D(4, 5, 10.5)) is " + c1.contains(c2));
        System.out.println("c1.overlaps(new Circle2D(3, 5, 2.3)) is " + c1.contains(c3));
    }

    /*
        (Geometry: the Triangle2D class) Define the Triangle2D class that contains:
        ■ Three points named p1, p2, and p3 of the type MyPoint with getter and
        setter methods. MyPoint is defined in Programming Exercise 10.4.
        ■ A no-arg constructor that creates a default triangle with the points (0, 0),
        (1, 1), and (2, 5).
        ■ A constructor that creates a triangle with the specified points.
        ■ A method getArea() that returns the area of the triangle.
        ■ A method getPerimeter() that returns the perimeter of the triangle.
        ■ A method contains(MyPoint p) that returns true if the specified point
        p is inside this triangle (see Figure 10.22a).
        ■ A method contains(Triangle2D t) that returns true if the specified
        triangle is inside this triangle (see Figure 10.22b).
        ■ A method overlaps(Triangle2D t) that returns true if the specified
        triangle overlaps with this triangle (see Figure 10.22c).
        Draw the UML diagram for the class and then implement the class. Write a
        test program that creates a Triangle2D object t1 using the constructor
        new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3),
        new MyPoint(5, 3.5)), displays its area and perimeter, and displays the
        result of t1.contains(3, 3), r1.contains(new Triangle2D(new
        MyPoint(2.9, 2), new MyPoint(4, 1), MyPoint(1, 3.4))), and t1
        .overlaps(new Triangle2D(new MyPoint(2, 5.5), new MyPoint
        (4, –3), MyPoint(2, 6.5))).
        (Hint: For the formula to compute the area of a triangle, see Programming
        Exercise 2.19. To detect whether a point is inside a triangle, draw three
        dashed lines, as shown in Figure 10.23. Let Δ denote the area of a triangle.
        If ΔABp + ΔACp + ΔBCp == ΔABC, the point p is inside the triangle, as
        shown in Figure 10.23a. Otherwise, point p is not inside the triangle, as
        shown in Figure 10.23b.

                   Triangle2D
        ----------------------------
        -p1: MyPoint
        -p2: MyPoint
        -p3: MyPoint
        ----------------------------
        +MyPoint()
        +MyPoint(p1: MyPoint, p2: MyPoint, p3: MyPoint)
        +getP1(): MyPoint
        +setP1(p: MyPoint) void
        +getP2(): MyPoint
        +setP2(p: MyPoint) void
        +getP3(): MyPoint
        +setP4(p: MyPoint) void
        +getArea(): double
        +getPerimeter(): double
        +contains(p: MyPoint): boolean
        +contains(t: Triangle2D): boolean
        +overlaps(t: Triangle2D): boolean
        ----------------------------
     */
    public static void ch10_12() {
        Triangle2D t1 = new Triangle2D(new MyPoint(2.5, 2), new MyPoint(4.2, 3), new MyPoint(5, 3.5));
        Triangle2D t2 = new Triangle2D(new MyPoint(2.9, 2), new MyPoint(4, 1), new MyPoint(1, 3.4));
        Triangle2D t3 = new Triangle2D(new MyPoint(2, 5.5), new MyPoint(4, -3), new MyPoint(2, 6.5));
        System.out.printf("t1 area = %.2f\n", t1.getArea());
        System.out.printf("t2 perimeter = %.2f\n", t1.getPerimeter());
        System.out.println("t1 contains (3, 3) is " + t1.contains(new MyPoint(3, 3)));
        System.out.println("t1 contains t2 is " + t1.contains(t2));
        System.out.println("t1 overlaps t3 is " + t1.overlaps(t3));
    }

    /*
        (Geometry: the MyRectangle2D class) Define the MyRectangle2D class that
        contains:
        ■ Two double data fields named x and y that specify the center of the rectangle
        with getter and setter methods. (Assume the rectangle sides are parallel to
        x- or y-axis.)
        ■ The data fields width and height with getter and setter methods.
        ■ A no-arg constructor that creates a default rectangle with (0, 0) for (x, y) and
        1 for both width and height.
        ■ A constructor that creates a rectangle with the specified x, y, width, and
        height.
        ■ A method getArea() that returns the area of the rectangle.
        ■ A method getPerimeter() that returns the perimeter of the rectangle.
        ■ A method contains(double x, double y) that returns true if the
        specified point (x, y) is inside this rectangle (see Figure 10.24a).
        ■ A method contains(MyRectangle2D r) that returns true if the specified
        rectangle is inside this rectangle (see Figure 10.24b).
        ■ A method overlaps(MyRectangle2D r) that returns true if the specified
        rectangle overlaps with this rectangle (see Figure 10.24c).
        Draw the UML diagram for the class then implement the class. Write a test
        program that creates a MyRectangle2D object r1 (new MyRectangle2D
        (2, 2, 5.5, 4.9)), displays its area and perimeter, and displays the result
        of r1.contains(3, 3), r1.contains(new MyRectangle2D(4, 5,
        10.5, 3.2)), and r1.overlaps(new MyRectangle2D(3, 5, 2.3,
        5.4)).

                  MyRectangle2D
        ----------------------------
        -x: double
        -y: double
        -width: double
        -height: double
        ----------------------------
        +MyRectangle2D()
        +MyRectangle2D(x: double, y: double, width: double, height: double,)
        +getX(): double
        +setX(x: double): void
        +getY(): double
        +setY(y: double): void
        +getWidth(): double
        +setWidth(width: double): void
        +getHeight(): double
        +setHeight(height: double): void
        +getArea(): double
        +getPerimeter(): double
        +contains(x: double, y: double): boolean
        +contains(r: MyRectangle2D): boolean
        +overlaps(r: MyRectangle2D): boolean
        ----------------------------
     */
    public static void ch10_13() {
        MyRectangle2D r1 = new MyRectangle2D(2, 2, 5.5, 4.9);
        MyRectangle2D r2 = new MyRectangle2D(4, 5, 10.5, 3.2);
        MyRectangle2D r3 = new MyRectangle2D(3, 5, 2.3, 5.4);
        System.out.printf("r1 area = %.2f\n", r1.getArea());
        System.out.printf("r1 perimeter = %.2f\n", r1.getPerimeter());
        System.out.println("r1 contains (3, 3) is " + r1.contains(3, 3));
        System.out.println("r1 contains r2 is " + r1.contains(r2));
        System.out.println("r1 overlaps t3 is " + r1.overlaps(r3));
    }

    /*
        (The MyDate class) Design a class named MyDate. The class contains:
        ■ The data fields year, month, and day that represent a date. month is 0-based,
        i.e., 0 is for January.
        ■ A no-arg constructor that creates a MyDate object for the current date.
        ■ A constructor that constructs a MyDate object with a specified elapsed time
        since midnight, January 1, 1970, in milliseconds.
        ■ A constructor that constructs a MyDate object with the specified year, month,
        and day.
        ■ Three getter methods for the data fields year, month, and day, respectively.
        ■ A method named setDate(long elapsedTime) that sets a new date for
        the object using the elapsed time.
        Draw the UML diagram for the class then implement the class. Write a test
        program that creates two MyDate objects (using new MyDate() and new
        MyDate(34355555133101L)) and displays their year, month, and day.
        (Hint: The first two constructors will extract the year, month, and day
        from the elapsed time. For example, if the elapsed time is 561555550000
        milliseconds, the year is 1987, the month is 9, and the day is 18. You may
        use the GregorianCalendar class discussed in Programming Exercise 9.5
        to simplify coding.)

                  MyDate
        ----------------------------
        -year: int
        -month: int
        -day: int
        ----------------------------
        +MyDate()
        +MyDate(millis: long)
        +MyDate(year: int, month: int, day: int)
        +getYear(): int
        +getMonth(): int
        +getDay(): int
        +setDate(elapsedTime: long): void
        ----------------------------
     */
    public static void ch10_14() {
        MyDate date1 = new MyDate();
        System.out.println(date1);
        MyDate date2 = new MyDate(34355555133101L);
        System.out.println(date2);
    }

    /*
        (Geometry: the bounding rectangle) A bounding rectangle is the minimum rect-
        angle that encloses a set of points in a two-dimensional plane, as shown in
        Figure 10.24d. Write a method that returns a bounding rectangle for a set of
        points in a two-dimensional plane, as follows:
        public static MyRectangle2D getRectangle(double[][] points)
        The Rectangle2D class is defined in Programming Exercise 10.13. Write a test
        program that prompts the user to enter five points and displays the bounding
        rectangle’s center, width, and height.
        Enter five points: 1.0 2.5 3 4 5 6 7 8 9 10
        The bounding rectangle's center (5.0, 6.25), width 8.0, height 7.5
     */
    public static void ch10_15() {
        System.out.print("Enter five points: ");
        double[][] points = new double[5][2];
        for (int i = 0; i < points.length; i++) {
            points[i][0] = scanner.nextDouble();
            points[i][1] = scanner.nextDouble();
        }
        MyRectangle2D r = getRectangle(points);
        System.out.printf(
                "The bounding rectangle's center (%.2f, %.2f), width %.1f, height %.1f\n",
                r.getX(), r.getY(), r.getWidth(), r.getHeight()
        );
    }

    public static MyRectangle2D getRectangle(double[][] points) {
        double minX = Arrays.stream(points).map(x -> x[0]).min(Double::compare).get();
        double maxX = Arrays.stream(points).map(x -> x[0]).max(Double::compare).get();
        double minY = Arrays.stream(points).map(x -> x[1]).min(Double::compare).get();
        double maxY = Arrays.stream(points).map(x -> x[1]).max(Double::compare).get();
        return new MyRectangle2D((minX + maxX) / 2, (minY + maxY) / 2, maxX - minX, maxY - minY);
    }

    /*
        (Divisible by 2 or 3) Find the first 10 numbers with 50 decimal digits that are
        divisible by 2 or 3.
     */
    public static void ch10_16() {
        final int minDigits = 50;
        final int N = 10;
        String startString = 1 + String.join("", Collections.nCopies(minDigits - 1, "0"));
        BigInteger current = new BigInteger(startString);
        BigInteger[] numbers = new BigInteger[N];
        int index = 0;
        BigInteger two = BigInteger.TWO;
        BigInteger three = BigInteger.valueOf(3);
        while (index < N) {
            if (current.remainder(two).equals(BigInteger.ZERO) && current.remainder(three).equals(BigInteger.ZERO)) {
                numbers[index] = current;
                index++;
            }
            current = current.add(BigInteger.ONE);
        }
        for (BigInteger n : numbers) {
            System.out.println(n);
        }
    }

    /*
        (Square numbers) Find the first 10 square numbers that are greater than Long.
        MAX_VALUE. A square number is a number in the form of n^2. For example, 4, 9,
        and 16 are square numbers. Find an efficient approach to run your program fast.
     */
    public static void ch10_17() {
        final int N = 10;
        BigInteger current = BigInteger.valueOf(Long.MAX_VALUE).sqrt();
        BigInteger[] numbers = new BigInteger[N];
        int index = 0;
        while (index < N) {
            current = current.add(BigInteger.ONE);
            numbers[index] = current.pow(2);
            index++;
        }
        for (BigInteger n : numbers) {
            System.out.println(n);
        }
    }

    /*
        (Large prime numbers) Write a program that finds five prime numbers larger
        than Long.MAX_VALUE.
     */
    public static void ch10_18() {
        final int N = 5;
        BigInteger current = BigInteger.valueOf(Long.MAX_VALUE);
        BigInteger[] numbers = new BigInteger[N];
        int index = 0;
        while (index < N) {
            current = current.add(BigInteger.ONE);
            if (isPrime(current)) {
                numbers[index] = current;
                index++;
            }
        }
        for (BigInteger n : numbers) {
            System.out.println(n);
        }
    }

    public static boolean isPrime(BigInteger n) {
        if (n.equals(BigInteger.ONE) || n.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        BigInteger start = BigInteger.TWO;
        BigInteger stop = n.divide(BigInteger.TWO);
        for (BigInteger i = start; i.compareTo(stop) <= 0; i = i.add(BigInteger.ONE)) {
            if (n.remainder(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }

    /*
        (Mersenne prime) A prime number is called a Mersenne prime if it can be
        written in the form 2p - 1 for some positive integer p. Write a program that
        finds all Mersenne primes with p … 100 and displays the output as shown
        below. (Hint: You have to use BigInteger to store the number because it is
        too big to be stored in long. Your program may take several hours to run.)
        p       2^p – 1
        ---------------------
        2       3
        3       7
        5       31
        ...
     */
    public static void ch10_19() {
        final int N = 100;
        System.out.printf("%-10s%-10s\n", "p", "2^p – 1");
        System.out.println("---------------------");
        for (int p = 2; p <= N; p++) {
            BigInteger mersenne = BigInteger.TWO.pow(p).subtract(BigInteger.ONE);
            if (!isPrime(mersenne)) continue;
            System.out.printf("%-10d%-10s\n", p, mersenne);
        }
    }

    /*
        (Approximate e) Programming Exercise 5.26 approximates e using the following
        series:
        e = 1 + 1/1! + 1/2! + 1/3! + 1/4! + ... + 1/i!
        In order to get better precision, use BigDecimal with 25 digits of precision
        in the computation. Write a program that displays the e value for i = 100,
        200, . . . , and 1000.
     */
    public static void ch10_20() {
        for (int i = 100; i <= 1000; i += 100) {
            System.out.println(approximateE(i));
        }
    }

    public static BigDecimal approximateE(int i) {
        final int scale = 25;
        BigDecimal e = BigDecimal.ONE;
        BigDecimal item = BigDecimal.ONE;
        for (int j = 2; j <= i; j++) {
            e = e.add(item);
            item = item.divide(BigDecimal.TWO, scale, RoundingMode.HALF_UP);
        }
        return e;
    }

    /*
        (Divisible by 5 or 6) Find the first 10 numbers greater than Long.MAX_VALUE
        that are divisible by 5 or 6.
     */
    public static void ch10_21() {
        final int N = 10;
        BigInteger current = BigInteger.valueOf(Long.MAX_VALUE);
        BigInteger[] numbers = new BigInteger[N];
        int index = 0;
        BigInteger five = BigInteger.valueOf(5);
        BigInteger six = BigInteger.valueOf(6);
        while (index < N) {
            if (current.remainder(five).equals(BigInteger.ZERO) || current.remainder(six).equals(BigInteger.ZERO)) {
                numbers[index] = current;
                index++;
            }
            current = current.add(BigInteger.ONE);
        }
        for (BigInteger n : numbers) {
            System.out.println(n);
        }
    }

    /*

     */
    public static void ch10_22() {

    }

    /*

     */
    public static void ch10_23() {

    }

    /*

     */
    public static void ch10_24() {

    }

    /*

     */
    public static void ch10_25() {

    }

    /*

     */
    public static void ch10_26() {

    }

    /*

     */
    public static void ch10_27() {

    }

    /*

     */
    public static void ch10_28() {

    }

}
