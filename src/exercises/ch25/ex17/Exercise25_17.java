package exercises.ch25.ex17;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Set;

public class Exercise25_17 extends Application {
    private final TextField inputTF = new TextField();
    private final TextField bitInputTF = new TextField();
    private final Button encodeBTN = new Button("Show Huffman Tree");
    private final Button decodeBTN = new Button("Decode To Text");
    private final Label label = new Label();
    private final HuffmanTreeView treeView = new HuffmanTreeView();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        encodeBTN.setOnAction(this::encode);
        decodeBTN.setOnAction(this::decode);
        TextFormatter<String> bitFormatter = new TextFormatter<>(c -> {
            if (!c.isAdded()) return c;
            if (!Set.of("0", "1").contains(c.getText())) {
                c.setText("");
            }
            return c;
        });
        bitInputTF.setTextFormatter(bitFormatter);
        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRoot() {
        BorderPane root = new BorderPane();
        HBox row1 = new HBox(5, new Label("Enter a text:"), inputTF, encodeBTN);
        row1.setAlignment(Pos.BASELINE_CENTER);
        HBox row2 = new HBox(5, new Label("Enter a bit string:"), bitInputTF, decodeBTN);
        row2.setAlignment(Pos.BASELINE_CENTER);
        VBox controls = new VBox(5, row1, row2);
        BorderPane.setMargin(controls, new Insets(5));
        root.setTop(controls);

        root.setCenter(treeView);

        BorderPane.setMargin(label, new Insets(5));
        BorderPane.setAlignment(label, Pos.CENTER);
        root.setBottom(label);
        return root;
    }

    private void encode(ActionEvent event) {
        String input = inputTF.getText().strip();
        if (input.isEmpty()) {
            return;
        }
        treeView.displayTree(input);
        label.setText(String.format("%s encoded to %s", input, treeView.getEncodedBits()));
    }

    private void decode(ActionEvent event) {
        String input = bitInputTF.getText().strip();
        if (input.isEmpty()) {
            return;
        }
        label.setText(String.format("%s is decoded to %s", input, treeView.decodeBits(input)));
    }
}
