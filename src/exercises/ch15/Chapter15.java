package exercises.ch15;

public class Chapter15 {


    /*
        (Pick four cards) Write a program that lets the user click the Refresh button to
        display four cards from a deck of 52 cards, as shown in Figure 15.26a. (See the
        hint in Programming Exercise 14.3 on how to obtain four random cards.)
     */
    public static void ch15_1() {
        Exercise15_1.run();
    }

    /*
        (Rotate a rectangle) Write a program that rotates a rectangle 15 degrees to the
        right when the Rotate button is clicked, as shown in Figure 15.26b.
     */
    public static void ch15_2() {
        Exercise15_2.run();
    }

    /*
        (Move the ball) Write a program that moves the ball in a pane. You should
        define a pane class for displaying the ball and provide the methods for moving
        the ball left, right, up, and down, as shown in Figure 15.26c. Check the bound-
        ary to prevent the ball from moving out of sight completely.
     */
    public static void ch15_3() {
        Exercise15_3.run();
    }

    /*
        (Create a simple calculator) Write a program to perform addition, subtraction,
        multiplication, and division, as shown in Figure 15.27a.
     */
    public static void ch15_4() {
        Exercise15_4.run();
    }

    /*
        (Create an investment-value calculator) Write a program that calculates the
        future value of an investment at a given interest rate for a specified number of
        years. The formula for the calculation is
        futureValue = investmentAmount * (1 + monthlyInterestRate)years*12
        Use text fields for the investment amount, number of years, and annual interest
        rate. Display the future amount in a text field when the user clicks the Calculate
        button, as shown in Figure 15.27b.
     */
    public static void ch15_5() {
        Exercise15_5.run();
    }

    /*
        (Alternate two messages) Write a program to display the text Java is fun
        and Java is powerful alternately with a mouse click.
     */
    public static void ch15_6() {
        Exercise15_6.run();
    }

    /*
        (Change color using a mouse) Write a program that displays the color of a
        circle as black when the mouse button is pressed, and as white when the mouse
        button is released.
     */
    public static void ch15_7() {
        Exercise15_7.run();
    }

    /*
        (Display the mouse position) Write two programs, such that one displays the
        mouse position when the mouse button is clicked (see Figure 15.28a), and the
        other displays the mouse position when the mouse button is pressed and ceases
        to display it when the mouse button is released.
     */
    public static void ch15_8() {
        Exercise15_8.run();
    }

    /*
        (Draw lines using the arrow keys) Write a program that draws line segments
        using the arrow keys. The line starts from (100, 100) in the pane and draws
        toward east, north, west, or south when the right-arrow key, up-arrow key,
        left-arrow key, or down-arrow key is pressed, as shown in Figure 15.28b.
     */
    public static void ch15_9() {
        Exercise15_9.run();
    }

    /*
        (Enter and display a string) Write a program that receives a string from the
        keyboard and displays it on a pane. The Enter key signals the end of a string.
        Whenever a new string is entered, it is displayed on the pane.
     */
    public static void ch15_10() {
        Exercise15_10.run();
    }

    /*
        (Move a circle using keys) Write a program that moves a circle up, down, left,
        or right using the arrow keys.
     */
    public static void ch15_11() {
        Exercise15_11.run();
    }

    /*
        (Geometry: inside a circle?) Write a program that draws a fixed circle centered
        at (100, 60) with radius 50. Whenever the mouse is moved, display a message
        indicating whether the mouse point is inside the circle at the mouse point or
        outside of it, as shown in Figure 15.29a.
     */
    public static void ch15_12() {
        Exercise15_12.run();
    }

    /*
        (Geometry: inside a rectangle?) Write a program that draws a fixed rectangle cen-
        tered at (100, 60) with width 100 and height 40. Whenever the mouse is moved,
        display a message indicating whether the mouse point is inside the rectangle at the
        mouse point or outside of it, as shown in Figure 15.29b. To detect whether a point
        is inside a polygon, use the contains method defined in the Node class.
     */
    public static void ch15_13() {
        Exercise15_13.run();
    }

