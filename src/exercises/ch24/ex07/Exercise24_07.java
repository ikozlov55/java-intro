package exercises.ch24.ex07;

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

public class Exercise24_07 extends Application {
    private final LinkedListPane linkedListPane = new LinkedListPane();
    private final TextField valueTF = new TextField();
    private final TextField indexTF = new TextField();
    private final Button searchBTN = new Button("Search");
    private final Button insertBTN = new Button("Insert");
    private final Button deleteBTN = new Button("Delete");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        linkedListPane.add(1);
        linkedListPane.add(2);
        linkedListPane.add(3);
        searchBTN.setOnAction(this::search);
        insertBTN.setOnAction(this::insert);
        deleteBTN.setOnAction(this::delete);
        stage.setScene(new Scene(root, 650, 350));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setCenter(linkedListPane);
        linkedListPane.setMaxHeight(30);
        linkedListPane.setPadding(new Insets(5));

        indexTF.setPrefColumnCount(2);
        valueTF.setPrefColumnCount(2);
        HBox controls = new HBox(5, new Text("Enter a value:"), valueTF,
                new Text("Enter an index:"), indexTF, searchBTN, insertBTN, deleteBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        return root;
    }

    private void search(ActionEvent e) {
        try {
            int value = Integer.parseInt(valueTF.getText());
            linkedListPane.search(value);
        } finally {
            valueTF.clear();
            indexTF.clear();
        }
    }

    private void insert(ActionEvent e) {
        try {
            int value = Integer.parseInt(valueTF.getText());
            if (indexTF.getText().isEmpty()) {
                linkedListPane.add(value);
            } else {
                int index = Integer.parseInt(indexTF.getText());
                linkedListPane.add(index, value);
            }
        } finally {
            valueTF.clear();
            indexTF.clear();
        }
    }

    private void delete(ActionEvent e) {
        try {
            if (!valueTF.getText().isEmpty()) {
                int value = Integer.parseInt(valueTF.getText());
                linkedListPane.deleteValue(value);
            } else if (!indexTF.getText().isEmpty()) {
                int index = Integer.parseInt(indexTF.getText());
                linkedListPane.deleteIndex(index);
            }
        } finally {
            valueTF.clear();
            indexTF.clear();
        }
    }

}
