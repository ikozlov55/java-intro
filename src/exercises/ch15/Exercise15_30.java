package exercises.ch15;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Path;

public class Exercise15_30 extends Application {
    ImageView imageView = new ImageView();
    private int slideNumber = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(imageView);

        showNextSlide();
        Timeline timeline = new Timeline(new KeyFrame(new Duration(1000), e -> showNextSlide()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        root.setOnMouseClicked(e -> {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
            } else {
                timeline.play();
            }
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.setWidth(200);
        stage.setHeight(200);
        stage.show();
    }

    private void showNextSlide() {
        int maxSlides = 10;
        slideNumber = slideNumber < maxSlides ? slideNumber + 1 : 1;
        Path path = Path.of("resources", "image", "card", String.format("%d.png", slideNumber));
        imageView.setImage(new Image(path.toString()));
    }
}