    /*
        (Geometry: inside a polygon?) Write a program that draws a fixed polygon
        with points at (40, 20), (70, 40), (60, 80), (45, 45), and (20, 60). Whenever
        the mouse is moved, display a message indicating whether the mouse point
        is inside the polygon at the mouse point or outside of it, as shown in Figure
        15.29c. To detect whether a point is inside a polygon, use the contains
        method defined in the Node class
     */
    public static void ch15_14() {
        Exercise15_14.run();
    }

    /*
        (Geometry: add and remove points) Write a program that lets the user click on
        a pane to dynamically create and remove points (see Figure 15.30a). When the
        user left-clicks the mouse (primary button), a point is created and displayed
        at the mouse point. The user can remove a point by pointing to it and right-
        clicking the mouse (secondary button).
     */
    public static void ch15_15() {
        Exercise15_15.run();
    }

    /*

     */
    public static void ch15_16() {
        Exercise15_16.run();
    }

    /*
        (Geometry: find the bounding rectangle) Write a program that enables the user
        to add and remove points in a two-dimensional plane dynamically, as shown
        in Figure 15.31a. A minimum bounding rectangle is updated as the points are
        added and removed. Assume the radius of each point is 10 pixels.
     */
    public static void ch15_17() {
        Exercise15_17.run();
    }

    /*
        (Move a rectangle using mouse) Write a program that displays a rectangle.
        You can point the mouse inside the rectangle and drag (i.e., move with mouse
        pressed) the rectangle wherever the mouse goes. The mouse point becomes the
        center of the rectangle.
     */
    public static void ch15_18() {
        Exercise15_18.run();
    }

    /*
        (Game: eye–hand coordination) Write a program that displays a circle of
        radius 10 pixels filled with a random color at a random location on a pane, as
        shown in Figure 15.31b. When you click the circle, it disappears and a new
        random-color circle is displayed at another random location. After 20 circles
        are clicked, display the time spent in the pane, as shown in Figure 15.31c.
     */
    public static void ch15_19() {
        Exercise15_19.run();
    }

    /*
        (Geometry: display angles) Write a program that enables the user to drag the
        vertices of a triangle and displays the angles dynamically as the triangle shape
        changes, as shown in Figure 15.32a. The formula to compute angles is given in
        Listing 4.1.
     */
    public static void ch15_20() {
        Exercise15_20.run();
    }

    /*
        (Drag points) Draw a circle with three random points on the circle. Connect
        the points to form a triangle. Display the angles in the triangle. Use the mouse
        to drag a point along the perimeter of the circle. As you drag it, the triangle and
        angles are redisplayed dynamically, as shown in Figure 15.32b. For computing
        angles in a triangle, see Listing 4.1
     */
    public static void ch15_21() {
        Exercise15_21.run();
    }

    /*
        (Auto resize cylinder) Rewrite Programming Exercise 14.10 so the cylinder’s
        width and height are automatically resized when the window is resized.
     */
    public static void ch15_22() {
        Exercise15_22.run();
    }

    /*
        (Auto resize stop sign) Rewrite Programming Exercise 14.15 so the stop sign’s
        width and height are automatically resized when the window is resized.
     */
    public static void ch15_23() {
        Exercise15_23.run();
    }

    /*
        (Animation: pendulum swing) Write a program that animates a pendulum
        swing, as shown in Figure 15.33. Press/release the mouse to pause/resume the
        animation.
     */
    public static void ch15_24() {
        Exercise15_24.run();
    }

    /*

     */
    public static void ch15_25() {
    }

    /*

     */
    public static void ch15_26() {
    }

    /*

     */
    public static void ch15_27() {
    }

    /*

     */
    public static void ch15_28() {
    }

    /*

     */
    public static void ch15_29() {
    }

    /*

     */
    public static void ch15_30() {
    }

    /*

     */
    public static void ch15_31() {
    }

    /*

     */
    public static void ch15_32() {
    }

    /*

     */
    public static void ch15_33() {
    }

    /*

     */
    public static void ch15_34() {
    }

    /*

     */
    public static void ch15_35() {
    }

    /*

     */
    public static void ch15_36() {
    }


}
