package exercises.ch16;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_19 extends Application {
    private final FanController fanController1 = new FanController();
    private final FanController fanController2 = new FanController();
    private final FanController fanController3 = new FanController();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        fanController1.prefHeightProperty().bind(stage.heightProperty());
        fanController2.prefHeightProperty().bind(stage.heightProperty());
        fanController3.prefHeightProperty().bind(stage.heightProperty());
        HBox fans = new HBox(5, fanController1, fanController2, fanController3);
        fans.setPadding(new Insets(5));
        fans.setAlignment(Pos.BASELINE_CENTER);
        root.setCenter(fans);

        Button startAllButton = new Button("Start All");
        startAllButton.setOnAction(e -> {
            fanController1.start();
            fanController2.start();
            fanController3.start();
        });
        Button stopAllButton = new Button("Stop All");
        stopAllButton.setOnAction(e -> {
            fanController1.stop();
            fanController2.stop();
            fanController3.stop();
        });
        HBox buttons = new HBox(5, startAllButton, stopAllButton);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(buttons);

        stage.setScene(new Scene(root, 600, 230));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }
}

class FanController extends BorderPane {
    private final Timeline animation;
    private final Fan fan = new Fan();

    public FanController() {
        setStyle("-fx-border-color: black");
        setCenter(fan);

        Button pauseButton = new Button("Pause");
        Button resumeButton = new Button("Resume");
        Button reverseButton = new Button("Reverse");
        HBox buttons = new HBox(5, pauseButton, resumeButton, reverseButton);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.BASELINE_CENTER);
        setTop(buttons);

        Slider speedSlider = new Slider(0, 20, 10);
        speedSlider.setPadding(new Insets(5, 10, 5, 10));
        setBottom(speedSlider);

        animation = new Timeline(
                new KeyFrame(Duration.millis(100), e -> fan.move())
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.rateProperty().bind(speedSlider.valueProperty());
        animation.play();

        pauseButton.setOnAction(e -> animation.stop());
        resumeButton.setOnAction(e -> animation.play());
        reverseButton.setOnAction(e -> fan.reverse());
    }

    public void start() {
        animation.play();
    }

    public void stop() {
        animation.stop();
    }
}
