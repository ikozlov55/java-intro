package exercises.ch18.ex32;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Arrays;

public class Exercise18_32 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Button solveBTN = new Button("Solve");
        root.setBottom(solveBTN);
        BorderPane.setAlignment(solveBTN, Pos.CENTER);
        BorderPane.setMargin(solveBTN, new Insets(5));

        KnightsTourBoard board = new KnightsTourBoard();
        root.setCenter(board);

        solveBTN.setOnAction(e -> board.solve());

        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
    }
}

class KnightsTourBoard extends StackPane {
    private final int boardSize;
    private final double cellSize = 40;
    private final GridPane grid = new GridPane();
    private final Pane canvas = new Pane();
    private final Pane[][] board;
    private final boolean[][] visited;
    private boolean knightPlaced = false;
    private int[] knightPosition;
    private boolean solved = false;

    public KnightsTourBoard() {
        boardSize = 8;
        board = new Pane[boardSize][boardSize];
        visited = new boolean[boardSize][boardSize];
        knightPosition = new int[2];
        paint();
    }

    public void solve() {
        if (!knightPlaced || solved) return;
        if (solveHelper(knightPosition[0], knightPosition[1])) {
            System.out.println("Solved!");
            solved = true;
        } else {
            System.out.println("Cannot be solved!");
        }
    }

    private boolean solveHelper(int row, int col) {
        visited[row][col] = true;
        int[][] moves = getAvailableMoves(row, col);
        if (allVisited()) {
            return true;
        }
        if (moves.length == 0) {
            return false;
        }
        for (int[] nextMove : getPrioritizedMoves(moves)) {
            int nextRow = nextMove[0];
            int nextCol = nextMove[1];
            Line line = getLine(row, col, nextRow, nextCol);
            canvas.getChildren().add(line);
            if (solveHelper(nextRow, nextCol)) {
                return true;
            }
            visited[nextRow][nextCol] = false;
            getChildren().remove(line);
        }
        return false;
    }

    private void paint() {
        getChildren().clear();
        getChildren().addAll(canvas, grid);
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                BorderPane cell = new BorderPane();
                cell.setOnMouseClicked(this::putKnight);
                Rectangle rec = new Rectangle(cellSize, cellSize);
                cell.setPrefSize(cellSize, cellSize);
                rec.setStroke(Color.GRAY);
                rec.setFill(Color.TRANSPARENT);
                cell.getChildren().add(rec);
                board[row][col] = cell;
                grid.add(board[row][col], col, row);
            }
        }
    }

    private void putKnight(MouseEvent e) {
        if (knightPlaced) return;
        BorderPane cell = (BorderPane) e.getSource();
        ImageView imageView = new ImageView(new Image("resources/image/knight.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(cellSize - 5);
        imageView.setSmooth(true);
        cell.setCenter(imageView);
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (board[row][col] == cell) {
                    knightPosition = new int[]{row, col};
                    knightPlaced = true;
                    return;
                }
            }
        }
    }

    private int[][] getAvailableMoves(int row, int col) {
        int[][] moves = new int[][]{
                {row - 2, col - 1}, {row - 2, col + 1},
                {row + 2, col - 1}, {row + 2, col + 1},
                {row - 1, col - 2}, {row - 1, col + 2},
                {row + 1, col - 2}, {row + 1, col + 2},
        };
        return Arrays.stream(moves).filter(move -> {
            int r = move[0];
            int c = move[1];
            return r >= 0 && r < boardSize && c >= 0 && c < boardSize && !visited[r][c];
        }).toArray(int[][]::new);
    }

    private int[][] getPrioritizedMoves(int[][] moves) {
        return Arrays.stream(moves).sorted(
                (a1, a2) -> getAvailableMoves(a1[0], a1[1]).length - getAvailableMoves(a2[0], a2[1]).length
        ).toArray(int[][]::new);
    }

    private boolean allVisited() {
        for (boolean[] row : visited) {
            for (boolean isVisited : row) {
                if (!isVisited) {
                    return false;
                }
            }
        }
        return true;
    }

    private Line getLine(int fromRow, int fromCol, int toRow, int toCol) {
        double x1 = fromRow * cellSize + cellSize / 2;
        double y1 = fromCol * cellSize + cellSize / 2;
        double x2 = toRow * cellSize + cellSize / 2;
        double y2 = toCol * cellSize + cellSize / 2;
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(2);
        line.setStroke(Color.BLACK);
        return line;
    }
}