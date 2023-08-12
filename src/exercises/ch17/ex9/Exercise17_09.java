package exercises.ch17.ex9;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Optional;

public class Exercise17_09 extends Application {
    private final TextField nameTF = new TextField("John Smith");
    private final TextField streetTF = new TextField("100 Main Street");
    private final TextField cityTF = new TextField("Savannah");
    private final TextField stateTF = new TextField("GA");
    private final TextField zipTF = new TextField("31412");
    private final Button addBTN = new Button("Add");
    private final Button firstBTN = new Button("First");
    private final Button nextBTN = new Button("Next");
    private final Button previousBTN = new Button("Previous");
    private final Button lastBTN = new Button("Last");
    private final Button updateBTN = new Button("Update");
    private RandomAccessFile file;
    private int index = 0;
    private ArrayList<Address> addresses;

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = makeRootPane();
        file = new RandomAccessFile("src/exercises/ch17/data/Exercise17_09.dat", "rw");
        addresses = readAllFromFile();

        updateState();
        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private BorderPane makeRootPane() {
        GridPane form = new GridPane();
        form.setPadding(new Insets(5));
        form.setHgap(5);
        form.setVgap(5);
        stateTF.setPrefColumnCount(2);
        zipTF.setPrefColumnCount(3);
        form.add(new Label("Name"), 0, 0);
        form.add(nameTF, 1, 0, 6, 1);
        form.add(new Label("Street"), 0, 1);
        form.add(streetTF, 1, 1, 6, 1);
        form.add(new Label("City"), 0, 2);
        form.add(cityTF, 1, 2);
        form.add(new Label("State"), 2, 2);
        form.add(stateTF, 3, 2);
        form.add(new Label("Zip"), 4, 2);
        form.add(zipTF, 5, 2);

        HBox controls = new HBox(addBTN, firstBTN, nextBTN, previousBTN, lastBTN, updateBTN);
        controls.setPadding(new Insets(5));
        controls.setAlignment(Pos.BASELINE_CENTER);
        controls.setSpacing(5);

        addBTN.setOnAction(e -> getFormAddress().ifPresent(address -> {
            addresses.add(address);
            writeToFile(address, addresses.size() - 1);
            index = addresses.size() - 1;
            updateState();
        }));
        firstBTN.setOnAction(e -> {
            index = 0;
            updateState();
        });
        nextBTN.setOnAction(e -> {
            index++;
            updateState();
        });
        previousBTN.setOnAction(e -> {
            index--;
            updateState();
        });
        lastBTN.setOnAction(e -> {
            index = addresses.size() - 1;
            updateState();
        });
        updateBTN.setOnAction(e -> getFormAddress().ifPresent(address -> {
            addresses.set(index, address);
            writeToFile(address, index);
            updateState();
        }));

        BorderPane pane = new BorderPane();
        pane.setCenter(form);
        pane.setBottom(controls);

        return pane;
    }


    private void updateState() {
        previousBTN.setDisable(index == 0);
        firstBTN.setDisable(index == 0);
        nextBTN.setDisable(addresses.isEmpty() || index == addresses.size() - 1);
        lastBTN.setDisable(addresses.isEmpty() || index == addresses.size() - 1);
        updateBTN.setDisable(addresses.isEmpty());
        if (!addresses.isEmpty()) {
            Address address = addresses.get(index);
            nameTF.setText(address.getName().strip());
            streetTF.setText(address.getStreet().strip());
            cityTF.setText(address.getCity().strip());
            stateTF.setText(address.getState().strip());
            zipTF.setText(address.getZip().strip());
        }
    }

    private Optional<Address> getFormAddress() {
        String name = String.format("%-32s", nameTF.getText());
        String street = String.format("%-32s", streetTF.getText());
        String city = String.format("%-20s", cityTF.getText());
        String state = String.format("%-2s", stateTF.getText());
        String zip = String.format("%-5s", zipTF.getText());
        if (name.isEmpty() || name.length() > 32 ||
                street.isEmpty() || street.length() > 32 ||
                city.isEmpty() || city.length() > 20 ||
                state.length() != 2 || zip.length() != 5) {
            System.out.println("Invalid input");
            return Optional.empty();
        }
        return Optional.of(new Address(name, street, city, state, zip));
    }

    private ArrayList<Address> readAllFromFile() {
        ArrayList<Address> addresses = new ArrayList<>();
        try {
            while (file.getFilePointer() < file.length()) {
                byte[] nameBytes = new byte[32];
                byte[] streetBytes = new byte[32];
                byte[] cityBytes = new byte[20];
                byte[] stateBytes = new byte[2];
                byte[] zipBytes = new byte[5];
                file.read(nameBytes);
                file.read(streetBytes);
                file.read(cityBytes);
                file.read(stateBytes);
                file.read(zipBytes);
                addresses.add(new Address(
                        new String(nameBytes),
                        new String(streetBytes),
                        new String(cityBytes),
                        new String(stateBytes),
                        new String(zipBytes)
                ));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return addresses;
    }

    private void writeToFile(Address address, int index) {
        try {
            file.seek(index * 91L);
            file.write(address.getName().getBytes());
            file.write(address.getStreet().getBytes());
            file.write(address.getCity().getBytes());
            file.write(address.getState().getBytes());
            file.write(address.getZip().getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
