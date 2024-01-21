package exercises.ch22.ex21;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class SudokuCell extends TextField {
    private int value;

    public SudokuCell(int value) {
        this.value = value;
        setPadding(new Insets(10, 8, 10, 8));
        setPrefColumnCount(2);
        setAlignment(Pos.CENTER);
        textProperty().addListener(this::textChangeHandler);
        setFont(Font.font("", FontWeight.SEMI_BOLD, 15));
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value != 0) {
            setText(Integer.toString(value));
        } else {
            setText("");
        }
    }

    private void textChangeHandler(ObservableValue<? extends String> ov, String oldText, String newText) {
        try {
            int newValue = Integer.parseInt(newText);
            if (newValue >= 1 && newValue <= 9) {
                setValue(newValue);
            } else {
                setValue(value);
            }
        } catch (NumberFormatException ignored) {
            setValue(value);
        }
    }
}