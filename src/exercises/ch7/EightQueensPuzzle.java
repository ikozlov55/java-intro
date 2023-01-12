package exercises.ch7;


public class EightQueensPuzzle {
    private final int N = 8;
    private final int QUEENS = 8;
    private boolean[] board;
    private int queensOnBoard;

    public EightQueensPuzzle() {
        clearBoard();
    }

    public void solve() {
        int start = 0;
        while (true) {
            for (int i = start; i < board.length && queensOnBoard < QUEENS; i++) {
                if (canBePlaced(i % N, i / N)) {
                    board[i] = true;
                    queensOnBoard++;
                    i = 0;
                }
            }
            if (queensOnBoard == QUEENS) {
                break;
            } else if (start > board.length) {
                System.out.println("Unable to solve!");
                break;
            } else {
                clearBoard();
                start++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= board.length; i++) {
            if (i % N == 1) result.append("|");
            result.append(board[i - 1] ? 'Q' : ' ');
            result.append("|");
            if (i % N == 0) result.append("\n");
        }
        return result.toString();
    }

    private void clearBoard() {
        board = new boolean[N * N];
        queensOnBoard = 0;
    }

    private boolean canBePlaced(int x, int y) {
        if (x >= N || y >= N) return false;
        if (isOccupied(x, y)) return false;

        // up
        for (int y1 = y; y1 >= 0; y1--) {
            if (isOccupied(x, y1)) return false;
        }
        // down
        for (int y1 = y; y1 < N; y1++) {
            if (isOccupied(x, y1)) return false;
        }
        // left
        for (int x1 = x; x1 >= 0; x1--) {
            if (isOccupied(x1, y)) return false;
        }
        // right
        for (int x1 = x; x1 < N; x1++) {
            if (isOccupied(x1, y)) return false;
        }
        //top right
        for (int x1 = x, y1 = y; x1 < N && y1 >= 0; x1++, y1--) {
            if (isOccupied(x1, y1)) return false;
        }
        //bottom right
        for (int x1 = x, y1 = y; x1 < N && y1 < N; x1++, y1++) {
            if (isOccupied(x1, y1)) return false;
        }
        //top left
        for (int x1 = x, y1 = y; x1 >= 0 && y1 >= 0; x1--, y1--) {
            if (isOccupied(x1, y1)) return false;
        }
        //bottom left
        for (int x1 = x, y1 = y; x1 >= 0 && y1 < N; x1--, y1++) {
            if (isOccupied(x1, y1)) return false;
        }

        return true;
    }

    private boolean isOccupied(int x, int y) {
        return board[y * N + x];
    }

}
