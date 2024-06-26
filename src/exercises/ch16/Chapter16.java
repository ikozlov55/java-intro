package exercises.ch16;


public class Chapter16 {
    /*
        (Use radio buttons) Write a GUI program as shown in Figure 16.36a. You can
        use buttons to move the message to the left and right and use the radio buttons to
        change the color for the message displayed.
     */
    public static void ch16_1() {
        Exercise16_01.run();
    }

    /*
        (Select geometric figures) Write a program that draws various figures, as
        shown in Figure 16.36b. The user selects a figure from a radio button and
        uses a check box to specify whether it is filled.
     */
    public static void ch16_2() {
        Exercise16_02.run();
    }

    /*
        (Traffic lights) Write a program that simulates a traffic light. The program
        lets the user select one of three lights: red, yellow, or green. When a radio
        button is selected, the light is turned on. Only one light can be on at a time
        (see Figure 16.37a). No light is on when the program starts
     */
    public static void ch16_3() {
        Exercise16_03.run();
    }

    /*
        (Create a miles/kilometers converter) Write a program that converts miles and
        kilometers, as shown in Figure 16.37b. If you enter a value in the Mile text field
        and press the Enter key, the corresponding kilometer measurement is displayed
        in the Kilometer text field. Likewise, if you enter a value in the Kilometer text
        field and press the Enter key, the corresponding miles is displayed in the Mile
        text field.
     */
    public static void ch16_4() {
        Exercise16_04.run();
    }

    /*
        (Convert numbers) Write a program that converts among decimal, hex, and binary
        numbers, as shown in Figure 16.37c. When you enter a decimal value in the
        decimal-value text field and press the Enter key, its corresponding hex and binary
        numbers are displayed in the other two text fields. Likewise, you can enter values
        in the other fields and convert them accordingly. (Hint: Use the Integer
        .parseInt(s, radix) method to parse a string to a decimal and use Integer
        .toHexString(decimal) and Integer.toBinaryString(decimal) to
        obtain a hex number or a binary number from a decimal.)
     */
    public static void ch16_5() {
        Exercise16_05.run();
    }

    /*
        (DemonstrateTextField properties) Write a program that sets the
        horizontal-alignment and column-size properties of a text field dynamically, as
        shown in Figure 16.38a.
     */
    public static void ch16_6() {
        Exercise16_06.run();
    }

    /*
        (Set clock time) Write a program that displays a clock and sets the time with the
        input from three text fields, as shown in Figure 16.38b. Use the ClockPane in
        Listing 14.21. Resize the clock to the center of the pane.
     */
    public static void ch16_7() {
        Exercise16_07.run();
    }

    /*
        (Geometry: two circles intersect?) Write a program that enables the user to spec-
        ify the location and size of the circles, and displays whether the two circles
        intersect, as shown in Figure 16.39a. Enable the user to point the mouse inside a
        circle and drag it. As the circle is being dragged, the circle’s center coordinates
        in the text fields are updated.
     */
    public static void ch16_8() {
        Exercise16_08.run();
    }

    /*
        (Geometry: two rectangles intersect?) Write a program that enables the user to
        specify the location and size of the rectangles and displays whether the two rect-
        angles intersect, as shown in Figure 16.39b. Enable the user to point the mouse
        inside a rectangle and drag it. As the rectangle is being dragged, the rectangle’s
        center coordinates in the text fields are updated.
     */
    public static void ch16_9() {
        Exercise16_09.run();
    }

    /*
        (Text viewer) Write a program that displays a text file in a text area, as shown
        in Figure 16.40a. The user enters a file name in a text field and clicks the View
        button; the file is then displayed in a text area.
     */
    public static void ch16_10() {
        Exercise16_10.run();
    }

