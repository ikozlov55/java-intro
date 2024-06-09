package exercises.ch25.ex16;

import java.io.*;
import java.util.*;

public class Exercise25_16 {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        File file = new File(scanner.next());

        Map<Character, Integer> frequencyMap = getCharacterFrequency(file);
        Tree tree = getHuffmanTree(frequencyMap);
        assignCodes(tree.root);
        System.out.println(tree);
    }

    private static Map<Character, Integer> getCharacterFrequency(File file) {
        HashMap<Character, Integer> map = new HashMap<>();
        try (FileInputStream input = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            int r;
            while ((r = reader.read()) > 0) {
                char c = (char) r;
                int count = map.getOrDefault(c, 0);
                map.put(c, count + 1);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

    private static Tree getHuffmanTree(Map<Character, Integer> frequencyMap) {
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

    private static void assignCodes(Tree.Node root) {
        Deque<Tree.Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Tree.Node n = stack.pop();
            if (n.left != null) {
                n.left.code += n.code + "0";
                stack.push(n.left);
                n.right.code += n.code + "1";
                stack.push(n.right);
            }
        }
    }

}
