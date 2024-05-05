package exercises.ch14;

import exercises.ch14.Resources;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_2 extends Application {
    private static final int N = 3;
    private final int[][] board = new int[N][N];

    private void initBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = (int) (Math.random() * 3);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initBoard();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                switch (board[i][j]) {
                    case 0 -> imageView.setImage(new Image(Resources.get("o.gif")));
                    case 1 -> imageView.setImage(new Image(Resources.get("x.gif")));
                }
                grid.add(imageView, i, j);
            }
        }
        Scene scene = new Scene(grid);
        stage.setTitle("Exercise14_02");
        stage.setScene(scene);
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
