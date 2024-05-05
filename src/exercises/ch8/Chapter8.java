package exercises.ch8;

import exercises.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        char[][] answers = {{'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'}, {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'}, {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'}, {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}, {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}};

        // Key to the questions
        char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};

        int[][] grades = new int[answers.length][2];

        // Grade all answers
        for (int i = 0; i < answers.length; i++) {
            grades[i][0] = i;
            // Grade one student
            for (int j = 0; j < answers[i].length; j++) {
                if (answers[i][j] == keys[j]) grades[i][1]++;
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
        int[][] weeklyHours = {{2, 4, 3, 4, 5, 8, 8}, {7, 3, 4, 3, 3, 4, 4}, {3, 3, 4, 3, 3, 2, 2}, {9, 3, 4, 7, 3, 4, 1}, {3, 5, 4, 3, 6, 3, 8}, {3, 4, 4, 6, 3, 4, 4}, {3, 7, 4, 8, 3, 8, 4}, {6, 3, 5, 9, 2, 7, 9},};
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
        double[][] points = {{-1, 0, 3}, {-1, -1, -1}, {4, 1, 1}, {2, 0.5, 9}, {3.5, 2, -1}, {3, 1.5, 3}, {-1.5, 4, 2}, {5.5, 4, -0.5}};

        // p1 and p2 are the indices in the points' array
        int p1 = 0, p2 = 1; // Initial two points
        double shortestDistance = distance3D(points[p1][0], points[p1][1], points[p1][2], points[p2][0], points[p2][1], points[p2][2]); // Initialize shortestDistance

        // Compute distance for every two points
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = distance3D(points[i][0], points[i][1], points[i][2], points[j][0], points[j][1], points[j][2]); // Find distance

                if (shortestDistance > distance) {
                    p1 = i; // Update p1
                    p2 = j; // Update p2
                    shortestDistance = distance; // Update shortestDistance
                }
            }
        }

        // Display result
        System.out.printf("The closest two points are (%.1f, %.1f, %.1f) and (%.1f, %.1f, %.1f)\n", points[p1][0], points[p1][1], points[p1][2], points[p2][0], points[p2][1], points[p2][2]);
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
            System.out.printf("The closest two points are (%.1f, %.1f) and (%.1f, %.1f)\n", pair[0], pair[1], pair[2], pair[3]);
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
        (Largest row and column) Write a program that randomly fills in 0s and 1s into
        a 4-by-4 matrix, prints the matrix, and finds the first row and column with the
        most 1s. Here is a sample run of the program:
        0011
        0011
        1101
        1010
        The largest row index: 2
        The largest column index: 2
     */
    public static void ch8_10() {
        final int N = 4;
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Math.random() < 0.5 ? 1 : 0;
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        int maxRowIndex = 0;
        int maxOnesInRow = 0;
        int maxColumnIndex = 0;
        int maxOnesInColumn = 0;

        for (int i = 0; i < N; i++) {
            int onesInRow = 0;
            int onesColumn = 0;
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    onesInRow++;
                }
                if (matrix[j][i] == 1) {
                    onesColumn++;
                }
            }
            if (onesInRow > maxOnesInRow) {
                maxOnesInRow = onesInRow;
                maxRowIndex = i;
            }
            if (onesColumn > maxOnesInColumn) {
                maxOnesInColumn = onesColumn;
                maxColumnIndex = i;
            }
        }

        System.out.println("The largest row index: " + maxRowIndex);
        System.out.println("The largest column index: " + maxColumnIndex);
    }

    /*
        (Game: nine heads and tails) Nine coins are placed in a 3-by-3 matrix with some
        face up and some face down. You can represent the state of the coins using a
        3-by-3 matrix with values 0 (heads) and 1 (tails). Here are some examples:
        0 0 0   1 0 1   1 1 0   1 0 1   1 0 0
        0 1 0   0 0 1   1 0 0   1 1 0   1 1 1
        0 0 0   1 0 0   0 0 1   1 0 0   1 1 0
        Each state can also be represented using a binary number. For example, the pre-
        ceding matrices correspond to the numbers
        000010000 101001100 110100001 101110100 100111110
        There are a total of 512 possibilities, so you can use decimal numbers 0, 1, 2, 3, . . . ,
        and 511 to represent all states of the matrix. Write a program that prompts the
        user to enter a number between 0 and 511 and displays the corresponding matrix
        with the characters H and T. In the following sample run, the user entered 7, which
        corresponds to 000000111. Since 0 stands for H and 1 for T, the output is correct.
            Enter a number between 0 and 511: 7
            H H H
            H H H
            T T T
     */
    public static void ch8_11() {
        final int MIN = 0;
        final int MAX = 511;
        final int N = 3;
        System.out.printf("Enter a number between %d and %d: ", MIN, MAX);
        int n = scanner.nextInt();
        if (n < MIN || n > MAX) {
            System.out.println("Invalid input");
        }
        String binary = String.format("%9s", Integer.toBinaryString(n)).replace(" ", "0");
        char[][] matrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = binary.charAt(i * N + j) == '0' ? 'H' : 'T';
            }
        }
        System.out.println(binary);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        (Financial application: compute tax) Rewrite Listing 3.5, ComputeTax.java,
        using arrays. For each filing status, there are six tax rates. Each rate is applied
        to a certain amount of taxable income. For example, from the taxable income of
        $400,000 for a single filer, $8,350 is taxed at 10%, (33,950 - 8,350) at 15%,
        (82,250 - 33,950) at 25%, (171,550 - 82,550) at 28%, (372,550 - 82,250) at
        33%, and (400,000 - 372,950) at 36%. The six rates are the same for all filing
        statuses, which can be represented in the following array:
        double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
        The brackets for each rate for all the filing statuses can be represented in a two-
        dimensional array as follows:
        int[][] brackets = {
                {8350, 33950, 82250, 171550, 372950},// Single filer
                {16700, 67900, 137050, 20885, 372950}, // Married jointly −or qualifying widow(er)
                {8350, 33950, 68525, 104425, 186475},// Married separately
                {11950, 45500, 117450, 190200, 372950} // Head of household
        };
        Suppose the taxable income is $400,000 for single filers. The tax can be com-
        puted as follows:
        tax = brackets[0][0] * rates[0] +
        (brackets[0][1] – brackets[0][0]) * rates[1] +
        (brackets[0][2] – brackets[0][1]) * rates[2] +
        (brackets[0][3] – brackets[0][2]) * rates[3] +
        (brackets[0][4] – brackets[0][3]) * rates[4] +
        (400000 – brackets[0][4]) * rates[5];
     */
    public static void ch8_12() {
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Taxable", "Single", "Married Joint", "Married", "Head of");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "Income", "", "or Qualifying", "Separate", "House hold");
        System.out.printf("%-14s%-14s%-18s%-14s%-14s\n", "", "", "Widow(er)", "", "");
        for (double income = 50_000; income <= 60_000; income += 50) {
            System.out.printf("%-14d%-14d%-18d%-14d%-14d\n", Math.round(income), Math.round(computeTax(0, income)), Math.round(computeTax(1, income)), Math.round(computeTax(2, income)), Math.round(computeTax(3, income)));
        }
    }

    public static double computeTax(int status, double income) {
        double[] rates = {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
        int[][] brackets = {{8350, 33950, 82250, 171550, 372950},// Single filer
                {16700, 67900, 137050, 20885, 372950}, // Married jointly −or qualifying widow(er)
                {8350, 33950, 68525, 104425, 186475},// Married separately
                {11950, 45500, 117450, 190200, 372950} // Head of household
        };
        int[] m = brackets[status];
        if (income <= m[0]) return income * rates[0];
        else if (income <= m[1]) return m[0] * rates[0] + (income - m[0]) * rates[1];
        else if (income <= m[2]) return m[0] * rates[0] + (m[1] - m[0]) * rates[1] + (income - m[1]) * rates[2];
        else if (income <= m[3])
            return m[0] * rates[0] + (m[1] - m[0]) * rates[1] + (m[2] - m[1]) * rates[2] + (income - m[2]) * rates[3];
        else if (income <= m[4])
            return m[0] * rates[0] + (m[1] - m[0]) * rates[1] + (m[2] - m[1]) * rates[2] + (m[3] - m[2]) * rates[3] + (income - m[3]) * rates[4];
        else
            return m[0] * rates[0] + (m[1] - m[0]) * rates[1] + (m[2] - m[1]) * rates[2] + (m[3] - m[2]) * rates[3] + (m[4] - m[3]) * rates[4] + (income - m[4]) * rates[5];
    }

    /*
        (Locate the largest element) Write the following method that returns the location
        of the largest element in a two-dimensional array:
        public static int[] locateLargest(double[][] a)
        The return value is a one-dimensional array that contains two elements. These
        two elements indicate the row and column indices of the largest element in the
        two-dimensional array. If there are more than one largest element, return the
        smallest row index and then the smallest column index.
        Write a test program that prompts the user to enter a two-dimensional array and
        displays the location of the largest element in the array. Here is a sample run:
        Enter the number of rows and columns of the array: 3 4
        Enter the array:
        23.5 35 2 10
        4.5 3 45 3.5
        35 44 5.5 9.6
        The location of the largest element is at (1, 2)
     */
    public static void ch8_13() {
        System.out.print("Enter the number of rows and columns of the array: ");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        System.out.println("Enter the array: ");
        double[][] matrix = ArrayUtils.inputMatrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        int[] max = locateLargest(matrix);
        System.out.printf("The location of the largest element is at (%d, %d)\n", max[0], max[1]);
    }

    public static int[] locateLargest(double[][] a) {
        int[] result = {0, 0};
        double maxValue = a[0][0];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > maxValue) {
                    maxValue = a[i][j];
                    result = new int[]{i, j};
                }
            }

        }

        return result;
    }

    /*
        (Explore matrix) Write a program that prompts the user to enter the length of a
        square matrix, randomly fills in 0s and 1s into the matrix, prints the matrix, and
        finds the rows, columns, and diagonals with all 0s or 1s. Here is a sample run of
        the program:
            Enter the size for the matrix: 4
            0111
            0000
            0100
            1111
            All 0s on row 2
            All 1s on row 4
            No same numbers on a column
            No same numbers on the major diagonal
            No same numbers on the sub−diagonal
     */
    public static void ch8_14() {
        System.out.print("Enter the size for the matrix: ");
        int N = scanner.nextInt();
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Math.random() < 0.5 ? 1 : 0;
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        int[][] sameRows = new int[0][2];
        int[][] sameColumns = new int[0][2];
        for (int i = 0; i < N; i++) {
            boolean isSameInRow = true;
            boolean isSameInColumn = true;
            int rowValue = matrix[i][0];
            int columnValue = matrix[0][i];
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != rowValue) {
                    isSameInRow = false;
                }
                if (matrix[j][i] != columnValue) {
                    isSameInColumn = false;
                }
            }
            if (isSameInRow) {
                sameRows = Arrays.copyOf(sameRows, sameRows.length + 1);
                sameRows[sameRows.length - 1] = new int[]{rowValue, i + 1};
            }
            if (isSameInColumn) {
                sameColumns = Arrays.copyOf(sameColumns, sameColumns.length + 1);
                sameColumns[sameColumns.length - 1] = new int[]{columnValue, i + 1};
            }
        }
        boolean isSameOnMajorDiagonal = true;
        int majorDiagonalValue = matrix[0][0];
        for (int i = 0, j = 0; i < N && j < N; i++, j++) {
            if (matrix[i][j] != majorDiagonalValue) {
                isSameOnMajorDiagonal = false;
                break;
            }
        }
        boolean isSameOnSubDiagonal = true;
        int subDiagonalValue = matrix[0][N - 1];
        for (int i = 0, j = N - 1; i < N && j >= 0; i++, j--) {
            if (matrix[i][j] != subDiagonalValue) {
                isSameOnSubDiagonal = false;
                break;
            }
        }

        if (sameRows.length > 0) {
            for (int[] values : sameRows) {
                System.out.printf("All %ds on row %d\n", values[0], values[1]);
            }
        } else {
            System.out.println("No same numbers on a row");
        }

        if (sameColumns.length > 0) {
            for (int[] values : sameColumns) {
                System.out.printf("All %ds on column %d\n", values[0], values[1]);
            }
        } else {
            System.out.println("No same numbers on a column");
        }

        if (isSameOnMajorDiagonal) {
            System.out.printf("All %ds on major diagonal\n", majorDiagonalValue);
        } else {
            System.out.println("No same numbers on the major diagonal");
        }

        if (isSameOnSubDiagonal) {
            System.out.printf("All %ds on sub−diagonal\n", subDiagonalValue);
        } else {
            System.out.println("No same numbers on the sub−diagonal");
        }

    }

    /*
        (Geometry: same line?) Programming Exercise 6.39 gives a method for testing
        whether three points are on the same line.
        Write the following method to test whether all the points in the array points are
        on the same line:
        public static boolean sameLine(double[][] points)
        Write a program that prompts the user to enter five points and displays whether
        they are on the same line. Here are sample runs:
            Enter five points: 3.4 2 6.5 9.5 2.3 2.3 5.5 5 -5 4
            The five points are not on the same line
            Enter five points: 1 1 2 2 3 3 4 4 5 5
            The five points are on the same line
     */
    public static void ch8_15() {
        double[][] points = new double[5][2];
        System.out.print("Enter five points: ");
        for (int i = 0; i < points.length; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points[i] = new double[]{x, y};
        }
        if (sameLine(points)) {
            System.out.println("The five points are on the same line");
        } else {
            System.out.println("The five points are not on the same line");
        }
    }

    public static boolean sameLine(double[][] points) {
        if (points.length <= 2) return true;
        double[] first = points[0];
        double[] last = points[points.length - 1];
        boolean result = true;

        for (int i = 1; i < points.length - 1; i++) {
            if (!onTheSameLine(first, last, points[i])) return false;
        }

        return result;
    }

    public static boolean onTheSameLine(double[] p0, double[] p1, double[] p2) {
        double x0 = p0[0];
        double y0 = p0[1];
        double x1 = p1[0];
        double y1 = p1[1];
        double x2 = p2[0];
        double y2 = p2[1];
        return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0) == 0;
    }


    /*
        (Sort two-dimensional array) Write a method to sort a two-dimensional array
        using the following header:
        public static void sort(int m[][])
        The method performs a primary sort on rows, and a secondary sort on columns.
        For example, the following array
        {{4, 2},{1, 7},{4, 5},{1, 2},{1, 1},{4, 1}}
        will be sorted to
        {{1, 1},{1, 2},{1, 7},{4, 1},{4, 2},{4, 5}}.
     */
    public static void ch8_16() {
        int[][] array = {{4, 2}, {1, 7}, {4, 5}, {1, 2}, {1, 1}, {4, 1}};
        sort(array);
        System.out.print("[");
        Arrays.stream(array).map(Arrays::toString).forEach(System.out::print);
        System.out.print("]");
    }

    public static void sort(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length - 1; j++) {
                for (int k = 0; k < m[j].length; k++) {
                    if (m[j][k] < m[j + 1][k]) break;
                    if (m[j][k] > m[j + 1][k]) {
                        int[] temp = m[j];
                        m[j] = m[j + 1];
                        m[j + 1] = temp;
                        break;
                    }
                }
            }
        }
    }

    /*
        (Financial tsunami) Banks lend money to each other. In tough economic times, if a
        bank goes bankrupt, it may not be able to pay back the loan. A bank’s total assets are
        its current balance plus its loans to other banks. The diagram in Figure 8.8 shows
        five banks. The banks’ current balances are 25, 125, 175, 75, and 181 million dol-
        lars, respectively. The directed edge from node 1 to node 2 indicates that bank 1
        lends 40 million dollars to bank 2.
        If a bank’s total assets are under a certain limit, the bank is unsafe. The money it
        borrowed cannot be returned to the lender, and the lender cannot count the loan in
        its total assets. Consequently, the lender may also be unsafe, if its total assets are
        under the limit. Write a program to find all the unsafe banks. Your program reads
        the input as follows. It first reads two integers n and limit, where n indicates the
        number of banks and limit is the minimum total assets for keeping a bank safe. It
        then reads n lines that describe the information for n banks with IDs from 0 to n−1.
        The first number in the line is the bank’s balance, the second number indicates
        the number of banks that borrowed money from the bank, and the rest are pairs
        of two numbers. Each pair describes a borrower. The first number in the pair is
        the borrower’s ID and the second is the amount borrowed. For example, the input
        for the five banks in Figure 8.8 is as follows (note the limit is 201):
        5 201
        25 2 1 100.5 4 320.5
        125 2 2 40 3 85
        175 2 0 125 3 75
        75 1 0 125
        181 1 2 125
        The total assets of bank 3 are (75 + 125), which is under 201, so bank 3 is unsafe.
        After bank 3 becomes unsafe, the total assets of bank 1 fall below (125 + 40).
        Thus, bank 1 is also unsafe. The output of the program should be
        Unsafe banks are 3 1
        (Hint: Use a two-dimensional array borrowers to represent loans. borrow-
        ers[i][j] indicates the loan that bank i provides to bank j. Once bank j
        becomes unsafe, borrowers[i][j] should be set to 0.)
     */
    public static void ch8_17() {
        System.out.print("Enter a number of banks and safe assets limit: ");
        int n = scanner.nextInt();
        int limit = scanner.nextInt();
        double[] balances = new double[n];
        double[][] borrowers = new double[n][n];
        for (int i = 0; i < n; i++) {
            balances[i] = scanner.nextDouble();
            int borrowersNumber = scanner.nextInt();
            for (int j = 0; j < borrowersNumber; j++) {
                int borrowerId = scanner.nextInt();
                double amountBorrowed = scanner.nextDouble();
                borrowers[i][borrowerId] = amountBorrowed;
            }
        }
        boolean[] unsafeBanks = new boolean[n];
        for (int i = 0; i < n; i++) {
            double amount = balances[i] + Arrays.stream(borrowers[i]).sum();
            if (amount < limit && !unsafeBanks[i]) {
                unsafeBanks[i] = true;
                for (int j = 0; j < n; j++) {
                    borrowers[j][i] = 0;
                }
                i = 0;
            }
        }
        System.out.print("Unsafe banks are");
        for (int i = 0; i < n; i++) {
            if (unsafeBanks[i]) {
                System.out.print(" " + i);
            }
        }
    }

    /*
        (Shuffle rows) Write a method that shuffles the rows in a two-dimensional int
        array using the following header:
        public static void shuffle(int[][] m)
        Write a test program that shuffles the following matrix:
        int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
     */
    public static void ch8_18() {
        int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
        shuffle(m);
        System.out.print("[");
        Arrays.stream(m).map(Arrays::toString).forEach(System.out::print);
        System.out.print("]");
    }

    public static void shuffle(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int index = (int) (Math.random() * m.length);
            int[] temp = m[i];
            m[i] = m[index];
            m[index] = temp;
        }
    }

    /*
        (Pattern recognition: four consecutive equal numbers) Write the following
        method that tests whether a two-dimensional array has four consecutive numbers
        of the same value, either horizontally, vertically, or diagonally:
        public static boolean isConsecutiveFour(int[][] values)
        Write a test program that prompts the user to enter the number of rows and
        columns of a two-dimensional array then the values in the array, and displays true
        if the array contains four consecutive numbers with the same value. Otherwise,
        the program displays false.
     */
    public static void ch8_19() {
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        System.out.println(isConsecutiveFour(matrix));
    }

    public static boolean isConsecutiveFour(int[][] values) {
        if (values.length == 0) return false;
        final int n = 4;
        int rows = values.length;
        int columns = values[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = values[i][j];
                int counter = 0;
                //down
                for (int k = i; k < rows; k++) {
                    if (values[k][j] == value) {
                        counter++;
                        if (counter == n) {
                            System.out.printf("down [%d,%d] - [%d,%d]\n", i, j, k, j);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //right
                for (int k = j; k < columns; k++) {
                    if (values[i][k] == value) {
                        counter++;
                        if (counter == n) {
                            System.out.printf("right [%d,%d] - [%d,%d]\n", i, j, i, k);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //down left
                for (int k = i, l = j; k < rows && l >= 0; k++, l--) {
                    if (values[k][l] == value) {
                        counter++;
                        if (counter == n) {
                            System.out.printf("down left [%d,%d] - [%d,%d]\n", i, j, k, l);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //down right
                for (int k = i, l = j; k < rows && l < columns; k++, l++) {
                    if (values[k][l] == value) {
                        counter++;
                        if (counter == n) {
                            System.out.printf("down right [%d,%d] - [%d,%d]\n", i, j, k, l);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }


    /*
        (Game: connect four) Connect four is a two-player board game in which the
        players alternately drop colored disks into a seven-column, six-row vertically
        suspended grid, as shown below
        The objective of the game is to connect four same-colored disks in a row, a col-
        umn, or a diagonal before your opponent can do likewise. The program prompts
        two players to drop a red or yellow disk alternately. Whenever a disk is dropped,
        the program redisplays the board on the console and determines the status of the
        game (win, draw, or continue). Here is a sample run:
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        Drop a red disk at column (0–6): 0
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        |R| | | | | | |
        ---------------
        Drop a yellow disk at column (0–6): 3
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        | | | | | | | |
        |R| | |Y| | | |
        . . .
        . . .
        . . .
        Drop a yellow disk at column (0–6): 6
        | | | | | | | |
        | | | | | | | |
        | | | |R| | | |
        | | | |Y|R|Y| |
        | | |R|Y|Y|Y|Y|
        |R|Y|R|Y|R|R|R|
        The yellow player won
     */
    public static void ch8_20() {
        ConnectFourGame game = new ConnectFourGame();
        game.start();
    }

    /*
        (Central city) Given a set of cities, the central city is the city that has the shortest
        total distance to all other cities. Write a program that prompts the user to enter
        the number of cities and the locations of the cities (coordinates), and finds the
        central city and its total distance to all other cities.
        Enter the number of cities: 5
        Enter the coordinates of the cities:
        2.5 5 5.1 3 1 9 5.4 54 5.5 2.1
        The central city is at (2.5, 5.0)
        The total distance to all other cities is 60.81
     */
    public static void ch8_21() {
        System.out.print("Enter the number of cities: ");
        int cities = scanner.nextInt();
        System.out.println("Enter the coordinates of the cities:");
        double[][] coordinates = new double[5][2];
        for (int i = 0; i < cities; i++) {
            coordinates[i][0] = scanner.nextDouble();
            coordinates[i][1] = scanner.nextDouble();
        }
        int centralCity = 0;
        double minTotalDistance = 0;
        for (int i = 0; i < cities; i++) {
            double totalDistance = 0;
            double x1 = coordinates[i][0];
            double y1 = coordinates[i][1];
            for (int j = 0; j < cities; j++) {
                double x2 = coordinates[j][0];
                double y2 = coordinates[j][1];
                totalDistance += distance(x1, y1, x2, y2);
            }
            if (i == 0) {
                minTotalDistance = totalDistance;
            }
            if (totalDistance < minTotalDistance) {
                centralCity = i;
                minTotalDistance = totalDistance;
            }
        }

        double x = coordinates[centralCity][0];
        double y = coordinates[centralCity][1];
        System.out.printf("The central city is at (%.1f, %.1f)\n", x, y);
        System.out.printf("The total distance to all other cities is %.2f\n", minTotalDistance);
    }

    /*
        (Even number of 1s) Write a program that generates a 6-by-6 two-dimensional
        matrix filled with 0s and 1s, displays the matrix, and checks if every row and
        every column have an even number of 1s
     */
    public static void ch8_22() {
        final int N = 6;
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Math.random() < 0.5 ? 1 : 0;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        boolean result = true;
        for (int i = 0; i < N; i++) {
            int onesInRow = 0;
            int onesInColumn = 0;
            for (int j = 0; j < N; j++) {
                onesInRow += matrix[i][j];
                onesInColumn += matrix[j][i];
            }
            if (onesInRow % 2 != 0 || onesInColumn % 2 != 0) {
                result = false;
                break;
            }
        }
        System.out.println(result);
    }

    /*
        (Game: find the flipped cell) Suppose you are given a 6-by-6 matrix filled with
        0s and 1s. All rows and all columns have an even number of 1s. Let the user flip
        one cell (i.e., flip from 1 to 0 or from 0 to 1) and write a program to find which
        cell was flipped. Your program should prompt the user to enter a 6-by-6 array
        with 0s and 1s and find the first row r and first column c where the even number
        of the 1s property is violated (i.e., the number of 1s is not even). The flipped cell
        is at (r, c). Here is a sample run
        Enter a 6−by−6 matrix row by row:
        1 1 1 0 1 1
        1 1 1 1 0 0
        0 1 0 1 1 1
        1 1 1 1 1 1
        0 1 1 1 1 0
        1 0 0 0 0 1
        The flipped cell is at (0, 1)
     */
    public static void ch8_23() {
        final int N = 6;
        System.out.println("Enter a 6−by−6 matrix row by row:");
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                final int column = j;
                boolean oddOnesInRow = Arrays.stream(matrix[i]).sum() % 2 != 0;
                boolean oddOnesInColumn = IntStream.range(0, N).map(x -> matrix[x][column]).sum() % 2 != 0;
                if (oddOnesInRow && oddOnesInColumn) {
                    System.out.printf("The flipped cell is at (%d, %d)\n", i, j);
                    break;
                }
            }
        }
    }

    /*
        (Check Sudoku solution) Listing 8.4 checks whether a solution is valid by check-
        ing whether every number is valid in the board. Rewrite the program by checking
        whether every row, every column, and every small box has the numbers 1 to 9.

            Enter a Sudoku puzzle solution:
            9 6 3 1 7 4 2 5 8
            1 7 8 3 2 5 6 4 9
            2 5 4 6 8 9 7 3 1
            8 2 1 4 3 7 5 9 6
            4 9 6 8 5 2 3 1 7
            7 3 5 9 6 1 8 2 4
            5 8 9 7 1 3 4 6 2
            3 1 7 2 4 6 9 8 5
            6 4 2 5 9 8 1 7 3
            Valid solution
     */
    public static void ch8_24() {
        // Read a Sudoku solution
        int[][] grid = readASolution();
        System.out.println(isValid(grid) ? "Valid solution" : "Invalid solution");
    }

    public static int[][] readASolution() {
        System.out.println("Enter a Sudoku puzzle solution:");
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = scanner.nextInt();

        return grid;
    }

    /**
     * Check whether a solution is valid
     */
    public static boolean isValid(int[][] grid) {
        // check rows
        for (int row = 0; row < 9; row++) {
            if (!hasNumbersOneToNine(grid[row])) {
                return false;
            }
        }

        // check columns
        for (int i = 0; i < 9; i++) {
            final int column = i;
            int[] columnArray = IntStream.range(0, 9).map(x -> grid[x][column]).toArray();
            if (!hasNumbersOneToNine(columnArray)) {
                return false;
            }
        }

        // check 3x3 boxes

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int[] boxArray = new int[9];
                for (int k = 0; k < 9; k++) {
                    boxArray[k] = grid[k / 3 + i * 3][k % 3 + j * 3];
                }
                if (!hasNumbersOneToNine(boxArray)) {
                    return false;
                }
            }
        }
        return true; // The solution is valid
    }

    public static boolean hasNumbersOneToNine(int[] array) {
        int[] oneToNineArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy);
        return Arrays.equals(copy, oneToNineArray);
    }

    /*
        (Markov matrix) An n * n matrix is called a positive Markov matrix if each
        element is positive and the sum of the elements in each column is 1. Write the
        following method to check whether a matrix is a Markov matrix:
        public static boolean isMarkovMatrix(double[][] m)
        Write a test program that prompts the user to enter a 3 * 3 matrix of double
        values and tests whether it is a Markov matrix. Here are sample runs:
            Enter a 3−by−3 matrix row by row:
            0.15 0.875 0.375
            0.55 0.005 0.225
            0.30 0.12 0.4
            It is a Markov matrix

            Enter a 3−by−3 matrix row by row:
            0.95 -0.875 0.375
            0.65 0.005 0.225
            0.30 0.22 -0.4
            It is not a Markov matrix
     */
    public static void ch8_25() {
        final int N = 3;
        System.out.println("Enter a 3−by−3 matrix row by row:");
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        System.out.printf("It %s a Markov matrix\n", isMarkov(matrix) ? "is" : "is not");
    }

    public static boolean isMarkov(double[][] matrix) {
        final int N = matrix.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            final int column = i;
            if (IntStream.range(0, N).mapToDouble(x -> matrix[x][column]).sum() != 1) {
                return false;
            }
        }
        return true;
    }

    /*
        (Row sorting) Implement the following method to sort the rows in a two-
        dimensional array. A new array is returned and the original array is intact.
        public static double[][] sortRows(double[][] m)
        Write a test program that prompts the user to enter a 3 * 3 matrix of double
        values and displays a new row-sorted matrix. Here is a sample run:
            Enter a 3−by−3 matrix row by row:
            0.15 0.875 0.375
            0.55 0.005 0.225
            0.30 0.12 0.4
            The row−sorted array is
            0.15 0.375 0.875
            0.005 0.225 0.55
            0.12 0.30 0.4
     */
    public static void ch8_26() {
        final int N = 3;
        System.out.println("Enter a 3−by−3 matrix row by row:");
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        double[][] sorted = sortRows(matrix);
        System.out.println("The row−sorted array is");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sorted[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] sortRows(double[][] m) {
        double[][] result = Arrays.copyOf(m, m.length);
        Arrays.stream(result).forEach(Arrays::sort);
        return result;
    }

    /*
        (Column sorting) Implement the following method to sort the columns in a
        two-dimensional array. A new array is returned and the original array is intact.
        public static double[][] sortColumns(double[][] m)
        318 Chapter 8 Multidimensional Arrays
        Write a test program that prompts the user to enter a 3 * 3 matrix of double
        values and displays a new column-sorted matrix. Here is a sample run:
            Enter a 3−by−3 matrix row by row:
            0.15 0.875 0.375
            0.55 0.005 0.225
            0.30 0.12 0.4
            The column−sorted array is
            0.15 0.0050 0.225
            0.3 0.12 0.375
            0.55 0.875 0.4
     */
    public static void ch8_27() {
        final int N = 3;
        System.out.println("Enter a 3−by−3 matrix row by row:");
        double[][] matrix = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        double[][] sorted = sortColumns(matrix);
        System.out.println("The column−sorted array is");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sorted[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] sortColumns(double[][] m) {
        double[][] result = Arrays.copyOf(m, m.length);
        int N = m.length;
        for (int i = 0; i < N; i++) {
            final int column = i;
            double[] columnArray = IntStream.range(0, N)
                    .mapToDouble(x -> result[x][column])
                    .sorted()
                    .toArray();
            for (int j = 0; j < N; j++) {
                result[j][column] = columnArray[j];
            }
        }
        return result;
    }

    /*
        (Strictly identical arrays) The two-dimensional arrays m1 and m2 are strictly
        identical if their corresponding elements are equal. Write a method that returns
        true if m1 and m2 are strictly identical, using the following header:
        public static boolean equals(int[][] m1, int[][] m2)
        Write a test program that prompts the user to enter two 3 * 3 arrays of integers
        and displays whether the two are strictly identical. Here are the sample runs:
            Enter list1: 51 22 25 6 1 4 24 54 6
            Enter list2: 51 22 25 6 1 4 24 54 6
            The two arrays are strictly identical
            Enter list1: 51 25 22 6 1 4 24 54 6
            Enter list2: 51 22 25 6 1 4 24 54 6
            The two arrays are not strictly identical
     */
    public static void ch8_28() {
        final int N = 3;
        int[][] m1 = ArrayUtils.inputMatrix(N, N, "Enter list1: ");
        int[][] m2 = ArrayUtils.inputMatrix(N, N, "Enter list2: ");
        if (strictlyEquals(m1, m2)) {
            System.out.println("The two arrays are strictly identical");
        } else {
            System.out.println("The two arrays are not strictly identical");
        }
    }

    public static boolean strictlyEquals(int[][] m1, int[][] m2) {
        final int N = m1.length;
        if (m1.length != m2.length) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            if (m1[i].length != m2[i].length) {
                return false;
            }
            for (int j = 0; j < N; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        (Identical arrays) The two-dimensional arrays m1 and m2 are identical if they
        have the same contents. Write a method that returns true if m1 and m2 are iden-
        tical, using the following header:
        public static boolean equals(int[][] m1, int[][] m2)
        Write a test program that prompts the user to enter two 3 * 3 arrays of integers
        and displays whether the two are identical. Here are the sample runs:
        Enter list1: 51 25 22 6 1 4 24 54 6
        Enter list2: 51 22 25 6 1 4 24 54 6
        The two arrays are identical
        Enter list1: 51 5 22 6 1 4 24 54 6
        Enter list2: 51 22 25 6 1 4 24 54 6
        The two arrays are not identical
     */
    public static void ch8_29() {
        final int N = 3;
        int[][] m1 = ArrayUtils.inputMatrix(N, N, "Enter list1: ");
        int[][] m2 = ArrayUtils.inputMatrix(N, N, "Enter list2: ");
        if (equals(m1, m2)) {
            System.out.println("The two arrays are identical");
        } else {
            System.out.println("The two arrays are not identical");
        }

    }

    public static boolean equals(int[][] m1, int[][] m2) {
        int[][] m1Copy = Arrays.copyOf(m1, m1.length);
        int[][] m2Copy = Arrays.copyOf(m2, m2.length);
        final int N = m1Copy.length;
        if (m1Copy.length != m2Copy.length) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            if (m1Copy[i].length != m2Copy[i].length) {
                return false;
            }
            Arrays.sort(m1Copy[i]);
            Arrays.sort(m2Copy[i]);
            for (int j = 0; j < N; j++) {
                if (m1Copy[i][j] != m2Copy[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
            ax + by = e
            cx + dy = f
            x = ed - bf
                ad - bc
            y = af - ec
                ad - bc

        (Algebra: solve linear equations) Write a method that solves the following 2 * 2
        system of linear equations:
        a00*x + a01*y = b0
        a10*x + a11*y = b1
        x = (b0*a11 - b1*a01)/(a00*a11 - a01*a10)
        y = (b1*a00 - b0*a10)/(a00*a11 - a01*a10)
        The method header is:
        public static double[] linearEquation(double[][] a, double[] b)
        The method returns null if a00*a11 - a01*a10 is 0. Write a test program that
        prompts the user to enter a00, a01, a10, a11, b0, and b1 and displays the result. If
        a00*a11 - a01*a10 is 0, report that “The equation has no solution.” A sample run is
        similar to Programming Exercise 3.3
            Enter a, b, c, d, e, f: 9.0 4.0 3.0 -5.0 -6.0 -21.0
            x is −2.0 and y is 3.0
            Enter a, b, c, d, e, f: 1.0 2.0 2.0 4.0 4.0 5.0
            The equation has no solution
     */
    public static void ch8_30() {
        double[][] a = new double[2][2];
        double[] b = new double[2];
        System.out.print("Enter a, b, c, d, e, f: ");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = scanner.nextDouble();
        }
        double[] solution = linearEquation(a, b);
        if (solution != null) {
            System.out.printf("x is %.2f and y is %.2f\n", solution[0], solution[1]);
        } else {
            System.out.println("The equation has no solution");
        }
    }

    public static double[] linearEquation(double[][] a, double[] b) {
        if (a[0][0] * a[1][1] - a[0][1] * a[1][0] == 0) {
            return null;
        } else {
            double x = (b[0] * a[1][1] - a[0][1] * b[1]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0]);
            double y = (a[0][0] * b[1] - b[0] * a[1][0]) / (a[0][0] * a[1][1] - a[0][1] * a[1][0]);
            return new double[]{x, y};
        }
    }

    /*
        (Geometry: intersecting point) Write a method that returns the intersecting point of
        two lines. The intersecting point of the two lines can be found by using the formula
        given in Programming Exercise 3.25. Assume that (x1, y1) and (x2, y2) are the
        two points on line 1 and (x3, y3) and (x4, y4) are on line 2. The method header is:
        public static double[] getIntersectingPoint(double[][] points)
        The points are stored in a 4-by-2 two-dimensional array points with (points
        [0][0], points[0][1]) for (x1, y1). The method returns the intersecting point
        or null if the two lines are parallel. Write a program that prompts the user to enter
        four points and displays the intersecting point. See Programming Exercise 3.25
        for a sample run.
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 5 -1.0 4.0 2.0 -1.0 -2.0
            The intersecting point is at (2.88889, 1.1111)
            Enter x1, y1, x2, y2, x3, y3, x4, y4: 2 2 7 6.0 4.0 2.0 -1.0 -2.0
            The two lines are parallel
     */
    public static void ch8_31() {
        System.out.print("Enter x1, y1, x2, y2, x3, y3, x4, y4: ");
        double[][] points = ArrayUtils.inputMatrix(4, 2);
        double[] intersectingPoint = getIntersectingPoint(points);
        if (intersectingPoint != null) {
            double x = intersectingPoint[0];
            double y = intersectingPoint[1];
            System.out.printf("The intersecting point is at (%.4f, %.4f)\n", x, y);
        } else {
            System.out.println("The two lines are parallel");
        }
    }

    public static double[] getIntersectingPoint(double[][] points) {
        double a = points[0][1] - points[1][1];
        double b = -(points[0][0] - points[1][0]);
        double c = points[2][1] - points[3][1];
        double d = -(points[2][0] - points[3][0]);
        double e = (points[0][1] - points[1][1]) * points[0][0] - (points[0][0] - points[1][0]) * points[0][1];
        double f = (points[2][1] - points[3][1]) * points[2][0] - (points[2][0] - points[3][0]) * points[2][1];
        if (a * d - b * c == 0) {
            return null;
        } else {
            double x = (e * d - b * f) / (a * d - b * c);
            double y = (a * f - e * c) / (a * d - b * c);
            return new double[]{x, y};
        }
    }

    /*
        (Geometry: area of a triangle) Write a method that returns the area of a triangle
        using the following header:
        public static double getTriangleArea(double[][] points)
        The points are stored in a 3-by-2 two-dimensional array points with points
        [0][0] and points[0][1] for (x1, y1). The triangle area can be computed
        using the formula in Programming Exercise 2.19. The method returns 0 if the
        three points are on the same line. Write a program that prompts the user to enter
        three points of a triangle and displays the triangle’s area. Here are the sample runs:
            Enter x1, y1, x2, y2, x3, y3: 2.5 2 5 -1.0 4.0 2.0
            The area of the triangle is 2.25
            Enter x1, y1, x2, y2, x3, y3: 2 2 4.5 4.5 6 6
            The three points are on the same line
     */
    public static void ch8_32() {
        System.out.print("Enter x1, y1, x2, y2, x3, y3: ");
        double[][] points = ArrayUtils.inputMatrix(3, 2);
        double area = getTriangleArea(points);
        if (area == 0) {
            System.out.println("The three points are on the same line");
        } else {
            System.out.printf("The area of the triangle is %.2f", area);
        }
    }

    public static double getTriangleArea(double[][] points) {
        double side1 = calcDistance(points[0][0], points[0][1], points[1][0], points[1][1]);
        double side2 = calcDistance(points[1][0], points[1][1], points[2][0], points[2][1]);
        double side3 = calcDistance(points[2][0], points[2][1], points[0][0], points[0][1]);
        double s = (side1 + side2 + side3) / 2;
        double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        return Double.isNaN(area) ? 0 : area;
    }

    private static double calcDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /*
        (Geometry: polygon subareas) A convex four-vertex polygon is divided into four
        triangles, as shown in Figure 8.9.
        Write a program that prompts the user to enter the coordinates of four vertices and
        displays the areas of the four triangles in increasing order. Here is a sample run:
            Enter x1, y1, x2, y2, x3, y3, x4, y4:
            -2.5 2 4 4 3 -2 -2 -3.5
            The areas are 6.17 7.96 8.08 10.42
     */
    public static void ch8_33() {
        System.out.println("Enter x1, y1, x2, y2, x3, y3, x4, y4:");
        double[][] points = ArrayUtils.inputMatrix(4, 2);
        double[] intersectingPoint = getIntersectingPoint(new double[][]{
                points[0],
                points[2],
                points[1],
                points[3]
        });
        if (intersectingPoint == null) {
            System.out.println("Invalid input");
            return;
        }
        double[] areas = new double[4];
        for (int i = 0; i < areas.length; i++) {
            double[][] trianglePoints = new double[][]{
                    points[i],
                    points[i + 1 < areas.length ? i + 1 : 0],
                    intersectingPoint
            };
            areas[i] = getTriangleArea(trianglePoints);
        }
        Arrays.sort(areas);
        System.out.print("The areas are");
        for (double area : areas) {
            System.out.printf(" %.2f", area);
        }
    }

    /*
        (Geometry: rightmost lowest point) In computational geometry, often you need
        to find the rightmost lowest point in a set of points. Write the following method
        that returns the rightmost lowest point in a set of points:
        public static double[] getRightmostLowestPoint(double[][] points)
        Write a test program that prompts the user to enter the coordinates of six points
        and displays the rightmost lowest point. Here is a sample run:
            Enter 6 points: 1.5 2.5 -3 4.5 5.6 -7 6.5 -7 8 1 10 2.5
            The rightmost lowest point is (6.5, −7.0)
     */
    public static void ch8_34() {
        System.out.print("Enter 6 points: ");
        double[][] points = ArrayUtils.inputMatrix(6, 2);
        double[] point = getRightmostLowestPoint(points);
        System.out.printf("The rightmost lowest point is (%.1f, %.1f)\n", point[0], point[1]);
    }

    public static double[] getRightmostLowestPoint(double[][] points) {
        double[] result = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i][1] <= result[1] && points[i][0] > result[0]) {
                result = points[i];
            }
        }
        return result;
    }

    /*
        (Largest block) Given a square matrix with the elements 0 or 1, write a program to
        find a maximum square submatrix whose elements are all 1s. Your program should
        prompt the user to enter the number of rows in the matrix. The program then dis-
        plays the location of the first element in the maximum square submatrix and the
        number of rows in the submatrix. Here is a sample run:
            Enter the number of rows in the matrix: 5
            Enter the matrix row by row:
            1 0 1 0 1
            1 1 1 0 1
            1 0 1 1 1
            1 0 1 1 1
            1 0 1 1 1
            The maximum square submatrix is at (2, 2) with size 3
        Your program should implement and use the following method to find the max-
        imum square submatrix:
        public static int[] findLargestBlock(int[][] m)
        The return value is an array that consists of three values. The first two values are
        the row and column indices for the first element in the submatrix, and the third
        value is the number of the rows in the submatrix. For an animation of this problem,
        see https://liveexample.pearsoncmg.com/dsanimation/LargestBlockeBook.html
     */
    public static void ch8_35() {
        System.out.print("Enter the number of rows in the matrix: ");
        int N = scanner.nextInt();
        int[][] matrix = ArrayUtils.inputMatrix(N, N, "Enter the matrix row by row:\n");
        int[] largestBlock = findLargestBlock(matrix);
        System.out.printf("The maximum square submatrix is at (%d, %d) with size %d\n",
                largestBlock[0], largestBlock[1], largestBlock[2]);
    }

    public static int[] findLargestBlock(int[][] m) {
        int[] result = new int[]{0, 0, 1};
        int maxSize = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                int size = 1;
                while (true) {
                    if (isSubmatrixOfOnes(i, j, size, m)) {
                        if (size > maxSize) {
                            maxSize = size;
                            result = new int[]{i, j, size};
                        }
                        size++;
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static boolean isSubmatrixOfOnes(int row, int col, int size, int[][] m) {
        if (row + size > m.length) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (col + size > m[i].length) {
                return false;
            }
            for (int j = 0; j < size; j++) {
                if (m[row + i][col + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        (Latin square) A Latin square is an n-by-n array filled with n different Latin let-
        ters, each occurring exactly once in each row and once in each column. Write a
        program that prompts the user to enter the number n and the array of characters,
        as shown in the sample output, and checks if the input array is a Latin square.
        The characters are the first n characters starting from A.
        Enter number n: 4
        Enter 4 rows of letters separated by spaces:
        A B C D
        B A D C
        C D B A
        D C A B
        The input array is a Latin square

        Enter number n: 3
        Enter 3 rows of letters separated by spaces:
        A F D
        Wrong input: the letters must be from A to C
     */
    public static void ch8_36() {
        final char start = 'A';
        System.out.print("Enter number n: ");
        int n = scanner.nextInt();
        System.out.printf("Enter %d rows of letters separated by spaces:\n", n);
        char[][] square = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = scanner.next().charAt(0);
                if (c == ' ') {
                    i--;
                    continue;
                }
                if (c < start || c > start + n) {
                    System.out.printf("Wrong input: the letters must be from %c to %c\n", start, start + n - 1);
                    return;
                }
                square[i][j] = c;
            }
        }
        if (isLatinSquare(square)) {
            System.out.println("The input array is a Latin square");
        } else {
            System.out.println("The input array is not a Latin square");
        }
    }

    public static boolean isLatinSquare(char[][] square) {
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square[i].length; j++) {
                char currentChar = square[i][j];
                int countInRow = 0;
                for (char c : square[i]) {
                    if (currentChar == c) {
                        countInRow++;
                    }
                }
                if (countInRow > 1) {
                    return false;
                }
                int countInColumn = 0;
                for (int k = 0; k < square.length; k++) {
                    if (square[j][k] == currentChar) {
                        countInColumn++;
                    }
                }
                if (countInColumn > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
        (Guess the capitals) Write a program that repeatedly prompts the user to enter
        a capital for a state. Upon receiving the user input, the program reports whether
        the answer is correct. Assume that 50 states and their capitals are stored in a
        two-dimensional array, as shown in Figure 8.10. The program prompts the user to
        answer all states’ capitals and displays the total correct count. The user’s answer
        is not case-sensitive.
            Alabama   Montgomery
            Alaska    Juneau
            Arizona   Phoenix
            ...       ...
            ...       ...
        Here is a sample run:
            What is the capital of Alabama? Montogomery
            The correct answer should be Montgomery
            What is the capital of Alaska? Juneau
            Your answer is correct
            What is the capital of Arizona? ...
            ...
            The correct count is 35
     */
    public static void ch8_37() {
        String[][] questions = new String[][]{
                {"Alabama", "Montgomery"},
                {"Alaska", "Juneau"},
                {"Arizona", "Phoenix"},
        };
        int count = 0;
        for (String[] question : questions) {
            System.out.printf("What is the capital of %s? ", question[0]);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(question[1])) {
                count++;
            }
        }
        System.out.printf("The correct count is %d\n", count);
    }
}