    /*
        (Create a histogram for occurrences of letters) Write a program that reads a
        file and displays a histogram to show the occurrences of each letter in the file,
        as shown in Figure 16.40b. The file name is entered from a text field. Pressing
        the Enter key on the text field causes the program to start to read, process the
        file, and display the histogram. The histogram is displayed in the center of the
        window. Define a class named Histogram that extends Pane. The class con-
        tains the property counts that is an array of 26 elements. counts[0] stores the
        number of A, counts[1] the number of B, and so on. The class also contains a
        setter method for setting a new counts and displaying the histogram for the new
        counts.
     */
    public static void ch16_11() {
        //>> ./src/exercises/ch16/Exercise16_11.java
        Exercise16_11.run();
    }

    /*
        (Demonstrate TextArea properties) Write a program that demonstrates the
        properties of a text area. The program uses a check box to indicate whether the
        text is wrapped onto next line, as shown in Figure 16.41a.
     */
    public static void ch16_12() {
        Exercise16_12.run();
    }

    /*
        (Compare loans with various interest rates) Rewrite Programming Exercise
        5.21 to create a GUI, as shown in Figure 16.41b. Your program should let the
        user enter the loan amount and loan period in the number of years from text
        fields, and it should display the monthly and total payments for each interest
        rate starting from 5% to 8%, with increments of one-eighth, in a text area.
     */
    public static void ch16_13() {
        Exercise16_13.run();
    }

    /*
        (Select a font) Write a program that can dynamically change the font of a text in
        a label displayed on a stack pane. The text can be displayed in bold and italic at
        the same time. You can select the font name or font size from combo boxes, as
        shown in Figure 16.42a. The available font names can be obtained using Font
        .getFontNames(). The combo box for the font size is initialized with numbers
        from 1 to 100.
     */
    public static void ch16_14() {
        Exercise16_14.run();
    }

    /*
        (Demonstrate Label properties) Write a program to let the user dynamically
        set the properties contentDisplay and graphicTextGap, as shown in
        Figure 16.42b.
     */
    public static void ch16_15() {
        Exercise16_15.run();
    }

    /*
        (Use ComboBox and ListView) Write a program that demonstrates selecting
        items in a list. The program uses a combo box to specify a selection mode, as
        shown in Figure 16.43a. When you select items, they are displayed in a label
        below the list.
     */
    public static void ch16_16() {
        Exercise16_16.run();
    }

    /*
        (Use ScrollBar and Slider) Write a program that uses scroll bars or sliders
        to select the color for a text, as shown in Figure 16.43b. Four horizontal scroll
        bars are used for selecting the colors: red, green, blue, and opacity percentages.
     */
    public static void ch16_17() {
        Exercise16_17.run();
    }

    /*
        (Simulation: a running fan) Rewrite Programming Exercise 15.28 to add a slider
        to control the speed of the fan, as shown in Figure 16.43c.
     */
    public static void ch16_18() {
        Exercise16_18.run();
    }

    /*
        (Control a group of fans) Write a program that displays three fans in a group,
        with control buttons to start and stop all of them, as shown in Figure 16.44.
     */
    public static void ch16_19() {
        Exercise16_19.run();
    }

    /*
        (Count-up stopwatch) Write a program that simulates a stopwatch, as shown
        in Figure 16.45a. When the user clicks the Start button, the button’s label is
        changed to Pause, as shown in Figure 16.45b. When the user clicks the Pause
        button, the button’s label is changed to Resume, as shown in Figure 16.45c. The
        Clear button resets the count to 0 and resets the button’s label to Start.
     */
    public static void ch16_20() {
        Exercise16_20.run();
    }

    /*
        (Count-down stopwatch) Write a program that allows the user to enter time
        in seconds in the text field and press the Enter key to count down the sec-
        onds, as shown in Figure 16.45d. The remaining seconds are redisplayed
        every second. When the seconds are expired, the program starts to play music
        continuously.
     */
    public static void ch16_21() {
        Exercise16_21.run();
    }

    /*
        (Play, loop, and stop a sound clip) Write a program that meets the following
        requirements:
        ■ Get an audio file from the class directory using AudioClip.
        ■ Place three buttons labeled Play, Loop, and Stop, as shown in Figure 16.46a.
        ■ If you click the Play button, the audio file is played once. If you click the Loop
        button, the audio file keeps playing repeatedly. If you click the Stop button,
        the playing stops.
     */
    public static void ch16_22() {
        Exercise16_22.run();
    }

