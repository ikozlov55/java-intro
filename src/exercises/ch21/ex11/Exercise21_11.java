package exercises.ch21.ex11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Exercise21_11 extends Application {
    private final ComboBox<Integer> yearCB = new ComboBox<>();
    private final ComboBox<String> genderCB = new ComboBox<>();
    private final Label statusLabel = new Label();
    private final TextField nameTF = new TextField();
    private final Button findBTN = new Button("Find Ranking");
    private final Map<String, Integer>[] boysNames = (Map<String, Integer>[]) new Map[10];
    private final Map<String, Integer>[] girlsNames = (Map<String, Integer>[]) new Map[10];

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRoot();
        findBTN.setOnAction(this::findHandler);
        stage.setScene(new Scene(root, 300, 150));
        stage.setTitle(getClass().getSimpleName());
        loadNames();
        stage.show();
    }

    private BorderPane makeRoot() {
        statusLabel.setPadding(new Insets(5));
        BorderPane root = new BorderPane();
        genderCB.getItems().addAll("Male", "Female");
        genderCB.setValue(genderCB.getItems().get(0));
        yearCB.getItems().addAll(IntStream.rangeClosed(2001, 2010).boxed().toList());
        yearCB.setValue(yearCB.getItems().get(0));
        GridPane form = new GridPane();
        form.add(new Label("Select a year:"), 0, 0);
        form.add(yearCB, 1, 0);
        form.add(new Label("Boy or girl?"), 0, 1);
        form.add(genderCB, 1, 1);
        form.add(new Label("Enter a name:"), 0, 2);
        form.add(nameTF, 1, 2);
        form.add(findBTN, 1, 3);
        form.setVgap(5);
        form.setHgap(5);
        form.setPadding(new Insets(5));
        form.setAlignment(Pos.BASELINE_CENTER);

        root.setCenter(form);
        root.setBottom(statusLabel);
        return root;
    }

    private void loadNames() {
        try {
            for (int year = 2001; year <= 2010; year++) {
                boysNames[year - 2001] = new HashMap<>();
                girlsNames[year - 2001] = new HashMap<>();
                String address = String.format("http://liveexample.pearsoncmg.com/data/babynamesranking%d.txt", year);
                URL url = new URL(address);
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    String[] record = Arrays.stream(scanner.nextLine().split("\t")).
                            map(String::trim)
                            .toArray(String[]::new);
                    int rank = Integer.parseInt(record[0]);
                    boysNames[year - 2001].put(record[1], rank);
                    girlsNames[year - 2001].put(record[3], rank);
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void findHandler(ActionEvent e) {
        statusLabel.setText("");
        int year = yearCB.getValue();
        String gender = genderCB.getValue().equals("Male") ? "Boy" : "Girl";
        String name = nameTF.getText();
        if (name.isEmpty()) {
            return;
        }
        Map<String, Integer> map = (gender.equals("Boy") ? boysNames : girlsNames)[year - 2001];
        Integer rank = map.get(name);
        if (rank == null) {
            statusLabel.setText("No data found");
        } else {
            statusLabel.setText(String.format("%s name %s is ranked #%d in year %d", gender, name, rank, year));
        }

    }
}
