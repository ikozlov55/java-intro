package exercises.ch16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_23 extends Application {

    private final BorderPane root = new BorderPane();
    private final Button startButton = new Button("Start Animation");
    private final ImageView imageView = new ImageView();
    private final TextField animationSpeedTF = new TextField("200");
    private final TextField filePrefixTF = new TextField("L");
    private final TextField numberOfImagesTF = new TextField("52");
    private final TextField audioFileUrlTF = new TextField("https://assets.mixkit.co/active_storage/sfx/2298/2298-preview.mp3");


    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        drawLayout();
        startButton.setOnAction(e -> startAnimation());
        stage.setScene(new Scene(root, 800, 750));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void startAnimation() {
        try {
            Duration duration = new Duration(Double.parseDouble(animationSpeedTF.getText()));
            String prefix = filePrefixTF.getText();
            int numberOfImages = Integer.parseInt(numberOfImagesTF.getText());
            Media media = new Media(audioFileUrlTF.getText());
            MediaPlayer player = new MediaPlayer(media);
            ImageHandler imageHandler = new ImageHandler(prefix, numberOfImages);
            Timeline animation = new Timeline(new KeyFrame(duration, ev -> imageHandler.showNextImage(imageView)));
            animation.setCycleCount(numberOfImages);
            animation.play();
            animation.setOnFinished(e -> player.stop());
            player.play();
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input!");
        }
    }

    private void drawLayout() {
        root.setPadding(new Insets(20, 5, 5, 5));
        root.setTop(startButton);
        BorderPane.setAlignment(startButton, Pos.BASELINE_RIGHT);

        root.setCenter(imageView);

        GridPane controls = new GridPane();
        controls.setHgap(10);
        controls.setPadding(new Insets(5));
        ColumnConstraints column1 = new ColumnConstraints(180);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        controls.getColumnConstraints().addAll(column1, column2);

        controls.add(new Label("Enter information for animation"), 0, 0);
        controls.add(new Label("Animation speed in milliseconds"), 0, 1);
        controls.add(animationSpeedTF, 1, 1);
        controls.add(new Label("Image file prefix"), 0, 2);
        controls.add(filePrefixTF, 1, 2);
        controls.add(new Label("Number of images"), 0, 3);
        controls.add(numberOfImagesTF, 1, 3);
        controls.add(new Label("Audio file URL"), 0, 4);
        controls.add(audioFileUrlTF, 1, 4);
        root.setBottom(controls);
    }
}

class ImageHandler {
    String prefix;
    int numberOfImages;
    int currentImageNumber;

    public ImageHandler(String prefix, int numberOfImages) {
        this.prefix = prefix;
        this.numberOfImages = numberOfImages;
        this.currentImageNumber = 1;
    }

    public void showNextImage(ImageView imageView) {
        if (currentImageNumber > numberOfImages) currentImageNumber = 1;
        Image image = new Image(String.format("resources/image/%s%d.gif", prefix, currentImageNumber));
        imageView.setImage(image);
        currentImageNumber++;
    }
}