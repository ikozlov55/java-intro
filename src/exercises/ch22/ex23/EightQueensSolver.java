package exercises.ch22.ex23;

import java.util.ArrayList;
import java.util.List;

public class EightQueensSolver {
    private final int size = 8;

    public List<boolean[][]> findSolutions() {
        List<boolean[][]> solutions = new ArrayList<>();
        for (int[] rows : permutations()) {
            boolean[][] board = rowsToBoard(rows);
            if (!isDiagonalAttacking(board)) {
                solutions.add(board);
            }
        }
        return solutions;
    }

    private List<int[]> permutations() {
        List<int[]> result = new ArrayList<>();
        permutations(new ArrayList<>(), List.of(0, 1, 2, 3, 4, 5, 6, 7), result);
        return result;
    }

    private void permutations(List<Integer> l1, List<Integer> l2, List<int[]> result) {
        if (l2.isEmpty()) {
            int[] l = new int[l1.size()];
            for (int i = 0; i < l.length; i++) {
                l[i] = l1.get(i);
            }
            result.add(l);
        }
        for (int i = 0; i < l2.size(); i++) {
            List<Integer> nextL1 = new ArrayList<>(l1);
            List<Integer> nextL2 = new ArrayList<>(l2);
            nextL1.add(nextL2.remove(i));
            permutations(nextL1, nextL2, result);
        }
    }

    private boolean isDiagonalAttacking(boolean[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!board[row][col]) continue;
                // check left up
                for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                    if (board[i][j]) return true;
                }
                // check left down
                for (int i = row + 1, j = col - 1; i < size && j >= 0; i++, j--) {
                    if (board[i][j]) return true;
                }
                // check right up
                for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {
                    if (board[i][j]) return true;
                }
                // check right down
                for (int i = row + 1, j = col + 1; i < size && j < size; i++, j++) {
                    if (board[i][j]) return true;

                }
            }
        }
        return false;
    }

    private boolean[][] rowsToBoard(int[] rows) {
        boolean[][] board = new boolean[size][size];
        for (int i = 0; i < rows.length; i++) {
            board[i][rows[i]] = true;
        }
        return board;
    }

}
