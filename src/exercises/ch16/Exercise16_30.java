package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise16_30 extends Application {
    private final Label label = new Label("");
    private final MatrixGridPane matrixGrid = new MatrixGridPane(6, 7);

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane pane = new BorderPane();
        pane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);

        Button solveButton = new Button("Solve");
        pane.setBottom(solveButton);
        BorderPane.setAlignment(solveButton, Pos.CENTER);

        solveButton.setOnAction(e -> {
            matrixGrid.solve();
            label.setText(matrixGrid.isSolved() ? "A consecutive four found" : "No solution found");
        });

        matrixGrid.setPadding(new Insets(5));
        pane.setCenter(matrixGrid);

        return pane;
    }
}

class MatrixGridPane extends GridPane {
    private final int rows;
    private final int cols;
    private final int[][] matrix;
    private final TextField[][] textFields;
    private boolean solved;

    public MatrixGridPane(int rows, int cols) {
        this.solved = false;
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        textFields = new TextField[rows][cols];
        draw();
    }

    public boolean isSolved() {
        return solved;
    }

    public void solve() {
        clear();
        final int n = 4;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int value = matrix[row][col];
                int counter = 0;
                for (int i = col; i < cols; i++) {
                    if (matrix[row][i] != value) break;
                    counter++;
                    if (counter < n) continue;
                    solved = true;
                    for (int j = col; j < col + n; j++) {
                        textFields[row][j].setStyle("-fx-border-color: red; -fx-border-radius: 5 5 5 5");
                    }
                    return;
                }
                counter = 0;
                for (int i = row; i < rows; i++) {
                    if (matrix[i][col] != value) break;
                    counter++;
                    if (counter < n) continue;
                    solved = true;
                    for (int j = row; j < row + n; j++) {
                        textFields[j][col].setStyle("-fx-border-color: red; -fx-border-radius: 5 5 5 5");
                    }
                    return;
                }
            }
        }
        solved = false;
    }

    private void clear() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                textFields[row][col].setStyle("");
            }
        }
    }

    private void draw() {
        getChildren().clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TextField tf = new TextField(Integer.toString(matrix[row][col]));
                tf.setPrefColumnCount(1);
                tf.setAlignment(Pos.CENTER);
                tf.setFont(Font.font(15));
                tf.setPrefSize(40, 40);
                int finalRow = row;
                int finalCol = col;
                tf.textProperty().addListener((ov, oldValue, newValue) -> {
                    try {
                        matrix[finalRow][finalCol] = Integer.parseInt(tf.getText());
                    } catch (NumberFormatException e) {
                        tf.setText(oldValue);
                    }
                });
                add(tf, col, row);
                textFields[row][col] = tf;
            }
        }
    }
}
