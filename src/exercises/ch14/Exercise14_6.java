package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise14_6 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final int N = 8;
        GridPane grid = new GridPane();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Rectangle cell = new Rectangle(50, 50);
                if ((i + j) % 2 == 0) {
                    cell.setFill(Paint.valueOf("white"));
                }
                grid.add(cell, i, j);
            }
        }
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Exercise14_06");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
