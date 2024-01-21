package exercises.ch22.ex19;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise22_19 extends Application {
    private final Button refreshBTN = new Button("Refresh");
    private final Button findBTN = new Button("Find Largest Block");
    private final MatrixPane matrixPane = new MatrixPane(10, 10);

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();
        refreshBTN.setOnAction(e -> matrixPane.refresh());
        findBTN.setOnAction(this::find);
        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane root = new BorderPane();
        HBox controls = new HBox(5, refreshBTN, findBTN);
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setPadding(new Insets(5));
        root.setCenter(matrixPane);
        root.setBottom(controls);
        return root;
    }

    private void find(ActionEvent e) {
        matrixPane.showLargestBlock();
    }
}
