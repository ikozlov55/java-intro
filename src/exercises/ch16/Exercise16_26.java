package exercises.ch16;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Exercise16_26 extends Application {
    final double width = 300;
    final double height = 300;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        MediaPlayer mediaPlayer = getMediaPlayer();
        ImageView imageView = getImageView();
        Line path = new Line(
                width / 2, height + imageView.getFitHeight(),
                width / 2, imageView.getFitHeight() / 2 + 50
        );
        path.setStroke(Color.TRANSPARENT);
        root.getChildren().addAll(imageView, path);
        PathTransition animation = new PathTransition(Duration.seconds(5), path, imageView);
        mediaPlayer.setOnReady(() -> {
            animation.play();
            mediaPlayer.play();
        });

        stage.setScene(new Scene(root, width, height));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private MediaPlayer getMediaPlayer() {
        String path = new File("src/resources/media/anthem.mp3").toURI().toString();
        Media media = new Media(path);
        return new MediaPlayer(media);
    }

    private ImageView getImageView() {
        ImageView imageView = new ImageView(new Image("resources/image/flag6.gif"));
        imageView.setFitHeight(height / 3);
        imageView.setFitWidth(width / 2);
        return imageView;
    }
}