    /*
        (Create an image animator with audio) Create animation in Figure 16.46b to
        meet the following requirements:
        ■ Allow the user to specify the animation speed in a text field.
        ■ Get the number of images and image’s file-name prefix from the user. For
        example, if the user enters n for the number of images and L for the image
        prefix, then the files are L1.gif, L2.gif, and so on, to Ln.gif. Assume the
        images are stored in the image directory, a subdirectory of the program’s class
        directory. The animation displays the images one after the other.
        ■ Allow the user to specify an audio file URL. The audio is played while the
        animation runs.
     */
    public static void ch16_23() {
        Exercise16_23.run();
    }

    /*
        (Revise Listing 16.14 MediaDemo.java) Add a slider to enable the user to set the
        current time for the video and a label to display the current time and the total
        time for the video. As shown in Figure 16.47a, the total time is 5 minutes and 3
        seconds and the current time is 3 minutes and 58 seconds. As the video plays, the
        slider value and current time are continuously updated.
     */
    public static void ch16_24() {
        Exercise16_24.run();
    }

    /*
        (Racing cars) Write a program that simulates four cars racing, as shown in
        Figure 16.47b. You can set the speed for each car, with a maximum of 100.
     */
    public static void ch16_25() {
        Exercise16_25.run();
    }

    /*
        (Simulation: raise flag and play anthem) Write a program that displays a flag
        rising up, as shown in Figure 15.15. As the national flag rises, play the national
        anthem. (You may use a flag image and anthem audio file from Listing 16.15.)
     */
    public static void ch16_26() {
        Exercise16_26.run();
    }

    /*
        (Display country flag and flag description) Listing 16.8, ComboBoxDemo.
        java, gives a program that lets the user view a country’s flag image and descrip-
        tion by selecting the country from a combo box. The description is a string
        coded in the program. Rewrite the program to read the text description from
        a file. Suppose the descriptions are stored in the files description0.txt, . . . ,
        and description8.txt under the text directory for the nine countries Canada,
        China, Denmark, France, Germany, India, Norway, the United Kingdom, and
        the United States, in this order.
     */
    public static void ch16_27() {
        Exercise16_27.run();
    }

    /*
        (Slide show) Programming Exercise 15.30 developed a slide show using images.
        Rewrite that program to develop a slide show using text files. Suppose that
        10 text files named slide0.txt, slide1.txt, . . . , slide9.txt are stored in the text
        directory. Each slide displays the text from one file. Each slide is shown for one
        second, and the slides are displayed in order. When the last slide finishes, the
        first slide is redisplayed, and so on. Use a text area to display the slide.
     */
    public static void ch16_28() {
        Exercise16_28.run();
    }

    /*
        (Display a calendar) Write a program that displays the calendar for the current
        month. You can use the Prior and Next buttons to show the calendar of the
        previous or next month. Display the dates in the current month in black and
        display the dates in the previous month and next month in gray, as shown in
        Figure 16.48
     */
    public static void ch16_29() {
        Exercise16_29.run();
    }

    /*
        (Pattern recognition: consecutive four equal numbers) Write a GUI program
        for Programming Exercise 8.19, as shown in Figures 16.49a–b. Let the user
        enter the numbers in the text fields in a grid of 6 rows and 7 columns. The user
        can click the Solve button to highlight a sequence of four equal numbers, if it
        exists. Initially, the values in the text fields are randomly filled with numbers
        from 0 to 9.
     */
    public static void ch16_30() {
        Exercise16_30.run();
    }

    /*
        (Game: connect four) Programming Exercise 8.20 enables two players to play
        the connect-four game on the console. Rewrite a GUI version for the program,
        as shown in Figure 16.49c. The program enables two players to place red and
        yellow discs in turn. To place a disk, the player needs to click an available cell.
        An available cell is unoccupied and its downward neighbor is occupied. The
        program flashes the four winning cells if a player wins, and reports no winners
        if all cells are occupied with no winners.
     */
    public static void ch16_31() {
        Exercise16_31.run();
    }

}
