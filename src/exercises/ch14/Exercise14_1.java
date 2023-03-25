package exercises.ch14;

import exercises.ch14.Resources;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new ImageView(Resources.get("germany.gif")), 0, 0);
        grid.add(new ImageView(Resources.get("china.gif")), 1, 0);
        grid.add(new ImageView(Resources.get("fr.gif")), 0, 1);
        grid.add(new ImageView(Resources.get("us.gif")), 1, 1);

        Scene scene = new Scene(grid);
        stage.setTitle("Exercise14_01");
        stage.setScene(scene);
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
