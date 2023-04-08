package exercises.ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_17 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HangmanPane pane = new HangmanPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }


}

class HangmanPane extends Pane {

    private void paint() {
        getChildren().clear();
        Arc arc = new Arc(getWidth() / 4, getHeight() - 10, getWidth() / 4 - 20, 40, 0, 180);
        arc.setType(ArcType.OPEN);
        arc.setFill(null);
        arc.setStroke(Color.BLACK);
        Line verticalBeam = new Line(
                arc.getCenterX(), 10,
                arc.getCenterX(), arc.getCenterY() - arc.getRadiusY()
        );
        Line horizontalBeam = new Line(
                verticalBeam.getStartX(), verticalBeam.getStartY(),
                verticalBeam.getStartX() + getHeight() / 3, verticalBeam.getStartY()
        );
        Line rope = new Line(
                horizontalBeam.getEndX(), horizontalBeam.getEndY(),
                horizontalBeam.getEndX(), getHeight() / 2
        );
        Circle head = new Circle(rope.getStartX(), 80, 30);
        head.setFill(Color.WHITE);
        head.setStroke(Color.BLACK);
        Line leftArm = new Line(
                head.getCenterX(), head.getCenterY(),
                head.getCenterX() - 80, getHeight() / 2 - 20
        );
        Line rightArm = new Line(
                head.getCenterX(), head.getCenterY(),
                head.getCenterX() + 80, getHeight() / 2 - 20
        );
        Line leftLeg = new Line(
                rope.getEndX(), rope.getEndY(),
                head.getCenterX() - 80, getHeight() * (2.0 / 3)
        );
        Line rightLeg = new Line(
                rope.getEndX(), rope.getEndY(),
                head.getCenterX() + 80, getHeight() * (2.0 / 3)
        );


        getChildren().addAll(arc, verticalBeam, horizontalBeam, rope, leftArm, rightArm, leftLeg, rightLeg, head);
    }

    @Override
    protected void setHeight(double v) {
        super.setHeight(v);
        paint();
    }

    @Override
    protected void setWidth(double v) {
        super.setWidth(v);
        paint();
    }
}

