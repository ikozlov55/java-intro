package exercises.ch25.ex17;

import exercises.ch25.ex16.Tree;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.*;

public class HuffmanTreeView extends Pane {
    private Tree tree = null;
    private String text = "";
    private HashMap<Character, String> bits = new HashMap<>();
    private final double vGap = 50;

    public void displayTree(String text) {
        this.text = text;
        Map<Character, Integer> frequencyMap = getCharacterFrequency(text);
        tree = getHuffmanTree(frequencyMap);
        assignCodes(tree.getRoot());
        getChildren().clear();
        draw(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
    }

    public String getEncodedBits() {
        if (tree == null) return "";
        StringBuilder builder = new StringBuilder();
        for (char c : this.text.toCharArray()) {
            builder.append(bits.get(c));
        }
        return builder.toString();
    }

    public String decodeBits(String bits) {
        StringBuilder builder = new StringBuilder();
        Tree.Node current = tree.getRoot();
        for (char c : bits.toCharArray()) {
            if (c == '0') {
                current = current.getLeft();
            } else if (c == '1') {
                current = current.getRight();
            }
            if (current.getLeft() == null && current.getRight() == null) {
                builder.append(current.getElement());
                current = tree.getRoot();
            }
        }
        return builder.toString();
    }

    public void draw(Tree.Node root, double x, double y, double hGap) {
        if (root.getLeft() != null) {
            Line line = new Line(x - hGap, y + vGap, x, y);
            Text label = new Text((2 * x - hGap) / 2 - 5, (2 * y + vGap) / 2 - 5, "0");
            getChildren().addAll(line, label);
            draw(root.getLeft(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight() != null) {
            Line line = new Line(x + hGap, y + vGap, x, y);
            Text label = new Text((2 * x + hGap) / 2 + 5, (2 * y + vGap) / 2 - 5, "1");
            getChildren().addAll(line, label);
            draw(root.getRight(), x + hGap, y + vGap, hGap / 2);
        }

        double radius = 15;
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        String s = String.valueOf(root.getWeight());
        double length = s.length() * 7;
        Text elementLabel = new Text(x - 2, y + radius + 12, String.valueOf(root.getElement()));
        Text weightLabel = new Text(x - length / 2, y + 4, s);
        getChildren().addAll(circle, elementLabel, weightLabel);
    }

    private Map<Character, Integer> getCharacterFrequency(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
        }
        return map;
    }

    private Tree getHuffmanTree(Map<Character, Integer> frequencyMap) {
        List<Tree> list = frequencyMap.entrySet().stream()
                .map(e -> new Tree(e.getValue(), e.getKey()))
                .toList();
        PriorityQueue<Tree> forest = new PriorityQueue<>(Comparator.reverseOrder());
        forest.addAll(list);
        while (forest.size() > 1) {
            Tree t1 = forest.poll();
            Tree t2 = forest.poll();
            Tree t3 = new Tree(t1, t2);
            forest.add(t3);
        }
        return forest.poll();
    }

    private void assignCodes(Tree.Node root) {
        Deque<Tree.Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Tree.Node n = stack.pop();
            if (n.getLeft() != null) {
                n.getLeft().setCode(n.getLeft().getCode() + n.getCode() + "0");
                stack.push(n.getLeft());
                n.getRight().setCode(n.getRight().getCode() + n.getCode() + "1");
                stack.push(n.getRight());
            } else {
                bits.put(n.getElement(), n.getCode());
            }
        }
    }
}
