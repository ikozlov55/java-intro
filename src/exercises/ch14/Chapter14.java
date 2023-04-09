package exercises.ch14;

public class Chapter14 {


    /*
        (Display images) Write a program that displays four images in a grid pane, as
        shown in Figure 14.43a.
     */
    public static void ch14_1() {
        Exercise14_1.run();
    }

    /*

     */
    public static void ch14_2() {
        Exercise14_2.run();
    }

    /*
        (Display three cards) Write a program that displays three cards randomly
        selected from a deck of 52, as shown in Figure 14.43c. The card image files are
        named 1.png, 2.png, . . . , 52.png and stored in the image/card directory. All
        three cards are distinct and selected randomly. (Hint: You can select random
        cards by storing the numbers 1–52 to an array list, perform a random shuffle
        introduced in Section 11.12, and use the first three numbers in the array list as
        the file names for the image.)
     */
    public static void ch14_3() {
        Exercise14_3.run();
    }

    /*
        (Color and font) Write a program that displays five texts vertically, as shown in
        Figure 14.44a. Set a random color and opacity for each text and set the font of
        each text to Times Roman, bold, italic, and 22 pixels.
     */
    public static void ch14_4() {
        Exercise14_4.run();
    }

    /*
        (Characters around circle) Write a program that displays a string “Welcome to
        Java” around the circle, as shown in Figure 14.44b. (Hint: You need to display
        each character in the right location with appropriate rotation using a loop.)
     */
    public static void ch14_5() {
        Exercise14_5.run();
    }

    /*
        (Game: display a checkerboard) Write a program that displays a checkerboard
        in which each white and black cell is a Rectangle with a fill color black or
        white, as shown in Figure 14.44c
     */
    public static void ch14_6() {
        Exercise14_6.run();
    }

    /*
        (Display random 0 or 1) Write a program that displays a 10-by-10 square matrix,
        as shown in Figure 14.45a. Each element in the matrix is 0 or 1, randomly gener-
        ated. Display each number centered in a text field. Use TextField’s setText
        method to set value 0 or 1 as a string.
     */
    public static void ch14_7() {
        Exercise14_7.run();
    }

    /*
        (Display 54 cards) Expand Exercise 14.3 to display all 54 cards (including two
        jokers), nine per row. The image files are jokers and are named 53.png and 54.png
     */
    public static void ch14_8() {
        Exercise14_8.run();
    }

    /*
        (Create four fans) Write a program that places four fans in a GridPane with two
        rows and two columns, as shown in Figure 14.45b.
     */
    public static void ch14_9() {
        Exercise14_9.run();
    }

    /*
        (Display a cylinder) Write a program that draws a cylinder, as shown in Figure 14.45c.
        You can use the following method to set the dashed stroke for an arc:
        arc.getStrokeDashArray().addAll(6.0, 21.0);
        The solution posted on the website enables the cylinder to resize horizontally.
        Can you revise it to resize vertically as well?
     */
    public static void ch14_10() {
        Exercise14_10.run();
    }

    /*
        (Paint a smiley face) Write a program that paints a smiley face, as shown in
        Figure 14.46a.
     */
    public static void ch14_11() {
        Exercise14_11.run();
    }

    /*
        (Display a bar chart) Write a program that uses a bar chart to display the
        percentages of the overall grade represented by projects, quizzes, midterm
        exams, and the final exam, as shown in Figure 14.46b. Suppose projects take
        20% and are displayed in red, quizzes take 10% and are displayed in blue,
        midterm exams take 30% and are displayed in green, and the final exam takes
        40% and is displayed in orange. Use the Rectangle class to display the bars.
        Interested readers may explore the JavaFX BarChart class for further study
     */
    public static void ch14_12() {
        Exercise14_12.run();
    }

    /*
        (Display a pie chart) Write a program that uses a pie chart to display the per-
        centages of the overall grade represented by projects, quizzes, midterm exams,
        and the final exam, as shown in Figure 14.46c. Suppose projects take 20% and
        are displayed in red, quizzes take 10% and are displayed in blue, midterm exams
        take 30% and are displayed in green, and the final exam takes 40% and is dis-
        played in orange. Use the Arc class to display the pies. Interested readers may
        explore the JavaFX PieChart class for further study
     */
    public static void ch14_13() {
        Exercise14_13.run();
    }

    /*
        (Display a rectanguloid) Write a program that displays a rectanguloid, as
        shown in Figure 14.47a. The cube should grow and shrink as the window grows
        or shrinks.
     */
    public static void ch14_14() {
        Exercise14_14.run();
    }

    /*
        (Display a STOP sign) Write a program that displays a STOP sign, as shown
        in Figure 14.47b. The octagon is in red and the sign is in white. (Hint: Place an
        octagon and a text in a stack pane.)
     */

    public static void ch14_15() {
        Exercise14_15.run();
    }

    /*
        (Display a 3 * 3 grid) Write a program that displays a 3 * 3 grid, as shown in
        Figure 14.47c. Use red color for vertical lines and blue for horizontals. The lines
        are automatically resized when the window is resized.
     */
    public static void ch14_16() {
        Exercise14_16.run();
    }

    /*
        (Game: hangman) Write a program that displays a drawing for the popular hang-
        man game, as shown in Figure 14.48a.
     */
    public static void ch14_17() {
        Exercise14_17.run();
    }

