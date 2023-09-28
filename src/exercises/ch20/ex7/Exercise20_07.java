package exercises.ch20.ex7;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise20_07 extends Application {
    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        HangManGamePane game = new HangManGamePane();
        BorderPane.setMargin(root, new Insets(50));

        root.setCenter(game);
        stage.setScene(new Scene(root, 400, 400));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
        game.requestFocus();
        game.newGame();
    }
}

class HangManGamePane extends Pane {
    private Shape[] parts;
    private final Label wordLabel = new Label();
    private final Label missedLabel = new Label();
    private HangManGame game;

    public void newGame() {
        double x = getWidth() / 2;
        Line rope = new Line(x, 20, x, 60);
        Circle head = new Circle(x, rope.getEndY() + 20, 20, Color.WHITE);
        head.setStroke(Color.BLACK);
        Line body = new Line(x, head.getCenterY() + head.getRadius(), x, head.getCenterY() + head.getRadius() + 60);
        Line leftArm = new Line(x, head.getCenterY() + head.getRadius(), x - 50, 150);
        Line rightArm = new Line(x, head.getCenterY() + head.getRadius(), x + 50, 150);
        Line leftLeg = new Line(x, body.getEndY(), x - 40, 220);
        Line rightLeg = new Line(x, body.getEndY(), x + 50, 220);
        parts = new Shape[]{rope, head, leftArm, rightArm, body, leftLeg, rightLeg};
        game = new HangManGame(parts.length);
        setOnKeyPressed(this::handleKeyPress);
        paint();
    }

    private void paint() {
        getChildren().clear();
        Arc arc = new Arc(getWidth() / 5, getHeight() - 20, 60, 20, 0, 180);
        arc.setFill(Color.TRANSPARENT);
        arc.setStroke(Color.BLACK);
        arc.setType(ArcType.OPEN);
        Polyline line = new Polyline(
                getWidth() / 5, getHeight() - 40,
                getWidth() / 5, 20,
                getWidth() / 2, 20
        );
        Font font = Font.font(15);
        wordLabel.setLayoutX(getWidth() / 3);
        wordLabel.setLayoutY(getHeight() * 0.8);
        wordLabel.setFont(font);
        missedLabel.setLayoutX(getWidth() / 3);
        missedLabel.setLayoutY(wordLabel.getLayoutY() + 20);
        missedLabel.setFont(font);
        Group hangMan = new Group();
        for (int i = 0; i < parts.length - game.getTriesLeft(); i++) {
            hangMan.getChildren().add(parts[i]);
        }
        getChildren().addAll(arc, line, wordLabel, missedLabel, hangMan);

        if (game.getTriesLeft() > 0 && !game.allGuessed()) {
            wordLabel.setText("Guess a word: " + game.getWord(true));
            String missed = game.getMissed();
            if (!missed.isEmpty()) {
                missedLabel.setText("Missed letters: " + missed);
            } else {
                missedLabel.setText("");
            }
            return;
        }
        wordLabel.setText("The word is: " + game.getWord(false));
        missedLabel.setText("To continue the game press ENTER");
        if (!game.allGuessed()) {
            animate(hangMan);
        }
    }

    private void handleKeyPress(KeyEvent e) {
        System.out.println(e.getText());
        if (e.getCode() == KeyCode.ENTER && (game.getTriesLeft() == 0 || game.allGuessed())) {
            newGame();
            return;
        }
        String input = e.getText();
        if (input.length() != 1) {
            return;
        }
        char c = e.getText().charAt(0);
        if (!Character.isLetter(c)) {
            return;
        }
        game.guess(c);
        paint();
    }

    private void animate(Group hangMan) {
        Rotate rotate = new Rotate(20, getWidth() / 2, 20);
        hangMan.getTransforms().add(rotate);
        Timeline animation = new Timeline();
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), -20)));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
    }
}

