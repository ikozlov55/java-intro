package exercises.ch8;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Chapter8 {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    /*
        (Sum elements column by column) Write a method that returns the sum of all the
        elements in a specified column in a matrix using the following header:
        public static double sumColumn(double[][] m, int columnIndex)
        Write a test program that reads a 3-by-4 matrix and displays the sum of each
        column. Here is a sample run:
            Enter a 3−by−4 matrix row by row:
            1.5 2 3 4
            5.5 6 7 8
            9.5 1 3 1
            Sum of the elements at column 0 is 16.5
            Sum of the elements at column 1 is 9.0
            Sum of the elements at column 2 is 13.0
            Sum of the elements at column 3 is 13.0
     */
    public static void ch8_1() {
        final int ROWS = 3;
        final int COLUMNS = 4;
        double[][] matrix = new double[ROWS][COLUMNS];
        System.out.printf("Enter a %d−by−%d matrix row by row: \n", ROWS, COLUMNS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        for (int i = 0; i < COLUMNS; i++) {
            System.out.printf("Sum of the elements at column %d is %.1f\n", i, sumOfColumn(i, matrix));
        }
    }

    private static double sumOfColumn(int column, double[][] matrix) {
        return Arrays.stream(matrix).map(row -> row[column]).reduce(0.0, Double::sum);
    }

    /*
        (Sum the major diagonal in a matrix) Write a method that sums all the numbers
        in the major diagonal in an n * n matrix of double values using the following
        header:
        public static double sumMajorDiagonal(double[][] m)
        Write a test program that reads a 4-by-4 matrix and displays the sum of all its
        elements on the major diagonal. Here is a sample run:
        Enter a 4−by−4 matrix row by row:
        1 2 3 4.0
        5 6.5 7 8
        9 10 11 12
        13 14 15 16
        Sum of the elements in the major diagonal is 34.5
     */
    public static void ch8_2() {
        final int ROWS = 4;
        final int COLUMNS = 4;
        double[][] matrix = new double[ROWS][COLUMNS];
        System.out.printf("Enter a %d−by−%d matrix row by row: \n", ROWS, COLUMNS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.printf("Sum of the elements in the major diagonal is %.1f\n", sumMajorDiagonal(matrix));
    }

    public static double sumMajorDiagonal(double[][] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i][i];
        }
        return sum;
    }

    /*
        (Sort students on grades) Rewrite Listing 8.2, GradeExam.java, to display the
        students in increasing order of the number of correct answers.
     */
    public static void ch8_3() {
        // Students' answers to the questions
        char[][] answers = {
                {'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
                {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
                {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
                {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}
        };

        // Key to the questions
        char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};

        int[][] grades = new int[answers.length][2];

        // Grade all answers
        for (int i = 0; i < answers.length; i++) {
            grades[i][0] = i;
            // Grade one student
            for (int j = 0; j < answers[i].length; j++) {
                if (answers[i][j] == keys[j])
                    grades[i][1]++;
            }
        }

        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades.length - 1; j++) {
                if (grades[j][1] > grades[j + 1][1]) {
                    int[] temp = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < grades.length; i++) {
            System.out.printf("Student %d's correct count is %d\n", grades[i][0], grades[i][1]);
        }

    }

    /*
        Compute the weekly hours for each employee) Suppose the weekly hours for all
        employees are stored in a two-dimensional array. Each row records an employ-
        ee’s seven-day work hours with seven columns. For example, the following array
        stores the work hours for eight employees. Write a program that displays employ-
        ees and their total hours in decreasing order of the total hours.
                    Su  M  T  W Th  F Sa
        Employee 0   2  4  3  4  5  8  8
        Employee 1   7  3  4  3  3  4  4
        Employee 2   3  3  4  3  3  2  2
        Employee 3   9  3  4  7  3  4  1
        Employee 4   3  5  4  3  6  3  8
        Employee 5   3  4  4  6  3  4  4
        Employee 6   3  7  4  8  3  8  4
        Employee 7   6  3  5  9  2  7  9
     */
    public static void ch8_4() {
        int[][] weeklyHours = {
                {2, 4, 3, 4, 5, 8, 8},
                {7, 3, 4, 3, 3, 4, 4},
                {3, 3, 4, 3, 3, 2, 2},
                {9, 3, 4, 7, 3, 4, 1},
                {3, 5, 4, 3, 6, 3, 8},
                {3, 4, 4, 6, 3, 4, 4},
                {3, 7, 4, 8, 3, 8, 4},
                {6, 3, 5, 9, 2, 7, 9},
        };
        int[][] totalHours = new int[weeklyHours.length][2];

        for (int i = 0; i < weeklyHours.length; i++) {
            totalHours[i][0] = i;
            for (int j = 0; j < 7; j++) {
                totalHours[i][1] += weeklyHours[i][j];
            }
        }

        for (int i = 0; i < totalHours.length; i++) {
            for (int j = 0; j < totalHours.length - 1; j++) {
                if (totalHours[j][1] < totalHours[j + 1][1]) {
                    int[] temp = totalHours[j];
                    totalHours[j] = totalHours[j + 1];
                    totalHours[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < totalHours.length; i++) {
            System.out.printf("Employee's %d weekly hours are: %d\n", totalHours[i][0], totalHours[i][1]);
        }
    }

    /*

     */
    public static void ch8_5() {

    }

    /*

     */
    public static void ch8_6() {

    }

    /*

     */
    public static void ch8_7() {

    }

    /*

     */
    public static void ch8_8() {

    }

    /*

     */
    public static void ch8_9() {

    }

    /*

     */
    public static void ch8_10() {

    }

    /*

     */
    public static void ch8_11() {

    }

    /*

     */
    public static void ch8_12() {

    }

    /*

     */
    public static void ch8_13() {

    }

    /*

     */
    public static void ch8_14() {

    }

    /*

     */
    public static void ch8_15() {

    }

    /*

     */
    public static void ch8_16() {

    }

    /*

     */
    public static void ch8_17() {

    }

    /*

     */
    public static void ch8_18() {

    }

    /*

     */
    public static void ch8_19() {

    }

    /*

     */
    public static void ch8_20() {

    }

    /*

     */
    public static void ch8_21() {

    }

    /*

     */
    public static void ch8_22() {

    }

    /*

     */
    public static void ch8_23() {

    }

    /*

     */
    public static void ch8_24() {

    }

    /*

     */
    public static void ch8_25() {

    }

    /*

     */
    public static void ch8_26() {

    }

    /*

     */
    public static void ch8_27() {

    }

    /*

     */
    public static void ch8_28() {

    }

    /*

     */
    public static void ch8_29() {

    }

    /*

     */
    public static void ch8_30() {

    }

    /*

     */
    public static void ch8_31() {

    }

    /*

     */
    public static void ch8_32() {

    }

    /*

     */
    public static void ch8_33() {

    }

    /*

     */
    public static void ch8_34() {

    }

    /*

     */
    public static void ch8_35() {

    }

    /*

     */
    public static void ch8_36() {

    }

    /*

     */
    public static void ch8_37() {

    }
}
