package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_20 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        Exercise14_20.drawArrowLine(
                Math.random() * stage.getWidth(),
                Math.random() * stage.getHeight(),
                Math.random() * stage.getWidth(),
                Math.random() * stage.getHeight(),
                root
        );
        stage.setResizable(false);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    public static void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane) {
        Line line = new Line(startX, startY, endX, endY);
        double distance = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        double lineAngle;
        double addAngle;
        if (startY > endY) {
            lineAngle = Math.asin((endX - startX) / distance);
            addAngle = Math.PI * 0.75;
        } else {
            lineAngle = Math.asin((startX - endX) / distance);
            addAngle = Math.PI * 0.25;
        }
        Line arrow1 = new Line(
                endX,
                endY,
                endX + 20 * Math.sin(lineAngle + addAngle),
                endY - 20 * Math.cos(lineAngle + addAngle)
        );
        Line arrow2 = new Line(
                endX,
                endY,
                endX + 20 * Math.sin(lineAngle - addAngle),
                endY - 20 * Math.cos(lineAngle - addAngle)
        );
        pane.getChildren().addAll(line, arrow1, arrow2);
    }

}



