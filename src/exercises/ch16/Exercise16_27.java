package exercises.ch16;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Exercise16_27 extends Application {
    public static void run() {
        Application.launch();
    }

    private final String[] flagTitles = {"Canada", "China", "Denmark",
            "France", "Germany", "India", "Norway", "United Kingdom",
            "United States of America"};

    // Declare an ImageView array for the national flags of 9 countries
    private final ImageView[] flagImage = {
            new ImageView("resources/image/ca.gif"),
            new ImageView("resources/image/china.gif"),
            new ImageView("resources/image/denmark.gif"),
            new ImageView("resources/image/fr.gif"),
            new ImageView("resources/image/germany.gif"),
            new ImageView("resources/image/india.gif"),
            new ImageView("resources/image/norway.gif"),
            new ImageView("resources/image/uk.gif"),
            new ImageView("resources/image/us.gif")
    };
    private final File textDir = new File("src/exercises/ch16/text");

    // Declare and create a description pane
    private final DescriptionPane descriptionPane = new DescriptionPane();

    // Create a combo box for selecting countries
    private final ComboBox<String> cbo = new ComboBox<>(); // flagTitles;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Add combo box and description pane to the border pane
        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);

        ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);

        // Set the first country (Canada) for display
        cbo.setValue(flagTitles[0]);
        setDisplay(0);

        // Display the selected country
        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * Set display information on the description pane
     */
    public void setDisplay(int index) {
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        Path path = Path.of(textDir.getAbsolutePath(), String.format("description%d.txt", index));
        try {
            String description = String.join("\n", Files.readAllLines(path, StandardCharsets.UTF_8));
            descriptionPane.setDescription(description);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class DescriptionPane extends BorderPane {
    private final Label lblImageTitle = new Label();
    private final TextArea taDescription = new TextArea();

    public DescriptionPane() {
        // Center the icon and text and place the text under the icon
        lblImageTitle.setContentDisplay(ContentDisplay.TOP);
        lblImageTitle.setPrefSize(200, 100);

        // Set the font in the label and the text field
        lblImageTitle.setFont(new Font("SansSerif", 16));
        taDescription.setFont(new Font("Serif", 14));
        taDescription.setWrapText(true);
        taDescription.setEditable(false);

        // Create a scroll pane to hold the text area
        ScrollPane scrollPane = new ScrollPane(taDescription);

        // Place label and scroll pane in the border pane
        setLeft(lblImageTitle);
        setCenter(scrollPane);
        setPadding(new Insets(5, 5, 5, 5));
    }

    public void setTitle(String title) {
        lblImageTitle.setText(title);
    }

    public void setImageView(ImageView icon) {
        lblImageTitle.setGraphic(icon);
    }

    public void setDescription(String text) {
        taDescription.setText(text);
    }
}