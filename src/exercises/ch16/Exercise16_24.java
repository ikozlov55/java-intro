package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Exercise16_24 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        String path = new File("src/resources/media/sample.mp4").toURI().toString();
        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
            if (playButton.getText().equals(">")) {
                mediaPlayer.play();
                playButton.setText("||");
            } else {
                mediaPlayer.pause();
                playButton.setText(">");
            }
        });
        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));

        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));

        MediaTimeLabel timeLabel = new MediaTimeLabel();
        Slider slTime = new Slider();
        mediaPlayer.currentTimeProperty().addListener((ov, oldValue, newValue) -> {
            timeLabel.setCurrent(newValue);
            slTime.setValue(newValue.toSeconds());
        });
        slTime.setOnDragDetected(e -> {
            mediaPlayer.pause();
            playButton.setText(">");
        });
        slTime.setOnMouseDragged(e -> {
            mediaPlayer.seek(Duration.seconds(slTime.getValue()));
        });
        slTime.setOnMouseReleased(e -> {
            if (mediaPlayer.getStatus() != MediaPlayer.Status.PAUSED) return;
            mediaPlayer.play();
            playButton.setText("||");
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(
                playButton, rewindButton,
                new Label("Time"), slTime, timeLabel,
                new Label("Volume"), slVolume
        );
        hBox.setDisable(true);
        mediaPlayer.setOnReady(() -> {
            hBox.setDisable(false);
            timeLabel.setTotal(mediaPlayer.getTotalDuration());
            slTime.setMax(mediaPlayer.getTotalDuration().toSeconds());
        });

        BorderPane root = new BorderPane();
        root.setCenter(mediaView);
        root.setBottom(hBox);

        stage.setScene(new Scene(root, 700, 530));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class MediaTimeLabel extends Label {
    private Duration total = new Duration(0);
    private Duration current = new Duration(0);

    public void setCurrent(Duration current) {
        this.current = current;
        showTime();
    }

    public void setTotal(Duration total) {
        this.total = total;
        showTime();
    }

    private void showTime() {
        String text = String.format("%s/%s", durationToString(current), durationToString(total));
        setText(text);
    }

    private String durationToString(Duration duration) {
        int totalSeconds = (int) duration.toSeconds();
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 60 / 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}