package exercises.ch20.ex15;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Exercise20_15 extends Application {
    private final Label statusLabel = new Label("");
    private final Button shuffleButton = new Button("Shuffle");
    private final Button verifyButton = new Button("Verify");
    private final Button findSolutionButton = new Button("Find Solution");
    private final TextField expressionField = new TextField();
    private final TextField solutionField = new TextField();

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
        findSolutionButton.setOnAction(e -> solutionField.setText(getSolution().orElse("No solution")));
        shuffle();
        stage.setScene(new Scene(makeRootPane()));
        stage.setTitle(getClass().getSimpleName());
        stage.setResizable(false);
        stage.show();
    }

    private Pane makeRootPane() {
        BorderPane root = new BorderPane();
        HBox topControls = new HBox(5, findSolutionButton, solutionField, shuffleButton);
        topControls.setAlignment(Pos.BASELINE_RIGHT);
        topControls.setPadding(new Insets(5));

        HBox bottomControls = new HBox(5, new Label("Enter an expression:"), expressionField, verifyButton);
        bottomControls.setPadding(new Insets(5));
        bottomControls.setAlignment(Pos.BASELINE_CENTER);

        cardsBox.setSpacing(5);
        cardsBox.setPadding(new Insets(5));
        cardsBox.setAlignment(Pos.CENTER);

        VBox bottomBox = new VBox(5, bottomControls, statusLabel);
        bottomBox.setPadding(new Insets(5));

        root.setTop(topControls);
        root.setCenter(cardsBox);
        root.setBottom(bottomBox);
        return root;
    }

    private void shuffle() {
        statusLabel.setText("");
        solutionField.clear();
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

    private Optional<String> getSolution() {
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
            if (tokens == null || !expressionNumbersMatch(tokens)) return Optional.empty();
            if (evaluateExpression(tokens) == 24) {
                return Optional.of(expression);
            }
            for (int i = 0; i < tokens.size() - 3; i+=2) {
                for (int j = 4; j < tokens.size()+2; j+=2) {
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
        double result = evaluateExpression(tokens);
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

