package exercises.ch23.ex17;

import exercises.ch23.ex16.BubbleSortPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise23_17 extends Application {
    private final Label label = new Label();
    private final RadixSortPane pane = new RadixSortPane();
    private final Button stepButton = new Button("Step");
    private final Button resetButton = new Button("Reset");


    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        stepButton.setOnAction(e -> {
            if (pane.step() == 1) {
                label.setText("Array is sorted!");
            }
        });
        resetButton.setOnAction(e -> {
            pane.reset();
            label.setText("");
        });
        stage.setScene(new Scene(root, 800, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setTop(label);
        BorderPane.setMargin(label, new Insets(5));
        BorderPane.setAlignment(label, Pos.CENTER);

        HBox controls = new HBox(5, stepButton, resetButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        root.setCenter(pane);
        BorderPane.setMargin(pane, new Insets(5));
        return root;
    }
}
