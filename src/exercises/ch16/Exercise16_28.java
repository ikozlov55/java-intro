package exercises.ch16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise16_28 extends Application {
    private final File textDir = new File("src/exercises/ch16/text");
    private final TextArea textArea = new TextArea();
    private int slideNumber = 0;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        textArea.setEditable(false);
        root.setCenter(textArea);

        showNextSlide();
        Timeline timeline = new Timeline(new KeyFrame(new Duration(1000), e -> showNextSlide()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        textArea.setOnMouseClicked(e -> {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
            } else {
                timeline.play();
            }
        });

        stage.setScene(new Scene(root, 200, 200));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void showNextSlide() {
        int maxSlides = 9;
        File file = new File(String.format("%s/slide%d.txt", textDir.getPath(), slideNumber));
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder text = new StringBuilder();
            while (scanner.hasNext()) {
                text.append(scanner.nextLine());
                text.append("\n");
            }
            textArea.setText(text.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        slideNumber = slideNumber < maxSlides ? slideNumber + 1 : 0;
    }
}
