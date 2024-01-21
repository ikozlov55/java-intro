package exercises.ch22.ex20;

import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    private final int[][] grid;

    public Sudoku() {
        grid = readAPuzzle();
    }

    public Sudoku(int[][] grid) {
        this.grid = grid;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(grid[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void printGrid(int[][] grid) {
        System.out.println(new Sudoku(grid));
    }

    /**
     * Check whether the fixed cells are valid in the grid
     */
    public boolean isValid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] < 0 || grid[i][j] > 9 || (grid[i][j] != 0 && !isValid(i, j))) {
                    return false;
                }
            }
        }
        return true; // The fixed cells are valid
    }

    private static int[][] readAPuzzle() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Sudoku puzzle:");
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = input.nextInt();
            }
        }

        return grid;
    }

    /**
     * Obtain a list of free cells from the puzzle
     */
    private static int[][] getFreeCellList(int[][] grid) {
        // Determine the number of free cells
        int numberOfFreeCells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    numberOfFreeCells++;
                }
            }
        }
        // Store free cell positions into freeCellList
        int[][] freeCellList = new int[numberOfFreeCells][2];
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    freeCellList[count][0] = i;
                    freeCellList[count][1] = j;
                    count++;
                }
            }
        }
        return freeCellList;
    }


    /**
     * Search for a solution
     */
    public int[][][] search() {
        int[][][] result = new int[0][][];
        int[][] currentGrid = Arrays.copyOf(grid, grid.length);
        int n = 0;
        int[][] freeCellList = getFreeCellList(currentGrid); // Free cells
        if (freeCellList.length == 0) {
            return result; // "No free cells");
        }
        int k = 0; // Start from the first free cell

        while (true) {
            int i = freeCellList[k][0];
            int j = freeCellList[k][1];
            if (currentGrid[i][j] == 0) {
                currentGrid[i][j] = 1; // Fill the free cell with number 1
            }
            if (isValid(i, j)) {
                if (k + 1 == freeCellList.length) { // No more free cells
                    result = Arrays.copyOf(result, n + 1);
                    int[][] temp = new int[9][9];
                    for (int l = 0; l < 9; l++) {
                        System.arraycopy(currentGrid[l], 0, temp[l], 0, 9);
                    }
                    result[n] = temp;
                    n++;
                    if (currentGrid[i][j] < 9) {
                        // Fill the free cell with the next possible value
                        currentGrid[i][j] = currentGrid[i][j] + 1;
                    } else { // free cell grid[i][j] is 9, backtrack
                        while (currentGrid[i][j] == 9) {
                            if (k == 0) {
                                return result;
                            }
                            currentGrid[i][j] = 0; // Reset to free cell
                            k--; // Backtrack to the preceding free cell
                            i = freeCellList[k][0];
                            j = freeCellList[k][1];
                        }
                        // Fill the free cell with the next possible value,
                        // search continues from this free cell at k
                        currentGrid[i][j] = currentGrid[i][j] + 1;
                    }
                } else { // Move to the next free cell
                    k++;
                }
            } else if (currentGrid[i][j] < 9) {
                // Fill the free cell with the next possible value
                currentGrid[i][j] = currentGrid[i][j] + 1;
            } else { // free cell grid[i][j] is 9, backtrack
                while (currentGrid[i][j] == 9) {
                    if (k == 0) {
                        return result;
                    }
                    currentGrid[i][j] = 0; // Reset to free cell
                    k--; // Backtrack to the preceding free cell
                    i = freeCellList[k][0];
                    j = freeCellList[k][1];
                }
                // Fill the free cell with the next possible value,
                // search continues from this free cell at k
                currentGrid[i][j] = currentGrid[i][j] + 1;
            }
        }
    }

    /**
     * Check whether grid[i][j] is valid in the grid
     */
    private boolean isValid(int i, int j) {
        // Check whether grid[i][j] is valid at the i's row
        for (int column = 0; column < 9; column++) {
            if (column != j && grid[i][column] == grid[i][j]) {
                return false;
            }
        }
        // Check whether grid[i][j] is valid at the j's column
        for (int row = 0; row < 9; row++) {
            if (row != i && grid[row][j] == grid[i][j]) {
                return false;
            }
        }
        // Check whether grid[i][j] is valid in the 3 by 3 box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (!(row == i && col == j) && grid[row][col] == grid[i][j]) {
                    return false;
                }
            }
        }
        return true; // The current value at grid[i][j] is valid
    }


}
