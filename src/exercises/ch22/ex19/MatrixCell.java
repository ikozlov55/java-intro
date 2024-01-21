package exercises.ch22.ex19;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

class MatrixCell extends TextField {
    private int value;

    public MatrixCell(int value) {
        setValue(value);
        setPadding(new Insets(5));
        setPrefColumnCount(2);
        setAlignment(Pos.CENTER);
        textProperty().addListener(this::textChangeHandler);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        setText(Integer.toString(value));
    }

    private void textChangeHandler(ObservableValue<? extends String> ov, String oldText, String newText) {
        try {
            int newValue = Integer.parseInt(newText);
            if (newValue == 0 || newValue == 1) {
                setValue(newValue);
            } else {
                setValue(value);
            }
        } catch (NumberFormatException ignored) {
            setValue(value);
        }
    }
}
