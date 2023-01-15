package exercises.ch8;

import java.util.Locale;
import java.util.Scanner;

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
public class TicTacToeGame {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static final int N = 3;
    private int[][] board = new int[N][N];
    private int currentPlayer = 1;

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += switch (board[i][j]) {
                    case 1 -> "|X";
                    case 2 -> "|O";
                    default -> "| ";
                };
            }
            result += "|\n";
        }
        return result;
    }

    public void start() {
        while (true) {
            System.out.println(this);
            int row, column;
            while (true) {
                row = input("row");
                column = input("column");
                if (board[row][column] == 0) {
                    board[row][column] = currentPlayer;
                    break;
                }
            }
            if (isWin()) {
                System.out.println(this);
                System.out.printf("%c player won\n", currentPlayer == 1 ? 'X' : 'O');
                return;
            }
            if (isDraw()) {
                System.out.println(this);
                System.out.println("It's a draw");
                return;
            }
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }
    }

    private int input(String type) {
        while (true) {
            System.out.printf("Enter a %s (0, 1, or 2) for player %c: ", type, currentPlayer == 1 ? 'X' : 'O');
            int value = scanner.nextInt();
            if (value == 0 || value == 1 || value == 2) return value;
        }
    }

    private boolean isDraw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    private boolean isWin() {
        //rows
        boolean fullRow;
        for (int i = 0; i < N; i++) {
            fullRow = true;
            for (int j = 0; j < N - 1; j++) {
                fullRow = fullRow && board[i][j] != 0 && board[i][j] == board[i][j + 1];
            }
            if (fullRow) return true;
        }
        //columns
        boolean fullColumn;
        for (int i = 0; i < N; i++) {
            fullColumn = true;
            for (int j = 0; j < N - 1; j++) {
                fullColumn = fullColumn && board[j][i] != 0 && board[j][i] == board[j + 1][i];
            }
            if (fullColumn) return true;
        }

        //diagonals
        boolean fullDiagonal1 = true;
        for (int i = 0, j = 0; i < N - 1; i++, j++) {
            fullDiagonal1 = fullDiagonal1 && board[i][j] != 0 && board[i][j] == board[i + 1][j + 1];
        }
        if (fullDiagonal1) return true;

        boolean fullDiagonal2 = true;
        for (int i = 0, j = N - 1; i < N - 1; i++, j--) {
            fullDiagonal2 = fullDiagonal2 && board[i][j] != 0 && board[i][j] == board[i + 1][j - 1];
        }

        return fullDiagonal2;
    }

}
