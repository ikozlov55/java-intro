package exercises.ch25.ex08;

import exercises.ch25.base.BST;
import exercises.ch25.base.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class BSTWithListIterator<E> extends BST<E> {
    public BSTWithListIterator() {
        super();
    }

    public BSTWithListIterator(Comparator<E> c) {
        super(c);
    }

    public BSTWithListIterator(E[] objects) {
        super(objects);
    }

    @Override
    public ListIterator<E> iterator() {
        return new InorderListIterator();
    }

    private class InorderListIterator implements ListIterator<E> {

        private final List<E> list;
        private int current;

        public InorderListIterator() {
            list = new ArrayList<>();
            current = 0;
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            inorder(root.getLeft());
            list.add(root.getElement());
            inorder(root.getRight());
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            E result = list.get(current);
            current++;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public E previous() {
            current--;
            return list.get(current);
        }

        @Override
        public int nextIndex() {
            return current + 1;
        }

        @Override
        public int previousIndex() {
            return current - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("set not supported");
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("add not supported");
        }
    }
}
