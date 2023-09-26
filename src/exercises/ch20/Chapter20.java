package exercises.ch20;

import exercises.ch20.ex2.Exercise20_02;
import exercises.ch20.ex5.Exercise20_05;
import javafx.geometry.Point2D;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Chapter20 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Display words in ascending alphabetical order) Write a program that reads
        words from a text file and displays all the words (duplicates allowed) in ascend-
        ing alphabetical order. The words must start with a letter. The text file is passed
        as a command-line argument.
     */
    public static void ch20_1(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input!");
            System.exit(1);
        }
        File file = new File(args[0]);
        ArrayList<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (!Character.isLetter(next.charAt(0))) continue;
                words.add(next);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        words.sort(String::compareToIgnoreCase);
        words.forEach(System.out::println);
    }

    /*
        (Store numbers in a linked list) Write a program that lets the user enter numbers
        from a graphical user interface and displays them in a text area, as shown in Figure
        20.17a. Use a linked list to store the numbers. Do not store duplicate numbers. Add
        the buttons Sort, Shuffle, and Reverse to sort, shuffle, and reverse the list.
     */
    public static void ch20_2() {
        Exercise20_02.run();
    }

    /*
        (Guessing the capitals) Rewrite Programming Exercise 8.37 to store the pairs
        of states and capitals so that the questions are displayed randomly.
     */
    public static void ch20_3() {
        ArrayList<String[]> questions = new ArrayList<>(
                Arrays.asList(
                        new String[]{"Alabama", "Montgomery"},
                        new String[]{"Alaska", "Juneau"},
                        new String[]{"Arizona", "Phoenix"}
                )
        );
        int count = 0;
        while (!questions.isEmpty()) {
            int index = (int) (Math.random() * questions.size());
            String[] question = questions.remove(index);
            System.out.printf("What is the capital of %s? ", question[0]);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(question[1])) {
                count++;
            }
        }
        System.out.printf("The correct count is %d\n", count);
    }

    /*
        (Sort points in a plane) Write a program that meets the following require-
        ments. Randomly create 100 points using Point2D and apply the Arrays.
        sort(list, Comparator) method to sort the points in increasing order of
        their y-coordinates and then in increasing order of their x-coordinates. Display
        the x- and y-coordinates of the first five points.
     */
    public static void ch20_4() {
        int count = 100;
        Point2D[] points = new Point2D[count];
        for (int i = 0; i < count; i++) {
            double x = (int) (Math.random() * 100 + 1);
            double y = (int) (Math.random() * 100 + 1);
            points[i] = new Point2D(x, y);
        }
        Comparator<Point2D> comparator = Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX);
        Arrays.sort(points, comparator);
        for (int i = 0; i < 5; i++) {
            System.out.println(points[i]);
        }
    }

    /*
        (Combine colliding bouncing balls) The example in Section 20.8 displays
        multiple bouncing balls. Extend the example to detect collisions. Once two
        balls collide, remove the later ball that was added to the pane and add its radius
        to the other ball, as shown in Figure 20.17b. Use the Suspend button to suspend
        the animation, and the Resume button to resume the animation. Add a mouse-
        pressed handler that removes a ball when the mouse is pressed on the ball.
     */
    public static void ch20_5() {
        Exercise20_05.run();
    }

    /*
        (Use iterators on linked lists) Write a test program that stores 5 million integers
        in a linked list and test the time to traverse the list using an iterator vs. using
        the get(index) method.
     */
    public static void ch20_6() {
        int count = 5_000_000;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        AtomicInteger x = new AtomicInteger();
        testTime(() -> {
            for (int number : list) {
                x.set(number);
            }
        });
        testTime(() -> {
            for (int i = 0; i < count; i++) {
                x.set(list.get(i));
            }
        });
    }

    private static void testTime(Runnable action) {
        long startTime = System.currentTimeMillis();
        action.run();
        long endTime = System.currentTimeMillis();
        double testTime = (endTime - startTime) / 1000.0;
        System.out.printf("Action took %.2f seconds\n", testTime);
    }

    /*

     */
    public static void ch20_7() {

    }

    /*

     */
    public static void ch20_8() {

    }

    /*

     */
    public static void ch20_9() {

    }

    /*

     */
    public static void ch20_10() {

    }

    /*

     */
    public static void ch20_11() {

    }

    /*

     */
    public static void ch20_12() {

    }

    /*

     */
    public static void ch20_13() {

    }

    /*

     */
    public static void ch20_14() {

    }

    /*

     */
    public static void ch20_15() {

    }

    /*

     */
    public static void ch20_16() {

    }

    /*

     */
    public static void ch20_17() {

    }

    /*

     */
    public static void ch20_18() {

    }

    /*

     */
    public static void ch20_19() {

    }

    /*

     */
    public static void ch20_20() {

    }

    /*

     */
    public static void ch20_21() {

    }

    /*

     */
    public static void ch20_22() {

    }

    /*

     */
    public static void ch20_23() {

    }

}
