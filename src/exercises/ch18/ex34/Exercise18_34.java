package exercises.ch18.ex34;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Exercise18_34 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        EightQueensBoard board = new EightQueensBoard();
        board.solve();
        root.setCenter(board);
        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
    }
}


class EightQueensBoard extends StackPane {
    private final int boardSize = 8;
    private final double cellSize = 40;
    private final boolean[][] board;
    private final BorderPane[][] cells;

    public EightQueensBoard() {
        cells = new BorderPane[boardSize][boardSize];
        board = new boolean[boardSize][boardSize];
        paint();
    }

    public void solve() {
        solveHelper(8);
    }

    private boolean solveHelper(int queensLeft) {
        if (queensLeft == 0) {
            System.out.println("Solved");
            return true;
        }
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (!canBePlaced(i, j)) continue;
                putQueen(i, j);
                if (solveHelper(queensLeft - 1)) {
                    return true;
                }
                removeQueen(i, j);
            }
        }
        return false;
    }

    private void paint() {
        getChildren().clear();
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                BorderPane cell = new BorderPane();
                cell.setPrefSize(cellSize, cellSize);
                grid.add(cell, col, row);
                cells[row][col] = cell;
            }
        }
        getChildren().addAll(grid);
    }

    private void putQueen(int row, int col) {
        ImageView queen = new ImageView(new Image("resources/image/queen.jpg"));
        queen.setPreserveRatio(true);
        queen.setSmooth(true);
        queen.setFitHeight(cellSize - 3);
        cells[row][col].setCenter(queen);
        board[row][col] = true;
    }

    private void removeQueen(int row, int col) {
        cells[row][col].getChildren().clear();
        board[row][col] = false;
    }

    private boolean canBePlaced(int row, int col) {
        // check row & col
        for (int i = 0; i < boardSize; i++) {
            if (board[row][i] || board[i][col]) {
                return false;
            }
        }
        // check left up
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) return false;
        }
        // check left down
        for (int i = row, j = col; i < boardSize && j >= 0; i++, j--) {
            if (board[i][j]) return false;
        }
        // check right up
        for (int i = row, j = col; i >= 0 && j < boardSize; i--, j++) {
            if (board[i][j]) return false;
        }
        // check right down
        for (int i = row, j = col; i < boardSize && j < boardSize; i++, j++) {
            if (board[i][j]) return false;
        }
        return true;
    }
}