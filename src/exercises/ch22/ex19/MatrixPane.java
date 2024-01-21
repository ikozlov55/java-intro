package exercises.ch22.ex19;

import javafx.scene.layout.GridPane;

class MatrixPane extends GridPane {
    private final MatrixCell[][] matrix;
    private final int rows;
    private final int cols;

    public MatrixPane(int rows, int cols) {
        matrix = new MatrixCell[rows][cols];
        this.rows = rows;
        this.cols = cols;
        refresh();
    }

    public void refresh() {
        getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new MatrixCell((int) Math.round(Math.random()));
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                add(matrix[i][j], j, i);
            }
        }
    }

    public void highLight(int row, int col, int size) {
        if (row + size > rows || col + size > cols) return;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                matrix[i][j].setStyle("-fx-background-color: red");
            }
        }
    }

    public void showLargestBlock() {
        int maxRow = 0;
        int maxCol = 0;
        int maxSize = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j].setStyle("-fx-background-color: white");
                int size = 1;
                while (allOnes(i, j, size)) {
                    size++;
                }
                size--;
                if (size > maxSize) {
                    maxRow = i;
                    maxCol = j;
                    maxSize = size;
                }
            }
        }
        highLight(maxRow, maxCol, maxSize);
    }

    private boolean allOnes(int row, int col, int size) {
        if (size == 0) return true;
        if (col + size > cols || row + size > rows) {
            return false;
        }
        for (int i = row; i < row + size; i++) {
            if (matrix[i][col + size - 1].getValue() == 0) {
                return false;
            }
        }
        for (int i = col; i < col + size; i++) {
            if (matrix[row + size - 1][i].getValue() == 0) {
                return false;
            }
        }
        return true;
    }
}
