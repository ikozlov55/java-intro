package exercises.ch10;

import exercises.ch10.ex1.Time;
import exercises.ch10.ex2.BMI;
import exercises.ch10.ex3.MyInteger;
import exercises.ch10.ex4.MyPoint;

import java.util.Locale;
import java.util.Scanner;

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

     */
    public static void ch10_5() {

    }

    /*

     */
    public static void ch10_6() {

    }

    /*

     */
    public static void ch10_7() {

    }

    /*

     */
    public static void ch10_8() {

    }

    /*

     */
    public static void ch10_9() {

    }

    /*

     */
    public static void ch10_10() {

    }

    /*

     */
    public static void ch10_11() {

    }

    /*

     */
    public static void ch10_12() {

    }

    /*

     */
    public static void ch10_13() {

    }

    /*

     */
    public static void ch10_14() {

    }

    /*

     */
    public static void ch10_15() {

    }

    /*

     */
    public static void ch10_16() {

    }

    /*

     */
    public static void ch10_17() {

    }

    /*

     */
    public static void ch10_18() {

    }

    /*

     */
    public static void ch10_19() {

    }

    /*

     */
    public static void ch10_20() {

    }

    /*

     */
    public static void ch10_21() {

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
