package exercises.ch8;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class ConnectFourGame {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static final int N = 4;
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private int[][] board = new int[ROWS][COLUMNS];

    private int currentPlayer = 1;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                char c = switch (board[i][j]) {
                    case 1 -> 'R';
                    case 2 -> 'Y';
                    default -> ' ';
                };
                result.append("|").append(c);
            }
            result.append("|\n");
        }
        result.append("-".repeat(COLUMNS * 2 + 1));
        return result.toString();
    }

    public void start() {
        while (true) {
            System.out.println(this);
            placeDisc();
            if (isDraw()) {
                System.out.println(this);
                System.out.println("It's a draw");
                break;
            }
            if (isConsecutiveFour()) {
                System.out.println(this);
                System.out.printf("The %s player wins\n", currentPlayer == 1 ? "red" : "yellow");
                break;
            }

            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }

    }

    private void placeDisc() {
        int column;
        while (true) {
            System.out.printf("Drop a %s disk at column (0–%d): ",
                    currentPlayer == 1 ? "red" : "yellow",
                    COLUMNS - 1);
            column = scanner.nextInt();
            if (column >= 0 && column < COLUMNS && board[0][column] == 0) break;
        }
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = currentPlayer;
                break;
            }
        }
    }

    private boolean isDraw() {
        return Arrays.stream(board[0]).allMatch(x -> x != 0);
    }

    private boolean isConsecutiveFour() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                int value = board[i][j];
                if (value == 0) continue;
                int counter = 0;
                //down
                for (int k = i; k < ROWS; k++) {
                    if (board[k][j] == value) {
                        counter++;
                        if (counter == N) {
                            System.out.printf("down [%d,%d] - [%d,%d]\n", i, j, k, j);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //right
                for (int k = j; k < COLUMNS; k++) {
                    if (board[i][k] == value) {
                        counter++;
                        if (counter == N) {
                            System.out.printf("right [%d,%d] - [%d,%d]\n", i, j, i, k);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //down left
                for (int k = i, l = j; k < ROWS && l >= 0; k++, l--) {
                    if (board[k][l] == value) {
                        counter++;
                        if (counter == N) {
                            System.out.printf("down left [%d,%d] - [%d,%d]\n", i, j, k, l);
                            return true;
                        }
                    } else {
                        break;
                    }
                }
                counter = 0;
                //down right
                for (int k = i, l = j; k < ROWS && l < COLUMNS; k++, l++) {
                    if (board[k][l] == value) {
                        counter++;
                        if (counter == N) {
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

}
