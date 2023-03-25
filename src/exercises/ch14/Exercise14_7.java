package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_7 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final int N = 10;
        GridPane grid = new GridPane();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                TextField cell = new TextField();
                cell.setMaxWidth(25);
                cell.setText(String.valueOf((int) (Math.random() * 2)));
                cell.setAlignment(Pos.BASELINE_CENTER);
                grid.add(cell, i, j);
            }
        }
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Exercise14_07");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
