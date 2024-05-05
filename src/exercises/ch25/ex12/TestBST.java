package exercises.ch25.ex12;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestBST {
    String captureOutput(Runnable action) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        action.run();
        System.out.flush();
        System.setOut(old);
        return baos.toString().trim();
    }

    @Test
    void testNoArgsConstructor() {
        BST<Integer> tree = new BST<>();

        assertEquals(0, tree.getSize());
        assertNull(tree.getRoot());
    }

    @Test
    void testArrayConstructor() {
        Integer[] array = new Integer[]{1, 2, 3};
        BST<Integer> tree = new BST<>(array);

        assertEquals(array.length, tree.getSize());
        assertNotNull(tree.getRoot());
    }

    @Test
    void testClone() {
        Integer[] array = new Integer[]{1, 2, 3};
        BST<Integer> tree = new BST<>(array);
        BST<Integer> treeClone = (BST<Integer>) tree.clone();

        assertEquals(tree, treeClone);
        assertNotEquals(tree.getRoot(), treeClone.getRoot());
    }

    @Test
    void testEquals() {
        Integer[] array = new Integer[]{1, 2, 3};
        BST<Integer> tree1 = new BST<>(array);
        BST<Integer> tree2 = new BST<>(array);
        BST<Integer> tree3 = new BST<>(new Integer[]{1, 2, 3, 4, 5});

        assertEquals(tree1, tree2);
        assertNotEquals(tree1, tree3);
    }

    @ParameterizedTest
    @CsvSource({
            "0, false",
            "1, true",
            "4, false",
            "3, true"
    })
    void testSearch(int value, boolean result) {
        Integer[] array = new Integer[]{1, 2, 3};
        BST<Integer> tree = new BST<>(array);

        assertEquals(tree.search(value), result);
    }

    @Test
    void testInsertRoot() {
        BST<Integer> tree = new BST<>();
        tree.insert(60);
        String output = captureOutput(tree::inorder);

        assertEquals("60", output);
    }

    @Test
    void testInsertLeft() {
        BST<Integer> tree = new BST<>();
        tree.insert(60);
        tree.insert(55);
        tree.insert(45);
        String output = captureOutput(tree::inorder);

        assertEquals("45 55 60", output);
    }

    @Test
    void testInsertRight() {
        BST<Integer> tree = new BST<>();
        tree.insert(60);
        tree.insert(55);
        tree.insert(45);
        tree.insert(100);
        tree.insert(107);
        String output = captureOutput(tree::inorder);

        assertEquals("45 55 60 100 107", output);
    }


    @Test
    void testInorder() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        String output = captureOutput(tree::inorder);

        assertEquals("45 55 57 59 60 67 100 101 107", output);
    }

    @Test
    void testPreorder() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        String output = captureOutput(tree::preorder);

        assertEquals("60 55 45 57 59 100 67 107 101", output);
    }

    @Test
    void testPostorder() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        String output = captureOutput(tree::postorder);

        assertEquals("45 59 57 55 67 101 107 100 60", output);
    }

    @Test
    void testPath() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);

        ArrayList<TreeNode<Integer>> path = tree.path(59);
        String result = path.stream()
                .map(node -> Integer.toString(node.getElement()))
                .collect(Collectors.joining(" "));
        assertEquals(result, "60 55 57 59");
    }

    @Test
    void testDeleteRoot() {
        BST<Integer> tree = new BST<>();
        tree.add(100);
        tree.delete(100);

        assertEquals(0, tree.getSize());
        assertNull(tree.getRoot());
    }

    @ParameterizedTest
    @CsvSource({
            "60, 55 45 100 67",
            "67, 60 55 45 100",
            "55, 60 45 100 67"
    })
    void testDelete(int value, String result) {
        Integer[] array = new Integer[]{60, 55, 45, 100, 67};
        BST<Integer> tree = new BST<>(array);
        tree.delete(value);
        String output = captureOutput(tree::preorder);

        assertEquals(result, output);
    }

    @Test
    void testClear() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        tree.clear();

        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }


    @Test
    void testInorderIterator() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        String iterationResult = tree.stream()
                .map(x -> Integer.toString(x))
                .collect(Collectors.joining(" "));

        assertEquals("45 55 57 59 60 67 100 101 107", iterationResult);
    }

    @Test
    void testPreorderIterator() {
        Integer[] array = new Integer[]{60, 55, 45, 57, 59, 100, 67, 107, 101};
        BST<Integer> tree = new BST<>(array);
        ArrayList<Integer> values = new ArrayList<>();
        Iterator<Integer> iterator = tree.preorderIterator();
        while (iterator.hasNext()) {
            values.add(iterator.next());
        }
        String iterationResult = values.stream()
                .map(x -> Integer.toString(x))
                .collect(Collectors.joining(" "));

        assertEquals("60 55 45 57 59 100 67 107 101", iterationResult);
    }

}
