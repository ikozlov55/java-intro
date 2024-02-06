package exercises.ch22.ex23;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class EightQueensBoard extends BorderPane {
    private final boolean[][] board;

    public EightQueensBoard(boolean[][] board, String labelText) {
        this.board = board;
        paint(labelText);
    }

    private void paint(String labelText) {
        getChildren().clear();
        setPadding(new Insets(5));
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                StackPane cell = new StackPane();
                int cellSize = 40;
                cell.setPrefSize(cellSize, cellSize);
                if (board[row][col]) {
                    ImageView queen = new ImageView(new Image("resources/image/queen.jpg"));
                    queen.setPreserveRatio(true);
                    queen.setSmooth(true);
                    queen.setFitHeight(cellSize - 3);
                    cell.getChildren().add(queen);
                }
                grid.add(cell, col, row);
            }
        }
        Label label = new Label(labelText);
        BorderPane.setAlignment(label, Pos.CENTER);
        setTop(label);
        setCenter(grid);
    }
}
