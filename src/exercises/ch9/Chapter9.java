package exercises.ch9;

import exercises.ch9.ex1.Rectangle;
import exercises.ch9.ex10.QuadraticEquation;
import exercises.ch9.ex11.LinearEquation;
import exercises.ch9.ex13.Location;
import exercises.ch9.ex2.Stock;
import exercises.ch9.ex6.StopWatch;
import exercises.ch9.ex7.Account;
import exercises.ch9.ex8.Fan;
import exercises.ch9.ex9.RegularPolygon;
import exercises.utils.ArrayUtils;

import java.util.*;
import java.util.stream.IntStream;

public class Chapter9 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (The Rectangle class) Following the example of the Circle class in Section 9.2,
        design a class named Rectangle to represent a rectangle. The class contains:
        ■ Two double data fields named width and height that specify the width and
        height of the rectangle. The default values are 1 for both width and height.
        ■ A no-arg constructor that creates a default rectangle.
        ■ A constructor that creates a rectangle with the specified width and height.
        ■ A method named getArea() that returns the area of this rectangle.
        ■ A method named getPerimeter() that returns the perimeter.
        Draw the UML diagram for the class then implement the class. Write a test pro-
        gram that creates two Rectangle objects—one with width 4 and height 40, and
        the other with width 3.5 and height 35.9. Display the width, height, area, and
        perimeter of each rectangle in this order.
     */
    public static void ch9_1() {
        Rectangle r1 = new Rectangle(4, 40);
        Rectangle r2 = new Rectangle(3.5, 35.9);
        System.out.println(r1);
        System.out.println(r2);
    }

    /*
        (The Stock class) Following the example of the Circle class in Section 9.2,
        design a class named Stock that contains:
        ■ A string data field named symbol for the stock’s symbol.
        ■ A string data field named name for the stock’s name.
        ■ A double data field named previousClosingPrice that stores the stock
        price for the previous day.
        ■ A double data field named currentPrice that stores the stock price for the
        current time.
        ■ A constructor that creates a stock with the specified symbol and name.
        ■ A method named getChangePercent() that returns the percentage changed
        from previousClosingPrice to currentPrice.
        Draw the UML diagram for the class then implement the class. Write a test pro-
        gram that creates a Stock object with the stock symbol ORCL, the name Oracle
        Corporation, and the previous closing price of 34.5. Set a new current price to
        34.35 and display the price-change percentage.
     */
    public static void ch9_2() {
        Stock oracle = new Stock("ORCL", "Oracle");
        oracle.previousClosingPrice = 34.5;
        oracle.currentPrice = 34.5;
        System.out.println(oracle.getChangePercent());
    }

    /*
        (Use the Date class) Write a program that creates a Date object, sets its elapsed
        time to 10000, 100000, 1000000, 10000000, 100000000, 1000000000,
        10000000000, and 100000000000, and displays the date and time using the
        toString() method, respectively.
     */
    public static void ch9_3() {
        final int start = 10000;
        Date date = new Date();
        for (int i : IntStream.range(0, 8).toArray()) {
            long time = (long) (start * Math.pow(10, i));
            date.setTime(time);
            System.out.println(date);
        }
    }

    /*
        (Use the Random class) Write a program that creates a Random object with seed
        1000 and displays the first 50 random integers between 0 and 100 using the
        nextInt(100) method.
     */
    public static void ch9_4() {
        final int max = 50;
        Random generator = new Random(1000);
        for (int i = 0; i < max; i++) {
            System.out.println(generator.nextInt(100));
        }
    }

    /*
        (Use the GregorianCalendar class) Java API has the GregorianCalendar
        class in the java.util package, which you can use to obtain the year, month, and
        day of a date. The no-arg constructor constructs an instance for the current date,
        and the methods get(GregorianCalendar.YEAR), get(GregorianCalen-
        dar.MONTH), and get(GregorianCalendar.DAY_OF_MONTH) return the year,
        month, and day. Write a program to perform two tasks:
        1. Display the current year, month, and day.
        2. The GregorianCalendar class has the setTimeInMillis(long), which
        can be used to set a specified elapsed time since January 1, 1970. Set the value
        to 1234567898765L and display the year, month, and day.
     */
    public static void ch9_5() {
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.printf(
                "%d:%02d:%02d\n",
                calendar.get(GregorianCalendar.YEAR),
                calendar.get(GregorianCalendar.MONTH) + 1,
                calendar.get(GregorianCalendar.DAY_OF_MONTH)
        );
        calendar.setTimeInMillis(1234567898765L);
        System.out.printf(
                "%d:%02d:%02d\n",
                calendar.get(GregorianCalendar.YEAR),
                calendar.get(GregorianCalendar.MONTH) + 1,
                calendar.get(GregorianCalendar.DAY_OF_MONTH)
        );
    }

    /*
        (Stopwatch) Design a class named StopWatch. The class contains:
        ■ Private data fields startTime and endTime with getter methods.
        ■ A no-arg constructor that initializes startTime with the current time.
        ■ A method named start() that resets the startTime to the current time.
        ■ A method named stop() that sets the endTime to the current time.
        ■ A method named getElapsedTime() that returns the elapsed time for the
        stopwatch in milliseconds.
        Draw the UML diagram for the class then implement the class. Write a test program
        that measures the execution time of sorting 100,000 numbers using selection sort.

                  Stopwatch
        ----------------------------
        -startTime: long
        -endTime: long
        ----------------------------
        +Stopwatch()
        +getStartTime(): long
        +getEndTime(): long
        +start(): void
        +stop(): void
        +getElapsedTime(): long
        ----------------------------
     */
    public static void ch9_6() {
        StopWatch watch = new StopWatch();
        int[] array = new int[10_000_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        watch.start();
        Arrays.sort(array);
        watch.stop();
        long elapsed = watch.getElapsedTime();
        System.out.println(elapsed);
    }

    /*
        (The Account class) Design a class named Account that contains:
        ■ A private int data field named id for the account (default 0).
        ■ A private double data field named balance for the account (default 0).
        ■ A private double data field named annualInterestRate that stores the current
        interest rate (default 0). Assume that all accounts have the same interest rate.
        ■ A private Date data field named dateCreated that stores the date when the
        account was created.
        ■ A no-arg constructor that creates a default account.
        ■ A constructor that creates an account with the specified id and initial balance.
        ■ The accessor and mutator methods for id, balance, and annualInterestRate.
        ■ The accessor method for dateCreated.
        ■ A method named getMonthlyInterestRate() that returns the monthly
        interest rate.
        ■ A method named getMonthlyInterest() that returns the monthly interest.
        ■ A method named withdraw that withdraws a specified amount from the
        account.
        ■ A method named deposit that deposits a specified amount to the account.
        Draw the UML diagram for the class then implement the class. (Hint: The method
        getMonthlyInterest() is to return monthly interest, not the interest rate.
        Monthly interest is balance * monthlyInterestRate. monthlyIntere-
        stRate is annualInterestRate / 12. Note annualInterestRate is a per-
        centage, for example 4.5%. You need to divide it by 100.)
        Write a test program that creates an Account object with an account ID of 1122,
        a balance of $20,000, and an annual interest rate of 4.5%. Use the withdraw
        method to withdraw $2,500, use the deposit method to deposit $3,000, and print
        the balance, the monthly interest, and the date when this account was created.

                     Account
        ------------------------------------
        -id: int
        -balance: double
        -annualInterestRate: double
        -dateCreated: Date
        ------------------------------------
        +Account()
        +Account(id:int, balance:double)
        +getId(): int
        +setId(id: int): void
        +getBalance(): double
        +setBalance(balance: double): void
        +getAnnualInterestRate(): double
        +setAnnualInterestRate(interestRate: double): void
        +getDateCreated(): Date
        +getMonthlyInterestRate(): double
        +getMonthlyInterest(): double
        +withdraw(amount: double): void
        +deposit(amount: double): void
        ------------------------------------
     */
    public static void ch9_7() {
        Account account = new Account(1122, 20_000);
        account.setAnnualInterestRate(4.5);
        account.withdraw(2500);
        account.deposit(3000);
        System.out.println(account);
    }

    /*
        (The Fan class) Design a class named Fan to represent a fan. The class contains:
        ■ Three constants named SLOW, MEDIUM, and FAST with the values 1, 2, and 3
        to denote the fan speed.
        ■ A private int data field named speed that specifies the speed of the fan (the
        default is SLOW).
        ■ A private boolean data field named on that specifies whether the fan is on (the
        default is false).
        ■ A private double data field named radius that specifies the radius of the fan
        (the default is 5).
        ■ A string data field named color that specifies the color of the fan (the default
        is blue).
        ■ The accessor and mutator methods for all four data fields.
        ■ A no-arg constructor that creates a default fan.
        ■ A method named toString() that returns a string description for the fan. If
        the fan is on, the method returns the fan speed, color, and radius in one com-
        bined string. If the fan is not on, the method returns the fan color and radius
        along with the string “fan is off” in one combined string.
        Draw the UML diagram for the class then implement the class. Write a test program
        that creates two Fan objects. Assign maximum speed, radius 10, color yellow,
        and turn it on to the first object. Assign medium speed, radius 5, color blue, and
        turn it off to the second object. Display the objects by invoking their toString
        method
                     Fan
        ------------------------------------
        -speed: int
        -on: boolean
        -radius: double
        -color: String
        +SLOW: int
        ¯¯¯¯¯¯¯¯¯¯
        +MEDIUM: int
        ¯¯¯¯¯¯¯¯¯¯
        +FAST: int
        ¯¯¯¯¯¯¯¯¯¯
        ------------------------------------
        +Fan()
        +getSpeed(): int
        +setSpeed(speed: int): void
        +isOn(): boolean
        +setOn(on: boolean): void
        +getRadius(): double
        +setRadius(radius: double): void
        +getColor(): String
        +setColor(color: String): void
        +toString(): String
        ------------------------------------
     */
    public static void ch9_8() {
        Fan fan1 = new Fan();
        fan1.setSpeed(Fan.FAST);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        fan1.setOn(true);

        Fan fan2 = new Fan();
        fan2.setSpeed(Fan.MEDIUM);
        fan2.setRadius(5);
        fan2.setColor("blue");
        fan2.setOn(false);

        System.out.println(fan1);
        System.out.println(fan2);
    }

    /*
        (Geometry: n-sided regular polygon) In an n-sided regular polygon, all sides have
        the same length and all angles have the same degree (i.e., the polygon is both equi-
        lateral and equiangular). Design a class named RegularPolygon that contains:
        ■ A private int data field named n that defines the number of sides in the polygon
        with default value 3.
        ■ A private double data field named side that stores the length of the side with
        default value 1.
        ■ A private double data field named x that defines the x-coordinate of the poly-
        gon’s center with default value 0.
        ■ A private double data field named y that defines the y-coordinate of the poly-
        gon’s center with default value 0.
        ■ A no-arg constructor that creates a regular polygon with default values.
        ■ A constructor that creates a regular polygon with the specified number of sides
        and length of side, centered at (0, 0).
        ■ A constructor that creates a regular polygon with the specified number of sides,
        length of side, and x- and y-coordinates.
        ■ The accessor and mutator methods for all data fields.
        ■ The method getPerimeter() that returns the perimeter of the polygon.
        ■ The method getArea() that returns the area of the polygon. The formula for
        computing the area of a regular polygon is
        Area = (n * s^2)/(4 * tan(PI/n))
        Draw the UML diagram for the class then implement the class. Write a test pro-
        gram that creates three RegularPolygon objects, created using the no-arg con-
        structor, using RegularPolygon(6, 4), and using RegularPolygon(10, 4,
        5.6, 7.8). For each object, display its perimeter and area.

                     RegularPolygon
        ------------------------------------
        -n: int
        -side: double
        -x: double
        -y: double
        ------------------------------------
        +RegularPolygon()
        +RegularPolygon(n: int, side: double)
        +RegularPolygon(n: int, side: double, x: double, y: double)
        +getN(): int
        +setN(n: int): void
        +getSide(): double
        +setSide(side: double): void
        +getX(): double
        +setX(x: double): void
        +getY(): double
        +setY(y: double): void
        +getPerimeter(): double
        +getArea(): double
        ------------------------------------
     */
    public static void ch9_9() {
        RegularPolygon polygon1 = new RegularPolygon();
        RegularPolygon polygon2 = new RegularPolygon(6, 4);
        RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);
        for (RegularPolygon polygon : new RegularPolygon[]{polygon1, polygon2, polygon3}) {
            System.out.println(polygon.getPerimeter());
            System.out.println(polygon.getArea());
        }
    }

    /*
        (Algebra: quadratic equations) Design a class named QuadraticEquation for
        a quadratic equation ax^2 + bx + c = 0. The class contains:
        ■ Private data fields a, b, and c that represent three coefficients.
        ■ A constructor with the arguments for a, b, and c.
        ■ Three getter methods for a, b, and c.
        ■ A method named getDiscriminant() that returns the discriminant, which
        is b^2 - 4*a*c.
        ■ The methods named getRoot1() and getRoot2() for returning two roots
        of the equation
        r1 = (-b + sqrt(b^2 - 4*a*c))/2*a
        r2 = (-b - sqrt(b^2 - 4*a*c))/2*a
        These methods are useful only if the discriminant is nonnegative. Let these methods
        return 0 if the discriminant is negative.
        Draw the UML diagram for the class then implement the class. Write a test pro-
        gram that prompts the user to enter values for a, b, and c and displays the result
        based on the discriminant. If the discriminant is positive, display the two roots. If
        the discriminant is 0, display the one root. Otherwise, display “The equation has
        no roots.” See Programming Exercise 3.1 for sample runs.
            Enter a, b, c: 1.0 3 1
            The equation has two roots −0.381966 and −2.61803
            Enter a, b, c: 1 2.0 1
            The equation has one root −1.0
            Enter a, b, c: 1 2 3
            The equation has no real roots

                    QuadraticEquation
        ------------------------------------
        -a: double
        -b: double
        -c: double
        ------------------------------------
        +QuadraticEquation(a: double, b: double, c: double)
        +getA(): double
        +getB(): double
        +getC(): double
        +getDiscriminant(): double
        +getRoot1(): double
        +getRoot2(): double
        ------------------------------------
     */
    public static void ch9_10() {
        System.out.print("Enter a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        QuadraticEquation equation = new QuadraticEquation(a, b, c);
        double d = equation.getDiscriminant();
        double r1 = equation.getRoot1();
        if (d > 0) {
            double r2 = equation.getRoot2();
            System.out.printf("The equation has two roots %.5f and %.5f\n", r1, r2);
        } else if (d == 0) {
            System.out.printf("The equation has one root %.5f\n", r1);
        } else {
            System.out.println("The equation has no real roots");
        }
    }

    /*
        (Algebra: 2 * 2 linear equations) Design a class named LinearEquation for a
        2 * 2 system of linear equations:
            ax + by = e
            cx + dy = f
            x = (ed - bf)/(ad - bc)
            y = (af - ec)/(ad - bc)
        The class contains:
        ■ Private data fields a, b, c, d, e, and f.
        ■ A constructor with the arguments for a, b, c, d, e, and f.
        ■ Six getter methods for a, b, c, d, e, and f.
        ■ A method named isSolvable() that returns true if ad - bc is not 0.
        ■ Methods getX() and getY() that return the solution for the equation.
        Draw the UML diagram for the class then implement the class. Write a test pro-
        gram that prompts the user to enter a, b, c, d, e, and f and displays the result.
        If ad - bc is 0, report that “The equation has no solution.” See Programming
        Exercise 3.3 for sample runs.
                Enter a, b, c, d, e, f: 9.0 4.0 3.0 -5.0 -6.0 -21.0
                x is −2.0 and y is 3.0
                Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0
                The equation has no solution

                   LinearEquation
        ------------------------------------
        -a: double
        -b: double
        -c: double
        -d: double
        -e: double
        -f: double
        ------------------------------------
        +LinearEquation(a: double, b: double, c: double, d: double, e: double, f: double)
        +getA(): double
        +getB(): double
        +getC(): double
        +getD(): double
        +getE(): double
        +getF(): double
        +isSolvable(): boolean
        +getX(): double
        +getY(): double
        ------------------------------------
     */
    public static void ch9_11() {
        System.out.print("Enter a, b, c, d, e, f: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        double e = scanner.nextDouble();
        double f = scanner.nextDouble();
        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);
        if (equation.isSolvable()) {
            double x = equation.getX();
            double y = equation.getY();
            System.out.printf("x is %.1f and y is %.1f\n", x, y);
        } else {
            System.out.println("The equation has no solution.");
        }
    }

    /*
        (Geometry: intersecting point) Suppose two line segments intersect. The two end-
        points for the first line segment are (x1, y1) and (x2, y2) and for the second line
        segment are (x3, y3) and (x4, y4). Write a program that prompts the user to enter
        these four endpoints and displays the intersecting point. As discussed in Program-
        ming Exercise 3.25, the intersecting point can be found by solving a linear equa-
        tion. Use the LinearEquation class in Programming Exercise 9.11 to solve this
        equation. See Programming Exercise 3.25 for sample runs.
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 -1.0 4.0 2.0 -1.0 -2.0
            The intersecting point is at (2.88889, 1.1111)
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 -1.0 -2.0
            The two lines are parallel
     */
    public static void ch9_12() {
        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        double x4 = scanner.nextDouble();
        double y4 = scanner.nextDouble();
        double a = y1 - y2;
        double b = -(x1 - x2);
        double c = y3 - y4;
        double d = -(x3 - x4);
        double e = (y1 - y2) * x1 - (x1 - x2) * y1;
        double f = (y3 - y4) * x3 - (x3 - x4) * y3;

        LinearEquation equation = new LinearEquation(a, b, c, d, e, f);
        if (equation.isSolvable()) {
            double x = equation.getX();
            double y = equation.getY();
            System.out.printf("The intersecting point is at (%.4f, %.4f)\n", x, y);
        } else {
            System.out.println("The two lines are parallel");
        }
    }

    /*
        (The Location class) Design a class named Location for locating a maximal
        value and its location in a two-dimensional array. The class contains public data
        fields row, column, and maxValue that store the maximal value and its indices in
        a two-dimensional array with row and column as int types and maxValue as a
        double type.
        Write the following method that returns the location of the largest element in a
        two-dimensional array:
        public static Location locateLargest(double[][] a)
        The return value is an instance of Location. Write a test program that prompts
        the user to enter a two-dimensional array and displays the location of the largest
        element in the array. Here is a sample run:
            Enter the number of rows and columns in the array: 3 4
            Enter the array:
            23.5 35 2 10
            4.5 3 45 3.5
            35 44 5.5 9.6
            The location of the largest element is 45 at (1, 2)
     */
    public static void ch9_13() {
        System.out.print("Enter the number of rows and columns in the array: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        System.out.println("Enter the array: ");
        double[][] array = ArrayUtils.inputMatrix(rows, cols);
        Location max = locateLargest(array);
        System.out.printf(
                "The location of the largest element is %.1f at (%d, %d)\n",
                max.maxValue, max.row, max.column
        );
    }

    public static Location locateLargest(double[][] a) {
        int maxRow = 0;
        int maxCol = 0;
        double maxValue = a[maxRow][maxCol];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > maxValue) {
                    maxRow = i;
                    maxCol = j;
                    maxValue = a[i][j];
                }
            }
        }
        return new Location(maxRow, maxCol, maxValue);
    }
}


