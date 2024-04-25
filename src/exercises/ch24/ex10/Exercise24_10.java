package exercises.ch24.ex10;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise24_10 extends Application {
    StackPane stackPane = new StackPane();
    private final TextField valueTF = new TextField();
    private final Button pushBTN = new Button("Push");
    private final Button popBTN = new Button("Pop");
    private final Button peekBTN = new Button("Peek");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        pushBTN.setOnAction(this::push);
        popBTN.setOnAction(e -> stackPane.pop());
        peekBTN.setOnAction(e -> stackPane.peek());
        stage.setScene(new Scene(root, 650, 250));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(stackPane);
        stackPane.setPadding(new Insets(5));

        valueTF.setPrefColumnCount(2);
        HBox controls = new HBox(5, new Text("Enter a value:"), valueTF,
                pushBTN, popBTN, peekBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        return root;
    }

    private void push(ActionEvent e) {
        try {
            int value = Integer.parseInt(valueTF.getText());
            stackPane.push(value);
        } catch (NumberFormatException ignored) {

        } finally {
            valueTF.clear();
        }
    }
}
