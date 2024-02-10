package exercises.ch22.ex21;

import javafx.scene.layout.GridPane;

public class SudokuPane extends GridPane {
    private final SudokuCell[][] cells = new SudokuCell[9][9];
    private int[][][] solutions;
    private Runnable onInvalidInput;
    private Runnable onNoSolution;
    private Runnable onSolved;

    public SudokuPane() {
        this(new int[9][9]);
    }

    public SudokuPane(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuCell cell = new SudokuCell(0);
                cell.setValue(grid[i][j]);
                cells[i][j] = cell;
                int top = i % 3 == 0 ? 3 : 0;
                int right = j == 8 ? 3 : 0;
                int bottom = i == 8 ? 3 : 0;
                int left = j % 3 == 0 ? 3 : 0;
                cell.setStyle(String.format("-fx-border-color: red; -fx-border-width: %d %d %d %d;",
                        top, right, bottom, left));
                add(cell, j, i);
            }
        }
        solutions = new int[0][][];
    }

    public void setOnInvalidInput(Runnable onInvalidInput) {
        this.onInvalidInput = onInvalidInput;
    }

    public void setOnNoSolution(Runnable onNoSolution) {
        this.onNoSolution = onNoSolution;
    }

    public void setOnSolved(Runnable onSolved) {
        this.onSolved = onSolved;
    }

    public int getSolutionsNumber() {
        if (solutions == null) {
            return 0;
        } else {
            return solutions.length;
        }
    }


    public void clear() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setValue(0);
                cells[i][j].setStyle(cells[i][j].getStyle() + "-fx-text-fill: black; ");
            }
        }
        solutions = new int[0][][];
    }

    public void solve() {
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = cells[i][j].getValue();
            }
        }
        SudokuEngine engine = new SudokuEngine(grid);
        if (!engine.isValid() && onInvalidInput != null) {
            onInvalidInput.run();
            return;
        }
        solutions = engine.search();
        if (solutions.length == 0 && onNoSolution != null) {
            onNoSolution.run();
            return;
        }
        onSolved.run();
    }

    public void showSolution(int index) {
        int[][] solution = solutions[index];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getValue() == 0) {
                    cells[i][j].setStyle(cells[i][j].getStyle() + "-fx-text-fill: gray;");
                }
                cells[i][j].setValue(solution[i][j]);
            }
        }
    }
}
