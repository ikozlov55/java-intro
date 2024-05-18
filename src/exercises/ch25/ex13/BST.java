package exercises.ch25.ex13;

import exercises.ch25.base.TreeNode;

import java.util.*;

public class BST<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected Comparator<E> c;

    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    public BST(Comparator<E> c) {
        this.c = c;
    }

    public BST(E[] objects) {
        this();
        for (E object : objects) {
            insert(object);
        }
    }

    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            if (c.compare(e, current.getElement()) < 0) {
                current = current.getLeft();
            } else if (c.compare(e, current.getElement()) > 0) {
                current = current.getRight();
            } else // element matches current.element
                return true; // Element is found
        }

        return false;
    }

    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (c.compare(e, current.getElement()) < 0) {
                    parent = current;
                    current = current.getLeft();
                } else if (c.compare(e, current.getElement()) > 0) {
                    parent = current;
                    current = current.getRight();
                } else
                    return false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.getElement()) < 0)
                parent.setLeft(createNewNode(e));
            else
                parent.setRight(createNewNode(e));
        }

        size++;
        return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    public List<E> inorder() {
        List<E> result = new ArrayList<>();
        if (size == 0) return result;
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.peek();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();
            if (left != null && !result.contains(left.getElement())) {
                stack.push(left);
            } else {
                stack.pop();
                result.add(current.getElement());
                if (right != null) {
                    stack.push(right);
                }
            }
        }
        return result;
    }

    public List<E> preorder() {
        List<E> result = new ArrayList<>();
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();

            result.add(current.getElement());
            if (right != null && !result.contains(right.getElement())) {
                stack.push(right);
            }
            if (left != null && !result.contains(left.getElement())) {
                stack.push(left);
            }
        }
        return result;
    }

    public List<E> postorder() {
        List<E> result = new ArrayList<>();
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.peek();
            TreeNode<E> left = current.getLeft();
            TreeNode<E> right = current.getRight();

            if (left != null && !result.contains(left.getElement())) {
                stack.push(left);
            } else if (right != null && !result.contains(right.getElement())) {
                stack.push(right);
            } else {
                result.add(stack.pop().getElement());
            }
        }
        return result;
    }

    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (c.compare(e, current.getElement()) < 0) {
                parent = current;
                current = current.getLeft();
            } else if (c.compare(e, current.getElement()) > 0) {
                parent = current;
                current = current.getRight();
            } else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.getLeft() == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.getRight();
            } else {
                if (c.compare(e, parent.getElement()) < 0)
                    parent.setLeft(current.getRight());
                else
                    parent.setRight(current.getRight());
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.getLeft();

            while (rightMost.getRight() != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.getRight(); // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.setElement(rightMost.getElement());

            // Eliminate rightmost node
            if (parentOfRightMost.getRight() == rightMost)
                parentOfRightMost.setRight(rightMost.getLeft());
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.setLeft(rightMost.getLeft());
        }

        size--; // Reduce the size of the tree
        return true; // Element deleted successfully
    }

    public void clear() {
        root = null;
        size = 0;
    }

}


