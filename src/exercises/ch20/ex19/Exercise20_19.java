package exercises.ch20.ex19;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Exercise20_19 {

    public static void run() {
        int[] deck = new int[52];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i % 13 + 1;
        }
        List<List<Integer>> combinations = pickCombinations(deck);
        int picks = combinations.size();
        System.out.printf("What is the number of all possible picks of four cards from 52 cards?: %d\n", picks);
        int solutions = 0;
        for (List<Integer> c : combinations) {
            if (hasSolution(c)) solutions++;
        }
        System.out.printf("Among all possible picks, how many of them have 24-point solutions?: %d\n", solutions);
        System.out.printf("What is the success ratio?: %.2f\n", (double) solutions / picks);
    }

    private static List<List<Integer>> pickCombinations(int[] deck) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < deck.length - 3; i++) {
            for (int j = i + 1; j < deck.length - 2; j++) {
                for (int k = j + 1; k < deck.length - 1; k++) {
                    for (int l = k + 1; l < deck.length; l++) {
                        List<Integer> pick = List.of(deck[i], deck[j], deck[k], deck[l]);
                        result.add(pick);
                    }
                }
            }
        }

        return result;
    }

    private static List<List<Integer>> permutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        permutationsHelper(list, new ArrayList<>(), result);
        return result;
    }

    private static void permutationsHelper(List<Integer> list, List<Integer> buffer,
                                           List<List<Integer>> permutations) {
        if (list.isEmpty()) {
            permutations.add(buffer);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> listCopy = new ArrayList<>(list);
            List<Integer> bufferCopy = new ArrayList<>(buffer);
            bufferCopy.add(listCopy.remove(i));
            permutationsHelper(listCopy, bufferCopy, permutations);
        }
    }

    private static boolean hasSolution(List<Integer> pick) {
        List<List<Integer>> permutations = permutations(pick);
        for (List<Integer> p : permutations) {
            if (hasSolutionHelper(List.of(), p)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSolutionHelper(List<String> tokens, List<Integer> numbers) {
        if (numbers.isEmpty()) {
            if (evaluateExpression(tokens) == 24) {
                //System.out.println(String.join("", tokens));
                return true;
            }
            for (int i = 0; i < tokens.size() - 3; i += 2) {
                for (int j = 4; j < tokens.size() + 2; j += 2) {
                    ArrayList<String> nextTokens = new ArrayList<>(tokens);
                    nextTokens.add(i, "(");
                    nextTokens.add(j, ")");
                    if (evaluateExpression(nextTokens) == 24) {
                        //System.out.println(String.join("", nextTokens));
                        return true;
                    }
                }
            }
            return false;
        }
        for (char op : List.of('+', '-', '*', '/')) {
            List<Integer> nextNumbers = new ArrayList<>(numbers);
            List<String> nextTokens = new ArrayList<>(tokens);
            nextTokens.add(Integer.toString(nextNumbers.remove(0)));
            if (!nextNumbers.isEmpty()) {
                nextTokens.add(Character.toString(op));
            }
            if (hasSolutionHelper(nextTokens, nextNumbers)) {
                return true;
            }
        }
        return false;
    }

    private static double evaluateExpression(List<String> tokens) {
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

    private static boolean isNumber(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static void process(char op, Stack<Double> operands) {
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
}
