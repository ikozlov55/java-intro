package exercises.ch8;

import exercises.utils.ArrayUtils;

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
            Enter a 3-by-4 matrix row by row:
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
        System.out.printf("Enter a %d-by-%d matrix row by row: \n", ROWS, COLUMNS);
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
        Enter a 4-by-4 matrix row by row:
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
        System.out.printf("Enter a %d-by-%d matrix row by row: \n", ROWS, COLUMNS);
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
        (Algebra: add two matrices) Write a method to add two matrices. The header of
        the method is as follows:
        public static double[][] addMatrix(double[][] a, double[][] b)
        In order to be added, the two matrices must have the same dimensions and the
        same or compatible types of elements. Let c be the resulting matrix. Each ele-
        ment cij is aij + bij. For example, for two 3 * 3 matrices a and b, c is
                a11 a12 a13   b11 b12 b13   a11+b11 a12+b12 a13+b13
                a21 a22 a23 * b21 b22 b23 = a11+b11 a12+b12 a13+b13
                a31 a32 a33   b31 b32 b33   a11+b11 a12+b12 a13+b13
        Write a test program that prompts the user to enter two 3 * 3 matrices and dis-
        plays their sum. Here is a sample run:
        Enter matrix1: 1 2 3 4 5 6 7 8 9
        Enter matrix2: 0 2 4 1 4.5 2.2 1.1 4.3 5.2
        The matrices are added as follows
        1.0 2.0 3.0   0.0 2.0 4.0   1.0 4.0 7.0
        4.0 5.0 6.0 + 1.0 4.5 2.2 = 5.0 9.5 8.2
        7.0 8.0 9.0   1.1 4.3 5.2   8.1 12.3 14.2
     */
    public static void ch8_5() {
        final int N = 3;
        System.out.print("Enter matrix1: ");
        double[][] matrix1 = ArrayUtils.inputMatrix(N, N);
        System.out.print("Enter matrix2: ");
        double[][] matrix2 = ArrayUtils.inputMatrix(N, N);

        double[][] added = addMatrix(matrix1, matrix2);
        System.out.println("The matrices are added as follows");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", matrix1[i][j]);
            }
            System.out.print(i == N / 2 ? " + " : "   ");
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", matrix2[i][j]);
            }
            System.out.print(i == N / 2 ? " = " : "   ");
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", added[i][j]);
            }
            System.out.println();
        }
    }

    public static double[][] addMatrix(double[][] a, double[][] b) {
        int N = a.length;
        double[][] result = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }


    /*
        (Algebra: multiply two matrices) Write a method to multiply two matrices. The
        header of the method is:
        public static double[][] multiplyMatrix(double[][] a, double[][] b)
        To multiply matrix a by matrix b, the number of columns in a must be the same as
        the number of rows in b, and the two matrices must have elements of the same or
        compatible types. Let c be the result of the multiplication. Assume the column size
        of matrix a is n. Each element cij is ai1 * b1j + ai2 * b2j + ... + ain * bnj.
        For example, for two 3 * 3 matrices a and b, c is

        a11 a12 a13   b11 b12 b13   c11 c12 c13
        a21 a22 a23 * b21 b22 b23 = c21 c22 c23
        a31 a32 a33   b31 b32 b33   c31 c32 c43

        where cij = ai1 * b1j + ai2 * b2j + ai3 * b3j.
        Write a test program that prompts the user to enter two 3 * 3 matrices and
        displays their product. Here is a sample run:
            Enter matrix1: 1 2 3 4 5 6 7 8 9
            Enter matrix2: 0 2 4 1 4.5 2.2 1.1 4.3 5.2
            The multiplication of the matrices is
            1 2 3   0   2.0 4.0     5.3  23.9 24
            4 5 6 * 1   4.5 2.2  =  11.6 56.3 58.2
            7 8 9   1.1 4.3 5.2     17.9 88.7 92.4
     */
    public static void ch8_6() {
        final int N = 3;
        System.out.print("Enter matrix1: ");
        double[][] matrix1 = ArrayUtils.inputMatrix(N, N);
        System.out.print("Enter matrix2: ");
        double[][] matrix2 = ArrayUtils.inputMatrix(N, N);

        double[][] multiplied = multiplyMatrix(matrix1, matrix2);
        System.out.println("The multiplication of the matrices is");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", matrix1[i][j]);
            }
            System.out.print(i == N / 2 ? " * " : "   ");
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", matrix2[i][j]);
            }
            System.out.print(i == N / 2 ? " = " : "   ");
            for (int j = 0; j < N; j++) {
                System.out.printf("%-5.1f", multiplied[i][j]);
            }
            System.out.println();
        }
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        int N = a.length;
        double[][] result = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double value = 0;
                for (int k = 0; k < N; k++) {
                    value += a[i][k] * b[k][j];
                }
                result[i][j] = value;
            }
        }
        return result;
    }

    /*
        (Points nearest to each other) Listing 8.3 gives a program that finds two points in
        a two-dimensional space nearest to each other. Revise the program so it finds two
        points in a three-dimensional space nearest to each other. Use a two-dimensional
        array to represent the points. Test the program using the following points:
        310 Chapter 8 Multidimensional Arrays
        double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
        {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2},
        {5.5, 4, -0.5}};
        The formula for computing the distance between two points (x1, y1, z1) and
        (x2, y2, z2) is sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2).
     */
    public static void ch8_7() {
        double[][] points = {
                {-1, 0, 3}, {-1, -1, -1}, {4, 1, 1},
                {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3},
                {-1.5, 4, 2}, {5.5, 4, -0.5}
        };

        // p1 and p2 are the indices in the points' array
        int p1 = 0, p2 = 1; // Initial two points
        double shortestDistance = distance3D(
                points[p1][0], points[p1][1], points[p1][2],
                points[p2][0], points[p2][1], points[p2][2]
        ); // Initialize shortestDistance

        // Compute distance for every two points
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = distance3D(
                        points[i][0], points[i][1], points[i][2],
                        points[j][0], points[j][1], points[j][2]
                ); // Find distance

                if (shortestDistance > distance) {
                    p1 = i; // Update p1
                    p2 = j; // Update p2
                    shortestDistance = distance; // Update shortestDistance
                }
            }
        }

        // Display result
        System.out.printf("The closest two points are (%.1f, %.1f, %.1f) and (%.1f, %.1f, %.1f)\n",
                points[p1][0], points[p1][1], points[p1][2],
                points[p2][0], points[p2][1], points[p2][2]
        );
    }

    public static double distance3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

    /*
        (All closest pairs of points) Revise Listing 8.3, FindNearestPoints.java, to display
        all closest pairs of points with the same minimum distance. Here is a sample run:
        Enter the number of points: 8
        Enter 8 points: 0 0 1 1 -1 -1 2 2 -2 -2 -3 -3 -4 -4 5 5
        The closest two points are (0.0, 0.0) and (1.0, 1.0)
        The closest two points are (0.0, 0.0) and (-1.0, -1.0)
        The closest two points are (1.0, 1.0) and (2.0, 2.0)
        The closest two points are (-1.0, -1.0) and (-2.0, -2.0)
        The closest two points are (-2.0, -2.0) and (-3.0, -3.0)
        The closest two points are (-3.0, -3.0) and (-4.0, -4.0)
        Their distance is 1.4142135623730951
     */
    public static void ch8_8() {
        System.out.print("Enter the number of points: ");
        int numberOfPoints = scanner.nextInt();
        double[][] points = new double[numberOfPoints][2];
        System.out.printf("Enter %d points: ", numberOfPoints);
        for (int i = 0; i < numberOfPoints; i++) {
            points[i][0] = scanner.nextDouble();
            points[i][1] = scanner.nextDouble();
        }

        double[][] closestPairs = new double[0][4];

        // Initialize shortestDistance
        double shortestDistance = distance(points[0][0], points[0][1], points[1][0], points[1][1]);

        // Compute distance for every two points
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // Find distance
                double distance = distance(points[i][0], points[i][1], points[j][0], points[j][1]);

                if (shortestDistance > distance) {
                    shortestDistance = distance; // Update shortestDistance
                    closestPairs = new double[][]{{points[i][0], points[i][1], points[j][0], points[j][1]}};
                } else if (shortestDistance == distance) {
                    closestPairs = Arrays.copyOf(closestPairs, closestPairs.length + 1);
                    double[] pair = new double[]{points[i][0], points[i][1], points[j][0], points[j][1]};
                    closestPairs[closestPairs.length - 1] = pair;
                }
            }
        }

        for (double[] pair : closestPairs) {
            System.out.printf("The closest two points are (%.1f, %.1f) and (%.1f, %.1f)\n",
                    pair[0], pair[1], pair[2], pair[3]
            );
        }
        System.out.println("Their distance is " + shortestDistance);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
        (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take turns
        marking an available cell in a 3 * 3 grid with their respective tokens (either X
        or O). When one player has placed three tokens in a horizontal, vertical, or diago-
        nal row on the grid, the game is over and that player has won. A draw (no winner)
        occurs when all the cells on the grid have been filled with tokens and neither
        player has achieved a win. Create a program for playing a tic-tac-toe game.
        The program prompts two players to alternately enter an X token and O token.
        Whenever a token is entered, the program redisplays the board on the console and
        determines the status of the game (win, draw, or continue). Here is a sample run:
        | | | |
        | | | |
        | | | |
        Enter a row (0, 1, or 2) for player X: 1
        Enter a column (0, 1, or 2) for player X: 1
        | | | |
        | |X| |
        | | | |
        Enter a row (0, 1, or 2) for player O: 1
        Enter a column (0, 1, or 2) for player O: 2
        | | | |
        | |X|O|
        | | | |
        Enter a row (0, 1, or 2) for player X:
        . . .
        |X| | |
        |O|X|O|
        | | |X|
        X player won
     */
    public static void ch8_9() {
        TicTacToeGame game = new TicTacToeGame();
        game.start();
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
