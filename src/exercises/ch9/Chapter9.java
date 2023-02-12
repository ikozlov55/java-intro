package exercises.ch9;

import exercises.ch9.ex1.Rectangle;
import exercises.ch9.ex2.Stock;
import exercises.ch9.ex6.StopWatch;
import exercises.ch9.ex7.Account;
import exercises.ch9.ex8.Fan;

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

     */
    public static void ch9_9() {
    }

    /*

     */
    public static void ch9_10() {
    }

    /*

     */
    public static void ch9_11() {
    }

    /*

     */
    public static void ch9_12() {
    }

    /*

     */
    public static void ch9_13() {
    }
}


