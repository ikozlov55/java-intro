package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_10 extends Application {

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Ellipse topEllipse = new Ellipse();
        topEllipse.centerXProperty().bind(stage.widthProperty().divide(2));
        topEllipse.centerYProperty().bind(stage.heightProperty().multiply(0.25));
        topEllipse.radiusXProperty().bind(stage.widthProperty().divide(2).subtract(20));
        topEllipse.radiusYProperty().bind(stage.heightProperty().divide(8));
        topEllipse.setFill(null);
        topEllipse.setStroke(Color.BLACK);

        Arc bottomArc = new Arc();
        bottomArc.centerXProperty().bind(stage.widthProperty().divide(2));
        bottomArc.centerYProperty().bind(stage.heightProperty().multiply(0.75));
        bottomArc.radiusXProperty().bind(stage.widthProperty().divide(2).subtract(20));
        bottomArc.radiusYProperty().bind(stage.heightProperty().divide(8));
        bottomArc.setStartAngle(180);
        bottomArc.setLength(180);
        bottomArc.setType(ArcType.OPEN);
        bottomArc.setFill(null);
        bottomArc.setStroke(Color.BLACK);

        Arc topArc = new Arc();
        topArc.centerXProperty().bind(stage.widthProperty().divide(2));
        topArc.centerYProperty().bind(stage.heightProperty().multiply(0.75));
        topArc.radiusXProperty().bind(stage.widthProperty().divide(2).subtract(20));
        topArc.radiusYProperty().bind(stage.heightProperty().divide(8));
        topArc.setStartAngle(0);
        topArc.setLength(180);
        topArc.setType(ArcType.OPEN);
        topArc.setFill(null);
        topArc.setStroke(Color.BLACK);
        topArc.getStrokeDashArray().addAll(6.0, 21.0);

        Group bottomEllipse = new Group(topArc, bottomArc);

        Line leftLine = new Line();
        leftLine.startXProperty().bind(topEllipse.centerXProperty().subtract(topEllipse.radiusXProperty()));
        leftLine.startYProperty().bind(topEllipse.centerYProperty());
        leftLine.endXProperty().bind(bottomArc.centerXProperty().subtract(bottomArc.radiusXProperty()));
        leftLine.endYProperty().bind(bottomArc.centerYProperty());

        Line rightLine = new Line();
        rightLine.startXProperty().bind(topEllipse.centerXProperty().add(topEllipse.radiusXProperty()));
        rightLine.startYProperty().bind(topEllipse.centerYProperty());
        rightLine.endXProperty().bind(bottomArc.centerXProperty().add(bottomArc.radiusXProperty()));
        rightLine.endYProperty().bind(bottomArc.centerYProperty());

        Group cylinder = new Group();
        cylinder.getChildren().addAll(topEllipse, bottomEllipse, leftLine, rightLine);

        BorderPane pane = new BorderPane();
        pane.setPrefWidth(400);
        pane.setPrefHeight(400);
        pane.setPadding(new Insets(10));
        pane.setCenter(cylinder);

        Scene scene = new Scene(pane);
        stage.setMinWidth(200);
        stage.setMinHeight(200);
        stage.setScene(scene);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}
