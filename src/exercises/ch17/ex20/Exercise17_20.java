package exercises.ch17.ex20;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

public class Exercise17_20 extends Application {
    private final TextField fileTF = new TextField();
    private final TextArea textArea = new TextArea();
    private final Button saveBTN = new Button("Save the change");

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();
        root.setOnKeyPressed(this::showFile);
        saveBTN.setOnAction(this::saveFile);
        stage.setScene(new Scene(root, 400, 200));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        BorderPane pane = new BorderPane();
        HBox top = new HBox(new Label("Enter a file: "), fileTF);
        fileTF.setPrefSize(300, 20);
        top.setAlignment(Pos.BASELINE_CENTER);
        top.setPadding(new Insets(5));
        top.setSpacing(5);

        pane.setTop(top);
        pane.setCenter(textArea);
        textArea.setWrapText(true);

        saveBTN.setDisable(true);
        pane.setBottom(saveBTN);
        BorderPane.setAlignment(saveBTN, Pos.CENTER);
        BorderPane.setMargin(saveBTN, new Insets(5));
        return pane;
    }

    private void showFile(KeyEvent e) {
        if (e.getCode() != KeyCode.ENTER || fileTF.getText().isEmpty()) return;
        String path = fileTF.getText();
        File file = new File(path);
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            StringBuilder builder = new StringBuilder();
            while (stream.available() > 0) {
                int b = stream.read();
                builder.append(getBinaryString(b));
            }
            textArea.setText(builder.toString());
            saveBTN.setDisable(false);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveFile(ActionEvent e) {
        if (textArea.getText().isEmpty() || fileTF.getText().isEmpty()) return;
        String text = textArea.getText();
        String path = fileTF.getText();
        File file = new File(path);
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < text.length(); i += 8) {
                String byteString = text.substring(i, i + 8);
                int b = Integer.parseInt(byteString, 2);
                stream.write(b);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getBinaryString(int value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            builder.append((value >> i) & 1);
        }
        return builder.toString();
    }
}
