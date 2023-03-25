package exercises.ch14;

import exercises.ch14.Resources;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Exercise14_3 extends Application {
    private ArrayList<String> cards;

    @Override
    public void start(Stage stage) throws Exception {
        initCards();
        HBox box = new HBox();
        box.setPadding(new Insets(10));
        box.setSpacing(10);
        for (int i = 0; i < 3; i++) {
            String imagePath = Resources.get("card", cards.get(i));
            ImageView imageView = new ImageView(imagePath);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);
            box.getChildren().add(imageView);
        }
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.setTitle("Exercise14_03");
        stage.show();
    }

    public static void run() {
        Application.launch();
    }

    private void initCards() {
        cards = new ArrayList<>(IntStream.rangeClosed(1, 52).mapToObj(x -> x + ".png").toList());
        Collections.shuffle(cards);
    }
}
