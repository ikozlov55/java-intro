package exercises.ch20.ex13;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class Exercise20_13 extends Application {
    private final Label statusLabel = new Label("");
    private final Button shuffleButton = new Button("Shuffle");
    private final TextField expressionField = new TextField();
    private final Button verifyButton = new Button("Verify");
    private final HBox cardsBox = new HBox();
    private final ArrayList<Integer> numbers = new ArrayList<>();
    private final List<Character> allowedOperators = List.of('(', ')', '+', '-', '*', '/');

    public static void run() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        shuffleButton.setOnAction(e -> shuffle());
        verifyButton.setOnAction(e -> verify());
        shuffle();
        stage.setScene(new Scene(makeRootPane(), 500, 250));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
    }

    private Pane makeRootPane() {
        statusLabel.setMaxWidth(200);
        statusLabel.setWrapText(true);
        BorderPane root = new BorderPane();
        HBox topBox = new HBox(5, statusLabel, shuffleButton);
        topBox.setAlignment(Pos.BASELINE_RIGHT);
        topBox.setPadding(new Insets(5));

        HBox bottomBox = new HBox(5, new Label("Enter an expression:"), expressionField, verifyButton);
        bottomBox.setPadding(new Insets(5));
        bottomBox.setAlignment(Pos.BASELINE_CENTER);

        cardsBox.setSpacing(5);
        cardsBox.setPadding(new Insets(5));
        cardsBox.setAlignment(Pos.CENTER);

        root.setTop(topBox);
        root.setCenter(cardsBox);
        root.setBottom(bottomBox);
        return root;
    }

    private void shuffle() {
        statusLabel.setText("");
        cardsBox.getChildren().clear();
        numbers.clear();
        Random generator = new Random();
        int cardsTotal = 52;
        int cardsNumber = 4;
        ArrayList<Integer> cards = new ArrayList<>();
        while (cards.size() < cardsNumber) {
            int card = generator.nextInt(1, cardsTotal + 1);
            if (!cards.contains(card)) {
                numbers.add(card % 13 == 0 ? 13 : card % 13);
                cards.add(card);
            }
        }
        for (int card : cards) {
            String path = String.format("resources/image/card/%d.png", card);
            ImageView image = new ImageView(new Image(path));
            image.setPreserveRatio(true);
            image.setSmooth(true);
            image.setFitHeight(200);
            image.setFitWidth(110);
            cardsBox.getChildren().add(image);
        }
    }

    private void verify() {
        String expression = expressionField.getText();
        if (expression.isEmpty()) return;

        List<String> tokens = tokenize(expression);
        if (tokens == null) {
            statusLabel.setText("Invalid expression!");
            return;
        }

        if (!expressionNumbersMatch(tokens)) {
            statusLabel.setText("The numbers in the expression don't match the numbers in the set");
            return;
        }
        System.out.println(tokens);
        int result = evaluateExpression(tokens);
        System.out.println(result);
        statusLabel.setText(result == 24 ? "Correct" : "Incorrect result");
    }

    private List<String> tokenize(String expression) {
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

    private boolean expressionNumbersMatch(List<String> tokens) {
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        List<Integer> sortedOperands = tokens.stream()
                .filter(this::isNumber)
                .map(Integer::parseInt)
                .sorted().toList();
        return sortedNumbers.equals(sortedOperands);
    }

    private boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private int evaluateExpression(List<String> tokens) {
        Stack<Character> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                operands.push(Integer.parseInt(token));
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

    private void process(char op, Stack<Integer> operands) {
        int n2 = operands.pop();
        int n1 = operands.pop();
        int result;
        switch (op) {
            case '+' -> result = n1 + n2;
            case '-' -> result = n1 - n2;
            case '*' -> result = n1 * n2;
            case '/' -> result = n1 / n2;
            default -> {
                return;
            }
        }
        ;
        operands.push(result);
    }

}

