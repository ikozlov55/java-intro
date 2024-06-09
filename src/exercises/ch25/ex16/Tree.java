package exercises.ch25.ex16;

import java.util.*;

public class Tree implements Comparable<Tree> {
    Node root;

    public Tree(Tree t1, Tree t2) {
        root = new Node();
        root.left = t1.root;
        root.right = t2.root;
        root.weight = t1.root.weight + t2.root.weight;
    }

    public Tree(int weight, char element) {
        root = new Node(weight, element);
    }

    @Override
    public int compareTo(Tree t) {
        if (root.weight < t.root.weight) // Purposely reverse the order
            return 1;
        else if (root.weight == t.root.weight)
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        String header = String.format("%-12s%-12s%-12s\n", "Character", "Code", "Frequency");
        StringBuilder builder = new StringBuilder(header);
        if (root == null) {
            return builder.toString();
        }

        List<Node> leafs = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (n.right != null) {
                stack.push(n.right);
            }
            if (n.left != null) {
                stack.push(n.left);
            }
            if (n.right == null && n.left == null) {
                leafs.add(n);
            }
        }
        leafs.sort(Comparator.comparingInt(n -> n.weight));
        for (Node n : leafs) {
            String row = String.format("%-12s%-12s%-12d\n", n.element, n.code, n.weight);
            builder.append(row);
        }
        return builder.toString();
    }

    public class Node {
        char element;
        int weight;
        Node left;
        Node right;
        String code = "";

        public Node() {
        }

        public Node(int weight, char element) {
            this.weight = weight;
            this.element = element;
        }

        @Override
        public String toString() {
            return String.format("'%s' [%d]", element, weight);
        }
    }
}
