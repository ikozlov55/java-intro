package exercises.ch25.ex15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BSTex15<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size = 0;

    public BSTex15() {
    }

    public BSTex15(E[] objects) {
        this();
        for (E object : objects) {
            insert(object);
        }
    }

    public boolean insert(E e) {
        if (size == 0) {
            root = new TreeNode<>(e);
        } else {
            TreeNode<E> current = root;
            TreeNode<E> parent = null;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = parent.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = parent.right;
                } else {
                    return false;
                }
            }
            TreeNode<E> node = new TreeNode<>(e);
            node.parent = parent;
            if (e.compareTo(parent.element) < 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        size++;
        return true;
    }

    public boolean delete(E e) {
        if (size == 0) return false;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null) {
            return false;
        }
        if (current.left == null) {
            TreeNode<E> parent = current.parent;
            if (parent == null) {
                root = current.right;
                if (current.right != null) {
                    current.right.parent = null;
                }
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
                if (current.right != null) {
                    current.right.parent = parent;
                }
            }
        } else {
            TreeNode<E> rightMost = current.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;
            if (rightMost.parent.right == rightMost) {
                rightMost.parent.right = rightMost.left;
            } else {
                rightMost.parent.left = rightMost.left;
            }
        }

        size--;
        return true;
    }

    public void bft() {
        List<E> result = new ArrayList<>();
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();
            result.add(current.element);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        String items = result.stream()
                .map(E::toString)
                .collect(Collectors.joining(" "));
        System.out.printf("[ %s ]\n", items);
    }

    /**
     * Return the path of elements from the specified element
     * to the root in an array list.
     */
    public ArrayList<E> getPath(E e) {
        ArrayList<E> path = new ArrayList<>();
        TreeNode<E> current = getNode(e);
        if (current == null) {
            return path;
        }
        while (current != null) {
            path.add(current.element);
            current = current.parent;
        }
        return path;
    }

    /**
     * Return true if the node for the element is a leaf
     */
    public boolean isLeaf(E element) {
        TreeNode<E> node = getNode(element);
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    /**
     * Return the node for the specified element.
     * Return null if the element is not in the tree.
     */
    private TreeNode<E> getNode(E element) {
        if (size == 0) return null;
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

}

class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;
    protected TreeNode<E> parent;

    public TreeNode(E e) {
        element = e;
    }

}