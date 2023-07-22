package exercises.ch16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_20 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        CountUpStopwatch watch = new CountUpStopwatch();
        root.setCenter(watch);

        Button startButton = new Button("Start");
        Button clearButton = new Button("Clear");
        HBox buttons = new HBox(5, startButton, clearButton);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(buttons);

        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> watch.tick()));
        animation.setCycleCount(Animation.INDEFINITE);
        startButton.setOnAction(e -> {
            switch (animation.getStatus()) {
                case STOPPED, PAUSED -> {
                    animation.play();
                    startButton.setText("Pause");
                }
                case RUNNING -> {
                    animation.pause();
                    startButton.setText("Resume");
                }
            }
        });
        clearButton.setOnAction(e -> watch.reset());

        stage.setScene(new Scene(root, 250, 150));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class CountUpStopwatch extends Label {
    private int secondsTotal = 0;

    public CountUpStopwatch() {
        setFont(Font.font(50));
        showTime();
    }

    private void showTime() {
        int hours = secondsTotal / 60 / 60;
        int minutes = (secondsTotal / 60) % 60;
        int seconds = secondsTotal % 60;
        setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public void tick() {
        secondsTotal++;
        showTime();
    }

    public void reset() {
        secondsTotal = 0;
        showTime();
    }

}
