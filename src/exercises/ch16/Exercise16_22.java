package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

public class Exercise16_22 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        File file = new File("src/resources/media/beep.mp3");
        AudioClip clip = new AudioClip(file.toURI().toString());

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            clip.setCycleCount(1);
            clip.play();
        });

        Button loopButton = new Button("Loop");
        loopButton.setOnAction(e -> {
            if (clip.isPlaying()) return;
            clip.setCycleCount(AudioClip.INDEFINITE);
            clip.play();
        });

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> clip.stop());

        HBox root = new HBox(10, playButton, loopButton, stopButton);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root, 250, 50));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}
