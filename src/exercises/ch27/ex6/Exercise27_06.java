package exercises.ch27.ex6;

import exercises.utils.UtilsFx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise27_06 extends Application {
    HashSetView<Integer> hashSetView = new HashSetView<>();
    private final TextField initTableSizeTF = new TextField();
    private final TextField loadFactorTF = new TextField();
    private final TextField valueTF = new TextField();
    private final Button searchBTN = new Button("Search");
    private final Button insertBTN = new Button("Insert");
    private final Button removeBTN = new Button("Remove");
    private final Button removeAllBTN = new Button("Remove All");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        initTableSizeTF.setTextFormatter(UtilsFx.intFormatter());
        initTableSizeTF.setOnAction(this::setCapacity);
        loadFactorTF.setOnAction(this::setLoadFactor);
        searchBTN.setOnAction(this::search);
        insertBTN.setOnAction(this::insert);
        removeBTN.setOnAction(this::remove);
        removeAllBTN.setOnAction(this::removeAll);
        valueTF.setTextFormatter(UtilsFx.intFormatter());
        stage.setScene(new Scene(root, 500, 500));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        initTableSizeTF.setPrefColumnCount(2);
        initTableSizeTF.setText(Integer.toString(hashSetView.getCapacity()));
        loadFactorTF.setPrefColumnCount(2);
        loadFactorTF.setText(Double.toString(hashSetView.getLoadFactorThreshold()));
        valueTF.setPrefColumnCount(2);
        HBox hBox1 = new HBox(5,
                new Label("Enter Initial Table Size:"),
                initTableSizeTF,
                new Label("Enter a Load Factor Threshold:"),
                loadFactorTF
        );
        hBox1.setAlignment(Pos.BASELINE_CENTER);
        HBox hBox2 = new HBox(5,
                new Label("Enter a key:"),
                valueTF,
                searchBTN, insertBTN, removeBTN, removeAllBTN
        );
        hBox2.setAlignment(Pos.BASELINE_CENTER);
        VBox controls = new VBox(5, hBox1, hBox2);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        root.setBottom(controls);

        root.setCenter(hashSetView);
        return root;
    }

    private void setCapacity(ActionEvent e) {
        if (initTableSizeTF.getText().isBlank()) {
            return;
        }
        int value = Integer.parseInt(initTableSizeTF.getText());
        initTableSizeTF.setText("");
        hashSetView.setCapacity(value);
    }

    private void setLoadFactor(ActionEvent e) {
        try {
            double value = Double.parseDouble(loadFactorTF.getText());
            hashSetView.setLoadFactorThreshold(value);
        } catch (NumberFormatException ignored) {
        } finally {
            loadFactorTF.setText("");
        }
    }

    private void search(ActionEvent e) {
        if (valueTF.getText().isBlank()) {
            return;
        }
        int value = Integer.parseInt(valueTF.getText());
        hashSetView.search(value);
    }

    private void insert(ActionEvent e) {
        if (valueTF.getText().isBlank()) {
            return;
        }
        int value = Integer.parseInt(valueTF.getText());
        hashSetView.insert(value);
        valueTF.setText("");
    }

    private void remove(ActionEvent e) {
        if (valueTF.getText().isBlank()) {
            return;
        }
        int value = Integer.parseInt(valueTF.getText());
        hashSetView.remove(value);
        valueTF.setText("");
    }

    private void removeAll(ActionEvent e) {
        hashSetView.removeAll();
    }
}
