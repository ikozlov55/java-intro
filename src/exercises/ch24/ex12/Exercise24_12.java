package exercises.ch24.ex12;

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

public class Exercise24_12 extends Application {
    private final QueuePane queuePane = new QueuePane();
    private final TextField valueTF = new TextField();
    private final Button enqueueBTN = new Button("Enqueue");
    private final Button dequeueBTN = new Button("Dequeue");
    private final Button restartBTN = new Button("Start Over");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        enqueueBTN.setOnAction(this::enqueue);
        dequeueBTN.setOnAction(e -> queuePane.dequeue());
        restartBTN.setOnAction(e -> queuePane.restart());
        stage.setScene(new Scene(root, 650, 350));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(queuePane);
        queuePane.setPadding(new Insets(5));

        valueTF.setPrefColumnCount(2);
        HBox controls = new HBox(5, new Text("Enter a value:"), valueTF,
                enqueueBTN, dequeueBTN, restartBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        return root;
    }

    private void enqueue(ActionEvent e) {
        try {
            int value = Integer.parseInt(valueTF.getText());
            queuePane.enqueue(value);
        } catch (NumberFormatException ignore) {
        } finally {
            valueTF.clear();
        }
    }

}
