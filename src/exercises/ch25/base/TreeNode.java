package exercises.ch25.base;

public class TreeNode<E> {
    protected E element;
    protected TreeNode<E> left;
    protected TreeNode<E> right;

    public TreeNode(E e) {
        element = e;
    }


    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public E getElement() {
        return element;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
