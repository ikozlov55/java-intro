package exercises.ch26.ex04;

import exercises.ch25.base.TreeNode;

public class AVLTreeNode<E> extends TreeNode<E> {
    protected int height = 0;
    private AVLTreeNode<E> parent;

    public AVLTreeNode(E o) {
        super(o);
    }

    public AVLTreeNode<E> getLeft() {
        return (AVLTreeNode<E>) left;
    }

    public AVLTreeNode<E> getRight() {
        return (AVLTreeNode<E>) right;
    }

    public int getHeight() {
        return height;
    }

    public AVLTreeNode<E> getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode<E> parent) {
        this.parent = parent;
    }
}