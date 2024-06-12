package exercises.ch25.ex18;

import exercises.ch17.ex17.BitOutputStream;
import exercises.ch25.ex16.Tree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Exercise25_18 {


    public static void run(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Exercise25_18 sourcefile targetfile");
            System.exit(1);
        }

        File outputFile = new File(args[1]);
        String text = "";
        try {
            text = Files.readString(Path.of(args[0]));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Tree tree = getHuffmanTree(getCharacterFrequency(text));
        Map<Character, String> codes = assignCodes(tree.getRoot());

        try (FileOutputStream fos = new FileOutputStream(outputFile);
             ObjectOutputStream output = new ObjectOutputStream(fos)
        ) {
            output.writeObject(codes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try (BitOutputStream output = new BitOutputStream(outputFile)) {
            output.writeBit(getEncodedBits(text, codes));
        }
    }

    private static Map<Character, Integer> getCharacterFrequency(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            int count = map.getOrDefault(c, 0);
            map.put(c, count + 1);
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

    private static Map<Character, String> assignCodes(Tree.Node root) {
        Map<Character, String> codes = new HashMap<>();
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
                codes.put(n.getElement(), n.getCode());
            }
        }
        return codes;
    }

    private static String getEncodedBits(String text, Map<Character, String> codes) {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            builder.append(codes.get(c));
        }
        return builder.toString();
    }
}
