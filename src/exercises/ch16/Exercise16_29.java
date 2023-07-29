package exercises.ch16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Exercise16_29 extends Application {
    private LocalDate now = LocalDate.now();
    private final Label label = new Label();
    private final GridPane grid = new GridPane();
    private final Font font = Font.font(16);

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();
        showCalendar();

        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane root = new BorderPane();
        label.setFont(font);
        root.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);

        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);
        root.setCenter(grid);

        Button priorButton = new Button("Prior");
        Button nextButton = new Button("Next");
        priorButton.setOnAction(e -> showPriorMonth());
        nextButton.setOnAction(e -> showNextMonth());
        HBox controls = new HBox(5, priorButton, nextButton);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);
        return root;
    }

    private void showNextMonth() {
        now = now.plusMonths(1);
        showCalendar();
    }

    private void showPriorMonth() {
        now = now.minusMonths(1);
        showCalendar();
    }

    private void showCalendar() {
        grid.getChildren().clear();
        Month currentMonth = now.getMonth();
        String text = String.format("%s, %s", currentMonth.getDisplayName(TextStyle.SHORT, Locale.US), now.getYear());
        label.setText(text);

        for (DayOfWeek d : DayOfWeek.values()) {
            Label label = new Label(d.getDisplayName(TextStyle.FULL, Locale.US));
            label.setFont(font);
            grid.add(label, d.getValue() % 7, 0);
        }

        LocalDate date = now.minusDays(now.getDayOfMonth() - 1);
        date = date.minusDays(date.getDayOfWeek().getValue());
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Label label = new Label(Integer.toString(date.getDayOfMonth()));
                label.setFont(font);
                if (!date.getMonth().equals(currentMonth)) {
                    label.setTextFill(Color.GRAY);
                }
                grid.add(label, j, i);
                date = date.plusDays(1);
            }
        }
    }
}
