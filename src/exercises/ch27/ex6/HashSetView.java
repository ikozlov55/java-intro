package exercises.ch27.ex6;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

class HashSetView<T> extends BorderPane {
    private static final int DEFAULT_INITIAL_CAPACITY = 4;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final double DEFAULT_MAX_LOAD_FACTOR = 0.75f;
    private int capacity;
    private double loadFactorThreshold;
    private int size = 0;
    Node<T>[] table;

    public HashSetView() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public HashSetView(int capacity, double loadFactorThreshold) {
        this.capacity = capacity;
        this.loadFactorThreshold = loadFactorThreshold;
        this.table = (Node<T>[]) new Node[capacity];
        draw();
    }

    public double getLoadFactorThreshold() {
        return loadFactorThreshold;
    }

    public void setLoadFactorThreshold(double loadFactorThreshold) {
        if (loadFactorThreshold >= 1) {
            String message = "The load factor threshold mus be less than 1.0";
            Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
            alert.show();
            return;
        }
        this.loadFactorThreshold = loadFactorThreshold;
        if (size >= capacity * this.loadFactorThreshold) {
            rehash();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < this.size + 1) {
            String message = "The capacity must be at least one more than the current size";
            Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
            alert.show();
            return;
        }
        Set<T> values = valuesSet();
        this.capacity = capacity;
        table = (Node<T>[]) new Node[capacity];
        size = 0;
        for (T value : values) {
            insert(value);
        }
        draw();
    }


    public void search(T value) {
        String message;
        if (get(value) != null) {
            message = String.format("%s is in the hash set", value);
        } else {
            message = String.format("%s is not in the hash set", value);
        }
        Alert alert = new Alert(Alert.AlertType.NONE, message, ButtonType.OK);
        alert.show();
    }

    public void insert(T value) {
        int index = hash(value.hashCode());
        while (true) {
            if (table[index] == null || table[index].getValue() == null) {
                if (size >= capacity * loadFactorThreshold) {
                    if (capacity > MAXIMUM_CAPACITY) {
                        throw new RuntimeException("Exceeding maximum capacity");
                    }
                    rehash();
                }
                table[index] = new Node<>(value);
                size++;
                draw();
                return;
            }
            if (table[index] != null && table[index].getValue().equals(value)) {
                return;
            }
            index = (index + 1) % capacity;
        }
    }

    public void remove(T value) {
        int index = hash(value.hashCode());
        while (true) {
            if (table[index] == null || table[index].getValue() == null) {
                return;
            }
            if (table[index] != null && table[index].getValue().equals(value)) {
                table[index] = new Node<>(null);
                size--;
                draw();
                return;
            }
            index = (index + 1) % capacity;
        }
    }

    public void removeAll() {
        table = (Node<T>[]) new Node[capacity];
        size = 0;
        draw();
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private void rehash() {
        Set<T> values = valuesSet();
        capacity <<= 1;
        table = (Node<T>[]) new Node[capacity];
        size = 0;
        for (T value : values) {
            insert(value);
        }
    }

    private T get(T value) {
        int index = hash(value.hashCode());
        while (true) {
            if (table[index] == null || table[index].getValue() == null) {
                return null;
            }
            if (table[index] != null && table[index].getValue().equals(value)) {
                return value;
            }
            index = (index + 1) % capacity;
        }
    }

    private Set<T> valuesSet() {
        Set<T> values = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].getValue() != null) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

    private void draw() {
        setStyle("-fx-border-color: gray");
        String status = String.format(
                Locale.US,
                "Current table size: %d. Number of keys: %d. Current load: %.2f. Load factor threshold: %.2f",
                capacity, size, (double) size / capacity, loadFactorThreshold);
        Label statusLabel = new Label(status);
        statusLabel.setPadding(new Insets(5));
        setTop(statusLabel);

        VBox vBox = new VBox();
        for (int i = 0; i < table.length; i++) {
            Node<T> node = table[i];

            Label indexLabel = new Label(String.format("[%d]" + (i < 10 ? "  " : ""), i));
            indexLabel.setFont(Font.font(15));
            indexLabel.setAlignment(Pos.BASELINE_CENTER);
            String value;
            if (node == null) {
                value = "";
            } else if (node.getValue() == null) {
                value = "X";
            } else {
                value = node.getValue().toString();
            }
            Label valueLabel = new Label(value);
            valueLabel.setFont(Font.font(15));
            valueLabel.setAlignment(Pos.BASELINE_CENTER);
            valueLabel.setPrefSize(40, 20);
            if (i == table.length - 1) {
                valueLabel.setStyle("-fx-border-color: black; -fx-border-width: 1 1 1 1;");
            } else {
                valueLabel.setStyle("-fx-border-color: black; -fx-border-width: 1 1 0 1;");
            }
            HBox hBox = new HBox(10, indexLabel, valueLabel);
            vBox.getChildren().add(hBox);
        }
        vBox.setPadding(new Insets(5));
        setCenter(vBox);
    }

    static class Node<T> {
        T value;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}

