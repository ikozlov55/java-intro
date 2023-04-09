package exercises.ch14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Exercise14_27 extends Application {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        DetailedClockPane clock = new DetailedClockPane();
        Label lblCurrentTime = new Label(clock.toString());
        lblCurrentTime.setFont(Font.font("", FontWeight.BOLD, 20));

        BorderPane pane = new BorderPane();
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

}

class DetailedClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    /**
     * Construct a default clock with the current time
     */
    public DetailedClockPane() {
        setCurrentTime();
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public DetailedClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set a new hour
     */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /**
     * Return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Set a new minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /**
     * Return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set a new second
     */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /**
     * Paint the clock
     */
    private void paintClock() {
        getChildren().clear();

        // Initialize clock parameters
        double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        ArrayList<Node> marks = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            double markLength = i % 5 == 0 ? clockRadius * 0.9 : clockRadius * 0.95;
            double startX = centerX + markLength * Math.sin(i * (2 * Math.PI / 60));
            double startY = centerY - markLength * Math.cos(i * (2 * Math.PI / 60));
            double endX = centerX + clockRadius * Math.sin(i * (2 * Math.PI / 60));
            double endY = centerY - clockRadius * Math.cos(i * (2 * Math.PI / 60));
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.BLACK);
            if (i % 5 == 0) {
                double x = centerX - 5 + clockRadius * 0.8 * Math.sin(i * (2 * Math.PI / 60));
                double y = centerY + 5 - clockRadius * 0.8 * Math.cos(i * (2 * Math.PI / 60));
                Text t = new Text(x, y, Integer.toString(i == 0 ? 12 : 12 - (12 - i / 5)));
                t.setFont(Font.font("", FontWeight.SEMI_BOLD, 20));
                marks.add(t);
            }
            marks.add(line);
        }

        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength * Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);

        getChildren().addAll(circle, sLine, mLine, hLine);
        getChildren().addAll(marks);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }
}




