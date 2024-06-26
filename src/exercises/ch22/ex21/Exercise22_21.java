package exercises.ch22.ex21;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise22_21 extends Application {
    private final Label statusLabel = new Label();
    private final SudokuPane sudoku = new SudokuPane(new int[][]{
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 6, 0, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 0, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 0, 0, 0, 9, 0, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    });
    private final Button solveButton = new Button("Solve");
    private final Button clearButton = new Button("Clear");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        clearButton.setOnAction(x -> {
            sudoku.clear();
            statusLabel.setText("");
        });
        solveButton.setOnAction(x -> {
            sudoku.solve();
            sudoku.showSolution(0);
        });
        sudoku.setOnSolved(() -> statusLabel.setText("A solution found"));
        sudoku.setOnInvalidInput(() -> statusLabel.setText("Invalid input"));
        sudoku.setOnNoSolution(() -> statusLabel.setText("No solutions found"));
        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setTop(statusLabel);
        BorderPane.setMargin(statusLabel, new Insets(5));
        BorderPane.setAlignment(statusLabel, Pos.CENTER);

        HBox controls = new HBox(5, solveButton, clearButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        root.setCenter(sudoku);
        return root;
    }
}




