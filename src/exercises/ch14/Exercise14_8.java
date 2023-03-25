package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_8 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final int cardsTotal = 54;
        final int cardsPerLine = 9;
        GridPane grid = new GridPane();
        for (int i = 0; i < cardsPerLine; i++) {
            for (int j = 0; j < cardsTotal / cardsPerLine; j++) {
                int cardNumber = j * cardsPerLine + i + 1;
                String imagePath = Resources.get("card", cardNumber + ".png");
                ImageView imageView = new ImageView(imagePath);
                grid.add(imageView, i, j);
            }
        }
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Exercise14_08");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }
}
