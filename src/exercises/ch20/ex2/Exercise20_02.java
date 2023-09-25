package exercises.ch20.ex2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;

public class Exercise20_02 extends Application {
    private final TextField numberTF = new TextField();
    private final TextArea textArea = new TextArea();
    private final Button sortBTN = new Button("Sort");
    private final Button shuffleBTN = new Button("Shuffle");
    private final Button reverseBTN = new Button("Reverse");
    private final LinkedList<Integer> list = new LinkedList<>();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        numberTF.setOnAction(this::addNumber);
        sortBTN.setOnAction(this::sort);
        shuffleBTN.setOnAction(this::shuffle);
        reverseBTN.setOnAction(this::reverse);

        stage.setScene(new Scene(makeRootPane(), 300, 200));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane pane = new BorderPane();
        HBox top = new HBox(5, new Label("Enter a number:"), numberTF);
        top.setPadding(new Insets(5));
        top.setAlignment(Pos.BASELINE_CENTER);

        HBox buttons = new HBox(5, sortBTN, shuffleBTN, reverseBTN);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.BASELINE_CENTER);

        textArea.setWrapText(true);

        pane.setTop(top);
        pane.setCenter(textArea);
        pane.setBottom(buttons);
        return pane;
    }

    private void addNumber(ActionEvent e) {
        try {
            int number = Integer.parseInt(numberTF.getText());
            if (!list.contains(number)) {
                list.addLast(number);
            }
        } catch (NumberFormatException ignore) {
        }
        numberTF.clear();
        showList();
    }

    private void sort(ActionEvent e) {
        Collections.sort(list);
        showList();
    }

    private void shuffle(ActionEvent e) {
        Collections.shuffle(list);
        showList();
    }

    private void reverse(ActionEvent e) {
        Collections.reverse(list);
        showList();
    }

    private void showList() {
        textArea.setText(String.join(" ", list.stream().map(x -> Integer.toString(x)).toList()));
    }
}
