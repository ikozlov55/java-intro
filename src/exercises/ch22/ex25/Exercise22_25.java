package exercises.ch22.ex25;

import exercises.ch22.ex21.SudokuPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise22_25 extends Application {

    private final Label statusLabel = new Label();
    private final int[][] grid = {
            {1, 0, 4, 0, 0, 0, 0, 0, 8},
            {0, 2, 0, 3, 0, 0, 6, 0, 0},
            {6, 0, 3, 0, 0, 0, 0, 0, 1},
            {0, 0, 5, 0, 0, 0, 0, 0, 0},
            {2, 0, 0, 0, 0, 0, 3, 0, 0},
            {0, 0, 0, 9, 0, 1, 0, 0, 0},
            {3, 0, 0, 0, 5, 0, 0, 0, 9},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 7, 0},
    };
    private final SudokuPane sudoku = new SudokuPane(grid);
    private final Button solveButton = new Button("Solve");
    private final Button clearButton = new Button("Clear");
    private final Button nextButton = new Button("Next");

    private int i = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        clearButton.setOnAction(x -> {
            sudoku.clear();
            statusLabel.setText("");
            nextButton.setVisible(false);
            i = 0;
        });
        solveButton.setOnAction(x -> {
            sudoku.solve();
            nextButton.setVisible(true);
        });
        nextButton.setOnAction(x -> {
            i++;
            if (i < sudoku.getSolutionsNumber()) {
                showSolution();
            }
        });
        sudoku.setOnSolved(this::showSolution);
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

        nextButton.setVisible(false);
        HBox controls = new HBox(5, solveButton, clearButton, nextButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        root.setCenter(sudoku);
        return root;
    }

    private void showSolution() {
        String text = String.format("%d/%d solutions", i + 1, sudoku.getSolutionsNumber());
        statusLabel.setText(text);
        sudoku.showSolution(i);
    }
}
