package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise16_31 extends Application {
    public static void run() {
        Application.launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        ConnectFourBoard board = new ConnectFourBoard(6, 7);
        root.setCenter(board);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class ConnectFourBoard extends GridPane {
    private final int rows;
    private final int cols;
    private final Circle[][] board;
    private int currentPlayer = 1;

    public ConnectFourBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new Circle[rows][cols];
        draw();
    }

    public void draw() {
        setBackground(Background.fill(Color.GREEN));
        setAlignment(Pos.BASELINE_CENTER);
        setPadding(new Insets(5));
        setVgap(5);
        setHgap(5);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Circle circle = new Circle(20);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                int finalRow = row;
                int finalCol = col;
                circle.setOnMouseClicked(e -> placeDisc(finalRow, finalCol));
                add(circle, col, row);
                board[row][col] = circle;
            }
        }
    }

    private void placeDisc(int row, int col) {
        Circle circle = board[row][col];
        if (!circle.getFill().equals(Color.WHITE)) return;
        if (row < rows - 1 && board[row + 1][col].getFill().equals(Color.WHITE)) return;
        circle.setFill(currentPlayer == 1 ? Color.RED : Color.YELLOW);
        if (isDraw()) {
            System.out.println("It's a draw!");
            stopGame();
            return;
        }
        if (isWinCondition()) {
            System.out.printf("The %s player wins!\n", currentPlayer == 1 ? "Red" : "Yellow");
            stopGame();
            return;
        }
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    private boolean isDraw() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].getFill().equals(Color.WHITE)) return false;
            }
        }
        return true;
    }

    private boolean isWinCondition() {
        final int n = 4;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Paint value = board[row][col].getFill();
                if (value.equals(Color.WHITE)) continue;
                int counter = 0;
                for (int i = col; i < cols; i++) {
                    if (!board[row][i].getFill().equals(value)) break;
                    counter++;
                    if (counter < n) continue;
                    return true;
                }
                counter = 0;
                for (int i = row; i < rows; i++) {
                    if (!board[i][col].getFill().equals(value)) break;
                    counter++;
                    if (counter < n) continue;
                    return true;
                }
                counter = 0;
                for (int i = row, j = col; i < rows && j < cols; i++, j++) {
                    if (!board[row][col].getFill().equals(value)) break;
                    counter++;
                    if (counter < n) continue;
                    return true;
                }
                counter = 0;
                for (int i = row, j = col; i < rows && j > 0; i++, j--) {
                    if (!board[row][col].getFill().equals(value)) break;
                    counter++;
                    if (counter < n) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private void stopGame() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col].setDisable(true);
            }
        }
    }
}