    /*
        (Plot the square function) Write a program that draws a diagram for the function
        f(x) = x2 (see Figure 14.48b).
        Hint: Add points to a polyline using the following code:
        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 0.0125;
        for (int x = –100; x <= 100; x++) {
        list.add(x + 200.0);
        list.add(scaleFactor * x * x);
        }
     */
    public static void ch14_18() {
        Exercise14_18.run();
    }

    /*
        (Plot the sine and cosine functions) Write a program that plots the sine function
        in red and cosine in blue, as shown in Figure 14.48c.
        Hint: The Unicode for p is \u03c0. To display -2p, use Text(x, y, "–2\
        u03c0"). For a trigonometric function like sin(x), x is in radians. Use the
        following loop to add the points to a polyline:
        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 50;
        for (int x = −170; x <= 170; x++) {
            list.add(x + 200.0);
            list.add(100 – scaleFactor * Math.sin((x / 100.0) * 2 *Math.PI));
        }
        Note that x in the loop is a point in the X-Axis and x does not correspond to
        angles in degrees. The entire expression (x / 100.0) * 2 * Math.PI rep-
        resents an angle in radians.
        When x is -100, Math.sin((x / 100.0) * 2 * Math.PI) is 0
        When x is -75, Math.sin((x / 100.0) * 2 * Math.PI) is 1
        When x is -50, Math.sin((x / 100.0) * 2 * Math.PI) is 0
        When x is -25, Math.sin((x / 100.0) * 2 * Math.PI) is -1
        When x is 0, Math.sin((x / 100.0) * 2 * Math.PI) is 0
        When x is 25, Math.sin((x / 100.0) * 2 * Math.PI) is 1
        When x is 50, Math.sin((x / 100.0) * 2 * Math.PI) is 0
        When x is 75, Math.sin((x / 100.0) * 2 * Math.PI) is -1
        When x is 100, Math.sin((x / 100.0) * 2 * Math.PI) is 0
     */
    public static void ch14_19() {
        Exercise14_19.run();
    }

    /*
        (Draw an arrow line) Write a static method that draws an arrow line from a
        starting point to an ending point in a pane using the following method header:
        public static void drawArrowLine(double startX, double startY,
        double endX, double endY, Pane pane)
        Write a test program that randomly draws an arrow line, as shown in Figure 14.49a.
     */
    public static void ch14_20() {
        Exercise14_20.run();
    }

    /*
        (Two circles and their distance) Write a program that draws two circles with
        radius 15 pixels, centered at random locations, with a line connecting the two
        circles. The distance between the two centers is displayed on the line, as shown
        in Figure 14.49b
     */
    public static void ch14_21() {
        Exercise14_21.run();
    }

    /*
        (Connect two circles) Write a program that draws two filled circles with radius
        15 pixels, centered at random locations, with a line connecting the two circles.
        The line should not cross inside the circles, as shown in Figure 14.49c.
     */
    public static void ch14_22() {
        Exercise14_22.run();
    }

    /*
        (Geometry: two rectangles) Write a program that prompts the user to enter the
        center coordinates, width, and height of two rectangles from the command line.
        The program displays the rectangles and a text indicating whether the two are
        overlapping, whether one is contained in the other, or whether they don’t over-
        lap, as shown in Figure 14.50. See Programming Exercise 10.13 for checking the
        relationship between two rectangles.
     */
    public static void ch14_23() {
        Exercise14_23.run();
    }

    /*
        (Geometry: Inside a polygon?) Write a program that prompts the user to enter
        the coordinates of five points from the command line. The first four points form a
        polygon, and the program displays the polygon and a text that indicates whether
        the fifth point is inside the polygon, as shown in Figure 14.51a. (Hint: Use the
        Node’s contains method to test whether a point is inside a node.)
     */
    public static void ch14_24() {
        Exercise14_24.run();
    }

    /*
        (Random points on a circle) Modify Programming Exercise 4.6 to create five
        random points on a circle, form a polygon by connecting the points clockwise,
        and display the circle and the polygon, as shown in Figure 14.51b.
     */
    public static void ch14_25() {
        Exercise14_25.run();
    }

    /*
        (Use the ClockPane class) Write a program that displays two clocks. The hour,
        minute, and second values are 4, 20, 45 for the first clock, and 22, 46, 15 for the
        second clock, as shown in Figure 14.51c.
     */
    public static void ch14_26() {
        Exercise14_26.run();
    }

    /*
        (Draw a detailed clock) Modify the ClockPane class in Section 14.12 to draw
        the clock with more details on the hours and minutes, as shown in Figure
        14.52a.
     */
    public static void ch14_27() {
        Exercise14_27.run();
    }

    /*
        (Random time) Modify the ClockPane class with three new Boolean properties
        —hourHandVisible,minuteHandVisible,and secondHandVisible
        and their associated accessor and mutator methods.
        You can use the set methods to make a hand visible or invisible. Write a test
        program that displays only the hour and minute hands. The hour and minute
        values are randomly generated. The hour is between 0 and 11, and the minute is
        either 0 or 30, as shown in Figure 14.52b.
     */
    public static void ch14_28() {
        Exercise14_28.run();
    }

    /*
        (Game: bean machine) Write a program that displays a bean machine introduced
        in Programming Exercise 7.37, as shown in Figure 14.52c.
     */
    public static void ch14_29() {
        Exercise14_29.run();
    }

}
