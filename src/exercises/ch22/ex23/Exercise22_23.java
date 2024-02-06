package exercises.ch22.ex23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

public class Exercise22_23 extends Application {
    private final EightQueensSolver solver = new EightQueensSolver();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox(10);
        List<boolean[][]> solutions = solver.findSolutions();

        for (int i = 0; i < solutions.size(); i++) {
            String label = String.format("Solution %d", i + 1);
            EightQueensBoard board = new EightQueensBoard(solutions.get(i), label);
            hBox.getChildren().add(board);
        }
        ScrollPane root = new ScrollPane();
        root.setContent(hBox);
        root.setPrefViewportWidth(900);
        root.setFitToHeight(true);
        stage.setTitle(getClass().getSimpleName());
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }


}
