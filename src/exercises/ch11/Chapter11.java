package exercises.ch11;

import exercises.ch10.ex9.Course;
import exercises.ch11.ex1.Triangle;
import exercises.ch11.ex10.MyStack;
import exercises.ch11.ex2.*;
import exercises.ch11.ex3.CheckingAccount;
import exercises.ch11.ex3.SavingsAccount;
import exercises.ch11.ex6.Loan;
import exercises.ch9.ex7.Account;
import javafx.scene.shape.Circle;

import java.util.*;
import java.util.stream.IntStream;

public class Chapter11 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (The Triangle class) Design a class named Triangle that extends
        GeometricObject. The class contains:
        ■ Three double data fields named side1, side2, and side3 with default val-
        ues 1.0 to denote three sides of a triangle.
        ■ A no-arg constructor that creates a default triangle.
        ■ A constructor that creates a triangle with the specified side1, side2, and side3.
        ■ The accessor methods for all three data fields.
        ■ A method named getArea() that returns the area of this triangle.
        ■ A method named getPerimeter() that returns the perimeter of this triangle.
        ■ A method named toString() that returns a string description for the triangle.
        For the formula to compute the area of a triangle, see Programming Exercise 2.19.
        The toString() method is implemented as follows:
        return "Triangle: side1 = " + side1 + " side2 = " + side2 +
        " side3 = " + side3;
        Draw the UML diagrams for the classes Triangle and GeometricObject and
        implement the classes. Write a test program that prompts the user to enter three
        sides of the triangle, a color, and a Boolean value to indicate whether the triangle
        is filled. The program should create a Triangle object with these sides and set
        the color and filled properties using the input. The program should display
        the area, perimeter, color, and true or false to indicate whether it is filled or not.
     */
    public static void ch11_1() {
        System.out.print("Enter three sides of a triangle: ");
        Triangle t = new Triangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        System.out.print("Enter a color of a triangle: ");
        String color = scanner.next();
        t.setColor(color);
        System.out.print("Should a triangle be filled: ");
        boolean filled = scanner.nextBoolean();
        t.setFilled(filled);
        System.out.println(t);
        System.out.printf("Area: %.2f\n", t.getArea());
        System.out.printf("Perimeter: %.2f", t.getPerimeter());
    }

    /*
        (The Person, Student, Employee, Faculty, and Staff classes) Design a class
        named Person and its two subclasses named Student and Employee. Make
        Faculty and Staff subclasses of Employee. A person has a name, address,
        phone number, and e-mail address. A student has a class status (freshman, soph-
        omore, junior, or senior). Define the status as a constant. An employee has an
        office, salary, and date hired. Use the MyDate class defined in Programming
        Exercise 10.14 to create an object for date hired. A faculty member has office
        hours and a rank. A staff member has a title. Override the toString method in
        each class to display the class name and the person’s name.
        Draw the UML diagram for the classes and implement them. Write a test program
        that creates a Person, Student, Employee, Faculty, and Staff, and invokes
        their toString() methods.
     */
    public static void ch11_2() {
        Person person = new Person(
                "Bob",
                "some address",
                "65287354832",
                "person@mail.com"
        );
        Student student = new Student(
                "Max",
                "some address",
                "65287354832",
                "student@mail.com"
        );
        Employee employee = new Employee(
                "Margaret",
                "some address",
                "65287354832",
                "employee@mail.com",
                "main office",
                50000
        );
        Faculty faculty = new Faculty(
                "Sam",
                "some address",
                "65287354832",
                "faculty@mail.com",
                "main office",
                100000,
                40,
                "Physics"
        );
        Staff staff = new Staff(
                "Barbara",
                "some address",
                "65287354832",
                "staff@mail.com",
                "main office",
                150000,
                "professor"
        );
        System.out.println(person);
        System.out.println(student);
        System.out.println(employee);
        System.out.println(faculty);
        System.out.println(staff);
    }

    /*
        (Subclasses of Account) In Programming Exercise 9.7, the Account class was
        defined to model a bank account. An account has the properties account number,
        balance, annual interest rate, and date created, and methods to deposit and with-
        draw funds. Create two subclasses for checking and saving accounts. A checking
        account has an overdraft limit, but a savings account cannot be overdrawn.
        Draw the UML diagram for the classes and implement them. Write a test program
        that creates objects of Account, SavingsAccount, and CheckingAccount and
        invokes their toString() methods.
     */
    public static void ch11_3() {
        Account account1 = new Account(1, 1000);
        SavingsAccount account2 = new SavingsAccount(2, 1000);
        CheckingAccount account3 = new CheckingAccount(3, 1000, 200);
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        account3.withdraw(500);
        System.out.println(account3);
        account3.withdraw(800);
        account2.withdraw(1100);
    }

    /*
        (Maximum element in ArrayList) Write the following method that returns the
        maximum value in an ArrayList of integers. The method returns null if the
        list is null or the list size is 0.
        public static Integer max(ArrayList<Integer> list)
        Write a test program that prompts the user to enter a sequence of numbers ending
        with 0 and invokes this method to return the largest number in the input.
     */
    public static void ch11_4() {
        System.out.print("Enter a sequence of numbers ending with 0: ");
        ArrayList<Integer> list = new ArrayList<>();
        int input = scanner.nextInt();
        while (input != 0) {
            list.add(input);
            input = scanner.nextInt();
        }
        System.out.println(max(list));
    }

    public static Integer max(ArrayList<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Integer max = list.get(0);
        for (Integer i : list) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /*
        (The Course class) Rewrite the Course class in Listing 10.6. Use an ArrayList
        to replace an array to store students. Draw the new UML diagram for the class.
        You should not change the original contract of the Course class (i.e., the defi-
        nition of the constructors and methods should not be changed, but the private
        members may be changed.)
     */
    public static void ch11_5() {
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
        (Use ArrayList) Write a program that creates an ArrayList and adds a Loan
        object, a Date object, a string, and a Circle object to the list, and use a loop to
        display all the elements in the list by invoking the object’s toString() method.
     */
    public static void ch11_6() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(new Loan());
        list.add(new Date());
        list.add("zzz");
        list.add(new Circle());
        list.forEach(System.out::println);
    }

    /*
        (Shuffle ArrayList) Write the following method that shuffles the elements in
        an ArrayList of integers:
        public static void shuffle(ArrayList<Integer> list)
     */
    public static void ch11_7() {
        ArrayList<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, 10).forEach(list::add);
        shuffle(list);
        System.out.println(list);
    }

    public static void shuffle(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int index = (int) (Math.random() * list.size());
            int temp = list.get(i);
            list.set(i, list.get(index));
            list.set(index, temp);
        }
    }

    /*
        (New Account class) An Account class was specified in Programming Exercise 9.7.
        Design a new Account class as follows:
        ■ Add a new data field name of the String type to store the name of the
        customer.
        ■ Add a new constructor that constructs an account with the specified name, id,
        and balance.
        ■ Add a new data field named transactions whose type is ArrayList that
        stores the transaction for the accounts. Each transaction is an instance of the
        Transaction class, which is defined as shown in Figure 11.6.
        Modify the withdraw and deposit methods to add a transaction to the
        transactions array list.
        ■ All other properties and methods are the same as in Programming Exercise 9.7.
        Programming Exercises 449
        Write a test program that creates an Account with annual interest rate 1.5%,
        balance 1000, id 1122, and name George. Deposit $30, $40, and $50 to the
        account and withdraw $5, $4, and $2 from the account. Print an account summary
        that shows the account holder name, interest rate, balance, and all transactions.
     */
    public static void ch11_8() {
        exercises.ch11.ex8.Account account = new exercises.ch11.ex8.Account(1122, 1000, "George");
        account.setAnnualInterestRate(1.5);
        account.deposit(30);
        account.deposit(40);
        account.deposit(50);
        account.withdraw(5);
        account.withdraw(4);
        account.withdraw(2);
        System.out.println(account);
    }

    /*
        (Largest rows and columns) Write a program that randomly fills in 0s and 1s
        into an n-by-n matrix, prints the matrix, and finds the rows and columns with the
        most 1s. (Hint: Use two ArrayLists to store the row and column indices with
        the most 1s.) Here is a sample run of the program:
        Enter the array size n: 4
        The random array is
        0011
        0011
        1101
        1010
        The largest row index: 2
        The largest column index: 2, 3
     */
    public static void ch11_9() {
        System.out.print("Enter the array size n: ");
        int N = scanner.nextInt();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add((int) (Math.random() * 2));
            }
            matrix.add(row);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }
        ArrayList<Integer> indices = new ArrayList<>(List.of(0, 0));
        int maxOnesInRow = (int) matrix.get(0).stream().filter(x -> x.equals(1)).count();
        for (int i = 1; i < N; i++) {
            int onesInRow = (int) matrix.get(i).stream().filter(x -> x.equals(1)).count();
            if (onesInRow > maxOnesInRow) {
                indices.set(0, i);
                maxOnesInRow = onesInRow;
            }
        }

        int maxOnesInColumn = (int) matrix.stream().map(x -> x.get(0)).filter(x -> x.equals(1)).count();
        for (int i = 1; i < N; i++) {
            final int col = i;
            int onesInColumn = (int) matrix.stream().map(x -> x.get(col)).filter(x -> x.equals(1)).count();
            if (onesInColumn > maxOnesInColumn) {
                indices.set(1, i);
                maxOnesInColumn = onesInColumn;
            }
        }

        System.out.println("The largest row index: " + indices.get(0));
        System.out.println("The largest column index: " + indices.get(1));
    }

    /*
        (Implement MyStack using inheritance) In Listing 11.10, MyStack
        is implemented using composition. Define a new stack class that extends
        ArrayList.
        Draw the UML diagram for the classes then implement MyStack. Write a test pro-
        gram that prompts the user to enter five strings and displays them in reverse order.
     */
    public static void ch11_10() {
        System.out.println("Enter five strings:");
        MyStack<String> stack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(scanner.nextLine());
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    /*
        (Sort ArrayList) Write the following method that sorts an ArrayList of numbers:
        public static void sort(ArrayList<Integer> list)
        Write a test program that prompts the user to enter five numbers, stores them in
        an array list, and displays them in increasing order.
     */
    public static void ch11_11() {
        System.out.print("Enter five numbers: ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(scanner.nextInt());
        }
        sort(list);
        list.forEach(System.out::println);
    }

    public static void sort(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int currentMax = list.get(i);
            int currentMaxIndex = i;
            for (int j = 0; j < i; j++) {
                if (list.get(j) > currentMax) {
                    currentMax = list.get(j);
                    currentMaxIndex = j;
                }
            }
            if (currentMaxIndex != i) {
                list.set(currentMaxIndex, list.get(i));
                list.set(i, currentMax);
            }
        }
        Collections.sort(list);
    }

    /*
        (Sum ArrayList) Write the following method that returns the sum of all num-
        bers in an ArrayList:
        public static double sum(ArrayList<Double> list)
        Write a test program that prompts the user to enter five numbers, stores them in
        an array list, and displays their sum
     */
    public static void ch11_12() {
        System.out.print("Enter five numbers: ");
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(scanner.nextDouble());
        }
        System.out.println(sum(list));
    }

    public static double sum(ArrayList<Double> list) {
        double result = 0;
        for (Double number : list) {
            result += number;
        }
        return result;
    }

    /*
        (Remove duplicates) Write a method that removes the duplicate elements from
        an array list of integers using the following header:
        public static void removeDuplicate(ArrayList<Integer> list)
        Write a test program that prompts the user to enter 10 integers to a list and dis-
        plays the distinct integers in their input order and separated by exactly one space.
        Here is a sample run:
        Enter 10 integers: 34 5 3 5 6 4 33 2 2 4
        The distinct integers are 34 5 3 6 4 33 2
     */
    public static void ch11_13() {
        final int N = 10;
        System.out.printf("Enter %d integers: ", N);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }
        removeDuplicate(list);
        System.out.print("The distinct integers are ");
        list.forEach(x -> System.out.print(x + " "));
    }

    public static void removeDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> distinct = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (distinct.contains(list.get(i))) {
                list.remove(i);
                i--;
            } else {
                distinct.add(list.get(i));
            }
        }

    }

    /*
        (Combine two lists) Write a method that returns the union of two array lists of
        integers using the following header:
        public static ArrayList<Integer> union(ArrayList<Integer> list1, ArrayList<Integer> list2)
        For example, the addition of two array lists {2, 3, 1, 5} and {3, 4, 6} is
        {2, 3, 1, 5, 3, 4, 6}. Write a test program that prompts the user to enter two lists,
        each with five integers, and displays their union. The numbers are separated by
        exactly one space. Here is a sample run:
        Enter five integers for list1: 3 5 45 4 3
        Enter five integers for list2: 33 51 5 4 13
        The combined list is 3 5 45 4 3 33 51 5 4 13
     */
    public static void ch11_14() {
        System.out.print("Enter five integers for list1: ");
        ArrayList<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list1.add(scanner.nextInt());
        }
        System.out.print("Enter five integers for list2: ");
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list2.add(scanner.nextInt());
        }
        ArrayList<Integer> combinedList = union(list1, list2);
        System.out.print("The combined list is ");
        combinedList.forEach(x -> System.out.print(x + " "));
    }

    public static ArrayList<Integer> union(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>(list1.size() + list2.size());
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }

    /*

     */
    public static void ch11_15() {

    }

    /*

     */
    public static void ch11_16() {

    }

    /*

     */
    public static void ch11_17() {

    }

    /*

     */
    public static void ch11_18() {

    }

    /*

     */
    public static void ch11_19() {

    }
}
