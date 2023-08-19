package exercises.ch17.ex11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise17_11 extends Application {
    private final TextField fileTF = new TextField();
    private final TextField filesNumberTF = new TextField();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Text label = new Text("If you split a file named temp.txt into 3 files, the three smaller files are temp.txt.1, temp.txt.2 and temp.txt.3.");
        label.setWrappingWidth(400);

        GridPane form = new GridPane();
        form.add(new Label("Enter a file:"), 0, 0);
        form.add(fileTF, 1, 0);
        form.add(new Label("Specify the number of smaller files:"), 0, 1);
        form.add(filesNumberTF, 1, 1);
        form.setPadding(new Insets(5));
        form.setHgap(5);
        form.setVgap(5);

        Button startBTN = new Button("Start");
        startBTN.setOnAction(this::start);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));
        root.setTop(label);
        root.setCenter(form);
        root.setBottom(startBTN);
        BorderPane.setAlignment(startBTN, Pos.CENTER);

        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void start(ActionEvent e) {
        if (fileTF.getText().isEmpty() || filesNumberTF.getText().isEmpty()) return;
        try {
            String fileName = fileTF.getText();
            File file = new File(fileName);
            int n = Integer.parseInt(filesNumberTF.getText());

            try (FileInputStream input = new FileInputStream(file)) {
                int chunkSize = input.available() / n;
                for (int i = 1; i <= n; i++) {
                    String filePath = String.format("src/exercises/ch17/data/%s.%d", file.getName(), i);
                    byte[] chunk = new byte[i == n ? input.available() : chunkSize];
                    input.read(chunk);
                    try (FileOutputStream output = new FileOutputStream(filePath);) {
                        output.write(chunk);
                    }
                }
            }
        } catch (NumberFormatException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
