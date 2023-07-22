package exercises.ch16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Exercise16_21 extends Application {
    private final TextField textField = new TextField();
    private int seconds = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        textField.setFont(Font.font(50));
        textField.setAlignment(Pos.BASELINE_CENTER);
        textField.setOnKeyPressed(this::handleEnterPress);

        stage.setScene(new Scene(textField, 250, 100));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void handleEnterPress(KeyEvent e) {
        if (e.getCode() != KeyCode.ENTER) return;
        try {
            textField.setEditable(false);
            seconds = Integer.parseInt(textField.getText());
            Timeline animation = new Timeline(new KeyFrame(Duration.seconds(1), ev -> startTimer()));
            animation.setCycleCount(seconds);
            animation.play();
            animation.setOnFinished(ev -> soundAlarm());
        } catch (NumberFormatException ex) {
            textField.setText("");
        }
    }

    private void startTimer() {
        seconds--;
        textField.setText(Integer.toString(seconds));
    }

    private void soundAlarm() {
        File file = new File("src/resources/media/alarm.mp3");
        MediaPlayer player = new MediaPlayer(new Media(file.toURI().toString()));
        player.play();
    }
}
