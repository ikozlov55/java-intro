package exercises.ch20.ex17;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.*;

public class Exercise20_17 extends Application {
    private final TextField solutionTF = new TextField();
    private final Button solveBTN = new Button("Solve");
    private final TextField number1TF = new TextField();
    private final TextField number2TF = new TextField();
    private final TextField number3TF = new TextField();
    private final TextField number4TF = new TextField();

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox controlsBox = new HBox(5, solutionTF, solveBTN);
        controlsBox.setPadding(new Insets(5));
        controlsBox.setAlignment(Pos.BASELINE_CENTER);
        solveBTN.setOnAction(this::solveBTNHandler);

        HBox numbersBox = new HBox(5, number1TF, number2TF, number3TF, number4TF);
        numbersBox.setPadding(new Insets(5));
        numbersBox.setAlignment(Pos.BASELINE_CENTER);
        for (TextField tf : List.of(number1TF, number2TF, number3TF, number4TF)) {
            tf.setPrefSize(60, 50);
            tf.setAlignment(Pos.CENTER);
            tf.setFont(Font.font(22));
            tf.textProperty().addListener((ov, oldValue, newValue) -> {
                if (newValue.isEmpty()) return;
                try {
                    int value = Integer.parseInt(newValue);
                    if (value < 1 || value > 13) {
                        Platform.runLater(tf::clear);
                    }
                } catch (NumberFormatException ex) {
                    Platform.runLater(tf::clear);
                }
            });
        }

        BorderPane root = new BorderPane();
        root.setTop(controlsBox);
        root.setCenter(numbersBox);
        stage.setScene(new Scene(root));
        stage.setTitle(getClass().getSimpleName());
        stage.show();
    }

    private void solveBTNHandler(ActionEvent e) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (TextField tf : List.of(number1TF, number2TF, number3TF, number4TF)) {
            try {
                int value = Integer.parseInt(tf.getText());
                numbers.add(value);
            } catch (NumberFormatException ex) {
                return;
            }
        }
        Optional<String> solution = getSolution(numbers);
        solutionTF.setText(solution.orElse("No solution"));
    }

    private Optional<String> getSolution(ArrayList<Integer> numbers) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
        getPermutations(numbers, new ArrayList<>(), permutations);
        for (ArrayList<Integer> p : permutations) {
            Optional<String> solution = getSolutionHelper("", p);
            if (solution.isPresent()) {
                return solution;
            }
        }
        return Optional.empty();
    }

    private Optional<String> getSolutionHelper(String expression, ArrayList<Integer> numbers) {
        if (numbers.isEmpty()) {
            System.out.println(expression);
            List<String> tokens = tokenize(expression);
            if (tokens == null) return Optional.empty();
            if (evaluateExpression(tokens) == 24) {
                return Optional.of(expression);
            }
            for (int i = 0; i < tokens.size() - 3; i += 2) {
                for (int j = 4; j < tokens.size() + 2; j += 2) {
                    ArrayList<String> nextTokens = new ArrayList<>(tokens);
                    nextTokens.add(i, "(");
                    nextTokens.add(j, ")");

                    System.out.println(String.join("", nextTokens));
                    if (evaluateExpression(nextTokens) == 24) {
                        return Optional.of(String.join("", nextTokens));
                    }
                }
            }
            return Optional.empty();
        }
        for (char op : List.of('+', '-', '*', '/')) {
            ArrayList<Integer> nextNumbers = new ArrayList<>(numbers);
            String nextExpression = expression;
            nextExpression += Integer.toString(nextNumbers.remove(0));
            if (!nextNumbers.isEmpty()) {
                nextExpression += Character.toString(op);
            }
            Optional<String> result = getSolutionHelper(nextExpression, nextNumbers);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    private List<String> tokenize(String expression) {
        List<Character> allowedOperators = List.of('(', ')', '+', '-', '*', '/');
        StringBuilder builder = new StringBuilder();
        Stack<Character> parentheses = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (Character.isWhitespace(c)) continue;
            if (!Character.isDigit(c) && !allowedOperators.contains(c)) {
                return null;
            }
            if (allowedOperators.contains(c)) {
                builder.append(' ');
                builder.append(c);
                builder.append(' ');
            } else {
                builder.append(c);
            }
            if (c == '(') {
                parentheses.push(c);
            } else if (c == ')') {
                if (parentheses.isEmpty()) {
                    return null;
                }
                parentheses.pop();
            }
        }
        if (!parentheses.isEmpty()) {
            return null;
        }
        return Arrays.stream(builder.toString().split("\\s+")).filter(x -> !x.isEmpty()).toList();
    }

    private double evaluateExpression(List<String> tokens) {
        Stack<Character> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                operands.push(Double.parseDouble(token));
                continue;
            }
            char op = token.charAt(0);
            switch (op) {
                case '+', '-' -> {
                    while (!operators.isEmpty() && operators.peek() != '(' && operators.peek() != ')') {
                        char nextOp = operators.pop();
                        process(nextOp, operands);
                    }
                    operators.push(op);
                }
                case '*', '/' -> {
                    while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                        char nextOp = operators.pop();
                        process(nextOp, operands);
                    }
                    operators.push(op);
                }
                case '(' -> operators.push(op);
                case ')' -> {
                    while (true) {
                        char nextOp = operators.pop();
                        if (nextOp == '(') break;
                        process(nextOp, operands);
                    }
                }
            }
        }
        while (!operators.isEmpty()) {
            char nextOp = operators.pop();
            process(nextOp, operands);
        }
        return operands.pop();
    }

    private boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private void process(char op, Stack<Double> operands) {
        double n2 = operands.pop();
        double n1 = operands.pop();
        double result;
        switch (op) {
            case '+' -> result = n1 + n2;
            case '-' -> result = n1 - n2;
            case '*' -> result = n1 * n2;
            case '/' -> result = n1 / n2;
            default -> {
                return;
            }
        }
        operands.push(result);
    }

    private void getPermutations(
            ArrayList<Integer> numbers,
            ArrayList<Integer> currentNumbers,
            ArrayList<ArrayList<Integer>> permutations) {
        if (numbers.isEmpty()) {
            permutations.add(currentNumbers);
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                ArrayList<Integer> numbersCopy = new ArrayList<>(numbers);
                ArrayList<Integer> currentNumbersCopy = new ArrayList<>(currentNumbers);
                currentNumbersCopy.add(numbersCopy.remove(i));
                getPermutations(numbersCopy, currentNumbersCopy, permutations);
            }
        }
    }
}
