package exercises.ch15;

import exercises.ch14.Resources;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Exercise15_1 extends Application {
    private final ArrayList<String> deck = new ArrayList<>(IntStream.rangeClosed(1, 52).mapToObj(x -> x + ".png").toList());

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox box = new HBox();
        box.setSpacing(10);
        showCards(box);

        Button button = new Button("Refresh");
        button.setMinSize(100, 20);
        button.setOnAction(e -> showCards(box));

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(box);
        root.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(320);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void showCards(HBox container) {
        container.getChildren().clear();
        for (String img : selectCards()) {
            String imagePath = Resources.get("card", img);
            ImageView imageView = new ImageView(imagePath);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(150);
            container.getChildren().add(imageView);
        }
    }

    private ArrayList<String> selectCards() {
        Collections.shuffle(deck);
        return new ArrayList<>(deck.subList(0, 4));
    }
}
